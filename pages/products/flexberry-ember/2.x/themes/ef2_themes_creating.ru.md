---
title: Создание новых тем
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: ru/ef2_themes_creating.html
folder: products/ember-flexberry/themes/
lang: ru
summary: Описание создания новых тем оформления
---

## Инструкция по созданию новых тем оформления

Как уже [было сказано](ef2_themes_structure.html), темы реализованы средствами Semantic-UI, и для их разработки требуется учитывать специфику тем семантика.
[Статья на официальном сайте фреймворка](https://semantic-ui.com/usage/theming.html)

## Создание тем в ember-flexberry

Создание темы начинаяется с переопределения файла `globals\site.variables` из темы default. В нём расположены основные используемые переменные.
Для существующих компонентов следует также сначала переопредеять их переменные в соответсвующих `.variables` файлах. Если же этого недостаточно, тогда можно переписать стили в `.overrides`.

При создании новых компонентов, их стили следует добавлять в существующие файлы темы `default`, т.к. Semantic-UI не одобряет импорт сторонних `.less` файлов, а также не забывать стилизовать компоненты в других темах, если это требуется.

## Создание тем в других аддонах, использующих ember-flexberry

Как и в самом ember-flexberry аддоны нуждаются в использовании тех же файлов `theme.config` и `theme.less`, а также директории с темами.
Однако, отличие будет в том, что в них нужно указать импорт тем из используемого аддона.

Пример на основе [ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis):

В файл [theme.config](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/theme.config) был добавлен путь к каталогу тем аддона:

```
/* Path to the ember-flexberry-gis theme packages */
@gisThemesFolder : 'addon/styles/themes';
```

А в файле [theme.less](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/styles/theme.less) добавлено название темы, используемой из ember-flexberry:
`@flexberry-theme: 'blue-sky';` и объявлены импорты файлов тем из используемого аддона.

Также важно учитывать, что если вы используете переменне в файлах `.variables` темы `default`, то необходимо указать их импорт в файлe `addon.less`, для файлов с расширением `.overrides` это не распостраняется. [Пример](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/styles/addon.less)

В остальном всё также, стили переопределяются сначала в `.variables`, затем в `.overrides`, и указывается название темы для используемых элементов в файле `theme.config` .
