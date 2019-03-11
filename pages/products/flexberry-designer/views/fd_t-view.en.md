--- 
title: T-view 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View, submission, master, generation, audit 
summary: Performance for filters and restrictions 
toc: true 
permalink: en/fd_t-view.html 
lang: en 
autotranslated: true 
hash: 0ca213dfe4e55d445328f4c6817fe51da96f7b519216fd24542f87d66c6cc2c1 
--- 

Created among a [view](fd_key-concepts.html) emit T-the view ([view](fd_key-concepts.html) with the name of «<class-name>T», such as the» «Uchenick). 

According to an informal agreement T-view - this view contains all the object's fields, and all [masters](fo_masters-details.html). 

T-view can be seen e.g. in [component creation limits](fw_limitation-editform.html): if [filter settings](fw_filter-settings.html) has [generated](fw_filtersand-limits.html), [standard features](fw_standart-view-limits-editor.html) there is a tree structure similar to the T-representation. 

## Settings 

[Visibility](fd_hidden-properties-view.html) does not affect the display of attributes, since all fields will be visible regardless of the value of the field» «Visibility. By default, the visibility must be set for all attributes. 

Fields [audit](efs_audit.html) should be the only object for which the view is created. Fields audit masters in this view must not be specified. 

Important the order of the attributes. The order determines the order of attributes in the filter. 

The title should be understood with respect to only the object wizard (because of the hierarchy view). For the properties of the master write only the names of the properties (for example: master `Адрес проживания`, properties master `Территория `, `Улица`). 

In the case where the attribute of the master is duplicated string attribute, have the first string field, then the master, the title master is formed as follows: <Name> ` (from the directory)`. For example: a string field – Dalnostroy, header string fields - `Должность`, master Job title master `Должность (from the directory)`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}