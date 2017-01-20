---
title: Событие установки/снятия галочки в ObjectListView
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_event-installing-removing-a-check-in--object-list-view.html
folder: products/flexberry-winforms/
lang: ru
---

Отследить изменение состояния галочки у `ObjectListView` возможно с помощью события `MarkObjectChanged`. В качестве аргумента события передается `DataObjectDef` объекта и состояние галочки.
```
public event MarkObjectChangedEventHandler MarkObjectChanged;
```