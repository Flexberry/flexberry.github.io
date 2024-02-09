---
title: Нехранимые (вычислимые) свойства объекта данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, DataServiceExpression, DSE, типы данных, объекты данных
toc: true
permalink: ru/fo_not-stored-attributes.html
lang: ru
---

## Введение

**Вычислимые свойства** - это специальные атрибуты объекта данных, значения которых вычисляются динамически на основе других свойств. Вычислимые свойства объекта не сохраняются в базе данных и их значение вычисляется сервером каждый раз, в момент загрузки объекта. Для работы вычислимых свойств требуется указывать `DataServiceExpression` - специфическое SQL-выражение, которое используется для улучшения производительности загрузки объекта.

## Настройка вычислимого свойства

Для корректной работы вычислимого свойства, требуется соблюсти ряд условий.

Вычислимое свойство должно быть:
1. Помечено [атрибутом `NotStored`](fo_attributes-class-data.html)
2. Иметь логику вычисления:
   1. В аксессоре `get` объекта данных (`C#`)
   2. В атрибуте `DataServiceExpression` (`SQL`)
   3. В `computed` свойстве (в приложениях `ember-flexberry` - см. [статью](gpg_computable-properties-and-projections-of-models.html))

### Пример вычислимого свойства

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

### Правила заполнения вычислимого свойства

