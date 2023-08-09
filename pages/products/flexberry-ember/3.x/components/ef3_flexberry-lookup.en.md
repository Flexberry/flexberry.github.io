---
title: LookUp-s in ember-flexberry applicationи
keywords: ember, lookup, flexberry-lookup
sidebar: flexberry-ember-3_sidebar
toc: false
permalink: en/ef3_flexberry-lookup.html
lang: en
summary: the basic features of LookUp-s
---

## Purpose of the component

## Overview of features and component API

### List of basic properties

Property | Description | Default value
:---------------------|:------------------------------------------------------------|:----------------
`choose` | Specifies the name of the action and that will be happening by clicking on the button 'choose'.|
`remove` | Specifies the name of the action and that will be happening by clicking on the button 'remove'.|
`updateLookupValue` | Specifies the name of the action and that will be happening when lookup value is changed.|
`chooseText` | Defines the text/html inside the button 'choose'.|
`removeText` | Defines the text/html inside the button 'remove'.|
`chooseButtonClass` | Defines css class for button 'choose'.|
`removeButtonClass` | Defines css class for button 'remove'.|
`placeholder` | Defines placeholder | t('flexberry-lookup.placeholder')
`value` | Determines the selected model instance (master object) |
`relatedModel` | Specifies the model for which will be edited the reference to a master object) |
`relationName` | Specifies the name of the relationship |
`projection` | Determines which view will be displayed in the wizard list |
`sizeClass` | Defines css class of the window size options are: small, large, fullscreen | small
`title` | Header modal window |
`lookupLimitPredicate` | Defines function restriction |
`lookupAdditionalLimitFunction` | Additional function ogranicenje for use in GroupEdit relative to the margin line |
`autocomplete` | Mode autocomplete, in the "read-Only" doesn't work | false
`dropdown` | Mode drop-down list in the "read-Only" doesn't work | false
`dropdownIsSearch` | search Mode (autocomplete) for a LookUp-and in the mode drop-down list | false
`displayAttributeName` | the name of the model attribute (properties master), which is displayed to the user |
`sorting` | Direction to sort by the field 'displayAttributeName' mode autocomplete and in the mode drop-down list | 'asc'
`minCharacters` | Minimum number of characters to autocomplete mode autocomplete and in the mode drop-down list | 1
`maxResults` | Maximum number of records displayed in autocomplete mode and in the mode in the mode drop-down list, not required property | 10

### Possibility to override the behavior when the value of the lookup changes.

If additional logic is needed when changing the value of the lookup, it is necessary to override the updateLookupValue action in the controller of the form on which the lookup is located:

```js
  actions: {
    updateLookupValue() {
      // Base logic
      this._super(...arguments);

      // Additional logic
      // ...
    }
  }
```

## Customizing the List of Values Window

## Specifying a limit function

## lookup-events service capabilities

## Dropdown mode

## Setting the default sort

## Lookup with autocomplete
