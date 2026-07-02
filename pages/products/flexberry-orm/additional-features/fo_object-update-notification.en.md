---
title: System of notifications about object updates
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, notification, object update
summary: Mechanism for executing logic at various stages of the data modification lifecycle
toc: true
permalink: en/fo_object-update-notification.html
lang: en
---

The system of notifications about object updates provides a mechanism for executing logic at various stages of the data modification lifecycle. Notifications allow you to react to events of creation, modification and deletion of objects at three levels:

- **INotifyUpdateObject** - at the level of the entire object
- **INotifyUpdateProperty** - at the level of individual object properties
- **INotifyUpdateObjects** - centralized manager for multiple objects

## Notification levels

### INotifyUpdateObject

Interface for notifications at the level of a separate object:

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

Interface for notifications at the level of object properties:

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

Centralized notification manager:

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

## Update lifecycle

### Successful update

```text
BeforeUpdateObjects
    └── Start processing, collect changes

SQL-queries (INSERT/UPDATE/DELETE)
    └── Execute queries to the database

AfterSuccessSqlUpdateObjects
    └── Notification after SQL, but before commit

AfterSuccessUpdateObjects
    └── Notification after processing

COMMIT TRANSACTION
    └── commit changes to the database

AfterCommitUpdateObjects
    └── notification after guaranteed saving

CleanupStateStore
    └── Memory cleanup
```

### Update with error

```text
BeforeUpdateObjects
    └── Start processing

SQL-queries (INSERT/UPDATE/DELETE)
    └── Execute queries to the database

ROLLBACK TRANSACTION
    └── rollback the transaction

AfterFailUpdateObjects
    └── Notification of failure

CleanupStateStore
    └── Memory cleanup (prevents leaks)
```

## Usage scenarios

### Sending notifications

After successful commit, you can safely send email, SMS or push notifications:

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

### Integration with external systems

Calling external API after guaranteed write to the database:

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

### Cache invalidation

Invalidate cache only after changes are committed:

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

### Property level

For properties of type that implements `INotifyUpdateProperty`:

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

## Memory cleanup

The `CleanupStateStore` method is called on errors to remove the internal state of the operation. This prevents memory leaks:

```csharp
public virtual void CleanupStateStore(Guid operationId)
{
    stateStore.Remove(operationId);
}
```

## Centralized notification manager

The notification manager is configured through `NotifierUpdateObjects`:

```csharp
var dataService = new SQLDataService();
dataService.NotifierUpdateObjects = new NotifierUpdateObjects();
```

For property handling, you can use a custom implementation:

```csharp
dataService.NotifierUpdateObjects = new NotifierUpdateObjects(
    new CustomPropertyNotifier());
```

## Error handling

In post-commit methods, exceptions are not thrown - the transaction has already been committed. Error handling is done through try/catch:

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

## Object statuses

The state of the object is passed through `ObjectStatus`:

- **Created** - object created
- **Altered** - object modified
- **Deleted** - object deleted

The difference of operations is possible by the value of the status:

```csharp
if (status == ObjectStatus.Deleted)
{
    // Handling deletion
}
```

## Principles of operation

### Internal state store

The internal store (`stateStore`) stores the state of changes for each operation:

- By `operationId` - operation identifier
- By `Type` - object type
- By `PrimaryKey` - primary key of the object
- By `PropertyName` - property name

Each stored value is a `Tuple<ObjectStatus, object, object>`: status, old and new value.

### Call order

1. **BeforeUpdateObjects** - collect changes before SQL-queries
2. **AfterSuccessSqlUpdateObjects** - after SQL execution, before COMMIT
3. **AfterSuccessUpdateObjects** - after processing, before COMMIT
4. **AfterCommitUpdateObjects** - after guaranteed commit
5. **AfterFailUpdateObjects** - on transaction rollback

## Notifications in the system

The notification system is integrated into `SQLDataService` and automatically calls the appropriate methods at all stages of the update lifecycle. Key components:

- **NotifierUpdateObjects** - standard implementation of `INotifyUpdateObjects`
- **DbTransactionWrapper** - wraps the transaction, tracks operation identifiers
- **StateStore** - internal state store

## Recommendations

The behavior of notification methods is regulated by the following principles:

- **AfterCommitUpdateObjects** - for actions requiring guaranteed saving (email, API, logs)

- **AfterSuccessUpdateObjects** - for operations without requiring commit (internal validation, caching at application level)

- Avoid long-running operations in post-commit methods

- Errors in post-commit are handled through try/catch; exceptions are not thrown - the transaction has already been committed

- Notifications may come for deleted objects; status is checked through `ObjectStatus`
