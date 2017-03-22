---
title: DisableInsertPropertyAttribute
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_disable-insert-property-attribute.html
---
* **Продукт**: [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент**: [Объект данных](fo_dataobject.html)
* **Программная библиотека**: ICSSoft.STORMNET.DataObject.dll
* **Предназначение**: `DisableInsertPropertyAttribute` - [атрибут](fo_attributes-class-data.html), который позволяет исключить свойство класса из Insert-запросов, генерируемых [сервисом данных](fo_data-service.html).

Атрибут `DisableInsertPropertyAttribute` позволяет исключить свойство класса из Insert-запросов. Рекомендуется к применению, если есть Default-значение в БД, которое надо применять при создании объекта, либо если БД сама при вставке правильно инициализирует это значение (различные id).

```cs
private int fId = 100;
[DisableInsertPropery(true)]
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