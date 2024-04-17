---
title: Flexberry Groupedit
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, groupedit
toc: true
permalink: ru/ef3_groupedit.html
lang: ru
summary: Свойства, особенности реализации, настройка сортировки и встраивание прикладных компонентов, реализация в отдельном роуте
---

## Назначение компонента

`flexberry-groupedit` – экомпонент на базе технологии Ember.js для работы с [детейлами](fo_detail-associations-properties.html) на форме редактирования. Этот компонент предоставляет возможность создания, редактирования, удаления записей. Кроме того, он поддерживает некоторые дополнительные возможности: настройка внешнего вида (например, изменение размера столбцов, пользовательские кнопки), обработка действий над строками и многое другое.

Пример использования `flexberry-groupedit` в шаблоне:

 ```handlebars
{{flexberry-groupedit
  componentName="userVotesGroupEdit"
  modelProjection=modelProjection.attributes.userVotes
  content=model.userVotes
  readonly=readonly
  orderable=false
}}
```

## Обзор возможностей и API компонента

### Свойства Flexberry Groupedit

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly`| Свойства из [базового контрола](efd3_editform.html).
`content`| Определяет записи, редактируемые в контроле.
`modelProjection`| Определяет представление, которое будет отображаться.
`placeholder`| Текст, отображаемый, когда содержимое не определено или пусто. Значение по умолчанию: t('components.flexberry-groupedit.placeholder')
`tableStriped`| Флаг, определяющий, будут ли строки таблицы раскрашены через одну ("зебра"). Значение по умолчанию: false.
`createNewButton`| Флаг, определяющий, будет ли показана кнопка создания на панели инструментов. Значение по умолчанию: true.
`deleteButton`| Флаг, определяющий будет ли показана кнопка удаления на панели инструментов. Значение по умолчанию: true.
`defaultSortingButton`| Флаг, определяющий, отображать ли кнопку установки сортировки по умолчанию. Значение по умолчанию: true.
`allowColumnResize`| Флаг, определяющий, может можно ли изменять размер столбцов. Значение по умолчанию: true.
`showAsteriskInRow`| Флаг, определяющий, показывать ли звездочку в первой колонке измененных строк. Значение по умолчанию: true.
`showCheckBoxInRow`| Флаг, определяющий, показывать ли чекбокс в первой колонке каждой строки. Он позволяет выделять отдельные строки и работать со всеми выделенными объектами (например, удалять). Значение по умолчанию: true.
`showDeleteButtonInRow`| Флаг, определяющий, отображать ли кнопку `-` для удаления записи в браузерном и мобильном шаблоне. Значение по умолчанию: false.
`showEditMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного меню `Редактировать запись`. Данная настройка используется в режиме работы для мобильного устройства. Значение по умолчанию: false.
`showDeleteMenuItemInRow`| Флаг, определяющий, отображать ли пункт контекстного, меню мобильного шаблона `flexberry-groupedit`, `Удалить запись`. Значение по умолчанию: false.
`singleColumnHeaderTitle`| Заголовок для мобильного представления `flexberry-groupedit`, вместо названий колонок.
`rowClickable`| Флаг, определяющий, являются ли строки таблицы кликабельными. Значение по умолчанию: false.
`immediateDelete`| Флаг, определяющий, следует ли немедленно отправлять DELETE-запрос на сервер (для каждой удаленной записи) или нет.
`orderable`| Флаг, определяющий, возможно ли производить сортировку по столбцам в компоненте (если значение флага будет изменено на `true`, потребуются дополнительные настройки, о чём ниже).
`defaultSortingButton`| Флаг, определяющий, отображать ли кнопку установки сортировки по умолчанию. Значение по умолчанию: true.
`fixedHeader`| Флаг, будет ли заголовок таблицы фиксированным. Значение по умолчанию: true.
`store` | Экземпляр хранилища данных Ember Data, используемый для операций с данными.
`_groupEditEventsService` | Сервис, который инициирует события, связанные с компонентом.
`action`| Имя действия для обработки событий клика по строкам. Значение по умолчанию: 'groupEditRowClick'.
`buttonClass`| CSS-классы, применяемые к кнопкам панели инструментов и кнопкам строк.
`cellComponent`| Метод, определяющий, каким контролом редактируется компонент.
`customButtons`| Массив конфигураций пользовательских кнопок.
`customTableClass`| Пользовательские CSS-классы для таблицы. Значение по умолчанию: ''.
`defaultSettingsButton`| Флаг, определяющий, будет ли показана кнопка настроек по умолчанию на панели инструментов. Значение по умолчанию: true.
`defaultSortingButton`| Флаг, определяющий, будет ли показана кнопка сортировки по умолчанию. Значение по умолчанию: true.
`editFormRoute`| Роут формы редактирования, который будет открыт после клика по соответствующей строке (именно тот, по которому был клик).

