---
title: Компонент лукапа
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, Lookup, Flexberry Ember Component
toc: true
permalink: ru/efd3_lookup.html
lang: ru
summary: Обзор возможностей и настроек для компонента лукапа в технологии Flexberry Ember.
---

## Назначение компонента

Основное предназначение [компонента лукапа](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookup.html) - это выбор значения [мастера](fd_master-association.html). Посредством компонента лукапа есть возможность получить доступ к списку мастеровых объектов (этот список может быть представлен как в отдельном [модальном окне](efd3_modal-dialog.html), так и в выпадающем списке) и выбрать какой-то объект в качестве значения [мастеровой связи](fd_master-association.html).

## Обзор возможностей и API компонента

После [генерации](efd3_generated-app-structure.html) компонент лукапа в [шаблоне](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/) [формы создания и редактирования объектов](efd3_editform.html) выглядит следующим образом:

```hbs
{% raw %}{{flexberry-lookup
    choose="showLookupDialog"
    remove="removeLookupValue"
    value=model.myMaster
    class=(if (v-get validationObject "myMaster" "isInvalid") "error")
    displayAttributeName="intField"
    autocomplete=true 
    relationName="myMaster"
    projection="MasterForChild1L"
    title=(t "forms.neo-platform-gen-test-child1-e.myMaster-caption")
    readonly=readonly
    componentName="myMasterLookup"
    data-test-neo-platform-gen-test-child1-e-myMaster=true
}}{% endraw %}
```

* `value` - значение мастера (это объект, выбранный экземпляр мастеровой модели).
* `displayAttributeName` - отображаемое на форме поле выбранного мастера (требуется для работе в режиме автодополнения и выпадающего списка).
* `autocomplete` - работа в режиме автодополнения, подробнее ниже.
* `relationName` - название связи в модели, куда будет записано выбранное значение.
* `projection` - имя [проекции](efd3_model.html), по которой будут грузиться списки мастеровых объектов.
* `title` - заголовок модального окна для выбора значения (в данном примере значение [локализовано]()).
* `readonly` - флаг, определяющий, что компонент работает в режиме "только для чтения" (то есть изменить выбранное значение невозможно).
* `componentName` - имя компонента.
* `choose` и `remove` - это имена методов контроллера, которые обрабатывают события выбора или очистки значения лукапа и, соответственно, меняют значение поля модели. Обработчики `showLookupDialog` и `removeLookupValue` расположены в [специальном миксине контроллера FlexberryLookupMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookupMixin.html) 

Работу с настройками компонента лукапа можно посмотреть на [тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/settings-example).

* `placeholder` - отображаемый в лукапе текст при отсутствии выбранного значения.
* `chooseText` - текст/html внутри кнопки выбора значения.
* `removeText` - текст/html внутри кнопки очистки значения.
* `chooseButtonClass` - css-class для кнопки выбора значения.
* `removeButtonClass` - css-class для кнопки очистки значения.

* `relatedModel` - модель, чей атрибут отображает данный компонент (данное свойство наследовано от [базового компонента FlexberryBaseComponent](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryBaseComponent.html)) (то есть модель ,для которой будет редактироваться ссылка на мастеровой объект).
* `displayValue` - отображаемый для пользователя текст, соответствующий текущему выбранному значению. Этот текст может быть изменён, но это не изменит автоматически текущую модель.

Компонент может работать в режиме автодополнения и выпадающего списка, настройка данных режимов рассмотрена ниже.

{% include important.html content="Компонент лукапа не может находиться одновременно в режиме выпадающего списка и автодополнения (за это отвечают соответствующие флаги), такая настройка приведёт к ошибке." %}

{% include note.html content="Более детальное описание устройства компонента лукапа при вызове модального окна представлено в [этой статье](efd3_modal-dialog.html)." %}

### Кнопка просмотра выбранного значения (preview)

Компонент лукапа предоставляет возможность осуществлять просмотр выбранного значения. Для использования данной возможности требуется определить следующие настройки:

```hbs
{% raw %}{{flexberry-lookup
  ...
  preview=(action "previewLookupValue")
  showPreviewButton=true
  previewFormRoute=previewFormRoute
}}{% endraw %}
```

