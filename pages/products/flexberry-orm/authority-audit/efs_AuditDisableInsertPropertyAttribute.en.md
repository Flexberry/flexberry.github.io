--- 
title: Audit and DisableInsertPropertyAttribute 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-disable-insert-property-attribute.html 
lang: en 
autotranslated: true 
hash: 40231781e6014dac5aa3ef0973bfadeec0a2120e3bb4cca0fb841839711bf4ad 
--- 
In version after 16.10.2013 appeared when creating the object record in the [audit](fa_audit-web.html) information about the object properties marked with the attribute [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html). 

Feature attribute [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html) is that the properties with this attribute value is defined after saving the corresponding object in the database, whereas an audit record for a number of reasons is to save the object in the database. In this regard, in [API audit](efs_audit-web-api.html) has been added methods appends the values of the properties with the attribute [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/