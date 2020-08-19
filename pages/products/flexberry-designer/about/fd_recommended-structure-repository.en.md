---
title: structure of a repository
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, levels of the repository, the repository configuration, stage, types, stages, systems, charts
summary: Definition of levels in the repository, recommendations for Delaney. the model configuration stage of the system and distribution models in the charts
toc: true
permalink: en/fd_recommended-structure-repository.html
lang: en
autotranslated: true
hash: bfbe0f0f379872496c86629f33f37caf6114688e45fb6dc7d9332a4e42f0ae7f
---

## Levels repository

The repository is a repository of all information about models. Information in the repository is stored in a hierarchical manner at the following levels:

The level number | Description
:--------------|:------------------------
Level 1 | Repository - provides a logical division into project groups. The user can choose a repository from the menu "Repository/select repository".
Level 2 | Projects contain all the information within individual developments.
Level 3 | Configuration. Configurations are a convenient tool for version control of models manufactured or and similar tasks.
Level 4 | Phase. Serve to manage the life cycle of the project.
Level 5 | System. The system is the lowest level in the project hierarchy and contains information about the model: diagrams in UML notation, and other objects.

## The recommended division of the configurations under

[Flexberry Designer](fd_flexberry-designer.html) in no way imposes any of the production life cycle of the software, but we recommend the "big" stage inside the following configurations:

* A survey that identifies: functional requirements, main fact, and framework проекта;
* Analysis and design where a detailed clarification of the subject characteristics and the functionality of the system, to get "drawn" the model of the system.
* Object design, where the system model is reduced to a formal appearance that is suitable for generation of the structures of the databases and the source code of the system.

To create a stage as follows: select the configuration and click `Создать`.

![Example](/images/pages/products/flexberry-designer/about/create-stage.png)

Stage can be of two types:

* Stage for analysis and create "base" application. Marked with the letter D (1 in the figure above).
* Stage for analysis. Generation of the database and the application is unavailable. Marked with A (2 in the figure above).

Stage for the analysis can be converted into full version:

![Example](/images/pages/products/flexberry-designer/about the/transform-stage.png)

## Recommended the division of the stages of object design diagrams and system

It should be understood that when any chart added [new classes and the relationships between them](fd_class-diagram.html) (for example), really, they *do not belong to the graph*. Such classes, and relationships belong to the *stage*, which is this chart. Stage keeps the model and diagram are only those or other representations of parts of this model. This can easily be verified:

* create single class diagram
* to draw on her class,
* to call him
* fill in the attributes
* and save the chart.

Then create another class diagram to draw her class to enter the same name: it will automatically displayed the attributes that you entered in the first chart. In fact, it is one and the same class displayed by the two charts.

When you generate the source code generator does not analyze charts and analyzes the structure of the model directly under the stage.

Thus, the model elements are unique within a stage. The model can be grouped into diagrams and systems in any convenient way.

However, there are some rules:

* Systems should be isolated logically valid части;
* On the individual charts are:

 * Model subject structuraly (level data)
 * Types данных;
 * Forms (user interface);
 * Business логика;
 * Application.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}