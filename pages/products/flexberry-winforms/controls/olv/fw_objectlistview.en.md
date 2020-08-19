---
title: ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, controls, list
summary: the Description, the main functionality, updating lists, processing and load optimization list
toc: true
permalink: en/fw_objectlistview.html
lang: en
autotranslated: true
hash: 32ec588b7d364f88a76e8e420a4afb8890192991e79fdc5ceca8972e5327509c
---

__To display in the form of a list of objects__ there is a specialized control that `ObjectListView`.

The object list can be generated based on the model [Flexberry Desinger](fd_flexberry-designer.html) or created manually.

To display a list of objects without generating you must:

* Place `ObjectListView` on the form.
* Join `ObjectListView` [service data](fo_data-service.html). For this you have to initialize `ObjectListView` property `DataService`. If the form has already implemented the data services, then you can specify in the editing window properties, where there is this drop-down list.
* Set up the debug package (if necessary).
* Choose one or several classes to display. For this you have to initialize `ObjectListView` property `DataObjectTypes`. This can also be done with a special designer, accessible from the edit window properties.
* Choose compatible with all classes of performance. It is necessary to initialize at `ObjectListView` property `ViewName`. This can also be done from the edit window properties. Attention! In the drop-down list are only compatible with all the types listed in `DataObjectTypes`, views.
* Install, if necessary, limitation. For this to initialize a property `LimitFunction`. If you do in the environment VS window edit properties, then a property `Limit`.
* To configure, if necessary, the visibility of and width of columns. To configure `ObjectListView` property `Columns` through the properties.

## The main functionality of ObjectListView for the user

`ObjectListView` displays different types of objects in one list in accordance with compatible performance. In addition to the attribute of the composition, according to the submission, `ObjectListView` can display pictures (`Image`) assigned to the data classes.

The main functionality `ObjectListView` for the user:

![Example](/images/pages/products/flexberry-winforms/controls/olv/primer15.jpg)

The user can:

* To view a list of objects (including different types)
* Refresh the list of objects ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer16.jpg)

* Return the selected objects (LookUp) ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer17.jpg)

* To sort objects, which is necessary to poke the mouse in the column heading
* Create the object (if DataObjectTypes contains several types, you can choose from the drop down list to choose which type you want to create the object) ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer18.jpg)

* To create an object based on the selected ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer19.jpg)
* To edit one or more selected objects ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer20.jpg)
* Remove one or more selected objects ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer21.jpg)
* Customize the visibility of columns (the user can hide some columns for convenience) ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer22.jpg)
* To restrict the list of displayed objects ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer23.jpg)
* To search the list ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer24.jpg)

Also available to the user:

* The print preview of the list ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer25.jpg)
* Print list ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer26.jpg)
* Print settings ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer27.jpg)
* Copy the selection to the clipboard ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer28.jpg)
* Operations with notes and highlighting ![Example](/images/pages/products/flexberry-winforms/controls/olv/primer29.jpg)

## Desktop update

To force a refresh of the folder tree and list the desktop you want to call a method `DesktopCtrl.ReloadDesktopcustomizer()`.

## The message about the continuation of the boot at the lack of resources

The message continued loading occurs when the exhaustion of physical memory allocated to the application, because the allocation of memory in the swap file takes time.

![Example](/images/pages/products/flexberry-winforms/controls/olv/load-question.jpg)

To enable this feature, you must set the property "MemoryTimeLoadLimit" any positive value.

## Optimization data read

In normal mode `ObjectListView` reads all the fields in the view, even if some columns are hidden. SQL Server when the query is guided by the fields included in the top query (when investing) and restriction. If the field wizard is not used in the list displayed, or in the limit, the SQL Server does not join, thus speeding up the query limit.

Setting properties `ObjectListView.UseColumnOptimization=true` allows to include hidden fields.

To set the optimization mode for all lists of the application allows the method `Tuner.Customize` in which to set the property `UseColumnOptimization` for `ObjectListView` passed as a parameter.

## Disable the F2 hotkey for edit objects

You need to specify `ObjectListView` `UseHotkeyForEdit = false;`. This will affect the tooltip that appears over the edit button.

## Useful links

* Objects in ObjectListView
 * [Entire list of objects in ObjectListView](fw_put-list-objects.html)
 * [Events ОbjectListView](fw_olv-event.html)
* Design and functionality
 * [Set date display default](fw_date-format.html)
* Different
 * [Assignment LimitFunction for the second ObjectListView](fw_assigning-limit-function-second-objectlistview.html)
 * [Availability of the operations on the ObjectListView depending on user rights](fw_objectlistview-rights.html)
 * [Customize and add columns and records in ObjectListView](fw_desktop-operations.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}