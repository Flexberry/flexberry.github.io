---
title: Конструирование объектов данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/fo_construction--data-objects.html
---

Разработчик конструирует любой объект данных стандартным для `.Net` образом: вызовом `new` с необходимым конструктором.

Для нашего примера:

```
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
```