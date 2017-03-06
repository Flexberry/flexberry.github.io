---
title: Отработка пользовательских операций в процессе работы сервиса данных (интеграция с бизнес-сервером)
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public, Бизнес-серверы
toc: true
permalink: ru/fo_testing-user-operations-dataservice.html
folder: products/flexberry-orm/
lang: ru
---
# Бизнес-сервер (BusinessServer)
## Общие сведения
Если необходимо выполнить какие-либо действия в процессе обновления [сервисом данных](data-service.html) хранилища, можно организовать обработку событий [сервиса данных](data-service.html) через специализированный [бизнес-сервер](business--servers--wrapper--business--facade.html).

Итак, чтобы организовать эту обработку, необходимо: 

# Описать класс — [бизнес-сервер](business--servers--wrapper--business--facade.html), наследник от `ICSSoft.STORMNET.Business.BusinessServer`.
# Описать метод-перехватчик вида ```
public DataObject[] OnUpdateXXXXX(XXXXX UpdatedObject)
```, где XXXXX — имя класса данных, чьи экземпляры обновляет [сервис данных](data-service.html). Имплементировать этот метод (выполнить необходимые Вам действия при обновлении).
# Прописать [бизнес-сервер](business--servers--wrapper--business--facade.html) нужному классу данных `.Net`-атрибутом `BusinessServer`, одновременно указывая тип события.


Типы событий бывают следующие: 

* `DataServiceObjectEvents.OnAllEvents` — все события [сервиса данных](data-service.html);
* `DataServiceObjectEvents.OnAnyEvent` — любые события [сервиса данных](data-service.html) (неактуально при приписывании события, актуально при получении бизнес-сервера методом `BusinessServerProvider.GetBusinessServer`, можно получить бизнес-сервер без различий относительно типов событий, на которые он был подписан);
* `DataServiceObjectEvents.OnDeleteFromStorage` — [объект данных](dataobject.html) будет удалён [сервисом данных](data-service.html);
* `DataServiceObjectEvents.OnInsertToStorage` — [объект данных](dataobject.html) будет создан в хранилище;
* `DataServiceObjectEvents.OnUpdateInStorage` — [объект данных](dataobject.html) будет обновлен в хранилище.


Типы событий можно комбинировать через |.

Сервис данных вызовет метод-перехватчик непосредственно перед выполнением соответствующей операции. Параметром методу придёт [объект данных](dataobject.html), над которым выполняется операция. Метод может вернуть какие-либо дополнительные объекты данных, которые будут «подхвачены» сервисом данных.

О том, зачем нужно возвращать [объекты данных](dataobject.html), а не выполнять отдельные вызовы сервиса данных прямо изнутри перехватчика. Ответ очевиден — для того, чтобы [обновление добавленных объектов выполнилось в той же транзакции](b-s-transact.html). Иначе, [UpdateObjectsOrdered|отдельный вызов сервиса данных — отдельная транзакция], соответственно, если код выполняется не под сервером транзакций (напр. `COM+`), будет разрыв.

Программист может узнать, какой [бизнес-сервер](business--servers--wrapper--business--facade.html) приписан [классу данных](dataobject.html) через провайдер бизнес-серверов, метод `BusinessServerProvider.GetBusinessServer`.

Пример [бизнес-сервера](business--servers--wrapper--business--facade.html) и класса данных с приписанным [бизнес-сервером](business--servers--wrapper--business--facade.html): 
```
public class DataServiceEventsServer:ICSSoft.STORMNET.Business.BusinessServer
	{
		public DataObject[] OnUpdateЖурнал(Журнал UpdatedObject)
		{
			Console.WriteLine("Поймали обновление журнала {0}." , UpdatedObject.Наименование);
			return new DataObject[0];
		}

	}

	[BusinessServer(typeof(DataServiceEventsServer), DataServiceObjectEvents.OnInsertToStorage)]
    // *** End programmer edit section *** (Журнал CustomAttributes)
    public class Журнал : Ресурс
{
		/*Что-то*/
}
```

## BusinessServer для интерфейсов
Можно прописать атрибут для привязки [бизнес-сервера](business--servers--wrapper--business--facade.html) не только к классу, но и к интерфейсу. В этом случае все объекты класса-наследника от этого интерфейса будут обрабатываться данным бизнес-сервером. Если указано несколько интерфейсов с бизнес серверами: 
```cs
public class Class2:Class3, Interface3
{
//...
}

public class Class1:Class2, Interface1, Interface2
{
//...
}
```

то порядок срабатывания будет таким: 

# бизнес-сервер, привязанный к Class3
# бизнес-сервер, привязанный к Interface3
# бизнес-сервер, привязанный к Class2
# бизнес-сервер, привязанный к Interface2
# бизнес-сервер, привязанный к Interface1
# бизнес-сервер, привязанный к Class1


Примечание: Если некоторый интерфейс встретится несколько раз, то [бизнес-сервер](business--servers--wrapper--business--facade.html) для него будет выполнен только один раз в соответствии с уровнем самого дальнего предка.


## Порядок срабатывания бизнес-серверов при наследовании
В случае наследования первыми отрабатывают [бизнес-сервера](business--servers--wrapper--business--facade.html) самых дальних предков, последовательно приближаясь к потомкам. [Бизнес-серверы, обёртки, бизнес-фасад](business--servers--wrapper--business--facade.html)


