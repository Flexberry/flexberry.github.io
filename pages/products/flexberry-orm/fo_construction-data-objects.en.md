---
title: Конструирование объектов данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: en/fo_construction-data-objects.html
---

Разработчик конструирует любой объект данных стандартным для `.Net` образом: вызовом `new` с необходимым конструктором.

Для нашего примера:

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
```