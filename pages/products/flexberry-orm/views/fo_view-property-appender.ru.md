---
title: ViewPropertyAppender
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, представление, Ограничения
summary: Основные методы расширения представлений для ограничений
toc: true
permalink: ru/fo_view-property-appender.html
lang: ru
---

Класс `AdvLimit.ExternalLangDef.ViewPropertyAppender` предназначен для того, чтобы расширять [представление](fd_view-definition.html) свойствами, которые находятся в [ограничении](fo_limit-function.html). 

## Основные методы

* `GetViewWithPropertiesUsedInFunction` - метод, который автоматически добавит в представление (будет создана копия представления) собственные и мастеровые свойства, которые используются в ограничении.
* `EnrichDetailViewInLimitFunction` - метод, который в представления (будет создана копия представления), соответствующие детейлам, добавляет собственные и мастеровые свойства детейла, которые используются в ограничении.
