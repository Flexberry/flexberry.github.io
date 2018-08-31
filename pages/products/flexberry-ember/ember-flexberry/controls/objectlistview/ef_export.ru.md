---
title: Экспорт списков в Excel
sidebar: ember-flexberry_sidebar
keywords: Flexberry Ember Select All
toc: true
permalink: ru/ef_export.html
lang: ru
summary: Настройка экспорта на юакэнде и фронтэнде
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