* `preview` - указание метода контроллера, который обрабатывают событие вызова просмотра. Обработчик `previewLookupValue` расположен в [специальном миксине контроллера FlexberryLookupMixin](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookupMixin.html) 
* `showPreviewButton` - флаг, определяющий, отображать ли в компоненте кнопку просмотра выбранного мастера (просмотр недоступен, если значение не выбрано).
* `previewOnSeparateRoute` - флаг, определяющий, что просмотр выбранного мастера следует осуществлять на отдельном роуте (а не в модальном окне).
* `previewFormRoute` - имя [роута](https://guides.emberjs.com/v3.1.0/routing/defining-your-routes/) [формы](efd3_editform.html), где будет происходить просмотр выбранного мастера (форма находится в режиме "только для чтения").
* `previewText` -  текст/html внутри кнопки просмотра, по умолчанию "{% raw %}<i class="eye icon"></i>{% endraw %}".
* `previewFormProjection` - имя проекции, по которой будет грузиться модель для просмотра (если данное свойство не указано, то будет взята используемая на роуте `previewFormRoute` проекция).
* `controllerForPreview` - имя [контроллера](https://guides.emberjs.com/v3.1.0/controllers/) [формы](efd3_editform.html), где будет происходить просмотр выбранного мастера (если значение не указано, то будет взят соответствующий `previewFormRoute` контроллер).

## Настройка окна со списком значений

Технология Flexberry Ember позволяет осуществлять настройку модального окна, для этого есть следующие возможности:

* `title` - заголовок модального окна (рекомендуется задавать [локализованное значение]()).
* `modalDialogSettings` - настройки внешнего вида модального окна, передаваемые непосредственно в [компонент модального диалога](efd3_modal-dialog.html), на базе которого оно реализовано.
* `lookupWindowCustomProperties` - указание метода, возвращающего список настроек, которые будут переданы в модальное окно.

### modalDialogSettings - настройки внешнего вида модального окна

Для данной настройки можно указывать следующие опции:

```js
{
    sizeClass: 'small'|'large'|'fullscreen',
    useOkButton: true|false,
    useCloseButton: true|false,
    settings: modalSettings
}
```

* `sizeClass` - css-class размера модального окна, возможные варианты: 'small', 'large', 'fullscreen'. Если ничего не указано, то используется 'small'.
* `useOkButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку окончания выбора значения. Если ничего не указано, то используется 'true'.
* `useCloseButton` - флаг, определяющий, следует ли на модальном окне отображать кнопку закрытия. Если ничего не указано, то используется 'true'.
* `settings` - [настройки](https://semantic-ui.com/modules/modal.html#/settings), которые передаются компоненту [Semantic UI Modal](https://semantic-ui.com/modules/modal.html), на базе которого реализовано [модальное окно](efd3_modal-dialog.html).

{% include note.html content="Настройка `modalDialogSettings` используется как для модального окна со списком для выбора значения, так и для модального окна предпросмотра выбранного значения." %}

### lookupWindowCustomProperties - настройки списка модального окна

В [примере](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/customizing-window-example) ниже настройка получает в качестве значение метод `getLookupFolvProperties` контроллера

```hbs
{% raw %}{{flexberry-lookup
    ...
    lookupWindowCustomProperties=(action "getLookupFolvProperties")
}}{% endraw %}
```

Реализация метода `getLookupFolvProperties` контроллера может быть следующая:

```js
import Ember from 'ember';
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  actions: {
    getLookupFolvProperties: function(options) {
      let methodArgs = Ember.merge({
        projection: undefined, 
        relationName: undefined, 
        componentName: undefined,
      }, options);

      if (methodArgs.relationName === 'type') {
        return {
          filterButton: true
        };
      }

      return undefined;
    }
  }
});
```

В метод передаются следующие параметры:

* projection - имя проекции, по которой будет строиться список на модальном окне.
* relationName - имя связи, для которой открывается модальное окно.
* componentName - компонент, открывающий модальное окно.

Метод может возвращать различные настройки (по умолчанию данные настройки попадают для инициализации напрямую в [компонент списка]() на модальной форме). Имена доступных настроек можно посмотреть [в документации](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryObjectlistviewComponent.html), в частности, это:

* `filterButton` - флаг, определяющий, отображать ли кнопку фильтрации на панели,
* `refreshButton` - флаг, определяющий, отображать ли кнопку обновления на панели,
* `perPage` - количество элементов, отображаемых на одной странице списка (по умолчанию 5),
* и другие.

## Указание функции ограничения

Для доступного списка мастеров можно задавать функцию ограничения (соответственно, объекты, не удовлетворяющие этому ограничению, отображаться не будут). Ограничение задаётся путём задания [предиката](efd3_query-language.html).

В [примере](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/limit-function-example) ниже ограничение возвращает [вычислимое свойство](https://api.emberjs.com/ember/3.1/classes/ComputedProperty) контроллера `lookupCustomLimitPredicate`

```hbs
{% raw %}{{flexberry-lookup
    ...
    lookupLimitPredicate=lookupCustomLimitPredicate
}}{% endraw %}
```

Реализация [вычислимого свойства](https://api.emberjs.com/ember/3.1/classes/ComputedProperty) контроллера `lookupCustomLimitPredicate` может быть следующая:

```js
import Ember from 'ember';
import EditFormController from 'ember-flexberry/controllers/edit-form';

