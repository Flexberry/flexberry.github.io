---
title: Система уведомлений об обновлении объектов
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, уведомление, обновление объекта
summary: Механизм для выполнения логики на различных этапах жизненного цикла изменения данных
toc: true
permalink: ru/fo_object-update-notification.html
lang: ru
---

Система уведомлений об обновлении объектов предоставляет механизм для выполнения логики на различных этапах жизненного цикла изменения данных. Уведомления позволяют реагировать на события создания, изменения и удаления объектов на трёх уровнях:

- **INotifyUpdateObject** — на уровне целого объекта
- **INotifyUpdateProperty** — на уровне отдельных свойств объекта
- **INotifyUpdateObjects** — централизованный менеджер для нескольких объектов

## Уровни уведомлений

### INotifyUpdateObject

Интерфейс для уведомлений на уровне отдельного объекта:

```csharp
public interface INotifyUpdateObject
{
    void BeforeUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects);
    void AfterSuccessSqlUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects);
    void AfterSuccessUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects);
    void AfterCommitUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects);
    void AfterFailUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects);
}
```

### INotifyUpdateProperty

Интерфейс для уведомлений на уровне свойств объекта:

```csharp
public interface INotifyUpdateProperty
{
    void BeforeUpdateProperty(DataObject dataObject, ObjectStatus status, string propertyName, object oldValue, object newValue);
    void AfterSuccessSqlUpdateProperty(DataObject dataObject, ObjectStatus status, string propertyName, object oldValue, object newValue);
    void AfterSuccessUpdateProperty(DataObject dataObject, ObjectStatus status, string propertyName, object oldValue, object newValue);
    void AfterCommitUpdateProperty(DataObject dataObject, ObjectStatus status, string propertyName, object oldValue, object newValue);
    void AfterFailUpdateProperty(DataObject dataObject, ObjectStatus status, string propertyName, object oldValue, object newValue);
}
```

### INotifyUpdateObjects

Централизованный менеджер уведомлений:

```csharp
public interface INotifyUpdateObjects
{
    void BeforeUpdateObjects(Guid operationId, IDataService dataService, IDbTransaction transaction, IEnumerable<DataObject> dataObjects);
    void AfterSuccessSqlUpdateObjects(Guid operationId, IDataService dataService, IDbTransaction transaction, IEnumerable<DataObject> dataObjects);
    void AfterSuccessUpdateObjects(Guid operationId, IDataService dataService, IEnumerable<DataObject> dataObjects);
    void AfterCommitUpdateObjects(Guid operationId, IDataService dataService, IEnumerable<DataObject> dataObjects);
    void AfterFailUpdateObjects(Guid operationId, IDataService dataService, IEnumerable<DataObject> dataObjects);
    void CleanupStateStore(Guid operationId);
}
```

## Жизненный цикл обновления

### Успешное обновление

```text
BeforeUpdateObjects
    └── Начало обработки, сбор изменений

SQL-запросы (INSERT/UPDATE/DELETE)
    └── Выполнение запросов к базе данных

AfterSuccessSqlUpdateObjects
    └── Уведомление после SQL, но до фиксации

AfterSuccessUpdateObjects
    └── Уведомление после обработки

COMMIT TRANSACTION
    └── фиксация изменений в базе

AfterCommitUpdateObjects
    └── уведомление после гарантии сохранения

CleanupStateStore
    └── Очистка памяти
```

### Обновление с ошибкой

```text
BeforeUpdateObjects
    └── Начало обработки

SQL-запросы (INSERT/UPDATE/DELETE)
    └── Выполнение запросов к базе данных

ROLLBACK TRANSACTION
    └── откат транзакции

AfterFailUpdateObjects
    └── Уведомление о провале

CleanupStateStore
    └── Очистка памяти (предотвращение утечек)
```

## Сценарии использования

### Отправка уведомлений

После успешного коммита можно безопасно отправлять email, SMS или push-уведомления:

```csharp
public class Order : DataObject, INotifyUpdateObject
{
    public string Email { get; set; }
    public decimal Amount { get; set; }

    public void AfterCommitUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects)
    {
        var order = (Order)dataObject;
        
        if (status == ObjectStatus.Created || status == ObjectStatus.Altered)
        {
            var emailService = DependencyResolver.Current.GetService<IEmailService>();
            emailService.SendOrderConfirmation(order.Email, order.Amount);
        }
    }
}
```

### Интеграция с внешними системами

Вызов внешнего API после гарантированной записи в базу:

