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
hash: 6e516ae19de294bbc1407f38003215a0f66533a9435fe367d9ca8fb38592533c 
--- 

Setting dynamic properties «ListFormCaption» data object allows you to override the title of the list form that opens when editing a feature (raising to LookUp). 
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