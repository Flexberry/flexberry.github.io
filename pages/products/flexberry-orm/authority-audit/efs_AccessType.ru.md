---
title: AccessType (тип проверки полномочий для класса)
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Designer, Flexberry Security
toc: true
permalink: ru/efs_access-type.html
folder: products/ember-flexberry-security/backend/
lang: ru
---

## AccessType

`AccessType` определяет тип [проверки полномочий](right-manager.html) для класса. Выделяют следующие типы:

```cs
public enum ICSSoft.STORMNET.AccessType
{
	none,  			// не производится никакой проверки
	@this, 			// производится проверка только над текущим классом
	@base, 			// производится проверка для базового класса
	@this_and_base  // производится проверка как для базового класса, так и для текущего
}
```

## AccessType по умолчанию

В Flexberry Designer добавлена возможность определять для стадии значение `AccessType` по умолчанию для создаваемых классов (для этого в контекстном меню стадии необходимо выбрать пункт "ORM -> C# -> Свойства модели -> Дополнительно", где указать желаемый "Тип проверки доступа по умолчанию").

## Смотрите также

* [Подсистема-полномочии|Подсистема полномочий]
* [Сценарии использования подсистемы полномочий](rights-scenarios.html)
* [AccessType (тип проверки полномочий для класса)](access-type.html)
* [c:Полномочия|Все статьи категории "Полномочия"]
