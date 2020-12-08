---
title: Клиентский язык запросов
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, запросы, предикат
toc: true
permalink: ru/efd2_query-language.html
lang: ru
summary: Описание клиентского языка запросов.
---

## Пространство имен Query

В аддоне `ember-flexberry-data` экспортируется пространство имен Query, в котором содержатся классы для работы с языком запросов.
Для импорта пространства имен Query необходимо использовать следующий код:

```javascript
import { Query } from 'ember-flexberry-data';
```

В пространстве имен Query содержится следующий перечень классов:

* `BaseAdapter` - базовый класс для адаптеров языка запросов, которые преобразуют объект запроса в запрос для конкретного типа бэкэнда.
* `BaseBuilder` - базовый класс для построителя запросов.
* `Builder` - класс для построения запросов (построитель запросов).
* `Condition` - перечисление с логическими операциями для Query.ComplexPredicate.
* `FilterOperator` - перечисление с операциями для Query.SimplePredicate.
* `IndexedDbAdapter` - адаптер языка запросов для построения запросов к IndexedDB из объекта запроса.
* `JsAdapter` - адаптер языка запросов для построения функций фильтраций JavaScript-массивов из объекта запроса.
* `OdataAdapter` - адаптер языка запросов для построения запросов согласно спецификации [OData](http://www.odata.org/) из объекта запроса.
* `OrderByClause` - класс для формирования order-by части запроса.
* `BasePredicate` - базовый класс для классов предикатов.
* `SimplePredicate` - класс для создания фильтра в запросе по значению атрибута и указанной операции.
* `ComplexPredicate` - класс для создания фильтра в запросе из нескольких предикатов, объединенных логическими операциями.
* `StringPredicate` - класс для создания фильтра в запросе по строковым полям.
* `DetailPredicate` - класс для создания фильтра в запросе по детейловым объектам.
* `DatePredicate` - класс для создания фильтра в запросе по полям с типом дата.
* `NotPredicate` - класс для инверсии вложенного предиката.
* `TruePredicate` - класс для создания фильтра с заведомо выполняемым условием.
* `FalsePredicate` - класс для создания фильтра с заведомо невыполняемым условием.
* `IsOfPredicate` - класс для создания фильтра в запросе по типу объекта данных.
* `InPredicate` - класс для создания фильтра в запросе по значению атрибута, входящего в заданный список значени.
* `createPredicate` - метод для создания предиката по заданным параметрам.

Пример использования классов из пространства имен:

```javascript
let builder = new Query.Builder(store);
```

Для того, чтобы не писать везде в коде явно пространство имен, можно использовать следующую конструкцию языка JavaScript:

```javascript
import { Query } from 'ember-flexberry-data';
const { Builder, FilterOperator } = Query;

let builder = new Builder(store, 'customer').where('firstName', FilterOperator.Eq, 'Vasya');
```

## Query.Builder

`Query.Builder` - класс для построения запроса.

### Конструктор

Конструктор `Query.Builder` может принимать 2 параметра: обязательный `store`, и `modelName` - наименование сущности запроса (наименование модели для вычитки).

```javascript
let builder = new Query.Builder(store);

let builder = new Query.Builder(store, 'customer');
```

### Вычитывание объекта по ключу

Метод `byId` принимает параметр строку или число в качестве ключа объекта (GUID необходимо передавать в нижнем регистре).

```javascript
let builder = new Query.Builder(this.store)
    .from(modelName)
    .selectByProjection(projectionName)
    .byId('3087fbdc-273e-4bae-b440-071fd1eab1e0');
```

### Наименование сущности

```javascript
builder.from('employee');
```

### Ограничение

Предикат - функция, возвращающая `true`/`false`.
Предикат может быть:

* Простым
* Сложным
* Строковым
* Детейловым

```javascript
builder.where(Query.SomePredicate);
```

или

```javascript
let builder = new Query.Builder(store, 'customer').where('firstName', Query.FilterOperator.Eq, 'Vasya');
```

_Для мастера_

```javascript
let builder = new Query.Builder(store, 'customer').where('manager', Query.FilterOperator.Eq, '3bcc4730-9cc1-4237-a843-c4b1de881d7c');
```

_Для поля мастера_

```javascript
let builder = new Query.Builder(store, 'customer').where('manager.firstName', Query.FilterOperator.Eq, 'Vasya');
```

{% include note.html content="Пожалуйста, не используйте в значениях ограничений функцию `cast` и другие OData-специфичные функции!<br/><br/>
Поступая так, вы привязываетесь в запросах к бэкэнду конкретного типа.<br/>
Язык же запросов создан для того, чтобы абстрагироваться от какого-либо типа бэкэнда.<br/>
При несоблюдении этого правила ожидайте проблемы при работе в оффлайне или смене типа бэкэнда.<br/>" %}

### Сортировка

```javascript
builder.orderBy('age desc, price asc');
```

_По полю мастера:_

```javascript
builder.orderBy('creator.age desc, price asc');
```

### Возвращение первых N записей

```javascript
builder.top(N);
```

### Пропуск N записей

```javascript
builder.skip(N);
```

## Подсчет количества результатов

```javascript
builder.count();
```

## Задание атрибутов для запроса

__Проекция и задание атрибутов вычитки (select) - взаимоисключающие вещи, необходимо использовать одно из этого!__

```javascript
builder.select('id,age,name');
```

## Задание проекции для запроса

__Проекция и задание атрибутов вычитки(select) - взаимоисключающие вещи, необходимо использовать одно из этого!__

```javascript
builder.selectByProjection('EmployeeTestProjection');
```

## Создание экземпляра запроса на основе заданных данных

```javascript
builder.build();
```

## Передача экземпляра в query

```javascript
store.query(modelName, builder.build());
```

## Предикаты

### Query.SimplePredicate

`Query.SimplePredicate` - класс для простых предикатов для фильтрации поля по значению и указанному оператору.

#### Конструктор

Конструктор `Query.SimplePredicate` принимает 3 параметра: `attributePath` - путь атрибута, `operator` - оператор, `value` - значение.

```javascript
let predicate = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
```

### Получение свойств предиката

Получение пути атрибута:

```javascript
predicate.attributePath
```

Получение оператора:

```javascript
predicate.operator
```

Получение значения фильтрации:

```javascript
predicate.value
```

### Query.ComplexPredicate

`Query.ComplexPredicate` - класс для сложных предикатов, объединяющий несколько предикатов логическими условиями.

#### Конструктор

Конструктор `Query.ComplexPredicate` принимает 2 параметра: `condition` - логическое условия для предикатов, `...predicates` - список предикатов для объединения.

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
let sp2 = new Query.SimplePredicate('surname', Query.FilterOperator.Eq, 'Ivanov');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
```

Другой вариант:

```javascript
let p1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
let p2 = new Query.SimplePredicate('surname', Query.FilterOperator.Eq, 'Ivanov');
let result = p1.or(p2);
```

#### Получение свойств предиката

Получение логического условия:

```javascript
complexPredicate.condition
```

Получение списка предикатов:

```javascript
complexPredicate.predicates
```

#### Добавление к существующему сложному предикату новых.

Возьмем cp1 из примера для конструктора.
Добавление через and:

```javascript
let sp3 = new Query.SimplePredicate('nationality', Query.FilterOperator.Eq, 'Russian');
let result = cp1.and(sp3);
```

Добавление через or:

```javascript
let sp3 = new Query.SimplePredicate('nationality', Query.FilterOperator.Eq, 'Russian');
let result = cp1.or(sp3);
```

### Query.StringPredicate

`Query.StringPredicate` - класс для построения фильтров по строковым полям.

#### Конструктор

Конструктор `Query.StringPredicate` принимает единственный параметр: `attributePath` - путь атрибута предиката.

#### Поиск по подстроке

Добавление значения, которое заданный в конструкторе атрибут должен содержать:

```javascript
let sp1 = new Query.StringPredicate('country').contains('i');
```

Добавление значения, которое атрибут мастера должен содержать:

```javascript
let sp1 = new Query.StringPredicate('country.name').contains('i');
```

#### Получение свойств предиката

Получение пути атрибута:

```javascript
predicate.attributePath
```

Получение значения, которое должно содержаться в атрибуте:

```javascript
predicate.containsValue
```

### Query.DetailPredicate

`Query.DetailPredicate` - класс для построения фильтров по детейлам.

#### Конструктор

Конструктор `Query.DetailPredicate` принимает единственный параметр - наименование детейла.

```javascript
let dp = new Query.DetailPredicate('detailName')
```

#### Добавление простого предиката для всех детейлов

```javascript
dp.all(new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1'));
```

или

```javascript
let p = new Query.DetailPredicate('detailName').all('field', Query.FilterOperator.Eq, 'Value');
```

_Для поля мастера_

```javascript
dp.all(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### Добавление простого предиката для любого детейла

```javascript
dp.any(new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1'));
```

или

```javascript
let p = new Query.DetailPredicate('detailName').any('field', Query.FilterOperator.Eq, 'Value');
```

_Для поля мастера_

```javascript
dp.any(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### Добавление сложного предиката для всех детейлов

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
let sp2 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag3');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').all(cp1);
```

_Для поля мастера_

```javascript
let sp1 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X');
let sp2 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'Z');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').all(cp1);
```

#### Добавление сложного предиката для любого детейла

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
let sp2 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag2');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').any(cp1);
```

_Для поля мастера_

```javascript
let sp1 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X');
let sp2 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'Y');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').any(cp1);
```

### Query.DatePredicate

`Query.DatePredicate` - класс для предикатов для фильтрации поля с типом дата по значению и указанному оператору.

#### Конструктор

Конструктор `Query.DatePredicate` принимает 4 параметра: `attributePath` - путь атрибута, `operator` - оператор, `value` - значение и `timeless` - флаг указывающий нужно ли учитывать время при сравнении дат (если true, то время не учитывается).

```javascript
let predicate = new Query.DatePredicate('birthday', Query.FilterOperator.Eq, '2018-02-06T11:00:00.000Z');
let predicate = new Query.DatePredicate('birthday', Query.FilterOperator.Eq, '2018-02-06', true);
```

#### Получение свойств предиката

Получение пути атрибута:

```javascript
predicate.attributePath
```

Получение оператора:

```javascript
predicate.operator
```

Получение значения фильтрации:

```javascript
predicate.value
```

Получение флага учета времени:

```javascript
predicate.timeless
```

### Query.NotPredicate

`Query.NotPredicate` – класс для инверсии вложенного предиката.

#### Конструктор

Конструктор `Query.NotPredicate` принимает единственный параметр – другой предикат.

```javascript
let np = new Query.NotPredicate(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### Получение свойств предиката

Получение вложенного предиката:

```javascript
let innerPredicate = np.predicate;
```

### Query.IsOfPredicate

#### Конструктор

Конструктор `Query.IsOfPredicate` с двумя параметрами принимает строку с выражением, указывающим на свойство модели, и строку с именем модели.

```javascript
let predicate = new Query.IsOFPredicate('manager', 'employee');
```

Конструктор `Query.IsOfPredicate` с одним параметром принимает строку с именем модели.

```javascript
let predicate = new Query.IsOFPredicate('customer');
```

Есть метод упрощающий работу с этим предикатом.

```javascript
let predicate = new Query.IsOFPredicate('man');
let builder = new Query.Builder(store, 'creator').where(predicate);
```

Равнозначное выражение без использования предиката.

```javascript
let builder = new Query.Builder(store, 'creator').isOf('man');
```

### Query.InPredicate

`Query.InPredicate` - класс для создания фильтра в запросе по значению атрибута, входящего в заданный список значени.

#### Конструктор

Конструктор `Query.InPredicate` принимает 2 параметра: `attributePath` - путь атрибута, `valueArray` - массив значений, для проверки вхождения в него значения атрибута.

```javascript
let predicate = new Query.InPredicate('name', ['Vasya', 'Petia', 'Ivan']);
```

#### Получение свойств предиката

Получение пути атрибута:

```javascript
predicate.attributePath
```

Получение массива значений:

```javascript
predicate.valueArray
```

## Логические операторы для сложных предикатов

### Or

`Query.Condition.Or`

```javascript
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
```

### And

`Query.Condition.And`

```javascript
let cp1 = new Query.ComplexPredicate(Query.Condition.And, sp1, sp2);
```

## Операторы для фильтрации данных для простых предикатов

### Равенство

`Query.FilterOperator.Eq`

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
```

### Неравенство

`Query.FilterOperator.Neq`

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Neq, 'Tag1');
```

### Больше

`Query.FilterOperator.Ge`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Ge, 10);
```

### Больше или равно

`Query.FilterOperator.Geq`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Geq, 11);
```

### Меньше

`Query.FilterOperator.Le`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Le, 12);
```

### Меньше или равно

`Query.FilterOperator.Leq`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Leq, 11);
```

### Создание запроса для фильтрации перечислений

По названию и типу перечисления берется `name` и пишется в предикат:

```javascript
let enumValues = Ember.getOwner(this).lookup('enum:' + filter.type);
let pattern = '';

for (let key in enumValues) {
    if (enumValues[key] === filter.pattern) {
        pattern = key;
        break;
    }
}

switch (filter.condition) {
    case 'Равно':
    return new SimplePredicate(filter.name, FilterOperator.Eq, pattern);
    case 'Не равно':
    return new SimplePredicate(filter.name, FilterOperator.Neq, pattern);
    // по умолчанию Равно
    default:
    return new SimplePredicate(filter.name, FilterOperator.Eq, pattern);
}
```

## Пример комплексного запроса

```javascript
let builder = new Query.QueryBuilder(store)
      .from('customer')
      .select('id,firstName,lastName,age')
      .where('firstName', Query.FilterOperator.Eq, 'Vasya')
      .top(50)
      .skip(100)
      .count();
```
