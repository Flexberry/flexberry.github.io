--- 
title: Classes with the stereotype ExternalInterface 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Designer, Flexberry ORM, ExternalInterface, interface, inheritance, example 
summary: features Description and example of generation of ExternalInterface 
toc: true 
permalink: en/fd_external-interface.html 
lang: en 
autotranslated: true 
hash: 5bffea1760fdfbec5b4a11af713511e8fa42f3cc12428e80c210ac033f9c22ff 
--- 

Classes with the [stereotype](fd_key-concepts.html) ExternalInterface is just a indication that you will need to generate [inheritance](fd_inheritance.html) interface for the class which will inherit from this interface. 

## an Example of using the class with the stereotype ExternalInterface 

Let the project `CatInterface` declared interface Koshka 

![](/images/pages/products/flexberry-designer/class-diagram/interface-i-cat.png) 

And in another project `TestExtInterface` structure of the species 

![](/images/pages/products/flexberry-designer/class-diagram/ext-interface-i-cat.png) 

If the project that contains the ExternalInterface to generate the objects in the class `СиамскаяКошка` will be a string of the form 

```csharp
public class СиамскаяКошка : ICSSoft.STORMNET.DataObject, IКошка
``` 
However, the project will fail to compile, because it does not specify where the interface `IКошка`. To fix this, you need to: 

1.Add reference in the project to build `CatInterface(Objects).dll`. 

2.In the code of the feature class to add the Directive: 

```csharp
namespace IIS.TestExtInterface
{
	//... 
	
    // *** Start programmer edit section *** (Using statements) 
    using IIS.CatInterface;
    // *** End programmer edit section *** (Using statements) 
	
	//... 
    public class СиамскаяКошка : ICSSoft.STORMNET.DataObject, IКошка
    {
		//... 
	}
}
``` 

3.Implement the methods of the interface `IКошка`. 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}