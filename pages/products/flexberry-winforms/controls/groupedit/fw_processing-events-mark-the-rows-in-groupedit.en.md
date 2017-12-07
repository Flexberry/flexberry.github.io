---
title: Обработка события отметки строк в GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: На примере рассмотрен способ обработки событий установки и снятия ометки строк GroupEdit
toc: true
permalink: en/fw_processing-events-mark-the-rows-in-groupedit.html
folder: products/flexberry-winforms/
lang: en
---

Как обработать check\uncheck в строках [GroupEdit](fw_group-edit.html)
* [Получаем FlexGrid из GroupEdit](fw_flex-grid.html). 
 
* Подписываемся на событие  CellChanged:

```csharp
 fg.CellChanged-=new RowColEventHandler(fg_CellChanged); 
 fg.CellChanged+=new RowColEventHandler(fg_CellChanged); 
```

* В обработчике проверяем все, что нужно... 

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