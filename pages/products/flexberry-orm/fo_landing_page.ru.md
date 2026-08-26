---
title: Flexberry ORM
keywords: хранение, store, mapping, database
sidebar: flexberry-orm_sidebar
toc: false
permalink: ru/fo_landing_page.html
lang: ru
---

`Flexberry ORM` является [технологическим компонентом платформы Flexberry](https://flexberry.net/ru/developers-flexberry-orm.html) предназначенном для организации слоя доступа к данным.

`Flexberry ORM` доступен для установки в проект через NuGet:

* [NewPlatform.Flexberry.ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM).
* [NewPlatform.Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService).

Исходный код `Flexberry ORM` доступен на [GitHub](https://github.com/search?q=topic%3Aorm+org%3AFlexberry&type=Repositories).

## Состав продукта

`Flexberry ORM` состоит из следующих модулей:

* [Компоненты для работы с объектной моделью (объекты данных).](fo_data-object.html)
* [Компоненты доступа к источнику данных (сервисы данных).](fo_data-service.html)
* [Представления](fd_view-definition.html)
* [Компоненты для фильтрации получаемых данных (ограничения).](fo_limitation.html)
* [Компоненты пессимистической блокировки, обеспечивающие возможность блокировать объекты данных во время выполнения операций над ними.](fo_lock-service.html)
* [Бизнес-серверы](fo_business-logic.html).
* [Монитор задач](fo_business-task-monitor.html)
* [Модуль расширения Flexberry Designer для генерации кода по uml-моделям.](fo_orm-case-plugin.html)
* [Вспомогательные инструменты ICSSoft.STORMNET.Tools.](fo_ics-soft-stormnet-tools.html)
* [Компоненты для интерфейса OData V4 для быстрого создания прогрессивных веб-сервисов](fo_orm-odata-service.html)

## Возможности и применение

Посредством [Flexberry Designer](fd_flexberry.html) производится создание [диаграммы классов](fd_class-diagram.html). С этой [диаграммы](fd_class-diagram.html) можно сформировать необходимую структуру базы данных (БД) и [объектную модель](fo_data-object.html). [Компонент работы с БД](fo_data-service.html) осуществляет отображение структуры реляционных таблиц БД на объектную модель. Для того, чтобы из БД извлекать элементы, удовлетворяющие определённым условиям, используется [компоненты для работы с фильтрами и ограничениями](fo_limitation.html).

Список библиотек, содержащих Runtime-компоненты Flexberry ORM представлен [здесь](fo_flexberry-orm-libraries.html).

Типы хранилищ и соответствующие им [сервисы данных](fo_standard-data-services.html):

* [PostgreSQL](fo_postgres-data-service.html)
* [Microsoft SQL Server](fo_mssql-data-service.html)
* [Oracle](fo_oracle-data-service.html)
* [Yandex ClickHouse](https://github.com/Flexberry/NewPlatform.Flexberry.ORM.ClickHouseDataService)
* [MongoDB](fo_mongodb-data-service.html)

## Демонстрационное приложение

Исходный код демонстрационного приложения доступен по следующему расположению: <https://github.com/Flexberry/FlexberryORM-DemoApp>.