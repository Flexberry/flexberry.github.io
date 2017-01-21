---
title: Как задать порядок отображения свойств в расширенном редакторе ограничений в web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_set-prop-order-at-web-adv-limit-editor.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Расширенный редактор ограничений](advanced-limit-editor.html).
* '''Предназначение''': возможность формирования условий с параметрами в [Расширенном редакторе ограничений](advanced-limit-editor.html).
</td>
</tr></tbody></table></a>
</div>



# Расширенный редактор ограничений
Эта статья описывает часть информации о [Расширенном редакторе ограничений](advanced-limit-editor.html).

(((<msg type=important head='Применение изменений'>При использовании [Расширенного редактора ограничений](advanced-limit-editor.html) в сессию записывается используемое [представление](view-definition.html). Если после этого были изменены указанные ниже настройки, то сразу изменения могут не отображаться. Необходимо либо запустить редактор в другом браузере, либо перейти в private-режим, либо почистить записи в сессии.</msg>)))

# Задание порядка свойств
В [расширенном редакторе ограничений в web](advanced-limit-editor.html) есть древовидная структура, представляющая собой свойства объекта, которые имеются в заданном представлении.

![](/images/pages/img/page/SetPropOrderAtWebAdvLimitEditor/WebAdvLimitTree.png)

<!--В версии после 11.07.2013-->
Можно повлиять на порядок отображения свойств. Делается это следующим образом: 

# Формируется массив, задающий порядок, из:
** собственных и мастеровых свойств объекта (к ним из [представления](view-definition.html) можно получить доступ как `View.Properties`).
** детейловых свойств объекта (к ним из представления можно получить доступ как `View.Details`).
** [псевдодетейловых свойств](l-i-n-q-provider.html) (элементы такого рода нужно формировать особым образом, поддержка псевдодетейлов в расширенном редакторе ограничений на момент написания данной статьи дорабатывается).
# Данным массивом означивается свойство в [WOLV'е](web-object-list-view.html) `ViewPropertiesOrderedList` (по умолчанию оно равно `null`).

При инициализации расширенного редактора ограничений, если свойство `ViewPropertiesOrderedList` не `null`, ему будет передан указанный порядок.

Например,
```cs
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
(((<msg type=Important>Обратите внимание, что использовать `ViewPropertiesOrderedList` без `LimitResolvingViews` нельзя.</msg>)))

Или при наличии [псевдодетейлов](details-at-adv-limit-editor.html) используется структура [PseudoDetailInExtendedView](pseudo-detail-in-extended-view.html):
```cs
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
Набор свойств, доступных в [Расширенном редакторе ограничений](advanced-limit-editor.html), и набор свойств [представления](view-definition.html), по которому загружается список, могут отличаться. Это может привести к тому, что описанное пользователем ограничение будет несовместимо с [представлением](view-definition.html) (в сформированном SQL- запросе в where будут использованы поля, отсутствующие в списке выбора).  Для автоматического расширения представления, используемого при загрузке списка, необходимо установить свойство `AutoAddUsedInLimitationProperties`.

Если свойство `AutoAddUsedInLimitationProperties` имеет значение `true`, то в работе будет использоваться [ViewPropertyAppender](view-property-appender.html).
