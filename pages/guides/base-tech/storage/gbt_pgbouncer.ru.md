---
title: Liquibase
keywords: pgbouncer, sql, postgres, postgresql, pooling, connection, пул соединений
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_pgbouncer.html
lang: ru
---

## Описание

[PgBouncer](https://www.pgbouncer.org) - это сервис, обеспечивающий `connection pooling` к экземпляру PostgreSQL. PgBouncer работает как посредник между приложением и базой данных, "транслируя" запросы к нужной БД в экземпляре и используя соединения из пула. В данной статье рассматривается процесс настройки PgBouncer для работы с несколькими базами данных на примере типичного Flexberry-приложения.

## Установка PgBouncer

### Windows

Можно использовать [EDB PostgreSQL Installer](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) и выбрать PgBouncer при установке.

### Linux

- [Debian](https://www.scaleway.com/en/docs/tutorials/install-pgbouncer)
- [Fedora](https://src.fedoraproject.org/rpms/pgbouncer)
- [OpenSuse](https://software.opensuse.org/download.html?project=server%3Adatabase%3Apostgresql&package=pgbouncer)
- [Установка из исходников](https://www.pgbouncer.org/install.html)

### Docker

Рекомендуется использовать образ [edoburu/pgbouncer](https://hub.docker.com/r/edoburu/pgbouncer). Для работы образа необходимо поместить в образ файлы `/etc/pgbouncer/pgbouncer.ini` и `/etc/pgbouncer/userlist.txt`. Это можно сделать с помощью [docker volumes](https://docs.docker.com/storage/volumes) или создав собственный [Dockerfile](https://docs.docker.com/engine/reference/builder) (пример [PgBouncer Dockerfile](https://github.com/Flexberry/Flexberry.PgBouncer.Sample/blob/main/src/Docker/dockerfiles/Dockerfile.PgBouncer)).

### Конфигурация PgBouncer

Настройка PgBouncer осуществляется с помощью файла конфигурации `pgbouncer.ini`. Типичное Flexberry приложение включает в себя БД приложения и БД полномочий - поэтому в файле конфигурации в разделе `[databases]` указаны настройки для двух баз данных.

**pgbouncer.ini**
```ini
[databases]
appdb = host=localhost port=5432 user=appuser password=123app
securitydb = host=localhost port=5432 user=securityuser password=sec3r1ty pool_size=20

[pgbouncer]
pool_mode = transaction
listen_addr = *
listen_port = 6432
auth_file = /etc/pgbouncer/userlist.txt
auth_type = md5
admin_users = flexberryuser
logfile = pgbouncer.log
```

**userlist.txt**
```txt
"pgbounceruser" "12345"
"flexberryuser" "@dm1n"
```
Файл `userlist.txt` описывает учётные данные для подключения к PgBouncer. PgBouncer, в свою очередь, использует логин/пароль из файла конфигурации `pgbouncer.ini` для подключения к нужной БД. Если учётные данные не указаны в разделе `[databases]`, используются те, что указаны в `userlist.txt`.

> Есть возможность синхронизировать пользователей PgBouncer с пользователями БД - см. параметры `auth_file`, `auth_user`, `auth_query` в [документации](https://www.pgbouncer.org/config.html#authentication-settings) и [пример auth_query](https://www.pgbouncer.org/config.html#authentication-settings). Альтернативный способ синхронизации - записать пользователей БД в `userlist.txt`, таким образом пользователи PgBouncer будут совпадать.

#### Раздел databases

Раздел `[databases]` описывает базы данных, к которым PgBouncer подключает клиентов. В данном примере две базы: `appdb` и `securitydb` (БД приложения и БД полномочий `Flexberry`). Клиент должен указать нужную ему БД в строке подключения к PgBouncer (параметр `Database=appdb`). PgBouncer предоставит подключение из своего пула для соответствующей БД, используя учётные данные из раздела `[databases]` или файла `userlist.txt`. _Для каждого пользователя в БД используется собственный пул соединений._

Для баз данных можно задавать дополнительные настройки, такие как размер пула (`pool_size`), количество резервных соединений (`reserve_pool`), максимальное число соединений для всех пулов БД (`max_db_connections`) и [прочие](https://www.pgbouncer.org/config.html#section-databases).

#### Раздел pgbouncer

В разделе `[pgbouncer]` задаются общие настройки.

- [`pool_mode`](https://www.pgbouncer.org/features.html) - позволяет задать режим выделения пулов:

  - `session pooling` - создавать отдельное подключение к БД для каждого подключения к PgBouncer.
  - `transaction pooling` - отдельное подключение к БД выделяется только при выполнении команды (транзакции). Это **предпочтительный режим** в большинстве случаев.
  > `transaction pooling` полезен если приложение держит "висящие" сессии в состоянии `idle`. В таком случае, висящее соединение к PgBouncer не будет выделять реального подключения к БД в состоянии `idle`.
  - `statement pooling` - режим для работы `autocommit` в [PL/Proxy](https://plproxy.github.io).
- `auth_file` - путь к файлу со списком пользователей для подключения к PgBouncer. _Не стоит путать с пользователями для подключения к БД._
- `auth_type` - способ подключения к PgBouncer. _PgBouncer имеет реализацию входа через [TLS](https://www.pgbouncer.org/config.html#tls-settings)._
- `admin_users` - список пользователей, которые могут выполнять команды администратора в консоли PgBouncer.

Прочие полезные настройки:

- `server_lifetime` - время до автоматического закрытия соединений к БД (активных и неактивных). По умолчанию `1 час`. Позволяет закрывать старые соединения из пула, очищая кеш (вычислимых процедур, подготовленных операторов, и т.д.). Не нарушает работу действующих соединений клиентов.
- `server_idle_timeout` - время до автоматического закрытия неактивных соединений к БД (в состоянии `idle`). По умолчанию `10 минут`.

#### Подключение приложения Flexberry к PgBouncer

Чтобы подключить приложение к PgBouncer, необходимо изменить строки подключения сервисов данных. Строки подключения по умолчанию задаются в файле `App.config` в проекте `ODataBackend` бекенда. Во-первых, необходимо изменить строку для подключения к PgBouncer (по умолчанию параметр `DefConnStr`): `SERVER=localhost;Port=6432;User ID=pgbounceruser;Password=12345;Database=appdb` _(PgBouncer по умолчанию работает на порте 6432)_. Во-вторых, изменить строки подключения в других сервисах ([аудит](fau_audit-install.html) и [полномочия](fs_flexberry_security_update.html)).

### Полезные ссылки

- [Пример использования PgBouncer в приложении Flexberry](https://github.com/Flexberry/Flexberry.PgBouncer.Sample)
- [Документация по использованию PgBouncer (на русском)](https://postgrespro.ru/docs/postgrespro/10/pgbouncer)
- [Официальная документация (на английском)](https://www.pgbouncer.org/config.html)
