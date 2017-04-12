---
title: Задание порядка отображения свойств в расширенном редакторе ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_prop-order-limit-editor.html
lang: ru
---

{% include important.html content="При использовании [Расширенного редактора ограничений](fa_advanced-limit-editor.html) в сессию записывается используемое [представление](fd_view-definition.html). Если после этого были изменены указанные ниже настройки, то сразу изменения могут не отображаться. Необходимо либо запустить редактор в другом браузере, либо перейти в private-режим, либо почистить записи в сессии." %}

## Задание порядка свойств

В [расширенном редакторе ограничений в web](fa_advanced-limit-editor.html) есть древовидная структура, представляющая собой свойства объекта, которые имеются в заданном представлении.

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/web-adv-limit-tree.png)

Можно повлиять на порядок отображения свойств. Делается это следующим образом: 

1. Формируется массив, задающий порядок, из:
	* собственных и мастеровых свойств объекта (к ним из [представления](fd_view-definition.html) можно получить доступ как `View.Properties`).
	* детейловых свойств объекта (к ним из представления можно получить доступ как `View.Details`).
	* [псевдодетейловых свойств](fo_linq-provider.html) (элементы такого рода нужно формировать особым образом, поддержка псевдодетейлов в расширенном редакторе ограничений на момент написания данной статьи дорабатывается).
2. Данным массивом означивается свойство в [WOLV'е](fa_web-object-list-view.html) `ViewPropertiesOrderedList` (по умолчанию оно равно `null`).

При инициализации расширенного редактора ограничений, если свойство `ViewPropertiesOrderedList` не `null`, ему будет передан указанный порядок.

Например,

```csharp
public partial class C__КредитL : BaseListForm<Кредит>
{
	// ...
	
	/// <summary>
	/// Вызывается самым первым в Page_Load.
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

{% include important.html content="Обратите внимание, что использовать `ViewPropertiesOrderedList` без `LimitResolvingViews` нельзя." %}

Или при наличии [псевдодетейлов](fa_details-limit-editor.html) используется структура [PseudoDetailInExtendedView](fa_pseudo-detail-extended-view.html):

```csharp
public partial class C__КлиентL : BaseListForm<Клиент>
{
	// ...
	
	/// <summary>
	/// Вызывается самым первым в Page_Load.
	/// </summary>
	protected override void Preload()
	{
		WebObjectListView1.Operations.LimitEdit = true;
		WebObjectListView1.LimitResolvingViews.AddRange(new View[] { Клиент.Views.КлиентE, Кредит.Views.КредитE });
		var viewPropertiesOrderedList = new ArrayList();
		viewPropertiesOrderedList.Add(View.Properties[1](ис-управления-проектами_1.html));
		viewPropertiesOrderedList.Add(View.Properties[0]);
		viewPropertiesOrderedList.AddRange(View.Details);
		viewPropertiesOrderedList.Add(new PseudoDetailInExtendedView(Кредит.Views.КредитE, Information.ExtractPropertyPath<Клиент>(x => x.ФИО), "Кредит_Клиент"));
		WebObjectListView1.ViewPropertiesOrderedList = viewPropertiesOrderedList;
	}
	
	// ...
}
```

## Автоматическое добавление свойств, используемых в ограничении (AutoAddUsedInLimitationProperties)

Набор свойств, доступных в [Расширенном редакторе ограничений](fa_advanced-limit-editor.html), и набор свойств [представления](fd_view-definition.html), по которому загружается список, могут отличаться. Это может привести к тому, что описанное пользователем ограничение будет несовместимо с [представлением](fd_view-definition.html) (в сформированном SQL-запросе в `where` будут использованы поля, отсутствующие в списке выбора).  Для автоматического расширения представления, используемого при загрузке списка, необходимо установить свойство `AutoAddUsedInLimitationProperties`.

Если свойство `AutoAddUsedInLimitationProperties` имеет значение `true`, то в работе будет использоваться [ViewPropertyAppender](fo_view-property-appender.html).
