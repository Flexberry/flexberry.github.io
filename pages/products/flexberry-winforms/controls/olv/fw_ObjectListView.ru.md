---
title: ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, контролы, список
summary: Описание, основная функциональность, обновление списков, обработка и оптимизация загрузки списка
toc: true
permalink: ru/fw_objectlistview.html
lang: ru
---

__Для отображения на форме списка объектов__ существует специализированный элемент управления, `ObjectListView`.

Список объектов может быть сгенерирован на основе модели [Flexberry Desinger](fd_flexberry-designer.html) или создан вручную.

Для отображения списока объектов без генерации необходимо:

* Поместить `ObjectListView` на форму.
* Присоединить к `ObjectListView` [сервис данных](fo_data-service.html). Для этого проинициализировать у `ObjectListView` свойство `DataService`. Если на форме уже реализованы сервисы данных, то указать можно в окне редактирования свойств, где есть для этого выпадающий список.
* Настроить отладочный пакет (если есть необходимость).
* Выбрать один или несколько классов для отображения. Для этого проинициализировать у `ObjectListView` свойство `DataObjectTypes`. Это можно также сделать при помощи специального дизайнера, доступного из окна редактирования свойств.
* Выбрать совместимое со всеми классами представление. Для этого необходимо проинициализировать у `ObjectListView` свойство `ViewName`. Это также можно сделать из окна редактирования свойств. Внимание! В выпадающий список попадают только совместимые со всеми типами, перечисленными в `DataObjectTypes`, представления.
* Установить, при необходимости, ограничение. Для этого проинициализировать свойство `LimitFunction`. Если делать в среде VS из окна редактирования свойств, то через свойство `Limit`.
* Настроить при необходимости видимость и ширину колонок. Для этого настроить у `ObjectListView` свойство `Columns` через окно редактирования свойств.

## Основная функциональность ObjectListView для пользователя

`ObjectListView` отображает разнотипные объекты в одном списке в соответствии с совместимым представлением. Кроме атрибутивного состава, согласно представлению, `ObjectListView` может отображать картинки (`Image`), приписанные классам данных.

Основная функциональность `ObjectListView` для пользователя:

![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer15.jpg)

Пользователь может:

* Просматривать список объектов (в т.ч. и разнотипных)
* Обновить список объектов ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer16.jpg)

* Вернуть выбранные объекты (LookUp) ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer17.jpg)

* Выполнять сортировку объектов, для чего необходимо ткнуть мышью в заголовок колонки
* Создать объект (если DataObjectTypes содержит несколько типов, то пользователь может из выпадающего списка выбрать, какого типа необходимо создать объект) ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer18.jpg)

* Создать объект на основе выделенного ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer19.jpg)
* Отредактировать один или несколько выделенных объектов ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer20.jpg)
* Удалить один или несколько выделенных объектов ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer21.jpg)
* Настроить видимость колонок (пользователь может скрыть часть колонок для удобства) ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer22.jpg)
* Наложить ограничение на список отображаемых объектов ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer23.jpg)
* Выполнить поиск по списку ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer24.jpg)

Также пользователю доступны:

* Предварительный просмотр печати списка ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer25.jpg)
* Печать списка ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer26.jpg)
* Настройки печати ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer27.jpg)
* Копирование выделенного в буфер обмена ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer28.jpg)
* Операции с пометками и выделением ![Пример](/images/pages/products/flexberry-winforms/controls/olv/primer29.jpg)

## Обновление рабочего стола

Для принудительного обновления дерева папок и списка рабочего стола необходимо вызвать метод `DesktopCtrl.ReloadDesktopcustomizer()`.

## Сообщение о продолжении загрузки при нехватке ресурсов

Сообщение с вопросом о продолжении загрузки возникает при исчерпании физической памяти, выделенной приложению, т.к. выделение памяти в файле подкачки требует значительных временных затрат.

![Пример](/images/pages/products/flexberry-winforms/controls/olv/load-question.jpg)

Для включения данной функции необходимо присвоить свойству ''MemoryTimeLoadLimit'' любое положительное значение.

## Оптимизация чтения данных

В обычном режиме `ObjectListView` зачитывает все поля, находящиеся в представлении, даже если некоторые колонки скрыты. SQL Server при выполнении запроса руководствуется полями входящими в самый верхний запрос (при их вложении) и ограничением. Если поле мастера не используется ни в списке выводимых, ни в ограничении, то SQL Server не выполняет join, тем самым ускоряя ограничение запроса.

Установка свойства `ObjectListView.UseColumnOptimization=true` позволяет не включать в запрос скрытые поля.

Установить режим оптимизации для всех списков приложения позволяет метод `Tuner.Customize`, в котором следует установить свойство `UseColumnOptimization` для `ObjectListView`, переданного в качестве параметра.

## Отключение хоткей F2 для редактирования объектов

Нужно в `ObjectListView` указать `UseHotkeyForEdit = false;`. Это повлияет и на тултип, который появляется над кнопкой редактирования.

## Полезные ссылки

* Объекты в ObjectListView
  * [Помещение списка объектов в ObjectListView](fw_put-list-objects.html)
  * [События ОbjectListView](fw_olv-event.html)
* Дизайн и функциональность
  * [Задание отображения даты по умолчанию](fw_date-format.html)
* Разное
  * [Присвоение LimitFunction для второго ObjectListView](fw_assigning-limit-function-second-objectlistview.html)
  * [Доступность операций на ObjectListView в зависимости от прав пользователя](fw_objectlistview-rights.html)
  * [Настройка и добавлении столбцов и записей в ObjectListView](fw_desktop-operations.html)
