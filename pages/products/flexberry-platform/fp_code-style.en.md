---
title: Style code
sidebar: flexberry-platform_sidebar
keywords: Code, style, stylecop, convention, style
toc: true
permalink: en/fp_code-style.html
lang: en
autotranslated: true
hash: 160d4a9ca20c7a5711f9a4765d2016dae21b7f4a3cc2d95bd85cc8d27449bdbc
summary: In the development of platform components Flexberry team must adhere to a single coding style.
---

To understand infrastructure requirements applications are encouraged to consult the book [Infrastructure software projects. Agreement, idioms, and patterns for reusable libraries .NET ( CD-ROM)](http://www.ozon.ru/context/detail/id/5588868/).

## JavaScript

JavaScript Open Source projects platform Flexberry has adopted a set of agreements which was published on [the GitHub page](https://github.com/Flexberry/javascript-style-guide).
There is also a separate [agreement in writing comments in your code EmberJS projects](fp_ember-comments-style-yuidoc.html).

## CSharp

It is recommended to read the book [Infrastructure software projects: conventions, idioms and patterns for reusable libraries .NET](http://www.williamspublishing.com/Books/978-5-8459-1692-1.html), which describes best practices, including coding and architectural highlights.
In .NET Open Source projects platform Flexberry set of agreements is realized through the mechanism of "rule Sets" which [is present](https://msdn.microsoft.com/ru-ru/library/dd465186.aspx) in Microsoft Visual Studio by following the above tips IDE allows you to develop applications with clear and understandable program code.

## Static code analyzers

### StyleCop

StyleCop is a static analyzer C# code for compliance with the style.

**Setting:**

1. Install NuGet package [StyleCop.Analyzers](http://nuget.ics.perm.ru/packages/StyleCop.Analyzers/)
2. In the project properties, in the tab `Code Analysis` to select rules in the drop-down list `Run this rule set`.

![Code style](/images/pages/products/flexberry-platform/code-style1.png)

With [list of rules](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/blob/develop/Flexberry.ruleset), which are used in the projects of the platform Flexberry can be found on GitHub-e.

### ESLint

ESLint is a static code analyzer for programs written in JavaScript that checks their compliance to coding standards.

{% include note.html content="ESLint is used on projects with a version of Ember-a 3.X.X" %}

**Setting:**

1. Install npm package [ember-cli-eslint](https://www.npmjs.com/package/ember-cli-eslint)
2. At the root of the project create and configure file `.eslintrc.js`, an example implementation is contained in the draft [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintrc.js).
3. In project root create a file `.eslintignore` - need to add the files and the directory should not be checked ESLint-Ohm. An example implementation is contained in the draft [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintignore).

More information can be found [on the official website ESLint](https://eslint.org/docs/user-guide/configuring)

### JSHint

JSHint is a static code analysis tool used in software development to check compliance with source code JavaScript coding guidelines.

{% include note.html content="use JSHint on the project with the version of Ember-a 2.X.X" %}

**Setting:**

1. Install npm package [jshint](https://www.npmjs.com/package/jshint) in ember applications this package installs as dependencies when installing [ember-cli-qunit](https://www.npmjs.com/package/ember-cli-qunit).
2. At the root of the project create and configure file `.jshintrc`, an example implementation is contained in the project [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/develop/.jshintrc).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}