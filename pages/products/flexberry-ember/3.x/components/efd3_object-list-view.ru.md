---
title: Списковый компонент
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, OLV, списки
toc: true
permalink: ru/efd3_object-list-view.html
lang: ru
summary: Используемые компоненты и настройки списков в приложениях с использованием технологии Flexberry Ember, экспорт, удаление элементов
---

## Назначение компонента

Основное предназначение __flexberry-ojectlistview__ – просмотр списка доступных объектов с возможностью их последующего открытия и редактирования (в контрол встроен пейджинг, позволяющий просматривать данные порционно).

## Обзор возможностей и API компонента

Для добавления контрола на страницу можно воспользоваться [шаблоном](efd3_template.html) (ниже представлен шаблон для добавления на [списковую форму](efd3_forms.html)):

```hbs
{% raw %}{{flexberry-objectlistview
  componentName = "ordersObjectListView"
  modelController = this
  modelName = "order"
  modelProjection = modelProjection
  content = model

  createNewButton = true
  refreshButton = true
  defaultSortingButton = true
  showDeleteButtonInRow = true
  colsConfigButton = true

  sorting = computedSorting
  orderable = true
  sortByColumn = (action "sortByColumn")
  addColumnToSorting = (action "addColumnToSorting")
  action = "rowClick"

  pages = pages
  perPageValue = perPageValue
  perPageValues = perPageValues
  hasPreviousPage = hasPreviousPage
  hasNextPage = hasNextPage
  previousPage = (action "previousPage")
  gotoPage = (action "gotoPage")
  nextPage = (action "nextPage")
}}{% endraw %}
```

> Синтаксис вида `addColumnToSorting=(action \"addColumnToSorting\")` определяет, что используется _ember closure action_. Если требуемое действие не определено, то при вызове соответствующей функциональности в консоли браузера появится ошибка.

Если на форме используются дополнительные события, в шаблон может быть передано как имя события

```hbs
{% raw %}{{flexberry-objectlistview
...
action="customActionName"
}}{% endraw %}
```

так и собственно само событие:

```hbs
{% raw %}{{flexberry-objectlistview
...
action=customActionName
}}{% endraw %}
```

### Список компонентов flexberry-ojectlistview

