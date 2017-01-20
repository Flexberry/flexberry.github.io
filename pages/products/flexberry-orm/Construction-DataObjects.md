---
title: Конструирование объектов данных
sidebar: product--sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/construction--data-objects.html
folder: product--folder
lang: ru
---

Разработчик конструирует любой объект данных стандартным для `.Net` образом: вызовом `new` с необходимым конструктором.

Для нашего примера:
```
SimpleDataObject sdo = new SimpleDataObject();
sdo.Master = new MasterDataObject();
```