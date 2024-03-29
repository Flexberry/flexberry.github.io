---
title: Генерация Ember-приложения
sidebar: guide-practical-guides_sidebar
keywords: guide, ember-flexberry, flexberry designer, flexberry ember, диаграмма классов, приложение, генерация приложения, node, npm, ember-cli
toc: true
permalink: ru/gpg_ember-application-generation.html
lang: ru
---

Для того, чтобы сгенерировать приложение, требуется установить соответствующие базовые инструменты, а также выполнить настройку генерации как для базы данных, так и для клиентской и серверной частей Ember-приложения.

## Установка базовых инструментов

Для генерации Ember-приложения требуется проверить, что локально установлены требуемые версии **базовых инструментов**:

1. [Node.js](https://nodejs.org/ru/) - версия >=10;
2. [npm](https://github.com/npm/cli) - версия >=6.13;
3. [ember-cli](https://github.com/ember-cli/ember-cli) - версия 3.1.4 (на данный момент мы поддерживаем эту версию фреймворка Ember.js для разработки информационных систем с использованием платформы Flexberry; актуальную информацию об используемых версиях можно найти на странице [соответствующего аддона](https://github.com/Flexberry/ember-flexberry)).

Пакет ember-cli должен быть установлен **глобально**. Для проверки установленной версии по каждому из инструментов необходимо выполнить команды:

> `node -v`  
> `npm -v`  
> `ember -v`

Если какая-либо команда не найдена операционной системой, значит, требуется выполнить установку соответствующего инструмента.

![Версии базовых инструментов в консоли](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-1.png)

Для установки требуемой версии Node.js на ОС Windows можно воспользоватся следующими ссылками:

> [win-x64/](https://nodejs.org/dist/latest-v10.x/win-x64/)  
> [win-x86/](https://nodejs.org/dist/latest-v10.x/win-x86/)

Пакетный менеджер npm будет установлен вместе с Node.js.

Для установки Ember CLI нужно выполнить команду (после установки Node.js):

> `npm install -g ember-cli@3.1.4`

Если на локальной машине установлен пакет ember-cli другой версии, перед установкой ember-cli требуемой версии предварительно удалите ember-cli с использованием следующей команды:

> `npm uninstall -g ember-cli`

После установки соответствующих инструментов ещё раз проверьте, что все команды стали доступны для запуска из консоли.

## Настройка версии ember-flexberry для генерации

Для настройки Flexberry Designer требуется открыть файл конфигурации **CASEBERRY.exe.config**. Для этого в меню `Настройки` выберите пункт `Открыть файл конфигурации...`:

![Открытие файла конфигурации из Flexberry Designer](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-2.png)

Файл **CASEBERRY.exe.config** откроется в редакторе по умолчанию:

![Файл конфигурации Flexberry Designer](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-3.png)

Вы этом файле вы найдете ключ **EmberPluginAddonName**: в качестве значения этого ключа нужно указать версию ember-flexberry **3.3.0**.

## Настройка строки соединения с базой данных

Чтобы выполнить генерацию базы данных из Flexberry Designer, необходимо предварительно настроить соответствующую строку соединения.

Если вы используете Microsoft SQL Server, то способ настройки строки соединения вы можете найти [здесь](https://flexberry.github.io/ru/gpg_configuring-script-generator-db.html).

Если вы используете PostgreSQL (в рассматриваемом примере использована PostgreSQL версия 9.6), то для настройки строки соединения с базой данных вам необходимо знать 4 параметра:

1. хост (host);
2. порт (port);
3. имя пользователя (user ID);
4. пароль (password).

На локальной машине чаще всего используются следующие данные по умолчанию:

`Host=localhost; Port=5432; User ID=postgres`

Пароль для пользователя postgres указывается как правило при установке СУБД на машину. Об установке и настройке PostgreSQL вы можете узнать [тут](https://winitpro.ru/index.php/2019/10/25/ustanovka-nastrojka-postgresql-v-windows/).

Настроим генерацию БД для PostgreSQL с использованием плагина генерации Ember-приложений:

*`Стадия (ember) → [ПКМ] → Ember → Storage → PostgreSQL → Настройка БД`*

![Настройка строки соединения БД на PostgreSQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-4.png)

Добавим в строку соединения следующие данные:

> `Host=localhost;Port=5432;Database=shop;User ID=postgres;Password=<пароль>;`

где <пароль> ‒ пароль для вашей учетной записи пользователя postgres (если вы забыли пароль, то см. <a target="_blank" href="https://oracleplsql.ru/change-a-user-password-postgresql.html">эту статью</a>)

![Окно настройки свойств БД для PostgreSQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-5.png)

Нажимаем кнопку "Сохранить и закрыть".

## Генерация базы данных

Чтобы сгенерировать базу данных для PostgreSQL, выполним следующую **команду**:

*`Стадия (ember) → [ПКМ] → Ember → Storage → PostgreSQL → Привести БД в соответствие с моделью`*

![Приведение БД в соответствие с моделью](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-6.png)

Flexberry Designer сгенерирует DDL-скрипт и предложит его подтвердить (при необходимости сгенерированный DDL-скрипт можно исправить вручную, но обычно это не требуется):

![Код SQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-7.png)

{% include important.html content="Выделенная на скриншоте строка соответствует атрибуту, для которого во Flexberry Designer установлен автоинкремент - в PostgreSQL для этой цели создается последовательность. <u>*Проверьте*</u>, есть ли в DDL-скрипте команды, отвечающие за создание соответствующих последовательностей (в более старых версиях плагина генерации БД для PostgreSQL команды создания последовательностей не генерировались). Если этих команд нет, их необходимо [добавить](https://www.postgresql.org/docs/9.6/sql-createsequence.html) в самое начало DDL-скрипта (в количестве 6 команд, для каждой последовательности). Если ошибок в DDL-скрипте нет, то данный скрипт выполнится успешно после подтверждения, и будет создана соответствующая структура базы данных." %}

## Настройка генерации приложения

Для того, чтобы настроить свойства для генерации приложения, необходимо открыть свойства модели в плагине генерации Ember-приложений у требуемой стадии:

*`Стадия (ember) → [ПКМ] → Ember → Свойства модели`*

![Свойства модели](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-8.png)

В открывшемся диалоговом окне указываем название продукта, которое будет являться **именем приложения**, а также **папку для генерации проекта**:

![Диалоговое окно свойств модели](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-9.png)

Нажимаем "Сохранить и закрыть".

## Генерация проекта

### Генерация бэкенда

Для того, чтобы сгенерировать серверную часть приложения, выполним следующую команду:

*`Стадия (ember) → [ПКМ] → Ember → Backend → ASP.NET → Генерировать`*

![Генерация бэкенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-10.png)

Далее из предложенного списка выбираем генерацию **Всех проектов**:

![Генерировать все проекты](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-11.png)

Если генерация выполнена успешно, то вы увидите следующее сообщение:

![Лог генерации бэкенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-12.png)

Если в процессе генерации возникнут какие-либо ошибки, то в сообщении будет выведен их список. Эти ошибки будет нужно устранить для того, чтобы генерация была выполнена успешно.

### Генерация фронтенда

Для того, чтобы сгенерировать клиентскую часть приложения, нужно выполнить следующую команду:

*`Стадия (ember) → [ПКМ] → Ember → Frontend → EmberJS → Генерировать все`*

![Генерация фронтенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-13.png)

Процесс генерации клиентской части приложения может быть достаточно долгим, так как требует создание проекта Ember.js в указанной папке, а также установки всех необходимых npm-пакетов.

Если генерация клиентской части приложения будет выполнена успешно, то вы увидите следующее сообщение:

![Лог генерации фронтенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-14.png)

Если возникнут **ошибки**, то следует внимательно изучить сообщения об ошибках и постараться их исправить. Если исправить ошибки самостоятельно не получается ‒ обратитесь за помощью: в процессе генерации не все сообщения об ошибках могут выводиться с понятными пояснениями.

## Итог

В результате проделанных действий были сгенерированы три части приложения:

1. База данных (в данном примере мы используем
   [PostgreSQL](https://www.postgresql.org/) ‒ администрирование баз данных СУБД PostgreSQL можно выполнять, например, с использованием приложеняи [pgAdmin](https://www.pgadmin.org/download/));
2. Бэкенд на [ASP.NET Web API](https://dotnet.microsoft.com/apps/aspnet/apis), сконфигурированный для работы со сгенерированной базой данных;
3. Фронтенд на [Ember.js](https://guides.emberjs.com/v3.1.0/).

Далее шаг за шагом мы выполним доработку как клиентской, так и серверной части приложения.

## Перейти

* [Практическое руководство «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Настройка свойств атрибутов класса и типов данных](gpg_autocomplete-and-data-types.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Запуск Ember-приложения](gpg_launch-ember-applications.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
