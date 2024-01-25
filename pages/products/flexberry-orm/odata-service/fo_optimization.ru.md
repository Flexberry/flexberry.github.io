---
title: Оптимизация работы ODataBackend
sidebar: flexberry-orm_sidebar
keywords: оптимизация, ODataBackend, OData, UpdateView, представление для обновления
summary:
toc: true
permalink: ru/fo_optimization.html
lang: ru
---

## Представление для обновления (UpdateView)
По умолчанию, при обновлении объекта через OData, он загружается из БД целиком (со связанными мастерами и детейлами). В случае, если известно, что какие-то атрибуты/мастера/детейлы объекта **никогда не будут обновлены** через OData, программист имеет возможность указать для объекта `UpdateView` - более экономное представление, которое необходимо использовать при обновлении объекта.

Атрибуты, мастера, детейлы, которые не включены в `UpdateView`, нельзя будет использовать в запросах на обновление объекта через OData. Кроме того, эти атрибуты, мастера и детейлы не будут загружены в бизнес-сервере. Несмотря на эти особенности, данная функция может дать прирост к производительности, особенно в случаях, когда объекты содержат множество детейлов.

### Использование
Изменить `UpdateView` для объекта можно на уровне приложения в файле `Startup.cs`, в методе `Configure`. Необходимо передать представления в параметер конструктора `updateViews` для `DefaultDataObjectEdmModelBuilder`:
```csharp
var updateViews = new Dictionary<Type, View>()
{
    { typeof(Медведь), Медведь.Views.МедведьUpdateView }, // задаём UpdateView для класса "Медведь"
    { typeof(Берлога), Берлога.Views.БерлогаUpdateView }, // задаём UpdateView для класса "Берлога"
};
var modelBuilder = new DefaultDataObjectEdmModelBuilder(assemblies, false, pseudoDetailDefinitions, updateViews: updateViews); // передаётся updateViews

var token = builder.MapDataObjectRoute(modelBuilder);
```
