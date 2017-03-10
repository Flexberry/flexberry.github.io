---
title: Хранение объектных данных в реляционной базе данных
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_data-objects-and-database-structures.html
---
<<<<<<< HEAD

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](fo_flexberry-orm.html)
* '''Компонент''': [Сервис данных](fo_data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.DataObject.dll, ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Описаны основные принципы отображения объектной модели на реляционную структуру.
</td>
</tr></tbody></table></a>
</div>

Большинство [сервисов данных](fo_data-service.html) обеспечивают работу с реляционным хранилищем.
Объектные данные [Flexberry ORM](fo_flexberry-orm.html) хранит следующим образом:


•	Каждый класс — отдельная таблица. 

•	Каждый атрибут — отдельное поле в таблице.

•	[Мастеровые связи](fo_master-association.html) — внешние ключи таблицы внутреннего класса.

•	[Детейловые связи](fo_detail-associations-and-their-properties.html) — внешний ключ детейловой таблицы на агрегатор (шапку).

•	[Наследуемый](fo_inheritance.html) класс — отдельная от предка таблица.

•	Если у [мастерового класса](fo_master-association.html) есть [наследник](fo_inheritance.html), то на его таблицу должен быть внешний ключ в таблице внутреннего класса.

•	Каждая [унаследованная](fo_inheritance.html) таблица хранит все атрибуты всех предков. Таким образом, одному экземпляру класса соответствует одна запись в одной таблице (почему сделано именно так, описано [здесь](fo_inheritance.html)).
=======
* **Продукт:** [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент:** [Сервис данных](fo_data-service.html)
* **Программная библиотека:** ICSSoft.STORMNET.DataObject.dll, ICSSoft.STORMNET.Business.dll
* **Предназначение:** Описаны основные принципы отображения объектной модели на реляционную структуру.

Большинство [сервисов данных](fo_data-service.html) обеспечивают работу с реляционным хранилищем.
Объектные данные [Flexberry ORM](fo_flexberry-o-r-m.html) хранит следующим образом:

* Каждый класс — отдельная таблица. 
* Каждый атрибут — отдельное поле в таблице.
* [Мастеровые связи](fd_master-association.html) — внешние ключи таблицы внутреннего класса.
* [Детейловые связи](fo_detail-associations-and-their-properties.html) — внешний ключ детейловой таблицы на агрегатор (шапку).
* [Наследуемый](fo_inheritance.html) класс — отдельная от предка таблица.
* Если у [мастерового класса](fd_master-association.html) есть [наследник](fo_inheritance.html), то на его таблицу должен быть внешний ключ в таблице внутреннего класса.
* Каждая [унаследованная](fo_inheritance.html) таблица хранит все атрибуты всех предков. Таким образом, одному экземпляру класса соответствует одна запись в одной таблице (почему сделано именно так, описано [здесь](fo_inheritance.html)).
>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f
