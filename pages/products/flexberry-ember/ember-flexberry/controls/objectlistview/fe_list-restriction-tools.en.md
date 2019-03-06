---
title:  List restriction tools
sidebar: flexberry-ember_sidebar
keywords: Flexberry Ember, filters, OLV, list, search, limit
summary: Фильтрация списка, ограничение списка, поиск по атрибутам
toc: true
permalink: en/fe_list-restriction-tools.html
lang: en
---

Для того чтобы настроить ограничения на списках, компонент Flexberry Objectlistview включает в себя следующие инструменты:

* фильтрация списков
* наложение ограничения (метод `objectListViewLimitPredicate`)
* поиск по атрибутам

## Настройка фильтрации на списке

Для того чтобы на [списковой форме](fe_object-list-view.html) реализовать возможность фильтрации необходимо:

1. Указать свойства поиска в [шаблоне](ef_template.html) списковой формы.
2. При необходимости переопределить предикаты в [роуте](ef_route.html) списковой формы.

По умолчанию отфильтровать можно не только по кнопке обновления списка, но и по клавише `Enter`. Также по умолчанию доступна фильтрация по подстроке при незаданной операции сравнения.

{% include note.html content="Регистрозависимость/ регистронезависимость при фильтрации зависит от [настроек БД](fo_insensitivity-register-ds.html)." %}

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

### Операции "пусто" и "не пусто"

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

## Наложение ограничений

Особенности наложения ограничений на Flexberry Objectlistview связаны с тем, что данные для контрола вычитываются в роуте. Соответственно, чтобы не допустить вычитывания данных без наложенного ограничения, ограничение должно быть определено, когда выполняется хук `model` в роуте формы.

Таким образом, чтобы наложить ограничение, необходимо переопределить метод `objectListViewLimitPredicate` в роуте прикладной списковой формы, чтобы он возвращал предикат для ограничения.

Например, есть форма `limit-function-example`. Eсли на странице отображается чётное количество записей, необходимо вывести записи, у которых в поле "address" есть буква "S". При нечетном количестве - имеющие в поле "address" букву "п".

Переопределяем метод `objectListViewLimitPredicate` в роуте соответствующей прикладной списковой формы.

```javascript
import Ember from 'ember';
import ListFormRoute from 'ember-flexberry/routes/list-form';
import { StringPredicate } from 'ember-flexberry-data/query/predicate';

// ...
export default ListFormRoute.extend({
  objectListViewLimitPredicate: function(options) {
    let methodOptions = Ember.merge({
      modelName: undefined,
      projectionName: undefined,
      params: undefined
    }, options);

    if (methodOptions.modelName === this.get('modelName') &&
        methodOptions.projectionName === this.get('modelProjection')) {
      let currentPerPageValue = methodOptions.params ? methodOptions.params.perPage : undefined;
      let limitFunction = (currentPerPageValue && currentPerPageValue % 2 === 0) ?
                          new StringPredicate('address').contains('S') :
                          new StringPredicate('address').contains('п');
      return limitFunction;
    }

    return undefined;
  }
});
```

## Настройка поиска по всем атрибутам для стандартного списка

Для того чтобы на [списковой форме](fe_object-list-view.html) реализовать возможность поиска по всем атрибутам необходимо:

1. Указать свойства поиска в [шаблоне](ef_template.html) списковой формы.
2. При необходимости переопределить предикаты в [роуте](ef_route.html) списковой формы.

### Настройка шаблона формы для поиска

Настройка шаблона формы осуществляется следующим образом:

```hbs
{% raw %}{{flexberry-objectlistview
    // ...
    filters=filters
    applyFilters=(action "applyFilters")
    resetFilters=(action "resetFilters")
    filterButton=true
    filterText=filter
    filterByAnyWord=filterByAnyWord // поиск по некоторым словам
    filterByAllWords=filterByAllWords // поиск по всем словам
    filterByAnyMatch=(action 'filterByAnyMatch')
    // ...
}}{% endraw %}
```

`filterByAnyWord` - в результате будут выданы все строки, в которых указано заданное в поиске слово/несколько слов.

![Поиск по некоторым атрибутам](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-any-word.png)

`filterByAllWords` - в результате будут выданы только те строки, в которых указано заданное слово/словосочетание.

![Поиск по всем атрибутам](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-all-words.png)

По-умолчанию поиск осуществляется по подстроке.

Реализация отображена на [ember-стенде](http://flexberry.github.io/ember-flexberry/dummy/develop/)

* [для OLV](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-objectlistview/custom-filter).
* [для simpleolv](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-simpleolv/custom-filter)

{% include note.html content="`filterByAnyWord` и `filterByAllWords` нельзя использовать вместе, если не реализованы дополнительные элементы тулбара (кнопки) для включения/отключения типа поиска." %}

### Настройка роута формы для поиска

Переопределить, как будет строится предикат, можно следующим образом:

```javascript
predicateForAttribute(attribute, filter) {
    switch (attribute.type) {
      case 'boolean':
        let yes = ['TRUE', 'True', 'true', 'YES', 'Yes', 'yes', 'ДА', 'Да', 'да', '1', '+'];
        let no = ['FALSE', 'False', 'false', 'NO', 'No', 'no', 'НЕТ', 'Нет', 'нет', '0', '-'];

        if (yes.indexOf(filter) > 0) {
          return new SimplePredicate(attribute.name, 'eq', 'true');
        }

        if (no.indexOf(filter) > 0) {
          return new SimplePredicate(attribute.name, 'eq', 'false');
        }

        return null;

      default:
        return this._super(...arguments);
    }
  },
```

Настройка предиката необходима для корректного поиска значений, имеющих два признака истина/ложь.

_Для flexberry-simpleolve настройки аналогичны._

## Настройка списка, отображаемого в лукапе

В [LookUp](ef_lookup.html) показывается компонент [flexberry-objectlistview](fe_object-list-view.html). Параметры для этого компонента можно задавать через событие `getLookupFolvProperties` в контроллере формы. Данная настройка описана в статье [Настройка поднимаемой по лукапу формы](ef_modal-window-settings.html) в п. "Настройка фильтрации и количества элементов на странице".
