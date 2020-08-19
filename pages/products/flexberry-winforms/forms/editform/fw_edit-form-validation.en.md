---
title: data Validation
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, forms, validation
summary: For windows-applications lists the level of data validation (while editing, when you save the form, the business servers), given their comparative characteristics
toc: true
permalink: en/fw_edit-form-validation.html
lang: en
autotranslated: true
hash: 8d70be134a585f0e6d3fbfed930966d54e7b34a492a3fc67e38152ef57295796
---

Data validation occurs in several stages.

* Checks during editing on a form.
* Checks during the save of the object.
* Check in [business server](fo_business-server.html).

{% include note.html content="to generate code edit form (or list), it is necessary in properties of a class with the [stereotype EditForm](fd_editform.html) to select the `GenerateDependedForm`." %}

| Admission | Advantages | Disadvantages|
|--|--|--|
| During editing | Allows you to quickly inform the user about incorrect values in some field | does Not allow to fully test the complex relationships between the fields of the object and other objects
| During the | save Allows you to test complex relationships between fields of an object since the check happens at a time | Test begins only when you try to save the form
| Business server | Allows to test complex relationships between fields of an object and relationships to other objects | Test only starts when you try to save the form

General comments on the implementation of the on-screen edit forms can be found in the article [Recommendations for improvement edit forms](fw_editform.html).

The preferred option is the implementation of checks __at all levels__.

## Check and highlighting of fields on the edit form

`DataObjectErrorProvider` component, allowing you to verify and illumination fields on the edit form.

Basic sequence of actions for working with `DataObjectErrorProvider` displayed in the way of checking of mandatory fields during the editing phase and save the data.

