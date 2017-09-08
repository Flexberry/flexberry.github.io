---
title:  Отображение на интерфейсе формы мастеров и детейлов
sidebar: flexberry-orm_sidebar
keywords:  Flexberry ORM, объекты данных, интерфейс, формы
summary: Интерфейс пользователя после генерации
toc: true
permalink: ru/fo_masters-details.html
lang: ru
---

то именно появится на интерфейсе формы после генерации диаграммы классов, содержащей [мастеровые ассоциации](fd_master-association.html) и [детейловые композиции](fo_detail-associations-properties.html), отображено на примере:

![](/images/pages/products/flexberry-orm/data-object/masters-and-details.jpg)

После генерации `Windows-форма` будет выглядеть примерно вот так:

![](/images/pages/products/flexberry-orm/data-object/form-interface.jpg)

А `web-форма` так:

![](/images/pages/products/flexberry-orm/data-object/web-form-interface.jpg)

Как можно заметить, [мастеровые ассоциации](fd_master-association.html) определили лукапы (как настроить внешний вид лукапа, описано в статье [Произвольный контрол в качестве лукапа](fo_custom-lookup.html)), а [детейловая композиция](fo_detail-associations-properties.html) - [GroupEdit](fw_group-edit.html)/ [AjaxGroupEdit](fa_ajax-group-edit.html).