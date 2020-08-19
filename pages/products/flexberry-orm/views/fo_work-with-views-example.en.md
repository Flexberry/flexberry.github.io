--- 
title: an Example of working with views 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, views, constraints 
summary: Access views 
toc: true 
permalink: en/fo_work-with-views-example.html 
lang: en 
autotranslated: true 
hash: 1b110aac05ca92ba00fac533347226f2c109df740768d4eee547162f13e6cd3e 
--- 

This example shows how to access [prestavleniem](fd_view-definition.html) of data objects. 

``` csharp
// Get statically point of view with Information. 
ICSSoft.STORMNET.View cd_e_for_cd_view = Information.GetView("CD_E", typeof(CD));

// The easiest way to get a statically scoped repose of the object. 
ICSSoft.STORMNET.View cd_e_for_cd_view1 = CD.Views.CD_E;

// Representation is valid also for descendant classes. 
ICSSoft.STORMNET.View cd_e_for_cdda_view = Information.GetView("CD_E", typeof(CDDA));
ICSSoft.STORMNET.View cd_e_for_cddd_view = Information.GetView("CD_E", typeof(CDDD));

// Get the names of static representations for various classes. 
string[] commonviewnames = Information.AllViews(new Type[] { typeof(CDDA), typeof(CDDD) });

Console.WriteLine("OK.");
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}