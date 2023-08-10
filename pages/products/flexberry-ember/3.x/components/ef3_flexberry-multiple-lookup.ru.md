---
title: Flexberry multiple lookup 
keywords: ember, multiple lookup, flexberry-multiple-lookup
sidebar: flexberry-ember-3_sidebar
toc: false
permalink: ru/ef3_flexberry-multiple-lookup.html
lang: ru
summary: Свойства flexberry-multiple-lookup, настройка flexberry-multiple-lookup
---

## Назначение компонента

Flexberry multiple lookup представляет собой элемент управления (компонент), позволяющий выбрать несколько значений мастера. Данный компонент является дополнением к компоненту [Flexberry lookup]().
Общий вид компонента, в случае, если текущая тема оформления “Ghost”:

![](/images/pages/products/flexberry-ember/3.x/components/flexberry-multiply-lookup.png)

## Кастомизация тегов

Доступно с версии ember-flexberry 3.10.0.

Общий вид компонента Flexberry multiple lookup с использованием кастомизации, если текущая тема оформления “Ghost”:

![](/images/pages/products/flexberry-ember/3.x/components/flexberry-multiply-lookup-tag-customization-example.png)

Вы можете посмотреть [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-multiple-lookup/configurate-tags) на тестовом стенде.

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

Чтобы кастомизировать теги, необходимо в объект `tagConfig` указать параментры настройки для нужной записи. Возможные настройки:

| Название свойства | Тип свойства | Описание |
|-|-|-|
| `canBeDeleted` | `Boolean` | Тег может быть удален. |
| `canBeSelected` | `Boolean` | Тег может быть выбран. Возможно будет открыть форму редактирования записи. |
| `customClass` | `String` | Пользовательские классы css для тега. |