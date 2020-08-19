---
title: MasterEditorAjaxDropDown
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP NET, JavaScript API
toc: true
permalink: en/fa_master-editor-ajax-dropdown.html
lang: en
autotranslated: true
hash: 81132642599757f48c780c1a6b0d6aa789eb45115d06863f5d3e14d7f6cec73f
---

`MasterEditorAjaxDropDown` is [web control](fa_web-controls.html), which allows you to view the list of available artisan objects in a drop-down list.

Below shows an example of displaying this control on web form.

![](/images/pages/products/flexberry-aspnet/on-form.png)

## Connection

In order to use `MasterEditorAjaxDropDown` on the edit form, you need to [edit view in Flexberry Designer to choose the type of lucapa `combo`](fd_view-edit-form.html), and the properties of the wizard to specify preferred display object property of the master.

To embed this control in AGE](fa_age-applied-controls.html), you want to make settings? similar to [this](fa_age-applied-controls.html). Thus in [WebControlProvider.xml](fa_web-control-provider.html) will appear to record:

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="WebControlProvider.xsd">

	...

  <!--Property TestSpecialControlLink the class LimitEditorDetail2 edited using MasterEditorAjaxDropDown.-->
  <customproperty class="LimitEditorDetail2" property="TestSpecialControlLink">
    <control typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxDropDown, ICSSoft.STORMNET.Web.AjaxControls" property="SelectedMasterPK" codefile=""/>
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxDropDown, ICSSoft.STORMNET.Web.AjaxControls" codefile="" property="SelectedMasterPK"/>
  </customproperty>
</root>
```

## Use

Use `MasterEditorAjaxDropDown` appropriate when it is known beforehand that the number of craftsmen small and there is no need to select them to raise a separate form of choice.

### Sample code

After [generate code objects and shapes](fa_asp-net-generator.html) appear in the structure view:

in objects:

```csharp
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailview", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```

on the web form (if control not [built-in AGE](fa_age-applied-controls.html)):

```csharp
<ac:MasterEditorAjaxDropDown ID="ctrlTestSpecialControlLink" CssClass="descTxt" runat="server" EnablePostBack="false"/>
```

### Settings control

#### Binding controls edit masters

How to bind web controls edit masters (as, for example, `MasterEditorAjaxDropDown`) described in [article Linking controls edit masters](fa_linked-master-editors.html).

#### Uniform change settings

For a uniform change settings implemented two static delegate:

1. `InitDropDownSettings` - Delegate to initialize the settings. Settings you can override in the. aspx forms.
2. `ChangeDropDownSettings` - Delegate to change settings, which allows you to bring all MasterEditorAjaxDropDown to the uniform mind.

Example: you want to subscribe in `Global.asax`:

```csharp
MasterEditorAjaxDropDown.ChangeDropDownSettings = ChangeMasterEditorAjaxDropDownSettings;
```

```csharp
/// <summary> 
/// The delegate to change the settings of <see cref="MasterEditorAjaxDropDown"/>. 
/// </summary> 
/// <param name="dropdown"> 
/// An instance of <see cref="MasterEditorAjaxDropDown"/> whose settings you want to change. 
/// </param> 
public static void ChangeMasterEditorAjaxDropDownSettings(MasterEditorAjaxDropDown dropdown)
{
    var type = Type.GetType(dropdown.MasterTypeName);
    
    // Filter all kinds of finish only on current values 
    if (type != null && typeof(ВидОтделки).IsAssignableFrom(type))
    {
        Function actualTypesLimitFunction = FunctionBuilder.BuildEquals<ВидОтделки>(x => x.Актуально, true);
        dropdown.LimitFunction = dropdown.LimitFunction != null 
            ? FunctionBuilder.BuildAnd(                
                dropdown.LimitFunction,
                actualTypesLimitFunction)
            : actualTypesLimitFunction;
    }
}
```

## JS API

To manipulate `MasterEditorAjaxDropDown` on the client side you should use [JS API](fa_javascript-api.html), which is a jQuery plugin (`icsMasterEditorAjaxDropDown`).

### Setting `MasterEditorAjaxDropDown`

To set the value `MasterEditorAjaxDropDown` you can use the method `val`.

For example, if the button is pressed `changeMasterDropDownValue` have to change the value `MasterEditorAjaxDropDown` `ctrlTestSpecialControlLink` specified in the control `masterDropDownValues`, we will use the following code:

```javascript
$(document).ready(function () {
	$('#<%= changeMasterDropDownValue.ClientID %>').click(function () {
		$('#<%=ctrlTestSpecialControlLink.ClientID%>').icsMasterEditorAjaxDropDown('val', $('#<%=masterDropDownValues.ClientID%>').val());
		return false;
	});
});
```

## Possible errors

If `MasterEditorAjaxDropDown` displays not only the value of artisan properties artisan and the entire object as a whole, it most likely was not finished [setting lucapa in the view Designer in Flexberry](fd_view-edit-form.html).

![](/images/pages/products/flexberry-aspnet/on-form-bad.png)

You can check this by making sure that the corresponding data object is a record:

```csharp
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailview", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}