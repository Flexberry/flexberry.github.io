---
title: Инструменты ограничения списка
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, фильтрация, OLV, список, поиск, ограничение
toc: true
permalink: ru/ef3_list-restriction-tools.html
lang: ru
summary: Фильтрация списка, ограничение списка, поиск по атрибутам
---

В статье описываются механизмы настройки фильтрации и поиска на списках.

## Выпадающий список как компонент фильтрации

Общий вид компонента [Flexberry Objectlistview](https://flexberry.github.io/ru/efd3_listform.html) с использованием выпадающего списка как компонента фильтрации, если текущая тема оформления “Ghost”:

![dropdown-filter-for-directories](/images/pages/products/flexberry-ember/3.x/components/dropdown-filter-for-directories.png)

[Пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-objectlistview/custom-filter) доступен для ознакомления на тестовом стенде.

### Настройка шаблона формы

В шаблоне формы необходимо указать для компонента `flexberry-objectlistview` свойство `ddlFilterSettings`:

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  ddlFilterSettings=ddlFilterSettings
}}
{% endraw %}
```

### Настройка контроллера формы

Для настройки выпадающего списка как компонента фильтрации необходимо определить массив объектов. Каждый объект должен содержать следующие свойства:

```javascript
[
  {
    modelName: '...', // Имя модели.
    projectionName: '...', // Имя проекции.
    propName: '...', // Имя поля.
    bindingPath: '...' // Путь к свойству модели, относящемуся к текущей ячейке таблицы.
  },
  ...
]
```

В контроллере нужно определить поле `ddlFilterSettings`. Например:

```javascript
import { computed, observer } from '@ember/object';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
  ...
  ddlFilterSettings: computed(function () {
    return [{
      modelName: 'ember-flexberry-dummy-suggestion-type',
      projectionName: 'SuggestionTypeL',
      propName: 'name',
      bindingPath: 'type'
    }];
  })
});
```
