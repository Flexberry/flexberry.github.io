---
title: Flexberry Objectlistview
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_object-list-view.html
lang: en
summary: Представлено описание контрола для просмотра списка объектов Flexberry Objectlistview
---

## Описание

Основное предназначение контрола Flexberry Objectlistview - просмотр списка доступных объектов с возможностью их последующего открытия и редактирования (в контрол встроен пейджинг, позволяющий просматривать данные порционно).

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
:--------------------------------|:------------------------------------------------------------------  
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

```js
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

## Настройка панели управления Flexberry Objectlistview

В состав контрола Flexberry Objectlistview входит [тулбар, который можно настраивать](ef_olv-toolbar.html) и добавлять пользовательские кнопки.

## Иерархия на Flexberry Objectlistview

Если список является иерархическим (у объекта есть ссылка на самого себя), то иерархия для списка установлена по умолчанию.

![](/images/pages/ABratchikova/Иерархия folv.png)

Если иерархию для списка необходимо отключить, то в шаблоне следует прописать `disableHierarchicalMode = true`.

Если в модели есть две или больше ссылки на себя (по которым может быть иерархия), то нужно указать имя той `по которой должна быть иерархия`: `hierarchyByAttribute = propertyName`.

## Наложение ограничений на Flexberry Objectlistview

Особенности наложения ограничений на Flexberry Objectlistview связаны с тем, что данные для контрола вычитываются в роуте. Соответственно, чтобы не допустить вычитывания данных без наложенного ограничения, ограничение должно быть определено, когда выполняется хук `model` в роуте формы.

Таким образом, чтобы наложить ограничение, необходимо переопределить метод `objectListViewLimitPredicate` в роуте прикладной списковой формы, чтобы он возвращал предикат для ограничения.

Например, есть форма `limit-function-example`. Требуется, если на странице отображается чётное количество записей, то отображать только записи, у которых в поле "address" есть буква "S", иначе те, у которых в поле "address" есть буква "п".

Переопределяем метод `objectListViewLimitPredicate` в роуте соответствующей прикладной списковой формы.

```javascript
import Ember from 'ember';
import ListFormRoute from 'ember-flexberry/routes/list-form';
import { StringPredicate } from 'ember-flexberry-data/query/predicate';

export default ListFormRoute.extend({
  /**
   * Name of model projection to be used as record's properties limitation.
   *
   * @property modelProjection
   * @type String
   * @default 'FolvWithLimitFunctionExampleView'
   */
  modelProjection: 'FolvWithLimitFunctionExampleView',

  /**
   * Name of model to be used as list's records types.
   *
   * @property modelName
   * @type String
   * @default 'ember-flexberry-dummy-suggestion'
   */
  modelName: 'ember-flexberry-dummy-suggestion',

  /**
   * It overrides base method and forms the limit predicate for loaded data.
     If there is displayed even number or records per page, records where 'address' attribute contains letter 'S' are filtered.
     If there is displayed odd number or records per page, records where 'address' attribute contains letter 'п' are filtered.

   * @method objectListViewLimitPredicate
   * @public
   *
   * @param {Object} options Method options.
   * @param {String} [options.modelName] Type of records to load.
   * @param {String} [options.projectionName] Projection name to load data by.
   * @param {String} [options.params] Current route query parameters.
   * @return {BasePredicate} The predicate to limit loaded data.
   */
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

Для удаления элементов списка используется метод `beforeDeleteRecord`. Метод поддерживает асинхронный режим, тщ есть можно возвращать `promises` в качестве значения результата.

## Проверка списков перед удалением элементов

При работе со списком есть возможность возвращать promise через `return` в теле обработчика `beforeDeleteRecord`, то есть можно реализовать асинхронную логику. Если сделать return <промис>- тогда операция удаления будет вызываться после того, как будет выполнен promise . Если он выполнение будет прервано, то удаление выполнено не будет.

## Отображение ошибок списка

Если список содержит ошибки, то будет отображена форма с указанием ошибки:

* при загрузке списка с ошибкой в первый раз будет отображен шаблон `error.hbs`
* в контроллере есть список ранее загруженных объектов - ошибка будет показана через компонент.

Изменить это на прикладном уровне можно с помощью метода `onModelLoadingRejected` в роуте.

## Другие возможности

* [Изменение размера столбцов во Flexberry Objectlistview](ef_olv-resize.html)

