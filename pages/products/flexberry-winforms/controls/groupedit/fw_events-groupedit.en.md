--- 
title: Events adding and removing in GroupEditBase 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, events 
summary: Processing of marker lines, interception of events of insertion and deletion of rows 
toc: true 
permalink: en/fw_events-groupedit.html 
lang: en 
autotranslated: true 
hash: 6f96cb12def115e3826d0503367810c8aedea6a289c45107e3c4476e75cfa630 
--- 

## Processing events mark the rows in GroupEdit 

To treat `check`\ `uncheck` in lines [GroupEdit](fw_group-edit.html): 

* To [FlexGrid](fw_flex-grid.html) from `GroupEdit`. 
* Subscribe to the event `CellChanged`: 

```csharp
 fg.CellChanged-=new RowColEventHandler(fg_CellChanged); 
 fg.CellChanged+=new RowColEventHandler(fg_CellChanged); 
``` 

* In the handler to check: 

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

## Interception of events of insertion/removal of an object in GroupEditBase 

To intercept events from the delete and add object and under certain conditions these events to cancel in GroupEditBase, you can use two ways: 

1. You can control the delete button on the toolbar (show, hide) 
2. [FlexGrid](fw_flex-grid.html) there is an event before deleting, which allows you to return to cancel the deletion 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}