---
title: Модули расширения функциональности Flexberry Designer
sidebar: flexberry-designer_sidebar
keywords: CASE Plugins, Flexberry Designer, Public
toc: true
permalink: ru/fd_case-plugins.html
folder: products/flexberry-designer/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Предназначение''': Расширение функциональных возможностей Flexberry Designer.
</td>
</tr></tbody></table></a>
</div>

# Модули расширения Flexberry Designer
## Что такое модули расширения
Модули расширения добавляют функциональность во `[Flexberry Designer](flexberry-designer.html)`, предоставляя новые возможности. Модулем может быть генератор исходного кода, утилита поиска, импорта-экспорта и т.п.

При использовании модулей расширения появляются дополнительные пункты меню при выборе репозитарных объектов Flexberry Designer: [Конфигурации, Стадии или Системы](recommended-structure-repository-and--placing-diagrams.html).

Модули хранятся в библиотеках (DLL) и располагаются в папке с исполняемым файлом `Flexberry Designer`.

# Как подключить модуль
Перед использованием модулей, их следует подключить к `Flexberry Designer`:
# Зарегистрировать модули в базе, в которой хранятся репозитарии моделей;
# Для конкретного репозитария (или Проекта, или Конфигурации, или Стадии) выбрать из зарегистрированных модулей, с которыми ведётся работа в рамках соответствующего репозитария (Проекта, Конфигурации или Стадии).

## Регистрация модулей в базе репозитариев
# Выберите в меню пункт Полезности\Модули:
![](/images/pages/img/Введение в Flexberry/pluginsreg.jpg)
# Нажмите кнопку Создать в панели инструментов списка модулей:
![](/images/pages/img/Введение в Flexberry/addplugin.jpg)
# Выберите модуль - динамически подключаемую библиотеку (*.dll), где расположен модуль;
# Повторите с пункта 2, если нужно зарегистрировать несколько модулей.

## Выбор модуля для репозитария, проекта, конфигурации или стадии
# Откройте форму редактирования свойств репозитария, проекта, конфигурации или стадии:
![](/images/pages/img/Введение в Flexberry/editrepprop.jpg)
# Выберите необходимые модули:
![](/images/pages/img/Введение в Flexberry/propeditselectmodules.jpg)
# Нажмите в панели инструментов кнопку Сохранить.
# Модули подключены.

# Стандартные модули Flexberry Designer
* [Модуль расширения Flexberry ORM](flexberry-orm-case-plugin.html)

# Как реализовать свой модуль расширения Flexberry Designer
[Как создать свой модуль расширения для Flexberry Designer описывается в отдельной статье.](flexberry-designer-plugins-development.html)
