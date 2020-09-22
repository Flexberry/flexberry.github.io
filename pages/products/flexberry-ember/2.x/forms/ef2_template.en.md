---
title: Templates in ember-flexberry applications
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_template.html
lang: en
autotranslated: true
hash: aeedd1e05ce48f432cf5bf2ee25b961bbf70fd9ab3260b5ea9faee9969ffa50e
summary: Presents basic information about the structure of the handlebars templates in ember-flexberry applications
---

General information on how to create templates, see [Ember's documentation](https://guides.emberjs.com/v2.4.0/templates/handlebars-basics/).

Templates are located in the folder `templates`.

## The application template

A typical template (template) for the Ember application `application.js`:

```hbs
{% raw %}<div class="ui page grid menu">
  <a class="brand item" href="#">Flexberry prototype written in Ember.js</a>
</div>
<div class="ui page grid">
  <div class="four wide column">
    {{render "sitemap" sitemap}}
  </div>
  <div class="twelve wide column">
    {{outlet}}
    {{outlet 'modal'}}
  </div>
</div>{% endraw %}
```

The controller application needs to be determined structure *sitemap* for displaying in the menu of the site (as is done [here](ef2_controller.html)).

* Pstrfoutlet` rendered templates relevant to the current route parameters.
* Pstrfoutlet 'modal'` being rendered in a modal window.

## Form templates

On templates [forms](ef2_forms.html) added various [controls](ef2_controls.html) can be [added validation](efd2_model-validation.html).

Templates [list of forms](ef2_forms.html) is usually added [Flexberry Objectlistview](ef2_object-list-view.html).
On templates [forms creating and editing](ef2_edit-form.html) - [controls for editing](ef2_controls.html).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}