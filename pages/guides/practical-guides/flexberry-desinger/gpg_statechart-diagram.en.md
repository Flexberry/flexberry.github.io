---
title: Construction of state diagrams
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_statechart-diagram.html
lang: en 
autotranslated: true 
hash: 104df52e458a016f71f2e0c02c092a99dbf216859c370ce328102d87a366a3da
---

[State diagram](fd_statechart-diagram.html) determines the sequence of States of an object caused a sequence of events.

## build Order chart

1. Create a state diagram. For example, for objects of class "Order".
2. Specification precedents, it follows that the order can be in three States: 
* "New"; 
* Paid ; 
* "Cancelled".

In the New state order falls immediately after its establishment, and is there until the time of his transfer Manager in the status "Paid". Event to transition is the receipt of money in cash. The transition payment shall be made not later than 10 days from the day of placing the order. In case if payment is not made within the allotted 10 days is later, the order status changes to "Cancelled". The corresponding state diagram is shown in figure:

![](/images/pages/guides/flexberry-designer/statechart-diagram1.png)

3.To save the graph. 
4.Create a state diagram for objects of the following class. For Example, "Invoice".
5.Construction of state diagrams, for example, the bill of lading. All newly created invoices come in as "New". After printing an invoice it goes into a state of "Discharged". At this point, the electronic invoice becomes available to the storeman, and he begins assembling the order. At the end of the Assembly storekeeper remits the invoice to "Ready". If for some reason the warehouse was not the right product (marriage in the party, late provider, etc.), making impossible the Assembly of the order, the invoice goes into a Suspended state. Once the product is shipped to the customer, the invoice status changes to "Shipped". State diagram for the invoice shown in the figure:

![](/images/pages/guides/flexberry-designer/statechart -: diagram2.png)

6.To save the graph.

## Go

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [charting the interaction](gpg_interaction-diagram.html)
* [Deployment diagram](gpg_deployment-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/