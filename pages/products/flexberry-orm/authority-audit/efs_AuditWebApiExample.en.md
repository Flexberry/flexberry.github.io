--- 
title: Example API usage audit. Create and confirm their own audit records 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_audit-web-api-example.html 
lang: en 
autotranslated: true 
hash: b88d8b0a4f2a7217933d1617e8a79453fb37f3a800b0bfc911dab9c25b14820d 
--- 

## Task 
Audit groups report 

## This 
* The method of report generation 
* Audit 

## Solution 
Suppose we have a method of report generation: 

```
public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
{
    var report = new Отчет();
    
    // Create report ... 

    return report;
}
``` 

You need to modify it by adding audit logging groups reports. 

First we need to write to the database, the fact of the beginning of report generation, using the method `[AuditWebApi#WriteCustomAuditOperation_2|WriteCustomAuditOperation]`. 

The method takes as a parameter an instance of the class `[AuditWebApi#CustomAuditParameters_0|CustomAuditParameters]`. Create it: 

```

var auditParams = new CustomAuditParameters
{
    CustomAuditOperation = string.Format(The "create report by client {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
    ExecutionResult = tExecutionVariant.Unknown,
    OperationTime = DateTime.Now,
    UseDefaultWriteMode = true
};
``` 

Note that we do not share any object (property `AuditDataObject`), as we actually changes nothing. 

Since we don't know the result of the operation of forming the report, we expose ExecutionResult = tExecutionVariant.Unknown 

You can now write data to the database audit: 

```
 Guid? auditOperationId = AuditService.Current.WriteCustomAuditOperation(auditParams, true); 
``` 

Use the simplest overload `WriteCustomAuditOperation`, DataService are defined as standard for the application. 

The result is the ID of the created record in the database audit. Remember it, it will be useful to us later. 

Next, you need some way to determine the result of the running operation and update the result in the database using the method `[AuditWebApi#RatifyAuditOperation_3|RatifyAuditOperation]`, use a try-catch-finally: 

```

bool correct = true;

try
{
    // Create report ... 
}
catch(Exception ex)
{
    // Writable error log ... 

    // Store the fact of occurrence of the error 
    correct = false;
}
finally
{
    // Update the transaction status in the database in accordance with the results of the report 
    AuditService.Current.RatifyAuditOperation(
                    correct ? tExecutionVariant.Executed : tExecutionVariant.Failed,
                    new List<Guid> { auditOperationId.Value },
                    false);
}
``` 


## Result 
Now when I try to create the report in the audit database will be created corresponding record. The status of this operation will depend on the success of its implementation. 

### Modified method 
```

        public static Отчет СоздатьОтчетПоКлиенту(Клиент клиент)
        {
            // Write the data on the beginning of the operation the report is created in the audit 
            var auditParams = new CustomAuditParameters
                {
                    CustomAuditOperation = string.Format(The "create report by client {0} (ID: {1})", клиент.ФИО, клиент.__PrimaryKey),
                    ExecutionResult = tExecutionVariant.Unknown,
                    OperationTime = DateTime.Now,
                    UseDefaultWriteMode = true
                };

            // Writable surgery audit database and remember the ID of the created record 
            Guid? auditOperationId = AuditService.Current.WriteCustomAuditOperation(auditParams, true);

            var report = new Отчет();
            bool correct = true;

            try
            {
                // Create report ... 
            }
            catch(Exception ex)
            {
                // Writable error log ... 

                // Store the fact of occurrence of the error 
                correct = false;
            }
            finally
            {
                // Update the transaction status in the database in accordance with the results of the report 
                AuditService.Current.RatifyAuditOperation(
                    correct ? tExecutionVariant.Executed : tExecutionVariant.Failed,
                    new List<Guid> { auditOperationId.Value },
                    false);
            }

            return report;
        }
``` 

### Web interface 
After trying to create two reports, one of which was successful and the second failed: 

The display form of audit records (in part): 
![Image](/images/img/page/AuditWebApiExample/AuditWebApiWolv.PNG) 


Form view the details of the audit entry: 
![Image](/images/img/page/AuditWebApiExample/AuditWebApiE.PNG) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}