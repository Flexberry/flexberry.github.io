--- 
title: charting the interaction 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_interaction-diagram.html 
lang: en 
autotranslated: true 
hash: c65bc7400a802c0aaaf64e31c24860edce24ce3643f366de02bfa915901e5c6c 
--- 

## Brief theoretical information 

In UML notation an interaction of elements is considered in the information aspect of communication. In other words, the interacting objects exchange some information. This information is a complete message. 
To describe the interaction of objects involved in a precedent use scripts. `Сценарий` is an instance of precedent that defines one of the possible flows of events of a given precedent. The precedent itself is an interweaving of scenarios – as the main, represent the normal course of events, and support defining the logic of the functioning of the system in situations of the form `что would happen if...`. In the early stages of system design, usually limited to a review of the basic scenario for each identified use case. 

For example, the implementation of the precedent `Оформление заказа`: 

![](/images/pages/guides/flexberry-designer/precedent.png) 

For this implementation you should build [sequence diagram](fd_sequence-diagram.html), which will describe `сценарий the new заказа`. 

## build Order charts of the sequences (Sequence Diagram) 

1. To create a sequence diagram. For example, `Создание new заказа`. 
2. This diagram should reflect the interaction of objects classes. For example, `Менеджер`, `Списковая form of goods складе`, `Списковая form заказов`, `Форма edit заказов`, `Заказ`, `Позиция Заказов`. The diagram shown in figure: 

![](/images/pages/guides/flexberry-designer/sequence-diagram.jpg) 

3. To save the graph. 

Sequence diagrams not only show the interaction of objects, but also allow us to determine/to find operations that needs to have certain classes. 

## Brief theoretical information about the chart of cooperation 

[Collaboration diagram](fd_collaboration-diagram.html) is an alternative way of describing the interaction of objects and focuses primarily on the organization of the objects. Messages between objects are denoted by arrows, however, their temporal sequence is determined by the numbered arrows. 

To create an implementation of the precedent `Оформление накладных` is to build a diagram of cooperation for the script `Печать накладной`. 

![](/images/pages/guides/flexberry-designer/precedent1.png) 

## build Order charts of the cooperation (Collaboration Diagram) 

1. Create a chart of cooperation 
2. Place the following elements: 

![](/images/pages/guides/flexberry-designer/collaboration-diagram.png) 

3.To save the graph. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [class diagram](gpg_class-diagram.html) 
* [Build Statecharts](gpg_statechart-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/