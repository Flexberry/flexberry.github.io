---
title: machining is required of the domain model
sidebar: guide-practical-guides_sidebar
keywords: guide, ember-flexberry, flexberry ember, flexberry designer, stage, class diagram
toc: true
permalink: en/gpg_setting-language-and-structure.html
lang: en
autotranslated: true
hash: a3f73de9ee9fc0a07a149ba5e473ef251b446c717c57bfebd6c08e7810b06ec3
---

{% include important.html content="the guide is a continuation of [a Practical guide for the creation of UML diagrams](gpg_practical-guides-uml.html)." %}

Before starting to work with Ember Flexberry, consider the question of choice of language for naming of classes and other model elements of the subject area.

In [practical guide Flexberry ASP.NET](gpg_preparatory-stage.html) for naming data objects, and other elements of the domain model is primarily used Russian language. This approach is convenient from the point of view of the perception of analysts and developers who work with the model. However, if you develop applications on the Ember Flexberry recommended to use the English language for naming those elements of the model that a developer.

This trebuie due not only to the established practice of programming in JavaScript ([naming variables in JavaScript](https://learn.javascript.ru/variables#variable-naming)), but also additional features when working with Flexberry Ember. First, there can be a situation in which a specific code framework Ember.js may not work properly due to the presence of Cyrillic in the names. Second, some browsers may become unstable, if the server API is Cyrillic. In this regard, before proceeding with further practical guidance, let us translate our model of the subject area in English.

Additionally, General recommendations for naming variables in JavaScript can be found [here](https://learn.javascript.ru/variable-names).

## Up stage

In order for a source model suitable for different practice guidelines, remains unchanged, duplicate stage. To do this, select "Export stage":

![Export stage](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/1.png)

Save the stage locally, and then re-upload this stage in Flexberry Designer by using the "Import stage":

![Import stage](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/2.png)

Save a copy of the stage called **stage (ember)**.

## Renaming classes

First let's rename the classes. To do this, open a class diagram "Entity" and select the **Custom**. In order to rename the class, you should call the context menu and then select "Rename":

_`Заказ → [right mouse button (RMB)] → Переименовать`_

![Rename class](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/3.png)

Enter in the box the new name - **Order**.

{% include important.html content="classes always use the command \"Rename\". Do not try to rename the classes by editing their names directly from the chart: so rename will not work." %}

---

**Samostoyatelno** rename the remaining classes as follows:

> Order → Order
> Strokethis → OrderItem
> Sostanziosa → OrderStatus
> Invoice → Invoice
> Zapisyvalas → InvoiceItem
> Sosteniendo → InvoiceStatus
> Warehouse → Storehouse
> Tavarnelle → StoreProduct
> Product → Product
> Employee → Employee
> Document → Document

---

{% include warning.html content="avoid using [language keywords JavaScript](https://developer.mozilla.org/ru/docs/Web/JavaScript/Reference/Lexical_grammar#Ключевые_слова)and \"standard\" names of the [object model Ember.js](https://api.emberjs.com/ember/3.1) as class names (for example, if the class a Stock not to call a Storehouse and Store, then when you run Ember-app may conflict with the name of the service store from the library, Ember Data in the relevant parts of the code)." %}

**As a result** of this step you should get the following diagram:

![Chart with the English names of the classes](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/4.png)

In order for the user interface of the application names of the classes displayed in the Russian language, you need to specify the Caption property of the class. In order to do this, run:

_`Order → [PTP] → ORM: Edit свойства`_

![Customize header class](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/5.png)

We see that the corresponding name in the Russian language has already been specified. It happened because we created classes in English and Russian, and when you create classes automatically have been stamped with the values of the properties "Description" And "Caption".

If you'll refer to classes initially on English language, do not forget to specify the importance of their headers in the property "Caption".

## Renaming attributes

Rename the attributes, for example of the Order class (Order). To do this, perform the following steps:

_`Order → [PTP] → ORM: Edit properties → Атрибуты`_

In the opened window, change the fields as follows:

![Tab redaktirovaniya attributes](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/6.png)

> Status → Status
> Datatrust → ShipmentDate
> Dataplate → PaymentDate
> Price → TotalSum

{% include important.html content="since we changed the name of an enumeration \"of Statusstate\" on \"OrderStatus\", we also need to change the type of the Status attribute in the Order class. Similar changes will need to be done in class Invoice (Invoice).
Class attributes can be edited directly on the chart." %}

{% include warning.html content="If classes were previously defined views that include the renamed attributes, you must also correct and appropriate representation. More work with representations, we consider in the following sections." %}

---

**Samostoyatelno** rename the attributes of the remaining classes as follows:

**Order**

> Undo() → Cancel() \*

**OrderItem**

> Number → Amount
> Zeynaloglu → PriceWTaxes
> Sum → TotalSum

**Invoice**

> Status → Status
> Maturemature → ShipmentDateTime
> Sum → TotalSum
> Weight → TotalWeight
Note → Note
> Hypolactasia → CustomerName
> **Especially**: Sosteniendo → InvoiceStatus

**InvoiceItem**

> Number → Amount
> Weight → Weight
> Price → Price
> Sum → TotalSum

**Storehouse**

> Number → Number
> Address → Address

**StoreProduct**

> Number → Amount

**Product**

> The ProductID → ProductCode
> Name → Name
> Edenization → Measure
> Description → Description
> Price → Price

**Employee**

> Tabernulae → Number
> Name → LastName
> Name → FirstName
> Middle Name → MiddleName
> Phone → PhoneNumber
> EMail → Email

**Document**

> Number → Number
> Datatypename → CreateDate

---

Podskazka:
rename the method directly on the chart or on the corresponding tab in the class properties:

_`Order → [PTP] → ORM: Edit properties → Методы`_

## Renaming roles of associations

Next to the Employee class (Employee) lists the name of roles for associations, svadhyaya class Worker with other classes, because different relations the role of the Worker is different. Their names also need to change:

> Materialinnovationen → ResponsiblePerson
> Storekeeper → Storekeeper
> Manager → Manager

## Localization listings

Classes with the stereotype "enumeration" are a special data type is a fixed set of values that you specify for the properties of different classes. In order to rename them, perform the following steps:

_`OrderStatus → [PTP] → ORM: Edit properties → Possible значения`_

For internal application experience required in the field **Name** enter **English names**. However, that enum values are displayed in the app in Russian, let's get **Russian names** in the column **Caption**:

> New → New
> Paid → Paid
> Cancelled → Canceled

![Settings tab enumerations](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/7.png)

---

**Samostoyatelno** rename the values of other enums in the following way:

**InvoiceStatus**

> New → New
> Issued → Issued
> Suspended → Paused
> Ready → Ready
> Shipped → Shipped

---

## Refinement of the model structure

Will introduce more changes to the class diagram to more accurately describe the subject area:

(1) Add the Weight of the double type in the Product class

![New Weight field in the Product class](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/8.png)

This field is necessary in order to describe weight per unit of a particular product. Thus, the weight on an invoice line may further be automatically calculated based on the weight of the item and its quantity.

(2) will Create a transfer PositionList (enumeration) values:

> Storekeeper → Storekeeper
> Manager → Manager

![Add listing](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/9.png)

Then also, we assume that responsible entities will be storekeepers.

(3) Add the **Position** (in the value position) type **PositionList** the Employee class

![New field Position type PositionList class Empoyee](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/10.png)

It is necessary because of a specific employee take on a specific job, and we should consider it in order to filter the list of employees according to their positions.

## Summary

The result of these changes, the class diagram should take the following form:

![Summary of the renaming and dorabotki of the model structure](/images/pages/guides/flexberry-ember/1-setting-language-and-structure/11.png)

It should be noted that the translation of Russian names in the domain model into English does not always apply. This approach applies in the case when the whole team is able to develop in the English language. If the whole team is not fluent in English at a sufficient level, instead of a full translation sometimes use transliteration when words are simply written in Roman letters, for example:

`Заказ - Order - Zakaz`

The choice of naming scheme is set by the project Manager or lead developer and is uniform for the whole team.

## Go

- [A practical guide «as I Do»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
- [Creating views, classes, forms, and applications](gpg_create-and-configure-application-structure.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}