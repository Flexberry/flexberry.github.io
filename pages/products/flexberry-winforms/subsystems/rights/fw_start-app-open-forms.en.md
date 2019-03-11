--- 
title: Permission to run the application and opening forms 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms 
summary: See how to add the user certain powers 
toc: true 
permalink: en/fw_start-app-open-forms.html 
lang: en 
autotranslated: true 
hash: 6b9e5f4739dcd91d51efe44c850ce3a38dee17bf428402c3faff57714fe8951f 
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

{% include note.html content="to add To the list of class objects you need to use «to Fill the list of classes from an Assembly object in section» «Entities\Classes», where to select the Assembly object. Form classes are added to the list of classes by hand." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}