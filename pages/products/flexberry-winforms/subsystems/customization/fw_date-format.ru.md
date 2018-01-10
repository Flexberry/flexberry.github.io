---
title: Формат отображения даты по умолчанию
sidebar: flexberry-winforms_sidebar
keywords: DateTime (работа с датами)
summary: Указано как с помощью настройки в конфиге задать формат даты для отображения в ObjectListView
toc: true
permalink: ru/fw_date-format.html
lang: ru
---

Чтобы задать формат отображения даты по умолчанию(например, `dd.MM.yyyy HH:mm`) в приложении(в GroupEdit'ах, в ObjectListView), в конфиге надо добавить строку


`<add key="NullableDateTimeDefaultFormat" value="dd.MM.yyyy HH:mm" />`

По умолчанию, формат `short`.

Так как DateTimePicker, расположенные на форме и в GroupEdit, имеют собственные настройки формата, то эта настройка в конфиге фактически влияет лишь на отображение в ObjectListView.