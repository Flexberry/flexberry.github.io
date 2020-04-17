---
title: Integration with business server
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, data services, business server
summary: the development of user operations in the process of service data
toc: true
permalink: en/fo_user-operations-dataservice.html
lang: en
autotranslated: true
hash: caf8eb7d079a0e0710ee2f9f055fdca70eb3975e34cc3935f3626ba9404d8654
---

If you want to perform any action in the process of updating [service data](fo_data-service.html) storage, it is possible to organize the processing of event data service through a dedicated [business server](fo_business-server.html).

So, to organize this processing, you must:

* Describe the class — business server, a successor from `ICSSoft.STORMNET.Business.BusinessServer`.
* To describe the method-interceptor view

```csharp
public DataObject[] OnUpdateXXXXX(XXXXX UpdatedObject)
```

where XXXXX is the name of the data class whose instances updates [service data](fo_data-service.html). To implement this method (to perform the necessary actions when you upgrade).

## Specify the business server to the data class

To register a business server needs data class `.Net`-attribute `BusinessServer`, at the same time indicating the type of event.

The event types are as follows:

* `DataServiceObjectEvents.OnAllEvents` — all the events of [data service](fo_data-service.html);
* `DataServiceObjectEvents.OnAnyEvent` — any events of [data service](fo_data-service.html) (irrelevant when attributing events that are important when obtaining a business server via `BusinessServerProvider.GetBusinessServer`, you can get a business server, without distinction regarding the types of events on which it was signed);
* `DataServiceObjectEvents.OnDeleteFromStorage` — [data object](fo_data-object.html) be deleted [service data](fo_data-service.html);
* `DataServiceObjectEvents.OnInsertToStorage` — [data object](fo_data-object.html) will be created in хранилище;
* `DataServiceObjectEvents.OnUpdateInStorage` — [data object](fo_data-object.html) will be updated in the repository.

Event types can be combined using `|`.

The data service will call a method-interceptor just before executing the operation. Parameter the method will come [data object](fo_data-object.html), on which the operation is performed. Method can return any additional data objects that will be picked up by the» «service data.

About why you need to return [data objects](fo_data-object.html) instead of doing individual calls to the data service directly from within an interceptor. The answer is obvious — in order to update attached objects executed in the same transaction](fo_bs-transact.html). Otherwise, a separate call data service — private transaction, respectively, if the code is not running under a transaction server (eg. `COM `), there will be a gap.

The programmer can know what [business server](fo_business-server.html) assigned to the [data class](fo_data-object.html) via provider business servers, the method `BusinessServerProvider.GetBusinessServer`.

### An example of a business server and data class with assigned business server

```csharp
public class DataServiceEventsServer:ICSSoft.STORMNET.Business.BusinessServer
	{
		public DataObject[] OnUpdateЖурнал(Журнал UpdatedObject)
		{
			Console.WriteLine("Caught log update {0}." , UpdatedObject.Наименование);
			return new DataObject[0];
		}

	}

	[BusinessServer(typeof(DataServiceEventsServer), DataServiceObjectEvents.OnInsertToStorage)]
    // *** End programmer edit section *** (Log CustomAttributes) 
    public class Журнал : Ресурс
{
		/*Something*/
}
```

### Other examples

* [Check uniqueness of data entered in the business server](fo_unique-data-check.html).
* [Example of use business-servers](fo_bs-example.html).
* [Checking the validity of changes to the data object in business server](fo_change-data-check.html)
* [github.com](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/BusinessServers/CDLibBS.cs).

## Features of functioning

[Service data](fo_data-service.html) will cause a method interceptor immediately before the operation. Parameter the method will come [data object](fo_data-object.html), on which the operation is performed. Method can return any additional data objects that will be picked up by the» «service data.

You need to return data objects instead of doing individual calls to the data service directly from within an interceptor to [updates added](fo_bs-transact.html) executed in the same transaction. Otherwise, a separate call data service — private transaction, respectively, if the code is not running under a transaction server (eg. `COM `), there will be a gap.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}