---
title: Функция для поиска
sidebar: flexberry-orm_sidebar
keywords: CASE Plugins
toc: true
permalink: ru/fo_intelli-search-plugin.html
folder: products/flexberry-orm/generation/
lang: ru
---

Модуль расширения [FlexberryDesigner](fd_landing_page.html): `IntelliSearch` разработан для поиска фрагментов диаграмм по выбранным проектам репозитория.

## Использование поиска

Чтобы воспользоваться поиском, необходимо:

* Создать пустую диаграмму классов в любой стадии
* Нарисовать объекты (классы, связи и пр.), которые необходимо найти
* Нажать кнопку `Искать в других стадиях` 

![](/images/pages/products/flexberry-orm/generation/intelli-search-plugin/search-example.png)

* В открывшемся окне "Стадии для поиска" выбрать интересующие стадии 

![](/images/pages/products/flexberry-orm/generation/intelli-search-plugin/search-studys.png)

* При необходимости настроить параметры поиска (порог релевантности и важность совпадения имен\типов данных\значений по умолчанию\кардинальностей) нажав на кнопку `Параметры поиска`

![](/images/pages/products/flexberry-orm/generation/intelli-search-plugin/search-params.png)

* Нажать кнопку `Поиск`.

__Примечание__: поиск требует индексации стадий, если будет выбрано большое количество непроиндексированных стадий, то процесс индексации может занять длительное время.

Результат представляется в виде дерева стадий со скриншотами диаграмм, на которых найдены схожие фрагменты. Скриншот диаграммы можно открыть в натуральную величину.

![](/images/pages/products/flexberry-orm/generation/intelli-search-plugin/search-results.png)
