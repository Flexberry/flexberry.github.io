---
title: Чтение принадлежащих различным классам объектов в одном представлении
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_reading-several-types-objects.html
folder: products/flexberry-orm/
lang: ru
---

Для того, чтобы прочитать множество объектов необходимо (как минимум) произвести настройки `[LoadingCustomizationStruct](fo_loading-customization-struct.html)`:

* указать [классы данных](fo_dataobject.html), для которых выполняется чтение (`[LoadingTypes](fo_loading-customization-struct.html)`);
* указать общее для них [представление](fo_view-def.html) (`[View](fo_loading-customization-struct.html)`).

Таким образом, если имеется ситуация вида:

![](/images/pages/products/flexberry-orm/tutorial-programmer-casseberry/primer-8.jpg)

и мы хотим прочитать все экземпляры CDDD и CDDA в представлении «Главное» для Ресурса, то `[LoadingCustomizationStruct](fo_loading-customization-struct.html)` параметризуется следующим образом:

```csharp
lcs = new LoadingCustomizationStruct(null);			
lcs.View=Information.GetView("Главное", typeof(Ресурс));
lcs.LoadingTypes=new Type[]{typeof(CDDD), typeof(CDDA)};
```
Затем, необходимо вызвать метод `[DataService#LoadObjectsLCS|LoadObjects(lcs)]`.

Пример доступен по адресу: <https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs>.
