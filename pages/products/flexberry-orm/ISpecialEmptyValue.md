---
title: Интерфейс ISpecialEmptyValue
sidebar: product--sidebar
keywords: DataObject (объекты данных), Windows UI (формы)
toc: true
permalink: ru/i-special-empty-value.html
folder: product--folder
lang: ru
---

Интерфейс `ISpecialEmptyValue` определяет значение, рассматриваемое в качестве пустого для данного типа. Если тип реализует данный интерфейс, то при проверке заполнения поля (при сохранении и отображении таракана) для экземпляра типа будет вызван метод `IsEmptyValue(object value)`, который должен вернуть булевское значение.