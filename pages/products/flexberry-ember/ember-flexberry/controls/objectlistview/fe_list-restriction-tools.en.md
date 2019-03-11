--- 
title: Tools to limit the list 
sidebar: flexberry-ember_sidebar 
keywords: Flexberry Ember, filtration, OLV, list, search, limit 
summary: Filtering in list, restriction list, search by attributes 
toc: true 
permalink: en/fe_list-restriction-tools.html 
lang: en 
autotranslated: true 
hash: 0e7d6d7009e4e3389fd66fa15fdcaab92f3bea87e084aecd8e4ad8588c2ebda4 
--- 

To set restrictions on the lists Flexberry Objectlistview component includes the following tools: 

* filtering lists 
* the imposition of limits (method `objectListViewLimitPredicate`) 
* search by attributes 

## configure filtering on the list 

In order to [list form](fe_object-list-view.html) to implement a filtering option, you must: 

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

## restraints 

Particularly the imposition of restrictions on Flexberry Objectlistview related to the fact that the data for the control shall be taxed in the router. Accordingly, in order to prevent the subtraction of data without restrictions, the restriction must be defined when you hook `model` in the router shape. 

Thus, to impose a restriction, you must override the method `objectListViewLimitPredicate` in the router application list forms to return a predicate for the constraint. 

For example, is a form `limit-function-example`. If the page displays an even number of records, you need to display records that have a field "address" is the letter "S". When the odd number - with the "address" field the letter "p". 

PstrfobjectListViewLimitPredicate` an overridable method in the router of the appropriate application list form. 

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

## search setting all attributes to a standard list 

In order to [list form](fe_object-list-view.html) to implement a search capability on all attributes should: 

1. Specify the search properties in the [template](ef_template.html) the list forms. 
2. If you need to override the predicates in [rout](ef_route.html) the list forms. 

### configure the form template to search for 

Customize a form template is as follows: 

```hbs
{% raw %}{{flexberry-objectlistview
    // ... 
    filters=filters
    applyFilters=(action "applyFilters")
    resetFilters=(action "resetFilters")
    filterButton=true
    filterText=filter
    filterByAnyWord=filterByAnyWord // search for some words 
    filterByAllWords=filterByAllWords // search for all words 
    filterByAnyMatch=(action 'filterByAnyMatch')
    // ... 
}}{% endraw %}
``` 

`filterByAnyWord` - as a result you will be given all the lines that contain the specified search word/few words. 

![Some attributes](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-any-word.png) 

`filterByAllWords` - the result will be issued to only those lines that contain the specified word/phrase. 

![Search attributes](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-all-words.png) 

By default the search is performed by a substring. 

The implementation is shown on [ember-stand](http://flexberry.github.io/ember-flexberry/dummy/develop/) 

* [for OLV](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-objectlistview/custom-filter). 
* [for simpleolv](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-simpleolv/custom-filter) 

{% include note.html content="`filterByAnyWord` and `filterByAllWords` cannot be used together, if not implemented additional elements of the toolbar (buttons) to enable/disable the search type." %} 

### configuration get form for search 

To override, as is the predicate in the following way: 

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

Setting the predicate required for the correct search values with two criteria true/false. 

Dla flexberry-simpleolve the same settings._ 

## setting list displayed in lucapa 

In the [LookUp](ef_lookup.html) shows the component [flexberry-objectlistview](fe_object-list-view.html). The parameters for this component can be set through the event `getLookupFolvProperties` in the controller form. This setting is described in [setup at raising lucapa form](ef_modal-window-settings.html) in the paragraph "setting the filter and the number of items per page". 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}