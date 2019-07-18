---
title: SQL
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_sql.html
lang: ru
---
## Краткое описание

**SQL (structured query language — «язык структурированных запросов»)** — формальный непроцедурный язык программирования, применяемый для создания, модификации и управления данными в произвольной реляционной базе данных, управляемой соответствующей системой управления базами данных (СУБД). SQL основывается на исчислении кортежей.

**SQL** является прежде всего информационно-логическим языком, предназначенным для описания, изменения и извлечения данных, хранимых в реляционных базах данных. SQL можно назвать языком программирования, при этом он не является тьюринг-полным, но вместе с тем стандарт языка спецификацией SQL/PSM предусматривает возможность его процедурных расширений.

##  Пример использования
### Пример запроса на выбор студентов, имеющих балл от 82 до 90, отсоротированных в порядке убывания балла
```sql
SELECT FAM FROM STUDENT WHERE BALL BETWEEN 81 AND 91 ORDER BY BALL DESC;
```

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21121&authkey=AB17cDLMUOuYkIE&em=2" width="402" height="327" frameborder="0" scrolling="no"></iframe>
</div>

## Программное обеспечение

* [Microsoft® SQL Server® 2014 Express](https://www.microsoft.com/ru-ru/download/details.aspx?id=42299)
* [Oracle Database Express Edition 11g Release 2](http://www.oracle.com/technetwork/database/database-technologies/express-edition/downloads/index.html)

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
                    <li><a href="http://www.contrib.andrew.cmu.edu/~shadow/sql/sql1992.txt">The SQL-92 standard</a><i> — contrib.andrew.cmu.edu</i></li>
                    <li><a href="http://citforum.ru/database/articles/art_2.shtml">Стандарты языка реляционных баз данных SQL: краткий обзор</a><i> — citforum.ru</i></li>
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
                    <li><a href="http://www.w3schools.com/sql/default.asp">SQL Tutorial</a><i> — W3Schools</i></li>
                    <li><a href="https://sql-language.ru/">Язык запросов SQL</a><i> — sql-language.ru</i></li>
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
                    <li><a href="https://www.youtube.com/playlist?list=PLeYxjiX1MAInukqt-0XKbG9qP2j0QovZH">Изучаем язык запросов SQL</a><i> — youtube-канал «Кирилл Антонов»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLN8vGBeK3TUrCfB6EdES5oQtXXTXFPnzT">Основы языка SQL (оператор SELECT)</a><i> — youtube-канал «Sergey Menshov»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw_NDm3pDMQ4_9bV_zKzBmNv">Видеокурс по SQL Essential</a><i> — youtube-канал «ITVDN»</i></li>
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
                    <li><a href="http://www.ozon.ru/context/detail/id/24939188/">SQL для простых смертных</a><i> — ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/31124973/">SQL. Полное руководство</a><i> — ozon.ru</i></li>
                    <li><a href="https://books.google.ru/books?id=XVnRAAAAQBAJ&pg=PA15&lpg=PA15&dq=sql:1992&source=bl&ots=IydSQak2Iz&sig=TplQOrfotnkk8qfE6vkb9kTrHX4&hl=ru&sa=X&ved=0ahUKEwiwosm2tfvRAhVMMJoKHTM0CAMQ6AEIRTAH#v=onepage&q=sql%3A1992&f=false">SQL: Руководство по изучению языка</a><i> — Google Книги</i></li>
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
                Лабораторные работы и практические задания</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://sql-ex.ru/">Упражнения по SQL</a><i> — sql-ex.ru</i></li>
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
                Примеры</a>
            </h4>
        </div>
        <div id="collapse6" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.sql-tutorial.ru/ru/content.html">SQL Задачи и решения</a><i> — sql-tutorial.ru</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLvItDmb0sZw-WX3dpyJJcuIyy6i2dT7FA">SQL Практикум</a><i> — youtube-канал «ITVDN»</i></li>
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
                    <li><a href="https://geekbrains.ru/courses/86">Видеокурс: основы баз данных</a><i> — GeekBrains</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [Microsoft SQL Server](gbt_mssql.html)
* [Главная страница курса](gbt_landing-page.html)
