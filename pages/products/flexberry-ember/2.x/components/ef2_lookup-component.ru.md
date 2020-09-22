---
title: LookUp-ы в ember-flexberry-приложении
keywords: ember
sidebar: flexberry-ember-2_sidebar
toc: false
permalink: ru/ef2_lookup-component.html
lang: ru
summary: Представлены основные особенности LookUp-ов
---

## Описание

LookUp представляет собой элемент управления (компонент), позволяющий выбрать значение мастера.
Общий вид компонента, в случае, если текущая тема оформления “BlueSky”:

![](/images/pages/products/flexberry-ember/flexberry-lookup/flexberry-lookup.png)

Для добавления компонента в ember-приложении требуется удостовериться в корректности настройки [модели](efd2_model.html), сериализатора и шаблона.

Пусть у сущности `Подготовка` есть мастер `Редактор` типа `Пользователь`. Требуется настроить LookUp по свойству `ExtendedName` мастера.

### Настройка модели

[Модель](efd2_model.html) `Подготовка` должна содержать ссылку на мастера. [Представление](efd2_model-projection.html) для отображения формы, где будет расположен LookUp, также должно включать описание мастера.

```javascript
var Model = BaseModel.extend({
  редактор: DS.belongsTo('пользователь', { inverse: null, async: false })
});

Model.defineProjection('ПодготовкаE', 'подготовка', {
  редактор: Proj.belongsTo('пользователь', 'Редактор', {
      extendedName: Proj.attr()
  })
});
```

В [модели](efd2_model.html), соответствующей мастеру типа `Пользователь`, необходимо задать [представление](efd2_model-projection.html), по которому будет происходить отображение.

```javascript
var Model = BaseModel.extend({
  extendedName: DS.attr('string')
});

Model.defineProjection('ПользовательE', 'пользователь', {
  extendedName: Proj.attr('Полное имя')
});
```

### Настройка сериализаторов

В [сериализаторе](efd2_serializer.html) `Подготовка` описать ссылку на мастера:

```javascript
export default ApplicationSerializer.extend({
  attrs: {
      редактор: { serialize: 'odata-id', deserialize: 'records' }
  },
  primaryKey: '__PrimaryKey'
});
```

[Сериализатор](efd2_serializer.html) для типа `Пользователь`:

```javascript
export default ApplicationSerializer.extend({
  attrs: {  },
  primaryKey: '__PrimaryKey'
});
```

### Настройка шаблона

На страницу редактирования класса `Подготовка` вставить конструкцию:

```hbs
{% raw %}<div class="field">
  <label>Редактор</label>
  {{#if model.errors.редактор }}
    <span style="color:red">{{model.errors.редактор }}</span>
  {{/if}}
  {{flexberry-lookup
    componentName="lookupUsers"
    choose='showLookupDialog'
    remove='removeLookupValue'
    value=model.редактор
    relatedModel=model
    relationName='редактор'
    projection='ПользовательE'
  }}
</div>{% endraw %}
```

## Список основных свойств

