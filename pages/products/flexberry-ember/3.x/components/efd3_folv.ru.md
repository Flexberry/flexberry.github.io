---
title: Списковый компонент
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, FOLV, FlexberryObjectListView
toc: true
permalink: ru/efd3_folv.html
lang: ru
summary: Списковый компонент FlexberryObjectListView, назначение и основные возможности.
---

## Назначение компонента

Списковый компонент __FlexberryObjectListView__ предназначен для отображения списка доступных объектов с возможностью их последующего просмотра и редактирования.

Обычно списковый компонент располагается на [списковой форме](ef3_listform.html). После [генерации](efd3_generated-app-structure.html) компонент на списковой форме имеет следующий [шаблон](ef2_template.html):

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

Внешний вид и основная функциональность компонента доступны на [тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-objectlistview/settings-example).

## Обзор основных возможностей и API компонента

### Основные свойства

Свойство |Описание
:-----------------------|:------------------------------  
`componentName`| Имя компонента. Должно быть уникальное в пределах одной страницы.  
`modelController`| Текущий [контроллер]() страницы.  
`modelName`| Имя текущей модели (список каких объектов отображается).  
`modelProjection`| Текущее используемое представление (определяет, какие свойства отображаются на списке).  
`content`| Отображаемые в компоненте данные.  
`content`| Отображаемые в компоненте данные.  
`rowClickable`| Флаг, определяющий, следует ли обрабатывать нажатие на строчку.  
`action`| Действие, выполняемое при нажатии на строчку.
`editFormRoute`| Задаёт имя роута формы редактирования, в котором будет открываться модель.  
`singleColumnHeaderTitle`| Заголовок для мобильного представления компонента, вместо названий колонок. Если не задан или равен "", то шапка таблицы в компоненте скрывается.  
`readonly`| Флаг, определяющий, доступно ли редактирование данных посредством компонента.  
`placeholder`| Отображаемый в теле списка текст, если содержимое не определено или пусто.
`buttonClass`| CSS-классы для отображения кнопок на компоненте.  
`tableStriped`| Флаг, определяющий, должны ли строчки в компоненте быть раскрашены попеременно.  
`customTableClass`| Произвольные css-классы для таблицы.  
`useSidePageMode`| Флаг, определяющий, что открываемые со списка модальные окна будут отображаться справа во всю высоту страницы ([на мобильных устройствах]() распахиваться на весь экран).

### Настройка панели управления, добавление пользовательских кнопок

Свойство |Описание
:-----------------------|:------------------------------  
`createNewButton`| Флаг, определяющий, отображать ли кнопку создания на панели управления.  
`deleteButton`| Флаг, определяющий, отображать ли кнопку удаления выделенных записей на панели управления (для выделения записей должен быть включён флаг `showCheckBoxInRow`).   
`refreshButton`| Флаг, определяющий, отображать ли кнопку обновления на панели управления.  
`defaultSortingButton`| Флаг, определяющий, отображать ли кнопку установки сортировки по умолчанию.  
`colsConfigButton`| Флаг, определяющий, отображать ли кнопку управления пользовательскими наcтройками.  
`advLimitButton`| Флаг, определяющий, отображать ли кнопку задания ограничений на панели управления.  
`exportExcelButton`| Флаг, определяющий, отображать ли кнопку экспорта в эксель на панели управления.  
`enableFilters`| Флаг, определяющий, включён ли режим фильтрации на списке (появляется дополнительная кнопка на панели управления и специальные элементы для каждого столбца, если не задан флаг отображения настроек фильтров на отдельном модальном окне `showFiltersInModal`).  
`filterButton`| Флаг, определяющий, отображать ли кнопку поиска на панели управления.  
`buttonClass`| CSS-классы для отображения кнопок на компоненте.   
`customButtons`| Настройки пользовательских кнопок на панели управления.

{% include note.html content="Также на панели управления списка могут отображаться кнопки включения/отключения режима иерархии (`disableHierarchicalMode`), разворачивания/сворачивания списка (`availableCollExpandMode`), для чего требуются также дополнительные настройки" %}

