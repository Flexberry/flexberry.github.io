---
title: Пример: работа с представлениями
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/work_with_views_example.html
folder: product--folder
lang: ru
---

В этом примере показывается, каким образом можно получать доступ к преставлениям объектов данных.

```
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