--- 
title: description of the variables in the build functions limitations 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM Limitations 
summary: examples of the use VariableDef and DetailVariableDef 
toc: true 
permalink: en/fo_variable-def.html 
lang: en 
autotranslated: true 
hash: 23ea816cedb1e06d8904289e5b6fa1c1d66242fe69c16e688e757d4bc664e846 
--- 

When constructing constraints using the method `GetFunction` argument to this method depending on the type of applied functions may be passed a description of the variable: 

* when setting limits with [SQLWhereLanguageDef](fo_function-list.html) class is used `VariableDef`, 
* when imposing restrictions on detail with [ExternalLangDef](fo_external-lang-def.html) to describe the variable-detail used class `DetailVariableDef`. 

## VariableDef 

Class `VariableDef` is used to define the variable in the restriction (usually indicates an attribute [object](fo_data-object.html)). Used together with [SQLWhereLanguageDef](fo_function-list.html). 

Defined in namespace: `ICSSoft.STORMNET.FunctionalLanguage`. 

Build: `ICSSoft.STORMNET.FunctionalLanguage.dll`. 

The most common constructor that is used when building constraints: 

```csharp
public VariableDef(ObjectType objType, string objStringedView)
``` 

He is passed the parameters: 

* `objType` - ObjectType-the type of the variable (for example, `langdef.StringType`), 
* `objStringedView` - the property name of the object on which the plan limitation. 

Examples of design `VariableDef` when specifying constraints, see [SQLWhereLanguageDef](fo_function-list.html). 

### VariableDef to PrimaryKey 

When building restrictions on [primary keys](fo_primary-keys-objects.html) deducted objects (private keys) it is worth considering that [SQLWhereLanguageDef](fo_function-list.html) ignores constant `PrimaryKey`. Instead of a constant `PrimaryKey` better to use `StormMainObjectKey` (determined by the corresponding constant). 

__Wrong__: 

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "PrimaryKey"), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
``` 

__True__: 

```csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, SQLWhereLanguageDef.StormMainObjectKey), "64F45BC3-339B-4FBA-A036-C5E9FE9EAE53");
``` 

It should be noted that the restriction on [primary key](fo_primary-keys-objects.html) __masters__ is imposed as follows: 

``` csharp
var ld = SQLWhereLanguageDef.LanguageDef;
ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, Information.ExtractPropertyPath<СамОбъект>(x => x.СсылкаНаМастера)), "84F456C1-312F-30C0-A238-11E3FE68E852");
``` 

where "Silentmaster" - a reference to the master. 

## DetailVariableDef 

Class `DetailVariableDef` is used to determine the variable restrictions, to describe detailov. Used in conjunction with [ExternalLangDef](fo_external-lang-def.html). 

Defined in namespace: `ICSSoft.STORMNET.Windows.Forms`. 
Build: `ExternalLangDef.dll`. 

When designing `DetailVariableDef` specify the settings: 

* `Type` - ObjectType-the type (you can get `ldef.GetObjectType("Details")`), 
* `View` - the view name of detail, 
* `ConnectMasterProp` - way from detail (see description below) 
* `OwnerConnectProp` - detalu from the object (see the description later). 

Examples of design `DetailVariableDef` when setting restrictions is available in the article: 

* [Restrictions on detali](fo_exist-details.html), 
* [The limit on detaily using the properties of aggregator](fo_limit-details.html). 

### Job OwnerConnectProp and ConnectMasterProp 

Problems often arise in determining what to specify as `ConnectMasterProp` and `OwnerConnectProp`. 

`ConnectMasterProp` specifies the path from detail and `OwnerConnectProp` - detalu from the object. 

The bundle is as follows: **Detail.ConnectMasterProp = Object.OwnerConnectProp**. 

It is worth noting that the property `OwnerConnectProp` determines what objects are detaily. If the property is not specified (i.e., specified `null`), the conjunction occurs on the [primary key](fo_primary-keys-objects.html)(i.e. `StormMainObjectKey`). 

#### Examples of usage 

##### Example 1 

![](/images/pages/products/flexberry-orm/query-language/diagramm.JPG) 

It is necessary to filter Odottamattomasti for Salenatural. 
When setting `DetailVariableDef` should specify: `OwnerConnectProp` = Person, `ConnectMasterProp` = Identity. 

##### Example 2 

![](/images/pages/products/flexberry-orm/query language/diagramm-2.PNG) 

It is necessary to filter Obrazovatelnogo for Stroyrekonstruksiya. 
When setting `DetailVariableDef` should specify: `OwnerConnectProp` = Zaveniaghina.Personality `ConnectMasterProp` = Identity. 

It is necessary to filter Obrazovatelnogo for Stroyrekonstruksiya. 
When setting `DetailVariableDef` should specify: `OwnerConnectProp` = Zaveniaghina.Personality `ConnectMasterProp` = Identity. 

### setting limits on pseudometal 

For example, the entity "Customer" and "Credit" are connected shown in the image. 

![](/images/pages/products/flexberry-orm/query language/pseudo-details.png) 

You need to restrict clients by setting the limit to reference them loans. 

It is obvious that from the point of view of storage of this object model in the database in accordance with [existing rules](fo_storing-data-objects.html), there is no distinction between aggregation and simple Association. However, restricting the sample according to the criteria of the child table do not differ in the case of aggregation and Association. Therefore to build restrictions in the case of pseudometal you must use `DetailVariableDef` together with [ExternalLangDef](fo_external-lang-def.html). 

If in the above example, it is necessary to select customers that have loans for more than 15 years, the code will look as follows: 

``` csharp
ExternalLangDef ldef = ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsДолгосрочныеКлиенты = LoadingCustomizationStruct.GetSimpleStruct(typeof(Клиент), "Client");
lcsДолгосрочныеКлиенты.LoadingTypes = new[) { typeof(Клиент) };
var view = Information.GetView("Loan", typeof(Кредит));
var dvd = new DetailVariableDef
{
    ConnectMasterPorp = "Client",
    OwnerConnectProp = new[) { SQLWhereLanguageDef.StormMainObjectKey },
    View = view,
    Type = ldef.GetObjectType("Details")
};
lcsДолгосрочныеКлиенты.LimitFunction = ldef.GetFunction(ldef.funcExist, dvd,
                                                        ldef.GetFunction(ldef.funcGEQ, 
                                                        new VariableDef(ldef.GuidType, "Loanyears"), 15));
ICSSoft.STORMNET.DataObject[) dobjsДолгосрочныеКлиенты = DataServiceProvider.DataService.LoadObjects(lcsДолгосрочныеКлиенты);
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}