---
title: Внутреннее устройство тем
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_themes_structure.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание внутреннего механизма тем оформления
---

## Устройство тем оформления

В ember-flexberry за стилизацию тем отвечает css фреймворк Semantic-UI, он находится в папке /bower_components/semantic-ui.
Эта папка содержит непосредственно сами папки с темами в /src/themes, в них отдельно для каждого элемента разметки есть файл
element.variables в котором задаются переменные less, содержащие различные свойства стилей. Ещё там есть файлы такие как
element.overrides в них можно записывать полноценные селекторы со свойствами.

## button.variables
![screenshoot](/images/pages/img_themes/screenshots/variables.jpg)

## button.overides
![screenshoot](/images/pages/img_themes/screenshots/overrides.jpg)

Помимо папки с темами там есть папка \src\definitions в ней также для каждого элемента есть свой файл element.less, в нём 
размещены все селекторы с переменными(которые берутся из файлов .variables).

## button.less
![screenshoot](/images/pages/img_themes/screenshots/less.jpg)

За выбор тем отвечает конфигурационный файл /src/theme.config.example, в нем можно указать для каждого елемента свою тему,
можно задать элементам разные стили от любых имеющихся тем.

## theme.config.example
![screenshoot](/images/pages/img_themes/screenshots/config_theme.jpg)

Также в папке /src находятся semantic.less и theme.less отвечающие за пути и импорт элементов тем.

## semantic.less
![screenshoot](/images/pages/img_themes/screenshots/semantic_less.jpg)

## theme.less
![screenshoot](/images/pages/img_themes/screenshots/theme.less.jpg)

## Для того чтобы, можно было добавлять свои кастомизированные темы в ember-flexberry необходимо:

1. bower_components/semantic-ui/src/theme.config.example скопировать в корневую папку ember-flexberry и переименовать в theme.config 
2. скопировать bower_components/semantic-ui/src/theme.less и папку themes в /test/dummy/app/style
3. переименовать /test/dummy/app/style/app.scss в app.less и добавить в него сверху строчку

   ```@import "src/semantic";```
   
4. Удалить строчку в package.json

   ```"ember-cli-sass": "^5.2.0",```
   
   и установить компилятор less командой npm install --save-dev ember-cli-less
   
5. В ../index.js добавить строчку

   ```app.import('bower_components/semantic-ui/dist/semantic.js');``` 
   
5. В ../ember-cli-build.js добавить строчку

   ```lessOptions: {
      paths: [
        'bower_components/semantic-ui'
      ]
    },
    SemanticUI: {
      import: {
        css: false,
        javascript: false,
        images: false,
        fonts: true
      }
    },
    ``` 
6. Закинуть папку с темой, например blueSky в /test/dummy/app/style/themes, и прописать в theme.config для всех элементов blueSky
  
Впринципе вот весь механизм настройки тем, тема blueSky должна примениться, но пока что без необходимой разметки, шрифтов и скриптов под эту тему.
