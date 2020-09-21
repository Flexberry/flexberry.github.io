---
title: Интернационализация в ember-flexberry-приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_i18n.html
folder: products/ember-flexberry/customization/
lang: ru
summary: 
---

## Описание

Интернационализация в аддоне ember-flexberry основана на [ember-i18n](https://github.com/jamesarosen/ember-i18n).
Ember-сервис `i18n`, описанный [здесь](https://github.com/jamesarosen/ember-i18n/wiki/Doc:-i18n-Service), внедряется при инициализации приложения во все компоненты, контроллеры, роуты, модели и представления.

## Инициализация локали
По умолчанию при запуске приложения устанавливается локаль, соответствующая языку браузера: `navigator.language || navigator.userLanguage`. Чтобы изменить текущую локаль, нужно в коде присвоить свойство `locale` сервиса `i18n`:

```javascript
this.set('i18n.locale', 'ru');
```

Все локализованные значения в приложении будут изменены на соответствующие установленной локали.
По умолчанию доступны локали, расположенные в папке `app/locales` аддона ember-flexberry. Там же можно посмотреть доступные ключи для локализации, назначение которых следует из названия и иерархии.

## Подмена значений для текущей локали

Если требуется осуществить изменение конкретной надписи, это можно сделать с помощью метода `addTranslations` сервиса `i18n`, указав локаль, ключ и его значение, как описано [здесь](https://github.com/jamesarosen/ember-i18n/wiki/Doc:-Defining-Translations#defining-translations-at-runtime).
