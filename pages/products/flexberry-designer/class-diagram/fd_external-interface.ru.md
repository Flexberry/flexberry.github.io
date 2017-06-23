---
title: Классы со стереотипом ExternalInterface
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, ExternalInterface, интерфейс, наследование, пример
summary: Описание особенностей и пример генерации ExternalInterface
toc: true
permalink: ru/fd_external-interface.html
lang: ru
---

Классы со [стереотипом](fd_key-concepts.html) ExternalInterface - это всего лишь указание на то, что нужно будет сгенерировать [наследование](fd_inheritance.html) от интерфейса для того класса, который будет наследоваться от этого интерфейса.

## Пример использования класса со стереотипом ExternalInterface

Пусть в проекте `CatInterface` объявлен интерфейс IКошка

![](/images/pages/products/flexberry-designer/class-diagram/interface-i-cat.png)

А в другом проекте `TestExtInterface` структура вида

![](/images/pages/products/flexberry-designer/class-diagram/ext-interface-i-cat.png)

Если в проекте, содержащем ExternalInterface, сгенерировать объекты, то в классе `СиамскаяКошка` будет строка вида

```csharp
public class СиамскаяКошка : ICSSoft.STORMNET.DataObject, IКошка
```
Однако проект компилироваться не будет, поскольку не указано, где находится интерфейс `IКошка`. Чтобы это исправить, необходимо: 

1.Добавить ссылку в проекте на сборку `CatInterface(Objects).dll`. 

2.В код класса объектов добавить директиву:

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

3.Реализовать методы интерфейса `IКошка`.

