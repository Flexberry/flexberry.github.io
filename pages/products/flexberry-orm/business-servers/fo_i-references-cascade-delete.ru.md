---
title: IReferencesCascadeDelete
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, бизнес-серверы, каскадное удаление объектов
summary: Особенности каскадного удаления объектов
toc: true
permalink: ru/fo_i-references-cascade-delete.html
lang: ru
---

## IReferencesCascadeDelete

Интерфейс `IReferencesCascadeDelete` позволяет осуществлять каскадное удаление объектов (при удалении самого объекта все ссылающиеся на него объекты также удаляются).

## Как работает IReferencesCascadeDelete

Логика по каскадному удалению прописана в [бизнес-сервере](fo_business-server.html) интерфейса `IReferencesCascadeDelete`, собственных свойств и методов данный интерфейс не предоставляет. Таким образом, чтобы вместе с некоторым объектом удалить все объекты, которые на него ссылаются, достаточно, чтобы класс объекта был наследником от `IReferencesCascadeDelete`.
Поиск классов, которые ссылаются на искомый, осуществляется следующим образом:

* Берутся сборки, заданные в свойстве `AssembliesForIReferencesCascadeDeleteSearch` класса `ICSSoft.STORMNET.Business.Interfaces.InterfaceBusinessServer` (задать можно с использованием метода `SetupAdditionalAssemblies`; если список сборок не задан, то поиск осуществляется только в сборке класса, наследующего от `IReferencesCascadeDelete`).

```csharp
var assemblies = new[]
{
    typeof(NewPlatform.Flexberry.ORM.Tests.Salad2).Assembly,
};

ICSSoft.STORMNET.Business.Interfaces.InterfaceBusinessServer.SetupAdditionalAssemblies(assemblies);
```

* В сборках ищутся типы, наследующие от [DataObject](fo_data-object.html).
* В найденных типах определяются свойства, которые содержат ссылку на искомый класс или его прародителей. 

## Пример

Пусть у нас есть диаграмма вида:

![](/images/pages/products/flexberry-orm/business-servers/i-references-cascade-delete.png)

На диаграмму добавлен `IReferencesCascadeDelete` со стереотипом [externalinterface](fd_external-interface.html) , от которого наследуется класс `Territory2` и `Country2`. При удалении экземпляров этого класса будут удаляться также все объекты, которые ссылаются на них.
При указанном расположении классов с учётом [наследования](fd_inheritance.html):
1. При удалении экземпляра класса `Territory2` будут проверяться ссылки среди экземпляров классов `Human2` и `Place2`.
2. При удалении экземпляра класса `Country2` будут проверяться ссылки среди экземпляров классов `Human2`, `Place2`, `Adress2`, `Apparatus2` (экземпляры класса `Region` являются детейлами и будут удаляться даже без использования соответствующего интерфейса).
