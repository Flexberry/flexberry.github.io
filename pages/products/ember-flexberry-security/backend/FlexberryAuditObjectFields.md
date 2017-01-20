---
title: Поля аудита
sidebar: product--sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/flexberry-audit-object-fields.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Audit](audit-web.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll, ICSSoft.STORMNET.Business.Audit.dll
* '''Предназначение''': Основными полями аудита, которые хранятся непосредственно в объекте, являются поля `CreateTime`, `Creator`, `EditTime`, `Editor`.
</td>
</tr></tbody></table></a>
</div>
# Поля аудита
Полями аудита являются:
* `CreateTime` - дата создания объекта 
* `Creator` - создатель объекта, пользователь, создавший его
* `EditTime` - дата последнего изменения объекта
* `Editor` - редактор объекта, пользователь, изменивший его в последний раз

На диаграмме поля аудита добавляются непосредственно в объект путём нажатия [соответствующей галочки в настройках аудита класса](audit-Flexberry-setup.html).

После генерации поля аудита добавляются непосредственно в объект и контролируются специальным интерфейсом «IDataObjectWithAuditFields».

Значения, которые [при выполнении подставляются в данные поля], берутся из `[CurrentUserService](not-stored-properties-and-audit.html)`.

# Пример полей аудита в коде класса
```cs
public class Клиент : ICSSoft.STORMNET.DataObject, IDataObjectWithAuditFields
{
	// ...

	private System.Nullable<System.DateTime> fCreateTime;
	
	private string fCreator;
	
	private System.Nullable<System.DateTime> fEditTime;
	
	private string fEditor;

	/// <summary>
	/// Время создания объекта.
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
	/// Создатель объекта.
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
	/// Время последнего редактирования объекта.
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
	/// Последний редактор объекта.
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
