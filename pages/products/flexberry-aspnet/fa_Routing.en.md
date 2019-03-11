--- 
title: Routing in Web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_routing.html 
lang: en 
autotranslated: true 
hash: f3f9ddbfa596222adb72dd9063ea67ba477479e35c5657f8a772e5288be7621b 
--- 

## Routing 

Routing ASP.NET allows you to use URLS that do not map to specific files in a web site. By default, the address `http://server/application/Products.aspx?id=4` will be comparable to the file `Products.aspx` and `id=4` will be used as parameters. The routing allows you to change the names and the parameters that are passed different ways to use user-friendly names that describe his actions. 

For example, the server may obtain the address `http://server/application/Products/show/beverages` and disassemble it as follows: 

* area = Products 
* action = show 
* category = beverages 

that is, this address will replace the address `http://server/application/Products?action=show&category=beverages` 

Of course, this is only possible when the relevant routing. 

Learn more about routing can be read on [MSDN](https://msdn.microsoft.com/ru-ru/default.aspx). 

## routing Support in Flexberry ASP.NET 

To support routing in Flexberry ASP.NET was added class `DynamicPageRoute` and in `Global.asax.cs` added routing for [technological forms](fa_tech-forms-web.html). 

The default path to register forms `Version` and `Log`, the path used is a string of the form `flexberry-dynamic-` page. For example, to form `Version` path will look like the following: `http://applicationName/flexberry-dynamic-Version` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}