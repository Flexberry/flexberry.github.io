---
title: Notstored (computable) properties of the data object
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, DataServiceExpression, DSE, data types, DataObject
summary: Features of using computable properties in Flexberry ORM
toc: true
permalink: en/fo_not-stored-attributes.html
lang: en
---

## Нехранимое свойство

Если свойство нехранимое (помечено [атрибутом `NotStored`](fo_attributes-class-data.html)), оно _не_ обрабатывается [сервисами данных](fo_data-service.html).
Внутри аксессора `get` указывается какой-либо код (выражение), вычисляющий значение свойства.

### Нехранимое свойство и представления

Разработчик должен внимательно следить, чтобы в [представление](fd_view-definition.html), с которым происходит загрузка объектов [сервисами данных](fo_data-service.html), попадали все свойства, от которых зависит вычислимый атрибут. 

**Важно: Несоблюдение этого вызовет неверный счёт и ошибки.**

### Поведение сервиса данных при работе с вычислимым полем

Обычно [сервисы данных](fo_data-service.html) при закачке списков не используют объекты данных, когда возвращают данные в виде строк. Это делается с целью повышения производительности, поскольку экономия вычислительных ресурсов на создании объектов данных очень высока. Однако, выражение для счёта вычислимого атрибута указано непосредственно в свойстве объекта данных, поэтому, когда объект данных содержит вычислимые атрибуты, сервисы данных поступают так: создаётся объект данных, свойства означиваются согласно [представления](fd_view-definition.html), затем происходит конвертация в строку (поскольку сервис данных запросит значение вычислимого свойства, выполнится его счёт). Чтобы избежать счёта через объект данных и, соответственно, ускорить работу сервисов данных при закачке списков, применяется атрибут `DataServiceExpression`, которым для свойства назначается соответствие сервиса данных выражению, «понятному» этому сервису данных. Таким образом, если атрибут `DataServiceExpression` указан, то объект данных не создаётся, а счёт вычислимого атрибута «перекладывается» на сервис данных согласно переданному выражению.

{% include warning.html content="создание выражения в DSE не отменяет, то что оно должно дублироваться в GET." %}

### Правила использования вычислимого поля

**Принято**, чтобы идентификаторы в выражении явно выделялись символом **@** (собака).

{% include important.html content="Вычислимые атрибуты, на которых накладываются полномочия __обязательно__ должны иметь `DataServiceExpression`" %}

#### Пример вычислимого свойства

```csharp
[NotStored]
public Деньги Итого 
{ 
    get 
         { 
              return ИтогоДисконт + ИтогоВекселя - СуммаНДС;
         }
}
```

#### Пример вычислимого свойства с атрибутом DataServiceExpression

```csharp
[DataServiceExpression(typeof(SQLDataService),"'\"'+ @НАИМЕНОВАНИЕ@+'\"'")]
[NotStored] 
public string НАИМЕНОВАНИЕВКАВЫЧКАХ 
{
    get
       {
           return "\""+ НАИМЕНОВАНИЕ+"\"";
       }
}
```

#### Пример использования детейлов

Пример использования детейлов при определении значения вычислимого поля доступен в статье [Пример создания вычислимого поля с помощью DataServiceExpression](fo_not-stored-attributes.html).

## Первичные ключи в вычислимых полях

Использовать [первичные ключи](fo_primary-keys-objects.html) в вычислимых полях можно посредством указания [STORMMainObjectKey](fo_sql-query.html) без символа "@".

Например, на списковой форме сообщение вида "Улица <Имя_Улицы> имеет первичный ключ <Первичный_Ключ_Улицы>".

``` csharp
[ICSSoft.STORMNET.NotStored())
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "\'Улица \' + @Название@ + \' имеет первичный ключ \' +CAST(STORMMainObjectKey as varchar(max))"))
public virtual string NotStoredName
```

При использовании данного свойства как мастерового в sql-запросе `STORMMainObjectKey` будет корректно заменён на `STORMJoinedMasterKey`.

```sql
('Улица ' + "IIS.TestStandWinforms.Дом"."Улица.Название" + ' имеет первичный ключ ' +CAST("STORMJoinedMasterKey0" as varchar(max))) as "Улица.NotStoredName"
```

## Применение вычислимых полей в вычислимых полях

В связи в архитектурными особенностями [генератора sql-запросов](fo_sql-query.html) во Flexberry ORM, использование вычислимых полей в вычислимых полях на настоящий момент __не поддерживается__.

Например, есть класс `Дом` с [мастером](fd_master-association.html) `Улица`.

У класса `Улица` есть вычислимое поле `NotStoredName`, вычисляемое следующим образом:

```sql
'Улица ' + @Название@ + ' имеет первичный ключ ' +CAST(STORMMainObjectKey as varchar(max))
```

Пусть есть потребность использовать данное поле при вычислении значения вычислимого поля класса `Дом`:

```sql
'Корпус ' + @Корпус@ + ';' + @Улица.NotStoredName@
```

Однако в таком варианте значение не будет подсчитано. Чтобы реализовать требуемую логику, можно расписать выражение, вычисляющее значение поля, следующим образом:

```sql
'Корпус ' + @Корпус@ + ';' + 'Улица ' + @Улица.Название@ + ' имеет первичный ключ ' +CAST(@Улица@ as varchar(max))
```

## Пример создания нехранимого свойства данных

Процесс создания вычислимого свойства с помощью `DataServiceExpression` хорошо иллюстрирует следущая задача: Есть система учёта покупателей, где хранится информация об их покупках. У покупок может быть два статуса: "Передано в банк" и "Оплачено". Необходимо определить у покупателя вычислимое поле "Сумма оплаченных покупок".

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

### Работа с программным кодом

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
