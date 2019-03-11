--- 
title: FlexGrid 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, FlexGrid 
summary: getting a FlexGrid from a GroupEdit 
toc: true 
permalink: en/fw_flex-grid.html 
lang: en 
autotranslated: true 
hash: bf343cca3b18634af83535eca40a8d458a6b094717149c3f81ef1e12db7fa31a 
--- 

[FlexGrid](http://www.componentone.com/SuperProducts/FlexGridWinForms/) - easy to use control-table from company ComponentOne. Allows you to display, edit, format, organize and print the data in a table. 

The control used in [GroupEdit](fw_group-edit.html), [ObjectListView](fw_objectlistview.html), as well as in Staticone. 

## getting a FlexGrid from a GroupEdit 

```csharp
public static C1.Win.C1FlexGrid.C1FlexGrid GetGridFromGE(ICSSoft.STORMNET.Windows.Forms.GroupEditBase groupEdit)
// Search For FlexGrid 
{
 for(int i =0; i < groupEdit.Controls.Count;i++)
 {
  if (groupEdit.Controls[i] is System.Windows.Forms.GroupBox)
  {
   for(int j =0; j < groupEdit.Controls[i].Controls.Count;j++)
   {
    if (groupEdit.Controls[i].Controls[j] is C1.Win.C1FlexGrid.C1FlexGrid)
    {
     return (C1.Win.C1FlexGrid.C1FlexGrid)groupEdit.Controls[i].Controls[j];
    }
   }
  }
 }
 return null;
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}