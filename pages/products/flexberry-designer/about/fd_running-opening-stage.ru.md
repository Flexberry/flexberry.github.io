---
title: Запуск с открытием указанной стадии
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_running-opening-stage.html
folder: products/flexberry-designer/about/
lang: ru
---

Есть возможность сделать несколько ярлыков на [Flexberry Designer](fd_landing_page.html), каждый из которых открывал бы разные стадии.

Для этого нужно настроить ярлык следующим образом: аргументом для запуска нужно передать параметр `path:Путь до стадии`

Например, если путь до стадии "`Репозитарий\Проект\Конфигурация\Стадия\`", то строка для запуска будет иметь вид

```
Flexberry.exe path:Репозитарий\Проект\Конфигурация\Стадия\
```

![](/images/pages/products/flexberry-designer/about/path.JPG)

{% include note.html content="В качестве пути может быть указан репозитарий, проект, конфигурация, стадия или система. Путь можно скопировать из выпадающего строки пути во [Flexberry Designer](fd_landing_page.html)." %}
