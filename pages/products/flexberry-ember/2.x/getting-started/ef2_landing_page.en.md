---
title: Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: mydoc
summary: Components and structural elements, tools to generate ember-application
toc: true
permalink: en/ef2_landing_page.html
lang: en
autotranslated: true
hash: 75aa40a4e867dd0dfbdf32a4e06e8a3888a23a19b1756623b22e8f36563d84a6
---

`Ember-flexberry` is [part of the platform Flexberry](http://flexberry.ru), implemented in javascript frameworks [EmberJS](http://emberjs.com/).

## Components Flexberry Ember

* [Controls](ef2_controls.html)
* [Form](ef2_forms.html)

## Structural elements Flexberry Ember-application

Structure `ember-flexberry` application is the same as classic [ember-app](https://guides.emberjs.com/v2.0.0/getting-started/core-concepts/), but there are some nuances, which are described in separate articles.

* [Models](efd2_model.html)
* [Serializers](efd2_serializer.html)
* [Router](ef2_router.html)
* [Ranting](ef2_route.html)
* [Controllers](ef2_controller.html)
* [Templates](ef2_template.html)
* [Services](ef2_service.html)

## Additional features

* [Run the application Flexberry Ember as mobile apps Apache Cordova](ef2_cordova.html)
* [Generate Ember-app from the menu Flexberry Designer](ef2_generator.html)

## Programs and tools to generate ember-application

To create the application using the product `ember-flexberry` you need to install a number of programs and their configuration to work properly.

### Software

1. [Flexberry Desinger](https://flexberry.github.io/ru/fd_flexberry-designer.html). [Set](https://flexberry.github.io/ru/fd_install.html) Flexberry Desinger from [flexberry.net](https://flexberry.net/). At the initial installation is given a promotional license for 14 days. Then it can [extend](https://designer.flexberry.net/#/download-win-app) for a period of 6 to 12 months. For students of colleges and Universities free academic license for six months.
2. Visual Studio 2017 or later. In working with applications based on [Ember.js](https://emberjs.com/) and `ember-flexberry` will also help [Visual Studio Code](https://code.visualstudio.com/), [Sublime Text](http://www.sublimetext.com/) or their [counterparts](https://jpnsoft.ru/visual-studio-code/).
3. PostgreSQL. For performance of works it is recommended to use a free cloud service [https://www.elephantsql.com/](https://www.elephantsql.com/).
4. TFS. For performance of works it is recommended to use a free cloud service Visual Studio Online
5. NodeJS version 8.16.x: [https://nodejs.org/dist/latest-v8.x/](https://nodejs.org/dist/latest-v8.x/)
6. The latest version of Yarn [https://yarnpkg.com/ru/docs/install#windows-stable](https://yarnpkg.com/ru/docs/install#windows-stable)
7. EmberJS version 3.1.4 or 2.4.3, depending on the planned use version `ember-flexberry` (for installation after you configure Yarn to perform `yarn global add ember-cli@3.1.4` or `yarn global add ember-cli@2.4.3`)
8. The latest version of Bower (to install after setting the Yarn to run `yarn global add bower`)

### Setting Yarn

You must configure the installation path (PATH): [https://yarnpkg.com/lang/en/docs/cli/global/](https://yarnpkg.com/lang/en/docs/cli/global/)

### Setting Flexberry Desinger

To ember-generator to work correctly is required in the configuration file CASEBERRY.exe.config to set the following parameter value `EmberPluginAddonName` (or 2.1.0 if you use ember-cli@2.4.3).

```xml
<appSettings>
  ...
  <add key="EmberPluginAddonName" value="ember-flexberry@3.1.0" />
  ...
</appSettings>
```

### Setting created ember app

In file `package.json` add

```javascript
"devDependencies": {
  ...
  "resolve": "1.11.0",
  ...
},
"resolutions": {
  "resolve": "1.11.0"
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}