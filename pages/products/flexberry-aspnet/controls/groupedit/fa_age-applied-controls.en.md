--- 
title: Embedding application controls in AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_age-applied-controls.html 
lang: en 
autotranslated: true 
hash: 009381278156d0b701c1d5a98b0f15fa24492d7854e5c43c47ded16f5920a1da 
--- 

## Binding 

For correct operation of control [AjaxGroupEdit](fa_ajax-group-edit.html), it must implement the interface `IAjaxGroupEditCompatible`: 

```csharp
namespace ICSSoft.STORMNET.Web.AjaxControls
{
    /// <summary> 
    /// Controls that implement this interface can be embedded in AjaxGroupEdit 
    /// </summary> 
    public interface IAjaxGroupEditCompatible : IGroupEditCompatible
    {
        /// <summary> 
        /// The property to which bendida the value in the column AjaxGroupEdit. 
        /// Put down by AjaxGroupEdit, control only uses the value. 
        /// </summary> 
        string BindingPropertyName { get; set; }

        /// <summary> 
        /// Indicator that is set to true if the control is inside in AjaxGroupEdit. 
        /// Put down by AjaxGroupEdit. 
        /// </summary> 
        bool IsInsideEditor { get; set; }

        /// <summary> 
        /// Generate or not the standard structure for the client binding. 
        /// Simplifies the development of controls with a simple bindinga. 
        /// </summary> 
        bool GenerateDefaultStructForBinding { get; }

        /// <summary> 
        /// The list of external scripts to attach. 
        /// All these scripts in AjaxGroupEdit will connect through the ContextHelper. 
        /// For Example "AjaxDataService.js" 
        /// </summary> 
        List<string> ExternalScriptPaths { get; }

        /// <summary> 
        /// The text of the scripts which will be executed on document ready AjaxGroupEdit 
        /// </summary> 
        List<string> DocumentReadyScripts { get; }
    }
}
``` 

An important aspect of working AGE is the client binding that is triggered in javascript. 

It works as follows: 
1. Close control should be a json structure containing properties `Property` - property of the DataObject and `ID` - ID of the control on the client 
2. By clicking on the button `сохранить` the value from the control on the client using js will create a new json structure, which will go with the postback to handle server-side `WebBinder`; 

If you enable `GenerateDefaultStructForBinding=true`, will generate a structure like 

```csharp
{"Property":"Dataromance", "ID" : "ctrl306940bb5b9e4bb98d7c93989c6ae9ed_ctrlдатарождения_ctrl"}
``` 

If you create a custom control with a complex client bindinga, to generate this structure will need themselves, checking the value `GenerateDefaultStructForBinding == false`. 

Example: 

```csharp
protected override void Render(HtmlTextWriter writer)
{
    if (IsInsideEditor)
    {
        string valJson = string.Format("`\"Property\":\"{0}\", \"ID\" : \"{1}\"`", BindingPropertyName, ID + "_ctrl");
        writer.AddAttribute("class", "binding");
        writer.AddAttribute("type", "hidden");
        writer.AddAttribute("value", valJson);
        writer.RenderBeginTag(HtmlTextWriterTag.Input);
        writer.RenderEndTag();
    }
}
``` 

**Important:** if you use the standard structure for binding in the value of the ID field of the json structure will be placed `ControlToEditClientID`. 

## customize the display of DatePicker in AGE 

The easiest way to display a DatePicker for all groupedit application to create the method producing the necessary actions on an instance of the control (which is passed to it as parameters), and assign it a static property InitSettings class DatePicker. Read more in Satya [global setting web controls](fa_init-control-settings-delegate.html). 

## Embedding MasterEditorAjaxLookUp 

In `AjaxGroupEdit` you can use [MasterEditorAjaxLookUp](fa_master-editor-ajax-lookup.html). To do this, set [WebControlProvider](fa_master-editor-ajax-lookup.html). 

An example of a piece of the file `WebControlProvider.xml`: 

```xml
  <propertytype name="Tiplady">
    <control typename="" property="" codefile=""/>
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxLookUp" property="SelectedMasterPK" codefile=""/>
  </propertytype>
``` 

It will look as follows: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/wge-ajax-lookup.jpg) 

## Controls for editing 
There is a need to define custom controls for viewing and editing properties of objects in `AjaxGroupEdit`. The controls for viewing are defined in the usual way [WebControlProvider](fa_web-control-provider.html). In order to specify the control that will be created to edit the properties, then you need to `WebControlProvider` add Directive `<editcontrol />` with similar properties `<control />`. 

For example, to view the date used `ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl`, and to edit `ICSSoft.STORMNET.Web.Controls.DatePicker`. 

It looks as follows: 

![](/images/pages/products/flexberry-aspnet/controls/groupedit/wge-dates.jpg) 

```xml
  <propertytype name="NullableDateTime">
    <control typename="ICSSoft.STORMNET.Web.Controls.DateTimeFormattedControl" property="Text" codefile="DateTimeFormattedControl.ascx"/>
    <editcontrol typename="ICSSoft.STORMNET.Web.Controls.DatePicker" property="Text" codefile="The DatePicker.ascx"/>
  </propertytype>
``` 

Read more about [WebControlProvider and embedding controls](fa_web-control-provider.html). 

## Scripts 

If the application control interface scripts (on page load), then all the scripts need to connect via [ContextHelper](fa_context-helper.html) and the scripts will automatically be added. If control is necessary connection of external scripts, you need to use a property `ExternalScriptPaths` interface `IAjaxGroupEditCompatible`.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}