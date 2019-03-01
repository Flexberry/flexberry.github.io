--- 
title: Push call edit form 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Example of how to invoke the edit form 
toc: true 
permalink: en/fw_force-call-editing-form.html 
lang: en 
autotranslated: true 
hash: 16f20a5dc7262f7fcf3a23ec1985e57a03001ec1e9e8c1de16afb4bb99e5f047 
--- 

Call edit form 

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);      
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.Edit(oОбъект, "", "");
``` 

If you want the functionality to preserve object when pressing on the button "Save" or "Save and close" in the dependent form, the code will look like this: 

```csharp
Объект oОбъект = new Объект();
oОбъект.__PrimaryKey = 'чего-то там';
ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObject(oОбъект);      
ICSSoft.STORMNET.UI.BaseIndpdEdit cont = (ICSSoft.STORMNET.UI.BaseIndpdEdit)Activator.CreateInstance(typeof(ОбъектE));
ICSSoft.STORMNET.UI.ContRunner.RunEditForm(cont);
cont.SaveEvent += (sr, ea) => { new BusinessService().UpdateObject(ea.dataobject); };
cont.Edit(oОбъект, "", "");
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/