---
title: Контролы ember-flexberry
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef_controls.html
folder: products/ember-flexberry/controls/
lang: ru
summary: 
---

## Базовый контрол ember-flexberry

Для удобства распространения однотипной логики был создан базовый компонент, от которого наследуются другие ember-контролы.

Базовый контрол содержит:

* обработку [режима "только для чтения" (`readonly`)](ef_read-only-form.html).

## Технологические контролы, наследующие от базового

### FlexberryTextbox

`FlexberryTextbox` - контрол для отображения текстовых полей.

```
`flexberry-textbox placeholder="(no value)" readonly=readonly required=true value=model.employee.firstName`
```

### DatetimePicker

`DatetimePicker` - контрол для отображения даты.

```
`datetime-picker placeholder="(no value)" readonly=readonly hasTimePicker=true value=model.orderDate`
```

### ObjectListView

`ObjectListView` - контрол для отображения списка объектов (может использоваться как на списковой форме для отображения списка объектов, так и на форме редактирования для отображения детейлов).

```hbs
{% raw %}{{object-list-view
	class = "ui attached"
	modelProjection = modelProjection.attributes.orders
	content = model.orders
	cellComponent = getCellComponent
	readonly = readonly
}}{% endraw %}
```

## Контролы, которые пока не наследуются от базового

* [Lookup](ef_lookup.html)
* [UI Message](ef_ui-message.html)
