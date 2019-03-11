--- 
title: date display Format by default 
sidebar: flexberry-winforms_sidebar 
keywords: DateTime (for dates) 
summary: See how to use the settings in the config to set the date format to display in ObjectListView 
toc: true 
permalink: en/fw_date-format.html 
lang: en 
autotranslated: true 
hash: 04372c0413c8134fd77c002b93b01e3f8f5919346ee70f8212b22530008f00d4 
--- 

To set the date display format by default(for example, `dd.MM.yyyy HH:mm`) in the application(in GroupEdit'Ah, in ObjectListView), the config need to add a line 


`<add key="NullableDateTimeDefaultFormat" value="dd.MM.yyyy HH:mm" />` 

By default, the format `short`. 

As DateTimePicker, located in GroupEdit form and have its own settings format, this setting in the config actually only affects the display in ObjectListView.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}