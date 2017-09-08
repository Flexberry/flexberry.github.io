---
title: Конструирование объектов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, объекты данных, пример
summary: Иллюстрация конструирования объекта данных на примере
toc: true
permalink: ru/fo_construction-data-objects.html
lang: ru
---

Разработчик конструирует любой объект данных стандартным для `.Net` образом: вызовом `new` с необходимым конструктором.

Пример:

```csharp
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
```
