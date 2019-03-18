---
title: Components of ember-flexberry
keywords: ember
sidebar: flexberry-ember_sidebar
toc: false
permalink: en/fe_base-component.html
lang: en
autotranslated: true
hash: 81d67e3b6780340fc2cbca900b744bf91c0615780f8b4ae97f424d57f1b01bc2
summary: List of components used in ember-flexberry
---

Components is a family of APIs for describing new DOM elements that are suitable for reuse. [Module `ember-flexberry`](ef_landing_page.html) includes a large variety of components for different types of tasks.

## A core feature of ember-flexberry

For ease of development and maintenance, the overall logic of the components were made in base component ([`flexberry-base-component`](https://github.com/Flexberry/ember-flexberry/blob/develop/addon/components/flexberry-base-component.js)). Almost all of the components [module `ember-flexberry`](ef_landing_page.html) inherit from it.

### The list of properties of the base component

Property | Description | Default value
:--------------|:-----------------------------------------------------------|:-------------
`readonly` | Flag: specifies whether the component is read-only. | false
`required` | Flag: specifies whether component. | false
`componentName` | Defines a unique name for the component. |
`dynamicProperties` | Determines the dynamic properties of the component | null
`relatedModel` | Specifies the model to which the value of the current component. | null
`appConfigSettingsPath` | Determines path to the component settings in the application configuration. | 'APP.components.flexberryBaseComponent'
`appConfig` | Defines the application configuration | null
`appConfigSettings` | single object component settings from the application configuration | null
`currentController` | Determines the current controller. | null

### The use of the base component

The base component is not a control and cannot be defined in templates. It is only used as a "Parent" for other components.

It is recommended to inherit from the base component in the following cases:

* When you create an embedded component (can be defined [inside cells lists using `getCellComponent`](https://flexberry.github.io/ru/ef_object-list-view.html#вычислимые-свойства-в-getcellcomponent))
* The need for a controller of the form inside the component.

In other cases, use the base component as a "Parent" or not depends on the specific situation.

## The list of components available in ember-flexberry

* Components for operation and data display
* List of objects:
* [flexberry-objectlistview](fe_object-list-view.html)
* flexberry-simpleolv
* [flexberry-groupedit](fe_groupedit.html)
* Text field:
* [flexberry-textarea](fe_edit-form-components.html#flexberry-textarea)
* [flexberry-textbox](fe_edit-form-components.html#flexberry-textbox)
* [flexberry-field](fe_edit-form-components.html#flexberry-field)
* Date:
* [flexberry-datepicker](fe_edit-form-components.html#flexberry-datepicker)
* [flexberry-simpledatetime](fe_edit-form-components.html#flexberry-simpledatetime)
* Artisan field:
* [flexberry-lookup](fe_lookup-component.html)
* Logichnogo fields:
* [flexberry-checkbox](fe_edit-form-components.html#flexberry-checkbox)
* [flexberry-ddau-checkbox](fe_edit-form-components.html#flexberry-ddau-checkbox)
* File:
* [flexberry-file](ef_file.html)
* Enumeration:
* [flexberry-dropdown](fe_edit-form-components.html#flexberry-dropdown)
* JSON:
* [flexberry-jsonarea](fe_edit-form-components.html#flexberry-jsonarea)
* flexberry-tree
* Components of information visualization:
* flexberry-validationmessage
* flexberry-validationsummary
* [ui-message](ef_ui-message.html)
* flexberry-icon
* [flexberry-error](ef_error.html)
* flexberry-tab-bar
* flexberry-menu
* [flexberry-toggler](ef_flexberry-toggler.html)
* flexberry-dialog
* Control components:
* [flexberry-button](fe_edit-form-components.html#flexberry-button)
* [flexberry-colorpicker](fe_edit-form-components.html#flexberry-colorpicker)
* [flexberry-ddau-slider](fe_edit-form-components.html#flexberry-ddau-slider)
* form-load-time-tracker
* modal-dialog



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}