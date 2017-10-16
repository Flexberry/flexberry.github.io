---
title: Событие установки/снятия галочки в ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: en/fw_event-installing-removing-a-check-in-objectlistview.html
folder: products/flexberry-winforms/
lang: en
---

Отследить изменение состояния галочки у `ObjectListView` возможно с помощью события `MarkObjectChanged`. В качестве аргумента события передается `DataObjectDef` объекта и состояние галочки.

```csharp
public event MarkObjectChangedEventHandler MarkObjectChanged;
```