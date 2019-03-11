--- 
title: Display of errors in the application 
sidebar: flexberry-ember_sidebar 
keywords: Flexberry Ember 
toc: true 
permalink: en/ef_error.html 
lang: en 
autotranslated: true 
hash: 64998e6856d405a528e4a8f2693aef566e45921a6db505f81cbf0255de1acd8b 
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

In this case, the display error will be used the components of [ui-message](ef_ui-message.html). 

If the error object has a property» «messageLocaleKey then to display it use» «t-helper, ie you can add localizations for these messages. 
If the error object has a property» «errors like array, the component loops through its elements and displays all component `flexberry-toggler`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}
