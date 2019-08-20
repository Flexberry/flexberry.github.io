---
title: Adaptive representations for detailov
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, views
summary: the Purpose and rules of using adaptive detailov
toc: true
permalink: en/fo_adaptive-views-details.html
---

For example, you have the following situation:

![](/images/pages/products/flexberry-orm/views/adaptive-views-for-details.jpg)

Class a is detaily D associated aggregation of DA. For A specified [view](fd_view-definition.html), which is connected with the idea of detail D.

For example, you are reading a data object of type A on presentation `AView`. Accordingly, as objects of classes D1, D2 [legacy](fd_inheritance.html) from the D, they will also be read by performance `DView` (views are inherited). However, what to do if they have a better attribute composition, which obyazatelno needs pracitce?

In order to solve the problem, there are **adaptive** performance. If you declare the view `DView` adaptive and datalow D1 and D2 to announce the submission of the same names (`DView`), but with its own attribute structure, then the [service data](fo_data-service.html) will read detaily D1 and D2 in accordance with it.

In order to specify that the view is adaptive, it is necessary for the Association metalowego view in the attribute [AssociatedDetailViewAttribute](fd_view-definition.html) to initialize the property `UseAdaptiveViewsLoading=true`.

Example:

```csharp
[AssociatedDetailView("AView", "D", "DView", UseAdaptiveViewsLoading=true)]
```

{% include note.html content="should Not be overused adaptive representations, because it affects the performance. In many cases, it is better to separately read Decalogue data objects."%}

Implementation of the proofreading and editing of objects-successors as a General representation of the ancestor described in the article [Read belonging to different object classes in a single view](fo_reading-several-types-objects.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}