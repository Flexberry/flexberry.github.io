--- 
title: Creating and maintaining data objects 
sidebar: flexberry-orm_sidebar 
keywords: Flexberry ORM, database, data service example 
summary: an Example of creating data objects and storing them in the database 
toc: true 
permalink: en/fo_instantiate-persist-objects.html 
lang: en 
autotranslated: true 
hash: 414fd2500b9a449c9d1701753d19192c07825f5d3b908a18c0f1e8825ec00a13 
--- 

This example creates a graph of data objects, and then made their saving by using [data service](fo_data-service.html). 

```csharp
Console.WriteLine("1. How to instantiate dataobjects and persist into DB.");

// Data objects are created as instances of other classes .Net - by calling the constructor. 
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

// Add detailov in the data object. 
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

// Data objects that you want to keep, add to the list. 
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

// Add all the objects that you want to update in the list to update. 
objectsToUpdate.AddRange(new ICSSoft.STORMNET.DataObject[]
{
    country1, country2, country3, person1, person2, publisher1, publisher2, cdda, cddd
});

try
{
    ICSSoft.STORMNET.DataObject[] objectsToUpdateArray = objectsToUpdate.ToArray();

    Stopwatch stopwatch = new Stopwatch();
    stopwatch.Start();

    // DataServiceProvider.Creates a DataService the data service type specified in the configuration file (keys DataServiceType, CustomizationStrings in the appSettings section). 
    // Flexberry ORM saves the data objects according to their condition. Dependencies between objects are handled automatically. All queries executed in one transaction. 
    // Thus, a single call UpdateObjects corresponds to a single transaction database. 
    // Besides, there is a method UpdateObject to update a single object (and the object graph data to which it refers). 
    DataServiceProvider.DataService.UpdateObjects(ref objectsToUpdateArray);

    stopwatch.Stop();
    Console.WriteLine("Time taken for persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
}
catch (Exception exc)
{
    Console.WriteLine("Something wrong: {0}", exc);
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}