---
title: Аудит и DisableInsertPropertyAttribute
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-disable-insert-property-attribute.html
lang: ru
---
В версии после 16.10.2013 появилась возможность при создании объекта записывать в [аудит](fa_audit-web.html) сведения о свойствах объекта, помеченных атрибутом [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html).

Особенностью атрибута [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html) состоит в том, что у свойства с таким атрибутом значение определяется уже после сохранения соответствующего объекта в базу данных, тогда как  запись аудита по ряду причин производится до сохранения объекта в БД. В связи с этим в [API аудита](efs_audit-web-api.html) были добавлены методы, позволяющие дозаписывать значения свойств, имеющих атрибут [DisableInsertPropertyAttribute](fo_disable-insert-property-attribute.html).
