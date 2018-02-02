---
title: Разработка Flexberry Service Bus
keywords: ESB, Development
sidebar: flexberry-servicebus_sidebar
toc: true
permalink: ru/fsb_development.html
lang: ru
summary: Функциональная подсистема платформы Flexberry - Flexberry Service Bus.
---

## Вместо введения

При разработке `Flexberry Service Bus` можно опираться на основные правила команды:
- [JavaScript style guide](https://github.com/Flexberry/javascript-style-guide).

## Репозитории

### Flexberry/NewPlatform.Flexberry.ServiceBus

Адрес репозитория на GitHub: https://github.com/Flexberry/NewPlatform.Flexberry.ServiceBus  
Репозиторий с основными компонентами `Flexberry Service Bus`, cодержит два решения:

* **NewPlatform.Flexberry.ServiceBus** - решение, включающее следующие проекты:
  * **NewPlatform.Flexberry.ServiceBus** - проект с реализацией компонентов.
  * **NewPlatform.Flexberry.ServiceBus.Objects** - проект с объектоной моделью.
  * **NewPlatform.Flexberry.ServiceBus.Components** - проект с интерфейсами компонентов.
  * **NewPlatform.Flexberry.ServiceBus.ClientTools** - проект с публичными интерфейсами.
  * **NewPlatform.Flexberry.ServiceBus.ConsoleHost** - проект с консольным приложением.
  * **NewPlatform.Flexberry.ServiceBus.WinServiceHost** - проект с приложением в виде службы.
  * **NewPlatform.Flexberry.ServiceBus.Tests** - проект с unit-тестами.
  * **NewPlatform.Flexberry.ServiceBus.IntegratedTests** - проект с интеграционными тестами.
* **NewPlatform.Flexberry.ServiceBus.Editor** - ASP.NET приложение для доступа к объектам `Flexberry Service Bus` по стандарту `OData`.

### Flexberry/ember-flexberry-service-bus

Адрес репозитория на GitHub: https://github.com/Flexberry/ember-flexberry-service-bus  
Репозиторий, хранящий реализацию основных возможностей по управлению `Flexberry Service Bus` в виде ember-аддона.

### Flexberry/flexberry-service-bus-editor

Адрес репозитория на GitHub: https://github.com/Flexberry/flexberry-service-bus-editor  
Репозиторий, хранящий приложение для управления `Flexberry Service Bus`, реализованое с использованием аддона `ember-flexberry-service-bus`. В качестве backend-части используется ASP.NET приложение `NewPlatform.Flexberry.ServiceBus.Editor` из репозитория `Flexberry/NewPlatform.Flexberry.ServiceBus`.
