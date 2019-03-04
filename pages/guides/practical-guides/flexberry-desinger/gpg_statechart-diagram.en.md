--- 
title: Construction of state diagrams 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_statechart-diagram.html 
lang: en 
autotranslated: true 
hash: 91388b4f1b18dde2e54eb2827d02b88a29f76e23b83b02b834f4ff1a78936436 
--- 

[State diagram](fd_statechart-diagram.html) determines the sequence of States of an object caused a sequence of events. 

## build Order chart 

1. Create a state diagram. For example, for objects of class `Заказ`. 
2. Specification precedents, it follows that the order can be in three States: 
* `Новый`; 
* `Оплаченный`; 
* `Отмененный`. 

In the state of `Новый` order falls immediately after its establishment, and is there until the time of his transfer Manager in the state of `Оплаченный`. Event to transition is the receipt of money in cash. The transition payment shall be made not later than 10 days from the day of placing the order. In case if payment is not made within the allotted 10 days is later, the order goes into a state of `Отмененный`. The corresponding state diagram is shown in figure: 

![](/images/pages/guides/flexberry-designer/statechart-diagram1.png) 

3.To save the graph. 
4.Create a state diagram for objects of the following class. For example, `Накладная`. 
5.Construction of state diagrams, for example, the bill of lading. All newly created invoices fall into a state of `Новая`. After printing an invoice it goes into a state of `Выписанная`. At this point, the electronic invoice becomes available to the storeman, and he begins assembling the order. At the end of the Assembly storekeeper remits the invoice to the state `Готовая`. If for some reason the warehouse was not the right product (marriage in the party, late provider, etc.), making impossible the Assembly of the order, the invoice goes into a state of `Приостановленная`. Once the product is shipped to the customer, the invoice goes into a state of `Отгруженная`. State diagram for the invoice shown in the figure: 

![](/images/pages/guides/flexberry-designer/statechart -: diagram2.png) 

6.To save the graph. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [charting the interaction](gpg_interaction-diagram.html) 
* [Deployment diagram](gpg_deployment-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/