---
title: Creating new themes
sidebar: flexberry-ember-2_sidebar
keywords: Flexberry Ember Themes
toc: true
permalink: en/ef2_themes_creating.html
folder: products/ember-flexberry/themes/
lang: en
autotranslated: true
hash: 2d82c61d553d49ab93b2789f82824bf4f5873a636e0645f49475d09b16acf8e1
summary: Description of create new themes
---

## How to create new themes

As has already [been said](ef2_themes_structure.html), threads are implemented using Semantic-UI for their design is required to take into account the specifics of the semantics.
[Article on the official website of the framework](https://semantic-ui.com/usage/theming.html)

## The creation in the ember-flexberry

Creating topics with nachinaetsya override file `globals\site.variables` from the default theme. It is main used variables.
For existing components should also first pereprodaet their variables in the appropriate `.variables` files. If that's not enough, then you can rewrite the styles in `.overrides`.

When creating new components, styles to add to your existing theme files `default`, because Semantic-UI does not approve the import of third-party `.less` files, and do not forget to stylize components in other subjects, if required.

## The creation of the other Addons that are using ember-flexberry

As in ember-Addons flexberry need to use the same files `theme.config` and `theme.less` and directory themes.
However, the difference will be that they need to specify the import of the addon.

An example based on the [ember-flexberry-gis](https://github.com/Flexberry/ember-flexberry-gis):

In the file [theme.config](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/theme.config) was added to the directory path to the addon:

```
/* Path to the ember-flexberry-gis theme packages */
@gisThemesFolder : 'addon/styles/themes';
```

And in the file [theme.less](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/styles/theme.less) added the name of the theme used from ember-flexberry:
`@flexberry-theme: 'blue-sky ;` and declared imports the files from your addon.

It is also important to note that if you use peremene files `.variables` themes `default`, you must specify them in the import file `addon.less` for files with the extension `.overrides` it does not apply. [Example](https://github.com/Flexberry/ember-flexberry-gis/blob/develop/addon/styles/addon.less)

In all other respects as well, the styles are overridden first in `.variables`, then `.overrides` and specifies the topic name for the elements used in the file `theme.config` .



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}