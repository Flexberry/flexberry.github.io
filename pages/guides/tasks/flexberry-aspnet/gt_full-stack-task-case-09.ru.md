---
title: Вариант 09 - «Дошкольное образование»
keywords: Tasks
sidebar: guide-tasks_sidebar
toc: true
permalink: ru/gt_full-stack-task-case-09.html
folder: guides/tasks/
lang: ru
---

## Задание

В рамках выполнения практической части курса вами будет разработан сквозной пример: приложение «Дошкольное образование».

Первая часть практического задания будет посвящена освоению базовых технологий, таких как C#, базы данных, клиентские технологии и т.п., вторая часть будет включать изучение возможностей платформы Flexberry для эффективного создания приложений.

## Общее описание предметной области

Требуется создать систему, учитывающую движение контингента учащихся по образовательным организациям дошкольного образования. Известно, что дети учатся в группах, каждая группа находится в какой-либо образовательной организации.

Каждая образовательная организация имеет свой тип (муниципальная, государственная, частная, ИП), и, возможно, является структурным подразделением другой образовательной организации.
Система должна хранить сведения о заявлениях на перевод или поступление в образовательную организацию. 

Технические требования:

*	Приложение реализуется в виде ASP.NET WebForms-приложения.
*	Данные хранятся в БД MSSQL Server.

## Практическое задание №1 - Серверная разработка (C#, .NET, ASP.NET)

Для реализации потребуется:

* Microsoft Visual Studio 2015
* Git for Windows

### Задание. Часть 1.

Требуется реализовать алгоритм расчёта стоимости годового посещения для указанного ребёнка. Стоимость посещения зависит от стоимости одного занятия в дошкольном учреждении для указанной группы. Также требуется учитывать отсутствие ребёнка по уважительной причине (такие занятия не оплачиваются). Дети с льготной категорией имеют скидку 50%. На вход алгоритму подаются параметры, влияющие на стоимость, а на выходе имеется стоимость годового посещения.

Реализацию следует сделать в виде .net-библиотеки и подготовить модульные тесты для неё, продемонстрировать процент покрытия кода модульными тестами (чем больше, тем лучше).

### Задание. Часть 2.

Реализовать простое ASP.NET WebForms-приложение (на данном этапе без БД), которое содержит компоненты (ascx-контролы), реализующиие:

1. Логику для ввода параметров, необходимых для расчёта стоимости годового посещения ребёнка, собственно использование библиотеки расчёта и вывод результата.
2. Локигу для отображения календаря посещения занятий ребёнком (цветом выделяются даты, в которые будет проводится занятие).

### Предоставление результатов выполнения работы на проверку

Реализованное решение (Visual Studio Solution) полностью разместить в репозитории на GitHub (решение должно компилироваться и запускаться). Ссылку на репозиторий предоставить преподавателям курса.

## Практическое задание №2 - Клиентская разработка (HTML, CSS, JS, jQuery)

Для реализации потребуется:

* Редактор кода для клиентской разработки: Visual Studio Code, Atom, Sublime Text и т.п.
* Git for Windows

### Задание

С использованием возможностей HTML, CSS, JS, jQuery сверстать форму, на которой будет отображаться распорядок дня ребенка в детском саду с учетом временного вектора. Каждому этапу должен соответствовать графический элемент, отображающий так же его длительность. Таким образом, на экране сформируется «линия занятости» ребенка. Длительность некоторых этапов может быть изменена путем изменения границ объекта, отображающего определенный этап. На форме также должен быть блок с информацией о номере группы, воспитателе, числу детей в группе, число отсутствующих по болезни. Необходимо реализовать объект позволяющим родителям делать выбор (например, при клике на элемент «обучение» становится доступен список возможных тем или курсов, при клике на элемент «обед» - меню).

### Предоставление результатов

