--- 
title: ServiceSecurityProvider 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_service-security-provider.html 
lang: en 
autotranslated: true 
hash: d4fb79e6366b61b2be10e041094e39876b8721fcb1d2795164d23355b41f9c65 
--- 

This provider is used in order to make use of the web service in a web application more secure. With it you can specify what method of web service what types of objects can be read from the database. This is because there are quite common techniques in which you can read all the data from the database. 

## Configuration file 

The configuration file is in the folder `/xml` called `ServiceSecurityProvider.xml` 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <method name="GetPropertyValues">
    <type name="IIS.Kokilabari.Cat, Kokilabari(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
    <type name="IIS.Kokilabari.Breed Kokilabari(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </method>

  <method name="LoadObject">
    <type name="IIS.Kokilabari.Breed Kokilabari(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null"/>
  </method>
</root>
``` 

For each method, specify a list of allowed types. 

## using it 

Currently WebTools `AjaxServiceHelper` there is a class that encapsulates the work with the database and settings from the configuration file `ServiceSecurityProvider.xml`. This class (`AjaxServiceHelper`) is called from methods of the web service in a web application. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}