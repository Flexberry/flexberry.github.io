--- 
title: Flexberry toggler 
sidebar: flexberry-ember_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_flexberry-toggler.html 
lang: en 
autotranslated: true 
hash: ee6ab7ba9bfd90e9e0f3b04b6f1a475dd7df0308a69788d340a414a4365c0dd7 
summary: Properties flexberry-toggler, setting flexberry-toggler 
--- 

[flexberry-toggler](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-toggler.js) is a component that allows the user to show or hide embedded in the contents. It can be placed form fields [list](fe_object-list-view.html), [detaily](fe_groupedit.html) and more. 

### a List of properties 

| Property name | Brief description | 
|:-------------------:|:------------------| 
| `expanded` | Current state of visibility: the element is deployed or not| 
| `caption` | Common header component header| 
| `expandedCaption` | Header component for the deployed condition| 
| `collapsedCaption` | Header component for collapsed| 
| `currentCaption` | Current component header| 
| `iconClass` | new component| 
| `hasResizableOLV` | Flag indicates when toogler contains resizable OLV `duration` Duration in milliseconds for the opening animation. When you set `0` animations disabled. Vaino: used only during initial rendering| 

### Example usage 

Setting `flexberry-toggler` is in the [template stranicy](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-toggler.js#L10): 

```hbs
{% raw %}
{{#flexberry-toggler
    expandedCaption='Expanded caption'
    collapsedCaption='Collapsed caption'
    expanded=true
}}
    Content
{{/flexberry-toggler}}
{% endraw %}
``` 

When loading the page the component is in the expanded state, the header for the expanded sosotoyanie `Expanded caption`, and for rolled - `Collapsed caption`. 

### Customizing 

State `flexberry-toggler` can be saved in [user settings](ef_model-user-settings-service.html) applications. 

For using the service user settings component `flexberry-toggler` must contain the following attributes: 

```hbs
{% raw %}{{flexberry-toggler
  ...
  componentName="..."
  ...
}}{% endraw %}
``` 

* `componentName` - a mandatory attribute used to anchor user settings. It must be unique within a single displayed page. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
