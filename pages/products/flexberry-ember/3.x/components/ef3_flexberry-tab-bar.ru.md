---
title: Навигация
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef3_flexberry-tab-bar.html
lang: ru
summary: Свойства flexberry-tab-bar, настройка flexberry-tab-bar
---

## Описание

[flexberry-tab-bar](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-tab-bar.js) - компонент, позволяющий организовать и осуществить навигацию между группами контента, которые связаны и находятся на одном уровне. В нем могут быть размещены поля формы, [список](efd3_object-list-view.html), [детейлы](ef3_groupedit.html) и другое.

Общий вид компонента с отображением вкладок в формате с переполнением, в случае, если текущая тема оформления “Ghost”:

![components/flexberry-tab-bar](/images/pages/products/flexberry-ember/3.x/components/flexberry-tab-bar.png)

[Пример с реализацией](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-tab-bar/settings-example) доступен на тестовом стенде.

### Логика работы вкладок в формате с переполнением

Ширина вкладок зависит от длинны названия и меняется в соответствии с ним. Если выкладки не помещаются на экране, то появляется кнопка, которая предлагает пользователю выбрать любую вкладку из списка (если вкладки помещаются на экране, то кнопка скрыта). Также есть возможность прокручивать вкладки, если они не помещаются на экран.

### Список свойств

| Название свойства | Краткое описание |
|:-------------------:|:------------------|
| `items` | Определяет массив вкладок.|
| `isOverflowedTabs` | Флаг, определяющий, отображать ли вкладки в формате с переполнением или в обычном режиме.|

### Пример использования

Настройка `flexberry-tab-bar` осуществляется в [шаблоне страницы](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-tab-bar.js#L1):

```hbs
{% raw %}{{flexberry-tab-bar 
    items=items
    isOverflowedTabs=false
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
