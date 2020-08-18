---
title: Cancellation of removal used the values from the directory
sidebar: flexberry-orm_sidebar
keywords: data Objects, business server, restrictions
summary: meeting the challenges of uninformative messages when deleting object
toc: true
permalink: en/fo_delete-cancel.html
lang: en
autotranslated: true
hash: 656a6ee899dd5a62f1d87bde4a99264458f3f4a2d4641fa9c58b616404aa04aa
---

## Used links wizard

This article addresses the question **cancel** values from a table storing the master used in other objects.

The question of how to **remove** the values considered in the paper [Cascade deleting objects](fo_cascade-delete.html).

## The attempt to delete the master

Given the following diagram:

![](/images/pages/products/flexberry-orm/business-servers/kredit-diagramm.png)

If in the database there are objects of type `Клиент` referencing it when trying to delete object of type `Адрес` error is displayed:

![](/images/pages/products/flexberry-orm/business-servers/delete-error.png)

The database will not delete such an object, and the user will be uninformative message.

## The solution to the problem

To solve this problem is to use the [business server](fo_business-server.html) of a deleted object:

* To know the number of objects that reference the deleted.
* If it is not equal to 0, throw exception with a user-friendly description of the problem.
* If necessary, catch the exception and handle in a special way.

## Example

For the above-described conditions: to add validation to a business class server `Адрес`:

```csharp
if (UpdatedObject.GetStatus() == ObjectStatus.Deleted)
{
    // Find the number of clients that reference the deleted address. 
    var ds = (MSSQLDataService)DataServiceProvider.DataService;
    var clientsCount = ds.Query<Клиент>(Клиент.Views.КлиентE).Where(k => k.Прописка.__PrimaryKey == UpdatedObject.__PrimaryKey).Count();

    // If the customer is not 0, throw an exception. 
    if (clientsCount != 0)
    {
        throw new Exception(string.Format("Could not remove Address. At this address is home to {0} client(s)(s)", clientsCount));
    }
}
```

As a result, when you try to remove the address at which the registered clients, the user will give the following message:

![](/images/pages/products/flexberry-orm/business-servers/delete-error-plus.png)

The message has changed to be more meaningful.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}