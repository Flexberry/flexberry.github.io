---
title: Отображение ember-формы без дополнительных элементов
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_show-ember-form-in-frame.html
folder: products/ember-flexberry/forms/
lang: ru
summary: Данная возможность позволяет отображать внутри фрейма исключительно ember-форму без меню и других дополнительных элементов.
---

## Описание

Если по какой-то причине требуется отобразить «голую» форму (то есть без меню сайта и других дополнительных элементов; например, внутри фрейма), то можно воспользоваться следующим способом:

1. В [контроллере `application.js`](ef2_controller.html) задаём [считывание параметра `inframe` Get-запроса](http://guides.emberjs.com/v2.4.0/routing/query-params/), создаём свойство `shouldShowInFrame`, определяющее, что заданный параметр имеет значение `true`:

```javascript
// ...

export default Ember.Controller.extend({
  queryParams: {
    showinframe: 'inframe'
  },
  showinframe: null,
  shouldShowInFrame: function() {
    var inFrame = this.get('showinframe');
    return inFrame && inFrame.toLowerCase() === 'true';
  }.property('showinframe')
  
//  ...
});
```

2. В шаблоне `application.hbs` с помощью конструкции «&#0123;&#0123;#[unless](http://guides.emberjs.com/v2.4.0/templates/conditionals/) shouldShowInFrame&#0125;&#0125; … &#0123;&#0123;/unless&#0125;&#0125;» задать фрагменты, которые не нужно отображать на «голой» форме.

Например, ниже для отображения оставлена только сама форма, все остальные элементы скрываются.

```hbs
{% raw %}{{#unless shouldShowInFrame}}
	<div class="ui grid page menu">
	  <a class="brand item" href="#">Flexberry prototype written in Ember.js</a>
	  <div class="right menu">
		{{#if session.isAuthenticated}}
		  <div class="item">
			<i class="user icon"></i>
			{{session.secure.userName}}
		  </div>
			<a {{ action 'invalidateSession' }} class="item">Sign out</a>
		{{else}}
		  <div class="item">
			{{#link-to 'login' class="ui primary button"}}Sign in{{/link-to}}
		  </div>
		{{/if}}
	  </div>
	</div>
{{/unless}}
<div class="ui grid page">
	{{#unless shouldShowInFrame}}
	  <div class="four wide column">
		{{render "sitemap" sitemap}}
	  </div>
	{{/unless}}
  <div class="twelve wide column">
    {{outlet}}
		{{outlet 'modal'}}
  </div>
</div>{% endraw %}
```

3. При вызове формы добавить параметр «inframe=true».

{% include note.html content="
Важно:

* Реализация не чувствительна к регистру значения `true`.
* Реализация чувствительна к регистру параметра `inframe`.
* При передаче в качестве значения `inframe` значения, отличного от `true`, данная логика отрабатывать не будет." 
%}

![](/images/pages/img/page/ShowEmberFormInFrame/EmptyEmberForm.png)

![](/images/pages/img/page/ShowEmberFormInFrame/FullEmberForm.png)
