---
title: Отображение подсказки(tooltip) для отдельных записей в ObjectListView
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/object-list-view-tool-tip.html
folder: product--folder
lang: ru
---

В `ObjectListView` реализована возможность отображения подсказки для отдельных строк.
Для включения данного режима необходимо установить свойство `ObjectListView.ShowToolTip`.
Для задания текста подсказки необходимо обработать событие `ObjectListView.BeforeToolTipRequired`. Для настройки параметров отображения подсказки (задержки появления, длительности отображения внешнего вида) следует изменить свойства объекта `ObjectListView.ToolTip`.

Пример:

В данном примере выводится подсказка к ячейкам первых 5-ти столбцов.
```cs
private void objectListView1_BeforeToolTipRequired(object sender, BeforeToolTipRequiredEventArgs e)
{
     e.TipText = string.Format("Строка {0}, столбец {1}", e.Row, e.Column);
     if (e.Column>5)
         e.Cancel = true;
}
```
