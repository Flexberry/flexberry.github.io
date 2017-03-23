---
title: Атрибуты классов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fo_attributes-class-data.html
---
## Определение атрибута и что как с него генерируется
В классе атрибут определяется строкой вида:

```
[/][AccessModifier]Name:Type[=DefaultValue]
```

, что соответствует нотации UML.

Например: 

```
+ДатаРегистрации:DateTime=Now
+Статус:Статус=Рег
+idx:int=0
```

| Что генерируется | Генерация в SQL DDL | Генерация в .Net-язык|
|---|---|---|

