---
title: the Internal structure of the
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: en/ef2_themes_structure.html
folder: products/ember-flexberry/themes/
lang: en
autotranslated: true
hash: e19e57f3233ff1004ab1c25180b24480a4bad1eafe21ff55cf767afef1ff6aef
summary: description of the internal mechanism themes
---

## Device themes

In ember-flexberry for styling the responsible css framework Semantic-UI, it is in a folder /bower_components/semantic-ui.
This folder contains directly the folder themes in /src/themes in them separately for each markup element is a file
`element.variables` to define less variables, containing the various properties of styles. Also, there are files such as
`element.overrides` you can write them a full selectors with the properties.

{% include important.html content="first and foremost, you should override the existing styles using variables in files `.variables`, and then to use `.overrides`." %}

## button.variables
![screenshoot](/images/pages/img_themes/screenshots/variables.jpg)

## button.overides
![screenshoot](/images/pages/img_themes/screenshots/overrides.jpg)

In addition to the themes folder there is a folder /src/definitions in it also each element has its own file `element.less` it
contains all the selectors with variables, which are taken from the files `.variables`.

## button.less
![screenshoot](/images/pages/img_themes/screenshots/less.jpg)

For the selection of the configuration file is responsible /src/theme.config.example, it is possible to specify for each element of his subject,
you can specify the elements of different styles from any number of different themes.

## theme.config.example
![screenshoot](/images/pages/img_themes/screenshots/config_theme.jpg)

Also in the folder /src `semantic are.less` and `theme.less` responsible for import of the items.

## semantic.less
![screenshoot](/images/pages/img_themes/screenshots/semantic_less.jpg)

## theme.less
![screenshoot](/images/pages/img_themes/screenshots/theme_less.jpg)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}