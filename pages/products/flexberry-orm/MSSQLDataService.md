---
title: MSSQLDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_m-s-s-q-l-data-service.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.MSSQLDataService
* '''Предназначение''': [Сервис данных](data-service.html) для работы с MS SQL Server, является реализацией [абстрактного класса SQLDataService](s-q-l-data-service.html).
</td>
</tr></tbody></table></a>
</div>

# MSSQLDataService
MSSQLDataService - это [сервис данных](data-service.html) для работы с MS SQL Server напрямую, минуя ODBC, является реализацией [абстрактного класса SQLDataService](s-q-l-data-service.html).

При указании MSSQLDataService в качестве сервиса данных используется строка "`ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.MSSQLDataService`".

# Особенности использования
* MSSQLDataService требует наличия SQL Client, однако скорость работы возрастает приблизительно на 25%.
* MSSQLDataService [особым образом обрабатывает значения NULL и string.Empty](m-s-s-q-l-data-service-string-null-or-empty.html).

''См. также статью [Обработка регистров в именах объектов для СУБД](processing-registers-names-for-objects-d-b-m-s.html).''


