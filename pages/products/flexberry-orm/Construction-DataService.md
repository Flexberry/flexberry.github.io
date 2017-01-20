---
title: Конструирование/получение сервиса данных
sidebar: product--sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/construction--data-service.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Существует несколько способов конструирования и получения [сервиса данных](data-service.html).
</td>
</tr></tbody></table></a>
</div>
# Конструирование/получение сервиса данных
Создать [сервис данных](data-service.html) можно разными способами:

1. Сконструировать [сервис данных](data-service.html)
```cs
IDataService ds = new ODBCDataService();			
ds.CustomizationString="DSN=LibNetSample";
```
2. В WinForms приложениях можно «бросить» [сервис данных](data-service.html) на форму как контрол, затем настроить его через стандартное окно редактирования свойств в среде Visual Studio.

3. [Получить его у провайдера сервиса данных](data-service-provider-data-service.html).
```cs
IDataService ds = DataServiceProvider.DataService;
```
