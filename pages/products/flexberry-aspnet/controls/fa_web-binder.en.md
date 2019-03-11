--- 
title: WebBinder 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_web-binder.html 
lang: en 
autotranslated: true 
hash: 292f1d80848200f229989a842a74c806c20f58f01b2c7e9ed80aa06e74fdae0f 
--- 

`WebBinder` component performing the binding web controls on the form and values of objects. 

## Public methods 

```xml
/// <summary> 
/// Set the accessibility of edit controls for the whole form. 
/// </summary> 
/// <param name="controls">Root collection of controls that will searched the controls.</param> 
/// <param name="view">the View (will only be managed by controls that edit the properties of this representation).</param> 
/// <param name="readOnlyFlag">the property Value is "readonly".</param> 
/// <param name="controlIdsComparer">Custom comparison function identifiers of the controls used to search the controls in the tree (if not specified, the comparison is performed character-by-character).</param> 
public void SetReadOnlyForm(ControlCollection controls, View view, bool readOnlyFlag, Func<string, string, bool> controlIdsComparer = null)
``` 

```xml
/// <summary> 
/// Set the edit availability for a specific property of data object 
/// (First is the attempt to set the ReadOnly flag, and then Enabled. 
/// In the future when receiving data from the control, the corresponding property of the object, these data should be ignored). 
/// </summary> 
/// <param name="controls">Root collection of controls that will be searched for the desired control.</param> 
/// <param name="propName">the property Name in the data object.</param> 
/// <param name="readOnlyFlag">the property Value is "readonly".</</param> 
public void SetReadOnlyProperty(ControlCollection controls, string propName, bool readOnlyFlag)
``` 

```xml
/// <summary> 
/// Set the control flag of availability edit 
/// (First is the attempt to set the ReadOnly flag, and then Enabled. 
/// In the future when retrieving data from controls 
/// these controls will be ignored). 
/// </summary> 
/// <param name="ctrl">the Control that set the flag.</param> 
/// <param name="readOnlyFlag">Flag availability edit.</param> 
public void SetReadonlyToControl(Control ctrl, bool readOnlyFlag)
``` 

```xml
/// <summary> 
/// Apply the data object to the controls on the page. 
/// </summary> 
/// <param name="controls">Root collection of controls that will searched the controls.</param> 
/// <param name="view">the View.</param> 
/// <param name="dataObject">the Object from which data is obtained.</param> 
/// <param name="isPostBack">a Flag that indicates whether postback is happening now (depending on this flag will be, or Vice versa will not update the values in lukapa).</param> 
/// <param name="isSaving">the Flag that indicates is the preservation of the data object.</param> 
/// <param name="controlsIdComparer">Custom comparison function identifiers of the controls used to search the controls in the tree (if not specified, the comparison is performed character-by-character).</param> 
public void ApplyDataToControls(ControlCollection controls, View view, DataObject dataObject, bool isPostBack, bool isSaving = false, Func<string, string, bool> controlsIdComparer = null)
``` 

```xml
/// <summary> 
/// Marks the specified control implementing the interface ISaveActionCompatible, 
/// given a flag value ISaveActionCompatible.IsSaveNow. 
/// </summary> 
/// <param name="controls">Root collection of controls.</param> 
/// <param name="view">the View corresponding to the data object.</param> 
/// <param name="isSaving">the Desired value of the flag ISaveActionCompatible.IsSaveNow showing is made whether the preservation of the data object.</param> 
public void ApplySavingFlagToSaveActionCompatibleControls(ControlCollection controls, View view, bool isSaving)
``` 

## Binding multiple properties 

In the folder ~/Xml/Bindings can be setup with custom binding. If the form contains detail, inside of which is a control that edits a few object properties, then you need to configure a custom binding. 
To complement its standard binding, you need the element `root` add attribute `partial="true"`. 

Example: 

```xml
<?xml version="1.0" encoding="utf-8" ?>
<root partial="true">
  <detail name="Meropriyatiya">
    <property name="Contractor">
      <control id="ctrlИсполнитель" prop="SelectedMasterPK">
      </control>
    </property>
    <property name="Ispolnitelnoe">
      <control id="ctrlИсполнитель" prop="Text">
      </control>
    </property>
  </detail>
</root>
``` 

In the example described that in detale 'Meroprijatija' is control 'ctrlИсполнитель', which simultaneously edits the properties 'SelectedMasterPK' and 'Text' 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}