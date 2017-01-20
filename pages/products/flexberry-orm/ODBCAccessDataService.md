---
title: ODBCAccessDataService
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_o-d-b-c-access-data-service.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.ODBCDataService
* '''Предназначение''': [ODBCDataService](o-d-b-c-data-service.html), оптимизированный для доступа к данным СУБД Access.
</td>
</tr></tbody></table></a>
</div>

# ODBCAccessDataService
ODBCAccessDataService - это [сервис данных](data-service.html), представляющий реализацию [ODBCDataService](o-d-b-c-data-service.html), оптимизированный для доступа к данным СУБД Access.

При указании ODBCAccessDataService в качестве сервиса данных используется строка "`ICSSoft.STORMNET.Business.ODBCAccessDataService, ICSSoft.STORMNET.Business.ODBCDataService`".

# Особенности использования
* В качестве строки подключения ODBCAccessDataService используются строки вида "`Driver={Microsoft Access Driver (*.mdb)};Dbq=D:\Temp\Flexberry.mdb;`" (в данном примере подключение будет производиться к БД "Flexberry.mdb", расположенной по пути "D:\Temp\").
* Из-за версии используемого драйвера, работа с ODBCAccessDataService возможно только в приложениях, скомпилированных под x86.
