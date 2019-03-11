--- 
title: creating a prototype 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_prototype-creating.html 
lang: en 
autotranslated: true 
hash: b6e14a72154a773ae18c91ee6301203d86a515ae9bf446bec8d515ac81a903a1 
--- 

{% include important.html content="Before the [prototype](fd_prototype-creation.html) you must ensure that the models are all right, because the correction of the model after the prototype will be more time consuming. 
To fix need to delete the automatically created chart and presentation in classes and repeat [rapid prototyping](fd_using-quick-prototyping.html)." %} 

1.In the project tree go to the created system: 

![](/images/pages/guides/flexberry-aspnet/system.png) 

2.Next, click on the system right click. In the menu that appears, select `ASP.NET` -> `Утилиты` -> `Создать views, forms and приложение`. A window will appear `Создать prototype приложения`. 

![](/images/pages/guides/flexberry-aspnet/create-prototype.jpg) 

3.In the appeared window specify the application name (can be any, the main thing that it does not coincide with the name of an existing class), for example, `АСУ_Склад`. Click `OK`. 

4.If the system consists of multiple applications that need to explicitly share, you must specify the prefix of the application (more important in generating Win-apps). 

5.If done correctly, the prototype will be created successfully: 

* will display the class diagram that contains the class name of the application and the stereotype `application` (e.g. `АСУ_Склад`), 
* as well as classes with stereotypes `editform` and `listform` describing domain classes edit forms and list forms, respectively. 

POPs up a window with log messages. If all goes well, you can close it. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [configure script generator for database](gpg_configuring-script-generator-db.html) 
* [Setup app structure](gpg_configuring-application-structure.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}