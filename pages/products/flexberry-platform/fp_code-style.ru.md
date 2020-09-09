---
title: Стиль оформления кода
sidebar: flexberry-platform_sidebar
keywords: Code, style, stylecop, convention, стиль
toc: true
permalink: ru/fp_code-style.html
lang: ru
summary: При разработке компонентов платформы Flexberry команда должна придерживаться единого стиля написания кода.
---

Для понимания требований к инфраструктуре приложений рекомендуется ознакомиться с книгой [Инфраструктура программных проектов. Соглашения, идиомы и шаблоны для многократно используемых библиотек .NET (+ CD-ROM)](http://www.ozon.ru/context/detail/id/5588868/).

## JavaScript

В JavaScript Open Source-проектах платформы Flexberry есть принятый набор соглашений, который опубликован на [странице GitHub](https://github.com/Flexberry/javascript-style-guide).
Также есть отдельное [соглашение по написанию комментариев в коде EmberJS-проектов](fp_ember-comments-style-yuidoc.html).

## CSharp

Рекомендуется ознакомиться с книгой [Инфраструктура программных проектов: соглашения, идиомы и шаблоны для многократно используемых библиотек .NET](http://www.williamspublishing.com/Books/978-5-8459-1692-1.html), в которой описаны лучшие практики, касающиеся как оформления кода, так и архитектурных моментов.  
В .NET Open Source-проектах платформы Flexberry набор соглашений реализуется за счёт механизма "Наборов правил", который [присутсвует](https://msdn.microsoft.com/ru-ru/library/dd465186.aspx) в Microsoft Visual Studio - следование данным подсказкам IDE позволяет разрабатывать приложения с четким и понятным программным кодом.

## Статические анализаторы кода

### StyleCop

StyleCop — статический анализатор C# кода на предмет соответствия стилю.

**Настройка:**

1. Установить NuGet пакет [StyleCop.Analyzers](http://nuget.ics.perm.ru/packages/StyleCop.Analyzers/)
2. В свойствах проекта, во вкладке `Code Analysis` выбрать правила в выпадающем списке `Run this rule set`.

![Стиль кода](/images/pages/products/flexberry-platform/code-style1.png)

С [перечнем правил](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/blob/develop/Flexberry.ruleset), которые используются в проектах платформы Flexberry можно ознакомится на GitHub-e.

### ESLint

ESLint — это статический анализатор кода для программ на языке JavaScript, проверяющий их соответствие стандартам оформления кода.

{% include note.html content="ESLint используется на проектах с версией Ember-a 3.X.X" %}

**Настройка:**

1. Установить npm пакет [ember-cli-eslint](https://www.npmjs.com/package/ember-cli-eslint)
2. В корне проекта создать и настроить файл `.eslintrc.js`, пример реализации содержится в проекте [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintrc.js).
3. В корне проекта создать файл `.eslintignore` - в него нужно добавить файлы и директории, которые не должны проверятся ESLint-ом. Пример реализации содержится в проекте [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintignore).

Более подробную информацию можно найти [на официальном сайте ESLint](https://eslint.org/docs/user-guide/configuring)

### JSHint

JSHint - это инструмент статического анализа кода, используемый при разработке программного обеспечения для проверки соответствия исходного кода JavaScript правилам кодирования.

{% include note.html content="JSHint используется на проектах с версией Ember-a 2.X.X" %}

**Настройка:**

1. Установить npm пакет [jshint](https://www.npmjs.com/package/jshint), в ember приложениях этот пакет устанавливается в качестве зависимости при установки [ember-cli-qunit](https://www.npmjs.com/package/ember-cli-qunit).
2. В корне проекта создать и настроить файл `.jshintrc`, пример реализации содержится в проекте [ember-flexberry](https://github.com/Flexberry/ember-flexberry/blob/develop/.jshintrc).
