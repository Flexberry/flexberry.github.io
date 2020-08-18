---
title: Язык программирования JavaScript
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_javascript.html
folder: guides/base-tech/frontend/
lang: ru
---

## Краткое описание

**JavaScript** - это это легкий, интерпретируемый, прототипно-ориентированный язык с динамической типизацией и [функциями первого класса](https://ru.wikipedia.org/wiki/%D0%A4%D1%83%D0%BD%D0%BA%D1%86%D0%B8%D0%B8_%D0%BF%D0%B5%D1%80%D0%B2%D0%BE%D0%B3%D0%BE_%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%B0). Является реализацией стандарта языка [ECMAScript](https://ru.wikipedia.org/wiki/ECMAScript). Наиболее широкое применение находит как язык сценариев веб-страниц, но также используется и как язык общего назначения (в том числе для разработки на стороне сервера) на базе программной платформы [Node.js](https://ru.wikipedia.org/wiki/Node.js).

**AJAX** (от англ. Asynchronous Javascript and XML — «асинхронный JavaScript и XML») - это подход к построению интерактивных пользовательских интерфейсов и получению/передаче данных в веб-приложениях, заключающийся в «фоновом» обмене данными браузера с веб-сервером. В результате, при обновлении данных веб-страница не перезагружается полностью, и веб-приложения становятся быстрее и удобнее.

**Тестирование JavaScript-кода** - автоматическое тестирование (обычно [модульное](https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%B4%D1%83%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5_%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)) клиентского кода. Для тестирования клиентского кода используется комплекс инструментов:

* Фреймворки для тестирования JavaScript-кода (Test frameworks): [qUnit](http://qunitjs.com/), [Mocha](http://mochajs.org/), [Jasmine](https://jasmine.github.io/), [YUI Test](https://yuilibrary.com/yui/docs/test/) и другие.
* Библиотеки для проверок (Assertion libraries): [Assert.js](http://angular.github.io/assert/), [Chai](http://chaijs.com/), [should.js](https://github.com/shouldjs/should.js), [expect.js](https://github.com/Automattic/expect.js) и другие.
* Утилиты для запуска тестов (Test runners): [Karma](http://karma-runner.github.io/), [AVA](https://github.com/avajs/ava), [testem](https://github.com/testem/testem) и другие.
* Mock-библиотеки (Mocking libraries): [Sinon.js](http://sinonjs.org/), [MoqJS](https://github.com/slavik57/moqjs), [testdouble.js](https://github.com/testdouble/testdouble.js), [jsmock](https://github.com/chrismichaelscott/jsmock) и другие.
* Инструменты для тестирования серверного API и AJAX-запросов (API Mocking tools): [Sandbox](https://getsandbox.com/), [nock](https://github.com/node-nock/nock), [pretender](https://github.com/pretenderjs/pretender), [jquery-mockjax](https://github.com/jakerella/jquery-mockjax) и другие.
* "Консольные браузеры" (консольные версии движков WebKit и Gecko, доступные через JavaScript API): [PhantomJS](http://phantomjs.org/), [SlimerJS](http://slimerjs.org/).
* Серверы непрерывной интеграции и сборки: [Travis CI](travis-ci.org), [Bamboo](https://ru.atlassian.com/software/bamboo), [Jenkins](https://jenkins.io/) и другие.
* Платформы для автоматизированного тестирования: [Sauce Labs](https://saucelabs.com/), [Ubertesters](https://ubertesters.com/) и другие.

##  Пример использования

### Вставка javascript кода в html:

```html
<html>
    <body>
        <script type="text/javascript">
            var d = new Date();
            var time = d.getHours();
            if (time < 10) 
            {
            document.write("<b>Доброе утро</b>");
            }
            else
            {
            document.write("<b>Добрый день</b>");
            }
        </script>
        <p>
            Этот пример демонстрирует конструкцию If...Else.
        </p>
        <p>
            Если время в вашем браузере меньше чем 10 часов,
            вы получите приветствие "Доброе утро".
            В противном случае вы увидите приветствие "Добрый день".
        </p>
    </body>
</html>
```

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/KmTK8kub_gw" frameborder="0" allowfullscreen></iframe>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Больше информации по теме</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><b>DOM</b> (от англ. Document Object Model — «объектная модель документа») - это не зависящий от платформы и языка программный интерфейс (API), позволяющий программам и скриптам получить доступ к содержимому HTML-, XHTML- и XML-документов, а также изменять содержимое, структуру и оформление таких документов.</li>
                    <li><b>TypeScript</b> - это типизированное надмножество языка JavaScript, которое компилируется в чистый JavaScript. Данный язык является разработкой компании Microsoft.</li>
                    <li><b>CoffeeScript</b> - это язык программирования, транслируемый в JavaScript. Добавляет синтаксический сахар в духе Ruby, Python, Haskell и Erlang для того, чтобы улучшить читаемость кода и уменьшить его размер по сравнению с аналогичным кодом на JavaScript.</li>
                    <li><b>JSON</b> (от англ. JavaScript Object Notation — «объектная нотация JavaScript») - это текстовый формат обмена данными, основанный на JavaScript. JSON является синтаксисом для сериализации объектов, массивов, чисел, строк логических значений и значения null. Он основывается на синтаксисе JavaScript, однако всё же отличается от него: не каждый код на JavaScript является JSON, и не каждый JSON является кодом на JavaScript. </li>
                    <li><b>Менеджер пакетов</b> - набор программного обеспечения, позволяющего управлять процессом установки, удаления, настройки и обновления различных компонентов программного обеспечения. Менеджеры пакетов избавляют от необходимости ручной установки/обновления дополнительных библиотек, их конфигурирования и управления зависимостями между ними. В мире JavaScript-разработки чаще всего используются менеджеры пакетов <a href="https://www.npmjs.com/">npm</a>, <a href="https://bower.io/">bower</a> и <a href="https://yarnpkg.com/lang/en/">yarn</a>.</li>
                    <li><b>Статические анализаторы кода</b> - программы для анализа исходного кода на соответствие определенным правилам без реального выпоолнения исследуемых программ. Применяются для нахождения синтаксических и стилистических ошибок кода в процессе написания и тестирования приложений. Для JavaScript-разработки применяются <a href="http://jshint.com/">JSHint</a>, <a href="http://www.jslint.com/">JSLint</a>, <a href="http://eslint.org/">ESLint</a>, <a href="http://jscs.info/">JSCS</a>, <a href="https://github.com/sindresorhus/xo">XO</a> и другие.</li>
                    <li><b>Системы сборки</b> - программные инструменты, позволяющие писать и запускать скрипты, которые автоматизируют процессы сборки и развертывания разрабатываемого решения. Обычно эти процессы включают такие задачи как компиляция, конкатенация (склеивание) файлов, минимизация/обфускация кода, проверка стиля и корректности написания кода, сборка кода из исходных файлов и размещение подготовленного решения в указанное место файловой системы, выполнение тестов, развертывание решения и генерация автодокументации. Наиболее популярными инструментами сборки для фронтэнда являются <a href="http://gruntjs.com/">Grunt</a>, <a href="http://gulpjs.com/">gulp.js</a>, <a href="http://broccolijs.com/">Broccoli.js</a>, <a href="http://brunch.io/">Brunch</a>.</li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Программное обеспечение

* Базовое программное обеспечение (требуется установить инструменты, необходимые в конкретном случае):
    * [Базовое программное обеспечение ](https://developer.mozilla.org/ru/docs/Learn/Getting_started_with_the_web/%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B1%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE_%D0%BE%D0%B1%D0%B5%D1%81%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D1%8F)*- Mozilla Developer Network*
* Среды разработки (требуется установить одну из указанных, рекомендуем Visual Studio Code):
    * [Visual Studio Code](https://code.visualstudio.com/) с расширениями [jshint](https://marketplace.visualstudio.com/items?itemName=dbaeumer.jshint), [EditorConfig](https://marketplace.visualstudio.com/items?itemName=EditorConfig.EditorConfig).
    * [WebStorm](http://www.jetbrains.com/webstorm/)
* Браузерные расширения и инструменты (требуется установить по необходимости):
    * [Google Chrome DevTools ](https://developer.chrome.com/devtools)*- Mozilla Developer Network*
    * [Инструменты разработчика Firefox ](https://developer.mozilla.org/ru/docs/Tools)*- Mozilla Developer Network*
    * [Расширение Firebug для Firefox ](https://addons.mozilla.org/ru/firefox/addon/firebug/)*- Mozilla Developer Network*
    * [Расширение Web Developer для Firefox ](https://addons.mozilla.org/ru/firefox/addon/web-developer/)*- Mozilla Developer Network*

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse2">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse2">
                Базовые ресурсы</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://es5.javascript.ru/">Стандарт ECMAScript 5 (ECMA-262 5.1 Edition), русский перевод</a><i> — javascript.ru</i></li>
                    <li><a href="https://www.w3.org/standards/techs/js">JavaScript APIs Standarts</a><i> — w3.org</i></li>
                    <li><a href="http://www.typescriptlang.org/docs/tutorial.html">TypeScript Documentation</a><i> — TypeScript</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse3">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse3">
                Самоучители и учебники</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://learn.javascript.ru/">Учебник JavaScript</a><i> — javascript.ru</i></li>
                    <li><a href="https://developer.mozilla.org/ru/docs/Web/JavaScript">Учебники и справочники JavaScript</a><i> — Mozilla Developer Network</i></li>
                    <li><a href="https://html5book.ru/javascript-jquery/">Самоучитель JavaScript и jQuery</a><i> — HTML5BOOK.ru</i></li>
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
                Интерактивные курсы</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/js/">JavaScript Tutorial</a><i> — W3Schools</i></li>                    
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse5">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse5">
                Справочники</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://javascript.ru/manual">Справочник JavaScript</a><i> — javascript.ru</i></li>
                    <li><a href="http://www.w3schools.com/jsref/">JavaScript and HTML DOM Reference</a><i> — W3Schools</i></li>                    
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse6">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse6">
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw8opJURj5eHdfdU5bnnhE5W">Видеокурс по JavaScript Essential</a><i> — youtube-аккаунт «ITVDN»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw_-AAcqd6XeZxJMKdv55_mS">Видеокурс по JavaScript Advanced</a><i> — youtube-аккаунт «ITVDN»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw9GTaAsuPGRydAUG61MnCsy">Видеокурс по JavaScript Шаблонам</a><i> — youtube-аккаунт «ITVDN»</i></li>                   
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse7">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse7">
                Дополнительно</a>
            </h4>
        </div>
        <div id="collapse7" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://youtu.be/uNJI3iYO-mA">Chrome DevTools: отладка, консоль, профилирование</a><i> — youtube-аккаунт «JSib Community»</i></li>                
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse8">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse8">
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse8" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://webref.ru/dev/learn-javascript">Знакомство с JavaScript</a><i> — WebReference</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/33835343/">JavaScript и jQuery. Исчерпывающее руководство</a><i> — ozon.ru</i></li>     
                    <li><a href="http://www.ozon.ru/context/detail/id/18421547/">JavaScript. Оптимизация производительности</a><i> — ozon.ru</i></li>                  
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse9">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse9">
                Тесты, лабораторные работы и практические задания</a>
            </h4>
        </div>
        <div id="collapse9" class="panel-collapse collapse">
            <div class="panel-body">
                <div>                    
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse10">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse10">
                                    Тесты</a>
                                </h4>
                            </div>
                            <div id="collapse10" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        <li><a href="https://learn.javascript.ru/quiz">Тесты по языку JavaScript и DOM</a><i> — javascript.ru</i></li>
                                        <li><a href="http://www.w3schools.com/js/js_quiz.asp">JavaScript Quiz Test</a><i> — W3Schools</i></li>                
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse11">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse11">
                                    Практические задания</a>
                                </h4>
                            </div>
                            <div id="collapse11" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        <li><a href="https://gist.github.com/codedokode/ce30e7a036f18f416ae0">Простые задачи на JavaScript</a><i> — codedokode</i></li>
                                        <li><a href="https://www.codecademy.com/learn/learn-javascript">Learn JavaScript</a><i> — Codecademy</i></li>     
                                        <li><a href="https://www.codewars.com/?language=javascript">JavaScript Challenge</a><i> — Codewars</i></li>                  
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
            </div>
        </div>
    </div>
</div>

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse12">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse12">
               Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse12" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/cert/cert_javascript.asp">JavaScript Certification</a><i> — W3Schools</i></li>          
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [JQuery](gbt_jquery.html)
* [Главная страница курса](gbt_landing-page.html)
