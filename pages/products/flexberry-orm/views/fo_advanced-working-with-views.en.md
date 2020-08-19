---
title: advanced Example of working with views
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, views
summary: Dynamic creation of views, operations predstavleniyami
toc: true
permalink: en/fo_advanced-working-with-views.html
---

## Dynamic creation of views

[View](fd_view-definition.html) can be created dynamically.

If you want to create a view "in code", on the fly, then fit one of the following options:

1. To create a view using the default constructor, then fill in the required properties

```csharp
// Create an empty view. 
ICSSoft.STORMNET.View dynaview = new ICSSoft.STORMNET.View();

// Specify the type of data object for which to create the view. 
dynaview.DefineClassType = typeof(CDDA); 

// You can add your own properties class and artisans of the properties of an array or one by one. 
// dynaview.AddProperty(...) 
dynaview.AddProperties(new string[] { "Name", "TotalTracks", "Publisher.Name" }); 

// Add the wizard to the view. 
dynaview.AddMasterInView("Publisher"); 

// You can also use the dynaview method.AddDetailInView to associate this view with metalowymi ideas. 
```

2. To create a dynamic view using ViewAttribute

```csharp
ICSSoft.STORMNET.View dynaview1 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView", new string[] { "Name", "Publisher.Name" }), 
    typeof(CDDA));
```

## Operations with representations

Each view acts as a [set of properties](fo_view-operations.html).

```csharp
ICSSoft.STORMNET.View view1 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView1", new string[] { "Name", "Publisher.Name" }), 
    typeof(CDDA));
ICSSoft.STORMNET.View view2 = new ICSSoft.STORMNET.View(
    new ViewAttribute("DynaView2", new string[] { "Name", "TotalTracks" }), 
    typeof(CDDA));

// a. Combined views 
// Result contains the properties of both representations ("Name", "Publisher.Name", "TotalTracks"); 
ICSSoft.STORMNET.View concatresult = (view1 | view2);

// b. The intersection of ideas 
// Result contains the common properties of the specified concepts ("Name"); 
ICSSoft.STORMNET.View intersectresult = (view1 & view2); 

// c. Subtraction ideas 
// Result contains the properties of view1, view2 which is not in ("Publisher.Name"); 
ICSSoft.STORMNET.View subtractsectresult = (view1 - view2); 

// d. Excluding the combined views 
// General properties of representations will be discarded (output "Publisher.Name", "TotalTracks"); 
ICSSoft.STORMNET.View xconcatresult = (view1 ^ view2); 
```

Full list of code examples Flexberry ORM is in ["code Examples"](fo_code-samples.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}