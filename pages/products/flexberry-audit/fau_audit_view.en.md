---
title: Presentation to audit
sidebar: flexberry-audit_sidebar
keywords: flexberry, audit, auditView
summary: Description of presentation for audit
toc: true
permalink: en/fau_audit-view.html
lang: en
autotranslated: true
hash: 9f7f5d53ee9bed8e3dd73e35b71375cc87af5c5ea6db665792c8126185dcadd6
---

Presentation to audit specifies the fields whose changes will be added to the audit record.
The default view `AuditView`, but in the settings you can use audit to change the view.
Learn more about configuring auditing, see the article [article](/fau_audit-install.html).

## Private object fields in the view audit

When you create an audit entry for each field changed self, the object is created `AuditField`. He is detalam audit records.
Created `AuditField` contains a link to the audit record, the name of the changed field, the old value and the new value (AuditEntity, Field, OldValue, NewValue).

## Field masters in performance audit

For all fields of the wizard in the view, two objects are created `AuditField`. The first contains a change to the primary key of the master.
In fact this change private fields of the object with reference to the master, the content is the same as the entry for the custom field from the previous paragraph, but the name of the field added `(LinkedPrimaryKey)`.
The second object `AuditField` also anasogeonsice for the custom field from the previous paragraph, but as the name field takes the name of the field with reference to the master, and as the old and the new value string of the form:
`M(M. F1=V1, M. F2=V2, ...)`, where M is the name of the field with reference to the master F - master field, V - field value of the master.

## Detaily in performance audit

For detail in performance, for kazhloy record detail is created by two objects `AuditField`, similar to the field master of the previous paragraph. The difference is that a field name is added in brackets the serial number of detail in the array of detailov.
Sample name field detail - `DetailName (n)(LinkedPrimaryKey)` where DetailName - the name of detail, n is the ordinal entry number of detail, LinkedPrimaryKey - is appended to the name if it `AuditField` change the primary key of detail.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}