**Дополнительные свойства:**

Свойство | Краткое описание
:--------|:----------------
`editOnSeparateRoute` | Флаг, определяющий, следует ли редактировать записи на отдельном роуте. Значение по умолчанию: false.
`menuInRowAdditionalItems` | Дополнительные пункты для выпадающего меню в последней колонке каждой строки. Значение по умолчанию: null.
`mainModelProjection` | Основная проекция модели, используемая для поддержки локализации подписей.
`saveBeforeRouteLeave` | Необходимо ли сохранять текущую модель перед переходом на роут детейла. Значение по умолчанию: false.
`searchForContentChange` | Отслеживание изменений в модели для соответствующего обновления компонента. Значение по умолчанию: true.
`showValidationMessagesInRow` | Показывать ли сообщения валидации в каждой строке. Значение по умолчанию: false.
`useSidePageMode` | Флаг, определяющий режим для модальных окон, используемых компонентом. Значение по умолчанию: false.
`appConfigSettingsPath` | Путь к настройкам компонента в конфигурации приложения. Значение по умолчанию: 'APP.components.flexberryGroupedit'.
`sorting` | Массив с данными сортировки. Значение по умолчанию: null.
`minAutoColumnWidth` | Минимальная ширина столбцов, если она не задана в пользовательских настройках. Значение по умолчанию: 100.
`columnsWidthAutoresize` | Флаг, определяющий, будут ли столбцы автоматически изменять свой размер, чтобы соответствовать ширине страницы. Значение по умолчанию: true.
`overflowedComponents` | Названия компонентов, которые могут выходить за пределы ячейки таблицы. Значение по умолчанию: ['flexberry-dropdown', 'flexberry-lookup'].
`componentProperties`| Компонент ячейки по умолчанию для отображения значений ячеек столбцов.

**Свойства, используемые для настройки редактирования в отдельном роуте:**

* `rowClickable`
* `rowClick`
* `editOnSeparateRoute`

## Особенности реализации

