--- 
title: setup script generator for database 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_configuring-script-generator-db.html 
lang: en 
autotranslated: true 
hash: e8aff309fb92414cd5ab2a319fe8736993a25c82f35725ae8d669bd0e299eab5 
--- 

1. Go to the step: select in the left tree item `Стадия` the left mouse button. 

![](/images/pages/guides/flexberry-aspnet/stage.png) 

2.Next, click on the stage, right-click. In the menu that appears, select `ASP.NET` -> `SQL` -> `Microsoft SQL Server` -> `Настройка БД`. A window will appear `Стадия (edit)`. 

3.In the appearing window change the field `Строка соединения`: 

* instead `server` need to specify the name of the specific сервера; for the local server you can use the following options: 
* .\<the SQL SERVER instance name> or <computer name>\<SQL SERVER instance name>, eg .\SQLEXPRESS 
* <computer name> 
* (local) 
* instead `database` you must specify the name of the specific database to which the application will work – it is desirable to specify the name of a nonexistent database, as in the first generation it will be created automatically. 

Connection string example: `SERVER=.\SQLEXPRESS;Trusted_connection=yes;DATABASE=sklad;` 
__Note:__ if one of the options will be non-working, try the other. 

4.Install in the field `Дополнительные настройки` «tick» `БД powers in DB приложения`. 

![](/images/pages/guides/flexberry-aspnet/stage-edit.jpg) 

5.Click `Сохранить and закрыть` ![](/images/pages/guides/flexberry-aspnet/save-and-close.png). 

6.Next, click on the stage, right-click. In the appeared menu, select `ASP.NET` -> `SQL` -> `Microsoft SQL Server` -> `Привести database in accordance with моделью`. A window will appear `Сообщения...`. 

![](/images/pages/guides/flexberry-aspnet/messages.png) 

If the database specified in the connection string does not exist it will be created. Also it will generate a script that represents a set of SQL DDL commands. The result of their application will be the resulting structure of the corresponding database model. 

7.If the database is successfully created, and generating the script is complete, a window will pop up `Результат` with the question of the use of this script. Press `Применить`. 

![](/images/pages/guides/flexberry-aspnet/script.jpg) 

8.In the window `Требуется подтверждение` need to confirm the use of the script - click `Да`. 

![](/images/pages/guides/flexberry-aspnet/script-application.png) 

9.As a result, the script will be applied: 

![](/images/pages/guides/flexberry-aspnet/script-applied.png) 

10.Close all Windows with messages.

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [the Preparatory stage of developing web-applications](gpg_preparatory-stage.html) 
* [Prototype](gpg_prototype-creating.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}