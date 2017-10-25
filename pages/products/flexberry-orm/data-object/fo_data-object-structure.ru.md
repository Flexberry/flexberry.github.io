---
title: Структура классов объектов данных
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, class 
summary:  Описание и примеры классов данных и их связей
toc: true
permalink: ru/fo_data-object-structure.html
lang: ru
---

Используемая в примерах диаграмма:

![](/images/pages/products/flexberry-orm/data-object/data-object-structure.jpg)

## Класс данных

Все классы данных наследуются от базового [DataObject](fo_data-object.html), обеспечивающего основные манипуляции:

* [статусы](fo_object-status.html), 
* хранение данных, 
* хранение [копии данных](fo_data-object-copy.html) для вычисления изменённых [атрибутов](fo_attributes-class-data.html),
* означивание и хранение [первичного ключа](fo_primary-keys-objects.html).

```csharp
public class SimpleDataObject:DataObject 
	{
		public SimpleDataObject()
		{
		}
	}
```

## Атрибут

Атрибуты UML-класса прописываются в класс данных приватным членом указанного типа с именем атрибута и соответствующим свойством для получения/установки значения. Свойство всегда виртуальное, но с любым модификатором.

```csharp
public class SimpleDataObject:DataObject 
	{
		public SimpleDataObject()
		{
		}
		private string fName;
		private int fIntAttr;
		public virtual string Name {get{return fName;} set{fName=value;}}		
		public virtual int IntAttr {get{return fIntAttr;} set{fIntAttr=value;}}
	}
```

## Мастер

[Мастеровая ассоциация](fd_master-association.html) представлена в классе данных членом соответствующего типа – мастерового класса данных, и аналогичным свойством, объявленным как virtual.
Пример:

```csharp
private MasterDataObject fMaster;
public virtual MasterDataObject Master {get{return fMaster;} set{fMaster=value;}}
```

## Детейл

Если [класс является агрегируемым](fo_detail-associations-properties.html), то:

1.Генерируется свойство, указывающее [агрегатор(шапку)](fd_key-concepts.html). Это свойство помечается атрибутом `ICSSoft.STORMNET.DataObject.AgregatorAttribute.`
Пример класса данных детейла:

```csharp
public class DetailDataObject:DataObject
	{
		public DetailDataObject()
		{
		}
		private string fStringAttr;
		public virtual string StringAttr {get{return fStringAttr;} set{fStringAttr=value;}}
		private SimpleDataObject fSimple;
		[Agregator]
		public virtual SimpleDataObject Simple {get{return fSimple;}set{fSimple=value;}}
}
```

2.Создаётся класс, производный от `DetailArray`, обеспечивающего базовые операции над массивом объектов данных агрегируемого класса. Вводятся операции получения объекта данных по ключу и по индексу, а также добавления объекта. Делается это для того, чтобы обеспечить доступ к массиву по индексу и добавление объекта.
Пример класса массива объектов данных для данного класса данных:

```csharp
public class DetailArrayOfDetailDataObject:DetailArray 
	{
		public DetailArrayOfDetailDataObject(SimpleDataObject simpledataobject):base(typeof(DetailDataObject), simpledataobject)
		{
		}
		public DetailDataObject this[object key]	{get {return (DetailDataObject) GetByKey(key);} set {SetByKey(key,value);}}
		public DetailDataObject this[int index]
		{
			get 
			{
				return (DetailDataObject) ItemByIndex(index);
			}
		}
		public void Add(DataObject dataobject)
		{
			AddObject(dataobject);
		}	
	}
```

3.Если класс является агрегирующим, то детейловая ассоциация представляется в объекте данных членом соответствующего типа-класса, производного от `DetailArray` и аналогичным свойством.
Пример:

```csharp
private DetailArrayOfDetailDataObject fDetails;
public virtual DetailArrayOfDetailDataObject Details {get{return fDetails;} set{fDetails=value;}}
```

Также пример доступен [по адресу] (https://github.com/Flexberry/FlexberryORM-DemoApp/tree/master/FlexberryORM/CDLIB/Objects).

## Наследование

Если класс является [унаследованным](fd_inheritance.html), то в исходном коде он наследуется от соответствующего предка (в соответствии с диаграммой).
Если у унаследованного класса есть атрибут (детейл, мастер) с тем же именем, что и у предка, он перегружается. При этом не может меняться тип.
Операции UML-класса генерируются виртуальными методами .Net-класса. Методы также могут быть перегружены в наследниках, для чего у наследника должен быть указан метод с аналогичным числом и типом параметров.