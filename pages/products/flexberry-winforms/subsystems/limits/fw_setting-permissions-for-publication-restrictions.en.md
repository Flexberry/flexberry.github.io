--- 
title: configuring access rights to the publication restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Security, Flexberry Winforms, Restrictions 
summary: it has been Specified what should be done to configure access rights to the publication restrictions 
toc: true 
permalink: en/fw_setting-permissions-for-publication-restrictions.html 
lang: en 
autotranslated: true 
hash: 0b4fb06e5c33617e13d26634924f28e73648063370b3a8998f7936b5f8750bf4 
--- 

## Description 

Configurable __AdvLimitComponent__ as follows: 

* __PublicateOperationId__ transaction id shown (default "-1") 
* __PublicateAccess__ indicates the possibility of access by default (If __PublicateOperationId__ <= 0 or not AzMan can check the access and causing the error) 

## setting in Flexberry Security 

This operation should be registered with a name corresponding to __PublicateOperationId__. This solution is not beautiful, but is used for compatibility with systems running on `AzMan` (for the possibility of a rapid transition to [Flexberry Rights](efs_security-legacy-services.html)). 

## See also 

* [Usage scenarios subsystem powers](efs_rights-scenarios.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/