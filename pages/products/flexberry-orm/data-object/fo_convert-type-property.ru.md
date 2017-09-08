---
title: Преобразование типа свойства объекта данных к типу хранилища
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, типы данных
summary: Описание преобразования типов данных и пример его использования
toc: true
permalink: ru/fo_convert-type-property.html
lang: ru
---

Свойства классов данных могут иметь самый различный тип, как стандартный для `.Net framework` (например, такой как `System.Int32 — int` в `C#, System.String — string` в `C#`), так и нестандартный (какой-либо пользовательский, не `.Net`-тип).

Значения [стандартных типов](fo_flexberry-orm-types.html) преобразуются [сервисами данных](fo_data-service.html) в типы хранилища некоторым предопределённым образом, например, `System.Int32` в `LONG, System.String` в `VARCHAR`.  Однако, сервис данных никак «не знает», каким образом должно преобразовываться значение некоторого пользовательского типа (иначе говоря, какого типа значение в хранилище). Следовательно, значение пользовательского типа должно приводиться к значению стандартного типа `.Net` и обратно, а со значением стандартного типа сервис данных уже «разберётся».

Как именно такое приведение должно выполняться конкретным сервисом данных, указывается непосредственно пользовательскому типу атрибутом [StoreInstancesInTypeAttribute](fd_data-types-properties.html). Параметрами указываются: тип сервиса данных и стандартный тип, к которому должно выполняться приведение.

От пользовательского типа требуется следующее:

* Поддержка явного (`explicit`) или неявного (`implicit`) преобразования к стандартному типу.
* Поддержка явного (`explicit`) или неявного (`implicit`) обратного преобразования от стандартного типа.
* Перегрузка метода `ToString()`.

Разумеется, если различные сервисы данных требуют приведения к различным типам, необходимо несколько раз указывать атрибут для каждого сервиса данных, а пользовательский тип должен поддерживать преобразования ко всем типам.

## Пример пользовательского типа

```csharp
[StoreInstancesInType(typeof(SQLDataService),typeof(decimal)))
public struct Деньги
{
	public class ParameterOutOfRangeException:Exception
	{
			public ParameterOutOfRangeException(){}
	}
	
	private int fieldРубли;
	private int fieldКопейки;
	
	public Деньги(int Руб,int Коп)
	{
			fieldРубли = 0;
			fieldКопейки = 0;
			Рубли = Руб;
			Копейки = Коп;
	}
	
	public int Рубли
	{
			get{return fieldРубли;}
			set
			{
					if (value>=0)
							fieldРубли = value;
			}
	}
	
	public int Копейки
	{
			get {return fieldКопейки;}
			set
			{
					if (value>=0 && value<100)
							fieldКопейки = value;
					else
							throw new ParameterOutOfRangeException();
			}
	}
	
	public static implicit operator decimal(Деньги value)
	{
			return (decimal)value.Рубли+((decimal)value.Копейки)/100;
	}
	
	public static implicit operator Деньги(decimal value)
	{
			return new Деньги((int)value,(int)((value-(int)value)*100));
	}
	
	public override string ToString()
	{
			return Рубли.ToString()+" руб. "+Копейки.ToString()+" коп.";
	}
}
```

Также доступен пример на [GitHub](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/Objects/Dollar.cs).
