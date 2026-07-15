---
title: Flexberry ORM API
sidebar: flexberry-orm_sidebar
keywords: API
toc: true
permalink: ru/fo_flexberry-orm-api.html
lang: ru
---

## Aвтодокументация по `Flexberry ORM`

* [Flexberry ORM ветка develop](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/develop/)
* [Flexberry ORM ветка master](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/master/)

## Руководство по миграции API

Начиная с последних версий, Flexberry ORM вводит breaking changes для поддержки .NET 10 и современных паттернов DI.

### Breaking Changes

| Изменённый компонент | Было | Стало |
|----------------------|------|-------|
| `CurrentUserService` | Статический класс | Заменён интерфейсом `ICurrentUser` |
| Перечисление `AppMode` | Режимы `Win/Web` | Удалено |
| `UnityFactory` | `GetContainer()` / `CreateContainer()` | Удалить, использовать встроенную DI |
| `ExternalLangDef.LanguageDef` | Статическое свойство | Конструктор с `IDataService` |

### Обновлённые классы

| Класс | Изменения |
|-------|-----------|
| `AuditService` | Конструктор требует `ICurrentUser`, нет статических свойств |
| `LockService` | Конструктор требует `IDataService` и `ICurrentUser`, нет статических методов |
| `ExternalLangDef` | Конструктор требует `IDataService` |
| `PostgresDataService` | Конструктор требует `IBusinessServerProvider` |

### Поддерживаемые целевые фреймворки

| Фреймворк | Статус |
|-----------|--------|
| .NET Framework 4.5 | Поддерживается |
| .NET Framework 4.6.1 | Поддерживается |
| .NET Standard 2.0 | Поддерживается |
| .NET 6.0 | Поддерживается |
| .NET 7.0 | Поддерживается |
| .NET 10.0 | Поддерживается |
| .NET Core 3.1 | Удалено |

Подробнее о миграции:
* [Получение сервиса данных](fo_ds-provider.html)
* [Сервис текущего пользователя](fo_current-user-service.html)
* [Unity Factory - миграция](fo_unity-factory.html)