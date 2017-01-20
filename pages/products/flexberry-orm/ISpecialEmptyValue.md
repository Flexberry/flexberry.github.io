---
title: Интерфейс ISpecialEmptyValue
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Windows UI (формы)
toc: true
permalink: ru/fo_i-special-empty-value.html
folder: products/flexberry-orm/
lang: ru
---

Интерфейс `ISpecialEmptyValue` определяет значение, рассматриваемое в качестве пустого для данного типа. Если тип реализует данный интерфейс, то при проверке заполнения поля (при сохранении и отображении таракана) для экземпляра типа будет вызван метод `IsEmptyValue(object value)`, который должен вернуть булевское значение.