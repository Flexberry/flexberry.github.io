---
title: CASE Insensitive для БД
sidebar: product--sidebar
keywords: Flexberry ORM, For code validate, Public, БД
toc: true
permalink: ru/c-a-s-e--insensitive-for--d-b.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Предназначение:''' краткое описание назначения контрола.
* '''Программная библиотека:''' ICSSoft.STORMNET.Business.dll
* '''[Автодокументация](http://www.google.ru)'''.
* '''[Демонстрационное приложение](https://github.com/Flexberry/FlexberryORM-DemoApp)'''.
* '''[0|Учебный курс]'''.
</td>
</tr></tbody></table></a>
</div>

# CASE Insensitive для БД

В конфигурационном файле нужно указать параметр
```xml
<add key="CaseInsensitive" value="true">
```
для того, чтобы в генерируемом [сервисом данных](data-service.html) запросе применялась функция UPPER для обеих сторон выражения сравнения строк.
