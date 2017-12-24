---
title: Обработка даты в ControlProvider
sidebar: flexberry-winforms_sidebar
keywords: DateTime (работа с датами)
summary: Приведен пример настройки контрола для обработки даты в CustomControlProvider
toc: true
permalink: ru/fw_processing-date-in-control-provider.html
folder: products/flexberry-winforms/
lang: ru
---

```csharp	
public class CustomControlProvider:ICSSoft.STORMNET.Windows.Forms.Binders.ControlProvider 
	{
	   public override ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct GetControl(string ApplicationType, Type type, ICSSoft.STORMNET.View view, string propertyName, object initControl)
	    {

	     if (type == typeof(ICSSoft.STORMNET.UserDataTypes.NullableDateTime))			
	     {				
		ICSSoft.STORMNET.Windows.Forms.DateTimePicker picker = new ICSSoft.STORMNET.Windows.Forms.DateTimePicker();				
		picker.Format = System.Windows.Forms.DateTimePickerFormat.Short;
		picker.OnlyDate = true;
		picker.UseNullableValues = true;
		ctrl = new ICSSoft.STORMNET.Windows.Forms.Binders.ControlForBindStruct(picker, "ObjectValue", 
                                                                                      new System.Type[] {                       typeof(ICSSoft.STORMNET.UserDataTypes.NullableDateTime),
typeof(System.DateTime)});
			}
		}
	}
```