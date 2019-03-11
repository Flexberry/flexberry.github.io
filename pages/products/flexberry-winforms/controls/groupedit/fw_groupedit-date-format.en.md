--- 
title: date Format GroupEdit 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, GroupEdit, DateTime 
summary: configure the edit mode and display the date and time 
toc: true 
permalink: en/fw_groupedit-date-format.html 
lang: en 
autotranslated: true 
hash: 32818cde5194787fbc5b7969f67c6ff472346ecd2213e09bfcefa98096de3667 
--- 

### edit Mode 

Date format [GroupEdit](fw_group-edit.html) in edit mode, the default cell `Short`. To change the date format (e.g. dd.MM.yyyy HH:mm) in a single `GroupEdit` should `SetupEditor` in the handler add the code: 

```csharp
if (e.control is ICSSoft.STORMNET.Windows.Forms.DateTimePicker)
{
    ICSSoft.STORMNET.Windows.Forms.DateTimePicker dtp=(ICSSoft.STORMNET.Windows.Forms.DateTimePicker)e.control;
    dtp.OnlyDate = false;

    dtp.Format = DateTimePickerFormat.Custom;
    dtp.CustomFormat = "dd.MM.yyyy HH:mm";
}
``` 

### In display mode 

The date format in the display mode for individual `GroupEdit` is set in the form designer: 

```csharp
			C1.Win.C1FlexGrid.C1FlexGrid flex = Tools.GetFlexGrid(this.ДвижениеОтказа);
			string attributeName = Date;			
			try
			{
				flex.Cols[attributeName].Style.Format = "dd.MM.yyyy"; 
			}
			catch
			{
				Tools.ShowWarning("Failed to set the date format attribute " + attributeName + 
							      "- it will use date format by default");
			}
``` 

In this example, `this.ДвижениеОтказа` type is `GroupEdit`. 

{% include note.html content="the Sequence number column is NOT EQUAL to display order. Use the column names (not the names). The names can be found in the Properties for GroupEdit, the Attribute Columns." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}