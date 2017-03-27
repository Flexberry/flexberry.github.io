---
title: Настройка параметров чтения (LoadingCustomizationStruct)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_loading-customization-struct.html
folder: products/flexberry-orm/
lang: ru
---

## LoadingCustomizationStruct

`LoadingCustomizationStruct` позволяет полностью настроить параметры [сервиса данных](fo_construction-data-service.html) для операций чтения объектов данных.

Удобно создавать структуру `LoadingCustomizationStruct` с помощью статического метода `GetSimpleStruct`.

``` csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Шапка), "ШапкаE");
```

## Параметры `LoadingCustomizationStruct`

`LoadingCustomizationStruct` позволяет настраивать следующие параметры чтения:

* `View` — указывает [представление](fd_view-definition.html), в соответствии с которым будет выполнено чтение;
* `ReturnTop` — указание количества возвращаемых записей;
* `LoadingTypes` — указывает классы данных, чьи экземпляры требуется читать ([пример](fo_reading-several-types-objects.html));
* `ColumnsSort` — параметры сортировки возвращаемых результатов;
* `ColumnsOrder` — порядок следования свойств объекта данных в результирующей строке с разделителями, актуально, если для чтения используется метод [сервиса данных](fo_construction-data-service.html) `LoadStringedObjectView`.
* `InitDataCopy` — включение или отключение инициализации [копии данных](fo_data-object-copy.html) при вычитке данных.
* `LimitFunction` — [ограничение на зачитываемые объекты данных](fo_limit-function.html), с сервера будут возвращены только те объекты, что удовлетворяют данному ограничению;
* `Distinct` — получение различных данных (используется, например, с методом [LoadRawValues SQLDataService](fo_standard-data-services.html)).
* `LoadingBufferSize` — размер порции при [порционном чтении](fo_reading-portion.html);
* `RowNumber` — указание диапазона индексов загружаемых объектов, `lcs.RowNumber = new RowNumberDef(2, 5);` (удобно, например, для постраничного вывода).

<div markdown="span" class="alert alert-info" role="alert"><i class="fa fa-info-circle"></i> <b>Представление и ограничение':</b> Представление `View` должно содержать в себе все свойства, которые используются в ограничении `LimitFunction`, чтобы не произошло ошибки или неверного счёта. Если есть потребность расширить [представление](fd_view-definition.html) в соответствии с функцией ограничения `LimitFunction`, можно воспользоваться специальным классом [ViewPropertyAppender](fo_view-property-appender.html).</div>


## Применение `LoadingCustomizationStruct`

Чтобы вычитать набор данных из базы в память, необходимо

1. Создать объект типа ICSSoft.STORMNET.Business.LoadingCustomizationStruct
``` csharp
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(тип, представление);
```
2. возможно вручную установить [представление](fd_view-definition.html)
``` csharp
lcs.View = Клиент.Views.КлиентE;
```
3. Установить тип вычитываемых объектов 
``` csharp
lcs.LoadingTypes=new Type[) {typeof(Кредит)};
```
4. Наложить [ограничение](fo_limit-function.html) на вычитываемые объекты (если ограничение не будет наложено - вычитаются все объекты такого типа)
``` csharp
lcs.LimitFunction = <Объект типа ICSSoft.STORMNET.FunctionalLanguage.Function>
```
5. Настроить другие параметры чтения (не обязательно)
6. Сделать запрос к серверу, используя метод [`LoadObjects` сервиса данных](fo_data-service.html) 
``` csharp
var credits = DataServiceProvider.DataService.LoadObjects(lcs).Cast<Кредит>();
```

## Пример

Пример использования `LoadingCustomizationStruct` доступен по адресу: [https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/CDADMTEST/Form1.cs]().

