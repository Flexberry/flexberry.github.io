---
title: Пример использования API подсистемы аудита. Создание и подтверждение собственных записей аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_audit-web-api-example.html
lang: ru
---

## Задача

Вести аудит формирований отчетов

## Дано

* Метод формирования отчета
* Подсистема аудита

## Решение

Допустим, что есть некий метод формирования отчета:

```csharp
public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
{
    var report = new Отчет();
    
    // Создать отчет ...

    return report;
}
```

Необходимо модифицировать его, добавив ведение аудита формирований отчетов.

В первую очередь, необходимо записать в базу факт начала формирования отчета, использовав метод `[AuditWebApi#WriteCustomAuditOperation_2|WriteCustomAuditOperation]`.

Метод принимает в качестве параметра экземпляр класса `[AuditWebApi#CustomAuditParameters_0|CustomAuditParameters]`. Создадать его можно следующим образом:

```csharp
var auditParams = new CustomAuditParameters
{
    CustomAuditOperation = string.Format("Создание отчета по клиенту {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
    ExecutionResult = tExecutionVariant.Unknown,
    OperationTime = DateTime.Now,
    UseDefaultWriteMode = true
};
```

Объект не передается (свойство `AuditDataObject`), так как фактически ничего не изменяется.

Исходя из того, что результат операции формирования отчета не известен, ExecutionResult = tExecutionVariant.Unknown

Теперь можно записать данные в базу аудита:

```csharp
UnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
AIAuditService audittservice = mainUnityContainer.Resolve<AIAuditService>();
Guid? auditOperationId = audittservice.WriteCustomAuditOperation(auditParams, true); 
```

Используется самая простая перегрузка метода `WriteCustomAuditOperation`, IDataService определится как стандартный для приложения.

Результатом выполнения операции является ID созданной записи в БД аудита. Этот результат потребуется позже.

Далее необходимо каким либо образом определить результат выполняемой операции и обновить результат в базе при помощи метода `[AuditWebApi#RatifyAuditOperation_3|RatifyAuditOperation]`, для этого используется блок try-catch-finally:

```csharp
bool correct = true;

try
{
    // Создать отчет ...
}
catch(Exception ex)
{
    // Записать ошибку в лог ...

    // Запоминить факт возникновения ошибки
    correct = false;
}
finally
{
    // Обновить статус операции в базе в соответствии с результатами построения отчета
    UnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    AIAuditService audittservice = mainUnityContainer.Resolve<AIAuditService>();
    audittservice.RatifyAuditOperation(
                    correct ? tExecutionVariant.Executed : tExecutionVariant.Failed,
                    new List<Guid> { auditOperationId.Value },
                    false);
}
```

## Результат

Теперь при попытке создания отчета в базе данных аудита создастся соответствующая запись. Статус выполнения операции будет зависеть от успешности её выполнения.

### Измененный метод

```csharp
public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
{
    // Занести данные о начале операции создания отчета в подсистему аудита
    var auditParams = new CustomAuditParameters
        {
            CustomAuditOperation = string.Format("Создание отчета по клиенту {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
            ExecutionResult = tExecutionVariant.Unknown,
            OperationTime = DateTime.Now,
            UseDefaultWriteMode = true
        };

    // Записать операцию аудита в базу и запоминить ID созданной записи
    UnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
    AIAuditService audittservice = mainUnityContainer.Resolve<AIAuditService>();
    Guid? auditOperationId = audittservice.WriteCustomAuditOperation(auditParams, true);

    var report = new Отчет();
    bool correct = true;

    try
    {
        // Создать отчет ...
    }
    catch(Exception ex)
    {
        // Записать ошибку в лог ...

        // Запоминить факт возникновения ошибки
        correct = false;
    }
    finally
    {
        // Обновить статус операции в базе в соответствии с результатами построения отчета
        UnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
        AIAuditService audittservice = mainUnityContainer.Resolve<AIAuditService>();
        audittservice.RatifyAuditOperation(
            correct ? tExecutionVariant.Executed : tExecutionVariant.Failed,
            new List<Guid> { auditOperationId.Value },
            false);
    }

    return report;
}
```

### Web-интерфейс

После попытки создания двух отчетов, одна из которых прошла успешно, а вторая неудачно:

Форма просмотра записей аудита (частично):
![Изображение](/images/img/page/AuditWebApiExample/AuditWebApiWolv.PNG)

Форма просмотра деталей записи аудита:
![Изображение](/images/img/page/AuditWebApiExample/AuditWebApiE.PNG)
