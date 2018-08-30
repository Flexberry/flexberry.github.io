---
title: Списковые компоненты
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_object-list-view.html
lang: ru
summary: Описание, используемые компоненты и настройки списков в приложениях с испольхованием технологии Flexberry Ember
---

## Описание

Основное предназначение Flexberry Objectlistview - просмотр списка доступных объектов с возможностью их последующего открытия и редактирования (в контрол встроен пейджинг, позволяющий просматривать данные порционно).

Для добавления контрола на страницу, можно воспользоваться [шаблоном](ef_template.html) (ниже представлен шаблон для добавления на [списковую форму](ef_forms.html)):

```hbs
{% raw %}{{flexberry-objectlistview
	componentName = "ordersObjectListView"
	modelController = this
	modelName = "order"
	modelProjection = modelProjection
	content = model

	createNewButton = true
	refreshButton = true
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

{% include note.html content="Синтаксис вида `addColumnToSorting=(action \"addColumnToSorting\")` определяет, что используется ember closure action. Если требуемое действие не определено, то при вызове соответствующей функциональности в консоли браузера появится ошибка." %}

### Список компонентов

Наименование компонента          |Краткое описание компонента  
:----------------------------|:------------------------------  
`componentName`| Имя компонента. Должно быть уникальное в пределах однойстраницы.  
`modelController`| Текущий контроллер страницы.  
`modelName`| Имя текущей модели (список каких объектов отображается).  
`modelProjection `| Текущее используемое представление.  
`content `| Отображаемые в контроле данные.  
`createNewButton `| Флаг, определяющий, отображать ли кнопку создания на [панели управления](ef_olv-toolbar.html).  
`refreshButton `| Флаг, определяющий, отображать ли кнопку обновления на [панели управления](ef_olv-toolbar.html).  
`deleteButton `| Флаг, определяющий, отображать ли кнопку удаления на [панели управления](ef_olv-toolbar.html).  
`showCheckBoxInRow`| Флаг, определяющий, отображать ли чекбоксы для выделения записей в каждой строчке.  
`showDeleteButtonInRow`| Флаг, определяющий, отображать ли кнопку удаления строки в каждой строчке.  
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-olv, "Удалить запись".  
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона f-olv, "Редактировать запись".  
`rowClickable `| Флаг, определяющий, следует ли обрабатывать нажатие на строчку.  
`action `| Действие, выполняемое при нажатии на строчку.  
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
`singleColumnHeaderTitle `| Заголовок для мобильного представления компонента, вместо названий колонок. Если не задан или равен "", то шапка таблицы в компоненте скрывается.  
`colsConfigButton`| Флаг (`true`/`false`) включающий/выключающий отображение кнопок пользовательских настроек.  

Значения по умолчанию:

```javascript
action: 'rowClick',
createNewButton: false,
refreshButton: false,
orderable: false,
rowClickable: true,
showCheckBoxInRow: false,
showDeleteButtonInRow: false,
showDeleteMenuItemInRow: false,
showEditMenuItemInRow: true
colsConfigButton - true
```

## Настройка панели управления

В состав контрола Flexberry Objectlistview входит [тулбар](ef_olv-toolbar.html), который можно настраивать и добавлять пользовательские кнопки.

## Пользовательские кнопки в тулбаре и строках списка

Панель управления, как и кнопки в строках, мжет быть дополнена пользовательскими кнопками, реализующими необходимые прикладные функции для работы со список. Подробнее описано в статье [Пользовательские кнопки для списков](ef_custom-buttons.html).


## Настройка иерархического списка

Если список является иерархическим (у объекта есть ссылка на самого себя), то иерархия для списка установлена по умолчанию.

![](/images/pages/ABratchikova/Иерархия folv.png)

Если иерархию для списка необходимо отключить, то в шаблоне следует прописать `disableHierarchicalMode = true`.

Если в модели есть две или больше ссылки на себя (по которым может быть иерархия), то нужно указать имя той `по которой должна быть иерархия`: `hierarchyByAttribute = propertyName`.

## Наложение ограничений

Особенности наложения ограничений на Flexberry Objectlistview связаны с тем, что данные для контрола вычитываются в роуте. Соответственно, чтобы не допустить вычитывания данных без наложенного ограничения, ограничение должно быть определено, когда выполняется хук `model` в роуте формы.

Таким образом, чтобы наложить ограничение, необходимо переопределить метод `objectListViewLimitPredicate` в роуте прикладной списковой формы, чтобы он возвращал предикат для ограничения.

Например, есть форма `limit-function-example`. Eсли на странице отображается чётное количество записей, необходимо вывести записи, у которых в поле "address" есть буква "S". При нечетном количестве - имеющие в поле "address" букву "п".

Переопределяем метод `objectListViewLimitPredicate` в роуте соответствующей прикладной списковой формы.

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

## Удаление элементов списка

Для удаления элементов списка используется метод `beforeDeleteRecord`. Метод поддерживает асинхронный режим, то есть можно возвращать `promises` в качестве значения результата.

### Удаление всех выделенных элементов

Для настройки одновременного удаления всех выделенных элементов списка нужно в контроллере прописать событие `beforeDeleteAllRecords`:

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

## Проверка списков перед удалением элементов

При работе со списком есть возможность возвращать promise через `return` в теле обработчика `beforeDeleteRecord`, то есть можно реализовать асинхронную логику. Если сделать return <промис>- тогда операция удаления будет вызываться после того, как будет выполнен promise . Если он выполнение будет прервано, то удаление выполнено не будет.

## Отображение ошибок списка

Если список содержит ошибки, то будет отображена форма с указанием ошибки:

* при загрузке списка с ошибкой в первый раз будет отображен шаблон `error.hbs`
* в контроллере есть список ранее загруженных объектов - ошибка будет показана через компонент.

Изменить это на прикладном уровне можно с помощью метода `onModelLoadingRejected` в роуте.

## Настройка фильтрации на списке

Особенности настройки и работы с фильтрами на списках описаны в статье [Настройка фильтров на списках](ef_configuring-filters.html).

## Поиск по всем атрибутам

Особенности настройки стандартного контрола поиска для списковых и мастеровых форм (поднимаемых по LookUp) описаны в статье [Поиск по всем атрибутам](ef_search-attributes.html).

## Изменение ширины столбцов

Принципы настройки и использования свойства изменения ширины столбцов на списках элементов и их детейлов описаны в статье [Изменение ширины столбцов](ef_olv-resize.html).

## Настройка панели управления для списков

Использование технологических и пользовательских кнопок в тулбаре описано в статье [Настройка панели управления для списков](ef_olv-toolbar.html).

## Инструменты работы с объектами на списках

Инструменты работы с объектами на странице/ всех страницах описаны в статье описаны в статье [Инструменты работы с объектами на списках](ef_list-component-select-all.html).

## Настройка ячеек списка

Возможности настроек поведения отдельных ячеек списка описано в статье [Блокирование отдельных ячеек списка](ef_block-cells-click.html).

## Вычислимые свойства в getCellComponent

Чтобы создать вычисляемое свойство нужно, в `controllers`, в `getCellComponent` добавить свойство `computedProperties: { thisController: this }`:

```javascript
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

Таким образом в свойстве `computedProperties` у текущего controller-а будет `this` из [dynamic-properties](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/dynamic-properties.js) со всеми своими observer-ами. Теперь чтобы поменять любое из свойств встраимого компонента достаточно изменить значение в `computedProperties`:

```javascript
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
