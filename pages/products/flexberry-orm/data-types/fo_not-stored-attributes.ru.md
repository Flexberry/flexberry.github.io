---
title: Нехранимые (вычислимые) свойства объекта данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, DataServiceExpression, DSE, типы данных, объекты данных
toc: true
permalink: ru/fo_not-stored-attributes.html
lang: ru
---

## Введение

**Вычислимые свойства** - это специальные атрибуты объекта данных, значения которых вычисляются динамически на основе других свойств. Вычислимые свойства объекта не сохраняются в базе данных и их значение вычисляется через getter свойства или СУБД (если указан `DataServiceExpression` - специфическое SQL-выражение).

## Создание вычислимого свойства

Вычислимые свойства могут быть сгенерированы через Flexberry Designer. Для создания вычислимого свойства необходимо:

1. Сделать свойство объекта нехранимым на уровне UML-модели.

2. Указать `DataServiceExpression` для свойства

![Пример DSE в Flexberry Enterprise](/images/pages/products/flexberry-orm/data-types/flexberry-enterprise-data-service-expression.png)

Для этого, необходимо нажать на карандаш, и в выпадающем окошке заполнить необходимое количество DataServiceExpressions:

![Выпадающее окошко DSE](/images/pages/products/flexberry-orm/data-types/flexberry-enterprise-data-service-expression-modal.png)

