---
title: Переход на новые темы оформления
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef2_themes_migrating_old.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание перехода на новые темы оформления
---

## Инструкция по переходу на новые темы оформления

Для того чтобы, можно было добавлять свои кастомизированные темы в ember-flexberry необходимо:

1. Cкопировать в корневую папку `ember-flexberry` файл [theme.config](https://github.com/Flexberry/ember-flexberry/blob/develop/theme.config)
2. Cкопировать [theme.less](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/styles/theme.less) и папку themes в `/addon/styles`
3. переименовать `/test/dummy/app/styles/app.scss` в `app.less` и добавить в него сверху строчку `@import "src/semantic";`

4. Удалить строчку в package.json

   `"ember-cli-sass": "^5.2.0",`

   и установить компилятор less командой `npm install --save-dev ember-cli-less`

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

6. В `theme.config` прописать для необходимых элементов название blueSky. Посмотреть пример можно здесь:

<https://github.com/Flexberry/ember-flexberry/blob/develop/theme.config>

В основном разметка изменилась на главной странице, поэтому нам нужно поправить файл `/test/dummy/app/templates/application.hbs`
он должен выглядеть вот так:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/templates/application.hbs>

Так как Semantic-UI не позволяет подключать 2 варианта шрифтовых иконок, то проще всего их подключить через vendor,
взять их можно отсюда:

<https://github.com/Flexberry/ember-flexberry/tree/develop/vendor>

необходимо скопировать папки `fonts`, `font-icon.css` и папку `serviseImages` (некоторые иконки темы, только для темы blueSky) в `ember-flexberry/vendor/`, а также надо добавить в `ember-cli-build.js` импорт этих файлов

```
  app.import('vendor/font-icon.css');  
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

Также, чтобы менялась надпись шрифтовой иконки меню и цвет при нажатии надо чтобы в `/test/dummy/app/controllers/application.js` метод `actions.toggleSidebar` выглядел вот так:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/application.js>

Если меню состоит не только из главных пунктов но и есть подпункты, то необходимо добавить скрипты, для того чтобы элементы меню скрывались, для этого необходимо в `/tests/dummy/app/controllers/sitemap-node.js`  добавить метод `actions.subMenuEl` чтобы он выглядел вот так:

<https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/sitemap-node.js>

Добавить действие и иконки скрытия подменю в `ember-flexberry/app/templates/sitemap-node-content.hbs` и в `/ember-flexberry/app/templates/sitemap-node.hbs` в итоге должно получиться вот так:

<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/sitemap-node-content.hbs>
<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/sitemap-node.hbs>

Подправить кнопки удаления и добавления, а также изменения, для этого надо добавить кнопкам классы `ui-add, ui-delete, ui-change`:

<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/components/groupedit-toolbar.hbs>
<https://github.com/Flexberry/ember-flexberry/blob/develop/app/templates/components/flexberry-lookup.hbs>

Дополнение:

*	В шаблонах списковых форм заменить `<div class="ui {{state}} row form">` на `<div class="row">`
*	Во всех файлах заменить `hidden-menu` на `hidden`.
*	В controllers/application.js заменить `.sidebar.icon.text-menu-1` на `.sidebar.icon.text-menu-show`, `.sidebar.icon.text-menu-2` на .`sidebar.icon.text-menu-hide`.
*	В templates/application.hbs сделать замены как в предыдущем пункте. Затем добавить в блок `<div id="example" class="pusher">` - `<div class="ui form {{objectlistviewEventsService.loadingState}}">` <https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/templates/application.hbs#L38>
*	В theme.config сделать как в <https://github.com/Flexberry/ember-flexberry/blob/03751c63e852ed2fde8c7519022a17cbc9d284d1/theme.config#L21> с 21 по 73 строку
*	В controllers/application.js – вставить action toggleSidebarMobile и сервис objectlistview-events, также изменить toggleSidebar. (изменения для controllers/application.js  - <https://github.com/Flexberry/ember-flexberry-security/commit/ebfd645c00834dba426b97c00bf0cd6eb25e5807#diff-b02194543e6fcf5c7caff7b1a2f3d0f4>;
*	В templates/application.hbs – вынести компонент ui-sidebar в начало страницы, чтобы он был вне блоков с контентом (по умолчанию нужно перенести выше блока с классом `bgw-fix`),  под блоком с классом `pusher`, создать блок с классом `ui main container` и перенести в него `{% raw %}{{outlet "modal"}}{% endraw %}`. (изменения для templates/application.hbs - <https://github.com/Flexberry/ember-flexberry-security/commit/ebfd645c00834dba426b97c00bf0cd6eb25e5807#diff-33efff51085b97215b246f6fcfd9e0fe>;
*	Если используется мобильный шаблон application.hbs, то для него нужно проделать действия из предыдущего пункта для ui-sidebar, а также заменить action toggleSidebar на toggleSidebarMobile;
