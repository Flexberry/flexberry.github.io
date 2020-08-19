---
title: Интеграция с бизнес-сервером
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, сервис данных, бизнес-сервер
summary: Отработка пользовательских операций в процессе работы сервиса данных
toc: true
permalink: ru/fo_user-operations-dataservice.html
lang: ru
---

Если необходимо выполнить какие-либо действия в процессе обновления [сервисом данных](fo_data-service.html) хранилища, можно организовать обработку событий сервиса данных через специализированный [бизнес-сервер](fo_business-server.html).

Итак, чтобы организовать эту обработку, необходимо:

* Описать класс — бизнес-сервер, наследник от `ICSSoft.STORMNET.Business.BusinessServer`.
* Описать метод-перехватчик вида

```csharp
public DataObject[] OnUpdateXXXXX(XXXXX UpdatedObject)
```

где XXXXX — имя класса данных, чьи экземпляры обновляет [сервис данных](fo_data-service.html). Имплементировать этот метод (выполнить необходимые действия при обновлении).

## Указание бизнес-сервера классу данных

Прописать бизнес-сервер нужному классу данных `.Net`-атрибутом `BusinessServer`, одновременно указывая тип события.

Типы событий бывают следующие: 

* `DataServiceObjectEvents.OnAllEvents` — все события [сервиса данных](fo_data-service.html);
* `DataServiceObjectEvents.OnAnyEvent` — любые события [сервиса данных](fo_data-service.html) (неактуально при приписывании события, актуально при получении бизнес-сервера методом `BusinessServerProvider.GetBusinessServer`, можно получить бизнес-сервер без различий относительно типов событий, на которые он был подписан);
* `DataServiceObjectEvents.OnDeleteFromStorage` — [объект данных](fo_data-object.html) будет удалён [сервисом данных](fo_data-service.html);
* `DataServiceObjectEvents.OnInsertToStorage` — [объект данных](fo_data-object.html) будет создан в хранилище;
* `DataServiceObjectEvents.OnUpdateInStorage` — [объект данных](fo_data-object.html) будет обновлен в хранилище.

Типы событий можно комбинировать через `|`.

Сервис данных вызовет метод-перехватчик непосредственно перед выполнением соответствующей операции. Параметром методу придёт [объект данных](fo_data-object.html), над которым выполняется операция. Метод может вернуть какие-либо дополнительные объекты данных, которые будут «подхвачены» сервисом данных.

О том, зачем нужно возвращать [объекты данных](fo_data-object.html), а не выполнять отдельные вызовы сервиса данных прямо изнутри перехватчика. Ответ очевиден — для того, чтобы [обновление добавленных объектов выполнилось в той же транзакции](fo_bs-transact.html). Иначе, отдельный вызов сервиса данных — отдельная транзакция, соответственно, если код выполняется не под сервером транзакций (напр. `COM+`), будет разрыв.

Программист может узнать, какой [бизнес-сервер](fo_business-server.html) приписан [классу данных](fo_data-object.html) через провайдер бизнес-серверов, метод `BusinessServerProvider.GetBusinessServer`.

### Пример бизнес-сервера и класса данных с приписанным бизнес-сервером

```csharp
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

### Другие примеры

* [Проверка уникальности введенных данных в бизнес-сервере](fo_unique-data-check.html).
* [Пример использования бизнес-сервера](fo_bs-example.html).
* [Проверка валидности изменений данных объекта в бизнес-сервере](fo_change-data-check.html)
* [github.com](https://github.com/Flexberry/FlexberryORM-DemoApp/blob/master/FlexberryORM/CDLIB/BusinessServers/CDLibBS.cs).

## Особенности функционирования

[Сервис данных](fo_data-service.html) вызовет метод-перехватчик непосредственно перед выполнением соответствующей операции. Параметром методу придёт [объект данных](fo_data-object.html), над которым выполняется операция. Метод может вернуть какие-либо дополнительные объекты данных, которые будут «подхвачены» сервисом данных.

Нужно возвращать объекты данных, а не выполнять отдельные вызовы сервиса данных прямо изнутри перехватчика, чтобы [обновление добавленных объектов](fo_bs-transact.html) выполнилось в той же транзакции. Иначе, отдельный вызов сервиса данных — отдельная транзакция, соответственно, если код выполняется не под сервером транзакций (напр. `COM+`), будет разрыв.
