--- 
title: Hierarchical WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-hierarhy.html 
lang: en 
autotranslated: true 
hash: 7cef92af4f8b13700e4124a437fc38da563d1eef2c6db7c00e820012385bbd4a 
--- 

Hierarchical WOLV is not a separate control, it is common [WebObjectListView](fa_web-object-list-view.html) that is configured in a special way. 

Hierarchical WOLV allows you to display hierarchical data. 

## Example 

An example of a chart: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhy-diagramm.png) 

### configure hierarchical WOLV 

Display a list of `Территории` in a hierarchical manner. 

1. Configure [L-view](fd_l-view.html) Territory. 
2. Kastrati WOLV. 

#### configuring L-view 

To WOLV earned, you want the property `Иерархия` fell in L-view. By default it includes only `Иерархия.Наименование`, you must add a reference to `Иерархию` and to remove from it the visibility to not show the user list on the form keys (`PrimaryKey`) Territories. 

#### setting WOLV 

Setting WOLV is to specify the hierarchical properties. In the code download page `ТерриторияL` method `PreLoad()` following code is added (enable hierarchical mode): 

```csharp
protected override void Preload()
{
    WebObjectListView1.HierarchyProperty = Hierarchy;
}
``` 

#### Result 

The panel added button to toggle the view WOLV: ![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhical-panel.png) 

If the button is pressed, the WOLV acquires the following form: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhical view.png) 

In this view WOLV'a (yet) missing Pager's, and add the buttons to collapse and expand the hierarchy. 

When you press the button WOLV acquires the usual form: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-simple-view.png) 

## displays the parents, not which a constraint 

Hierarchical WOLV supports the ability to display the hierarchy in the case of the restrictions imposed on parent elements by analogy with [OLV](fw_objectlistview.html) in Windows applications. 

To enable this mode requires the property `UseLimitFunctionExtension` corresponding instance WOLV assign a value `true`. 

The restriction on parent elements may be superimposed as in the WOLV-e and lucapa if WOLV in the hierarchical mode is used to select object on the form, raised on luckau (using [WOLVSettApplyer](fa_wolv-sett-applyer.html)). 





{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}