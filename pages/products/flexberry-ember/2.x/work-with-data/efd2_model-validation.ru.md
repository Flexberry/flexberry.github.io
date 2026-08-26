---
title: Валидация модели
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd2_model-validation.html
folder: products/ember-flexberry-data/models-and-projections/
lang: ru
summary: Описаны основные шаги по настройке валидации
---

## Описание

Клиентская валидация настраивается в приложении на базе аддона [Ember Validations](https://github.com/dockyard/ember-validations) (в документации к данному аддону указано больше возможностей по заданию правил).

Валидация задаётся на уровне модели (остальные манипуляции с настройкой компонента зашиты в технологию Flexberry ASP.NET Ember):

```javascript
var Model = BaseModel.extend({
  номерПроекта: DS.attr('string'),
  краткоеСодержание: DS.attr('string'),
  визирование: DS.attr('boolean', {defaultValue: false}),
  примечание: DS.attr('string'),

  // Validation rules.
  validations: {
      номерПроекта: { presence: true },
      краткоеСодержание: { presence: true, length: { maximum: 255 } },
      визирование: { presence: true },
      примечание: { length: { maximum: 255 } },
  }
});
```

Для отображения валидации на форме редактирования в соответствующем шаблоне необходимо написать примерно следующее (стили и расположение элементов может отличаться):

```html
<div class="ui grid `if model.errors.номерПроекта 'error' ''`">
	<div class="ui two wide column right aligned middle aligned content">
		<label>Номер проекта</label>
	</div>
	<div class="ten wide column">
		<span class="flexberry-validation-error-message">`model.errors.номерПроекта`</span>
		`input type="text" placeholder="(no value)" value=model.номерПроекта`
	</div>
</div>
```

{% include note.html content="Поскольку при валидации изначально считается, что у полей логического типа значение `null`, то, чтобы не отображались сообщения валидации, можно задать [значение по умолчанию](http://guides.emberjs.com/v2.4.0/models/defining-models/) (как это сделано в примере выше)." %}

## Типы валидаторов

Существуют "стандартные" валидаторы, предоставляемые аддоном Ember Validations, а также кастомные валидаторы, предоставляемые вместе с аддоном [ember-flexberry](https://github.com/Flexberry/ember-flexberry).

### "Стандартные" валидаторы

Аддоном Ember Validations предоставляются следующие валидаторы:

* `absence` - проверка того, что валидируемое свойство должно быть пустым. [Подробнее](https://github.com/dockyard/ember-validations#absence).
* `acceptance` - проверка того, что в валидируемое свойство введено допустимое значение (задается в валидаторе). [Подробнее](https://github.com/dockyard/ember-validations#acceptance).
* `confirmation` - проверка того, что значение валидируемого свойства совпадает со значением свойства propertyConfirmation, где property-имя валидируемого свойства. [Подробнее](https://github.com/dockyard/ember-validations#confirmation).
* `exclusion` - проверка того, что значение валидируемого свойства не входит в перечень недопустимых значений. [Подробнее](https://github.com/dockyard/ember-validations#exclusion).
* `format` - проверка того, что значение валидируемого свойства соответствует заданному регулярному выражению. [Подробнее](https://github.com/dockyard/ember-validations#format).
* `inclusion` - проверка того, что значение валидируемого свойства входит в перечень допустимых значений. [Подробнее](https://github.com/dockyard/ember-validations#inclusion).
* `length` - проверка того, что значение валидируемого свойства имеет заданную длину. [Подробнее](https://github.com/dockyard/ember-validations#length).
* `numericality` - проверка того, что значение валидируемого свойства является числовым. [Подробнее](https://github.com/dockyard/ember-validations#numericality).
* `presence` - проверка того, что валидируемое свойство должно быть не пустым. [Подробнее](https://github.com/dockyard/ember-validations#presence).

### Кастомные валидаторы

В рамках аддона ember-flexberry реализованы следующие валидаторы:

* `datetime` - проверка корректности ввода даты/даты-времени. [Подробнее](efd2_date-time-validator.html).

### Условные валидаторы

Все валидаторы могут работать также в режиме условной валидации - их срабатывание будет зависеть либо от дополнительной логической функции, либо от логического свойства объекта. [Подробнее](https://github.com/dockyard/ember-validations#conditional-validators).

## Разработка кастомных валидаторов

Прикладные разработчики также могут создавать свои кастомные валидаторы, если имеющихся валидаторов не достаточно для реализации прикладной логики.
Существует 2 способа реализовать кастомные валидаторы:

* Реализовать отдельный валидатор, который будет возможно переиспользовать для моделей разных сущностей
* Реализовать inline-валидатор, который будет проверять корректность данных для модели одной конкретной сущности

Подробнее о процессе создания кастомных валидаторов можно посмотреть [здесь](https://github.com/dockyard/ember-validations#custom-validators).

Кроме этого, можно посмотреть исходный код валидаторов в [репозитории аддона Ember Validations на GitHub](https://github.com/dockyard/ember-validations/tree/master/addon/validators).
