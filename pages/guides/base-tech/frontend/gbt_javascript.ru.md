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

**DOM** (от англ. Document Object Model — «объектная модель документа») - это не зависящий от платформы и языка программный интерфейс (API), позволяющий программам и скриптам получить доступ к содержимому HTML-, XHTML- и XML-документов, а также изменять содержимое, структуру и оформление таких документов.

**TypeScript** - это типизированное надмножество языка JavaScript, которое компилируется в чистый JavaScript. Данный язык является разработкой компании Microsoft.

**CoffeeScript** - это текстовый формат обмена данными, основанный на JavaScript. 

**AJAX** (от англ. Asynchronous Javascript and XML — «асинхронный JavaScript и XML») - это подход к построению интерактивных пользовательских интерфейсов и получению/передаче данных в веб-приложениях, заключающийся в «фоновом» обмене данными браузера с веб-сервером. В результате, при обновлении данных веб-страница не перезагружается полностью, и веб-приложения становятся быстрее и удобнее.

**JSON** (от англ. JavaScript Object Notation — «объектная нотация JavaScript») - это текстовый формат обмена данными, основанный на JavaScript. JSON является синтаксисом для сериализации объектов, массивов, чисел, строк логических значений и значения null. Он основывается на синтаксисе JavaScript, однако всё же отличается от него: не каждый код на JavaScript является JSON, и не каждый JSON является кодом на JavaScript. 

