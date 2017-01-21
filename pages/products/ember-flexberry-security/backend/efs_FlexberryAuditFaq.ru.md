---
title: FAQ по аудиту
sidebar: ember-flexberry-security_sidebar
keywords: FAQ, Flexberry Audit
toc: true
permalink: ru/efs_flexberry-audit-faq.html
folder: products/ember-flexberry-security/backend/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Audit](audit-web.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll, ICSSoft.STORMNET.Business.Audit.dll
* '''Предназначение''': Представлены основные вопросы, возникающие у пользователей аудита, и возможные способы их решения.
</td>
</tr></tbody></table></a>
</div>

# Аудит совсем ничего не пишет
«Аудит совсем ничего не пишет – не знаю, куда смотреть». Что можно сделать:
# Проверить, что есть код, [инициализирующий аудит в приложении](audit-setter.html).
# Проверить, что нет вызова метода, отключающего аудит (`[AuditService.DisableAudit()](audit-web-api.html)`).
# Проверить, что требуемая операция включена для класса (например, по умолчанию аудит изменений выключен).
# Проверить правильность [настроек в конфиге и классах](keep-and-use-audit-settings.html).
# Проверить, что аудит [настроен на свой пишущий вариант](i-audit.html).
# …

# Аудит не пишет пользователя
«Аудит не пишет пользователя, сделавшего изменения – не знаю, куда смотреть». Что можно сделать:
# Проверить настройки [сервиса текущего пользователя](current-user-service.html) (общий алгоритм определения аудитом текущего пользователя описан [здесь](not-stored-properties-and-audit.html)).
# …
