--- 
title: AlphaNumericTextBox 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_alpha-numeric-textbox.html 
lang: en 
autotranslated: true 
hash: de28d0071521d83cae46f8dfbef686d54cf447fe45eea3b77c7d54325f374209 
--- 

Control `ICSSoft.STORMNET.Web.AjaxControls.AlphaNumericTextBox` designed for fields where you may use only letters and/or numbers. Inherits `System.Web.UI.WebControls.TextBox`, implements `ICSSoft.STORMNET.Web.AjaxControls.IAjaxGroupEditCompatible`. 
Supports the insertion of values from the buffer. 

## Setting 

Setting properties when the page is loaded in the method `PreApplyToControls()`. 

### Type 

To specify the type of the input property is used `Type` type `AlphaNumericType`: 

```csharp
/// <summary> 
/// Enumeration of input types to control AlphaNumericTextBox 
/// </summary> 
public enum AlphaNumericType
{
	/// <summary> 
	/// Literal 
	/// </summary> 
	Alpha, 

	/// <summary> 
	/// Numeric 
	/// </summary> 
	Numeric, 
	
	/// <summary> 
	/// Alphanumeric 
	/// </summary> 
	AlphaNumeric
}
``` 

### Interval 

You can specify the range of input numbers: `Min` and `Max`. By default, you can enter numbers from `int32`, it is possible to use both positive and negative values. 

### Alphabets 

In the field `NumericChars` and `AlphabeticChars` you can enter the valid characters of the alphabet of numbers and letters, respectively. Characters that do not fall into these alphabets will not be entered in this control. 

### Initialization 

To initialize the properties of the control you can use a static property-delegate `InitSettings` [InitControlSettingsDelegate<>](fa_init-control-settings-delegate.html), which will be called in the constructor of the control. 

### Features 

When disabled `ViewState` control does not save the value between PostBack s, if he is in a state `Disabled` (because of the peculiarities of the infrastructure ASP.NET). 

## Examples 

The following code sets the type of control in `Numeric` (which will allow to enter only digits), and sets a dictionary of valid characters (which allows to enter only digits 0, 1 and 2): 

```csharp
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.NumericChars = "012";
``` 

The following code sets a minimum and maximum value allowed for input: 

```csharp
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.Min = 1200;
ctrlAlpha.Max = 1300;
``` 

The following code sets the type to `AlphaNumeric` (which will allow you to enter letters and numbers), and sets dictionaries dopustimyh characters. Thus, control can only enter numbers 1 and 2, as well as letters 'a' and 'b': 

```csharp
ctrlAlpha.Type = AlphaNumericType.AlphaNumeric;
ctrlAlpha.NumericChars = "12";
ctrlAlpha.AlphabeticChars = ab;
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}