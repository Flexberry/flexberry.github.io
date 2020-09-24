---
title: Обзор структуры сгенерированных приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_generated-app-structure.html
lang: ru
summary: Обзор структуры сгенерированных клиентского и серверного приложений, основные рекомендации по написанию кода с возможностью перегенерации
---

Рассмотрение сгенерированного приложения происходит для диаграммы классов 

![Диаграмма классов для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generation-class-diagram.png).

## Структура сгенерированного клиентского приложения

Структура сгенерированного `Flexberry Ember`-приложения по сути является расширением [«чистого» Ember-приложения](https://guides.emberjs.com/v3.0.0/getting-started/core-concepts/). На скриншоте ниже слева представлена структура, полученная с помощью командной строки Ember, справа - с помощью генерации кода клиентского приложения из [Flexberry Designer](https://flexberry.github.io/ru/fd_flexberry-designer.html). Как можно заметить, в правой структуре уже установлены bower- и node-модули (из-за этой установки, помимо прочего, генерация из [Flexberry Designer](https://flexberry.github.io/ru/fd_flexberry-designer.html) может занимать существенное время), а также добавлен файл «theme.config» для осуществления настройки темы оформления конкретного приложения.

![Сравнение структуры «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare.png).

При сравнении структуры папки 'app' (на скриншоте ниже слева «чистое» Ember-приложение, справа `Flexberry Ember`-приложение) также очевидно, что `Flexberry Ember`-приложение содержит дополнительные элементы.

![Cравнение структуры папки APP «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare-detailed.png).

Детально структура сгенерированного клиентского `Flexberry Ember`-приложения рассмотрена в отдельных статьях:

* [Адаптеры](efd3_generated-adapter.html) (папка 'adapters')
* [Компоненты](efd3_generated-component.html) (папка 'components')
* [Контроллеры](efd3_generated-controller.html) (папка 'controllers')
* [Перечисления](efd3_generated-enum.html) (папка 'enums')
* [Хэлперы](efd3_generated-helper.html) (папка 'helpers')
* [Локали](efd3_generated-locale.html) (папка 'locales')
* [Миксины](efd3_generated-mixin.html) (папка 'mixins')
* [Модели](efd3_generated-model.html) (папка 'models')
* [Роуты](efd3_generated-route.html) (папка 'routes')
* [Сериализаторы](efd3_generated-serializer.html) (папка 'serializers')
* [Сервисы](efd3_generated-service.html) (папка 'services')
* [Стили](efd3_generated-style.html) (папка 'styles')
* [Шаблоны](efd3_generated-template.html) (папка 'templates')
* [Преобразования](efd3_generated-transform.html) (папка 'transforms')
* [Роутер](efd3_generated-router.html) (файл 'router.js')

## Структура сгенерированного серверного приложения

Сгенерированное приложение состоит из двух основных частей. Первая - это классы объектов данных, соответствующих классам на диаграмме классов приложения [Flexberry Designer](https://flexberry.github.io/ru/fd_flexberry-designer.html). Вторая - это непосредственно OData-бакэнд, к которому будет обращаться клиентское приложение.

![Структура сгенерированного серверного приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-app.png).

## Правила написания кода
К добавлению прикладного кода необходимо подходить ответственно, поскольку он может быть утерян при последующих перегенерациях.

Код для объектов данных должен быть заключён в [скобки программиста](https://flexberry.github.io/ru/fo_programmer-brackets.html). Аналогичного механизма для остальных сгенерированных частей не предусмотрено, поэтому возможны следующие варианты решения проблемы:

* Код может быть помещён под систему контроля версий, посредством которой возможно отслеживание изменений, произошедших при перегенерации, и корректное их разрешение.
* Генерация кода может производиться в отдельное место, откуда изменения уже будут перенесены в основной проект, однако при использовании данного способа существует опасность, что не все требуемые изменения будут перенесены.