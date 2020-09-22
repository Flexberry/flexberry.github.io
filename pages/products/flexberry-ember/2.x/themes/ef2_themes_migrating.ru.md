---
title: Переход на новые темы оформления
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef2_themes_migrating.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание перехода на новые темы оформления
---

## Инструкция по переходу на новые темы оформления

## Переход для BlueSky
Текущая версия ember-flexberry содержит значительные изменения онтносительно первоначальной, где не было поддержки тем.

Поэтому единственным верным решением будет перегенерация приложения с переносом в него логики.

Вот неполный список того, что требуется сделать, чтобы подключить темы. Внимание! статья не дополняется.

[Старая инструкция по переходу на новые темы оформления](ef2_themes_migrating_old.html)

## Переход на Ghost

{% include important.html content="Данная тема доступна только для приложений, использующих `ember-flexberry` версии 3.4.0 и выше." %}

Все темы для версий 3.4.0 и выше вынесены в отдельный пакет [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes).

### Настройка импорта стилей

1. Установить последнюю версию пакета `ember-flexberry-themes` через `npm` или `yarn`
2. Прописать в `ember-cli-build.js` пути до пакетов с темами (`semantic-ui` и `ember-flexberry-themes`)

```js
  lessOptions: {
    paths: [
      'bower_components/semantic-ui',
      'node_modules/ember-flexberry-themes',
    ]
  }
```

3. Настроить файл `theme.config` (в корневой папке приложения), можно скопировать из [примера](src/theme.config.example)

`@semanticUiThemesFolder` - путь до папки с темой `semantic-ui`
`@emberFlexberryThemesFolder` - путь до папки с темой `ember-flexberry-themes`
`@siteFolder` - папака с локальными стилями приложения

4. Настроить файл `app/styles/theme.less` (можно скопировать из [примера](src/theme.less))

Прописать в `app/styles/app.less` импорт стилей (помимо импорта локальных стилей должна остаться одна строка, `semantic` импортировать не нужно)

```less
  @import 'src/flexberry-imports';
```

### Настройка шрифтов

1. Скопировать шрифты в папку `vendor/fonts` из папки `assets/fonts` ([src/themes/ghost/assets/fonts](src/themes/ghost/assets/fonts))
2. Скопировать в папку `vendor` `.css` с объявлением стиилей из папки `assets` ([src/themes/ghost/assets](src/themes/ghost/assets))
3. Добавить импорт шрифта `GOSTUI2` в `ember-cli-build.js`
4. Добавить импорт стилей и настроек для иконок и шрифтов в `ember-cli-build.js`

```js
  app.import('vendor/fonts.css');

  // GOSTUI2
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w170-regular_g_temp.eot', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w170-regular_g_temp.ttf', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w170-regular_g_temp.woff', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w170-regular_g_temp.woff2', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w450-medium_g_temp.eot', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w450-medium_g_temp.ttf', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w450-medium_g_temp.woff', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w450-medium_g_temp.woff2', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w706-bold_g_temp.eot', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w706-bold_g_temp.ttf', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w706-bold_g_temp.woff', { destDir: 'assets/fonts' });
  app.import('vendor/fonts/GOSTUI2/GOSTUI2-w706-bold_g_temp.woff2', { destDir: 'assets/fonts' });

  // guideline-icons
  app.import('vendor/fonts/guideline-icons/guideline-icons.eot', { destDir: 'assets/fonts/guideline-icons' });
  app.import('vendor/fonts/guideline-icons/guideline-icons.ttf', { destDir: 'assets/fonts/guideline-icons' });
  app.import('vendor/fonts/guideline-icons/guideline-icons.woff', { destDir: 'assets/fonts/guideline-icons' });
  app.import('vendor/fonts/guideline-icons/guideline-icons.woff2', { destDir: 'assets/fonts/guideline-icons' });
  app.import('vendor/fonts/guideline-icons/guideline-icons.svg', { destDir: 'assets/fonts/guideline-icons' });
```

### Настройка приложения

Установить пакет `autoprefixer` и добавить настройку в `ember-cli-build.js`

```js
  const autoprefixer = require('autoprefixer');
  module.exports = function(defaults) {
    let app = new EmberAddon(defaults, {
      ...
      postcssOptions: {
        compile: {
          enabled: false,
          browsers: ['last 3 versions'],
        },
        filter: {
          enabled: true,
          plugins: [
            {
              module: autoprefixer,
              options: {
                browsers: ['last 2 versions']
              }
            }
          ]
        }
      }
    });
    ...
  }
```
#### Основное меню приложения

1. Чтобы отображать новое меню нужно использовать компонент `flexberry-sitemap-guideline`. [Пример на стенде](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/tests/dummy/app/templates/application.hbs#L15).
2. Чтобы отображать элементы в подвале сайдбара, их нужно поместить в блок с классом `sitebar-footer`. [Пример на стенде](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/tests/dummy/app/templates/application.hbs#L16)
2. Для установки иконок в пункты меню при объявлении `sitemap'а` добавить для узла параметр `icon`

```js
  sitemap: computed('i18n.locale', function() {
    let i18n = this.get('i18n');

    return {
      nodes: [{
        link: 'index',
        caption: i18n.t('forms.application.sitemap.index.caption'),
        title: i18n.t('forms.application.sitemap.index.title'),
        icon: 'home',
        children: null
      }, {
        link: null,
        caption: i18n.t('forms.application.sitemap.application.caption'),
        title: i18n.t('forms.application.sitemap.application.title'),
        icon: 'clock outline',
        ...
```
3. `toggleSidebar` и `toggleSidebar` скопировать [отсюда](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/tests/dummy/app/controllers/application.js#L43) 

### Модальные окна в режиме `Sidepage`

В новой теме добавлен дополнительный режим открытия модального окна `sidepage`.
В данном режиме модальное окно открывается справа во всю высоту страницы а на мобильных устройствах распахивается на весь экран.
Чтобы модальное окно открылось в режиме `sidepage`, необходимо в разметку добавить класс `flexberry-sidepage`, а также использовать анимацию `transition:'slide left'`.

При использовании компоеннта `modal-dialog` достаточно указать `useSidePageMode=true`.

Для того, чтобы модальные окна `lookup`'a и настройки столбцов а также строки мобильного `flexberry-groupedit` открывались в режиме `sidepage`, необходимо добавить следующие настройки в environment.js:

```js
components: {
  ...
  // For guideline theme
  // Settings for flexberry-objectlistview component.
  flexberryObjectlistview: {
    // Flag indicates whether to side page or usually mode.
    useSidePageMode: true,
  },

  // Settings for flexberry-lookup component.
  flexberryLookup: {
    // Flag: indicates whether to side page or usually mode.
    useSidePageMode: true,
  }

  flexberryGroupedit: {
    // Flag: indicates whether to side page or usually mode.
    useSidePageMode: true,
  },
  ...
}
```
#### Некоторые классы
Чтобы модальное окно распахивалось на мобильнольном устройстве на весь экран необходимо использовать класс `fullhight-mobile-modal`, например, [окно обратной связи](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/tests/dummy/app/templates/modal/ember-flexberry-support-modal.hbs#L7) 
