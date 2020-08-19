--- 
title: Administrative application bus 
sidebar: flexberry-servicebus_sidebar 
keywords: Bus 
toc: true 
permalink: en/fsb_editor.html 
lang: en 
autotranslated: true 
hash: 02b25043311d05bbbd1413b0e85680fb98901cb19f1fe06f02d909a9287a0cd9 
summary: the Purpose and administrative applications service bus. 
--- 

## the Appointment of an administrative application 

The administration application allows you to: 

* To configure forwarding of messages through service bus. 
* To manage the messages in the current time in service bus. 
* To view the statistics information of the service bus. 
* View the audit information of the application service bus. 
* Customization of user privileges, administrative application (if appropriate). 

All settings made via the administration application, stored in the [database bus](https://flexberry.github.io/ru/fsb_database.html). 

The appearance of the main page of the administration application: 

![](/images/pages/products/flexberry-servicebus/components/index.png) 

## Deploy and run administrative applications 

Detailed installation and start-up administrative applications are described in [Installing and running the tyres in Docker](https://flexberry.github.io/ru/fsb_installation.html). 

## set up forwarding of messages through service bus 

In order for applications to exchange messages through service bus, you must: 

1. Create the types of messages to send between applications. 
2. Create customers (senders and recipients). 
3. To create subscriptions for receiving messages. 

Help on terminology, refer to the [thesaurus](https://flexberry.github.io/ru/fsb_thesaurus.html). 

Create these data is done via the menu item "Routing": 
![](/images/pages/products/flexberry-servicebus/components/routing.png) 

### Creating message types 

To create message types, you can go to the appropriate list: 

![](/images/pages/products/flexberry-servicebus/components/message-type-list.png) 

To create a new message type, click Add. To edit an existing message type, click the corresponding line. 

In the opened form must be filled in field "ID" and "Name": 

![](/images/pages/products/flexberry-servicebus/components/message-type-edit.png) 

### Create customers 

To create customers you need to go to the corresponding list: 

![](/images/pages/products/flexberry-servicebus/components/clients-list.png) 

To create a new customer you must click on the "Add" button. To edit an existing customer click on the appropriate line.

When you create a client of a message sender is required besides "Name" and "ID" of the customer be sure to complete the "Permission to send", where you specify the possible types of message sent: 

![](/images/pages/products/flexberry-servicebus/components/clients-sender-edit.png) 

When you create a customer message recipient "Permission to send" to create is not required, but you must specify the address to which the [service bus](https://flexberry.github.io/ru/fsb_service.html) will attempt to deliver the message, and the subscription indicating the type of incoming messages, the period and method of data transmission: 

![](/images/pages/products/flexberry-servicebus/components/clients-receiver-edit.png) 

A subscription can be created or edited on a separate form and on the edit form of a particular client. 

### the Use of client identifiers and message types 

The client identifier or the message type can be any text string. To ensure uniqueness of an identifier can be a GUID, but it is not mandatory. 

Compare identifiers within the service bus usually happens is case-sensitive way, but in some cases it may be relegated to [database bus](https://flexberry.github.io/ru/fsb_database.html). Therefore, the recommended practice is to use identifiers that differ not only in character case, as well as tracking the transfer of customer IDs in the right register. 

It is also necessary to take into account when finding subscriptions to the message sent to the service bus handles client identifiers and the message type in a special way. If the identifier does not match the client ID (or message type), the service bus tries to interpret the ID as a GUID and compare it with a primary key (Primary Key) of the client (or message type). 

## Manage service bus 

To manage messages in a queue within the service bus, you must open the appropriate list: 

![](/images/pages/products/flexberry-servicebus/components/messages-list.png) 

All messages, which at the moment is still not delivered to the recipients, will be displayed in the list. If necessary, you can perform various operations on messages (e.g., delete). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}