--- 
title: audit for Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/fa_audit_entries.html 
lang: en 
autotranslated: true 
hash: 976201e9881c37a7fc1679070c829c4397393b68dc50fb312a89272e520ca64f 
--- 

`Система аудита` allows you to track __actions of users__ and __the state of system objects__. 

Tracked: 

* Track user actions on objects: creation, modification, deletion. 
* For each fixed time, the author of the changes and the type of operation. 
* If you change the fixed field is changed, its old value and the new value. 

Storing audit data: 

* Database auditing can be optionally separated from the application database. 
* There are two recording modes collect audit data: synchronous operation and asynchronous (at time of writing, work is only synchronous). 

Setting up auditing: 

* Simple to configure auditing for a class, his own fields, and datalow ([using Flexberry Tool](fo_audit-setup.html)). 

## audit trail Features in web systems 

Features of audit in a web system is described in [article Audit for Web applications](fa_audit-web.html). 

## Audit for web forms 

Audit for the web-form described in [article Web-form audit](fa_audit-web-forms.html). 

## connection Example of an audit to an existing Web application using regeneration project 

An example of a connection auditing to an existing Web application with the use of a regeneration project in the appropriate [article](fa_audit-web-example.html). 

## connection Example of an audit to an existing Web application without using regeneration project 

An example of a connection auditing to an existing Web application without using regeneration project described in the corresponding [article](fa_audit-web-example-manual.html).


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}