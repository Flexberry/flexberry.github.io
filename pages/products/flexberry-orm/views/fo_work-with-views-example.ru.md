---
title: Пример работы с представлениями
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, представления, ограничения
summary: Доступ к представлениям
toc: true
permalink: ru/fo_work-with-views-example.html
lang: ru
---

В этом примере показывается, каким образом можно получать доступ к [преставлениям](fd_view-definition.html) объектов данных.

``` csharp
// Получение статически определенного представления с помощью Information.
ICSSoft.STORMNET.View cd_e_for_cd_view = Information.GetView("CD_E", typeof(CD));

// Самый простой путь получить статически определенное преставление объекта.
ICSSoft.STORMNET.View cd_e_for_cd_view1 = CD.Views.CD_E;

// Представления действительны также и для наследников класса.
ICSSoft.STORMNET.View cd_e_for_cdda_view = Information.GetView("CD_E", typeof(CDDA));
ICSSoft.STORMNET.View cd_e_for_cddd_view = Information.GetView("CD_E", typeof(CDDD));

// Получение имен статических представлений для различных классов.
string[] commonviewnames = Information.AllViews(new Type[] { typeof(CDDA), typeof(CDDD) });

Console.WriteLine("OK.");
```
