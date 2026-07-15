--- 
title: Flexberry ORM API 
sidebar: flexberry-orm_sidebar 
keywords: API 
toc: true 
permalink: en/fo_flexberry-orm-api.html 
lang: en 
autotranslated: true 
hash: f7e95a53f40d8f9c3fc8c8d2c88822771afed3e35db62ea9b04efc6c993a0b9d 
--- 

## Autodocumentary for `Flexberry ORM` 

* [ORM Flexberry the develop branch](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/develop/) 
* [ORM Flexberry master branch](https://flexberry.github.io/NewPlatform.Flexberry.ORM/autodoc/master/) 

## API Migration Guide

Starting from the latest versions, Flexberry ORM introduces breaking changes to support .NET 10 and modern DI patterns.

### Breaking Changes

| Changed Component | Before | After |
|-------------------|--------|-------|
| `CurrentUserService` | Static class | Replaced by `ICurrentUser` interface |
| `AppMode` enum | `Win/Web` modes | Removed |
| `UnityFactory` | `GetContainer()` / `CreateContainer()` | Remove, use built-in DI |
| `ExternalLangDef.LanguageDef` | Static property | Constructor with `IDataService` |

### Updated Classes

| Class | Changes |
|-------|---------|
| `AuditService` | Constructor requires `ICurrentUser`, no static properties |
| `LockService` | Constructor requires `IDataService` and `ICurrentUser`, no static methods |
| `ExternalLangDef` | Constructor requires `IDataService` |
| `PostgresDataService` | Constructor requires `IBusinessServerProvider` |

### Supported Target Frameworks

| Framework | Status |
|-----------|--------|
| .NET Framework 4.5 | Supported |
| .NET Framework 4.6.1 | Supported |
| .NET Standard 2.0 | Supported |
| .NET 6.0 | Supported |
| .NET 7.0 | Supported |
| .NET 10.0 | Supported |
| .NET Core 3.1 | Removed |

For detailed migration instructions, see:
* [Dependency Injection Provider](fo_ds-provider.html)
* [Current User Service](fo_current-user-service.html)
* [Unity Factory Migration](fo_unity-factory.html)