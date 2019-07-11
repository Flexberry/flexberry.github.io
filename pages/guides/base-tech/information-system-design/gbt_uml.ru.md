---
title: UML
keywords: Programming
sidebar: guide-base-tech_sidebar
toc: true
permalink: ru/gbt_uml.html
folder: guides/base-tech/information-system-design/
lang: ru
---

## Краткое описание

UML (англ. Unified Modeling Language — унифицированный язык моделирования) — язык графического описания для объектного моделирования в области разработки программного обеспечения, моделирования бизнес-процессов, системного проектирования и отображения организационных структур.

## Примеры использования

### Пример диаграммы прецедентов (Use Case):
![UseCase](/images/pages/guides/base-technologies/information-system-design/use-case.png)

### Пример диаграммы последовательностей:
![Sequence-diagram](/images/pages/guides/base-technologies/information-system-design/Sequence-diagram.png)

### Пример диаграммы классов:
![UML-class-diagram](/images/pages/guides/base-technologies/information-system-design/UML-class-diagram.png)

## «Для тех, кто предпочитает один раз увидеть»

<div class="thumb-wrap" style="margin-top: 20px; margin-bottom: 20px">
    <iframe src='https://onedrive.live.com/embed?cid=043A2F24ADFAA4FD&resid=43A2F24ADFAA4FD%21110&authkey=&em=2&wdAr=1.3333333333333332' width='610px' height='481px' frameborder='0'>This is an embedded <a target='_blank' href='https://office.com'>Microsoft Office</a> presentation, powered by <a target='_blank' href='https://office.com/webapps'>Office Online</a>.</iframe>
</div>

## Программное обеспечение

