---
title: IReferencesNullDelete
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, бизнес серверы, каскадное удаление объектов
summary: Особенности каскадного удаления объектов c использованием Null
toc: true
permalink: ru/fo_i-references-null-delete.html
lang: ru
---

Интерфейс `IReferencesNullDelete` позволяет осуществлять каскадное удаление объектов (при удалении у всех ссылающихся объектов проставляется NULL вместо ссылки на удаляемый объект).

## Как работает IReferencesNullDelete

Логика по занулению ссылок прописана в [бизнес-сервере](fo_business-server.html) интерфейса `IReferencesNullDelete`; собственных свойств и методов данный интерфейс не предоставляет. Таким образом, чтобы вместе с некоторым объектом удалить все ссылки на него, достаточно, чтобы класс объекта был наследником от `IReferencesNullDelete`.
Поиск классов, которые ссылаются на искомый, осуществляется следующим образом:

* Берутся сборки, заданные в свойстве `AssembliesForIReferencesCascadeDeleteSearch` класса `ICSSoft.STORMNET.Business.Interfaces.InterfaceBusinessServer` (задать можно с использованием метода `SetupAdditionalAssemblies`; если список сборок не задан, то поиск осуществляется только в сборке класса, наследующего от `IReferencesNullDelete`).

```csharp
var assemblies = new[]
{
    typeof(NewPlatform.Flexberry.ORM.Tests.Salad2).Assembly,
};

ICSSoft.STORMNET.Business.Interfaces.InterfaceBusinessServer.SetupAdditionalAssemblies(assemblies);
```

* В сборках ищутся типы, наследующие от [DataObject](fo_data-object.html).
* В найденных типах определяются свойства, которые содержат ссылку на искомый класс или его прародителей. 

{% include important.html content="Если у ссылки на удаляемый объект стоит атрибут [NotNull](fo_attributes-class-data.html), то будет проброшено исключение `PropertyCouldnotBeNullException` и объект не будет удалён." %}

## Пример

Пусть есть диаграмма вида:

![](/images/pages/products/flexberry-orm/business-servers/i-references-null-delete.png)

На диаграмму добавлен `IReferencesNullDelete` со стереотипом [externalinterface](fd_external-interface.html) , от которого наследуется класс `Plant2` и `Cabbage2`. При удалении экземпляра этого класса будут удаляться также все ссылки на данный объект.
При указанном расположении классов с учётом [наследования](fd_interfaces.html):
* При удалении экземпляра класса `Plant2` будут проверяться ссылки среди экземпляров классов `Salad2` и `Dish2`.
* При удалении экземпляра класса `Cabbage2` будут проверяться ссылки среди экземпляров классов `Salad2`, `Dish2`, `CabbageSalad`, `Soup2` (экземпляры класса `CabbagePart2` являются детейлами и будут удаляться).
* Если удаляемый объект будет стоять, например, в экземпляре класса `Salad2` в свойстве `Ingridient2`, то будет проброшено исключение, поскольку у данного свойства стоит атрибут `NotNull`.
