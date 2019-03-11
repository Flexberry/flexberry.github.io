--- 
title: Creating the application 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, application, create applications,stages of application, analysis, protop, prototyping, modulii, scripts, customize 
summary: Stages of application, analysis, prototyping and scenarios dorabotki application 
toc: true 
permalink: en/fd_flexberry.html 
lang: en 
autotranslated: true 
hash: 7cf0ff058bd8267cd27e52babe27f5456936f0e7fb7da80be07ba474a4dd6d96 
--- 

## [Approach to Flexberry](fd_model-driven-architecture.html) 

If you are developing on Flexberry the use of the approach "development-driven models" (Model-Driven Development). 

Description of the approach can be found in [article, a description of the methodology MDD](fd_model-driven-architecture.html) (and also in [Wikipedia](http://ru.wikipedia.org/wiki/Model_Driven_Architecture)). 
Implementation the application consists of the following steps: 

* [Analysis](fd_analys.html) 
* [Prototyping](fd_using-quick-prototyping.html) 
* [Revision of the prototype to the final level of the app](fd_development.html) 

The result of two possible scenarios: 

* Generate prototype on the side of the platform and publish to the cloud (as the cloud [Windows Azure](http://www.windowsazure.com)) 
* Generate source code on the side of the platform and placing them in a Git repository. 

## [Analysis](fd_analys.html) 

1. To understand the problem 
2. To visually describe an application using UML 
* Create all the necessary charts 
* [activity](fd_activity-diagram.html) 
* [States](fd_statechart-diagram.html) 
* [use cases](fd_use-case-diagram.html) 
* [deployment](fd_deployment-diagram.html) 
* [collaboration](fd_collaboration-diagram.html) 
* [sequence](fd_sequence-diagram.html) 
* [Create class diagram](fd_editing-diagram.html) 
* Create classes for the entities 
* To establish links between classes 

## [Prototyping](fd_prototype-creation.html) 

1.Create a UML description of the shapes using [the rapid prototyping](fd_prototype-creation.html) 
2.To build the application prototype 
* Includes (automatically): 
* Create a base application and authority 
* Generate application 
* Publish to Azure/Git-repository 

*At this point, the application can run on* 

3.To connect functional modules 
* [If necessary, disable the built-in subsystem powers](efs_secutity.html) 
* [Enable and configure the Auditing subsystem](efs_audit.html) 
* Connect and configure the subsystem integration and running reports 
* Connect and configure the GIS-component for work with electronic maps 
4.[Choose the theme of the visual design of the app](fa_choose-theme.html) 

## [Revision of the prototype to the final level of application](fd_application-development.html) 

### the General principles of the revision application by using Flexberry 

1. [Development through the modification of the model](fd_code-generation.html) 
2. Description of the process of obtaining the source code. 
3. [Description of process of generation of code](fd_code-generation.html). 
4. [Programmer's use of parentheses](fo_programmer-brackets.html). 

#### Scenario the app. 

1. Managing application forms 
* Adding new forms 
* Customize list forms 
* Customize views 
* Adjust button 
* Customizing edit forms and view 
* Customize views 
2. User interface customization 
* Set form title 
* Alignment of elements on forms 
* Bringing to a common style, color and font of form elements 
* Resizable forms 
* Add localization 
3. Setting up visual logic 
* Connect predictive 
* Implementation of mode “Read-only” 
4. Setting data validation 
* Configure validation on the client side 
* Configure validation on the server side 
5. Implementing business logic 
* Adding checks when updating data via a business server. 
* Update multiple objects in one transaction. 
* Check uniqueness of data entered. 
* Check the validity of the data changes object. 
* Adding methods decidi\editing data 
6. Setup error handling 
* Enable\disable error logging 
* Customize form error 
* Customize catching exceptions 





{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}