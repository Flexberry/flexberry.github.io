---
title: Computed properties in getCellComponent
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_computed-properties-in-getcellcomponent.html
folder: products/ember-flexberry/controls/
lang: en
summary: Computed properties in getCellComponent
---

## Описание

В [0.11.0](https://github.com/Flexberry/ember-flexberry-data/blob/master/CHANGELOG.md) добавлена возможность создавать вычисляемые свойства для встраиваемых компонентов.

## Пример

Чтобы создать вычисляемое свойство нужно, в controllers, в getCellComponent добавить свойство `computedProperties: { thisController: this }`:

```js
getCellComponent(attr, bindingPath, model) {
   let cellComponent = this._super(...arguments);
   if (attr.kind === 'belongsTo') {
     cellComponent.componentProperties = {
       choose: 'showLookupDialog',
       remove: 'removeLookupValue',
       displayAttributeName: 'name',
       required: true,
       relationName: 'author',
       projection: 'ApplicationUserL',
       autocomplete: true,
       computedProperties: { thisController: this },
       readonly: false,
      };
   }

   return cellComponent;
 },
```

Таким образом в свойстве computedProperties у текущего controller-а будет this из [dynamic-properties](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/dynamic-properties.js) со всеми своими observer-ами. Теперь чтобы поменять любое из свойств встраимого компонента достаточно изменить значение в computedProperties:

```js
checkboxValue: false,

lookupReadonly: Ember.observer('checkboxValue', function() {
  if (!Ember.isNone(this.get('computedProperties.dynamicProperties.readonly'))) {
    if (this.get('checkboxValue')) {
      this.set('computedProperties.dynamicProperties.readonly', true);
    } else {
      this.set('computedProperties.dynamicProperties.readonly', false);
    }
  }

  return this.get('checkboxValue');
}),
```
