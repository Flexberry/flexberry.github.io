---
title: Задание значения поля создаваемого объекта
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM
summary: Пример задания значения по умолчанию при создании объекта
toc: true
permalink: ru/fo_define-field-created.html
lang: ru
---

Если есть некий текущий объект, который должен быть указан по умолчанию при создании другого объекта, то можно переопределить метод [Edit](fw_form-interaction.html), где проверить, что [статус загрузки данных определён как `ObjectStatus.Created`](fo_object-status.html), после чего установить текущий объект в интересующее поле и [отразить изменения на форме с помощью `EditManager`](fw_editmanager.html).

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