Наименование компонента |Краткое описание компонента  
:-----------------------|:------------------------------  
`componentName`| Имя компонента. Должно быть уникальное в пределах одной страницы.  
`modelController`| Текущий контроллер страницы.  
`modelName`| Имя текущей модели (список каких объектов отображается).  
`modelProjection`| Текущее используемое представление.  
`content`| Отображаемые в контроле данные.  
`createNewButton`| Флаг, определяющий, отображать ли кнопку создания на [панели управления](efd3_setting-lists.html).  
`refreshButton`| Флаг, определяющий, отображать ли кнопку обновления на панели управления.
`defaultSortingButton`| Флаг, определяющий, отображать ли кнопку установки сортировки по умолчанию.
`deleteButton`| Флаг, определяющий, отображать ли кнопку удаления на панели управления.  
`showCheckBoxInRow`| Флаг, определяющий, отображать ли чекбоксы для выделения записей в каждой строчке.  
`showDeleteButtonInRow`| Флаг, определяющий, отображать ли кнопку удаления строки в каждой строчке.  
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-olv, "Удалить запись".  
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-olv, "Редактировать запись".  
`rowClickable`| Флаг, определяющий, следует ли обрабатывать нажатие на строчку.  
`action`| Действие, выполняемое при нажатии на строчку.  
`orderable`| Флаг, определяющий, возможно ли производить сортировку по столбцам в компоненте.  
`sorting`| Метод определения текущей сортировки.  
`sortByColumn`| Действие (`action`) контроллера, которое должно быть выполнено для сортировки по столбцу.  
`addColumnToSorting`| Действие (`action`) контроллера, которое должно быть выполнено для добавления сортировки по столбцу.  
`pages`| Метод определения доступных страниц для отображения в пейджинге.  
`perPageValue`| Метод определения текущей настройки, где указано, по сколько записей отображается на странице.  
`perPageValues`| Массив, определяющий, по сколько записей на странице можно отображать.  
`hasPreviousPage`| Флаг, определяющий, есть ли у текущей страницы предшествующая.  
`hasNextPage`| Флаг, определяющий, есть ли у текущей страницы последующая.  
`previousPage`| Метод для перехода на предыдущую страницу.  
`gotoPage`| Метод для перехода на заданную страницу.  
`nextPage`| Метод для перехода на следующую страницу.  
`editFormRoute`| Задаёт имя роута формы редактирования, в котором будет открываться модель.  
`singleColumnHeaderTitle`| Заголовок для мобильного представления компонента, вместо названий колонок. Если не задан или равен "", то шапка таблицы в компоненте скрывается.  
`colsConfigButton`| Флаг (`true`/`false`) включающий/выключающий отображение кнопок пользовательских настроек.  
`bottomPagination`| Флаг, определяющий положение пагинации верху/снизу.  
`tableStriped`| Флаг, определяющий, будут ли строки таблицы раскрашены через одну ("зебра"). Значение по умолчанию: true.  
`_availableHierarchicalMode`| Флаг, включающий настройку иерархического списка. Значение по умолчанию: false.  
`disableHierarchicalMode`| Флаг, отключающий настройку иерархического списка. Значение по умолчанию: false.  
`hierarchicalIndent`| Отступ в пикселях для вложенных элементов. Значение по умолчанию: 20.  
`hierarchyPaging`| Флаг, используемый для включения/отключения иерархической пейджинга. Значение по умолчанию: false.  
`hierarchicalAttribute`| Имя атрибута для построения иерархии.  
`inHierarchicalMode`| Флаг указывающий, когда компонент находится в иерархическом режиме. Значение по умолчанию: false.  
`onEditForm`| Флаг указывающий, размещен ли компонент на форме редактирования. Значение по умолчанию: false.  

Значения по умолчанию:

```javascript
{% raw %}
action: 'rowClick',
createNewButton: false,
refreshButton: false,
defaultSortingButton: true,
orderable: false,
rowClickable: true,
showCheckBoxInRow: false,
showDeleteButtonInRow: false,
showDeleteMenuItemInRow: false,
showEditMenuItemInRow: true,
colsConfigButton: true,
bottomPagination: true
{% endraw %}
```

## Настройки списка по умолчанию

### Экспорт в Excel

Технология `Ember Flexberry` предоставляет возможность экспорта списков. Для того чтобы экспорт стал доступен в приложении, необходимо осуществить [настройки на бекэнде](fan_odata-export-to-excel.html).

В шаблоне непосредственно формы должно быть указано

```hbs
{% raw %}{{flexberry-objectlistview
...
exportExcelButton=true
{% endraw %}
```

Настройки экспорта можно сохранять, задав наименование. Также можно изменять наименование столбцов для экспорта.

### Удаление элементов списка

Для удаления элементов списка используется метод `beforeDeleteRecord`. Метод поддерживает асинхронный режим, то есть можно возвращать `promise` в качестве значения результата.

### Удаление всех выделенных элементов

Для настройки одновременного удаления всех выделенных элементов списка, нужно в контроллере прописать событие `beforeDeleteAllRecords`:

```javascript
import ListFormController from './list-form';

export default ListFormController.extend({
  actions: {
    beforeDeleteAllRecords(modelName, data) {
      if (modelName === 'application-user') {
        data.cancel = false;
      }
    }
  }
});
```

