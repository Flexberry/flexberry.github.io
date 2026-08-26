---
title: MongoDB
keywords: NoSQL, BigData
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_mongodb.html
lang: ru
---


## Краткое описание

MongoDB — написанная на языке C++ документоориентированная NoSQL система управления базами данных с открытым исходным кодом, не требующая описания схемы таблиц (schemaless).

MongoDB имеет следующие уровни представления данных:
1. Документ - JSON-объект имеющий произвольное число полей. Поля могуть хранить как простве значение, так к воженные объекты и массивы.
2. Коллекция (таблица)- однотипные документы хранятся в отдельной коллекции. Документы в коллекции могуть быть проиндекированы. Доступ к докумениту возможен как по ключу, так и по значению полей.
3. База данных - набор коллекций.

Поддержка MongoDB реализована для большинства языков программирования: ```C```, ```C++```, ```C#```, ```Java```, ```Node.js```, ```Perl```, ```PHP```, ```Python```, ```Ruby```, ```Scala```.

Отличия MongoDB от реляционных баз данных:
- Не  поддерживаются транзации. Атомарность гарантируется только на уровне целого документа, то есть частичного обновления документа произойти не может.
 - Отсутствие механизма «изоляции». Любые данные, которые считываются одним клиентом, могут параллельно изменяться другим клиентом.
 
 Преимущества MongoDB перед реляционными базами:
 - Поддержка горизонтального масштабирования с репликацией данных. Данные могут храниться на произвольном числе серверов. Репликация обеспечивает отказоустойчивость системы с поддержкой функционала при выходе узлов из строя.
 - Формат хранения данных (документ) близок к формату представления данных в языках программирования (объектов) не требуется сложных и дорогостоящих запросов для получения нужного объекта.
- Поддержка операций MapReduce для массовой параллельной обработки данных.

## Пример использования

### Работа с MongoDB через СУБД Navicat

![MongoDB](/images/pages/guides/base-technologies/storage/MongoDB.png)

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap">
    <iframe width="854" height="480" src="https://www.youtube.com/embed/tgckAOyjXPI" frameborder="0" allowfullscreen></iframe>
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
                    <p>Для установки mongoDB удобнее всего воспользоваться<a href="https://hub.docker.com/_/mongo/"> docker-образом MongoDB.</a></p>
                    <p>Запуск сервера производится командой:</p>
                    <p><pre>docker run --name mongodb -p 27017:27017 -d mongo</pre></p>
                    <p>Для работы с сервером mongo в конейнере рекомендуется запустить в рамках запущенного контейнера mongo-shell командой:</p>
                    <p><pre>docker exec -it mongodb mongo</pre></p>
                </div>   
            </div>
        </div>
    </div>
</div>

## Программное обеспечение

* [Install MongoDB](https://docs.mongodb.com/manual/installation/);
* [Официальный Docker-образ MongoDB](https://hub.docker.com/_/mongo/)
* [Официальный Docker-образ WEB-интерфейса MongoDB](https://hub.docker.com/_/mongo-express/)

## Ресурсы

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
                    <li><a href="https://docs.mongodb.com/getting-started/shell/">Getting Started with MongoDB (MongoDB Shell Edition)</a><i> — MongoDB Documentation</i></li>
                    <li><a href="http://proselyte.net/tutorials/mongodb/">Руководство по MongoDB</a><i> — PROSELYTE Записки задумчивого программиста</i></li>
                    <li><a href="http://security-corp.org/administration/sys_admin/39539-rukovodstvo-po-bezopasnosti-mongodb.html">Руководство по безопасности MongoDB</a><i> — security-corp.org</i></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li>MongoDB в действии: <a href="https://www.ozon.ru/context/detail/id/8688130/"><i> — ozon.ru</i></a> ;<a href="https://cafe-aristokrat.nethouse.ru/static/doc/0000/0000/0165/165988.c2f3acpbax.pdf"><i> — электронный вариант на cafe-aristokrat.nethouse.ru</i></a></li>
                    <li><a href="http://www.pvsm.ru/download/mongodb-ru.pdf">The Little MongoDB Book (Маленькая книга о MongoDB)</a><i> — pvsm.ru</i></li>
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
                Лабораторные работы и практические задания</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    В качестве практических заданий  для лабораторных работ можно использовать <a href="https://github.com/mesdt/course/wiki/Tasks-Mongo"> Задания к лабораторной работе 5 MongoDB</a>. На данной странице приведены как<a href="https://yadi.sk/d/3l92O1G6fJst5"> тестовые наборы данных</a>, так и список заданий по выполнению операций на данных наборах данных.
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
                Примеры</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    Примеры выполнения CRUD и aggregation операций над указанными в предыдущем разделе наборами данных приведены на страницах:
                    <li><a href="https://github.com/mesdt/course/wiki/Cheat-list-Mongo">Команды CRUD в MongoDB</a><i> — github.сom</i></li>
                    <li><a href="https://github.com/mesdt/course/wiki/Cheat-list-Mongo-Aggregation-Framework">Aggregation Framework в MongoDB</a><i> — github.сom</i></li>
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
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://university.mongodb.com/certification">MongoDB Professional Certification Program</a><i> — MongoDB University</i></li>
                    <li><a href="https://habrahabr.ru/post/273011/">Сертификация mongoDB</a><i> — habr</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Разработка мобильных приложений](gbt_mobile.html)
* [Главная страница курса](gbt_landing-page.html)
