--- 
title: the Thesaurus for Flexberry Service Bus 
sidebar: flexberry-servicebus_sidebar 
keywords: DnsIdentity, Callback, Type, message, Bus 
toc: true 
permalink: en/fsb_thesaurus.html 
lang: en 
autotranslated: true 
hash: a38dfe30c5ac021af0adeb71c6e5d363c027987d3c1729d99450befa0ee4bc9e 
summary: Key terms and definitions related to Flexberry Service Bus. 
--- 

## concepts 

* **The bus client** - the information system is being integrated with bus, can send and receive messages, characterized by the following properties: 
* **ID** - client ID (a GUID) 
* **Name** - used for visual identification of the client (displayed in the client list administrative application) 
* **Address** - bus for delivering messages to customers with the callback subscription 
* **DnsIdentity** - 
* **Message** - the information unit is transmitted between the bus and the customers, is characterized by the following properties: 
* **Body** - the actual content of the message 
* **Tags** - method of structuring messages of the same type 
* **Group** - the way podderjaniya messages to date, if message was transmitted with the group and thus the tire has not delivered the message to the same group, the message stored in the bus will be removed 
* **Attachment** - message can contain attachments 
* **The message type** message type 
* **Priority** - messages with higher priority are sent first 
* **Event** - a message without a body 
* **The message type** message characteristic that is used to implement routing 
* **Subscribe** - way message routing, characterized by the following properties: 
* **Client** - the ID of the client wishing to receive messages 
* **The message type** - ID of message type which wished to the client 
* **Date of termination** - the date after which the subscription becomes relevant 
* **Callback** - initializer send the message itself will tire 
* **Transfer method** how the message is delivered to the client 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}