--- 
title: Applications (classes with the stereotype application) 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, application, stereotype, application, generation, properties, attributes, methods 
summary: Generation application, especially classes with steriotip application 
toc: true 
permalink: en/fd_application.html 
lang: en 
autotranslated: true 
hash: f5ff76c58bb7f0ca65193690d4357720c0af9c294da2f6a49641c6b80d05c62b 
--- 

Class `Application` intended to describe the structure and properties of client applications that are directly invoked by the user. 

To describe the application, you must create a UML class with the [stereotype](fd_key-concepts.html) `application`. 

![](/images/pages/products/flexberry-designer/class-diagram/application.png) 

## Result of generation of the Application class 

* Assembly name XXXXX(Desktop Customizers), class code inherited from `ICSSoft.STORMNET.UI.DesktopCustomizer` and overloaded methods: 
* `GetDesktopCaption` (returns the title of the window [desktop](fw_app-desktop.html)) 
* `GetRunners` (returns a structure [desktop](fw_app-desktop.html)) 
* Run the application with the name similar to the name of a UML class shape, inherited from the form of the standard [desktop](fw_app-desktop.html) `ICSSoft.STORMNET.Windows.Forms.Desktop`. 

## application Properties 

![](/images/pages/products/flexberry-designer/class-diagram/applicationprops.jpg) 

Property | Description | Generation .Net language 
:---------------------|:-------------------------------|:----------------------------------------- 
`Name` | Name UML class | application Name, form name (form XXXXXDesktop), Assembly (type XXXXX(Desktop Customizers)) class of desktop settings, the class name of the desktop settings (XXXXXDesctopCustomizer) 
`Description` | a class description | `DocComment` before the class definition XXXXXDesktopCustomizer and form class 
`Caption` | a class description | Value returning method `XXXXXDesktopCustomizer.GetDesktopCaption()` 
`StandartDesktop` | | If the option is specified - form desktop inherited from a standard desktop `ICSSoft.STORMNET.UI.DesktopCustomizer`, if not, it generates an empty form (inherited from `System.Windows.Forms.Form`) 
`PBCustomAttributes` | Allows you to specify whether to brace the programmer immediately before the description of the class for `ручного` of any attributes | If the option is specified - generated bracket programmer for `ручного` making .Net attributes before classes `XXXXXDesktopCustomizer` and form class. 
`NamespacePostfix` | Allow to set the Assembly and namespace | see [the Location of assemblies after code generation](fo_location-assembly.html) 
`Containers` | Allow you to configure the composition and arrangement of desktop items (forms, opening from the desktop) | Generated contents method `XXXXXDesktopCustomizer.GetRunners()`. 

## setting composition desktop 

By clicking on the button `...` (`Containers`) opens a dialog that allows you to adjust the composition and arrangement of desktop items 

![](/images/pages/products/flexberry-designer/class-diagram/applicationconts.png) 

In the left list shows the menu structure of desktop, and the buttons `Добавить/Remove папку` allow you to design this structure. 
The right side displays the contents selected in the left of the folder. To the folder add/remove list form buttons `Добавить/Remove контейнер`. 
In the bottom of the window you can configure the title and description (as it should be called on the desktop) for the selected in right list form. 
To sort containers, you must use the appropriate buttons `вверх` and `вниз`. The sorting of folders is not included. 

To move the containers from one folder to another, you need to use `drag&drop`. 

## the configuration file of the application 

To configure the configuration file, switch to the tab `Конфигурация` 

![](/images/pages/products/flexberry-designer/class-diagram/applicationconfig.jpg) 

Contents of the configuration file you can edit the app. 

## attribute Properties 

The properties of the attributes are the same as specified [in the class Attributes data](fo_attributes-class-data.html), given the fact that are generated in class - form desktop. 

## Properties methods 

Properties methods similar to those described [in the class Attributes data](fo_attributes-class-data.html), given the fact that are generated in class - form desktop. 





{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}