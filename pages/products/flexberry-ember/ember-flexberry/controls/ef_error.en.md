---
title: Displaying errors in the application
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef_error.html
lang: en
summary: Правила использования в приложении
---

`Flexberry-error` - компонент который отображает в «дружелюбном» для пользователя виде сообщение об ошибке.

По умолчанию, для отображения сообщений об ошибка используется модальное окно, это поведение можно изменить указав компоненту параметр `modal` со значением `false`: 

```hbs
{% raw %}
{{flexberry-error
    error=error
    modal=false}}
{% endraw %}
```

в этом случае для отображения ошибок будут использоваться компоненты [{{ui-message}}](ef_ui-message.html) [semantic-ui](http://semantic-ui.com/collections/message.html).

Если в объекте ошибки есть свойство «messageLocaleKey» то для его отображения используется «t»-хелпер, т.е. можно добавить локализации для этих сообщений.
Если в объекте ошибки есть свойство «errors», подобное массиву, компонент перебирает его элементы и отображает все в компоненте {{flexberry-toggler}}.