Для добавления пользовательской кнопки на панель управления списка в контроллере формы нужно определить ряд свойств:
* `buttonName` - отображаемое имя кнопки.
* `buttonAction` - действие, вызываемое контроллером при нажатии этой кнопки (должно быть передано в шаблоне).
* `buttonClasses` - css-класс кнопки.
* `buttonTitle` - текст всплывающей подсказки, которая появляется при наведении курсора на кнопку.
* `iconClasses` - css-классы для иконки кнопки (если не указаны, то кнопка отображается без иконки).

```json
{
    buttonName: 'Моя кнопка',
    buttonAction: 'MyButtonAction',
    buttonClasses: 'red',
    buttonTitle: 'Это пользовательская кнопка',
    iconClasses: 'green tree icon'
}
```

Если необходимо добавить несколько кнопок, то их свойства задаются в массиве:

```json
[{ buttonName: ..., buttonAction: ..., buttonClasses: ... }, {...}, ...]
```

В контроллере нужно определить метод, возвращающий настройки для пользовательских кнопок на панели управления. Например (в примере реализован вариант, поддерживающий [локализацию]()):

```js
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

Далее, в контроллере, нужно определить обработчик, указанный в `buttonAction`:

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

Определенные таким образом методы и свойства должны быть переданы в шаблоне списка:

```hbs
{% raw %}{{flexberry-objectlistview
    ...
    customButtons=customButtonsMethod
    userButtonActionTest='userButtonActionTest'
}}{% endraw %}
```

### Настройка отображения столбцов

Свойство |Описание
:-----------------------|:------------------------------  
`columnsWidthAutoresize`| Флаг, определяющий, следует ли столбцам автоматически изменять размер, чтобы растягиваться по всей ширине страницы.  
`columnsResizeMode`| Определение способа изменения размера столбцов (доступные значения: `fit`, `flex`, `overflow`; по умолчанию `overflow`).  
`minAutoColumnWidth`| Минимальная ширина столбца (если ширина не задана в [настройках пользователя]()).  
`allowColumnResize`| Флаг, определяющий, досупно ли изменение ширины столбцов.  

### Настройка отображения строчек, добавление пользовательских кнопок

Для включения дополнительной функциональности строчек доступны основные флаги как для первого столбца, так и контекстного меню, которое расположено в последнем столбце каждой строчки (данная возможность особо актуальна для [мобильной версии]()).

Свойство |Описание
:-----------------------|:------------------------------  
`showCheckBoxInRow`| Флаг, определяющий, отображать ли чекбоксы для выделения записей в каждой строчке в первом столбце (при активации данного флага также в левой верхней ячейке появляются кнопки “Отметить все на текущей странице”, “Отметить все на всех страницах” и “Установить сортировку по умолчанию”).  
`showDeleteButtonInRow`| Флаг, определяющий, отображать ли кнопку удаления строки в каждой строчке в первом столбце.
`showEditButtonInRow`| Флаг, определяющий, отображать ли кнопку редактирования в каждой строчке в первом столбце.  
`showPrototypeButtonInRow`| Флаг, определяющий, отображать ли кнопку прототипирования в каждой строчке в первом столбце.  
`customButtonsInRow`| Настройки пользовательских кнопок в каждой строчке в первом столбце.  
`showValidationMessagesInRow`| Флаг, определяющий, следует ли отображать сообщения валидации в каждой строчке.  
`showAsteriskInRow`| Флаг, определяющий, отображать ли "звёздочку" у изменённых записей в первом столбце.  
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного меню "Удалить запись".  
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного меню "Редактировать запись".  
`showPrototypeMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного меню "Создать запись на основе" (для реализации прототипирования).  
`menuInRowAdditionalItems`| Дополнительные пункты в контекстном меню.  

Задание пользовательских кнопок в каждой строчке в первом столбце полностью идентично заданию пользовательских кнопок на панели управления списком, за исключением того, что нельзя указать `buttonName` (используется только иконка).
```hbs
{% raw %}{{flexberry-objectlistview
    ...
    customButtonsInRow=customButtonsMethod
    userButtonActionTest='userButtonActionTest'
}}{% endraw %}
```

