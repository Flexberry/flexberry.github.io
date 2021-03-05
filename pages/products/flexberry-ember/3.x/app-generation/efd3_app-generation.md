---
title: Генерация приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_app-generation.html
lang: ru
summary: Настройка и генерация метаданных приложения, генерация разными способами фронтенда и бакэнда.
---

## Подготовка к генерации

Для того, чтобы сгенерировать приложение Flexberry Ember, необходимо во [Flexberry Designer](fd_flexberry-designer.html) представить предметную область в виде [диаграммы классов](gpg_class-diagram.html).

{% include note.html content="[Построение других UML-диаграмм](gpg_practical-guides-uml.html) также необходимо для глубокого анализа и проработки предметной области, однако непосредственно генерация производится по [диаграмме классов](gpg_class-diagram.html)." %}

{% include important.html content="При разработке приложений на Flexberry Ember [рекомендовано использовать английский язык](gpg_setting-language-and-structure.html) для именования тех элементов модели, с которыми работает разработчик." %}

В [диаграмме классов](gpg_class-diagram.html) должны быть [сгенерированы](gpg_create-and-configure-application-structure.html) или созданы вручную [представления и формы](gpg_create-and-configure-application-structure.html), [настроены контейнеры](gpg_create-and-configure-application-structure.html) (впоследствии на основании настроек контейнеров в [контроллер](https://guides.emberjs.com/v3.1.0/controllers/) "application" [после генерации будет добавлена структура](efd3_generated-app-structure.html), соответствующая сайтмапу приложения Flexberry Ember).

## Плагин генерации для Flexberry Ember во Flexberry Designer

Для корректной работы плагина генерации для Flexberry Ember во Flexberry Designer требуется проверить, что на компьютере установлено [необходимое программное обеспечение](gpg_ember-application-generation.html).

Далее наобходимо [установить плагин](fd_flexberry-plugins.html) Flexberry Ember CasePlugin в папку [Flexberry Designer](fd_flexberry-designer.html) (или удостовериться, что он уже установлен).

В конфигурационном файле Flexberry Designer можно добавить настройку, которая отвечает за версию устанавливаемого ember-аддона:

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  ...
  <appSettings>
    ...
    <add key="EmberPluginAddonName" value="ember-flexberry@3.5.0"/>
  </appSettings>
  ...
</configuration>
```

Или при желании можно указать прямо ссылку на [git-репозиторий](https://github.com/Flexberry/ember-flexberry):

```xml
<add key="EmberPluginAddonName" value="https://github.com/Flexberry/ember-flexberry.git#develop" />
```

Вызов плагина происходит из стадии во [Flexberry Designer](fd_flexberry-designer.html), где после клика правой кнопкой мыши на стадии в контекстном меню выбираем пункт "Ember".

### Меню плагина генерации

Плагин генерации для Flexberry Ember во Flexberry Designer предоставляет следующие возможности посредством меню:

![Меню плагина генерации для Flexberry Ember во Flexberry Designer](/images/pages/products/flexberry-ember/ember-flexberry/generation/FE-plugin.png)

* `Свойства модели` - основные настройки генерации: расположение генерируемого кода, название продукта, общепроектные настройки, [карта типов](fd_types-map.html).

В процессе генерации типы .NET из карты типов будут автоматически заменены на соответствующие типы Javascript согласно этой таблицы:

| Типы .NET | Типы Javascript
|-----------|----------------|
| bool, bool? | boolean |
| decimal, decimal?, double, double?, short, short?, int, int?, long, long?, float, float?, ushort, ushort?, uint, uint?, ulong, ulong?, NullableInt, NullableDecimal | number |
| DateTime, DateTime?, NullableDateTime | date |
| Все остальные типы | number |

* `Создать представления, формы и приложение` - создание быстрого прототипа на базе реализованной [диаграммы классов](gpg_class-diagram.html) (часть созданных элементов может потребоваться [донастроить вручную](gpg_create-and-configure-application-structure.html)).

* `Frontend` - подменю, связанное с генерацией фронтенда.
  * `EmberJS` - генерация фронтенда на EmberJS
    * `Генерировать всё` - генерация
    * `Генерировать метаданные` - генерация метаданных, об этом ниже.
    * `Компилировать` - сборка сгенерированного приложения (в общем случае это приводит к запуску команды [Ember CLI](https://cli.emberjs.com/release/) [`ember build -e development`](https://cli.emberjs.com/release/basic-use/cli-commands/)).
    * `Запустить сервер` - запуск сгенерированного приложения (в общем случае это приводит к запуску команды [Ember CLI](https://cli.emberjs.com/release/) [`ember serve`](https://cli.emberjs.com/release/basic-use/cli-commands/)).
    * `Открыть каталог` - открытие папки со сгенерированным кодом.
  * `Apache Cordova` - генерация [фронтенда на Apache Cordova]()
    * `Генерировать проект Apache Cordova`
    * `Собрать мобильное приложение`

* `Backend`  - подменю, связанное с генерацией бакенда.
  * `ASP.NET` - генерация бакенда для [ASP.NET](https://dotnet.microsoft.com/apps/aspnet).
    * `Генерировать`
    * `Открыть каталог`
    * `Открыть в Visual Studio...`
  * `ASP.NET Core 3.1` - генерация бакенда для [ASP.NET Core 3.1](https://dotnet.microsoft.com/learn/aspnet/what-is-aspnet-core).
    * `Генерировать`
    * `Открыть каталог`
    * `Открыть в Visual Studio...`
  * `JAVA` - в настоящий момент данный пункт меню находится в разработке.
  * `PHP` - в настоящий момент данный пункт меню находится в разработке.

* `Docker` - работа с [Docker](gbt_deployment_docker.html).
  * `Создать Dockerfile`
  * `Создать Dockerfile для автоматической сборки`
  * `Создать скрипт сборки образов`
  * `Создать Swarn-конфигурацию и скрипт запуска Swarn-конфигурации`
  * `Запустить Swarn-конфигурацию`

* `Storage` - подменю, связанное с генерацией скриптов для приложения для разных СУБД (пункты меню у разных СУБД аналогичны, поэтому указаны только для Postgre SQL).
  * `Microsoft SQL server` - настройки и генерация скриптов для [Microsoft SQL server](https://ru.wikipedia.org/wiki/Microsoft_SQL_Server).
  * `Postgre SQL` - настройки и генерация скриптов для [Postgre SQL](https://www.postgresql.org/).
    * `Настройка БД`
    * `Привести БД в соответствие с моделью`
    * `Сгенерировать SQL`
    * `Полномочия`
      * `Создать полномочия`
      * `Создать пользователя по умолчанию`
  * `ClickHouse` - настройки и генерация скриптов для [ClickHouse](https://clickhouse.tech/docs/ru/).
  * `Oracle` - настройки и генерация скриптов для [Oracle](https://www.oracle.com/ru/index.html).
  * `Microsoft Access` - настройки и генерация БД для [Microsoft Access](https://ru.wikipedia.org/wiki/Microsoft_Access).

* `Утилиты` - [вспомогательные утилиты](fo_orm-case-plugin.html) для работы со стадией.
  * `Менеджер классов`
  * `Менеджер ассоциаций`
  * `Менеджер наследований`
  * `Менеджер представлений`
  * `Менеджер запусков генерации`
  * `Обновить представления`
  * `Найти ошибки в модели`
  * `Экспорт стадии`
  * `Сохранить диаграммы в формате wmf`
  * `Генерация документации по диаграммам`
  * `Переместить...`
  * `История изменений стадии`
  * `Создать бекап`

* `Информация о лицензии`

## Генерация метаданных приложения

## Генерация фронтенда. Блюпринты для генерации. Дефолтные блюпринты

## Генерация клиентского приложения из командной строки

## Генерация бакенда

## Установка пакета ODataService «вручную» в приложение ASP.NET Web API
