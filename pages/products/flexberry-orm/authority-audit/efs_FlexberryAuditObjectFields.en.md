--- 
title: Field audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_flexberry-audit-object-fields.html 
lang: en 
autotranslated: true 
hash: 7cf68f2acf682fa55bf4eaf828f6953451e7330491934bc27e339c06a020a69a 
--- 

# audit Fields 
Fields of audit are: 
* `CreateTime` - creation date of the object 
* `Creator` - the Creator of the object, the user who created it 
* `EditTime` - last modified object 
* `Editor` - object editor, the user who changed it last time 

The chart audit fields are added directly to the object by clicking the appropriate box in the settings of audit-class](fo_audit-setup.html). 

After generating the audit fields are added directly to the object and controlled by a special interface `IDataObjectWithAuditFields`. 

The values that [when you run populate these fields] are taken from [CurrentUserService](efs_not-stored-properties-and-audit.html). 

# Example of a field audit in the code of the class 

```csharp
public class Клиент : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
{
	// ... 

	private System.Nullable<System.DateTime> fCreateTime;
	
	private string fCreator;
	
	private System.Nullable<System.DateTime> fEditTime;
	
	private string fEditor;

	/// <summary> 
	/// Create time object. 
	/// </summary> 
	public virtual System.Nullable<System.DateTime> CreateTime
	{
		get
		{
			System.Nullable<System.DateTime> result = this.fCreateTime;
			return result;
		}
		set
		{
			this.fCreateTime = value;
		}
	}
	
	/// <summary> 
	/// The Creator of the object. 
	/// </summary> 
	[StrLen(255)]
	public virtual string Creator
	{
		get
		{
			string result = this.fCreator;
			return result;
		}
		set
		{
			this.fCreator = value;
		}
	}
	
	/// <summary> 
	/// Time of last edit of an object. 
	/// </summary> 
	public virtual System.Nullable<System.DateTime> EditTime
	{
		get
		{
			System.Nullable<System.DateTime> result = this.fEditTime;
			return result;
		}
		set
		{
			this.fEditTime = value;
		}
	}
	
	/// <summary> 
	/// The last editor of the object. 
	/// </summary> 
	[StrLen(255)]
	public virtual string Editor
	{
		get
		{
			string result = this.fEditor;
			return result;
		}
		set
		{
			this.fEditor = value;
		}
	}
}
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/