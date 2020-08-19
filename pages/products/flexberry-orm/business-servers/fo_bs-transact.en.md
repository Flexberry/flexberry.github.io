---
title: Updating multiple objects in one transaction
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, business servers, transaction example
summary: Illustration update the objects in one transaction, for example
toc: true
permalink: en/fo_bs-transact.html
lang: en
autotranslated: true
hash: fc676344a65b664d606e1f8db24059f658e772d1fe1ec71c9cc4d8589ddc6db8
---

If you need to update multiple objects in one transaction (for example, to maintain referential integrity), you can use the standard mechanism [business server](fo_business-server.html).

For the formation of the transaction that uses the object returned by [`OnUpdate`](fo_bs-example.html) any business server:

* Create\subtract objects to the transaction.
* To make the necessary changes in all the objects for the transaction (for example, insert links or edit fields).
* Add all transaction objects in the array returned by method elements.

{% include important.html content="For all objects of the transaction will fulfill the business server is responsible for updating them (except for the object which started the transaction)." %}

## Example

![](/images/pages/products/flexberry-orm/business-servers/bs-transact-example.png)

For example, there are two classes that reference each other mandatory connection. The challenge when creating object of class A to automatically create a class object and put In links to each other.

To begin, you must create a business server for class A and privaste it to update objects of this class.

In the method OnUpdate of class A, you would write the following code:

```csharp
var a = UpdatedObject;
var b = new B();
a.Копия = b;
b.Копия = a;

return new DataObject[2] { a, b }; 
```

Now when you create an object type And will automatically generate the copy - an object of type V.

{% include important.html content="Changed `return` method `OnUpdate`: through it, the list of elements of transactions." %}



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}