---
title: Запуск Ember-приложения
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, Visual Studio, VS, Visual Studio Code, odata
toc: true
permalink: ru/gpg_launch-ember-applications.html
lang: ru
---

Поскольку сгенерированное приложение содержит и клиентскую часть, и серверную, требуется запустить эти части приложения по отдельности.

Для того, чтобы запустить **бэкенд**, нужно открыть файл **Shop.sln**, который находится в папке со сгенерированным из Flexbrry Designer решением, в среде разработки **Visual Studio**. По умолчанию проектом для запуска в сгенерированном решении может являться Shop.Objects. Чтобы приложение запустилось, требуется назначить проектом для запуска **Shop.ODataBackend**.

Чтобы это сделать, необходимо щелкнуть правой кнопкой мыши по проекту Shop.ODataBackend и выбрать пункт меню **Set as StartUp Project**.

![Настройка проекта для запуска](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-1.png)

Далее нужно нажать Ctrl+F5 или выбрать пункт меню `Debug` → `Start Without Debugging` для старта приложения. При успешном запуске приложения в браузере, назначенном по умолчанию в Visual Studio, откроется страница с кодом 403.14 - Forbidden:

![Успешный запуск бэкенда](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-2.png)

Добавьте к URL этой страницы **/odata**, чтобы убедиться, что сгенерированный серверный API доступен:

![Корректная работа серверного API](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-3.png)

Для запуска **фронтенд**-части приложения нужно открыть папку `ember-app` внутри папки со сгенерированным из Flexberry Designer приложением в `Visual Studio Code`: можно это сделать выбрав пункт меню `Файл` → `Открыть папку…`:

![Выбор папки в VS Code](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-4.png)
![Открытие папки ember-app в VS Code](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-5.png)

После открытия папки с приложением, в левой части Visual Studio Code появится структура сгенерированого Ember-приложения. Для запуска приложения остается только открыть **новый терминал** (Терминал → Новый терминал) и выполнить в нем команду **ember s** (или **_ember server_**):

![Сборка проекта на ember](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-6.png)

Если сборка приложения завершилась успешно, то в терминале вы увидите следующее сообщение:

![Сообщение об успешной сборке Ember-приложения](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-7.png)

В терминале вы можете также найти **ссылку** для открытия сообранного Ember-приложения:

![Ссылка для локального запуска Ember-приложения](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-8.png)

Если перейти по указанной в терминале ссылке, то Ember-приложение будет *запущено в браузере*:

![Ember-приложение](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-9.png)

## Итог

В данном разделе мы рассмотрели алгоритм запуска клиентской и серверной части сгенерированнго приложения. При возникновении ошибок в процессе старта сгенерированного приложения, обратитесь за помощью - в некоторых случаях фронтенд или бэкенд могут быть сгенерированы не полностью, например, при возникновении проблем с сетевым подключением во время генерации или в случае недоступности галерей пакетов.

Для работоспособности сгенерированного Ember-приложения важно, чтобы и фронтенд и бэкенд были запущены, при этом порядок запуска клиентской и серверной частей приложения не важен.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Генерация Ember-приложения](gpg_ember-application-generation.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Добавление тестовых данных](gpg_filling-application-primary-data.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>

