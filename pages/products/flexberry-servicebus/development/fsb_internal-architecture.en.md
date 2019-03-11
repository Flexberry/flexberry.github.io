--- 
title: Internal architecture Flexberry Service Bus 
sidebar: flexberry-servicebus_sidebar 
keywords: ESB 
toc: true 
permalink: en/fsb_internal-architecture.html 
lang: en 
autotranslated: true 
hash: 004c2a355529ddae4e92a02345a60f9e3199e1b1b71e7214595239591b105fbc 
summary: describes the components of the enterprise service bus. 
--- 

## Instead of introducing 

`Flexberry Service Bus` built on component architecture. 

## Components Flexberry Service Bus 

`Flexberry Service Bus` includes the following components: 

* **ReceivingManager** - the component receiving the messages. 
* **SendingManager** - the component sending the message. 
* **SubscriptionsManager** - the component subscription management. 
* **ObjectRepository** - abstraction over the ORM to access the repository. 
* **"WcfService" ** - the component for client interaction with `Flexberry Bus` Service through WCF technology. 
* **WebApiService** - the component for client interaction with `Flexberry Service Bus` REST-style. 
* **MailScanningService** - the component for client interaction with `Flexberry Service Bus` via e-mail. 
* **StatisticsService** component collects statistics `Flexberry Service Bus`. 
* **RerouterService** 
* **CrossBusCommunicationService** component for the interaction of two instances `Flexberry Service Bus`. 
* **Logger** - component for logging process work `Flexberry Service Bus`. 

## Interaction of components Flexberry Service Bus 

The description of the interaction. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}