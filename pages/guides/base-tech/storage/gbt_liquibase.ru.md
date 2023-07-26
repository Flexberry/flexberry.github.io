---
title: Liquibase
keywords: liquibase, cli, docker, sql, postgres, postgresql, mssql, flexberry enterprise
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_liquibase.html
lang: ru
---

## Описание
Liquibase - это библиотека с открытым исходным кодом для отслеживания, управления и применения изменений схемы базы данных. Является аналогом Git для баз данных. Вместо "коммитов" - changeset'ы, представляющие наборы SQL команд на изменение БД.

Использование Liquibase в вашем проекте позволяет отслеживать изменения схем БД, откатывать (rollback) их до нужного состояния, облегчает развертывание скриптов на разных серверах.

> Flexberry Designer поддерживает генерацию скриптов Liquibase для СУБД `PostgreSQL` и `Microsoft SQL Server`.

### Установка Liquibase
Liquibase позволяет запускать команды через свой CLI. В качестве альтернативы, можно запускать CLI в виде Docker контейнера.
- [Скачать Liquibase CLI](https://www.liquibase.com/download).
- [Использование Liquibase в Docker](https://docs.liquibase.com/workflows/liquibase-community/using-liquibase-and-docker.html)

### Конфигурация Liquibase на проекте
1. Инициализируйте Liquibase в корневой папке вашего проекта: `liquibase init project` (следуйте инструкциям на экране). При создании проекта, вам нужно будет указать адрес БД в формате JDBC URL, и данные для подключения (логин и пароль).
> Настройки сохраняются в файл `liquibase.properties`. Настройки применяются по умолчанию. Вы можете использовать этот файл, чтобы изменить подключение или схему, на которой будут запущены скрипты. Более детально см. [официальную документацию.](https://docs.liquibase.com/concepts/connections/creating-config-properties.html)
2. Добавьте ссылки на папки со скриптами, которые необходимо отслеживать Liquibase:
    1. В файле `liquibase.properties` укажите название корневого changelog: `changeLogFile=liquibase.json`
    2. В корневой changelog `liquibase.json` добавьте ссылки на отслеживаемые папки:
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
3. Если вы устанавливаете Liquibase в существующее приложение, и у вас уже имеется БД и скрипты (которые уже были применены вручную), вам необходимо отметить их как выполненные:
   - `liquibase changelog-sync` отметит существующие скрипты в папках как "применённые", сохранив информацию о них в таблицу `databasechangelog`; - команды `update` будут применять только новые скрипты

#### Рекомендации
Рекомендуется для liquibase указывать отдельную схему для хранения таблиц `databasechangelog` и `databasechangeloglock`:
1. Укажите название схемы `liquibase` для хранения внутренних таблиц Liquibase. В файл `liquibase.properties` добавьте строчку:
`liquibase.liquibaseSchemaName=liquibase`
2. Создайте схему `liquibase` в БД (если скрипты будут применяться на разных БД, необходимо создать схему в каждой БД):
```sql
CREATE SCHEMA liquibase AUTHORIZATION postgres;
```

### Создание скриптов
Скрипты могут быть оформлены в 4х форматах (json/sql/xml/yaml), но рекомендуется использовать [liquibase formatted sql](https://docs.liquibase.com/concepts/changelogs/sql-format.html), т.к. именно его генерирует Flexberry Designer.

Оформленные скрипты можно генерировать, используя `Flexberry Designer`:
1. [Desktop версия](https://flexberry.github.io/ru/fd_flexberry-designer.html)
  - Выберите стадию -> Кликните правой кнопкой мыши -> `ORM` -> `SQL` -> `PostgreSQL` -> `Сгенерировать SQL для liquibase`

2. [Online версия](https://flexberry.github.io/ru/fdo_landing_page.html)
  - Перейдите в раздел `Генерация` -> для нужного типа хранилища отметьте `"Сгенерировать SQL для liquibase"`:
![Настройка через интерфейс](/images/pages/guides/base-technologies/storage/liquibaseExample.jpg)
  - Нажите `"Скачать fdg файл"`. Данный файл будет иметь название `GenConfig.fdg`, в нём появится опция `"LiquibaseSQL": true` в разделе, который соответствует выбранной СУБД (в данном примере - `PostgreSQL`). Если эта настройка в файле отсутствует, необходимо добавить её вручную:
  ```json
  "Storage": {
      "PostgreSql": {
          "LiquibaseSql": true,
          ...
      }
  }
  ```
  > Подробнее в [практическом руководстве по созданию приложения с помощью Flexberry Designer Enterprise](../../practical-guides/flexberry-designer-enterprise/gpg_practical-guide.md)

Есть возможность написать скрипт самостоятельно. Для этого необходимо в начало вашего `.sql` скрипта добавить следующие строчки:
- `--liquibase formatted sql`
- `--changeset author:id` - где `author` - автор изменений, `id` - уникальный идентификатор набора изменений _(рекомендуется использовать текущую дату и время)_.

### Запуск команд Liquibase
После того, как вы завершили конфигурацию проекта Liquibase, подключив необходимые скрипты, вы можете использовать Liquibase CLI.

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
Есть возможность запускать команды Liquibase через Docker - т.е. без установки Liquibase CLI. Для этого используйте следующую команду:
```sh
docker run --rm -v ${PWD}/:/liquibase/changelog/ liquibase/liquibase --defaultsFile=/liquibase/changelog/liquibase.properties --changelog-file=liquibase.json --search-path=/liquibase/changelog/
```
Описание команды:
- `--rm` - удаляем контейнер при завершении
- `-v ${PWD}/:/liquibase/changelog/` - монтируем текущую директорию в папку `/liquibase/changelog/` внутри образа
- `liquibase/liquibase` - используем официальный образ Liquibase CLI
- `--defaultsFile=/liquibase/changelog/liquibase.properties` - указываем путь до файла конфигурации `liquibase.properties`
- `--changelog-file=liquibase.json` - указываем путь до корневого changelog
- `--search-path=/liquibase/changelog/` - указываем путь, в котором будет производиться поиск changelog'ов

> Если в контейнере папка /liquibase/changelog/ становится пустой, возможно вы запускаете команду из Windows через Git Bash. В таком случае, перед запуском задайте ENV переменную
**MSYS_NO_PATHCONV=1**

Подробнее см. [Использование Liquibase в Docker.](https://docs.liquibase.com/workflows/liquibase-community/using-liquibase-and-docker.html)

### Откат изменений
Liquibase позволяет откатывать изменения. Это можно сделать с помощью команд `rollback`, `rollback-to-date`, `rollback-count` (см. [rollback](https://docs.liquibase.com/commands/home.html#database-rollback-commands)). Но стоит отметить, что "из коробки" откат изменений работать не будет. Для каждого скрипта необходимо указывать команды для отката (см. [rollback actions](https://docs.liquibase.com/concepts/changelogs/sql-format.html#rollback-actions)). Flexberry Designer на текущий момент не генерирует команд для отката.

### Использование скриптов в нескольких БД
Вашему приложению может потребоваться использовать несколько баз данных, и развёртывать скрипты на разных БД (или разных схемах одной БД). Для этого вы можете воспользоваться контекстами в Liquibase.

*Контекст* - это группа, в которую помещаются changelog'и для того, чтобы фильтровать скрипты по контексту и применять только нужные вам изменения ([подробнее](https://docs.liquibase.com/concepts/changelogs/attributes/contexts.html)). Можно применить скрипты из контекста на определённой БД.

- В вашем проекте, каждый скрипт может быть отнесен к контексту.

Чтобы отнести файл/папку к контексту, добавьте параметр `contextFilter` в ваш changelog:
```json
"includeAll": {
    "path": "путь/к/папке/со/скриптами",
    "contextFilter": "название_контекста"
}
```
Параметр задаёт, к какому контексту отнести нужный файл/папку.

- Скрипты из контекста можно применить на другой БД и другой схеме.

Используйте комбинацию из следующих параметров для команд Liquibase, чтобы применить скрипты на нужной БД:
  - `--contexts контекст` - запустить команду `liquibase` только по скриптам из указанного контекста;
  - `--url=jdbc:postgresql://адрес:порт/название_бд` - применить скрипты на указанной БД;
  - `--default-schema-name схема` - применить скрипты на указанной схеме БД.

> **Пример.** Представим - ваше приложение имеет 2 БД - основную и `storm` (для хранения полномочий и настроек). Чтобы применять скрипты на двух базах, необходимо добавить 2 папки: `/SQL/app` - для скриптов основного приложения; `/SQL/storm` - для скриптов полномочий. Каждую папку отнесём к своему контексту (см. раздел *использование нескольких БД*). `/SQL/app` - отнесём к контексту `core`, `/SQL/storm` - к контексту `storm`. Затем, вы можете запустить команду `liquibase update --contexts storm --url=jdbc:postgresql://localhost:5432/storm` чтобы применить полномочия на БД `storm`, и команду `liquibase update --contexts core --url=jdbc:postgresql://localhost:5432/app` - чтобы применить скрипты приложения на основной БД.

> Чтобы упростить жизнь разработчиков, вы можете написать `cmd` или `sh` скрипт, который будет подставлять нужный url БД в зависимости от переданного контекста. Вызывать его, например, так: `./liquibase.sh update storm` - без необходимости прописывать url вручную. [Пример скрипта liquibase.sh](https://gist.github.com/turbcool/969c545421cc0d8b43fa8b8c391e6571).

## Дополнительно
* [Использование Liquibase в Azure Pipelines](./gbt_liquibase_azure.ru) <i class="fa fa-arrow-up" aria-hidden="true"></i>

## Ресурсы
* [Официальная документация Liquibase](https://docs.liquibase.com/start/home.html)
