---
title: Задание значения поля создаваемого объекта
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: en/fo_define-field-of-created-object.html
---

(((Данная статья ещё редактируется)))

Если есть некий текущий объект, который должен быть указан по умолчанию при создании другого объекта, то можно переопределить метод [`Edit`](fw_form-interaction.html), где проверить, что [статус загрузки данных определён как `ObjectStatus.Created`](fo_object-status-and-loading-state.html), после чего установить текущий объект в интересующее поле и [отразить изменения на форме с помощью `EditManager`](fw_edit-manager-change.html).

```csharp
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
	base.Edit(dataobject, contpath, propertyname, tag); //вызов базового метода
	if (dataobject != null)
	{
		if (dataobject.GetStatus() == ObjectStatus.Created) //проверка, что объект ещё не сохранялся
		{
			((Покупка)dataobject).МагазинПокупки = Магазин.CurrentShop; //задание некоего текущего объекта
			EditManager.Change("Магазин"); //отображение изменений на форме
		}
	}
}
```
----