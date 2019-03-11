--- 
title: Flexberry LogService Objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry LogService, Flexberry ORM, logging 
summary: Examples of logging 
toc: true 
permalink: en/fo_log-service-objects.html 
lang: en 
autotranslated: true 
hash: 1a9bb474d657ec7b38ebe58e228e61d7f73674215688136f448cb624ce7afdd2 
--- 

## Logging using Microsoft Enterprise Library and Flexberry. 

When using [Microsoft Enterprise Library Logging Application Block](http://msdn.microsoft.com/en-us/library/ff664569(v=pandp.50).aspx) can be used as a Trace Listener'a class CaseberryDatabaseTraceListener from the Assembly IIS.Flexberry.Logging.MsEntLib. 

An example of topic listeners in the application configuration: 

```csharp
    <listeners>
      <add formatter="Text Formatter"
			  listenerDataType="IIS.Flexberry.Logging.MsEntLib.Configuration.CaseberryDatabaseTraceListenerData, IIS.Flexberry.Logging.MsEntLib, Version=1.0.0.0, Culture=neutral, PublicKeyToken=e89274d6fcfab3e9"
			  traceOutputOptions="None" type="IIS.Flexberry.Logging.MsEntLib.CaseberryDatabaseTraceListener, IIS.Flexberry.Logging.MsEntLib, Version=1.0.0.0, Culture=neutral, PublicKeyToken=e89274d6fcfab3e9"
			  name="Database Trace Listener" />
    </listeners>
``` 

The log record will be saved in the ApplicationLog table. List to view the log can be embedded in an application using the class ApplicationLogL from the Assembly IIS.Flexberry.Logging(Forms): 

```csharp
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Flexberry.Logging.Forms.ApplicationLogL), "Logging", "Application log", ""));
``` 

(code for DesktopCustomizer'a) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}