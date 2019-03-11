--- 
title: Definition of user roles and their functions 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_identifying-roles.html 
lang: en 
autotranslated: true 
hash: c8e467d6ae68d4977a5c4e81752bf42d8bbf6d6c8c3993cf8005ca9e20d3e252 
--- 

Proektiruemy system example to be worked up in accordance with the requirements. You must first return to the [chart options use](gpg_use-case-diagram.html) and to assess which actors have to interact with the system. Based on this information, you can see the user roles that should be used in the system. 

![](/images/pages/guides/flexberry-aspnet/use-case-diagram-old.png) 

Based on this chart, we can conclude that the system will be present `три role пользователей`: 

* Manager 
* Accountant 
* Storekeeper 

In addition, on the basis of information from other charts previously constructed (for example, [class diagram](gpg_class-diagram.html)), it becomes apparent that not all application functions are covered by the above use cases (for example, do not include work with employees and control of warehouses). 

Diagram of use cases should be clarified in accordance with the above observations (to simplify the problem, let the control over the warehouses are storekeepers, and maintain a list of employees managers): 

![](/images/pages/guides/flexberry-aspnet/use-case-diagram-new.png) 

Thus, the listed user roles must be available the following functions (the permissions for the users in the framework of this workshop not illustrated): 

* The Manager must be able to work with products, orders, documents, invoices and employees. 
* Accountant to be able needs to work with documents, invoices and orders. 
* Storekeeper have the opportunity must work with warehouses, goods, goods in stock. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Enable ASP.NET State Service](gpg_asp-net-state-service.html) 
* [Add user roles from an application](gpg_adding-user-roles.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}