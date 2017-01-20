---
title: Обработка статуса и состояния загрузки объекта сервисами данных
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/processing-status-and-condition-of-load-object-data-services.html
folder: product--folder
lang: ru
---

При обновлении в хранилище объекта данных, любой сервис данных учитывает статусы следующим образом:

{| border="1"
! '''ObjectStatus'''
! '''Хранилище'''
! '''Объект данных'''
|-
| UnAltered
| Не изменяет данные
| Не меняется (остаётся Loaded/LightLoaded и UnAltered)
|-
| Created
| Создаёт данные в хранилище
| Меняется статус на Loaded и UnAltered
|-
| Altered
| Изменяет данные в хранилище
| Меняется статус на Loaded/LightLoaded и UnAltered
|-
| Deleted
| Удаляет данные из хранилища
| Объект удаляется из массива обрабатываемых объектов (возвращается null в случае одного объекта)
|}