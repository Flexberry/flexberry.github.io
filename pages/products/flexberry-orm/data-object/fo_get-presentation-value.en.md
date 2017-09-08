---
title: Getting a Presentation Value for a Data Object
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM
summary: Methods that allow you to retrieve and override the values of data objects
toc: true
permalink: en/fo_get-presentation-value.html
lang: en
---

При необходимости получения строкового представления [DataObject](fo_data-object.html) можно воспользоваться методом `DataObject.GetPresentationValue()`.

Данный метод вернет значение атрибутов "Название", "Наименование", "Name" при их наличии, либо первый строковый атрибут. При отсутствии строковых атрибутов вернется значение метода `DataObject.ToString()`.

В случае необходимости переопределить поведение метода в прикладном проекте необходимо использовать делегат `DataObject.GetPresentationValueDelegate`.

Метод `DataObject.GetPresentationValue()` используется для именования ярлыков рабочего стола.
