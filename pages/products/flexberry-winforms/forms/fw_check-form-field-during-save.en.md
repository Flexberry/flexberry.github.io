--- 
title: data Validation on the form during the save 
sidebar: flexberry-winforms_sidebar 
keywords: DataObject (object data) 
summary: Comparative analysis of various variants of test data on the form when you save, example 
toc: true 
permalink: en/fw_check-form-field-during-save.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 5bf45895f8b9105c715860da9cd328f70c1376bb3a32d62f4ded672994fc88f5 
--- 

<!-- This article is still being edited --> 

Data validation on the form during the save is [ through events `OnSave`/`OnSaveEvent`](fw_check-through-on-save-event-example.html) and may include the following elements: 
* Determination of the required fields on the class diagram using the attribute [`NotNull`](fo_attributes-class-data.html). 
* Check [`DataObjectErrorProvider`](fw_data-object-error-provider.html). 

| Admission | Advantages | Disadvantages | 
|--|--|--| 
| Define required fields on the class diagram using the attribute [`NotNull`](fo_attributes-class-data.html) | allows the model to define required fields | - to determine the field required only in some situations 
| Check [`DataObjectErrorProvider`](fw_data-object-error-provider.html) | allows you to quickly prescribe in the code the list of mandatory fields and users of the application will not be able to change | does not allow users to change the validation criteria on the form 


An example of the use of all methods: 

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

Other methods of data validation on the form is described [here](fw_edit-form-validation.html). 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/