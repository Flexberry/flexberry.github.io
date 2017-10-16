---
title: Установка заголовка списковой формы
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: en/fw_list-form-caption.html
folder: products/flexberry-winforms/
lang: en
---

Установка динамического свойства «ListFormCaption» объекта данных  позволяет переопределить заголовок списковой формы, открываемой при редактировании объекта (поднятии на LookUp).
Следует заметить, что установка заголовка окна происходит в методе `BaseWinList.Edit`.
      
```csharp
public override void OnEdit(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
{
   if (propertyname == "Порода")
      if (!dataobject.DynamicProperties.ContainsKey("ListFormCaption"))
         dataobject.DynamicProperties.Add("ListFormCaption", "Список всех пород");
  
   base.OnEdit(propertyname, dataobject, contpath, tag);
}
```
 

