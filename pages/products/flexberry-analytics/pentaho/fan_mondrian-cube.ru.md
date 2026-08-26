---
title: Создание Mondrian-схемы для OLAP-куба
keywords: OLAP, куб, измерение, схема, подключение БД
sidebar: flexberry-analytics_sidebar
toc: false
permalink: ru/fan_mondrian-cube.html
lang: ru
summary:
---

`Mondrian` — сервер OLAP (аналитической обработки в реальном времени) с открытыми исходными кодами, написанный на языке `Java`. Разрабатывается и поддерживается корпорацией `Pentaho`.

Для создания схемы потребуется воспользоваться desktop-приложением `Pentaho schema workbench`.

`OLAP-куб` — (_On-Line Analytical Processing_ — интерактивный анализ данных) многомерный массив данных, как правило, разреженный и долговременно хранимый, используемый в OLAP. Может быть реализован на основе универсальных реляционных СУБД или специализированным программным обеспечением. Подробнее в статье [OLAP-куб](https://ru.wikipedia.org/wiki/OLAP-%D0%BA%D1%83%D0%B1).

## Создание схемы

Для создания схемы необходимо выполнить действие: `File -> new -> schema`. В атрибутах новой схемы заполнить `name` (наименование схемы).

Для сохранения схемы необходимо выполнить действие: `File -> save`.

## Настройка подключения к БД

{% include important.html content="Наименование базы данных должно быть только на английском языке." %}

Задается соединение с БД: `Options -> connection`. В появившемся окне в разделе `General` заполнить:

   * Connection Name – Наименование для подключения;
   * Connection Type = Postgre SQL – Тип хранения данных;
   * Access = Native (JDBC) – Тип подключения;
   * Settings:

![](/images/pages/products/flexberry-analytics/mondrian-cube-001.png) 
 

## Создание куба

* В созданной схеме необходимо создать куб (ПКМ по схеме и кликнуть «Add cube»)
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-002.png)

В атрибутах нового куба заполнить:

   * name – наименование куба.
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-003.png)

* Далее необходимо добавить таблица фактов для куба (ПКМ по кубу и кликнуть «Add Table»);
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-004.png)

В атрибутах новой таблицы заполнить:

   * schema - схема БД;
   * name – таблица фактов для куба.

![](/images/pages/products/flexberry-analytics/mondrian-cube-005.png)
 
## Создание Меры

Добавить меру для куба (ПКМ по кубу и выбрать «Add Measure») и заполнить:

![](/images/pages/products/flexberry-analytics/mondrian-cube-006.png)
 
* name – наименование меры (Отображается на веб-интерфейсе);
* aggregator – тип вычисления, который принимает одно из значений:

    * sum;
    * count;
    * min;
    * max;
    * avg;
    * distinct-count.
* column – наименование атрибута таблицы фактов, по которому будет считаться мера.
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-007.png)
 
## Создание Измерения

### Измерение для схемы

Измерение для схемы используется в случаях, когда в рамках одной схемы создается несколько кубов. Для нескольких кубов можно использовать одно измерение схемы. При этом нужно лишь добавить ссылку на это измерение в кубе.

1.Создать новое измерение для схемы (ПКМ по схеме и кликнуть на Dimension).

![](/images/pages/products/flexberry-analytics/mondrian-cube-008.png)
 
Если измерение создано для схемы, а не для куба то из обязательных атрибутов только поле name – имя.
Поле type ¬– тип измерения, заполняется по умолчанию значением StandartDimension, для не временных измерений. Для временных измерений заполняется значением TimeDimension.

![](/images/pages/products/flexberry-analytics/mondrian-cube-009.png)
 
2.При раскрытии измерения появляется иерархия. Обязательные атрибуты для иерархии:

* name – наименование иерархии, 
* primarykey - столбец таблицы измерения, значения которого совпадают с каким – либо столбцом таблиц (ы) фактов (для связи куба и измерения)

