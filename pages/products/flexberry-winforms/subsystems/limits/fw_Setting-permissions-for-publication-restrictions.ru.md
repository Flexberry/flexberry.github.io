---
title: Настройка прав доступа на публикацию ограничений
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Security, Flexberry Winforms, Ограничения
summary: Указано что следует сделать, чтобы настроить права доступа на публикацию ограничений
toc: true
permalink: ru/fw_setting-permissions-for-publication-restrictions.html
lang: ru
---

## Описание

Настраивается __AdvLimitComponent__ следующим образом:

* В свойстве __PublicateOperationId__ указвается id операции (по умолчанию "-1")
* В свойстве __PublicateAccess__ указывается возможность доступа по умолчанию (Если __PublicateOperationId__ <= 0 либо AzMan не может проверить доступ и вызывает ошибку)

## Настройка в Flexberry Security

Данная операция должна быть прописана с именем, соответствующим __PublicateOperationId__. Данное решение не является красивым, но применяется для обеспечения совместимости с системами, работающими на `AzMan` (для возможности быстрого перехода на [Flexberry Rights](efs_security-legacy-services.html)).

## Смотрите также

* [Сценарии использования подсистемы полномочий](efs_rights-scenarios.html).
