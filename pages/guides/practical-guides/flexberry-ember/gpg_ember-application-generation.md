---
title: Генерация Ember-приложения
sidebar: guide-practical-guides_sidebar
keywords: guide, ember-flexberry, flexberry designer, flexberry ember, диаграмма классов, приложение, генерация приложения, node, npm, ember-cli
toc: true
permalink: ru/gpg_ember-application-generation.html
lang: ru
---

Для того, чтобы сгенерировать приложение, требуется задать определенные настройки не только для самого приложения, но и для базы данных, с которой будущее ember-приложение будет связано.

## Версии пакетов

Для генерации будущего ember-приложения нам требуется уточнить версии **базовых пакетов**, в частности:

1. Node.js - версия >=10;
2. npm - версия >=6.13;
3. ember-cli - версия 3.1.4;

Эти пакеты должны быть установлены **глобально**. Для проверки текущей версии по каждой из позиций следует выполнить команду:

> `node -v`  
> `npm -v`  
> `ember -v`

![Версии базовых пакетов в консоли](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-1.png)

Для установки валидной версии Node.js: 

> [win-x64/](https://nodejs.org/dist/latest-v10.x/win-x64/)  
> [win-x86/](https://nodejs.org/dist/latest-v10.x/win-x86/)

При установки Node.js npm совместимой версии установится вместе с ним.
Далее в терминале нужно выполнить команду:

> `npm install -g ember-cli@3.1.4`

Проверьте версии пакетов при помощи команды **`<имя пакета> -v`**

## Конфигурация Flexberry Designer

Для настройки Flexberry Designer требуется найти в папке, куда установлен / распакован Дизайнер файл **CASEBERRY.exe.config**. Для этого вызовем свойства приложения (через ярлык в панели задач):

![Поиск файла конфигурации Flexberry Designer](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-2.png)

Скопируйте **расположение** файла, вставьте его в **адресную строку проводника** (если нужно ‒ исключите само имя файла). В открывшейся папке найдите файл **CASEBERRY.exe.config**:

![Файл конфигурации Flexberry Designer](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-3.png)

Вы это файле вы найдете ключ **EmberPluginAddonName**: в качестве значения ключа нужно указать версию ember-flexberry **3.3.0**.

## Настройка генерации базы данных

Если вы используете Microsoft SQL Server, то способ настройки можете найти [здесь](https://flexberry.github.io/ru/gpg_configuring-script-generator-db.html).
Если вы используете PostgreSQL (9.6 для данного проекта), то для настройки генерации базы данных вам необходимо знать 4 параметра:

1. хост (host);
2. порт (port);
3. имя пользователя (user ID);
4. пароль (password).

На локальной машине чаще всего используются следующие данные по умолчанию:

`Host=localhost; Port=5432; User ID=postgres`

Пароль для postgres по умолчанию не установлен: это следует сделать. Об установке и настройке PostgreSQL можете узнать [тут](https://winitpro.ru/index.php/2019/10/25/ustanovka-nastrojka-postgresql-v-windows/).

Настроим генерацию БД для PostgreSQL:

*`Стадия (ember) → [ПКМ] → Ember → Storage → PostgreSQL → Настройка БД`*

![Настройка БД на PostgreSQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-4.png)

Добавим в строку соединения следующие данные:

> `Host=localhost;Port=5432;Database=shop;User ID=postgres;Password=<пароль>;`

где <пароль> ‒ пароль для вашей учетной записи postgres (если забыли, то см. <a href="https://oracleplsql.ru/change-a-user-password-postgresql.html">тут</a>)

![Окно настройки БД на PostgreSQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-5.png)

Нажимаем “Сохранить и закрыть”.

## Генерация базы данных

Чтобы сгенерировать базу данных, выполним следующую **команду**:

*`Стадия (ember) → [ПКМ] → Ember → Storage → PostgreSQL → Привести БД в соответствие с моделью`*

![Приведение БД в соответствие с моделью](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-6.png)

Дизайнер сгенерирует код SQL и предложит его проверить:

![Код SQL](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-7.png)

{% include important.html content="Выделенная строка задает последовательность, которая будет отвечать за автоинкремент. <u>*Проверьте*</u>, есть ли в скрипте команды, отвечающие за создание самой последовательности. Если их нет ‒ их необходимо [добавить](https://www.postgresql.org/docs/9.6/sql-createsequence.html) в самое начало скрипта (в количестве 6 команд, для каждой последовательности).
Если ошибок нет, то скрипт выполнится успешно." %}

## Настройка генерации проекта

Для того, чтобы сгенерировать проект, необходимо вызвать для стадии свойства модели:

*`Стадия (ember) → [ПКМ] → Ember → Свойства модели`*

![Свойства модели](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-8.png)

После этого указываем **имя** и **папку для генерации проекта**:

![Окно свойств модели](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-9.png)

Нажимаем “Сохранить и закрыть”.

## Генерация проекта

### Генерация backend

Для того, чтобы генерировать серверную часть приложения, выполним следующую команду:

*`Стадия (ember) → [ПКМ] → Ember → Backend → ASP.NET → Генерировать`*

![Генерация бэкенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-10.png)

Далее из предложенного списка выбираем **Все**:

![Генерировать все](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-11.png)

Если все успешно и нет никаких ошибок, то вы увидите следующее сообщение:

![Лог генерации бэкенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-12.png)

Если в процессе генерации будут найдены какие-то ошибки, то в сообщении будет выведен их список. Их нужно устранить.

### Генерация frontend

Для того, чтобы генерировать frontend, нужно выполнить следующую команду:

*`Стадия (ember) → [ПКМ] → Ember → Frontend → EmberJS → Генерировать все`*

![Генерация фронтенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-13.png)

Процесс генерации может быть долгим, так как требует создание проекта на ember в указанной папке, а также установки всех зависимостей.
Если все пройдет успешно, то вы увидите сообщение:

![Логи генерации фронтенда](/images/pages/guides/flexberry-ember/3-ember-application-generation/3-14.png)

Если возникнут **ошибки**, то вам следует внимательно изучить сообщение ошибки и постараться исправить. Если исправить самостоятельно не получается ‒ обратитесь за помощью: процесс генерации не всегда показывает достаточно понятные ошибки. Иногда причины неуспешной генерации найти не так просто.

## Итог

В результате генерации приложения мы имеем 3 составляющие:

1. База данных (в данном примере созданная на 
[PostgreSQL](https://www.postgresql.org/) ‒ её можно посмотреть при помощи приложения [pgAdmin](https://www.pgadmin.org/download/));
2. Бэкенд на [ASP.NET Web API](https://dotnet.microsoft.com/apps/aspnet/apis);
3. Фронтенд на [Ember.js](https://guides.emberjs.com/v3.1.0/).

В процессе дальнейшей работы мы выполним доработку фронтенд-части приложения.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Автозаполнение и типы данных](gpg_autocomplete-and-data-types.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Запуск Ember-приложения](gpg_launch-ember-applications.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>
