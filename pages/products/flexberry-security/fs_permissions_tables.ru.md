---
title: Flexberry Security
keywords: flexberry, security
sidebar: flexberry-security_sidebar
toc: false
permalink: ru/fs_permissions_tables.html
lang: ru
---

## Таблицы полномочий

### Описание таблиц полномочий

| Название  | Расшифровка | Описание |
| --------- | ----------- | ----------- |
| stormag   | Agent       | Расположение агентов: пользователей и ролей  |
| storms    | Subject     | Объекты данных, на которые будут навешиваться ограничения |
| stormp    | Permission  | Связующая таблица между агентом и объектом данных |
| stormac   | Access      | Таблица с типами доступа (чтение, запись и т.д). Привязывается к созданному Permission |

### Примеры скриптов добавления полномочий

Чтобы создать разрешение на какой-то объект для какого-то агента, нужно в правильном порядке создать записи в соответствующих таблицах.

1. Создаем агента

``` sql
INSERT INTO schema_name.stormag(primarykey, name, login, pwd, isuser, isgroup, isrole, connstring, enabled, email, comment)
VALUES ('some_generated_uuid_agent', 'agent_name', '', '', false, false, true, '', true, '', '');
```

2. Добавляем объект

``` sql
INSERT INTO schema_name.storms(primarykey, name, type, isattribute, isoperation, isview, isclass, sharedoper)
VALUES ('some_generated_uuid_object', 'object_name', 'type_name', false, false, false, true, false);
```

3. Соединяем агент и объект

``` sql
INSERT INTO schema_name.stormp(primarykey, subject_m0, agent_m0)
VALUES ('some_generated_uuid_permission', 'some_generated_uuid_object', 'some_generated_uuid_agent');
```

4. Устанавливаем уровень доступа

``` sql
INTO schema_name.stormac(primarykey, typeaccess, filter_m0, permition_m0)
VALUES ('some_generated_uuid_access', 'Read/Update/Delete/Full', NULL, 'some_generated_uuid_permission');
```
