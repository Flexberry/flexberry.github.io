---
title: Настройка фильтров на списках
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember, фильтрация, OLV, список
summary: Особенности настройки и работы с фильтрами на списках
toc: true
permalink: ru/ef_configuring-filters.html
lang: ru
---

## Настройка фильтрации на списке

Для того чтобы на [списковой форме](ef_object-list-view.html) реализовать возможность фильтрации необходимо:

1. Указать свойства поиска в [шаблоне](ef_template.html) списковой формы.
2. При необходимости переопределить предикаты в [роуте](ef_route.html) списковой формы.

По умолчанию отфильтровать можно не только по кнопке обновления списка, но и по клавише `Enter`. Также по умолчанию доступна фильтрация по подстроке при незаданной операции сравнения.

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

#### Фильтрация по датам без учета времени

Если нужно фильтровать поля с датами не учитывая время, то нужно в роуте в predicateForFilter добавить условие:

```javascript
predicateForFilter(filter) {
    if (filter.type === 'date') {
      return new DatePredicate(filter.name, filter.condition, filter.pattern, true);
    }

    return this._super(...arguments);
  },
```

#### Пользовательские функции для фильтров

Если на прикладном уровне нужны специфические фильтры, то можно использовать функцию `predicateForAttribute`. Данная функция получает на вход атрибут, по которому происходит фильтрация, значение, по которому фильтровать, условие фильтра и возвращает предикат, по которому затем формируется параметр `$filter` в [OData-запросе](fo_orm-odata-service.html). 

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

##№ Операции "пусто" и "не пусто"

Если существует необходимость использовать операции, не требующие заполнения значения, то можно настроить соответствующую операцию для предиката в контроллере формы. Например

```javascript
// ...
case 'string':
return ['eq', 'neq', 'like'];
return ['eq', 'neq', 'like', 'empty'];
// ...
```

Далее в роуте задать соответствующее условие. Например,

```javascript
// ...
if (filter.type === 'string' && filter.condition === 'empty') {
  return new SimplePredicate(filter.name, 'eq', null);
}
// ...
```
