---
title: Хранение объектных данных в реляционной базе данных
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/data-objects-and-database-structures.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.DataObject.dll, ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Описаны основные принципы отображения объектной модели на реляционную структуру.
</td>
</tr></tbody></table></a>
</div>

Большинство [сервисов данных](data-service.html) обеспечивают работу с реляционным хранилищем.
Объектные данные [Flexberry ORM](flexberry-o-r-m.html) хранит следующим образом:


•	Каждый класс — отдельная таблица. 

•	Каждый атрибут — отдельное поле в таблице.

•	[Мастеровые связи](master--association.html) — внешние ключи таблицы внутреннего класса.

•	[Детейловые связи](detail-associations-and-their-properties.html) — внешний ключ детейловой таблицы на агрегатор (шапку).

•	[Наследуемый](inheritance.html) класс — отдельная от предка таблица.

•	Если у [мастерового класса](master--association.html) есть [наследник](inheritance.html), то на его таблицу должен быть внешний ключ в таблице внутреннего класса.

•	Каждая [унаследованная](inheritance.html) таблица хранит все атрибуты всех предков. Таким образом, одному экземпляру класса соответствует одна запись в одной таблице (почему сделано именно так, описано [здесь](inheritance.html)).
