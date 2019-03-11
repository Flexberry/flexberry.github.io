--- 
title: Setting the display properties in the advanced editor limitations 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_prop-order-limit-editor.html 
lang: en 
autotranslated: true 
hash: e967d74b691d607339c6c91790657983b9f21e8d1ea97d71e9abd5215ce53dc8 
--- 

{% include important.html content="If you use [Advanced editor limitations](fa_advanced-limit-editor.html) in the session is recorded used a [view](fd_view-definition.html). If, after this was changed the following settings, then immediately changes may not be displayed. You must either start the editor in a different browser, or switch to private mode, or to clean entries in session." %} 

## set order of properties 

In the expanded constraint editor in the web](fa_advanced-limit-editor.html) a tree structure representing object properties that exist in a given view. 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/web-adv-limit-tree.png) 

You can affect the display order of properties. This is done as follows: 

1. Creates an array that specifies the order of: 
* own and artisans of the properties of the object (from [view](fd_view-definition.html) can be accessed as `View.Properties`). 
* metalowych object properties (from the view can be accessed as `View.Details`). 
* [pseudometallic properties](fo_linq-provider.html) (elements of this kind need to form a special way, the support of pseudometal in the advanced editor limitations at the time of this writing is being finalized). 
2. The array data indicated a property in [WOLV'e](fa_web-object-list-view.html) `ViewPropertiesOrderedList` (the default is `null`). 

When you initialize the advanced editor restrictions, if the property is not `ViewPropertiesOrderedList` `null`, he will be transferred to the specified order. 

For example, 

```csharp
public partial class C__КредитL : BaseListForm<Кредит>
{
	// ... 
	
	/// <summary> 
	/// Called the first in the Page_Load. 
	/// </summary> 
	protected override void Preload()
	{
		WebObjectListView1.Operations.LimitEdit = true;
		WebObjectListView1.LimitResolvingViews.AddRange(new View[] { Клиент.Views.C__КлиентE, Кредит.Views.C__КредитE });
		var viewPropertiesOrderedList = new ArrayList();
		viewPropertiesOrderedList.AddRange(Кредит.Views.C__КредитE.Properties);
		viewPropertiesOrderedList.AddRange(Кредит.Views.C__КредитE.Details);
		WebObjectListView1.ViewPropertiesOrderedList = viewPropertiesOrderedList;
	}
	
	// ... 
}
``` 

{% include important.html content="Please note that the use of `ViewPropertiesOrderedList` without `LimitResolvingViews` impossible." %} 

Or in the presence of [pseudometal](fa_details-limit-editor.html) structure is used [PseudoDetailInExtendedView](fa_pseudo-detail-extended-view.html): 

```csharp
public partial class C__КлиентL : BaseListForm<Клиент>
{
	// ... 
	
	/// <summary> 
	/// Called the first in the Page_Load. 
	/// </summary> 
	protected override void Preload()
	{
		WebObjectListView1.Operations.LimitEdit = true;
		WebObjectListView1.LimitResolvingViews.AddRange(new View[] { Клиент.Views.КлиентE, Кредит.Views.КредитE });
		var viewPropertiesOrderedList = new ArrayList();
		viewPropertiesOrderedList.Add(View.Properties[1](ис-управления-проектами_1.html));
		viewPropertiesOrderedList.Add(View.Properties[0]);
		viewPropertiesOrderedList.AddRange(View.Details);
		viewPropertiesOrderedList.Add(new PseudoDetailInExtendedView(Кредит.Views.КредитE, Information.ExtractPropertyPath<Клиент>(x => x.ФИО), "Kreditkort"));
		WebObjectListView1.ViewPropertiesOrderedList = viewPropertiesOrderedList;
	}
	
	// ... 
}
``` 

## Automatically add the properties used in the restriction (AutoAddUsedInLimitationProperties) 

The set of properties available in the Expanded constraint editor](fa_advanced-limit-editor.html), and a set of properties [view](fd_view-definition.html), which loads the list may vary. This can lead to what is described by the user limit is incompatible with [the view](fd_view-definition.html) (in the generated SQL query will be used in `where` missing fields in the select list). To automatically expand the view that is used when loading the list, you must set the property `AutoAddUsedInLimitationProperties`. 

If the property `AutoAddUsedInLimitationProperties` matters `true`, will be used [ViewPropertyAppender](fo_view-property-appender.html). 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}