---
title: Ember JS
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_emberjs.html
folder: guides/base-tech/frontend/
lang: ru
---

## Краткое описание

**Ember.js** — свободный JavaScript каркас веб-приложений, реализующий MVC шаблон, предназначенный для упрощения создания масштабируемых одностраничных веб-приложений. Фреймворк используется такими компаниями как TED, Yahoo!, Twitch.tv и Groupon.

В декабре 2011 года каркас веб-приложений SproutCore 2.0 был переименован в Ember.js, дабы не быть перепутанным с версией 1.0. Авторами проекта являются Tom Dale и Yehuda Katz, а всего в Ember Core Team более 10 разработчиков.

**Основные принципы**
* **Маршруты** являются одним из основополагающих принципов Ember.js и подчеркивают важность URL в управлении состоянием приложения. Маршруту объекта соответствует URL-адрес, который определяет текущее состояние приложения. Маршруты определены в единственном объекте маршрутизатора.
* **Модели**, каждому маршруту соответствует модель, в которой содержатся данные, соответствующие текущему состоянию приложения. И несмотря на то, что есть возможность использовать jQuery чтобы загружать с сервера JSON-объекты, большинство приложений все-таки использует для этих целей библиотеку с моделью данных, например, Ember Data.
* **Контроллеры** используются для того, чтобы добавить модели некую логику отображения. Ранее стандартной практикой было наследовать контроллер от ObjectController если модель представляла собой один объект, и от ArrayController - если модель была массивом записей. Сейчас эти базовые классы считаются устаревшими и нормальной практикой считается обращение к свойствам модели из Ember.Controller.
* **Шаблоны** написаны на языке HTMLBars и описывают пользовательский интерфейс. Шаблоны используются для построения HTML кода приложения и позволяют встраивать в него динамически обновляемые выражения.

##  Ссылки на материалы для изучения

### Примеры

* [Приложение для сайта с арендой недвижимости (eng)](https://guides.emberjs.com/v2.11.0/tutorial/ember-cli/)
* [Приложение для сайта с арендой недвижимости (rus)](http://emjs.ru/v2/tutorial/ember-cli/)

### Базовый сведения

* [Официальная документация по Ember.js](https://guides.emberjs.com/v2.11.0/)
* [Официальная документация по Ember-CLI](https://ember-cli.com/user-guide/)
* [Базовая структура приложения](http://emjs.ru/v2/getting-started/core-concepts/)
* Состав приложения
    * [Routing](https://emjs.ru/v2/routing/)
    * [Controllers](https://emjs.ru/v2/controllers/)
    * [Templates](https://guides.emberjs.com/v2.11.0/templates/handlebars-basics/)
    * [Components](https://guides.emberjs.com/v2.11.0/components/defining-a-component/)
* [Testing](https://guides.emberjs.com/v2.11.0/testing/)
    * [Acceptance Tests](https://guides.emberjs.com/v2.11.0/testing/acceptance/#)
    * [Unit Testing Basics](https://guides.emberjs.com/v2.11.0/testing/unit-testing-basics/)
    * [Testing Controllers](https://guides.emberjs.com/v2.11.0/testing/testing-controllers/)
    * [Testing Routes](https://guides.emberjs.com/v2.11.0/testing/testing-routes/)
    * [Testing Models](https://guides.emberjs.com/v2.11.0/testing/testing-models/)
    * [Testing Components](https://guides.emberjs.com/v2.11.0/testing/testing-components/)

### Детальный обзор

* [Управление зависимостями приложения](https://guides.emberjs.com/v2.11.0/addons-and-dependencies/managing-dependencies/)
    * Установка ember-addon-ов
    * Установка npm-пакетов
    * Установка bower-пакетов
    * Vendor
    * Каталог assets
    * Ember-cli-build
* [Конфигурирование Ember.js](https://guides.emberjs.com/v2.11.0/configuring-ember/configuring-your-app/)
    * Базовая структура конфига и значение настроек в нем
    * Настройки зависящие от окружения (development/production)
    * Как импортировать его в свои классы  и вычитывать оттуда настройки
    * Как добраться до этих настроек без импорта (через Ember.getOwner и свойства экзепляра приложения)
* [Routing](gbt_embrout.html)
* [Controllers](gbt_embcontr.html)
* [Templates](gbt_embtemp.html)
* [Разработка Ember-компонентов](gbt_devcomp.html)
* [Разработка Ember-сервисов](gbt_devservic.html)
* [Ember Data](gbt_emddata.html)

### Презентация

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="//www.slideshare.net/slideshow/embed_code/key/5snk9pZdXKzcES?startSlide=23" width="595" height="485" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" style="border:1px solid #CCC; border-width:1px; margin-bottom:5px; max-width: 100%;" allowfullscreen> </iframe>
</div>

## 

## Программное обеспечение

* [Ember-CLI](https://guides.emberjs.com/v2.11.0/getting-started/)

## Перейти

* [Серверная разработка](gbt_backend.html)
* [Главная страница курса](gbt_landing-page.html)