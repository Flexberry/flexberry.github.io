--- 
title: prototyping Phase 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, key concepts, prototype, prototyping, UML, generation, steps for creating application modules 
summary: Operation rapid prototyping, prototype genrace, connectivity modules 
toc: true 
permalink: en/fd_prototype-creation.html 
lang: en 
autotranslated: true 
hash: 65efb2af83ef1a2b39c542610e248eed1f1fa09c56612fee3342d2d1c808ecf8 
--- 

This article describes the process of creating working prototype applications from class diagrams, obtained on the [analysis phase](fd_analys.html). 

The result of this phase a prototype of the application. 

## Algorithm prototyping 

1. Create a UML description of the shapes using [the rapid prototyping](fd_using-quick-prototyping.html) 
2. To build the application prototype 
*At this point, the application can run on* 
3. To connect functional modules 
4. [Choose the theme of the visual design of the app](fa_choose-theme.html) 

### Create UML descriptions of the forms through a process of rapid prototyping 

To control the forms of input/output data, and custom and other forms, UML provides description of these forms. That did not appear necessary to create this description was manually created by the operation of rapid prototyping, which automatically generates form based on existing data classes. 

Read more about rapid prototyping it is possible to look in [Use quick prototypization](fd_using-quick-prototyping.html). 

### Generation of prototype applications 

If the class diagram was drawn correctly (see [analysis phase](fd_analys.html)) and the operation of rapid prototyping has successfully worked, the prototype application will be executed automatically when the corresponding request command. 

Prototyping includes: 
* Establishment of the necessary databases (applications, permissions, audit). 
* Generation of executable codes of the application. 
* Publish apps in the [Windows Azure](http://www.windowsazure.com)/in the Git repository. 

Thus, the application becomes available for use. 

In the generated application may be: add/delete/edit data in the database, create users and roles, to give authority, etc. 

{% include note.html content="following further changes to the model, you must re-generate the application for the changes to take effect." %} 

### the Connection of functional modules 

The functionality of the generated application you can extend with plugins. At the moment the following modules are available: 

1. **Subsystem powers**. Responsible for creating users and access rights to various data applications ([more](efs_secutity.html)). 
2. **Audit**. Responsible for auditing of all changes to application data ([more](fa_audit-web.html)). 
3. **Subsystem integration and running reports**. Responsible for creating reports ([more](fp_flex-reports.html)). 
4. **GIS sub-system**. Designed to work with electronic maps ([more](fg_landing_page.html)). 

### Choice of themes 

Themes are responsible for appearance of the application. The changes affect the colors, fonts, spacing, icons, etc. 

Read more about skins can be learned from [articles, the Choice of theme of Web apps](fa_choose-theme.html). 

## the Next stage 

After creating a prototype application, further refinement is carried out by modifying the generated code using the APIs provided by technology `Flexberry`. 

Description of principles of re-engineering of applications, as well as basic scripting improvements can be found in [this article](fd_application-development.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}