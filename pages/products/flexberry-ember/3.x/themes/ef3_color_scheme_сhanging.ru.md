---
title: Подключение функции смены цветовой схемы приложения
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef3_color_scheme_сhanging.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Инструкция по подключению функции смены цветовой схемы приложения
---

## Подключение функции смены цветовой схемы приложения

> Функция смены цветовой схемы приложения доступна только для темы оформелния [Ghost](ef3_supported_themes.html#тема-оформления-ghost).

1.В `ember-cli-build.js` добавить настройки сборки приложения (после этого перезапустить проект).
Значения в массиве `fingerprint.exclude` - это названия цветовых схем.
В `outputPaths.app.css` необходимо сопоставить названия конечных `*.less` файлов и названия `css` файлов на выходе.

```js
    outputPaths: {
      app: {
        css: {
          'app': '/assets/purple.css',
          'dark': '/assets/dark.css',
          'default': '/assets/default.css'
        }
      }
    },

    fingerprint: {
      exclude: ['purple', 'dark', 'default']
    },
```

2.В шаблон `application.hbs` добавить контрол для выбора цветовой схемы.

```html
{% raw %}{{flexberry-dropdown
  class="compact theme"
  items=themes
  value='blue'
  onChange=(action 'changeTheme')
  settings=(hash direction="upward")
}}{% endraw %}
```

3.В контроллере объявить массив `themes` и присвоить значения, добавить экшн `changeTheme`, который будет срабатывать при выборе значения в контроле.

```html
  /**
    Themes supported by application.
    @property themes
    @type String[]
    @default ['purple', 'dark', 'default']
  */
  themes: undefined,

  init() {
    this._super(...arguments);
    this.set('themes', ['purple', 'dark', 'default']);
  },

  actions: {
    /**
      Select themes.
      @method actions.changeTheme
    */
    changeTheme(key, value) {
      let sheet = document.querySelector('#theme');
      if (!sheet) {
        return;
      }

      let rootURL = this.get('router.location.location.origin') + config.rootURL;
      sheet.setAttribute('href', `${rootURL}assets/${value}.css`);
    }
  }
```

4.В `app.less` в первой строке прописать дефолтную тему.

```html
@Theme: 'purple';
```

5.Добавить в эту же деррикторию недостающие `*.less` файлы. По числу тем оформления, исключая дефолтную, которая указана в `app.less`, файлы должны назваться так же как указано в `outputPaths` и в выпадающем списке выбора тем.

В этих файлах нужно прописать название темы и импорт как в `app.less`.

Например, для `dark.less`:

```html
@Theme: 'dark';
@import 'src/flexberry-imports';
/*<импорты как в app.less>*/
```

6.В папке `styles/site/globals/` добваить аналогичные `*.less` файлы,  по числу тем оформления, включая дефолтную.
Таким образом, для нашего примера будет добвалено три файла `purple.less`, `dark.less`, `default.less`.

![files](/images/pages/products/flexberry-ember/3.x/themes/themes_changing/files.png)

В данных файлах будут заданы значения переменных конкретной цветовой схемы.
Например, для темы `dark` можно указать:

```html
// Main
@defaultColor          : fade(#000, 10%);
@controlColor          : fade(#000, 15%);
@dimmerBackgroundColor : rgba(0, 0, 0, 0.3);

// Sidebar
@sidebarBackgroundColor: #353535;

// Page
@textColor             : rgba(255, 255, 255, 0.65);
@selectedTextColor     : rgba(255, 255, 255, 0.75);
@pageBackground        : #3e3e3e;

@inputHoverBorderColor  : @defaultColor;
@inputReadonlyBackground: fade(#000, 15%);

@defaultInputFocusBackground: @defaultColor;

/*-------------------
    Scroll Bars
--------------------*/

@trackBooxShadow: inset 0 0 10px 10px fade(#000, 15%);

// OLV
@olvRowActiveBackground        : fade(#ECF2FB, 20%);
@olvContainerBorderColorDefault: rgba(0, 0, 0, 0.14);
```

## Полезные ссылки

* [Внутреннее устройство тем](ef3_themes_changing.html)
* [Тестовый стенд Ember Flexberry с темой Ghost](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/){:target='_blank'}
