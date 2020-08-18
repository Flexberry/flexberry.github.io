---
title: call Order business servers
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, business servers, inheritance, interface, data object, example
summary: Illustration of procedure calls to a business server in different situations
toc: true
permalink: en/fo_order-calls-bs.html
lang: en
autotranslated: true
hash: 9fced8cfa2c8c4a1b0e9bd8e32ab606847bf6de6a2e4b5f1dc5cf18230aaa7d2
---

Note that in [business server](fo_business-server.html) can be caused by the object is updated not through the returned array, and a separate call to [service data](fo_data-service.html). In this case, it is necessary to consider that the business server will be called for this object separately.

### Aggregator, detaily

If you have the following situation [detaylari](fo_detail-associations-properties.html):

![](/images/pages/products/flexberry-orm/business-servers/agregator-detail-at-bs.png)

the business server when the update will be triggered in the following order:

* Classcreator
* Classical

### Inheritance

If you have the following situation with [the inheritance](fd_inheritance.html):

![](/images/pages/products/flexberry-orm/business-servers/bs-hierarchy.JPG)

the business server when the object is updated class `Человек` will fire in the following order:

* The creature
* Animal
* Mammal
* People

### Interfaces

You can register [attribute binding business servers](fo_bs-example.html) not only in the classroom but also to the interface. In this case, all objects of a descendant class from this interface will be processed according to the business server. If you specify multiple interfaces with business server:

```csharp
public class Class2:Class3, Interface3
{
//... 
}

public class Class1:Class2, Interface1, Interface2
{
//... 
}
```

the order of triggering is:

* business server bound to Class3
* business server bound to Interface3
* business server tied to Class2
* business server bound to Interface2
* business server linked to Interface1
* business server bound to Class1

{% include note.html content="If some interface will meet several times, [business server](fo_business-server.html) for him to be executed only once in accordance with the level of the most distant ancestor." %}

### Rules for updating a data object

For example, you have the following situation:

![](/images/pages/products/flexberry-orm/business-servers/bs-masters.png)

If the business server class `Товар` fixed `Фирма-производитель` and returned in the method business servers, then the manufacturer will called it a business server. **Important to understand** that the request for `Товара` was already generalivan (this was done immediately after the testing of its own business server) and business server `Фирмы-производителя` change `Товар `не should - new changes will not be committed to the database.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}