```csharp
public class Product : DataObject, INotifyUpdateObject
{
    public string ExternalId { get; set; }
    public string Name { get; set; }

    public void AfterCommitUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects)
    {
        var product = (Product)dataObject;
        
        var syncService = DependencyResolver.Current.GetService<IExternalCatalogService>();
        
        switch (status)
        {
            case ObjectStatus.Created:
                syncService.CreateProduct(product.Id, product.Name);
                break;
            case ObjectStatus.Altered:
                syncService.UpdateProduct(product.Id, product.Name);
                break;
            case ObjectStatus.Deleted:
                syncService.DeleteProduct(product.Id);
                break;
        }
    }
}
```

### Сброс кэша

Инвалидация кэша только после фиксации изменений:

```csharp
public class Customer : DataObject, INotifyUpdateObject
{
    public string Name { get; set; }

    public void AfterCommitUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects)
    {
        var cache = DependencyResolver.Current.GetService<IMemoryCache>();
        cache.Remove($"customer_{dataObject.__PrimaryKey}");
    }
}
```

### Уровень свойств

Для свойств с типом, реализующим `INotifyUpdateProperty`:

```csharp
public class ComplexProperty : INotifyUpdateProperty
{
    public string Value { get; set; }

    public void AfterCommitUpdateProperty(
        DataObject dataObject, 
        ObjectStatus status, 
        string propertyName, 
        object oldValue, 
        object newValue)
    {
        var complexProp = (ComplexProperty)newValue;
        Logger.Info($"Property {propertyName} changed to {complexProp.Value}");
    }
}
```

## Очистка памяти

Метод `CleanupStateStore` вызывается при ошибках для удаления внутреннего состояния операции. Это предотвращает утечки памяти:

```csharp
public virtual void CleanupStateStore(Guid operationId)
{
    stateStore.Remove(operationId);
}
```

## Централизованный менеджер уведомлений

Менеджер уведомлений настраивается через `NotifierUpdateObjects`:

```csharp
var dataService = new SQLDataService();
dataService.NotifierUpdateObjects = new NotifierUpdateObjects();
```

Для обработки свойств можно использовать пользовательскую реализацию:

```csharp
dataService.NotifierUpdateObjects = new NotifierUpdateObjects(
    new CustomPropertyNotifier());
```

## Обработка ошибок

В post-commit методах исключения не выбрасывают — транзакция уже зафиксирована. Обработка ошибок выполняется через try/catch:

```csharp
public void AfterCommitUpdateObject(DataObject dataObject, ObjectStatus status, IEnumerable<DataObject> dataObjects)
{
    try
    {
        SendEmail(...);
    }
    catch (Exception ex)
    {
        logger.Error("Failed to send email after commit", ex);
    }
}
```

## Статусы объектов

Состояние объекта передаётся через `ObjectStatus`:

- **Created** — объект создан
- **Altered** — объект изменён
- **Deleted** — объект удалён

Различие операций возможно по значению статуса:

```csharp
if (status == ObjectStatus.Deleted)
{
    // Обработка удаления
}
```

## Принципы работы

### Внутреннее хранилище состояний

Внутреннее хранилище (`stateStore`) хранит состояние изменений для каждой операции:

- По `operationId` — идентификатор операции
- По `Type` — тип объекта
- По `PrimaryKey` — первичный ключ объекта
- По `PropertyName` — имя свойства

Каждое хранимое значение — `Tuple<ObjectStatus, object, object>`: статус, старое и новое значение.

### Очерёдность вызовов

1. **BeforeUpdateObjects** — сбор изменений до SQL-запросов
2. **AfterSuccessSqlUpdateObjects** — после выполнения SQL, до COMMIT
3. **AfterSuccessUpdateObjects** — после обработки, до COMMIT
4. **AfterCommitUpdateObjects** — после гарантированной фиксации
5. **AfterFailUpdateObjects** — при откате транзакции

## Уведомления в системе

Система уведомлений интегрирована в `SQLDataService` и автоматически вызывает соответствующие методы на всех этапах жизненного цикла обновления. Ключевые компоненты:

- **NotifierUpdateObjects** — стандартная реализация `INotifyUpdateObjects`
- **DbTransactionWrapper** — оборачивает транзакцию, отслеживает идентификаторы операций
- **StateStore** — внутреннее хранилище состояний

## Рекомендации

Поведение методов уведомлений регулируется следующими принципами:

- **AfterCommitUpdateObjects** — для действий, требующих гарантии сохранения (email, API, логи)

- **AfterSuccessUpdateObjects** — для операций без требования коммита (внутренняя валидация, кэширование на уровне приложения)

- Длительные операции в post-commit методах избегают

- Ошибки в post-commit обрабатывают через try/catch; исключения не выбрасывают — транзакция уже зафиксирована

- Уведомления могут приходить для удалённых объектов; статус проверяют через `ObjectStatus`
