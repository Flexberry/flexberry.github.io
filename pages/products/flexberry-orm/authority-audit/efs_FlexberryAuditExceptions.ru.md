---
title: Обработка ошибок во Flexberry Audit
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_flexberry-audit-exceptions.html
folder: products/ember-flexberry-security/backend/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Audit](audit-web.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll
* '''Предназначение''': Описана иерархия основных используемых во [Flexberry Audit](audit-web.html) сообщений об ошибках.
</td>
</tr></tbody></table></a>
</div>

# Обработка ошибок
![Изображение](/images/img/page/AuditWeb/AuditErrorHandle.PNG)


* `AuditException` – базовое исключение подсистемы аудита. При выполнении таких базовых операций аудита как [`WriteCustomAuditOperation`, `RatifyAuditOperation`, `WriteCommonAuditOperation`](audit-web-api.html) наружу пробрасываются только исключения такого типа.
* `DisabledAuditException` – исключение сообщает, что аудит выключен, соответственно, ничего в БД аудита не попадёт.
* `DataNotFoundAuditException` – не все данные, необходимые для функционирования подсистемы аудита, есть в наличии.
* `ExecutionFailedAuditException` – исключение сообщает, что в ходе записи данных аудита произошло нечто, что не позволило их записать.
* `RatifyExecutionFailedAuditException` – исключение сообщает, у каких именно записей аудита не удалось изменить статус.
