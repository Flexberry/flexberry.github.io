--- 
title: Features of the editor class diagram 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, diagram, class diagram, features of class diagrams, Association, storage 
summary: Renaming classes, changing roles and assigning storage Association 
toc: true 
permalink: en/fd_class-diagram-editor-features-work.html 
lang: en 
autotranslated: true 
hash: 9bb25f50b989afb1aa22b25872c49ee3cf0c5b6dc4c60102c2ed479a051f2872 
--- 

Editor [class diagram](fd_class-diagram.html) in Flexberry Designer has several features: 

* **Rename classes** 
To **rename the class** figure you should click at it with the right mouse button and choose **drop-down menu item "Rename"**. You will be prompted to enter a new class name. 
If you change the class name directly in the diagram, it will create a new class with the specified name, and the old one will be deleted. As a consequence, removed all the attributes and views of the old class. 
* **The changing role of the Association** 
If you change the role of the Association directly in the diagram (not to bring up edit form Association), you will create a new Association. All the attributes of the old Association will be removed (for example, `Storage`, `Description`). 
If you want **to change the role of the Association, keeping all the old attributes**, you should click the right mouse button on the Association and choose **drop-down menu item "Edit properties"** and edit properties of the Association in the window that appears. 
* **Automatic prescription Storage Association** 
When adding a new Association, its property `Storage` prescribed automatically in accordance with a given role if the role is not specified, the prescribed name of the start class. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}