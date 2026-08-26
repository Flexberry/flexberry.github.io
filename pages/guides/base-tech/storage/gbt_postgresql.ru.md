---
title: PostgreSQL
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_postgresql.html
lang: ru
---

## Краткое описание

PostgreSQL — СУБД с открытым исходным кодом, основой которого был код, написанный в Беркли. Она поддерживает большую часть стандарта SQL и предлагает множество современных функций:

* сложные запросы
* внешние ключи
* триггеры
* изменяемые представления
* транзакционная целостность
* многоверсионность

Кроме того, пользователи могут всячески расширять возможности PostgreSQL, например создавая свои

* типы данных
* функции
* операторы
* агрегатные функции
* методы индексирования
* процедурные языки

Благодаря свободной лицензии, PostgreSQL разрешается бесплатно использовать, изменять и распространять всем и для любых целей — личных, коммерческих или учебных.

Базовые ссылки приведены на странице [Вики](https://wiki.postgresql.org/wiki/Russian)

## Пример использования

### Окно pgAdmin - СУБД для работы с PostgreSQL

![PostgreSQL](/images/pages/guides/base-technologies/storage/postgresql.png)

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src="https://onedrive.live.com/embed?cid=2FB293CA43965F14&resid=2FB293CA43965F14%21123&authkey=AOfMEaTkDAm3hZA&em=2" width="402" height="327" frameborder="0" scrolling="no"></iframe>
</div>

## Программное обеспечение

* [Исходные, бинарные коды, Live CD, виртуальные машины](https://www.postgresql.org/download/)
* [Официальный docker-образ](https://hub.docker.com/_/postgres/)

##  Ресурсы

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse1">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse1">
                Видеокурсы</a>
            </h4>
        </div>
        <div id="collapse1" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://www.youtube.com/playlist?list=PLaFqU3KCWw6KzGwUubZm-9-vKsi6vh5qC">DBA1. Администрирование PostgreSQL 9.4 - Тверь 16-18 декабря 2015</a><a href="http://files.postgrespro.ru/departments/edu/www/dba1_student_guide.zip"> (Слайды)</a><i> — youtube-канал «Postgres Professional»</i></li>
                    <li><a href="https://www.youtube.com/playlist?list=PLaFqU3KCWw6JgufXBiW4dEB2-tDpmOXPH">DBA 2. «Администрирование PostgreSQL 9.5. Расширенный курс»</a><i> — youtube-канал «Postgres Professional»</i></li>
                     <li><a href="https://youtu.be/ejLzS6rVpkk">Базы данных: PostgreSQL - Владимир Бородин</a><i> — youtube-канал «Системное администрирование, безопасность, сети»</i></li>
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
                Рекомендованные книги</a>
            </h4>
        </div>
        <div id="collapse2" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="https://postgrespro.ru/media/2016/07/14/PostgreSQL%20for%20Beginners%20v2i.pdf">PostgreSQL для начинающих</a><i> — PostgresPRO</i></li>
                    <li><a href="http://files.postgrespro.ru/departments/edu/www/dba1_student_guide.zip">Документация по PostgreSQL 9.6.1</a><i> — PostgresPRO</i></li>
                     <li><a href="http://www.tutorialspoint.com/postgresql/">PostgreSQL Tutorial</a><i> — tutorialspoint.сom</i></li>
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
                Возможности по сертификации</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                  <li><a href="http://academy.ru/catalog/postgresql/DBA1.html">Администрирование PostgreSQL 9.4. Базовый курс</a><i> — СЕТЕВАЯ АКАДЕМИЯ ЛАНИТ</i></li>
                  <li><a href="https://academy.ru/catalog/postgresql/">Курсы PostgreSQL</a><i> — СЕТЕВАЯ АКАДЕМИЯ ЛАНИТ</i></li>     
                </div>   
            </div>
        </div>
    </div>
</div>

## Лабораторные работы и практические задания

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse4">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse4">
                Варианты заданий для самостоятельного выполнения с последующей проверкой со стороны преподавателя</a>
            </h4>
        </div>
        <div id="collapse4" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse5">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse5">
                                    Вариант №1</a>
                                </h4>
                            </div>
                            <div id="collapse5" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       <p>Разработать базу данных для автоматизации работы оптового склада. Система должна содержать информацию о местах хранения склада, о товаре, о покупках и продажах товара. Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.</p>
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
                                    Вариант №2</a>
                                </h4>
                            </div>
                            <div id="collapse6" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Разработать базу данных для файлового хранилища интернет-проекта. Файловый архив содержит музыку, фильмы и книги в различных форматах. Для доступа к архиву необходима регистрация, скачать файл можно только за определенную плату. Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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
                                    Вариант №3</a>
                                </h4>
                            </div>
                            <div id="collapse7" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему магазина по продаже мобильных телефонов, база данных которого должна содержать такую информацию:
                                       <li>информация про телефон: фирма-производитель, модель телефона, цвет корпуса, цена продажи, количество, поставщик, закупочная цена;</li>
                                       <li>информация о сотрудниках магазина: фамилия, имя, отчество, должность, домашний адрес, телефон;</li>
                                       <li>данные по продажам: что, в каком количестве и кем из сотрудников продано;</li>
                                       <li>информация про поставщиков: название компании, юридический адрес, телефон, факс.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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
                                    Вариант №4</a>
                                </h4>
                            </div>
                            <div id="collapse8" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для молочного киоска, база данных которого должна содержать такую информацию: 
                                       <li>информация о товаре: наименование товара, фирма-производитель, срок годности, цена розничная, количество, поставщик, закупочная цена;</li>
                                       <li>информация о сотрудниках: фамилия, имя, отчество, должность, домашний адрес, телефон;</li>
                                       <li>данные по продажам: что, в каком количестве и кем из сотрудников продано;</li>
                                       <li>информация про поставщиков: название компании, юридический адрес, телефон.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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
                                    Вариант №5</a>
                                </h4>
                            </div>
                            <div id="collapse9" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для автостоянки, база данных которой должна содержать такую информацию:
                                       <li>информация о местах: номер места, владелец, автомобиль, навес (есть/нет);</li>
                                       <li>информация о владельцах: ФИО, адрес, телефон;</li>
                                       <li>информация о машинах: марка, номер, цвет, год выпуска;</li>
                                       <li>информация об оплате: номер места, начисленная сумма, задолженность.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse10">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse10">
                                    Вариант №6</a>
                                </h4>
                            </div>
                            <div id="collapse10" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для фирмы оказывающей риэлтерские услуги, база данных которой должна содержать такую информацию:
                                       <li>информация о недвижимости: тип (дом/квартира), адрес, владелец, цена;</li>
                                       <li>информация о квартирах: этажность дома, проект дома, этаж, жилая площадь, количество комнат, состояние (по шкале 0-10);</li>
                                       <li>информация о домах: площадь участка, количество этажей, жилая площадь, количество комнат, состояние;</li>
                                       <li>информация о владельцах: ФИО, ИНН, адрес, телефон, продаваемая недвижимость;</li>
                                       <li>информация о покупателях: ФИО, ИНН, адрес, телефон, желаемый тип недвижимости, максимальная сумма.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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
                                    Вариант №7</a>
                                </h4>
                            </div>
                            <div id="collapse11" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для фирмы оказывающей услуги по ремонту бытовой техники, база данных которой должна содержать такую информацию:
                                       <li>информация о технике на ремонте: категория, наименование, неисправность, мастер, дата выполнения заказа, стоимость ремонта, номер заказа;</li>
                                       <li>информация о мастерах: ФИО, адрес, вид ремонта, количество заказов;</li>
                                       <li>информация о владельцах: ФИО, адрес, телефон, номер заказа;</li>
                                       информация о видах ремонта.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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
                                    Вариант №8</a>
                                </h4>
                            </div>
                            <div id="collapse12" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для автобусного парка, база данных которого должна содержать такую информацию:
                                       <li>информация об автобусах: модель, балансовая стоимость, номера, номер маршрута;</li>
                                       <li>информация о водителях: ФИО, ИНН, адрес, телефон, автобус;</li>
                                       <li>расписание: номер маршрута, время, остановка;</li>
                                       <li>информация на станции техобслуживания: модель, зап.часть.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse13">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse13">
                                    Вариант №9</a>
                                </h4>
                            </div>
                            <div id="collapse13" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для видеопроката, база данных которого должна содержать такую информацию:
                                       <li>информация о фильмах: название, год выпуска, режиссер, актеры, жанр, количество прокатов;</li>
                                       <li>информация о ценах: фильм, стоимость проката;</li>
                                       <li>информация о прокате: клиент, залог, фильм, дата начала проката, дата конца проката.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse14">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse14">
                                    Вариант №10</a>
                                </h4>
                            </div>
                            <div id="collapse14" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                       Необходимо разработать информационную систему для проката автомобилей, база данных которой должна содержать такую информацию:
                                       <li>информация об автомобилях: марка, номер, год выпуска, вид топлива, расход л/час, остаточная стоимость, цена проката в час;</li>
                                       <li>информация о клиентах: клиент, автомобиль, дата начала проката, дата конца проката, залог;</li>
                                       <li>информация о клиенте: ФИО, ИНН, адрес, телефон, паспорт.</li>
                                       Кроме того, структура базы данных должна предоставлять возможность хранить другую информацию, которая, по мнению студента, относится к данной предметной области и задачам, решаемым разрабатываемой системой.
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

## Перейти

* [MongoDB](gbt_mongodb.html)
* [Главная страница курса](gbt_landing-page.html)

