---
title: Отображение ошибок в приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_error.html
lang: ru
summary: Отображение flexberry-error, использование t-хелперов (локализации)
---

[flexberry-error](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js) - компонент который отображает в «дружелюбном» для пользователя виде сообщение об ошибке.

[По умолчанию](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js#L8), для отображения сообщений об ошибках используется модальное окно, это поведение можно изменить, указав компоненту параметр `modal` со значением `false`: 

```hbs
{% raw %}
{{flexberry-error
    error=error
    modal=false}}
{% endraw %}
```

В этом случае для отображения ошибок будут использоваться компоненты [ui-message](ef2_ui-message.html).

Если в объекте ошибки есть свойство «messageLocaleKey» то для его отображения используется «t»-хелпер, т.е. можно добавить локализации для этих сообщений.
Если в объекте ошибки есть свойство «errors», подобное массиву, компонент перебирает его элементы и отображает все в компоненте `flexberry-toggler`.
