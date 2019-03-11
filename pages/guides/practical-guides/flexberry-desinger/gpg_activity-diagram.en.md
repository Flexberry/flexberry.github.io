--- 
title: diagram of activities 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_activity-diagram.html 
lang: en 
autotranslated: true 
hash: e9216da045ccb79a7e10cdee5532a8fc042b68113c625b158a6ae8d366ae451f 
--- 

## Brief theoretical information 

[Chart of activities](fd_activity-diagram.html) as state diagram, `отражает dynamic aspects of behavior системы`. Essentially, this diagram is a flowchart that shows the flow of control passes from one activity to another. 

As an example, it is proposed to consider, will the activity diagram to describe the process of selling products to customers. This will allow better understanding of the situation of action. 

## build Order charts of activities 

1. Create a chart of activities. 
2. For example, the business process involves four objects: customer, Manager, accountant, and storekeeper. You should create `дорожки` (`swimlanes`) for each object. Each of the pathways responsible for specific actions the object with which it is associated. 
3. Next, you need to place on the chart all activities/actions performed on an object. 
4. The result is a diagram. For example, the following: 

![](/images/pages/guides/flexberry-designer/activity-diagram.png) 

5.To save the graph. 

This diagram can also `оказаться reference for построения` [class diagram](fd_class-diagram.html). 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Build entity relationship diagram](gpg_use-case-diagram.html) 
* [Class diagram](gpg_class-diagram.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}