Для того, чтобы задать дополнительные пункты в контекстном меню, в контроллере следует определить настройки пунктов меню, а также соответствующие обработчики:
```js
...
  menuItems: [{
    icon: 'spy icon',
    title: 'Шпионить',
    actionName: 'makeItASpy',
  }],
  ...
  actions: {
    ...
    makeItASpy(record) {
      record.set('isSpy', true);
    },
    ...
  }
```

После чего передать соответствующую информацию в компонент (при этом в компонент требуется передавать обработчик в виде `actionName="actionName"`).

```hbs {% raw %}
{{flexberry-objectlistview
    ...
    menuInRowAdditionalItems=menuItems
    makeItASpy="makeItASpy"
    ...
}}{% endraw %}
```

{% include important.html content="Для создания новой записи на основе другой (прототипирования) необходимо указать представление, по которому будет создаваться копия. Можно указать представление для конкретной формы: в роуте соответствующей [формы создания](efd3_editform.html) задать свойство `prototypeProjection` (имя представления строкой); или указать представление для копирования по умолчанию для всей модели: в нужной модели задать свойство `prototypeProjection` (имя представления строкой)." %}

### Инструменты работы с объектами на списках

Кнопки “Отметить все на текущей странице”, “Отметить все на всех страницах” и “Установить сортировку по умолчанию” расположены в левой верхней ячейке списка и появляются, если указан флаг `showCheckBoxInRow`.

* “Отметить все на текущей странице” - отмечает все объекты на странице, добавляет отмеченые объекты в `selectedRecords` (обрабатывать изменение массива выделенных объектов можно посредством указания обработчика в `configurateSelectedRows`).
* “Отметить все на всех страницах” - активирует параметр `allSelect` (обработка удаления при активации этого параметра реализуется в соответствии с потребностями конкретного приложения в action `delete` компонента).
* “Установить сортировку по умолчанию” - устанавливает сортировку и количество отображаемых страниц по умолчанию.

### Пейджинг спискового компонента
При просмотре удобно видеть лишь некоторый текущий фрагмент списка. Весь список делится на "страницы" и для просмотра этих "страниц" реализуется механизм пейджинга для спискового контрола.

Основные настройки пейджинга представлены ниже:

Свойство |Описание
:-----------------------|:------------------------------  
`pages`| Метод определения доступных страниц для отображения в пейджинге (например, из списка в 16 записей, если отображается по 5 записей на странице, получается 4 страницы).  
`perPageValue`| Метод определения текущей настройки, где указано, по сколько записей отображается на странице (например, "5").  
`perPageValues`| Массив, определяющий, по сколько записей на странице можно отображать (например, "1, 5, 10").  
`hasPreviousPage`| Флаг, определяющий, есть ли у текущей страницы предшествующая.  
`hasNextPage`| Флаг, определяющий, есть ли у текущей страницы последующая.  
`previousPage`| Метод для перехода на предыдущую страницу.  
`gotoPage`| Метод для перехода на заданную страницу.  
`nextPage`| Метод для перехода на следующую страницу.  

### Настройка сортировки

Свойство |Описание
:-----------------------|:------------------------------  
`orderable`| Флаг, определяющий, возможно ли производить сортировку по столбцам в компоненте.  
`sorting`| Метод определения текущей сортировки.  
`sortByColumn`| Действие (`action`) контроллера, которое должно быть выполнено для сортировки по столбцу.  
`addColumnToSorting`| Действие (`action`) контроллера, которое должно быть выполнено для добавления сортировки по столбцу.  

## Инструменты ограничения списка
Для того чтобы настроить ограничения, списковый компонент __FlexberryObjectListView__ включает в себя следующие инструменты:

* фильтрация списков
* наложение ограничения (метод `objectListViewLimitPredicate`)
* поиск по атрибутам

## Настройка фильтрации на списке

