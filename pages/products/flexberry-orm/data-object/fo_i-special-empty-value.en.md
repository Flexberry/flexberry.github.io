---
title: ISpecialEmptyValue
sidebar: flexberry-orm_sidebar
keywords: DataObject, Windows UI, empty value
summary: Empty for a given data type
toc: true
permalink: en/fo_i-special-empty-value.html
lang: en
---

Интерфейс `ISpecialEmptyValue` определяет значение, рассматриваемое в качестве пустого для данного типа. Если тип реализует данный интерфейс, то при проверке заполнения поля (при сохранении и отображении символа `*`) для экземпляра типа будет вызван метод `IsEmptyValue(object value)`, который должен вернуть булевское значение.