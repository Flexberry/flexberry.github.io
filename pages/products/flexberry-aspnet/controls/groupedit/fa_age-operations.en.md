--- 
title: Operations AjaxGroupEdit 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_age-operations.html 
lang: en 
autotranslated: true 
hash: 820c87c9bfd1a5cd19a14966a15070eba030bc925c82f6f8e47de0d718fa62c4 
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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/