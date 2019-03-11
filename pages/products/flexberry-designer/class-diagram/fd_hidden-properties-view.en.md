--- 
title: the Hidden properties in the view 
sidebar: flexberry-designer_sidebar 
keywords: Flexberry Desinger, View, view hidden properties, example 
summary: Features generation hidden properties in the view shown in the example 
toc: true 
permalink: en/fd_hidden-properties-view.html 
lang: en 
autotranslated: true 
hash: a5feea9e6ff5a8e6d8d0a22cc6f6f0a56bf195e53e448d61ef46bd5894f490a5 
--- 

The programmer can declare properties that fall into [view](fd_view-definition.html)» «hidden, then they will be in a [view](fd_view-definition.html), but will not be visible in the user interface. 

For declaring the hidden properties you want to initialize the property `Hidden` when specifying an attribute `View`. 

__Example__: 

```csharp
[View("Простое2", new string[]{"Name as Name",  "AnOtherAttrib"}, Hidden=new string[]{"AnOtherAttrib"})]
``` 

## Illustration on the example of comparison of different representations 

There are 3 different ideas: 

* in the first 2 properties and none of them will be hidden 
* in the second the same 2 properties, but hidden 1 
* the third will be only 1 feature (the same that remained "open" in the view number 2). 

Stage | 2 0 the hidden | hidden 2 total 1 | 1 total 0 hidden 
:------------------|:-----------------------------|:--------------------------|:---------------------------- 
Flexberry Desinger | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-view.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-view.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-view.png) 
Code | ``` [View("КлиентHidden1", new string[] {"Name", "Registration"})] ```| ``` [View("КлиентHidden2", new string[] {"Name", "Registration"}, Hidden=new string[] {"Registration"})] ```| ``` [View("КлиентHidden3", new string[] {"Name"})] ``` 
The edit form is | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-e.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-e.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-e.png) 
The list | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-l.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-l.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-l.png) 
Downloadable data | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-data.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-data.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-data.png) 

For example, it is clearly seen that 1 and 2 are identical in terms of uploaded data (line 5), and 2 and 3 are identical in terms of the user interface (lines 3 and 4)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}