и указать его в шаблоне списка:

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  beforeDeleteAllRecords=(action 'beforeDeleteAllRecords')
  ...
}}{% endraw %}
```

### Проверка списков перед удалением элементов

При работе со списком есть возможность возвращать `promise` через `return` в теле обработчика `beforeDeleteRecord`, то есть можно реализовать асинхронную логику. Если сделать `return < promise >` – тогда операция удаления будет вызываться после того, как будет выполнен `promise`. Если его выполнение будет прервано, то удаление выполнено не будет.

### Отображение ошибок списка

Если список содержит ошибки, то будет отображена форма с указанием ошибки:

* при загрузке списка с ошибкой в первый раз будет отображен шаблон `error.hbs`;
* в контроллере есть список ранее загруженных объектов – ошибка будет показана через компонент.

Изменить это на прикладном уровне можно с помощью метода `onModelLoadingRejected` в роуте.

### Изменение ширины столбцов

Изменение ширины столбцов для списка и [детейлов](efd3_groupedit.html) на форме редактирования осуществляется по общим принципам.

Для того чтобы включить/отключить изменение ширины столбцов, необходимо задать у контрола в шаблоне свойство `allowColumnResize` (для обычных приложений это свойство по умолчанию имеет значение `true`, для мобильных – `false`).

> Несмотря на то, что для мобильных приложений изменение ширины столбцов доступно, включать данный флаг для данного типа приложений не рекомендуется.

Если плагин инициализировался правильно, то после загрузки контрола при наведении курсора на границу между столбцами курсор меняет свой внешний вид и появляется возможность изменять ширину столбцов.

Возможно запретить изменение размеров отдельных столбцов, используя [начальные настройки отображаемых столбцов](efd3_model-user-settings-service.html) в свойстве `columnWidths`.

## Сервис настроек пользователя

Если [сервис настроек пользователя](efd3_model-user-settings-service.html) включён, то при загрузке спискового контрола вычитывается сохранённая настройка ширины столбцов, а при изменении ширины столбцов – настройка сохраняется.

## Указание функции ограничения

Особенности настройки ограничений на списках описаны в статье [Инструменты ограничения списка](efd3_list-restriction-tools.html).

## Пользовательские кнопки в тулбаре

Панель управления, как и кнопки в строках, может быть дополнена пользовательскими кнопками, реализующими необходимые прикладные функции для работы со списком. Подробнее описано в статье [Настройки списков](efd3_setting-lists.html).

## Раскраска строк

У компонента существует свойство раскраски строк `tableStriped`. При включенном свойстве строки таблицы окрашиваются через одну для удобства восприятия. По умолчанию это свойство включено для браузерных версий и выключено для мобильной.

## Ограничение длины текста в ячейках

Есть возможность задавать длину текста в ячейках спискового компонента. Пример можно посмотреть на [тестовом стенде](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/#/components-examples/flexberry-objectlistview/limited-text-size-example).

Длину текста можно задать, прописав настройку `maxTextLength` и/или `cutBySpaces`в `getCellComponent` в контроллере:

```js
/**
  Method to get type and attributes of a component,
  which will be embeded in object-list-view cell.

  @method getCellComponent.
  @param {Object} attr Attribute of projection property related to current table cell.
  @param {String} bindingPath Path to model property related to current table cell.
  @param {DS.Model} modelClass Model class of data record related to current table row.
  @return {Object} Object containing name & properties of component, which will be used to render current table cell.
  { componentName: 'my-component',  componentProperties: { ... } }.
  */
getCellComponent: function(attr) {
  let cellComponent = {
    componentName: 'object-list-view-cell',
    componentProperties: {
      maxTextLength: 10,
      cutBySpaces: true,
    }
  };

  if (attr.caption === 'Text') {
    cellComponent = {
      componentName: 'flexberry-text-cell',
      componentProperties: {
        maxTextLength: 10,
        cutBySpaces: true,
      }
    };
  }

  return cellComponent;
}
```

Длина текста `maxTextLength`задается в символах. Если содержимое ячейки не влезает в указанную длину, то будет показываться заданное количество символов и троеточие после. Если задать длину 0, то ограничение длины отключено. По умолчанию равно 0.

Включенная настройка `cutBySpaces` будет обрезать строки по первому встреченному пробелу. По умолчанию отключена.

## Возможности сервиса objectlistview-events

`objectlistview-events` - это сервис, который предназначен для управления событиями, связанными с компонентом flexberry-objectlistview. Чтобы получить доступ к сервису, нужно прописать:

``` js
  /**
    Service that triggers objectlistview events.

    @property objectlistviewEventsService
    @type Service
  */
  objectlistviewEventsService: service('objectlistview-events'),
