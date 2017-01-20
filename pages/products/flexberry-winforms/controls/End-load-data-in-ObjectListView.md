---
title: Отслеживание окончания загрузки данных в ObjectListView
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/end-load-data-in--object-list-view.html
folder: product--folder
lang: ru
---

Отследить окончание загрузки данных в `ObjectListView` возможно с помощью события `AfterFillData` и свойства `IsDataLoaded`. Событие `AfterFillData` происходит при окончании загрузки, а свойство `IsDataLoaded` принимает истинное значение, в случае если данные загружены и `ObjectListView` не находится в состоянии обновления.
