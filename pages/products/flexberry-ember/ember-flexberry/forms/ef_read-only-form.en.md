--- 
title: Open ember-forms read-only 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_read-only-form.html 
lang: en 
autotranslated: true 
hash: 38cbd6b5acac27ff54b8c2862c4b5f0d47d2a9b4a976e53465da06cd2fac2fd6 
summary: This feature allows you to access ember-form read-only 
--- 

## Description 
For operation in mode *read-only* in the base controller and edit form property is added `readonly`. 

Thus, to open the [edit form](ef_edit-form.html) read-only, you can: 

* To pass a GET parameter in the query string, like so: `http://localhost:4200/orders/10251?readonly=true`. 
* To override the definition of the value of the property `readonly` in the controller. 

Processing of this parameter is used in the following versions: 

* This property may be transferred into the [controls inherited from the base control ember-flexberry](ef_controls.html). 
* This property can be used to determine which buttons can be accessed by the user. 

A variant design of the form template edit to account for the possibility of working in read-only mode are presented [in the article](ef_edit-form.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/