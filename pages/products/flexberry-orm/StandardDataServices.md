---
title: Стандартные сервисы данных Flexberry ORM
sidebar: product--sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/standard-data-services.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll, ICSSoft.STORMNET.Business.DRDataService.dll, ICSSoft.STORMNET.Business.MSSQLDataService, ICSSoft.STORMNET.Business.ODBCDataService, ICSSoft.STORMNET.Business.OracleDataService, ICSSoft.STORMNET.Business.PostgresDataService
* '''Предназначение''': Описание сервисов данных, поставляемых в [NuGet-пакете Flexberry ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM) для различных источников данных.
</td>
</tr></tbody></table></a>
</div>
# Иерархия наследования сервисов данных
![](/images/pages/img/page/StandardDataServices/IDataServiceInheritance.png)

# Описание стандартных сервисов данных
Существуют следующие [сервисы данных](data-service.html):
* `[ICSSoft.STORMNET.Business.XMLFileDataService](x-m-l-file-data-service.html)` для доступа к данным в формате `XML`-файлов.
* `[ICSSoft.STORMNET.Business.SQLDataService](s-q-l-data-service.html)` — базовый предок всех сервисов данных, работающих с реляционными источниками.
* `[ICSSoft.STORMNET.Business.MSSQLDataService](m-s-s-q-l-data-service.html)` — сервис данных для доступа к `MS SQL Server` напрямую, минуя `ODBC`. Требует наличия `SQL Client`, однако скорость работы возрастает приблизительно на 25%.
* `[ICSSoft.STORMNET.Business.DRDataService](d-r-data-service.html)` — сервис данных для `MS SQL Server`, реализующий "грязное чтение" данных (`WITH (NOLOCK)`).
* `[ICSSoft.STORMNET.Business.OracleDataService](oracle-data-service.html)` — сервис данных для доступа к `Oracle` напрямую, минуя `ODBC`. Требует наличия [клиентского ПО Oracle](minimum-required-client-set-for--oracle--data--service.html).
* `[ICSSoft.STORMNET.Business.PostgresDataService](postgres-data-service.html)`
* `[ICSSoft.STORMNET.Business.ODBCDataService.ODBCDataService](o-d-b-c-data-service.html)` для доступа к реляционным источникам данных. Независим от реляционной СУБД, важно использование стандартов `ANSI` и `ODBC`.
* `[ICSSoft.STORMNET.Business.ODBCDataService.ODBCAccessDataService](o-d-b-c-access-data-service.html)` — `[ODBCDataService](o-d-b-c-data-service.html)`, оптимизированный для доступа к данным `СУБД Access`.'''Использование Бд Access возможно только в х86-версии!'''
* `[ICSSoft.STORMNET.Business.ODBCDataService.ODBCMySqlDataService](o-d-b-c-my-sql-data-service.html)` — `[ODBCDataService](o-d-b-c-data-service.html)`, оптимизированный для доступа к данным `СУБД MySQL` (версия 4.1 и более поздние, с некоторыми ограничениями на объектную структуру).