1. `DataServiceExpression` (`DSE`)
- **принято**, чтобы собственные идентификаторы в выражении `DSE` явно выделялись символом **@** (собака): `@Наименование@`
- указывать `DSE` необязательно, но это будет влиять на производительность (см. [раздел](#поведение-сервиса-данных-при-работе-с-вычислимым-полем))
- при определении `DataServiceExpression` можно указать тип сервиса данных, который поддерживает данное выражение: `DataServiceExpression(typeof(SQLDataService), ...)`
> ### Для чего используется `DataServiceExpression`
> В обычных условиях, при загрузке объекта [сервисы данных](fo_data-service.html) возвращают данные в виде строк, не создавая при этом объектов данных. SQL-выражение из `DataServiceExpression` используется для вычисления нехранимых свойств на уровне СУБД. Если `DataServiceExpression` не указан, сервису данных приходится создавать объект данных в C# (`DataObject`), чтобы получить значение вычислимых свойств через аксессоры `get`. Поэтому, для экономии ресурсов сервера, рекомендуется всегда указывать `DataServiceExpression` - это позволит вычислять нехранимые поля средствами СУБД (это гораздо экономнее, чем создавать `DataObject` для расчёта свойств).
{% include important.html content="Вычислимые атрибуты, на которых накладываются полномочия __обязательно__ должны иметь `DataServiceExpression`" %}
2. Аксессор `get`.
- Аксессор должен быть обязательно задан у каждого вычислимого свойства.
```csharp
public string НАИМЕНОВАНИЕВКАВЫЧКАХ
{
    get => "\"" + НАИМЕНОВАНИЕ + "\"";
}
```
3. `computed` свойство (для приложений `ember-flexberry`)
- Данное свойство задавать необязательно, но желательно - т.к. это позволит обновлять поле сразу же, не дожидаясь загрузки обновлённого значения с сервера - см. [подробнее](gpg_computable-properties-and-projections-of-models.html)

### Загрузка вычислимых полей

В [представлении](fd_view-definition.html), по которому происходит загрузка, должны присутствовать все свойства, от которых зависит вычислимый атрибут. Несоблюдение этого **приведёт к неверному расчёту значения свойства.**

## Примеры

- [Пример 1. Вычислимое свойство по детейлам](#пример-1-вычислимое-свойство-по-детейлам)
- [Пример 2. Первичные ключи в DataServiceExpression](#пример-2-первичные-ключи-в-dataserviceexpression)
- [Пример 3. Применение вычислимых полей в вычислимых полях](#пример-3-применение-вычислимых-полей-в-вычислимых-полях)

### Пример 1. Вычислимое свойство по детейлам

Будет разобран процесс создания вычислимого свойства по значениям детейлов на примере системы учета покупателей. В этой системе фиксируются покупки клиентов, каждая из которых может иметь статус `Передано в банк` или `Оплачено`. Необходимо добавить к покупателю поле `Сумма оплаченных покупок`, которое будет вычисляться автоматически.

#### Шаг 1: Моделирование в Flexberry Designer

В первую очередь следует создать через Flexberry Designer [диаграмму классов](fd_class-diagram.html).

![](/images/pages/products/flexberry-orm/data-types/class-diagram-customer-purchase.jpg)

Поле `СуммаОплаченныхПокупок` класса `Покупатель` должно быть [нехранимым](fo_attributes-class-data.html). Затем, в атрибуте
[DataServiceExpression](fo_attributes-class-data.html) этого поля, необходимо добавить SQL-выражение через Flexberry Designer.
```sql
"SELECT SUM(purchase."Сумма")
FROM "Покупатель" customer join "Покупка" purchase on customer."primaryKey" = purchase."Покупатель"
WHERE purchase."Покупатель" = StormMainObjectKey AND purchase."Статус" = 'Оплачено' "
```

> В поле `DataService` во Flexberry Designer необходимо указать `ICSSoft.STORMNET.Business.SQLDataService`.

#### Шаг 2: Генерация программного кода

В результате генерации будет создан следующий код:

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Сумма\")"+
" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\""+
" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND purchase.\"Статус\" = \'Оплачено\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get {	return null;	}
	set {}
}
```
В результате, поле `СуммаОплаченныхПокупок` будет вычисляться без необходимости [создавать объект данных](#для-чего-используется-dataserviceexpression) для каждого покупателя.

#### Шаг 3: Оптимизация работы с кэшированным значением

Чтобы улучшить производительность и избежать потери данных при редактировании объекта `Покупатель`, необходимо реализовать кэширование значения вычислимого поля. Для этого в классе `Покупатель` следует создать приватные переменные для хранения кэшированных сумм.

```csharp
public class Покупатель : ICSSoft.STORMNET.DataObject
{
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedPurchaseSum = null; // переменная кэша для поля СуммаОплаченныхПокупок
	private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedAvailableSum = null; //переменная кэша для поля ДоступнаяСумма
	// ...
}
```

Далее, нужно скорректировать аксессоры свойства `СуммаОплаченныхПокупок` для использования кэшированного значения.

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(purchase.\"Сумма\")"+
" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\""+
" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND  purchase.\"Статус\" = \'Оплачено\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	get => this.cashedPurchaseSum;
	set
	{
		if (value != null)
		{
			this.cashedPurchaseSum = value;
		}
	}}
```

#### Шаг 4: Дополнительные вычисляемые свойства

Пусть условие задачи будет расширено. Необходимо добавить поле `Доступная сумма`, которое показывает доступные средства на счету клиента после оплаты.

Код свойства будет иметь следующий вид.

```csharp
// *** Start programmer edit section *** (Покупатель.ДоступнаяСумма CustomAttributes)
[DataServiceExpression(typeof(SQLDataService), "SELECT @СуммаНаСчёте@ - SUM(purchase.\"Сумма\") "+
	" FROM \"Покупатель\" customer join \"Покупка\" purchase on customer.\"primaryKey\" = purchase.\"Покупатель\" "+
	" WHERE purchase.\"Покупатель\" = StormMainObjectKey AND  purchase.\"Статус\" = \'Передано в банк\' ")]
// *** End programmer edit section *** (Покупатель.ДоступнаяСумма CustomAttributes)
[ICSSoft.STORMNET.NotStored()]
public virtual decimal ДоступнаяСумма
{
	get => this.cashedAvailableSum;
	set
	{
		if (value != null)
		{
			this.cashedAvailableSum = value;
		}
	}
}
```

{% include warning.html content="Важно помнить, что при изменении статусов покупок или сумм на счетах (т.е. при изменении детейлов), необходимо пересчитывать кэшированные значения, чтобы они всегда отражали актуальное положение дел. Это можно сделать, например, с помощью [SQL-запроса](fo_sql-query.html), который обновит кэш при изменении данных." %}

### Пример 2. Первичные ключи в DataServiceExpression

Использовать [первичные ключи](fo_primary-keys-objects.html) в вычислимых полях можно посредством указания [STORMMainObjectKey](fo_sql-query.html) без символа "@".

Например, выражение `DSE` для сообщения вида "Улица <Название> имеет первичный ключ <Первичный_Ключ_Улицы>" будет выглядеть следующим образом.
``` csharp
[DataServiceExpression(typeof(SQLDataService), "\'Улица \' + @Название@ + \' имеет первичный ключ \' +CAST(STORMMainObjectKey as varchar(max))")]
//...
```

При использовании данного свойства как мастерового в sql-запросе `STORMMainObjectKey` будет корректно заменён на `STORMJoinedMasterKey`:
```sql
('Улица ' + "IIS.TestStandWinforms.Дом"."Улица.Название" + ' имеет первичный ключ ' + CAST("STORMJoinedMasterKey0" as varchar(max))) as "Улица.NotStoredName"
```

### Пример 3. Применение вычислимых полей в вычислимых полях

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
