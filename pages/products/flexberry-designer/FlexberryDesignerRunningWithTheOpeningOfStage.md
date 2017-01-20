---
title: Запуск Flexberry Designer с открытием указанной стадии
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/fd_flexberry-designer-running-with-the-opening-of-stage.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': [Flexberry Designer](flexberry-designer.html) поддерживает возможность запуска приложения с открытием на определённой стадии.
</td>
</tr></tbody></table></a>
</div>

# Запуск Flexberry Designer с открытием указанной стадии
Есть возможность сделать несколько ярлыков на [Flexberry Designer](flexberry-designer.html), каждый из которых открывал бы разные стадии.

Для этого нужно настроить ярлык следующим образом: аргументом для запуска нужно передать параметр `path:Путь до стадии`

Например, если путь до стадии "`Репозитарий\Проект\Конфигурация\Стадия\`", то строка для запуска будет иметь вид
```
Flexberry.exe path:Репозитарий\Проект\Конфигурация\Стадия\
```

![](/images/pages/img/page/FlexberryDesignerRunningWithTheOpeningOfStage/Flexberry.exe path.JPG)

(((<msg type=note head='Путь для запуска'>В качестве пути может быть указан репозитарий, проект, конфигурация, стадия или система. Путь можно скопировать из выпадающего строки пути во [Flexberry Designer](flexberry-designer.html).</msg>)))
