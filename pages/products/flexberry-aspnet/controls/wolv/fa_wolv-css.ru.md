---
title: CSS-классы WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-css.html
lang: ru
---

Возможность кастомизации отображения [WOLV](fa_web-object-list-view.html) при помощи CSS отображена во внутренней структуре и CSS-классах:

```html
<div class="ics-wolv-column-header">
    <span class="ics-wolv-column-sort-order">[индекс порядка сортировки]</span>
    <span class="ics-wolv-column-sort-direction (ics-wolv-column-sort-asc|ics-wolv-column-sort-desc)"></span>
    <span class="ics-wolv-column-caption (ics-wolv-column-sort-enabled|ics-wolv-column-sort-disabled)">[подпись столбца]</span>
</div>
```

| CSS-класс | Описание |
| --------- | -------- |
| `ics-wolv-column-header` | Контейнер заголовка столбца |
| `ics-wolv-column-sort-order` | Цифра, отвечающая за порядок сортировки |
| `ics-wolv-column-sort-direction` | Элемент дизайна, показывающий направление сортировки (общий для сортировки по убыванию и по возрастанию) |
| `ics-wolv-column-sort-asc` | Элемент дизайна, показывающий направление сортировки по возрастанию |
| `ics-wolv-column-sort-desc` | Элемент дизайна, показывающий направление сортировки по убыванию |
| `ics-wolv-column-caption`  | Подпись столбца (общая для включенной и отключенной сортировки) |
| `ics-wolv-column-sort-enabled` | Подпись столбца с включенной сортировкой |
| `ics-wolv-column-sort-disabled` | Подпись столбца с отключенной сортировкой |
