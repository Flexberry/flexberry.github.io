---
title: Liquibase
keywords: Programming, liquibase
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase.html
lang: ru
---

## Краткое описание

Большинство приложений разрабатывается командой из нескольких человек и имеет несколько стендов, например, стенд разработки, пре-прод и прод. В связи с этим встаёт вопрос о безопасной и бесконфликтной синхронизации схем БД. С этим успешно справляется библиотека `Liquibase`, которую поддерживает наша технология.

**`Liquibase`** - независимая от базы данных библиотека с открытым исходным кодом для отслеживания, управления и применения изменений схемы базы данных.

## Пример использования
В качестве примера рассматривается генерация для `PostgreSQL`.

### Генерация из flexberry-designer

1) Десктопная версия flexberry-дизайнера

Для того, чтобы сгенерировать SQL для liquibase в оффлайн версии дизайнера, необходимо нажать правой кнопкой мыши на стадию и перейти по следующему пути:
- **`ORM -> SQL -> PostgreSQL -> Сгенерировать SQL для liquibase`**

2) Веб версия flexberry-дизайнера

Для того, чтобы сгенерировать SQL для liquibase в онлайн версии дизайнера, необходимо, чтобы в файле `GenConfig.fdg` было указано `"LiquibaseSQL": true` в соответствующем разделе:

```
"Storage": {
    "PostgreSql": {
        "LiquibaseSql": true,
        ...
    }
}
```

Задать эту настройку можно и через интерфейс, выбрав соответствующий пункт:

![Настройка через интерфейс](/images/pages/guides/base-technologies/storage/liquibaseExample.jpg)


- Также не стоит забывать о том, что в веб версии дизайнера можно получить изменения, генерируя код лишь для выбранных классов, а не для всей стадии целиком.

### Описание изменений

Для описания изменений предназначен файл `changelog.xml`. Помимо описания изменений файл содержит метаданные, а именно:
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

Для обновления базы данных можно воспользоваться командой:
- `liquibase --defaultsFile=changelog/liquibase.properties --log-level=INFO update`

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
                    <li><a href="https://www.liquibase.org/">Official site</li>
                </div>
            </div>
        </div>
    </div>
</div>