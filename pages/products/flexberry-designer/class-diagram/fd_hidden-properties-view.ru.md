---
title: Скрытые свойства в представлении
sidebar: flexberry-designer_sidebar
keywords: Flexberry Desinger, View, представление, скрытые свойства, пример
summary: Особенности генерации скрытых свойств в представлении, продемонстрированные на примере
toc: true
permalink: ru/fd_hidden-properties-view.html
lang: ru
---

Программист может объявить часть свойств, попадающих в [представление](fd_view-definition.html) «скрытыми», тогда они будут в [представлении](fd_view-definition.html), но не будут видны в пользовательском интерфейсе.

Для объявления состава скрытых свойств необходимо инициализировать свойство `Hidden` при указании атрибута `View`.

__Пример__:

```csharp
[View("Простое2", new string[]{"Name as Имя",  "AnOtherAttrib"}, Hidden=new string[]{"AnOtherAttrib"})]
```

## Иллюстрация на примере сравнения разных представлений

Существуют 3 разных представления:

* в первом будет 2 свойства и ни одно из них не будет скрытым
* во втором те же 2 свойства, но уже 1 скрытое
* в третьем будет только 1 свойство (то самое, которое осталось "открытым" в представлении №2).

Этап | 2 всего 0 скрытых | 2 всего 1 скрытое | 1 всего 0 скрытых
:------------------|:-----------------------------|:--------------------------|:----------------------------
Flexberry Desinger | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-view.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-view.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-view.png)
Код | ``` [View("КлиентHidden1", new string[] {"ФИО", "Прописка"})] ```| ``` [View("КлиентHidden2", new string[] {"ФИО", "Прописка"}, Hidden=new string[] {"Прописка"})] ```| ``` [View("КлиентHidden3", new string[] {"ФИО"})] ```
Форма редактирования | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-e.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-e.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-e.png)
Форма списка | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-l.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-l.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-l.png)
Загружаемые данные | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-1-data.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-2-data.png) | ![](/images/pages/products/flexberry-designer/class-diagram/client-hidden-3-data.png)

На примере наглядно видно, что 1 и 2 вариант идентичны в плане выгружаемых данных (строка 5), а 2 и 3 идентичны в плане пользовательского интерфейса (строки 3 и 4)