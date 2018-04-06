---
title: Настройка поднимаемой по лукапу формы
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_modal-window-settings.html
lang: en
summary: Представлены основные возможности настройки поднимаемой по лукапу формы.
---

## Настройка модального окна лукапа

Настройки модального окна [лукапа](ef_lookup.html) определены в `components/lookup-field/lookup-field-mixin.js`.

```js
lookupSettings: {
	controllerName: undefined,
	template: undefined,
	contentTemplate: undefined,
	loaderTemplate: undefined,
	modalWindowWidth: undefined,
	modalWindowHeight:undefined
}
```

В контроллере формы редактирования `controllers/edit-form.js` данные настройки заданы:

```js
lookupSettings: {
    controllerName: 'lookup-dialog',
    template: 'lookup-dialog',
    contentTemplate: 'lookup-dialog-content',
    loaderTemplate: 'loading',
    modalWindowWidth: 750,
    modalWindowHeight:600
},
```

## Настройка размера

* `modalWindowWidth` - это ширина открываемого по лукапу модального окна.
* `modalWindowHeight` - это высота открываемого по лукапу модального окна.

Если требуется изменить размер открываемого по лукапу модального окна, то можно переопределить заданные по умолчанию значения.

## Установка заголовка окна, открываемого по лукапу

Заголовок окна, открываемого по [лукапу](ef_lookup.html), устанавливается в свойстве `title` компонента `lookup-field` в шаблоне соответствующей формы редактирования. Например, в шаблоне формы редактирования `employee.hbs` встраивание лукапа может выглядеть следующим образом:

```hbs
{% raw %}{{lookup-field/lookup-field
  choose='showLookupDialog'
  remove='removeLookupValue'
  value=model.employee1.firstName
  relationName='employee1'
  projection='EmployeeL'
  title='Employees'
}}{% endraw %}
```

В результате заголовок из свойства `title` компонента `lookup-field` будет отображаться в модальном окне, открываемом по лукапу:

![](/images/pages/img/page/EditFormTitle/lookuptitle.png)

## Настройка фильтрации и количества элементов на странице

Параметры настройки фильтраци и/или количества элементов на странице осуществляется через событие `getLookupFolvProperties` в контроллере формы:

```javascript
getLookupFolvProperties: function(options) {
    //...

    if (methodArgs.relationName === 'type') {
    return {
        filterButton: true,
        filterByAnyWord: true,
        enableFiltres: true,
        refreshButton: true,
        perPage: 25,
      };
    }

    // ...
}
```

{% include note.html content="Необходимо выбрать тип поиска (`filterByAnyWord` или `filterByAllWords`), так на lookup-форме использовать использовать можно только один из них." %}

Далее указать событие в шаблоне настраевомого для LookUp списка:

```hbs
{% raw %}{{flexberry-lookup
    // ...
    lookupWindowCustomProperties=(action 'getLookupFolvProperties')
}}{% endraw %}
```

Реализация отображена на [ember-стенде](https://flexberry-ember-dev.firebaseapp.com/components-examples/flexberry-lookup/customizing-window-example).

## Настройка иерархии

В контроллере формы редактирования указать:

```javascript
export default EditFormController.extend({

  // Активировать иерархию при загрузке lookup-формы.
  init() {
    this._super(...arguments);

    this.set('lookupController.inHierarchicalMode', true);
    this.set('lookupController.hierarchicalAttribute', 'parent');
  },

    // ...
    getLookupFolvProperties: function(options) {
      // ...

      if (methodArgs.relationName === 'type') {
        return {
            // ...
            disableHierarchicalMode: false,

            // Активировать иерархию при загрузке lookup-формы.
            inHierarchicalMode: true,
            hierarchicalAttribute: 'Name' // Свойство, по которому осуществляется иерархия.
        };
      }

     // ...
});
```
