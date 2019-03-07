---
title: Внутреннее устройство тем
sidebar: flexberry-ember_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef_themes_structure.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание внутреннего механизма тем оформления
---

## Устройство тем оформления

В ember-flexberry за стилизацию тем отвечает css фреймворк Semantic-UI, он находится в папке /bower_components/semantic-ui.
Эта папка содержит непосредственно сами папки с темами в /src/themes, в них отдельно для каждого элемента разметки есть файл
`element.variables` в котором задаются переменные less, содержащие различные свойства стилей. Также там есть файлы такие как
`element.overrides` в них можно записывать полноценные селекторы со свойствами.

{% include important.html content="В первую очередь следует переопределять существующие стили с помощью переменных в файлах `.variables`, а уже затем использовать `.overrides`." %}

## button.variables
![screenshoot](/images/pages/img_themes/screenshots/variables.jpg)

## button.overides
![screenshoot](/images/pages/img_themes/screenshots/overrides.jpg)

Помимо папки с темами есть папка /src/definitions в ней также для каждого элемента есть свой файл `element.less`, в нём
размещены все селекторы с переменными, которые берутся из файлов `.variables`.

## button.less
![screenshoot](/images/pages/img_themes/screenshots/less.jpg)

За выбор тем отвечает конфигурационный файл /src/theme.config.example, в нем можно указать для каждого елемента свою тему,
можно задать элементам разные стили от любых имеющихся тем.

## theme.config.example
![screenshoot](/images/pages/img_themes/screenshots/config_theme.jpg)

Также в папке /src находятся `semantic.less` и `theme.less` отвечающие за импорт элементов тем.

## semantic.less
![screenshoot](/images/pages/img_themes/screenshots/semantic_less.jpg)

## theme.less
![screenshoot](/images/pages/img_themes/screenshots/theme_less.jpg)
