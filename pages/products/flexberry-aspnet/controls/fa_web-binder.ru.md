---
title: WebBinder
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_web-binder.html
lang: ru
---

`WebBinder` - компонент, осуществляющий связывание web-контролов на форме и значений объектов.

## Публичные методы

```xml
/// <summary>
/// Установить флаг доступности редактирования для контролов всей формы.
/// </summary>
/// <param name="controls">Корень коллекции контролов, в которой будут искаться контролы.</param>
/// <param name="view">Представление (будут управляться только контролы, редактирующие свойства из этого представления).</param>
/// <param name="readOnlyFlag">Значение свойства "Только для чтения".</param>
/// <param name="controlIdsComparer">Кастомная функция сравнения идентификаторов контролов, использующаяся при поиске контролов в дереве (если не задана, то производится посимвольное сравнение).</param>
public void SetReadOnlyForm(ControlCollection controls, View view, bool readOnlyFlag, Func<string, string, bool> controlIdsComparer = null)
```

```xml
/// <summary>
/// Установить флаг доступности редактирования для определённого свойства объекта данных
/// (Сначала будет попытка установить флаг ReadOnly, а потом Enabled.
/// В дальнейшем при получении данных из контрола, соответствующего свойству объекта, эти данные будут игнорироваться).
/// </summary>
/// <param name="controls">Корень коллекции контролов, в которой будет искаться нужный контрол.</param>
/// <param name="propName">Имя свойства в объекте данных.</param>
/// <param name="readOnlyFlag">Значение свойства "Только для чтения".</</param>
public void SetReadOnlyProperty(ControlCollection controls, string propName, bool readOnlyFlag)
```

```xml
/// <summary>
/// Установить контролу флаг доступности редактирования
/// (Сначала будет попытка установить флаг ReadOnly, а потом Enabled.
/// В дальнейшем при получении данных из контролов
/// эти контролы будут игнорироваться).
/// </summary>
/// <param name="ctrl">Контрол, которому устанавливаем флаг.</param>
/// <param name="readOnlyFlag">Флаг доступности редактирования.</param>
public void SetReadonlyToControl(Control ctrl, bool readOnlyFlag)
```

```xml
/// <summary>
/// Применить данные из объекта к контролам на странице.
/// </summary>
/// <param name="controls">Корень коллекции контролов, в которой будут искаться контролы.</param>
/// <param name="view">Представление.</param>
/// <param name="dataObject">Объект, из которого берутся данные.</param>
/// <param name="isPostBack">Флаг, показывающий происходит ли сейчас postback (в зависимости от этого флага будут, или наоборот не будут обновлены значения в лукапах).</param>
/// <param name="isSaving">Флаг, показывающий производится сейчас сохранение объекта данных.</param>
/// <param name="controlsIdComparer">Кастомная функция сравнения идентификаторов контролов, использующаяся при поиске контролов в дереве (если не задана, то производится посимвольное сравнение).</param>
public void ApplyDataToControls(ControlCollection controls, View view, DataObject dataObject, bool isPostBack, bool isSaving = false, Func<string, string, bool> controlsIdComparer = null)
```

```xml
/// <summary>
/// Проставляет заданным контролам, реализующим интерфейс ISaveActionCompatible,
/// заданое значение флага ISaveActionCompatible.IsSaveNow.
/// </summary>
/// <param name="controls">Корень коллекции контролов.</param>
/// <param name="view">Представление соответствующее объекту данных.</param>
/// <param name="isSaving">Желаемое значение флага ISaveActionCompatible.IsSaveNow, показывающее производиться ли сейчас сохранение объекта данных.</param>
public void ApplySavingFlagToSaveActionCompatibleControls(ControlCollection controls, View view, bool isSaving)
```

## Биндинг нескольких свойств

В папке ~/Xml/Bindings могут находится настройки для нестандартного биндинга. Если на форме есть детейл, внутри которого находится контрол, который редактирует несколько свойств объекта, то нужно настраивать нестандартный биндинг.  
Для того чтобы дополнить стандартный биндинг своим, нужно элементу `root` добавить атрибут `partial="true"`.

Пример:

```xml
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
