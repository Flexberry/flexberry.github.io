--- 
title: Flexberry BPM 
keywords: ember 
sidebar: flexberry-bpm_sidebar 
toc: false 
permalink: en/fbpm_components.html 
lang: en 
autotranslated: true 
hash: 738cf7ea6bad615521b837e6ef318ca341c7e2fdf67fc623fe85a97e6bfd58d0 
--- 

## a Family of solutions based on jBPM 

This class of solutions is based on a common technological base – Jboss – family of light, cloud products enterprise-level company RedHat. 
Basic product jBoss workflow version 1.0 was created in 2003 *(see figure below).* In 2011, one of the developers of jBPM, Tom Baeyens has moved to Alfresco and using the specifications and jBPM code base in conjunction with the company Camunda created BPMS Activiti. 
In the period 2003-2013 year based on jBPM version 3 a division of consulting group RUNA creates BPMS workflow-enabled integration with Alfrecso. 
Version jBoss workflow and solutions based on them are presented in the figure below: 
![](/images/pages/products/flexberry-bpm/family-bpms products.png) 

### jBPM 
In addition to the basic kernel, providing support for business processes throughout the life cycle jBPM supports many additional features and tools: 
* The Eclipse editor and the web editor to support the graphical creation of business processes (drag drop &). 
* Support for plug-qmc «manual tasks and services based on WS-HumanTask for including tasks performed by employees. 
* Management console supports the management of the process instances, task lists, and manage task form, reporting. 
* Additional git repository of process models for versioning models and run the correct version of the process. 
* Recording the history (for requests / monitoring / analysis). 
* Integration with Seam, Spring, OSGi, etc.. 

As of version 6.0, jBPM in conjunction with the company RedHat began to develop other integration products: 
* **Drools Rule Engine** – management of business processes based on rule engine. 
* **KIE** *(Knowledge Is Everything)* – integration system, which includes in addition to jBPM and Drools products: 
* **OptaPlanner** – system for optimising the use of business resources, such as routing of vehicles, scheduling, optimization of cloud resources, job scheduling, optimum packing, etc. 
* **UberFire** – web framework for developing extensible desktop and console applications. 
* **Dashbuilder** – a fully featured web application that allows non-technical users to visually create data interfaces (dashboard) for display and monitoring business information. 

The application of these solutions significantly expands the flexibility of creating business solutions and simplifies the mechanism for the creation of the final product. 

**Dignity jBPM** 
* High flexibility and easy dynamic modification of the developed solutions. 
* Native support of versioning of models of business processes.
* Rich set of tools to create a quality product with support for Analytics and monitoring of business processes. 
* Wide opportunities of integration with external systems and applications. 
* The possibility of modelling of complex processes. 
* Support for docker containerization. 

**Disadvantages jBPM** 
* High demands on server hardware. 

### Activiti 
The functionality of Activiti is generally consistent with the functionality of jBPM, because both projects are in the process of its development share ideas. 
In the framework of the Activiti Camunda there is a project, which was implemented the concept of a sharing mechanism processes and built-in mechanism of their processing. However, currently this functionality is also implemented in Activiti. 
Currently, both projects share a common code base. Activiti is more focused on developers, Camunda consumers. 

**The Dignity Of The Activiti** 
* Wide opportunities of integration with external systems and applications. 
* Support mechanisms for the joint use processes and built-in mechanism of their processing. 
* The possibility of modelling of complex processes. 
* Support the document management system Alfresco. 

**Disadvantages Of Activiti** 
* The heaviness of your decisions. 
* Limited the list of supported databases. 

### RunaWFE 
**WFE** – free system of managing business processes and administrative regulations with open source. Distributed under free LGPL. Runa WFE uses its own solutions, and some ideas of projects JBoss jBPM and Activiti, contains a large number of components, the task of which is to provide convenient end user experience. 

**Dignity RunaWFE** 
* Integration of existing disparate enterprise applications. 
* Simple integration with relational databases. 
* Bots to perform automated tasks. 
* Security system, which allows integration with LDAP/MS Active Directory. 
* Integration with Alfresco. 
* Localization for Russian language. 

**Disadvantages RunaWFE** 
* Focus on a rather old codebase jBPM. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}