Реализованный проект разместить в репозитории на GitHub в виде встроенных страниц [GitHub Pages](https://pages.github.com/), позволяющих просматривать готовый результат. Ссылку на репозиторий и опубликованный таким образом проект предоставить преподавателям курса.

## Практическое задание №3 - Базы данных

Для реализации потребуется:

*	Microsoft SQL Server
*	Microsoft SQL Server Management Studio
*	Git for Windows

### Задание

Для указанной предметной области реализовать базу данных, заполнить базу скриптами (минимум по 5 записей на таблицу). Реализовать скрипты по созданию констрейнтов, индексов. Приложить скрипты создания БД и заполнения, бакап БД.

Подготовить SQL-скрипты для получения следующей информации:

1. Вывести список детей, оплата детского сада которых была максимальна за весь период их посещения (вне зависимости от детского сада).
2. Вывести топ N детей с наибольшим количеством переходов по детским садам за весь период.
3. Вывести детские сады, в которых находилось меньше всего детей в прошлом от текущего года. 
4. Проранжировать детские сады по средней цене обучения за текущий год. 
5. Вывести сумму оплаты за обучения для каждого ребенка по годам, с указанием детского сада и группы. 
6. Вывести детские сады в которых имели место прецеденты с обработкой заявления дольше чем N дней.

### Предоставление результатов

Реализованные скрипты закоммитить в GitHub-репозиторий. Ссылку на репозиторий предоставить преподавателям курса.

## Практическое задание №4 - Проектирование ИС

Для реализации потребуется:

*	Flexberry Designer

### Задание

Нарисовать UML-диаграммы для обозначенной предметной области. Состав диаграмм определяется самим слушателем курсов, но должен обеспечивать полноценное описание предметной области.

### Предоставление результатов

Результатом работы является выгруженная в формате CRP стадия из Flexberry Designer. Стадию (файл с расширением *.CRP) следует закоммитить в репозиторий на GitHub, ссылку предоставить преподавателям курса.

## Практическое задание №5 - Объектный дизайн и генерация приложений

Для реализации потребуется:

*	Flexberry Designer
*	Microsoft Visual Studio 2015
*	Microsoft SQL Server
*	Git for Windows

### Задание

Выполнить объектный дизайн и генерацию ASP.NET-приложения для описанной предметной области. В качестве основы использовать реализованные ранее UML-модели. Генерация приложения и БД должна быть выполнена средствами Flexberry Designer приложение должно соответствовать требованиям и быть полностью работоспособным. Представления должны быть качественно настроены, подписи к классам и атрибутам на формах должны быть адекватными. Перечень форм и атрибутивный состав должны соответствовать предметной области и покрывать все требуемые бизнес-функции.

### Предоставление результатов

Сгенерированное приложение и скрипты создания БД следует выложить в репозиторий на GitHub. Предоставить преподавателям ссылку на репозиторий.

## Практическое задание №6 - Разработка бизнес-логики приложений

Для реализации потребуется:

* Flexberry Designer
* Microsoft Visual Studio 2015
* Microsoft SQL Server
* Git for Windows

### Задание

В сгенерированном при помощи Flexberry Designer приложении требуется реализовать следующую бизнес-логику.

1. Реализовать бизнес-сервер, который будет отслеживать, что перевод ребенка в другую группу или детский сад производится только при наличии заявления на перевод. Без заявления перевод не допускается. 
2. Реализовать бизнес-сервер, который будет использовать .net-библиотеку для вывода на странице суммарной платы за посещение детских садов.
3. Реализовать бизнес-сервер, который будет проверять, что ребенок в момент времени принадлежит только одной группе, одного детского сада.
4. Добавить хранимое поле в класс группы, которое будет содержать количество детей в данной группе. Перевычисление этого поля реализовать в бизнес-сервере.
5. Добавить не хранимое поле в класс группы, которое будет составлять строку, содержащую название детского сада, название группы, количество детей.

### Предоставление результатов

Доработанная бизнес-логика должна быть включена в разрабатываемое приложение и закоммичена в соответствующий репозиторий. Ссылка на репозиторий предоставляется для проверки преподавателям курса.

## Практическое задание №7 - Разработка UI-логики приложения

Для реализации потребуется:

*	Microsoft Visual Studio 2015
*	Microsoft SQL Server
*	Редактор кода для клиентской разработки: Visual Studio Code, Atom, Sublime Text и т.п.
*	Git for Windows

### Задание


### Предоставление результатов

Доработанная UI-логика должна быть включена в разрабатываемое приложение и закоммичена в соответствующий репозиторий. Ссылка на репозиторий предоставляется для проверки преподавателям курса.

## Практическое задание №8 - Функциональные подсистемы Flexberry

Для реализации потребуется:

*	Flexberry Designer
*	Microsoft Visual Studio 2015
*	Microsoft SQL Server
*	Git for Windows

### Задание

Для реализованного приложения настроить подсистему полномочий. Пользователи должны заводиться администратором приложения. Авторизация на основе форм. Создать иерархию ролей, добавить операции на просмотр данных и выдать права только определённым пользователям. Настроить несколько ролей и назначить эти роли пользователям.
Настроить подсистему аудита. В разрабатываемом приложении все изменения объектов данных должно фиксироваться в подсистеме аудита. В навигационное меню следует добавить формы аудита, которые должны показываться только администраторам.

### Предоставление результатов

Доработанное приложение должно быть закоммичено в соответствующий репозиторий. Дополнительно в репозиторий должны быть добавлены SQL-скрипты, которые нужно выполнить для функционирования приложения с подсистемой полномочий и аудита. Ссылка на репозиторий предоставляется для проверки преподавателям курса.