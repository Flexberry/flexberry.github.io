---
title: Flexberry Groupedit
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, groupedit
toc: true
permalink: ru/ef2_groupedit.html
lang: ru
summary: Свойства, особенности реализации, настройка сортировки и встраивание прикладных компонентов, реализация в отдельном роуте
---

`flexberry-groupedit` предназначен для работы с [детейлами](fo_detail-associations-properties.html) на форме редактирования.

Чтобы добавить groupedit на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-groupedit
  componentName="ordersGroupEdit"
  defaultSortingButton=true
  class="attached"
  modelProjection=modelProjection.attributes.orders
  content=model.orders
  readonly=readonly
  orderable=false
}}{% endraw %}
```

### Свойства Flexberry Groupedit

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly`| Свойства из [базового контрола](ef2_controls.html).
`modelProjection`| Определяет представление, которое будет отображаться.
`content`| Определяет записи, редактируемые в контроле.
`cellComponent`| Метод, определяющий, каким контролом редактируется компонент.
`defaultSortingButton`| Флаг, определяющий, отображать ли кнопку установки сортировки по умолчанию. Значение по умолчанию: true.
`orderable`| Флаг, определяющий, возможно ли производить сортировку по столбцам в компоненте (если значение флага будет изменено на `true`, потребуются дополнительные настройки, о чём ниже).
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона FGE, `Удалить запись`. Значение по умолчанию: false.
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона FGE, `Редактировать запись`. Значение по умолчанию: false.
`showDeleteButtonInRow`| Флаг, определяющий, отображать кнопку `-` для удаления записи в браузерном и мобильном шаблоне. Значение по умолчанию: false.
`singleColumnHeaderTitle`| Заголовок для мобильного представления FGE, вместо названий колонок.

Свойства, используемые для настройки редактирования в отдельном роуте:

* `rowClickable`
* `rowClick`
* `editOnSeparateRoute`

## Особенности реализации

* Flexberry Groupedit состоит из двух компонент: `GroupeditToolbar` и `ObjectListView`.
* Свойство `class` применяется к `ObjectListView`.

## Сортировка элементов

Если для флага `orderable` выставить значение `true`, то требуется дополнительно задать настройки:

```hbs
{% raw %}{{flexberry-groupedit
  ...
  orderable=true
  sortByColumn=(action "sortByColumn")
  addColumnToSorting=(action "addColumnToSorting")
}}{% endraw %}
```

* `sortByColumn` - действие (action) контроллера, которое должно быть выполнено для сортировки по столбцу.
* `addColumnToSorting` - действие (action) контроллера, которое должно быть выполнено для добавления сортировки по столбцу.

Использовать сортировку в шаблоне можно, если в контроллере шаблона были определены действия (action) с именами `sortByColumn` и `addColumnToSorting`. Синтаксис `addColumnToSorting=(action \"addColumnToSorting\")` определяет, что используется ember closure action.

## Встраивание компонентов в groupedit

Компонент `flexberry-groupedit` - это таблица, в ячейки которой можно встраивать любые компоненты, наследуемые от [flexberry-base-component](ef2_controls.html).

Для встраивания компонентов flexberry-groupedit находит метод `getCellComponent` в текущем контроллере, и вызывает его при формировании каждой ячейки таблицы.

Метод `getCellComponent` уже определен в базовом контроллере формы редактирования (`ember-flexberry/controllers/edit-form.js`), его логика направлена на встраивание компонентов в зависимости от типа данных в ячейке, и выглядит следующим образом:

