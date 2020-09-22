---
title: Задание значения по умолчанию в ember-flexberry-приложении
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/ef2_default-value.html
folder: products/ember-flexberry/forms/
lang: ru
summary: Описаны варианты задания значений по умолчанию в ember-flexberry-приложении.
---

{% include warning.html content="Данная статья не завершена. Предложенный в статье вариант не является лучшим." %} 

## Описание
Существуют разные подходы к заданию значения по умолчанию в Ember-приложениях.

Один из подходов - использование на уровне [модели](efd2_model.html) [`defaultValue`](https://guides.emberjs.com/v2.4.0/models/defining-models/#toc_options).

Например:

```javascript
var Model = BaseModel.extend({
  firstName: DS.attr('string', { defaultValue: 'Test2602' }),
  birthDate: DS.attr('date', {
    defaultValue() { return new Date(); }
  })
});
```

{% include warning.html content="При сохранении определённой таким образом модели, если значение свойства не было изменено, то на сервер оно передано не будет.
Поэтому для задания значения по умолчанию, которое корректно будет сохраняться на сервере, проще использовать подход, когда инициализация происходит на [форме создания](ef2_edit-form.html)." %} 

## Задание значения по умолчанию на форме создания

Задание значения по умолчанию может происходить на [форме создания](ef2_edit-form.html) в [роуте](ef2_route.html) в [afterModel](http://emberjs.com/api/classes/Ember.Route.html#method_afterModel).

Например, требуется в свойство "датаПроекта" задать текущую дату.

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

Более сложный вариант - это когда значение по умолчанию требуется получить с сервера. Например, требуется указать текущего пользователя в свойство 'зарегистрировал'. В этом случае потребуется делать ajax-запрос на сервер. Пусть серверный метод `GetCurrentUser` возвращает текущего пользователя, тогда код может быть следующим: 

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
      url: config.APP.backendUrls.api + '/GetCurrentUser', // В "config.APP.backendUrls.api" записан путь до сервера.
      success: function(result) {
        if (result) {
          store.pushPayload('some-project-пользователь', result); // Сначала результат преобразуем в модельку, которую поймёт Ember.
          store.findRecord('some-project-пользователь', result.__PrimaryKey).then(function(person) {
            model.set('зарегистрировал', person); // Находим эту полученную модельку и записываем в требуемое свойство.
          });
        }
      }
    });
  }
});
```
