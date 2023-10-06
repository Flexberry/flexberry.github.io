---
title: Liquibase
keywords: pgbouncer, sql, postgres, postgresql, pooling, connection, пул соединений
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_pgbouncer.html
lang: ru
---

## Описание
PgBouncer - это сервис, обеспечивающий `connection pooling` к экземпляру PostgreSQL. PgBouncer работает как посредник между приложением и базой данных, "транслируя" запросы к нужной БД в экземпляре и используя соединения из пула. Прикладное приложение подключается к `PgBouncer` (порт `6432` по умолчанию).

## Установка PgBouncer

### Windows
Можно использовать [EDB PostgreSQL Installer](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) и выбрать PgBouncer при установке.

### Linux
- [Debian](https://www.scaleway.com/en/docs/tutorials/install-pgbouncer)
- [Fedora](https://src.fedoraproject.org/rpms/pgbouncer)
- [OpenSuse](https://software.opensuse.org/download.html?project=server%3Adatabase%3Apostgresql&package=pgbouncer)
- [Установка из исходников](https://www.pgbouncer.org/install.html)

### Docker
Рекомендуется использовать образ [edoburu/pgbouncer](https://hub.docker.com/r/edoburu/pgbouncer). Для работы образа, необходимо поместить в образ файлы `/etc/pgbouncer/pgbouncer.ini` и `/etc/pgbouncer/userlist.txt`. Это можно сделать с помощью docker volumes или создав собственный Dockerfile.

### Конфигурация PgBouncer
Настройка PgBouncer осуществляется с помощью файла конфигурации `pgbouncer.ini`. Пример конфигурации.

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
Файл `userlist.txt` описывает учётные данные для подключения к PgBouncer. Для подключения к БД PgBouncer использует логин/пароль из файла конфигурации.

> Есть возможность синхронизировать пользователей PgBouncer с пользователями БД - см. параметры `auth_file`, `auth_user`, `auth_query` в [документации](https://www.pgbouncer.org/config.html#authentication-settings) и [пример auth_query](https://www.pgbouncer.org/config.html#authentication-settings).

#### Раздел databases
Раздел `[databases]` описывает базы данных, к которым PgBouncer подключает клиентов. В данном примере две базы: `appdb` и `securitydb`. При подключении к PgBouncer, клиент должен указать БД: `SERVER=localhost;Port=6432;User ID=pgbounceruser;Password=12345;Database=appdb`. PgBouncer предоставит подключение из своего пула для соответствующей БД. _Для каждого пользователя в БД используется собственный пул соединений._

Для баз данных можно задавать дополнительные настройки, такие как размер пула (`pool_size`), количество резервных соединений (`reserve_pool`), максимальное число соединений для всех пулов БД (`max_db_connections`) и [прочие](https://www.pgbouncer.org/config.html#section-databases).

#### Раздел pgbouncer
В разделе `[pgbouncer]` задаются общие настройки.

- [`pool_mode`](https://www.pgbouncer.org/features.html) - позволяет задать режим выделения пулов:
  - `session pooling` - создавать отдельное подключение к БД для каждого подключения к PgBouncer.
  - `transaction pooling` - отдельное подключение к БД выделяется только при выполнении команды (транзакции). Это предпочтительный режим в большинстве случаев.
  > `transaction pooling` полезен если приложение держит "висящие" сессии в состоянии `idle`. В таком случае, висящее соединение к PgBouncer не будет выделять реального подключения к БД в состоянии `idle`.
  - `statement pooling` - режим для работы `autocommit` в [PL/Proxy](https://plproxy.github.io).
- `auth_file` - путь к файлу со списком пользователей для подключения к PgBouncer. _Не стоит путать с пользователями для подключения к БД._
- `auth_type` - способ подключения к PgBouncer. _PgBouncer имеет реализацию входа через [TLS](https://www.pgbouncer.org/config.html#tls-settings)._
- `admin_users` - список пользователей, которые могут выполнять команды администратора в консоли PgBouncer.

Прочие полезные настройки:
- `server_lifetime` - время до автоматического закрытия соединений к БД (активных/неактивных). По умолчанию `1 час`. Позволяет закрывать старые соединения из пула, очищая кеш (вычислимых процедур, подготовленных операторов, и т.д.). Не нарушает работу активных соединений клиентов.
- `server_idle_timeout` - время до автоматического закрытия неактивных соединений к БД (в состоянии `idle`). По умолчанию `10 минут`.

### Полезные ссылки
- [Пример использования PgBouncer в приложении Flexberry](https://github.com/Flexberry/Flexberry.PgBouncer.Sample)
- [Документация по использованию PgBouncer (на русском)](https://postgrespro.ru/docs/postgrespro/10/pgbouncer)
- [Официальная документация (на английском)](https://www.pgbouncer.org/config.html)
