---
title: Пример использования собственных типов
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: en/fo_using-custom-types-example.html
folder: products/flexberry-orm/
lang: en
---

## Пример использования собственных типов данных в приложении

Вы можете создать собственный тип данных на диаграмме Flexberry: разместите новый класс на диаграмме и измените его стереотип на "type".
Как результат, вы сможете использовать его для определения свойств классов с собственным типом во всей стадии Flexberry.

Flexberry генерирует пустой шаблон класса в C#. Также, вам необходимо разрешить ["typedef"](classes-with-stereotype-typedef.html), в котором будет храниться этот тип в БД. Э Это можно сделать на [карте типов Flexberry](fd_types-map.html).

Давайте посмотрим на класс `Dollar` на диаграмме `Entities`, а также на его сгенерированный код в CDLIB(Objects)/Dollar.cs:

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
Как указано в атрибуте [StoreInstancesInType](fo_convert-type-property-object-data-to-type-storage.html), этот класс будет храниться в БД в виде decimal. Это возможно благодаря определенным в классе преобразованиям:

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

Dollar используется как тип свойства Price в классе CD:

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

Пример загрузки и сохранения объекта с собственными типами:

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