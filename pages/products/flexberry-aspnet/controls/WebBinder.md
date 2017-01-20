---
title: WebBinder
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/web-binder.html
folder: product--folder
lang: ru
---

# Описание

# Биндинг нескольких свойств
В папке ~/Xml/Bindings могут находится настройки для нестандартного биндинга.
Если на форме есть детейл, внутри которого находится контрол, который редактирует несколько свойств объекта, то нужно настраивать нестандартный биндинг.
<br />
Для того, чтобы дополнить стандартный биндинг своим, нужно элементу root добавить атрибут 'partial="true"'

Пример:
```
<?xml version="1.0" encoding="utf-8" ?>
<root partial="true">
  <detail name="МероприятияМедРеаб">
    <property name="Исполнитель">
      <control id="ctrlИсполнитель" prop="SelectedMasterPK">
      </control>
    </property>
    <property name="ИсполнительПроп">
      <control id="ctrlИсполнитель" prop="Text">
      </control>
    </property>
  </detail>
</root>
```

В примере описано, что в детейле 'МероприятияМедРеаб' находится контрол 'ctrlИсполнитель', который одновременно редактирует свойства 'SelectedMasterPK' и 'Text'
