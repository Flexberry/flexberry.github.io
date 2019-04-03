---
title: AjaxGroupEdit
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP NET, JavaScript API, Web UI (Controls)
toc: true
permalink: en/fa_ajax-group-edit.html
lang: en
autotranslated: true
hash: e11ed7ca2486f8d159ade398fd1679799d19f91fef612de854c0f616e4be0701
---

`AjaxGroupEdit` - control that allows to edit many objects. Is analogous to [GroupEdit](fw_group-edit.html) for Web applications.

## Customize the appearance of control

### Customize the appearance of tables in the theme BlueSky

The topic BlueSky was added 2 version of the coloring table:

* The default table:

![Table-default](/images/pages/products/flexberry-aspnet/controls/groupedit/bluesky-default-age.png)

* The default table:

![Table-default](/images/pages/products/flexberry-aspnet/controls/groupedit/bluesky-classic-age.png)

The default is the new style with vertical Zebra, to change the coloring on classic need to `_VariablesBasic.less` to change the value of the variable `@BlueSkyTableStyle` on `false`.

{% include important.html content="this also will change the table style WOLV" %}

## Configure the controls inside AGE

Configure the controls inside the AGE described in [configuring the controls inside AjaxGroupEdit](fa_controls-age.html).

### The limit for LookUp in AjaxGroupEdit

The use of restraints to LookUp in AjaxGroupEdit described in [article Limit for LookUp in AjaxGroupEdit](fa_limited-lookup-age.html).

### Settings for LookUp in AjaxGroupEdit

Settings for LookUp in AjaxGroupEdit described in the corresponding [article](fa_settings-lookup-age.html).

## CSS classes AGE

Description CSS classes of AGE can be cleaned in [article CSS classes AjaxGroupEdit](fa_age-css.html).

## Operations

Description of operations AGE can be read in [the article Operation AjaxGroupEdit](fa_age-operations.html).

## Events

Description of the event AGE can be read in [article Events AjaxGroupEdit](fa_age-events.html).

## Add new object

To create the object in the WGE has the last line for data entry. In order to completely hide, you need to specify not only `Add = false`, but `PlusInRow = false`

```csharp
ctrlMyWebGroupEdit.Operations.Add = false;
ctrlMyWebGroupEdit.Operations.PlusInRow = false;
```

### Adding objects to AjaxGroupEdit at form initialization (new object)

Description of the algorithm add can be read in [the article Adding objects to AjaxGroupEdit at form initialization (new object)](fa_add-objects-age-initialization.html).

## Edit features

There is the possibility of opening the edit form of the objects AGE in a separate window. In detail, this possibility is set out in [article open the edit window in AjaxGroupEdit](fa_open-windows-age.html).

## Order attributes

If the view have a order attribute, AjaxGroupEdit will reset the sort, and would impose sorting on this attribute. In the toolbar added 2 buttons to move rows up and down, which reduce\increase the value of the order attribute.