export default EditFormController.extend({
  lookupCustomLimitPredicate: Ember.computed('model.type', function() {
    let currentLookupValue = this.get('model.type');
    if (currentLookupValue) {
      let currentLookupText = this.get('model.type.name');
      return new StringPredicate('name').contains(currentLookupText);
    }

    return undefined;
  })
});
```

Также [задать функцию ограничения возможно через динамические свойства компонента](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/limit-function-through-dynamic-properties-example) (эта возможность определена в [базовом компоненте FlexberryBaseComponent](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryBaseComponent.html#property_dynamicProperties)), для этого в `dynamicProperties` передаётся структура вида: 

```js
{
    lookupLimitPredicate: null // Программно можно определять текущее ограничение.
}
```

{% include note.html content="Если [компонент лукапа находится в компоненте Групэдит](), то для задания функции ограничения следует воспользоваться настройкой [lookupAdditionalLimitFunction](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookup.html#property_lookupAdditionalLimitFunction)." %}

## Возможности сервиса lookup-events

Для расширения возможностей лукапа [технология Flexberry Ember предоставляет сервис](efd3_service.html) [`lookup-events`](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/LookupEvents.html), содержащий [миксин `Evented`](https://api.emberjs.com/ember/3.1/classes/Evented), благодаря чему данный сервис позволяет подписываться на [события компонета лукапа](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/services/lookup-events.js).

* `lookupDialogOnShow` - событие взводится, когда модальное окно лукапа начинает отображаться. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа.
* `lookupDialogOnVisible` - событие взводится, когда модальное окно лукапа полностью отобразилось. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `lookupDialog` - контекст модального окна.
* `lookupDialogOnHidden` - событие взводится, когда модальное окно лукапа перестало отображаться. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа.
* `lookupOnChange` - событие взводится, когда значение лукапа было изменено. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `newValue` - новое значение лукапа.
* `lookupDialogOnDataLoaded` - событие взводится, когда в модальное окно лукапа заканчивают загружаться данные. Событие передаёт следующие параметры: `componentName` - имя компонента лукапа, `loadedData` - загруженные данные, `isInitialLoad` - флаг, определяющий, является ли загрузка первой (перезагрузки возможны при наложении фильтров, работе пэйджинга и др.).

## Режим выпадающего списка
Компонент лукапа [может работать в режиме выпадающего списка](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/dropdown-mode-example) (соответственно, выозможность выбора значения через модальное окно при этом недоступна).

Основные [настройки компонента лукапа](http://flexberry.github.io/ember-flexberry/autodoc/develop/classes/FlexberryLookup.html) для работы в режиме выпадающего списка:

* `dropdown` - флаг, определяющий, что компонент работает в режиме выпадающего списка.
* `dropdownClass` - CSS-классы для лукапа, работающего в режиме выпадающего списка.
* `maxResults` - максимальное число записей, которое единовременно может увидеть пользователь, остальные записи (при наличии) доступны через прокрутку. Данная настройка по умолчанию имеет значение "10".
* `dropdownIsSearch` - флаг, определяющий, что среди доступных значений будет производиться поиск, то есть пользователь в поле лукапа будет иметь возможность начать печатать некоторое значение, а в выпадающем окне будут отображаться только найденные возможные значения (поиск регистрозависимый).
* `displayAttributeName` - имя отображаемого пользователю свойства (соответственно, если у нескольких мастеровых объектов одно и то же значение этого свойства, то на форме пользователь их отличить не сможет), данная настройка актуальна только при работе в режиме автодополнения или выпадающего списка.
* `sorting` - направление сортировки доступных значений по полю `displayAttributeName`, может иметь значения `asc` (по возрастанию) или `desc` (по убыванию), по умолчанию имеет значение `asc`.
* `autocompleteOrder` - порядок сортировки запрашиваемых элементов, может включать в том числе скрытые поля, содержит перечисление названий свойств и направлений сортировки. Например, `moderated asc, parent desc`. Если указан `autocompleteOrder`, то `sorting` не учитывается.

Выпадающий список реализован на базе [Semantic UI Dropdown](https://semantic-ui.com/modules/dropdown.html), поэтому к компоненту лукапа можно применять [некоторые настройки Semantic UI Dropdown](https://semantic-ui.com/modules/dropdown.html#/settings), в частности:

* `allowReselection` (по умолчанию false),
* `allowAdditions` (по умолчанию false),
* `hideAdditions` (по умолчанию true),
* `minCharacters` (по умолчанию 1),
* `match` (по умолчанию 'both'),
* `selectOnKeydown` (по умолчанию true),
* `forceSelection` (по умолчанию true),
* `allowCategorySelection` (по умолчанию false),
* `direction` (по умолчанию 'auto'),
* `keepOnScreen` (по умолчанию true),
* `fullTextSearch` (по умолчанию false),
* `preserveHTML` (по умолчанию true),
* `sortSelect` (по умолчанию false),
* `showOnFocus` (по умолчанию true),
* `allowTab` (по умолчанию true),
* `transition` (по умолчанию 'auto'),
* `duration` (по умолчанию 200).

[Пример компонента лукапа, настроенного на работу в режиме выпадающего списка](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/dropdown-mode-example):

```hbs
{% raw %}{{flexberry-lookup
    value=model.type
    projection="DropDownLookupExampleView"
    displayAttributeName="name"
    title="Master"
    relationName="type"
    choose="showLookupDialog"
    remove="removeLookupValue"
    readonly=readonly
    lookupLimitPredicate=lookupCustomLimitPredicate
    dropdown=true
    sorting="desc"
    direction="upward"
}}{% endraw %}
```

{% include note.html content="Запрос записей для выпадающего списка производится по проекции, указанной в настройке `projection`." %}

## Лукап с автодополнением

Компонент лукапа в режиме работы с автодополнением позволяет как выбрать значение через модальное окно, так и путём набора значения прямо в поле компонента лукапа, при этом пользователю будет предоставлен список возможных значений (в этом отношении это имеет схожесть с работой в режиме выпадающего списка, поэтому некоторые настройки выпадающего списка актуальны и здесь).

* `autocomplete` - флаг, определяющий, что компонент работает в режиме автодополнения.
* `maxResults` - максимальное число записей, которое единовременно может увидеть пользователь. Данная настройка по умолчанию имеет значение "10". Доступность остальных записей в списке автодополнения зависит от наличия флага `usePaginationForAutocomplete`.
* `usePaginationForAutocomplete` - флаг, определяющий, что для значений из автодополнения следует использовать пейджинг (что позволит пролистыванием страниц просмотреть все подходящие значения).
* `displayAttributeName` - имя отображаемого пользователю свойства (соответственно, если у нескольких мастеровых объектов одно и то же значение этого свойства, то на форме пользователь их отличить не сможет), данная настройка актуальна только при работе в режиме автодополнения или выпадающего списка.
* `sorting` - направление сортировки доступных значений по полю `displayAttributeName`, может иметь значения `asc` (по возрастанию) или `desc` (по убыванию), по умолчанию имеет значение `asc`.
* `autocompleteOrder` - порядок сортировки запрашиваемых элементов, может включать в том числе скрытые поля, содержит перечисление названий свойств и направлений сортировки. Например, `moderated asc, parent desc`. Если указан `autocompleteOrder`, то `sorting` не учитывается.
* `autocompletePersistValue` - флаг, определяющий, следует ли удалять набранное значение из поля лукапа, если такого нет в результатах поиска (удаление производится, если данный флаг имеет значение `false`) (при включении данной настройки `displayValue` должно быть забиндено на поле, в которое нужно сохранять отображаемое в компоненте лукапа значение: `displayValue=model.lookupDisplayValue`).
* `autocompleteProjection` - имя проекции, по которой производится запрос для получения значений для автодополнения.
* `autocompleteDirection` - направление открытия выпадающего списка с подходящими значениями (возможные значения: `downward`, `upward`, `auto`; по умолчанию `downward`).

{% include note.html content="Запрос записей для списка на модальном окне производится по проекции, указанной в настройке `projection`; запрос подходящих записей для выбора в режиме автодополнения производится по проекции, указанной в `autocompleteProjection`. Если `autocompleteProjection` не указано, то запрос производится по проекции, содержащей только поле `displayAttributeName`." %}

Список автодополнения реализован на базе [Semantic UI Search](https://semantic-ui.com/modules/search.html).

[Пример компонента лукапа, настроенного на работу в режиме автодополнения](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/autocomplete-order-example):

```hbs
{% raw %}{{flexberry-lookup
    readonly=readonly
    value=model.type
    projection="SettingLookupExampleView"
    displayAttributeName="name"
    autocompleteOrder="moderated asc, parent desc"
    title=title
    relatedModel=model
    relationName="type"
    choose="showLookupDialog"
    remove="removeLookupValue"
    autocomplete=true
    placeholder=placeholder
}}{% endraw %}
```

## Установка сортировки по умолчанию
Благодаря [сервису настроек пользователя]() для компонента лукапа можно задать сортировку по умолчанию и использовать её на списке в модальном окне.

[Данный пример доступен на тестовом стенде](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-lookup/default-ordering-example).

```hbs
{% raw %}{{flexberry-lookup
    ...
    lookupWindowCustomProperties=(action 'getLookupFolvProperties')
    componentName=lookupComponentName
}}{% endraw %}
```

Контроллер для такого компонента лукапа может быть следующим (данный контроллер позволяет на списке в модальном окне создать кастомную кнопку с локализованным содержимым, при нажатии на которую применяется сортировка по умолчанию для данного компонента лукапа):

```js
import Ember from 'ember';
import EditFormController from 'ember-flexberry/controllers/edit-form';
import serializeSortingParam from 'ember-flexberry/utils/serialize-sorting-param'; // Метод для сериализации сортировки.
import { translationMacro as t } from 'ember-i18n'; // Метод для локализации текста.

