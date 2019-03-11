--- 
title: Example of using native types 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data types, example 
summary: Example of using typedef in applications 
toc: true 
permalink: en/fo_using-custom-types-example.html 
lang: en 
autotranslated: true 
hash: 03af622d4a3d2ba623581430daa8bfb3b08260a0ea3c48b238c0b028066b1dcf 
--- 

You can create your own data types in the diagram Flexberry Desinger. This should place the new class in the diagram and change its stereotype to "type". As a result, the class will be available to define the properties of classes with a native type in all stage Flexberry Desinger. 

Flexberry ORM generates an empty class template in C#. Also, you need to allow [typedef](fd_typedef.html), in which to store this type in the database. E you can do This on the [map types](fd_types-map.html). 

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

This class was implemented manually based on automatically generated template. It allows you to display the dollar in the user friendly form: $or 1.24 .46¢. 

As indicated in the attribute [StoreInstancesInType](fo_convert-type-property.html), this class will be stored in the database in the form of a decimal. This is possible thanks to a specific class to change: 

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