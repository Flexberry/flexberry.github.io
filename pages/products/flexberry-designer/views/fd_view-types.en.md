--- 
title: Principal submissions 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View, views, types of views 
summary: Types of submissions and working with them 
toc: true 
permalink: en/fd_view-types.html 
lang: en 
autotranslated: true 
hash: bf15a171444612b8206e9e121258528a5eec3f9b32a9fa39b7fecb907a58ce57 
--- 

Definition __presentation__ described in the article [view definition](fd_view-definition.html). 

## Types of representations 

In each class, when it was created, should be created __three__ main view ([E](fd_e-view.html), [L](fd_l-view.html), [T](fd_t-view.html)), and, if necessary, Decalogue the representation ([D](fd_d-view.html)). 
__Note__: when you use the quick prototypization performance E and L are created automatically. 

As the name uses the class name and Postfix. The name must satisfy the pattern: <Classname> <Postfix> (for example: Videobytes, ВидСобытияL, Videobytes). 

Postfix | Name | Application 
:-----------------|:-------------------|:--------------------------------------------------- 
[E](fd_e-view.html) | View to edit | Use [form of editing](fd_editform.html) [GroupEdit](fw_group-edit.html), [AjaxGroupEdit](fa_ajax-group-edit.html). GroupEdit is a fast edit object and may have the functionality to open the current object in the edit form. However, other attributes, invisible in GroupEdit'e must also be filled with values. Therefore, to edit form and to GroupEdit'and must be used in one view 
[L](fd_l-view.html) | List view | Use [forms list](fd_listform.html) (property ViewName component [ObjectListView](fw_objectlistview.html), [WebObjectListView](fa_web-object-list-view.html)) 
[T](fd_t-view.html) | Wood view | is Used to search in the [filters](fw_filtersand-limits.html), and it is used for building restrictions on the lists 
[D](fd_d-view.html) | Decalogue performance | is Used for searching in the filters, and it is used to build restriction on the lists for attributes [detail](fo_detail-associations-properties.html). 

Despite the fact that the representation T is used to build restriction in the fields list that is loaded in another view (view L), it may be different in composition attributes. Presentation L may contain fewer attributes, because when you read will automatically take into account the attributes that are in the view, Etc 

View D differs from the view of T only, that does not contain the attributes of the aggregator. Since the representation D is also used to build the constraints on the lists, then his attributes must also be present in the representation of L.

## Basic rules of use 

* All attributes must have a human-readable title describing the essence of the attribute, regardless of the [visibility](fd_hidden-properties-view.html) attribute. The title is capitalized, words are separated (for example: the attribute – `ТипМестаПроисш`, title - `Тип place происшествия`). 
* Standard abbreviations are allowed, the main criterion of which is the absence of periods, such as: number, name (for example: `ФИО заявителя`). 
If the title is very long, allowed abbreviations to comply with the reasonable length of the title (for example: `Кол in unknown. пострадавших`, `Доп. features места`). 
The reduction should not be ambiguous or unclear. A bad example is `Кол-l/с` because it can be interpreted as `Количество horse сил`, although it was implicit `Количество personal состава`. In this case, it is recommended to use the full name (`“number of personal состава`). 

Important the order of the attributes. For filters of order in the view to determine the sequence order when displaying. For lists and GroupEdit's order in view determines the order the first time you open (the user can customize the order of the attributes in lists and GroupEdit'Oh). 






{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}