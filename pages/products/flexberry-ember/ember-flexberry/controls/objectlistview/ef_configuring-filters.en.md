--- 
title: setting up filters in the lists 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember, filtration, OLV, and the list 
summary: configuration of filters on the list 
toc: true 
permalink: en/ef_configuring-filters.html 
lang: en 
autotranslated: true 
hash: a56c44cf921009c6b2670b769d67e1a10ed0aea34c1e23c2fd311a990a1742ee 
--- 

## configure filtering on the list 

In order to [list form](ef_object-list-view.html) to implement a filtering option, you must: 

1. Specify the search properties in the [template](ef_template.html) the list forms. 
2. If you need to override the predicates in [rout](ef_route.html) the list forms. 

The default filter can not only refresh button of the list, but and key `Enter`. Also by default, filtering by a substring if you do not provide a comparison operation. 

{% include note.html content="case-sensitivity/ registronacional filtering depends on the [DB settings](fo_insensitivity-register-ds.html)." %} 

### setup form template 

Customize a form template is as follows: 

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

### configure the router forms 

To override, as is the predicate in the following way: 

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

#### Filtering by date without time 

If you want to filter the fields with dates are not given time, then you need to get in predicateForFilter add a condition: 

```javascript
predicateForFilter(filter) {
    if (filter.type === 'date') {
      return new DatePredicate(filter.name, filter.condition, filter.pattern, true);
    }

    return this._super(...arguments);
  },
``` 

#### Custom function for filters 

If at the application level need specific filters, you can use the function `predicateForAttribute`. This function receives the input of the attribute that is filtered, the value on which to filter, the filter condition and returns a predicate, which then formed a parameter `$filter` in [OData-query](fo_orm-odata-service.html). 

### specify the comparison operations 

Comparison operations are indicated via `conditionsByType` function that returns an array for dropdown operations. To do this: 

1.In [controller](ef_controller.html) list of forms to register the function with the required values: 

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

2.In the template list to specify the appropriate event: 

```hbs
{% raw %}{{flexberry-objectlistview
    // ... 
    conditionsByType=(action "conditionsByType")
    // ... 
}}{% endraw %}
``` 

### Operation "empty" and "not empty" 

If there is a need to use operations not requiring fill values, then you can configure the appropriate operation for the predicate in the controller form. For example 

```javascript
// ... 
case 'string':
return ['eq', 'neq', 'like'];
return ['eq', 'neq', 'like', 'empty'];
// ... 
``` 

Later in the router to set the appropriate condition. For example, 

```javascript
// ... 
if (filter.type === 'string' && filter.condition === 'empty') {
  return new SimplePredicate(filter.name, 'eq', null);
}
// ... 
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/