```

Основные возможности этого сервиса:

* Управление выбранными записями: Сервис хранит и управляет списком выбранных записей для всех компонентов flexberry-objectlistview. Он предоставляет методы для получения, очистки и восстановления выбранных записей. Примеры использования:

  ```js
  // Получение выбранных записей
  let selectedRecords = this.get('objectlistviewEvents').getSelectedRecords('myOlvComponentName');

  // Очистка выбранных записей
  this.get('objectlistviewEvents').clearSelectedRecords('myOlvComponentName');

  // Восстановление выбранных записей после обновления страницы или перехода на другую страницу
  this.get('objectlistviewEvents').restoreSelectedRecords('myOlvComponentName');

  ```

* Управление фильтрами: Сервис хранит информацию о доступных фильтрах для каждого компонента flexberry-objectlistview и предоставляет методы для их установки и получения. Примеры использования:

  ```js
    // Установка набора столбцов с фильтрами для компонента flexberry-objectlistview
    this.get('objectlistviewEvents').setOlvFilterColumnsArray('myOlvComponentName', [
      { name: 'name', caption: 'Name', filterType: 'text' },
      { name: 'age', caption: 'Age', filterType: 'number' }
    ]);

    // Получение набора столбцов с фильтрами
    let filters = this.get('objectlistviewEvents').getOlvFilterColumnsArray('myOlvComponentName');
  ```

* Триггеры событий: Сервис предоставляет множество методов для генерации различных событий, связанных с компонентом flexberry-objectlistview, таких как добавление, удаление, выбор и изменение записей, применение фильтров, обновление списка и т.д. Примеры использования:

  ```js
  // Триггер события "добавить новую строку"
  this.get('objectlistviewEvents').addRowTrigger('myOlvComponentName');

  // Триггер события "удалить выбранные строки"
  this.get('objectlistviewEvents').deleteRowsTrigger('myOlvComponentName', true);

  // Триггер события "обновить список"
  this.get('objectlistviewEvents').refreshListTrigger('myOlvComponentName');
  ```

* Управление состоянием загрузки: Сервис предоставляет методы для установки и получения состояния загрузки формы, которые используются в приложении. Примеры использования:

  ```js
  // Установка состояния загрузки
  this.get('objectlistviewEvents').setLoadingState('loading');

  // Получение состояния загрузки
  let loadingState = this.get('objectlistviewEvents.loadingState');

  ```

* Управление сортировкой: Сервис предоставляет методы для установки и получения параметров сортировки для компонента flexberry-objectlistview. Примеры использования:

  ```js
  // Установка сортировки для flexberry-objectlistview
  this.get('objectlistviewEvents').setSortingTrigger('myOlvComponentName', [
    { propName: 'name', direction: 'asc' },
    { propName: 'age', direction: 'desc' } // Массив определений сортировки
  ]);

  // Установка сортировки для flexberry-groupedit
  this.get('objectlistviewEvents').setGeSortTrigger('myGeComponentName', [
    { propName: 'name', direction: 'asc' },
    { propName: 'age', direction: 'desc' }], // Массив определений сортировки
    this.get('model.colDescs') // Массив столбцов
  );

  // Установка сортировки по умолчанию для flexberry-groupedit
  this.get('_groupEditEventsService').setDefaultGeSortTrigger(this.get('colDescs'));

  // Применение сортировки для flexberry-groupedit
  this.get('objectlistviewEvents').geSortApplyTrigger('myOlvComponentName', [
    { propName: 'name', direction: 'asc' },
    { propName: 'age', direction: 'desc' }
  ]);
  ```

* Управление шириной столбцов: Сервис предоставляет метод `updateWidthTrigger` для обновления ширины столбцов в компоненте flexberry-objectlistview. Пример использования:

  ```js
    updateWidth() {
      this.get('objectlistviewEventsService').updateWidthTrigger('myOlvComponentName');
    },
  ```

  Имя компонента 'myOlvComponentName' можно не передавать, тогда триггериться будут все доступные компоненты.

* Управление выделением всех записей: Сервис предоставляет метод для выделения или снятия выделения со всех записей в компоненте flexberry-objectlistview. Примеры использования:

  ```js
  // Выделение всех записей
  this.get('objectlistviewEvents').updateSelectAllTrigger('myOlvComponentName', true, false);

  // Снятие выделения со всех записей
  this.get('objectlistviewEvents').updateSelectAllTrigger('myOlvComponentName', false, false);
  ```

* Управление перемещением записей: Сервис предоставляет метод для перемещения записей вверх или вниз в компоненте flexberry-objectlistview. Примеры использования:

  ```js
  // Перемещение записей вниз
  this.get('objectlistviewEvents').moveRowTrigger('myOlvComponentName', 1);

  // Перемещение записей вверх
  this.get('objectlistviewEvents').moveRowTrigger('myOlvComponentName', -1);
  ```

* Управление изменением фильтра: Сервис предоставляет метод для генерации события об изменении фильтра в компоненте flexberry-objectlistview, установку, получение фильтра. Примеры использования:

  ```hbs
  {{flexberry-objectlistview
    componentName='myOlvComponentName'
    modelName='user'
    modelProjection='UserE'
    limit=getLimitFunction('myOlvComponentName')
    ...
  }}
  ```

  ```js
  import { SimplePredicate } from 'ember-flexberry-data/query/predicate';

  // Создание LimitFunction
  let limitFunction = new SimplePredicate('Name', 'eq', 'John');

  // Установка LimitFunction для компонента 'myOlvComponentName'
  this.get('objectlistviewEvents').setLimitFunction(limitFunction, 'myOlvComponentName');

  // Получение LimitFunction для компонента 'myOlvComponentName'
  limitFunction = this.get('objectlistviewEvents').getLimitFunction('myOlvComponentName');

  // Генерация события об изменении фильтра
  this.get('objectlistviewEvents').filterConditionChangedTrigger(
    'myOlvComponentName',
    { name: 'name', caption: 'Name', filterType: 'text' }, // Объект с описанием фильтра
    'newFilterValue',
    'oldFilterValue'
  );
  ```

* Управление диалогом редактирования записи: Сервис предоставляет методы для генерации событий о создании и скрытии диалога редактирования записи. Примеры использования:

  ```js
  // Генерация события о создании диалога редактирования
  this.get('objectlistviewEvents').editRecordDialogCreatedTrigger();

  // Генерация события о скрытии диалога редактирования
  this.get('objectlistviewEvents').editRecordDialogHiddenTrigger();
  ```

## Иерархический список

Для спискового объекта есть возможность задать отображение иерархий (когда у детейла есть детейл). Примеры есть на тестовом стенде:

* [Пример иерархии на списке](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/#/components-examples/flexberry-objectlistview/hierarchy-example)
* [Пример иерархии c пейджингом на списке](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/#/components-examples/flexberry-objectlistview/hierarchy-paging-example).

Атрибуты, влияющие на настройку иерархии:

Наименование компонента |Краткое описание компонента  |Значение по умолчанию
:-----------------------|:------------------------------ |:------------------------------  
`_availableHierarchicalMode`| Флаг, включающий настройку иерархического списка. | false
`disableHierarchicalMode`| Флаг, отключающий настройку иерархического списка. | false
`hierarchicalIndent`| Отступ в пикселях для вложенных элементов. | 20
`hierarchyPaging`| Флаг, используемый для включения/отключения иерархической пейджинга. | false
`hierarchicalAttribute`| Имя атрибута для построения иерархии. Если указано, будет предпринята попытка построить эту иерархию атрибутов. | undefined
`inHierarchicalMode`| Флаг указывающий, когда компонент находится в иерархическом режиме. | false

Для отображения списка в иерархическом режиме достаточно прописать в шаблоне компонента `disableHierarchicalMode=true`. Однако, настройка может выглядеть и так:

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  disableHierarchicalMode=false
  inHierarchicalMode=true
  hierarchicalAttribute='parent'
  hierarchyPaging=true
}}
{% endraw %}
```

