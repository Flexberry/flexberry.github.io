---
title: Компоненты для работы с данными на форме редактирования (edit-form)
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_edit-form-components.html
lang: ru
summary: Перечень компонентов, используемых для отображения данных
---

## Компоненты для отображения текстовых полей

### flexberry-field

flexberry-field - компонент для отображения текстовых полей вместе с надписями.
Общий вид компонента, в случае, если текущая тема оформления “BlueSky”:

![](/images/pages/products/flexberry-ember/components/flexberry-field.png)

Чтобы добавить flexberry-field на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-field
  value=model.text
  label=label
  placeholder=placeholder
  readonly=readonly
  type=type
  maxlength=maxlength
}}{% endraw %}
```

#### Свойства flexberry-field

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`placeholder` | Определяет placeholder.
`label` | Определяет label.
`classNames` | Определяет css-class на компонент.
`type` | Определяет вид компонента.
`maxlength` | Определяет максимальное количество символов (в единицах кода UTF-16), которое может ввести пользователь.
`appConfigSettingsPath` | Определяет путь к настройкам компонента в конфигурации приложения.

### flexberry-textbox

flexberry-textbox - компонент для отображения текстовых полей в виде одной строки.
Общий вид компонента, в случае, если текущая тема оформления “BlueSky”:

![](/images/pages/products/flexberry-ember/components/flexberry-textbox.png)

Чтобы добавить flexberry-textbox на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-textbox
  value=model.text
  placeholder=placeholder
  readonly=readonly
  class=class
  maxlength=maxlength
}}{% endraw %}
```

#### Свойства flexberry-textbox

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`placeholder` | Определяет placeholder.
`classNames` | Определяет css-class на компонент.
`type` | Определяет вид компонента.
`maxlength` | Определяет максимальное количество символов (в единицах кода UTF-16), которое может ввести пользователь.
`appConfigSettingsPath` | Определяет путь к настройкам компонента в конфигурации приложения.

### flexberry-textarea

flexberry-textarea - компонент для отображения текстовых полей в виде области, в которую можно вводить несколько строк.
Общий вид компонента, в случае, если текущая тема оформления “BlueSky”:

![](/images/pages/products/flexberry-ember/components/flexberry-textarea.png)

Чтобы добавить flexberry-textarea на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-textarea
  value=model.text
  placeholder=placeholder
  readonly=readonly
  class=class
  rows=rows
  cols=cols
  maxlength=maxlength
}}{% endraw %}
```

#### Свойства flexberry-textarea

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`placeholder` | Определяет placeholder.
`classNames` | Определяет css-class на компонент.
`appConfigSettingsPath` | Определяет путь к настройкам компонента в конфигурации приложения.
`rows` | Определяет количество строк таблицы, связанных с текстовой областью.
`cols` | Определяет количество столбцов таблицы, связанных с текстовой областью.

## Компоненты для отображения даты

### flexberry-datepicker

flexberry-datepicker - компонент для отображения даты.

![](/images/pages/products/flexberry-ember/components/flexberry-datepicker.png)

Чтобы добавить flexberry-datepicker на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-datepicker
  dateTimeFormat=dateTimeFormat
  hasTimePicker=hasTimePicker
  minDate=minDate
  maxDate=maxDate
  value=model.date
  placeholder=placeholder
  readonly=readonly
  drops='up'
}}{% endraw %}
```

#### Свойства flexberry-datepicker

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`placeholder` | Определяет placeholder.
`classNames` | Определяет css-class на компонент.
`hasTimePicker` | Флаг, определяющий нужно ли показывать время в элементе управления и указатель времени внутри указателя даты.
`dateTimeFormat` | Определяет формат даты.
`minDate` | Определяет самую раннюю дату, которую пользователь может выбрать.
`maxDate` | Определяет самую последнюю дату, которую пользователь может выбрать.
`drops` | Определяет открывается календарь ниже («вниз» по умолчанию) или выше («вверх») элемента, к которому он прикреплен.
`type` | Определяет вид компонента.

### flexberry-simpledatetime

flexberry-simpledatetime - компонент для отображения даты/даты и времени.

![](/images/pages/products/flexberry-ember/components/flexberry-simpledatetime.png)

Чтобы добавить flexberry-simpledatetime на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-simpledatetime
  type=type
  removeButton=removeButton
  value=model.date
  min=min
  max=max
  readonly=readonly
}}{% endraw %}
```

#### Свойства flexberry-simpledatetime

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`placeholder` | Определяет placeholder.
`classNames` | Определяет css-class на компонент.
`defaultMinute` | Определяет значение минут по умолчанию.
`defaultHour` | Определяет значение часов по умолчанию.
`useBrowserInput` | Флаг, определяющий нужно ли использовать импут браузера
`min` | Определяет самую раннюю дату, которую пользователь может выбрать.
`max` | Определяет самую последнюю дату, которую пользователь может выбрать.
`type` | Определяет вид компонента.
`canClick` | Флаг, определяющий нужно ли onClick вызывать flatpickr.open()
`removeButton` | Флаг, определяющий, отображать ли кнопку удаления у flatpickr
`locale` | Определяет локали (Если значение не определено, используется языковой стандарт приложения).

## Компоненты для отображения логических полей

### flexberry-checkbox

flexberry-checkbox - компонент для отображения логических данных.

![](/images/pages/products/flexberry-ember/components/flexberry-checkbox.png)

Чтобы добавить flexberry-checkbox на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-checkbox
  value=model.flag
  label=label
  readonly=readonly
  class=class
}}{% endraw %}
```

