---
title: Cascade delete of objects
sidebar: flexberry-orm_sidebar
keywords: data Objects, Flexberry ORM, database, business server, restrictions
summary: How a cascading delete of objects, their pros and cons
toc: true
permalink: en/fo_cascade-delete.html
lang: en
autotranslated: true
hash: f74adde383bdfcf548aa2f2b93dcd5b7b5545463540abefad7a960ac7b34746c
---

## The task of a cascading delete

Given the following [figure](fd_class-diagram.html):

![](/images/pages/products/flexberry-orm/business-servers/kredit-diagramm.png)

If in the database there are objects of type `Клиент` referencing `Адрес`, without additional settings when you try to remove an object of type `Адрес` an error will occur. The database will not delete such an object.

## The solutions to the problem

Options can be many, this article will present only a few. Technology provides mechanisms for the solution of the problem (they mostly rely on the use of [business server](fo_business-server.html)), the options are limited only by the imagination of the developer.

### Special interfaces

To implement cascading deletes you can use specially developed interfaces [IReferencesCascadeDelete](fo_i-references-cascade-delete.html) and [IReferencesNullDelete](fo_i-references-null-delete.html).

### Recursive deletion

This is the easiest option, but also the most unfriendly to the user: removing 1 object can remove important information, information associated with the object.

Algorithm:

* Business server wizard (in the example - `Адрес`) subtract all the objects that reference the deleted.
* To assign all objects status [ObjectStatus.Deleted](fo_object-status.html).
* Send to delete all the objects.
* Repeat recursively for all objects.

### A dummy object

This option allows you to save all the data, except for the object you want to delete. However, the database will be a lot of objects that reference non-existent.

It should also be noted that this method requires additional processing of the data when output to the user. Objects that refer to fictitious, you want to filter or handle in a special way.

Solutions to problems are several:

* create a dummy object at each removal
* create 1 dummy object for each class and "hang" all references to it.

The algorithm for the second option:

* (once) to Create an object and write it to the database. Remember him [PrimaryKey](fo_primary-keys-objects.html), for example, in a configuration file or in the file with constants.
* Business server wizard (in the example - `Адрес`) subtract all the objects that reference the deleted.
* To assign all objects a reference to a dummy object.
* Send to update all objects.

### Bogus removal

When the fictitious destruction of data is actually not deleted from the database, but only marked as deleted. All objects added to some box, type `bool`. When you delete an object in the business server is intercepted by the object, it changes the status from `Deleted` on `Altered` and changing field `Актуально = false;`.

Then the object goes to update the database and remains there, but is considered remote. Of course, you need to implement logic that will "count" these objects are removed: in the output information the user has to impose restrictions on the output.

{% include note.html content="This method allows you to recover deleted objects." %}

#### Example

Necessary to Refine the class diagram thus, to support the bogus deletion: add a field `Актуально:bool`.

![](/images/pages/products/flexberry-orm/business-servers/kredit-diagramm-aktualno.png)

To add logic to the business-server objects (for example `Адреса`):

```csharp
if (UpdatedObject.GetStatus() == ObjectStatus.Deleted)
{
	// Don't let the object to be removed, but will raise the flag of Relevance. 
	UpdatedObject.SetStatus(ObjectStatus.Altered);
	UpdatedObject.Актуально = false;

	// Find all objects, referencing "delete" and remove them. 
	var ds = (SQLDataService)DataServiceProvider.DataService;
	var klients =
		ds.Query<Клиент>(Клиент.Views.КлиентE)
		  .Where(k => k.Прописка.__PrimaryKey == UpdatedObject.__PrimaryKey);
	foreach (var k in klients)
	{
		k.SetStatus(ObjectStatus.Deleted);
	}

	return klients.ToArray();
}
```

{% include note.html content="Attention! Reference objects are sent for deletion, but they are just as perevodyatsya in the business server and is not removed." %}

Next to the user being shown the "deleted" data when viewing the list of objects required for the appropriate control limit types:

``` csharp
var ds = (MSSQLDataService)DataServiceProvider.DataService;
IQueryable<Клиент> limit1 = ds.Query<Адрес>(Адрес.Views.АдресL).Where(Address => Address.Актуально);
Function onlyActual = LinqToLcs.GetLcs(limit1.Expression, Адрес.Views.АдресL).LimitFunction;
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}