* Flexberry Groupedit состоит из двух компонентов: `GroupeditToolbar` и `ObjectListView`. Также на базе `ObjectListView` есть ещё компонент [flexberry-ojectlistview](https://github.com/Flexberry/flexberry.github.io/blob/master/pages/products/flexberry-ember/3.x/components/efd3_object-list-view.ru.md).
* Свойство `class` применяется к `ObjectListView`.

## Настройка строк

### Настройка шаблона агрегатора и роутера

В [шаблоне](efd3_template.html) формы редактирования агрегатора у контрола `flexberry-groupedit` определить следующие свойства:

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
* `rowClick` - действие, выполняемое при нажатии на строчку. По умолчанию имеет значение "rowClick", для редактирования детейла в отдельном роуте нужно, чтобы действие (action) с заданным в свойстве именем было определено в контроллере или [роуте](efd3_router.html) (`rowClick` определено в базовом роуте формы редактирования).

{% include note.html content="Текущий обработчик метода `rowClick` реализован таким образом, что в случае, если детейл сохранён, то будет произведена переадресация на роут как `modelName/:id`, а если не сохранён, то `modelName.new` (соответственно, [роутер](efd3_router.html) должен быть настроен, чтобы переадресация могла пройти корректно)." %}

* `editOnSeparateRoute` - флаг, определяющий, следует ли редактировать детейл в отдельном роуте. По умолчанию имеет значение `false`. При задании значения "true" изменяется внешний вид `flexberry-groupedit`: отключается возможность редактирования непосредственно в контроле, при нажатии на кнопку `Добавить` теперь создаётся новая запись и сразу происходит переадресация на роут редактирования.

### Настройка шаблона детейла

Для настройки редактирования детейла в отдельном роуте должна быть создана соответствующая форма.

Если предполагается, что форма редактирования детейла может быть использована не только с формы агрегатора, но и со списковой формы детейлов, то для настройки отображения кнопок `Сохранить`, `Удалить`, `Закрыть` можно использовать условия, представленные в статье [Формы редактирования и создания Flexberry Ember](efd3_editform.html).

### Настройка контроллера детейла

Контроллер формы редактирования детейла должен наследоваться от `detail-edit-form` вместо `edit-form`. В `detail-edit-form` присутствует дополнительная логика, организующая корректное взаимодействие между формами агрегатора и детейла. В случае, если определено, что пользователь попал на форму детейла не с формы агрегатора, то будет выполняться логика базового контроллера `edit-form`.

```javascript
import DetailEditFormController from 'ember-flexberry/controllers/detail-edit-form';

export default DetailEditFormController;
```

### Настройка моделей агрегатора и детейла

При описании [модели](efd3_model.html) _агрегатора_ важно проверить, что проставлена детейловая [inverse-связь](https://guides.emberjs.com/v3.1.0/models/relationships/#toc_reflexive-relations).

```javascript
var Model = BaseModel.extend({
  ...
  orders: DS.hasMany('order', { inverse: 'employee', async: false }),
});
```

При описании модели _детейла_ важно проверить, что проставлена [inverse-связь](https://guides.emberjs.com/v3.1.0/models/relationships/#toc_reflexive-relations) на агрегатора.

```javascript
var Model = BaseModel.extend({
  ...
  employee: DS.belongsTo('employee', { inverse: 'orders', async: false })
});
```

### Настройка сериализатора агрегатора

Важно, чтобы [сериализаторы](efd3_serializer.html) для агрегатора и детейла были настроены корректно.

### Встраивание компонентов в groupedit

Компонент `flexberry-groupedit` - это таблица, в ячейки которой можно встраивать любые компоненты, наследуемые от [flexberry-base-component](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/components/flexberry-base-component.js).

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

При этом важно, чтобы встраиваемый компонент наследовался от базового компонента (`ember-flexberry/components/flexberry-base-component.js`), и если в компоненте переопределен метод `init`, то в конце прикладной инициализации обязательно должен вызываться метод инициализации из базового класса, так как там определена логика, инициализирующая свойства компонента из объекта, переданного в возвращаемом значении метода `getCellComponent` по ключу `componentProperties`.

### Редактирование детейлов в отдельном роуте

Настройка шаблонов и моделей агрегатора и детейла, сериализатора агрегатора и контроллера детейла для реализации возможности редактирования в отдельном роуте является частным случаем использования компонента (например, при работе на мобильных устройствах).

При этом следует учитывать некоторые особенности применения:

* Изменения в агрегаторе и детейлах сохраняются в БД только при нажатии кнопки `Сохранить` на форме агрегатора.
* На странице детейла есть только две кнопки: `Сохранить` и `Закрыть` (на форме нет кнопки, чтобы откатить состояние детейла на момент захода на форму).
* При нажатии на кнопку `Сохранить` на странице детейла происходит возврат на страницу агрегатора без сохранения изменений в БД.

### Сортировка элементов

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

## Возможности сервиса detail-interaction

DetailInterationService - это сервис, расширяющий стандартный сервис фреймворка Ember и предоставляющий возможность управления переходами между формами агрегатора и детейла.

> Использование данного сервиса вне аддона [ember-flexberry](ef3_landing_page.html) не рекомендуется.

### Свойства сервиса

`modelSelectedDetail`| Объект, представляющий выбранный детейл. Инициализируется на форме агрегатора после клика по строке несохраненного детейла. Используется только на форме создания нового детейла для открытия уже созданного, но не сохраненного объекта.
`modelCurrentAgregators`| Стек текущих агрегаторов детейлов (для поддержки детейлов N-го уровня). Инициализируется на форме агрегатора после клика по строке детейла. Используется только на форме детейла для возврата к соответствующему агрегатору.
`modelCurrentAgregatorPathes` | Стек путей к агрегаторам детейлов (для поддержки детейлов N-го уровня). Инициализируется на форме агрегатора после клика по строке детейла. Используется только на форме детейла для получения URL возврата.
`modelCurrentNotSaved` | Текущая не сохраненная модель. Инициализируется на форме детейла после инициирования возврата на форму агрегатора. Используется только на форме агрегатора для получения уже созданного, но не сохраненного объекта агрегатора, исключая создание нового экземпляра.
`modelLastUpdatedDetail` | Последняя обновленный детейл. Инициализируется на форме детейла после инициирования возврата на форму агрегатора. Используется для анализа состояния обновления детейла.
`saveBeforeRouteLeave` | Флаг, указывающий на необходимость сохранения текущей модели перед переходом на роут агрегатора.

### Методы сервиса

Наименование | Параметры
:--------|:----------------
`hasValues(currentArray)` - проверяет, является ли переданное значение массивом и содержит ли оно элементы. | `currentArray` - значение для проверки; возвращает ответ, содержит ли массив элементы.
`pushValue(propertyName, currentArray, value)` - добавляет переданное значение в массив и сохраняет этот массив в указанном свойстве. | `propertyName` - наименование свойства, в котором будет сохранен результат, `currentArray` - массив, к которому добавляется значение (если массива нет, то создается новый), `value` - значение для добавления в массив.
`getLastValue(propertyName)` - возвращает последнее значение массива, хранящегося в указанном свойстве, или _undefined_, если массив не содержит элементов. | `propertyName` - наименование свойства, в котором хранится массив.

### Примеры использования

1.Пример использования метода hasValues

```javascript
let someArray = [1, 2, 3];
let isEmpty = detailInteractionService.hasValues(someArray);  // вернет true, так как массив не пуст
```

2.Пример использования метода pushValue

```javascript
let propertyName = 'modelCurrentAgregators';
let currentArray = detailInteractionService.get(propertyName); // представим, что currentArray = ['agregator1'];
detailInteractionService.pushValue(propertyName, currentArray, 'agregator2');
// свойство modelCurrentAgregators теперь будет содержать ['agregator1', 'agregator2']
```

3.Пример использования метода getLastValue

```javascript
let lastAgregator = detailInteractionService.getLastValue('modelCurrentAgregators');
// вернет 'agregator2', если modelCurrentAgregators = ['agregator1', 'agregator2']
```

## Пользовательские кнопки в тулбаре

EditFormController - расширяет стандартный контроллер формы редактирования и позволяет создавать пользовательские кнопки для тулбара групэдита.

Пример определения пользовательской кнопки в шаблоне:

<YourCustomComponent
  @clickCounter={{this.clickCounter}}
  @messageForUser={{this.messageForUser}}
  @hiButtonState={{this.hiButtonState}}
  @onCustomButtonClick={{this.actions.customButtonActionTest}}
  @onToggleHiButton={{this.actions.toggleHideCustomButton}}
/>

Где `YourCustomComponent` - компонент, который будет использовать эти данные и методы.

Свойства:

* `clickCounter` (по умолчанию 1 ) - счётчик нажатий на пользовательскую кнопку.
* `messageForUser` - сообщение для пользователя, которое отображается после нажатия на пользовательскую кнопку.
* `hiButtonState` (по умолчанию true) - состояние пользовательской кнопки; неактивна (true) или активна (false).
* `_records` - массив строк, доступен только для чтения.
* `customButtons` (вычесляемое свойство) - массив объектов, описывающих пользовательские кнопки. Включает в себя перевод текста кнопок и их состояния (активна/неактивна). Зависит от текущего языка интерфейса и состояния пользовательской кнопки.

События:

* `customButtonActionTest` - обработчик нажатия на пользовательскую кнопку. Увеличивает счётчик нажатий (clickCounter) и обновляет сообщение для пользователя
(messageForUser), добавляя в него количество нажатий.
* `toggleHideCustomButton` - переключает состояние пользовательской кнопки (hiButtonState) между активным и неактивным состоянием.

Для дополнения тулбара пользовательскими кнопками необходимо определить соответствующие события и свойства для созданной формы редактирования.

> Для корректного отображения текста кнопок и сообщений необходимо определить соответствующую локализацию.

## Настройка лукапов в групэдите

Настройка лукапов для групэдита является дополнительной возможностью работы с детейлами на основе [базового контроллера](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/controllers/edit-form.js) и [миксина для формы редактирования](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/mixins/edit-form-route-operations-indication.js), благодаря чему становится доступной настройка формы редактирования детейла для работы с компонентами [flexberry-lookup](ef3_flexberry-lookup.html) для выбора связанных данных по аналогии со стандартной формой редактирования, включая переходы между роутами.

Свойства:

* `parentRoute` - определяет имя роута для перехода после закрытия формы редактирования. Для него может быть установлено значение по умолчанию.
* `checkboxValue` - может быть использовано для управления режимом только для чтения компонента flexberry-lookup. Значение по умолчанию: false.
* `fieldvalue` - значение поля, применяемое для ограничения поиска в компоненте flexberry-lookup. Для него может быть установлено значение по умолчанию.
* `lookupDynamicProperties` (вычисляемое) - определяет динамические настройки для компонента flexberry-lookup внутри компонента flexberry-groupedit. Включает в себя функции выбора и удаления значения, атрибуты для отображения, связь с моделью, проекцию, автодополнение и функции ограничения поиска. Только для чтения.

Обзерверы:

`lookupReadonly` - наблюдает за изменением значения `checkboxValue` и устанавливает свойство `readonly` в динамических свойствах компонента flexberry-lookup в зависимости от его значения.
`lookupLimitFunction` - следит за изменением `fieldvalue` и задает ограничение поиска `lookupLimitPredicate` для flexberry-lookup , используя `StringPredicate`, который ищет вхождение `fieldvalue` в поле name.

`Метод getCellComponent(attr, bindingPath, model)` - определяет компонент и его свойства, которые будут встроены в ячейку object-list-view в зависимости от типа свойства и пути к модели. Возвращает: Object с именем и свойствами компонента для рендеринга ячейки таблицы.

Параметры:

* `attr`: атрибут проекции свойства, связанного с текущей ячейкой таблицы.
* `bindingPath`: путь к свойству модели, связанного с текущей ячейкой таблицы.
* `model`: класс модели записи данных, связанный с текущим рядом таблицы.

### Примеры использования

1.Пример использования lookupReadonly:

```javascript
// При изменении значения checkboxValue, например, на true
this.set('checkboxValue', true);

// Автоматически вызывается observer lookupReadonly и устанавливает
// свойство readonly в объекте lookupDynamicProperties в значение true
console.log(this.get('lookupDynamicProperties.readonly')); // Выведет true
```

2.Пример работы метода getCellComponent:

```javascript
// Например, необходимо вызвать getCellComponent для ячейки таблицы с author связью
let cellComponent = this.getCellComponent({
  kind: 'belongsTo',
  // другие настройки атрибута...
}, 'author', {
  modelName: 'ember-flexberry-dummy-vote'
});

console.log(cellComponent); // Выведет объект с настройками компонента для ячейки таблицы
```

Подробнее на [github.com](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/components-examples/flexberry-groupedit/ember-flexberry-dummy-suggestion-edit-groupedit-with-lookup-with-computed-atribute.js).

## Динамическое обновление списка объектов в групэдите

Доработка формы редактирования детейла для динамического обновления списка объектов осуществляется с помощью контроллера [EditFormController](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/EditFormController.html).

**Свойства:**

* `_itemsCounter` - счетчик созданных записей. Значение по умолчанию: 0.
* `searchForContentChange`  - флаг, указывающий, что компонент должен отслеживать изменения в модели и отображать их. Значение по умолчанию: true.

**События:**

Наименование | Методы
:--------|:----------------
`addDetail` | `actions.addDetail` - добавляет запись детейла в конец массива. Создает новую запись в хранилище с использованием счетчика _itemsCounter и добавляет ее в соответствуюий список.
`deleteDetail` | `actions.deleteDetail` - удаляет первый элемент массива списка детейлов.

`getCellComponent(attr, bindingPath)` - метод для получения типа и атрибутов компонента, который будет встроен в ячейку таблицы [object-list-view]. В случае, если атрибут связан с внешним ключом (belongsTo) и путь привязки равен master, компонент для ячейки будет настроен определенным образом.

* `attr` - атрибут проекции, связанный с текущей ячейкой таблицы,
* `bindingPath` - путь к свойству модели, связанному с текущей ячейкой таблицы,
* `modelClass` - класс модели записи данных, связанный с текущей строкой таблицы.

*Возвращаемое значение:* объект, содержащий имя и свойства компонента, который будет использоваться для отображения текущей ячейки таблицы, где

`componentName` - имя компонента,
`componentProperties` - свойства компонента.

Для использования контроллера необходимо его импортировать и применить в соответствующем месте приложения. События `addDetail` и `deleteDetail` можно вызвать, например, при обработке событий пользовательского интерфейса, связанных с добавлением или удалением детейлов. Метод `getCellComponent` используется автоматически фреймворком для конфигурации компонентов ячеек таблицы.

Подробнее на [github.com](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/controllers/components-examples/flexberry-groupedit/model-update-example.js).

## Дополнительные особенности реализации

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

### Вычислимые свойства в getCellComponent

Чтобы иметь возможность изменять свойства встроенного в ячейку компонента, этот компонент должен использовать миксин [DynamicPropertiesMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html).

Свойства компонента, описанные объектом `componentProperties`, в хуке контроллера `getCellComponent`, будут переданы с использованием свойства [`dynamicProperties`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/DynamicPropertiesMixin.html#property_dynamicProperties) из миксина.
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

Создан [пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/develop/#/components-examples/flexberry-groupedit/ember-flexberry-dummy-suggestion-list-groupedit-with-lookup-with-computed-atribute) на тестовом стенде.

### Ordered свойства в groupedit

При добавлении в представление, groupedit ordered-свойства в нем появятся некоторые особенности, которые следует учитывать:

1. Сортировка в groupedit будет по ordered свойству. При этом обычная сортировка в ГЕ будет отключена и ее нельзя включить.
2. При вставке/удалении строк в groupedit осуществляется автоматический пересчет ordered-атрибута по всем строкам.
3. В groupedit, когда в модели есть ordered-атрибут, появляется возможность перемещать строки вверх-вниз через стрелочки. При этом производится автоматический пересчет значения ordered-атрибута тех строк, у которых изменилась позиция.
4. Если ordered-атрибут отображается в ГЕ для пользователя, то соответствующее значение данного атрибута будет readonly.
