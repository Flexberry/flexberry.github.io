---
title: Диаграмма классов (Class diagram)
sidebar: product--sidebar
keywords: Flexberry Designer, Public, Черновик статьи
toc: true
permalink: ru/class-diagram.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry Designer](flexberry-designer.html)
* '''Компонент''': [Редактор UML-диаграмм](editing-diagram.html)
* '''Предназначение''': Диаграмма классов является ключевым элементом [редактора UML-диаграмм](editing-diagram.html), поскольку зачастую приложения генерируются именно с диаграммы классов.
</td>
</tr></tbody></table></a>
</div>
# Диаграмма классов (Class diagram)
Диаграмма классов - один из доступных [видов диаграмм](editing-diagram.html), поддерживаемых [Flexberry Designer](flexberry-designer.html).

Диаграмма классов является ключевым элементом [редактора UML-диаграмм](editing-diagram.html), поскольку зачастую приложения генерируются именно с диаграммы классов.
Диаграмма классов - это набор статических, декларативных элементов модели. Диаграммы классов могут применяться и при прямом проектировании, то есть в процессе разработки новой системы, и при обратном проектировании - описании существующих и используемых систем. Информация с диаграммы классов напрямую отображается в исходный код приложения. Таким образом, диаграмма классов - конечный результат проектирования и отправная точка процесса разработки.

# Особенности построения диаграмм классов
Основные особенности построения диаграмм классов указаны в следующих статьях:
* [Ключевые понятия объектной структуры для прикладных систем, разрабатывающихся в Flexberry Designer](key-concepts-flexberry-designer.html).
* [Особенности построения диаграммы классов](class-diagram-constraction.html)

# Возможности диаграмм классов
Основные возможности при работе с диаграммами указаны в статье [Редактор UML-диаграмм](editing-diagram.html).

Специфичные для диаграмм классов возможности:
* Изменение [стереотипа класса](key-concepts-flexberry-designer.html) через контекстное меню.
* Просмотр зависимостей через контекстное меню (функционирование аналогично отображению зависимостей класса в [менеджере классов](class--manager.html)).

# Основные элементы диаграммы классов
На диаграмме классов можно отобразить следующие элементы нотации UML, доступные в панели элементов: 


{| border="1" 
! Элемент/Нотация !! Предназначение
|-
| ![](/images/pages/img/Введение в Flexberry/assoc.jpg) || [Ассоциация](master--association.html) (Association);
|-
| ![](/images/pages/img/Введение в Flexberry/aggregation.jpg) || Агрегация (Aggregation);
|-
| ![](/images/pages/img/Введение в Flexberry/composition.jpg) || [Композиция](detail-associations-and-their-properties.html) (Composition);
|-
| ![](/images/pages/img/Введение в Flexberry/inheritance.jpg) || [Наследование](inheritance.html)/обобщение (Inheritance/generalization);
|-
| ![](/images/pages/img/Введение в Flexberry/implement.jpg) || Реализация (Realization);
|-
| ![](/images/pages/img/Введение в Flexberry/nested.jpg) || Ассоциация вложенного класса (Nested class association);
|-
| ![](/images/pages/img/Введение в Flexberry/class.jpg) || Класс (Class);
|-
| ![](/images/pages/img/Введение в Flexberry/templateclass.jpg) || Класс-шаблон (Template class);
|-
| ![](/images/pages/img/Введение в Flexberry/instance.jpg) || Экземпляр класса (instance);
|-
| ![](/images/pages/img/Введение в Flexberry/activeobject.jpg) || Активный объект (Active object);
|-
| ![](/images/pages/img/Введение в Flexberry/multiobject.jpg)  || Мультиобъект (Multiobject);
|-
| ![](/images/pages/img/Введение в Flexberry/object.jpg) || Объект (Property object);
|-
| ![](/images/pages/img/Введение в Flexberry/naryassoc.jpg) || N-арная ассоциация (N-ary association);
|-
| ![](/images/pages/img/Введение в Flexberry/naryconn.jpg) || Коннектор n-арной ассоциации (N-ary association connector);
|-
| ![](/images/pages/img/Введение в Flexberry/qlink.jpg) || Квалифицированная ассоциация (Qualified link);
|-
| ![](/images/pages/img/Введение в Flexberry/qcomposition.jpg) || Квалифицированная композиция (Qualified composition link);
|-
| ![](/images/pages/img/Введение в Flexberry/qaggregation.jpg) || Квалифицированная агрегация (Qualified aggregation link);
|-
| ![](/images/pages/img/Введение в Flexberry/moreclasses.jpg) || Ещё классы (More classes);
|-
| ![](/images/pages/img/Введение в Flexberry/dependency.jpg) || Зависимость (Dependency);
|-
| ![](/images/pages/img/Введение в Flexberry/package.jpg) || Пакет (Package);
|-
| ![](/images/pages/img/Введение в Flexberry/corner.jpg) || Точка изгиба связей (Point);
|-
| ![](/images/pages/img/Введение в Flexberry/note.jpg) || Комментарий (Note);
|-
| ![](/images/pages/img/Введение в Flexberry/noteconn.jpg) || Коннектор комментария (Note connector).
|}


{Discussuon}