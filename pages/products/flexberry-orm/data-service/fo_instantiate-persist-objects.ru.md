---
title: Создание и сохранение объектов данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, база данных, сервис данных, пример
summary: Пример создания объектов данных и сохранение их в БД
toc: true
permalink: ru/fo_instantiate-persist-objects.html
lang: ru
---

В данном примере создается граф объектов данных и затем производится их сохранение в БД с помощью [сервиса данных](fo_data-service.html).

```csharp
Console.WriteLine("1. How to instantiate dataobjects and persist into DB.");

// Объекты данных создаются как и экземпляры других классов .Net - с помощью вызова конструктора.
Country country1 = new Country { Name = "Greece" };
Country country2 = new Country { Name = "USA" };
Country country3 = new Country { Name = "Ireland" };

Person person1 = new Person { LastName = "Johnson", FirstName = "John" };
Person person2 = new Person { LastName = "McLaren", FirstName = "Alice" };

Publisher publisher1 = new Publisher { Name = "First Publisher", Country = country1 };

Publisher publisher2 = new Publisher { Name = "Second Publisher", Country = country2 };

CDDA cdda = new CDDA
{
   Publisher = publisher1, Name = "Strange music", Price = new Dollar(0, 87)
};

// Добавление детейлов в объект данных.
cdda.Track.Add(new Track()
{
    Name = "My strange love",
    Author = person1,
    Singer = person2,
    Length = new Random().Next(100, 600)
});
cdda.Track.Add(new Track()
{
    Name = "Stupid is as stupid does",
    Author = person2,
    Singer = person1,
    Length = new Random().Next(100, 600)
});
cdda.Track.Add(new Track()
{
    Name = "Save my life",
    Author = person2,
    Singer = person1,
    Length = new Random().Next(100, 600)
});

CDDD cddd = new CDDD();
cddd.Publisher = publisher2;
cddd.Name = "Old software";
cddd.Capacity = 640;
cddd.Price = new Dollar(1, 52);

// Объекты данных, которые нужно сохранить, добавим в список.
List<ICSSoft.STORMNET.DataObject> objectsToUpdate = new List<ICSSoft.STORMNET.DataObject>();

for (int i = 0; i < 5; i++)
{
    DVD dvd = new DVD();
    dvd.Publisher = publisher1;
    dvd.Name = string.Format("Movie {0}", i);
    dvd.Capacity = i * 100;
    dvd.Price = new Dollar(2, 66);
    objectsToUpdate.Add(dvd);
}

// Добавление всех объектов, которые нужно обновить, в список для обновления.
objectsToUpdate.AddRange(new ICSSoft.STORMNET.DataObject[]
{
    country1, country2, country3, person1, person2, publisher1, publisher2, cdda, cddd
});

try
{
    ICSSoft.STORMNET.DataObject[] objectsToUpdateArray = objectsToUpdate.ToArray();

    Stopwatch stopwatch = new Stopwatch();
    stopwatch.Start();

    // DataServiceProvider.DataService создает сервис данных, тип которого задан в конфигурационном файле (ключи DataServiceType, CustomizationStrings в секции appSettings).
    // Flexberry ORM сохраняет объекты данных в соответствии с их состоянием. Зависимости между объектами обрабатываются автоматически. Все запросы выполняются в одной транзакции. 
    // Таким образом, один вызов UpdateObjects соответствует одной транзакции БД.
    // Кроме этого, существует метод UpdateObject для обновления одиночного объекта (и графа объектов данных, на которые он ссылается).
    DataServiceProvider.DataService.UpdateObjects(ref objectsToUpdateArray);

    stopwatch.Stop();
    Console.WriteLine("Time taken for persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
}
catch (Exception exc)
{
    Console.WriteLine("Something wrong: {0}", exc);
}
```
