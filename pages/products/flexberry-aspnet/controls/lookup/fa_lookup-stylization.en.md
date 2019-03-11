--- 
title: Pastiche LookUp 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_lookup-stylization.html 
lang: en 
autotranslated: true 
hash: 344131f4c455cfd136f26658ae53ac6c817cc73ff845ce9af4c3695dc9788565 
--- 

## customize the appearance of icons lucapa in the theme BlueSky 

The topic BlueSky was Dabuleni few variables in stake of font icons for buttons lucapa. 

To change icons you need to lucapa `Controls\Lookup\_Variables.less` to change the value of the relevant variables: 

```css
@lookupShowObjectBtn: '\e879';    // The button "Show object". 
@lookupLookupBtnIcon: '\e81d';    // Button "Select". 
@lookupClearBtnIcon: '\e806';     // The "Clear" Button. 
``` 

Select from the following icons: 

![](/images/pages/products/flexberry-aspnet/themes/lookup-icons.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}