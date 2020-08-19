---
title: Predictive typing in Windows applications
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, predictive text input
summary: Definition, connection and check-in Flexberry Desinger, the use in the text box and filter
toc: true
permalink: en/fw_predict-input.html
lang: en
autotranslated: true
hash: 9fd743403718e70f1e6e0ff4d9c682dec096ba45e62c95975303f3d91a1ba68c
---

`Предиктивный ввод` allows you to modify the controls for master ([LookUp](fa_lookup-overview.html)) or text input so that the system suggests to the user possible options as you type data.

## Predictive LookUp for

As a control, the prompter can be

* Control contained in the Assembly `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll` ([ExtendedLookUp](fw_extended-lookup.html) or `ExtLookUpTextControl`)
* Control, created by the programmer ([read more in the article Arbitrary control as lucapa](fo_custom-lookup.html)).

## Connection control

To connect the control predictive, you must:

* Register the control in Stage Flexberry Desinger
* In [E-view](fd_e-view.html) of the object pointed to master custom-LookUp
* To generate objects
* Compile objects
* Generate forms
* In the Visual Studio project to set a reference to the control library

### Registration control at the Stage Flexberry Desinger

To register a control, you must go to the Stage and call up the context menu, the menu `Winforms` -> `C#` -> `Свойства модели`.

In the form that opens go to the tab `Дополнительно` and click `Дополнительные настройки`.

![](/images/pages/products/flexberry-winforms/controls/edit-stage.png)

In the form, additional configuration is required in column `Путь to сборке` click `...` and specify the path to a DLL with the control (for Winforms controls is the file `ICSSoft.STORMNET.Windows.Forms.AdditionalControls.dll`).

![](/images/pages/products/flexberry-winforms/controls/path2dll.png)

In the dialog form select the type it should be noted `ExtendedLookUp`, `ExtLookUpTextControl` or both.

__Note__: it is recommended to use only `ExtendedLookUp`.

![](/images/pages/products/flexberry-winforms/controls/type-select.png)

Save the settings by pressing `OK` - `Сохранить and закрыть`

Now the control was in stage Flexberry Desinger.

### Specify the LookUp wizard in the E-representation of the object

To turn a regular `LookUp` in `LookUp with predictive вводом`, you need to point the wizard to the type LookUp.

* Open the E-representation of an object and allocate the new, which requires the use of predictive.

![](/images/pages/products/flexberry-winforms/controls/select-master.png)

* The `Тип лукапа` specify `custom`

![](/images/pages/products/flexberry-winforms/controls/select-type.png)

* The `Свойство мастера` specify the property __wizard__ by which to search for predictive.

__Note__: the property master should be in view. The name of the property is entered from the keyboard.

![](/images/pages/products/flexberry-winforms/controls/select-property.png)

* The `Имя лукапа` choose from the dropdown list LookUp.

__Note__: if dropdown list has no LookUp necessary, should be to the point `Регистрация control under Flexberry Desinger`.

![](/images/pages/products/flexberry-winforms/controls/select-lookup.png)

* To save changes to the view.

### The result

![](/images/pages/products/flexberry-winforms/controls/predict-lookup.gif)

## Predictive text field

To implement predictive text input control ExtendedTextBox(fw_extended-textbox.html), which is contained in the Assembly `ICSSoft.STORMNET.Windows.Forms`.

Typically used for selection of the workman of an object based on some string.

The most common case entry name to get the directory name, to set `ExtendedTextBox` it and get a quick choice among people, are available in the database.

### Properties

* Property `AutoOpenListForSuggestions` – if the value `true` automatically opens a list of options when entering text.
* Property `UseExtendedMask` – if the value `true` options are offered when entering the entered text as a substring. Should be used in conjunction with `AutoOpenListForSuggestions = true;` and `MaskFormat=”%{0}%”`.

## The addition of the words

When you call a method `GetCurrentDataObject` have `ExtendedTextBox` is the addition of the word entered, and the data object will be returned for augmented words. Sometimes there are situations where this behavior is undesirable (e.g. when pressing backspace).
Have `ExtendedTextBox` there is a method overload `GetCurrentDataObject` that allows you to specify whether the addition of the word entered.

```csharp
public DataObject GetCurrentDataObject(bool reloadItems)
```

If this parameter is set to `reloadItems` `false` reload will not occur if the value `true` will return the object for augmented words.

## Filtering records for predictive

To impose additional filtering on the output predictive input prompts, you must specify the control [LimitFunction](fo_function-list.html), subject to the restriction.

```csharp
Фильтрация записей для предиктивного ввода по логину			
			extTextControl1.LimitFunction = FunctionBuilder.BuildEquals("Login", username);
```

## Predictive Web applications

Predictive Web applications described in [Predictive Web applications](fa_predict-input-web.html)



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}