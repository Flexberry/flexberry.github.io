---
title: Валидатор уникальности по нескольким атрибутам
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, валдация, validation, ember-cp-validations, unique-attributes
toc: true
permalink: ru/gpg_unique-attributes-validator.html
lang: ru
---

Валидатор уникальности по нескольким атрибутам является [кастомным валидатором](gpg_custom-validation.html), который проверяет уникальность настраиваемого объекта в хранилище данных по нескольким атрибутам. Проверяемым атрибутом автоматически становится тот атрибут, для которого настраивается валидатор, а так же все указанные связанные поля.

![Пример валидатора по нескольким атрибутам](/images/pages/guides/flexberry-ember/5-5-unique-attributes-validator/5-5-1.png)

Валидатор имеет уникальное наименование `unique-attributes`. Для указания в кастомном валидаторе используется `uniqueAttributes`.

Настройка валидации в аддоне `ember-flexberry` версий **3.x.x** осуществляется при помощи аддона **[ember-cp-validations](http://offirgolan.github.io/ember-cp-validations/docs/modules/Usage.html)** (для версий **2.x.x** используется аддон [ember-validations](https://github.com/DavyJonesLocker/ember-validations)). Он не только предоставляет определенный набор готовых валидаторов, но и также позволяет писать кастомные.

Пример настройки валидации уникальности для двух полей **text+date** в модели **ember-flexberry-dummy-suggestion** тестовго стенда:

{% highlight javascript%}
{% raw %}
// app\models\integration-examples\edit-form\validation\base.js
import DS from 'ember-data';
import EmberFlexberryDataModel from 'ember-flexberry-data/models/model';
import { attr, belongsTo, hasMany } from 'ember-flexberry-data/utils/attributes';
import { validator, buildValidations } from 'ember-cp-validations';

const Validations = buildValidations({
  //...

  text: {
    validators: [
      validator('uniqueAttributes', {
        secondProperty: 'date',
        modelName: 'ember-flexberry-dummy-suggestion',
        view: 'SuggestionL',
        message: 'Combination of attributes (Text, Date) are not unique',
        debounce: 300
      }),
    ],
  },

  //...
});

{% endraw %}
{% endhighlight %}

Свойства валидатора:
- **modelName**: Имя модели, в которой будут проверятся атрибуты;
- **view**: Представление, по которому будет происходить проверка наличия объекта в хранилище;
- **secondProperty**: Дополнительное свойство для проекрки уникальности;
- **message**: Сообщение валидации;
- **debounce**: Задержка в милисекундах между повторными проверками. Это необходимо, когда есть текстовое поле и пользователь быстро вводит текст. Чтобы не перегружать и не отправлялось множество запросов к хранилищу данных, необходимо указать задержку для проверки валидации.

Если не указывать атрибут `modelName`, то он автоматически установиться в имя модели текущего проверяемого объекта.

Указание `modelName` добавлено с целью возможности проверять наличие уникальности комбинации полей в другом объекте, отличном от того что редактируется на текущей форме.

Для срабатывания валидатора в примере на тестовом стенде, необходимо добавить строку в таблицу *suggestion* с необходимыми данными:

```sql
INSERT INTO public.suggestion(
	primarykey, 
	createtime, creator, edittime, editor, 
	address, text, 
	date, votes, moderated, 
	type, 
	author, 
	editor1)
	VALUES ('87fe604d-92cd-4cce-896b-e3d62ab566da', 
			NULL, NULL, NULL, NULL, 
			'addr', '12345', 
			TO_DATE('20230703','YYYYMMDD'), 0, false, 
			'1a5a5fcb-2ad1-4554-8fbc-cd33e51308da', 
			'01c157a0-aa74-411d-96d3-8f877dbab853', 
			'01c157a0-aa74-411d-96d3-8f877dbab853');
```

Когда поменяем текст или дату в нашем примере, на те что нет в хранилище данных, то сообщение валидации исчезает:
![Валидация прошла](/images/pages/guides/flexberry-ember/5-5-unique-attributes-validator/5-5-2.png)

Для проверки значений полей на уникальность используются стандартные возможности хранилища данных.

Пример:
{% highlight javascript%}
{% raw %}
let limitPredicate = new ComplexPredicate(Condition.And,
    new SimplePredicate(attribute, FilterOperator.Eq, value),
    new SimplePredicate(secondProp, FilterOperator.Eq, secondValue));
let builder = new QueryBuilder(thisStore)
    .from(modelName)
    .selectByProjection(options.view)
    .where(limitPredicate);
{% endraw %}
{% endhighlight %}

Вот почему для работы этого валидатора не тредуется дополнительных доработок со сторны бекенда.

## Перейти

* [Настройка кастомной валидации](gpg_custom-validation.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>
* [Особые состояния с валидацией](uiuxg_input_fields.html#%D0%BE%D1%81%D0%BE%D0%B1%D1%8B%D0%B5-%D1%81%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D1%8F-%D1%81-%D0%B2%D0%B0%D0%BB%D0%B8%D0%B4%D0%B0%D1%86%D0%B8%D0%B5%D0%B9) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Пример валидации на тестовом стенде](https://flexberry.github.io/ember-flexberry/dummy/dummy-test-3/#/integration-examples/edit-form/validation) <i class="fa fa-arrow-right" aria-hidden="true"></i>