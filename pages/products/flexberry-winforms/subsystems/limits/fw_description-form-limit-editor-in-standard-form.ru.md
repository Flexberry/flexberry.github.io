---
title: Стандартный вид редактора ограничений. Описание формы
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms
summary: Описание стандартного вида редактора ограничений с иллюстрациями
toc: true
permalink: ru/fw_description-form-limit-editor-in-standard-form.html
folder: products/flexberry-winforms/
lang: ru
---

## Стандартный вид

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/10.png)

1 – __«Доступные свойства»__ - свойства класса, доступные для создания
ограничения.


2 – __«Ограничение»__ - окно, предназначенное для
конструирования ограничения.


3 – __«Панель текста запроса»__ - окно, где отображается конечный
вид ограничения, написано в одну строку.


4 – __«Панель параметров»__ - окно для работы с параметрами
(переменными, которым можно присваивать некоторое значение в процессе работы со
списком), используемыми в ограничении. 


### Кнопки на форме

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/27.png)
- __Подсчитать количество строк__ - по нажатию выдается количество объектов, удовлетворяющих, соответствующих созданному ограничению (без применения ограничения к списку).
Будет выдано сообщение вида:

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/11.png)


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/28.png)

- __Добавить ограничение по полю__ – после нажатия на эту кнопку появляется окно «Создание ограничения»:


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/12.png)


В зависимости от типа выбранного поля появляется возможность
выбрать функции, подходящие конкретно для него. Например, для полей типа Дата
появляется параметр «Функции для дат» со следующими значениями:


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/13.png)


Колонка и имя свойства подставляются автоматически и равны
выбранному полю в окне «Доступные свойства». В этом окне нужно выбрать вид операции. 


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/42.png)


Далее нужно нажать на кнопку «…», «Выбрать из базы». Появится окошко:


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/14.png)


Нужно найти в списке значение из базы, необходимое для
составления ограничения. 


Для облегчения поиска нужного значения можно ввести в
верхней строке начальные буквы этого значения и нажать кнопку "Обновить". После этого будут показаны значения из базы, удовлетворяющие введенной строке. 


Выбрать нужное значение (двойным кликом или одним кликом по
значению и затем кнопку Вернуть значение). Окно «Выбор значения из базы»
закроется.


В окне «Создание ограничения» нужно нажать «Сохранить». Это
окно тоже закроется. Ограничение по заданному полю будет создано.

![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/29.png)
 
 - __Редактировать__ – откроется окно «Редактирование ограничения», аналогичное
окну «Создание ограничение», все параметры соответствуют созданному ограничению.


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/30.png)
 - __Удалить__ – выделенное ограничение в списке будет удалено.


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/31.png)
 - __Вырезать__ - выделенное ограничение в списке будет вырезано.


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/32.png)
 - __Вставить__ - скопированное/вырезанное ограничение будет вставлено в указанное
место.


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/33.png)
 - __Добавить ограничение И__ – ниже по иерархии относительно выделенной в дереве ветки будет добавлена ветка И. 


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/34.png)
 - __Добавить ограничение ИЛИ__ - ниже по иерархии относительно выделенной в дереве ветки будет добавлена ветка ИЛИ. 


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/35.png)
 - __Добавить ограничение НЕ__ – ниже по иерархии относительно выделенной в дереве ветки будет добавлена ветка НЕ. 


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/36.png)
 - __Вставлять И, ИЛИ, НЕ выше по иерархии__ – следующая ветка будет добавлена выше по иерархии относительно выделенной ветки.


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/40.png)
 - __Показать/скрыть панель текста запроса__ – панель текста запроса закроется, а панель с ограничением развернется на ее место. 


![](/images/pages/products/flexberry-winforms/subsystems/limits/limit-editor-form/41.png)
 - __Показать/скрыть панель параметров__ – панель параметров закроется, форма редактирования ограничения станет уже.