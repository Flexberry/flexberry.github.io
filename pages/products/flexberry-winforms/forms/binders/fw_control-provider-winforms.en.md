---
title: Provider of controls for Winforms Flexberry
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, forms, controls, binding, provider controls
summary: Concept, types, according the type of data and control, creation of provider controls, the structure of the processing controls a variety of data types
toc: true
permalink: en/fw_control-provider-winforms.html
lang: en
autotranslated: true
hash: b08273602796ae50ae34b8577d52f5d1d0165ea13f3035067b30073e87d4b8da
---

Providers of the controls classes that are inherited from the abstract `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider` designed to prevalene in compliance with the type of control and the type of the data object. For example, universal [edit](fw_editform.html).

The class `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider` `GetControl` there is a method that returns a structure `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct`, which specifies the relationship of the control type value.

There are so-called standartny provider kontrolov `ICSSoft.STORMNET.Windows.Forms.Binders.StandardControlProvider` in which `GetControl` method is overloaded so that it returns the control to some predefined manner.

## Controls returned StandardControlProvider

| Type | Control
|--|--
| string| System.Windows.Forms.TextBox |
| System.Decimal<br>System.Double<br> System.Int16<br>System.Int32<br>System.Int64<br>System.SByte<br>System.Single<br>System.UInt16<br>System.UInt32<br>System.UInt64| System.Windows.Forms.TextBox
| System.DateTime| ICSSoft.STORMNET.Windows.Forms.DateTimePicker
| bool| System.Windows.Forms.CheckBox
| Enum| ICSSoft.STORMNET.Windows.Forms.ExtendedComboBox
| ICSSoft.STORMNET.DetailArray| ICSSoft.STORMNET.Windows.Forms.GroupEditBase
| ICSSoft.STORMNET.DataObject| ICSSoft.STORMNET.Windows.Forms.ComboLookup or (depending on settings) ICSSoft.STORMNET.Windows.Forms.LookUp.LookUp

If you want edited by another type, or another control, you need to define your own provider control and associate it with a type.

There are several common situations:

* To set control for editing the values of the standard типа;
* Editing a non-standard type standard контролом;
* Editing non-standard non-standard control.

## ControlForBindStruct

`ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct` - structure that defines a control for editing some properties. For example, [Processing date ControlProvider](fw_processing-date-in-control-provider.html)).

For this structure defines three constructors:

* `ControlForBindStruct(object control, string controlPropName)` — an instance of the control that will edit значение;
* `ControlForBindStruct(object control, string controlPropName, Type[] typeMapping)` — name significant properties of the control, i.e., which is and returns значение;
* `ControlForBindStruct(object control, string controlPropName, Type[] typeMapping, IComponent[] additionalControls)` — mapping (chain explicit or implicit conversions) of types used in the case when significant property is not supported directly the desired type, but is supported by another, to which the type can be converted. If this mapping is specified, then setting the value in the property control conversion occurs sequentially on the specified type starting from the beginning of the array, and if back (if you are installing from a property control in a property of the data object), then the end of the array.

### Control

`control` - an instance of the control that will edit the value.

```csharp
var txtbox = new System.Windows.Forms.TextBox();
var dateTimePicker = new ICSSoft.STORMNET.Windows.Forms.DateTimePicker();
```

### ControlPropName

`controlPropName` - name significant properties of the control, i.e., which is set and returns the value.

Naprimer:_

* for control type `System.Windows.Forms.TextBox` : "Text".
* for control type `System.Windows.Forms.CheckBox` : "Checked".
* for control type `System.Windows.Forms.ComboBox` : "Text".
* for control type `ICSSoft.STORMNET.Windows.Forms.DateTimePicker` : "ObjectValue".
* ...

### TypeMapping

`typeMapping` is an array used to maprounea value types, which should work `control`.

Naprimer:_

1.If the value of type `System.String` will be processed using `System.Windows.Forms.TextBox`, the mapping can be omitted:

```csharp
new ControlForBindStruct(new System.Windows.Forms.TextBox(), "Text")
```

2.If the value of type `ICSSoft.STORMNET.UserDataTypes.NullableDateTime` will be processed using `ICSSoft.STORMNET.Windows.Forms.DateTimePicker` that works with the type `System.DateTime`, it must implement the mapping (a complete example in the article [Processing date ControlProvider](fw_processing-date-in-control-provider.html)):

