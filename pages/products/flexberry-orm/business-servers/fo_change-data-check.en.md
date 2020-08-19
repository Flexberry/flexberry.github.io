---
title: Check the validity of the data change object in a business server
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, database, business server, example
summary: Description of the object changes according to the preset conditions
toc: true
permalink: en/fo_change-data-check.html
lang: en
autotranslated: true
hash: 7e3c5997996960fd7500309063046f4534857760a4a0744c773b2f3be3e02e9a
---

## Modifying object data

There are times when you can prevent the changing of any fields in the object only if it satisfies certain conditions relative to the old value of the same field. However, at the time of exposure of the object in the [business server](fo_business-server.html) (in method [OnUpdate](fo_bs-example.html)), information about the old value of the field is already missing.

The old value field, you can learn subtracts it from the database, because the changed object is stored in application memory, but the changes were not yet in the database.

{% include important.html content="the Important point is the need for proofreading of the object in `отдельную переменную`, as if to subtract the data in the variable `UpdatedObject`, all changes made to the object will be lost (if there is a need to deduct the value of the field in the same object, then you need to properly and carefully use the [gecitkoy objects](fo_additional-loading.html))." %}

## Example

![](/images/pages/products/flexberry-orm/business-servers/filter-ex-diagram.png)

In the Bank in which you plan to use this system, you have the following rule: loan period defined for the customer when the loan may be extended, but it is sure to increase the amount of the loan (imposition of fines). Thus, the term of the loan may not be reduced, only increased.

* Create business server for class `Кредит` (if it has not yet been created).
* PstrfOnUpdate` in the method write the following code:

```csharp
if (UpdatedObject.GetStatus() == ObjectStatus.Altered)
{
    var newValue = UpdatedObject;
    var ds = (MSSQLDataService)DataService;
    var oldValue = ds.Query<Кредит>(Кредит.Views.КредитE).Where(k => k.__PrimaryKey == UpdatedObject.__PrimaryKey).First();

    if (newValue.СрокКредита > oldValue.СрокКредита && newValue.СуммаКредита <= oldValue.СуммаКредита)
        throw new Exception("If you change the term of the loan the loan amount needs to increase.");

    if (newValue.СрокКредита < oldValue.СрокКредита)
        throw new Exception("The term of the loan can't decline.");
}
```

`oldValue` can be obtained, and thus:

```csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = FunctionBuilder.BuildEquals<Кредит>(x => x.Клиент, UpdatedObject.Клиент);
var oldValue = DataService.LoadObjects(lcs)[0) as Кредит;
```

```csharp
UpdatedObject.GetStatus() == ObjectStatus.Altered
```

If such verification [status](fo_object-status.html) are truncated when the object is created or deleted.

Thus we can check the entered data on the basis of already existing data.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}