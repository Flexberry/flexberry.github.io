---
title: JavaScript API
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/fa_javascript-api.html
lang: ru
---

`JavaScript API` основано на `библиотеке jQuery` и представляет собой набор плагинов для неё.
 	
Всю систему клиентского JS API можно разделить на несколько основны групп.
 	
1.`ядро API` (общий набор методов и данных для работы с Flexberry ASP.NET).
* [jquery.ics.js](fa_js-api-core.html)


2.`API контролов` (набор операций для работы с основными технологическими web-контролами).
* [jquery.icsWolv.js](fa_js-api-wolv.html)
* [jquery.ajaxgroupedit.js](fa_ajax-group-edit.html)
* [jquery.icsDatePicker.js](fa_date-picker.html)
* [jquery.icsMasterEditorAjaxDropDown.js](fa_master-editor-ajax-dropdown.html)
* [jquery.icsMasterEditorAjaxLookup.js](fa_master-editor-ajax-lookup.html)

3.`API форм` (набор операций для работы с основными технологическими web-формами).
* [jQuery.icsEditForm](fa_editform.html)

## Конвенция именования

Имена всех файлов JS API должны соответствовать формату `jquery.icsName.js`, где `Name` - имя плагина JS API.

Если у плагина имеются стилевые CSS файлы, то они должны называться аналогично: `jquery.icsName.css`.
