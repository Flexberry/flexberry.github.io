--- 
title: Permission to run the application and opening forms 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms 
summary: See how to add the user certain powers 
toc: true 
permalink: en/fw_start-app-open-forms.html 
lang: en 
autotranslated: true 
hash: ef7d8bfd5f7f29e7ccea2e3c2c99d37207727e0cc1efaa6a9f2eec04184d5c77 
--- 

* Customize application 
PstrfAzManBridgeService` to deploy and configure the application. 

* Add the permission to start the application 
To add specific user rights to run application you must: 

* For the application (the application class) in the attribute [`AccessType`](fo_access-type.html) to specify "`this`". 

* [Management authority](efs_security-console.html) in the section "Entities\" Operation to create the operation whose name matches the name of the application. 
Then, on the edit form of the user in the tab "Agents\Users" tab on the "Operations" note added to operation [access type `Execute`(Performance)](efs_right-manager.html). 

* Add permission to open the forms 
First you need to add powers into classes. 
How to add permissions on classes can be found in [this article](fa_authority-classes.html) 

{% include note.html content="to add To the list of class objects you need to use the button `Заполнить a list of classes from an Assembly объектами` in section `Субъекты\Классы` where to select the Assembly object. Form classes are added to the list of classes by hand." %} 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/