---
title: ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: Перечень статей по ObjectListView и некоторые свойства и методы
toc: true
permalink: ru/fw_objectlistview.html
folder: products/flexberry-winforms/
lang: ru
---

<!-- Данная статья ещё редактируется -->

ObjectListView - списковый контрол для win-приложений.

## Полезные ссылки
* Объекты в ObjectListView
    * [Помещение списка объектов в ObjectListView](fw_put-list-objects-in-objectlistview.html)
    * [Отслеживание окончания загрузки данных в ObjectListView](fw_end-load-data-in-objectlistview.html)
    * [Обновление объекта в ObjectListView](fw_updating-object-in-olv.html)
    * [Получение информации об удачности загрузки ОbjectListView](fw_after-fill-data-event-args.html)
    * [Оптимизация чтения данных ObjectListView](fw_objectlistview-optimization.html)
* События:
    * [Событие установки/снятия галочки в ObjectListView](fw_event-installing-removing-a-check-in-objectlistview.html)
    * [Перехват событий ObjectListView (создание, удаление, изменение объекта), выполнение действий](fw_interception-events-objectlistview.html) 
    * [Событие ObjectListView.BeforeRefresh](fw_objectlistview-before-refresh.html)
* Дизайн и функциональность
    * [ObjectListView, основная функциональность в RunTime](fw_objectlistview-basic-functionality-in-run-time.html)
    * [Настройка видимости колонок ObjectListView](fw_objectlistview-column-visibility-customization.html)
    * [Как без использования генераторов кода сделать на форме список объектов данных](fw_make-a-list-of-data-objects-without-generators.html)
    * [Задание отображения даты по умолчанию](fw_date-format.html)
    * [Отображение подсказки(tooltip) для отдельных записей в ObjectListView ](fw_objectlistview-tooltip.html)
* Разное
    * [Присвоение LimitFunction для второго ObjectListView](fw_assigning-limit-function-second-objectlistview.html)
    * [Удаление настроек пользователя в ObjectListView](fw_delete-columns-settings-objectlistview.html)
    * [Доступность операций на ObjectListView в зависимости от прав пользователя](fw_objectlistview-rights.html)
    * [Настройка и добавлении столбцов и записей в ObjectListView](fw_objectlistview-in-desktop-ctrl.html)

## Как отключить хоткей F2 для редактирования объектов
Нужно в `ObjectListView` указать `UseHotkeyForEdit = false;`. Это повлияет и на тултип, который появляется над кнопкой редактирования.

## Обновление рабочего стола
Для принудительного обновления дерева папок и списка рабочего стола необходимо вызвать метод `DesktopCtrl.ReloadDesktopcustomizer()`.

## Сообщение о продолжении загрузки при нехватке ресурсов
Сообщение с вопросом о продолжении загрузки возникает при исчерпании физической памяти, выделенной приложению, т.к. выделение памяти в файле подкачки требует значительных временных затрат.

![](/images/pages/products/flexberry-winforms/controls/olv/load-question.jpg)

Для включения данной функции необходимо присвоить свойству ''MemoryTimeLoadLimit'' любое положительное значение.