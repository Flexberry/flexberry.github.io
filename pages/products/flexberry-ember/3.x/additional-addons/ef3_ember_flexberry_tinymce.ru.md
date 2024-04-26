---
title: Аддон Ember flexberry tinymce
sidebar: flexberry-ember-3_sidebar
keywords: Ember Flexberry Tinymce
toc: true
permalink: ru/ef3_ember_flexberry_tinymce.html
lang: ru
summary: Описание аддона Ember flexberry tinymce
---

## Описание аддона

`ember-flexberry-tinymce` - аддон для [ember-flexberry](ef3_landing_page.html), позволяющий встроить в свое приложение WYSIWYG HTML редактор.

WYSIWYG (является аббревиатурой от англ. What You See Is What You Get, «что видишь, то и получишь») — свойство прикладных программ или веб-интерфейсов, в которых содержание отображается в процессе редактирования. Также широко используется понятие «визуальный редактор».

Редактор представляет из себя поле для ввода текста и функциями его редакирования, а также включает в себя получившуюся html разметку. Выглядит это следующим образом:

![Example](/images\pages\products\flexberry-ember\3.x\components\ember-flexberry-tinymce.png)

## Установка

Установить аддон в свое ember приложение можно командой:

```cmd
ember install ember-flexberry-tinymce
```

Также нужно установить `broccoli-funnel@^3.0.8` и `tinymce@^6`, выполнив следующие команды:

```bash
npm install broccoli-funnel@^3.0.8
npm install tinymce@^6
```

Далее, нужно добавить изменения в файл `ember-cli-build.js`:

```js
const Funnel = require('broccoli-funnel');

module.exports = function(defaults) {
  let app = new EmberAddon(defaults, {
    // Добавьте эти опции для включения TinyMCE
    autoImport: {
      webpack: {
        externals: { tinymce: 'tinymce' },
      },
    },
  });

  app.import('node_modules/tinymce/tinymce.min.js');

  const tinymceTree = new Funnel('node_modules/tinymce/', {
    include: ['icons/**/*', 'models/**/*', 'skins/**/*', 'themes/**/*', 'plugins/**/*', 'langs/**/*'],
    destDir: '/assets'
  });

  return app.toTree(tinymceTree);
};
```
