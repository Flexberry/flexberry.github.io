--- 
title: E-submission 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View, edit form, groupedit, performance 
summary: Features edit form 
toc: true 
permalink: en/fd_e-view.html 
lang: en 
autotranslated: true 
hash: ae9d6e37a9c240bc22714e16df4e16abb2fe57eb5b5fac9e88cb67b0065bf754 
--- 

Used for [forms editing and GroupEdit](fd_editform.html). GroupEdit is a fast edit object and may have the functionality to open the current object in the edit form. However, other attributes, invisible in GroupEdit'e must also be filled with values. Therefore, to edit form and to GroupEdit'and must be used in one view 

## Setting 

[Visibility](fd_hidden-properties-view.html) is responsible for the list of fields in GroupEdit, as well as for the presence of attributes on the edit form. When generating the edit form it will be located in the attributes, which is a sign `Видимый`. 

Fields [audit](efs_audit.html) not needed, because the functionality to display audit data from the form E itself reads audit data. 
The path describes the location of the fields on the form (`|` tab, `-` group `\` division, for example: `|Description\-Стоимость` specifies that the attribute must be in the group `Стоимость` located on the tab `Описание`). 

Group (groupBox) are used to group logically related objects. Recommended to be grouped in the following categories of attributes: 

* Belong to the same master. 

**Example**: group - `Статья УК`, attributes – `Наименование`, `Номер`, `Часть`, `Пункт`. 

![](/images/pages/products/flexberry-designer/views/e-view1.jpg) 


If the master is only one attribute, such as» «Name, then create a separate group (groupBox) is not recommended. 

* Related time field. 

**Example 1**: `Время передачи`, `Время выезда`, `Время beginning работ`, `Время the end работ`. 

![](/images/pages/products/flexberry-designer/views/e-view2.jpg) 


**Example 2**: the attributes `КогдаПроизошлоПроисш`, `КогдаПроизошлоПроисшПо` can be combined into a group `Время происшествия` and use within the group headers and `С` `По`. 

![](/images/pages/products/flexberry-designer/views/e-view3.jpg) 


* All the numerical characteristics that describe the object. For example: group – `Силы and средства`, field - `Кол in personal состава`, ``Кол in the car. техники`, `Кол in engineering техники`, `Кол-in specials. техники`. 

![](/images/pages/products/flexberry-designer/views/e-view4.jpg) 


Bookmarking this files most often by detali, each of which corresponds to a separate tab. For example: bookmark `Реагирование` 

![](/images/pages/products/flexberry-designer/views/e-view5.jpg) 


Or tab makes a set of logically related attributes, to save space on the form and reduce the size of the form. For example: bookmark `Место` 

![](/images/pages/products/flexberry-designer/views/e-view6.jpg) 


It is also possible to combine approaches within the bookmark `Текущая ситуация` can make GroupEdit `Ситуации`, as well as some characteristics such as `Кол in пострадавших`, `Кол in потерпевших`, `Ранг пожара`, `Площадь`, etc. 

![](/images/pages/products/flexberry-designer/views/e-view7.jpg) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}