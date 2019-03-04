--- 
title: the title Setting list form 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Explained how to override the header of the list form that opens when editing a feature (raising to LookUp) 
toc: true 
permalink: en/fw_list-form-caption.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 4336ba7fe86db4a62b473a5f9e36dcf0c72ce19d81e213dd1aa5aa1b9c4371db 
--- 

Setting dynamic properties `ListFormCaption` data object allows you to override the title of the list form that opens when editing a feature (raising to LookUp). 
It should be noted that the installation of the window title happens in the `BaseWinList.Edit`. 

```csharp
public override void OnEdit(string propertyname, ICSSoft.STORMNET.DataObject dataobject, string contpath, object tag)
{
   if (propertyname == "Breed")
      if (!dataobject.DynamicProperties.ContainsKey("ListFormCaption"))
         dataobject.DynamicProperties.Add("ListFormCaption", "A list of all breeds");
  
   base.OnEdit(propertyname, dataobject, contpath, tag);
}
``` 





 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/