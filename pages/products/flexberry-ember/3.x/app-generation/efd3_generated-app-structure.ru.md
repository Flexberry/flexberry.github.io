---
title: Обзор структуры сгенерированных приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_generated-app-structure.html
lang: ru
summary: Обзор структуры сгенерированных клиентского и серверного приложений, основные рекомендации по написанию кода с возможностью перегенерации
---

Рассмотрение сгенерированного приложения происходит для диаграммы классов 

![Диаграмма классов для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generation-class-diagram.png)

## Структура сгенерированного клиентского приложения

Структура сгенерированного `Flexberry Ember`-приложения по сути является расширением [«чистого» Ember-приложения](https://guides.emberjs.com/v3.0.0/getting-started/core-concepts/). На скриншоте ниже слева представлена структура, полученная с помощью командной строки Ember, справа - с помощью генерации кода клиентского приложения из [Flexberry Designer](fd_flexberry-designer.html). Как можно заметить, в правой структуре уже установлены bower- и node-модули (из-за этой установки, помимо прочего, генерация из [Flexberry Designer](fd_flexberry-designer.html) может занимать существенное время), а также добавлен файл «theme.config» для осуществления настройки темы оформления конкретного приложения.

![Сравнение структуры «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare.png)

При сравнении структуры папки 'app' (на скриншоте ниже слева «чистое» Ember-приложение, справа `Flexberry Ember`-приложение) также очевидно, что `Flexberry Ember`-приложение содержит дополнительные элементы.

![Cравнение структуры папки APP «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare-detailed.png)

### Сгенерированные модели 
При генерации в клиентское приложение добавляются следующие [модели](efd3_model.html) (папка `models`)

![Модели в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-models.png)

Выделены модели, соответствующие классам данных, их структура описана в [отдельной статье](efd3_model.html).

{% include note.html content="Модели создаются в папку `models` и именуются следующим образом: если соответствующий C#-класс называется `NewPlatform.Someproject.Somemodel`, то файл с моделью должен называться `new-platform-someproject-somemodel`. Если на OData-бакенде используется атрибут [PublishName](fo_metadata-for-client.html) для упрощения именования моделей, то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет соответствующим образом формироваться из имени в EDM-модели на OData-бакенде)" %}

Также генерируется 'custom-inflector-rules.js', где посредством [Ember Inflector](https://github.com/emberjs/ember-inflector) задаётся соответствие имён в единственном и множественном числе (если присмотреться, то можно заметить, что там указаны последние слова в именах моделей, соответствующих классам данных).

```javascript
import Inflector from 'ember-inflector';

const inflector = Inflector.inflector;

inflector.irregular('agregator', 'Agregators');
inflector.irregular('child1', 'Child1s');
inflector.irregular('child2', 'Child2s');
inflector.irregular('class', 'Classs');

export default {};
```

### Сгенерированные сериализаторы
При генерации в клиентское приложение добавляются следующие [сериализаторы](efd3_serializer.html) (папка `serializers`)

![Cериализаторы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-serializers.png)

Каждому классу данных соответствует свой сериализатор. Для [сериализаторов](efd3_serializer.html) сразу описаны структуры для работы в офлайн-режиме.

### Сгенерированные миксины
При генерации в клиентское приложение добавляются следующие [миксины](efd3_mixin.html) (папка `mixins`)

![Миксины в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-mixins.png)

Миксины при генерации добавлены во вложенную папку `regenerated`, при каждом запуске перегенерации данные миксины будут обновлены, что нужно учитывать, чтобы новый программный код не был утерян. В миксины вынесены как части [моделей](efd3_model.html), так и [сериализаторов](efd3_serializer.html) (здесь также для [сериализаторов](efd3_serializer.html) сразу описаны структуры для работы в офлайн-режиме).

### Сгенерированные адаптеры
При генерации в клиентское приложение добавляются следующие [адаптеры](efd3_adapter.html) (папка `adapters`), сразу описаны структуры для работы в офлайн-режиме.

![Адаптеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-adapters.png)

### Сгенерированные сервисы
При генерации в клиентское приложение добавляется следующий [сервис](efd3_service.html) (папка `services`), ориентированный на работу в онлайн-режиме.

![Сервисы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-services.png)

### Сгенерированные перечисления и трансформации
При генерации в клиентское приложение добавляются следующие [перечисления](efd3_enum.html) (папка `enums`)

![Перечисления в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-enums.png)

Перечисления соответствуют заданным на диаграмме классов элементам со стереотипом `enumeration`. Поскольку перечисления не являются нативными для `Ember`, то для каждого перечисления должна быть описана [трансформация](efd3_transform.html), которая также генерируется (папка transforms)

![Трансформации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-transforms.png)

### Сгенерированные контроллеры
При генерации в клиентское приложение добавляются следующие [контроллеры](efd3_controller.html) (папка `controllers`)

![Контроллеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-controllers.png)

Контроллеры генерируются для форм и приложений, заданных на диаграмме во [Flexberry Designer](fd_flexberry-designer.html). Также для форм создания отдельно генерируется контроллер `new` в папку с именем соответствующей формы редактирования.

![Диаграмма форм и приложений для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/app-and-forms-class-diagram.png)

В контроллер `application` сгенерирован код для работы с меню (в нем определяется структура меню, заданная во [Flexberry Designer](fd_flexberry-designer.html)), а также элементы для смены локализации.

### Сгенерированные роуты
При генерации в клиентское приложение добавляются следующие [роуты](efd3_route.html) (папка `routes`)

![Роуты в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-routes.png)

Роуты генерируются для списковых форм, форм редактирования, заданных на диаграмме во [Flexberry Designer](fd_flexberry-designer.html). Для форм создания отдельно генерируется роут `new` в папку с именем соответствующей формы редактирования.

### Сгенерированные шаблоны
При генерации в клиентское приложение добавляются следующие [шаблоны](efd3_template.html) (папка `templates`)

![Шаблоны в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-templates.png)

Шаблоны генерируются для списковых форм и форм редактирования, заданных на диаграмме во [Flexberry Designer](fd_flexberry-designer.html). Для списковых форм отдельно генерируется шаблон `loading` в папку с именем соответствующей списковой формы, этот шаблон выполняется в процессе подгрузки данных в список.
Также шаблон определён для формы сообщения об ошибке (`error`), для всего приложения в целом (в том числе, для мобильного отображения (`application` в папке `mobile`)).

В "чистом" Ember-приложении присутствует только шаблон `application`.

### Сгенерированные файлы локализации
При генерации в клиентское приложение добавляются следующие [файлы локализации](efd3_locale.html) (папка `locales`)

![Файлы локализации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-locales.png)

Генерируются файлы локализации для русского и английского языков для форм (папка `forms`) и моделей (папка `models`), а также для приложения в целом (`translations.js`).

### Сгенерированные стили
При генерации в клиентское приложение добавляются следующие [стили](efd3_style.html) (папка `styles`).

![Стили в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-styles.png)

В "чистом" Ember-приложении присутствует только пустой файл app.css.

### Сгенерированные компоненты
При генерации в клиентское приложение не добавляются [компоненты](efd3_component.html) (папка `components`).

### Сгенерированные хэлперы
При генерации в клиентское приложение не добавляются [хэлперы](efd3_helper.html) (папка `helpers`).

## Структура сгенерированного серверного приложения

Сгенерированное приложение состоит из двух основных частей. Первая - это классы объектов данных, соответствующих классам на диаграмме классов приложения [Flexberry Designer](fd_flexberry-designer.html). Вторая - это непосредственно OData-бакэнд, к которому будет обращаться клиентское приложение.

![Структура сгенерированного серверного приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-app.png)

## Правила написания кода
К добавлению прикладного кода необходимо подходить ответственно, поскольку он может быть утерян при последующих перегенерациях.

Код для объектов данных должен быть заключён в [скобки программиста](fo_programmer-brackets.html). Аналогичного механизма для остальных сгенерированных частей не предусмотрено, поэтому возможны следующие варианты решения проблемы:

* Код может быть помещён под систему контроля версий, посредством которой возможно отслеживание изменений, произошедших при перегенерации, и корректное их разрешение.
* Генерация кода может производиться в отдельное место, откуда изменения уже будут перенесены в основной проект, однако при использовании данного способа существует опасность, что не все требуемые изменения будут перенесены.