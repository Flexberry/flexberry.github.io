---
title: Description of the process of code generation
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, architecture, model, brackets programmer
summary: the Nuances of generation of the application through the change chart
toc: true
permalink: en/fd_code-generation.html
lang: en
autotranslated: true
hash: 0c02f49c711e8fd87485f7534504cbf224a7d7d679589c30bd035846054b2ab9
---

## Model-Driven Development

When developing applications using [Flexberry Designer](fd_flexberry-designer.html) use [Model-Driven Architecture](https://ru.wikipedia.org/wiki/Model_Driven_Architecture) - that is, development is by creating and refining models.

Unfortunately, to confine the creation of a model is impossible, since you need to implement are unique to each project logic. However, [Flexberry Designer](fd_flexberry-designer.html) allows you to take on a routine of creating applications and significantly speed up the development process.

### Model

As the renderer model used by [UML](http://ru.wikipedia.org/wiki/UML). However, the model is not limited to [define charts](fd_editing-diagram.html): there are many metadata affecting the generation of the executable code of the application.

## Development through the modification of the model

After the creation of the first model there is possibility to generate executable code for the application.

Adding unique functionality to the generated application by appending executable code, but often there is a need for changes to the model. To make the programmer changes are not lost when changes to the model were created [brackets programmer](fo_programmer-brackets.html): all code written within these parentheses will not be lost when changes are made to the model and re-generate the executable code. All the code that lies outside the brackets, in contrast, is overwritten in accordance with the current state of the model.

{% include note.html content="the Model must always comply with the code. If you have the opportunity to make some changes by modifying the model, these changes need to be made through the model, not by modifying the generated code!" %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}