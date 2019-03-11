--- 
title: WebControlProvider 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_web-control-provider.html 
lang: en 
autotranslated: true 
hash: 78d942e11a819bc1752b1a17b4fc335b76492072ce48b97a2aa5b7e9fc17561d 
--- 

`WebControlProvider` is a provider that allows you to configure controls to display object properties for list controls (e.g., [WebObjectListView](fa_web-object-list-view.html), [AjaxGroupEdit](fa_ajax-group-edit.html)). All its settings are stored in the file /xml/WebControlProvider.xml 

## Settings 

There are 2 way settings: 

1.Specify the type of control for any type (XML element `propertytype`), for example 

```xml
  <propertytype name="Boolean">
    <control typename="System.Web.UI.WebControls.CheckBox, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Checked" codefile="" />
    <editcontrol ... />
    <filtercontrol ... />
  </propertytype>
``` 

2.Specify the type of the control property to a specific type (XML element `customproperty`), for example 

```xml
  <customproperty class="Address" property="Pervichnykh">
    <control typename="IIS.ISOGD.Controls.The Partials.ArcMapViewControl" property="PrimaryKey" codefile="~/Controls/Partials/ArcMapViewControl.ascx" /> 
    <editcontrol ... />
    <filtercontrol .. />
  </customproperty>
``` 

* control - the control that will be used for display (for example, in [WebObjectListView](fa_web-object-list-view.html)) or for artisan properties in [AjaxGroupEdit](fa_ajax-group-edit.html); 
* editcontrol is the control that will be used for editing (for example, in [AjaxGroupEdit](fa_ajax-group-edit.html)); 
* filtercontrol control to be used for filtering in [WebObjectListView](fa_web-object-list-view.html); 

In the tag `<control /> (<editcontrol />,<filtercontrol />)` shall include: 

* `typename` - type контрола; 
* `property` - property control, which bendida the value in the list (in the particular cell where you want the control); 
* `codefile` - the path to the. ASCX file of the control (only for ASCX controls); 

{% include important.html content="it is Important to remember that `customproperty` has b**LSI priority than `propertytype`. 

1. First searched for control specific properties. 
2. If the setting is not found, it looks for a control for the type of this property. 
"%} 

{% include note.html content="the Artisans of the properties in [AjaxGroupEdit](fa_ajax-group-edit.html) are always taken only from control, not from the editcontrol." %} 

## Embedding controls 

You can specify the control for viewing and for editing (for example, in [AjaxGroupEdit](fa_ajax-group-edit.html)). 

If you have developed a custom control that is used on edit forms and want to embed it in [WebObjectListView](fa_web-object-list-view.html). It may be a problem with the fact that in [WebObjectListView](fa_web-object-list-view.html) it looks like the control for data entry, and planned to use it for display only.In such cases, you can implement a property `Enabled` have control, and when control will be embedded in WOLV, he automatically put down `Enabled = false`. 

## Example 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root>
  <propertytype name="Boolean">
    <control typename="System.Web.UI.WebControls.CheckBox, System.Web, Version=2.0.0.0, Culture=neutral, PublicKeyToken=b03f5f7f11d50a3a" property="Checked" codefile=""/>
  </propertytype>
  <propertytype name="NullableDateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="The DatePicker.ascx"/>
  </propertytype>
  <propertytype name="DateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="The DatePicker.ascx"/>
  </propertytype>
  <customproperty class="Address" property="Pervichnykh">
    <control typename="IIS.ISOGD.Controls.The Partials.ArcMapViewControl" property="PrimaryKey" codefile="~/Controls/Partials/ArcMapViewControl.ascx" /> 
  </customproperty>
</root>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}