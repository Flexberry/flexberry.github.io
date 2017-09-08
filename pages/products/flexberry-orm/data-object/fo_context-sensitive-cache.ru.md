---
title: Контекстно-зависимый кэш объектов данных
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, получение ссылок
summary: Получение ссылок на объекты данных
toc: true
permalink: ru/fo_context-sensitive-cache.html
lang: ru
---

Бывает удобно не хранить явно ссылок на объекты данных, а получать эти ссылки по `.Net` типу и первичному ключу. Бывают также ситуации, когда программист вообще не имеет явных ссылок и не имеет другой возможности их получить, кроме как по `.Net` типу и первичному ключу.

Такая возможность реализована в `CASEBERRY FRAMEWORK`, она называется `контекстно-зависимым кэшем` и поддерживается  классом `DataObjectCache`. Программист может ограничить часть кода, в котором применяется кэширование, методами `DataObjectCache.StartCaching, DataObjectCache.StopCaching`. Внутри этой части кода можно положить в кэш объект данных (`DataObjectCache.AddDataObject`) и получить его (`DataObjectCache.GetLivingDataObject`).

```csharp
class Class1
	{
		[STAThread)
		static void Main(string[) args)
		{
            var doc = new DataObjectCache();
			doc.StartCaching(false);
			object pkey = prv_CreateDataObject();
			SimpleDataObject sdo = (SimpleDataObject) doc.GetLivingDataObject(typeof(SimpleDataObject), pkey);
			doc.StopCaching();
			Console.WriteLine(String.Format("Getted from cache dataobject name = {0}",sdo.Name));
			Console.Read();
		}
		private static object prv_CreateDataObject()
		{
            var doc = new DataObjectCache();
			SimpleDataObject sdo = new SimpleDataObject();			
			sdo.Name="Объект данных";
			Console.WriteLine(String.Format("Created dataobject name = {0}",sdo.Name));
			doc.AddDataObject(sdo);
			return sdo.__PrimaryKey;
		}
	}
```

Каждый вызов метода `DataObjectCache.StartCaching` образует контекст, простирающийся до вызова `DataObjectCache.StopCaching`.  Вызов метода `DataObjectCache.StartCaching` внутри контекста образует вложенный контекст.

![](/images/pages/products/flexberry-orm/data-object/sensitive-cache.png)

Объекты, добавляемые в контекстах более низкого уровня, автоматически попадают в контексты более высокого.

Доступностью объектов более высокого уровня в контекстах более низкого программист может управлять. Можно «отрезать» вышестоящий контекст. Для этого служит параметр `ClipParentCache` метода `DataObjectCache.StartCaching`. Если метод вызван с параметром `true`, то объекты контекста уровнем выше никогда не доступны создаваемому контексту и всем нижележащим.

Метод `DataObjectCache.GetLivingDataObject` просматривает только контекст текущего уровня.
Метод `DataObjectCache.GetExtLivingDataObject` просматривает все вышележащие контексты, кроме текущего, но не выше «отрезанного».

Внутри одного метода нельзя делать вложенные контексты, за исключением случая, когда новый контекст «отрезает» вышестоящий.
