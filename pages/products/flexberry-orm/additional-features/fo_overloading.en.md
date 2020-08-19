--- 
title: Overload of attributes, associations, methods, 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, attributes, methods, associations, overload, example 
summary: describes how to overload attributes, associations and methods 
toc: true 
permalink: en/fo_overloading.html 
lang: en 
autotranslated: true 
hash: 5d5cd70fe06d5006f381b43fbacad350a8dd3560649ff1d0c4d12b308eedcaa0 
--- 

Attributes, methods, associations can be overloaded, in accordance with the rules .Net. 

In order to overload a property, method, must be rendered in an inherited class attribute, a method with the same name that returns the value of the same type, if the method, with the same number and type of parameters. 

For example: 

![](/images/pages/products/flexberry-orm/additional-features/override-orm.png) 

In this case, the attribute will be overloaded and the method in the following way: 

In an inherited class property will appeal to property from a base class and a method, respectively, to the method of the base class. 

Corresponding to the described situation, the source code of a descendant to the following: 

```csharp
public class Потомок : ICSSoft.Product.Предок
  {
		// *** Start programmer edit section *** (Descendant CustomMembers) 
		// *** End programmer edit section *** (Descendant CustomMembers) 
		// *** Start programmer edit section *** (a Descendant.Attribute In CustomAttributes) 
		// *** End programmer edit section *** (a Descendant.Attribute In CustomAttributes) 
		public override int Атрибут
		{
			get
			{
				// *** Start programmer edit section *** (a Descendant.The attribute Get start) 
				// *** End programmer edit section *** (a Descendant.The attribute Get start) 
				int result = base.Атрибут;
				// *** Start programmer edit section *** (a Descendant.The Get attribute end) 
				// *** End programmer edit section *** (a Descendant.The Get attribute end) 
				return result;
			}
			set
			{
				// *** Start programmer edit section *** (a Descendant.Attribute of the Set start) 
				// *** End programmer edit section *** (a Descendant.Attribute of the Set start) 
				base.Атрибут = value;
				// *** Start programmer edit section *** (a Descendant.Attribute Set end) 
				// *** End programmer edit section *** (a Descendant.Attribute Set end) 
			}
		}
  
		// *** Start programmer edit section *** (a Descendant.Method System.Int32 CustomAttributes) 
		// *** End programmer edit section *** (a Descendant.Method System.Int32 CustomAttributes) 
		public override string Метод(int Параметр)
		{
			// *** Start programmer edit section *** (a Descendant.Method System.Method implementation Int32) 
			return base.Метод(Параметр);
			// *** End programmer edit section *** (a Descendant.Method System.Method implementation Int32) 
		}
	}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}