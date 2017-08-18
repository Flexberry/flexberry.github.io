---
title: Мастера в представлении (View)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Desinger, View, мастер, представление, первичный ключ
summary: Правила использования мастеров и их свойств
toc: true
permalink: ru/fd_masters-view.html
lang: ru
---

Когда нужно выбирать в представлении самих [мастеров](fd_master-association.html), а когда достаточно просто их свойства?

Общие рекомендации такие:
* Если создаётся [представление](fd_view-definition.html), которое будет использоваться на списке и никакой логики не планируется, то достаточно вытащить в [представление](fd_view-definition.html) атрибуты [мастера](fd_master-association.html). 
* Если [представление](fd_view-definition.html) будет применяться для какой-то логики, то, чтобы иметь полноценный [объект-мастер](fd_master-association.html), нужно вытащить в [представление](fd_view-definition.html) его.

Дело в том, что при зачитке [первичный ключ](fo_primary-keys-objects.html) [мастера](fd_master-association.html) проставится правильно в любом случае, но инициализируется он в первом случае по упрощённой схеме. К тому же после изменения [мастера](fd_master-association.html) и попытке его сохранить может произойти ошибка `NotLoadedProperties`, поскольку мастер будет отсутствовать в [списке загруженных мастеров](fo_definition-loaded-properties.html).

