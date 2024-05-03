---
title: Изменение в строке Groupedit
sidebar: flexberry-ember-3_sidebar
keywords: Flexberry Ember, groupedit, configurate row
toc: true
permalink: ru/ef3_groupedit_configurate_row.html
lang: ru
---

### Изменение поля в Groupedit в зависимости от значения в другом поле

В [ember-flexberry](https://flexberry.github.io/ru/ef3_landing_page.html) существует возможность настроить зависимость одного поля в groupedit от значения другого поля этой же строки. Логику зависимости можно определить согласно конкретной задаче. 

Например, существует задача, согласно которой требуется, чтобы поле ``Flag`` становилось недоступно для изменения в том случае, если в поле ``Enumeration`` введено значение ``Block Flag``.

![Пример GroupEdit](/images/pages/products/flexberry-ember/3.x/components/groupedit-configurate-row.png)

Для реализации данной логики нужно:
1. Создать в контроллере [observer](https://guides.emberjs.com/v3.15.0/object-model/observers/), отлавливающий изменения в каждом поле столбца ``Enumeration``:

```javascript
  enumerationObserver: observer('model.details.@each.enumeration', function () {
    const details = this.get('model.details').toArray();

    details.forEach((detail) => {
      let rowConfig = detail.rowConfig;
      if (rowConfig) {
        this.send('configurateRow', rowConfig, detail);
      }
    });
  }),
```

2. В экшене ``configurateRow``, к которому обращается ``enumerationObserver``, реализовать логику зависимости поля ``Flag`` от значения поля ``Enumeration`` соответствующей строки:

```javascript
  actions: {
    configurateRow(rowConfig, detail) {
      let enumeration = detail.enumeration;

      if (enumeration === 'Block Flag') {
        set(rowConfig, 'readonlyColumns', ['flag']);
      } else {
        set(rowConfig, 'readonlyColumns', []);
      }
    }
  }
```

[Реализация данного примера](http://flexberry.github.io/ember-flexberry/dummy/dummy-test-2/?#/components-examples/flexberry-groupedit/field-readonly-status-depend-on-another-field-value) доступна на тестовом стенде. Для блокировки поля ``Flag`` нужно выбрать ``Block Flag`` в списке поля ``Enumeration``.

