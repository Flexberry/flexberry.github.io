---
title: Configuring the control panel for lists
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_olv-toolbar.html
lang: en
summary: Использование технологических и пользовательских кнопок в тулбаре
---

Панель управления (тулбар) [Flexberry Objectlistview](ef_object-list-view.html) основана на технологическом контроле `olv-toolbar`.

Настройка панели управления происходит через компонент `Flexberry Objectlistview`.

## Кнопка создания новой записи

```hbs
{% raw %}
{{flexberry-objectlistview 
  createNewButton = true
}}{% endraw %}
```

`createNewButton` - флаг, определяющий, отображать ли кнопку создания на панели управления.

## Кнопка обновления

```hbs
{% raw %}
{{flexberry-objectlistview
  refreshButton = true
}}{% endraw %}
```

`refreshButton` - флаг, определяющий, отображать ли кнопку обновления на панели управления.

## Кнопка удаления выделенных записей

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

## Кнопки пользовательских настроек

При значении атрибута `colsConfigButton=true` на панели управления отображаются кнопки управления пользовательскими наcтройками.

```hbs
{% raw %}{{flexberry-objectlistview
	componentName = "..."
	colsConfigButton=true
	...
}}{% endraw %}
```

Подробно о функционале пользовательских настроек можно почитать в статье [Сервис настроек пользователя](ef_model-user-settings-service.html).

### Добавление пользовательских кнопок на панель управления

На панель управления можно добавлять кнопки, реализующие пользовательскую логику.
Чтобы добавить пользовательскую кнопку, требуется выполнить следующее:

1.В соответствующем прикладном [контроллере](ef_controller.html) определить вычислимое свойство с произвольным именем, например, `сustomButtons`, которое возвращает массив структур вида:

```javascript
{
 buttonName: '...',
 buttonAction: '...',
 buttonClasses: '...',
 disabled: true,
}
```

* `buttonName` - имя кнопки на интерфейсе пользователя. Если передано пустое значение, то имя кнопки будет 'UserButton'.
* `buttonAction` - имя действия (action), которое будет вызываться данной кнопкой (при вызове действия используется [sendAction](http://emberjs.com/api/classes/Ember.Component.html#method_sendAction), поэтому обработчик можно определять как в [контроллере](ef_controller.html), так и в соответствующем [роуте](ef_route.html)). Если передано пустое значение, то в консоли браузера появится сообщение об ошибке. Желательно именовать действия с префиксом "userButtonAction", чтобы случайно не перетереть свойство контрола при регистрации этого действия.
* `buttonClasses` - классы, которые требуется добавить в создаваемую пользовательскую кнопку.
* `disabled` - логическое свойство, отвечающее за состояние кнопки, если `true` кнопка неактивна, иначе, активна.

2.В соответствующем прикладном контроллере (или [роуте](ef_route.html)) определить обработчик события, имя которого было указано в `buttonAction`.

3.В [шаблоне](ef_template.html) соответствующей формы у компонента `flexberry-objectlistview` определить свойства:

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
* `userButtonAction1`, `userButtonAction2`, ... `userButtonActionN` - регистрация действий, которые определялись в свойстве `buttonAction` ([без такой "регистрации"](http://emberigniter.com/send-action-does-not-fire/) действие может не вызываться и Ember не выдаст сообщения об ошибке).

## Пример использования пользовательской кнопки на списке

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
