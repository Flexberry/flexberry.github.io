---
title: Обзор структуры сгенерированных приложений
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember
toc: true
permalink: ru/efd3_generated-app-structure.html
lang: ru
summary: Обзор структуры сгенерированных клиентского и серверного приложений, основные рекомендации по написанию кода для возможности его перегенерации
---

В этом разделе рассматривается пример структуры сгенерированного приложения для следующей диаграммы классов: 

![Диаграмма классов для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generation-class-diagram.png).

## Структура сгенерированного клиентского приложения

Структура сгенерированного `Flexberry Ember`-приложения по сути расширяет структуру ["чистого" Ember-приложения](https://guides.emberjs.com/v3.1.0/getting-started/core-concepts/). При генерации `Flexberry Ember`-приложения сначала происходит генерация "чистого" `Ember`-приложения, а затем генерируются дополнительные элементы приложения на осове метаданных, соответствующих спроектированной модели предметной области во [Flexberry Designer](fd_flexberry-designer.html). На скриншоте ниже слева представлена структура "чистого" `Ember`-приложения, полученная при генерации с использованием [инструментов командной строки Ember](https://cli.emberjs.com/), справа - с помощью генерации кода клиентского приложения из [Flexberry Designer](fd_flexberry-designer.html). Как можно заметить, в варианте структуры справа уже установлены bower- и node-модули (из-за установки соответствующих дополнительных пакетов, помимо прочего, генерация из [Flexberry Designer](fd_flexberry-designer.html) может занимать длительное время), а также добавлен файл `theme.config` для осуществления настройки [темы оформления](efd3_themes-structure.html) конкретного приложения.

![Сравнение структуры «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare.png).

При сравнении структуры папки `app` (на скриншоте ниже слева представлено "чистое" `Ember`-приложение, справа - `Flexberry Ember`-приложение) также очевидно, что `Flexberry Ember`-приложение содержит дополнительные элементы.

![Cравнение структуры папки APP «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare-detailed.png).

Далее мы подробнее рассмотрим те элементы `Ember`-приложения, которые генерируются из [Flexberry Designer](fd_flexberry-designer.html).

### Сгенерированные модели 
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [модели](efd3_model.html) (папка `models`):

![Модели в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-models.png).

На скриншоте выделены модели, соответствующие [объектам данных](fo_data-object.html) (бизнес-объекты приложения), структура этих моделей описана в [отдельной статье](efd3_model.html).

{% include note.html content="Модели генерируются в папку `models` и именуются следующим образом: если соответствующий C#-класс на [OData-бэкенде](fo_orm-odata-service.html) называется `NewPlatform.Someproject.Somemodel`, то файл с моделью в клиентском приложении должен называться `new-platform-someproject-somemodel`. Если на [OData-бэкенде](fo_orm-odata-service.html) используется атрибут [PublishName](fo_metadata-for-client.html) для упрощения именования моделей, то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет формироваться соответственно имени в EDM-модели на OData-бакенде)" %}

Помимо самих моделей также генерируется файл `custom-inflector-rules.js`, где посредством [Ember Inflector](https://github.com/emberjs/ember-inflector) задаётся соответствие имён моделей в единственном и множественном числе - соответствующие преобразования имен автоматически осуществляются при формировании запросов к серверу (если присмотреться, то можно заметить, что в правилах преобразования указаны последние слова в именах моделей, соответствующих [классам данных](fo_data-object.html)).

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
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [сериализаторы](efd3_serializer.html) (папка `serializers`):

![Cериализаторы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-serializers.png).

Каждой модели соответствует свой [сериализатор](efd3_serializer.html). Для каждой модели также генерируются [офлайн-сериализаторы](efd3_offline-serializer.html) для работы с офлайн-хранилищем.

### Сгенерированные миксины
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [миксины](efd3_mixin.html) (папка `mixins`):

![Миксины в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-mixins.png).

Миксины при генерации добавляются во вложенную папку `regenerated`. При каждом запуске перегенерации из [Flexberry Designer](fd_flexberry-designer.html) данные миксины будут обновлены, что нужно учитывать, чтобы новый программный код не был утерян. В миксины вынесены как части [моделей](efd3_model.html), так и [сериализаторов](efd3_serializer.html), в том числе [офлайн-сериализаторов](efd3_offline-serializer.html).

### Сгенерированные адаптеры
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [адаптеры](efd3_application-adapter.html) (папка `adapters`), в том числе [адаптер для работы в офлайн-режиме](efd3_application-adapter-ofline.html):

![Адаптеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-adapters.png).

### Сгенерированные сервисы
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляется переопределенный [сервис store](efd3_store-service.html) (папка `services`), который позволяет приложению работать как в онлайн-режиме, так и в офлайн-режиме.

![Сервисы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-services.png).

### Сгенерированные перечисления и трансформации
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [перечисления](efd3_enum.html) (папка `enums`):

![Перечисления в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-enums.png).

Перечисления соответствуют заданным на диаграмме классов сущностям со стереотипом `enumeration`. Поскольку перечисления не поддерживаются в `Ember`-приложениях "из коробки", то для каждого перечисления генерируется также соответствующая [трансформация](efd3_transform.html) (папка `transforms`):

![Трансформации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-transforms.png).

### Сгенерированные контроллеры
При генерации в клиентское приложение добавляются следующие [контроллеры](efd3_controller.html) (папка `controllers`):

![Контроллеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-controllers.png).

Контроллеры генерируются для форм и приложений, заданных на соответствующей диаграмме классов во [Flexberry Designer](fd_flexberry-designer.html). Также для форм создания записей отдельно генерируется контроллер `new` в папку с именем соответствующей формы редактирования.

![Диаграмма форм и приложений для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/app-and-forms-class-diagram.png).

В контроллер `application` сгенерирован код для работы с меню (в нем определяется структура меню, заданная во [Flexberry Designer](fd_flexberry-designer.html)), а также необходимый для смены локализации код.

### Сгенерированные роуты
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [роуты](efd3_route.html) (папка `routes`):

![Роуты в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-routes.png).

Роуты генерируются для списковых форм, форм редактирования, заданных на соответствующей диаграмме классов во [Flexberry Designer](fd_flexberry-designer.html) (эту диаграмму мы рассматривали ранее, когда говорили о сгенерированных контроллерах). Для форм создания записей по аналогии с генерацией контроллеров отдельно генерируется роут `new` в папку с именем соответствующей формы редактирования.

### Сгенерированные шаблоны
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [шаблоны](efd3_template.html) (папка `templates`):

![Шаблоны в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-templates.png).

Шаблоны генерируются для списковых форм и форм редактирования, заданных на соответствующей диаграмме класов во [Flexberry Designer](fd_flexberry-designer.html) (эту диаграмму мы рассматривали ранее, когда говорили о сгенерированных контроллерах).

Для списковых форм отдельно генерируется также шаблон `loading` в папку с именем соответствующей списковой формы, этот шаблон отображается в процессе перехода на соответствующий роут (этот шаблон генерируется также и на уровень всего приложения).

Также генерируется шаблон для отображения сообщения об ошибках (шаблон `error`). Этот шаблон  генерируется только на уровень всего приложения.

Стандартый для "чистого" `Ember`-приложения шаблон `application` также заменяется на шаблон, разметка которого используется в [темах оформления](efd3_themes-structure.html) `Flexberry Ember`-приложений. Кроме того, генерируется также вариант шаблона `application`, который будет использоваться при запуске приложения на мобильных устройствах (генерация осуществляется в папку `mobile`; по аналогии во вложенных папках `mobile` могут быть созданы и другие элементы приложения, если их работу требуется переопределить при запуске приложения на мобильных устройствах).

По сравнению с рассмотренной структурой папки `templates` в "чистом" `Ember`-приложении присутствует только шаблон `application` со стандартной разметкой "из коробки".

### Сгенерированные файлы локализации
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [файлы локализации](efd3_locale.html) (папка `locales`):

![Файлы локализации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-locales.png).

Генерируются файлы локализации для русского и английского языков для форм (папка `forms`) и моделей (папка `models`), а также для приложения в целом (`translations.js`).

### Сгенерированные стили
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [стили](efd3_style.html) (папка `styles`):

![Стили в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-styles.png).

В "чистом" `Ember`-приложении "из коробки" присутствует только пустой файл `app.css`.

### Сгенерированные компоненты
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение не добавляются новые [компоненты](efd3_component.html) (папка `components`). Однако на сгенерированных формах используются компоненты, которые определены в аддоне [ember-flexberry](https://github.com/Flexberry/ember-flexberry).

### Сгенерированные хэлперы
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение не добавляются [хэлперы](efd3_helper.html) (папка `helpers`). Однако в шаблонах сгенерированных форм используются хелперы, которые определены в аддоне [ember-flexberry](https://github.com/Flexberry/ember-flexberry).

## Структура сгенерированного серверного приложения

Сгенерированное решение для бэкенда состоит из двух основных проектов. Первый - это проект с [объектами данных](fo_data-object.html), соответствующих сущностям модели предметной области во [Flexberry Designer](fd_flexberry-designer.html) (то есть классам на диаграммах классов). Второй - это непосредственно Web API-приложение с [OData-сервисом](fo_orm-odata-service.html), которое предоставляет серверный API для клиентского приложения.

![Структура сгенерированного серверного приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-app.png).

## Правила написания кода
К внесениям изменений в прикладной код необходимо подходить ответственно, поскольку он может быть утерян при последующих перегенерациях.

Код для объектов данных на бэкенде должен быть заключён в [скобки программиста](fo_programmer-brackets.html). Аналогичного механизма для остальных частей сгенерированных приложений (как клиентского, так и серверного) пока не предусмотрено, поэтому возможны следующие варианты решения данной проблемы:

* Код как клиентского, так и серверного приложений может быть помещён в систему контроля версий, посредством которой возможно отслеживание изменений, произошедших при перегенерации, и корректное их разрешение.
* Повторная генерация кода может производиться в отдельное место, откуда необходимые изменения уже будут перенесены в основной проект вручную. Однако при использовании данного способа существует вероятность того, что не все требуемые изменения будут перенесены.
