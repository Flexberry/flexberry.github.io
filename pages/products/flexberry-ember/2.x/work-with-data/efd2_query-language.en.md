---
title: a Client-side query language
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, queries, predicate
toc: true
permalink: en/efd2_query-language.html
lang: en
autotranslated: true
hash: 6b7cb97a548760541fab26e14f6031f61e44f4091e998222e758649df1f5309f
summary: Description the client language requests.
---

## The namespace of the Query

In the expansion `ember-flexberry-data` exported namespace is Query, which contains classes for working with the query language.
Import namespace Query you must use the following code:

```javascript
import { Query } from 'ember-flexberry-data';
```

The Query namespace contains the following list of classes:

* `BaseAdapter` - base class for adapters query language, which convert the request object to the request for a specific type of backend.
* `BaseBuilder` - base class for query Builder.
* `Builder` class to build queries (query Builder).
* `Condition` - listing with the logical operators for the Query.ComplexPredicate.
* `FilterOperator` - transfer operations for the Query.SimplePredicate.
* `IndexedDbAdapter` adapter query language to query the IndexedDB from the request object.
* `JsAdapter` adapter query language for building functions filtrations of JavaScript arrays from the request object.
* `OdataAdapter` adapter query language to construct queries according to the specification [OData](http://www.odata.org/) from the request object.
* `OrderByClause` class for the formation of the order-by part of the query.
* `BasePredicate` - the base class for classes of predicates.
* `SimplePredicate` class to create a filter in the query for the attribute value and the specified operation.
* `ComplexPredicate` class to create a filter in the request of several predicates combined with logical operations.
* `StringPredicate` class to create a filter in the query for string fields.
* `DetailPredicate` class to create a filter in the request for Dethalbum objects.
* `DatePredicate` class to create a filter in a query on fields of type date.
* `NotPredicate` class for the inversion of the nested predicate.
* `IsOfPredicate` class to create a filter in the query type of the data object.
* `createPredicate` - method to create a predicate on the specified parameters.

An example of using the classes in the namespace:

```javascript
let builder = new Query.Builder(store);
```

In order not to write anywhere in the code explicitly the namespace, you can use the following JavaScript:

```javascript
import { Query } from 'ember-flexberry-data';
const { Builder, FilterOperator } = Query;

let builder = new Builder(store, 'customer').where('firstName', FilterOperator.Eq, 'Vasya');
```

## Query.Builder

`Query.Builder` class to build the query.

### Designer

Designer `Query.Builder` can take 2 parameters: mandatory `store`, and `modelName` - the name of the request entity (the model name for editing).

```javascript
let builder = new Query.Builder(store);

let builder = new Query.Builder(store, 'customer');
```

### Subtraction of object key

PstrfbyId` method accepts a parameter string or a number as the object key (GUID should be passed in lower case).

```javascript
let builder = new Query.Builder(this.store)
    .from(modelName)
    .selectByProjection(projectionName)
    .byId('3087fbdc-273e-4bae-b440-071fd1eab1e0');
```

### The name of the entity

```javascript
builder.from('employee');
```

### Restriction

A predicate function that returns `true`/`false`.
The predicate may be:

* Simple
* Complex
* String
* Dethalbum

```javascript
builder.where(Query.SomePredicate);
```

or

```javascript
let builder = new Query.Builder(store, 'customer').where('firstName', Query.FilterOperator.Eq, 'Vasya');
```

Dla mastera

```javascript
let builder = new Query.Builder(store, 'customer').where('manager', Query.FilterOperator.Eq, '3bcc4730-9cc1-4237-a843-c4b1de881d7c');
```

Dla field master

```javascript
let builder = new Query.Builder(store, 'customer').where('manager.firstName', Query.FilterOperator.Eq, 'Vasya');
```

{% include note.html content="Please do not use the constraint values, the function `cast` and other OData-specific functions!<br/><br/>
In doing so, you are linked in requests to the specific backend type.<br/>
The language of the queries created in order to abstract from any type of backend.<br/>
Failure to comply with this rule, expect problems when you work offline or change the type of backend.<br/>" %}

### Sorting

```javascript
builder.orderBy('age desc, price asc');
```

Po field masters:_

```javascript
builder.orderBy('creator.age desc, price asc');
```

### Return the first N records

```javascript
builder.top(N);
```

### Skip N records

```javascript
builder.skip(N);
```

## Counting the number of results

```javascript
builder.count();
```

## To set the attributes for the request

__Projection and set the attributes of this activity (select statements) are mutually exclusive, you must use one of these!__

```javascript
builder.select('id,age,name');
```

## Job projection for the query

__Projection and set the attributes of this activity(select statements) are mutually exclusive, you must use one of these!__

```javascript
builder.selectByProjection('EmployeeTestProjection');
```

## Create query instance based on the specified data

```javascript
builder.build();
```

## Passing the instance to query

```javascript
store.query(modelName, builder.build());
```

## Predicates

### Query.SimplePredicate

`Query.SimplePredicate` class for simple predicates to filter the field value and the specified operator.

#### Designer

Designer `Query.SimplePredicate` takes 3 parameters: `attributePath` - path attribute `operator` operator, `value` value.

```javascript
let predicate = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
```

### To derive properties of predicate

Get the path attribute:

```javascript
predicate.attributePath
```

Obtaining operator:

```javascript
predicate.operator
```

Getting the value of filtering:

```javascript
predicate.value
```

### Query.ComplexPredicate

`Query.ComplexPredicate` class for complex predicates that combine multiple predicates by a logical conditions.

#### Designer

Designer `Query.ComplexPredicate` takes 2 parameters: `condition` - logical conditions for predicates, `...predicates` - list of predicates to be merged.

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
let sp2 = new Query.SimplePredicate('surname', Query.FilterOperator.Eq, 'Ivanov');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
```

Another option:

```javascript
let p1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Vasya');
let p2 = new Query.SimplePredicate('surname', Query.FilterOperator.Eq, 'Ivanov');
let result = p1.or(p2);
```

#### To derive properties of predicate

Receiving a logical condition:

```javascript
complexPredicate.condition
```

Get a list of predicates:

```javascript
complexPredicate.predicates
```

#### Adding to the existing complex predicate a new one.

Take cp1 in the example for the constructor.
Adding and using:

```javascript
let sp3 = new Query.SimplePredicate('nationality', Query.FilterOperator.Eq, 'Russian');
let result = cp1.and(sp3);
```

Add using or:

```javascript
let sp3 = new Query.SimplePredicate('nationality', Query.FilterOperator.Eq, 'Russian');
let result = cp1.or(sp3);
```

### Query.StringPredicate

`Query.StringPredicate` class to build filters on string fields.

#### Designer

Designer `Query.StringPredicate` takes a single parameter: `attributePath` - path attribute of the predicate.

#### Search by substring

The addition of the value specified in the designer attribute must contain:

```javascript
let sp1 = new Query.StringPredicate('country').contains('i');
```

The addition of the value attribute of the master should contain:

```javascript
let sp1 = new Query.StringPredicate('country.name').contains('i');
```

#### To derive properties of predicate

Get the path attribute:

```javascript
predicate.attributePath
```

Obtaining the value that should be contained in the attribute:

```javascript
predicate.containsValue
```

### Query.DetailPredicate

`Query.DetailPredicate` class to build filters detalam.

#### Designer

Designer `Query.DetailPredicate` takes a single parameter - the name of detail.

```javascript
let dp = new Query.DetailPredicate('detailName')
```

#### The addition of a simple predicate for all detailov

```javascript
dp.all(new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1'));
```

or

```javascript
let p = new Query.DetailPredicate('detailName').all('field', Query.FilterOperator.Eq, 'Value');
```

Dla field master

```javascript
dp.all(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### Adding a simple predicate to any detail

```javascript
dp.any(new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1'));
```

or

```javascript
let p = new Query.DetailPredicate('detailName').any('field', Query.FilterOperator.Eq, 'Value');
```

Dla field master

```javascript
dp.any(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### Adding complex predicate for all detailov

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
let sp2 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag3');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').all(cp1);
```

Dla field master

```javascript
let sp1 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X');
let sp2 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'Z');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').all(cp1);
```

#### Adding complex predicate for any detail

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
let sp2 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag2');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').any(cp1);
```

Dla field master

```javascript
let sp1 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X');
let sp2 = new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'Y');
let cp1 = new Query.ComplexPredicate(Query.Condition.Or, sp1, sp2);
let dp = new Query.DetailPredicate('tags').any(cp1);
```

### Query.DatePredicate

`Query.DatePredicate` class for predicates to filter fields of type date by value, and the specified operator.

#### Designer

Designer `Query.DatePredicate` takes 4 parameters: `attributePath` - path attribute `operator` operator, `value` - value and `timeless` - flag indicating whether to consider time when comparing the dates (if true, then time does not count).

```javascript
let predicate = new Query.DatePredicate('birthday', Query.FilterOperator.Eq, '2018-02-06T11:00:00.000Z');
let predicate = new Query.DatePredicate('birthday', Query.FilterOperator.Eq, '2018-02-06', true);
```

#### To derive properties of predicate

Get the path attribute:

```javascript
predicate.attributePath
```

Obtaining operator:

```javascript
predicate.operator
```

Getting the value of filtering:

```javascript
predicate.value
```

Getting a flag taking into account the time:

```javascript
predicate.timeless
```

### Query.NotPredicate

`Query.NotPredicate` class for the inversion of the nested predicate.

#### Designer

Designer `Query.NotPredicate` takes a single parameter – the other the predicate.

```javascript
let np = new Query.NotPredicate(new Query.SimplePredicate('creator.name', Query.FilterOperator.Eq, 'X'));
```

#### To derive properties of predicate

Getting a nested predicate:

```javascript
let innerPredicate = np.predicate;
```

### Query.IsOfPredicate

#### Designer

Designer `Query.IsOfPredicate` with two parameters takes a string expression that indicates the property of the model, and the string with the model name.

```javascript
let predicate = new Query.IsOFPredicate('manager', 'employee');
```

Designer `Query.IsOfPredicate` with one parameter takes a string with the model name.

```javascript
let predicate = new Query.IsOFPredicate('customer');
```

There is a method to simplify work with this predicate.

```javascript
let predicate = new Query.IsOFPredicate('man');
let builder = new Query.Builder(store, 'creator').where(predicate);
```

An equivalent expression without using a predicate.

```javascript
let builder = new Query.Builder(store, 'creator').isOf('man');
```

## Logical operators for complex predicates

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

## Operators to filter the data for simple predicates

### Equality

`Query.FilterOperator.Eq`

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Eq, 'Tag1');
```

### Inequality

`Query.FilterOperator.Neq`

```javascript
let sp1 = new Query.SimplePredicate('name', Query.FilterOperator.Neq, 'Tag1');
```

### More

`Query.FilterOperator.Ge`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Ge, 10);
```

### Greater than or equal to

`Query.FilterOperator.Geq`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Geq, 11);
```

### Less

`Query.FilterOperator.Le`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Le, 12);
```

### Less than or equal to

`Query.FilterOperator.Leq`

```javascript
let builder = new Query.QueryBuilder(store, modelName).where('age', Query.FilterOperator.Leq, 11);
```

### Create a query to filter the listings

The name and type of enumeration is taken `name` and written in a predicate:

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
    // defaults to 
    default:
    return new SimplePredicate(filter.name, FilterOperator.Eq, pattern);
}
```

## Example of a complex query

```javascript
let builder = new Query.QueryBuilder(store)
      .from('customer')
      .select('id,firstName,lastName,age')
      .where('firstName', Query.FilterOperator.Eq, 'Vasya')
      .top(50)
      .skip(100)
      .count();
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}