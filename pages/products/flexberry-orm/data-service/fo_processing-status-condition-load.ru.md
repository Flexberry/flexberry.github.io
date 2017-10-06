---
title: Обработка статуса и состояния загрузки объекта
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных
summary: Обработка статуса и состояния загрузки объекта сервисами данных
toc: true
permalink: ru/fo_processing-status-condition-load.html
folder: products/flexberry-orm/
lang: ru
---

При обновлении в хранилище объекта данных, любой сервис данных учитывает статусы следующим образом:

| **ObjectStatus**| **Хранилище**| **Объект данных**|
|:----------------|:----------------|:----------------|
| UnAltered| Не изменяет данные| Не меняется (остаётся Loaded/LightLoaded и UnAltered)|
| Created| Создаёт данные в хранилище| Меняется статус на Loaded и UnAltered|
| Altered| Изменяет данные в хранилище| Меняется статус на Loaded/LightLoaded и UnAltered|
| Deleted| Удаляет данные из хранилища| Объект удаляется из массива обрабатываемых объектов (возвращается null в случае одного объекта)|