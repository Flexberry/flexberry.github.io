---
title: Компонент лукапа
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, Lookup, Flexberry Ember Component
toc: true
permalink: ru/efd3_lookup.html
lang: ru
summary: Обзор возможностей и настроек для компонента лукапа в технологии Flexberry Ember.
---

## Назначение компонента

## Обзор возможностей и API компонента

## Настройка окна со списком значений

## Указание функции ограничения

## Возможности сервиса lookup-events

Для расширения возможностей лукапа [технология Flexberry Ember предоставляет сервис](efd3_service.html) [`lookup-events`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/LookupEvents.html). Данный сервис позволяет подписываться на [события компонета лукапа](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/services/lookup-events.js).

* `lookupDialogOnShow` - событие взводится, когда модальное окно лукапа начинает отображаться. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа.
* `lookupDialogOnVisible` - событие взводится, когда модальное окно лукапа полностью отобразилось. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `lookupDialog` - контекст модального окна.
* `lookupDialogOnHidden` - событие взводится, когда модальное окно лукапа перестало отображаться. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа.
* `lookupOnChange` - событие взводится, когда значение лукапа было изменено. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `newValue` - новое значение лукапа.
* `lookupDialogOnDataLoaded` - событие взводится, когда в модальное окно лукапа заканчивают загружаться данные. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `loadedData` - загруженные данные, `isInitialLoad` - флаг, определяющий, является ли загрузка первой (перезагрузки возможны при наложении фильтров, работе пэйджинга и др.).

## Режим выпадающего списка

## Установка сортировки по умолчанию

## Лукап с автокомплитом
