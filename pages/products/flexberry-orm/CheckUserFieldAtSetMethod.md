---
title: Проверка поля объекта в методе set
sidebar: product--sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/check-user-field-at-set-method.html
folder: product--folder
lang: ru
---
Проверка данных на форме может осуществляться за счёт генерации исключения при неправильном вводе в методе `set` соответствующего поля объекта.

```cs
public class Кредит : ICSSoft.STORMNET.DataObject
{
	//...
	public virtual double СуммаКредита
	{
		get
		{
			//...
		}
		set
		{
			if (value <= 0)
			{
				Exception ex = new Exception("Значение суммы кредита должно быть положительным!");
				throw ex; 
			}
			this.fСуммаКредита = value;
		}
	}
}
```
Другие методы проверки данных на форме описаны [здесь](edit-form-validation.html). 

Сравнение этого метода с некоторыми другими можно найти [здесь](check-form-field-during-edit.html).
----