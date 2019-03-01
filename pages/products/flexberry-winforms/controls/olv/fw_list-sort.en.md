--- 
title: Sorting lists 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, list 
summary: an Example of the task sort settings list in code 
toc: true 
permalink: en/fw_list-sort.html 
lang: en 
autotranslated: true 
hash: c98e95bf0d4b895c489d6b686e2d3c5e4ae07fa09e150df8ebbee5ad34a16f52 
--- 

Lists allow you to sort the contained objects in specific columns. The user can set the necessary sorting. 
Also sorting the list can be customized from the code. 

Sort columns [ObjectListView](fw_objectlistview.html) carried out by specifying the type of sort object 
`ICSSoft.STORMNET.Windows.Forms.ColumnInfo` properties `.Columns`. 

Example: 

```csharp
this.objectListView1.Columns = new ICSSoft.STORMNET.Windows.Forms.ColumnInfo[] {
    new ICSSoft.STORMNET.Windows.Forms.ColumnInfo("Name", -1, true, ICSSoft.STORMNET.Business.SortOrder.Asc, ((byte)(0)), "Name"),
    new ICSSoft.STORMNET.Windows.Forms.ColumnInfo("Registration", -1, true, ICSSoft.STORMNET.Business.SortOrder.Asc, ((byte)(1)), "Registration")
}
``` 

Here the designer takes `ColumnInfo` the third and fourth parameters of the sorting method of the type `ICSSoft.STORMNET.Business.SortOrder` (Asc, Desc, None) and sort priority type `byte`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/