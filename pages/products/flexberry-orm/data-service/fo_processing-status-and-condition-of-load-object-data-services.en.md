---
title: Обработка статуса и состояния загрузки объекта сервисами данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: en/fo_processing-status-and-condition-of-load-object-data-services.html
folder: products/flexberry-orm/
lang: en
---

При обновлении в хранилище объекта данных, любой сервис данных учитывает статусы следующим образом:

| **ObjectStatus**| **Хранилище**| **Объект данных**|
|:----------------|:----------------|:----------------|
| UnAltered| Не изменяет данные| Не меняется (остаётся Loaded/LightLoaded и UnAltered)|
| Created| Создаёт данные в хранилище| Меняется статус на Loaded и UnAltered|
| Altered| Изменяет данные в хранилище| Меняется статус на Loaded/LightLoaded и UnAltered|
| Deleted| Удаляет данные из хранилища| Объект удаляется из массива обрабатываемых объектов (возвращается null в случае одного объекта)|