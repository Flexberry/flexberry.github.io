---
title: search Function
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, plugins
summary: the Features of the search of fragments of diagrams for selected projects
toc: true
permalink: en/fo_intelli-search-plugin.html
lang: en
autotranslated: true
hash: 1c84b7f9ff1d14c40a7882c7a94b0138cb73ebe59b12e60f088a601831cac990
---

Expansion module [Flexberry Designer](fd_flexberry-designer.html): `IntelliSearch` developed to search for pieces of diagrams for selected projects of the repository.

## Connection

The connection is made according to the following algorithm:

* Register the plugin in CASEBERRY
 * Open menu `Полезности` - `Модули`
 * Click `Создать`
 * Specify the library path `IntelliSearch.dll`

* Add a plugin to the repository
 * Open the properties of the repository by selecting the menu item `Репозитарий` - `Редактировать свойства`
 * In section `Модули` `Создать` click in the added row in column `Модуль` to choose `IntelliSearchPlugin`
 * Save changes

{% include note.html content="More on modules and their connection can be found in the article [Modules extend the functionality](fd_flexberry-plugins.html)." %}

This will bring up a menu Stage `IntelliSearchPlugin` that allows you to rebuild the index for a particular Stage and a button `Искать in other стадия` on the class diagram.

## Use the search

To use search, you need to:

* Create an empty class diagram at any stage
* Draw objects (classes, relationships, etc.) that need to be found
* Click `Искать in other стадиях`

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/search-example.png)

* In the window "Stage to search" to select the desired stage

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/search-studys.png)

* If necessary, configure the search parameters (the threshold of relevance and the importance of name matching\data types\defaults\cardinalities) by clicking on the button `Параметры поиска`

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/search-params.png)

* Click `Поиск`.

{% include note.html content="Search indexing requires the stages, if you have selected a large number neyroendokrinnykh stages, the indexing process can take a long time." %}

The result is a tree of stages with screenshots of charts, which found similar fragments. A screenshot of the chart to open in full size.

![Example](/images/pages/products/flexberry-orm/module-flexberry-designer/search-results.png)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}