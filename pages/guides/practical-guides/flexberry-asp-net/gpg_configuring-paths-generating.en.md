---
title: configuring routes generate forms web application
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_configuring-paths-generating.html
lang: en
autotranslated: true
hash: b8f97baa7f247837231c99455867a610e89044c6794acae4e50e82b9720d2a87
---

By default, project web app all the forms are generated in the following way:

1. In the root folder of the project folder is created `forms`
2. In the folder `forms` creates a separate folder for each form that is listed on [the class diagram of the prototype application](gpg_prototype-creating.html), with folders for forms created in Latin letters, even if the graph shape was named in Russian. At the same form with the same name but with different stereotypes (usually form stereotypes `listform` and `editform`) are in one folder.
Thus, the structure of the project with the web application default as follows:

![Project structure](/images/pages/guides/flexberry-aspnet/project-structure.png)

For setting the menu of the web application for different user roles would be more convenient to organize the folder structure of the forms in a slightly different way, since each folder will be a file with the settings for users with different roles to the web forms located within these folders.
In addition, when you create real enterprise applications, web forms can be quite a lot and requires, as a rule, the possibility of a more flexible structure within the project right at the stage of generation of web applications.

__Note:__ to solve the problem of creating configuration files, folder structure change is not necessary is the desire to optimize in this way the content of configuration files.

To change the way of generating web forms in the design phase, you must:

1.In [Flexberry Designer](fd_flexberry-designer.html) open a chart with [application prototype](gpg_prototype-creating.html) to open the form properties (the class with the appropriate stereotype), the way of generation which you want to change.

![Change in the prototype](/images/pages/guides/flexberry-aspnet/prototype-change.png)

2.In the form you must fill in the property `Packet`.

![Property](/images/pages/guides/flexberry-aspnet/packet.png)

Property `Packet` for forms should be filled in as follows:

* For forms `СкладE`, `СкладL`, `ТоварE`, `ТоварL` to specify the value `Products` (without the quotes).
* For forms `НакладнаяE`, `НакладнаяL`, `ЗаказE`, `ЗаказL`, `ДокументE`, `ДокументL` to specify the value `Orders` (without the quotes).
* For forms `СотрудникE`, `СотрудникL` to specify the value `Employees` (without the quotes).

Then you need to perform regeneration of the application to the project folder structure the web application has changed accordingly.
However, after modifying the properties `Packet` and the subsequent generation of the web application, the old version of the previously generated forms will not be removed, so in a real application, you'll need to migrate your code from old versions of forms in the new and then remove old versions of the forms. But because of changes in the code of the generated web forms is not yet included, you can delete the previously generated web application and to do the mistake again:

1.To view the path to the generated web application, choose the menu item `Настройки` -> `Путь generation...` on the main form `Flexberry Designer`.

![Path generation](/images/pages/guides/flexberry-aspnet/paths-generating.png)

2.Close the window to select the path generation applications.
3.Close `Visual Studio` if it is open.
4.Open `в Explorer Windows` folder with the generated web application.
5.Delete all files inside this folder or the entire folder.
Then re-generate the web application (from stage select menu `ASP.NET` -> `C#` -> `Генерировать and компилировать`).

The resulting structure is re-generated project, the web application should have the following form:

![New project structure](/images/pages/guides/flexberry-aspnet/project-structure-new.png)

## Go

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Add user roles from an application](gpg_adding-user-roles.html)
* [Customize application menu for different user roles](gpg_customize-application-menu.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}