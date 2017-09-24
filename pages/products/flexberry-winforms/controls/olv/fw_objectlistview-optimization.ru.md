---
title: Оптимизация чтения данных ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_objectlistview-optimization.html
folder: products/flexberry-winforms/
lang: ru
---

В обычном режиме `ObjectListView` зачитывает все поля находящиеся в представлении, даже если некоторые колонки скрыты. SQL Server при выполнении запроса руководствуется полями входящими в самый верхний запрос (при их вложении) и ограничением. Если поле мастера не используется ни в списке выводимых, ни в ограничении, то SQL Server не выполняет join, тем самым ускоряя ограничение запроса.

Установка свойства `ObjectListView.UseColumnOptimization=true` в запрос не включаются скрытые поля.

Для установки режима оптимизации для всех списков приложения можно использовать метод `Tuner.Customize`, в котором установить свойство `UseColumnOptimization` для `ObjectListView`, переданного в качестве параметра.
