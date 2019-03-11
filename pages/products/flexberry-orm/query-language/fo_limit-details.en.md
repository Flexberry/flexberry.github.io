--- 
title: a Limit on detaily using the properties of the aggregator 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: Example of using constraints 
toc: true 
permalink: en/fo_limit-details.html 
lang: en 
autotranslated: true 
hash: 9e2f051a77e3c5eec675907eee701868b690c54fe2562956e007f6e19ce8f808 
--- 

If you want to set the condition for the existence of [datalow](fd_key-concepts.html) the condition in which it should participate to set the [aggregator](fd_key-concepts.html), you must use the function [Exist](fo_exist-details.html). When specifying the properties in the condition for the function `Exist` you must explicitly specify that this property aggregator: 

```
<Имя агрегирующего свойства у детейла>.<Свойство агрегатора>.
``` 

## Example of imposing restrictions on detaily using the properties of the aggregator 

![](/images/pages/products/flexberry-orm/query language/exist-example.png) 

To identify erroneously entered data in the database: find all companies that have a software product created before the creation of the company. 

``` csharp
 DataServiceLoader.InitializeDataSetvice();
 View view = Information.GetView("CompanyL", typeof(Company));
 View view2 = Information.GetView("SoftwareL", typeof(Software));
 view.AddDetailInView("Software", view2, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Company), view);
 ExternalLangDef langDef = ExternalLangDef.LanguageDef;
 var detail = new DetailVariableDef(langDef.GetObjectType("Details"), "Software", view2, "Company");
 lcs.LimitFunction = langDef.GetFunction(langDef.funcExist, detail,
                                         langDef.GetFunction(langDef.funcL,
                                         new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Software>(x => x.DateCreation)),
                                         new VariableDef(langDef.DateTimeType, Information.ExtractPropertyPath<Software>(x => x.Company.DateCreation))));
 var dos = DataServiceProvider.DataService.LoadObjects(lcs);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}