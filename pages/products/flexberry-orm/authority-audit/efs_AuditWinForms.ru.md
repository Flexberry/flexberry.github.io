---
title: Win-формы для аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit, Windows UI (формы)
toc: true
permalink: ru/efs_audit-win-forms.html
lang: ru
---

# Доступные win-формы [аудита](fa_audit-web.html)
Win-формы [аудита](audit-web.html) были разработаны для отображения объектов, используемых [ICSSoft.STORMNET.Business.Audit.Audit](i-audit.html). Формы упакованы в виде nuget-пакета `NewPlatform.Flexberry.Audit.WinForms`.

Существуют также [web-формы аудита](fa_audit-web-forms.html).

Win-формы [аудита](fa_audit-web.html):
* Формы просмотра записей о выполнении аудируемых действий.
* Формы просмотра информации об аудите объектов (с соответствующих форм редактирования есть доступ на просмотр записей по каждому аудируемому действию по конкретному объекту).

# Ограничения win-форм [аудита](fa_audit-web.html)
У форм [аудита](fa_audit-web.html) существуют следующие ограничения:
* "'''&lt;add key="AuditConnectionStringName" value="..." /&gt;'''" лучше задавать на имя текущей строки соединения, поскольку формы поддерживают вычитку только из текущей базы данных.

# Применение win-форм [аудита]fa_audit-web.html)
На настоящий момент формы [аудита](fa_audit-web.html) подключены к [консоли полномочий](security-console.html).

Для работы форм может потребоваться следующее:
* [Настройка записи аудита для объектов полномочий](rights-and-audit-subsystems.html).
* [Добавление в файл конфигурации настроек аудита и их инициализация в коде](efs_audit-win-example-manual.html).
