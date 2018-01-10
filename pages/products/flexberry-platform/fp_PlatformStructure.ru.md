---
title: Структура технологической платформы
sidebar: product--sidebar
keywords: Public
toc: true
permalink: ru/fp_platform-structure.html
folder: product--folder
lang: ru
---

# Продукты [платформы Flexberry](http://flexberry.net)

## [Flexberry ORM](fo_flexberry-orm.html)
Программный продукт, реализующий объектно-реляционное отображение (Object-Relational Mapping) на базе Microsoft .NET Framework.

* Установить в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM)
* [Документация по Flexberry ORM](fo_flexberry-orm.html)

## [Flexberry Designer](fd_landing_page.html)
Инструментарий проектирования представляет собой CASE-инструмент и состоит из UML-редактора Flexberry Designer и модуля расширения «Flexberry ORM», предназначенным для генерации C#-кода и SQL. Данный инструментарий позволяет по диаграмме классов сгенерировать C#-классы объектов данных и БД. Проектирование с использованием данного инструментария подразумевает принцип Model-First, когда все изменения в модели производятся в CASE, а изменения в коде выполняются во время генерации. Также реализован механизм, позволяющий программистам писать код, который при перегенерации не будет потерян, так называемые, «скобки программиста». Использование инструментария проектирования позволяет с лёгкостью вносить изменения в модель любому участнику команды разработки, не опасаясь что-нибудь сломать.

## Flexberry Winforms

Программный продукт, реализующий пользовательский интерфейс на базе Windows Forms.

## Flexberry LogService

[LogService (log4net)](fo_log-service-log4net.html)

## Flexberry UnityFactory

[Flexberry UnityFactory](fo_unity-factory.html)

## Flexberry CurrentUserService

[Сервис текущего пользователя](fo_current-user-service.html)