Свойство |Описание
:-----------------------|:------------------------------  
`enableFilters`| Флаг, определяющий, включён ли режим фильтрации на списке (при этом на панели управления появляется соответствующая кнопка).  
`filters`| Текущие ограничения.  
`applyFilters`| Действие (`action`) контроллера, которое должно быть выполнено для применения ограничений. 
`resetFilters`| Действие (`action`) контроллера, которое должно быть выполнено для сброса ограничений. 
`showFiltersInModal`| Флаг, определяющий, каким образом осуществлять настройку фильтров: в модальном окне (актуально для [мобильной версии]()) или в элементах, отображаемых в каждом столбце. 
`conditionsByType`| Действие (`action`) контроллера для определения, какие операции сравнения доступны для фильтрации для конкретного типа или атрибута.  
`componentForFilter`| Действие (`action`) контроллера для определения, какой компонент использовать для задания значения фильтрации для конкретного типа, отношения, атрибута (вызывается при инициализации компонента).  
`componentForFilterByCondition`| Действие (`action`) контроллера для определения, какой компонент использовать для задания значения фильтрации для конкретного типа, отношения, атрибута (вызывается при инициализации компонента и при каждом изменении условия).  

Для того чтобы на списковом компоненте реализовать возможность фильтрации, необходимо:

1 Передать необходимые свойства в шаблоне для функционирования фильтрации:

```hbs
{% raw %}
{{flexberry-objectlistview
  enableFilters=true
  filters=filters
  applyFilters=(action "applyFilters")
  resetFilters=(action "resetFilters")
  ...
}}
{% endraw %}
```

{% include important.html content="Регистрозависимость/ регистронезависимость при фильтрации зависит от [настроек БД](fo_insensitivity-register-ds.html)." %}

{% include note.html content="По умолчанию отфильтровать можно не только по кнопке обновления списка, но и по клавише `Enter`. Также по умолчанию доступна фильтрация по подстроке при незаданной операции сравнения." %}

2 При необходимости переопределить для некоторых столбцов доступные операции сравнения и компонент введения значения фильтрации

```js
export default ListFormController.extend({
  actions: {
    /**
      @method actions.conditionsByType
      @param {String} type The type name.
      @param {Object} attribute An object with an attribute description.
      @return {Array} An array with items for `flexberry-dropdown` component.
    */
    conditionsByType(type, attribute) {
      return ['eq', 'neq'];
    },

    /**
      @method actions.componentForFilter
      @param {String} type The type name.
      @param {Boolean} relation If this is a relation, then `true`.
      @param {Object} attribute An object with an attribute description.
      @return {Object} An object with component description.
    */
    componentForFilter(type, relation, attribute) {
      switch (type) {
        case 'decimal':
          return { name: 'flexberry-textbox', properties: { class: 'compact fluid' } };

        default:
          return {};
      }
    },
  },
});
```

```hbs
{% raw %}
{{flexberry-objectlistview
  componentForFilter=(action "componentForFilter")
  conditionsByType=(action "conditionsByType")
  // ...
}}
{% endraw %}
```

{% include note.html content="Для более тонкой настройки можно использовать `componentForFilterByCondition`." %}

3 При необходимости изменить логику создания [предиката]() для фильрации, для чего переопределить метод `predicateForFilter` роута формы.

```javascript
import ListFormRoute from 'ember-flexberry/routes/list-form';

export default ListFormRoute.extend({
  /**
    @method predicateForFilter
    @param {Object} filter An object with filter description.
    @return {BasePredicate} The predicate or null.
  */
  predicateForFilter(filter) {
    if (filter.type === 'string' && filter.condition === 'like') {
      return new StringPredicate(filter.name).contains(filter.pattern);
    }

    if (filter.type === 'decimal') {
      return new SimplePredicate(filter.name, filter.condition, filter.pattern ? Number(filter.pattern) : filter.pattern);
    }

    return this._super(...arguments);
  },
});
```

Если нужно фильтровать поля с датами, не учитывая время, то нужно в роуте в методе `predicateForFilter` добавить условие:

```javascript
predicateForFilter(filter) {
  if (filter.type === 'date') {
    return new DatePredicate(filter.name, filter.condition, filter.pattern, true);
  }

  return this._super(...arguments);
},
```

Если существует необходимость использовать операции, не требующие заполнения значения (операции "пусто" и "не пусто"), то можно настроить соответствующую операцию для предиката в контроллере формы (`conditionsByType`). Например:

```javascript
// ...
case 'string':
return ['eq', 'neq', 'like'];
return ['eq', 'neq', 'like', 'empty'];
// ...
```

