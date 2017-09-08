---
title: Designing Data Objects
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, DataObject, example
summary: Illustration of data object construction
toc: true
permalink: en/fo_construction-data-objects.html
lang: en
---

Разработчик конструирует любой объект данных стандартным для `.Net` образом: вызовом `new` с необходимым конструктором.

Пример:

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
```
