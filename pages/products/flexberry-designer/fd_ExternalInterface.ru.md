---
title: Классы со стереотипом ExternalInterface
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, Public
toc: true
permalink: ru/fd_external-interface.html
folder: products/flexberry-designer/
lang: ru
---
# Классы со стереотипом ExternalInterface
Классы со [стереотипом](key-concepts-flexberry-designer.html) ExternalInterface - это всего лишь указание на то, что нужно будет сгенерировать [наследование](inheritance.html) от интерфейса для того класса, который будет наследоваться от этого интерфейса.

# Пример использования класса со стереотипом ExternalInterface
Пусть в проекте `CatInterface` объявлен интерфейс IКошка

![Изображение](/images/img/page/ExternalInterface/InterfaceICat.png)

А в другом проекте `TestExtInterface` структура вида

![Изображение](/images/img/page/ExternalInterface/ExtInterfaceICat.png)

Если в проекте, содержащем ExternalInterface, сгенерировать объекты, то в классе `СиамскаяКошка` будет строка вида
```cs
public class СиамскаяКошка : ICSSoft.STORMNET.DataObject, IКошка
```
Однако проект компилироваться не будет, поскольку не указано, где находится интерфейс `IКошка`. Чтобы это исправить, необходимо: 

1. Добавить ссылку в проекте на сборку `CatInterface(Objects).dll`. 

2. В код класса объектов добавить директиву:
```cs
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
3. Реализовать методы интерфейса `IКошка`.

----