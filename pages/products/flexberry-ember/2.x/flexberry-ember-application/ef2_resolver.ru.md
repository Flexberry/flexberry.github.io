---
title: Мобильные шаблоны.
sidebar: flexberry-ember-2_sidebar
keywords:
toc: true
permalink: ru/ef2_resolver.html
folder: products/ember-flexberry/mobile/
lang: ru
summary: Основная информация о resolver-е.
---

## Описание

Resolver с помощью сервиса device из [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/master/addon/services/device.js) который использует библиотеку [devicejs](https://github.com/matthewhudson/device.js) определяет устройство,
на котором запущено приложение (iOS/Android/Windows), его тип (phone/tablet/tv) и положение экрана (portrait/landscape) и в зависимости от этой информации ищет ресурсы приложения (шаблоны, классы и прочее).

## Определение resolver-а в приложении

Для использования ресовера в приложении необходимо:

* в корне приложения создать файл resolver.js и определить в нем нужный resolver: `export { default } from 'ember-flexberry/resolver';` [пример определения на ember стенде](https://github.com/Flexberry/ember-flexberry/blob/master/tests/dummy/app/resolver.js)
* и подключить его в корне приложения в файле app.js:

```js
import Ember from 'ember';
import Resolver from 'ember-flexberry/resolver';

let App = Ember.Application.extend({
  Resolver: Resolver
});

export default App;
```

[пример подключения на ember стенде](https://github.com/Flexberry/ember-flexberry/blob/master/tests/dummy/app/app.js#L13)

Сделав эту настройку можно будет определять множества ресурсов приложения, предназначенных для разных устройств(возможные варианты устройств, включая платформу, тип и ориентацию устройства):

* templates/ipad-landscape/my-form.hbs - шаблон для ipad-ов при горизонтальной ориентации экрана
* templates/ipad-portrait/my-form.hbs - шаблон для ipad-ов при портретной ориентации экрана
* templates/ipad/my-form.hbs - шаблон для ipad-ов, который будет использован при любой ориентации экрана (если шаблоны под ipad-landscape или ipad-portrait не заданы)

* templates/iphone-landscape/my-form.hbs - шаблон для iphon-ов при горизонтальной ориентации экрана
* templates/iphone-portrait/my-form.hbs - шаблон для iphone-ов при портретной ориентации экрана
* templates/ipone/my-form.hbs - шаблон для iphone-ов, который будет использован при любой ориентации экрана (если шаблоны под iphone-landscape или iphone-portrait не заданы)

* templates/android-tablet-landscape/my-form.hbs - шаблон для android-планшетов при горизонтальной ориентации экрана
* templates/android-tablet-portrait/my-form.hbs - шаблон для android-планшетов при портретной ориентации экрана
* templates/android-tablet/my-form.hbs - шаблон для android-планшетов, который будет использован при любой ориентации экрана (если шаблоны под android-tablet-landscape или android-tablet-portrait не заданы)

* templates/android-phone-landscape/my-form.hbs - шаблон для android-телефонов при горизонтальной ориентации экрана
* templates/android-phone-portrait/my-form.hbs - шаблон для android-телефонов при портретной ориентации экрана
* templates/android-phone/my-form.hbs - шаблон для android-телефонов, который будет использован при любой ориентации экрана (если шаблоны под android-phone-landscape или android-phone-portrait не заданы)

* templates/windows-tablet-landscape/my-form.hbs - шаблон для windows-планшетов при горизонтальной ориентации экрана
* templates/windows-tablet-portrait/my-form.hbs - шаблон для windows-планшетов при портретной ориентации экрана
* templates/windows-tablet/my-form.hbs - шаблон для windows-планшетов, который будет использован при любой ориентации экрана (если шаблоны под windows-tablet-landscape или windows-tablet-portrait не заданы)

* templates/windows-phone-landscape/my-form.hbs - шаблон для windows-телефонов при горизонтальной ориентации экрана
* templates/windows-phone-portrait/my-form.hbs - шаблон для windows-телефонов при портретной ориентации экрана
* templates/windows-phone/my-form.hbs - шаблон для windows-телефонов, который будет использован при любой ориентации экрана (если шаблоны под windows-phone-landscape или windows-phone-portrait не заданы)

...

* templates/tablet-landscape/my-form.hbs - шаблон для планшетов (на любой платформе) при горизонтальной ориентации экрана
* templates/tablet-portrait/my-form.hbs - шаблон для планшетов (на любой платформе) при портретной ориентации экрана
* templates/tablet/my-form.hbs - шаблон для планшетов (на любой платформе), который будет использован при любой ориентации экрана (если шаблоны под tablet-landscape или tablet-portrait не заданы)

* templates/phone-landscape/my-form.hbs - шаблон для телефонов (на любой платформе) при горизонтальной ориентации экрана
* templates/phone-portrait/my-form.hbs - шаблон для телефонов (на любой платформе) при портретной ориентации экрана
* templates/phone/my-form.hbs - шаблон для телефонов (на любой платформе), который будет использован при любой ориентации экрана (если шаблоны под tablet-landscape или tablet-portrait не заданы)

* templates/mobile/my-form.hbs - шаблон для любых мобильных устройств (телефонов или планшетов) работающих на любой платформе, который будет использован, если не заданы более подходящие шаблоны под текущий тип, платформу, ориентацию устройства

* templates/tv/my-form.hbs - шаблон для телевизоров (на любой платформе)

* templates/my-form.nbs - стандартный шаблон формы (который будет использован в самую последнюю очередь, если под текущее устройство не  найдено ни одного подходящего "устройство-специфичного" шаблона)

## Дополнительные настройки

 Эти настройки resolver-a, позволяют ограничить его варианты перебора каталогов до минимума:

* **resolveWithoutDeviceTypeDetection** позволяет задать конкретные ресурсы, которые не нужно искать в зависимости от устройства (конкретный компонент, конкретный сервис, и т.п.).

Задать настройку можно в конфиге (`config\environment.js`) следующим образом:

```js
APP: {
    resolveWithoutDeviceTypeDetection: [
          'component:object-list-view',
          'component:object-list-view-row',
          'template:components/object-list-view',
          'template:components/object-list-view-row',
      ],
    }
```

По умолчанию: `resolveWithoutDeviceTypeDetection: undefined`

* **deviceRelatedTypes** - задает перечень устройство-зависимых типов ресурсов (по умолчанию это компоненты, шаблоны и представления).

Изменить настройку можно в прикладном resolver.js унаследовав его от `ember-flexberry/resolver` и переопределив в нем этот массив `deviceRelatedTypes`:

```js
import Resolver from 'ember-flexberry/resolver';

export default Resolver.extend({
  deviceRelatedTypes: [
    'template',
    'controller'
  ],
});
```

По умолчанию: `deviceRelatedTypes: ['component', 'template', 'view']`
