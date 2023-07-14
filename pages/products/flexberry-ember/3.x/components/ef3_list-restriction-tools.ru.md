---
title: Инструменты ограничения списка
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, фильтрация, OLV, список, поиск, ограничение
toc: true
permalink: ru/ef3_list-restriction-tools.html
lang: ru
summary: Фильтрация списка, ограничение списка, поиск по атрибутам
---

## Выпадающий список как компонент фильтрации

Доступно начиная с версии ember-flexberry 3.9.0.

Общий вид компонента Flexberry Objectlistview с использованием выпадающего списока как компонента фильтрации, если текущая тема оформления “Ghost”:

![](/images/pages/products/flexberry-ember/3.x/components/dropdown-filter-for-directories.png)

Вы можете посмотреть [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-objectlistview/custom-filter) на тестовом стенде.

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

Чтобы настроить выпадающий список как компонент фильтрации, необходимо определить массив объектов. Каждый объект должен содержать следующие свойтва:

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
