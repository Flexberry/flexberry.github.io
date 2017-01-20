---
title: Flexberry Designer
sidebar: product--sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/flexberry-designer.html
folder: product--folder
lang: ru
---

# Flexberry Designer
Flexberry Designer - это [CASE-инструмент](https://ru.wikipedia.org/wiki/CASE), который может быть использован как для бизнес-моделирования (анализ бизнес-процессов, реинжиниринг бизнес-процессов), так и для объектно-ориентированного проектирования программного обеспечения и баз данных. В качестве нотации используется [UML](http://www.uml.org).

Flexberry Designer является [продуктом платформы Flexberry](platform-structure.html).

# Состав
## Репозиторий UML-моделей
Репозиторий моделей позволяет удобно хранить диаграммы в структурированном виде. Для работы с репозиторием применяется  [браузер репозиториев Flexberry Designer](flexberry-designer-repository-browser.html).

[Репозиторий имеет структуру](recommended-structure-repository-and--placing-diagrams.html), не ограничивающую пользователя какой-либо методологией.

Для диаграммы как репозиторного объекта реализованы [дополнительные возможности для манипуляции](working-with-diagrams-in-the-repository-browser.html).

## Редактор UML-диаграмм
Реализованы широкие возможности моделирования (создания моделей в нотации `UML`) соответствующими диаграммными методами в рамках [редактора UML-диаграмм](editing-diagram.html).

## Модули расширения Flexberry Designer
Имеется возможность расширения функционала посредством подключения [дополнительных модулей](case-plugins.html). Модули предоставляют возможности расширения пользовательского интерфейса, структуры данных, возможностей по генерации исходного кода с моделей.

# Проектирование во Flexberry Designer
Проектирование с использованием инструментария Flexberry Designer подразумевает принцип Model-First, когда все изменения в модели производятся в Flexberry Designer, а изменения в коде выполняются во время генерации. Также реализован [механизм, позволяющий программистам писать код, который при перегенерации не будет потерян](code-generation.html), так называемые, «[скобки программиста](programmer-brackets.html)». Использование инструментария проектирования позволяет с лёгкостью вносить изменения в модель любому участнику команды разработки, не опасаясь что-нибудь сломать.

Ключевые понятия объектной структуры для систем, разрабатывающихся во Flexberry Designer, представлены в [этой статье](key-concepts-flexberry-designer.html).

# Установка и настройка Flexberry Designer
Существует несколько вариантов установки Flexberry Designer на различных реляционных СУБД:
* [из дистрибутива](flexberry-designer-standalone-install.html).
* [с сайта продукта](flexberry-designer-install.html).

Для работы Flexberry Designer должна быть [установлена корректная лицензия](installation-licensing-files.html) на все используемые продукты.

Дополнительно Flexberry Designer предоставляет следующие полезные возможности:
* [Импорт стадии и диаграммы при открытии файлов crp и csdg](import-crp-csdg.html)
* [Запуск Flexberry Designer с открытием указанной стадии](flexberry-designer-running-with-the-opening-of-stage.html)
* [Настройка имени главной формы Flexberry Designer](flexberry-designer-form-name.html)
