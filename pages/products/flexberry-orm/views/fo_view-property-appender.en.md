---
title: ViewPropertyAppender
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, View (представление), Ограничения
toc: true
permalink: en/fo_view-property-appender.html
folder: products/flexberry-orm/
lang: en
---

## ViewPropertyAppender

Класс `AdvLimit.ExternalLangDef.ViewPropertyAppender` (находится в сборке `ExternalLangDef`) предназначен для того, чтобы расширять [представление](fd_view-definition.html) свойствами, которые находятся в [ограничении](fo_limit-function.html). 

## Основные методы

* `GetViewWithPropertiesUsedInFunction` - метод, который автоматически добавит в [представление](fd_view-definition.html) (будет создана копия представления) собственные и мастеровые свойства, которые используются в [ограничении](fo_limit-function.html).
* `EnrichDetailViewInLimitFunction` - метод, который в [представления](fd_view-definition.html) (будет создана копия представления), соответствующие детейлам, добавляет собственные и мастеровые свойства детейла, которые используются в [ограничении](fo_limit-function.html).