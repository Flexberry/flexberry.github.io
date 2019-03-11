--- 
title: Indication of the current date 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_date-time-now.html 
lang: en 
autotranslated: true 
hash: 75244aaa2cd4fb8f56f5b7b0b96789e7bf4138cc40717534faada4423fce5ed6 
--- 

Objective: in the type attribute `DateTime` default must bear the current date. 

To the current date stamped automatically, you must: 

1.Write on the diagram the initial value as follows 

![](/images/pages/guides/flexberry-aspnet/date-now.png) 

2.To regenerate the objects. 

3.Verify that the custom attribute is of type `DateTime` in the source code of this class is defined as `System.DateTime.Now`. 
For example, the attribute `ДатаЗаполнения` class `Документ`: 

```csharp
private System.DateTime fДатаЗаполнения = System.DateTime.Now;
``` 

__Note:__ According to `C#` initializers are equivalent to the class constructor by default, so each time you design a new `Документа` and any of its successor will bear the current time. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [the Imposition of restrictions on LookUp](gpg_limitfunction-for-lookup.html) 
* [Automatic calculation](gpg_auto-calculation.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}