```javascript
// ...
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

Если на прикладной форме редактирования требуется встраивать прикладной компонент в ячейки groupedit, то необходимо будет переопределить метод `getCellComponent` в прикладном контроллере, унаследованном от базового контроллера формы редактирования (`ember-flexberry/controllers/edit-form.js`).

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

При этом важно, чтобы встраиваемый компонент наследовался от базового компонента (`ember-flexberry/components/flexberry-base-component.js`), и если в компоненте переопределен метод `init`, то в конце прикладной инициализации обязательно должен вызываться метод инициализации из базового класса, так как там определена логика, инициализирующая свойства компонента из объекта переданного в возвращаемом значении метода `getCellComponent` по ключу `componentProperties`.

## Редактирование детейлов в отдельном роуте

Настройка шаблонов и моделей агрегатора и детейла, сериализатора агрегатора и контроллера детейла для реализации возможности редактирования в отдельном роуте является частным случаем использования компонента (например, при работе на мобильных устройствах).

При этом следует учитывать некоторые особенности приминения:

* Изменения в агрегаторе и детейлах сохраняются в БД только при нажатии кнопки `Сохранить` на форме агрегатора.
* На странице детейла есть только две кнопки: `Сохранить` и `Закрыть` (на форме нет кнопки, чтобы откатить состояние детейла на момент захода на форму).
* При нажатии на кнопку `Сохранить` на странице детейла происходит возврат на страницу агрегатора без сохранения изменений в БД.

### Настройка шаблона агрегатора и роутера

В [шаблоне](ef2_template.html) формы редактирования агрегатора у контрола `flexberry-groupedit` определить следующие свойства:

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
}}{% endraw %}
```

* `rowClickable` - флаг, определяющий, следует ли обрабатывать нажатие на строчку. По умолчанию имеет значение `false`, для редактирования детейла в отдельном роуте нужно задать `true`.
* `rowClick` - действие, выполняемое при нажатии на строчку. По умолчанию имеет значение "rowClick", для редактирования детейла в отдельном роуте нужно, чтобы действие (action) с заданным в свойстве именем было определено в [контроллере](ef2_controller.html) или [роуте](ef2_route.html) (`rowClick` определено в базовом роуте формы редактирования).

{% include note.html content="Текущий обработчик метода `rowClick` реализован таким образом, что в случае, если детейл сохранён, то будет произведена переадресация на роут как `modelName/:id`, а если не сохранён, то `modelName.new` (соответственно, [роутер](ef2_router.html) должен быть настроен, чтобы переадресация могла пройти корректно)." %}

* `editOnSeparateRoute` - флаг, определяющий, следует ли редактировать детейл в отдельном роуте. По умолчанию имеет значение `false`. При задании значения "true" изменяется внешний вид `flexberry-groupedit`: отключается возможность редактирования непосредственно в контроле, при нажатии на кнопку `Добавить` теперь создаётся новая запись и сразу происходит переадресация на роут редактирования.

### Настройка шаблона детейла

Чтобы была возможность редактировать детейл в отдельном роуте, соответствующая форма должна быть создана.

Если предполагается, что форма редактирования детейла может быть использована не только с формы агрегатора, но и со списковой формы детейлов, то для настройки отображения кнопок `Сохранить`, `Удалить`, `Закрыть` можно использовать условия, представленные в статье [Формы редактирования и создания](ef2_edit-form.html).

### Настройка контроллера детейла

Контроллер формы редактирования детейла должен наследоваться от `detail-edit-form` вместо `edit-form`. В `detail-edit-form` присутствует дополнительная логика, организующая корректное взаимодействие между формами агрегатора и детейла. В случае, если определено, что пользователь попал на форму детейла не с формы агрегатора, то будет выполняться логика базового контроллера `edit-form`.

```javascript
import DetailEditFormController from 'ember-flexberry/controllers/detail-edit-form';

export default DetailEditFormController;
```

### Настройка моделей агрегатора и детейла

