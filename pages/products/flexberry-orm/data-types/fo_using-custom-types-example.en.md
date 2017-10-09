---
title: An example of using custom types
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data types, example
summary: Using typedef in applications
toc: true
permalink: en/fo_using-custom-types-example.html
lang: en
---

Существует возможность создать собственный тип данных на диаграмме Flexberry Desinger. Для этого следует разместить новый класс на диаграмме и изменить его стереотип на "type". Как результат, класс будет доступен для определения свойств классов с собственным типом во всей стадии Flexberry Desinger.

Flexberry ORM генерирует пустой шаблон класса в C#. Также, необходимо разрешить [typedef](fd_typedef.html), в котором будет храниться этот тип в БД. Э Это можно сделать на [карте типов](fd_types-map.html).

Например, существует класс `Dollar` на диаграмме `Entities`, а также на его сгенерированный код в CDLIB(Objects)/Dollar.cs:

```csharp
[ICSSoft.STORMNET.StoreInstancesInType(typeof(SQLDataService), typeof(decimal))]
public class Dollar
{
    
    private int fDollars;
    private int fCents;
    
    public Dollar(int Dollars, int Cents)
    {
        fDollars = 0;
        fCents = 0;
        this.Dollars = Dollars;
        this.Cents = Cents;
    }

    public int Dollars 
    {
        get{return fDollars;}
        set 
        {
            if (value>=0) 
                fDollars = value; 
        }
    }

    public int Cents
    {
        get {return fCents;}
        set
        {
            if (value>=0 && value<100)
                fCents = value;
            else
                throw new ParameterOutOfRangeException();
        }
    }

        public override string ToString()
    {
        return fDollars==0 ? string.Format(".{0:00}¢", Cents) : string.Format("${0}.{1:00}", Dollars, Cents) ;
    }

        {...}

}
```

Этот класс был реализован вручную на основе автоматически сгенерированного шаблона. Он позволяет отображать доллар в дружественном к пользователю виде: $1.24 или .46¢.

Как указано в атрибуте [StoreInstancesInType](fo_convert-type-property.html), этот класс будет храниться в БД в виде decimal. Это возможно благодаря определенным в классе преобразованиям:

```csharp
public static implicit operator decimal(Dollar value)
{
    return (decimal)value.Dollars+((decimal)value.Cents)/100;
}

public static implicit operator Dollar(decimal value)
{
    return new Dollar((int)value, (int)((value - (int)value) * 100));
}
```

`Dollar` используется как тип свойства `Price` в классе `CD`:

```csharp
public virtual IIS.CDLIB.Dollar Price
{
    get
    {
        IIS.CDLIB.Dollar result = this.fPrice;
        return result;
    }
    set
    {
        this.fPrice = value;
    }
}
```

### Пример загрузки и сохранения объекта с собственными типами

```csharp
IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);

// Загрузка объекта из БД по представлению CD_E.
dataService.LoadObject(CD.Views.CD_E, cdda);

// Изменим цену.
cdda.Price = new Dollar(0, 55);

// Сохраним объект в БД.
dataService.UpdateObject(cdda);

Console.WriteLine(string.Format("'{0}' price is {1}", cdda.Name, cdda.Price));
```
