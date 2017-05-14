---
title: Функция генерации документации по диаграммам
sidebar: flexberry-orm_sidebar
keywords: Plugins, Flexbery ORM
toc: true
permalink: en/fo_doc-gen.html
folder: products/flexberry-orm/generation/
lang: en
---

Функция предназначен для выгрузки информации об атрибутивном составе классов. Позволяет выгружать отчёт для стадий, не подготовленных для генерации.

## Использование

* Выбрать объект System в стадии.
* Выбрать в меню `Утилиты -> Генерация документации по диаграммам`:

![](/images/pages/products/flexberry-orm/generation/doc-gen-using.png)

* Дождаться выполнения. Результатом выполнения будет файл в формате .csv с описанием всех классов и атрибутов.

{% include note.html content="В сгенерированном .csv-файле используется кодировка UTF-8, поэтому некоторые приложения могут некорректно отобразить информацию, содержащуюся в файле." %}

## Пример использования

* Создать диаграмму классов, по которой будет сгенерирована документация:

![](/images/pages/products/flexberry-orm/generation/doc-gen-ex-1.png)

* Выделить стадию и выбирать в меню `Утилиты -> Генерация документации по диаграммам`:

![](/images/pages/products/flexberry-orm/generation/doc-gen-using.png)

* Результат:

![](/images/pages/products/flexberry-orm/generation/doc-gen-ex-3.png)




