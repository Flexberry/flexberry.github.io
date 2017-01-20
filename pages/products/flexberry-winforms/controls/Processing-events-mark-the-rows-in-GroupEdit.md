---
title: Обработка события отметки строк в GroupEdit
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_processing-events-mark-the-rows-in--group-edit.html
folder: products/flexberry-winforms/
lang: ru
---

Как обработать check\uncheck в строках [GroupEdit](group-edit.html)
1. [Получение-FlexGrid-из-GroupEdit|Получаем FlexGrid из GroupEdit]. 
 
2. Подписываемся на событие  CellChanged:
```
 fg.CellChanged-=new RowColEventHandler(fg_CellChanged); 
 fg.CellChanged+=new RowColEventHandler(fg_CellChanged); 
```
3. В обработчике проверяем все, что нужно... 
```

 private void fg_CellChanged(object sender, RowColEventArgs e)
{ 
C1.Win.C1FlexGrid.C1FlexGrid fg = (C1.Win.C1FlexGrid.C1FlexGrid)sender; 
if ((fg.GetData( e.Row,e.Col) is bool)&& (e.Col==1)) 
    { 
         if ((bool)fg.GetData( e.Row,e.Col))  {   MessageBox.Show("Checked!");    }  else   {   MessageBox.Show("UnChecked!");    } 
    }
}
```