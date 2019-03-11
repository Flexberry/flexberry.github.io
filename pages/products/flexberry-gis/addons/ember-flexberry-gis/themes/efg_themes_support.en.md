--- 
title: Support to those in the expansion 
sidebar: ember-flexberry-gis_sidebar 
keywords: Flexberry Ember Gis 
toc: true 
permalink: en/efg_themes_support.html 
folder: products/flexberry-gis/ember-flexberry-gis/themes/ 
lang: en 
autotranslated: true 
hash: a1b31c9117b02324f5f6238ce873778f07080fc3fe3968a8bd0bac78dc535364 
summary: Description of themes in the addon 
--- 

## Support the 

The addon ember-flexberry-gis added support for skins, similar to ember-flexberry. 
To use a theme, you need to file `theme.config` and `addon\styles\theme.less` to specify the name of the theme. 

In theme.config specifies the name of the theme for the components that you want to style. 
In theme.less variable `@flexberry-theme` specifies the name of the theme you are using in the expansion. 

Logic work is that first imported the standard styles Semantic-UI from ember-flexberry, then stylized the theme from there, and then it complements the theme of ember-flexberry-gis. 
All import's written in the file `theme.less`. Also, due to the specifics of semantics, you must import the file `addon.less` files variables if they are used for default theme. 

## Adding new features 

As the ideal Semantic-UI does not allow to correctly import .less files in the theme semantics, to style a new components represented in the existing `.overrides` files. 
For example styles for a component sidebar-wrapper specified in the file `sidebar.overrides` and the variables in `sidebar.variables`. Styles new components you must specify in the subject `default`, preferably using the variables in the file `.variables`, then in any of the topics they can be easily reassigned. 

To style the existing elements of the semantics must first attempt to use variables from files `.variables`, and then to redefine them through `.overrides`. 
To know which variables are available for editing from the source files semantic-ui, they are available in `bower_components\semantic-ui\src`. Search variables can be done by copying from the browser CSS selector and look for it in the files directory `semantic-ui\src\definitions`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}