**Менеджер пакетов** - набор программного обеспечения, позволяющего управлять процессом установки, удаления, настройки и обновления различных компонентов программного обеспечения. Менеджеры пакетов избавляют от необходимости ручной установки/обновления дополнительных библиотек, их конфигурирования и управления зависимостями между ними. В мире JavaScript-разработки используются менеджеры пакетов [npm](https://www.npmjs.com/), [bower](https://bower.io/) и [yarn](https://yarnpkg.com/lang/en/).

**Статические анализаторы кода** - программы для анализа исходного кода на соответствие определенным правилам без реального выпоолнения исследуемых программ. Применяются для нахождения синтаксических и стилистических ошибок кода в процессе написания и тестирования приложений. Для JavaScript-разработки применяются [JSHint](http://jshint.com/), [JSLint](http://www.jslint.com/), [ESLint](http://eslint.org/), [JSCS](http://jscs.info/), [XO](https://github.com/sindresorhus/xo) и другие.

**Системы сборки** - программные инструменты, позволяющие писать и запускать скрипты, которые автоматизируют процессы сборки и развертывания разрабатываемого решения. Обычно эти процессы включают такие задачи как компиляция, конкатенация (склеивание) файлов, минимизация/обфускация кода, проверка стиля и корректности написания кода, сборка кода из исходных файлов и размещение подготовленного решения в указанное место файловой системы, выполнение тестов, развертывание решения и генерация автодокументации. Наиболее популярными инструментами сборки для фронтэнда являются [Grunt](http://gruntjs.com/), [gulp.js](http://gulpjs.com/), [Broccoli.js](http://broccolijs.com/), [Brunch](http://brunch.io/).

**Тестирование JavaScript-кода** - автоматическое тестирование (обычно [модульное](https://ru.wikipedia.org/wiki/%D0%9C%D0%BE%D0%B4%D1%83%D0%BB%D1%8C%D0%BD%D0%BE%D0%B5_%D1%82%D0%B5%D1%81%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5)) клиентского кода. Для тестирования клиентского кода используется комплекс инструментов:

* Фреймворки для тестирования JavaScript-кода (Test frameworks): [qUnit](http://qunitjs.com/), [Mocha](http://mochajs.org/), [Jasmine](https://jasmine.github.io/), [YUI Test](https://yuilibrary.com/yui/docs/test/) и другие.
* Библиотеки для проверок (Assertion libraries): [Assert.js](http://angular.github.io/assert/), [Chai](http://chaijs.com/), [should.js](https://github.com/shouldjs/should.js), [expect.js](https://github.com/Automattic/expect.js) и другие.
* Утилиты для запуска тестов (Test runners): [Karma](http://karma-runner.github.io/), [AVA](https://github.com/avajs/ava), [testem](https://github.com/testem/testem) и другие.
* Mock-библиотеки (Mocking libraries): [Sinon.js](http://sinonjs.org/), [MoqJS](https://github.com/slavik57/moqjs), [testdouble.js](https://github.com/testdouble/testdouble.js), [jsmock](https://github.com/chrismichaelscott/jsmock) и другие.
* Инструменты для тестирования серверного API и AJAX-запросов (API Mocking tools): [Sandbox](https://getsandbox.com/), [nock](https://github.com/node-nock/nock), [pretender](https://github.com/pretenderjs/pretender), [jquery-mockjax](https://github.com/jakerella/jquery-mockjax) и другие.
* "Консольные браузеры" (консольные версии движков WebKit и Gecko, доступные через JavaScript API): [PhantomJS](http://phantomjs.org/), [SlimerJS](http://slimerjs.org/).
* Серверы непрерывной интеграции и сборки: [Travis CI](travis-ci.org), [Bamboo](https://ru.atlassian.com/software/bamboo), [Jenkins](https://jenkins.io/) и другие.
* Платформы для автоматизированного тестирования: [Sauce Labs](https://saucelabs.com/), [Ubertesters](https://ubertesters.com/) и другие.

##  Ссылки на материалы для изучения

### Базовые ресурсы

* [JavaScript — Википедия](https://ru.wikipedia.org/wiki/JavaScript)
* [DOM — Википедия](https://ru.wikipedia.org/wiki/JavaScript)
* [TypeScript — Википедия](https://ru.wikipedia.org/wiki/TypeScript)
* [CoffeeScript — Википедия](https://ru.wikipedia.org/wiki/CoffeeScript)
* [AJAX — Википедия](https://ru.wikipedia.org/wiki/AJAX)
* [JSON — Википедия](https://ru.wikipedia.org/wiki/JSON)
* [Стандарт ECMAScript 5 (ECMA-262 5.1 Edition) - Ecma international](http://www.ecma-international.org/ecma-262/5.1/)
* [Стандарт ECMAScript 5 (ECMA-262 5.1 Edition), русский перевод - javascript.ru](http://es5.javascript.ru/)
* [Стандарт ECMAScript 2015 (ECMA-262 6th Edition) - Ecma international](http://www.ecma-international.org/ecma-262/6.0/)
* [Стандарт ECMAScript 2016 (ECMA-262 7th Edition) - Ecma international](http://www.ecma-international.org/ecma-262/7.0/)
* [Стандарты Document Object Model (DOM) - w3.org](https://www.w3.org/DOM/Activity)
* [Стандарты JavaScript APIs - w3.org](https://www.w3.org/standards/techs/js)
* [TypeScript Documentation - TypeScript](http://www.typescriptlang.org/docs/tutorial.html)
* [CoffeeScript Documentation - CoffeeScript](http://coffeescript.org/)

### Самоучители и учебники
* [Учебник JavaScript - javascript.ru](https://learn.javascript.ru/)
* [Учебники и справочники JavaScript - Mozilla Developer Network](https://developer.mozilla.org/ru/docs/Web/JavaScript)
* [JavaScript и jQuery - HTML5BOOK.ru](https://html5book.ru/javascript-jquery/)

### Интерактивные курсы

Представленные ниже интерактивные курсы включают в себя задания для проверки полученных знаний и навыков.

* [JS Tutorial - W3Schools](http://www.w3schools.com/js/)

### Справочники
* [Справочник JavaScript - javascript.ru](http://javascript.ru/manual)
* [JavaScript and HTML DOM Reference - W3Schools](http://www.w3schools.com/jsref/)

### Видеокурсы
* [Основы разработки сайтов и веб-приложений - Microsoft Virtual Academy](https://mva.microsoft.com/ru/training-courses/--8723?l=zZGYOLS1_1904984382)
* [Основы JavaScript - Sorax](https://www.youtube.com/playlist?list=PL363QX7S8MfSxcHzvkNEqMYbOyhLeWwem)
* [Видеокурс по JavaScript Essential - ITVDN](https://www.youtube.com/playlist?list=PLvItDmb0sZw8opJURj5eHdfdU5bnnhE5W)
* [Видеокурс по JavaScript Advanced - ITVDN](https://www.youtube.com/playlist?list=PLvItDmb0sZw_-AAcqd6XeZxJMKdv55_mS)
* [Видеокурс по JavaScript Шаблонам - ITVDN](https://www.youtube.com/playlist?list=PLvItDmb0sZw9GTaAsuPGRydAUG61MnCsy)
* [Видеокурс по TypeScript Fundamentals - ITVDN](https://www.youtube.com/playlist?list=PLvItDmb0sZw-IMHd1YSzLZ4UayqSQP6-I)
* [Видеокурс по CoffeeScript - webtheory](https://www.youtube.com/playlist?list=PLwSSV-_L9sztYcaMbY2XlehMFeR8Khs0j)

### Дополнительно
* [Школа разработки интерфейсов. Москва - Академия Яндекса](https://academy.yandex.ru/events/frontend/shri_msk-2013/)
* [Школа разработки интерфейсов. Екатеринбург - Академия Яндекса](https://academy.yandex.ru/events/frontend/shri_ekb-2013/)
* [Школа разработки интерфейсов. Симферополь - Академия Яндекса](https://academy.yandex.ru/events/frontend/shri_simf-2013/)
* [Chrome DevTools: отладка, консоль, профилирование - JSib Community](https://www.youtube.com/watch?v=uNJI3iYO-mA&t=525s)

### Вместо презентации


<div class="thumb-wrap">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/KmTK8kub_gw" frameborder="0" allowfullscreen></iframe>
</div>


### Рекомендованные книги

* [Знакомство с JavaScript - Сами Песси](https://webref.ru/dev/learn-javascript)
* [Создание первого приложения на Node - Крис Севилеха](https://webref.ru/dev/first-node-app)
* [Node, Express и libsass: проект с нуля - Дейл Санде](https://webref.ru/dev/node-express-libsass)
* [Приступая к работе с Grunt - Крис Севилеха](https://webref.ru/dev/grunt-getting-started)
* [Сборка с Gulp - Каллум Макрей](https://webref.ru/dev/building-with-gulp)
* [Автоматизация работы с Gulp - Ахмед Салифу Амиду](https://webref.ru/dev/automate-with-gulp)
* [Bower, Gulp и Yeoman - Дэви Шафик](https://webref.ru/dev/bower-gulp-yeoman)
* [Веб-приложения на JavaScript - Алекс Маккоу](http://www.ozon.ru/context/detail/id/28317424/)
* [JavaScript. Оптимизация производительности - Николас Закас](http://www.ozon.ru/context/detail/id/18421547/)
* [Секреты JavaScript ниндзя - Джон Резиг, Беэр Бибо](http://www.ozon.ru/context/detail/id/22421421/)

## Программное обеспечение

* [Базовое программное обеспечение - Mozilla Developer Network](https://developer.mozilla.org/ru/docs/Learn/Getting_started_with_the_web/%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B1%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE_%D0%BE%D0%B1%D0%B5%D1%81%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D1%8F)
* [Visual Studio Code](https://code.visualstudio.com/)
* [Google Chrome DevTools - Mozilla Developer Network](https://developer.chrome.com/devtools)
* [Инструменты разработчика Firefox - Mozilla Developer Network](https://developer.mozilla.org/ru/docs/Tools)
* [Расширение Firebug для Firefox - Mozilla Developer Network](https://addons.mozilla.org/ru/firefox/addon/firebug/)
* [Расширение Web Developer для Firefox - Mozilla Developer Network](https://addons.mozilla.org/ru/firefox/addon/web-developer/)

## Тесты, лабораторные работы и практические задания

### Тесты
* [Тесты по языку JavaScript и DOM - javascript.ru](https://learn.javascript.ru/quiz)
* [JavaScript Quiz Test - W3Schools](http://www.w3schools.com/js/js_quiz.asp)

### Практические задания
* [Learn JavaScript - Codecademy](https://www.codecademy.com/learn/learn-javascript)

### Задания для самостоятельного выполнения

Варианты заданий для самостоятельного выполнения с последующей проверкой со стороны преподавателя.

### Вариант №1

### Вариант №2

### Вариант №3

### Вариант №4

### Вариант №5

## Примеры

* [-]()
* [-]()

## Возможности по сертификации

* [W3Schools JavaScript Certificate](http://www.w3schools.com/cert/cert_javascript.asp)
* [MTA 98-375 HTML5 Application Development Fundamentals](https://www.microsoft.com/ru-ru/learning/exam-98-375.aspx)

## Перейти

* [JQuery](gbt_jquery.html)
* [Главная страница курса](gbt_landing-page.html)