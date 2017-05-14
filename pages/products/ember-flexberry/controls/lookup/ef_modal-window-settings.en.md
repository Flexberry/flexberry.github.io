---
title: Настройка поднимаемой по лукапу формы
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_modal-window-settings.html
folder: products/ember-flexberry/controls/lookup/
lang: en
summary: Представлены основные возможности настройки по настройке поднимаемой по лукапу формы.
---

## Настройка модального окна лукапа

Настройки модального окна [лукапа](ef_lookup.html) определены в `components/lookup-field/lookup-field-mixin.js`.

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

## Настройка размера

`modalWindowWidth` - это ширина открываемого по лукапу модального окна.

`modalWindowHeight` - это ширина открываемого по лукапу модального окна.

Если требуется изменить размер открываемого по лукапу модального окна, то можно переопределить заданные по умолчанию значения.

## Установка заголовка окна, открываемого по лукапу

Заголовок окна, открываемого по [лукапу](ef_lookup.html), устанавливается в свойстве `title` компонента `lookup-field` в шаблоне соответствующей формы редактирования. Например, в шаблоне формы редактирования `employee.hbs` встраивание лукапа может выглядеть следующим образом:

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

В результате заголовок из свойства `title` компонента `lookup-field` будет отображаться в модальном окне, открываемом по лукапу:

![](/images/pages/img/page/EditFormTitle/lookuptitle.png)


