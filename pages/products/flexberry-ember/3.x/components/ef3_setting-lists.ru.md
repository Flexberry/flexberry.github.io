---
title: Настройка списков
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, OLV, настройка
toc: true
permalink: ru/ef3_setting-lists.html
lang: ru
summary: Настройка панели управления, пользовательские компоненты
---

Списки для разных систем требуют разного, часто индивидуального, подхода. Для этих целей в компоненте Flexberry Objectlistview реализован ряд механизмов, позволяющих настраивать как панель управления, так и отдельные строки, ячейки или внешний вид.

### Встраивание пользовательских компонентов в тулбар списка

Доступно начиная с версии ember-flexberry 3.9.0.

Общий вид компонента Flexberry Objectlistview с использованием пользовательского компонента в тулбаре, если текущая тема оформления “Ghost”:

![toolbar-custom-components](/images/pages/products/flexberry-ember/3.x/components/toolbar-custom-components.png)

Вы можете посмотреть [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-objectlistview/toolbar-custom-components-example) на тестовом стенде.

Чтобы встроить пользовательские компоненты в контроллере формы, необходимо определить массив объектов. Каждый объект должен содержать имя компонента и его свойства:

```javascript
[
    {
        name: '...', // Имя компонента.
        properties: {...} // Свойства компонента.
    },
    ...
]
```

Для того чтобы добавить в тулбар списка пользовательский компонент, в контроллере нужно определить метод `customToolbarComponents`. Например:

```javascript
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
    ...
    customToolbarComponents: computed('dropdownValue', function() {
        return [{
            name: 'flexberry-dropdown',
            properties: {
                items: this.get('dropdownItems'),
                value: this.get('dropdownValue'),
                onChange: this.get('onChange').bind(this)
            }
        }];
    })
});
```

Затем, в контроллере, необходимо определить метод `onChange`

```javascript
dropdownValue: null,
dropdownItems: null,

onChange: function(value) {
    this.set('dropdownValue', value);
}
```

Свойство `customToolbarComponents` должно быть указано в шаблоне списка:

```hbs
{% raw %}{{flexberry-objectlistview
    ...
    customToolbarComponents=customToolbarComponents
}}
{% endraw %}
```
