---
title: Configuring filters on lists
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember, filters, OLV, list
summary: Features of setting and working with filters in lists
toc: true
permalink: en/ef_configuring-filters.html
lang: en
---

## Настройка фильтрации на списке

Для того чтобы на [списковой форме](ef_object-list-view.html) реализовать возможность фильтрации необходимо:

1. Указать свойства поиска в [шаблоне](ef_template.html) списковой формы.
2. При необходимости переопределить предикаты в [роуте](ef_route.html) списковой формы.


### Настройка шаблона формы

Настройка шаблона формы осуществляется следующим образом:

```hbs
{% raw %}{{flexberry-objectlistview
{{flexberry-objectlistview
// ...
enableFilters=true
filters=filters
applyFilters=(action "applyFilters")
resetFilters=(action "resetFilters")
componentForFilter=(action "componentForFilter")
conditionsByType=(action "conditionsByType")
// ...
}}{% endraw %}
```

### Настройка роута формы

Переопределить, как будет строится предикат, можно следующим образом:

```javascript
predicateForFilter(filter) {
    if (filter.type === 'string' && filter.condition === 'like') {
      return new StringPredicate(filter.name).contains(filter.pattern);
    }

    if (filter.type === 'decimal') {
      return new SimplePredicate(filter.name, filter.condition, filter.pattern ? Number(filter.pattern) : filter.pattern);
    }

    return this._super(...arguments);
  },
```

### Указание операций сравнения

Операции сравнения указываются через функцию `conditionsByType`, возвращающую массив для дропдауна с операциями. Для этого:

1.В [контроллере](ef_controller.html) списковой формы прописать функцию с необходимыми значениями:

```javascript
conditionsByType(type) {
      switch (type) {
        case 'file':
          return null;

        case 'date':
        case 'number':
          return ['eq', 'neq', 'le', 'ge'];

        case 'string':
          return ['eq', 'neq', 'like'];

        case 'boolean':
          return ['eq'];

        default:
          return ['eq', 'neq'];
      }
    },
```

2.В шаблоне списка указать соответствующее событие:

```hbs
{% raw %}{{flexberry-objectlistview
    // ...
    conditionsByType=(action "conditionsByType")
    // ...
}}{% endraw %}
```
