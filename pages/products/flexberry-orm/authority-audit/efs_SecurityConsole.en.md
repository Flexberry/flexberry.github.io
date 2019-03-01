--- 
title: Console privilege management (Security Console) 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Security 
toc: true 
permalink: en/efs_security-console.html 
lang: en 
autotranslated: true 
hash: 15073db2d8f37420acf82b1b62b6fe78bb18ff04aec79af7fb3d9614f3035414 
--- 

## Console privilege management (Security Console) 

Management console [powers](efs_right-manager-module.html) are given the right to classes and operations. 

The management console powers collected under 3.5 .NET Framework. 

The basic nuances of working with the management console powers: 

* PstrfFull` it represents a permission (Full control) means that for a query on any of the queries on this object (`Update`, `Read`, etc.) will return a positive answer, i.e. the law is. Remember that permissions except `Full` are independent, i.e., the permission update does not automatically give read permissions on the object (such a division actually can be used, for example, the business server when the object is updated, but the user reads). 
* The name may not contain the character _ (underscore) as the character to use as separator in the system and will lead to errors in the future. 
* In the version after 18.07.2014 in win-console added the following logic: if the user selects permission `Full`, check the other permissions (`Update`, `Read`, etc.) are cleaned and locked (they cannot be put until exposed `Full` it represents a permission). The old permissions when you open a new console to change on their own will. 

## See also 

* [Usage scenarios subsystem powers](efs_rights-scenarios.html). 
* [Service authority Flexberry Rights (CheckingLibrary)](efs_security-legacy-services.html) 
* [Engine powers WEB](fa_right-manager.html). 
* [Checking password complexity in the security console](efs_checking-password-complexity-in-security-console.html) 
* [Add users to the database system of authority in windows authentication](fa_authentication-adapter.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/