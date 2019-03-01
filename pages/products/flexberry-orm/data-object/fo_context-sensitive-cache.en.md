--- 
title: Context-dependent cache data objects 
sidebar: flexberry-orm_sidebar 
keywords: data Objects, Flexberry ORM, obtaining references 
summary: Getting references to data objects 
toc: true 
permalink: en/fo_context-sensitive-cache.html 
lang: en 
autotranslated: true 
hash: e8d095b9f9c5f4e6cce447d4a5315e409c3ee6db1200b03d5a3b9b988f2648e7 
--- 

It is useful to explicitly store references to data objects, and to these links for `.Net` type and primary key. There are also situations when the programmer does not have explicit references and has no other way to obtain except by `.Net` type and primary key. 

This option is implemented in `CASEBERRY FRAMEWORK`, it's called `контекстно-dependent кэшем` and supported by the class `DataObjectCache`. The programmer can limit the part of the code that uses caching techniques `DataObjectCache.StartCaching, DataObjectCache.StopCaching`. Inside this part of code can be put in the cache data object (`DataObjectCache.AddDataObject`) and get it (`DataObjectCache.GetLivingDataObject`). 

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
			sdo.Name="The data object";
			Console.WriteLine(String.Format("Created dataobject name = {0}",sdo.Name));
			doc.AddDataObject(sdo);
			return sdo.__PrimaryKey;
		}
	}
``` 

Each method call `DataObjectCache.StartCaching` forms the context, extending to the call `DataObjectCache.StopCaching`. The method call `DataObjectCache.StartCaching` within the context forms a nested context. 

![](/images/pages/products/flexberry-orm/data-object/sensitive-cache.png) 

Objects that are added to the contexts of a lower level are automatically placed in the contexts of higher. 

The availability of higher level objects in contexts of lower programmer can manage. You can "cut" the parent context. This can be set using the method `ClipParentCache` `DataObjectCache.StartCaching`. If the method is invoked with a parameter `true`, the context objects on a higher level never available the created context and all the underlying. 

Method `DataObjectCache.GetLivingDataObject` looks only at the context of the current level. 
Method `DataObjectCache.GetExtLivingDataObject` reviewed all of the overlying contexts other than the current, but not above the "cut off". 

Inside a single method you can not do sub-contexts, except in the case when a new context of "cuts" of the parent. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/