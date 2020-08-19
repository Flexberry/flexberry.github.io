--- 
title: Operations AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_age-operations.html 
lang: en 
autotranslated: true 
hash: 87c32e0743c7d3e0d76eabcd5116ee1679267462ae12f7af495a32501948844e 
--- 

## Description of operations 

In order to be able to specify permissible operations and, consequently, to change the appearance [AjaxGroupEdit](fa_ajax-group-edit.html), you should contact the property `Operations`. 

| Transaction | Description | 
|:------------|:-------------------------------------------------------------| 
| PlusInRow | whether to Show the row with the Plus button, which cancels the edit, keeping in the fields, entered values| 
| Edit | Edit| 
| Add | Add| 
| Delete | | Delete 
| EditInRow | Show on the edit button of detail in the string. Editing is possible in [multi-modes](fa_open-windows-age.html): in this, a modal and a new window| 
| AddNewRowOnTop | Show the newly added record at the top of the list (for AjaxGroupEdit)| 


For example, if you want to hide the edit button, the code might look as follows: 

```csharp
ctrlMyWebGroupEdit.Operations.Delete = false;
``` 

{% include warning.html content="as default for detailov not generated edit form to enable options `EditInRow` you must first manually generate the edit form, and then set the property `DetailEditForm` to `указать` GroupEdit'where to edit detaily. This should be set in the method `PostApplyToControls()`." %} 

Example: 

```csharp
protected override void PostApplyToControls()
{
	ctrlПодзадача.DetailEditForm = ПодзадачаE.FormPath;
	ctrlПодзадача.Operations.EditInRow = true;

	Page.Validate();
}
``` 

{% include warning.html content="When opening the edit AGE saving is the object." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}