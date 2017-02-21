---
title: Flexberry ORM
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/fo_flexberry-o-r-m.html
folder: products/flexberry-orm/
lang: ru
---

# Информация о продукте
`Flexberry ORM` является [продуктом платформы Flexberry](platform-structure.html). Сайт продукта `Flexberry ORM`: [http://flexberry.ru/FlexberryORM](http://flexberry.ru/FlexberryORM).

(((
<msg type=information>Flexberry ORM доступно для установки в проект через [NuGet package](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM).</msg>
)))

# Состав продукта
`Flexberry ORM` состоит из следующих модулей:
* [Компоненты для работы с объектной моделью (объекты данных).](dataobject.html)
* [Компоненты доступа к источнику данных (сервисы данных).](data-service.html)
* [Представления](view-definition.html)
* [Компоненты для фильтрации получаемых данных (ограничения).](limitation.html)
* [Компоненты пессимистической блокировки, обеспечивающие возможность блокировать объекты данных во время выполнения операций над ними.](lock-service.html) 
* [Бизнес-серверы](business-logic.html).
* [Монитор задач.](business-task-monitor.html)
* [Модуль расширения Flexberry Designer для генерации кода по uml-моделям.](flexberry-orm-case-plugin.html)
* [Вспомогательные инструменты ICSSoft.STORMNET.Tools.](i-c-s-soft-s-t-o-r-m-n-e-t-tools.html)


# Описание продукта
Посредством `[Flexberry Designer](flexberry-designer.html)` возможно создание [диаграммы классов](fd_class-diagram.html). С этой [диаграммы](fd_class-diagram.html) можно сформировать необходимую структуру базы данных (БД) и объектную модель. [Компонент работы с БД](data-service.html) осуществляет отображение структуры реляционных таблиц БД на объектную модель. Для того, чтобы из БД извлекать элементы, удовлетворяющие определённым условиям, используется [компоненты для работы с фильтрами и ограничениями](limitation.html).

Список библиотек, содержащих Runtime-компоненты Flexberry ORM представлен [здесь](flexberry-o-r-m-libraries.html).

Поддерживаемая типизация описана в [отдельной статье](flexberry-orm-types.html).

# Демонстрационное приложение
Исходный код демонстрационного приложения доступен по следующему расположению: <https://github.com/Flexberry/FlexberryORM-DemoApp>.
