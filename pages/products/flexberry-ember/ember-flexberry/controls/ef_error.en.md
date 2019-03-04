--- 
title: Display of errors in the application 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_error.html 
lang: en 
autotranslated: true 
hash: 7629110fdf0be5f46d41faf27dfd6aaf57a304b65cde388341bad7b247d06bc9 
summary: Display flexberry-error, the use of t-helpers (localization) 
--- 

[flexberry-error](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js) is a component which displays in `дружелюбном` user error message. 

[Umolchaniu](https://github.com/Flexberry/ember-flexberry/blob/master/addon/components/flexberry-error.js#L8)to display error messages to the modal window, this behavior can be changed by specifying a component parameter with a value of `modal` `false`: 

```hbs
{% raw %}
{{flexberry-error
    error=error
    modal=false}}
{% endraw %}
``` 

In this case, the display error will be used the components of [ui-message](ef_ui-message.html). 

If the error object has a property `messageLocaleKey` then to display it use `t`-helper, ie you can add localizations for these messages. 
If the error object has a property `errors` array-like, component iterates through its elements and displays all component `flexberry-toggler`. 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/