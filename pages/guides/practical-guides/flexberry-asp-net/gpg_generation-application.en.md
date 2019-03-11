--- 
title: Generation of web applications 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_generation-application.html 
lang: en 
autotranslated: true 
hash: ba8972e428a8c4d3c2f6694170c2416d32d38391ad9beb23cf0b50faf9fd5c9e 
--- 

1.Choose the stage (click the left mouse button). 
2.Next, click on the stage, right-click. In the menu that appears, select `ASP.NET` -> `C#` -> `Генерировать and компилировать`. When you select this menu item performs the following steps: 

* generated the project with the data classes corresponding to domain model from the source graph классов; 
* installed the necessary NuGet packages in the project with classes данных; 
* compiled project classes данных; 
* generated project web applications ASP.NET Web Forms include a list form and edit form in accordance with the class diagram that was created when generating the prototype приложения; 
* installed the necessary NuGet packages to the project web application ASP.NET Web Forms. 

Generation and compilation of each project can be carried out separately, choosing items `Генерировать` and `Компилировать`, respectively, in menu `ASP.NET` -> `C#`. 
In the process of generating and compiling applications window displays `Сообщения...`, which will receive the log generation. 

3.After completion of the process of generation and compilation will see the following window: 

![](/images/pages/guides/flexberry-aspnet/fin generation.png) 

By clicking on the button `Да` will open the directory with the generated solution. 

4.By default, the generated web application is configured for authentication using forms. In order to carry out the authentication process when you run the application you must first create the credentials for a user in the database. To do this, click on the stage, right-click, then from the menu select `ASP.NET` -> `SQL` -> `Microsoft SQL Server` -> `Полномочия` -> `Создать user умолчанию`. As a result, in previously generated database will be added a user with username/password `admin/admin`, the role `Administrator`, wherein the generated user will be added to this role. 
In the process of generating the default user will be presented with a log of actions. Close it after the operation is completed. 

5.To close a window `Сообщения...`, which is displayed when you generate and compile the solution with the web application. 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [setting of code generation (CSharp)](gpg_configuring-generation.html) 
* [Run the web app](gpg_start-application.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}