--- 
title: Processing of dates in ControlProvider 
sidebar: flexberry-winforms_sidebar 
keywords: DateTime (for dates) 
summary: Shows an example of setting the control to handle the date in CustomControlProvider 
toc: true 
permalink: en/fw_processing-date-in-control-provider.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 1ed04899b95ccd1607857ab767a9e978fab941f22c0e4fc496891028da42d8ed 
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


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}