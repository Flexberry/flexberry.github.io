---
title: Custom buttons for lists
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember, buttons, OLV, list
summary: Embed buttons in the toolbar and list rows
toc: true
permalink: en/ef_custom-buttons.html
lang: en
---

## Встраивание пользовательских кнопок в тулбар списка

Для встраиваемой кнопки в контроллере формы нужно определить ряд свойств:

```javascript
{
    buttonName: '...', // Отображаемое имя кнопки.
    buttonAction: '...', // Действие, вызываемое контроллером при нажатии этой кнопки (должно быть указано в шаблоне).
    buttonClasses: '...', // Css-класс кнопки.
    buttonTitle: '...' // Подпись.
}
```

Если необходимо добавить несколько кнопок, то их свойства задаются в массиве:

```javascript
[{ buttonName: ..., buttonAction: ..., buttonClasses: ... }, {...}, ...]
```

Для того чтобы добавить в тулбар списка пользовательскую кнопку, в контроллере нужно определить метод `customButtonsMethod`. Например:

```javascript
import Ember from 'ember';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
...
customButtonsMethod: Ember.computed('i18n.locale', function() {
    let i18n = this.get('i18n');
    return [{
        buttonName: i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-button-name'),
        buttonAction: 'userButtonActionTest',
        buttonClasses: 'test-click-button'
        }];
    })
});
```

Далее, в контроллере, нужно указать событие `buttonAction`

```javascript
...
clickCounter: 1,
messageForUser: undefined,

actions: {
    userButtonActionTest: function() {
    let i18n = this.get('i18n');
    let clickCounter = this.get('clickCounter');
    this.set('clickCounter', clickCounter + 1);
    this.set('messageForUser',
        i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-message').string +
        ' ' + clickCounter);
    }
}
});
```

Определенные методы и свойства должны быть указаны в шаблоне списка:

```hbs
{% raw %}{{flexberry-objectlistview
...
customButtons=customButtonsMethod
userButtonActionTest='userButtonActionTest'
}}
{% endraw %}
```

## Встраивание пользовательских кнопок в строки списка

Пользовательские кнопки для строк списка создаются по аналогичному принципу. Событие, как и для кнопки в тулбаре, может быть задано строкой. Например, так:

```javascript
...
actions: {
    userButtonInRowActionTest(model) {
      this.set('modelFromClickedRow', model);
    },
});
```