{% include warning.html content="the Attribute `Order` exhibited through Flexberry or added manually in code: `[Order()]`

The Order attribute applies only to fields of type `int`." %}

## Group

Algorithm description settings group is described in [configuring groups in AjaxGroupEdit](fa_grouping-age.html).

## Enable Read-only mode for an individual column

Algorithm description configure Read-only mode for individual columns are described in [article Enable Read-only mode for an individual column AGE](fa_read-only-age.html).

## Display HTML values

In order to correctly display HTML values in the cells, it is necessary for the attribute to put the attribute

```html
[IsHTML()|IsHTML()]
```

That is, similarly to [HTML AjaxLookup](fa_master-editor-ajax-lookup.html) [WOLV](fa_web-object-list-view.html).

## JavaScript API

For manipulation of AGE on the client side you should use AGE JS API, which is a jQuery plugin (`ajaxgroupedit`).

### Methods

| Name | Parameters | Description|
|:---------------------|:-------------------|:----------------------------------------------|
| `getDataRows`| `selector`| Method that returns all `tr`, except for the remote. If `selector` takes the value "all", then the deleted rows will not be deleted.|
| `addNewRow` | `ИмяПараметра1` - a description of the purpose | create a new line edit.|
| `updateVisualState`| `$container`/ `statusValue`| Method, affix a "star" for edited values and hiding the remote.|
| `addDependedLookups`| `settings`| Adding dependent lyapov (see below).|
| `deleteRows`| `rows` - jQuery object or array of HtmlElement corresponding to the deleted rows and Remove the specified rows.|
| `deleteAllRows`| | Delete all rows.|

### Examples of usage

#### Add a new line

```javascript
 $('#<%=ctrlКомната.ClientID %>').ajaxgroupedit('addNewRow');
```

#### Deleting all rows

```javascript
<script type="text/javascript">
    $(document).ready(function () {
        $('span#delAllRows').click(function () {
            $('#<%= ctrlКвартира.ClientID %>').ajaxgroupedit('deleteAllRows');
        });
    });
</script>
```

#### Count the number of rows

Use the method `getDataRows`:

```javascript
<script type="text/javascript">
    function getRows() {
        var data = $('#<%=ctrlПодзадача.ClientID%>').ajaxgroupedit('getDataRows');
        if (data.length != 0) {
            var result = '';
            $.each(data, function(index, value) {
                result += value.innerHTML;
            });
            alert('Записей в списке: ' + data.length + '\n' + result);
        } else {
            alert('В списке нет записей.');
        }
    };
</script>
```

### Dependent lucapa inside AGE

For implementation dependent lyapov there is a method `addDependedLookups`:

```javascript
<script type="text/javascript">
    $(function () {
        $('#<%=ctrlTestLookUpD.ClientID%>').ajaxgroupedit('addDependedLookups', {
            master: '<%=ICSSoft.STORMNET.Information.ExtractPropertyName<WebFormsTestStand.TestLookUpD>(x=>x.TestLookUpA1)%>',
            depended: '<%=ICSSoft.STORMNET.Information.ExtractPropertyName<WebFormsTestStand.TestLookUpD>(x=>x.TestLookUpA2)%>',
            url: '~/Forms/Controls/AjaxGroupEdit/JavaScriptApiTests/TestLinkedLookUpInAGE.aspx',
            method: 'GetPageMethod'
        });
    });
</script>
```

Here:

* `master` - the name of the property that is responsible for artisan lucap
* `depended` - the name of the property that is responsible for the dependent lookup
* `url` - url, which will go to queries if you change the values in Masterova lucapa
* `method` method, which will go to queries if you change the values in Masterova lucapa

While in server side code must contain a description of the method, limiting associated lucapa:

```csharp
// <summary> 
/// Server method to handle dependent lyapov on the client. 
/// </summary> 
/// <param name="lfKey"> 
/// The lf key. 
/// </param> 
/// <param name="masterPk"> 
/// The master pk. 
/// </param> 
/// <returns> 
/// The <see cref="string"/>. 
/// </returns> 
[System.Web.Services.WebMethod]
protected void GetPageMethod(string lfKey, string masterPk)
{
}
```

An example of a [dependent lyapov](fa_change-lcs-lookup-age.html).

## Possible errors

* Not saved changes (e.g., added features):
* Make sure the page has no js ошибок;
* Check whether the method is called the preservation AGE, which is stored in `document.WgeSaveHandlers`.

For example, by default a masterpage.js:

```javascript
if (typeof document.WgeSaveHandlers !== 'undefined') {
  var resHandler;
  $.each(document.WgeSaveHandlers, function (i, handler) {
    resHandler = handler();
    if (resHandler == false) {
      good = false;
      return;
    }
  });
}
```

## Customizing

Description of settings contained in [article Customizing AjaxGroupEdit](fa_age-user-settings.html).

## The embedding application controls

Information about embedding application controls can be read in [the article Embedding application controls in AjaxGroupEdit](fa_age-applied-controls.html).

## Multiple LookUp in AGE

Information about multiple LookUp'Ah can be read in [the article Using multiple choice. in AjaxGroupEdit](fa_multi-lookup-age.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}