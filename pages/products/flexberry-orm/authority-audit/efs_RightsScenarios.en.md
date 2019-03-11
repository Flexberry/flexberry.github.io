--- 
title: the rights of users of the application 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security, Key concepts 
toc: true 
permalink: en/efs_rights-scenarios.html 
lang: en 
autotranslated: true 
hash: 0a8af9fbf10b644a91b8a40d238344348b029a97643b8997175786a066632c35 
--- 

# Scripts user access rights differentiation applications 
The following scenarios are supported user access rights differentiation system applications: 
# Distinction on the level of navigation - the user will not be available to certain top-level elements in the navigation tree on the desktop application. It should be noted that while the user will still access the hidden pages. To deny access (and not just hide it from the navigation tree) you should use the underlying methods of access settings. 
# Distinction at the level of individual list form, the user will not be available to certain lower-level items of the navigation tree on the desktop application corresponding to the individual lists, and, consequently, inaccessible to the functionality of these lists. 
# Distinction at the level of the edit forms - would be able to view only the information in the lists, with no possibility to edit. 
# of Differentiation at the level of entities (classes) - have the ability to restrict access to an entire class for certain permissions: read, write, delete, update, execute. For example, in the absence of permission to delete, the user can create and update records, but will not be able to delete them. 
# Distinction at the level of individual records everywhere, especially on the lists when reading data from DB will be deducted only those records for which there is authority. 
# of Differentiation at the level of individual operations - can define arbitrary operations and the execution of certain actions (e.g. pressing the button) programmatically verify the existence of authority for a specified action. Using this approach can be implemented can edit the individual attributes in a separate form, shown at just the click of a button. 

Technical description of the system of authority [Subsystem-permission|here] 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}