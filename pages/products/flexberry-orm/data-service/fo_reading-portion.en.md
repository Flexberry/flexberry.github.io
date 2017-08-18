---
title: Порционное чтение
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: en/fo_reading-portion.html
folder: products/flexberry-orm/
lang: en
---

## Порционное чтение

Весьма удобной возможностью является чтение объектов данных порциями, по частям. Существует возможность вызвать операцию чтения таким образом, чтобы кроме порции объектов данных, [сервис данных](fo_data-service.html) возвратил некоторое состояние чтения. Передавая последующим операциям чтения это состояние, можно получать очередные порции. 

Для выполнения порционного чтения необходимо:
* Установить в [`LoadingCustomizationStruct`](fo_loading-customization-struct.html) дополнительно размер порции через свойство [`LoadingCustomizationStruct`](fo_loading-customization-struct.html).
* Вызвать метод чтения объектов с параметрами [`LoadingCustomizationStruct`](fo_loading-customization-struct.html) и состояния, получить состояние.
* Выполнять последующее дочитывание по состоянию.

``` csharp
IDataService ds = DataServiceProvider.DataService;
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Автор), Автор.Views.Главное);					
lcs.LoadingBufferSize = 1; // Размер порции.
object state = null; // Сюда вернётся состояние сервиса данных.
DataObject[) dataobjects = ds.LoadObjects(lcs, ref state); // Вызываем сервис данных, состояние запоминается.
prvPrintPortion(dataobjects); // Печатаем первую порцию.
while (dataobjects.Length &gt; 0) // Пока ещё что-то возвращается.
{
	Console.WriteLine("Нажмите Enter, чтобы прочитать очередную порцию авторов.");
	Console.ReadLine();
	dataobjects = ds.LoadObjects(ref state); // Прочитываем очередную порцию. Lcs уже не передаём.
	prvPrintPortion(dataobjects); // Печатаем очередную порцию.
} 

Console.WriteLine("Больше авторов нет. Конец.");
Console.Read();
```

{% include note.html content="Обратите внимание, что сигнатуры вызова метода `LoadObjects` __разные__. Для дочитывания данных используется вызов метода с 1 параметром `ref state`." %}
