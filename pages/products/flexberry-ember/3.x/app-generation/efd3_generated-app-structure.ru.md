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

![Диаграмма классов для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generation-class-diagram.png)

## Структура сгенерированного клиентского приложения

Структура сгенерированного `Flexberry Ember`-приложения по сути расширяет структуру ["чистого" Ember-приложения](https://guides.emberjs.com/v3.1.0/getting-started/core-concepts/). При генерации `Flexberry Ember`-приложения сначала происходит генерация "чистого" `Ember`-приложения, а затем генерируются дополнительные элементы приложения на основе метаданных, соответствующих спроектированной модели предметной области во [Flexberry Designer](fd_flexberry-designer.html). На скриншоте ниже слева представлена структура "чистого" `Ember`-приложения, полученная при генерации с использованием [инструментов командной строки Ember](https://cli.emberjs.com/), справа - с помощью генерации кода клиентского приложения из [Flexberry Designer](fd_flexberry-designer.html). Как можно заметить, в варианте структуры справа уже установлены bower- и node-модули (из-за установки соответствующих дополнительных пакетов, помимо прочего, генерация из [Flexberry Designer](fd_flexberry-designer.html) может занимать длительное время), а также добавлен файл `theme.config` для осуществления настройки [темы оформления]() конкретного приложения.

![Сравнение структуры «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare.png)

При сравнении структуры папки `app` (на скриншоте ниже слева представлено "чистое" `Ember`-приложение, справа - `Flexberry Ember`-приложение) также очевидно, что `Flexberry Ember`-приложение содержит дополнительные элементы.

![Cравнение структуры папки APP «чистого» Ember-приложения и `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-ember-app-compare-detailed.png)

Далее подробнее будут рассмотрим те элементы `Ember`-приложения, которые генерируются из [Flexberry Designer](fd_flexberry-designer.html).

### Сгенерированные модели 
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [модели](https://guides.emberjs.com/v3.1.0/models/) (папка `models`):

![Модели в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-models.png).

На скриншоте выделены модели, соответствующие [объектам данных](fo_data-object.html) (бизнес-объекты приложения), структура этих моделей описана в [отдельной статье](efd3_model.html)

{% include note.html content="Модели генерируются в папку `models` и именуются следующим образом: если соответствующий C#-класс на [OData-бэкенде](fo_orm-odata-service.html) называется `NewPlatform.Someproject.Somemodel`, то файл с моделью в клиентском приложении должен называться `new-platform-someproject-somemodel`. Если на [OData-бэкенде](fo_orm-odata-service.html) используется атрибут [PublishName](fo_metadata-for-client.html) для упрощения именования моделей, то наименование пространства имен в этом случае в клиентской модели может отсутствовать (имя клиентской модели будет формироваться соответственно имени в EDM-модели на OData-бакенде)" %}

Помимо самих моделей также генерируется файл `custom-inflector-rules.js`, где посредством [Ember Inflector](https://github.com/emberjs/ember-inflector) задаётся соответствие имён моделей в единственном и множественном числе - соответствующие преобразования имен автоматически осуществляются при [формировании запросов к серверу](efd3_generated-app-start.html) (если присмотреться, то можно заметить, что в правилах преобразования указаны последние слова в именах моделей, соответствующих [классам данных](fo_data-object.html)).

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
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [сериализаторы](https://guides.emberjs.com/v3.1.0/models/customizing-serializers/) (папка `serializers`):

![Cериализаторы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-serializers.png)

Каждой модели соответствует свой сериализатор; [в технологии Flexberry Ember сериализаторы имеют свою специфику](efd3_serializer.html). Для каждой модели также генерируются [офлайн-сериализаторы]() для работы с офлайн-хранилищем.

### Сгенерированные миксины
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [миксины](https://api.emberjs.com/ember/3.1/classes/Mixin) (папка `mixins`):

![Миксины в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-mixins.png)

Миксины при генерации добавляются во вложенную папку `regenerated`. При каждом запуске перегенерации из [Flexberry Designer](fd_flexberry-designer.html) данные миксины будут обновлены, что нужно учитывать, чтобы новый программный код не был утерян. В миксины вынесены как части [моделей](efd3_model.html), так и [сериализаторов](efd3_serializer.html), в том числе [офлайн-сериализаторов]().

### Сгенерированные адаптеры
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [адаптеры](https://guides.emberjs.com/v3.1.0/models/customizing-adapters/) (папка `adapters`), в том числе [адаптер для работы в офлайн-режиме]():

![Адаптеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-adapters.png)

### Сгенерированные сервисы
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляется [переопределенный сервис store](efd3_service.html) ([папка `services`](https://guides.emberjs.com/v3.1.0/applications/services/#toc_defining-services)), который позволяет приложению работать как в онлайн-режиме, так и в [офлайн-режиме]().

![Сервисы в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-services.png)

### Сгенерированные перечисления и трансформации
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [перечисления]() (папка `enums`):

![Перечисления в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-enums.png).

Перечисления соответствуют заданным на диаграмме классов сущностям со стереотипом `enumeration`. Поскольку перечисление [не является поддерживаемым типом данных в `Ember`-приложениях "из коробки"](https://guides.emberjs.com/v3.1.0/models/defining-models/#toc_transforms), то для каждого перечисления генерируется также соответствующая [трансформация]() (папка `transforms`):

![Трансформации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-transforms.png)

### Сгенерированные контроллеры
При генерации в клиентское приложение добавляются следующие [контроллеры](https://guides.emberjs.com/v3.1.0/controllers/) (папка `controllers`):

![Контроллеры в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-controllers.png)

Контроллеры генерируются для [форм](efd3_form.html) и приложений, заданных на соответствующей диаграмме классов во [Flexberry Designer](fd_flexberry-designer.html). Также для [форм создания записей](efd3_editform.html) отдельно генерируется контроллер `new` в папку с именем соответствующей [формы редактирования](efd3_editform.html).

![Диаграмма форм и приложений для рассмотрения сгенерированного `Flexberry Ember`-приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/app-and-forms-class-diagram.png)

В контроллер `application` сгенерирован код для работы с меню (в нем определяется структура меню, заданная во [Flexberry Designer](fd_flexberry-designer.html)), а также необходимый для [смены локализации]() код.

### Сгенерированные роуты
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [роуты](https://guides.emberjs.com/v3.1.0/routing/) (папка `routes`):

![Роуты в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-routes.png).

Роуты генерируются для [списковых форм](efd3_listform.html), [форм редактирования](efd3_editform.html), заданных на соответствующей диаграмме классов во [Flexberry Designer](fd_flexberry-designer.html) (эту диаграмма была рассмотрена ранее). Для [форм создания записей](efd3_editform.html) по аналогии с генерацией контроллеров отдельно генерируется роут `new` в папку с именем соответствующей [формы редактирования](efd3_editform.html).

### Сгенерированные шаблоны
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [шаблоны](https://guides.emberjs.com/v3.1.0/templates/handlebars-basics/) (папка `templates`):

![Шаблоны в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-templates.png)

Шаблоны генерируются для [списковых форм](efd3_listform.html) и [форм редактирования](efd3_editform.html), заданных на соответствующей диаграмме класов во [Flexberry Designer](fd_flexberry-designer.html) (эту диаграмма была рассмотрена ранее).

Для [списковых форм](efd3_listform.html) отдельно генерируется шаблон `loading` в папку с именем соответствующей списковой формы, этот шаблон отображается [в процессе перехода на соответствующий роут](https://guides.emberjs.com/v3.1.0/routing/loading-and-error-substates/) (этот шаблон генерируется также и на уровень всего приложения).

Также генерируется шаблон [для отображения сообщения об ошибках (шаблон `error`)](https://guides.emberjs.com/v3.1.0/routing/loading-and-error-substates/). Этот шаблон  генерируется только на уровень всего приложения.

Стандартый для "чистого" `Ember`-приложения шаблон `application` заменяется на шаблон, разметка которого используется в [темах оформления]() `Flexberry Ember`-приложений. Кроме того, генерируется вариант шаблона `application`, который будет использоваться [при запуске приложения на мобильных устройствах]() (генерация осуществляется в папку `mobile`; по аналогии во вложенных папках `mobile` могут быть созданы и другие элементы приложения, если их работу требуется переопределить при запуске приложения на мобильных устройствах).

По сравнению с рассмотренной структурой папки `templates` в "чистом" `Ember`-приложении присутствует только шаблон `application` со стандартной разметкой "из коробки".

### Сгенерированные файлы локализации
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [файлы локализации]() (папка `locales`):

![Файлы локализации в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-locales.png)

Генерируются файлы локализации для русского и английского языков для форм (папка `forms`) и моделей (папка `models`), а также для приложения в целом (`translations.js`).

### Сгенерированные стили
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение добавляются следующие [стили]() (папка `styles`):

![Стили в сгенерированном `Flexberry Ember`-приложении](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-styles.png)

В "чистом" `Ember`-приложении "из коробки" присутствует только пустой файл `app.css`.

### Сгенерированные компоненты
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение не добавляются новые [компоненты](https://api.emberjs.com/ember/3.1/classes/Component) (папка `components`). На [сгенерированных формах](efd3_form.html) используются [компоненты, которые определены в технологии Flexberry Ember]().

### Сгенерированные хэлперы
При генерации приложения из [Flexberry Designer](fd_flexberry-designer.html) в клиентское приложение не добавляются [хэлперы](https://api.emberjs.com/ember/3.1/classes/Helper) (папка `helpers`). В шаблонах [сгенерированных формах](efd3_form.html) используются хелперы, которые определены в технологии Flexberry Ember.

## Структура сгенерированного серверного приложения

Сгенерированное решение для бэкенда состоит из двух основных проектов. Первый - это проект с [объектами данных](fo_data-object.html), соответствующих сущностям модели предметной области во [Flexberry Designer](fd_flexberry-designer.html) (то есть классам на диаграммах классов). Второй - это непосредственно Web API-приложение с [OData-сервисом](fo_orm-odata-service.html), которое предоставляет серверный API для клиентского приложения.

![Структура сгенерированного серверного приложения](/images/pages/products/flexberry-ember/ember-flexberry/generation/generated-backend-app.png)

## Правила написания кода
К внесениям изменений в прикладной код необходимо подходить ответственно, поскольку он может быть утерян при последующих перегенерациях.

Код для объектов данных на бэкенде должен быть заключён в [скобки программиста](fo_programmer-brackets.html). Аналогичного механизма для остальных частей сгенерированных приложений (как клиентского, так и серверного) пока не предусмотрено, поэтому возможны следующие варианты решения данной проблемы:

* Код как клиентского, так и серверного приложений может быть помещён в систему контроля версий, посредством которой возможно отслеживание изменений, произошедших при перегенерации, и корректное их разрешение.
* Повторная генерация кода может производиться в отдельное место, откуда необходимые изменения уже будут перенесены в основной проект вручную. Однако при использовании данного способа существует вероятность того, что не все требуемые изменения будут перенесены.
