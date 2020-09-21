---
title: Обзор технологии Flexberry Ember
sidebar: flexberry-ember-3_sidebar
keywords: mydoc
summary: Цели, возможности, архетектура, особенности и инструменты разработки Flexberry Ember
toc: true
permalink: ru/ef3_technology-overview.html
lang: ru
---

## Цель и основные возможности технологии Flexberry Ember

**Целью** технологии Flexberry Ember является быстрое создание прототипа приложения с использованием фреймворка Ember.js, имеющего базовый функционал учетной системы “из коробки”.

### Основные возможности технологии Flexberry Ember

1. [Генерация Ember-приложения](https://flexberry.github.io/ru/ef2_generator.html) с функционалом учетной системы из меню Flexberry Designer
2. Поддержка [офлайн-режима](https://flexberry.github.io/ru/efd2_offline.html) работы Ember-приложения
3. Поддержка [протокола OData](https://flexberry.github.io/ru/efd2_odata.html)
4. Поддержка [работы с файлами](https://flexberry.github.io/ru/efd2_work-files.html)
5. Предоставление возможностей для использования Ember-приложения в качестве [мобильного приложения](https://flexberry.github.io/ru/ef2_cordova.html) Apache Cordova на той же кодовой базе

## Архитектура приложений на Flexberry Ember

![Архитектура приложений](/images/pages/products/flexberry-ember/ember-flexberry/getting-started/application-architecture.png)

Как следует из схемы, приложения на Flexberry Ember состоят из трех основных частей:

* базы данных
* серверной бизнес-логики
* пользовательского интерфейса (клиентская бизнес-логика)

Сервер непосредственно взаимодействует с базой данных (используются такие СУБД как MS SQL Server, Oracle, Postgres) , обрабатывает емкие запросы. На клиенте не всегда можно быстро обработать запросы, требующие больших объемов данных, так как это замедляет работу пользовательского приложения.
Сервер и клиент взаимодействуют с помощью REST API (OData): создание, изменение и удаление объектов приложения. Кроме того, на сервере хранится html-документ, который отображается в браузере в результате первого запроса (далее клиент и сервер обмениваются только данными) . Собственно пользовательский интерфейс приложения описывается с помощью html, css, semantic ui, JavaScript.

### Составные части приложений на Flexberry Ember реализуются с использованием следующих технологий

1. Backend реализуется в виде приложения ASP.NET Web API с использованием  Flexberry ORM
2. Frontend реализуется на базе фреймворка Ember.js и Apache Cordova (при необходимости разработать мобильное приложение) с использованием аддонов, входящих в состав технологии Flexberry Ember
3. База данных может работать под управлением СУБД PostgreSQL, MS SQL Server или Oracle

## Цикл разработки приложений на Flexberry Ember

![Цикл разработки приложений](/images/pages/products/flexberry-ember/ember-flexberry/getting-started/application-development-cycle.png)

1.Создание модели приложения на языке UML с помощью Flexberry Designer

2.Генерация прототипа приложения с помощью плагина Ember Flexberry:

* создание БД
* генерация и настройка Backend
* генерация Frontend

3.Доработка приложения в соответствии с поставленным прикладным заданием

4.При необходимости:

* доработка модели (например, если внесены корректировки заказчиком)
* перегенерация приложения

## Аддоны для клиентского приложения

### При генерации Frontend в клиентское приложение устанавливаются следующие аддоны

1.[ember-flexberry](https://github.com/Flexberry/ember-flexberry) (компоненты, роуты, контроллеры и сервисы для “учетного движка” приложения)

2.[ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data) (адаптеры, сериализаторы, сервисы для работы с OData и поддержки офлайн-режима работы)

3.[ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes) ([темы оформления](https://flexberry.github.io/ru/ef2_supported_themes.html) приложения). В технологии Flexberry Ember  стилизация тем реализована с использованием  css-фреймворка Semantic UI. Помимо представленных «из коробки» тем, технологи Flexberry Ember позволяет [создавать новые](https://flexberry.github.io/ru/ef2_themes_creating.html) в соответствии с требованиями проекта.

### Дополнительно можно установить следующие аддоны

4.[ember-flexberry-security](https://github.com/Flexberry/ember-flexberry-security) (клиентские формы для работы с подсистемами аудита и полномочий)

5.[ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis) (клиентская часть для работы с ГИС-подсистемой)

## NuGet-пакеты для серверного приложения

При генерации Backend в серверное приложение устанавливаются следующие NuGet-пакеты:

1. [Flexberry.ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM). [Flexberry ORM](https://flexberry.github.io/ru/fo_landing_page.html) является технологическим компонентом платформы Flexberry, предназначенным для организации слоя доступа к данным.
2. [Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService). [OData-сервис](https://flexberry.github.io/ru/fo_orm-odata-service.html) позволяет получить готовый REST API на основе имеющихся сборок с [объектами данных](https://flexberry.github.io/ru/fo_data-object.html). Кроме того, он предоставляет [возможность](https://flexberry.github.io/ru/efd2_work-files.html) загружать файлы на сервер, скачивать их, а также осуществлять их привязку к свойствам объектов данных. Клиентская часть, в свою очередь, содержит специальную трансформацию для представления файловых свойств на клиенте, и компонент [flexberry-file](https://flexberry.github.io/ru/ef2_file.html) для работы с ними.

## Предварительные требования к установленному ПО, ОС

1. Flexberry Designer
2. Visual Studio 2017 или новее
3. СУБД PostgreSQL не ниже версии 9.x
4. Azure DevOps. Для выполнения работ рекомендуется использовать бесплатный облачный сервис [Azure DevOps Services](https://azure.microsoft.com/ru-ru/services/devops/)
5. NodeJS версии 10.х
6. EmberJS версии 3.1.4 или 2.4.3
7. Последняя версия Bower

Подробнее о настройке перечисленного ПО можно почитать в ["этой"](https://flexberry.github.io/ru/ef2_landing_page.html#%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%BC%D1%8B-%D0%B8-%D0%B8%D0%BD%D1%81%D1%82%D1%80%D1%83%D0%BC%D0%B5%D0%BD%D1%82%D1%8B-%D0%B4%D0%BB%D1%8F-%D0%B3%D0%B5%D0%BD%D0%B5%D1%80%D0%B0%D1%86%D0%B8%D0%B8-ember-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D1%8F) статье.

## Доступные тестовые стенды

1. Тестовый стенд с последней стабильной версией Flexberry Ember: [http://flexberry.github.io/ember-flexberry/dummy/master/](http://flexberry.github.io/ember-flexberry/dummy/master/)
2. Тестовый стенд с последней бета-версией Flexberry Ember: [http://flexberry.github.io/ember-flexberry/dummy/develop/](http://flexberry.github.io/ember-flexberry/dummy/develop/)

## Сайты с документацией и учебными материалами

1. Документация по фреймворку  ["Flexberry Ember"](https://flexberry.github.io/ru/ef3_landing_page.html)
2. Плейлист на YouTube-канале Flexberry Platform ["Разработка приложений на Ember.js и ASP.NET Core"](https://www.youtube.com/playlist?list=PLlhqsC7hBaScz0kuH8ZbA8b5tnA2C3xzF)

## Для разработчиков

На GitHub на странице организации [Flexberry PLATFORM](https://github.com/Flexberry/) находятся  репозитории, в которых доступна доработка компонентов Flexberry Ember через клонирование ветви develop, создании на ее основе ветви доработки и последующей отправке pull request (стандартный процесс Git Flow).

### Репозитории, доступные для доработки

1. [ember-flexberry](https://github.com/Flexberry/ember-flexberry)
2. [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data)
3. [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes)
4. [ember-flexberry-security](https://github.com/Flexberry/ember-flexberry-security)
5. [ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis)
