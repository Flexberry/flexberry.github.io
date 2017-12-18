---
title: IAudit
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit, БД
toc: true
permalink: ru/efs_i-audit.html
lang: ru
---

# IAudit
`IAudit` представляет собой интерфейс для организации логики работы [аудита](fa_audit-web.html) (то есть именно класс, реализующий этот интерфейс, будет отвечать за запись данных аудита и за их вычитку).

# ICSSoft.STORMNET.Business.Audit
`ICSSoft.STORMNET.Business.Audit` представляет собой реализацию интерфейса `IAudit` и располагается в сборке `ICSSoft.STORMNET.Business.Audit`. 

# ICSSoft.STORMNET.Business.Audit.EmptyAudit
В версии после 28.01.2015 добавлен класс `ICSSoft.STORMNET.Business.Audit.EmptyAudit`, реализующий интерфейс `IAudit` и располагающийся в сборке `ICSSoft.STORMNET.Business`. Данный класс представляет собой класс-заглушку, которую можно использовать, чтобы информация об изменении полей объектов не шли в БД.