Далее в роуте задать соответствующее условие. Например:

```javascript
// ...
if (filter.type === 'string' && filter.condition === 'empty') {
  return new SimplePredicate(filter.name, 'eq', null);
}
// ...
```

{% include note.html content="Если на прикладном уровне нужны специфические фильтры, то можно использовать функцию `predicateForAttribute`. Данная функция получает на вход атрибут, по которому происходит фильтрация, значение, по которому фильтровать, условие фильтра и возвращает предикат, по которому затем формируется параметр `$filter` в [OData-запросе](fo_orm-odata-service.html)." %}

## Наложение ограничений

Особенности наложения ограничений на FlexberryObjectlistview связаны с тем, что данные для компоненты вычитываются в роуте. Соответственно, чтобы не допустить вычитывания данных без наложенного ограничения, ограничение должно быть определено, когда выполняется хук `model` в роуте формы. Таким образом, чтобы наложить ограничение, необходимо переопределить метод `objectListViewLimitPredicate` в роуте прикладной списковой формы, чтобы он возвращал предикат для ограничения.

Например, если на странице отображается чётное количество записей, необходимо вывести записи, у которых в поле "address" есть буква "S", а при нечетном количестве - имеющие в поле "address" букву "п".
Переопределение метода `objectListViewLimitPredicate` в роуте соответствующей прикладной списковой формы следующее:

```javascript
export default ListFormRoute.extend({
  objectListViewLimitPredicate(options) {
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

## Настройка поиска по всем атрибутам для стандартного списка

Свойство |Описание
:-----------------------|:------------------------------  
`filterButton`| Флаг, определяющий, включён ли режим поиска на списке (при этом на панели управления появляется соответствующая кнопка).  
`filters`| Текущие ограничения.  
`applyFilters`| Действие (`action`) контроллера, которое должно быть выполнено для применения ограничений. 
`resetFilters`| Действие (`action`) контроллера, которое должно быть выполнено для сброса ограничений. 
`filterText`| Текст для поиска, который будет также отображен в соответствующем компоненте поиска.  
`filterByAnyWord`| Флаг, определяющий, что осуществляется по заданному слову/нескольким словам. 
`filterByAllWords`| Флаг, определяющий, что осуществляется по заданному слову/словосочетанию. 
`filterByAnyMatch`| Действие (`action`) контроллера, которое должно быть выполнено для осуществления поиска.
`filterProjectionName`| Имя проекции, по свойствам которой будет осуществляться поиск (если не указано, то используется `modelProjection`). 

Для того чтобы на [списковой форме](ef3_listform.html) реализовать возможность поиска по всем атрибутам, необходимо:

1 Указать свойства поиска в [шаблоне списковой формы](ef2_listform.html).

```hbs
{% raw %}
{{flexberry-objectlistview
  filters=filters
  applyFilters=(action "applyFilters")
  resetFilters=(action "resetFilters")
  filterButton=true
  filterText=filter
  filterByAnyWord=filterByAnyWord
  filterByAllWords=filterByAllWords
  filterByAnyMatch=(action 'filterByAnyMatch')
}}
{% endraw %}
```

`filterByAnyWord` - в результате будут выданы все строки, в которых указано заданное в поиске слово/несколько слов.

![Поиск по некоторым атрибутам](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-any-word.png)

`filterByAllWords` - в результате будут выданы только те строки, в которых указано заданное слово/словосочетание.

![Поиск по всем атрибутам](/images/pages/products/flexberry-ember/ember-flexberry/controls/filter-by-all-words.png)

По умолчанию поиск осуществляется по подстроке.

Реализация представлена на [тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-objectlistview/custom-filter).

{% include note.html content="`filterByAnyWord` и `filterByAllWords` нельзя использовать вместе, если не реализованы дополнительные элементы панели управления (кнопки) для включения/отключения типа поиска." %}


2 При необходимости переопределить логику в [роуте списковой формы](ef2_listform.html), чтобы изменить способ построения предикатов.

Например, настройка предиката необходима для корректного поиска значений, имеющих два признака истина/ложь:

```javascript
predicateForAttribute(attribute, filter) {
  switch (attribute.type) {
    case 'boolean':
      let yes = ['TRUE', 'True', 'true', 'YES', 'Yes', 'yes', 'ДА', 'Да', 'да', '1', '+'];
      let no = ['FALSE', 'False', 'false', 'NO', 'No', 'no', 'НЕТ', 'Нет', 'нет', '0', '-'];

      if (yes.indexOf(filter) > 0) {
        return new SimplePredicate(attribute.name, 'eq', 'true');
      }

      if (no.indexOf(filter) > 0) {
        return new SimplePredicate(attribute.name, 'eq', 'false');
      }

      return null;

    default:
      return this._super(...arguments);
  }
},
```

## Ограничение длины текста в ячейках. Перенос в строках. Обрезка текста в строках
Доступна возможность обрезать текст в ячейках по заданной длине. При наведении на такую ячейку во всплывающей подсказке указано полное значение текста ячейки. Это делается через настройку стандартного компонента ячейки в `getCellComponent`:

```javascript
  getCellComponent: function(attr, bindingPath, model) {
    let cellComponent = {
      componentName: 'object-list-view-cell',
      componentProperties: {
        maxTextLength: 10,
        cutBySpaces: false,
        displayMemberPath: Ember.get(attr, 'options.displayMemberPath')
      }
    };

    return cellComponent;
  }
