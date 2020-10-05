---
title: Клиентский язык запросов
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, запросы, предикат
toc: true
permalink: ru/efd3_query-language.html
lang: ru
summary: Описание клиентского языка запросов.
---

## Введение в клиентский язык запросов

Аддон [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data) содержит набор классов, реализующих возможности клиентского языка запросов. Данный язык запросов позволяет абстрагироваться от специфики конкретного бэкенда и единобразно формировать запросы в Ember-приложении, независимо от того, какой именно формат передачи данных используется между Ember-приложением и бэкендом.

Преобразование запросов с использованием клиентского языка запросов в формат запросов для конкретного бэкенда осуществляется с использованием адаптеров, которые также реализуются в аддоне [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data). На данный момент реализованы следующие адаптеры:

* Адаптер для запросов к [OData](https://www.odata.org/)-бэкенду.
* Адаптер для запросов к [IndexedDB](https://developers.google.com/web/ilt/pwa/working-with-indexeddb).
* Адаптер для запросов к JavaScript-массивам.

Перечисленные виды бэкендов (источников данных) поддерживаются в приложениях, реализованных с использованием фреймворка Flexberry Ember.

Синтаксис клиентского языка запросов, который предоставляет фреймворк Flexberry Ember, основан на [Breeze query language](http://breeze.github.io/doc-js/#query) библиотеки [Breeze.js](https://www.getbreezenow.com/breezejs).

## Классы клиентского языка запросов

Аддон [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data) содержит следующие классы и функции клиентского языка запросов:

* `BaseAdapter` - базовый класс для адаптеров языка запросов, преобразующих объект запроса в запрос для конкретного типа бэкэнда.
* `BaseBuilder` - базовый класс для построителя запросов.
* `Builder` - класс для построения запросов (построитель запросов).
* `Condition` - перечисление с логическими операциями для `ComplexPredicate`.
* `FilterOperator` - перечисление с операциями для `SimplePredicate`, `StringPredicate` и `DatePredicate`.
* `IndexedDbAdapter` - адаптер языка запросов для построения запросов к [IndexedDB](https://developers.google.com/web/ilt/pwa/working-with-indexeddb).
* `JsAdapter` - адаптер языка запросов для построения функций фильтрации JavaScript-массивов.
* `OdataAdapter` - адаптер языка запросов для построения запросов к бэкенду на основе протокола [OData](http://www.odata.org/).
* `OrderByClause` - класс для формирования условий сортировки в запросе.
* `BasePredicate` - базовый класс для классов предикатов.
* `SimplePredicate` - класс для создания фильтра в запросе по значению атрибута и указанной операции.
* `ComplexPredicate` - класс для создания фильтра в запросе из нескольких предикатов, объединенных логическими операциями.
* `StringPredicate` - класс для создания фильтра в запросе по строковым полям.
* `DetailPredicate` - класс для создания фильтра в запросе по детейловым моделям.
* `DatePredicate` - класс для создания фильтра в запросе по полям с типом дата.
* `NotPredicate` - класс для инверсии значения указанного предиката.
* `IsOfPredicate` - класс для создания фильтра в запросе по типу модели.
* `GeographyPredicate` - класс для создания фильтра в запросе по географическим атрибутам.
* `GeometryPredicate` - класс для создания фильтра в запросе по геометрическим атрибутам.
* `createPredicate` - функция для создания предиката по заданным параметрам.
* `stringToPredicate` - функция для конвертации строки в предикат.

Для импорта и использования классов языка запросов необходимо использовать следующий код:

```javascript
import Builder from 'ember-flexberry-data/query/builder';

let builder = new Builder(store);
```

{% include note.html content="В версиях Flexberry Ember 2.x содержалось пространство имен Query, содержащее перечисленные выше классы. Поскольку в Ember 3.x пространства имен более не используются в связи с [использованием ES6-модулей](https://github.com/emberjs/rfcs/blob/master/text/0176-javascript-module-api.md), все классы, содержавшиеся в пространстве имен Query, во Flexberry Ember 3.x теперь необходимо импортировать напрямую из [папки query](https://github.com/Flexberry/ember-flexberry-data/tree/feature-ember-update/addon/query) аддона [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data)." %}

## Builder

`Builder` - класс для построения запроса (построитель запросов).

### Конструктор

Конструктор `Builder` может принимать 2 параметра:

* `store` (обязательный) - используемый сервис [store](https://api.emberjs.com/ember-data/3.1/classes/DS.Store) библиотеки [Ember Data](https://api.emberjs.com/ember-data/3.1) в приложении.
* `modelName` (необязательный)  - наименование модели для вычитки с помощью запроса.

```javascript
let builder = new Builder(store);

let builder = new Builder(store, 'customer');
```

### Вычитывание модели по первичному ключу

Для вычитывания модели по первичному ключу (идентификатору) используется метод `byId` построителя запросов. Метод `byId` построителя запросов принимает в качстве параметра первичный ключ (идентификатор) модели в виде строки или числа (значение типа GUID необходимо передавать в нижнем регистре):

```javascript
let builder = new Builder(this.store)
    .from(modelName)
    .selectByProjection(projectionName)
    .byId('3087fbdc-273e-4bae-b440-071fd1eab1e0');
```

### Указание сущности (имени модели)

Для указания имени модели, которая будет использоваться в запросе, используется метод `from` построителя запросов:

```javascript
builder.from('employee');
```

### Указание ограничений

Каждый предикат - это функция, возвращающая `true` или `false`.

Для указания того, какой предикат будет использоваться в запросе, используется метод `where` построителя запросов. При этом можно использовать различные варианты передачи параметров в данный метод.

1. Передача предиката любого типа в качестве параметра:

```javascript
builder.where(SomePredicate);
```

2. Передача имени атрибута, операции и значения атрибута (аналог использования `SimplePredicate`)в качестве параметров:

```javascript
let builder = new Builder(store, 'customer').where('firstName', FilterOperator.Eq, 'Vasya');
```

Аналогичным образом можно формировать ограничения по _мастеру_:

```javascript
let builder = new Builder(store, 'customer').where('manager', FilterOperator.Eq, '3bcc4730-9cc1-4237-a843-c4b1de881d7c');
```

Можно таким же образом формировать ограничения и по _полю мастера_:

```javascript
let builder = new Builder(store, 'customer').where('manager.firstName', FilterOperator.Eq, 'Vasya');
```

{% include note.html content="Пожалуйста, не используйте в значениях ограничений функцию `cast` и другие OData-специфичные функции!<br/><br/>
Поступая так, вы привязываетесь в запросах к бэкенду конкретного типа.<br/>
Язык запросов создан для того, чтобы абстрагироваться от какого-либо типа бэкенда.<br/>
При несоблюдении этого правила ожидайте проблемы при работе в офлайне или смене типа бэкенда.<br/>" %}

### Сортировка

Для указания способа сортировки, которая будет использоваться в запросе, используется метод `orderBy` построителя запросов:

Пример указания сортировки по _собственному_ полю модели:

```javascript
builder.orderBy('age desc, price asc');
```

Пример указания сортировки по _полю мастера_ у модели:

```javascript
builder.orderBy('creator.age desc, price asc');
```

### Возвращение первых N записей

Для указания того, что в ответе на запрос должно вернуться только первые N записей, используется метод `top` построителя запросов:

```javascript
builder.top(N);
```

### Пропуск N записей

Для указания того, что в ответе на запрос должно быть пропущено N первых записей, используется метод `skip` построителя запросов:

```javascript
builder.skip(N);
```

## Подсчет количества записей в ответе

Для указания того, что требуется включить количество результирующих записей в ответ, используется метод `count` построителя запросов:

```javascript
builder.count();
```

## Указание вычитываемых атрибутов

Для указания перечня вычитываемых атрибутов в явном виде, используется метод `select` построителя запросов:

```javascript
builder.select('id,age,name');
```

{% include note.html content="Если используется данный способ указания вычитываемых атрибутов в запросе, то нельзя одновременно использовать в запросе метод selectByProjection!" %}

## Указание проекции

Для указания перечня вычитываемых атрибутов в виде проекции, используется метод `selectByProjection` построителя запросов:

```javascript
builder.selectByProjection('EmployeeTestProjection');
```

{% include note.html content="Если используется данный способ указания вычитываемых атрибутов в запросе, то нельзя одновременно использовать в запросе метод select!" %}

## Создание объекта запроса

Для того, чтобы создать объект запроса с указанными ранее опциями, используется метод `build` построителя запросов:

```javascript
builder.build();
```

## Использование объекта запроса

Созданный объект запроса должен быть передан в качестве второго параметра в метод [query](https://api.emberjs.com/ember-data/3.1/classes/DS.Store/methods/query?anchor=query) сервиса [store](https://api.emberjs.com/ember-data/3.1/classes/DS.Store) библиотеки [Ember Data](https://api.emberjs.com/ember-data/3.1). 

```javascript
store.query(modelName, builder.build());
```

## Предикаты

### SimplePredicate

`SimplePredicate` - предикат для фильтрации записей по значению указанного атрибута и заданному оператору.

#### Конструктор

Конструктор класса `SimplePredicate` принимает 3 параметра: 
* `attributePath` - атрибут (собственный или у связанных моделей).
* `operator` - оператор (операция).
* `value` - значение атрибута.

```javascript
let predicate = new SimplePredicate('name', FilterOperator.Eq, 'Vasya');
```

### Свойства предиката

Получить заданный в предикате атрибут ("путь атрибута") можно с использованием свойства `attributePath` предиката:

```javascript
predicate.attributePath
```

Получить заданный в предикате оператор (операцию) можно с использованием свойства `operator` предиката:

```javascript
predicate.operator
```

Получить заданное в предикате значение для атрибута можно с использованием свойства `value` предиката:

```javascript
predicate.value
```

### ComplexPredicate

`ComplexPredicate` - предикат, объединяющий несколько предикатов с помощью логических условий.

#### Конструктор

Конструктор класса `ComplexPredicate` принимает 2 параметра: 
* `condition` - логическое условие (операция) для объединения предикатов.
* `...predicates` - список предикатов для объединения.

Пример явного создания комплексного предиката:

```javascript
let sp1 = new SimplePredicate('name', FilterOperator.Eq, 'Vasya');
let sp2 = new SimplePredicate('surname', FilterOperator.Eq, 'Ivanov');
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
```

Пример неявного создания комплексного предиката:

```javascript
let p1 = new SimplePredicate('name', FilterOperator.Eq, 'Vasya');
let p2 = new SimplePredicate('surname', FilterOperator.Eq, 'Ivanov');
let result = p1.or(p2);
```

#### Свойства предиката

Получить заданную в предикате логическую операцию для объединения предикатов можно с использованием свойства `condition` предиката:

```javascript
complexPredicate.condition
```

Получить заданные в предикате предикаты для объединения можно с использованием свойства `predicates` предиката:

```javascript
complexPredicate.predicates
```

#### Добавление новых предикатов

Для добавления новых предикатов в существующий комплексный предикат, как и для формирования нового комплексного предиката, используются методы `and` и `or` предиката.

Пример добавления предиката к существующему комплексному предикату с использованием метода `and`: (предикат `cp1` ранее был описан в подразделе про конструктор сложного предиката):

```javascript
let sp3 = new SimplePredicate('nationality', FilterOperator.Eq, 'Russian');
let result = cp1.and(sp3);
```

Пример добавления предиката к существующему комплексному предикату с использованием метода `or`: (предикат `cp1` ранее был описан в подразделе про конструктор сложного предиката):

```javascript
let sp3 = new SimplePredicate('nationality', FilterOperator.Eq, 'Russian');
let result = cp1.or(sp3);
```

### StringPredicate

`StringPredicate` - предикат для построения фильтров по строковым полям.

#### Конструктор

Конструктор класса `StringPredicate` принимает единственный параметр:

* `attributePath` - атрибут (собственный или у связанных моделей)

#### Поиск по подстроке

Указание значения, которое может содержаться в атрибуте модели, осуществляется с использованием метода `contains` предиката:

```javascript
let sp1 = new StringPredicate('country').contains('i');
```

Аналогичным образом указывается фильтрация по подстроке для _мастеровых_ атрибутов:

```javascript
let sp1 = new StringPredicate('country.name').contains('i');
```

#### Свойства предиката

Получить заданный в предикате атрибут ("путь атрибута") можно с использованием свойства `attributePath` предиката:

```javascript
predicate.attributePath
```

Получить заданное в предикате значение для поиска по подстроке можно с использованием свойства `containsValue` предиката:

```javascript
predicate.containsValue
```

### DetailPredicate

`DetailPredicate` - предикат для построения фильтров по детейлам.

#### Конструктор

Конструктор класса `DetailPredicate` принимает единственный параметр: 
* `detailPath` - имя связанной детейловой модели.

```javascript
let dp = new DetailPredicate('detailName')
```

#### Фильтрация по условию для всех детейлов

Фильтрация по условию, которое должно выполняться для всех детейлов, осуществляется с использованием метода `all` предиката.

Пример передачи `SmplePredicate` для фильтрации по всем детейлам:

```javascript
dp.all(new SimplePredicate('name', FilterOperator.Eq, 'Tag1'));
```

Пример передачи имени атрибута, операции и значения для фильтрации по всем детейлам:

```javascript
let p = new DetailPredicate('detailName').all('field', FilterOperator.Eq, 'Value');
```

Пример передачи имени _мастерового_ атрибута, операции и значения для фильтрации по всем детейлам:

```javascript
dp.all(new SimplePredicate('creator.name', FilterOperator.Eq, 'X'));
```

Пример передачи комплексного предиката для фильтрации по всем детейлам:

```javascript
let sp1 = new SimplePredicate('name', FilterOperator.Eq, 'Tag1');
let sp2 = new SimplePredicate('name', FilterOperator.Eq, 'Tag3');
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
let dp = new DetailPredicate('tags').all(cp1);
```

Пример передачи комплексного предиката, сформированного по _мастеровому_ полю у детейла, для фильтрации по всем детейлам:

```javascript
let sp1 = new SimplePredicate('creator.name', FilterOperator.Eq, 'X');
let sp2 = new SimplePredicate('creator.name', FilterOperator.Eq, 'Z');
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
let dp = new DetailPredicate('tags').all(cp1);
```

#### Фильтрация по условию для любого детейла

Фильтрация по условию, которое должно выполняться для любого из детейлов, осуществляется с использованием метода `any` предиката.

Пример передачи `SmplePredicate` для фильтрации по любому из детейлов:

```javascript
dp.any(new SimplePredicate('name', FilterOperator.Eq, 'Tag1'));
```

Пример передачи имени атрибута, операции и значения для фильтрации по любому из детейлов:

```javascript
let p = new DetailPredicate('detailName').any('field', FilterOperator.Eq, 'Value');
```

Пример передачи имени _мастерового_ атрибута, операции и значения для фильтрации по любому из детейлов:

```javascript
dp.any(new SimplePredicate('creator.name', FilterOperator.Eq, 'X'));
```

Пример передачи комплексного предиката для фильтрации по любому из детейлов:

```javascript
let sp1 = new SimplePredicate('name', FilterOperator.Eq, 'Tag1');
let sp2 = new SimplePredicate('name', FilterOperator.Eq, 'Tag2');
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
let dp = new DetailPredicate('tags').any(cp1);
```

Пример передачи комплексного предиката, сформированного по _мастеровому_ полю у детейла, для фильтрации по любому из детейлов:

```javascript
let sp1 = new SimplePredicate('creator.name', FilterOperator.Eq, 'X');
let sp2 = new SimplePredicate('creator.name', FilterOperator.Eq, 'Y');
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
let dp = new DetailPredicate('tags').any(cp1);
```

### DatePredicate

`DatePredicate` - предикат для фильтрации записей по атрибуту с типом "дата".

#### Конструктор

Конструктор класса `DatePredicate` принимает 4 параметра:
* `attributePath` - атрибут (собственный или у связанных моделей).
* `operator` - оператор (операция).
* `value` - значение атрибута.
`timeless` - флаг, указывающий нужно ли учитывать время при сравнении дат (если указано `true`, то время не учитывается).

```javascript
let predicate = new DatePredicate('birthday', FilterOperator.Eq, '2018-02-06T11:00:00.000Z');
let predicate = new DatePredicate('birthday', FilterOperator.Eq, '2018-02-06', true);
```

#### Свойства предиката

Получить заданный в предикате атрибут ("путь атрибута") можно с использованием свойства `attributePath` предиката:

```javascript
predicate.attributePath
```

Получить заданный в предикате оператор (операцию) можно с использованием свойства `operator` предиката:

```javascript
predicate.operator
```

Получить заданное в предикате значение для атрибута можно с использованием свойства `value` предиката:

```javascript
predicate.value
```

Получить заданный в предикате флаг для необходимости учета времени можно с использованием свойства `timeless` предиката:

```javascript
predicate.timeless
```

### GeographyPredicate и GeometryPredicate

* `GeographyPredicate` - предикат для проверки пересечения атрибута с типом "Geography" с заданной геометрической фигурой.
* `GeometryPredicate` - предикат для проверки пересечения атрибута с типом "Geometry" с заданной геометрической фигурой.

#### Конструктор

Конструктор принимает один параметр:
* `attributePath` - атрибут (собственный или у связанных моделей).

Также необходимо задать геометрическую фигуру, пересечение с которой будет проверяться. Для этого нужно воспользоваться методом `intersects` предиката.
Геометрическую фигуру необходимо задавать строкой в формате [EWKT](http://postgis.refractions.net/docs/using_postgis_dbmanagement.html#EWKB_EWKT).

```javascript
let predicate = new GeographyPredicate('locationPolygon').intersects('SRID=4326;POLYGON((30 10, 40 40, 20 40, 10 20, 30 10))');
let predicate = new GeometryPredicate('locationPolygon').intersects('SRID=4326;POLYGON((30 10, 40 40, 20 40, 10 20, 30 10))');
```

#### Свойства предиката

Получить заданный в предикате атрибут ("путь атрибута") можно с использованием свойства `attributePath` предиката:

```javascript
predicate.attributePath
```

Получить заданную в предикате геометрическую фигуру можно с использованием свойства `intersectsValue` предиката:

```javascript
predicate.intersectsValue
```

### NotPredicate

`NotPredicate` – предикат для инверсии значения другого заданного предиката.

#### Конструктор

Конструктор класса `NotPredicate` принимает единственный параметр:
* `predicate` - предикат, значение которого нужно инвертировать.

```javascript
let np = new NotPredicate(new SimplePredicate('creator.name', FilterOperator.Eq, 'X'));
```

#### Свойства предиката

Получить предикат, который был указан для получения инвертированного значения, можно с использованием свойства `predicate` предиката:

```javascript
let predicateToInvert = np.predicate;
```

### IsOfPredicate

`IsOfPredicate` – предикат для фильтрации записей по типу модели.

#### Конструктор

Конструктор класса `IsOfPredicate` можт принимать один параметр: 
`typeName` - имя модели.

В данном варианте использования предиката проверка указанного типа будет осуществляться у текущей модели, которая указана в построителе запросов:

```javascript
let predicate = new IsOFPredicate('customer');
```

Конструктор класса `IsOfPredicate` может также принимать два параметра: 
* `expression` - атрибут (собственный или у связанных моделей).
* `typeName` - имя модели.

В таком варианте использования предиката проверка указанного типа будет осуществляться у указанного атрибута модели, заданной в построителе запросов:

```javascript
let predicate = new IsOFPredicate('manager', 'employee');
```

#### Использование метода isOf

В построителе запросов доступен метод `isOf`, который позволяет упростить формирование предиката `IsOFPredicate`.

Пример явного объявления предиката `IsOFPredicate`:

```javascript
let predicate = new IsOFPredicate('man');
let builder = new Builder(store, 'creator').where(predicate);
```

Пример объявления предиката `IsOFPredicate` с использованием метода `isOf` построителя запросов:

```javascript
let builder = new Builder(store, 'creator').isOf('man');
```

## Операторы (операции)

В клиентском языке есть два класса, которые содержат свойства, являющиеся операторами (операциями) предикатов:
* `Condition` - класс с логическими операциями для `ComplexPredicate`.
* `FilterOperator` - класс с операциями для `SimplePredicate`, `StringPredicate` и `DatePredicate`.

### Операторы для фильтрации данных

Указанные ниже операторы (опрации) используются при создании таких предикатов как `SimplePredicate`, `StringPredicate` и `DatePredicate`. 

#### Равенство

Для указания равенства в предикате используется оператор `FilterOperator.Eq`:

```javascript
let sp1 = new SimplePredicate('name', FilterOperator.Eq, 'Tag1');
```

#### Неравенство

Для указания неравенства в предикате используется оператор `FilterOperator.Neq`:

```javascript
let sp1 = new SimplePredicate('name', FilterOperator.Neq, 'Tag1');
```

#### Больше

Для указания операции "больше" в предикате используется оператор `FilterOperator.Ge`:

```javascript
let builder = new Builder(store, modelName).where('age', FilterOperator.Ge, 10);
```

#### Больше или равно

Для указания операции "больше или равно" в предикате используется оператор `FilterOperator.Geq`:

```javascript
let builder = new Builder(store, modelName).where('age', FilterOperator.Geq, 11);
```

#### Меньше

Для указания операции "меньше" в предикате используется оператор `FilterOperator.Le`:

```javascript
let builder = new Builder(store, modelName).where('age', FilterOperator.Le, 12);
```

#### Меньше или равно

Для указания операции "меньше или равно" в предикате используется оператор `FilterOperator.Leq`:

```javascript
let builder = new Builder(store, modelName).where('age', FilterOperator.Leq, 11);
```

### Логические операторы для комплексных предикатов

Указанные ниже операторы (опрации) используются при комплексных предикатов. 

#### Or

Для указания логического значения "или" в предикате используется оператор `Condition.Or`:

```javascript
let cp1 = new ComplexPredicate(Condition.Or, sp1, sp2);
```

#### And

Для указания логического значения "и" в предикате используется оператор `Condition.And`

```javascript
let cp1 = new ComplexPredicate(Condition.And, sp1, sp2);
```

## Фильтрация перечислений

Если требуется отфильтровать записи по значению перечисления, то требуется на основании имени и типа перечисления сформировать требуетмый предикат (в рассмотренном ниже примере `filter` - это объект с информацией о фильтре для текущего столбца внутри компонента [flexberry-objectlistview](ef3_object-list-view.html)):

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

## Пример запроса

Запросы с использованием клиентского языка запросов формируются с использованием цепочки вызова методов построителя запросов: 

```javascript
let builder = new Builder(store)
      .from('customer')
      .select('id,firstName,lastName,age')
      .where('firstName', FilterOperator.Eq, 'Vasya')
      .top(50)
      .skip(100)
      .count();
```
