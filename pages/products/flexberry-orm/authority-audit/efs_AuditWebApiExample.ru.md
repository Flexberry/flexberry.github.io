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
Допустим, что у нас есть некий метод формирования отчета:

```
public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
{
    var report = new Отчет();
    
    // Создаем отчет ...

    return report;
}
```

Необходимо модифицировать его, добавив ведение аудита формирований отчетов.

Для начала необходимо записать в базу факт начала формирования отчета, использовав метод `[AuditWebApi#WriteCustomAuditOperation_2|WriteCustomAuditOperation]`.

Метод принимает в качестве параметра экземпляр класса `[AuditWebApi#CustomAuditParameters_0|CustomAuditParameters]`. Создадим его:

```

var auditParams = new CustomAuditParameters
{
    CustomAuditOperation = string.Format("Создание отчета по клиенту {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
    ExecutionResult = tExecutionVariant.Unknown,
    OperationTime = DateTime.Now,
    UseDefaultWriteMode = true
};
```

Обратим внимание, что мы не передаем никакого объекта (свойство `AuditDataObject`), так как у нас фактически ничего не изменяется.

Так как мы не знаем результата операции формирования отчета, мы выставляем ExecutionResult = tExecutionVariant.Unknown

Теперь можно записать данные в базу аудита:

```
 Guid? auditOperationId = AuditService.Current.WriteCustomAuditOperation(auditParams, true); 
```

Используем самую простую перегрузку метода `WriteCustomAuditOperation`, DataService определится как стандартный для приложения.

Результатом выполнения операции является ID созданной записи в БД аудита. Запомним его, он пригодится нам позже.

Далее необходимо каким либо образом определить результат выполняемой операции и обновить результат в базе при помощи метода `[AuditWebApi#RatifyAuditOperation_3|RatifyAuditOperation]`, воспользуемся блоком try-catch-finally:

```

bool correct = true;

try
{
    // Создаем отчет ...
}
catch(Exception ex)
{
    // Записываем ошибку в лог ...

    // Запоминаем факт возникновения ошибки
    correct = false;
}
finally
{
    // Обновим статус операции в базе в соответствии с результатами построения отчета
    AuditService.Current.RatifyAuditOperation(
                    correct ? tExecutionVariant.Executed : tExecutionVariant.Failed,
                    new List<Guid> { auditOperationId.Value },
                    false);
}
```


## Результат
Теперь при попытке создания отчета в базе данных аудита создастся соответствующая запись. Статус выполнения операции будет зависеть от успешности её выполнения.

### Измененный метод
```

        public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
        {
            // Заносим данные о начале операции создании отчета в подсистему аудита
            var auditParams = new CustomAuditParameters
                {
                    CustomAuditOperation = string.Format("Создание отчета по клиенту {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
                    ExecutionResult = tExecutionVariant.Unknown,
                    OperationTime = DateTime.Now,
                    UseDefaultWriteMode = true
                };

            // Записываем операцию аудита в базу и запоминаем ID созданной записи
            Guid? auditOperationId = AuditService.Current.WriteCustomAuditOperation(auditParams, true);

            var report = new Отчет();
            bool correct = true;

            try
            {
                // Создаем отчет ...
            }
            catch(Exception ex)
            {
                // Записываем ошибку в лог ...

                // Запоминаем факт возникновения ошибки
                correct = false;
            }
            finally
            {
                // Обновим статус операции в базе в соответствии с результатами построения отчета
                AuditService.Current.RatifyAuditOperation(
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
