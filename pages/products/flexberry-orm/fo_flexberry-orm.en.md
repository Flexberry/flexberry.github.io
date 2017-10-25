---
title: Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: en/fo_flexberry-orm.html
---
## Информация о продукте

`Flexberry ORM` является [продуктом платформы Flexberry](fp_platform-structure.html). Сайт продукта `Flexberry ORM`: [http://flexberry.ru/FlexberryORM](http://flexberry.ru/FlexberryORM).

{% include note.html content="Flexberry ORM доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM)." %}

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

## Описание продукта

Посредством [Flexberry Designer](fd_flexberry.html) возможно создание [диаграммы классов](fd_class-diagram.html). С этой [диаграммы](fd_class-diagram.html) можно сформировать необходимую структуру базы данных (БД) и объектную модель. [Компонент работы с БД](fo_data-service.html) осуществляет отображение структуры реляционных таблиц БД на объектную модель. Для того, чтобы из БД извлекать элементы, удовлетворяющие определённым условиям, используется [компоненты для работы с фильтрами и ограничениями](fo_limitation.html).

Список библиотек, содержащих Runtime-компоненты Flexberry ORM представлен [здесь](fo_flexberry-orm-libraries.html).

Поддерживаемая типизация описана в [отдельной статье](fo_flexberry-orm-types.html).

## Демонстрационное приложение
Исходный код демонстрационного приложения доступен по следующему расположению: <https://github.com/Flexberry/FlexberryORM-DemoApp>.
