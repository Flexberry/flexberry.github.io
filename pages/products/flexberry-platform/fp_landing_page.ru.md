---
title: Платформа Flexberry
sidebar: flexberry-platform_sidebar
keywords: flexberry
summary:
toc: false
permalink: ru/fp_landing_page.html
lang: ru
---

![Архитектура платформы Flexberry](/images/pages/products/flexberry-platform/architecture/flexberry-platform-app-architecture.png)

Продукты [платформы Flexberry](http://flexberry.net):

* Инструменты проектирования и редактирования:
  * [Flexberry Designer](fd_flexberry-designer.html). Установить можно, скачав с сайта [flexberry.net](https://designer.flexberry.net/#/download-win-app).
* Фреймворки:
  * [Flexberry Ember](ef3_landing_page.html)
  * [Flexberry ASP.NET](fa_landing_page.html)
  * [Flexberry Winforms](fw_landing_page.html)
* Функциональные подсистемы:
  * Flexberry Foundation
  * [Flexberry GIS](fg_landing_page.html)
  * [Flexberry Service Bus](fsb_landing_page.html)
  * Flexberry Analytics
  * [Flexberry BPM](fbpm_landing_page.html)
  * Flexberry Portal
* Технологические компоненты
  * [Flexberry ORM](fo_landing_page.html)
  * Flexberry Security
  * Flexberry Audit

Полный перечень возможностей платформы Flexberry отображен на [главной странице](index.html) сайта документации.

## Рекомендуемые инструменты для работы с продуктами платформы Flexberry

### Инструменты для работы с Ember приложениями

__В первую очередь необходимо следующее:__

* [Node.js](http://nodejs.org). Позволяет работать с инструментами для фронтенда вне браузера. Также в состав node.js входит менеджер пакетов npm (node package manager) для работы с зависимостями во фронтенд-проектах, аналогично NuGet в Flexberry ASP.NET WebForms.
* [Bower](http://bower.io). Является аналогом npm, но они часто применяются на проекте вместе.
* [Git](http://git-scm.com). Распределённая система управления версиями.
* [Ember CLI](http://www.ember-cli.com).
* [yarn](https://yarnpkg.com/lang/en/docs/install/#windows-stable).

__Далее потребуются:__

* **Браузер** и расширение Ember inspector, которое помогает отлаживать ember-приложение:

  * [chrome](https://chrome.google.com/webstore/detail/ember-inspector/bmdblncegkenkacieihfhpjfppoconhi)
  * [firefox](https://addons.mozilla.org/en-US/firefox/addon/ember-inspector)
* **Консоль** для работы с git, npm, bower, ember-cli и др.
  * **linux**, возможно, подойдет любой терминал.
  * **windows** стандартный cmd.exe не лучший выбор.
        Рекомендуются:

          * PowerShell.
          * ConEmu.
          * Cmder.
* **IDE и текстовый редактор**. IDE для js очень ресурсно затратна, поэтому рекомендуется использовать текстовые редакторы такие как:
  * Visual Studio Code
  * Atom (к нему рекомендуется установить расширения, облегчающие работу)
  
        * [file-icons](https://atom.io/packages/file-icons)
        * [platformio-ide-terminal](https://atom.io/packages/platformio-ide-terminal)
        * [language-markdown](https://atom.io/packages/language-markdown)
        * [markdown-preview-plus](https://atom.io/packages/markdown-preview-plus)
    * и другие.

{% include note.html content="На сайте [ember-cli.com](http://www.ember-cli.com/user-guide/#editors) написаны некоторые рекомендации при использовании некоторых редакторов." %}

#### Алгоритм установки и настройки Node.js

{% include important.html content="Все команды в алгоритме нужно вводить в cmd в режиме администратора" %}

1. Нужно скачать и установить [Node.js](https://nodejs.org/en/) версию Recommended For Most Users
2. После установки открыть cmd от администратора и ввести `npm -v`, если выдан номер версии - установка npm прошла успешно и можно переходить к следующему пункту.
3. Далее нужно добавить proxy, это можно сделать двумя способами:

#### Алгоритм установки и настройки Bower

{% include important.html content="Все команды в алгоритме нужно введить в cmd в режиме администратора" %}

{% include important.html content="Перед установкой Bower необходимо вначале установить Node.js" %}

1. В cmd введите команду `npm install -g bower`, чтобы установить [Bower](https://bower.io/).
2. Чтобы проверить, установился ли Bower, введите команду `bower -v` в cmd, если выдан номер версии - установка прошла успешно и можно переходить к следующему пункту.

#### Алгоритм установки и настройки Git-a

1. Нужно скачать и установить [Git](https://git-scm.com/).

{% include important.html content="После установки обязательно нужно перезагрузить компьютер" %}

Прежде чем начать работать с репозиторием, нужно произвести первоначальную настройку git (если не настроено ранее) как указано в [Введение - Первоначальная настройка Git](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%9F%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0%D1%87%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0-Git).
Как минимум:

* Нужно указать имя и email, прикрепленный к Вашему аккаунту на GitHub:

  * Имя: `git config --global user.name "John Doe"`
  * Email: `git config --global user.email johndoe@example.com`

Следующим шагом необходимо стать участником команды [Flexberry на GitHub](https://github.com/Flexberry).
Это необходимо для отправки коммитов в удаленный репозиторий на GitHub.

{% include note.html content="Может возникнуть проблема, что при проверке через консоль (команда git в Powershel, к примеру) будет выдаваться ошибка. Тогда нужно перезагрузить компьютер. Если это не поможет - то переустановить git (удалить и установить заново). " %}

#### Алгоритм установки Ember CLI

{% include important.html content="Перед установкой Ember CLI необходимо вначале установить Node.js и Bower" %}

1. Когда npm и bower установлены, нужно установить [Ember CLI](https://ember-cli.com/), для этого введите команду `npm install -g ember-cli@2.4.3`.
2. После завершения установки Ember CLI введите команду `ember -v`, если выдан номер версии - установка прошла успешно.

### Инструменты для работы с Docker-образами

* [Docker](https://www.docker.com/get-started)

[Разворачивание решений в контейнерах Docker](gbt_deployment_docker.html)

__Алгоритм установки Docker:__

1. Установить Docker Desktop.
2. Включить виртуализацию в BIOS-е.

### Инструменты для работы с ASP.NET приложениями

__Обязательный набор:__

* [Flexberry Designer](https://flexberry.net/ru/);
* Microsoft Visual Studio 2019;
* SQL Management Studio;
* pgAdmin.

__Дополнительно рекомендуется:__

* Resharper;
* DotPeek;
* SQL Toolbelt;
* LinqPad;
* .NET Developer Bundle™;
  * ANTS Performance Profiler;
  * ANTS Memory Profiler;
  * Exception Hunter;
  * .NET Reflector Pro;
* DevArt Data Compare;
* DevArt Schema Compare.
