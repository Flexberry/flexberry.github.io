---
title: Обзор технологии Flexberry Ember
sidebar: flexberry-ember-3_sidebar
keywords: mydoc
summary: Цели, возможности, архетектура, особенности и инструменты разработки Flexberry Ember
toc: true
permalink: ru/ef3_technology-overview.html
lang: ru
---

## Назначение и основные возможности технологии Flexberry Ember

**Назнечением** технологии Flexberry Ember является быстрое создание прототипа приложения, имеющего базовый функционал учетной системы "из коробки", а также предоставление разработчикам готовых компонент, сервисов и средств работы с данными для упрощения разработки сложных бизнес-приложений, включая веб- и мобильные приложения на базе фреймворка [Ember.js](https://emberjs.com/).

Основные возможности технологии Flexberry Ember:

1. [Генерация Ember-приложения](https://flexberry.github.io/ru/ef2_generator.html) с функционалом учетной системы из приложения [Flexberry Designer](https://flexberry.github.io/ru/fd_flexberry-designer.html)
2. Поддержка [офлайн-режима](https://flexberry.github.io/ru/efd2_offline.html) работы Ember-приложения
3. Поддержка [протокола OData](https://flexberry.github.io/ru/efd2_odata.html)
4. Поддержка [работы с файлами](https://flexberry.github.io/ru/efd2_work-files.html)
5. Предоставление возможностей для использования Ember-приложения в качестве [мобильного приложения Apache Cordova](https://flexberry.github.io/ru/ef2_cordova.html) на той же кодовой базе

## Архитектура приложений на Flexberry Ember

![Архитектура приложений](/images/pages/products/flexberry-ember/ember-flexberry/getting-started/application-architecture.png)

Как следует из схемы, приложения, созданные с использованием фреймворка Flexberry Ember, состоят из трех основных частей:

* базы данных
* серверной бизнес-логики
* пользовательского интерфейса (клиентская бизнес-логика)

Приложения, реализованные с использованием фреймворка Flexberry Ember, представляют собой [одностраничные приложения](https://ru.wikipedia.org/wiki/%D0%9E%D0%B4%D0%BD%D0%BE%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%87%D0%BD%D0%BE%D0%B5_%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5) (Single Page Application, SPA). Клиент и сервер взаимодействуют с использованием [REST API](https://starkovden.github.io/what-is-rest-api.html) (используется протокол [OData](https://www.odata.org/documentation/)), который обеспечивает создание, изменение и удаление бизнес-объектов приложения. Клиентская часть приложения реализуется с использованием фреймворка [Ember.js](http://emberjs.com/), и содержит как бизнес-лоогику приложения, так и реализацию пользовательсткого интерфейса приложения. В свою очередь фреймворк Ember.js использует такие языки и библиотеки как [Handlebars](https://handlebarsjs.com/), [CSS](http://htmlbook.ru/samcss) и [JavaScript](https://learn.javascript.ru/) для разработки приложений. Темы оформления приложений на Flexberry Ember реализованы с использованием CSS-фреймворка [Semantic UI](https://semantic-ui.com/).

На сервере также реализуется часть бизнес-логики приложения, а также обеспечивается непосредственное взаимодействие с базами данных приложения.

Приложения на Flexberry Ember реализуются с использованием следующих технологий:

1. Backend реализуется в виде приложения [ASP.NET Web API](https://dotnet.microsoft.com/apps/aspnet/apis) с использованием [Flexberry ORM](https://flexberry.github.io/ru/fo_landing_page.html) и [Flexberry ORM ODataService](https://flexberry.github.io/ru/fo_orm-odata-service.html).
2. Frontend реализуется на базе фреймворка [Ember.js](https://emberjs.com/) и [Apache Cordova](https://cordova.apache.org/) (при необходимости разработать мобильное приложение) с использованием аддонов, входящих в состав технологии Flexberry Ember.
3. База данных может работать под управлением СУБД [PostgreSQL](https://www.postgresql.org/docs/), [MS SQL Server](https://aka.ms/sqldocs) или [Oracle](https://docs.oracle.com/en/database/index.html).

## Цикл разработки приложений на Flexberry Ember

![Цикл разработки приложений](/images/pages/products/flexberry-ember/ember-flexberry/getting-started/application-development-cycle.png)

1.Создание модели приложения на языке [UML](http://uml.org/) с помощью [Flexberry Designer](https://flexberry.github.io/ru/fd_flexberry-designer.html)

2.Генерация прототипа приложения с помощью плагина Ember Flexberry:

* создание БД
* генерация и настройка Backend
* генерация Frontend

3.Доработка приложения в соответствии с поставленным прикладным заданием

4.При необходимости:

* доработка модели (например, если требования были изменены заказчиком)
* перегенерация приложения

## Аддоны для клиентского приложения

При генерации Frontend в клиентское приложение устанавливаются следующие аддоны:

1. [ember-flexberry](https://github.com/Flexberry/ember-flexberry) (компоненты, роуты, контроллеры и сервисы для “учетного движка” приложения)

2. [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data) (адаптеры, сериализаторы, сервисы для работы с OData и поддержки офлайн-режима работы)

3. [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes) ([темы оформления](https://flexberry.github.io/ru/ef2_supported_themes.html) приложения). В технологии Flexberry Ember  стилизация тем реализована с использованием  CSS-фреймворка [Semantic UI](https://semantic-ui.com/). Помимо представленных «из коробки» тем, технологи Flexberry Ember позволяет [создавать новые](https://flexberry.github.io/ru/ef2_themes_creating.html) в соответствии с требованиями проекта.

Дополнительно можно установить следующие аддоны:

4. [ember-flexberry-security](https://github.com/Flexberry/ember-flexberry-security) (клиентские формы для работы с подсистемами [аудита](https://flexberry.github.io/ru/fau_landing_page.html) и [полномочий](https://flexberry.github.io/ru/fo_landing_page.html))

5. [ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis) (клиентская часть для работы с [ГИС-подсистемой](https://flexberry.github.io/ru/fg_landing_page.html))

## NuGet-пакеты для серверного приложения

При генерации Backend в серверное приложение устанавливаются следующие NuGet-пакеты:

1. [Flexberry.ORM](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM). [Flexberry ORM](https://flexberry.github.io/ru/fo_landing_page.html) является технологическим компонентом платформы Flexberry, предназначенным для организации слоя доступа к данным.
2. [Flexberry.ORM.ODataService](https://www.nuget.org/packages/NewPlatform.Flexberry.ORM.ODataService). [OData-сервис](https://flexberry.github.io/ru/fo_orm-odata-service.html) позволяет получить готовый REST API на основе имеющихся сборок с [объектами данных](https://flexberry.github.io/ru/fo_data-object.html). Кроме того, он предоставляет [возможность](https://flexberry.github.io/ru/efd2_work-files.html) загружать файлы на сервер, скачивать их, а также осуществлять их привязку к свойствам объектов данных. Клиентская часть, в свою очередь, содержит специальную [трансформацию](https://flexberry.github.io/ru/efd2_work-files.html#%D1%84%D0%B0%D0%B9%D0%BB%D0%BE%D0%B2%D1%8B%D0%B5-%D1%81%D0%B2%D0%BE%D0%B9%D1%81%D1%82%D0%B2%D0%B0-%D0%BE%D0%B1%D1%8A%D0%B5%D0%BA%D1%82%D0%BE%D0%B2-%D0%B4%D0%B0%D0%BD%D0%BD%D1%8B%D1%85-%D0%B2-ember) для представления файловых свойств на клиенте, и компонент [flexberry-file](https://flexberry.github.io/ru/ef2_file.html) для работы с ними.

## Предварительные требования к установленному ПО

1. [Flexberry Desinger](https://flexberry.github.io/ru/fd_flexberry-designer.html). Для работы с Flexberry Designer, Вы можете воспользоваться либо [Flexberry Designer Online](https://flexberry.github.io/ru/fd_landing_page.html), либо [установить Flexberry Desinger Desktop](https://flexberry.github.io/ru/fd_install.html). При первой установке или использовании Flexberry Designer генерируется демо-лицензия на 14 дней. Далее её можно [продлить](https://designer.flexberry.net/#/download-win-app) на срок от 6 до 12 месяцев. Для студентов колледжей и ВУЗов предоставляется бесплатная [академическая лицензия](https://flexberry.net/ru/education-licenses.html) на полгода с возможностью её продления в течение периода обучения в соответствующем учебном заведении.
2. [Visual Studio 2017](https://visualstudio.microsoft.com/vs/) или новее для разработки серверной части приложений. 
3. [Visual Studio Code](https://code.visualstudio.com/) (рекомендуем к использованию), [Sublime Text](http://www.sublimetext.com/) или их [аналоги](https://jpnsoft.ru/visual-studio-code/) для разработки клиентской части приложений.
4. СУБД [PostgreSQL](https://www.postgresql.org/) не ниже версии 9.x.
5. Аккаунт на [GitHub](https://github.com/) или [Azure DevOps Services](https://azure.microsoft.com/ru-ru/services/devops/). Для выполнения работ внутри организации также можно установить [Azure DevOps Server](https://azure.microsoft.com/ru-ru/services/devops/server/) или развернуть [GitLab](https://about.gitlab.com/install/).
6. [NodeJS](https://nodejs.org/) версии 10.х или выше, включая пакетный менеджер [npm](https://www.npmjs.com/) (устанавливается вместе с Node.js).
7. [EmberJS](https://emberjs.com/) версии 3.1.4.
8. Пакетный менеджер [Bower](https://bower.io/) последней версии.

### Настройка NPM

При работе в ОС Windows необходимо проверить, что в переменной окружения PATH для текущего пользователя содержится путь `%USERPROFILE%\AppData\Roaming\npm`.

### Настройка Flexberry Desinger

Для использования конкретной версии аддона [ember-flexberry](https://github.com/Flexberry/ember-flexberry) при генерации приложений из Flexberry Deigner, требуется в файле конфигурации CASEBERRY.exe.config установить значение ключа `EmberPluginAddonName` следующим образом:

```xml
<appSettings>
  ...
  <add key="EmberPluginAddonName" value="ember-flexberry@3.5.0" />
  ...
</appSettings>
```

## Доступные тестовые стенды

1. Тестовый стенд для Flexberry Ember 3.х (новая тема оформления): [http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/)
2. Тестовый стенд для Flexberry Ember 3.х (старая тема оформления): [http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/)

## Сайты с документацией и учебными материалами

1. [Документация](https://flexberry.github.io/ru/ef3_landing_page.html) по фреймворку Flexberry Ember
2. [Практическое руководство](https://flexberry.github.io/ru/ef3_landing_page.html) по Flexberry Ember
2. Плейлист ["Разработка приложений на Ember.js и ASP.NET Core"](https://www.youtube.com/playlist?list=PLlhqsC7hBaScz0kuH8ZbA8b5tnA2C3xzF) на [YouTube-канале Flexberry Platform](https://www.youtube.com/user/FlexberryPLATFORM)

## Для разработчиков

На странице организации [Flexberry PLATFORM на GitHub](https://github.com/Flexberry/) находятся  репозитории, в которых доступна доработка аддонов, входящих в состав фреймворка Flexberry Ember, через клонирование соответствующего репозитория, создании ветки доработки на основе ветки `develop`, и последующей отправке [Pull Request](https://docs.github.com/en/github/collaborating-with-issues-and-pull-requests/about-pull-requests) (стандартный процесс [git-flow](https://nvie.com/posts/a-successful-git-branching-model/)).

### Репозитории, доступные для доработки

1. [ember-flexberry](https://github.com/Flexberry/ember-flexberry)
2. [ember-flexberry-data](https://github.com/Flexberry/ember-flexberry-data)
3. [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes)
4. [ember-flexberry-security](https://github.com/Flexberry/ember-flexberry-security)
5. [ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis)