```

* `maxTextLength` определяет максимальное количество символов в ячейке.
* `cutBySpaces` определяет способ обрезания текста (false - ровно по заданной длине, true - по последнему пробелу).
* `displayMemberPath` необходимо, если значением ячейки является объект. Задает путь до отображаемого в ячейке свойства.

## Раскраска строк
Стандартным вариантом раскраски строк в компоненте является "полосатый" вариант. Чтобы его использовать, требуется выставить флаг `tableStriped`. Однако если требуется настройть раскраску, завязанную на сложное составное условие, требуются дополнительные механизмы.

Например, требуется раскрасить строки объектов, у которых адрес равен заданному. Для этого в контроллере требуется определить действие ("action"), который будет передан в шаблон компонента в параметре `configurateRow`. В данном действии строкам, удовлетворяющим условию, выставляется класс `positive`, остальным `negative`, после чего посредством CSS выставляются настройки внешнего вида строк. 

```js
actions: {
    /**
      Configurate rows on the condition.
    */
    configurateRow(rowConfig, record) {
      if (record.get('address') === this.get('configurateRowByAddress')) {
        set(rowConfig, 'customClass', 'positive ');
      } else {
        set(rowConfig, 'customClass', 'negative ');
      }
    }
  }
```

```hbs
{% raw %}
{{flexberry-objectlistview
    ...
    configurateRow=(action "configurateRow")
  }}
{% endraw %}
```

В случае, если адрес, на основании которого производится раскраска, может меняться, то требуется дополнительная логика. Полный пример представлен на [тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-objectlistview/configurate-rows).

## Иерархический список
Списковый компонент FlexberryObjectListView имеет возможность отображения списка объектов в иерархическом режиме.

Свойство |Описание
:-----------------------|:------------------------------  
`hierarchyByAttribute`| Имя атрибута, по которому будет строиться иерархия (требуется указать, если объект имеет на себя более одной ссылки).  
`disableHierarchicalMode`| Флаг, определяющий, что необходимо отключить возможность отображения списка в иерархическом режиме.  
`isParentRecordPropertyName`| Имя свойства в модели, в котором определяется, что у записи есть дочерние записи по иерархии (если не указано, используется свойство `IsParentRecord`). Если у соответствующей записи значение определено (то есть не `undefined`), то отображается кнопка для просмотра дочерних записей (дочерние записи будут подгружены после нажатия на эту кнопку).
`hierarchicalIndent`| Отступ в пикселях для обозначения иерархии.  
`currentController.hierarchyPaging`| Свойство переданного контроллера, определяющее, следует ли компоненту в режиме иерархии отображаться с пейджингом.  

Если у объекта есть ссылка на самого себя, список таких объектов считается иерархическим. Если объект имеет только одну такую ссылку, над списком объектов будет отображена кнопка для переключения списка в иерархический режим. Если объект имеет больше одной ссылки на самого себя, для возможности отображения списка в иерархическом режиме необходимо указать в свойстве `hierarchyByAttribute` имя атрибута, по которому будет строиться иерархия.
Если необходимо отключить возможность отображения списка в иерархическом режиме, необходимо свойство `disableHierarchicalMode` установить в значение `true`.

При отображении списка объектов в иерархическом режиме для каждого объекта выполняется отдельный запрос для проверки наличия у него дочерних объектов.
Чтобы избавиться от лишних запросов, можно реализовать в объекте атрибут, позволяющий определить, есть ли у объекта дочерние объекты, что позволит сразу отобразить кнопку для просмотра дочерних объектов и загрузить их только при нажатии кнопки пользователем. Этот атрибут может быть [нехранимым](fo_not-stored-attributes.html) и определять наличие дочерних объектов с помощью запроса в выражении для [сервиса данных](fo_data-service.html).

В модели для клиентского приложения это может быть простой логический атрибут.

```csharp
[NotStored]
[DataServiceExpression(typeof(SQLDataService), "SELECT COUNT(*) > 0 FROM MyObject WHERE MyObject.Parent = StormMainObjectKey")]
public bool IsParentRecord
{
    get => fIsParentRecord;
    set => fIsParentRecord = value;
}
```

```javascript
isParentRecord: DS.attr('boolean'),
```

Если атрибут называется `IsParentRecord`, он по умолчанию будет использован в компоненте, иначе нужно в свойстве `isParentRecordPropertyName` указать имя этого атрибута.

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  isParentRecordPropertyName="hasChildren"
  ...
}}{% endraw %}
```

