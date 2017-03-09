---
title: Перегрузка атрибутов, ассоциаций, методов 
sidebar: flexberry-orm_sidebar
keywords: Ключевые понятия
toc: true
permalink: ru/fo_overloading-attributes-associations-methods.html
---

Атрибуты, методы, ассоциации могут быть перегружены, в соответствии с правилами .Net.


Для того, чтобы перегрузить некоторое свойство, метод, необходимо отрисовать в наследуемом классе атрибут, метод, с тем же именем, возвращающий значение того же типа, если метод, то с тем же числом и типом параметров.

Например:

![](/images/pages/products/flexberry-orm/flexberry-plugins/override.gif)

В данном случае, будут перегружены атрибут и метод, следующим образом:

В унаследованном классе свойство будет обращаться к свойству базового класса, а метод, соответственно, также, к методу базового класса.

Соответствующий описанной ситуации исходный код потомка приведён ниже:

```csharp
public class Потомок : ICSSoft.Product.Предок
  {
		// *** Start programmer edit section *** (Потомок CustomMembers)
		// *** End programmer edit section *** (Потомок CustomMembers)
		// *** Start programmer edit section *** (Потомок.Атрибут CustomAttributes)
		// *** End programmer edit section *** (Потомок.Атрибут CustomAttributes)
		public override int Атрибут
		{
			get
			{
				// *** Start programmer edit section *** (Потомок.Атрибут Get start)
				// *** End programmer edit section *** (Потомок.Атрибут Get start)
				int result = base.Атрибут;
				// *** Start programmer edit section *** (Потомок.Атрибут Get end)
				// *** End programmer edit section *** (Потомок.Атрибут Get end)
				return result;
			}
			set
			{
				// *** Start programmer edit section *** (Потомок.Атрибут Set start)
				// *** End programmer edit section *** (Потомок.Атрибут Set start)
				base.Атрибут = value;
				// *** Start programmer edit section *** (Потомок.Атрибут Set end)
				// *** End programmer edit section *** (Потомок.Атрибут Set end)
			}
		}
  
		// *** Start programmer edit section *** (Потомок.Метод System.Int32 CustomAttributes)
		// *** End programmer edit section *** (Потомок.Метод System.Int32 CustomAttributes)
		public override string Метод(int Параметр)
		{
			// *** Start programmer edit section *** (Потомок.Метод System.Int32 method implementation)
			return base.Метод(Параметр);
			// *** End programmer edit section *** (Потомок.Метод System.Int32 method implementation)
		}
	}
```