--- 
title: Push call edit form 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: Example of how to invoke the edit form 
toc: true 
permalink: en/fw_force-call-editing-form.html 
lang: en 
autotranslated: true 
hash: 26c3a9c5aadf86937d76e188f2a776c3a95669415d25fa315fe8a5ad6025aa5f 
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



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}