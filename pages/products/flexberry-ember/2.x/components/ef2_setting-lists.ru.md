---
title: Настройка списков
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, OLV, настройка
toc: true
permalink: ru/ef2_setting-lists.html
lang: ru
summary: Настройка панели управления, пользовательские кнопки, инструменты работы с объектами на списках, блокирование отдельных ячеек, иерархический список, вычислимые свойства, создание на основе
---

Списки для разных систем требуют разного, часто индивидуального, подхода. Для этих целей в компоненте Flexberry Objectlistview реализован ряд механизов, позволяющих настравивать как панель управления, так и отдельные строки, ячейки или внешний вид.

## Настройка панели управления для списков

Панель управления (тулбар) [Flexberry Objectlistview](ef2_object-list-view.html) основана на технологическом контроле `olv-toolbar`.

Настройка панели управления происходит через компонент `Flexberry Objectlistview`.

### Кнопка создания новой записи

```hbs
{% raw %}
{{flexberry-objectlistview
  createNewButton = true
}}{% endraw %}
```

`createNewButton` - флаг, определяющий, отображать ли кнопку создания на панели управления.

### Создание новой записи на основе

```hbs
{% raw %}
{{flexberry-objectlistview
  showPrototypeButtonInRow = true
  showPrototypeMenuItemInRow = true
}}{% endraw %}
```

`showPrototypeButtonInRow` - флаг, определяющий отображать ли кнопку создания на основе в строке.

`showPrototypeMenuItemInRow` - флаг, определяющий отображать ли кнопку создания на основе в меню строки.

Также необходимо указать представление, по которому будет создаваться копия.

1.Можно указать представление для конкретной формы - в new роуте задать свойство `prototypeProjection` (имя представления строкой)

2.Можно указать представление для копирования по умолчанию для всей модели - в нужной модели задать свойство `prototypeProjection` (имя представления строкой)

### Кнопка обновления

```hbs
{% raw %}
{{flexberry-objectlistview
  refreshButton = true
}}{% endraw %}
```

`refreshButton` - флаг, определяющий, отображать ли кнопку обновления на панели управления.

### Кнопка удаления выделенных записей

```hbs
{% raw %}{{flexberry-objectlistview
  componentName = "..."
  deleteButton = true
  showCheckBoxInRow = true
  ...
}}{% endraw %}
```

Для того чтобы начала функционировать кнопка удаления выделенных записей, требуется определить следующие свойства:

