---
title: Creating a computable field
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data types, view
summary: Example of creating a computable field using the DataServiceExpression
toc: true
permalink: en/fo_creating-computable-field.html
lang: en
---

Как создавать вычислимые свойства объектов данных описано в статье [Нехранимые (вычислимые) свойства объекта данных](fo_not-stored-attributes.html).

Процесс создания вычислимого свойства с помощью `DataServiceExpression` хорошо иллюстрирует следущая задача: Есть система учёта покупателей, где хранится информация об их покупках. У покупок может быть два статуса: "Передано в банк" и "Оплачено". Необходимо определить у покупателя вычислимое поле "Сумма оплаченных покупок".

## Работа во Flexberry Designer

В первую очередь следует создать во Flexberry Designer [диаграмму классов](fd_class-diagram.html).

![](/images/pages/products/flexberry-orm/data-types/class-diagram-customer-purchase.jpg)

Поле "СуммаОплаченныхПокупок" класса "Покупатель" [сделать нехранимым](fo_attributes-class-data.html), после чего в [атрибут DataService Expression](fo_attributes-class-data.html) данного поля добавить строку:

`DataService : ICSSoft.STORMNET.Business.SQLDataService`;

_DataService Expression:_ 

```sql
"SELECT SUM(purchase."Сумма")
FROM "Покупатель" customer join "Покупка" purchase on customer."primaryKey" = purchase."Покупатель"
WHERE purchase."Покупатель" = StormMainObjectKey AND purchase."Статус" = 'Оплачено' "
```

Сгенерировать программный код.

## Работа с программным кодом

Был сгенерирован следующий код:

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Сумма\")"+
" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\""+
" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND  purchase.\"Статус\" = \'Оплачено\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get {	return null;	}
	set {}
}
```

В результате при просмотре списков покупателей нехранимое поле "СуммаОплаченныхПокупок" вычисляется [без создания объекта данных](fo_not-stored-attributes.html). 

{% include note.html content="После редактирования объекта `Покупатель` в списке у соответствующего покупателя в поле `СуммаОплаченныхПокупок` отобразится пустая строка. Одним из вариантов решения данной проблемы может быть организация кэширования значения, для чего нужно создать private-переменную, где будет храниться кэшированное значение." %}

```csharp
public class Покупатель : ICSSoft.STORMNET.DataObject
{
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedPurchaseSum = null; //переменная для хранения кэша поля СуммаОплаченныхПокупок
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedAvailableSum = null; //переменная для хранения кэша поля ДоступнаяСумма
	//...
}
```

Также нужно отредактировать код для `СуммаОплаченныхПокупок`.

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Сумма\")"+
" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\""+
" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND  purchase.\"Статус\" = \'Оплачено\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get
	{
		return this.cashedPurchaseSum;
	}
	set
	{
		if (value != null)
		{
			this.cashedPurchaseSum= value;
		}
	}}
```

Если расширить условие задачи, что в поле "Доступная сумма" класса "Покупатель" необходимо записать доступную сумму на счёте (то есть сумма на счёте минус сумма платежей, что имеет статус "Передано в банк"), то программный код для данного поля объекта может иметь следующий вид (почему `this.СуммаНаСчёте` в выражении записано как `@СуммаНаСчёте@`, было объяснено в статье [Нехранимые (вычислимые) свойства объекта данных](fo_not-stored-attributes.html)):

```csharp
// *** Start programmer edit section *** (Покупатель.ДоступнаяСумма CustomAttributes)
[DataServiceExpression(typeof(SQLDataService), "SELECT @СуммаНаСчёте@ - SUM(purchase.\"Сумма\") "+
	" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\" "+
	" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND  purchase.\"Статус\" = \'Передано в банк\' ")]
// *** End programmer edit section *** (Покупатель.ДоступнаяСумма CustomAttributes)
[ICSSoft.STORMNET.NotStored()]
public virtual decimal ДоступнаяСумма
{
	get
	{
		return this.cashedAvailableSum;
	}
	set
	{
		if (value != null)
		{
			this.cashedAvailableSum = value;
		}
	}
}
```

{% include warning.html content="В приведённом примере опущено, что если значение детейлов изменится, то кэшированное значение необходимо пересчитать (например, с помощью [SQL-запроса](fo_sql-query.html))" %}
