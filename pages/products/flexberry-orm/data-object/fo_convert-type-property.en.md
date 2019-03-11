--- 
title: type Conversion properties of the data object to the storage type 
sidebar: flexberry-orm_sidebar 
keywords: DataObject Flexberry ORM data types 
summary: Description of data type conversion and an example of its use 
toc: true 
permalink: en/fo_convert-type-property.html 
lang: en 
autotranslated: true 
hash: d183f00be5b25b539507629708e20e4c08ba12c24d39ad7ab565aa63a663a72d 
--- 

Properties of data classes can have the different type as standard `.Net framework` (such as `System.Int32 — int` in `C#, System.String — string` in `C#`) and custom (any user, not `.Net`-type). 

[Standard types](fo_flexberry-orm-types.html) is converted to the [data services](fo_data-service.html) to store types some predefined way, for example, `System.Int32` in `LONG, System.String` in `VARCHAR`. However, data services does not knows» qmo, how to evaluate the value of some custom type (in other words, what type the value in storage). Therefore, the value of the user-defined type must be the value of the standard type `.Net` and back, and with the value of a standard type data service already» «will understand. 

How exactly such a cast must be performed by a specific data service, specified user-defined type attribute [StoreInstancesInTypeAttribute](fd_data-types-properties.html). The details include: type of service data and a standard type to be cast. 

From a user-defined type requires the following: 

* Support for explicit (`explicit`) or implicit (`implicit`) conversion to standard types. 
* Support for explicit (`explicit`) or implicit (`implicit`) the inverse transformation from the standard type. 
* Method overloading `ToString()`. 

Of course, if different data services require a cast to different types, it is necessary to repeatedly specify an attribute for each data service, and a user-defined type must support conversion for all types. 

## an Example of a custom type 

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
			return Рубли.ToString()+"RUB."+Копейки.ToString()+"COP.";
	}
}
``` 

Is also available example [GitHub](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/Objects/Dollar.cs). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}