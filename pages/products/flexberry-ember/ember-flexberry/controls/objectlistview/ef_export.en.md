---
title: Exporting lists to Excel
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember Select All
toc: true
permalink: en/ef_export.html
lang: en
summary: Configuring export on the backend and the frontend
---

Технология `Ember Flexberry` предоставляет возможность экспорта списков. Для того чтобы экспорт стал доступен в приложении, необходимо осуществить [настройки на бакэнде](fan_odata-export-to-excel.html).

В шаблоне непосредственно формы должно быть указано

```hbs
{% raw %}{{flexberry-objectlistview
...
exportExcelButton=true
{% endraw %}
```

Настройки экспорта можно сохранять, задав наименование. Также можно изменять наименование столбцов для экспорта.