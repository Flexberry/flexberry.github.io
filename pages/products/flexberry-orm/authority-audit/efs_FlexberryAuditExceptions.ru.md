---
title: Обработка ошибок во Flexberry Audit
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_flexberry-audit-exceptions.html
lang: ru
---

# Обработка ошибок
![Изображение](/images/img/page/AuditWeb/AuditErrorHandle.PNG)


* `AuditException` – базовое исключение подсистемы аудита. При выполнении таких базовых операций аудита как [`WriteCustomAuditOperation`, `RatifyAuditOperation`, `WriteCommonAuditOperation`](efs_audit-web-api.html) наружу пробрасываются только исключения такого типа.
* `DisabledAuditException` – исключение сообщает, что аудит выключен, соответственно, ничего в БД аудита не попадёт.
* `DataNotFoundAuditException` – не все данные, необходимые для функционирования подсистемы аудита, есть в наличии.
* `ExecutionFailedAuditException` – исключение сообщает, что в ходе записи данных аудита произошло нечто, что не позволило их записать.
* `RatifyExecutionFailedAuditException` – исключение сообщает, у каких именно записей аудита не удалось изменить статус.
