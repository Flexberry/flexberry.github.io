--- 
title: Add user roles from an application 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_adding-user-roles.html 
lang: en 
autotranslated: true 
hash: c127a29771c7e2c117ff890b620e628dd5f70793594752626e584587b95f1f11 
--- 

For different roles of users, typically requiring a different application interface. That is, different user roles must be available «different sections of the» application. 
To add new roles and users and assign them access privileges to the data classes in the section `Администрирование` web application. 
The application menu for users and roles is defined in the file `Web.sitemap` project web app, and the possibility of access to individual pages, or pages that are located in separate folders in the application, is defined in the file `Web.config` (this is always present in the root folder of the project with the web application, in addition, such files may be created for each subfolder with pages in the same project). 

Details of the filtering mechanism maps web application ASP.NET you can see in the documentation [MSDN](https://msdn.microsoft.com/ru-ru/library/ms178428(v=vs.100).aspx). 

Learn more about the mechanism of authorization control in web application ASP.NET using roles can also be found in the documentation [MSDN](https://msdn.microsoft.com/ru-ru/library/9ab2fxh0(v=vs.100).aspx). 

You must first add a new role, according to made on the basis of the [entity relationship diagram](gpg_identifying-roles.html) conclusions. 

1.To add the role of Manager, accountant and storekeeper, you need to run the app from `Visual Studio`, log in under user `admin\admin` and to open a branch `Администрирование\Роли`. 

![](/images/pages/guides/flexberry-aspnet/administration.jpg) 

2.In the opened list form please click on the `Добавить` 

![](/images/pages/guides/flexberry-aspnet/add-role.png) 

3.In the opened form edit role, you need to fill in details about the role (`Наименование`) and then save the role. In addition to entering the role name anything else while on this form of note is not required. 

![](/images/pages/guides/flexberry-aspnet/save-role.png) 

Then use a similar process to create at least one user for each role, opening the relevant forms from branch `Администрирование\Пользователи`. 

![](/images/pages/guides/flexberry-aspnet/add-other-roles.jpg) 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [the Definition of user roles and their functions](gpg_identifying-roles.html) 
* [Set up ways of generating web apps](gpg_configuring-paths-generating.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}