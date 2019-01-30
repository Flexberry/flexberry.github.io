---
title: Стиль оформления кода
sidebar: flexberry-platform_sidebar
keywords: Code, style, stylecop, convention, стиль
toc: true
permalink: ru/fp_code-style.html
lang: ru
summary: При разработке компонентов платформы Flexberry команда должна придерживаться единого стиля написания кода.
---

## Общие сведения

Чтобы углубиться в тему подробнее рекомендуем ознакомиться с [книгой](http://www.ozon.ru/context/detail/id/5588868/).

## JavaScript

В JavaScript Open Source-проектах платформы Flexberry есть принятый набор соглашений, который опубликован на странице нашей организации на GitHub: <https://github.com/Flexberry/javascript-style-guide>.
Также есть отдельное [соглашение по написанию комментариев в коде EmberJS-проектов](fp_ember-comments-style-yuidoc.html).

## CSharp

Рекомендуется ознакомиться с книгой [Инфраструктура программных проектов: соглашения, идиомы и шаблоны для многократно используемых библиотек .NET](http://www.williamspublishing.com/Books/978-5-8459-1692-1.html), в которой описаны лучшие практики, касающиеся как оформления кода, так и архитектурных моментов.  
В .NET Open Source-проектах платформы Flexberry набор соглашений реализуется за счёт механизма "Наборов правил", который [присутсвует](https://msdn.microsoft.com/ru-ru/library/dd465186.aspx) в Microsoft Visual Studio - придерживайтесь подсказок IDE и ваш код будет хорошим и согласованным.

## Статические анализаторы кода

### StyleCop
StyleCop — статический анализатор C# кода на предмет соответствия стилю.

**Настройка:**

1. Установить NuGet пакет [StyleCop.Analyzers](http://nuget.ics.perm.ru/packages/StyleCop.Analyzers/)
2. В свойствах проекта, во вкладке `Code Analysis` выбрать правила в выпадающем списке `Run this rule set`.

![](/images/pages/products/flexberry-platform/code-style1.png)

Файл с правилами которые использутся в проектах платформы Flexberry можно взять [тут](https://github.com/Flexberry/NewPlatform.Flexberry.ORM/blob/develop/Flexberry.ruleset).

### JSLint
JSLint — это статический анализатор кода для программ на языке JavaScript, проверяющий их соответствие стандартам оформления кода.

{% include note.html content="JSLint используется на проектах с версией Ember-a 3.X.X" %}

**Настройка:**

1. Установить npm пакет [ember-cli-eslint](https://www.npmjs.com/package/ember-cli-eslint)
2. В корне проекта, создать и настроить файл `.eslintrc.js` пример файла можно посмотреть [тут](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintrc.js).
3. В корне проекта, создать файл `.eslintignore` - в него можно добавить файлы и дериктории которые не должны проверятся JSLint-ом. Пример файла можно посмотреть [тут](https://github.com/Flexberry/ember-flexberry/blob/feature-ember-update/.eslintignore).

Более подробную информацию можно найти [на официальном сайте JSLint](https://eslint.org/docs/user-guide/configuring)

### JSHint
JSHint - это инструмент статического анализа кода, используемый при разработке программного обеспечения для проверки соответствия исходного кода JavaScript правилам кодирования.

{% include note.html content="JSHint используется на проектах с версией Ember-a 2.X.X" %}

**Настройка:**

1. Установить npm пакет [jshint](https://www.npmjs.com/package/jshint) в ember приложениях этот пакет устанавливается в качестве зависимости при установки [ember-cli-qunit](https://www.npmjs.com/package/ember-cli-qunit).
2. В корне проекта, создать и настроить файл `.jshintrc` пример файла можно посмотреть [тут](https://github.com/Flexberry/ember-flexberry/blob/develop/.jshintrc).
