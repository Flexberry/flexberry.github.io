---
title: Настройка отображения столбцов WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-columns.html
lang: ru
---

Данная настройка позволяет пользователю изменить отображение столбцов: порядок, видимость. Созданные настройки доступно сохранить.  
Для того, чтобы включить данную возможность, необходимо установить свойство `wolv.Operations.ConfigureColumns = true`.

На верхней панели [WOLV](fa_web-object-list-view.html) появится кнопка:

![](/images/pages/products/flexberry-aspnet/controls/wolv/configure-columns-button.png)

Вы можете при помощи drag'n'drop установить порядок отображения столбцов.

![](/images/pages/products/flexberry-aspnet/controls/wolv/configure-columns-form.png)

Текущая настройка для списка хранится для каждого пользователя отдельно в таблице со всеми настройками. Сохранённые настройки хранятся там же, при этом префиксом имени настройки служит строка "ConfigureColumns/".