Свойство | Описание | Дефолтное значение
:---------------------|:------------------------------------------------------------|:----------------
`choose` | Определяет имя action'а, которое будет происходит по клику на кнопку 'choose'.|
`remove` | Определяет имя action'а, которое будет происходит по клику на кнопку 'remove'.|
`chooseText` | Определяет текст/html внутри кнопки 'choose'.|
`removeText` | Определяет текст/html внутри кнопки 'remove'.|
`chooseButtonClass` | Определяет css-class на кнопку 'choose'.|
`removeButtonClass` | Определяет css-class на кнопку 'remove'.|
`placeholder` | Определяет placeholder | t('flexberry-lookup.placeholder')
`value` | Определяет выбранный экземпляр модели (мастеровой объект) |
`relatedModel` | Определяет модель для которой будет редактироваться ссылка на мастеровой объект) |
`relationName` | Определяет имя отношения |
`projection` | Определяет, по какому представлению будут отображаться мастера в списке |
`sizeClass` | Определяет css-class размера окна, возможные варианты: small, large, fullscreen | small
`title` | Заголовок модального окна |
`lookupLimitPredicate` | Определяет функцию ограничения |
`lookupAdditionalLimitFunction` | Дополнительная функция ограниченяи для использования в GroupEdit относительно полей строки |
`autocomplete` | Режим автокомплита, в режиме "Только для чтения" не работает | false
`dropdown` | Режим выпадающего списка, в режиме "Только для чтения" не работает | false
`dropdownIsSearch` | Режим поиска (автокомплита) для LookUp-а в режиме выпадающего списка | false
`displayAttributeName` | Имя атрибута модели (свойства мастера), которое отображается для пользователя |
`sorting` | Направление сортировки по полю 'displayAttributeName', в режиме автокомплита и в режиме выпадающего списка | 'asc'
`minCharacters` | Минимальное количество символов для автокомпилита, в режиме автокомплита и в режиме выпадающего списка | 1
`maxResults` | Максимальное количество отображаемых записей в режиме автокомплита и в режиме в режиме выпадающего списка, не обязательное свойство | 10

## Встроенные в lookup дополнительные возможности

### Кнопка просмотра выбранного значения (preview)

Для использования данной возможности LookUp требуется определить следующие свойства:

```hbs
{% raw %}{{flexberry-lookup
  preview=(action "previewLookupValue")
  showPreviewButton=true
  previewFormRoute=previewFormRoute
  // ...
}}{% endraw %}
```

Ниже указаны добавленные в LookUp свойства для работы кнопки просмотра:

Свойство | Описание | Дефолтное значение
:---------------------|:------------------------------------------------------------|:----------------
`preview` | Определяет имя action'а, которое будет происходит по клику на кнопку 'preview'.|
`showPreviewButton` | Флаг, определяющий, отображать ли кнопку просмотра. | false
`previewFormRoute` | Определяет Route, в котором будет открыто выбранное значение.|
`previewOnSeparateRoute` | Флаг, определяющий, открывать ли выбранное значение в отдельной странице (по умолчанию открывается в модальном окне)| false
`previewButtonClass` | Определяет css-class на кнопку 'preview'.|
`previewText` | Определяет текст/html внутри кнопки 'preview'.|
`controllerForPreview` | Определяет Сontroller, для выбранного значения (если значение не задано сontroller берется из "previewFormRoute"). |

### LookUp в режиме автодополнения (autocomplete)

Автодополнение в LookUp-е позволяет осуществлять ввод с клавиатуры части значения и последующий выбор из предложенных вариантов.

Чтобы перевести LookUp в режим автодополнения, требуется добавить свойство `autocomplete`:

```hbs
{% raw %}
{{flexberry-lookup
  autocomplete=true
  // ...
}}{% endraw %}
```

Ниже указаны добавленные в LookUp свойства для работы автодополнения.

Свойство | Описание | Дефолтное значение
:--------------|:-----------------------------------------------------------|:-------------
`autocomplete` | Режим автокомплита, в режиме "Только для чтения" не работает | false
`minCharacters` | Минимальное количество символов для автокомпилита, в режиме автокомплита и в режиме выпадающего списка | 1
`maxResults` | Максимальное количество отображаемых записей в режиме автокомплита и в режиме в режиме выпадающего списка, не обязательное свойство | 10
`autocompleteProjection` | Имя проекции по которому считываются поля в запросе для отображения записей, не обязательное свойство используется для вычислимых полей | undefined
`autocompletePersistValue` | Флаг, определяющий оставлять или нет введенное значение при потере фокуса, если из результатов автокомплита не было выбрано никакое значение | false

#### Наложение сортировки на скрытые поля мастера

Если возникает необходимость сортировки скрытых полей при использовании автодополнения, то следует использовать свойство `autocompleteOrder`. Для этого в шаблоне приложения необходимо добавить данное свойство с указанием полей, по которым требуется отсортировать, и направления сортировки. К примеру:

