---
title: Бизнес-сервер (BusinessServer)
sidebar: flexberry-orm_sidebar
keywords: BS
summary: Базовые принципы разработки бизнес-сервера
toc: true
permalink: ru/fo_business-server.html
lang: ru
---

Бизнес-сервер есть набор методов. В `UML` бизнес-сервер отрисовывается [UML-классом с установленным атрибутом «businessserver»](fd_business-servers.html). Система может содержать произвольное число бизнес-серверов. В общем, число бизнес-серверов и состав их методов определяется прикладным разработчиком.

`Бизнес-сервер` — класс, непосредственно содержащий код бизнес-операций.

## Пример сгенерированного кода бизнес-сервера

Для каждого объекта данных, у которого на UML-диаграмме указан BusinesServer генерируется метод подобно следующему (в данном случае классу Car указан в соответствие бизнес-сервер CarBS):

```csharp
/// <summary>
/// CarBS.
/// </summary>
// *** Start programmer edit section *** (CarBS CustomAttributes)

// *** End programmer edit section *** (CarBS CustomAttributes)
[ICSSoft.STORMNET.AccessType(ICSSoft.STORMNET.AccessType.none)]
public class CarBS : ICSSoft.STORMNET.Business.BusinessServer
{
        
    // *** Start programmer edit section *** (CarBS CustomMembers)

    // *** End programmer edit section *** (CarBS CustomMembers)

    // *** Start programmer edit section *** (OnUpdateCar CustomAttributes)

    // *** End programmer edit section *** (OnUpdateCar CustomAttributes)
    public virtual ICSSoft.STORMNET.DataObject[] OnUpdateCar(NewPlatform.Flexberry.ORM.ODataService.Tests.Car UpdatedObject)
    {
        // *** Start programmer edit section *** (OnUpdateCar)
        var dObjs = new List<DataObject>();

        if (UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created)
        {
            UpdatedObject.Driver.CarCount++;

            UpdatedObject.Number =
                string.Format("{0}/{1}",
                    "TECT",
                    UpdatedObject.Driver.CarCount);

            dObjs.Add(UpdatedObject.Driver);
        }

        // Чтобы попасть в одну транзакцию объекты должны быть возвращены.
        return dObjs.ToArray();
        // *** End programmer edit section *** (OnUpdateCar)
    }
}
```

## Правила разработки бизнес-серверов

Класс бизнес-сервера наследуется от `ICSSoft.STORMNET.Business.BusinessServer`, который содержит свойства:

* `DataService` - сервис данных, на котором сработает этот бизнес-сервер.
* `ObjectsToUpdate` - "соседние" обновляемые объекты данной транзакции.
* `Order` - Упорядочение бизнес-серверов. 0 - выполнится раньше остальных, int.MaxValue выполнится последним. По-умолчанию: 0.

Допустим, выполняется операция обновления данных следующим образом:

```csharp
DataObject[] objectsCars = new DataObject[] { car1, car2 };
ds.UpdateObjects(ref objectsCars);
```

Показанный в примере метод `OnUpdateCar` будет вызван последовательно для объектов `car1` и `car2`. Через свойство `ObjectsToUpdate` можно получить доступ к массиву `objectsCars`, переданному в метод `UpdateObjects` сервиса данных. Это может быть полезным, если логика бизнес-сервера зависит от "соседних" объектов.

В бизнес-сервере в методе `OnUpdateCar` доступна инстанция сервиса данных `ds` через свойство `DataService`. При необходимости обращения к сервису данных из кода бизнес-сервера следует использовать именно эту инстанцию сервиса данных, поскольку приложения могут работать на нескольких сервисах данных и различных хранилищах. Получение сервиса данных из провайдера может повлечь ошибки прикладного характера. Также следует учитывать, что на момент вызова бизнес-сервера сервис данных ещё не выполняет SQL запросы, а только готовит данные для этого. Таким образом, обращаясь к хранилищу из метода бизнес-сервера мы наблюдаем хранилище в состоянии до изменений. Особенно осторожно нужно пользоваться операциями обновления данных, поскольку трензакция ещё не создана и изменение хранилища в бизнес-сервере может привести к неконсистентному состоянию, если сформированный SQL запрос после отработанных бизнес-серверов по какой-то причине завершится с ошибкой. Чтобы изменённые в бизнес-сервере объекты попали в общую транзакцию их нужно вернуть в методе бизнес-сервера.

Важно понимать, что объект `UpdatedObject` будет иметь ровно то состояние, которое было передано в `ds.UpdateObjects` плюс изменения в "соседних" бизнес-серверах. Хорошей практикой считается проверять загруженность свойств перед их изменением.

## Особенности использования

Дополнительная информация об особенностях использования бизнес-серверов доступна в следующих статьях:

* [Порядок вызова бизнес-серверов](fo_order-calls-bs.html)
* [Отработка пользовательских операций в процессе работы сервиса данных (интеграция с бизнес-сервером)](fo_user-operations-dataservice.html)
