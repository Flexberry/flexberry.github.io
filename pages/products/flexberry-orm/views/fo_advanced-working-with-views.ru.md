---
title: Пример продвинутой работы с представлениями
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, представления
summary: Динамическое создание представлений, операции с прдставлениями
toc: true
permalink: ru/fo_advanced-working-with-views.html
---

## Динамическое создание представлений

[Представление](fd_view-definition.html) можно создать динамически.

Если нужно создать представление "в коде", "на лету", то подойдет один из следующих вариантов:

1. Создать представление, используя конструктор по умолчанию, затем заполнить необходимые свойства

```csharp
// Создать пустое представление.
ICSSoft.STORMNET.View dynaview = new ICSSoft.STORMNET.View();

// Указать тип объекта данных, для которого создаем представление.
dynaview.DefineClassType = typeof(CDDA); 

// Вы можете добавить собственные свойства класса и мастеровые свойства массивом, либо по одному.
// dynaview.AddProperty(...)
dynaview.AddProperties(new string[] { "Name", "TotalTracks", "Publisher.Name" }); 

// Добавить мастера в представление.
dynaview.AddMasterInView("Publisher"); 

// Также вы можете использовать метод dynaview.AddDetailInView для связывания этого представления с детейловыми представлениями.
```

2. Создать динамическое представление с помощью ViewAttribute

```csharp
ICSSoft.STORMNET.View dynaview1 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView", new string[] { "Name", "Publisher.Name" }), 
    typeof(CDDA));
```

## Операции с представлениями 

Каждое представление выступает как [набор свойств](fo_view-operations.html).

```csharp
ICSSoft.STORMNET.View view1 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView1", new string[] { "Name", "Publisher.Name" }), 
    typeof(CDDA));
ICSSoft.STORMNET.View view2 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView2", new string[] { "Name", "TotalTracks" }), 
    typeof(CDDA));

// a. Объединение представлений
// Результат содержит все свойства из обоих представлений ("Name", "Publisher.Name", "TotalTracks");
ICSSoft.STORMNET.View concatresult = (view1 | view2);

// b. Пересечение представлений
// Результат содержит общие свойства их указанных представлений ("Name");
ICSSoft.STORMNET.View intersectresult = (view1 & view2); 

// c. Вычитание представлений
// Результат содержит свойства из view1, которых нет во view2 ("Publisher.Name");
ICSSoft.STORMNET.View subtractsectresult = (view1 - view2); 

// d. Исключающее объединение представлений
// Общие свойства представлений будут отброшены (результат "Publisher.Name", "TotalTracks");
ICSSoft.STORMNET.View xconcatresult = (view1 ^ view2); 
```

Полный список примеров кода Flexberry ORM находится в статье ["Примеры кода"](fo_code-samples.html).
