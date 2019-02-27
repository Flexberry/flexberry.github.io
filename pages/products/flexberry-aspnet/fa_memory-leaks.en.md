--- 
title: Possible locations of a memory leak in web applications 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_memory-leaks.html 
lang: en 
autotranslated: true 
hash: 1899b630615ac454da585fd80ace6b6556f36b3ad7860c6fe359793b47dc022f 
--- 

The first is to pay attention to: 

* Static classes and attributes. 
* Subscribe to events. Garbage collector does not collect objects on which someone else signed. 
* Any external resources that are not closed properly: all files, DB connection, etc. 
* Settings for the user session. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/