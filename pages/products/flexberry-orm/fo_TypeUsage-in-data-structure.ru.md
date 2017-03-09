---
title: Отображение TypeUsage в структуру данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_type-usage-in-data-structure.html
folder: products/flexberry-orm/
lang: ru
---
# Обработка TypeUsage
Встроенные в `Flexberry Platform` [сервисы данных](fo_data-service.html) (`[SQLDataService](fo_sql-data-service.html)` и его наследники) обрабатывают `[TypeUsage](type-usage-problem.html)` следующим образом:


Если указан `[TypeUsage](type-usage-problem.html)` для [мастерового свойства](master--association.html), этому свойству [в структуре данных соответствуют](fo_data-objects-and-database-structures.html) внешние ключи на таблицы, соответствующие указанным в [TypeUsage](type-usage-problem.html) классам. Имена внешним ключам даются такие: `<ИмяРолиМастера>_M<ПорядкНомерВTypeUsage>.«ПорядкНомерВTypeUsage»` — начинается с 0.


Таким образом, вышеприведённому примеру соответствует таблица, у которой есть два внешних ключа с именами M_m0 (соответствует M1) и M_m1 (соответствует M2).

# Задание имён для полей хранения ссылок
Если есть потребность задать вместо имён M_m0 и M_m1 некоторые мнемонические имена, то необходимо:
# Отключить галочку `[AutoGenerateTypeUsage](master--association.html)`.
# Правильно проставить атрибуты [TypeUsage](type-usage-problem.html) и Storage.

Например, пусть имеется диаграмма вида:


![](/images/pages/img/page/TypeUsage-in-data-structure/TypeUsageTest.png)


Если ничего не менять в настройках элементов диаграммы, то, например, в таблице "`ДниПосещения`" будут внешние ключи с именами "`Пользователь_m0`", "`Пользователь_m1`", `"Пользователь_m2`". 

Однако если у связи, соединяющей классы "`Пользователь`" и "`ДниПосещения`" проставить в TypeUsage и Storage строку вида "`Пользователь,Врач,Пациент`", то в таблице "`ДниПосещения`" будут внешние ключи с именами "`Пользователь`", "`Врач`", "`Пациент`". Аналогично можно настроить [детейловую связь](fo_detail-associations-and-their-properties.html).
----