{% include important.html content="Атрибут `IsParentRecord` или указанный в `isParentRecordPropertyName` нужно добавить в представление, которое используется для загрузки списка объектов, он может быть скрытым." %}

На [тестовом стенде реализован пример, где списковый компонент находится в режиме иерархии](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-objectlistview/hierarchy-example).

## Возможности сервиса objectlistview-events
Для расширения возможностей спискового компонента FlexberryObjectListView [технология Flexberry Ember предоставляет сервис](efd3_service.html) [`objectlistview-events`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ObjectlistviewEvents.html), содержащий [миксин `Evented`](https://api.emberjs.com/ember/3.1/classes/Evented), благодаря чему данный сервис позволяет подписываться на [события спискового компонента FlexberryObjectListView](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/addon/services/objectlistview-events.js).

Основные доступные события (параметры событий указаны в [автодокументации](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/ObjectlistviewEvents.html)):

Событие |Описание  
:-----------------------|:------------------------------  
`olvRowSelected`| Событие взводится, когда строка списка была выделена.  
`updateSelectAll`| Событие взводится, когда производится отметка всех записей на всех страницах.
`setSorting`| Событие взводится, когда изменяется сортировка на компоненте.  
`updateWidth`| Событие взводится, когда изменяется ширина столбцов списка.
`olvAddRow`| Событие взводится, когда в список добавлена новая строчка.
`olvRowDeleted`| Событие взводится, когда запись была удалена.
`olvDeleteRows`| Событие взводится, когда удаляются выделенные записи.
`olvDeleteAllRows`| Событие взводится, когда удаляются все записи на всех страницах.
`filterByAnyMatch`| Событие взводится, когда производится поиск на списке.
`refreshList`| Событие взводится, когда список обновляется.
`geSortApply`| Событие взводится, когда применяется сортировка.
`moveRow`| Событие взводится, когда требуется "подвинуть" строки (например, в результате удаления некоторых).
`filterConditionChanged`| Событие взводится, когда условие фильтрации в любом столбце было изменено.
`refreshListOnly`| Событие взводится, когда происходит обновление только самого списка.
`resetFilters`| Событие взводится, когда происходит очистка фильтров.



## Установка ширины колонок и пр. по умолчанию
## Механизм сохранения пользовательских настроек. Сервис user-settings
## Размещение компонента списка на форме редактирования
## Пользовательские ограничения. Возможности сервиса adv-limit. Настройки для пользовательских ограничений в конфигурационном файле
