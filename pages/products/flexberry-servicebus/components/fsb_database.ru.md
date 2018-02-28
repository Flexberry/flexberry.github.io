---
title: База данных Flexberry Service Bus
sidebar: flexberry-servicebus_sidebar
keywords: Шина, БД
toc: true
permalink: ru/fsb_database.html
lang: ru
summary: Описание базы данных сервисной шины предприятия.
---

## Введение

База данных используется для хранения следующей информации:
* Сообщения, передаваемые клиентам (сохраняются для обеспечения гарантированной доставки)
* Настройки, необходимые ддя работы шины (информация о клиентах, подписках и т.д.)
* Статистика передачи данных

## Структура данных

Скрипты для создания БД достуны в [репозитории сервиса шины](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry%20Service%20Bus/SQL).

## Docker-образ

Для удобного использования БД Flexberry Service Bus автоматически собирается Docker-образ [flexberry-service-bus-postgres-db](https://hub.docker.com/r/flexberry/flexberry-service-bus-postgres-db).

## Поддерживаемые хранилища

Сервис шины и административное приложение используют для доступа к данным Flexberry ORM, соответственно, для функционирования доступны все, поддерживаемые со стороны Flexberry ORM типы СУБД:
* Postgre SQL
* Microsoft SQL Server
* Oracle
* и др.

Скрипты создания структуры БД под конкретное хранилище можно сгенерировать из Flexberry Designer по [UML-модели](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry%20Service%20Bus) (файл `Flexberry Service Bus.crp`).

Настройка для использования выполняется по [инструкции к Flexberry ORM](fo_ds-provider.html).