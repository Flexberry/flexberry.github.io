--- 
title: Win-forms for audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit, Windows UI (forms) 
toc: true 
permalink: en/efs_audit-win-forms.html 
lang: en 
autotranslated: true 
hash: c1b564d6147bbf34cb5c1234d4fc1e43d318c08538a79398fb8e9132da5d9d07 
--- 

## Available win-form audit 

Win-forms [audit](audit-web.html) was developed for displaying objects used [ICSSoft.STORMNET.Business.Audit.Audit](efs_i-audit.html). Forms are packaged as nuget package `NewPlatform.Flexberry.Audit.WinForms`. 

There is also a [web-form audit](fa_audit-web-forms.html). 

Win-forms [audit](fa_audit-web.html): 
* View records on the implementation of audit actions. 
* View information about the audit objects (with the relevant edit forms you have access to view the records for each audited action on a specific object). 

## Constraints win-forms audit 

The forms of the [audit](fa_audit-web.html) there are the following limitations: 
* ""'&lt;add key="AuditConnectionStringName" value="..." /&gt;"'" better to ask for the name of the current connection string, since the forms of support read only from the current database. 

## Application win-forms audit 

At the moment, the form of the [audit](fa_audit-web.html) is connected to the [console powers](efs_security-console.html). 

To work forms may require the following: 
* [Setup audit records for authorization objects](efs_rights-and-audit-subsystems.html). 
* [Adding to the configuration file settings of the audit and their initialization in the code](efs_audit-win-example-manual.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/