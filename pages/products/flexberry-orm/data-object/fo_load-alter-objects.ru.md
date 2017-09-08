---
title: Пример загрузки и изменения объекта
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, объекты данных, пример
summary: Пример загрузки объекта данных по представлению, его изменения и сохранения
toc: true
permalink: ru/fo_load-alter-objects.html
lang: ru
---

В данном примере выполняются следующие действия:

* Берется [первичный ключ](fo_primary-keys-objects.html) какого-либо из имеющихся в БД объектов класса `CDDA`.
* Создается новый экземпляр класса `CDDA`, ему присваивается этот [первичный ключ](fo_primary-keys-objects.html).
* Производится загрузка свойств этого экземпляра посредством [сервиса данных](fo_data-service.html). Значения загружаются из записи, соответствующей установленному [первичному ключу](fo_primary-keys-objects.html).
  При загрузке применяется [представление](fd_view-definition.html).
* Выполняется изменение свойств и сохранение объекта.

```csharp
Console.WriteLine("2. How to load dataobject in specific view, change it\'s property, then persist. Object status and loading state.");

IDataService dataService = DataServiceProvider.DataService;

// Инициализация вспомогательного объекта, с помощью которого мы получим первичный ключ произвольного объекта
// заданного типа.
OrmSample ormSample = new OrmSample(dataService);
object primaryKey = ormSample.GetSomeObjectPrimaryKey(typeof(CDDA));

// Для загрузки объекта данных необходимо создать новый экземпляр объекта данных, присвоить ему имеющийся первичный ключ, а затем
// передать его в метод LoadObject сервиса данных. При этом запись с таким первичным ключом должна существовать в БД.
CDDA cdda = new CDDA();
cdda.SetExistObjectPrimaryKey(primaryKey);

// Кроме самого экземпляра, свойства которого необходимо загрузить, в метод LoadObject переадается представление.
// Представление - это набор свойств объекта. Представления можно создать в Flexberry, либо с помощью атрибута ViewAttribute.
// В данном случае представление определяет, какие свойства будут загружены.
dataService.LoadObject(CDDA.Views.CDDA_E, cdda);

// После загрузки статус объекта (cdda.GetStatus()) равен ObjectStatus.UnAltered. После вызова следующей строки он изменится на ObjectStatus.Altered.
// Изменять можно только загруженные свойства. Иначе при сохранении объекта получим ошибку.
cdda.Name = "Blablabla " + DateTime.Now;

Stopwatch stopwatch = new Stopwatch();
stopwatch.Start();

// Сохранение объекта. Обновляются только загруженные свойства.
dataService.UpdateObject(cdda);
            
stopwatch.Stop();
Console.WriteLine("Time taken for persistence: {0} ms.", stopwatch.ElapsedMilliseconds);
```
