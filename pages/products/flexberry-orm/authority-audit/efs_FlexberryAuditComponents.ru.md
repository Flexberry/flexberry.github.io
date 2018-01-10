---
title: Элементы Flexberry Audit
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit
toc: true
permalink: ru/efs_flexberry-audit-components.html
lang: ru
---

# AuditService
У приложения есть класс `AuditService`. Через класс `AuditService` реализуются API для обращения: 
* к классу, наследующему от интерфейса [IAudit](i-audit.html), если аудит не выделен в отдельный сервис.
* к сервису аудита `AuditWinService`.

`AuditService` хранит настройки приложения по аудиту, куда они загружаются в начале работы приложения.

При выполнении потенциально аудируемой операции [сервис данных](fo_sql-data-service.html) приложения сообщает об этом классу `AuditService`, который:
* просматривает имеющиеся настройки аудита и принимает решение о необходимости выполнения записи аудита.
* если аудит необходим, то соответствующее сообщение идёт либо в `IAudit`, если нет отдельного сервиса аудита, либо в `AuditWinService` (ожидание ответа будет зависеть от настроек аудита).

Класс `AuditService` реализовывает интерфейс `IAuditService`, а также имеет статическое поле типа `IAuditService`, куда будет записана инстанция класса `AuditService` (это позволит вести работу как со статическим классом через обращение «AuditService.CurrentAuditService»). Все вызовы осуществляются через интерфейс (если появится потребность, то класс `Audit` можно легко подменить).

# IAudit

[IAudit](efs_i-audit.html) представляет собой интерфейс для организации логики работы аудита (то есть именно класс, реализующий этот интерфейс, будет отвечать за запись данных аудита и за их вычитку).

# AuditWinService

[AuditWinService](efs_audit-win-service.html) представляет собой службу, через которую возможно производить запись данных аудита.

# AsyncAuditController

`AsyncAuditController` – класс, организующий асинхронный доступ к `IAudit`, что позволяет отложить на некоторое время запись данных об аудите ('''в настоящее время не используется''').

# RemoteAuditController (ServiceAuditController)

`RemoteAuditController (ServiceAuditController)` – класс, отвечающий за взаимодействие через wcf с win-сервисом аудита `AuditWinService`, связанному с wcf-сервисом, реализующим интерфейс `IAuditWcfServiceLibrary`.

# Схема взаимодействия

## Схема взаимодействия компонент подсистемы аудита без отдельного сервиса аудита

![Изображение](/images/img/page/AuditWeb/AuditDiagramm1.PNG)

## Схема взаимодействия компонент подсистемы аудита при наличии отдельного сервиса аудита

![Изображение](/images/img/page/AuditWeb/AuditDiagramm2.PNG)

## Схема взаимодействия компонент подсистемы аудита

![Изображение](/images/img/page/AuditWeb/AuditDiagramm3.PNG)

