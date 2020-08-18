---
title: Пример действий при сохранении объекта
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM
summary: Дополнительные действия при работе с объектом данных в приложении
toc: true
permalink: ru/fo_actions-saving-object.html
lang: ru
---

Часто в приложениях возникает необходимость выполнения каких-либо дополнительных действий при добавлении, изменении или удалении объектов данных, например, создание записи в логе приложения.
Во [Flexberry ORM](fo_flexberry-orm.html) эта возможность реализуется с помощью классов, называемых [бизнес-серверами](fo_business-server.html). Такие классы должны содержать методы, названные определенным образом, для выполнения действий на нужном пользователю этапе.

```csharp
Console.WriteLine("4. How to do something at persistence moment.");

IDataService dataService = DataServiceProvider.DataService;
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

// Как сделать что-то в момент сохранения:
// 1. Задать для сущности клас бизнес-сервера в Flexberry или с помощью .Net-атрибута BusinessServer (см. класс CDDA в проекте CDLIB(Objects)).
// 2. Сгенерируйте из Flexberry или создайте самостоятельно класс бизнес-сервера с методом, обрабатывающим сохранение объектов, и реализуйте его 
//    (см. класс CDLibBS в проекте CDLIB(BusinessServers))
// 3. Произведите какие-либо действия с объектом и сохраните его.
CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);
            
Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();
            
dataService.LoadObject(CDDA.Views.CDDA_E, cdda);
cdda.Name = "Huh! " + DateTime.Now;
dataService.UpdateObject(cdda);
stopwatch.Stop();
Console.WriteLine("Time taken for loading and persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
```

Пример [метода бизнес-сервера, срабатывающего при обновлении объекта данных](fo_user-operations-dataservice.html) класса `CDDA`:

```csharp
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateCDDA(IIS.CDLIB.CDDA UpdatedObject)
{
    // Изменение свойств сохраняемого объекта.
    UpdatedObject.TotalTracks = UpdatedObject.Track.Count;

    // В возвращаемом значении содержится массив объектов, которые нужно обновить, помимо UpdatedObject.
    // Содержимое зависит от бизнес-логики. Если ничего больше обновлять не требуется, возвращается пустой массив.
    return new ICSSoft.STORMNET.DataObject[0];
}
```
