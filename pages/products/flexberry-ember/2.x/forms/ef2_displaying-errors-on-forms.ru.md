---
title: Отображение ошибок на формах
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember, errors
toc: true
permalink: ru/ef2_displaying-errors-on-forms.html
folder: products/ember-flexberry/forms/
lang: ru
summary: Управление отображением ошибок в списковых формах и формах редактирования.
---

## Настройка отображения ошибок в формах

Для того что бы изменить поведение по умолчанию, необходимо переопределить действие `error` в контроллере соответствующей формы. Единственным параметром этого метода будет объект ошибки, который необходимо установить в свойство, связанное с ошибкой в компоненте `flexberry-error` (по умолчанию `error`).

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

## Настройка создания ошибок в адаптере

Для того что бы изменить поведение по умолчанию, необходимо переопределить метод `handleResponse` в адаптере, в котором можно создавать ошибки преобразуя ответ сервера в понятные пользователю сообщения.

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
