---
title: Пример наложения ограничений на загрузку объектов
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Ограничения, пример
summary: Наложение ограничения, сортировка, диапазон индексов загружаемых объектов
toc: true
permalink: ru/fo_load-limitation-example.html
lang: ru
---

Полный список примеров кода [Flexberry ORM](fo_flexberry-orm.html) находится в статье ["Примеры кода"](fo_code-samples.html).

## Пример наложения ограничения на загружаемые объекты данных (условия, количество и т.д.)

Обычно приложения не ограничиваются простой загрузкой объектов из БД: требуется сортировка, наложение условий на загружаемые объекты, на их индексы.
Для хранения и передачи сервису данных всей этой информации используется структура данных [`ICSSoft.STORMNET.Business.LoadingCustomizationStruct`](fo_loading-customization-struct.html).
В примере показано ее использование при загрузке объектов данных: наложение ограничения, сортировка, диапазон индексов загружаемых объектов.

```csharp
Console.WriteLine("3. How to load a set of dataobjects in specific view, limitation, quantity, etc..");

// Во Flexberry ORM есть разные наборы операций (языки) для задание ограничений. Самый простой - SQLWhereLanguageDef.
ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef ld =
    ICSSoft.STORMNET.FunctionalLanguage.SQLWhere.SQLWhereLanguageDef.LanguageDef;

// Создаем структуру, хранящую параметры загрузки объектов (представление, типы данных, ограничение и т.д.).
LoadingCustomizationStruct lcs = new LoadingCustomizationStruct(null);
lcs.View = CD.Views.CD_E;
lcs.LoadingTypes = new[] { typeof(CDDA), typeof(CDDD), typeof(DVD) };

// Ограничение, использующие свойства классов, на которые ссылается класс CS. Могут быть использованы те свойства, 
// которые указаны в представлении, по которому производится загрузка объектов.
lcs.LimitFunction = ld.GetFunction(ld.funcEQ,
    new VariableDef(ld.StringType, Information.ExtractPropertyPath<CD>(c => c.Publisher.Country.Name)), "USA");

// Параметры сортировки загружаемых объектов. Указать можно также только свойства, которые есть в представлении.
lcs.ColumnsSort = new[] { new ColumnsSortDef(Information.ExtractPropertyName<CD>(c => c.Name), ICSSoft.STORMNET.Business.SortOrder.Asc) };

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

// Можно указать диапазон индексов загружаемых объектов, что удобно, например, для постраничного вывода.
// lcs.RowNumber = new RowNumberDef(2, 5);
// Есть три основных метода для загрузки объектов:
// 1. Загрузить из БД записи и для каждой из них создать экземпляр объекта данных.
ICSSoft.STORMNET.DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);

// 2. Загрузить без создания экземпляров (каждый объект представлен в виде строки из значений свойств с разделителями). 
// Используется, когда не требуется редактирование объектов. Он намного быстрее!
ObjectStringDataView[] stringedview = DataServiceProvider.DataService.LoadStringedObjectView(';', lcs);

// 3. Получить количество объектов, без загрузки данных.
int iObjsCount = DataServiceProvider.DataService.GetObjectsCount(lcs);

stopwatch.Stop();
Console.WriteLine("Time taken for all loadings: {0} ms.", stopwatch.ElapsedMilliseconds);
```
