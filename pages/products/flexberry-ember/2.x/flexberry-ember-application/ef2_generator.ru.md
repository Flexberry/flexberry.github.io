---
title: Генератор Flexberry Ember
sidebar: flexberry-ember-2_sidebar
keywords: CASE Plugins, Flexberry Designer, Flexberry Ember
toc: true
permalink: ru/ef2_generator.html
lang: ru
summary: Генерация Ember-приложения из меню Flexberry Designer.
---

## Установка плагина

Требуется установить Flexberry Ember CasePlugin в папку Flexberry Designer.

В конфигурационном файле Flexberry Designer можно добавить настройку, которая отвечает за версию устанавливаемого ember-аддона (при желании можно указать сслыку на git-репозиторий):

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <!-- ... -->
  <appSettings>
  <!-- ... -->
    <add key="EmberPluginAddonName" value="ember-flexberry@0.8.2"/>
  <!-- ... -->
  </appSettings>
  <!-- ... -->
</configuration>
```

## Вызов генератора

Вызов происходит из стадии во [Flexberry Designer](fd_flexberry-designer.html).

Вызывает кликом ПКМ на стадии контекстное меню, выбираем в нем Ember
![Пример](/images/pages/products/flexberry-ember/ember-flexberry/generation/Ember-plugin-menu.jpg)

## Меню генератора

* `Свойства модели` - необходима настройка карты типов аналогично, как это делается для [карты типов](fd_types-map.html) для программного кода.

В процессе генерации типы .NET из карты типов будут автоматически заменены на соответствующие типы Javascript согласно этой таблицы:

| Типы .NET | Типы Javascript
|-----------|----------------|
| bool, bool? | boolean |
| decimal, decimal? | number |
| double, double? | number |
| short, short? | number |
| int, int? | number |
| long, long? | number |
| float, float? | number |
| ushort, ushort? | number |
| uint, uint? | number |
| ulong, ulong? | number |
| DateTime, DateTime? | date |
| NullableDateTime | date |
| NullableInt | number |
| NullableDecimal | number |
| Все остальные типы | number |

* `Frontend`

  * `EmberJS` - генерация Frontend'a на EmberJS
    * `Генерировать` - Выполняется генерация Ember-приложения.
    * `Открыть каталог` - Открывается каталог с Ember-приложением.
  * `Apache Cordova`

* `Backend`
  * ASP.NET
  * JAVA
  * PHP

* `Storage`
  * Microsoft SQL server
  * Postgre SQL
  * Oracle
  * Microsoft Access

* `Утилиты`
* `Информация о лицензии`

## Процесс генерации

На первом этапе происходит создание метаданных (каталог `vendor\flexberry`), которые будут использоваться в командах `ember generate ...`.

Далее генерация может быть выполнена в двух вариантах:

1)При пустом каталоге ember-app - Происходит полная установка приложения, [аддона ember-flexberry](https://github.com/Flexberry/ember-flexberry), а также генерация сущностей приложения Ember командами `ember generate ...`.

При начальном запуске генератора для выбранной модели Flexberry необходимо, чтобы выполнялся вариант 1).

2)При непустом каталоге ember-app - Выполняется только генерация моделей командами `ember generate ...`.

Этот вариант подходит для последующих запусков генератора.

## Перегенерация

`Перегенерация` - это генерация приложения поверх уже имеющегося сгенерированного и доработанного кода, т.е. без перезаписи определённых сущностей этого приложения, которые могли быть изменены прикладным программистом.

Модели находятся в папках:

`app\models` и `app\mixins\regenerated\models`.

Сериалайзеры находятся в папках:

`app\serializers` и `app\mixins\regenerated\serializers`.

В настоящий момент не будут перезаписываться при запуске генерации файлы в папках `app\models` и `app\serializers`.

Для того чтобы изменения из диаграмм Flexberry попадали в приложение Ember, будут использоваться миксины из папок `app\mixins\regenerated\models` и `app\mixins\regenerated\serializers`. Файлы в этих папках будут перезаписываться при каждом запуске процесса генерации.

### Перегенерация файлов локализации

Если в приложении существуют файлы локализации, то при перегенерации они сохраняются и дополняются (при необходимости). Соответственно, удаление более не используемых значений локализации для `ru` и `en` необходимо очищать вручную.

## Ошибки процесса генерации

Если в процессе генерации возникает ошибка, в которой встречается строка:

`... You have to be inside an ember-cli project...`

то необходимо выполнить в каталоге проекта Ember(каталог 'ember-app') следующие команды:

```bash
ember init
ember install ember-flexberry
```

а затем запустить генератор заново.

Так как генератор находится в стадии разработки, то команда `ember install ember-flexberry` будет в дальнейшем изменяться.

### Алгоритм ускорения работы с командами ember init и ember install

* Создать заранее архив node_modules.7z с помощью следующих шагов:

  * Создать новую папку и выполнить в ней команды
    * ember init
    * ember install ember-flexberry
    * npm install
    * bower install
    * npm uninstall ember-flexberry
    * 7z a -r node_modules.7z node_modules

* Перед запуском генератора всегда очищать папку, куда должно генерироваться ember-приложение

  * Скопировать в папку файл node_modules.7z, полученный в п.1 и выполнить команды:

    * 7z x node_modules.7z
    * ember init
    * ember install ember-flexberry
    * npm install
    * bower install

Кроме возможного ускорения работы команд `ember...` будет достигаться актуальность версии `ember-flexberry`.
Для быстрой очистки папки можно не удалять папку `node_modules`, а перемещать её в другое место и затем уже очищать папку с ember-приложением.

## Генерация в ember addon

* Аддон нужно создавать вручную, командой ember addon название-аддона, затем переходим в этот созданный каталог с аддоном
* В аддон нужно установить ember-flexberry командой `ember install ember-flexberry`
* Нужны метаданные для генерации. Пока сам плагин из Flexberry не понимает что от него хотят на выходе аддон, а не обычное приложение. Но метаданные, которые генерируются в папку «vendor\flexberry» универсальны и подходят для обоих вариантов. Нужно сгенерировать приложение при помощи обычного плагина генерации ember-приложений - это будет отдельное приложение, само оно не будет использоваться, нужны только метаданные. После этого есть 2 варианта:
  * Скопировать метаданные из папки vendor\flexberry в такую же папку созданного addon-а.
  * Указывать путь до папки с метаданными при вызове блюпринтов генерации, статья [Blueprints кодогенерация в ember-flexberry](ef2_blueprints.html).
* Вызвать генерацию по метаданным: `ember g flexberry-application`
* Если нужно указывать путь до метаданных, то можно использовать вот такой формат: `ember g flexberry-addon addon --metadata-dir vendor\flexberry`
* Можно генерировать формы и модели для Dummy-приложения этим же самым генератором, для этого нужно выполнять команду генрации с флагом `--dummy`: `ember g flexberry-application app --metadata-dir vendor\flexberry --dummy`
