---
title: Браузер репозиториев Flexberry Designer
sidebar: product--sidebar
keywords: Flexberry Designer, Public
toc: true
permalink: ru/flexberry-designer-repository-browser.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Предназначение''': Структурированное хранилище UML-моделей.
</td>
</tr></tbody></table></a>
</div>

# Назначение и структура
Репозиторий является универсальным хранилищем информации о моделях. Специальный браузер обеспечивает доступ к [структуре и элементам репозитория](recommended-structure-repository-and--placing-diagrams.html).

На иллюстрации представлен внешний вид браузера:

![](/images/pages/img/page/FlexberryDesignerRepositoryBrowser/RepositoryBrowser.png)

В левой части отображается [структура репозитория по уровням](recommended-structure-repository-and--placing-diagrams.html), в правой - содержимое выбранного в левой части уровня.

Репозиторий представляет собой хранилище всей информации о моделях. Информация в репозитории хранится в [иерархическом виде](recommended-structure-repository-and--placing-diagrams.html).

# Пользовательский интерфейс
Поле "Размещение" показывает текущий путь внутри репозитория.


Назначение кнопок панели инструментов браузера.

![](/images/pages/img/page/FlexberryDesignerRepositoryBrowser/repbrowsertoolbar.jpg)

# Создание/удаление уровней репозитория
* Для создания нового объекта (проекта, конфигурации, фазы, системы) необходимо перейти на соответствующий уровень в браузере (например, для создания системы необходимо перейти на уровень фазы) и нажать кнопку ![](/images/pages/img/Введение в Flexberry/newbtn.jpg). В появившемся окне необходимо ввести имя и описание создаваемого объекта.
* Для переименования или редактирования свойств уровня, необходимо выбрать его мышкой и нажать кнопку редактирования свойств объекта ![](/images/pages/img/Введение в Flexberry/propertiesbtn.jpg). 
В появившемся окне отредактируйте свойства.
*Для удаления объекта необходимо выбрать его мышкой в правой половине окна браузера и нажать кнопку ![](/images/pages/img/Введение в Flexberry/delbtn.jpg).

## Горячие клавиши
Нажатие этих клавиш, при выделенной строке уровня вызовет следующее действие:



* '''Enter''' - переход на уровень ниже;
* '''F2''' - открытие редактирования свойств выделенного уровня. Аналогично нажатию ![](/images/pages/img/Введение в Flexberry/propertiesbtn.jpg);
* '''Delete''' - удаление объекта. Аналогично нажатию ![](/images/pages/img/Введение в Flexberry/delbtn.jpg).

<msg type=caution> Горячие клавиши работают только в центральном окне (в списке слева не работают). </msg>

# Дополнительные возможности браузера репозитариев
* [Ярлыки для диаграмм в Flexberry Designer](flexberry-designer-diagram-links.html)
* [Импорт стадий и диаграмм](import-crp-csdg.html)
* [Работа с диаграммами в браузере репозитория](working-with-diagrams-in-the-repository-browser.html)
