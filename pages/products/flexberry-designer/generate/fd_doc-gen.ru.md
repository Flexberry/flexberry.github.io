---
title: Плагин генерации документации по диаграммам
sidebar: flexberry-designer_sidebar
keywords: Plugins, Flexbery Desinger
toc: true
permalink: ru/fd_doc-gen.html
folder: products/flexberry-designer/generate/
lang: ru
---

Плагин предназначен для выгрузки информации об атрибутивном составе классов. Позволяет выгружать отчёт для стадий, не подготовленных для генерации.

## Использование

* Выбрать объект System в стадии.
* Выбрать в меню DocGen -> Сгенерировать документацию:

![](/images/pages/products/flexberry-designer/generate/doc-gen-using.png)

* Дождаться выполнения. Результатом выполнения будет файл в формате .csv с описанием всех классов и атрибутов.

{% include note.html content="В сгенерированном .csv-файле используется кодировка UTF-8, поэтому некоторые приложения могут некорректно отобразить информацию, содержащуюся в файле." %}

## Пример использования

* Создать диаграмму классов, по которой будет сгенерирована документация:

![](/images/pages/products/flexberry-designer/generate/doc-gen-ex-1.png)

* Выделить стадию и выбирать в меню DocGen -> Сгенерировать документацию:

![](/images/pages/products/flexberry-designer/generate/doc-gen-using.png)

* Результат:

![](/images/pages/products/flexberry-designer/generate/doc-gen-ex-3.png)




