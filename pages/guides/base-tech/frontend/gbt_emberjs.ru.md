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
* **Шаблоны** написаны на языке HTMLBars (HTML + [handlebars](http://handlebarsjs.com/) = HTMLbars) и описывают пользовательский интерфейс. Шаблоны используются для построения HTML кода приложения и позволяют встраивать в него динамически обновляемые выражения.

##  Ссылки на материалы для изучения

### Примеры

* [Приложение для сайта с арендой недвижимости (eng)](https://guides.emberjs.com/v2.11.0/tutorial/ember-cli/)
* [Приложение для сайта с арендой недвижимости (rus)](http://emjs.ru/v2/tutorial/ember-cli/)

### Базовый сведения

* [Официальная документация по Ember.js](https://guides.emberjs.com/v2.11.0/)
* [Официальная документация по Ember-CLI](https://ember-cli.com/user-guide/)
* [Базовая структура приложения](http://emjs.ru/v2/getting-started/core-concepts/)
* Состав приложения
    * [Маршрутизация](http://emjs.ru/v2/routing/)
    * [Контроллеры](http://emjs.ru/v2/controllers/)
    * [Шаблоны](http://emjs.ru/v2/templates/handlebars-basics/)
    * [Компоненты](http://emjs.ru/v2/components/defining-a-component/)
* [Тестирование](http://emjs.ru/v2/testing/)
    * [Приемочные тесты](http://emjs.ru/v2/testing/acceptance/#)
    * [Основы модульного тестирования](http://emjs.ru/v2/testing/unit-testing-basics/)
    * [Тестирование контроллеров](http://emjs.ru/v2/testing/testing-controllers/)
    * [Тестирование маршрутов](http://emjs.ru/v2/testing/testing-routes/)
    * [Тестирование моделей](http://emjs.ru/v2/testing/testing-models/)
    * [Тестирование компонентов](http://emjs.ru/v2/testing/testing-components/)

### Детальный обзор

* [Управление зависимостями приложения](http://emjs.ru/v2/addons-and-dependencies/managing-dependencies/)
    * [Установка ember-addon-ов](gbt_embaddon.html)
    * [Установка npm-пакетов](gbt_embnpm.html)
    * [Установка bower-пакетов](gbt_embbower.html)
    * [Vendor](gbt_embvendor.html)
    * [Каталог assets](gbt_embassets.html)
    * [Ember-cli-build](gbt_embclibuild.html)
* [Конфигурирование Ember.js](http://emjs.ru/v2/configuring-ember/configuring-your-app/)
    * [Базовая структура конфигурационного файл](gbt_embbaseconf.html)
    * [Настройки зависящие от окружения](gbt_embsetting.html)
    * [Как импортировать его в свои классы  и вычитывать оттуда настройки](gbt_embiosetting.html)
    * [Ember.getOwner и свойства экзепляра приложения](gbt_embgetowner.html)
* [Маршрутизация](gbt_embrout.html)
* [Контроллеры](gbt_embcontr.html)
* [Шаблоны](gbt_embtemp.html)
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
