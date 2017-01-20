---
title: Прототипизация в Web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-data-object-prototyping.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Платформа''': [FlexberryASP.NET](flexberry-a-s-p-n-e-t.html).
* '''Компонент''': [WebObjectListView (WOLV)](web-object-list-view.html).
* '''Предназначение''': возможность выполнять прототипизацию объекта, открываемого на редактирование.
</td>
</tr></tbody></table></a>
</div>


В версии шаблона после 08.10.2013 появилась возможность выполнять прототипизацию объекта, открываемого на редактирование.

# Как работает прототипизация в web
Если в параметрах GET-запроса на форму редактирования передано, помимо первичного ключа редактируемого объекта, что `Prototyping=true`, то система понимает, что необходимо будет создать новый объект на основе объекта с указанным первичным ключом.

# Особенности реализации
* Сама прототипизация выполняется при сохранении объекта с помощью метода `[Prototyping](data-object-prototype.html)`.
* На исходный объект выставляется блокировка, которая снимается при первом сохранении объекта-прототипа.

# WOLV и "создание на основе"
[WOLV](web-object-list-view.html) предоставляет возможность выполнения прототипизации объекта. Для этого у соответствующего [WOLV](web-object-list-view.html) необходимо включить операцию `[NewByExampleInRow](w-o-l-v-operations.html)`:
```cs
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
