---
title: the edit Form submissions
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, View, submission, master, detail, editing form attributes
summary: view customization
toc: true
permalink: en/fd_view-edit-form.html
lang: en
autotranslated: true
hash: 1349e17cf606b0ff0c3389492fed5e32df31be7f426ba77a59dd029d0468e76d
---

[View](fd_view-definition.html) describes some logical combination of:

* [own class attributes](fo_attributes-class-data.html),
* [masters](fd_master-association.html) of any depth and their attributes
* representations [of datalow](fo_detail-associations-properties.html).

Therefore, for easy editing [view](fd_view-definition.html) uses a special form editor.
This form is invoked from the tab `Представления` when editing [class properties](fd_data-classes.html).

### Call edit form submissions

* Open a class diagram

![Example](/images/pages/products/flexberry-designer/views/form-edit-view1.jpg)

* Choose a class, selcom the right mouse button on it to open the context menu
* Select edit properties

![Example](/images/pages/products/flexberry-designer/views/form-edit-view2.jpg)

* In the opened window `Class (edit)` go to the tab `Представления`;
* Select the row in the row click on the cell `Properties`. In the right corner of the cell button should appear `...`
* Click `...` (This will cause the edit form submissions)

![Example](/images/pages/products/flexberry-designer/views/form-edit-view3.jpg)

### Bookmark edit form submissions

The form has the following tabs:

* Bookmark `Атрибуты` to edit the list of [private](fo_attributes-class-data.html) and workmen of the attributes in the view
* Закладка`Представления` to specify views [metalowych classes](fo_detail-associations-properties.html).

#### The Attributes Tab Page

![Example](/images/pages/products/flexberry-designer/views/vieweditor1.jpg)

In the left list are all private attributes of a class](fo_attributes-class-data.html) and also [in connection with the mechanics of objects](fd_master-association.html) revealing their hierarchy, you can `добраться` to the attributes of the mechanics of objects. The right list contains attributes that are included in the presentation. Add/decrease of attributes in the show is a selection of buttons `влево`, `вправо`. The order of the attributes in the submission buttons `вверх`, `вниз` that allow you to move in the list currently highlighted by the cursor attributes.

Moving the cursor on the right list, you can set properties for a specific attribute in the view:

* `Заголовок` - specifies a title for the forms.
* Button `Создать` allows you to generate a certain title from the full name of the attributes.
* `Путь` - specifies the path (location) for the control providing an indication of attribute values by the user on the edit form. The path is a string of the form:

```code
@@[-|)[Имя)[\ [-|)[Имя))@@
```

* `-` refers to grouping (GroupBox),
* `|` - tab (Tab) on the edit forms.
* `\` — the path separator.
* `Имя` — the name of the corresponding bookmark/groups. This setting affects the automatic placement.
* `Видимость` - if you uncheck this box, the attribute in a [view](fd_view-definition.html) will be listed as hidden.

When the selected attribute is a master, there are three additional properties intended for additional wizard in a [view](fd_view-definition.html) (the result is generated in the source code attribute [MasterViewDefineAttribute](fd_view-definition.html)):

* `Тип лукапа` - type selection (`lookup type`) of the workman of the object:
 * `standard` - standard,
 * `combo` in a dropdown list
 * `default` - attribute `MasterViewDefineAttribute` do not generated in code
* `Свойство мастера` is the name of the attribute in the selection type `LookupTypeEnum.Combo`.
* `Настройка выборки` - Line initialization of the object (control) selection of the workman of the object.

#### Properties of interfaces in performance

In [Flexberry Designer](fd_flexberry-designer.html) is supported by adding a [view](fd_view-definition.html) properties declared at the [interfaces](fd_interfaces.html), from which inherits the [classes of objects](fd_data-classes.html).

For example, suppose there is a chart of the form:

![Example](/images/pages/products/flexberry-designer/views/interface-inheritance.png)

Then, when the job submission form for a class `КлассСОбъектами` will be available to all properties inherited from interfaces:

![Example](/images/pages/products/flexberry-designer/views/intInh-properties.png)

#### Bookmark `Детейлы`

Button `Добавить детейл`, `Удалить`, allow, respectively, to put in a [view](fd_view-definition.html) any [detail](fo_detail-associations-properties.html) or remove from the [views](fd_view-definition.html). The list contains the existing view detaily. For each specified detail in the source code is generated specifying the attribute [AssociatedDetailViewAttribute](fd_view-definition.html).

Moving the mouse over the list, you can configure the properties that are located in the right part of the form:

* `Заголовок`, `Путь`, `Видимость` - similar to the same properties for the attributes.
* `Представление` - include a [view](fd_view-definition.html)[metalowego class](fo_detail-associations-properties.html) (remember that in a [view](fd_view-definition.html)[hats](fd_key-concepts.html) turn on [view](fd_view-definition.html)[detail](fo_detail-associations-properties.html) and not himself).
* `Загружать with шапкой` - when loading service data object [cap](fd_key-concepts.html) this [view](fd_view-definition.html), will read [detaily](fo_detail-associations-properties.html) this [view](fd_view-definition.html).

![Example](/images/pages/products/flexberry-designer/views/vieweditor2.jpg)

### Hotkeys

![Example](/images/pages/products/flexberry-designer/views/view-edit-from.jpg)

Consider the example properties `Подразделение.Название`

It is necessary to allocate property is added to the view and click one of the corresponding combinations:

* `Ctrl D` - Create a title (`Название Подразделение`)
* `Ctrl T` - Create a title with word breaking (`Название подразделение`)
* `Ctrl Sift T` - Create a title with the word-breaking, deleting the names of the artisans (`Название`)
* `Del` is to remove the attribute from the view



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}