---
title: Пример: выполнение действий при сохранении объекта
sidebar: flexberry-orm_sidebar
keywords: Public, Sample, Черновик статьи
toc: true
permalink: ru/fo_data-object-update-hook-example.html
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

Полный список примеров кода [Flexberry ORM](flexberry-o-r-m.html) находится в статье ["Примеры кода"](code-samples.html).

</td>
</tr></tbody></table></a>
</div>

## Пример выполнения действий при сохранении объекта

Часто в приложениях возникает необходимость выполнения каких-либо дополнительных действий при добавлении, изменении или удалении объектов данных, например, создание записи в логе приложения.
Во [Flexberry ORM](flexberry-o-r-m.html) эта возможность реализуется с помощью классов, называемых [бизнес-серверами](business--servers--wrapper--business--facade.html). Такие классы должны содержать методы, названные определенным образом, для выполнения действий на нужном пользователю этапе.

```cs
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

Пример [метода бизнес-сервера, срабатывающего при обновлении объекта данных](otrabotka-polzovatelskih-operacii-v-processe-raboty-servisa-dannyh-integraciya-s-biznes-serverom.html) класса `CDDA`:

```cs
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateCDDA(IIS.CDLIB.CDDA UpdatedObject)
{
    // Изменение свойств сохраняемого объекта.
    UpdatedObject.TotalTracks = UpdatedObject.Track.Count;

    // В возвращаемом значении содержится массив объектов, которые нужно обновить, помимо UpdatedObject.
    // Содержимое зависит от бизнес-логики. Если ничего больше обновлять не требуется, возвращается пустой массив.
    return new ICSSoft.STORMNET.DataObject[0];
}
```
