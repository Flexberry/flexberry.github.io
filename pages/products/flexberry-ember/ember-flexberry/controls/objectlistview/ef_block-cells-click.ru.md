---
title: Блокирование отдельных ячеек списка
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember, filters, OLV, список
summary: Использование параметров при работе со строками списков
toc: true
permalink: ru/ef_block-cells-click.html
lang: ru
---

Если на списке расположены пользовательские кнопки в заданных столбцах и при этом требуется сохранить возможность открытия объекта по клику на строке, но так, чтобы "ячейка" с кнопкой не была активна, возможна реализовать "отключение" заданной ячейки от клика по строке.

Для этого нужно переопределить обработчик клика по строке. В нем есть параметр(params). Чтобы выключить клик по строке, нужно установить свойство 

```javascript
params.goToEditForm = false; 
```

и вызвать метод `_super`.

В параметрах обработчика клика по строке есть: 

* запись, по которой кликнули
* колонка по которой кликнули (в ней есть имя свойства, заголовок колонки на форме, cellComponent этой колонки)
* индекс нажатой колонки.

Можно эту информацию использовать, чтобы выключать обработку клика по строке при определенных условиях (т.е. по сути при клике по определенным ячейкам).

Например:

```javascript
actions: {
    objectListViewRowClick(record, params) {
      if (params.column && params.column.cellComponent.componentName === 'flexberry-file' && params.originalEvent.target.tagName.toLowerCase() !== 'td') {
        params.goToEditForm = false;
      }

      this._super(...arguments);
    }
```

Подробнее в коде приложения [dummy](https://github.com/Flexberry/ember-flexberry/blob/develop/tests/dummy/app/routes/components-examples/flexberry-objectlistview/downloading-files-from-olv-list.js#L45).
