--- 
title: Detaylari in the advanced editor limitations 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_details-limit-editor.html 
lang: en 
autotranslated: true 
hash: 77276ff64e83be9dd9c455c5831ca8c1b04f80dbc2032d9dc01002f85401b041 
--- 

## Work with detaylari in the advanced editor, limitations in the web 

You should pay attention (especially associated with the [conversion functions restrictions in linq-expression](fo_lcs-to-linq.html)): 

* When you set the constraint of universality on detaily (in lcs is [ExistExact](fo_exist-details.html)) in the view of detail should be included a reference to the aggregator. 
* When setting limits on detaily using the advanced editor restrictions to the view of the aggregator may want to include a link to detali. 

## Work with pseudocatalase in the advanced editor, limitations in the web 

For pseudocatalase in the expanded constraint editor](fa_advanced-limit-editor.html) you must do the following: 
* When [specify display order of the properties in the advanced editor, limitations](fa_prop-order-limit-editor.html) add interest pseudometal (problem of specifying limits on pseudometal described [here](fo_linq-provider.html)) 
* In the constraint editor, you can open stored in the lcs limits, or designing a new one. 

![](/images/pages/products/flexberry-aspnet/ogranicheniye/le-pseudo-detail.png) 

## Dynamic view of datalow 

For the work of datalow in the expanded constraint editor in the web](fa_advanced-limit-editor.html) in the application of restrictions on [WOLV](fa_web-object-list-view.html), you may need: 
* [Ask detaily to view](fa_prop-order-limit-editor.html) if they are not already there. 
* Ask [property AutoAddUsedInLimitationProperties](fa_prop-order-limit-editor.html) that the view formed dynamically. 
* To determine the mechanism of formation of dynamic representations for detailov through the interface ICSSoft.STORMNET.Windows.Forms.IViewGenerator (located in the Assembly ExternalLangDef). For example, in the version after 14.01.2015 in config add the following line: 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
				...	
	<container>
				  ...
	<register type="ICSSoft.STORMNET.Windows.Forms.IViewGenerator, ExternalLangDef" mapTo="NewPlatform.Flexberry.Web.Page.LimitEditorViewGenerator, NewPlatform.Flexberry.Web.LimitEditor" />
	</container>
</unity>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}