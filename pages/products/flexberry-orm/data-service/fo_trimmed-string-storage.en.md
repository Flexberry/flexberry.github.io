---
title: Cropping rows with data service
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data service
summary: Using TrimmedStringStorage to Control Cropping Rows
toc: true
permalink: en/fo_trimmed-string-storage.html
lang: en
---

Чтобы управлять обрезкой строк при вычитке данных из источника, необходимо использовать [TrimmedStringStorageAttribute](fo_attributes-class-data.html), который настраивается вo [Flexberry Designer](fd_landing_page.html) в свойствах атрибута (Trim).

По-умолчанию строки обрезаются (эта логика зашита в [Information.SetPropValueByName()](fo_methods-class-information.html)).
