---
title: JQuery
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_jquery.html
folder: guides/base-tech/frontend/
lang: ru
---

## Краткое описание

**jQuery** - это библиотека JavaScript, которая упрощает взаимодействие с DOM, обработку событий, анимацию, работу с AJAX. Для этого библиотека предоставлет простой в использовании API, работающий в различных браузерах.

**jQuery Mobile** - это сенсорно-ориентированный (мобильный) фреймворк, предназначенный для создания отзывчивых веб-сайтов и веб-приложений, доступных на всех смартфонах, планшетах и десктопных устройствах.

**jQuery UI** - это библиотека, которая предоставляет набор элеметов управления пользовательского интерфейса, эффектов, виджетов, тем, разработанных на основе библиотеки jQuery.

##  Пример использования

### Пример получения данных элементов формы и сохранения их в качестве строки:

```javascript
function showValues() 
{
    var str = $("form").serialize();
    return str; 	
}
$("form:eq(0)").submit(function(){
    var val = showValues();
    $(".serialize").text(val);
    $.post("post.php",{name:""+val+""},function(data){
    $(".result").empty();
    $(".result").append(data);});
    return false
    });

```

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/KmTK8kub_gw" frameborder="0" allowfullscreen></iframe>
</div>

## Программное обеспечение

* [См. программное обеспечение для JavaScript](http://flexberry.github.io/ru/gbt_javascript.html#section-10)

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
                    <li><a href="http://learn.jquery.com/">jQuery Learning Center</a><i> — jquery.сom</i></li>
                    <li><a href="http://api.jquery.com/">jQuery API</a><i> — jquery.сom</i></li>
                    <li><a href="http://api.jqueryui.com/">jQuery UI API</a><i> — jqueryui.сom</i></li>
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
                Самоучители и учебники</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://html5book.ru/javascript-jquery/">Самоучитель по JavaScript и jQuery</a><i> — HTML5BOOK.ru</i></li>
                    <li><a href="https://professorweb.ru/my/javascript/jquery/level1/jquery_index.php">Библиотека jQuery</a><i> — professorweb.ru</i></li>
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
                    <li><a href="http://www.w3schools.com/jquery/">jQuery Tutorial</a><i> — W3Schools</i></li>
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
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw964PmBjUcB75x17RK7M5ZA">Видеокурс по jQuery</a><i> — youtube-аккаунт «ITVDN»</i></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://webref.ru/dev/jqfundamentals">Основы jQuery</a><i> — WebReference</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/6277333/">jQuery. Подробное руководство по продвинутому JavaScript</a><i> — ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/20468239/">jQuery Mobile. Разработка приложений для смартфонов и планшетов</a><i> — ozon.ru</i></li>
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
                Тесты, лабораторные работы и практические задания</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/jquery/jquery_quiz.asp">jQuery Quiz Test</a><i> — W3Schools</i></li>
                    <li><a href="https://www.codecademy.com/learn/learn-jquery">Introduction to jQuery</a><i> — Codecademy</i></li>
                    <li><a href="https://gist.github.com/codedokode/ce30e7a036f18f416ae0#dom--jquery">Задания по DOM/jQuery</a><i> — codedokode</i></li>
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
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse7" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.w3schools.com/cert/cert_jquery.asp">jQuery Certification</a><i> — W3Schools</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Верстка](gbt_layout.html)
* [Главная страница курса](gbt_landing-page.html)