![](/images/pages/products/flexberry-analytics/mondrian-cube-010.png)
 
3.Добавить таблицу для измерения (ПКМ по иерархии и кликнуть на Add Table). Среди обязательных атрибутов, уровень имеет следующие:

* schema – схема БД;
* name – наименование таблицы

4.После добавлении таблицы, аналогично нужно добавить хотя бы один уровень (Level). Среди обязательных атрибутов, уровень имеет следующие:

* name – наименование уровня;
* column (столбец, который будет отражаться как измерение в кубе).

![](/images/pages/products/flexberry-analytics/mondrian-cube-011.png)
 
5.Чтобы использовать измерение схемы в кубе, нужно кликнуть ПКМ по нужному кубу и нажать add dimension usage

![](/images/pages/products/flexberry-analytics/mondrian-cube-012.png)
 
Обязательные атрибуты:

* name – наименование измерения в кубе.
* foreign key – столбец таблицы фактов, значения которого должны совпадать с ключом, указанном в иерархиях измерения. 
* source – необходимо выбрать измерение, которое будет использоваться.

Необязательный атрибут:

* caption –  имя для измерения, которое будет отражаться для конкретного  куба.
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-013.png)

### Измерение для куба

1.Чтобы использовать измерение для куба, нужно кликнуть ПКМ по нужному кубу и нажать add dimension
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-014.png)

Если измерение создано для куба, то заполнить следующие поля:

   * name – имя;
   * type ¬– тип измерения, заполняется по умолчанию значением StandartDimension, для не временных измерений. Для временных измерений заполняется значением TimeDimension.
   * foreignKey –  внешний ключ для связи с таблицей фактов куба.
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-015.png)

2.При раскрытии измерения появляется иерархия. Обязательные атрибуты для иерархии:

   * name – наименование иерархии, 
   * primarykey - столбец таблицы измерения, значения которого совпадают с каким – либо столбцом таблиц (ы) фактов (для связи куба и измерения)
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-016.png)

3.Добавить таблицу для измерения (ПКМ по иерархии и кликнуть на Add Table). Среди обязательных атрибутов, уровень имеет следующие:

   * schema – схема БД;
   * name – наименование таблицы

4.После добавлении таблицы, аналогично нужно добавить хотя бы один уровень (Level). Среди обязательных атрибутов, уровень имеет следующие:

   * name – наименование уровня;
   * column (столбец, который будет отражаться как измерение в кубе).
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-017.png)
 

### Измерение JOIN

Используемая диаграмма классов:

![](/images/pages/products/flexberry-analytics/mondrian-cube-018.png)
 
1.Создать Измерение
2.В уже созданной иерархии кликнуть ПКМ и создать Join
3.Заполнить в Join ссылки на таблицы
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-019.png)

![](/images/pages/products/flexberry-analytics/mondrian-cube-020.png)
 
4.В самом Join необходимо указать primarykey и foreignkey

![](/images/pages/products/flexberry-analytics/mondrian-cube-021.png)
 
5.Создать Level:

![](/images/pages/products/flexberry-analytics/mondrian-cube-022.png)
 
6.Заполнить Иерархию для связи с таблицей фактов

![](/images/pages/products/flexberry-analytics/mondrian-cube-023.png)
 
7.В кубе нужно создать Dimension Usage
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-024.png)

Схема куба:

![](/images/pages/products/flexberry-analytics/mondrian-cube-025.png)
 
### Измерение с иерархией

#### Использование Уровней

1.Модель:

![](/images/pages/products/flexberry-analytics/mondrian-cube-026.png)
 
2.Создать новое измерение для схемы или для куба

![](/images/pages/products/flexberry-analytics/mondrian-cube-027.png)
 
Если предполагается работа с датами, то нужно отметить атрибут type = TimeDimension
3.Далее заполнить поля в Иерархии
4.Добавить таблицу для иерархии
5.После создать первый уровень:

   * Заполнить Год:
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-028.png)

