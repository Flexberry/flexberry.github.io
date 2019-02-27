--- 
title: multiple choice LookUp in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_multi-lookup-age.html 
folder: products/flexberry-aspnet/ 
lang: en 
autotranslated: true 
hash: 75677857d66dd4916a8f803bd331d6edacda2afd06f2c6ddfde72a6623a2e175 
--- 

In AiaxGroupEdit there is the possibility of multiple selection LookUp. 

You can configure this: 

```csharp
ctrlКвартира.AddLookUpSettings(Information.ExtractPropertyName<Квартира>(x => x.ВидОтделки), new LookUpSetting { MultiSelect = true });
``` 

On the user interface multiple choice looks like this: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age1.png) 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age2.png) 




 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/