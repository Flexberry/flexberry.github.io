--- 
title: Example of imposing restrictions on loading objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, Constraints, example 
summary: the Imposition of restrictions, sorting, the index range of downloadable objects 
toc: true 
permalink: en/fo_load-limitation-example.html 
lang: en 
autotranslated: true 
hash: 42b3fd282ffdebcac6ea4dc6b9e1b15c9ab7569fc5566315a1aafa7fa3ec8618 
--- 

Full list of code examples [Flexberry ORM](fo_flexberry-orm.html) is in ["code Examples"](fo_code-samples.html). 

## an Example of a restriction on loadable data objects (terms, quantity, etc.) 

Typically, applications are not limited to simple loading of objects from the database: it is required to sort the imposition of conditions to downloadable objects on their indexes. 
Storage and transfer of data service this information is used by the data structure [`ICSSoft.STORMNET.Business.LoadingCustomizationStruct`](fo_loading-customization-struct.html). 
The example shows its use when loading the data objects: the imposition of restrictions, sorting, the index range of downloadable objects. 

```csharp
Console.WriteLine("3. How to load a set of dataobjects in specific view, the limitation, quantity, etc..");

// In Flexberry ORM have different sets of operations (a language) for defining constraints. The easiest SQLWhereLanguageDef. 
ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef ld =
    ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef.LanguageDef;

// Create a structure to store the boot parameters of objects (representation, data types, etc.). 
LoadingCustomizationStruct lcs = new LoadingCustomizationStruct(null);
lcs.View = CD.Views.CD_E;
lcs.LoadingTypes = new[] { typeof(CDDA), typeof(CDDD), typeof(DVD) };

// The constraint using the properties of the classes referenced by the class CS. Can be used those properties 
// specified in the representation that is loading objects. 
lcs.LimitFunction = ld.GetFunction(ld.funcEQ,
    new VariableDef(ld.StringType, Information.ExtractPropertyPath<CD>(c => c.Publisher.Country.Name)), "USA");

// Parameters sorting the loaded objects. You can also specify only the properties that are in view. 
lcs.ColumnsSort = new[] { new ColumnsSortDef(Information.ExtractPropertyName<CD>(c => c.Name), ICSSoft.STORMNET.Business.SortOrder.Asc) };

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

// You can specify a range of indexes of downloadable objects, which is useful, e.g., for pagination. 
// lcs.RowNumber = new RowNumberDef(2, 5); 
// There are three main methods for loading objects: 
// 1. To load from the database records and for each one create an instance of the data object. 
ICSSoft.STORMNET.DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

// 2. To download without creating instances (each object is represented as a string of property values delimited). 
// Use when not needed editing. It is much faster! 
ObjectStringDataView[] stringedview = DataServiceProvider.DataService.LoadStringedObjectView(';', lcs);

// 3. To obtain the number of objects without loading data. 
int iObjsCount = DataServiceProvider.DataService.GetObjectsCount(lcs);

stopwatch.Stop();
Console.WriteLine("Time taken for all loadings: {0} ms.", stopwatch.ElapsedMilliseconds);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}