---
title: Запуск Flexberry Designer с открытием указанной стадии
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_flexberry-designer-running-with-the-opening-of-stage.html
folder: products/flexberry-designer/
lang: ru
---

# Запуск Flexberry Designer с открытием указанной стадии
Есть возможность сделать несколько ярлыков на [Flexberry Designer](fd_flexberry-designer.html), каждый из которых открывал бы разные стадии.

Для этого нужно настроить ярлык следующим образом: аргументом для запуска нужно передать параметр `path:Путь до стадии`

Например, если путь до стадии "`Репозитарий\Проект\Конфигурация\Стадия\`", то строка для запуска будет иметь вид
```
Flexberry.exe path:Репозитарий\Проект\Конфигурация\Стадия\
```

![](/images/pages/img/page/FlexberryDesignerRunningWithTheOpeningOfStage/Flexberry.exe path.JPG)

(((<msg type=note head='Путь для запуска'>В качестве пути может быть указан репозитарий, проект, конфигурация, стадия или система. Путь можно скопировать из выпадающего строки пути во [Flexberry Designer](fd_flexberry-designer.html).</msg>)))
