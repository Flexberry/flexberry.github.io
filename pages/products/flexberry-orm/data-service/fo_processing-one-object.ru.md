---
title: Обработка одного объекта
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных
summary: Особенности обновления объекта данных с использованием сервиса данных
toc: true
permalink: ru/fo_processing-one-object.html
lang: ru
---

Для того чтобы обновить данные в [хранилище для объекта данных](fo_storing-data-objects.html), необходимо выполнить метод [сервиса данных](fo_data-service.html) `UpdateObject`.

Для того чтобы прочитать объект данных из хранилища по его [ключу](fo_primary-keys-objects.html) необходимо вызвать метод сервиса данных `LoadObject` (объект данных будет прочитан в [представлении](fd_view-definition.html), объявленном как «*»).

Ниже приведён пример, когда созданный объект данных создаётся в хранилище, а затем читается по [ключу](fo_primary-keys-objects.html).

```csharp
static void Main(string[) args)
{
	//Сохранение одного объекта
	Страна странакоторуюпишем = new Страна();
	странакоторуюпишем.Наименование="Россия";
	UpdateObject(странакоторуюпишем);
	Console.WriteLine("Конец сохранения");			
	//чтение одного объекта
	Страна странакоторуючитаем = new Страна();
	странакоторуючитаем.SetExistObjectPrimaryKey(странакоторуюпишем.__PrimaryKey);
	LoadObject(странакоторуючитаем);
	Console.WriteLine("Конец чтения, страна {0}", странакоторуючитаем.Наименование);			
	Console.Read();
}
private static void UpdateObject(DataObject dparam)
{
	IDataService ds = DataServiceProvider.DataService;			
	ds.UpdateObject(ref dparam);
}
private static void LoadObject(DataObject dparam)
{
	IDataService ds = DataServiceProvider.DataService;			
	ds.LoadObject(dparam);
}
```

Важно учитывать `.Net`-атрибут [AutoAltered](fo_object-status.html)! Если прочитан некоторый объект данных, изменены его свойства и выполняется обновление в хранилище, то если объект данных не является `AutoAltered`, не следует ждать от [сервиса данных](fo_data-service.html) обновления данных в хранилище без «ручной» установки этому объекту статуса `Altered`.
