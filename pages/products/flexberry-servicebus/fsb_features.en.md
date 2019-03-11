--- 
title: Opportunities Flexberry Service Bus 
sidebar: flexberry-servicebus_sidebar 
keywords: Bus 
toc: true 
permalink: en/fsb_features.html 
lang: en 
autotranslated: true 
hash: 34bb0d3a15f980753daa9c5f6292dd07ece2bff6e4c2da7e9634a1899f3d8e72 
summary: Description of the enterprise service bus. 
--- 

The main objective `Flexberry Bus` Service is the transmission of messages to customers, accept messages from senders and delivery of messages to recipients. 

## Main features 

* Receive messages from clients 
* Receive messages by clients 
* Delivery of messages to customers (`callback`) 
* Flow control bus message 
* Interaction through `WCF` interface 
* Interaction through `REST` interface 

## Additional features 

* Logging of process work 
* Gathering statistics on received and transmitted messages 
* Compression statistics 

### Receiving messages from clients 

Sheena receives messages from the clients having [permission](fsb_thesaurus.html) the transmission of messages of this type.[](<br>) 
When you send messages, you can specify [priority](fsb_thesaurus.html), [group](fsb_thesaurus.html) or add [tags](fsb_thesaurus.html).[](<br>) 
To send a message to the bus, you can use one of the currently available interfaces, `WCF` or `REST`. 

### Receiving messages cliente 

Subscribing to a certain message type, the client may receive the bus messages, after receiving customer reports, the bus it is not stored.[](<br>) 
When a message is received, the client can specify an index that would not receive the first message in the queue, the message queue is sorted by priority and time of receipt of the message. Also, you can specify tags which should have the message.[](<br>) 
To receive messages from the bus, you can use one of the currently available interfaces, `WCF` or `REST`. 

### delivery of messages to clients 

If you are using a subscription option `callback`, message delivery is performed at the initiative of the tires specified on the customer [address](fsb_thesaurus.html) selected [method](fsb_thesaurus.html). 

### flow Control messages 

To control message flows developed [administrative application](fsb_editor.html), which lets you: 

* View and edit [message types](fsb_thesaurus.html) 
* View and edit [clients](fsb_thesaurus.html) 
* View and edit the [subscription](fsb_thesaurus.html) 
* To view and edit the [messages](fsb_thesaurus.html) 
* View and edit settings of reference and compression statistics 
* And much more 

### Interaction through `WCF` interface 

One of the possible variants of interaction with the bus is the use of [platform WCF](https://docs.microsoft.com/EN-us/dotnet/framework/wcf/whats-wcf) to call methods provided by the interface bus. At the moment, this interface provides the most complete coverage possible of the tire. 

`WCF` interface allows you to: 

* To transfer the message to the bus 
* Receive messages from the bus 
* Sign clients on message types 

### Interaction through `REST` interface 

One of the possible variants of interaction with the bus is to run queries on [HTTP](https://ru.wikipedia.org/wiki/HTTP) in the stele [REST](https://ru.wikipedia.org/wiki/REST). 

`REST` interface allows you to: 

* To transfer the message to the bus 
* Receive messages from the bus 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}