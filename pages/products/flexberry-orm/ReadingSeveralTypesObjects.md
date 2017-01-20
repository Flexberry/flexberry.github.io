---
title: Чтение принадлежащих различным классам объектов в одном представлении
sidebar: product--sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/reading-several-types-objects.html
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
* '''Предназначение''': Описано применение настроек `[LoadingCustomizationStruct](loading-customization-struct.html)` для чтения принадлежащих различным классам объектов, имеющим общее представление.
</td>
</tr></tbody></table></a>
</div>

Для того, чтобы прочитать множество объектов необходимо (как минимум) произвести настройки `[LoadingCustomizationStruct](loading-customization-struct.html)`:
* указать [классы данных](dataobject.html), для которых выполняется чтение (`[LoadingTypes](loading-customization-struct.html)`);
* указать общее для них [представление](view-definition.html) (`[View](loading-customization-struct.html)`).

Таким образом, если имеется ситуация вида:
![](/images/pages/img/Учебник программиста Casseberry/primer8.jpg)
и мы хотим прочитать все экземпляры CDDD и CDDA в представлении «Главное» для Ресурса, то `[LoadingCustomizationStruct](loading-customization-struct.html)` параметризуется следующим образом:
```cs
lcs = new LoadingCustomizationStruct(null);			
lcs.View=Information.GetView("Главное", typeof(Ресурс));
lcs.LoadingTypes=new Type[]{typeof(CDDD), typeof(CDDA)};
```
Затем, необходимо вызвать метод `[DataService#LoadObjectsLCS|LoadObjects(lcs)]`.

Пример доступен по адресу: <https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs>.