{% include important.html content="
`DataObjectErrorProvider` not supplied as standard Flexberry." %}

## Data validation on the form during editing

Data validation on the form during editing can be carried out by:

* [the exception](fo_check-object-set.html) if an invalid entry in the method `set` the corresponding field object
* define required fields on the class diagram using the attribute [NotNull](fo_attributes-class-data.html)
* checks through `DataObjectErrorProvider`

| Admission | Advantages | Disadvantages|
|--|--|--|
| Throw an exception if an invalid entry in the method `set` the corresponding field of the object | Allows to organize work in such a way that the user will not leave the field until, until the field value is not entered correctly | To begin the test requires that the focus may fall on corresponding field|
| Define required fields on the class diagram using the attribute `NotNull` | Allows the model to define required fields <br> Discreet flag of nezaposlenosti field | does Not allow to determine the field required only in some situations|
| Check `DataObjectErrorProvider` | Allows you to quickly prescribe in the code the list of mandatory fields and users of the application will not be able to change it, <br> Discreet flag of nezaposlenosti field | does Not allow users to change the validation criteria for the form|

## Data validation on the form during the save

Data validation on the form during the save is done through the events `OnSave`/`OnSaveEvent` and may include the following elements:

* Determination of the required fields on the class diagram using the attribute `NotNull`.
* Check through `DataObjectErrorProvider`.

| Admission | Advantages | Disadvantages |
|--|--|--|
| Define required fields on the class diagram using the attribute `NotNull` | Allows the model to define required fields | allows you to define fields that are mandatory only in certain situations
| Check `DataObjectErrorProvider` | allows you to quickly prescribe in the code the list of mandatory fields and users of the application will not be able to change | does not allow users to change the validation criteria on the form

Primer the use of all methods:_

```csharp
protected override void OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) //method OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) independent 
{
  System.Collections.ArrayList arl = new System.Collections.ArrayList();
  arl.AddRange(e.dataobject.CheckNotNullProperties(m_objView, true)); //check the set in the model NotNull field 
  arl.AddRange((Editor as WinformВещьE).dataObjectErrorProvider1.GetNullProperties()); //check the set using DataObjectErrorProvider required fields 
  if (arl.Count > 0)
  {
    System.Windows.Forms.MessageBox.Show("Left blank required fields: " + Environment.NewLine + string.Join(", ", (string[])arl.ToArray(typeof(string))), "Attention");
    (Editor as WinformВещьE).dataObjectErrorProvider1.FocusProperty(arl[0].ToString());
    m_bFailedSave = true;
    Editor.FailedSave(null);
    ((ICSSoft.STORMNET.UI.IDpdEditForm)Editor).SetStatusMessage(ICSSoft.STORMNET.UI.EditFormStatusMessage.ErrorInSave);
  }
  else
  {
    #region //check logical conditions and illumination through DataObjectErrorProvider 
    SpecificControls.DataObjectErrorProvider errorProvider = null;
    if (Editor != null) errorProvider = (Editor as WinformВещьE).ValidationErrorProvider;
    if (!SQLValidationManagerIntegrator.CheckAllRulesDigitReport(e.dataobject, this, null, errorProvider))
    {
      ОтменитьСохранение();
      return;
    }
    #endregion
    base.OnSave(e); //save if all checks are passed 
  }
}
```

## Validation of required fields

If the fields are of the same class on the same edit form needs to be filled, and the other is not, then to display and validate required fields (not mentioned in the class attribute `NotNull`) you can use `DataObjectErrorProvider`.

### Procedure

* throw `DataObjectErrorProvider` on the form
* snap `DataObjectErrorProvider` to `EditManager`, is bound to check the field(DataObjectErrorProvider.EditManagerForBind)
* to check the field in DataObjectErrorProvider.Properties

```csharp
// 
// dataObjectErrorProvider1 
// 
this.dataObjectErrorProvider1.DpdEditContainer = this;
this.dataObjectErrorProvider1.EditManagerForBind = this.EditManager;
this.dataObjectErrorProvider1.Properties = new string[] {
  "Nomercy",
  "Ticktock",
  "Breathability"};
```

* DataObjectErrorProvider to associate with the data object. Call dataObjectErrorProvider1.BindToData(); Edit method
* in order to display the error field object (cockroach), you can use BindToData() or SetError() for a particular field

```csharp
if (obj #  null || obj.ToString()  "")
    dataObjectErrorProvider1.SetError(property, EditManager.NotNullToolTip);
else
    dataObjectErrorProvider1.SetError(property, string.Empty);
```

* add fields to the message about not filling the required fields when saving. In the peer-editing form in the method OnSave() to write:

```csharp
System.Collections.ArrayList arl = new System.Collections.ArrayList();//array where you will get the headers from a specific view, blank fields 
arl.AddRange(obj.CheckNotNullProperties(m_objView, true));//required fields for the object (marked in the class attribute NotNull) 
string[] s1 = form.dataObjectErrorProvider1.GetNullProperties();//mandatory fields specified in dataObjectErrorProvider1 
for (int i = 0; i < s1.Length; i++)
   arl.Add(m_objView.GetProperty(s1[i]).Caption);
if (arl.Count > 0)
{
   form.dataObjectErrorProvider1.FocusFirstNullProperty();//set focus on first in the array is not filled in a required field 
   IIS.WinUI.Tools.ShowWarning("Left blank required fields: " + Environment.NewLine + string.Join(", ", (string[])arl.ToArray(typeof(string))), "Attention");
   ОтменитьСохранение();
   return;
}
```

## An example of data validation on the form using OnSave/OnSaveEvent

The essence of the test is that the event [OnSave](fw_form-interaction.html)/[OnSaveEvent](fw_form-interaction.html) is overridden and if the data does not satisfy certain conditions, the base method does not get called.

[OnSaveEvent](fw_form-interaction.html) dependent forms:

```csharp
protected override void OnSaveEvent()
{
  ОбъектыДанных.ЗаявНаВыплату vЗаяв = (ОбъектыДанных.ЗаявНаВыплату) EditManager.DataObject;bool bContinueSave = true;
  if (vЗаяв.ДатаНачалаНачисл != null && vЗаяв.ЛгКатЛичн != null && vЗаяв.ЛгКатЛичн.ДатаНазначения != null && vЗаяв.ДатаНачалаНачисл.Value < vЗаяв.ЛгКатЛичн.ДатаНазначения.Value)
  {
    if (System.Windows.Forms.MessageBox.Show("The payment may be designated with a " + vЗаяв.ЛгКатЛичн.ДатаНазначения.Value.ToString("dd.MM.yyyy") + ". Save changes? ","Attention", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.No)
      bContinueSave = false;
  }
  if (bContinueSave)
    base.OnSaveEvent (); //call the base method 
  if (!m_bFailedSave) //the value of the variable could change in the base method 
  {
    olПереплата.FillData();
    olУдержания.FillData();
  }
}
```

[OnSave](fw_form-interaction.html) independent of the form:

```csharp
protected override void OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)
{
  BS.BFСправочникиBS BS = new ICSSoft.Соцзащита.BS.BFСправочникиBS();
  ОбъектыДанных.Специалист vСпециалист;
  vСпециалист = BS.ИдентифицироватьСпециалиста();
  if (vСпециалист != null)
  {
    ОбъектыДанных.Личность vПолучатель = заявка.Получатель;
    if (!vСпециалист.ПроверитьСпеца(vПолучатель))
    {
      FailedSave(new Exception ("Saving changes is not possible!")); //generate the exception that you cannot save 
      return;
    }
  }
  base.OnSave(e); //call the base method 
}
```

{% include important.html content=" we Must distinguish between `OnSave()` and `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` independent. Speaking [simplified](fw_form-interaction.html), if the closing of the forms was carried out on the cross and the user has agreed to store the object that will be invoked `OnSave()`, then `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)`, but if the shape was carried out through the toolbar, the first will be called `OnSaveEvent()` dependent form, and then `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` independent.
"%}

## Cockroaches «and» an enum type

There are two ways to specify that one of the values of an enumerated type is a value corresponding» «empty, i.e. the value for which the cockroaches appear «and» a message saying I need to fill when you save the form.

1. The value of an enumerated type is marked `Caption("")` with an empty string as a parameter. This functionality is standard for Flexberry. It should be recalled, to set `Caption` attribute with an empty value in the editor Flexberry you must use the symbol «~» (tilde).

2. The value of an enumerated type is marked `EmptyEnumValue`.

__Observations:__

1. To display the cockroach and the control input value when saving a property of a class must be marked `NotNull()`.

2.» «Cockroaches might not appear in `GroupEdit` that is associated with the mechanism of display of list of values (using a built-in opportunity `FlexGrid`), but the control when you save the form will be implemented. If the display is using the standard `ComboBox`, cockroaches» «will be displayed correctly.

## Scenario refinement

* To configure the check of mandatory fields.
* Add the limit number of characters for string and controls a valid range for numeric controls.
* To put [default](fo_features-dafault-value.html).
* Add a check [unique offer](fo_unique-data-check.html), [date ranges](fo_func-between.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}