---
title: CSS
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_css.html
folder: guides/base-tech/frontend/
lang: ru
---
## Краткое описание

**CSS** (от англ. Cascading Style Sheets — «каскадные таблицы стилей») - это язык иерархических правил (таблиц стилей), используемый для представления внешнего вида документа, написанного на HTML или [XML](https://developer.mozilla.org/ru/docs/XML) (включая различные языки XML, такие как [SVG](https://developer.mozilla.org/ru/docs/SVG) и [XHTML](https://developer.mozilla.org/ru/docs/XHTML)). CSS описывает, каким образом элемент должен отображаться на экране, на бумаге, голосом или с использованием других медиа средств.

**CSS3** - это последнее эволюционное изменение языка CSS, которое направлено на расширение CSS 2.1. Оно привносит давно ожидаемые новшества, такие как закруглённые углы, тени, градиенты, переходы или анимация, а также новые макеты, такие как макет из нескольких колонок, «резиновый» дизайн или сеточный макет.

**LESS** - [препроцессор](https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%B5%D0%BF%D1%80%D0%BE%D1%86%D0%B5%D1%81%D1%81%D0%BE%D1%80) языка CSS, позволяющий использовать переменные, функции, циклы и другие технологии для упрощения работы со стилями. «Препроцессор» в данном случае означает, что мы имеем дело с динамическим языком стилей, который преобразуется (компилируется) в CSS.

**SASS** (от англ. Syntactically Awesome Stylesheets — «синтаксически великолепные таблицы стилей») - еще один популярный препроцессор языка CSS.

##  Пример использования

```css
body {
  font-family: Arial, Verdana,  sans-serif; /* Семейство шрифтов */
  font-size: 11pt; /* Размер основного шрифта в пунктах  */
  background-color: #f0f0f0; /* Цвет фона веб-страницы */
  color: #333; /* Цвет основного текста */ 
}
h1 {
  color: #a52a2a; /* Цвет заголовка */
  font-size: 24pt; /* Размер шрифта в пунктах */
  font-family: Georgia, Times, serif; /* Семейство шрифтов */
  font-weight: normal; /* Нормальное начертание текста  */
}
p {
  text-align: justify; /* Выравнивание по ширине */
  margin-left: 60px; /* Отступ слева в пикселах */
  margin-right: 10px; /* Отступ справа в пикселах */
  border-left: 1px solid #999; /* Параметры линии слева */
  border-bottom: 1px solid #999; /* Параметры линии снизу */
  padding-left: 10px; /* Отступ от линии слева до текста  */
  padding-bottom: 10px; /* Отступ от линии снизу до текста  */
}
```

##  «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/iPV5GKeHyV4" frameborder="0" allowfullscreen></iframe>
</div>

## Программное обеспечение

* [Базовое программное обеспечение](https://developer.mozilla.org/ru/docs/Learn/Getting_started_with_the_web/%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0_%D0%B1%D0%B0%D0%B7%D0%BE%D0%B2%D0%BE%D0%B3%D0%BE_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE_%D0%BE%D0%B1%D0%B5%D1%81%D0%BF%D0%B5%D1%87%D0%B5%D0%BD%D0%B8%D1%8F) *- Mozilla Developer Network*
* [Visual Studio Code](https://code.visualstudio.com/) *- code.visualstudio.сom*
* [Google Chrome DevTools](https://developer.chrome.com/devtools) *- Mozilla Developer Network*
* [Инструменты разработчика Firefox](https://developer.mozilla.org/ru/docs/Tools) *- Mozilla Developer Network*
* [Расширение Firebug для Firefox](https://addons.mozilla.org/ru/firefox/addon/firebug/) *- Mozilla Developer Network*
* [Расширение Web Developer для Firefox](https://addons.mozilla.org/ru/firefox/addon/web-developer/) *- Mozilla Developer Network*
* [W3C CSS Validation Service](https://jigsaw.w3.org/css-validator/) *- jigsaw.w3.org*
* [Получить информацию об устройстве графического вывода](http://mydevice.io/) *- mydevice.io*
* [SimpLESS - Компилятор LESS](https://wearekiss.com/simpless/) *- wearekiss.сom*
* [Koala - Компилятор LESS, SASS, Compass и CoffeScript](http://koala-app.com/) *- koala-app.сom*
* [Less.js - Компилятор LESS на Node.js](https://github.com/less/less.js) *- github.сom*

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Базовые ресурсы</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://developer.mozilla.org/ru/docs/Web/CSS/CSS3">Статья по CSS3</a><i> — Mozilla Developer Network</i></li>
                    <li><a href="http://sass-scss.ru/">SASS - Документация на русском языке</a><i> — sass-scss.ru</i></li>
                    <li><a href="http://lesscss.org/">LESS - Official Site</a><i> — lesscss.org</i></li>
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
                Самоучители</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://html5book.ru/css-css3/">Самоучитель CSS и CSS3</a><i> — HTML5BOOK.ru</i></li>
                    <li><a href="http://htmlbook.ru/faq">Рецепты CSS</a><i> — htmlbook.ru</i></li>
                    <li><a href="https://webref.ru/layout/less">Самоучитель Less</a><i> — WebReference.ru</i></li>
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
                Интерактивные курсы</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://webref.ru/course/css-basics">Основы CSS</a><i> — WebReference.ru</i></li>
                    <li><a href="https://webref.ru/course/sass">Основы SASS</a><i> — WebReference.ru</i></li>
                    <li><a href="http://www.w3schools.com/css/">CSS Tutorial</a><i> — W3Schools</i></li>
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
                Справочники</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://htmlbook.ru/css">Справочник CSS</a><i> — htmlbook.ru</i></li>
                    <li><a href="https://developer.mozilla.org/ru/docs/Web/CSS/Reference">Справочник CSS</a><i> — Mozilla Developer Network</i></li>
                    <li><a href="http://www.w3schools.com/cssref/">CSS Reference</a><i> — W3Schools</i></li>
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
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://channel9.msdn.com/Series/HTML5-and-CSS3-Fundamentals-Development-for-Absolute-Beginners?l=Y4COscFfB_7500115888">HTML5 & CSS3 Fundamentals: Development for Absolute Beginners</a><i> — msdn Channel 9</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLX2yKxdx7ck-Ex9XFu8Zz0m1JJyZF9OtE">LESS - уроки и примеры. Все о CSS препроцессоре LESS</a><i> — youtube-аккаунт «Best Lessons»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLZfRjCZl2NuQr8v2_DV8ZX6a03gntn7yU">Все про Sass и Compass</a><i> — youtube-аккаунт «uWebDesign»</i></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.ozon.ru/context/detail/id/3881079/">CSS. Каскадные таблицы стилей. Подробное руководство</a><i> — ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/5538886/">CSS. Рецепты программирования</a><i> — ozon.ru</i></li>
                    <li><a href="https://webref.ru/layout/magic-of-css">Магия CSS</a><i> — WebReference.ru</i></li>
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
                Тесты, лабораторные работы и практические задания</a>
            </h4>
        </div>
        <div id="collapse7" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/css/css_quiz.asp">CSS Quiz</a><i> — W3Schools</i></li>
                    <li><a href="http://www.w3schools.com/css/exercise.asp">CSS Exercises</a><i> — W3Schools</i></li>
                    <li><a href="http://htmlbook.ru/practical">Практикум по HTML и CSS</a><i> — htmlbook.ru</i></li>
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
                Примеры</a>
            </h4>
        </div>
        <div id="collapse8" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/css/css_examples.asp">CSS Examples</a><i> — W3Schools</i></li>
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
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse9" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/cert/cert_css.asp">CSS Certification</a><i> — W3Schools</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Язык программирования JavaScript](gbt_javascript.html)
* [Главная страница курса](gbt_landing-page.html)
