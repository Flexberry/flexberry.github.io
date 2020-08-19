---
title: Design your own types
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data types, example, custom, compare, IComparableType
summary: Example of using classes with steriotip type applications
toc: true
permalink: en/fo_using-custom-types-example.html
lang: en
autotranslated: true
hash: 1ef574f16f9a8c48ecf105c0b1a1f6509fadb94ae4ab77fa5f01d5842e479819
---

You can create your own data types in the diagram Flexberry Desinger. This should place the new class in the diagram and change its stereotype to» «type. As a result, the class will be available to define the properties of classes with a native type in all stage Flexberry Desinger.

Flexberry ORM generates an empty class template in C# for classes with the stereotype» «type.

Be sure to specify the type of storage that will be stored in the database the field corresponding to attribute this to our type. You can do this on the [map types](fd_types-map.html) differently for each DBMS.

The chart should also set the StoreInstancesInType, which will define the correspondence between service type and data .NET type, which will be cast when reading from the database. This .NET type must match the type of the storage in the map types to the mechanisms ADO.NET practiced correctly.

For example, there is a class `Dollar` `Entities` in the diagram and also the generated code in CDLIB(Objects)/Dollar.cs:

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
            if (value >= 0) 
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

This class was implemented manually based on automatically generated template. It allows you to display the dollar in the user friendly form: $or 1.24 .46¢.

As indicated in the attribute [StoreInstancesInType](fo_convert-type-property.html) and the map types, this class will be stored in the database in the form `decimal`. This is possible thanks to a specific class to change:

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

`Dollar` is used as the property type `Price` in the class `CD`:

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

## Compare attributes of user-defined types between

For the correct operation of the ORM analysis when the changed fields of the object should choose one of the following strategies:

1. Override method `ToString()` that is guaranteed to answer the question as to whether the 2 variables of the specified type between them. This method is used ORM-Ohm by default for all non-standard types.
2. To inherit a developed type of the interface `IComparableType`. This can be done even on the chart through the use of a class with the stereotype externalinterface» «and Inheritance. If the data type of such an interface, the ORM performs a comparison not using a cast to string and using the method from this interface. This method is preferred for performance reasons.

Read more about how to work check for changes in the data objects written in [this article](fo_object-status.html).

### Example of loading and saving object with its own types

```csharp
IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);

// Load object from DB upon submission CD_E. 
dataService.LoadObject(CD.Views.CD_E, cdda);

// Will change the price. 
cdda.Price = new Dollar(0, 55);

// Save the object to the database. 
dataService.UpdateObject(cdda);

Console.WriteLine(string.Format("'{0}' price is {1}", cdda.Name, cdda.Price));
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}