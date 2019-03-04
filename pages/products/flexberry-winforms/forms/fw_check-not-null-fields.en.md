--- 
title: Validation of required fields 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: learn how to use DataObjectErrorProvider to check the mandatory fields that are optional in the model, examples 
toc: true 
permalink: en/fw_check-not-null-fields.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 86da9dc1aedc84c3eea129aad7c385f645cbd3df916da74af0b806560890eac3 
--- 

If the fields are of the same class on the same edit form needs to be filled, and the other is not, then to display and validate required fields (not mentioned in the class attribute [`NotNull`](fo_attributes-class-data.html)) you can use [`DataObjectErrorProvider`](fw_data-object-error-provider.html) 

## Procedure 
* throw DataObjectErrorProvider on the form 
* snap DataObjectErrorProvider to EditManager (bound check field)(DataObjectErrorProvider.EditManagerForBind) 
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


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/