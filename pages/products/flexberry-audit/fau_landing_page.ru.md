---
title: Flexberry Audit
keywords: flexberry, Audit
sidebar: flexberry-audit_sidebar
toc: false
permalink: ru/fau_landing_page.html
lang: ru
---

## Описание

Технологический компонент **Flexberry Audit** реализует функцию журналирования изменения данных пользователями.

## Возможности

* Фиксация любых фактов работы и обращений к данным
* Фиксация произвольных операций (через API)
* Просмотр детальных сведений о произведённых операциях над данными, включая изменённые значения
* Выполнение записи фактов в одной транзакции с изменением данных
* Гибкая настройка фиксируемых операций и детализации информации

## Архитектура

**Flexberry Audit** состоит из следующих частей:

* БД с данными аудита (может быть в основной БД приложения или отдельно, в т.ч. на другой СУБД)
* Серверная часть - NuGet-пакет с объектной моделью и API: [NewPlatform.Flexberry.Audit](https://www.nuget.org/packages/NewPlatform.Flexberry.Audit)
* Интерфейс просмотра данных аудита
  * [Реализация](https://github.com/flexberry/ember-flexberry-security) для [Flexberry Ember](ef3_landing_page.html)
  * Реализация для [Flexberry ASP.NET](fa_landing_page.html)
  * Реализация для [Flexberry Winforms](fw_landing_page.html)
* Настройка фиксируемых операций по объектам данных выполняется в интерфейсе Flexberry Designer.

## Как воспользоваться

Чтобы создавать приложения с использованием **Flexberry Audit** можно выполнить проектирование и генерацию приложения в Flexberry Designer.  
Отдельно доступен NuGet-пакет [NewPlatform.Flexberry.Audit](https://www.nuget.org/packages/NewPlatform.Flexberry.Audit), но для его использования в ручном режиме потребуется самостоятельно реализовывать обращения к API.
Также вам понадобится [инструкция по установке и настройке аудита](fau_audit-install.html).
