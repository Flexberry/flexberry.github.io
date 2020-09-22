---
title: Blueprints кодогенерация в ember-flexberry
sidebar: flexberry-ember-2_sidebar
keywords: blueprints, Прототипизация
toc: true
permalink: ru/ef2_blueprints.html
lang: ru
summary: Кодогенерация средствами аддона ember-flexberry на основе `blueprints`
---

## Прототипизация ember-flexberry приложений на основе OData

Требуется создать новое ember-flexberry приложение. Подробности по установке необходимого для разработки ember-приложений инструментария можно найти на сайте [EmberJS.com](http://emberjs.com/). Версия `ember-cli` должна соответствовать поддерживаемой версии со стороны аддона [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/master/package.json) (см. в секции `devDependencies` версию `ember-cli`).

```bash
ember new foo-bar-sample
cd foo-bar-sample
ember install ember-flexberry
```

Для выполнения прототипизации у вас дожен быть доступный OData Feed, возвращающий метаданные. Для проверки наличия метаданных можно воспользоваться браузером, указав в строке запроса URL с параметром `$metadata`. Пример такого URL:

```
https://flexberry-ember-dummy.azurewebsites.net/odata/$metadata
```

После проверки доступности метаданных можно выполнить прототипизацию приложения командой:

```bash
ember g flexberry-app-prototype --metadata-dir=vendor/Flexberry --odata-feed-url=https://flexberry-ember-dummy.azurewebsites.net/odata/
```

{% include callout.html content="Обратите внимание на то, что в URL указывать `$metadata` не нужно." type="default" %}

После выполнении прототипизации вы можете проверить что в каталоге, указанном параметром `--metadata-dir` у вас появились файлы соответствующего формата, которые вы можете отредактировать в случае необходимости. Теперь нужно выполнить полную генерацию ember-flexberry приложения на основе имеющихся у вас метаданных (см. ниже).

### Правила прототипизации приложений

В метаданных OData v4 присутствует описание данных, но нет описания визуальной составляющей ember-flexberry приложений. Данные, предназначенные для генерации форм создаются автоматически по следующим правилам:

* Для каждой модели создаётся 2 формы:
  * Списковая форма. В представление для списковой формы выносятся все собственные атрибуты, а также первый строковый атрибут мастера (belongsTo) первого уровня. Детейловые связи (hasMany) пропускаются.
  * Форма редактирования. В представление для формы редактирования добавляются все собственные атрибуты, мастера (belongsTo) добавляются с первым попавшимся строковым полем (оно будет использоваться для автодополнения в компоненте flexberry-lookup), также добавляются все детейлы (hasMany) первого уровня.
* В навигационное дерево добавляются все списковые формы.

## Генерация ember-flexberry приложений на основе метаданных

### Полная генерация приложения 

Выполняется следующей командой:

```bash
ember g flexberry-application app --metadata-dir=vendor/Flexberry
```

### Генерация форм


### Генерация моделей


## Формат метаданных ember-flexberry

Для генерации приложения ember-flexberry на основе технологии Blueprints, встроенной в EmberJS используются специального вида метаданные. Под метаданными понимаются файлы определённого формата, содержащие некоторое описание моделей, перечислений, форм и т.д.. Метаданные могут быть получены как при помощи CASE-инструментария, сгенерированы на основе OData-метаданных (в OData свой формат метаданных, в котором нет описания форм и некоторых других элементов ember-flexberry приложений). Также метаданные могут быть созданы самим пользователем.

Генерация ember-flexberry приложения выполняется итеративно, т.е. предполагается, что в первую очередь изменяются метаданные и именно в них содержится правильное описание структуры приложения. После изменения метаданных выполняется повторная генерация ember-flexberry приложения.

### Структура каталогов метаданных

Метаданные могут располагаться в любом каталоге, но как правило, их располагают в папке `vendor/flexberry/`.

* `root folder`
  * models
    * foo.json
    * counrty.json
    * ...
  * enums
    * sex.json
    * state.json
    * ...
  * application
    * sitemap.json
  * edit-forms
    * foo-e.json
    * country-e.json
    * ...
  * list-forms
    * foo-l.json
    * country-l.json
    * ...
  * schema-edit-form.json
  * schema-enum.json
  * schema-list-form.json
  * schema-model.json
  * schema-sitemap.json

В корневом каталоге располагаются файлы с JSON-схемами для проверки валидности метаданных.

### Метаданные моделей

Метаданные моделей располагаются в каталоге `models`. Пример метаданных модели:

```json
{
  "name": "ApplicationUser", // Полное название класса в CASE-инструментарии.
  "modelName": "application-user", // Название модели в ember-приложении.
  "className": "ApplicationUser", // Краткое название класса в CASE-инструментарии.
  "parentModelName": null, // Название родительской модели.
  "parentClassName": null, //Название родительского класса в CASE-инструментарии.
  "attrs": [ // Описание атрибутов модели.
    {
      "name": "name", // Название атрибута.
      "type": "string", // Тип атрибута.
      "flexberryType": "string", // Тип атрибута в CASE-инструментарии.
      "notNull": true, // Допустимы ли null-значения.
      "defaultValue": "" // Значение атрибута по умолчанию.
    },
    {
      "name": "birthday",
      "type": "date",
      "flexberryType": "NullableDateTime",
      "notNull": false,
      "defaultValue": ""
    },
    {
      "name": "gender",
      "type": "gender", // Указание ember-типа перечисления
      "flexberryType": "Gender",
      "notNull": true,
      "defaultValue": ""
    }
  ],
  "belongsTo": [], // belongsTo связи (мастера).
  "hasMany": [], // hasMany связи (детейлы).
  "projections": [ // Описание проекций (представлений).
    {
      "name": "FriendsL",
      "modelName": "application-user",
      "attrs": [
        {
          "name": "name",
          "caption": "Имя",
          "hidden": false,
          "index": 0
        }
      ],
      "belongsTo": [],
      "hasMany": []
    },
    {
      "name": "UserProfileE",
      "modelName": "application-user",
      "attrs": [
        {
          "name": "name",
          "caption": "Name",
          "hidden": false,
          "index": 0
        },
        {
          "name": "birthday",
          "caption": "Birthday",
          "hidden": false,
          "index": 3
        },
        {
          "name": "gender",
          "caption": "Gender",
          "hidden": false,
          "index": 4
        }
      ],
      "belongsTo": [],
      "hasMany": []
    }
  ]
}
```

### Метеданные перечислений

Пример метаданных перечислимого типа:

```json
{
  "nameSpace": "MonstersInc.MonsterEcm", // Пространство имён в CASE-инструментарии.
  "className": "Gender", // Название класса в CASE-инструментарии.
  "enumObjects": { // Значения перечислимого типа.
    "Male": "Мужской", // "Значение": "Заголовок"
    "Female": "Женский",
    "Unknown": "Не указано"
  }
}
```

### Метаданные уровня приложения

На уровне приложения к метаданным относится описание навигационного меню (`sitemap.json`). Пример такого сайтмапа:

```json
{
  "applicationCaption": "My application",
  "applicationTitle": "My application",
  "items": [
    {
      "link": null,
      "menuName": "my-application",
      "caption": "My application",
      "title": "My application",
      "children": [
        {
          "link": "topics-l",
          "menuName": null,
          "caption": "Мои темы",
          "title": "Темы, которые создал я",
          "children": null
        },
        {
          "link": "invites-l",
          "menuName": null,
          "caption": "Приглашения",
          "title": "Темы, поучаствовать в которых меня пригласили",
          "children": null
        },
        {
          "link": "friends-l",
          "menuName": null,
          "caption": "Друзья",
          "title": "Мои друзья",
          "children": null
        }
      ]
    }
  ]
}
```

### Метаданные форм редактирования

Для каждой формы редактирования в приложении создаётся свой файл метаданных. Имя файла с метаданными должно соответствовать имени будущей формы в приложении. Пример метаданных формы редактирования:

```json
{
  "propertyLookup": [],
  "caption": "Друг",
  "name": "UserProfileE",
  "className": "UserProfileE",
  "attrs": [],
  "projections": [
    {
      "modelName": "application-user",
      "modelProjection": "UserProfileE"
    }
  ]
}
```

### Метаданные списковых форм

Для каждой списковой формы в приложении создаётся свой файл метаданных. Имя файла с метаданными должно соответствовать имени будущей формы в приложении. Пример метаданных списковой формы:

```json
{
  "newForm": "user-profile-e",
  "editForm": "user-profile-e",
  "caption": "Друзья",
  "name": "FriendsL",
  "className": "FriendsL",
  "attrs": [],
  "projections": [
    {
      "modelName": "application-user",
      "modelProjection": "FriendsL"
    }
  ]
}
```

## Разработка Blueprints-генераторов

При разработке Blueprint-генераторов используется TypeScript. В качестве редактора кода для TypeScript рекомендуется использовать [Visual Studio Code с настроенным Task-ом компиляции TypeScript в JavaScript](https://code.visualstudio.com/Docs/languages/typescript). Для отладки требуется настроить конфигурацию в файле `launch.json`. Пример настройки конфигурации для отладки:

```JSON
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Запустить",
      "type": "node",
      "request": "launch",
      "program": "C:/Users/WindowsUserName/AppData/Roaming/npm/node_modules/ember-cli/bin/ember",
      "stopOnEntry": false,
      "args": [
        "g",
        "flexberry-app-prototype",
        "--odata-feed-url=http://server:8080/odata"
      ],
      "cwd": "D:/CodeGen/Samples/foo-bar-sample/",
      "preLaunchTask": null,
      "runtimeExecutable": null,
      "runtimeArgs": [
        "--nolazy"
      ],
      "env": {
        "NODE_ENV": "development"
      },
      "sourceMaps": true,
      "console": "externalTerminal"
    }
  ]
}
```