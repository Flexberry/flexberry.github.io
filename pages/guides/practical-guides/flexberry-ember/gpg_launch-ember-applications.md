---
title: Запуск Ember-приложения
sidebar: guide-practical-guides_sidebar
keywords: guide, ember, ember-cli, Visual Studio, VS, Visual Studio Code, odata
toc: true
permalink: ru/gpg_launch-ember-applications.html
lang: ru
---

Для того, чтобы успешно запустить ember-приложение, требуется запустить обе части по отдельности: frontend и backend.

Для того, чтобы запустить **backend**, нужно открыть файл **Shop.sln** в **Visual Studio**. По умолчанию, исполняемым файлом является Shop.Objects. Требуется назначить исполняемым **Shop.ODataBackend**.

Для этого нужно щелкнуть правой кнопкой мыши по Shop.ODataBackend и выбрать пункт меню **Set as StartUp Project**.

![Настройка исполняемого файла](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-1.png)

Далее нужно нажать Ctrl+F5 для старта проекта. Если все прошло успешно, в браузере, назначенном по умолчанию для VS, откроется страница с кодом 403.14 - Forbidden:

![Успешный запуск бэкенда](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-2.png)

Добавьте к адресу этой страницы /odata, чтобы убедиться, что сгенерированный серверный API доступен:

![Корректная работа odata](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-3.png)

Для запуска **frontend**-части приложения нужно открыть папку `ember-app` в `Visual Studio Code`: запускаем программу и открываем проект через `Файл` → `Открыть папку…`:

![Открытие VS Code](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-4.png)
![Открытие ember-app во VS Code](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-5.png)

После открытия нужной папки, слева появится структура ember-приложения, сгенерированная автоматически с помощью Fleberry Designer. Для запуска приложения остается только открыть **новый терминал** (Терминал → Новый терминал) и выполнить в нем команду **ember s** (или **_ember server_**):

![Сборка проекта на ember](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-6.png)

Если приложение запустилось успешно, в терминале вы увидите запись:

![Сообщение об успешной сборке проекта на ember](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-7.png)

Выше в терминале вы можете найти **ссылку**, по которой доступно наше ember-приложение:

![Локальная ссылка на ember-приложение](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-8.png)

Ember-приложение *запущено*:

![Ember-приложение](/images/pages/guides/flexberry-ember/4-1-launch-ember-applications/4-1-9.png)

## Итог

Описанный выше аглоритм запуска частично (без настройки бэкенда) придется выполнять каждый раз, когда вы будете локально запускать ember-приложение. В связи с этим, следует внимательно отнестись к содержимому данного параграфа. Если у вас возникли какие-то ошибки ‒ обратитесь за помощью, т.к. это могут быть недостатки генерации приложения из Flexberry Designer.

Порядок запуска фронтенда и бэкенда не важен, важно наличие обоих запущенных процессов сборки.

## Перейти

* [Практическое руководство  «Делай как я»](gpg_landing-page.html) <i class="fa fa-arrow-up" aria-hidden="true"></i>

* [Генерация Ember-приложения](gpg_ember-application-generation.html) <i class="fa fa-arrow-left" aria-hidden="true"></i>
* [Заполнение первичных данных приложения](gpg_filling-application-primary-data.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>

