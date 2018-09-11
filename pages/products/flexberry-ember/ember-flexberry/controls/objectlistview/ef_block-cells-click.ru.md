---
title: Блокирование отдельных ячеек списка
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember, filters, OLV, список
summary: Использование параметров при работе со строками списков
toc: true
permalink: ru/ef_block-cells-click.html
lang: ru
---

На [списке](ef_object-list-view.html) существует возможность заблокировать отдельную ячеку для открытия объекта [на редактирование](ef_edit-form.html), оставив при этом активным клик на строке.

Для этого чтобы выключить клик по строке, нужно переопределить переход к форме редактирования, используя параметр (params):

```javascript
params.goToEditForm = false; 
```

Затем вызвать метод `_super`.

В параметрах обработчика клика по строке есть: 

* запись, по которой кликнули
* колонка по которой кликнули (в ней есть имя свойства, заголовок колонки на форме, cellComponent этой колонки)
* индекс нажатой колонки.

Данные параметры используются для отключения обработки клика по строке при определенных условиях (т.е. при клике по определенным ячейкам).

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
