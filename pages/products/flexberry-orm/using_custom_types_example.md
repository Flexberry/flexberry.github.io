---
title: Пример использования собственных типов
sidebar: product--sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/using_custom_types_example.html
folder: product--folder
lang: ru
---

Вы можете создать собственный тип данных на диаграмме Flexberry: разместите новый класс на диаграмме и измените его стереотип на "type".
Как результат, вы сможете использовать его для определения свойств классов с собственным типом во всей стадии Flexberry.

Flexberry генерирует пустой шаблон класса в C#. Также, вам необходимо разрешить "typedef", в котором будет храниться этот тип в БД. Это можно сделать на карте типов Flexberry.

Давайте посмотрим на класс `Dollar` на диаграмме `Entities`, а также на его сгенерированный код в CDLIB(Objects)/Dollar.cs:

```cs
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
Как указано в атрибуте StoreInstancesInType, этот класс будет храниться в БД в виде decimal. Это возможно благодаря определенным в классе преобразованиям:
```

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
```

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