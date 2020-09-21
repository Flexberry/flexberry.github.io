---
title: Internationalization in ember-flexberry application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_i18n.html
folder: products/ember-flexberry/customization/
lang: en
autotranslated: true
hash: c66e268dee85a46bf01aea8e5bf0a45834e3acef30461c277cb41afb1e853a94
summary:
---

## Description

Internationalization in the addon ember-flexberry based on [ember-i18n](https://github.com/jamesarosen/ember-i18n).
Ember-service `i18n` described [here](https://github.com/jamesarosen/ember-i18n/wiki/Doc:-i18n-Service), is introduced during application initialization in all components, controllers, ranting, models and views.

## Initialize locale
By default, when you run the application sets the locale corresponding to the language of the browser: `navigator.language || navigator.userLanguage`. To change the current locale you need in your code to assign the property `locale` service `i18n`:

```javascript
this.set('i18n.locale', 'ru');
```

All localized values in the app will be changed to the corresponding locale installed.
By default the locale to located in the folder `app/locales` addon ember-flexberry. There you can see available keys for localization, the purpose of which the names and the hierarchy.

## Substitution of the values for the current locale

If you want to implement the change specific labels, it can be done using the method `addTranslations` service `i18n`, putting the locale key and its value as described [sdes](https://github.com/jamesarosen/ember-i18n/wiki/Doc:-Defining-Translations#defining-translations-at-runtime).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}