--- 
title: Development of adapters for applications 
sidebar: flexberry-servicebus_sidebar 
keywords: Bus, Adapter 
toc: true 
permalink: en/fsb_adapters.html 
lang: en 
autotranslated: true 
hash: 916a83b79b7eca40eb5fb5229a99aab7f2984fe3d0cc4b2ef8c0515fac96ffb7 
summary: Adapters are used to connect applications to enterprise bus 
--- 

## Introduction 

The adapter is a client application bus for receiving and/or transmitting messages to the bus. 

## Use `WCF` user interface in adapters 

When using `WCF` user interface the application must implement the invocation methods provided by the interfaces of the bus and the transfer of those calls are [WCF](https://docs.microsoft.com/ru-ru/dotnet/framework/wcf/whats-wcf). 

There are several ways to obtain interfaces tyres: 

* NuGet package [NewPlatform.Flexberry.ServiceBus.ClientTools](https://www.nuget.org/packages/NewPlatform.Flexberry.ServiceBus.ClientTools/) 
* Generate using WSDL 

Example of adapter implementation using `WCF` interface described in [this article](fsb_wcf-adapters-sample.html). 

## Use `REST` interface adapters 

When using `REST` user interface the application has to perform a suitable query to perform those or other operations. 
Description of the queries under consideration are presented in [this state](). 

Example of adapter implementation using `REST` interface described in [this article](fsb_rest-adapters-sample.html). 

## Adapters for subscriptions with the option `callback` 

When using subscriptions with opca `callback`, the fundamental difference between applications is that it must be either a service (service or server), able to receive and process the request from the tire to the delivery of the message. 

The type and format of the request depends on the [transfer method](fsb_thesaurus.html) messages in [subscription](fsb_thesaurus.html), and can be as follows: 

* `WCF` - when using this method, you must implement a method `AcceptMessage` interface `ICallbackClient` 
* `HTTP` - when using this method, you must implement processing `HTTP` query description of the query under consideration are presented in [this state]() 
* `WEB` - when using this method, you must implement a method `AcceptMessage` interface `ICallbackClient` 
* `MAIL` is **experimental feature**, be careful when using 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}