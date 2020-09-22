---
title: Flexberry Audit
keywords: flexberry, Audit
sidebar: flexberry-audit_sidebar
toc: false
permalink: en/fau_landing_page.html
lang: en
autotranslated: true
hash: ab88822d94f6d45881a01df977f45f6903a79440e03647130376541df81178ab
---

## Description

Technology component **Flexberry Audit** implements a function of logging data changes by users.

## Opportunities

* Fixing of any facts and references to data
* Fix arbitrary operations (through the API)
* View detailed information about performed operations on the data, including the changed values
* Recording facts in the same transaction with the data change
* Flexible configuration of fixed operations and detail information

## Architecture

**Flexberry Audit** consists of the following parts:

* Databases with data auditing (may be the main database of the application or separately, including in other DBMS)
* Backend - NuGet package of the object model and API: [NewPlatform.Flexberry.Audit](https://www.nuget.org/packages/NewPlatform.Flexberry.Audit)
* Interface viewing audit data
* [The implementation of](https://github.com/flexberry/ember-flexberry-security) for [Flexberry Ember](ef3_landing_page.html)
* Implementation for [Flexberry ASP.NET](fa_landing_page.html)
* Implementation for [Flexberry Winforms](fw_landing_page.html)
* Set up fixed operations on data objects are performed in the interface Flexberry Designer.

## How to use

To create an application using the **Flexberry Audit** you can design and generate applications in Flexberry Designer.
Separately available as a NuGet package [NewPlatform.Flexberry.Audit](https://www.nuget.org/packages/NewPlatform.Flexberry.Audit), but to use it in manual mode will need to implement requests to the API.
You will also need [instructions for installing and configuring audit](fau_audit-install.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}