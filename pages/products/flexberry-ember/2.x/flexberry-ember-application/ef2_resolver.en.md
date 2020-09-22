---
title: Mobile templates.
sidebar: flexberry-ember-2_sidebar
keywords:
toc: true
permalink: en/ef2_resolver.html
folder: products/ember-flexberry/mobile/
lang: en
autotranslated: true
hash: 58d0917198b882f2a6f7354228c4fec5863949ef7d4ed1a0a88f9e6fbc26fd91
summary: Basic information about resolver.
---

## Description

Resolver using the service device from [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/master/addon/services/device.js) which uses library [devicejs](https://github.com/matthewhudson/device.js) specifies the device
running the app (iOS/Android/Windows), its type (phone/tablet/tv) and screen position (portrait/landscape) and depending on this information, is looking for application resources (templates, classes, etc.).

## Determination of the resolver and the application

To use recover in the application should:

* in the application root to create the file resolver.js and define it the right resolver: `export { default } from 'ember-flexberry/resolver ;` [example of determination of ember on the stand](https://github.com/Flexberry/ember-flexberry/blob/master/tests/dummy/app/resolver.js)
* and plug it into the application root in the file app.js:

```js
import Ember from 'ember';
import Resolver from 'ember-flexberry/resolver';

let App = Ember.Application.extend({
  Resolver: Resolver
});

export default App;
```

[connection example on ember stand](https://github.com/Flexberry/ember-flexberry/blob/master/tests/dummy/app/app.js#L13)

By making this setting you can define many application resources, target different devices(possible devices including the platform type and the orientation of the device):

* templates/ipad-landscape/my-form.hbs - template for ipad in horizontal screen orientation
* templates/ipad-portrait/my-form.hbs - template for iPads in portrait screen orientation
* templates/ipad/my-form.hbs - template for iPads, which will be used in any screen orientation (if the templates are ipad-landscape ipad and portrait not specified)

* templates/iphone-landscape/my-form.hbs - template for the iPhones in landscape mode
* templates/iphone-portrait/my-form.hbs - template for the iphone in portrait screen orientation
* templates/ipone/my-form.hbs - template for the iPhones, which will be used in any screen orientation (if the templates under iphone-landscape or iphone portrait not specified)

* templates/android-tablet-landscape/my-form.hbs - template for android tablets in landscape screen orientation
* templates/android-tablet-portrait/my-form.hbs - template for an android tablet in portrait screen orientation
* templates/android tablet/my-form.hbs - template for an android tablet, which will be used in any screen orientation (if the template under android-tablet-landscape, or android-tablet-portrait not specified)

* templates/android-phone-landscape/my-form.hbs - template for android phones with a horizontal screen orientation
* templates/android-phone-portrait/my-form.hbs - template for android phones portrait screen orientation
* templates/android-phone/my-form.hbs - template for android phones, which will be used in any screen orientation (if the template under android-phone-landscape or android phone-portrait not specified)

* templates/windows-tablet-landscape/my-form.hbs - template for windows tablets in landscape screen orientation
* templates/windows-tablet-portrait/my-form.hbs - template for a windows tablet in portrait screen orientation
* templates/windows-tablet/my-form.hbs - template for a windows tablet, which will be used in any screen orientation (if the templates windows-tablet-landscape or windows-tablet-portrait not specified)

* templates/windows-phone-landscape/my-form.hbs - template for windows phone when the horizontal screen orientation
* templates/windows-phone-portrait/my-form.hbs - template for windows phone in portrait screen orientation
* templates/windows-phone/my-form.hbs - template for windows phone, which will be used in any screen orientation (if the templates windows-phone-landscape or windows-phone-portrait not specified)

...

* templates/tablet-landscape/my-form.hbs - template for tablets (on any platform) for horizontal screen orientation
* templates/tablet-portrait/my-form.hbs - template for tablets (on any platform) in portrait screen orientation
* templates/tablet/my-form.hbs - template for tablets (on any platform) that will be used when any screen orientation (if the templates for tablet-landscape or tablet portrait not specified)

* templates/phone-landscape/my-form.hbs - template for phones (on any platform) for horizontal screen orientation
* templates/phone-portrait/my-form.hbs - template for phones (on any platform) in portrait screen orientation
* templates/phone/my-form.hbs - template for phones (on any platform) that will be used when any screen orientation (if the templates for tablet-landscape or tablet portrait not specified)

* templates/mobile/my-form.hbs - template for all mobile devices (phones or tablets) running on any platform that will be used unless more appropriate under the current template type, platform, and device orientation

* templates/tv/my-form.hbs - template for the TV (on any platform)

* templates/my-form.nbs - standard template forms (which will be used in the last step, if the current device does not find any suitable "device-specific" template)

## Additional settings

These settings resolver-a, allow you to limit his options of iterating through the directories to a minimum:

* **resolveWithoutDeviceTypeDetection** allows you to specify specific resources that don't need to search depending on the device (the specific component, a specific service, etc.).

To specify a setting in the config (`config\environment.js`) as follows:

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

Default: `resolveWithoutDeviceTypeDetection: undefined`

* **deviceRelatedTypes** - specifies a list of device-dependent resource types (default is components, templates and views).

To change the settings in the application resolver.js inherited from `ember-flexberry/resolver` and overriding this array `deviceRelatedTypes`:

```js
import Resolver from 'ember-flexberry/resolver';

export default Resolver.extend({
  deviceRelatedTypes: [
    'template',
    'controller'
  ],
});
```

Default: `deviceRelatedTypes: ['component', 'template', 'view']`



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}