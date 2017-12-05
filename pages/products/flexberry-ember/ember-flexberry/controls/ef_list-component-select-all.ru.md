---
title: Flexberry select all at list
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember Select All
toc: true
permalink: ru/ef_list-component-select-all.html
folder: products/ember-flexberry/controls/
lang: ru
summary: Кнопка выделить все в списковых компонентах
---

## Описание

Добавлены кнопки "Отметить все на текущей странице", "Отметить все на всех страницах" и "Установить сортировку  по умолчанию" в flebxberry-olv и flexberry-simpleolv.

Они активируются вместе с checkbox в строках параметром showCheckBoxInRow.

* "Отметить все на текущей странице" - отмечает все объекты на сранице, добавляет отмеченые объекты в slectRecords.
* "Отметить все на всех страницах" - активирует параметр allSeclect, обработка удаления при активации этого параметра реализуется в соответствии с потребностями конкретного приложения в action delete() компонента.
* "Установить сортировку по умолчанию" - устанавливает сортировку и количество отображаемых страниц по умолчанию.