```csharp
ControlForBindStruct(new ICSSoft.STORMNET.Windows.Forms.DateTimePicker(), "ObjectValue",
                            new System.Type[] {typeof(ICSSoft.STORMNET.UserDataTypes.NullableDateTime),
                                        typeof(System.DateTime)})
```

3.If the value of type `ICSSoft.STORMNET.UserDataTypes.NullableDecimal` will be processed using `System.Windows.Forms.TextBox` that works with the type `System.String`, the required chain mapping, because the system knows how to translate `ICSSoft.STORMNET.UserDataTypes.NullableDecimal` in `System.Decimal`, and `System.Decimal` already in `System.String`.

```csharp
ControlForBindStruct(new System.Windows.Forms.TextBox(), "Text",
                        new Type[] { typeof(ICSSoft.STORMNET.UserDataTypes.NullableDecimal),
                                        typeof(Decimal), typeof(string) }
```

## Description private provider of controls

In fact, in all cases, the creation of the control is through a standard provider of controls, but pre-standard provider checks the associated with the type of provider control. Standard provider returns the predefined controls only when no other associate of the provider or the associated provider method returned `null` or `ControlForBindStruct.Empty`.

To create the custom provider controls, you must unasledovala from `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider` and override the method `GetControl`. It has parameters:

* `string ApplicationType` — type applications (some string identifying a type of user interface);
* `Type type` — type values which you want to edit контролом;
* `ICSSoft.STORMNET.View` View — the view in which the object is данных;
* `string propertyName` — the property name of the data object that you want to edit.

An example can be found in [DateTimePicker](fw_datetime-picker.html).

Return control is possible depending on combination of values of these parameters, i.e. the flexibility to customize the user interface.

The method returns a structure `ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct` in the design which indicate:

* `System.Object control`
* `System.String controlPropName`
* `System.Type[] typeMapping`

__Note__: linking a control with a value occurs through standard `.Net`-binding. I.e., link control needs to understand» «type values.

## Association provider controls type

Once the provider is created, you must associate it with a type. For this is the attribute `ICSSoft.STORMNET.Windows.Forms.Binders.ControlProviderAttribute`. Parameter specifies the provider type.

Naprimer:_

```csharp
[ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider(typeof(ДеньгиTextBoxControlProvider))]
    public struct Деньги //By the way, an example of a custom type 
    {
        //Etc. 
```

Obviously, this method is useful for custom types. But what about the standard types, they do not attribute the attribute?

There is another mechanism, installation of the provider controls, which handles all the types. To accomplish this, you should set a static property `StandardControlProvider.ControlProviderForNotCustomizedTypes` provider controls.

Primer:_

```csharp
StormNetForms.Binders.StandardControlProvider.ControlProviderForNotCustomizedTypes=new РесурсControlProvider();
```

## Setting the control for editing the value of a standard type

You must implement the provider controls and set it in a static property `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## Editing non-standard type standard control

You must implement the provider controls indicating matirovanie types and associate it with a type, or set it in a static property `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## Editing non-standard non-standard control

You need to create a control for editing values of custom types.

To do this:

* Implement control as the heir from `ICSSoft.STORMNET.Windows.Forms.Binders.BindableUserControl`
* Control must have a meaningful property and event signaling about the change of the value with the name `ХХХХХChanged`, where XXXXX is the name of the significant properties. Event have to cock when you change the value of the most significant properties.
* The control can implement the interface `ICSSoft.STORMNET.Windows.Forms.ICustomizableControl` for more accurate adjustment depending on the class of the data, presentation, name of the property.
* Control can also implement the interface `ICSSoft.STORMNET.Windows.Forms.IButtonizableControl` specially for more convenient input of values via `ICSSoft.STORMNET.Windows.Forms.GroupEditBase`.

Next, you implement the provider controls and to associate it with a type, or set it in a static property `StandardControlProvider.ControlProviderForNotCustomizedTypes`.

## General comments on the providers control

Because the type and other parameters come in overloaded method `GetControl` provider, of course, no need to make one provider for each control. You can use one of several types, or create a single provider controls the entire system and install it in `StandardControlProvider.ControlProviderForNotCustomizedTypes`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}