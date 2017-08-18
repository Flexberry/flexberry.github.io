---
title: Win-формы для аудита
sidebar: ember-flexberry-security_sidebar
keywords: Flexberry Audit, Windows UI (формы)
toc: true
permalink: ru/efs_audit-win-forms.html
folder: products/ember-flexberry-security/backend/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Audit](audit-web.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.Audit(WinForms).dll
* '''Предназначение''': Описаны доступные Win-формы для отображения данных аудита в формате, используемом [ICSSoft.STORMNET.Business.Audit.Audit](i-audit.html).
</td>
</tr></tbody></table></a>
</div>

# Доступные win-формы [аудита](audit-web.html)
Win-формы [аудита](audit-web.html) были разработаны для отображения объектов, используемых [ICSSoft.STORMNET.Business.Audit.Audit](i-audit.html). Формы упакованы в виде nuget-пакета `NewPlatform.Flexberry.Audit.WinForms`.

Существуют также [web-формы аудита](fa_audit-web-forms.html).

Win-формы [аудита](audit-web.html):
* Формы просмотра записей о выполнении аудируемых действий.
* Формы просмотра информации об аудите объектов (с соответствующих форм редактирования есть доступ на просмотр записей по каждому аудируемому действию по конкретному объекту).

# Ограничения win-форм [аудита](audit-web.html)
У форм [аудита](audit-web.html) существуют следующие ограничения:
* "'''&lt;add key="AuditConnectionStringName" value="..." /&gt;'''" лучше задавать на имя текущей строки соединения, поскольку формы поддерживают вычитку только из текущей базы данных.

# Применение win-форм [аудита](audit-web.html)
На настоящий момент формы [аудита](audit-web.html) подключены к [консоли полномочий](security-console.html).

Для работы форм может потребоваться следующее:
* [Настройка записи аудита для объектов полномочий](rights-and-audit-subsystems.html).
* [Добавление в файл конфигурации настроек аудита и их инициализация в коде](audit-win-example-manual.html).
