---
title: Задание заголовка класса в Расширенном редакторе ограничений
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/web-limit-editor-class-caption.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Расширенный редактор ограничений](advanced-limit-editor.html).
* '''Предназначение''': Описан способ задания заголовка класса в [редакторе ограничений](advanced-limit-editor.html).
</td>
</tr></tbody></table></a>
</div>



# Расширенный редактор ограничений
Эта статья описывает часть информации о [Расширенном редакторе ограничений](advanced-limit-editor.html).

# Задание заголовка класса в расширенном редакторе ограничений
В [расширенном редакторе ограничений](advanced-limit-editor.html) по умолчанию в качестве заголовка класса (корневая вершина в дереве доступных свойств) используется результат работы метода [Information](information-class-as-metadata-supervisor.html).GetClassCaption (метод возвращает либо заданный заголовок для класса, либо имя класса).

Если требуется задать некоторое отличное значение в качестве заголовка класса в [расширенном редакторе ограничений](advanced-limit-editor.html), то можно воспользоваться следующими способами:
# У соответствуюшего [WOLV](web-object-list-view.html) задать свойство `LimitEditorClassCaption`.
# если [расширенный редактор ограничений запускается без WOLV](limit-editor-without-w-o-l-v.html), то требуемое имя нужно указать у параметра, заносимого в сессию (`LimitEditorParam.ClassCaption`).

