---
title: Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_flexberry-orm.html
---
## Информация о продукте

`Flexberry ORM` является [продуктом платформы Flexberry](fp_landing_page.html). Сайт продукта `Flexberry ORM`: [http://flexberry.ru/FlexberryORM](http://flexberry.ru/FlexberryORM).

Для `Flexberry ORM` доступна сборка под:

* .NETFramework 4.5
* .NETFramework 4.6.1
* .NETStandard 2.0
* .NETCoreApp 3.1
* net6.0
* net7.0

{% capture net6_note %}
Для Flexberry ORM .NET6 и выше в проекте может потребоваться установить флаг `<EnableUnsafeBinaryFormatterSerialization>true</EnableUnsafeBinaryFormatterSerialization>`, если из пакета Flexberry ORM используется функциональность с _BinaryFormatter_, который [устарел](https://learn.microsoft.com/ru-ru/dotnet/core/compatibility/serialization/5.0/binaryformatter-serialization-obsolete).
{% endcapture %}

{% include note.html content=net6_note %}

{% include note.html content="Flexberry ORM доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM)." %}

## Состав продукта

`Flexberry ORM` состоит из следующих модулей:

* [Компоненты для работы с объектной моделью (объекты данных)](fo_data-object.html)
* [Компоненты доступа к источнику данных (сервисы данных)](fo_data-service.html)
* [Представления](fd_view-definition.html)
* [Компоненты для фильтрации получаемых данных (ограничения).](fo_limitation.html)
* [Компоненты пессимистической блокировки, обеспечивающие возможность блокировать объекты данных во время выполнения операций над ними](fo_lock-service.html)
* [Бизнес-серверы](fo_business-logic.html).
* [Монитор задач](fo_business-task-monitor.html)
* [Модуль расширения Flexberry Designer для генерации кода по uml-моделям](fo_orm-case-plugin.html)
* [Вспомогательные инструменты ICSSoft.STORMNET.Tools](fo_ics-soft-stormnet-tools.html)

## Описание продукта

Посредством [Flexberry Designer](fd_flexberry.html) возможно создание [диаграммы классов](fd_class-diagram.html). С этой [диаграммы](fd_class-diagram.html) можно сформировать необходимую структуру базы данных (БД) и объектную модель. [Компонент работы с БД](fo_data-service.html) осуществляет отображение структуры реляционных таблиц БД на объектную модель. Для того, чтобы из БД извлекать элементы, удовлетворяющие определённым условиям, используется [компоненты для работы с фильтрами и ограничениями](fo_limitation.html).

Список библиотек, содержащих Runtime-компоненты Flexberry ORM представлен [здесь](fo_flexberry-orm-libraries.html).

Поддерживаемая типизация описана в [отдельной статье](fo_flexberry-orm-types.html).

## Демонстрационное приложение

Исходный код демонстрационного приложения доступен по следующему расположению: <https://github.com/Flexberry/FlexberryORM-DemoApp>.
