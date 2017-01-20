---
title: Настройка отображения столбцов WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_w-o-l-v-columns.html
folder: products/flexberry-aspnet/
lang: ru
---
Эта статья описывает часть информации о [WebObjectListView](web-object-list-view.html).

Данная настройка позволяет пользователю изменить отображение столбцов: порядок, видимость.

Для того, чтобы включить данную возможность, необходимо установить свойство `wolv.Operations.ConfigureColumns = true`.

На верхней панели WOLV появится кнопка:

![](/images/pages/img/CaseberryWeb/wolv/ConfigureColumns.png)

Вы можете при помощи drag'n'drop установить порядок отображения столбцов.

![](/images/pages/img/CaseberryWeb/wolv/ConfigureColumnsShow.png)

Настройка для списка хранится для каждого пользователя отдельно в таблице со всеми настройками. Ключ для настройки учитывает URL-адрес страницы, название
контрола WOLV и название используемого представления.
