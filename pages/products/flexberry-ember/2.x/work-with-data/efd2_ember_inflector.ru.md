---
title: Использование ember-inflector
sidebar: flexberry-ember-2_sidebar
keywords: ember, inflector, ember-inflector, custom-inflector-rules
toc: true
permalink: ru/efd2_ember_inflector.html
lang: ru
summary: Ember Inflector - библиотека для преобразования слов между формами множественного и единственного числа
---

# Использование Ember Inflector

[`Ember Inflector`](https://github.com/emberjs/ember-inflector) — это библиотека для преобразования слов между формами множественного и единственного числа.

## Зачем это нужно

Это необходимо для правильной работы библиотеки [`Ember Data`](https://github.com/emberjs/data/tree/v2.8.0).

`Ember Data` — это библиотека для надежного управления данными модели. Она независима от базового механизма сохранения, поэтому он работает как с API-интерфейсами JSON через HTTP, так и с потоковой передачей WebSockets или локальным хранилищем IndexedDB.

До того, как будет отправлено сообщение в OData-backend, происходит преобразование имени типа сущности из единственного числа во множественное и обратно. Например: ***cat -> cats***.

Таким образом вместо http://localhost:6500/odata/Cat запрос пойдет на http://localhost:6500/odata/Cats.

Такое преобразование происходит внутри *Ember Data*, где идет обращение к библиотеке *Ember Inflector*.

## Установка

```cmd
ember install ember-inflector
```

## Использование

Для установки дополнительных правил добавляется инициализатор:

```javascript
import Inflector from 'ember-inflector';

export function initialize() {
  const inflector = Inflector.inflector;

  // Тут добавляются правила преобразования...
}

export default {
  name: 'inflector-rules',
  initialize
};
```

Либо добавляется файл `models/custom-inflector-rules.js`

```javascript
import Inflector from 'ember-inflector';

const inflector = Inflector.inflector;

// Тут добавляются правила преобразования...

export default {};
```

А в файл `app.js` добавляется строчка импорта

```javascript
import './models/custom-inflector-rules';
```

## Основные методы Ember Inflector

Есть два основных метода `singularize`, `pluralize` и несколько дополнительных для настройки преобразования.

* [`singularize`](https://github.com/emberjs/ember-inflector/blob/efc43cababbab05d55f275529455f85c46b9b492/addon/lib/system/inflector.js#L226C9-L226C9) - преобразование из множественного числа в единственное.

```javascript
Inflector.inflector.singularize("cats"); // cat
```

* [`pluralize`](https://github.com/emberjs/ember-inflector/blob/efc43cababbab05d55f275529455f85c46b9b492/addon/lib/system/inflector.js#L215C8-L215C8) - преобразование из единственного числа во 
множественное.

```javascript
Inflector.inflector.pluralize("cat"); // cats
```

* [`irregular`](https://github.com/emberjs/ember-inflector/blob/efc43cababbab05d55f275529455f85c46b9b492/addon/lib/system/inflector.js#L206) - добавить нестандартное соответствие преобразования. Принимает два аргумента: *singular* - вариант единственного числа, *plural* - вариант множественного числа.

```javascript
Inflector.inflector.irregular("cat", "dog");
Inflector.inflector.pluralize("cat"); // dog
```

* [`uncountable`](https://github.com/emberjs/ember-inflector/blob/efc43cababbab05d55f275529455f85c46b9b492/addon/lib/system/inflector.js#L196C34-L196C34) - добавить исключение преобразования. Оно никак не будет преобразовываться.

```javascript
Inflector.inflector.uncountable("cat");
Inflector.inflector.pluralize("cat"); // cat
```

Так же это работает и на окончание названий моделей:

```javascript
Inflector.inflector.irregular("cat", "dog");
Inflector.inflector.pluralize("ember-flexberry-cat"); // ember-flexberry-dog
```

## Пример файла custom-inflector-rules

```javascript
import Inflector from 'ember-inflector';

const inflector = Inflector.inflector;

inflector.irregular('document', 'documents');
inflector.irregular('request', 'requests');
inflector.irregular('user', 'users');
inflector.irregular('profile', 'profiles');
inflector.irregular('entity', 'entitys');
inflector.irregular('zony', 'zonys');
inflector.irregular('zayavlenie', 'zayavlenies');
inflector.irregular('rights', 'rightss');
inflector.irregular('notifications', 'notificationss');
inflector.irregular('news', 'newss');
inflector.irregular('themes', 'themess');
inflector.irregular('municipality', 'municipalitys');
inflector.irregular('files', 'filess');
inflector.irregular('class', 'classs');
inflector.irregular('category', 'categorys');

inflector.uncountable('stages');

export default {};

```

## Места где Ember Data обращается к Ember Inflector

Версия 2.8.0:
* [relationship-meta](https://github.com/emberjs/data/blob/534577f0db5ac88a797c5635c44cb409b901f798/addon/-private/system/relationship-meta.js#L9) - получение имени типа для отношения hasMany (детейл).
* [json-api](https://github.com/emberjs/data/blob/v2.8.0/addon/serializers/json-api.js) - формирование JSON запроса к источнику данных.

Версия 3.1.1:
* [build-url-mixin](https://github.com/emberjs/data/blob/9466a512c28d1cdf1802a05bce42fa7beb005bdd/addon/-private/adapters/build-url-mixin.js#L441) - построение URL для запроса к источнику данных.
* [relationship-meta](https://github.com/emberjs/data/blob/9466a512c28d1cdf1802a05bce42fa7beb005bdd/addon/-private/system/relationship-meta.js#L9) - получение имени типа для отношения hasMany (детейл).
* [json-api](https://github.com/emberjs/data/blob/v3.1.1/addon/serializers/json-api.js) - формирование JSON запроса к источнику данных.