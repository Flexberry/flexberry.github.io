--- 
title: the analysis Phase 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, analysis, model, problem, application, types of diagrams, UML, stages of application creation 
summary: description of the analysis phase, the creation model and graph types 
toc: true 
permalink: en/fd_analys.html 
lang: en 
autotranslated: true 
hash: 6cd0e131371db4cef70cabb1f5058982f3f50d9156db743b91cb9e6686f59795 
--- 

This article describes the analysis phase of application creation with the help of [Flexberry Desinger](fd_flexberry-designer.html). 
The result of this phase is the creation of the application model. 

## the Algorithm for creating the model 

1. Awareness of the problem. 
2. The creation of the application model. 

## awareness of the problem 

Platform Flexberry offers the following options: 

* Request General description of the problem. 
* Request composed with the specification. 
* A query is composed of a model and a prototype application. 

## the Creation of the application model 

For modeling application uses language [UML](http://ru.wikipedia.org/wiki/UML). The creation of a model in UML in the analysis phase, reduces the likelihood of disagreements and misunderstandings at later stages. It is also worth noting that UML specially designed to model [Model-Driven development](fd_code-generation.html). 

Minimum required the minimum is only charts [class diagram](fd_class-diagram.html). 

It is also recommended to create [a diagram of the use cases](fd_use-case-diagram.html) and [state diagram](fd_statechart-diagram.html). 

You can create other chart types: 

* [activity](fd_activity-diagram.html) 
* [deployment](fd_deployment-diagram.html) 
* [collaboration](fd_collaboration-diagram.html) 
* [sequence](fd_sequence-diagram.html) 

Description of the process of creating the chart can be found in [article Editor UML diagrams](fd_editing-diagram.html). 

## Requirements class diagram 

Created class diagram must satisfy the following requirements: 

* Check class relationships 
* There must be relationships of type "Aggregation" (can only be Association and composition) 
* Should not be relations with a multiplicity of "1 to 1" 
* There must be links with the plurality "* to *" 
* Check class names - class names __should not__ include spaces 
* Signing of relationships between classes 
* All communications must be signed by the "1" 
* Signatures should not contain spaces 
* Checking the types of the class attributes (more about [map types](fd_types-map.html) and [class attributes](fo_attributes-class-data.html)) 
* Ensure that you use [Nullable counterparts](fd_nullable-types.html) of the appropriate type (DateTime, int, bool, decimal...) 
* Filling in default values (for details about [default values](fo_features-dafault-value.html)).


## the Next stage 

After creating the application model, you can begin to create a [minimal working application](fd_prototype-creation.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}