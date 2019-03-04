--- 
title: Display errors on the forms 
sidebar: ember-flexberry_sidebar 
keywords: Flexberry Ember, errors 
toc: true 
permalink: en/ef_displaying-errors-on-forms.html 
folder: products/ember-flexberry/forms/ 
lang: en 
autotranslated: true 
hash: 38b030c3c4a6f0b3441b5aa125c37f379dfa1e16bd54a762255b0ee91623ea20 
summary: control the display of errors in list forms and edit forms. 
--- 

## customize the display of errors in forms 

In order to change the default behavior, you must override the action controller `error` in the appropriate form. The only parameter of this method will be the error object, which must be set to the property associated with the error in the component `flexberry-error` (default `error`). 

```javascript
import Ember from 'ember';
import DS from 'ember-data';

export default Ember.Controller.extend({
  actions: {
    error(error) {
      if (error instanceof DS.AdapterError) {
        error = new Error('При обработке запроса произошла ошибка.');
        this.set('error', error);
      }
    },
  },
});
``` 

## the setting error in the adapter 

In order to change the default behavior, you must override the method `handleResponse` in the adapter, in which you can create error converting the response from the server in a user-friendly message. 

```javascript
import DS from 'ember-data';
import SilentError from '../errors/silent-error';

export default DS.RESTAdapter.extend({
  handleResponse(status, headers, payload) {
    if (!this.isSuccess(status, headers, payload)) {
      if (this.analyzeErrorResponse(status, headers, payload)) {
        return new SilentError();
      } else {
        return new DS.AdapterError(payload.error);
      }
    }

    return this._super(...arguments);
  },
});
``` 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/