При описании [модели](efd2_model.html) _агрегатора_ важно проверить, что проставлена детейловая [inverse-связь](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations).

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
```

При описании модели _детейла_ важно проверить, что проставлена [inverse-связь](https://guides.emberjs.com/v2.4.0/models/relationships/#toc_reflexive-relations) на агрегатора.

```javascript
var Model = BaseModel.extend({
  ...
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
```

### Настройка сериализатора агрегатора

Важно, чтобы [сериализаторы](efd2_serializer.html) для агрегатора и детейла были настроены корректно.

### Дополнительные особенности реализации

* У базового контроллера формы редактирования есть свойство `returnToAgregatorRoute`, которое определяет, следует ли производить настройки для потенциального возвращения на предыдущий роут агрегатора. По умолчанию в базовом контроллере значение `false`. В базовом контроллере детейла данное значение переопределено на `true` (при необходимости можно в прикладном контроллере детейла поменять его обратно на `false`, чтобы не происходило возвращение). Вычитка данного флага происходит в методе роута [setupController](http://devdocs.io/ember/classes/ember.route#method_setupController).
* Для организации сохранения информации между роутами агрегатора и детейла используется специальный сервис `detail-interaction`. Данный сервис не предназначен для использования на прикладных проектах.
* В базовый роут формы редактирования миксинится свойство `newRoutePath`, где определяется, каким образом по роуту модели определяется роут для новой записи. Сейчас метод реализован как `currentPath + '.new'`. Потенциально этот метод можно переопределить на прикладном роуте.

### Редактирование детейлов в отдельном роуте во Flexberry Groupedit с сохранением

Существует режим работы `flexberry-groupedit`, при котором редактирование детейла происходит на отдельном роуте, при этом, при переходе с роута агрегатора на роут детейла, происходит сохранение агрегатора, а при переходе обратно - сохранение детейла.

Настройка данного варианта аналогичная описываемому выше способу. В шаблоне контрола при этом нужно выставить один дополнительный флаг `saveBeforeRouteLeave`.

```hbs
{% raw %}
{{flexberry-groupedit
  ...
  rowClickable=true
  rowClick='rowClick'
  editOnSeparateRoute=true
  saveBeforeRouteLeave=true
}}{% endraw %}
```

`saveBeforeRouteLeave` - флаг, определяющий, следует ли сохранять текущую модель при переходах между агрегатором и детейлом. По умолчанию имеет значение `false`.

О том, как функционируют кнопки на такой детейловой форме, описано в статье [Формы редактирования и создания](ef2_edit-form.html).

## Вычислимые свойства в getCellComponent

Что бы иметь возможность, изменять свойства, встроенного в ячейку компонента, этот компонент должен использовать миксин [DynamicPropertiesMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html).

Свойства компонента, описанные объектом `componentProperties`, в хуке контроллера `getCellComponent`, будут переданны с использованием свойства [`dynamicProperties`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html#property_dynamicProperties) из миксина.
Поэтому, при изменении свойств объекта `componentProperties`, эти изменения будут выполнены для компонента.

Пример с реализацией:

```javascript
// app/controllers/my-controller.js
import Controller from '@ember/controller';
import { observer } from '@ember/object';

export default Controller.extend({
  checkboxValue: false,

  lookupReadonly: observer('checkboxValue', function() {
    this.set('componentDynamicProperties.readonly', this.get('checkboxValue'));
  }),

  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);
    if (attr.kind === 'belongsTo') {
      this.set('componentDynamicProperties', {
        choose: 'showLookupDialog',
        remove: 'removeLookupValue',
        displayAttributeName: 'name',
        required: true,
        relationName: 'author',
        projection: 'ApplicationUserL',
        autocomplete: true,
        readonly: false,
      });

      cellComponent.componentProperties = this.get('componentDynamicProperties');
    }

    return cellComponent;
  },
});
```

Вы можете посмотреть [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-groupedit/ember-flexberry-dummy-suggestion-list-groupedit-with-lookup-with-computed-atribute) на тестовом стенде.

## Ordered свойства в groupedit

При добавлении в представление, groupedit-а ordered-свойства в нем появятся некоторые особенности, которые следует учитывать:

1. Сортировка в groupedit-е будет по ordered свойству. При этом обычная сортировка в ГЕ будет отключена и ее нельзя включить.
2. При вставке/удалении строк в groupedit осуществляется автоматический пересчет ordered-атрибута по всем строкам.
3. В groupedit, когда в модели есть ordered-атрибут, появляется возможность перемещать строки вверх-вниз через стрелочки. При этом производиться автоматический пересчет значения ordered-атрибута тех строк, у которых изменилась позиция.
4. Если ordered-атрибут отображается в ГЕ для пользователя, то соответствующие значения данного атрибута будет readonly.
