--- 
title: generation Plugin documentation charts 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, generation, documentation, utilities, 
summary: the location of the plug-in generation parameters, the result of generation 
toc: true 
permalink: en/fd_doc-gen.html 
lang: en 
autotranslated: true 
hash: a768fd8e8a2c47f13fae06e783f7f45a1b9c8894b35d28b792f354b9d2b4ad4b 
--- 

The plugin documentation generation is in paragraph `Утилиты` for each plugin products [plataforma Flexberry](fp_architecture.html). For example, `ORM -> Utilities -> Generate documentation диаграммам`. 

This will open a window with a list of parameters that can be included in the final document. 

![Document settings](/images/pages/products/flexberry-designer/generate/codegen-parameters.png) 

### Options for generating documentation 

| ____ | __Description__ | 
|--------------|--------------| 
|Stolbtsy - list of attributes class| 
|`Название`|attribute Name class| 
|`Тип`|the attribute Type for application forms| 
|`Тип in БД`|the attribute Type for the database| 
|`Хранилище`|class Name in the database| 
|`Обязательность`|Mandatory or not attribute to save the class| 
|`Хранимость`|or no Stored attribute (stored/ not stored)| 
|`Описание`|Comment to the attribute| 
|Skrytye attributes - the attributes existing in the database table but not displayed on the chart and forme| 
|`Первичный ключ`|table Primary key| 
|`ДатаСоздания`|date and time the record was created| 
|`ДатаИзменения`|date and time of the last change| 
|Vid - appearance document| 
|`Заголовки before each классом`|whether to Display headers before each class| 
|`В terms данных` databases|to Use the terminology of databases, or programming| 

### the Result of generating documentation 

Click `Старт` creates two documents: 

* `MS Excel` - list of classes and set parameters 
* `.TXT` - list of classes and properties with long title names 

The plugin allows to open files to view a question in a popup window. To view the press `Да`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
