---
title: Прототипизация в Web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-data-object-prototyping.html
folder: products/flexberry-aspnet/
lang: ru
---


В версии шаблона после 08.10.2013 появилась возможность выполнять прототипизацию объекта, открываемого на редактирование.

# Как работает прототипизация в web
Если в параметрах GET-запроса на форму редактирования передано, помимо первичного ключа редактируемого объекта, что `Prototyping=true`, то система понимает, что необходимо будет создать новый объект на основе объекта с указанным первичным ключом.

# Особенности реализации
* Сама прототипизация выполняется при сохранении объекта с помощью метода `[Prototyping](data-object-prototype.html)`.
* На исходный объект выставляется блокировка, которая снимается при первом сохранении объекта-прототипа.

# WOLV и "создание на основе"
[WOLV](fa_web-object-list-view.html) предоставляет возможность выполнения прототипизации объекта. Для этого у соответствующего [WOLV](fa_web-object-list-view.html) необходимо включить операцию [NewByExampleInRow](fa_wolv-operations.html):

```csharp
public partial class КредитL : BaseListForm<Кредит>
{
	//...
	
	protected override void Preload()
	{
		WebObjectListView1.Operations.NewByExampleInRow = true;
	}
}
```

При этом в каждой строчке web-контрола появится дополнительная иконка, после нажатия на которую будет выполнено не редактирование выбранного объекта, а его прототипизация.
