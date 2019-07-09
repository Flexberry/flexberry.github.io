---
title: ORM
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_orm.html
lang: ru
---

## Краткое описание

**ORM (англ. Object-Relational Mapping, рус. объектно-реляционное отображение)** — технология программирования, которая связывает базы данных с концепциями объектно-ориентированных языков программирования, создавая «виртуальную объектную базу данных». Существуют как проприетарные, так и свободные реализации этой технологии.

**Задача.** Необходимо обеспечить работу с данными в терминах классов, а не таблиц данных и напротив, преобразовать термины и данные классов в данные, пригодные для хранения в СУБД. Необходимо также обеспечить интерфейс для CRUD-операций над данными. В общем, необходимо избавиться от необходимости писать SQL-код для взаимодействия в СУБД

**Language Integrated Query (LINQ)** — проект Microsoft по добавлению синтаксиса языка запросов, напоминающего SQL, в языки программирования платформы .NET Framework. Ранее был реализован в языках C# и Visual Basic .NET. Множество концепций, которые вводит LINQ, изначально опробовали в исследовательском проекте Microsoft.

**ADO.NET** — это набор классов, предоставляющих службы доступа к данным программистам, которые используют платформу .NET Framework. ADO.NET имеет богатый набор компонентов для создания распределенных приложений, совместно использующих данные. Это неотъемлемая часть платформы .NET Framework, которая предоставляет доступ к реляционным данным, XML-данным и данным приложений. ADO.NET удовлетворяет различные потребности разработчиков, включая создание клиентских приложений баз данных, а также бизнес-объектов среднего уровня, используемых приложениями, средствами, языками и браузерам.

## Пример использования
Схема использования ORM
![Схема использования ORM:](/images/pages/guides/base-technologies/backend/ORM-System.png)

## «Для тех, кто предпочитает один раз увидеть»
<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
<iframe style="position: absolute; width: 100%; height: 100%; left: 0px; top: 0px; z-index: 2;" src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21116&authkey=AEyXkF-LOfH9yQw&em=2" frameborder="0" scrolling="no"></iframe>
</div>

## Программное обеспечение

* [Microsoft Visual Studio](https://www.visualstudio.com/)
* [MonoDevelop (Cross platform IDE for C#, F# and more)](http://www.monodevelop.com/)
* [LINQPad (The .NET Programmer’s Playground)](https://www.linqpad.net/)
* [dotPeek (Free .NET Decompiler and Assembly Browser)](https://www.jetbrains.com/decompiler/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Базовый курс</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA_ORM-%D0%B1%D0%B8%D0%B1%D0%BB%D0%B8%D0%BE%D1%82%D0%B5%D0%BA#.NET"> Список ORM-библиотек</a></li>
                    <li><a href="https://msdn.microsoft.com/en-us/library/mt693024.aspx"> Language-Integrated Query (LINQ) (C#)</a></li>
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/h43ks021(v=vs.110).aspx"> Общие сведения об ADO.NET</a></li> 
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/e80y5yhx(v=vs.110).aspx"> ADO.NET</a></li>
                    <li><a href="https://msdn.microsoft.com/ru-ru/library/bb399567(v=vs.110).aspx"> Общие сведения о платформе Entity Framework</a></li>
                    <li><a href="https://msdn.microsoft.com/en-us/library/aa937723(v=vs.113).aspx"> Introduction to Entity Framework</a></li>
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
                    <li><a href="https://professorweb.ru/my/LINQ/base/level1/info_linq.php"> LINQ - язык интегрированных запросов</a></li>
                    <li><a href="http://metanit.com/sharp/ado.php"> Платформа ADO.NET и Entity Framework</a></li>
                    <li><a href="https://professorweb.ru/my/ADO_NET/base/level1/info_db.php"> Работа с базами данных в .NET Framework</a></li>
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
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.youtube.com/watch?v=gQu-Swk1_hI"> Особенности LINQ</a></li>
                    <li><a href="https://www.youtube.com/watch?v=2YaIfvlRiWY&list=PLvItDmb0sZw9FohaaH2utnCloyM_fqYhN"> Видеокурс по Entity Framework 6</a></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.ozon.ru/context/detail/id/32602672/"> LINQ. Карманный справочник</a></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/32851534/"> Технология LINQ на примерах</a></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Введение в хранилища данных](gbt_storage.html)
* [Главная страница курса](gbt_landing-page.html)