* `componentName` - имя контрола (используется для идентификации составных частей контрола, которые взаимодействуют через [внедрённый сервис](http://emberjs.com/api/classes/Ember.inject.html#method_service)). Например, в качестве значения для списка записей типа "employee" можно указать "employeesFlexberryObjectListView".
* `deleteButton` - флаг, определяющий, отображать ли кнопку удаления на панели управления.
* `showCheckBoxInRow` - флаг, определяющий, отображать ли чекбокс в строке

{% include note.html content="Если в Flexberry Objectlistview не отмечена ни одна строка, то кнопка удаления не активна. Когда появляются отмеченные записи, кнопка удаления становится доступна." %}

При удалении информация сразу отправляется на сервер для сохранения изменений.

## Пользовательские кнопки для списков

### Встраивание пользовательских кнопок в тулбар списка

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

### Встраивание пользовательских кнопок в строки списка

Пользовательские кнопки для строк списка создаются по аналогичному принципу. Событие, как и для кнопки в тулбаре, может быть задано строкой. Например, так:

```javascript
...
actions: {
    userButtonInRowActionTest(model) {
      this.set('modelFromClickedRow', model);
    },
});
```

### Кнопки пользовательских настроек

При значении атрибута `colsConfigButton=true` на панели управления отображаются кнопки управления пользовательскими наcтройками.

```hbs
{% raw %}{{flexberry-objectlistview
  componentName = "..."
  colsConfigButton=true
  ...
}}{% endraw %}
```

Подробно о функционале пользовательских настроек можно почитать в статье [Сервис настроек пользователя](ef2_model-user-settings-service.html).

#### Добавление пользовательских кнопок на панель управления

На панель управления можно добавлять кнопки, реализующие пользовательскую логику.
Чтобы добавить пользовательскую кнопку, требуется выполнить следующее:

1.В соответствующем прикладном [контроллере](ef2_controller.html) определить вычислимое свойство с произвольным именем, например, `сustomButtons`, которое возвращает массив структур вида:

```javascript
{
 buttonName: '...',
 buttonAction: '...',
 buttonClasses: '...',
 disabled: true,
}
```

* `buttonName` - имя кнопки на интерфейсе пользователя. Если передано пустое значение, то имя кнопки будет 'UserButton'.
* `buttonAction` - имя действия (action), которое будет вызываться данной кнопкой (при вызове действия используется [sendAction](http://emberjs.com/api/classes/Ember.Component.html#method_sendAction), поэтому обработчик можно определять как в [контроллере](ef2_controller.html), так и в соответствующем [роуте](ef2_route.html)). Если передано пустое значение, то в консоли браузера появится сообщение об ошибке. Желательно именовать действия с префиксом "userButtonAction", чтобы случайно не перетереть свойство контрола при регистрации этого действия.
* `buttonClasses` - классы, которые требуется добавить в создаваемую пользовательскую кнопку.
* `disabled` - логическое свойство, отвечающее за состояние кнопки, если `true` кнопка неактивна, иначе, активна.

2.В соответствующем прикладном контроллере (или [роуте](ef2_route.html)) определить обработчик события, имя которого было указано в `buttonAction`.

3.В [шаблоне](ef2_template.html) соответствующей формы у компонента `flexberry-objectlistview` определить свойства:

```hbs
{% raw %}
{{flexberry-objectlistview
  // ...
  customButtons=customButtons  
  userButtonAction1='userButtonAction1'
  userButtonAction2='userButtonAction2'
  // ...
  userButtonActionN='userButtonActionN'
}}{% endraw %}
```

* `customButtons` - определение свойства, откуда можно взять массив.
* `userButtonAction1`, `userButtonAction2`, ... `userButtonActionN` - регистрация действий, которые определялись в свойстве `buttonAction` (без такой "регистрации" действие может не вызываться и Ember не выдаст сообщения об ошибке).

### Пример использования пользовательской кнопки на списке

Например, требуется добавить пользовательскую кнопку, которая при клике будет выводить сообщение пользователю.

1.В шаблоне страницы задать заголовок в виде переменной "header".

```hbs
{% raw %}<h3 class="ui header">Страница с пользовательскими кнопками</h3>
<div class="row">
  {{flexberry-objectlistview
    // ...
    customButtons=customButtons
    userButtonAction1='userButtonAction1'
    userButtonAction2='userButtonAction2'
    // ...
    userButtonActionN='userButtonActionN'
  }}
</div>
<div class="row">{{messageForUser}}</div>{% endraw %}
```

2.В контроллере определить необходимые переменные, задать вычислимое локализируемое свойство "customButtons", которое вернёт массив описаний пользовательских кнопок (в данном случае - одной кнопки),  и действие "userButtonActionTest", которое будет обрабатывать нажатие на кнопку.

```javascript
import Ember from 'ember';
import ListFormController from 'ember-flexberry/controllers/list-form';

export default ListFormController.extend({
  /**
    Property to count clicks on user button.

    @property clickCounter
    @type Number
    @default 1
   */
  clickCounter: 1,

  /**
    Property to show user message after click on user button.

    @property messageForUser
    @type String
   */
  messageForUser: undefined,

  /**
    Property to form array of special structures of custom user buttons.

    @property customButtons
    @type Array
   */
  customButtons: Ember.computed('i18n.locale', function() {
    let i18n = this.get('i18n');
    return [{
      buttonName: i18n.t('forms.components-examples.flexberry-objectlistview.toolbar-custom-buttons-example.custom-button-name'),
      buttonAction: 'userButtonActionTest',
      buttonClasses: 'my-test-user-button test-click-button'
    }];
  }),

  actions: {
    /**
      Handler for click on custom user button.

      @method userButtonActionTest
     */
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

3.В шаблоне указать свойство для получения пользовательских кнопок, а также зарегистриовать  пользовательское действие.

```hbs
{% raw %}<h3>{{header}}</h3>

<div class="row">
  {{flexberry-objectlistview
    // ...
    customButtons=customButtons
    userButtonActionTest='userButtonActionTest'
  }}
</div>{% endraw %}
```

## Инструменты работы с объектами на списках

Кнопки "Отметить все на текущей странице", "Отметить все на всех страницах" и "Установить сортировку  по умолчанию" активируются вместе с `checkbox` в строках параметром `showCheckBoxInRow`.

* "Отметить все на текущей странице" - отмечает все объекты на сранице, добавляет отмеченые объекты в `slectRecords`.
* "Отметить все на всех страницах" - активирует параметр `allSeclect`, обработка удаления при активации этого параметра реализуется в соответствии с потребностями конкретного приложения в `action delete()` компонента.
* "Установить сортировку по умолчанию" - устанавливает сортировку и количество отображаемых страниц по умолчанию.

## Настройка иерархического списка

Компонент `{% raw %}{{flexberry-objectlistview}}{% endraw %}` имеет возможность отображения списка объектов в иерархическом режиме.

Если у объекта есть ссылка на самого себя, список таких объектов считается иерархическим.
Если объект имеет только одну такую ссылку, над списком объектов будет отображена кнопка, для переключения списка в иерархический режим.

Если объект имеет больше одной ссылки на самого себя, для возможности отображения списка в иерархическом режиме необходимо указать в свойстве `hierarchyByAttribute`, имя атрибута, по которому будет строиться иерархия.

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  hierarchyByAttribute="parent"
  ...
}}{% endraw %}
```

Если необходимо отключить возможность отображения списка в иерархическом режиме, необходимо свойство `disableHierarchicalMode` установить в значение `true`.

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  disableHierarchicalMode=true
  ...
}}{% endraw %}
```

При отображении списка объектов в иерархическом режиме, для каждого объекта выполняется отдельный запрос, для проверки наличия у него дочерних объектов.
Чтобы избавиться от лишних запросов, реализуйте в объекте атрибут, позволяющий определить, есть ли у объекта дочерние объекты.
Это позволит сразу отобразить кнопку для просмотра дочерних объектов, и загрузить их только при нажатии кнопки пользователем.

Этот атрибут может быть не хранимым, и определять наличие дочерних объектов, с помощью запроса в выражении для сервиса данных.
В модели для клиентского приложения, это может быть простой логический атрибут.

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

Если атрибут называется `IsParentRecord`, он по умолчанию будет использован в компоненте, иначе, нужно в свойстве `isParentRecordPropertyName` указать имя этого атрибута.

```hbs
{% raw %}
{{flexberry-objectlistview
  ...
  isParentRecordPropertyName="hasChildren"
  ...
}}{% endraw %}
```

Не забудьте добавить этот атрибут в представление которое используется для загрузки списка объектов, он может быть скрытым.

Пример компонента, отображающего список объектов в иерархическом режиме:

![](/images/pages/ABratchikova/Иерархия folv.png)

## Блокирование отдельных ячеек списка

На [списке](ef2_object-list-view.html) существует возможность заблокировать отдельную ячеку для открытия объекта [на редактирование](ef2_edit-form.html), оставив при этом активным клик на строке.

Для этого чтобы выключить клик по строке, нужно переопределить переход к форме редактирования, используя параметр (params):

```javascript
params.goToEditForm = false;
```

Затем вызвать метод `_super`.

В параметрах обработчика клика по строке есть:

* запись, по которой кликнули
* колонка по которой кликнули (в ней есть имя свойства, заголовок колонки на форме, cellComponent этой колонки)
* индекс нажатой колонки.

Данные параметры используются для отключения обработки клика по строке при определенных условиях (т.е. при клике по определенным ячейкам).

Например:

```javascript
actions: {
    objectListViewRowClick(record, params) {
      if (params.column && params.column.cellComponent.componentName === 'flexberry-file' && params.originalEvent.target.tagName.toLowerCase() !== 'td') {
        params.goToEditForm = false;
      }

      this._super(...arguments);
    }
```

Подробнее в коде приложения [dummy](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/routes/components-examples/flexberry-objectlistview/downloading-files-from-olv-list.js#L45).

## Обрезание текста в ячейках по длине

Есть возможность обрезать текст в ячейках по заданной длине. При наведении на такую ячейку во всплывающей подсказке указано полное значение текста ячейки. Это делается через настройку стандартного компонента ячейки в `getCellComponent`:

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

Свойство | Описание
:---------------------|:------------------------------------------------------------
`maxTextLength` | Определяет максимальное количество символов в ячейке.
`cutBySpaces` | Определяет способ обрезания текста (false - ровно по заданной длине, true - по последнему пробелу).
`displayMemberPath` | Необходимо, если значением ячейки является объект. Задает путь до отображаемого в ячейке свойства.

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
