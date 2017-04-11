---
title: Маршрутизация в Web-приложениях
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_routing.html
lang: ru
---

## Маршрутизация

Маршрутизация ASP.NET позволяет использовать URL-адреса, не сопоставляемые с определенными файлами на веб-узле. По умолчанию, адрес вида `http://server/application/Products.aspx?id=4` будет сопоставим с файлом `Products.aspx`, а `id=4` будет использовано в качестве параметров. Использование маршрутизации позволяет изменять имена и передаваемые параметры различными способами так, чтобы использовать понятные пользователю имена, описывающие его действия.

К примеру, сервер может получить адрес вида `http://server/application/Products/show/beverages` и разобрать его следующим образом:

* area = Products
* action = show
* category = beverages

то есть этот адрес будет заменой адресу `http://server/application/Products?action=show&category=beverages`

Разумеется, подобное возможно только при соответстующей настройке маршрутизации.

Более подробно о механизме маршрутизации можно почитать на [MSDN](https://msdn.microsoft.com/ru-ru/default.aspx).

## Поддержка маршрутизации в Flexberry ASP.NET

Для поддержки маршрутизации в Flexberry ASP.NET был добавлен класс `DynamicPageRoute`, а также в `Global.asax.cs` добавлена маршрутизация для [технологических форм](fa_tech-forms-web.html).

По умолчанию регистрируются пути до форм `Version` и `Log`, в качестве пути используется строка вида `flexberry-dynamic-` + тип страницы. К примеру, для формы `Version` путь будет выглядеть следующим образом: `http://applicationName/flexberry-dynamic-Version`
