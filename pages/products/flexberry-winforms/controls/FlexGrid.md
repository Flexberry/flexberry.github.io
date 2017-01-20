---
title: FlexGrid
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_flex-grid.html
folder: products/flexberry-winforms/
lang: ru
---

# FlexGrid
[FlexGrid](http://www.componentone.com/SuperProducts/FlexGridWinForms/) - простой в использовании контрол-таблица от компании ComponentOne. Позволяет отображать, редактировать, форматировать, организовывать и печатать данные в виде таблицы.

Контрол используется в [GroupEdit](group-edit.html), [ObjectListView](object-list-view.html), а также в [Статиторе](statitor.html).


# Документация
Документацию можно найти на официальном сайте компонента, или скачать [здесь](http://wiki.ics.perm.ru/GetFile.aspx?File=FlexGrid.rar&AsStreamAttachment=1&Provider=ScrewTurn.Wiki.Plugins.SqlServer.SqlServerFilesStorageProvider&IsPageAttachment=1&Page=FlexGrid&NoHit=1).

# Получение FlexGrid из GroupEdit
```

public static C1.Win.C1FlexGrid.C1FlexGrid GetGridFromGE(ICSSoft.STORMNET.Windows.Forms.GroupEditBase groupEdit)
// Ищем FlexGrid 
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
