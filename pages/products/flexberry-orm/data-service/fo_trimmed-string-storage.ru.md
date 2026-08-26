---
title: Обрезка строк сервисом данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных
summary: Использование TrimmedStringStorage для управления обрезкой строк
toc: true
permalink: ru/fo_trimmed-string-storage.html
lang: ru
---

Чтобы управлять обрезкой строк при вычитке данных из источника, необходимо использовать [TrimmedStringStorageAttribute](fo_attributes-class-data.html), который настраивается вo [Flexberry Designer](fd_flexberry-designer.html) в свойствах атрибута (Trim).

По-умолчанию строки обрезаются (эта логика зашита в [Information.SetPropValueByName()](fo_methods-class-information.html)).
