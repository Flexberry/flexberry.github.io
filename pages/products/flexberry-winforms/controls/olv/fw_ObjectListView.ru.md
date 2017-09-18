---
title: ObjectListView
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_object-list-view.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))

ObjectListView - списковый контрол для win-приложений.

# Полезные ссылки
* Объекты в ObjectListView
** [Помещение списка объектов в ObjectListView](put-list-objects-in--object-list-view.html)
** [Отслеживание окончания загрузки данных в ObjectListView](end-load-data-in--object-list-view.html)
** [Обновление объекта в ObjectListView](updating-object-in--o-l-v.html)
** [Получение информации об удачности загрузки ОbjectListView](after-fill-data-event-args.html)
** [Оптимизация чтения данных ObjectListView](object-list-view-optimization.html)
** [Загрузка списка из нескольких источников данных](прикладные-системы_Загрузка-списка-из-нескольких-источников.html)
* События:
** [Событие установки/снятия галочки в ObjectListView](event-installing-removing-a-check-in--object-list-view.html)
** [Перехват-событии-ObjectListView-создание-удаление-изменение-объекта-выполнение-деиствии.ashx|Перехват событий ObjectListView (создание, удаление, изменение объекта), выполнение действий ] 
** [Событие ObjectListView.BeforeRefresh](object-list-view_Before-refresh.html)
* Дизайн и функциональность
** [ObjectListView, основная функциональность в RunTime](object-list-view-basic-functionality-in--run-time.html)
** [Настройка видимости колонок ObjectListView](object-list-view-column-visibility-customization.html)
** [Как без использования генераторов кода сделать на форме список объектов данных](make-a-list-of-data-objects-without-generators.html)
** [Настройка невидимых по умолчанию колонок](прикладные-системы_Настроика-невидимых-по-умолчанию-колонок-использование-атрибута--default-visible.html)
** [Задание отображения даты по умолчанию](Формат-отображения-даты-по-умолчанию.html)
** [Отображение подсказки(tooltip) для отдельных записей в ObjectListView ](object-list-view-tool-tip.html)
* Разное
** [Присвоение LimitFunction для второго ObjectListView](assigning--limit-function-second--object-list-view.html)
** [Удаление настроек пользователя в ObjectListView](delete-columns-settings-object-list-view.html)
** [Доступность операций на ObjectListView в зависимости от прав пользователя](object-list-view-rights.html)
** [Настройка и добавлении столбцов и записей в ObjectListView](object-list-view-in--desktop-ctrl.html)

# Как отключить хоткей F2 для редактирования объектов
Нужно в `ObjectListView` указать `UseHotkeyForEdit = false;`. Это повлияет и на тултип, который появляется над кнопкой редактирования.

# Обновление рабочего стола
Для принудительного обновления дерева папок и списка рабочего стола необходимо вызвать метод `DesktopCtrl.ReloadDesktopcustomizer()`.

# Сообщение о продолжении загрузки при нехватке ресурсов
Сообщение с вопросом о продолжении загрузки возникает при исчерпании физической памяти, выделенной приложению, т.к. выделение памяти в файле подкачки требует значительных временных затрат.



![](/images/pages/products/flexberry-winforms/controls/olv/LoadQuestion.jpg)



Для включения данной функции необходимо присвоить свойству ''MemoryTimeLoadLimit'' любое положительное значение.
----* [Загрузка списка из нескольких источников данных](прикладные-системы_Загрузка-списка-из-нескольких-источников.html)
* [Настройка невидимых по умолчанию колонок](прикладные-системы_Настроика-невидимых-по-умолчанию-колонок-использование-атрибута--default-visible.html)