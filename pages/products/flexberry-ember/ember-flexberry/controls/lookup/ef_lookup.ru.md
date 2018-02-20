---
title: Лукапы в ember-flexberry-приложении
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_lookup.html
lang: ru
summary: Представлены основные особенности лукапов
---

## Описание

Лукап представляет собой [элемент управления (контрол)](ef_controls.html), позволяющий выбрать значение мастера.

## Настройка лукапов в ember-приложении

Для настройки лукапов в ember-приложении требуется удостовериться в корректности настройки [модели](efd_model.html), сериализатора и шаблона.

Пусть у сущности `Подготовка` есть мастер `Редактор` типа `Пользователь`. Требуется настроить лукап по свойству `ExtendedName` мастера.

## Настройка модели

[Модель](efd_model.html) `Подготовка` должна содержать ссылку на мастера. [Представление](efd_model-projection.html) для отображения формы, где будет расположен лукап, также должно включать описание мастера.

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

В [модели](efd_model.html), соответствующей мастеру типа `Пользователь`, необходимо задать [представление](efd_model-projection.html), по которому будет происходить отображение.

```javascript
var Model = BaseModel.extend({
  extendedName: DS.attr('string')
});

Model.defineProjection('ПользовательE', 'пользователь', {
  extendedName: Proj.attr('Полное имя')
});
```

## Настройка сериализаторов

В [сериализаторе](efd_serializer.html) `Подготовка` описываем ссылку на мастера.

```javascript
export default ApplicationSerializer.extend({
  attrs: { 
      редактор: { serialize: 'odata-id', deserialize: 'records' }
  },
  primaryKey: '__PrimaryKey'
});
```

[Сериализатор](efd_serializer.html) для типа `Пользователь` попроще:

```javascript
export default ApplicationSerializer.extend({
  attrs: {  },
  primaryKey: '__PrimaryKey'
});
```

## Настройка шаблона

На страницу редактирования класса `Подготовка` можно вставить конструкцию

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

### Список настроек лукапа:

Свойство | Описание | Дефолтное значение
:---------------------|:------------------------------------------------------------|:----------------
`hoose` | Определяет имя action'а, которое будет происходит по клику на кнопку 'choose'.|
`remove` | Определяет имя action'а, которое будет происходит по клику на кнопку 'remove'.|
`chooseText` | Определяет текст/html внутри кнопки 'choose'.|
`removeText` | Определяет текст/html внутри кнопки 'remove'.|
`chooseButtonClass` | Определяет css-class на кнопку 'choose'.|
`removeButtonClass` | Определяет css-class на кнопку 'remove'.|
`placeholder` | Определяет placeholder | t('flexberry-lookup.placeholder')
`value` | Определяет выбранный экземпляр модели (мастеровой объект) |
`relatedModel` | Определяет модель для которой будет редактироваться ссылка на мастеровой объект) |
`relationName` | Определяет имя отношения  |
`projection` | Определяет, по какому представлению будут отображаться мастера в списке |
`sizeClass` | Определяет css-class размера окна, возможные варианты: small, large, fullscreen | small
`title` | Заголовок модального окна |
`autocomplete` | Режим автокомплита, в режиме "Только для чтения" не работает | false
`dropdown` | Режим выпадающего списка, в режиме "Только для чтения" не работает | false
`displayAttributeName` | Имя атрибута модели (свойства мастера), которое отображается для пользователя | 
`minCharacters` | Минимальное количество символов для автокомпилита, в режиме автокомплита и в режиме выпадающего списка | 1
`maxResults` | Максимальное количество отображаемых записей в режиме автокомплита и в режиме в режиме выпадающего списка, не обязательное свойство | 10

## Блочная форма компонента

{% include important.html content="В данный момент доступна только для мобильного шаблона." %}

Возможно использовать компонент в блочной форме:

```hbs
{% raw %}{{#flexberry-lookup ...}}
  {{model.employee1.firstName}}<br>
  {{model.employee1.lastName}}
{{/flexberry-lookup}}{% endraw %}
```
в этом случае прикладной программист может переопределить как будет выглядеть отображение мастера на форме

## Настройка модального окна

Как настраивать поднимаемые по лукапу модальные окна, описано [здесь](ef_modal-window-settings.html).

## Задание ограничений

{% include important.html content="Информация устарела, свойство limitFunction удалено." %}

Задание ограничений на значения, отображаемые в списке для выбора, осуществляются посредством задания фильтра ($filter) в формате OData ([OData Version 4.0](http://docs.oasis-open.org/odata/odata/v4.0/errata02/os/complete/part2-url-conventions/odata-v4.0-errata02-os-part2-url-conventions-complete.html#_Toc406398094)). Данный фильтр задаётся в виде строки в свойство `limitFunction` при задании шаблона.

```hbs
{% raw %}
{{flexberry-lookup
  limitFunction=SomeFilterFunction
  // ...
}}{% endraw %}
```

Рассмотрим задание ограничения на примере. Пусть у сущности `Employee` есть мастеровая ссылка `Employee1` типа `Employee`. Требуется по лукапу отобразить только те записи, у которых `FirstName` больше или равен `FirstName` текущей выбранной записи.

Модель `Employee` имеет следующий вид:

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string'),
  lastName: DS.attr('string'),
  birthDate: DS.attr('date'),
  employee1: DS.belongsTo('employee', { inverse: null, async: false }),
});
```

В контроллере типа `Employee` создадим вычислимое свойство `lookupLimitFunction`, которое будет возвращать фильтр, чтобы "отобразить только те записи, у которых `FirstName` больше или равен `FirstName` текущей выбранной записи".

```javascript
export default EditFormController.extend({
  // Caption of this particular edit form.
  title: 'Employee',

  /**
   * Current function to limit accessible values of model employee1.
   *
   * @property lookupLimitFunction
   * @type String
   * @default undefined
   */
  lookupLimitFunction: Ember.computed('model.employee1', function() {
    let currentLookupValue = this.get('model.employee1');
    if (currentLookupValue) {
      let currentLookupText = this.get('model.employee1.firstName');
      return 'FirstName ge \'' + currentLookupText + '\'';
    }

    return undefined;
  })
});
```

Далее соответствующее свойство необходимо указать в шаблоне формы:

```hbs
{% raw %}
{{flexberry-lookup
  limitFunction=lookupLimitFunction
  // ...
}}{% endraw %}
```

{% include important.html content="Поскольку фильтр задаётся непосредственно для OData, `FirstName` пишется с большой буквы и соответствует имени свойства в модели OData (в ember-модели пишется `firstName` с маленькой буквы).<br/>
В соответствии со [стандартом OData Version 4.0](http://docs.oasis-open.org/odata/odata/v4.0/errata02/os/complete/part2-url-conventions/odata-v4.0-errata02-os-part2-url-conventions-complete.html#_Toc406398094) фильтр с обращением к полю мастера будет записываться как `limitFunction='ТипДокумента/Наименование eq \'ОРД - распоряжение\''` (через символ `\/`)." %}

## Лукап в режиме автодополнения

Лукап можно переводить в [режим автодополнения](ef_lookup-autocomplete.html).
