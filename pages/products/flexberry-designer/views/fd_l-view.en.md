--- 
title: list View 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View, list form, ObjectListView, WebObjectListView, list, view 
summary: Features list form 
toc: true 
permalink: en/fd_l-view.html 
lang: en 
autotranslated: true 
hash: 28ed4bde22dc6bc72c91e56a42f43d8fc52ecc25c527960c736e89c191edc9df 
--- 

Used on [list of forms](fd_listform.html) (property ViewName component [ObjectListView](fw_objectlistview.html), [WebObjectListView](fa_web-object-list-view.html) and [Flexberry ObjectListView](ef2_object-list-view.html)). 

## Setting 

[Visibility](fd_hidden-properties-view.html) describes the list of fields that appear on the list (invisible attributes are used, for example, for the formation of a [computable](fo_not-stored-attributes.html) fields). 

For lists of reference fields [audit](efs_audit.html) are not submitted. In list view, data object audit fields rendered visible. 

The title should be clear about the whole list. For the properties of the master write the full name in the first place the property of the master, then the name of the master in the genitive (for example: the Applicant.The post – `Должность заявителя`). 

However, this does not always lead to the best result (for example: Airesprovince.Street – street address of residence), in such cases, the applicable reduction (for example: master `Адрес проживания`, properties master `Территория проживания`, `Улица проживания`). 

The names of the artisans references references should normally match the name of the directory (for example: Videobite.Name – `Вид события`) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}