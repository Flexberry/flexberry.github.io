---
title: Saiku. Бизнес-процесс создания OLAP-куба
keywords: mydoc
sidebar: flexberry-analytics_sidebar
toc: false
permalink: ru/fan_saiku-cube.html
lang: ru
summary:
---

`Saiku Analytics` - это клиент для работы с массивами данных с открытыми исходными кодами. Разрабатывается и поддерживается корпорацией `Pentaho`.

`OLAP-куб` — (_On-Line Analytical Processing_ — интерактивный анализ данных) многомерный массив данных, как правило, разреженный и долговременно хранимый, используемый в OLAP. Может быть реализован на основе универсальных реляционных СУБД или специализированным программным обеспечением. Подробнее в статье [OLAP-куб](https://ru.wikipedia.org/wiki/OLAP-%D0%BA%D1%83%D0%B1).

## Настройка подключения к БД

Первым шагом для создания OLAP куба является подключение источника данных. Для этого необходимо произвести следующие действия:

{% include important.html content="Pentaho не умеет подключаться к базам с русским наименованием БД. Но корректно работает с русскими наименованиями таблиц." %}

1.Авторизоваться под ролью «Администратор»;

2.Открыть «Manage Data Sources» и во всплывающем окне кликнуть на шестеренку ![](/images/pages/products/flexberry-analytics/saiku-cube-settings.png);

3.В выпадающем меню кликнуть на «New connection»;

4.В открывшемся pop up «Database Connection» заполнить поля для подключения к БД:

   * Connection Name;
   * Database Type (при выборе различным типов БД меняются поля для настройки подключения, далее указаны поля для PostgreSQL);
   * Access (по умолчанию Native (JDBC))
   * Host Name;
   * Database Name;
   * Port Number (по умолчанию 5432);
   * User Name;
   * Password

![](/images/pages/products/flexberry-analytics/saiku-cube001.png)
 
5.После заполнения полей кликнуть на кнопку «Test» для проверки соединения;

![](/images/pages/products/flexberry-analytics/saiku-cube002.png)
 
6.Если все корректно отработало, кликнуть по кнопке «OK» для сохранения конфигурации.

## Публикация схемы OLAP куба

Вторым шагом для создания куба является публикация схемы, разработанной в Pentaho Schema Workbrench или вручную.

1.Открыть «Manage Data Sources» и во всплывающем окне кликнуть на шестеренку  ![](/images/pages/products/flexberry-analytics/saiku-cube-settings.png);

2.В выпадающем меню кликнуть на «Import Analysis»;

3.Во всплывающем окне «Import Analysis» нужно заполнить следующие поля:

   * Mondrian File (Выбирается xml файл схемы с директории своего ПК);
   * Выбрать «Select from available data sources»;
   * Data Source (Выбрать из списка подключение к БД, созданное на 1 шаге)

![](/images/pages/products/flexberry-analytics/saiku-cube003.png)
 
4.После заполнения кликнуть на кнопку «Import»;

## Создание куба

1.На главной странице Pentaho кликнуть на кнопку «Create New»;

2.В появившемся списке кликнуть на кнопку «Saiku Analytics»;

![](/images/pages/products/flexberry-analytics/saiku-cube004.png)
 
3.Далее кликнуть на кнопку «Create a new quary»

4.В открывшемся окне справа нужно кликнуть на иконку ![](/images/pages/products/flexberry-analytics/saiku-cube-refresh.png) для обновления данных;

5.Выбрать нужный куб из выпадающего списка;

6.Добавить измерения и меры, отображен результат:

![](/images/pages/products/flexberry-analytics/saiku-cube005.png)
 