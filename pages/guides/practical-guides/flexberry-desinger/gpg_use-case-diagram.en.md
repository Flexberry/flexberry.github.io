--- 
title: Building entity relationship diagram 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_use-case-diagram.html 
lang: en 
autotranslated: true 
hash: 4633955e12c2383bc79fbb7ec9150e1e8c7018c29c558f977a4e90283d7d3eb5 
--- 

## a summary of the diagram of use cases 

[Chart options use](fd_use-case-diagram.html) is the most common representation of functional requirements to the system. For the subsequent design of the system requires more specific details, which are described in the document called `сценарием option использования` or `потоком событий` (`flow of events`). The scenario in detail, documenting the process of actor interaction with the system implemented in the framework of the use case. Main flow of events describes the normal course of events (if no errors). Alternative flows describe deviations from the normal course of events (erroneous situations) and their treatment. 

__The advantages of the model variants to use__ is that it: 

* defines users and border системы; 
* defines system интерфейс; 
* convenient for users to communicate with разработчиками; 
* used for writing тестов; 
* is the basis for writing custom документации; 
* fits well in any design techniques (as object-oriented and structural). 

## the Basic elements of diagrams using 

![](/images/pages/guides/flexberry-designer/actor.png) `Активный субъект` (`actor`) is identified with something or someone interacting with the system, i.e. plays a role in relation to the system, it may not necessarily be the user of the future system, it may also be an external system. 

![](/images/pages/guides/flexberry-designer/use cases.png) `Варианты of use (use cases)` can simulate a dialogue between an active subject and the system and display functions of the system. Each use case has a flow of events that occur as you perform the relevant functions of the system. In describing the flow of events is defined that must be implemented, and ignored the aspects of how it's done. 

Between an active actor and a use established communication `ассоциация` (`association relationship`), which performs a communicative function, informing about the interaction of the subject with the system within a particular use case. The direction of the relationship indicates who (the subject or the system) is the initiator of the interaction. 
In addition to the relations between an actor and a use case, communication can be established between use cases. Connections are of two types - `включающими` (`inclusive`) and `расширяющими` (`extensive`). 

## build Order Usecase Diagram 

1. To create a usecase diagram with the name of the «Main functionality» 
2. To perform any of the active actors must interact with the future system. 
3. To create an actor s. (E.g., Manager, Accountant, and Storekeeper). 
4. To create precedents. For example, 
* Execution of the order. 
* Processing invoices. 
* Making the invoice. 
* The results of the product. 
5. To clarify, you can use comments. 
6. To set the context for the relationship (consider what the precedents are in a dependency relationship). 
7. The result is this chart: 

![](/images/pages/guides/flexberry-designer/use-case-diagram.png) 

8.To save the graph 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [problem Statement](gpg_formulation-problem.html) 
* [Diagram construction activities](gpg_activity-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}