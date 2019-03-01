--- 
title: date display Format by default 
sidebar: flexberry-winforms_sidebar 
keywords: DateTime (for dates) 
summary: See how to use the settings in the config to set the date format to display in ObjectListView 
toc: true 
permalink: en/fw_date-format.html 
lang: en 
autotranslated: true 
hash: 506792fed5eda49be2ecdc3244fe4225caab9d534dffdf568871814351a232bc 
--- 

To set the date display format by default(for example, `dd.MM.yyyy HH:mm`) in the application(in GroupEdit'Ah, in ObjectListView), the config need to add a line 


`<add key="NullableDateTimeDefaultFormat" value="dd.MM.yyyy HH:mm" />` 

By default, the format `short`. 

As DateTimePicker, located in GroupEdit form and have its own settings format, this setting in the config actually only affects the display in ObjectListView.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/