#### Свойства flexberry-checkbox

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`classNames` | Определяет css-class на компонент.
`label` | Определяет label.

### flexberry-ddau-checkbox

flexberry-ddau-checkbox - компонент для отображения логических данных.

![](/images/pages/products/flexberry-ember/components/flexberry-ddau-checkbox.png)

Чтобы добавить flexberry-ddau-checkbox на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-ddau-checkbox
  class=class
  value=model.flag
  caption=caption
  readonly=readonly
  change=(action "onCheckboxChange" "model.flag")
}}{% endraw %}
```

#### Свойства flexberry-ddau-checkbox

Свойство | Краткое описание
:--------|:----------------
`readonly` | Определяет, доступен ли объект только для чтения. По умолчанию данное свойство имеет значение `false`.
`value` | Определяет значение, которое будет отображаться.
`classNames` | Определяет css-class на компонент.
`caption` | Определяет заголовок компонента.

## Компоненты для отображения перечисления

### flexberry-dropdown

flexberry-dropdown - компонент для отображения перечисления.

![](/images/pages/products/flexberry-ember/components/flexberry-dropdown.png)

Чтобы добавить flexberry-dropdown на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-dropdown
  items=(flexberry-enum "components-examples/flexberry-dropdown/settings-example/enumeration")
  value=model.enumeration
  placeholder=placeholder
  readonly=readonly
  class=class
}}{% endraw %}
```

#### Свойства flexberry-dropdown

Свойство | Краткое описание
:--------|:----------------
Свойства `componentName` и `readonly` | Свойства из [базового контрола](ef2_base-component.html).
`value` | Определяет значение, которое будет отображаться.
`class` | Определяет css-class на компонент.
`items` | Определяет перечисление.
`placeholder` | Определяет placeholder.
`appConfigSettingsPath` | Определяет путь к настройкам компонента в конфигурации приложения.
`isSearch` | Режим автокомплита в dropdown-е.

## Компоненты для отображения JSON

### flexberry-jsonarea

flexberry-jsonarea - компонент для отображения JSON.

![](/images/pages/products/flexberry-ember/components/flexberry-jsonarea.png)

Чтобы добавить flexberry-jsonarea на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-jsonarea
  value=model.json
  placeholder=placeholder
  readonly=readonly
  classNames=classNames
}}{% endraw %}
```

#### Свойства flexberry-jsonarea

Свойство | Краткое описание
:--------|:----------------
`readonly` | Определяет, доступен ли объект только для чтения. По умолчанию данное свойство имеет значение `false`.
`value` | Определяет значение, которое будет отображаться.
`classNames` | Определяет css-class на компонент.
`placeholder` | Определяет placeholder.

## Компоненты для управления формой:

### flexberry-button

flexberry-button - компонент для создания на веб-странице кнопки.

![](/images/pages/products/flexberry-ember/components/flexberry-button.png)

Чтобы добавить flexberry-button на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-button
  class=class
  iconClass=class
  caption=caption
  tooltip=tooltip
  readonly=readonly
  click=(action "onButtonClick")
}}{% endraw %}
```

#### Свойства flexberry-button

Свойство | Краткое описание
:--------|:----------------
`readonly` | Определяет, доступен ли объект только для чтения. По умолчанию данное свойство имеет значение `false`.
`classNames` | Определяет css-class на компонент.
`caption` | Определяет заголовок компонента.
`tooltip` | Определяет текст подсказки компонента.
`iconClass` |Определяет css-class для иконки компонент.

### flexberry-ddau-slider

flexberry-ddau-slider - компонент для создания на веб-странице ползунка.

![](/images/pages/products/flexberry-ember/components/flexberry-ddau-slider.png)

Чтобы добавить flexberry-ddau-slider на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-ddau-slider
  classNames=classNames
  value=model.opacity
  min=min
  max=max
  defaultValue=defaultValue
  step=step
  readonly=readonly
  change=(action "onOpacitySliderChange")
}}{% endraw %}
```

#### Свойства flexberry-ddau-slider

Свойство | Краткое описание
:--------|:----------------
`readonly` | Определяет, доступен ли объект только для чтения. По умолчанию данное свойство имеет значение `false`.
`classNames` | Определяет css-class на компонент.
`value` | Определяет значение, которое будет отображаться.
`min` | Определяет минимальное значение компонента.
`max` | Определяет максимальное значение компонента.
`step` | Определяет шаг диапазона компонента.
`defaultValue` | Определяет начальное значение компонента.

### flexberry-colorpicker

flexberry-colorpicker - компонент для выбора цветов на веб-странице.

![](/images/pages/products/flexberry-ember/components/flexberry-colorpicker.png)

Чтобы добавить flexberry-colorpicker на страницу, в шаблоне нужно указать:

```hbs
{% raw %}{{flexberry-colorpicker
  value=value
  class=class
  change=(action "onGradientColorEndChange")
}}{% endraw %}
```

#### Свойства flexberry-colorpicker

Свойство | Краткое описание
:--------|:----------------
`classNames` | Определяет css-class на компонент.
`value` | Определяет значение, которое будет отображаться.
