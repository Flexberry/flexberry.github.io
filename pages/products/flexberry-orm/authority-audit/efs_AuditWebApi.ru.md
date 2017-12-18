---
title: API подсистемы аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-web-api.html
lang: ru
---
(((Данная статья относится к [новому аудиту](fa_audit-web.html))))

# API и IAuditService
Интерфейс API подсистемы аудита содержится в классе `IAuditService` и предназначен для фиксирования собственных операций аудита.

Реализация находится в классе `AuditServce`.

## WriteCommonAuditOperation

Метод `WriteCommonAuditOperation` используется `SQLDataService`'ом для записи стандартных операций аудита (создание, изменение, удаление).

__Не рекомендуется__ вызывать этот метод самостоятельно.

Описание метода:

```
 Guid? WriteCommonAuditOperation(
                        DataObject operationedObject,
                        IDataService dataService,
                        bool throwExceptions); ```
{Br}
* `operationedObject` - Объект, над которым выполняется операция.
* `dataService` - Сервис данных, выполняющий операцию.
* `throwException` - Следует ли пробрасывать возникшее при выполнении операции исключение. Если установить `false` и произойдет ошибка, метод вернет `null`
* returns `Guid?` - возвращает `Guid` созданной записи аудита, `null` при возникновении ошибки и установленном throwExceptions = false

Метод автоматически высчитывает статус операции (создание, удаление или изменение) на основании статуса объекта (dataObject.GetStatus()).

## WriteCustomAuditOperation

Метод `WriteCustomAuditOperation` предназначен для фиксации собственных операций аудита.

Описание метода:

```
Guid? WriteCustomAuditOperation(
            CustomAuditParameters customAuditParameters,
            string dataServiceConnectionString,
            Type dataServiceType,
            bool throwExceptions);
```

* `customAuditParameters` - параметры аудита (подробнее см. ниже)
* `dataServiceConnectionString` - строка соединения сервиса данных
* `dataServiceType` - тип сервиса данных
* `throwException` - Следует ли пробрасывать возникшее при выполнении операции исключение. Если установить `false` и произойдет ошибка, метод вернет `null`
* returns `Guid?` - возвращает `Guid` созданной записи аудита, `null` при возникновении ошибки и установленном throwExceptions = false

### CustomAuditParameters
Класс, содержащий описание параметров аудита для собственных операций.

Содержит в себе следующие поля:

* tTypeOfAuditOperation `CommonAuditOperation` - устанавливает тип операции поля `CustomAuditOperation` на основе существующих типов операций (Создание, изменение, удаление, чтение, выполнение). Добавлено для удобство использования.


* string `CustomAuditOperation` - тип операции, отображаемый на списковой форме аудита.


* DataObject `AuditDataObject` - аудируемый объект. Сам объект не сохраняется, сохраняется только его `primaryKey`. Автоматически означивает свойство `AuditObjectType`.


* Type `AuditObjectType` - тип аудируемого объекта. Автоматически означивается при установке `AuditDataObject`.


* object `AuditObjectPrimaryKey` - primaryKey аудируемого объекта. Автоматически означивается при установке `AuditDataObject`.


* string `AuditObjectTypeOrDescription` - Тип (`AssemblyQualifiedName`) или описание объекта, аудит которого проводится. Задание `AuditDataObject` или `AuditObjectType` автоматически означит это поле.


* DateTime `OperationTime` - время выполнения операции. Если время не задано, установится `DateTime.Now` (''__Примечание__: в таком случае, при асинхронной записи реальные и записанные даты будут различаться. Рекомендуется означивать это поле самостоятельно'').


* tExecutionVariant `ExecutionResult` - результат выполнения операции (Не известно, Не выполнено, Выполнено, Ошибка).


* CustomAuditFieldList `CustomAuditFieldList` - список с изменениями объектов, которые необходимо записать в подсистему аудита.


* bool `UseDefaultWriteMode` - использовать ли режим записи по умолчанию.


* tWriteMode `WriteMode` - режим записи аудита (синхронный или асинхронный). При установленом `UseDefaultWriteMode` = true; это поле не учитывается.

## RatifyAuditOperation
Метод `RatifyAuditOperation` используется для подтверждения записанной ранее операции аудита. Фактически, он изменяет её `ExcecutionResult`.


К примеру, если разработчик хочет аудировать все нажатия конкретной кнопки, то первым делом в обработчик нажатия записывается директива создания записи в аудите (с помощью метода `WriteCustomAuditOperation`) с результатом выполнения ExecutionResult = tExecutionVariant.Unknown; После выполнения всех операций необходимо изменить статус записи аудита (при помощи вызова метода `RatifyAuditOperation`) на соответствующий действительности: если процесс завершился успешно, установить ExecutionResult = tExecutionVariant.Executed и т.д.

```
bool RatifyAuditOperation(
            tExecutionVariant executionVariant, 
            List<Guid> auditOperationIdList, 
            string dataServiceConnectionString,
            Type dataServiceType,
            bool throwExceptions);
```

* `executionVariant` - статус операции, который необходимо установить.
* `auditOperationIdList` - список ID записей __аудита__, для которых необходимо изменить статус
* `dataServiceConnectionString` - строка соединения сервиса данных
* `dataServiceType` - тип сервиса данных
* `throwException` - Следует ли пробрасывать возникшее при выполнении операции исключение. Если установить `false` и произойдет ошибка, метод вернет `null`
* returns `bool` - результат выполнения операции (`True`, если успешно).

