---
title: Setting a default value in the ember-flexberry application
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: en/ef2_default-value.html
folder: products/ember-flexberry/forms/
lang: en
autotranslated: true
hash: 92e57d394e70c0c7d061f6a5447cc7f9afa288ab9755a295b8a33de5730915cf
summary: Describes the options for setting the default values in ember-flexberry application.
---

{% include warning.html content="This article is not complete. The proposed article is not the best option." %}

## Description
There are different approaches to setting a default value in the Ember application.

One approach would be to use on the level of [models](efd2_model.html) [`defaultValue`](https://guides.emberjs.com/v2.4.0/models/defining-models/#toc_options).

For example:

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string', { defaultValue: 'Test2602' }),
  birthDate: DS.attr('date', {
    defaultValue() { return new Date(); }
  })
});
```

{% include warning.html content="When you save this defined model, if the property value has not been changed, then the server passed it will not.
So to set default values that will be correctly saved on the server, it is easier to use the approach when the initialization is happening in [the form of](ef2_edit-form.html)." %}

## Assignment default values on the creation form

Job defaults can occur at [the form of](ef2_edit-form.html) in [rout](ef2_route.html) in [afterModel](http://emberjs.com/api/classes/Ember.Route.html#method_afterModel).

For example, you want to the property "dataproject" set the current date.

```javascript
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  ...
  
  afterModel: function(model, transition) {
    var date = new Date();
    model.set('датаПроекта', date);
  }
});
```

A more complex option is when the default value you want to retrieve from the server. For example, you want to specify the current user in the 'registered'. In this case you will need to do an ajax request to the server. Let `GetCurrentUser` server method returns the current user, then the code can be as follows:

```javascript
import Ember from 'ember';
import config from '../../config/environment';
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

export default EditFormNewRoute.extend({
  ...
  
  afterModel: function(model, transition) {
    var store = this.store;

    Ember.$.ajax({
      type: 'GET',
      async: false,
      url: config.APP.backendUrls.api + '/GetCurrentUser', / / "Config.APP.backendUrls.api" the recorded path to the server. 
      success: function(result) {
        if (result) {
          store.pushPayload('some-project-пользователь', result); // First convert the result in a model understand Ember. 
          store.findRecord('some-project-пользователь', result.__PrimaryKey).then(function(person) {
            model.set('зарегистрировал', person); // We find that the resulting model is written to with the desired property. 
          });
        }
      }
    });
  }
});
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}