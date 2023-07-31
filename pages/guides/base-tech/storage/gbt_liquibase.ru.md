---
title: Liquibase
keywords: liquibase, cli, docker, sql, postgres, postgresql, mssql, flexberry enterprise
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase.html
lang: ru
---

## Описание

Liquibase - это библиотека с открытым исходным кодом для отслеживания, управления и применения изменений схемы базы данных. Является аналогом Git для баз данных. Вместо "коммитов" - changesets, представляющие наборы SQL команд на изменение БД.

Использование Liquibase в вашем проекте позволяет отслеживать изменения схем БД, откатывать (rollback) базы до нужного состояния, облегчает развертывание скриптов на разных серверах.

> [Flexberry Designer](fd_flexberry-designer.html) поддерживает генерацию скриптов Liquibase для СУБД `PostgreSQL` и `Microsoft SQL Server`.

### Установка Liquibase

Liquibase позволяет запускать команды через свой CLI. В качестве альтернативы, можно запускать CLI в виде Docker контейнера.
- [Скачать Liquibase CLI](https://www.liquibase.com/download).
- [Использование Liquibase в Docker](https://docs.liquibase.com/workflows/liquibase-community/using-liquibase-and-docker.html)

### Конфигурация Liquibase на проекте

1. Инициализировать Liquibase в корневой папке вашего проекта: `liquibase init project` (следовать инструкциям на экране). При создании проекта, требуется указать адрес БД в формате JDBC URL, и данные для подключения (логин и пароль).
> Настройки сохраняются в файл `liquibase.properties`. Настройки применяются по умолчанию. Этот файл может быть использован, чтобы изменить подключение или схему, на которой будут запущены скрипты. Более детально см. [официальную документацию.](https://docs.liquibase.com/concepts/connections/creating-config-properties.html)
2. Добавить ссылки на папки со скриптами, которые необходимо отслеживать Liquibase:
    1. В файле `liquibase.properties` указать название корневого changelog: `changeLogFile=liquibase.json`
    2. В корневой changelog `liquibase.json` добавить ссылки на отслеживаемые папки:

    ```json
    {
        "databaseChangeLog": [
            {
                "includeAll": {
                    "path": "путь/к/папке/со/скриптами/SQL"
                }
            },
            {
                "includeAll": //...
            }
        ]
    }
    ```

3. Если Liquibase устанавливается в существующее приложение и имеет скрипты, примененные вручную, необходимо отметить эти скрипты как выполненные:
   - `liquibase changelog-sync` отметит существующие скрипты в папках как "применённые", сохранив информацию о них в таблицу `databasechangelog`; - после этого командой `update` будут применяться только новые скрипты

#### Рекомендации

Рекомендуется для liquibase указывать отдельную схему для хранения таблиц `databasechangelog` и `databasechangeloglock`:
1. Указать название схемы `liquibase` для хранения внутренних таблиц Liquibase. В файл `liquibase.properties` добавить строчку:
`liquibase.liquibaseSchemaName=liquibase`
2. Создать схему `liquibase` в БД (если скрипты будут применяться на разных БД, необходимо создать схему в каждой БД):

```sql
CREATE SCHEMA liquibase AUTHORIZATION postgres;
```

### Создание скриптов

Для описания изменений в Liquibase используются текстовые файлы [changelog](https://docs.liquibase.com/concepts/changelogs/home.html). Файл содержит SQL-изменения базы данных в специальном liquibase-формате (xml, yaml, json, sql - на выбор). Рекомендуется использовать [liquibase formatted sql](https://docs.liquibase.com/concepts/changelogs/sql-format.html), т.к. именно его генерирует Flexberry Designer.

Скрипты для Liquibase можно генерировать, используя `Flexberry Designer`:
1. [Desktop версия](https://flexberry.github.io/ru/fd_flexberry-designer.html)
  - Выбрать стадию -> Кликнуть правой кнопкой мыши -> `ORM` -> `SQL` -> `PostgreSQL` -> `Сгенерировать SQL для liquibase`

2. [Online версия](https://flexberry.github.io/ru/fdo_landing_page.html)
  - Перейти в раздел `Генерация` -> для нужного типа хранилища отметить `"Сгенерировать SQL для liquibase"`:
![Настройка через интерфейс](/images/pages/guides/base-technologies/storage/liquibaseExample.jpg)
  - Нажать `"Скачать fdg файл"`. Данный файл будет иметь название `GenConfig.fdg`, в нём появится опция `"LiquibaseSQL": true` в разделе, который соответствует выбранной СУБД (в данном примере - `PostgreSQL`). Если эта настройка в файле отсутствует, необходимо добавить её вручную:

  ```json
  "Storage": {
      "PostgreSql": {
          "LiquibaseSql": true,
          ...
      }
  }
  ```

  > Подробнее в [практическом руководстве по созданию приложения с помощью Flexberry Designer Enterprise](../../practical-guides/flexberry-designer-enterprise/gpg_practical-guide.md)

Есть возможность написать скрипт самостоятельно. Для этого необходимо в начало вашего обычного `.sql` скрипта добавить следующие строчки:
- `--liquibase formatted sql`
- `--changeset author:id` - где `author` - автор изменений, `id` - уникальный идентификатор набора изменений _(рекомендуется использовать текущую дату и время)_.

### Запуск команд Liquibase

После того, как конфигурация проекта Liquibase завершена, можно запускать команды. Команды запускаются с помощью CLI, который устанавливается вместе с Liquibase.

Команды можно запускать следующим образом:

```sh
liquibase update --log-level INFO
```

> Команды необходимо запускать из папки, в которой был инициализирован liquibase (корневая папка).

Команда `liquibase update` применит изменения, на которые есть ссылка в корневом changelog. Список полезных команд:
- `update` - применить все скрипты
- `update-sql` - предпросмотр SQL для команды `update` _(не применяет скрипты)_
- `status` - отобразить количество ещё не запущенных скриптов
- `validate` - проверить корректность скриптов
> [Ссылка на полный список команд](https://docs.liquibase.com/commands/home.html)

### Запуск команд Liquibase через Docker

Есть возможность запускать команды Liquibase через Docker - т.е. без установки Liquibase CLI. Для этого необходимо использовать следующую команду:

```sh
docker run --rm -v ${PWD}/:/liquibase/changelog/ liquibase/liquibase --defaultsFile=/liquibase/changelog/liquibase.properties --changelog-file=liquibase.json --search-path=/liquibase/changelog/
```

Описание команды:
- `--rm` - удалить контейнер при завершении
- `-v ${PWD}/:/liquibase/changelog/` - монтировать текущую директорию в папку `/liquibase/changelog/` внутри образа
- `liquibase/liquibase` - использовать официальный образ Liquibase CLI
- `--defaultsFile=/liquibase/changelog/liquibase.properties` - путь до файла конфигурации `liquibase.properties`
- `--changelog-file=liquibase.json` - путь до корневого changelog
- `--search-path=/liquibase/changelog/` - путь, в котором будет производиться поиск changelog'ов

> Если в контейнере папка `/liquibase/changelog/` становится пустой, возможно вы запускаете команду из Windows через Git Bash. В таком случае, перед запуском задайте ENV переменную
**MSYS_NO_PATHCONV=1**

Подробнее см. [Использование Liquibase в Docker.](https://docs.liquibase.com/workflows/liquibase-community/using-liquibase-and-docker.html)

### Откат изменений

Liquibase позволяет откатывать изменения. Это можно сделать с помощью команд `rollback`, `rollback-to-date`, `rollback-count` (см. [rollback](https://docs.liquibase.com/commands/home.html#database-rollback-commands)). Но стоит отметить, что "из коробки" откат изменений работать не будет. Для каждого скрипта необходимо указывать команды для отката (см. [rollback actions](https://docs.liquibase.com/concepts/changelogs/sql-format.html#rollback-actions)). Flexberry Designer на текущий момент не генерирует команд для отката.

### Использование скриптов в нескольких БД

Вашему приложению может потребоваться использовать несколько баз данных, и развёртывать скрипты на разных БД (или разных схемах одной БД). Для этого можно воспользоваться контекстами в Liquibase.

*Контекст* - это группа, в которую помещаются changelog'и для того, чтобы фильтровать скрипты по контексту и применять только нужные вам изменения ([подробнее](https://docs.liquibase.com/concepts/changelogs/attributes/contexts.html)). Можно применить скрипты из контекста на определённой БД.

- Каждый скрипт из проекта может быть отнесен к своему контексту.

Чтобы отнести файл/папку к контексту, добавьте параметр `contextFilter` в ваш changelog:

```json
"includeAll": {
    "path": "путь/к/папке/со/скриптами",
    "contextFilter": "название_контекста"
}
```

Параметр задаёт, к какому контексту отнести нужный файл/папку.

- Скрипты из контекста можно применить на другой БД и другой схеме.

Следующие параметры могут быть использованы, чтобы применить скрипты на нужной БД:
  - `--contexts контекст` - запустить команду `liquibase` только по скриптам из указанного контекста;
  - `--url=jdbc:postgresql://адрес:порт/название_бд` - применить скрипты на указанной БД;
  - `--default-schema-name схема` - применить скрипты на указанной схеме БД.

> **Пример.** Представим - приложение имеет 2 БД - основную и `storm` (для хранения полномочий и настроек). Чтобы применять скрипты на двух базах, необходимо добавить 2 папки: `/SQL/app` - для скриптов основного приложения; `/SQL/storm` - для скриптов полномочий. Каждую папку необходимо отнести к своему контексту (см. раздел *использование нескольких БД*). `/SQL/app` - отнести к контексту `core`, `/SQL/storm` - к контексту `storm`. Затем, необходимо запустить команду `liquibase update --contexts storm --url=jdbc:postgresql://localhost:5432/storm` чтобы применить полномочия на БД `storm`, и команду `liquibase update --contexts core --url=jdbc:postgresql://localhost:5432/app` - чтобы применить скрипты приложения на основной БД.

> Чтобы упростить жизнь разработчиков, можно написать `cmd` или `sh` скрипт, который будет подставлять нужный url БД в зависимости от переданного контекста. Вызывать его, например, так: `./liquibase.sh update storm` - без необходимости прописывать url вручную. [Пример такого скрипта liquibase.sh](https://gist.github.com/turbcool/969c545421cc0d8b43fa8b8c391e6571).

## Дополнительно

* [Использование Liquibase в Azure Pipelines](./gbt_liquibase_azure.ru) <i class="fa fa-arrow-up" aria-hidden="true"></i>

## Ресурсы

* [Официальная документация Liquibase](https://docs.liquibase.com/start/home.html)
