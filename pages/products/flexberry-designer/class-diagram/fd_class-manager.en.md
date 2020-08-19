---
title: Manager classes
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Manager classes, a reference class based on
summary: the Number of references in the Manager classes, dependency mapping
toc: true
permalink: en/fd_class-manager.html
lang: en
autotranslated: true
hash: 3471413f049b1d76ce7dc3e0745f2784e1cfa153805c62cd4d8174f3545e3f13
---

The Manager class allows you to control classes within the same stage. To start the Manager should select the [menu `Менеджер классов`](fo_orm-case-plugin.html).

Opening is available in any class for editing is if the edit the class properties for the [chart](fd_class-diagram.html).

![Example](/images/pages/products/flexberry-designer/class-diagram/classmanager.jpg)

### The number of references

`Количество упоминаний` is a count of the number of occurrences of a class in all [charts](fd_class-diagram.html) stage. For example, there are two charts, each of them 2 times found in the same class. Then the number of references is 4.

It is possible that the number of references may not reflect the actual condition of the charts. This situation is erroneous if it is possible to reproduce the sequence of actions that leads to a conflicting situation, you should contact support. For experienced users [Flexberry Designer](fd_flexberry-designer.html) have the option to manually correct this error condition by increasing\decreasing the number of references to the class.

### According to

The Manager classes have an opportunity to see what [the graphs](fd_class-diagram.html) is used by a particular class. To do this, in the context menu of the selected class, select `Показать зависимости`.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}