---
title: Reading objects belonging to different classes in one view
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, class, object, view
summary: Features of reading objects from different classes
toc: true
permalink: en/fo_reading-several-types-objects.html
lang: en
---

Для того чтобы прочитать множество объектов, необходимо (как минимум) произвести настройки [LoadingCustomizationStruct](fo_loading-customization-struct.html):

* указать [классы данных](fo_data-object.html), для которых выполняется чтение ([LoadingTypes](fo_loading-customization-struct.html));
* указать общее для них [представление](fd_view-definition.html) ([View](fo_loading-customization-struct.html)).

Таким образом, если имеется ситуация вида:

![](/images/pages/products/flexberry-orm/additional-features/primer-8.jpg)

и необходимо прочитать все экземпляры CDDD и CDDA в представлении «Главное» для Ресурса, то `LoadingCustomizationStruct` параметризуется следующим образом:

```csharp
lcs = new LoadingCustomizationStruct(null);			
lcs.View=Information.GetView("Главное", typeof(Ресурс));
lcs.LoadingTypes=new Type[]{typeof(CDDD), typeof(CDDA)};
```

Затем необходимо вызвать метод [LoadObjects(lcs)](fo_data-service.html).

Пример доступен по адресу: [на GitHub](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs).

Для решения противоположной задачи, а именно, для вычитки объектов-наследников по более широкому набору свойств, нежели у предков, могут использоваться [адаптивные представления](fo_adaptive-views-for-details.html).
