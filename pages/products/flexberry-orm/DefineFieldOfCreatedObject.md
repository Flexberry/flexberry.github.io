---
title: Задание значения поля создаваемого объекта
sidebar: product--sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/define-field-of-created-object.html
folder: product--folder
lang: ru
---

(((Данная статья ещё редактируется)))

Если есть некий текущий объект, который должен быть указан по умолчанию при создании другого объекта, то можно переопределить метод `[Edit](form-interaction.html)`, где проверить, что [статус загрузки данных определён как `ObjectStatus.Created`](object-status-and-loading-state.html), после чего установить текущий объект в интересующее поле и [отразить изменения на форме с помощью `EditManager`](edit-manager-change.html). 
```cs
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