* [Flexberry Designer](http://flexberry.ru/Flexberry/ForDevelopers/FlexberryDesigner)
* [Перечень UML-инструментов - Википедия](https://en.wikipedia.org/wiki/List_of_Unified_Modeling_Language_tools)

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
                    <li><a href="http://www.intuit.ru/studies/courses/1007/229/info"> Курс «Введение в UML»</a><i> - ИНТУИТ</i></li>
                    <li><a href="http://www.intuit.ru/studies/courses/32/32/info"> Курс «Нотация и семантика языка UML»</a><i> - ИНТУИТ</i></li>
                    <li><a href="http://uml.org"> UML Standarts</a><i> - uml.org</i></li>
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
                    <li><a href="http://www.ozon.ru/context/detail/id/2260613/"> UML Основы</a><i> - ozon.ru</i></li>
                    <li><a href="http://www.ozon.ru/context/detail/id/3105480/"> Применение UML и шаблонов проектирования</a><i> - ozon.ru</i></li>
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
                Примеры</a>
            </h4>
        </div>
        <div id="collapse3" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <li><a href="http://www.uml2.ru/forum/index.php?board=3.0"> Задачи студентов</a><i> - uml2.ru</i></li>
                </div>   
            </div>
        </div>
    </div>
</div>

## Лабораторные задания

<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse22">&#9660;</a>
            <h4 class="panel-title">
                <a data-toggle="collapse" href="#collapse22">
                Варианты заданий для самостоятельного создания UML-диаграмм с последующей проверкой со стороны преподавателя</a>
            </h4>
        </div>
        <div id="collapse22" class="panel-collapse collapse">
            <div class="panel-body">
                <div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse4">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse4">
                                    Вариант №1</a>
                                </h4>
                            </div>
                            <div id="collapse4" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        <p>Имеется несколько складов. Для каждого склада известен владелец и название. На каждом складе хранятся товары.
                                        Одинаковые товары могут храниться на разных складах. Некоторые склады могут временно пустовать. 
                                        Известна вместимость каждого склада в тоннах. Складов без владельцев не бывает.
                                        О каждом товаре известно его наименование, уникальный номер-артикул.</p>
                                        <p>Товары на склады привозятся на автомашинах. О каждой автомашине известна её марка, грузоподъемность в тоннах 
                                        и фамилия владельца. Машин без владельцев не бывает. Имеется информация о поступлениях, показывающая какая 
                                        машина какой товар на какой склад привозит в каком количестве (в тоннах).</p>
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
                                    Вариант №2</a>
                                </h4>
                            </div>
                            <div id="collapse5" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Предметной областью является институт, в котором студенты изучают разные дисциплины у разных преподавателей. О каждом студенте известны фамилия, группа, факультет, дата рождения. О каждом преподавателе известны фамилия, кафедра, стаж работы и дата рождения. О каждой дисциплине известны название, количество семестров ее изучения. Имеются ведомости, в которых конкретный преподаватель выставляет оценку (2, 3, 4, 5) студентам группы по конкретной дисциплине. В институте принято положение, когда один преподаватель может читать насколько дисциплин и дисциплина может читаться несколькими преподавателями.
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
                                    Вариант №3</a>
                                </h4>
                            </div>
                            <div id="collapse6" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Есть несколько таблиц, в которых хранятся списки книг, написанных конкретным автором. В каждой таблице хранятся книги одного автора. Известны фамилии авторов. О каждой книге известно название, издательство и количество страниц. Если книгу написали несколько авторов, то она хранится в таблице каждого из авторов.
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
                                    Вариант №4</a>
                                </h4>
                            </div>
                            <div id="collapse7" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Авиакомпания хочет получать ответы на подобные вопросы о своих самолетах: Сколько посадочных мест в Боинге 727? Сколько у него двигателей? Какой средний возраст Боингов 746 нашего авиапарка? Кто главный механик, ответственный за обслуживание самолета номер 1388? Какая компания создала этот самолет?
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
                                    Вариант №5</a>
                                </h4>
                            </div>
                            <div id="collapse8" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Администрация города N хочет получать ответы на вопросы: Какой максимальный объем памяти возможен у IBM PC, Macintosh II, Pentium I,II,III? У кого из служащих есть в кабинете компьютер? У кого стоит компьютер с серийным номером 4538842? Какова его оперативная память и ёмкость винчестера?
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
                                    Вариант №6</a>
                                </h4>
                            </div>
                            <div id="collapse9" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Студент изучает несколько предметов, о каждом из которых известно название, количество часов изучения, номера семестров, когда изучался предмет. У студента есть несколько общих тетрадей, в которых он пишет конспекты лекций. В одной тетради могут быть написаны конспекты по разным предметам и в разных тетрадях - по одному предмету. О каждой тетради известно её название (надпись на обложке (уникальная)), цвет обложки, количество лекций по каждому предмету, записанных в тетради.
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
                                    Вариант №7</a>
                                </h4>
                            </div>
                            <div id="collapse10" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        <p>В электронном магазине имеется информация о складе, где хранятся товары. О каждом товаре известно его название, количество на складе и суммарная стоимость всего товара. При поступлении товара поступившее количество добавляется к имеющемуся и их стоимости суммируются. Продажная цена товара определяется делением суммарной стоимости на его количество.</p>
                                        <p>Ведётся ежемесячный учёт продаж каждого товара: название, сколько продано и по какой цене. При продаже корректируется количество товара и его стоимость.</p>
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
                                    Вариант №8</a>
                                </h4>
                            </div>
                            <div id="collapse11" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Имеется информация о сотрудниках некоторой фирмы. Фирма состоит из нескольких отделов, каждый из которых имеет своего начальника и нескольких (может быть и ни одного) подчиненных. В фирме работают несколько супружеских пар. В фирме принято условие, что из работающих в ней супругов начальником может быть только жена. Кроме того, для каждого сотрудника известна зарплата, которую он получает и суммарный объём сделок, которые он заключил.
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
                                    Вариант №9</a>
                                </h4>
                            </div>
                            <div id="collapse12" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Банк «Товарищество заслуженных программистов» открывает для клиентов (физических и юридических лиц) текущие и сберегательные счета, выдает кредиты. Текущий счет может быть открыт для использования несколькими клиентами, сберегательный - только для одного. Клиент может иметь несколько текущих и сберегательных счетов.
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
                                    Вариант №10</a>
                                </h4>
                            </div>
                            <div id="collapse13" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        В буфете ресторана имеется несколько шкафов, в которых хранятся столовая и чайная посуда и приборы. В каждом шкафу есть несколько полок, на которых расставляется разная посуда. Каждая полка имеет свой уникальный для каждого шкафа номер. Каждый шкаф имеет название, цвет и известное количество полок. На каждой полке может стоять разная посуда и на разных полках может стоять одинаковая посуда. О посуде известны её название, рисунок-расцветка или его отсутствие, количество посуды данного вида, стоящее на полке.
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
                                    Вариант №11</a>
                                </h4>
                            </div>
                            <div id="collapse14" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Существует фирма, производящая некоторое количество товаров. Фирма разбита на отделы, каждым из которых управляет один начальник. В каждом отделе работают несколько сотрудников, причем в разных отделах сотрудники разные. О фирме известны название, адрес, расчетный счет. О сотруднике известны его фамилия, дата рождения, адрес, служебный телефон, имена детей, пол, профессия, суммарная себестоимость изготовленных сотрудником товаров. О товарах известны название, артикул, сортность, себестоимость, изготовленное количество.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse15">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse15">
                                    Вариант №12</a>
                                </h4>
                            </div>
                            <div id="collapse15" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Лесничество разбито на несколько участков, на которых растут разные породы деревьев. На одном участке могут расти несколько пород, в том числе и таких, которые имеются и на других участках. О каждом участке известны его название, площадь и количество деревьев каждой породы, которые растут здесь. О каждой породе известно её название, средняя высота деревьев, средний диаметр и средний возраст.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse16">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse16">
                                    Вариант №13</a>
                                </h4>
                            </div>
                            <div id="collapse16" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        На ипподроме имеется несколько конюшен лошадей. О каждой конюшне известны клички и общее количество лошадей, в ней имеющихся, работающие в ней жокеи. О каждой лошади известна кличка, в каких состязаниях она участвовала, под управлением какого жокея и какое место заняла. О жокее известна его фамилия и спортивный разряд. Жокей может работать в нескольких конюшнях и управлять несколькими лошадьми. Лошадь всегда управляется только одним жокеем и находится в одной конюшне.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse17">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse17">
                                    Вариант №14</a>
                                </h4>
                            </div>
                            <div id="collapse17" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        В школе имеются несколько классов, в которых установлены разные парты, приобретённые в разных странах. В одном классе могут быть установлены парты, купленные в разных странах. Парты из одной страны могут быть установлены в разных классах. Парты, купленные в одной стране могут быть разных типов. О каждом классе известны его номер, площадь, сколько парт какого типа и какой страны в нем установлены. О парте известны: ее тип, страна изготовитель, цвет, площадь, год выпуска.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse18">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse18">
                                    Вариант №15</a>
                                </h4>
                            </div>
                            <div id="collapse18" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Известна информация о компьютерах разных типов, установленных в разных НИИ. Для каждого компьютера известен тип процессора, объем оперативной памяти и винчестера. Для каждого НИИ известно его название, количество работающих и установленных компьютеров каждого типа.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse19">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse19">
                                    Вариант №16</a>
                                </h4>
                            </div>
                            <div id="collapse19" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Коммерсант покупает товары и распределяет их по складам. При этом учитывается: наименование товара, цена покупки, количество купленного, сумма, сколько на какой склад поступило. Купленные товары продаются в розницу. При этом фиксируется количество проданного, цена продажи, количество проданного, сумма, с какого склада продан товар. На складах товар одного наименования фиксируется один раз.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse20">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse20">
                                    Вариант №17</a>
                                </h4>
                            </div>
                            <div id="collapse20" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Информационная система для интернет-магазина «Шины и диски мира». В магазине принимаются заказы на имеющиеся в наличии шины и диски различных марок и модификаций, при этом оформляется заказ, где указана дата, количество, цена товара, ФИО и телефон заказчика.
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <a class="pull-right spoiler-push" data-toggle="collapse" href="#collapse21">&#9660;</a>
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" href="#collapse21">
                                    Вариант №18</a>
                                </h4>
                            </div>
                            <div id="collapse21" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <div>
                                        Информационная система для системного администратора. В фирме имеется несколько отделов. У каждого отдела есть руководитель. Компьютеры фирмы подключены в локальную сеть. Сеть управляется несколькими серверами. Требуется организовать учет существующих компьютеров в фирме с привязкой к отделам, сотрудникам и пр. Требуется также учитывать основные комплектующие компьютеров.
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

* [Паттерны проектирования](gbt_design-patterns.html)
* [Главная страница курса](gbt_landing-page.html)
