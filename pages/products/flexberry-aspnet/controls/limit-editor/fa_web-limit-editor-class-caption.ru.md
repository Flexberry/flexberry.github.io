---
title: Задание заголовка класса в расширенном редакторе ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-limit-editor-class-caption.html
lang: ru
---

В [расширенном редакторе ограничений](fa_advanced-limit-editor.html) по умолчанию в качестве заголовка класса (корневая вершина в дереве доступных свойств) используется результат работы метода [Information](fo_methods-class-information.html).GetClassCaption (метод возвращает либо заданный заголовок для класса, либо имя класса).

Если требуется задать некоторое отличное значение в качестве заголовка класса в [расширенном редакторе ограничений](fa_advanced-limit-editor.html), то можно воспользоваться следующими способами:

* У соответствуюшего [WOLV](fa_web-object-list-view.html) задать свойство `LimitEditorClassCaption`.
* если [расширенный редактор ограничений запускается без WOLV](fa_limit-editor-without-wolv.html), то требуемое имя нужно указать у параметра, заносимого в сессию (`LimitEditorParam.ClassCaption`).

