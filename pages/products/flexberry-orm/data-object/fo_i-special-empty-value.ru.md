---
title: Интерфейс ISpecialEmptyValue
sidebar: flexberry-orm_sidebar
keywords: DataObject, Windows UI, пустые значение
summary: Пустое значение для заданного типа данных
toc: true
permalink: ru/fo_i-special-empty-value.html
lang: ru
---

Интерфейс `ISpecialEmptyValue` определяет значение, рассматриваемое в качестве пустого для данного типа. Если тип реализует данный интерфейс, то при проверке заполнения поля (при сохранении и отображении символа `*`) для экземпляра типа будет вызван метод `IsEmptyValue(object value)`, который должен вернуть булевское значение.