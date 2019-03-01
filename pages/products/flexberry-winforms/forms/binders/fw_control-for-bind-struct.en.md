--- 
title: ControlForBindStruct 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, binders 
summary: Constructors, properties, mapping 
toc: true 
permalink: en/fw_control-for-bind-struct.html 
lang: en 
autotranslated: true 
hash: b59e125c3fdd7afa0d523c5b3efbae9d82e4b572fbb5b8c6e896415ef6b7d4f0 
--- 

`ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct` - structure that defines a control for editing some properties (used, for example, when you create a [provider controls](fw_control-provider-winforms.html): for an example, see [Handling dates in ControlProvider](fw_processing-date-in-control-provider.html)). 

## Designers 

For this structure defines three constructors: 

* `ControlForBindStruct(object control, string controlPropName)` 
* `ControlForBindStruct(object control, string controlPropName, Type[] typeMapping)` 
* `ControlForBindStruct(object control, string controlPropName, Type[] typeMapping, IComponent[] additionalControls)` 

General description of the constructor arguments is presented in [Provider controls for Winforms Flexberry](fw_control-provider-winforms.html). Let's focus on the constructor arguments. 

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

`typeMapping` is an array used for [mepyramine](fw_control-provider-winforms.html) types of values, which should work `control`. 

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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/