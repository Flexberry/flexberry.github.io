---
title: Flexberry Groupedit
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_groupedit.html
folder: products/ember-flexberry/controls/groupedit/
lang: en
summary: Представлено описание контрола для редактирования детейлов Flexberry Groupedit.
---

## Описание

Основное предназначение контрола Flexberry Groupedit - редактирование детейлов на форме редактирования.

Для добавления контрола на страницу, можно воспользоваться шаблоном:

```hbs
{% raw %}{{flexberry-groupedit
	componentName="ordersGroupEdit"
	class="attached"
	modelProjection=modelProjection.attributes.orders
	content=model.orders
	readonly=readonly
	orderable=false
}}{% endraw %}
```

### Свойства Flexberry Groupedit

Свойство | Краткое описание
:-------------------------------------|:------------------------------------------------------------
Свойства `componentName` и `readonly`| Свойства из [базового контрола](ef_controls.html).
`modelProjection`| Определяет представление, которое будет отображаться.
`content`| Определяет записи, редактируемые в контроле.
`cellComponent`| Метод, определяющий, каким контролом редактируется компонент.
`orderable`| Флаг, определяющий, возможно ли производить сортировку по столбцам в компоненте (если значение флага будет изменено на "true", потребуются дополнительные настройки, о чём ниже).
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-ge, "Удалить запись". Значение по умолчанию: false.
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-ge, "Редактировать запись". Значение по умолчанию: false.
`showDeleteButtonInRow`| Флаг, определяющий, отображать кнопку "-" для удаления записи в браузерном и мобильном шаблоне. Значение по умолчанию: false.
`singleColumnHeaderTitle `| Заголовок для мобильного представления f-ge, вместо названий колонок.

Также могут использоваться свойства:

* `rowClickable`
* `rowClick`
* `editOnSeparateRoute` 

Подробнее см. [статью](ef_groupedit-detail-in-route.html)).


## Особенности реализации

* Flexberry Groupedit состоит из двух компонент: `GroupeditToolbar` и `ObjectListView`.
* Свойство `class` применяется к `ObjectListView`.

## Особенности работы с флагом orderable

Если для флага `orderable` выставить значение "true", то требуется дополнительно задать настройки:

```hbs
{% raw %}{{flexberry-groupedit
	...
	orderable=true
	sortByColumn=(action "sortByColumn")
	addColumnToSorting=(action "addColumnToSorting")
}}{% endraw %}
```

`sortByColumn` - действие (action) контроллера, которое должно быть выполнено для сортировки по столбцу.

`addColumnToSorting` - действие (action) контроллера, которое должно быть выполнено для добавления сортировки по столбцу.

{% include note.html content="Чтобы использовать указанную выше запись в шаблоне, требуется, чтобы в контроллере шаблона были определены действия (action) с именами `sortByColumn` и `addColumnToSorting`.
Синтаксис `addColumnToSorting=(action \"addColumnToSorting\")` определяет, что используется ember closure action." %}

Если настройки sortByColumn и addColumnToSorting не будут выставлены, то в консоли браузера появится соответствующее сообщение об ошибке.

## Встраивание компонентов в groupedit

Компонент flexberry-groupedit - это таблица, в ячейки которой можно встраивать любые [компоненты, наследуемые от flexberry-base-component](ef_controls.html).

Для встраивания компонентов flexberry-groupedit находит метод `getCellComponent` в текущем контроллере, и вызывает его при формировании каждой ячейки таблицы.
Метод `getCellComponent` уже определен в базовом контроллере формы редактирования (`ember-flexberry/controllers/edit-form.js`), его логика направлена 
на встраивание компонентов в зависимости от типа данных в ячейке, и выглядит следующим образом:

```javascript
  /**
   * Method to get type and attributes of component,
   * which will be embedded in object-list-view cell.
   *
   * @method getCellComponent.
   * @param {Object} attr Attribute of projection property related to current table cell.
   * @param {String} bindingPath Path to model property related to current table cell.
   * @param {DS.Model} modelClass Model class of data record related to current table row.
   * @return {Object} Object containing name & properties of component, which will be used to render current table cell.
   * { componentName: 'my-component',  componentProperties: { ... } }.
   */
    getCellComponent: function(attr, bindingPath, modelClass) {
    var cellComponent = {
      componentName: 'flexberry-textbox',
      componentProperties: null
    };

    if (attr.kind === 'belongsTo') {
      cellComponent.componentName = 'flexberry-lookup';
      return cellComponent;
    }

    var modelAttr = !Ember.isNone(modelClass) ? Ember.get(modelClass, 'attributes').get(bindingPath) : null;
    if (attr.kind === 'attr' && modelAttr && modelAttr.type) {
      switch (modelAttr.type) {
        case 'boolean':
          cellComponent.componentName = 'flexberry-checkbox';
          break;
        case 'date':
          cellComponent.componentName = 'flexberry-datepicker';
          break;
        case 'file':
          cellComponent.componentName = 'flexberry-file';
          break;
        default:

          // Current cell type is possibly custom transform.
          var modelAttrType = getOwner(this)._lookupFactory('transform:' + modelAttr.type);

          // Handle enums (extended from transforms/enum-base.js).
          if (modelAttrType && modelAttrType.isEnum) {
            cellComponent.componentName = 'flexberry-dropdown';
            cellComponent.componentProperties = {
              items: modelAttrType.create().getAvailableValuesArray()
            };
          }

          break;
      }
    }

    return cellComponent;
  }
``` 

Если на прикладной форме редактирования требуется встраивать свой компонент в ячейки groupedit-а,
то необходимо будет переопределить метод getCellComponent в прикладном контроллере,
унаследованном от базового контроллера формы редактирования (`ember-flexberry/controllers/edit-form.js`).
Это будет выглядеть примерно так:

```javascript
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  title: 'My edit form',

  //...

  getCellComponent: function(attr, bindingPath, model) {
    if (...) {
      return {
        componentName: 'my-component',
        componentProperties: null
      };
    }

    if (...) {
      return {
        componentName: 'my-another-component',
        componentProperties: {
          myAnotherComponentProperty1: 'someValue',
          myAnotherComponentProperty2:  4815162342
        }
      };
    }

    return this._super(...arguments);
  }

  //...
});
```

При этом важно, чтобы встраиваемый компонент наследовался от базового компонента (`ember-flexberry/components/flexberry-base-component.js`),
и если в компоненте переопределен метод init, то в конце прикладной инициализации обязательно должен вызываться метод инициализации
из базового класса, т.к. там определена логика, инициализирующая свойства компонента из объекта переданного в
возвращаемом значении метода getCellComponent по ключу componentProperties.

## Другие возможности Flexberry Groupedit

* [Редактирование детейлов в отдельном роуте во Flexberry Groupedit](ef_groupedit-detail-in-route.html)
