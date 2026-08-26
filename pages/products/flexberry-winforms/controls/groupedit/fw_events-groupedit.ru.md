---
title: События добавления и удаления в GroupEditBase 
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit, events
summary:  Обработка отметки строк, перехват событий вставки и удаления строк
toc: true
permalink: ru/fw_events-groupedit.html
lang: ru
---

## Обработка события отметки строк в GroupEdit

Для того чтобы обработать `check`\ `uncheck` в строках [GroupEdit](fw_group-edit.html) необходимо:

* Получить [FlexGrid](fw_flex-grid.html) из `GroupEdit`. 
* Подписаться на событие  `CellChanged`:

```csharp
 fg.CellChanged-=new RowColEventHandler(fg_CellChanged); 
 fg.CellChanged+=new RowColEventHandler(fg_CellChanged); 
```

* В обработчике проверить: 

```csharp
 private void fg_CellChanged(object sender, RowColEventArgs e)
{ 
C1.Win.C1FlexGrid.C1FlexGrid fg = (C1.Win.C1FlexGrid.C1FlexGrid)sender; 
if ((fg.GetData( e.Row,e.Col) is bool)&& (e.Col==1)) 
    { 
         if ((bool)fg.GetData( e.Row,e.Col))  {   MessageBox.Show("Checked!");    }  else   {   MessageBox.Show("UnChecked!");    } 
    }
}
```

## Перехват событий вставки/удаления объекта в GroupEditBase

Для того чтобы перехватить события удаления и добавления объекта и при определенных условиях эти события отменить в GroupEditBase, можно воспользоваться двумя способами:

1. Можно управлять кнопкой удаления на тулбаре (показать, скрыть)
2. У [FlexGrid](fw_flex-grid.html) есть событие перед удалением, в котором можно вернуть отмену удаления
