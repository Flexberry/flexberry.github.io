---
title: Правила написания комментариев с автодокументацией в Flexberry Ember
sidebar: flexberry-platform_sidebar
keywords: code style, yuidoc, comments, autodoc
toc: true
permalink: ru/fp_ember-comments-style-yuidoc.html
lang: ru
summary: Существуют определенные правила написания комментариев для корректного формирования автодокументации в ember-flexberry и других ember-проектах. Все комментарии пишутся по правилам YUIDoc.
---

## Правила YUIDoc

В EmberJS-проектах платформы Flexberry принят стандарт оформления комментариев - [YUIDoc](http://yui.github.io/yuidoc/syntax/index.html). Данные комментарии используются для формирования автодокументации к коду, описывающей `API` разрабатываемых модулей платформы Flexberry.

### Указание приватных свойств и методов

 Все приватные свойства и методы должны начинаться со знака `_`, например:

``` javascript
_somePrivateProperty: null
```

Все внутренние для классов свойства, например, вычисляемые на основе публичных, всегда должны быть приватными.
**Для компонентов публичными считаются свойства** через которые в компонент отдаются его настройки, данные и прочие свойства, указываемые в шаблоне формы.
Для других классов аналогично.

### Порядок расположения свойств и методов внутри классов

Внутри классов порядок расположения свойств и методов должен быть следующим:
1. private-свойства
2. public-свойства
3. actions
4. public-методы (первыми должны идти методы init и didInsertElement, если они есть, а также прочие перегружаемые "эмберные" методы)
5. private-методы

### Имена методов для событий

Имена методов для action-ов документировать с префиксом **actions.<имя_метода>** (чтобы action-ы видеть сразу по именам методов в документации). Например:

``` javascript
// ...
actions: {
  /**
    This action is called when Superman fall from skyscraper.

    @method actions.someAction
  */
  someAction: function() {
    // ...
  }
}
// ...
```

### Описание событий посылаемых компонентами или другими классами "наружу"

Action-ы посылаемые компонентами, или другими классами "наружу" (через метод sendAction), нужно описывать в конце класса, в комментариях, не привязанных к каким-то свойствам и методам, **с префиксом sendingActions.<имя_action-a>**.

``` javascript
/**
Component's action invoking when checkbox was clicked and it's 'checked' state changed.

@method sendingActions.change
@param {Object} e Action's event object.
@param {Boolean} e.newValue Component's new value.
@param {Object} e.originalEvent [jQuery event object](http://api.jquery.com/category/events/event-object/)
which describes inner input's 'change' event.
*/
```

А в комментариях к методам, которые вызывают `sendAction` внутри себя, нужно ссылаться на посылаемые "наружу" action-ы.

``` javascript
/**
  Handles inner input's bubbled 'change' action.
  Invokes component's `#crossLink "MyComponent/sendingActions.change:method"`'change'`/crossLink` action.

  @method actions.change
  @param {Object} e [jQuery event object](http://api.jquery.com/category/events/event-object/)
  which describes inner input's 'change' event.
*/
change(e) {
  let checked = e.target.checked === true;

  // Invoke component's custom 'change' action.
  this.sendAction('change', {
    newValue: checked,
    originalEvent: e
  });
}
```

### Документирование перегруженных свойств и методов

Все перегруженные "эмберные" свойства и методы можно подробно не документировать, т.к. в публичную автодокументацию включать еще раз описание "стандартных" свойств и методов, которые есть в автодокументации ember-а, смысла нет.
**Документировать перегруженные "эмберные" методы имеет смысл, если изменились их сигнатуры** (например, добавились параметры).
Достаточно такого описания:

``` javascript
/**
  initializes component.
*/
init() {
 // ...
}
```

### Ссылки на внешнюю документацию

Если где-то требуется сослаться на внешнюю документацию ember-a или еще куда-то вовне, то `yuidoc` поддерживает `html` или `markdown`-синтаксис прямо в комментариях.
Например:

``` javascript
/**
  Query for records that meet certain criteria. Resolves with [DS.RecordArray](http://emberjs.com/api/data/classes/DS.RecordArray.html).
  For more information see [query](http://emberjs.com/api/data/classes/DS.Store.html#method_query) method of [DS.Store](http://emberjs.com/api/data/classes/DS.Store.html).
  @method query
  @param {String} modelName
  @param {Object} query
  @param {Boolean} [query.useOnlineStore] Allow to explicitly specify online or offline store using independently of global online status
  @return {Promise} promise
*/
query(modelName, query) {
  // ...
}
```

### Указание родительского класса

В автодокументации к классам надо не забывать указывать родительский класс (`@extends`) и какие миксины класс использует (`@uses`).
Если в `@extends` указывается эмберный родительский класс (или класс из какого-то внешнего аддона), или в `@uses` используется эмберный миксин (или миксин из какого-то внешнего аддона) то прописывать его нужно ссылкой (через `#crossLink`..`/crossLink`):

``` javascript
/**
  This should be used as store:main

  @class BaseStore
  @extends <a href="http://emberjs.com/api/data/classes/DS.Store.html">DS.Store</a>
*/
export default DS.Store.extend({
  // ...
});
```

## Генерация автодокументации на основе комментариев

Чтобы посмотреть локально, как будет выглядеть документация, надо в папке с исходным кодом, например `ember-flexberry/addon` выполнить команду:

``` bash
yuidoc . --server
```

Если это не было сделано ранее, то перед запуском надо установить `yuidoc` командой:

``` bash
npm install -g yuidocjs
```

Подробнее синтаксис `YUIDoc` можно посмотреть тут: [http://yui.github.io/yuidoc/syntax/index.html](http://yui.github.io/yuidoc/syntax/index.html).