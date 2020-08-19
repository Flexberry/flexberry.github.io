---
title: GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit
summary: Properties, change values, the display of the wizard, customize the appearance, toolbar, and sorting
toc: true
permalink: en/fw_group-edit.html
lang: en
autotranslated: true
hash: 33b465045a07380c5573e28075a112dd629bfd38c913ef355b7dba3c2c8d3645
---

`GroupEdit` - Winforms-control for creating and editing [datalow](fo_detail-associations-properties.html).

### Properties GroupEdit

| Property | Description |
| ------------- | ------------- |
| `AdvansedMarks` | Adding extra control buttons to highlight rows and labels on the control panel GroupEdit. |
| `AllowRowLocking` | Support edit mode with the locks. |
| `AlternativeColor` | Alternative color for coloring lines. |
| `BackOnShiftEnter` | move to the next cell by pressing `Shift` `Enter` in the enabled mode `EditOnEnter`.
| `DoNotAutoLoadItems` | do Not load the data from the database automatically.
| `EnableValueDisplayResponsibility` | mode Support `ValueDisplayResponsibility`.
| `EditOnEnter` | Edit contents of a cell pressing `Enter`
| `GenerateValueChangedEventOnrowoperations` | Generate change events when adding or deleting a row.
| `KeepFocus` | default is "false". If set to true, then the save will be focused on the same line where it was before the save. Remembered not the line number, and the ID of the selected object.
| `LeaveOnLastEnter` | go to next control when you press the `Enter` in the last line.
| `MoveNextOnEnter` | go to next cell by pressing `Enter` when this mode is enabled `EditOnEnter`.
| `NewRowOnInsert` | go to first cell of new row when clicking `Enter`.
| `NextOnEnter` | Move the active cell by pressing `Enter` in the enabled mode `EditOnEnter`.
| `ReadOnly` | Mode "read only".
| `SortOrder` | adjustment column to sort: Asc (ascending) Desc (descending), None (no sorting).
| `SortPriority` | setting the sort priority column.
| `ShowStatusBar` | bar Display state, showing the number of items.
| `UseAlernativeColoring` | Use alternating color rows (base/alternative color). |

### Change the values in the control

In order to perform the setting control stranovogo in cell `GroupEdit`, you need to use the `gr_SetupEditorEventHandler` event handler which is automatically generated in the edit forms. In the arguments of the event are passed a data object, control and name currently being edited properties.

Example:

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

## Display wizard in GroupEdit

If you want to display in the cell `GroupEdit`» «presentation attribute of the master (i.e., a certain expression of the attributes of the wizard), you can use one of the following solutions.

1. Override method `ToString()` the data object. The peculiarity of this solution is that the override will affect everywhere, where the method is used `ToString()`.
2. To implement the control that is associated with the column `GroupEdit` interface `IValueDisplayResponsible`. PstrfGetDisplayValue` the only method of this interface should return displayed in the cell value. PstrfGroupEdit` have to set the property `EnableValueDisplayResponsibility` in `true`.

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

## Customize ToolBar in GroupEdit

Feature configure toolbar in `GroupEdit` is that after specifying the necessary operations in the designer, you must close it and open again - the vertical dimension in this case will be recalculated.

## Sorting

As [ObjectListView](fw_objectlistview.html) `GroupEdit` allows you to sort on various columns. To quickly configure multi-level sorting, you can click on the columns button with the left mouse, holding down the `Ctrl`.

To multi-level sorting is not reset when accidentally clicking on the column, added a clarifying question about the change sort.

## Named display settings column

There is the ability to save named settings in which the columns appear by analogy with `ObjectListView`. The settings are stored in the database separately for each user.

{% include note.html content="This option only works when the setting `UseSettings = true` in the configuration file." %}

## Drawing cell borders

To make the frame in `GroupEdit` using the following code:

```csharp
C1FlexGrid ge = GetGridFromGE(Лапа);
ge.Styles.Normal.Border.Direction = BorderDirEnum.Both;
ge.Styles.Normal.Border.Style = BorderStyleEnum.Flat;
```

`GroupEdit` with drawn borders will look like the following:

![Example GroupEdit with drawn borders](/images/pages/products/flexberry-winforms/controls/groupedit/groupedit-explain.png)

## EditManager

Usually `GroupEdit` is on [edit form](fd_editform.html) with your `EditManager`. However, `GroupEdit'а` __your__ `EditManager` responsible for binding and events.

For example, in order to catch the event of the return value when you select master, you must subscribe to the event `AfterChangeProperty` EditManager related to `GroupEdit`, and not to the edit page:

```csharp
GroupEdit1.EditManager.AfterChangeProperty += (o, s) =>
{
    // Handlers 
};
```

{% include note.html content="it's Worth noting that the event `AfterChangeProperty` when choosing a master work dwaggy: 1st time you press the button [lucapa](fa_lookup-overview.html), and the 2nd time the return value."%}

In `GroupEdit` added status bar that displays the number of items.

To enable the display of the strip condition, you need to set the property `ShowStatusBar = true;`

## Processing keystrokes controls

Interface `ISpecialKeysEditable` intended to convey the controls editing keys pressed non-alphanumeric. For example, control clicking `F3` raises the list to select a value. The handler defined in the control, only works when the control is in the editing state. Implementation of control `ISpecialKeysEditable` allows you to bring the key pressed in the control of GE. While the focus on the GE cell and press these keys, the control goes into edit mode, and then will be transferred to the pressed key.

The interface contains a single method `List<Keys> GetSpecialEditKeys()`, which should return the list of source control combinations.

A sample implementation of the interface is presented below. Processed combination `F2 Shift Ctrl` and `F3`.

```csharp
        #region ISpecialKeysEditable Members

        public List<Keys> GetSpecialEditKeys()
        {
            return new List<Keys> { Keys.F2 | Keys.Shift| Keys.Control , Keys.F3 };
        }

        #endregion
```

## Useful links for GroupEdit

* Some frequently asked questions was published in article [FAQ in introductory training](gbt_initial-trainig-faq.html) and in [WinForms UI FAQ](fw_winforms-ui-faq.html).
* [Edit locking individual records in `GroupEdit`](fw_lock-rows-in-groupedit.html).
* [Date format GroupEdit](fw_groupedit-date-format.html)
* [Getting FlexGrid from GroupEdit](fw_flex-grid.html).
* Event handling:
* [The events in GroupEdit](fw_events-groupedit.html).
* [Restriction-type-lucapa-combo-in-GroupEdit](fw_restriction-type-lookup-combo-in-groupedit.html).
* [Functionality when working with arrays of objects detailbug DetailArray](fo_functionality-work-detail-array.html).
* [The imposition of restrictions on GroupEdit](fw_add-limit-to-groupedit.html).

## Extensions GroupEdit

For `GroupEdit` there are a number of extensions, for example:

* [GEEditorExt](fw_ge-editor-ext.html) (edit detailov in a separate window).
* [GEEmptyDetailRemover](fw_ge-empty-detail-remover.html) (delete blank lines).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}