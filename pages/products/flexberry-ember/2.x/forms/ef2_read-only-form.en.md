---
title: Open ember-forms read-only
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_read-only-form.html
lang: en
autotranslated: true
hash: 1441e4cfef3d438104a8445f4e18bed9b27ba8add5c72c61a01062bcc1819315
summary: This feature allows you to access ember-form read-only
---

## Description
For operation in mode *read-only* in the base controller and edit form property is added `readonly`.

Thus, to open the [edit form](ef2_edit-form.html) read-only, you can:

* To pass a GET parameter in the query string, like so: `http://localhost:4200/orders/10251?readonly=true`.
* To override the definition of the value of the property `readonly` in the controller.

Processing of this parameter is used in the following versions:

* This property may be transferred into the [controls inherited from the base control ember-flexberry](ef2_controls.html).
* This property can be used to determine which buttons can be accessed by the user.

A variant design of the form template edit to account for the possibility of working in read-only mode are presented [in the article](ef2_edit-form.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}