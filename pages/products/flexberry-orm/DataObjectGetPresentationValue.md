---
title: Получение презентационного значения для объекта данных
sidebar: product--sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/data-object-get-presentation-value.html
folder: product--folder
lang: ru
---

При необходимости получения строкового представления `DataObject` можно воспользоваться методом `DataObject.GetPresentationValue()`. Данный метод вернет значение атрибутов "Название", "Наименование", "Name" при их наличии, либо первый строковый атрибут. При отсутствии строковых атрибутов вернется значение метода `DataObject.ToString()`.

В случае необходимости переопределить поведение метода в прикладном проекте необходимо использовать делегат `DataObject. GetPresentationValueDelegate`.

Метод `DataObject.GetPresentationValue()` используется для именования ярлыков рабочего стола.
