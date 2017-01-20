---
title: Настройка прав доступа на публикацию ограничений
sidebar: product--sidebar
keywords: Flexberry Security, Flexberry Winforms, Ограничения
toc: true
permalink: ru/fw_setting-permissions-for-publication-restrictions.html
folder: products/flexberry-winforms/
lang: ru
---

## Настройка прав доступа на публикацию ограничений

Настраивается '''AdvLimitComponent''' следующим образом:

* В свойстве '''PublicateOperationId''' указвается id операции (по умолчанию "-1")

* В свойстве '''PublicateAccess''' указывается возможность доступа по умолчанию (Если '''PublicateOperationId''' <= 0 либо AzMan не может проверить доступ и вызывает ошибку)

## Настройка в Flexberry Security
Данная операция должна быть прописана с именем, соответствующим '''PublicateOperationId'''. Данное решение не является красивым, но применяется для обеспечения совместимости с системами, работающими на `AzMan` (для возможности быстрого перехода на [RightService-Flexberry-Rights|Flexberry Rights]).


## Смотрите также

* [Как начать работу с подсистемой полномочий](how-to-start-work-with-right-manager.html).
* [Сценарии использования подсистемы полномочий](rights-scenarios.html).
* [c:Полномочия|Все статьи категории "Полномочия"].
