---
title: Переход на новые темы оформления
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_themes_migrating.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание перехода на новые темы оформления
---

## Инструкция по переходу на новые темы оформления

Для того чтобы, можно было добавлять свои кастомизированные темы в ember-flexberry необходимо:

1. bower_components/semantic-ui/src/theme.config.example скопировать в корневую папку ember-flexberry, переименовать в theme.config и 
   прописать вместо @import "theme.less";  другой путь @import "tests/dummy/app/styles/theme.less";
2. скопировать bower_components/semantic-ui/src/theme.less и папку themes в /test/dummy/app/style
3. переименовать /test/dummy/app/style/app.scss в app.less и добавить в него сверху строчку

   ```@import "src/semantic";```
   
4. Удалить строчку в package.json

   ```"ember-cli-sass": "^5.2.0",```
   
   и установить компилятор less командой npm install --save-dev ember-cli-less
   
5. В ../index.js добавить строчку

   ```app.import('bower_components/semantic-ui/dist/semantic.js');``` 
   
5. В ../ember-cli-build.js добавить строчки

   ```
   lessOptions: {  
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
  
Впринципе стили темы blueSky должны примениться, но пока что без необходимой разметки, шрифтов и скриптов под эту тему.

Темы BlueSky и Orange имеют одинаковую разметку, поэтому сделаем так, чтобы всё красиво отображалось для обоих тем.
В основном разметка изменилась на главной странице, поэтому нам нужно поправить файл /test/dummy/app/templates/application.hbs
он должен выглядеть вот так:

<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/tests/dummy/app/templates/application.hbs>

Так как semantic-ui не позволяет подключать 2 варианта шрифтовых иконок, то проще всего их подключить через vendor,
взять их можно отсюда:

<https://github.com/Flexberry/ember-flexberry/tree/flexberry-crimean-theme/vendor>

необходимо скопировать папку fonts, font-icon.css и папку serviseImages(некоторые иконки темы, только для темы blueSky) в ember-flexberry/vendor/, а также надо добавить в ember-cli-build.js импорт этих файлов

 ```app.import('vendor/font-icon.css');  
  app.import('vendor/fonts/icons.eot', { destDir: 'assets/fonts' });  
  app.import('vendor/fonts/icons.otf', { destDir: 'assets/fonts' });  
  app.import('vendor/fonts/icons.svg', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/icons.ttf', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/icons.woff', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/icons.woff2', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.eot', { destDir: 'assets/fonts' });     
  app.import('vendor/fonts/crim.otf', { destDir: 'assets/fonts' });     
  app.import('vendor/fonts/crim.svg', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.ttf', { destDir: 'assets/fonts' });    
  app.import('vendor/fonts/crim.woff', { destDir: 'assets/fonts' });   
  app.import('vendor/fonts/crim.woff2', { destDir: 'assets/fonts' });  
  app.import('vendor/serviceImages/close.png', {   
    destDir: 'assets/serviceImages'   
  });  
    app.import('vendor/serviceImages/close-hover.png', {   
    destDir: 'assets/serviceImages'   
  });  
   app.import('vendor/serviceImages/Plus.png', {   
    destDir: 'assets/serviceImages'   
  });  
    app.import('vendor/serviceImages/Minus.png', {   
    destDir: 'assets/serviceImages'   
  });
  ```  
Также чтобы менялась надпись шрифтовой иконки меню и цвет при нажатии надо чтобы в /test/dummy/app/controllers/application.js method actions.toggleSidebar выглядел вот так:

<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/tests/dummy/app/controllers/application.js>

Если меню состоит не только из главных пунктов но и есть подпункты, то необходимо добавить скрипты, для того чтобы элементы меню скрывались, для этого необходимо в /tests/dummy/app/controllers/sitemap-node.js  добавить method actions.subMenuEl чтобы он выглядел вот так:

<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/tests/dummy/app/controllers/sitemap-node.js>
 
Осталось добавить действие и иконки скрытия подменю в ember-flexberry/app/templates/sitemap-node-content.hbs и в /ember-flexberry/app/templates/sitemap-node.hbs в итоге должно получиться вот так:
 
<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/app/templates/sitemap-node-content.hbs>
<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/app/templates/sitemap-node.hbs>
 
И в завершении осталось подправить кнопки удаления и добавления а также изменения, для этого надо добавить кнопкам классы ui-add, ui-delete, ui-change:
 
<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/app/templates/components/groupedit-toolbar.hbs>


<https://github.com/Flexberry/ember-flexberry/blob/flexberry-crimean-theme/app/templates/components/flexberry-lookup.hbs>
 
 
 



