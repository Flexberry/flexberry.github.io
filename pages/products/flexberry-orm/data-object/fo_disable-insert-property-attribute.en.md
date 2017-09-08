---
title: DisableInsertPropertyAttribute
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, queries to the database
summary: Excluding objects from the query
toc: true
permalink: en/fo_disable-insert-property-attribute.html
lang: en
---

[Атрибут](fo_attributes-class-data.html) `DisableInsertPropertyAttribute` позволяет исключить свойство класса из Insert-запросов, генерируемых [сервисом данных](fo_data-service.html). Рекомендуется к применению, если есть значение по умолчанию, определённое в БД, которое надо применять при создании [объекта](fo_data-object.html), либо если БД сама при вставке правильно инициализирует это значение (различные идентификаторы).

``` csharp
private int fId = 100;
[DisableInsertPropery(true))
public virtual int Id
{
	get
	{
		int result = this.fId;
		return result;
	}
	set
	{
		this.fId = value;
	}
}
```