{% include important.html content="Для первого уровня требуется отметить галочкой uniqueMembers = true." %}

   * Заполнить Месяц:

![](/images/pages/products/flexberry-analytics/mondrian-cube-029.png)
 
#### Использование Closure Table

0.Модель:

![](/images/pages/products/flexberry-analytics/mondrian-cube-030.png)
 
1.Создать новое измерение для схемы или для куба

![](/images/pages/products/flexberry-analytics/mondrian-cube-031.png)
 
2.Далее заполнить поля в Иерархии

![](/images/pages/products/flexberry-analytics/mondrian-cube-032.png)
 
3.Добавить таблицу для иерархии

![](/images/pages/products/flexberry-analytics/mondrian-cube-033.png) 
 
4.Далее в раскрытой иерархии нужно добавить уровень

![](/images/pages/products/flexberry-analytics/mondrian-cube-034.png)
 
5.В созданном уровне нужно добавить Closure

![](/images/pages/products/flexberry-analytics/mondrian-cube-035.png)
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-036.png)
 
6.Заполнить Closure, для этого нужно выбрать в Table нужную таблицу

![](/images/pages/products/flexberry-analytics/mondrian-cube-037.png)
 
7.Заполнить уровень, для этого необходимо заполнить поля:

   * name – наименование уровня;
   * table – выбрать из выпадающего списка таблицу для измерения;
   * column – атрибут первичного ключа таблицы;
   * nameColumn – атрибут для отображения значения измерения;
   * parentColumn – атрибут для ключа родителя;
   * type – тип данных;
   * uniqueMembers –  отметить галочку для отметки, что данный уровень является первичным по счету;
   * levelType – Regular;
   * hideMemberlf – Never
   * caption – отображаемое имя

![](/images/pages/products/lexberry-analytics/mondrian-cube-038.png)
 
8.Заполнить в Closure атрибуты parentColumn и childColumn значениями из выпадающих списков:

![](/images/pages/products/flexberry-analytics/mondrian-cube-039.png) 

9.Для куба создать ссылку на измерение:

![](/images/pages/products/flexberry-analytics/mondrian-cube-040.png) 

10.Заполнить Dimension Usage:

![](/images/pages/products/flexberry-analytics/mondrian-cube-041.png)

## Создание виртуального Куба

Для отображения нескольких кубов на одной схеме используется функция создания виртуальных кубов. Для этого требуется:

1.Создать как минимум два куба с измерениями и мерами

2.В открытой схеме нужно создать виртуальный куб (ПКМ по схеме и кликнуть `Add virtual cube`)
 
![](/images/pages/products/flexberry-analytics/mondrian-cube-042.png)

3.Заполнить, в появившимся окне, наименование (name) для виртуального куба

4.Добавить в виртуальный куб ссылки на измерения обычных кубов, для этого:
   * Кликнуть ПКМ по виртуальному кубу и выбрать `Add virtual cube dimension`

![](/images/pages/products/flexberry-analytics/mondrian-cube-043.png)

   * В появившемся окне виртуального измерения заполнить атрибуты: name и cubeName в точности, как они называются в исходном кубе

![](/images/pages/products/flexberry-analytics/mondrian-cube-044.png) 

5.Добавить в виртуальный куб ссылки на меры обычных кубов, для этого:
   * Кликнуть ПКМ по виртуальному кубу и выбрать `Add virtual cube measure`

![](/images/pages/products/flexberry-analytics/mondrian-cube-045.png)

   * В появившемся окне виртуального измерения заполнить атрибуты: name и cubeName в точности, как они называются в исходном кубе

{% include important.html content=" Параметр name заполнять в формате: [Measures].[<Наименование меры>]." %}

![](/images/pages/products/flexberry-analytics/mondrian-cube-046.png)
