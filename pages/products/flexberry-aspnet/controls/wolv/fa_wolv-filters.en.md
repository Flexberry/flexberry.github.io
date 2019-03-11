--- 
title: Filters WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-filters.html 
lang: en 
autotranslated: true 
hash: 6f4c8ad470063de62f895e6a9d13316a0ad0ada8ca50ecab396b4f6b93f6d7d4 
--- 

If you enable the setting up [WOLV](fa_web-object-list-view.html) `Filter=true`, the toolbar button will appear with filters. When you press the button you will see the first line where you can apply filters to. The first control cell filter operation, the second the value. Especially treated type properties `bool` and heirs `Enum`: for Boolean fields are generated `DropDownList` with three values - `Пусто`, `Да`, `Нет` for `Enum` - `DropDownList` with the range of enumeration values (caption's). 

In order to use your control in filters, you need to: 

* to control to implement the interface `IFilterControl` 
* add to WebControlProvider.xml to the appropriate type line: 

```xml
    ...
    <propertytype name="Imathia">
      <control .../>
      <editcontrol ...>
      <filtercontrol typename="Imediacontrol" codefile="Talktalktalk" />
    </propertytype>
    ...
    ``` 

* If you need the ability to manually determine the limit function for the specific control of the filter, then you need to implement the interface `ICSSoft.STORMNET.Web.Tools.IComparableFilterControl`. 

That is, the control filter can be set similarly to the control for editing or viewing, not the name of a data property, because 
used interface. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-filters.png) 

When you click on the cell right-click context menu appears in which you can apply a filter to the column. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-context-filters.png) 

## Controls for filtering 

As controls for filters at the moment can be used: 

* [AlphaNumericTextBox](fa_alpha-numeric-textbox.html) 
* [DatePicker](fa_date-picker.html) 
* [MasterEditorLinkedAjaxLookUp](fa_master-editor-linked-ajax-lookup.html) 

The default properties of different types will be used the following controls for filtering (used for controls of various types can be overridden in the file `WebControlProvider.xml`): 

* For properties with types `int`, `Nullable<int>` and `NullableInt` used [AlphaNumericTextBox](fa_alpha-numeric-textbox.html) 
* For properties with types `DateTime`, `Nullable<DateTime>` and `NullableDateTime` use [DatePicker](fa_date-picker.html) 
* For properties with types `bool` and `Enum` used `DropDownList` 
* For properties with other types of used `TextBox` 

## filtering Rules 

Rules for filtering items in list forms: 

* if you enter incorrect values (including numeric) filtering, all operations will return an empty list of items and will display an appropriate message to the user 
* empty value (except for fields with a type that allows the value `null`) would be treated the same as incorrect (if the selected filtering operation) 
* for string fields, or fields that allow `null` value, an empty value is considered as null when this operation `больше равно` and `меньше равно` will not limit the list 
* exceptions in case of incorrect values in the General case will not be thrown 

{% include note.html content="Exception is thrown only in the case where the control used to filter in WOLV, throws an exception when setting incorrect value to the Text property (because of the mechanism of ASP.NET)." %} 

### Features of working with filters 

Features of the symbol `*`: 

* if the filter is superimposed on a field of string type, then `*` defines any number of characters, i.e.: 

* `*123` will look for all lines ending in `123`. 
* `123*` will search for all lines beginning with `123`. 
* `*123*` will search all strings that contain a substring `123`. 

* if the filter is superimposed on the field of non-string type, `*` is not interpreted in any special way (for example, if the numeric column to look for `*123*`, nothing will be found, since no number is presented in a similar sequence of characters). 

### Transfer of filter parameters via a GET request 

The filter parameters can be transmitted via GET request, it is possible to specify the necessary parameter in the address bar and thus to restrict the list. In the future, you can configure quick access to the list via a link or control. String of a GET request as follows: 

`?WOLF_WebObjectListView1=<Numerology>:<Filter>`. 

Numbering of columns runs from left to right and starts with `0` (not including the column of buttons). 

{% include warning.html content="Considered all the columns of the list, not just displayed on the page." %} 

Use a separator `|` between the parameters, in this case, the settings are merged according to the principle `И`. 

In the result of specified filter values will be indicated in the appropriate column. 

For example, there is a list of apartments: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/apartments1.png) 

You must obtain a restricted list: "Apartment No. 1 standard finishing." The page doesn't display all columns. The column "Number" scoreless in the default list, column `Вид отделки` - tenth. A GET request will look like `?WOLF_WebObjectListView1=0:1/10:Стандартная`. 

The resulting list will be as follows: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/apartments2.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}