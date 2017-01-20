---
title: Проверка валидности изменений данных объекта в бизнес-сервере
sidebar: product--sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public, БД, Бизнес-серверы
toc: true
permalink: ru/b-s-change-data-check.html
folder: product--folder
lang: ru
---

# Изменение данных объекта
В данной статье будет рассмотрен вопрос проверки возможности изменения каких-либо данных объекта, уже находящегося в базе. Бывают ситуации, когда можно допустить изменение какого-либо поля объекта, только если это удовлетворяет некоторым условиям относительно старого значения этого же поля. Однако, на момент попадания объекта в [бизнес-сервер](business--servers--wrapper--business--facade.html) (в метод [OnUpdate](otrabotka-polzovatelskih-operacii-v-processe-raboty-servisa-dannyh-integraciya-s-biznes-serverom.html)), информация о старом значении поля уже отсутствует.

Старое значение поля можно вычитать из базы, ведь измененный объект хранится в памяти приложения, но изменения еще не попали в базе => можно узнать старое значение поле, вычитав его из базы.

Важным моментом является необходимость вычитки объекта в '''отдельную переменную''', если вычитать данные в переменную `UpdatedObject`, то все изменения, внесенные в объект, потеряются (если есть потребность вычитать значение поля в тот же объект, то нужно правильно и аккуратно воспользоваться [дочиткой объектов](additional-loading-data-object.html)).

Рассмотрим на примере:

# Пример
![](/images/pages/img/Filters/FilterExDiagram.PNG)

В банке, в котором планируется использование этой системы, предусмотрено следующее правило: срок кредита, определенный для клиента при выдаче кредита, может быть продлен, но при этом обязательно увеличение суммы кредита (наложение штрафов). При этом, срок кредита не может быть уменьшен, а только увеличен.

# Создадим [бизнес-сервер](business--servers--wrapper--business--facade.html) для класса `Кредит` (если он еще не был создан).
# В методе [`OnUpdate`](b-s-example.html) напишем следующий код:

```
if (UpdatedObject.GetStatus() == ObjectStatus.Altered)
{
    var newValue = UpdatedObject;
    var ds = (MSSQLDataService)DataServiceProvider.DataService;
    var oldValue = ds.Query<Кредит>(Кредит.Views.КредитE).Where(k => k.__PrimaryKey == UpdatedObject.__PrimaryKey).First();

    if (newValue.СрокКредита > oldValue.СрокКредита && newValue.СуммаКредита <= oldValue.СуммаКредита)
        throw new Exception("При изменении срока кредита сумма кредита должна увеличиться.");

    if (newValue.СрокКредита < oldValue.СрокКредита)
        throw new Exception("Срок кредита не может уменьшиться.");
}
```

(((<msg type=note>`oldValue` можно [получить](Flexberry-s-q-l-query.html) и таким образом:
```
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Кредит), Кредит.Views.КредитE);
var ld = SQLWhereLanguageDef.LanguageDef;
lcs.LimitFunction = ld.GetFunction(ld.funcEQ, new VariableDef(ld.GuidType, "Клиент"), UpdatedObject.Клиент.__PrimaryKey);
var oldValue = DataServiceProvider.DataService.LoadObjects(lcs)[0] as Кредит;```
</msg>)))

(((<msg type=Important>Обратите внимание на проверку [статуса](object-status-and-loading-state.html) ```
UpdatedObject.GetStatus() == ObjectStatus.Altered
``` таким образом мы отсекаем варианты, когда объект создан или удален.</msg>)))

Таким образом мы можем проверять вводимые данные на основании уже существующих данных.


