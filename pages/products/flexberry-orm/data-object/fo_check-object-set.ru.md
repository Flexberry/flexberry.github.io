---
title: Проверка поля объекта в методе set
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, объекты данных, исключения
summary: Описание использования метода set
toc: true
permalink: ru/fo_check-object-set.html
lang: ru
---

Проверка данных на форме может осуществляться за счёт генерации исключения при неправильном вводе в методе `set` соответствующего поля объекта.

```csharp
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

Другие методы проверки данных на форме описаны в статье [Валидация данных](fw_edit-form-validation.html).