```hbs
autocompleteOrder="moderated asc, parent desc"
```

#### Режим автокомплита с автоматическим выбором по введенному значению

В данном режиме:
1. При вводе в автокомплит значения, которого нет в справочнике, оно не обнуляется, но устанавливается пустая ссылка на справочник.
2. При вводе в автокомплит значения, которое есть в справочнике, устанавливается ссылка на справочник.

Это может быть полезно, если введенное в LookUp значение, нужно сохранять в отдельное поле, даже если в справочнике LookUp-а данного значения нет.

Для включения данного режима, следует свойство `autocompletePersistValue` установить в true, а свойство `displayValue` забиндить на поле, в которое нужно сохранять отображаемое в LookUp-е значение.

```hbs
{% raw %}
{{flexberry-lookup
  ...

  autocomplete=true
  autocompletePersistValue=true
  displayValue=model.lookupDisplayValue
}}{% endraw %}
```

### Задание ограничений

Задание ограничений на значения, отображаемые в списке для выбора, осуществляются посредством задания фильтра с помощью  [клиентского языка запросов](efd2_query-language.html). Данный фильтр задаётся в виде функции в свойство `lookupLimitPredicate` при задании шаблона.

```hbs
{% raw %}
{{flexberry-lookup
  lookupLimitPredicate=lookupCustomLimitPredicate
  // ...
}}{% endraw %}
```

Рассмотрим задание ограничения на примере. Пусть у сущности `Employee` есть мастеровая ссылка `Employee1` типа `Employee`. Требуется по LookUp-у отобразить только те записи, у которых `FirstName` больше или равен `FirstName` текущей выбранной записи.

Модель `Employee` имеет следующий вид:

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string'),
  lastName: DS.attr('string'),
  birthDate: DS.attr('date'),
  employee1: DS.belongsTo('employee', { inverse: null, async: false }),
});
```

В контроллере типа `Employee` создадим вычислимое свойство `lookupCustomLimitPredicate`, которое будет возвращать фильтр, чтобы "отобразить только те записи, у которых `FirstName` больше или равен `FirstName` текущей выбранной записи".

```javascript
export default EditFormController.extend({
  // Caption of this particular edit form.
  title: 'Employee',

  /**
   * Current function to limit accessible values of model employee1.
   *
   * @property lookupCustomLimitPredicate
   * @type String
   * @default undefined
   */
  lookupCustomLimitPredicate: Ember.computed('model.employee1', function() {
    let currentLookupValue = this.get('model.employee1');
    if (currentLookupValue) {
      let currentLookupText = this.get('model.employee1.firstName');
      return new StringPredicate('firstName', Query.FilterOperator.Ge, currentLookupText);
    }

    return undefined;
  })
});
```

Далее соответствующее свойство необходимо указать в шаблоне формы:

```hbs
{% raw %}
{{flexberry-lookup
  lookupLimitPredicate=lookupCustomLimitPredicate
  // ...
}}{% endraw %}
```

### Настройка модального окна LookUp-а

Настройки модального окна LookUp-а определены в `components/lookup-field/lookup-field-mixin.js`.

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

#### Пример задания ограничений в строке GE относительно элементов строки
Если возникает необходимость ограничить лукап по значению в элемента в GroupEdit в той же строке, то это можно сделать с помощью lookupAdditionalLimitFunction.

```js
  getCellComponent(attr, bindingPath, model) {
    let cellComponent = this._super(...arguments);
    if (attr.kind === 'belongsTo') {
      switch (`${model.modelName}+${bindingPath}`) {
        case 'ember-flexberry-dummy-vote+author':
          cellComponent.componentProperties = this.get('lookupDynamicProperties');
          break;      }
    }
    return cellComponent;
  }
});
```

```js
  lookupDynamicProperties: Ember.computed(function() {
    ...
    lookupAdditionalLimitFunction = function (relationModel) {
      return new StringPredicate('eMail').contains(relationModel.get('voteType'));
    };

    return {
      ...
      lookupAdditionalLimitFunction
      ...
    };
  })
