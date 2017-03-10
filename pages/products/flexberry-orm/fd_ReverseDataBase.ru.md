---
title: Создание диаграммы классов по базе данных
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM
toc: true
permalink: ru/fd_reverse-data-base.html
folder: products/flexberry-designer/
lang: ru
---

# Функционал
Данная функция позволяет создавать диаграммы классов по базе данных.

# Использование
# Создать новую Стадию.
# Выделить Стадию, зайти в [ORM -> SQL](configure--m-s--s-q-l--server-direct-generator.html), указать путь к базе данных.
# Создать новый System в этой Стадии.
# Выделить System, в меню [FlexberryOrmCasePlugin.ashx#SQL_22|ORM -> SQL -> Microsoft SQL] выбрать пункт Построить диаграммы по БД.(((![](/images/pages/img/page/ReverseDataBase/ReengineeringPlugin.png))))
# Дождаться выполнения операции.

(((<msg type=note>При создании диаграмм по базе данных все [детейловые](fo_detail-associations-and-their-properties.html) связи будут интерпретироваться как [Ассоциации](fo_master-association.html) 1 - * (это связано с тем, что на уровне БД нет разницы между мастеровыми и детейловыми связями).</msg>)))

(((<msg type=note>При создании диаграммы стандартным образом обрабатываются типы параметров, которые были указанны по умолчанию [в карте типов SQL](fd_types-map.html).

Например:
тип '''VARCHAR(255)''' есть по умолчанию в карте типов SQL, поэтому будет преобразован на диаграмме классов в '''string'''. Однако в этой карте нет типа '''VARCHAR(25)''', поэтому будет создан [typedef](classes-with-stereotype--typedef.html) '''VARCHAR25''', который [в карте типов SQL](types-map.html) будет замаппирован на '''VARCHAR(25)''', а в карте [генератора C#-кода](flexberry-orm-case-plugin.html) - на '''string'''.

Также для особой обработки типов параметров возможно [добавить их в соответствующие карты типов C# и SQL](classes-with-stereotype--typedef.html) '''перед''' созданием диаграммы классов по БД.</msg>)))

# Пример создания диаграммы классов по базе данных

# Создаем диаграмму классов, на основе которой будет создана база:(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-0.png))))
# Выделяем стадию и [указываем путь к базе данных](configure--m-s--s-q-l--server-direct-generator.html) (в данном случае будет использоваться база с именем A-Test-DB).
# Генерируем скрипт изменения базы данных:(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-3.png))))
и применяем его на A-Test-DB.
# Создаем стадию для генерации диаграммы классов по базе данных, [указываем путь к базе](configure--m-s--s-q-l--server-direct-generator.html) A-Test-DB.
# Создаем объект System.
# Выделяем объект System и генерируем диаграмму:(((![](/images/pages/img/page/ReverseDataBase/ReengineeringPlugin.png))))
# Проверяем результат:

получили 4 диаграммы классов(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-1.png))))
'''Reverse:'''(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-2.png))))
'''Reverse Object1:'''(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-3.png))))
'''Reverse Object2:'''(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-4.png))))
'''Reverse Object3:'''(((![](/images/pages/img/page/ReverseDataBase/Reeng-step-7-5.png))))







