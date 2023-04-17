---
title: Flexberry tab bar
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef3_flexberry-tab-bar.html
lang: ru
summary: Свойства flexberry-tab-bar, настройка flexberry-tab-bar
---

[flexberry-tab-bar](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-tab-bar.js) - компонент, позволяющий организовать и осуществить навигацию между группами контента, которые связаны и находятся на одном уровне. В нем могут быть размещены поля формы, [список](ef2_object-list-view.html), [детейлы](ef2_groupedit.html) и другое.

### Список свойств

| Название свойства | Краткое описание |
|:-------------------:|:------------------|
| `items` | Определяет массив вкладок.|

### Пример использования

Настройка `flexberry-tab-bar` осуществляется в [шаблоне страницы](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-tab-bar.js#L1):

```hbs
{% raw %}{{flexberry-tab-bar 
    items=items
}}
<div class="ui bottom attached tab active segment" data-tab="tab1">
    Content
</div>
<div class="ui bottom attached tab segment" data-tab="tab2">
    Content
</div>{% endraw %}
```

При загрузке страницы будет активна вкладка компонента, в которой указано свойство `active: true`.
Массив вкладок `items`:
```javascript
[
    { selector: 'tab1', caption: 'Tab №1', active: true },
    { selector: 'tab2', caption: 'Tab №2' },
];
```
