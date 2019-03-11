--- 
title: Events ОbjectListView 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, controls, list, events 
summary: handle events, load events and the state of the list, properties, methods, 
toc: true 
permalink: en/fw_olv-event.html 
lang: en 
autotranslated: true 
hash: 6f0b345b3fdbb9e42cb5136e10dd65a8fd008c536a31be851768d86ebca3de85 
--- 

`ObjectListView` does not contain any functionality for actions (creation, preservation, etc.) with the data objects displayed in the list. The programmer must write code that catches» «events in `ObjectListView`, and work out an appropriate response. On the contrary, if there has been some action, you need to report it `ObjectListView` for adequate display of objects. 

To catch events, you must: 
* Hang up the handler on event `CreateObject` in the implementation of actions to create the data object, the event occurs when the user clicks the toolbar button create объекта; 
* Hang up the handler on event `EditObject` in the implementation of action to edit the data object, the event occurs when the user clicks the toolbar button edit объекта; 
* Hang up the handler on event `DeleteObject` in the implementation of actions to create the data object, the event occurs when the user clicks the toolbar button removal объекта; 

For information about `ObjectListView` occurred, you must: 

* Call method `AddObject` if the data object has been added, it will appear in `ObjectListView` (Important! The call, prior to processing the data object service data will result in an error, because `ObjectListView` will attempt to read object data from storage, where it does not); 
* Call method `UpdateObject` if the data object has been modified, then the fields in `ObjectListView` change the value to the appropriate object данных; 
* Call method `DeleteObject` if the data object was deleted, then it will disappear from `ObjectListView`. 

## load Events list 

### download Information list 

In the event handler `ОbjectListView.AfterFillData` there is a possibility to obtain information about the success of the download. As the event parameter is of type `EventArgs` is transferred to his heir `AfterFillDataEventArgs`. 
Type `AfterFillDataEventArgs` has three properties: 

```csharp
        /// <summary> 
        /// An exception occurred while downloading 
        /// </summary> 
        public Exception Exception { get; private set;}
        /// <summary> 
        /// Download canceled by the user 
        /// </summary> 
        public bool CanceledByUser { get; private set; }
        /// <summary> 
        /// Download completed successfully 
        /// </summary> 
        public bool IsFillSuccessfullyCompleted { get; private set; }
``` 

Example: 

```csharp
        private void objectListView1_AfterFillData(object sender, EventArgs e)
        {
            if (e is AfterFillDataEventArgs)
            {
                var afterFillDataEventArgs = e as AfterFillDataEventArgs;
                MessageBox.Show(
                    string.Format("CancelByUser: {0}, Exception: {1}, IsFillSuccessfullyCompleted: {2} ",
                    afterFillDataEventArgs.CanceledByUser, afterFillDataEventArgs.Exception, afterFillDataEventArgs.IsFillSuccessfullyCompleted));
            }
        }
``` 

### Tracking data is being loaded 

To track the load data in `ObjectListView` possible with the help of events `AfterFillData` and properties `IsDataLoaded`. Event `AfterFillData` occurs at the end of loading, and `IsDataLoaded` property is true if the data is loaded and `ObjectListView` is not in the status updates. 

## state of the list 

### row Selection 

To monitor changes in the status of a line (selected or not) `ObjectListView` possible with the help of events `MarkObjectChanged`. As the event argument is passed `DataObjectDef` of the object and the state of the row. 

```csharp
public event MarkObjectChangedEventHandler MarkObjectChanged;
``` 

`ObjectListView` does not contain any functionality for actions (creation, preservation, etc.) with the data objects displayed in the list. The programmer must write code that catches» «events in `ObjectListView`, and work out an appropriate response. On the contrary, if there has been some action, you need to report it `ObjectListView` for adequate display of objects. 

To catch events, you must: 
* Hang up the handler on event `CreateObject` in the implementation of actions to create the data object, the event occurs when the user clicks the toolbar button create объекта; 
* Hang up the handler on event `EditObject` in the implementation of action to edit the data object, the event occurs when the user clicks the toolbar button edit объекта; 
* Hang up the handler on event `DeleteObject` in the implementation of actions to create the data object, the event occurs when the user clicks the toolbar button removal объекта; 

For information about `ObjectListView` occurred, you must: 

* Call method `AddObject` if the data object has been added, it will appear in `ObjectListView` (Important! Do not call this method before processing the data object service data, as this may cause an error, because `ObjectListView` will attempt to read object data from storage, where it does not); 
* Call method `UpdateObject` if the data object has been modified, then the fields in `ObjectListView` change the value to the appropriate object данных; 
* Call method `DeleteObject` if the data object was deleted, then it will disappear from `ObjectListView`. 

### Display prompts for the individual lines 

In `ObjectListView` implemented the ability to display tooltips for individual lines. 
To enable this mode, you must set the property `ObjectListView.ShowToolTip`. 
To set the tooltip text you need to handle the event `ObjectListView.BeforeToolTipRequired`. To configure settings to display a tooltip (delay of occurrence, duration of display, appearance) should change the properties of an object `ObjectListView.ToolTip`. 

Example: 

This example displays the hint to the cells of the first 5 columns.

```csharp
private void objectListView1_BeforeToolTipRequired(object sender, BeforeToolTipRequiredEventArgs e)
{
     e.TipText = string.Format("Line {0}, column {1}", e.Row, e.Column);
     if (e.Column>5)
         e.Cancel = true;
}
``` 

## Additional events, properties and methods 

* `OnChangeCurrentObject` — occurs when the user selects an object (moves the cursor) in the list. 
* `ObjectDblClick` — proishodit where the current object is made `DblClick`. 
* `ObjectCount` — get the number of objects in the list. 
* `GetObject` — receiving the data object directly from the list by various criteria. 
* `FillData` — populate list data (update). 
* `HideToolBar` — show/hide the toolbar. 
* `UseToolBar` is to use a custom toolbar, some other, outside, it automatically» «spread buttons from `ObjectListView`. 
* `ClearCache` — cleaning. 
* `ObjectListView.BeforeRefresh` occurs before the data is updated in the list. Data refresh can be initiated when the user clicks a button to Update the» «or method call `Refresh`. 
* `SetObject` - method to add an object to the list. 
* `SetObjects` - method to add multiple objects to the list. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}