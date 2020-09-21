---
title: Update EmberJS projects
keywords: ember, ember-cli, update, version, upgrade
tags: [EmberJS]
sidebar: flexberry-ember-2_sidebar
toc: true
permalink: en/ef2_update-ember-js.html
lang: en
autotranslated: true
hash: 04f3ac8afcefd8cbc1051306a21e7833449c625035df697096afbc38bd7f9288
summary: instructions for updating EmberJS on projects that use Ember Flexberry.
---

## The General approach to update ember-cli

The update is recommended to perform consistently switching versions `ember-cli` to run on each new version of the initialization of the project and controlling the collection of project and testing. You should also closely monitor `DEPRECATIONS`, because from version to version, they turn into errors.
The most convenient way to use `ember-cli` with multiple versions is to use [Docker-container ember-cli](ef2_docker-for-ember-cli.html).

## Upgrading from version 2.4.3 to 3.0.0

The table that will help when you update Ember.js to 3.x in terms of replacing the names in the imports to the new «dogs»
<https://github.com/emberjs/rfcs/blob/master/text/0176-javascript-module-api.md#addenda>

The good news in the fact that in Ember there is the so-called» «GodMode packages, which automatically allow the code to replace the old for the new syntax when you upgrade versions Ember.js.
Here's the corresponding codemod to update the names in the imports (instructions for use there in the description):
<https://github.com/ember-codemods/ember-modules-codemod>

After starting codmod, perhaps something will need to dopravit hands.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}