export default EditFormController.extend({
  lookupComponentName: 'lookupUserSettings', // Имя компонента лукапа, по этому имени ищется настройка по умолчанию.

  actions: {
    getLookupFolvProperties: function(options) { // Метод возвращает настройки, которые будут переданы списку на модальном окне.
      let methodArgs = Ember.merge({
        projection: undefined,
        relationName: undefined
      }, options);

      if (methodArgs.relationName === 'type') { // На форме может быть несколько компонент лукапа. Проверяем, что сработал нужный.
        return {
          filterButton: true, // Флаг определяет, что на список на модальной форме будет добавлена кнопка фильтрации.
          customButtons: [{ // Добавление кастомной кнопки.
            i18n: this.get('i18n'),
            buttonName: t('components.olv-toolbar.clear-sorting-button-text'), // Локализованное имя кнопки.
            buttonAction: () => { // Обработка нажатия кастомной кнопки.
              // Получение пользовательской настройки для компонента с заданным именем.
              let defaultUserSetting = this.get('userSettingsService').getDefaultDeveloperUserSetting(this.get('lookupComponentName'));

              // Указание данной сортировки в качестве текущей.
              this.set('lookupController.sort', serializeSortingParam(defaultUserSetting.sorting || []));
            },
          }],
        };
      }

      return undefined;
    }
  }
});
```
