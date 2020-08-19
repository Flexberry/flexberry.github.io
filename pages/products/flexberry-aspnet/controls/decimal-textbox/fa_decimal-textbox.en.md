---
title: DecimalTextBox
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Controls)
toc: true
permalink: en/fa_decimal-textbox.html
lang: en
autotranslated: true
hash: b0cd8556c0570a325a1b71343e18c00b002bcc830d9abcf5803e61b3f8ca316b
---

ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox is [web control](fa_web-controls.html), which is a TextBox, which is superimposed on the input filter for validation of positive real numbers in the form of decimal fractions. It is similar to working with conventional TextBox'ohms, as is his heir. To separate the integer and fractional part, use a dot or a comma.

`DecimalTextBox` generated at [web edit](fa_editform.html) if the properties of object exposed type Double, Decimal or Single, and Nullable counterparts of these types. For these types for list form and web-form editing removes trailing zeros.

When you paste from the clipboard values that are not positive real numbers, the control displays "0".

## Properties

| Name | Type | Description|
|---|---|---|
| MaxDecimalPlaces | Int | Maximum number of digits after the decimal separator. If the value is negative, the limit on the number of digits after the decimal point is not imposed.|

## Features of use

PstrfDecimalTextBox` when the component is used to display real numbers for which the database uses the type with a fixed number of decimal places (including 0 decimals), you must set the property MaxDecimalPlaces for correct display number in the component.

At the moment, [DataObject](fo_data-object.html) does not store metadata about the number of digits after the decimal point for real types C#, therefore, when generating applications, there is no possibility to automate the initialization properties MaxDecimalPlaces.

If you do not initialize a property `MaxDecimalPlaces` in the above case, on the edit form after changing the properties of an existing data object in the component `DecimalTextBox` and then saving the data without closing the form, a real number may not be displayed correctly - the displayed number of decimal places may not correspond to the number of characters that is specified for the corresponding type in the database (to be displayed will be the value that the user entered before saving). When adding a new data object for this behavior is not observed, because it is an additional request to the server to retrieve primary key values from the new object data, resulting in re-read a data object before it is displayed on the form (the field values are displayed as they are stored in the database).

Perhaps in future versions [Flexberry Designer](fd_flexberry-designer.html) will be able to specify and store the number of digits after the decimal point for real types C# on the meta level, then the need to manually set the MaxDecimalPlaces will disappear.

## DecimalTextBox in AjaxGroupEdit

By default, editing of real numbers in [AjaxGroupEdit](fa_ajax-group-edit.html), use a normal TextBox.
Editing took place using the DecimalTextBox, you want to make the appropriate settings using [WebControlProvider](fa_web-control-provider.html).

For example, if you want to edit the property Свойство5 class SeveralViewsDetail with control DecimalTextBox, in [WebControlProvider.xml](fa_web-control-provider.html) need the following entry:

 ```csharp
<customproperty class="SeveralViewsDetail" property="Свойство5">
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox, ICSSoft.STORMNET.Web.AjaxControls" codefile="" property="Text"/>
</customproperty>
 ```

To `AjaxGroupEdit` to change the values of properties `DecimalTextBox`, you can use the delegate.

For example, if the property `Свойство5` class `SeveralViewsDetail` edited in presenting SeveralViewsDetailD1 and you want to limit the number of digits after the decimal point is three, it is required to determine the appropriate delegate:

```csharp
ICSSoft.STORMNET.Web.AjaxControls.AjaxGroupEdit.SetControlTuner(SeveralViewsDetail.Views.SeveralViewsDetailD1.Name, TuneControlDelegate);
```

```csharp
/// <summary> 
/// Delegate for the adjustments of the control in AGE. 
/// In this case, the properties <see cref="SeveralViewsDetail.Свойство5"/> 
/// to control <see cref="ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox"/> 
/// put the maximum number of characters after the decimal point "3". 
/// </summary> 
/// <param name="control">the Current selected control.</param> 
/// <param name="createdControlData">parameters for the control.</param> 
private void TuneControlDelegate(Control control, CreatedControlData createdControlData)
{
  string searchedProperty = ICSSoft.STORMNET.Information.ExtractPropertyPath<SeveralViewsDetail>(x => x.Свойство5);
  if (createdControlData.ControlCreationReason == CreatedControlData.CreateControlReason.Edit
    && createdControlData.DataObjectType == typeof(SeveralViewsDetail)
    && createdControlData.PropertyName == searchedProperty)
  {
    if (!(control is ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox))
    {
      throw new System.Exception(
        string.Format(
          "To properly function, the test {0} is required in order to edit the properties for {1} was used to control {2}.",
          typeof(TestDecimalAge).Name,
          searchedProperty,
          typeof(ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox).Name));
    }

    var decimalTextBox = (ICSSoft.STORMNET.Web.AjaxControls.DecimalTextBox)control;
    decimalTextBox.MaxDecimalPlaces = 3;
  }
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}