--- 
title: Possible locations of a memory leak in web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_memory-leaks.html 
lang: en 
autotranslated: true 
hash: 553700398a5ba32d337f00662b7557e92faf033bb998744bc256fe6a0d97bcc6 
--- 

The first is to pay attention to: 

* Static classes and attributes. 
* Subscribe to events. Garbage collector does not collect objects on which someone else signed. 
* Any external resources that are not closed properly: all files, DB connection, etc. 
* Settings for the user session. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}