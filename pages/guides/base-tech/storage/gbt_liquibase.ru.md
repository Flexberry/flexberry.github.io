---
title: Liquibase
keywords: Programming, liquibase
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase.html
lang: ru
---

## Краткое описание

Большинство приложений разрабатывается командой из нескольких человек и имеет несколько стендов. Например, стенд разработки, пре-прод и прод. В связи с этим встаёт вопрос о безопасной и бесконфликтной синхронизации схем БД. С этим успешно справляется библиотека `Liquibase`, которую поддерживает технология [Flexberry](https://flexberry.net/).

* [Liquibase](https://www.liquibase.org/) - независимая от базы данных библиотека с открытым исходным кодом для отслеживания, управления и применения изменений схемы базы данных.

## Пример использования

При обновлении диаграмм в дизайнере (добавление новых, изменение сущствующих классов) необходимо также изменить и структуру существующей базы данных. Для этого в технологии есть возможность сгенерировать как полноценные SQL-скрипты для всей БД, так и SQL-скрипты для обновления схемы с помощью `liquibase`.

В данный момент поддерживается генерация SQL-скриптов под работу с liquibase для СУБД `PostgreSQL` и `Microsoft SQL Server`. В качестве примера ниже рассматривается генерация для `PostgreSQL`.

### Генерация из flexberry-designer

1) [Десктопная версия](https://flexberry.github.io/ru/fd_flexberry-designer.html)

Для генерации файла с SQL-скриптами в десктопной версии дизайнера, необходимо нажать правой кнопкой мыши на стадию и перейти по следующему пути:
- **`ORM -> SQL -> PostgreSQL -> Сгенерировать SQL для liquibase`**

2) [Веб версия](https://flexberry.github.io/ru/fdo_landing_page.html)

В разделе **Генерация** главного меню можно указать настройки для генерации приложения, в том числе и настройки генерации для хранилищ данных.

- Для генерации файла с SQL-скриптами в веб версии дизайнера, необходимо для нужного типа хранилища выбрать пункт **"Сгенерировать SQL для liquibase"**

![Настройка через интерфейс](/images/pages/guides/base-technologies/storage/liquibaseExample.jpg)

- Альтернативный вариант - в этом же разделе нажать **"Скачать fdg файл"** (Обычно размещается в корневой папке проекта). Данный файл будет иметь название `GenConfig.fdg`, в нём должно быть прописано `"LiquibaseSQL": true` в разделе, который соответствует выбранной СУБД (в данном примере - `PostgreSQL`). Если эта настройка в файле отсутствует, то необходимо прописать её вручную:

```
"Storage": {
    "PostgreSql": {
        "LiquibaseSql": true,
        ...
    }
}
```

### Описание изменений

Для описания изменений предназначен текстовый файл [changelog](https://docs.liquibase.com/concepts/changelogs/home.html). Разработчик записывает все SQL-изменения базы данных в данном файле в специальном liquibase-формате(xml, yaml, json, sql - на выбор). Один набор изменений называется `changeset`. В нём содержится информация о входящих в конкретную транзакцию обновлениях. Помимо описания наборов изменений файл содержит метаданные, а именно:
- `id` - идентификатор для конкретного набора изменений
- `author` - автор изменений

Существует несколько вариантов использования данного файла:

1) Если изменений немного, то SQL-скрипт можно поместить прямо внутри данного файла:
```xml
<changeSet id="add-rating-1" author="flexberry-user">
 <sql>alter table users add (rating int)</sql>
</changeSet>
```

2) Если SQL-скрипт имеет значительный объём, то выгоднее вынести его в отдельный файл, зафиксировав путь до него:
```xml
<changeSet id="add-rating-2" author="flexberry-user">
 <sqlFile path="sql/add_rating.sql" />
</changeSet>
```

3) Альтернативный вариант - указать директорию, в которой будут храниться все SQL-скрипты:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
        http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.3.xsd">
    <includeAll path="changes/stage"/>
</databaseChangeLog>
```

В этом случае в директории `changes/stage` должны располагаться файлы `.sql`, первые две строки внутри каждого из которых представляют собой форматированные комментарии:

```sql
--liquibase formatted sql
--changeset author:id
alter table users add (rating int);
create table workers ...
```

- `author` и `id` - те же метаданные, уникальные для каждого SQL-файла

### Обновление БД

Для обновления схемы базы данных нужно убедиться, что установлен сам [liquibase](https://docs.liquibase.com/start/install/home.html), а затем воспользоваться командой, выполнив её в терминале:
```
liquibase --defaultsFile=changelog/liquibase.properties --log-level=INFO update
```

Здесь `liquibase.properties` - файл с основными настройками:

```
classpath: /liquibase/changelog
url: jdbc:postgresql://example-database:5432/example
changelog-file: changelog.xml
username: postgres
password: password
liquibase.liquibaseSchemaName: liquibase
liquibase.hub.mode=off
```

Более детально о создании и конфигурациях данного файла - в [официальной документации.](https://docs.liquibase.com/concepts/connections/creating-config-properties.html)

- Если необходимо обновить несколько схем сразу, то для каждой из них необходимо выполнить аналогичную команду. Можно объединить команды для разных схем в единый .sh файл. Например, синтаксис для выполнения в терминале PowerShell с помощью docker:

```
docker run --rm -it -e TZ=Asia/Yekaterinburg --network=dev -v ${PWD}:/liquibase/changelog liquibase/liquibase:4.9 sh /liquibase/changelog/database.sh
```
- `database.sh` - рассматриваемый файл с командами для обновления

## Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Документация</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://docs.liquibase.com/home.html">Liquibase documentation</a></li>
                </div>   
                <div>
                    <li><a href="https://www.liquibase.org/">Official site</a></li>
                </div>
            </div>
        </div>
    </div>
</div>