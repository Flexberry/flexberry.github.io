---
title: Ограничение тип лукапа «combo» в GroupEdit
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_restriction-type--lookup-combo-in--group-edit.html
folder: products/flexberry-winforms/
lang: ru
---

Поле «Настройка выборки» в окне настройки представления служит для задания ограничения на мастера, если используется тип лукапа '''«combo»'''. Эта функция будет особенно полезна при редактировании мастера в GroupEdit.
Первоначально предполагалось, что в это поле записывается сериализованный экземпляр класса FunctionForControls. Практика показала несостоятельность этого предположения.
Теперь в поле «Настройка выборки» можно записать и выражение для ''funcSQL''.