---
title: TrimmedStringStorage - обрезка строк сервисом данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_trimmed-string-storage.html
folder: products/flexberry-orm/
lang: ru
---

## Обрезка строк [сервисом данных](fo_data-service.html).

Чтобы управлять обрезкой строк при вычитке данных из источника, воспользуйтесь [`TrimmedStringStorageAttribute`](fo_attributes-class-data.html), который настраивается вo [FlexberryDesigner|Flexberry Designer) в свойствах атрибута (Trim).

По-умолчанию строки обрезаются (эта логика зашита в [`Information`](fo_information-class-as-metadata-supervisor.html)`.SetPropValueByName()`).
