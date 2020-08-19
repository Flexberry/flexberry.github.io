--- 
title: configuring access rights to the publication restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Security, Flexberry Winforms, Restrictions 
summary: it has been Specified what should be done to configure access rights to the publication restrictions 
toc: true 
permalink: en/fw_setting-permissions-for-publication-restrictions.html 
lang: en 
autotranslated: true 
hash: a2e79ede0d16e192f1d2a69c276e88af43dadc6f039e36253bd98b88d384ea8c 
--- 

## Description 

Configurable __AdvLimitComponent__ as follows: 

* __PublicateOperationId__ transaction id shown (default "-1") 
* __PublicateAccess__ indicates the possibility of access by default (If __PublicateOperationId__ <= 0 or not AzMan can check the access and causing the error) 

## setting in Flexberry Security 

This operation should be registered with a name corresponding to __PublicateOperationId__. This solution is not beautiful, but is used for compatibility with systems running on `AzMan` (for the possibility of a rapid transition to [Flexberry Rights](efs_security-legacy-services.html)). 

## See also 

* [Usage scenarios subsystem powers](efs_rights-scenarios.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}