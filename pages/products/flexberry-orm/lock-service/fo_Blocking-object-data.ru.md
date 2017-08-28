---
title: Блокирование объекта данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, объекты данных, блокировки
summary: Описание, методы и пример блокировок
toc: true
permalink: ru/fo_blocking-object-data.html
lang: ru
---

Следует отметить, что механизм блокировок реализуется [сервисом блокировок](fo_lock-service.html) `LockService`, который сохраняет информацию об устанавливаемой на объект данных блокировке в хранилище данных. Это позволяет защитить от изменения объект данных, даже если он редактируется другим пользователем.

Однако возникают ситуации, когда удобно временно защитить какой-либо объект данных от изменения, например, от изменения по ошибке из какого-либо другого места программы. В этом случае вместо использования сервиса блокировок можно применить описываемый в данной статье способ.

При использовании описываемого метода информация о блокировке хранится непосредственно в экземпляре [объекта данных](fo_data-object.html): установка блокировки не отразится на других инстанциях данного объекта.

Пример использования: в Windows-приложении блокировка устанавливается данным образом для объекта данных, если открывается его форма редактирования, а у пользователя недостаточно полномочий на изменение объекта.

## Методы блокировки в DataObject

Для блокировки необходимо вызвать у объекта данных метод `DataObject#LockObject` с каким-либо ключом блокирования (любое значение любого типа).

Разблокировать можно вызовом метода `DataObject#LockObject` с передачей того же ключа. Таким образом, разблокировать объект можно, только зная ключ блокирования, что исключает возможность случайного изменения объекта данных.

Всё время, пока объект заблокирован, свойство `IsReadOnly` возвращает `true`, а защищённый (`protected`) метод объекта данных `CheckReadOnly` выдаёт исключение `DataObjectIsReadOnlyException`. Соответственно, проверку на заблокированность программист должен либо проводить «снаружи» объекта данных вызовом свойства `IsReadOnly`, либо добавить внутри свойства объекта данных перед изменением значения вызов метода `CheckReadOnly`. Не следует злоупотреблять данной возможностью (добавлять `CheckReadOnly` во много мест), поскольку это вызовет падение производительности при обращении к свойствам - лучше явно проверять `IsReadOnly`.

## Пример

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
			//Для my необходима явная проверка
			if (my.IsReadOnly) 
			{
				Console.WriteLine("my Locked");
			}
			else
			{
				my.MyAttr = "STORM.NET Framework";
			}

			//Для myover - нет
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

			Console.WriteLine("\n\n\nPress Enter to exit.");
			Console.Read();
		}
	}
}
```
