--- 
title: customize reading options (LCS) 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, data services, lcs, restriction, proofreading, filtering, loading 
summary: setup data sampling LoadingCustomizationStruct, data types, representation, limit, page load 
toc: true 
permalink: en/fo_loading-customization-struct.html 
lang: en 
autotranslated: true 
hash: 298bfda7bfc8cfae099fd69f263662c69e6d9f14e0fc3410b9a25b9f6c33fc16 
--- 

`LoadingCustomizationStruct` is a class Flexberry ORM to specify options for data selection (including the limit) that implements a private API, in contrast to the alternative option on the basis of LINQ. `LoadingCustomizationStruct` allows you to fully customize the settings of [data service](fo_construction-ds.html) for read operations of data objects. 

It is convenient to create a structure `LoadingCustomizationStruct` using the static method `GetSimpleStruct`. 

``` csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Шапка), "Chapchae");
``` 

## Parameters `LoadingCustomizationStruct` 

`LoadingCustomizationStruct` allows you to configure the following reading options: 

* `View` — specifies a [view](fd_view-definition.html), which will be performed чтение; 
* `ReturnTop` — specify the number of returned записей; 
* `LoadingTypes` — specifies the data classes whose instances you want to read ([example](fo_reading-several-types-objects.html)); 
* `ColumnsSort` — collation return результатов; 
* `ColumnsOrder` — order properties of the data object in the result string with delimiters, true if the read method is used [data service](fo_construction-ds.html) `LoadStringedObjectView`. 
* `InitDataCopy` — enable or disable initialization [copy data](fo_data-object-copy.html) when reading data. 
* `LimitFunction` — [restriction on read data objects](fo_limit-function.html), server will return only those objects that satisfy the given ограничению; 
* `Distinct` — obtaining of various data (used for example with the method [LoadRawValues SQLDataService](fo_standard-data-services.html)). 
* `LoadingBufferSize` — serving size when [batch reading](fo_reading-portion.html); 
* `RowNumber` — the range of indexes of downloadable objects, `lcs.RowNumber = new RowNumberDef(2, 5);` (useful, for example, for paging). 

{% include note.html content="__Presentation and restriction:__ `View` Submission must contain all the properties that are used to limit `LimitFunction`, to avoid errors or incorrect account. If there is a need to expand a [view](fd_view-definition.html) in accordance with the restriction feature `LimitFunction`, you can use a special class [ViewPropertyAppender](fo_view-property-appender.html)." %} 

## Application `LoadingCustomizationStruct` 

To subtract a dataset from the database into memory, you 

1. To create an object of type ICSSoft.STORMNET.Business.LoadingCustomizationStruct 

``` csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(тип, представление);

``` 
2. it is possible to manually set a [view](fd_view-definition.html) 

``` csharp
lcs.View = Клиент.Views.КлиентE;
``` 

3. Set the type of objects deducted 

``` csharp
lcs.LoadingTypes=new Type[) {typeof(Кредит)};
``` 

4. To impose [restrictions](fo_limit-function.html) deducted items (if the restriction is not imposed is deducted, all objects of this type) 

``` csharp
lcs.LimitFunction = <Объект типа ICSSoft.STORMNET.FunctionalLanguage.Function>
``` 

5. To configure other settings for reading (optional) 
6. To make a request to the server, using the method of [`LoadObjects` data service](fo_data-service.html) 

``` csharp
var credits = DataServiceProvider.DataService.LoadObjects(lcs).Cast<Кредит>();
``` 

## Example 

An example of using `LoadingCustomizationStruct` available at: [https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs). 




{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}