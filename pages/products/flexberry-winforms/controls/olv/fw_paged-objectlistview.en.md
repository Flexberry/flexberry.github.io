--- 
title: List control PagedObjectListView 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, forms, list 
summary: Features OLV with page granularity, the user interface, features and configuration rules 
toc: true 
permalink: en/fw_paged-objectlistview.html 
lang: en 
autotranslated: true 
hash: 2191036680ac0e25e494c075928a606d960d8a88f446d1e37848a74018fa78e7 
--- 

List control `PagedObjectListView` intended for paged display a list of data objects. `PagedObjectListView` inherits from the class [ObjectListView](fw_objectlistview.html), thus, setup and operation of this control, in General, not different from the standard list. 

## user Interface 

To navigate the pages, use the panel located in the lower right corner of the control. On the panel are buttons with page numbers and a text field for entering the page number with the keyboard. Current displayed page is highlighted with background color. 

![](/images/pages/products/flexberry-winforms/controls/olv/p-olv.png) 

To configure the number of records per page use the context menu where the user can select with the default value, or enter a custom value in the text box. 

![](/images/pages/products/flexberry-winforms/controls/olv/p-olv2.png) 

## Properties 

Additional properties (in comparison with the base class) control is presented in the table below. 

| Property | Description| 
|--|--| 
| `PageNumber`| Current displayed page| 
| `RecordsPerPage`| Number of records per page| 
| `RecordCount`| Total number of entries| 
| `PageCount`| Total number of pages| 

## Placement on the list form 

For the organization of the page view list in the list form, the last needs to inherit from a class `ICSSoft.STORMNET.UI.BaseWinListPaged`. For list forms, you must establish a dependent form and in it to replace the base class. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}