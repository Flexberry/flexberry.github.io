---
title: ViewPropertyAppender
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, View (представление), Ограничения
toc: true
permalink: ru/fo_view-property-appender.html
folder: products/flexberry-orm/
lang: ru
---

# ViewPropertyAppender
Класс `AdvLimit.ExternalLangDef.ViewPropertyAppender` (находится в сборке `ExternalLangDef`) предназначен для того, чтобы расширять [представление](view-definition.html) свойствами, которые находятся в ограничении. 

Данный класс используется в [WebObjectListView](web-object-list-view.html) при работе с ограничениями (необходимо, чтобы свойство [AutoAddUsedInLimitationProperties](set-prop-order-at-web-adv-limit-editor.html) имело значение true).

# Основные методы
# `GetViewWithPropertiesUsedInFunction` - метод, который автоматически добавит в представление (будет создана копия представления) собственные и мастеровые свойства, которые используются в ограничении.
# `EnrichDetailViewInLimitFunction` - метод, который в представления (будет создана копия представления), соответствующие детейлам, добавляет собственные и мастеровые свойства детейла, которые используются в ограничении. Данный метод доступен после 30.10.2014.

