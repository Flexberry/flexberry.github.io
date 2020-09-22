---
title: Внутреннее устройство тем
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef2_themes_structure.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание внутреннего механизма тем оформления
---

## Устройство тем оформления

### Основа стилей

В `ember-flexberry` за стилизацию тем отвечает css фреймворк `Semantic-UI`, он находится в папке `/bower_components/semantic-ui`.

За выбор тем отвечает конфигурационный файл `theme.config`, который распологоется в корневой папке проекта. В нем для каждого элемента можно указать свою тему,
а также можно задать элементам разные стили от любых имеющихся тем.  
![screenshoot](/images/pages/img_themes/screenshots/config_theme.jpg)

Также в прокте необходим файл `styles/theme.less`, он отвечает за импорт всех необходимх файлов с переменными и переопределениями для выбранных тем.

#### Общие файлы
В папке `/src/definitions` для каждого элемента есть свой файл `element.less`, в нём
размещены общие для всех тем селекторы с переменными, значение которых импортируется из файлов `.variables`.

#### Файлы темы
Темы состоят из двух файлов: `.variables` файла и `.overrides` файла отдельно для каждого элемента разметки. 
Тема может включать как переменные, так и переопределения, либо только одно из них.

- в файлах `.variables` указаны переменные, которые необходимо настроить для темы.
- файлы `.overrides` содержат дополнительные `CSS` правила, которые необходимо добавить для темы.

