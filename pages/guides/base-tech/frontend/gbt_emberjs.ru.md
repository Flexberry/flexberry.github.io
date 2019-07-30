---
title: Ember JS
keywords: Programming, ember-cli, ember weekly, ember times, frontend
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_emberjs.html
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

##  Пример использования

### Пример запуска Ember-приложения «dice-roller» с использованием `ember-cli`:

```javascript
$ cd dice-roller
$ ember serve
Livereload server on http://localhost:49153
'instrument' is imported from external module 'ember-data/-debug' but never used
Warning: ignoring input sourcemap for vendor/ember/ember.debug.js because ENOENT: no such file or directory, open '/Users/coxg/source/me/writing/repos/dice-roller/tmp/source_map_concat-input_base_path-2fXNPqjl.tmp/vendor/ember/ember.debug.map'
Warning: ignoring input sourcemap for vendor/ember/ember-testing.js because ENOENT: no such file or directory, open '/Users/coxg/source/me/writing/repos/dice-roller/tmp/source_map_concat-input_base_path-Xwpjztar.tmp/vendor/ember/ember-testing.map'

Build successful (5835ms) – Serving on http://localhost:4200/



Slowest Nodes (totalTime => 5% )              | Total (avg)
----------------------------------------------+---------------------
Babel (16)                                    | 4625ms (289 ms)
Rollup (1)                                    | 445ms

```

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe style="position: absolute; width: 100%; height: 100%; left: 0px; top: 0px; z-index: 2;" src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21117&authkey=ANqVyK0lG7YX6t0&em=2" frameborder="0" scrolling="no"></iframe>
</div>

## Больше информации по теме 

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse3">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse3">
                Детальный обзор</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://guides.emberjs.com/release/addons-and-dependencies/managing-dependencies/">Managing Dependencies</a><i> — guides.emberjs.сom</i>
                    <ul>
                        <li><a href="gbt_embaddon.html">Установка ember-addon-ов</a></li>
                        <li><a href="gbt_embnpm.html">Установка npm-пакетов</a></li>
                        <li><a href="gbt_embbower.html">Установка bower-пакетов</a></li>
                        <li><a href="gbt_embvendor.html">Работа с Vendor</a></li>
                        <li><a href="gbt_embassets.html">Каталог assets</a></li>
                        <li><a href="gbt_embclibuild.html">Работа с Ember-cli-build</a></li>
                    </ul>
                    </li>
                    <li><a href="https://guides.emberjs.com/release/configuring-ember/configuring-your-app/">Configuring Your App</a><i> — guides.emberjs.сom</i>
                    <ul>
                        <li><a href="gbt_embbaseconf.html">Базовая структура конфигурационного файла</a></li>
                        <li><a href="gbt_embsetting.html">Настройки зависящие от окружения</a></li>
                        <li><a href="gbt_embiosetting.html">Как импортировать его в свои классы  и вычитывать оттуда настройки</a></li>
                        <li><a href="gbt_embgetowner.html">Ember.getOwner и свойства экзепляра приложения</a></li>
                    </ul>
                    </li>
                    <li><a href="gbt_embrout.html">Маршрутизация</a></li>
                    <li><a href="gbt_embcontr.html">Контроллеры</a></li>
                    <li><a href="gbt_embtemp.html">Шаблоны</a></li>
                    <li><a href="gbt_devcomp.html">Разработка Ember-компонентов</a></li>
                    <li><a href="gbt_devservic.html">Разработка Ember-сервисов</a></li>
                    <li><a href="gbt_emddata.html">Работа с Ember Data</a></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Программное обеспечение

* [Ember-CLI](https://guides.emberjs.com/v2.16.0/getting-started/quick-start/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Примеры</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://guides.emberjs.com/v2.16.0/tutorial/ember-cli/">Creating Your App Tutorial</a><i> — guides.emberjs.сom</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Базовые сведения</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://guides.emberjs.com/v2.16.0/">Ember.js Guides and Tutorials</a><i> — emberjs.сom</i></li>
                    <li><a href="https://cli.emberjs.com/release/">The Ember CLI Guides</a><i> — emberjs.сom</i></li>
                    <li><a href="https://guides.emberjs.com/release/getting-started/core-concepts/">ECore Concepts</a><i> — emberjs.сom</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse4">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse4">
                Подписка на новости от команды EmberJS и сообщества</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://the-emberjs-times.ongoodbits.com/">The Ember.js Times</a><i> — The Ember Times</i></li>
                    <li><a href="http://www.emberweekly.com/">Ember Weekly</a><i> — Ember Weekly</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Серверная разработка](gbt_backend.html)
* [Главная страница курса](gbt_landing-page.html)
