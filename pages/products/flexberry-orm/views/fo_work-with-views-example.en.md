--- 
title: an Example of working with views 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, views, constraints 
summary: Access views 
toc: true 
permalink: en/fo_work-with-views-example.html 
lang: en 
autotranslated: true 
hash: a07d7ef06747e8a0967a8ff9a432bb54faa552d0361249cede1761b464f5035a 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/