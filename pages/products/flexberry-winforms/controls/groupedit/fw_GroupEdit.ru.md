---
title: GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit
summary: Свойства, изменение значений, отображение мастера, настройка внешнего вида, тулбара и сортировки
toc: true
permalink: ru/fw_group-edit.html
lang: ru
---

`GroupEdit` - Winforms-контрол для создания и редактирования [детейлов](fo_detail-associations-properties.html).

### Свойства GroupEdit

| Свойство  | Описание |
| ------------- | ------------- |
| `AdvansedMarks`  | Добавление дополнительных кнопок управления выделением строк и меток на панель управления GroupEdit.  |
| `AllowRowLocking`  | Поддержка режима редактирования с блокировками.  |
| `AlternativeColor`  | Альтернативный цвет для раскраски строк.  |
| `BackOnShiftEnter` | Переход на следующую ячейку по нажатию `Shift` + `Enter` при выключенном режиме `EditOnEnter`.
| `DoNotAutoLoadItems` | Не загружать данные из базы автоматически.
| `EnableValueDisplayResponsibility` | Поддержка режима `ValueDisplayResponsibility`.
| `EditOnEnter` | Редактирование содержимого ячейки по нажатию на клавишу `Enter`
| `GenerateValueChangedEventOnRowOperations` | Генерировать события изменения при добавлении или удалении строки.
| `KeepFocus` | По умолчанию "false". Если установлено "true", то при сохранении будет установлен фокус на той же строке, где он был до сохранения. Запоминается не номер строки, а идентификатор выделенного объекта.
| `LeaveOnLastEnter` | Переходить к следующему контролу при нажатии на клавишу `Enter` в последней строке.
| `MoveNextOnEnter` | Переходить к следующей ячейке по клавише `Enter` при включенном режиме `EditOnEnter`.
| `NewRowOnInsert` | Переходить на первую ячейку новой строки при нажатии `Enter`.
| `NextOnEnter` | Перемещать активную ячейку по клавише `Enter` при выключенном режиме `EditOnEnter`.
| `ReadOnly` | Режим "только чтение".
| `SortOrder` | Настройка сортировки столбца: Asc (по возрастанию), Desc (по убыванию), None (без сортировки).
| `SortPriority` | Настройка приоритета сортировки столбца.
| `ShowStatusBar` | Отображение полосы состояния, на которой показывается количество элементов.
| `UseAlernativeColoring` | Использовать чередующуюся окраску строк (базовый/альтернативный цвет). |

### Изменение значения в контроле

Для того чтобы выполнить настройку контрола, встраемового в ячейку `GroupEdit`, нужно использовать событие `gr_SetupEditorEventHandler`, обработчик которого автоматически генерируется на формах редактирования. В аргументы данного события передаются объект данных, контрол и имя редактируемого в данный момент свойства.

Пример:

```csharp
protected override void gr_SetupEditorEventHandler(object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e)
{
    // *** Start programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ))
    // *** End programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ))
    base.gr_SetupEditorEventHandler(sender, e);
    // *** Start programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ) End)
    DateTimePicker dateTimePicker = e.control as DateTimePicker;
    ICSSoft.STORMNET.DataObject dataObject = e.dataObject;
    string propertyName = e.propertyName;

    if (dateTimePicker != null && dataObject.GetStatus() == ObjectStatus.Created)
    {
        dateTimePicker.Value = DateTime.Now.AddMonths(1);
    }

    // *** End programmer edit section *** (gr_SetupEditorEventHandler( object sender, ICSSoft.STORMNET.Windows.Forms.SetupEditorEventArgs e ) End)
}
```

## Отображение мастера в GroupEdit

При необходимости отображения в ячейке `GroupEdit` «презентационного» атрибута мастера (т.е. некоторого выражения из атрибутов мастера) можно воспользоваться одним из описанных ниже решений.

1. Переопределить метод `ToString()` у объекта данных. Особенность данного решения в том, что переопределение повлияет всюду, где используется метод `ToString()`.
2. Реализовать у контрола, который связан со столбцом `GroupEdit`, интерфейс `IValueDisplayResponsible`. Единственный метод  `GetDisplayValue` данного интерфейса должен вернуть отображаемое в ячейке значение. У `GroupEdit` установить свойство `EnableValueDisplayResponsibility` в `true`.

```csharp
#region IValueDisplayResponsible Members

public string GetDisplayValue(ICSSoft.STORMNET.DataObject dataObject)
{
  if (curObject == null)
    return string.Empty;

    return string.Format(Формат,
    Tools.IsNull(ICSSoft.STORMNET.Information.GetPropValueByName(curObject, Наименование), "").ToString(),
    Tools.IsNull(ICSSoft.STORMNET.Information.GetPropValueByName(curObject, Код), "").ToString()).TrimStart();
}
#region IValueDisplayResponsible Members
```

## Настройка ToolBar в GroupEdit

