--- 
title: Search all attributes 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember, search, OLV, list 
summary: Features settings for standard control list and search for artisan of forms (picks up at LookUp) 
toc: true 
permalink: en/ef_search-attributes.html 
lang: en 
autotranslated: true 
hash: 977bcd1bb7746941a4ac6e1d41c2ce500092713a57ae8e9bc9f052fbb1a4437f 
--- 

## search setting all attributes to a standard list 

In order to [list form](ef_object-list-view.html) to implement a search capability on all attributes should: 

1. Specify the search properties in the [template](ef_template.html) the list forms. 
2. If you need to override the predicates in [rout](ef_route.html) the list forms. 

### setup form template 

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

![](/images/pages/products/ember-flexberry/controls/filter-by-any-word.png) 

`filterByAllWords` - the result will be issued to only those lines that contain the specified word/phrase. 

![](/images/pages/products/ember-flexberry/controls/filter-by-all-words.png) 

By default the search is performed by a substring. 

The implementation is shown on [ember-stand](https://flexberry-ember-dev.firebaseapp.com/) 
* [for OLV](https://flexberry-ember-dev.firebaseapp.com/components-examples/flexberry-objectlistview/custom-filter?filterCondition=and&perPage=20). 
* [for simpleolv](https://flexberry-ember-dev.firebaseapp.com/components-examples/flexberry-simpleolv/custom-filter) 

{% include note.html content="`filterByAnyWord` and `filterByAllWords` cannot be used together, if not implemented additional elements of the toolbar (buttons) to enable/disable the search type." %} 

### configure the router forms 

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

In the [LookUp](ef_lookup.html) shows the component [flexberry-objectlistview](ef_object-list-view.html). The parameters for this component can be set through the event `getLookupFolvProperties` in the controller form. This setting is described in [setup at raising lucapa form](ef_modal-window-settings.html) in the paragraph "setting the filter and the number of items per page". 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}