[Подробнее о темах `Semantic-UI`](https://semantic-ui.com/usage/theming.html#using-packaged-themes) 

## Темы `ember-flexberry`
В зафисимости от версии `ember-flexberry` используются разные способы хранения стилей.

Для проектов, использующих версии `ember-flexberry` 2.X.X темы оформления расположены прямо в [аддоне ember-flexberry](https://github.com/Flexberry/ember-flexberry/tree/develop/addon/styles)  
Для проектов, использующих версии `ember-flexberry` начиная с 3.4.0 темы располагаются в отдельном пакете [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes)

## Структура тем аддона
Темы распологаются прямо в [аддоне ember-flexberry](https://github.com/Flexberry/ember-flexberry/tree/develop/addon/styles).  
Структура файлов основана на компонентах `Semantic-UI`.  
Доступны темы [`blue-sky`](ef2_supported_themes.html#bluesky-theme), [`orange`](ef2_supported_themes.html#orange-theme-временно-не-поддерживается).

## Структура `ember-flexberry-themes`
Темы распологаются в отдельном репозитории [ember-flexberry-themes](https://github.com/Flexberry/ember-flexberry-themes).  
Структура фйалов основана на компонентах `ember-flexberry`.  
В данном пакете доступны темы [`blue-sky`](ef2_supported_themes.html#bluesky-theme), [`ghost`](ef2_supported_themes.html#ghost).

Структура пакета:

```
  ember-flexberry-themes
  ├── examples
  └── src
      ├── flexberry-associations.config /* для сопоставления файловой структуры `ember-flexberry-themes` и `semantic-ui` */
      ├── flexberry-imports.less
      ├── theme.config.example          /* Пример файла theme.config для прикладного проекта */
      ├── theme.less                    /* Пример файла theme.less для прикладного проекта */
      ├── definitions                   /* Стили, общие для всех тем */
      |   ├── components                /* Общие для всех тем стили для компонентов ember-flexberry */
      |   ├── globals                   /* Общие для всех тем глобальные стили */
      |   └── pages
      └── themes
          └── <theme-name>
              ├── assets                /* Шрифты, картинки, итд. для темы */
              ├── components            /* Стили компонентов ember-flexberry для данной темы */
              ├── globals
              ├── pages
              └── semantic              /* Стили для различных компонентов semantic ui */
      

```

`flexberry-associations.config` - файл необходим для сопоставления файловой структуры пакетов `ember-flexberry-themes` и `semantic-ui`

## Доработка стилей в приложении

{% include important.html content="В первую очередь следует переопределять существующие стили с помощью переменных в файлах `.variables`, а уже затем использовать `.overrides`." %}

1. Добавить папку `site` в app/styles
3. Файл `*.variables` для переменных, `*.overrides` для добавления новых стилей.
4. В первую очередь нужно использовать переменные для изменения стилей.

Данная схема актуальна для всех тем.

## Доработка стилей для приложений, использующих `ember-flexberry-themes`

{% include note.html content="Стили рассортированы по компонентам ember-flexberry." %}

Структура каталога

```
  app
  ├── ...
  ├── styles
  |   ├── site
  |   |   ├── components
  |   |   |   ├── flexberry-button.variables/.overrides
  |   |   |   ├── flexberry-checkbox.variables/.overrides
  |   |   |   ├── flexberry-colsconfig.variables/.overrides
  |   |   |   ├── flexberry-dropdown.variables/.overrides
  |   |   |   ├── flexberry-field.variables/.overrides
  |   |   |   ├── flexberry-file.variables/.overrides
  |   |   |   ├── flexberry-groupedit.variables/.overrides
  |   |   |   ├── flexberry-lookup.variables/.overrides
  |   |   |   ├── flexberry-modal.variables/.overrides
  |   |   |   ├── flexberry-objectlistview.variables/.overrides
  |   |   |   ├── flexberry-sidebar.variables/.overrides
  |   |   |   ├── flexberry-simpledatetime.variables/.overrides
  |   |   |   └── flexberry-validationsummary.variables/.overrides
  |   |   ├── globals
  |   |   |   └── site.variables/.overrides
  |   |   └── pages
  |   |   |   ├── edit-form.variables/.overrides
  |   |   |   ├── login-form.variables/.overrides
  |   |   |   └── main.variables/.overrides
  |   └── app.less
  └──...
```

В теме используется цветовая схема, таким образом, если поменять основной цвет глобально, то он поменяется для кнопок и чекбокса и тд.
Цветовая схема задается в `globals/site.variables`

```less
  /*******************************
            COLOR SCHEME
  *******************************/

  // Main
  @defaultColor                   : #ECF2FB;
  @primaryColor                   : @cobaltBlue;
  @activeColor                    : @mayaBlue;
  @accentColor                    : #E94B3D;
  @secondaryColor                 : #7699B3;
  @disabledColor                  : @lightGrayishBlue;

  // Sidebar
  @sidebarBackgroundColor         : @textColor;

  // Page
  @simplePageBackground           : @defaultColor;
  @textColor                      : @blueZodiak;
  @iconColor                      : @lightGrayishBlue;

  // Input
  @inputBorderColor               : @defaultColor;
  @inputBackground                : @defaultColor;
  @inputHoverBorderColor          : @chambray;

  @defaultInputFocusBackground    : @white;
  @defaultFocusBorderColor        : @activeColor;
  @focusedFormBorderColor         : @activeColor;
```
### Список основных цветов

| Свойство          | Описание                                               | Дефолтное значение                                                                     |
|-------------------|--------------------------------------------------------|----------------------------------------------------------------------------------------|
| `@defaultColor`   | Основной цвет (заливка полей, фон, кнопки по умолчанию)| ![#ECF2FB](https://placehold.it/15/ECF2FB/000000?text=+) `#ECF2FB`                     |
| `@primaryColor`   | Основной цвет (кнопки с классом `primary`, чекбоксы)   | @cobaltBlue: ![#0C49CD](https://placehold.it/15/0C49CD/000000?text=+) `#0C49CD`        |
| `@accentColor`    | Акцентный цвет (акцентные элементы управления)         | ![#E94B3D](https://placehold.it/15/E94B3D/000000?text=+) `#E94B3D`                     |
| `@secondaryColor` | Вспомогательный цвет (кнопка secondary, ссылки)        | ![#7699B3](https://placehold.it/15/7699B3/000000?text=+) `#7699B3`                     |
| `@textColor`      | Цвет текста                                            | @blueZodiak: ![#3B4256](https://placehold.it/15/3B4256/000000?text=+) `#3B4256`        |
| `@iconColor`      | Иконки в полях                                         | @lightGrayishBlue: ![#848E99](https://placehold.it/15/848E99/000000?text=+) `#848E99`  |
| `@disabledColor`  | Недоступные элементы управления                        | @lightGrayishBlue: ![#848E99](https://placehold.it/15/848E99/000000?text=+) `#848E99`  |

### Интерактивные

| Свойство          | Описание                                               | Дефолтное значение                                                                     |
|-------------------|--------------------------------------------------------|----------------------------------------------------------------------------------------|
| `@activeColor`    | Активный элемент (focus)                               | @mayaBlue:   ![#62B0FF](https://placehold.it/15/62B0FF/000000?text=+) `#62B0FF`        |
| `@negativeColor`  | Ошибка (error)                                         | @cinnabar:   ![#E53935](https://placehold.it/15/E53935/000000?text=+) `#E53935`        |

### Список цветов полей на форме

Применяется ко всем компонентам для ввода данных
`flexberry-field`, `flexberry-dropdown`, `flexberry-lookup` и тд.

| Свойство                       | Описание                              | Дефолтное значение                                                                |
|--------------------------------|---------------------------------------|-----------------------------------------------------------------------------------|
| `@inputBorderColor`            | Цвет бордера поля                     | @defaultColor: ![#ECF2FB](https://placehold.it/15/ECF2FB/000000?text=+) `#ECF2FB` |
| `@inputHoverBorderColor`       | Цвет бордера поля при наведении       | @chambray:     ![#B3BBC3](https://placehold.it/15/B3BBC3/000000?text=+) `#B3BBC3` |
| `@inputBackground`             | Цвет заливки поля                     | @defaultColor: ![#ECF2FB](https://placehold.it/15/ECF2FB/000000?text=+) `#ECF2FB` |
| `@defaultInputFocusBackground` | Цвет заливки поля, когда оно в фокусе | @white:        ![#FFFFFF](https://placehold.it/15/FFFFFF/000000?text=+) `#FFFFFF` |
| `@focusedFormBorderColor`      | Цвет бордера поля, когда оно в фокусе | @activeColor:  ![#62B0FF](https://placehold.it/15/62B0FF/000000?text=+) `#62B0FF` |


### Пример доработки через пременные

Последовательность действий для верной доработки стилей и минимизации количества правил.
Для примера возмем смену цвета заливки текстового поля.

1. Открыть инструменты разработчика в браузере, найти нужный элемент в дереве а также свойство, которое необходимо изменить. Если навести курсор на название файла, в котором расположено правило, то отобразится путь к файлу. В данном примере можно увидеть, что правило задано в файле `form.less`, который расположен в папке `/bower_components/semantic-ui`

![screenshoot](/images/pages/img_themes/screenshots/exam_default_fields_styles.jpg)

2. Открыть `/bower_components/semantic-ui` и найти там файл, в котором задано правило. Отсюда можно узнать имя переменной, которую необходимо переопределить, в данном случае это `@inputBackground`.
![screenshoot](/images/pages/img_themes/screenshots/exam_form_less.jpg)

3. Далее необходимо найти где задано значение этой переменной, для этого можно воспользоваться поиском по проекту. Для начала поиск необходимо провести по проекту `ember-flexberry-themes`.
![screenshoot](/images/pages/img_themes/screenshots/exam_find_var.jpg)

4. Для доработки стилей на прикладном проекте необходимо создать папку `site/<навзвание темы>` в каталоге `app/styles` и создать там соответствующую структуру папок. Таким образом, в данном примере необходимо добавить файл `site.variables` в каталоге `app/styles/site/ghost/globals`. Далее просто заменим значение переменной `@inputBackground` в добавленом файле.
![screenshoot](/images/pages/img_themes/screenshots/exam_change_var.jpg)

5. В результате с помощью одной переменной цвет был изменен для всех полей формы и не пришлось дописывать дополнительных стилей.
![screenshoot](/images/pages/img_themes/screenshots/exam_result.jpg)

### Пример доработки с помощью новых правил

Если необходимо добавить свойство, которое не определено дефолтными стилями, то правило можно задать в соответствующем файле `.overrides`.
Для примера добавим границы для кнопок на форме редактирования.

1. Открыть инструменты разработчика в браузере, найти нужный элемент в дереве. В стилях найти название файла, в котором разполагаются правила для данного элемента. Если навести курсор на название файла, в котором расположено правило, то отобразится путь к файлу. В данном примере можно увидеть, что правило задано в файле `edit-form.overrides`, который расположен в папке `/ember-flexberry-themes/src/themes/ghost/pages`
![screenshoot](/images/pages/img_themes/screenshots/exam_overrides.jpg)

2. Для доработки стилей на прикладном проекте необходимо создать папку `site/<навзвание темы>` в каталоге `app/styles` и создать там соответствующую структуру папок. Таким образом, в данном примере необходимо добавить файл `edit-form.overrides` в каталоге `app/styles/site/ghost/pages`. Далее в этом файле указываются все необходимые доработки для формы редактирвоания.
![screenshoot](/images/pages/img_themes/screenshots/exam_overrides_rule.jpg)

Данное правило будет импортировано в последнюю очередь
![screenshoot](/images/pages/img_themes/screenshots/exam_overrides_result.jpg)