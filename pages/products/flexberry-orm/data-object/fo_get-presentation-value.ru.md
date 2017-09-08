---
title: Получение презентационного значения для объекта данных
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM
summary: Методы, позволяющие получить и переопределять значения объектов данных
toc: true
permalink: ru/fo_get-presentation-value.html
lang: ru
---

При необходимости получения строкового представления [DataObject](fo_data-object.html) можно воспользоваться методом `DataObject.GetPresentationValue()`.

Данный метод вернет значение атрибутов "Название", "Наименование", "Name" при их наличии, либо первый строковый атрибут. При отсутствии строковых атрибутов вернется значение метода `DataObject.ToString()`.

В случае необходимости переопределить поведение метода в прикладном проекте необходимо использовать делегат `DataObject.GetPresentationValueDelegate`.

Метод `DataObject.GetPresentationValue()` используется для именования ярлыков рабочего стола.
