---
title: Редактор диаграмм Flexberry Designer Enterprise
sidebar: guide-practical-guides_sidebar
keywords: guide, flexberry designer enterprise, enterprise, online, онлайн, редактор, диаграмма, классов
toc: true
permalink: ru/gpg_editor-guide.html
lang: ru
---

## О редакторе
В данном руководстве вы узнаете как пользоваться редактором. 

По умолчанию диаграммы открываются в режиме **просмотра**. Чтобы перейти в режим **редактирования**, нажмите ![Правка](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon-readonly3.png){:style="margin: 0"} в панели управления.

![Внешний вид редактора](/images/pages/guides/flexberry-designer-enterprise/editor-guide.png)

## Панель управления диаграммой
В режиме **просмотра** доступны действия:

| Иконка | Кнопка | Описание действия |
| :---: | --- | --- |
| ![UML валидатор](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon-readonly1.png){:style="margin: 0"} | UML валидатор | Проверяет диаграмму на ошибки |
| ![UML корректор](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon-readonly2.png){:style="margin: 0"} | UML корректор | Автоматически исправляет ошибки |
| ![Правка](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon-readonly3.png){:style="margin: 0"} | Правка | Переводит диаграмму в режим редактирования |
| ![Копировать](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon-readonly4.png){:style="margin: 0"} | Копировать | Позволяет копировать выбранные элементы на любую диаграмму |

В режиме **редактирования** доступны действия:

| Иконка | Кнопка | Описание действия |
| :---: | --- | --- |
| ![Дополнительные настройки](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon1.png){:style="margin: 0"} | Дополнительные настройки | Позволяет задать каталог и описание. |
| ![Сохранить диаграмму](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon2.png){:style="margin: 0"} | Сохранить диаграмму | Сохраняет объекты. Переводит диаграмму в режим просмотра. |
| ![Удалить диаграмму](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon3.png){:style="margin: 0"} | Удалить диаграмму | Удаляет диаграмму. |
| ![Отмена / повтор шага](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon4.png){:style="margin: 0"} | Отмена / повтор шага | Отменяет / возвращает последнее действие. Работает аналогично комбинациям Ctrl+Z/Ctrl+Y. |
| ![Вырезать / копировать выделенные элементы](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon5.png){:style="margin: 0"} | Вырезать / копировать выделенные элементы | Позволяет копировать элементы внутри/между диаграммами. |
| ![Печать диаграммы](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon6.png){:style="margin: 0"} | Печать диаграммы | Открывает стандартное меню печати в новой вкладке. |
| ![Генерировать код для выделенных классов](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon7.png){:style="margin: 0"} | Генерировать код для выделенных классов | Позволяет генерировать код для выбранных классов. |
| ![Копировать ссылку](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon8.png){:style="margin: 0"} | Копировать ссылку | Позволяет поделиться ссылкой на диаграмму. |
| ![Развернуть](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon9.png){:style="margin: 0"} | Развернуть | Разворачивает окно редактора на весь экран. |
| ![Закрыть](/images/pages/guides/flexberry-designer-enterprise/editor-panel/icon10.png){:style="margin: 0"} | Закрыть | Проверяет, есть ли несохранённые изменения, закрывает диаграмму. |

## Рабочая область
- Чтобы нарисовать объект, кликните по иконке объекта в палитре, а затем по рабочей области.
- Чтобы нарисовать связь, кликните по иконке связи в палитре, затем по первому и второму связываемому объекту. Если строится связь между классами, первый выбранный объект будет мастером (т.е. связь будет идти от мастера ко второму объекту `1 - *`).

## Палитра
Описание элементов палитры можно посмотреть на странице соответствующей диаграммы:
1. [Диаграмма классов (Class diagram)](fd_class-diagram.html)
2. [Диаграмма активностей (Activity diagram)](fd_activity-diagram.html)
3. [Диаграмма сотрудничества (Collaboration diagram)](fd_collaboration-diagram.html)
4. [Диаграмма развёртывания (Deployment diagram)](fd_deployment-diagram.html)
5. [Диаграмма последовательности (Sequence diagram)](fd_sequence-diagram.html)
6. [Диаграмма состояний (Statechart diagram)](fd_statechart-diagram.html)
7. [Диаграмма вариантов использования (UseCase diagram)](fd_use-case-diagram.html)

## Горячие клавиши
- **`Ctrl+C`** (копировать), **`Ctrl+V`** (вставить), **`Ctrl+X`** (вырезать)
- **`Ctrl+Z`** (отменить / шаг назад), **`Ctrl+Y`** (вернуть / шаг вперёд)
- **`Ctrl+A`** (выделить всё), **`Ctrl+Click`** (выделить несколько элементов)
- **`Ctrl+S`** (сохранить, не переводя в режим просмотра)
- **`Tab`** (переход к следующей ячейке) 
- **`Delete`** (удалить выбранные элементы)

Для работы горячих клавиш, фокус должен быть на редакторе.

## Внешний вид
Редактор поддерживает тёмную тему. Чтобы изменить тему, перейдите на вкладку `Настройка` -> `Настройки дизайнера` -> `Тема`.

## Полезные ссылки
- [Практическое руководство по созданию приложения с помощью Flexberry Designer Enterprise](gpg_practical-guide.md)
