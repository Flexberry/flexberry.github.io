---
title: Flexberry toggler
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_flexberry-toggler.html
lang: en
summary: Описание, свойства и пример использования
---

## Описание

### Список свойств

Название свойства | Краткое описание
:-----------------|:------------------
`expanded` | Текущее состояние видимости: равернут элемент или нет
`caption` | Общий заголовок в заголовке компонента
`expandedCaption` | Заголовок компонента для развернутого состояния
`collapsedCaption` | Заголовок компонента для свернутого состояния
`currentCaption` | Текущий заголовок компонента
`iconClass` | Иконка компонента
`hasResizableOLV` | Флаг указывает, когда toogler содержит изменяемый размер OLV
`duration` Продолжительность в миллисекундах открытия анимации. При установленном `0` анимация отключена. _Важно_: используется только при первоначальном рендеринге.

### Пример использования

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

При загрузке страницы компонент находится в развернутом состоянии, заголовок для развернутого сосостояния `Expanded caption`, а для свернутого - `Collapsed caption`.

### Пользовательские настройки toggler

Состояние `toggler` может быть сохранено в [пользовательских настройках](ef_model-user-settings-service.html) приложения.
