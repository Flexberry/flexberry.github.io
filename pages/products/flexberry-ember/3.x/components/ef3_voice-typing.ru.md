---
title: Режим голосового ввода
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, voice typing
toc: true
permalink: ru/ef3_voice-typing.html
lang: ru
summary: Обзор возможностей, голосового контрола.
---

## Режим голосового ввода

В компоненты [ember-flexberry](https://flexberry.github.io/ru/ef3_landing_page.html) добавлена возможность включить голосовой ввод.
Этот режим доступен в сл компонентах:

* flexberry-field
* flexberry-textarea
* flexberry-textbox
* [flexberry-objectlistview](https://flexberry.github.io/ru/efd3_object-list-view.html) в фильтрах и поиске

Голосовой ввод осуществляется с помощью Web Speech API через интерфейс [SpeechRecognition](https://developer.mozilla.org/en-US/docs/Web/API/SpeechRecognition).

## Включение голосового ввода

Для включения голосового режима во всем приложение сразу нужно в `environment.js` включить настройку `APP.useVoiceTypingInAllControls`

```javascript
APP: {
  ...
  // Flag: indicates whether to use voice typing in all controls or not.
  useVoiceTypingInAllControls: true,

  ...
}
```
### Точечное включение голосового ввода в компонентах

Чтобы использовать режим голосового ввода не обязательно его включать в `environment.js` для всех компонентов, можно его включить точечно только в нужных (или отключить в тех где он не нужен при включеной настройке `APP.useVoiceTypingInAllControls`).

Для этого надо в шаблоне соответственного компонента нужно добавить свойство `isUseVoiceTyping`:

```hbs
{% raw %}{{flexberry-objectlistview
...
isUseVoiceTyping=true
}}{% endraw %}
```
