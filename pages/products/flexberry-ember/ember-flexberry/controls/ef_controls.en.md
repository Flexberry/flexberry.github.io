---
title: Контролы ember-flexberry
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_controls.html
lang: en
summary: Перечень контролов, используемых в ember-flexberry
---

## Базовый контрол ember-flexberry

Для удобства распространения однотипной логики был создан базовый компонент, от которого наследуются другие ember-контролы.

Базовый контрол содержит обработку [режима "только для чтения"](ef_read-only-form.html)(`readonly`).

От него наследуются:

* `ObjectListView`
* `FlexberryTextbox`
* `DatetimePicker`

## FlexberryTextbox

`FlexberryTextbox` - контрол для отображения текстовых полей.

```hbs
{% raw %}
flexberry-textbox placeholder="(no value)" readonly=readonly required=true value=model.employee.firstName{% endraw %}
```

## DatetimePicker

`DatetimePicker` - контрол для отображения даты.

```hbs
{% raw %}
datetime-picker placeholder="(no value)" readonly=readonly hasTimePicker=true value=model.orderDate{% endraw %}
```

## ObjectListView

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

## Lookup

Лукап представляет собой элемент управления (контрол), позволяющий выбрать значение мастера. Его описание содержится в статье [Lookup](ef_lookup.html).

## UI Message

Основное предназначение [UI Message](ef_ui-message.html) - отображение сообщений о состоянии контрола. Например, отображение об успешности/неуспешности сохранения формы, предупреждения, информация и т.п.

## Flexberry-file

Компонент `flexberry-file` предоставляет ряд возможностей по работе с файлами в приложении. Подробнее описано в стать [Компонент flexberry-file](ef_file.html).
