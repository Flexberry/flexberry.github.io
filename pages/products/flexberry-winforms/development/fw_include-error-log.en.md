--- 
title: How to enable error log 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Windows UI (forms), Deployment 
summary: See how to enable error log in the configuration file 
toc: true 
permalink: en/fw_include-error-log.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: dbe07e2be46decb32f3099c41acfcdee2a95b93c61a35c1f2be8562cd74a11dc 
--- 

To enable the error log, it is sufficient to specify this attribute in the configuration file: 

```xml   
<add key="ErrorLog" value="true" />
``` 

All ErrorBox-s will log errors to a. csv file in the folder with the application. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}