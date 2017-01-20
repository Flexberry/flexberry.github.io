---
title: Аудит и DisableInsertPropertyAttribute
sidebar: product--sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/audit-disable-insert-property-attribute.html
folder: product--folder
lang: ru
---
В версии после 16.10.2013 появилась возможность при создании объекта записывать в [аудит](audit-web.html) сведения о свойствах объекта, помеченных атрибутом [DisableInsertPropertyAttribute](disable-insert-property-attribute.html).

Особенностью атрибута [DisableInsertPropertyAttribute](disable-insert-property-attribute.html) состоит в том, что у свойства с таким атрибутом значение определяется уже после сохранения соответствующего объекта в базу данных, тогда как  запись аудита по ряду причин производится до сохранения объекта в БД. В связи с этим в [API аудита](audit-web-api.html) были добавлены методы, позволяющие дозаписывать значения свойств, имеющих атрибут [DisableInsertPropertyAttribute](disable-insert-property-attribute.html).
