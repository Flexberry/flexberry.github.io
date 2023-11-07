---
title: Использование пользовательских типов данных
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, transform, type, тип
toc: true
permalink: ru/gpg_custom-attribute-type.html
lang: ru
---

Пользовательские типы данных для атрибутов объектов описываются в папке `app\transforms` и являются расширением `DS.Transform`.

Обязательны для указания базовые методы преобразования `serialize` и `deserialize`.
{% highlight javascript%}
{% raw %}
// app/transforms/ember-flexberry-dummy-text.js
import DS from 'ember-data';

let FlexberryTextTransform = DS.Transform.extend({
  deserialize: function(serialized) {
    return isNone(serialized) ? null : String(serialized);
  },

  serialize: function(deserialized) {
    return isNone(deserialized) ? null : String(deserialized);
  },
});

export default FlexberryTextTransform;
{% endraw %}
{% endhighlight %}

Далее указываются методы которые используются в технологии, на списковых формах и запросах данных к бекенду. Все методы указываются внутри **reopenClass**:
* **conditionsForFilter**: перечень условий для списковой формы. Возращает JSON;

{% highlight javascript%}
{% raw %}
/**
  Builds conditions for filter

  @example
    ```javascript
    conditionsForFilter()
    ```

  @method conditionsForFilter
  @return JSON conditions for filter.
*/
conditionsForFilter() {
  return {
    'eq': 'Text equal',
    'neq': 'Text not equal',
    'like': 'Text like',
    'nlike': 'Text not like',
    'hasAttr': 'Text has attribute',
  };
}
{% endraw %}
{% endhighlight %}

* **predicateForFilter**: сформировать ограничение по фильтру. Принимает параметр **filter**, содержащий атрибуты **name, condition, pattern**. Имя, условие и значение соответственно. Возвращает предикат, то ограничение которое применится при указании соответствующего фильтра.

{% highlight javascript%}
{% raw %}
/**
  Builds predicate for filter

  @example
    ```javascript
    predicateForFilter(filter)
    ```

  @method predicateForFilter
  @param {Object} filter Object (`{ name, condition, pattern }`) with parameters for filter.
  @return {BasePredicate|null} Predicate to filter through.
*/
predicateForFilter(filter) {
  if (filter.condition) {
    switch (filter.condition) {
      case 'like':
        return new StringPredicate(filter.name).contains(filter.pattern || '');
      case 'nlike':
        return new NotPredicate(new StringPredicate(filter.name).contains(filter.pattern || ''));
      case 'hasAttr':
          return new StringPredicate(filter.name).contains('"' + filter.pattern + '":' || '');
      default:
        return new SimplePredicate(filter.name, filter.condition, filter.pattern || null);
    }
  }

  return null;
}
{% endraw %}
{% endhighlight %}

* **componentForFilter**: компонент который будет использоваться для ввода значения фильтрации. Возвращает JSON с параметрами **name** и **properties**.
{% highlight javascript%}
{% raw %}
/**
  Builds component for filter

  @example
    ```javascript
    componentForFilter()
    ```

  @method componentForFilter
  @return JSON component for filter.
*/
componentForFilter() {
  return { name: 'flexberry-textbox', properties: { class: 'compact fluid' } };
}
{% endraw %}
{% endhighlight %}

* **getOdataValue**: Получить значение для ограничения OData. Используется при запросе к бекенду.

{% highlight javascript%}
{% raw %}
/**
  Get OData value

  @example
    ```javascript
    getOdataValue(predicateValue)
    ```

  @method getOdataValue
  @param {Object} predicateValue Raw object value.
  @return {String} OData value.
*/
getOdataValue(predicateValue) {
  return `'${String(predicateValue).replace(/'/g, `''`)}'`;
}
{% endraw %}
{% endhighlight %}

## OData
Если в одата еще не добавлен функционал по использованию getOdataValue, то необходимо добавить инициализатор с переопределением функции `_processConstForODataSimplePredicateByType`

{% highlight javascript%}
{% raw %}
//app/initializers/odata-adapter-custom.js
import { isNone } from '@ember/utils';
import Application from '@ember/application';
import ODataAdapter from 'ember-flexberry-data/query/odata-adapter';
import getSerializedDateValue from 'ember-flexberry-data/utils/get-serialized-date-value';

export function initialize() {
  ODataAdapter.prototype._processConstForODataSimplePredicateByType = function (predicate, predicateValue, valueType) {
    let appContainer = Application.NAMESPACES.find(x => x.backendUrl && x.__container__).__container__;
    let transformInstance = appContainer.lookup('transform:' + valueType);
    let transformClass = !isNone(transformInstance) ? transformInstance.constructor : null;

    if (transformClass && transformClass.getOdataValue) {
      return transformClass.getOdataValue(predicateValue);
    }

    return valueType === 'string'
      ? `'${String(predicateValue).replace(/'/g, `''`)}'`
      : ((valueType === 'date' || (valueType === 'object' && predicateValue instanceof Date))
        ? getSerializedDateValue.call(this._store, predicateValue, predicate.timeless)
        : predicateValue);
  }
}

export default {
  name: 'odata-adapter-custom',
  initialize
};
{% endraw %}
{% endhighlight %}

## Перейти

* [Пример списка с пользовательским типом на тестовом стенде](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/components-examples/flexberry-objectlistview/custom-filter) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Пример описания пользовательского типа](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/transforms/ember-flexberry-dummy-text.js) <i class="fa fa-arrow-left" aria-hidden="true"></i>