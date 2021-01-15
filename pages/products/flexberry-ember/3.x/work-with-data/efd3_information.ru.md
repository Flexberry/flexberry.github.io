---
title: Вспомогательный класс Information
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_information.html
lang: ru
summary: Вспомогательный класс Information для работы с метаданными модели.
---

## Метаданные модели и вспомогательный класс Information
Вспомогательный класс [Information](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js) из аддона `ember-flexberry-data` позволяет получать метаданные [модели](efd3_model.html). Список доступных методов можно посмотреть в [автодокументации](http://flexberry.github.io/ember-flexberry-data/autodoc/develop/classes/Utils.Information.html#index).

Ниже представлен пример использования [Information](https://github.com/Flexberry/ember-flexberry-data/blob/develop/addon/utils/information.js): в [роуте](efd3_route.html) формы создания определяется тип поля `stringField`, и соответствующее значение записывается в поле `stringField` созданной [модели](efd3_model.html).

```js
import EditFormNewRoute from 'ember-flexberry/routes/edit-form-new';

// Экспорт вспомогательного класса для последующей работы с ним.
import Information from 'ember-flexberry-data/utils/information';

export default EditFormNewRoute.extend({
  modelProjection: 'Child1E',
  modelName: 'i-i-s-gen-test-child1',
  templateName: 'i-i-s-gen-test-child1-e',

  afterModel(resolvedModel, transition){
    this._super(...arguments);

    // Создание экземпляра класса Information. Во Flexberry-эмбер приложениях в роутах и контроллерах инжектится сервис store и явным образом его получать не требуется.
    let information = new Information(this.get('store'));

    // Получение типа поля 'stringField' созданной модели.
    let fieldType = information.getType(this.get('modelName'), 'stringField');

    // Задание типа поля в качестве значения 'stringField'.
    resolvedModel.set('stringField', fieldType);
  }
});
```