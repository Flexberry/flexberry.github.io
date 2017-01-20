---
title: OracleDataService
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/oracle-data-service.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.OracleDataService
* '''Предназначение''': [Сервис данных](data-service.html) для работы с Oracle Server, является реализацией [абстрактного класса SQLDataService](s-q-l-data-service.html).
</td>
</tr></tbody></table></a>
</div>

# OracleDataService
OracleDataService- это [сервис данных](data-service.html) для работы с Oracle Server напрямую, минуя ODBC; является реализацией [абстрактного класса SQLDataService](s-q-l-data-service.html).

При указании OracleDataService в качестве сервиса данных используется строка "`ICSSoft.STORMNET.Business.OracleDataService, ICSSoft.STORMNET.Business.OracleDataService`".

# Особенности использования
* [OracleDataService требует наличия клиентского ПО Oracle.](minimum-required-client-set-for--oracle--data--service.html)
Существуют ограничения на именование каталогов, в которых размещается ПО, использующее ORACLE. Если ПО разместить в каталоге, в наименовании которого присутствуют спецсимволы (точки, скобки), то всё может перестать работать. Для исправления этой ситуации достаточно переименовать каталог.
