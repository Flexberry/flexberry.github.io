--- 
title: error Handling in Flexberry Audit 
sidebar: ember-flexberry-security_sidebar 
keywords: Flexberry Audit 
toc: true 
permalink: en/efs_flexberry-audit-exceptions.html 
lang: en 
autotranslated: true 
hash: 4c029fb6474ae437dc5c12d57cc6b8ab5e7468594082be458cf7f9a374f8b9c2 
--- 

# error Handling 
![Image](/images/img/page/AuditWeb/AuditErrorHandle.PNG) 


* `AuditException` – the underlying exception audit. When performing such basic operations as of audit [`WriteCustomAuditOperation`, `RatifyAuditOperation`, `WriteCommonAuditOperation`](efs_audit-web-api.html) out propisyvayutsya only exceptions of this type. 
* `DisabledAuditException` the exception reports that the audit is turned off, accordingly, nothing in DB audit will not fall. 
* `DataNotFoundAuditException` – not all data necessary for the functioning of the audit, are available. 
* `ExecutionFailedAuditException` – exception reports that during the recording of audit data was something that was not allowed to record them. 
* `RatifyExecutionFailedAuditException` – exception reports, at which the audit record has failed to change the status. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/