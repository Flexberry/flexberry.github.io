--- 
title: Block of the data object 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, object data, lock 
summary: Description, methods, and sample locks 
toc: true 
permalink: en/fo_blocking-object-data.html 
lang: en 
autotranslated: true 
hash: b483216d92711a6a708dda05bb21239de25132349b03f32a237a4ae8832c7d53 
--- 

It should be noted that the locking mechanism is implemented [service locks](fo_lock-service.html) `LockService` that stores information about the selected data object to the lock in the data store. This is to protect against changes in the data object, even if it is being edited by another user. 

However, there are situations when it is convenient temporarily to protect any data object from being modified, for example, from the change in error from any other place in the program. In this case, instead of using the service of locks, you can apply described in this article method. 

When using the described method, information on lock is stored directly in the instance of [data object](fo_data-object.html): lock setting will not affect other instances of the object. 

Usage example: in a Windows application lock is set in this manner for the data object, if it opens the edit form and the user does not have authority to modify the object. 

## blocking Methods in the DataObject 

To lock you need to call the data object method `DataObject#LockObject` with any key block (any value of any type). 

Unblock can call the method `DataObject#LockObject` passing the same key. Thus, to unlock the object only knowing the key lock that prevents accidental changes to the object data. 

All the time while the object is locked, the property `IsReadOnly` returns `true` and protected (`protected`) method of the data object throws an exception `CheckReadOnly` `DataObjectIsReadOnlyException`. Accordingly, checking for blockages, the programmer must either hold «outside» data object with a call to `IsReadOnly` properties, or add properties inside of the data object before changing values in the method call `CheckReadOnly`. You should not abuse this opportunity (add `CheckReadOnly` in many places), as this will cause a performance drop when accessing the properties - it is better to explicitly check `IsReadOnly`. 

## Example 

```csharp 
using System;
using ICSSoft.STORMNET;

namespace Locking
{
	public class MyDataObject:DataObject
	{
		private string fMyAttr;

		public virtual string MyAttr
		{
			get
			{
				return fMyAttr;
			}
			set
			{
				fMyAttr=value;
			}
		}
	}

	public class MyOverridedDataObject:MyDataObject
	{
		public override string MyAttr
		{
			set
			{
				CheckReadOnly();
				base.MyAttr = value;
			}
		}

	}

	class Class1
	{

		private static MyDataObject my = new MyDataObject();
		private static  MyOverridedDataObject myover = new MyOverridedDataObject();

		private static void prv_PrintObjects()
		{
			Console.WriteLine("my.MyAttr={0}, myover.MyAttr={1}", my.MyAttr, myover.MyAttr);
		}

		[STAThread)
		static void Main(string[) args)
		{
			my.MyAttr = "STORM.NET";
			myover.MyAttr = "STORM.NET";

			prv_PrintObjects();

			my.LockObject(1);
			myover.LockObject(2);
			//Necessary for my clear test 
			if (my.IsReadOnly) 
			{
				Console.WriteLine("my Locked");
			}
			else
			{
				my.MyAttr = "STORM.NET Framework";
			}

			//For myover - no 
			try
			{
				myover.MyAttr= "STORM.NET Framework";
			}
			catch (Exception exc)
			{
				Console.WriteLine(exc.Message);
			}

			prv_PrintObjects();

			my.UnLockObject(1);
			myover.UnLockObject(2);

			my.MyAttr = "STORM.NET Framework";
			myover.MyAttr= "STORM.NET Framework";

			prv_PrintObjects();

			Console.WriteLine("\n\n\press Enter to exit.");
			Console.Read();
		}
	}
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}