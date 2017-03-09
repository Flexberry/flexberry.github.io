---
title: Разработка ember-компонентов
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_devcomp.html
folder: guides/base-tech/frontend/emberjs
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

### Базовый курс

* [Ember.js](http://emberjs.com/)
* [Ember-CLI](https://ember-cli.com/)
* [Guides and Tutorials Ember.js](https://guides.emberjs.com/v2.11.0/)
* [User guide Ember-CLI](https://ember-cli.com/user-guide/)

### Самоучители



### Видеокурсы



### Презентация

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="//www.slideshare.net/slideshow/embed_code/key/5snk9pZdXKzcES?startSlide=23" width="595" height="485" frameborder="0" marginwidth="0" marginheight="0" scrolling="no" style="border:1px solid #CCC; border-width:1px; margin-bottom:5px; max-width: 100%;" allowfullscreen> </iframe>
</div>

### Рекомендованные книги

* [-]()
* [-]()

## Программное обеспечение

* [Ember-CLI](https://guides.emberjs.com/v2.11.0/getting-started/)

## Примеры

* [Приложение для сайта с арендой недвижимости (eng)](https://guides.emberjs.com/v2.11.0/tutorial/ember-cli/)
* [Приложение для сайта с арендой недвижимости (rus)](http://emjs.ru/v2/tutorial/ember-cli/)

## Возможности по сертификации

* [-]()
* [-]()

## Перейти

* [Вернуться назад](gbt_emberjs.html)