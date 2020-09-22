---
title: Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: mydoc
summary: Компоненты и структурные элементы, инструменты для генерации ember-приложения
toc: true
permalink: ru/ef2_landing_page.html
lang: ru
---

Фреймворк `Flexberry Ember` является [частью платформы Flexberry](http://flexberry.ru) и представляет собой набор аддонов для JavaScript-фреймворка [Ember.js](http://emberjs.com/).

## Компоненты Flexberry Ember

* [Контролы](ef2_controls.html)
* [Формы](ef2_forms.html)

## Структурные элементы Flexberry Ember-приложения

Структура `Flexberry Ember`-приложения такая же как и у классического [ember-приложения](https://guides.emberjs.com/v2.4.0/getting-started/core-concepts/), но есть некоторые нюансы, которые описаны в отдельных статьях.

* [Модели](efd2_model.html)
* [Сериализаторы](efd2_serializer.html)
* [Роутер](ef2_router.html)
* [Роуты](ef2_route.html)
* [Контроллеры](ef2_controller.html)
* [Шаблоны](ef2_template.html)
* [Сервисы](ef2_service.html)

## Дополнительные возможности

* [Запуск приложения Flexberry Ember в качестве мобильного приложения Apache Cordova](ef2_cordova.html)
* [Генерация Ember-приложения из меню Flexberry Designer](ef2_generator.html)

## Программы и инструменты для генерации ember-приложения

Для создания приложения с использованием фреймворка `Flexberry Ember` необходимо установить ряд программ и осуществить их настройку для корректной работы.

### Программное обеспечение

1. [Flexberry Desinger](https://flexberry.github.io/ru/fd_flexberry-designer.html). [Установить](https://flexberry.github.io/ru/fd_install.html) Flexberry Desinger можно с сайта [flexberry.net](https://flexberry.net/). При первичной установке дается промо-лицензия на 14 дней. Затем ее можно [продлить](https://designer.flexberry.net/#/download-win-app) на срок от 6 до 12 месяцев. Для студентов колледжей и ВУЗов предоставляется бесплатная академическая лицензия на полгода.
2. Visual Studio 2017 или новее. При работе с приложениями на базе фреймворков [Ember.js](https://emberjs.com/) и `Flexberry Ember` также помогут [Visual Studio Code](https://code.visualstudio.com/), [Sublime Text](http://www.sublimetext.com/) или их [аналоги](https://jpnsoft.ru/visual-studio-code/).
3. PostgreSQL. Для выполнения работ рекомендуется использовать бесплатный облачный сервис [https://www.elephantsql.com/](https://www.elephantsql.com/).
4. Azure DevOps. Для выполнения работ рекомендуется использовать бесплатный облачный сервис Azure DevOps Services
5. NodeJS версии 10.x: [https://nodejs.org/dist/latest-v10.x/](https://nodejs.org/dist/latest-v10.x/)
6. EmberJS версии 3.1.4 или 2.4.3, в зависимости от планируемой к использованию версии `ember-flexberry` (для установки выполнить `npm i -g ember-cli@3.1.4` или `npm i -g ember-cli@2.4.3`)
7. Последняя версия Bower (для установки выполнить `npm i -g bower`)

### Настройка NPM

При работе в ОС Windows необходимо проверить, что в переменной окружения PATH для текущего пользователя содержится путь `%USERPROFILE%\AppData\Roaming\npm`.

### Настройка Flexberry Desinger

Чтобы ember-генератор работал корректно требуется в файле конфигурации CASEBERRY.exe.config установить следующее значение параметра `EmberPluginAddonName` (либо `ember-flexberry@2.4.0`, если используется ember-cli@2.4.3).

```xml
<appSettings>
  ...
  <add key="EmberPluginAddonName" value="ember-flexberry@3.3.0" />
  ...
</appSettings>
```
