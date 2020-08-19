---
title: Limits on detaily
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM Limitations
summary: Functions funcExist, funcExistExact, funcExistAll, funcExistAllExact, funcExistDetails
toc: true
permalink: en/fo_exist-details.html
lang: en
autotranslated: true
hash: be33f3fb77d9d345158f6ce3d1b21ddafe98af55508ce79f0f66d30874673ed5
---

## Description and differences of functions

Using these functions [ExternalLangDef](fo_external-lang-def.html) it is possible to impose [restrictions](fo_limit-function.html) only detaily ([example](fo_limit-details.html))

| Name | Displayed name | Description |
|---|---|---|
| `funcExist` | There are {} that {} | Returns True if there is at least one object satisfying the condition, otherwise False. Condition - only one function.|
| `funcExistAll` | There are {} that {} And {} And {} ... | Return True if there is at least one object satisfying the condition, otherwise False. As the condition there may be many features, which will automatically connect a conjunction. Attention! The valid kinds of functions only two: "=" ([FuncEQ](fo_func-eq.html)) and "AMONG the VALUES()" ([FuncIN](fo_func-in.html)). |
| `funcExistExact` | There are only such {} that {} | Returns True if all objects satisfy the condition, otherwise False. Condition - only one function. |
| `funcExistAllExact` | There are only such {} that {} And {} And {} ... | Return True if all objects satisfy the condition, otherwise False. As the condition there may be many features, which will automatically connect a conjunction. Attention! The valid kinds of functions only two: "=" ([FuncEQ](fo_func-eq.html)) and "AMONG the VALUES()" ([FuncIN](fo_func-in.html)).|
| `funcExistDetails` | There are {} and such {} that {} | the Function will return `True` if there is at least one object from the first set and at least one object from the second set that satisfies the condition, otherwise `False`. Condition - only one function (=, >, <, >=, <=).|

## Example usage

![](/images/pages/products/flexberry-orm/query-language/exist-detals-example-2.jpg)

The following code assumes that the view `"Servicedisplayname"` always present properties ` Division ` (as it is condition) and `"Server"` (property on which there is a connection).

Required to deduct the servers that belong to a particular Department (i.e., servers that belong to the specified Unit and some will also be proofread).

```csharp
ExternalLangDef ldef = ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(typeof (Репликации.Сервер), "Server");
lcsСервер.LoadingTypes = new Type[] {typeof (Репликации.Сервер)};
View view = Information.GetView("Servicedisplayname", typeof(Репликации.СерверПодразделения));
ICSSoft.STORMNET.Windows.Forms.DetailVariableDef dvd = new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef();
dvd.ConnectMasterPorp = Server;
dvd.OwnerConnectProp = new string[] { SQLWhereLanguageDef.StormMainObjectKey };
dvd.View = view;
dvd.Type = ldef.GetObjectType("Details");
lcsСервер.LimitFunction = ldef.GetFunction(ldef.funcExist,
                                            dvd,
                                            ldef.GetFunction(ldef.funcEQ,
                                                            new VariableDef(ldef.GuidType, Division),
                                                            UpdatedObject.НаправленоИз.__PrimaryKey));
lcsСервер.ReturnTop = 1;
ICSSoft.STORMNET.DataObject[] dobjsСервер = ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObjects(lcsСервер);
```

You want to subtract only those servers that belong only to one particular Unit (i.e., servers that belong to the specified Division and some will not be proofread).

```csharp
ExternalLangDef ldef = ICSSoft.STORMNET.Windows.Forms.ExternalLangDef.LanguageDef;
LoadingCustomizationStruct lcsСервер = LoadingCustomizationStruct.GetSimpleStruct(typeof (Сервер), "Server");
lcsСервер.LoadingTypes = new[] {typeof (Сервер)};
View view = Information.GetView("Servicedisplayname", typeof(Репликации.СерверПодразделения));
var dvd = new ICSSoft.STORMNET.Windows.Forms.DetailVariableDef
                {
                    ConnectMasterPorp = Server,
                    OwnerConnectProp = new[] { SQLWhereLanguageDef.StormMainObjectKey },
                    View = view,
                    Type = ldef.GetObjectType("Details")
                };
lcsСервер.LimitFunction = ldef.GetFunction(ldef.funcExistExact,
                                            dvd,
                                            ldef.GetFunction(ldef.funcEQ,
                                                            new VariableDef(ldef.GuidType, Division),
                                                            new Guid("6D7DC426-F5E9-4F63-B7B5-20C9E237DF2D")));
DataObject[] dobjsСервер = DataServiceProvider.DataService.LoadObjects(lcsСервер);
```

## Comparison of properties of two different datalow (not above ground level) with a common aggregator

For example, you need to all computers have at least one "piece of iron" will be bought before any software for it.
The order of properties in the comparison function applied to detali has a value (in this case, the property names coincide).

```csharp
 View view = Information.GetView("ComputerL", typeof(Computer));
 View view2 = Information.GetView("HardwareD", typeof(Hardware));
 View view3 = Information.GetView("SoftwareD", typeof(Software));
 view.AddDetailInView("Computer", view2, true);
 view.AddDetailInView("Computer", view3, true);
 var lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Computer), view);
 ExternalLangDef langDef = ExternalLangDef.LanguageDef;
 var detail1 = new DetailVariableDef(langDef.GetObjectType("Details"), "Hardware", view2, "Computer");
 var detail2 = new DetailVariableDef(langDef.GetObjectType("Details"), "Software", view3, "Computer");
 lcs.LimitFunction = langDef.GetFunction(langDef.funcExistDetails,
                        detail1, detail2, langDef.GetFunction(langDef.funcG,
                        new VariableDef(langDef.DateTimeType, "DeliveryDate"),
                        new VariableDef(langDef.DateTimeType, "DeliveryDate")));
 var dos = DataServiceProvider.DataService.LoadObjects(lcs);
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}