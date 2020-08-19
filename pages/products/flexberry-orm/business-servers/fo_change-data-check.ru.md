---
title: Проверка валидности изменений данных объекта в бизнес-сервере
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, база данных, бизнес-серверы, пример
summary: Описание изменения объекта по заданным условиям
toc: true
permalink: ru/fo_change-data-check.html
lang: ru
---

## Изменение данных объекта

Бывают ситуации, когда можно допустить изменение какого-либо поля объекта, только если это удовлетворяет некоторым условиям относительно старого значения этого же поля. Однако, на момент попадания объекта в [бизнес-сервер](fo_business-server.html) (в метод [OnUpdate](fo_bs-example.html)), информация о старом значении поля уже отсутствует.

Старое значение поля можно узнать, вычитав его из базы, так как измененный объект хранится в памяти приложения, но изменения еще не попали в базу.

{% include important.html content="Важным моментом является необходимость вычитки объекта в `отдельную переменную`, поскольку если вычитать данные в переменную `UpdatedObject`, то все изменения, внесенные в объект, потеряются (если есть потребность вычитать значение поля в тот же объект, то нужно правильно и аккуратно воспользоваться [дочиткой объектов](fo_additional-loading.html))." %}

## Пример

![](/images/pages/products/flexberry-orm/business-servers/filter-ex-diagram.png)

В банке, в котором планируется использование этой системы, предусмотрено следующее правило: срок кредита, определенный для клиента при выдаче кредита, может быть продлен, но при этом обязательно увеличение суммы кредита (наложение штрафов). При этом, срок кредита не может быть уменьшен, а только увеличен.

* Создать бизнес-сервер для класса `Кредит` (если он еще не был создан).
* В методе `OnUpdate` напишем следующий код:

```csharp
if (UpdatedObject.GetStatus() == ObjectStatus.Altered)
{
    var newValue = UpdatedObject;
    var ds = (MSSQLDataService)DataService;
    var oldValue = ds.Query<Кредит>(Кредит.Views.КредитE).Where(k => k.__PrimaryKey == UpdatedObject.__PrimaryKey).First();

    if (newValue.СрокКредита > oldValue.СрокКредита && newValue.СуммаКредита <= oldValue.СуммаКредита)
        throw new Exception("При изменении срока кредита сумма кредита должна увеличиться.");

    if (newValue.СрокКредита < oldValue.СрокКредита)
        throw new Exception("Срок кредита не может уменьшиться.");
}
```

`oldValue` можно получить и таким образом:

```csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Кредит), Кредит.Views.КредитE);
lcs.LimitFunction = FunctionBuilder.BuildEquals<Кредит>(x => x.Клиент, UpdatedObject.Клиент);
var oldValue = DataService.LoadObjects(lcs)[0) as Кредит;
```

```csharp
UpdatedObject.GetStatus() == ObjectStatus.Altered
```

При такой проверке [статуса](fo_object-status.html) отсекаются варианты, когда объект создан или удален.

Таким образом мы можем проверять вводимые данные на основании уже существующих данных.