_О том, как заполнять DSE, см. в разделе [Правила заполнения вычислимого свойства](#правила-заполнения-вычислимого-свойства)._

Каждый `DataServiceExpression` определяет выражение для определённого сервиса данных (напр. `ICSSoft.STORMNET.Business.PostgresDataService`). Допускается использование `ICSSoft.STORMNET.Business.SQLDataService` (выражение будет работать для всех SQL-подобных сервисов данных).

В результате, после генерации вычислимое свойство будет:

1. Помечено [атрибутом `[NotStored]`](fo_attributes-class-data.html)
2. Иметь логику вычисления в атрибутах `[DataServiceExpression]` (`SQL`) - каждый `DSE` соответствует своей СУБД
3. Иметь пустой аксессор `get` объекта данных (`C#`)

Для полноценной работы свойства необходимо **вручную заполнить**:

1. Логику аксессора `get` объекта данных (`C#`) _(и для `set` - в случае необходимости)_
2. `computed` свойство (в приложениях `ember-flexberry` - см. [Вычислимые свойства и проекции моделей](gpg_computable-properties-and-projections-of-models.html))

### Пример вычислимого свойства

```csharp
[DataServiceExpression(typeof(SQLDataService),"'\"'+ @Наименование@+'\"'")]
[NotStored]
public string НаименованиеВКавычках
{
    get
    {
        return "\"" + Наименование + "\"";
    }
}
```

### Правила заполнения вычислимого свойства

1. `DataServiceExpression` (`DSE`)

- атрибуты объекта в выражении `DSE` должны быть выделены символом **@** (собака): `@Наименование@`
- указывать `DSE` необязательно, но это будет влиять на производительность (см. [раздел](#поведение-сервиса-данных-при-работе-с-вычислимым-полем)) _в некоторых случаях при отсутствии `DSE` поле может быть не вычислено_
- при определении `DataServiceExpression` необходимо указать тип сервиса данных, который поддерживает данное выражение: `DataServiceExpression(typeof(SQLDataService), ...)`

#### Для чего используется `DataServiceExpression`
> В некоторых случаях при загрузке объекта (при `LoadObjectStringedView`), [сервисы данных](fo_data-service.html) могут загружать данные в виде строк, не создавая при этом объектов данных. В таком случае для вычисления нехранимых свойств используется СУБД - применяется SQL-выражение из `DataServiceExpression`. Если `DataServiceExpression` не указан, сервису данных приходится создавать объект данных в C# (`DataObject`), чтобы получить значение вычислимых свойств через аксессоры `get`. Поэтому, для экономии ресурсов сервера, заполнение `DataServiceExpression` позволит вычислять нехранимые поля средствами СУБД (это гораздо экономнее, чем создавать `DataObject` для расчёта свойств).

{% include important.html content="Вычислимые атрибуты, на которых накладываются полномочия __обязательно__ должны иметь `DataServiceExpression`" %}

2. Аксессор `get`.

- Аксессор должен быть задан у каждого вычислимого свойства.

```csharp
public string ФИО
{
    get => $"{Фамилия} {Имя} {Отчество}";
}
```

> Если для загрузки объекта используется только метод `LoadObjectStringedView` и у свойства задан `DataServiceExpression`, аксессор задавать необязательно.

3. `computed` свойство (для приложений `ember-flexberry`). Данное свойство задавать необязательно, но желательно - т.к. это позволит обновлять поле сразу же, не дожидаясь загрузки обновлённого значения с сервера - см. [подробнее](gpg_computable-properties-and-projections-of-models.html)

### Загрузка вычислимых полей

В [представлении](fd_view-definition.html), по которому происходит загрузка, должны присутствовать все свойства, от которых зависит вычислимый атрибут. Несоблюдение этого **приведёт к неверному расчёту значения свойства.**

## Примеры

- [Пример 1. Вычислимое свойство по детейлам](#пример-1-вычислимое-свойство-по-детейлам)
- [Пример 2. Первичные ключи в DataServiceExpression](#пример-2-первичные-ключи-в-dataserviceexpression)
- [Пример 3. Применение вычислимых полей в вычислимых полях](#пример-3-применение-вычислимых-полей-в-вычислимых-полях)

### Пример 1. Вычислимое свойство по детейлам

Будет разобран процесс создания вычислимого свойства по значениям детейлов на примере системы учета покупателей. В этой системе фиксируются покупки клиентов, каждая из которых может иметь статус `Передано в банк` или `Оплачено`. Необходимо добавить к покупателю поле `Сумма оплаченных покупок`, которое будет вычисляться автоматически.

#### Шаг 1: Моделирование в Flexberry Designer

В первую очередь, следует создать через Flexberry Designer [диаграмму классов](fd_class-diagram.html).

![Пример диаграммы классов](/images/pages/products/flexberry-orm/data-types/class-diagram-customer-purchase.jpg)

Поле `СуммаОплаченныхПокупок` класса `Покупатель` должно быть [нехранимым](fo_attributes-class-data.html). Затем в атрибуте
[DataServiceExpression](fo_attributes-class-data.html) этого поля необходимо добавить SQL-выражение через Flexberry Designer.

```sql
"SELECT SUM(purchase."Сумма")
FROM "Покупатель" customer join "Покупка" purchase on customer."primaryKey" = purchase."Покупатель"
WHERE purchase."Покупатель" = StormMainObjectKey AND purchase."Статус" = 'Оплачено' "
```

> В поле `DataService` во Flexberry Designer необходимо указать `ICSSoft.STORMNET.Business.SQLDataService`, т.к. в выражении нет специфичных для Postgres или MSSQL функций.

> Стоит отметить, что в ограничениях по значению перечисления, необходимо указывать заголовок значения. Например, в C# имеется значение `"ОплаченоПокупателем"`, и заголовок `"Оплачено покупателем"`. В ограничении необходимо использовать именно **заголовок** `"Оплачено покупателем"`.

#### Шаг 2: Генерация программного кода

В результате генерации будет создан следующий код:

```csharp
[ICSSoft.STORMNET.NotStored()]
[DataServiceExpression(typeof(ICSSoft.STORMNET.Business.SQLDataService), "SELECT SUM(Purchase.\"Сумма\")"+
" FROM \"Покупатель\" customer join \"Покупка\" Purchase on customer.\"primaryKey\" = Purchase.\"Покупатель\""+
" WHERE Purchase.\"Покупатель\" = StormMainObjectKey AND Purchase.\"Статус\" = \'Оплачено\' ")]
public virtual decimal СуммаОплаченныхПокупок
{
	  get {	return null;	}
	  set {}
}
```

В результате, поле `СуммаОплаченныхПокупок` будет вычисляться без необходимости [создавать объект данных](#для-чего-используется-dataserviceexpression) для каждого покупателя.

#### Шаг 3: Оптимизация работы с кэшированным значением
Для улучшения производительности и сохранения данных при редактировании объекта `Покупатель` рекомендуется реализовать кэширование значения вычислимого поля. Для этого в классе `Покупатель` следует создать приватные переменные для хранения кэшированных сумм.

```csharp
public class Покупатель : ICSSoft.STORMNET.DataObject
{
	  private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedPurchaseSum = null; // переменная кэша для поля СуммаОплаченныхПокупок
	  private ICSSoft.STORMNET.UserDataTypes.NullableDecimal cashedAvailableSum = null; //переменная кэша для поля ДоступнаяСумма
	  // ...
}
```

Затем необходимо скорректировать аксессоры свойства `СуммаОплаченныхПокупок` для использования кэшированного значения.

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
    }
}
```

#### Шаг 4: Дополнительные вычисляемые свойства

Пусть условие задачи будет расширено. Необходимо добавить поле `Доступная сумма`, которое показывает доступные средства на счету клиента после оплаты.

Код свойства будет иметь следующий вид:

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

{% include warning.html content="После изменения детейлов необходимо пересчитывать кэшированные значения для сохранения актуальности данных. Это позволяет сделать [SQL-запрос](fo_sql-query.html), обновляющий кэш при изменениях." %}

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

### Пример 3. Использование вычислимых полей в вычислимых полях

В связи в архитектурными особенностями [генератора sql-запросов](fo_sql-query.html) во Flexberry ORM, использование вычислимых полей внутри других вычислимых полей __не поддерживается__. При необходимости необходимо скопировать логику вычисления нужного поля:

Например, есть класс `Дом` с [мастером](fd_master-association.html) `Улица`.

У класса `Улица` есть вычислимое поле `NotStoredName`, вычисляемое следующим образом:

```sql
'Улица ' + @Название@ + ' имеет первичный ключ ' +CAST(STORMMainObjectKey as varchar(max))
```

Пусть есть потребность использовать данное поле при вычислении значения вычислимого поля класса `Дом`:

```sql
'Корпус ' + @Корпус@ + ';' + @Улица.NotStoredName@
```

В описанном выше примере значение не будет подсчитано. Для реализации требуемой логики, рекомендуется расписать выражение, вычисляющее значение поля, следующим образом:

```sql
'Корпус ' + @Корпус@ + ';' + 'Улица ' + @Улица.Название@ + ' имеет первичный ключ ' +CAST(@Улица@ as varchar(max))
```
