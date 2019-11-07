---
title: Flexberry Ember
sidebar: flexberry-ember_sidebar
keywords: mydoc
summary: Компоненты и структурные элементы, инструменты для генерации ember-приложения
toc: true
permalink: ru/fe_landing_page.html
lang: ru
---

`Ember-flexberry` является [частью платформы Flexberry](http://flexberry.ru), реализованным на javascript-фреймворке [EmberJS](http://emberjs.com/).

## Компоненты Flexberry Ember

* [Контролы](ef_controls.html)
* [Формы](ef_forms.html)

## Структурные элементы Flexberry Ember-приложения

Структура `ember-flexberry` приложения такая же как и у классического [ember-приложения](https://guides.emberjs.com/v2.0.0/getting-started/core-concepts/), но есть некоторые нюансы, которые описаны в отдельных статьях.

* [Модели](efd_model.html)
* [Сериализаторы](efd_serializer.html)
* [Роутер](ef_router.html)
* [Роуты](ef_route.html)
* [Контроллеры](ef_controller.html)
* [Шаблоны](ef_template.html)
* [Сервисы](ef_service.html)

## Дополнительные возможности

* [Запуск приложения Flexberry Ember в качестве мобильного приложения Apache Cordova](ef_cordova.html)
* [Генерация Ember-приложения из меню Flexberry Designer](ef_generator.html)

## Программы и инструменты для генерации ember-приложения

Для создания приложения с помощью продукта `ember-flexberry` необходимо установить ряд программ и осуществить их настройку для корректной работы.

### Программное обеспечение

1. [Flexberry Desinger](https://flexberry.github.io/ru/fd_landing_page.html). [Установить](https://flexberry.github.io/ru/fd_install.html) Flexberry Desinger можно с сайта [flexberry.net](https://flexberry.net/). При первичной установке дается промо-лицензия на 14 дней. Затем ее можно [продлить](https://designer.flexberry.net/#/download-win-app) на срок от 6 до 12 месяцев. Для студентов колледжей и ВУЗов предоставляется бесплатная академическая лицензия на полгода.
2. Visual Studio 2017 или новее. В работе с приложениям на базе [Ember.js](https://emberjs.com/) и `ember-flexberry` также помогут [Visual Studio Code](https://code.visualstudio.com/), [Sublime Text](http://www.sublimetext.com/) или их [аналоги](https://jpnsoft.ru/visual-studio-code/).
3. PostgreSQL. Для выполнения работ рекомендуется использовать бесплатный обачный сервис [https://www.elephantsql.com/](https://www.elephantsql.com/).
4. TFS. Для выполнения работ рекомендуется использовать бесплатный облачный сервис Visual Studio Online
5. NodeJS версии 6.17.1: [https://nodejs.org/dist/latest-v6.x/](https://nodejs.org/dist/latest-v6.x/)
6. Последняя версия Yarn [https://yarnpkg.com/ru/docs/install#windows-stable](https://yarnpkg.com/ru/docs/install#windows-stable)
7. EmberJS версии 3.1.4 или 2.4.3, в зависимости от планируемой к использованию версии `ember-flexberry` (для установки после настройки Yarn выполнить `yarn global add ember-cli@3.1.4` или `yarn global add ember-cli@2.4.3`)
8. Последняя версия Bower (для установки после настройки Yarn выполнить `yarn global add bower`)

### Настройка Yarn

Необходимо настроить путь для установки (PATH): [https://yarnpkg.com/lang/en/docs/cli/global/](https://yarnpkg.com/lang/en/docs/cli/global/)

### Настройка Flexberry Desinger

Чтобы ember-генератор работал корректно требуется в файле конфигурации CASEBERRY.exe.config установить следующее значение параметра `EmberPluginAddonName`

```xml
<appSettings>
  ...
  <add key="EmberPluginAddonName" value="ember-flexberry@3.1.0" />
  ...
</appSettings>
```

### Настройка созданного ember-приложения

В файл `package.json` добавить

```javascript
"resolutions": {
"resolve": "1.11.0"
}
```
