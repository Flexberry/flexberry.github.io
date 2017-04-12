---
title: Наиболее интересные свойства LookUp'ов для Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: JavaScript API, Windows UI (Контролы)
toc: true
permalink: ru/fa_interesting-lookup-settigs.html
lang: ru
---

Наиболее интересные свойства LookUp'ов:

* PropertyToShow
* MasterViewName
* MasterTypeName
* ObjectTypeName
* ObjectViewName

Ниже они описываются подробно.

### PropertyToShow

Отвечает за отображаемое свойство __мастера__ после выбора лукапа. 

Не работает при включеном [AutoComplete](fa_predict-input-web.html).

Устанавливается в методе `PostApplyToControls` или `PostLoad` формы редактирования. Если установить в методах, срабатывающих раньше, то изменения перезапишутся WebBinder'ом.

Принимает составные свойства (свойства мастеров). К примеру, можно указать:

```csharp
ctrlКлиент.PropertyToShow = "Прописка.Город";
```

Таким образом, при выборе мастера `Клиент` будет отображаться свойство `Клиент.Прописка.Город`.

### MasterViewName

Устанавливает представление, которое будет использоваться на открываемой форме лукапа.

В отличии от `PropertyToShow`, может устанавливаться в любом месте и не зависит от `AutoComplete`.

### MasterTypeName

Устанавливает тип объекта, который необходимо выбрать на LookUp. Актуально, когда мастеровой объект связан наследованием. То есть если есть два класса, связанных наследованием (класс A наследуется от класса B), и необходимо, чтобы на LookUp открывался класс А (а по умолчанию открывается класс B), то необходимо установить `MasterTypeName` следующим образом:

```
ctrlM1.MasterTypeName = typeof(M1).AssemblyQualifiedName;
```

не забыв при этом установить `MasterViewName`

{% include note.html content="Для означивания `MasterTypeName` следует использовать `typeof(Тип).AssemblyQualifiedName`.  
Подробнее можно посмотреть [в статье Проблема используемых типов (TypeUsage)](fo_type-usage-problem.html)." %}

### ObjectTypeName

Устанавливает тип объекта, для которого устанавливается мастер.  
Аналогично `MasterTypeName`, актуально для случаев наследования, но на сей раз в наследовании участвует базовый объект, а не мастер. Устанавливается в паре с `ObjectViewName`.

### ObjectViewName

Устанавливает представление базового объекта.  
Аналогично `MasterViewName`, актуально для случаев наследования, но на сей раз в наследовании участвует базовый объект, а не мастер. Устанавливается в паре с `ObjectTypeName`.