---
title: Выбор нескольких значений мастера 
keywords: ember, multiple lookup, flexberry-multiple-lookup
sidebar: flexberry-ember-3_sidebar
toc: false
permalink: ru/ef3_flexberry-multiple-lookup.html
lang: ru
summary: Свойства flexberry-multiple-lookup, настройка flexberry-multiple-lookup
---

## Назначение компонента

`Flexberry multiple lookup` представляет собой элемент управления (компонент), позволяющий выбрать несколько значений мастера. Данный компонент является дополнением к компоненту [Flexberry lookup](https://flexberry.github.io/ru/ef3_flexberry-lookup.html).
Общий вид компонента в случае, если текущая тема оформления “Ghost”:

![flexberry-multiply-lookup](/images/pages/products/flexberry-ember/3.x/components/flexberry-multiply-lookup.png)

## Кастомизация тегов

Общий вид компонента `Flexberry multiple lookup` с использованием кастомизации, если текущая тема оформления “Ghost”:

![flexberry-multiply-lookup-tag-customization-example](/images/pages/products/flexberry-ember/3.x/components/flexberry-multiply-lookup-tag-customization-example.png)

[Пример реализации](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-multiple-lookup/configurate-tags) компонента можно посмотреть на тестовом стенде Ember Flexberry.

### Настройка шаблона формы

В шаблоне формы необходимо указать для компонента `flexberry-multiple-lookup` свойство `configurateTag`:

```hbs
{% raw %}
{{flexberry-multiple-lookup
  ...
  configurateTag=(action "configurateTag")
}}
{% endraw %}
```

### Настройка контроллера формы

В контроллере нужно определить "экшн", который принимает два аргумента: `tagConfig` и `record`. Например:

```javascript
import { set } from '@ember/object';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
  actions: {
    configurateTag(tagConfig, record) {
      set(tagConfig, 'canBeDeleted', false);
      if (record === this.get('myFavoriteRecord')) {
        set(tagConfig, 'canBeSelected', false);
        set(tagConfig, 'customClass', 'my-fav-record');
      }
    }
  }
});
```

Для кастомизации тегов необходимо в объекте `tagConfig` указать параметры настройки для нужной записи. Возможные настройки:

| Название свойства | Тип свойства | Описание |
|-|-|-|
| `canBeDeleted` | `Boolean` | Тег может быть удален. |
| `canBeSelected` | `Boolean` | Тег может быть выбран. Возможно будет открыть форму редактирования записи. |
| `customClass` | `String` | Пользовательские классы css для тега. |
