--- 
title: Multiple choice LookUp in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_multi-lookup-age.html 
folder: products/flexberry-aspnet/ 
lang: en 
autotranslated: true 
hash: 4b7f92b28d6eaff24e06d9257293c4242e215e403caa8a2bce5a870398936189 
--- 

In AiaxGroupEdit there is the possibility of multiple selection LookUp. 

You can configure this: 

```csharp
ctrlКвартира.AddLookUpSettings(Information.ExtractPropertyName<Квартира>(x => x.ВидОтделки), new LookUpSetting { MultiSelect = true });
``` 

On the user interface multiple choice looks like this: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age1.png) 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/multi-age2.png) 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}