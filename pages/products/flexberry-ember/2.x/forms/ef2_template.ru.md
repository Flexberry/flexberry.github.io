---
title: Шаблоны в ember-flexberry-приложениях
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_template.html
lang: ru
summary: Представлена основная информация о структуре handlebars-шаблонов в ember-flexberry-приложениях
---

Общую информацию о том, как создавать шаблоны, можно посмотреть в [документации Ember](https://guides.emberjs.com/v2.4.0/templates/handlebars-basics/).

Шаблоны располагаются в папке `templates`.

## Шаблон приложения

Типичный шаблон (template) для Ember-приложения `application.js`:

```hbs
{% raw %}<div class="ui grid page menu">
  <a class="brand item" href="#">Flexberry prototype written in Ember.js</a>
</div>
<div class="ui grid page">
  <div class="four wide column">
    {{render "sitemap" sitemap}}
  </div>
  <div class="twelve wide column">
    {{outlet}}
    {{outlet 'modal'}}
  </div>
</div>{% endraw %}
```

В контроллере приложения должна быть определена структура *sitemap* для отображении в меню сайта (как это сделано [здесь](ef2_controller.html)).

* В `outlet` рендерятся шаблоны, соответствующие текущему роуту.
* В `outlet 'modal'` рендерится модальное окно.

## Шаблоны форм

На шаблонах [форм](ef2_forms.html) добавляются различные [контролы](ef2_controls.html), может быть [добавлено отображение валидации](efd2_model-validation.html).

В шаблонах [списковых форм](ef2_forms.html) обычно добавлен [Flexberry Objectlistview](ef2_object-list-view.html).
На шаблонах [форм создания и редактирования](ef2_edit-form.html) - [контролы для редактирования](ef2_controls.html).
