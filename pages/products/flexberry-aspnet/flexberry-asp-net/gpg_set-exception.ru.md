---
title: Обработка исключений
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_set-exception.html
lang: ru
---

Проверка количества товаров, указанного менеджером в заказе.

Цель: Необходимо предусмотреть возможные ошибки при вводе данных, чтобы пользователь не имел возможности ввести совершенно неподходящее значение. Например, пользователь при указании количества товара в заказе не должен иметь возможности вводить отрицательное значение. 

Для этого нужно в методе `set` генерировать исключение при неправильном вводе: 

```csharp
set
{
	// *** Start programmer edit section *** (СтрокаЗаказа.Количество Set start)
	if (value < 0)
	{
		Exception ex = new Exception("Значение количества не может быть отрицательным");
		throw ex;
	}
	// *** End programmer edit section *** (СтрокаЗаказа.Количество Set start)
	this.fКоличество = value;
	// *** Start programmer edit section *** (СтрокаЗаказа.Количество Set end)

	// *** End programmer edit section *** (СтрокаЗаказа.Количество Set end)
}
```

Когда данное исключение будет сгенерировано, на форме будет выдано сообщение об ошибочном вводе.

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Наложение ограничения на LookUp в AGE, связывание лукапов.](gpg_limit-function-for-lookup-in-age.html)
* [Автоматическое получение данных из LookUp](gpg_auto-get-data-from-lookup.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