Особенностью настройки тулбара в `GroupEdit` вляется то, что после указания необходимых операций в дизайнере, необходимо закрыть его и открыть снова - вертикальный размер при этом будет пересчитан.

## Сортировка

Как и [ObjectListView](fw_objectlistview.html) `GroupEdit` позволяет отсортировать содержимое по различным столбцам. Чтобы быстро настроить многоуровневую сортировку, можно щелкать по столбцам левой кнопокой мыши, зажав клавишу `Ctrl`.

Чтобы многоуровневая сортировка не сбросилась при случайном нажатии на столбец, добавлен уточняющий вопрос о смене сортировки.

## Именованные настройки отображения столбцов

Существует возможность сохранения именованых настроек отображения столбцов по аналогии с `ObjectListView`. Настройки сохраняются в базе данных отдельно для каждого пользователя.

{% include note.html content="Данная опция работает только при включенной настройке `UseSettings = true` в файле конфигурации." %}

## Прорисовка границ ячеек

Сделать рамки в `GroupEdit` можно, используя следующий код:

```csharp
C1FlexGrid ge = GetGridFromGE(Лапа);
ge.Styles.Normal.Border.Direction = BorderDirEnum.Both;
ge.Styles.Normal.Border.Style = BorderStyleEnum.Flat;
```

`GroupEdit` с прорисованными границами будет выглядеть следующим образом:

![Пример GroupEdit с прорисованными границами](/images/pages/products/flexberry-winforms/controls/groupedit/groupedit-explain.png)

## EditManager

Обычно `GroupEdit` располагается на [форме редактирования](fd_editform.html), имеющей свой `EditManager`. Однако, у `GroupEdit'а` есть __свой__ `EditManager`, отвечающий за связывание и события.

К примеру, для того чтобы отловить событие возврата значения при выборе мастера, то необходимо подписаться на событие `AfterChangeProperty` EditManager, относящегося к `GroupEdit`, а не к странице редактирования:

```csharp
GroupEdit1.EditManager.AfterChangeProperty += (o, s) =>
{
    // Обработчики
};
```

{% include note.html content="Стоит учесть, что событие `AfterChangeProperty` при выборе мастера сработает _дважды_: 1ый раз при нажатии на кнопку [лукапа](fa_lookup-overview.html), а 2ой раз при возврате значения." %}

В `GroupEdit` добавлена полоса состояния, отображающая количество элементов.

Чтобы включить отображение полосы состояния, необходимо установить свойство `ShowStatusBar = true;`

## Обработка нажатий клавиш контролами

Интерфейс `ISpecialKeysEditable` предназначен для передачи контролам редактирования нажатых клавиш, не являющихся алфавитно-цифровыми. Например, контрол при нажатии `F3` поднимает список для выбора значения. Обработчик, определённый в контроле, срабатывает только, когда контрол находится в состоянии редактирования. Реализация контролом `ISpecialKeysEditable` позволяет предать нажатые клавиши в контрол из GE. При нахождении фокуса на ячейке GE и нажатии указанных клавиш контрол перейдет в режим редактирования, и затем ему будут переданы нажатые клавиши.

Интерфейс содержит единственный метод `List<Keys> GetSpecialEditKeys()`, который должен вернуть список обрабатываемых контролом сочетаний.

Пример реализации интерфейса представлен ниже. Обрабатываются сочетания `F2+Shift+Ctrl` и `F3`.

```csharp
        #region ISpecialKeysEditable Members

        public List<Keys> GetSpecialEditKeys()
        {
            return new List<Keys> { Keys.F2 | Keys.Shift| Keys.Control , Keys.F3 };
        }

        #endregion
```

## Полезные ссылки по GroupEdit

* Некоторые часто задаваемые вопросы освящены в статье [FAQ по вводному обучению](gbt_initial-trainig-faq.html), а также в статье [WinForms UI FAQ](fw_winforms-ui-faq.html).
* [Блокировка редактирования отдельных записей в `GroupEdit`](fw_lock-rows-in-groupedit.html).
* [Формат даты в GroupEdit](fw_groupedit-date-format.html)
* [Получение FlexGrid из GroupEdit](fw_flex-grid.html).
* Обработка событий:
  * [События в GroupEdit](fw_events-groupedit.html).
* [Ограничение-тип-лукапа-combo-в-GroupEdit](fw_restriction-type-lookup-combo-in-groupedit.html).
* [Функциональность при работе с массивами детеиловых объектов DetailArray](fo_functionality-work-detail-array.html).
* [Наложение ограничений на GroupEdit](fw_add-limit-to-groupedit.html).

## Расширения GroupEdit

Для `GroupEdit` существует ряд расширений, например:

* [GEEditorExt](fw_ge-editor-ext.html) (редактирование детейлов в отдельном окне).
* [GEEmptyDetailRemover](fw_ge-empty-detail-remover.html) (удаление пустых строк).
