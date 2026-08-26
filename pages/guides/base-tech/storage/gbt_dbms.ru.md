---
title: СУБД
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_dbms.html
lang: ru
---

## Краткое описание

**Система управления базами данных (СУБД)** — совокупность программных и лингвистических средств общего или специального назначения, обеспечивающих управление созданием и использованием баз данных

**Основные функции СУБД**

* управление данными во внешней памяти (на дисках);
* управление данными в оперативной памяти с использованием дискового кэша;
* журнализация изменений, резервное копирование и восстановление базы данных после сбоев;
* поддержка языков БД (язык определения данных, язык манипулирования данными).

**Состав СУБД**

* **ядро**, которое отвечает за управление данными во внешней и оперативной памяти и журнализацию,
* **процессор языка базы данных**, обеспечивающий оптимизацию запросов на извлечение и изменение данных и создание, как правило, машинно-независимого исполняемого внутреннего кода,
* **подсистему поддержки времени исполнения**, которая интерпретирует программы манипуляции данными, создающие пользовательский интерфейс с СУБД
* **сервисные программы (внешние утилиты)**, обеспечивающие ряд дополнительных возможностей по обслуживанию информационной системы

##  Пример использования

### Пример правил проверки поля в конструкторе таблиц СУБД Access

![Access Example](/images/pages/guides/base-technologies/storage/accessExample.png)

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21119&authkey=ABwmyQ0j-OWjgPc&em=2" width="402" height="327" frameborder="0" scrolling="no"></iframe>
</div>

## Программное обеспечение

* [Microsoft® SQL Server® 2014 Express](https://www.microsoft.com/ru-ru/download/details.aspx?id=42299)
* [PostgreSQL](https://www.postgresql.org/download/)
* [Microsoft Access](https://products.office.com/ru-ru/access)
* [Oracle Database Software](http://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html)

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
                    <li><a href="http://www.bseu.by/it/tohod/lekcii5.htm">Система управления базами данных - Лекции</a><i> — bseu.by</i></li>
                    <li><a href="http://citforum.ru/database/advanced_intro/">Базы данных. Вводный курс</a><i> — citforum.ru</i></li>
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
                    <li><a href="http://www.site-do.ru/db/db1.php">Основы баз данных</a><i> — site-do.ru</i></li>
                    <li><a href="http://www.intuit.ru/studies/courses/508/364/info">Академия Microsoft: Базы данных</a><i> — ИНТУИТ</i></li>
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
                    <li><a href="https://www.youtube.com/playlist?list=PLrCZzMib1e9r6c-j8aW1JuETSyCBp9iAg">Базы данных</a><i> — youtube-канал «Технострим Mail.Ru Group»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLrCZzMib1e9pyyrqknouMZbIPf4l3CwUP">Алгоритмы интеллектуальной обработки больших объемов данных</a><i> — youtube-канал «Технострим Mail.Ru Group»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLrCZzMib1e9pq_sbw7ZEcEU3Yyz1AvE--">Проектирование СУБД</a><i> — youtube-канал «Технострим Mail.Ru Group»</i></li>
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
                    <li><a href="http://www.ozon.ru/context/detail/id/136880774/">Введение в системы баз данных</a><i> — ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/24955082/">Разработка и эксплуатация удаленных баз данных. Учебник</a><i> — ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/138854275/">Базы данных. Учебник и практикум</a><i> — ozon.ru</i></li>
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
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse5" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://geekbrains.ru/courses/86">Видеокурс: основы баз данных</a><i> — GeekBrains</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Перейти

* [SQL](gbt_sql.html)
* [Главная страница курса](gbt_landing-page.html)
