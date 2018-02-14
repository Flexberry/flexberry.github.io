---
title: Разработка Flexberry Service Bus
keywords: ESB, Development
sidebar: flexberry-servicebus_sidebar
toc: true
permalink: ru/fsb_development.html
lang: ru
summary: Функциональная подсистема платформы Flexberry - Flexberry Service Bus.
---

## Введение

Разработка `Flexberry Service Bus` ведётся на GitHub в открытых репозиториях как силами команды платформы Flexberry, так и при поддержке энтузиастов из сообщества.  

## Сервис шины

Адрес репозитория на GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus>.  
Репозиторий с основными компонентами сервиса `Flexberry Service Bus`, cодержит решение :

* `NewPlatform.Flexberry.ServiceBus.sln` - решение, включающее следующие проекты:
  * `NewPlatform.Flexberry.ServiceBus` - проект с реализацией компонентов.
  * `NewPlatform.Flexberry.ServiceBus.Objects` - проект с объектоной моделью.
  * `NewPlatform.Flexberry.ServiceBus.Components` - проект с интерфейсами компонентов.
  * `NewPlatform.Flexberry.ServiceBus.ClientTools` - проект с публичными интерфейсами.
  * `NewPlatform.Flexberry.ServiceBus.ConsoleHost` - проект с консольным приложением.
  * `NewPlatform.Flexberry.ServiceBus.WinServiceHost` - проект с приложением в виде службы.
  * `NewPlatform.Flexberry.ServiceBus.Tests` - проект с unit-тестами.
  * `NewPlatform.Flexberry.ServiceBus.IntegratedTests` - проект с интеграционными тестами.

В этом же репозитории располагаются nuspec-файлы, которые используются для создания NuGet-пакетов шины, использующихся в административном приложении.

SQL-скрипты для создания базы данных сервисной шины также располагаются в этом репозитории: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Flexberry%20Service%20Bus/SQL>.

Файлы конфигурации и скрипты, использующиеся для создания Docker-образов `flexberry-service-bus` и `flexberry-service-bus-postgres-db` располагаются в папке [Docker](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus/tree/develop/Docker) этого же репозитория.

## Административное приложение

### OData-backend административного приложения шины

Адрес репозитория на GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Editor>.  

* `NewPlatform.Flexberry.ServiceBus.Editor` - ASP.NET приложение для доступа к объектам `Flexberry Service Bus` по стандарту `OData`.

Файлы конфигурации и скрипты, использующиеся для создания Docker-образа `flexberry-service-bus-editor`  располагаются в папке [Docker](https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Editor/tree/develop/Docker) этого же репозитория.

### ember-addon административного приложения шины

Адрес репозитория на GitHub: <https://github.com/Flexberry/ember-flexberry-service-bus>.  
Ember-addon содержит реализацию основных возможностей по управлению `Flexberry Service Bus`. Предназначен для встраивания интерфейса управления шиной в конечные приложения, создаваемые на платформе Flexberry.

### ember-приложение административного приложения шины

Адрес репозитория на GitHub: <https://github.com/Flexberry/flexberry-service-bus-editor>.  
Приложение для управления `Flexberry Service Bus`, реализованое с использованием аддона `ember-flexberry-service-bus`. В качестве backend-части используется ASP.NET приложение `NewPlatform.Flexberry.ServiceBus.Editor`. Упаковывается в Docker-образ `flexberry-service-bus-editor` вместе с OData-backend.

## Примеры адаптеров

Адрес репозитория на GitHub: <https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus.Samples>.  
Репозиторий содержит пример реализации различных вариантов адаптеров для шины.

## Процесс разработки

Процесс разработки основан на [gitflow](https://proglib.io/p/git-github-gitflow/).

### Тестирование компонентов платформы

Проекты на C# содержат тесты:
* Автономные юнит-тесты - выполняются на Travis-CI по каждому коммиту и PR
* Интеграционные юнит-тесты - требуют для исполнения доступ к нескольким СУБД и исполняются разработчиками и на сервере, подготавливающем NuGet-пакеты.

Проекты на ember содержат тесты, исполняющиеся на Travis-CI по каждому коммиту и PR.

### Issues и Pool request

Обнаруженные проблемы в шине фиксируются в `Issues` соответствующего репозитория. Приветствуются исправления проблем отправленные в виде `Pool request` в ветку `develop`.  
Запросы на крупные изменения в `Flexberry Service Bus` следует оформлять в [RFC-репозитории платформы](https://github.com/Flexberry/rfcs).

### Стиль кода

При разработке `Flexberry Service Bus` применяется общий для платформы Flexberry [подход к оформлению кода](fp_code-style.html), которого следует придерживаться, чтобы PR были успешно приняты.
