--- 
title: Introduction to systems integration 
keywords: Programming 
sidebar: guide-base-tech_sidebar 
toc: true 
permalink: en/gbt_integration.html 
folder: guides/base-tech/system-integration/ 
lang: en 
autotranslated: true 
hash: b264190a09db3804722d2bdda6bfe3144b358a8d25365dbabfb9ff60ba7d4e1a 
--- 

## Brief description 

### Service-oriented architecture and the integration of information systems 

Today, Russian enterprises and organizations have developed it infrastructures, numerous application systems (accounting, HR, ERP and CRM systems, billing, and other applications, including specialized). These it systems record sufficient baseline information to support operational decision-making. But the difficulty lies in the fact that these» «raw, detailed data you need to quickly collect and process (to monitor and check uniform formats, to aggregate and analyze), which is necessary to use the integration and analytic technologies. 

In addition, increasing the number of applied systems in enterprises and organizatsiy to the fore the problem of their integration: the introduction of new, replacement or modernization is required, as a rule, any changes of integration between them, or establishing them from scratch. Moreover, the continuous development of business processes of the enterprise leads to constant the changes of integration between applications, even if the new application does not appear. 

Currently, the most sophisticated approach to solving the problems of application integration is the development of [the corporate information system (CIS)](https://dvc.academic.ru/dic.nsf/ruwiki/433085) of the enterprise according to the concept of [service-oriented architecture — SOA (Service-Oriented Architecture)](https://ru.wikipedia.org/wiki/Сервис-ориентированная_архитектура). 

Central to SOA is the [enterprise service bus — ESB (Enterprise Service Bus)](https://ru.wikipedia.org/wiki/Сервисная_шина_предприятия) (although enterprise service bus is not necessarily used in the case of a service-oriented architecture). 

![](/images/pages/guides/base-technologies/integration/esb.png) 

Services in the image are usually different enterprise information systems between which you want to organize the information exchange. 

The basic principle of the service bus is the concentration of exchanging messages between different systems through a single point, which, if necessary, is provided by transaction control, data processing, safety messages. All settings of the processing and message transmission are assumed as concentrated at a single point, and formed in terms of services, therefore when replacing any information system connected to the bus, there is no need to reconfigure other systems. 

### the Main scenarios of applying service-oriented architecture 

#### the Management of large geographically distributed companies 

For the Russian economy is characterized by large geographically distributed companies (holding companies), formed as a result of mergers and acquisitions of previously independent companies and continue to undergo changes to its structure. Effective operational management of such a conglomerate is impossible without creation of a flexible information space on the basis of disparate legacy it systems 
its constituent enterprises. 

![](/images/pages/guides/base-technologies/integration/esb-destributed.png) 

At the level of the it systems create a single, flexible information space of the holding includes three main objectives: 

* Create a single entry point for access by geographically distributed users to the integrated centralized applications and databases. 
* Integration of heterogeneous legacy applications and databases and their centralization in enterprise data centers. 
* Consolidation of compute resourcesharing in the corporate data processing Center (DPC). 

Tools SOA, in particular, portal and enterprise service bus (ESB), is optimal for solving the first two of the above tasks, and significantly increases the efficiency of the consolidation of computing resources. 

#### Managing services and business processes 

Management of services and ensuring their business processes is a key challenge for enterprises and organizations in such sectors as telecommunications, financial services, distribution, retail, transport, public authorities, providing services to the public. 

Creating and bringing to market new services, the actual provision of services to consumers with a guaranteed level of quality, modification of services due to changes in market conditions is not a trivial task. Including due to the complexity of coordination work involved information systems, because most services are formed using more than one information system, including the system of third-party contractors. Therefore, flexible integration of these systems is extremely relevant for service companies. 

![](/images/pages/guides/base-technologies/integration/esb-business.png) 

Integration is also important in connection with necessity of increase of efficiency and reduction of cost to market for new services. This requires that the functionality of existing information systems as fully as possible has been used to provide new services, minimizing the need to purchase or develop additional functionality. In the limiting case when modifying an existing service or creating a new one may be just enough existing information systems with the creation (for new services) or migrate (existing) integration links between them. 

#### process Management-intensive processing 

Formally the work of many organizations can be defined as the processing of many different incoming documents, which are generated by a new flow. This processing takes place in accordance with the approved regulations. This is especially true for businesses and organizations with a large number of customers and/or suppliers that produce a wide range of products or services. 

Automation regulations, workflow, conversion of paper documents into electronic format and organization of their storage in electronic archive is a timely, important service which should be established in such organizations. For quality provision of this service needs to be fully integrated applications where a separate component acts [content management system class ECM (Enterprise Content Management)](https://ru.wikipedia.org/wiki/Управление_корпоративным_контентом). 

![](/images/pages/guides/base-technologies/integration/esb ecm.png) 

For many industrial enterprises are not less important documents of a different type — research and development data (KTD), which are generated in CAD and in the future on their basis there are other documents, including administrative. In this case, a bunch of SOA-platform ECM is complemented by the CAD system. This allows to extend all the benefits and opportunities of workflow systems to work with such specific documents as blueprints. 

## Links to materials for the study 

### Articles and materials on the subject 

* [Integrating applications and services (BCC)](http://bcc.ru/pdf/bcc_soa.pdf) 

### Video 

* [Service Oriented Architecture Oracle Service Bus](https://www.youtube.com/watch?v=p45WDeEky_o) 

## Go 

* [Methods and approaches to systems integration](gbt_integration-methods.html) 
* [Course home page](gbt_landing-page.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}