```

В данном примере отображаемые объекты лукапа ограничены полем GroupEdit 'voteType' по значению мастера 'eMail'.
relationModel.get('...') - модель, для которой лукапом редактируется мастер.


#### Установка заголовка и размера окна, открываемого по LookUp-у

Если требуется изменить размер открываемого по LookUp-у модального окна, то можно переопределить заданные по умолчанию значения.

* `modalWindowWidth` - это ширина открываемого по LookUp-у модального окна.
* `modalWindowHeight` - это высота открываемого по LookUp-у модального окна.

Заголовок окна, открываемого по LookUp-у, устанавливается в свойстве `title` компонента `lookup-field` в шаблоне соответствующей формы редактирования. Например, в шаблоне формы редактирования `employee.hbs` встраивание LookUp-а может выглядеть следующим образом:

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

В результате заголовок из свойства `title` компонента `lookup-field` будет отображаться в модальном окне, открываемом по LookUp-у:

![](/images/pages/products/flexberry-ember/flexberry-lookup/lookuptitle.png)

#### Настройка фильтрации и количества элементов на странице

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

{% include note.html content="Необходимо выбрать тип поиска (`filterByAnyWord` или `filterByAllWords`), так на LookUp-форме использовать использовать можно только один из них." %}

Далее указать событие в шаблоне настраевомого для LookUp списка:

```hbs
{% raw %}{{flexberry-lookup
    // ...
    lookupWindowCustomProperties=(action 'getLookupFolvProperties')
}}{% endraw %}
```

Реализация отображена на [ember-стенде](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-lookup/customizing-window-example).

#### Настройка иерархии

В контроллере формы редактирования указать:

```javascript
export default EditFormController.extend({
  actions: {
    getLookupFolvProperties(options) {
      if (options.relationName === 'type') { // Свойство LookUp-а.
        return {
          // Показывать ли кнопку переключения иерархии, если иерархия для списка доступна
          // (при false кнопка показывается)
          disableHierarchicalMode: false,

          // Активировать иерархию при загрузке lookup-формы.
          inHierarchicalMode: true,

          // Свойство, по которому строится иерархия.
          hierarchicalAttribute: 'Name',
        };
      }
    },
  }
});
```

### Блочная форма компонента

{% include important.html content="В данный момент доступна только для мобильного шаблона." %}

Возможно использовать компонент в блочной форме:

```hbs
{% raw %}{{#flexberry-lookup ...}}
  {{model.employee1.firstName}}<br>
  {{model.employee1.lastName}}
{{/flexberry-lookup}}{% endraw %}
```
в этом случае прикладной программист может переопределить как будет выглядеть отображение мастера на форме

### Автозаполнение значения в LookUp-е.

Автодополнение позволяет сразу подставлять подходящее по условию значение.

Чтобы вкючить автодополнение в LookUp-е, требуется добавить свойство `autofillByLimit` и указать функцию ограничения в `lookupLimitPredicate`:

```hbs
{% raw %}
{{flexberry-lookup
  autofillByLimit=true
  lookupLimitPredicate=lookupLimitPredicate
  // ...
}}{% endraw %}
```
В этом случае если указанному ограничению соответствует только один объект, то оно оно сразу будет установлено. Если ранее было выбрано другое значение, автозаполнение заменит его, но только если ограничению соответствует один объект.

### Пользовательские настройки для списка в LookUp-е (componentName)

Для списка в LookUp-e можно использовать пользовательские настройки из [сервиса настроек пользователя](ef2_model-user-settings-service.html).
Они задаются в свойство `componentName` при задании шаблона.

```hbs
{% raw %}
{{flexberry-lookup
  componentName="lookUpClassLookup"
  // ...
}}{% endraw %}
```