## Размещение компонента списка на форме редактирования

Списковый компонент можно разместить на форме редактирования. Это показывает [пример](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/#/components-examples/flexberry-objectlistview/on-edit-form?sort=%2Bname) на тестовом стенде при открытии любой записи на редактирование.

Для расположения flexberry-objectlistview на форме редактирования нужно прописать компонент в шаблоне нужной эдит форме с атрибутом `onEditForm=true`, в роуте эдит форме прописать в `developerUserSettings` списковый компонент, в контроллере эдит формы прописать свойства спискового компонента (используя `OlvOnEditMixin` миксин):

```hbs
{% raw %}
{{!-- templates/example/edit-form.hbs --}}

{{flexberry-objectlistview
  ...
  componentName="FOLVOnEditForm"
  onEditForm=true
  content=customFolvContent
  modelName=folvModelName
  editFormRoute=folvEditFormRoute
  modelProjection=folvProjection
  customButtons=customButtons
  userButtonAddAction=(action 'userButtonAddAction')
}}
{% endraw %}
```

```js
{% raw %}
// routes/example/edit-form.js

import EditFormRoute from 'ember-flexberry/routes/edit-form';
import { computed } from '@ember/object';
export default EditFormRoute.extend({
  /**
    Name of model projection to be used as record's properties limitation.

    @property modelProjection
    @type String
    @default 'ExampleE'
   */
  modelProjection: 'ExampleE',

  developerUserSettings: computed(function() {
    return { FOLVOnEditForm: { } }}),
  /**
    Name of model to be used as form's record type.

    @property modelName
    @type String
    @default 'example-model-name'
   */
  modelName: 'example-model-name'
});
{% endraw %}
```

```js
{% raw %}
// controlles/example/edit-from.js

import EditFormController from 'ember-flexberry/controllers/edit-form';
import OlvOnEditMixin from 'ember-flexberry/mixins/flexberry-objectlistview-on-edit-form-controller';

export default EditFormController.extend(OlvOnEditMixin, {
  /**
   Route name for transition after close edit form.

   @property parentRoute
   @type String
  */
  parentRoute: 'example/edit-form',

  /**
    Name of related to FOLV edit form route.

    @property folvEditFormRoute
    @type String
   */
  folvEditFormRoute: 'folv-edit-form-route',

  /**
    Name of FOLV model.

    @property folvModelName
    @type String
   */
  folvModelName: 'folv-edit-form-model-name',

  /**
    Name of FOLV projection.

    @property folvProjection
    @type String
   */
  folvProjection: 'folvEditFormL',

  /**
    Property to form array of special structures of custom user buttons.

    @property customButtons
    @type Array
   */
  customButtons: computed('i18n.locale', function() {
    let i18n = this.get('i18n');
    return [{
      buttonName: 'User button',
      buttonAction: 'userButtonAddAction',
      buttonClasses: 'my-add-user-button add-click-button positive'
    }];
  }),

  actions: {
    /**
      Handler for click on custom user button.

      @method userButtonAddAction
    */
    userButtonAddAction: function() {
      let thisUrl = this.get('target.url');
      this.transitionToRoute(this.get('folvEditFormRoute') + '.new')
      .then((newRoute) => {
        newRoute.controller.set('parentRoute', thisUrl);
      });
    },
  },
});
{% endraw %}
```
