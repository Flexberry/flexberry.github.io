---
title: Display of errors in the application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_error.html
lang: en
autotranslated: true
hash: 23301bcd502e3b6370a31f2527ec6dc44baa31e8795f30e935f2b496aa612bf7
summary: Display flexberry-error, the use of t-helpers (localization)
---

[flexberry-error](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js) is a component which displays in the» «friendly user error message.

[Umolchaniu](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js#L8)to display error messages to the modal window, this behavior can be changed by specifying a component parameter with a value of `modal` `false`:

```hbs
{% raw %}
{{flexberry-error
    error=error
    modal=false}}
{% endraw %}
```

In this case, the display error will be used the components of [ui-message](ef2_ui-message.html).

If the error object has a property» «messageLocaleKey then to display it use» «t-helper, ie you can add localizations for these messages.
If the error object has a property» «errors like array, the component loops through its elements and displays all component `flexberry-toggler`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}