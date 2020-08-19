---
title: features of setting default values
sidebar: flexberry-orm_sidebar
keywords: data Objects, Flexberry ORM data types
summary: Features methods of specifying the value default
toc: true
permalink: en/fo_features-dafault-value.html
lang: en
autotranslated: true
hash: ab4c6cf2d10f70df935004328592dfe01a5fc8f8e9d586b83976290a4da5ae2b
---

Set default values available when you edit a [class diagram](fd_class-diagram.html) and in the code. Consider the main features of these methods.

## Assignment default values on the class diagram

### Scalar types

To indicate [the class diagram](fd_class-diagram.html) the default value for fields scalar types it is sufficient to define it in the [DefaultValue](fo_attributes-class-data.html) in the appropriate fields (note the peculiarities of generation for the [DefaultValue](fo_attributes-class-data.html)).

{% include note.html content="If the type of default value is not appropriate for the field type, the project will be generated but will not be compiled." %}

### Nullable scalar types

The job defaults to [class diagram](fd_class-diagram.html) for [nullable scalar-types](fd_nullable-types.html) is similar to scalar types.

Generic Nullable types can be initialized with values that accrue from the internal types. For example, if you assign a value `Now` `Default` in the field for the attribute on the class diagram, you can obtain this code:

```cs
private System.Nullable<System.DateTime> fRelease = System.DateTime.Now;
```
{% include note.html content="Please note that set default `null` will not work." %}

### Complex types

To set the default value on the class diagram for the field of complex type (for example specified by the class with the [stereotype](fd_key-concepts.html) [Types of data classes with the stereotype-type-and-their-properties](fd_data-types-properties.html)) in the General case impossible.

### Type synonyms

For [synonyms types](fd_typedef.html) set a default value on the class diagram, is similar to how it occurs in types, synonyms, which they are.

## The task default values in the code

| Admission | Advantages | Disadvantages |
|---|---|---|
| Set the default values in the form's code Allows for each form to define their default values | - Requires to initialize the default values in each form |
| Set the default values in the [data object](fo_data-object.html) | Allows you to set the default value in one place | - Complication of code if each form requires its own defaults |
| Set the default values in the [business server](fo_business-server.html) | Allows you to set the default value in one place and separate logic from UI | - Complication of code if each form requires its own defaults |

### The task default values in the form's code

```csharp
public class WinformC__ПациентE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.BSTest.DPDIC__ПациентE
{
	private void WinformC__ПациентE_DataObjectOnFormLoaded(object sender, EventArgs e)
	{
		((Пациент) this.DataObject).ФИО = "Vipasyana"; //set default values 
		...
	}
	//... 
}
```

### The task default values in the data object

```csharp
public class Пациент : ICSSoft.STORMNET.DataObject
{
	private fФИО = "Vipasyana"; //setting default values 
	public string ФИО 
	{
		get
		{
			return fФИО;
		}
		
		set
		{
			fФИО = value;
		}
	}
	//... 
}
```

### Specify a default business server
Form code:

```csharp
public class WinformC__ЗаписьНаПриёмE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.BSTest.DPDIC__ЗаписьНаПриёмE
{
	private void WinformC__ЗаписьНаПриёмE_DataObjectOnFormLoaded(object sender, EventArgs e)
	{
		//define which business servers defined for the object type "Zapolarie" 
		BusinessServer[] businessServers = BusinessServerProvider.GetBusinessServer(typeof(ЗаписьНаПриём), DataServiceObjectEvents.OnAllEvents, DataServiceProvider.DataService);
		if (businessServers.Length>0) 
		{
			TestBS curBS = (TestBS) businessServers[0]; //get first and only (in this case only) 
			curBS.InitializeRecord(this.DataObject as ЗаписьНаПриём); //set default values 
			EditManager.Change(); //apply the changes 
		}
	}
	//... 
}
```

Code business server:

```csharp
public class TestBS : ICSSoft.STORMNET.Business.BusinessServer
{
	public void InitializeRecord(IIS.BSTest.ЗаписьНаПриём RecordToInitialize)
	{
		RecordToInitialize.Дата = new DateTime(2012,12,12); //setting default values 
	}
	//... 
}
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}