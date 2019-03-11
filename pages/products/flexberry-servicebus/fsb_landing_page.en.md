--- 
title: Flexberry Service Bus 
keywords: ESB, Messaging 
sidebar: flexberry-servicebus_sidebar 
toc: false 
permalink: en/fsb_landing_page.html 
lang: en 
autotranslated: true 
hash: ae18512ebfa965820b73623ca69faf7ca55de14506c0db6fdc65e71681217018 
summary: Functional subsystem Flexberry platform for integration of information systems. 
--- 

## About subsystem 

`Flexberry Service Bus` is [an Open Source solution](fsb_development.html) for the integration of information systems based on the paradigm of Enterprise Service Bus](gbt_integration.html). This solution allows you to create complex information systems operating in a single information space. As example the [SOA-architecture](gbt_integration.html) information system, which at its base uses the service bus. 
Basic terms and definitions concerning Flexberry Service Bus made in [thesaurus](fsb_thesaurus.html). 

## Install and run 

`Flexberry Service Bus` available for deployment as Docker images. The process of installing and running described in the relevant [instructions](fsb_installation.html). 

## Architecture 

`Flexberry Service Bus` includes the following components: 
* [Service bus](fsb_service.html) carries out reception and transmission of messages, classifies the facts of data transfer between clients 
* [An administrative application bus](fsb_editor.html) - allows you to configure the bus and to control data streams 
* [Database bus](fsb_database.html) - contains messages waiting to be доставки; settings шины; statistical information about the facts of data transfer between clients 
* [The adapters](fsb_adapters.html) - client-side part of components of the tire, which is specific to each connected to the bus applications 

[Detailed description of the architecture is provided in a separate article](fsb_architecture.html). 

## Opportunities 

`Flexberry Service Bus` provides the following [features](fsb_features.html): 
* Guaranteed delivery of messages 
* Connection via a WCF interface 
* Connection via a REST interface 
* The client callback (callback) for immediate delivery to the recipient 
* Logging and statistics collection of the transmitted messages 

[Learn more about the capabilities of the tire.](fsb_features.html) 

## applications Connecting to the bus 

Connection applications that need to integrate through a service bus is done by writing modules of the call bus adapters. Before you start developing adapters are recommended to read [the relevant regulations](fsb_adapters.html). 

## Developing and making code changes 

`Flexberry Service Bus` developed [on GitHub in public repositories](fsb_development.html) as the team of platform Flexberry, and the community. Anyone can take part in the finalization of the tire.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}