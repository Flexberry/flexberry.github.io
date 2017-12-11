---
title: Отслеживание окончания загрузки данных в ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Указан способ отслеживания окончания загрузки данных в ObjectListView
toc: true
permalink: ru/fw_end-load-data-in-objectlistview.html
folder: products/flexberry-winforms/
lang: ru
---

Отследить окончание загрузки данных в `ObjectListView` возможно с помощью события `AfterFillData` и свойства `IsDataLoaded`. Событие `AfterFillData` происходит при окончании загрузки, а свойство `IsDataLoaded` принимает истинное значение, в случае если данные загружены и `ObjectListView` не находится в состоянии обновления.
