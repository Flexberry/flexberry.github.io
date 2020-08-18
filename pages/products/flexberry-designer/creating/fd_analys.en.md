---
title: the analysis Phase
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, analysis, model, problem, application, types of diagrams, UML, stages of application creation
summary: description of the analysis phase, the creation model and graph types
toc: true
permalink: en/fd_analys.html
lang: en
autotranslated: true
hash: e5f5c6bbea3d8c247193f070318c162b0745050af0ee35bb1042a9b2e1d124fd
---

This article describes the analysis phase of application creation with the help of [Flexberry Desinger](fd_flexberry-designer.html).
The result of this phase is the creation of the application model.

## The algorithm for creating the model

1. Awareness of the problem.
2. The creation of the application model.

## Awareness of the problem

Platform Flexberry offers the following options:

* Request General description of the problem.
* Request composed with the specification.
* A query is composed of a model and a prototype application.

## The creation of the application model

For modeling application uses language [UML](http://ru.wikipedia.org/wiki/UML). The creation of a model in UML in the analysis phase, reduces the likelihood of disagreements and misunderstandings at later stages. It is also worth noting that UML is specifically designed to describe the model in [Model-Driven development](fd_code-generation.html).

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
 * Should not be relations with a multiplicity of "1to1"
 * There must be links with the plurality "*to*"
* Check class names - class names __should not__ include spaces
* Signing of relationships between classes
 * All communications must be signed by the "1"
 * Signatures should not contain spaces
* Append the attribute types of classes (more about [map types](fd_types-map.html) and [class attributes](fo_attributes-class-data.html))
 * Make sure that you are using [Nullable counterparts](fd_nullable-types.html) of the appropriate type (DateTime, int, bool, decimal...)
 * Filling in default values (for details about [default values](fo_features-dafault-value.html)).

## The next stage

After creating the application model, you can begin to create a [minimal working application](fd_prototype-creation.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}