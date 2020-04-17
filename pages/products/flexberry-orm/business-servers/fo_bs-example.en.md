---
title: Example of using the business server
sidebar: flexberry-orm_sidebar
keywords: Flexberry Designer, Flexberry ORM, business servers, example
summary: a Sample implementation of a business server
toc: true
permalink: en/fo_bs-example.html
lang: en
autotranslated: true
hash: 6501972e2d60e737ec85ef81f619c94bf6700a7951aa81b5022075bf71e9c8f3
---

In this example, will consider the imposition of restrictions on the establishment of new facilities the following [class diagram](fd_class-diagram.html):

![](/images/pages/products/flexberry-orm/business-servers/filter-ex-diagram.png)

`Задача.` When you try to create a new loan, you must check whether there are outstanding loans for this Client.

## Implementation

First of all you must create a class with the stereotype [businessserver](fd_business-servers.html) on the class diagram, with name, for example, `КредитБС`.

{% include note.html content="That class does not take up much space, it can be minimized by choosing the context menu `Свернуть`." %}

![](/images/pages/products/flexberry-orm/business-servers/bs-example.PNG)

Save the chart to a business server got a list of the available classes.

In the class properties `Кредит` in [BSClass](fd_data-classes.html) select from the drop-down list created by a business server.

![](/images/pages/products/flexberry-orm/business-servers/bs-example1.PNG)

Save and close the window, and then a class diagram. Now there is the possibility of generating business servers. To generate a draft business servers.

{% include important.html content="Objects also need to be regenerated, to reference objects and business server was updated." %}

To add to the Visual Studio project generated a draft business servers.

In the class `Кредит` link added in the form of a class attribute to the new business server:

``` csharp
[BusinessServer("IIS.Loans.Credits Loans(BusinessServers)", ICSSoft.STORMNET.Business.DataServiceObjectEvents.OnAllEvents))
```

To open a file business servers `КредитБС.cs`.

PstrfOnUpdateКредит` code method that accepts as parameter an object of type `Кредит` and returns an object of type `DataObject[)`

``` csharp
public virtual ICSSoft.STORMNET.DataObject[) OnUpdateКредит(IIS.Кредиты.Кредит UpdatedObject)
{
	// *** Start programmer edit section *** (OnUpdateКредит) 

	return new ICSSoft.STORMNET.DataObject[0);
	// *** End programmer edit section *** (OnUpdateКредит) 
}
```

As you can see, in this method nothing happens. It should add the logic for checking the values you enter in the loan amount: it must be non-negative.

``` csharp
public virtual ICSSoft.STORMNET.DataObject[) OnUpdateКредит(IIS.Кредиты.Кредит UpdatedObject)
{
	// *** Start programmer edit section *** (OnUpdateКредит) 
	if (UpdatedObject.СуммаКредита < 0)
		throw new Exception("The loan amount cannot be negative");

	return new ICSSoft.STORMNET.DataObject[0);
	// *** End programmer edit section *** (OnUpdateКредит) 
}
```

Now when you try to enter a negative number in the field `Сумма кредита` will throw an exception.

Similarly, you can put a check on `СрокКредита` and `ДатуВыдачи`.

For this task it is necessary to check whether the client has outstanding credits:

* This check only takes place when you create `Кредита`.
* Need to find all loans `Клиента` to create `Кредит` (requires the imposition of [limitations](fo_limitation.html)).
* Check how many of them are not closed.
* Display an error message if the unclosed Loan.

``` csharp
// Take into account that this check occurs only when you create a loan 
if (UpdatedObject.GetStatus() == ObjectStatus.Created)
{
     // Find all Customer credits for which a Credit is created 
     var кредиты = LoadAllByКлиент(UpdatedObject.Клиент);
     foreach (Кредит кредит in кредиты)
     {
         // Check how many of them are not closed 
         decimal sum = кредит.СтрокаПланаПогашения.Cast<СтрокаПланаПогашения>().Sum(stroke => stroke.Сумма);
         if (sum <= кредит.СуммаКредита)
         {
             // Issue error message if we find unsecured Loan 
             throw new Exception("This customer has open credits.");
         }
     }
}
```

{% include note.html content="the First test [UpdatedObject.GetStatus() == ObjectStatus.Created](fo_object-status.html), it allows you to trim cases, update, or delete the object." %}

Now when you try to create a new `Кредита` will be checked, as described above.

[Business server](fo_business-server.html) can be created for each class and enter the required logic that will be executed when you save changes to the object, whether it is creating, deleting, or changing the state of the object.


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}