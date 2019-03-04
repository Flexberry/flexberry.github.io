--- 
title: Control desktop 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: the basic configuration tools, desktop applications. Namely, 1) States how to set background picture desktop, 2)described the context menu, 3)describes how to specify the column on which grouping is performed on desktop items, 4) describes how to replace the default icons for folders, custom forms and edit forms, 5) lists the methods that force an update `ListView`, 6) describes the Column and Item, we consider a class of ItemInListView, 7)describes how to override the event `TreeViewItemSelected` to perform a specific action with the re-election item in the treeView, 8) lists what user settings are saved to the desktop tables, and indicates how to manage their conservation 
toc: true 
permalink: en/fw_objectlistview-in-desktop-ctrl.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 819fbfe91b049edc7474a0c40e6029a60fa312d499505931a612a0dfce75bd81 
--- 

## Picture on the desktop 

The background picture of the desktop set by the property `listView1.OverlayImage.Image`. You can choose any picture format "gif, jpg, jpeg, bmp, wmf, png". You can set an animated image, but to animate it will not. After changing image will need to update the method call `RefreshOverlay`: 

```csharp
listView1.RefreshOverlay(listView1.OverlayImage)
``` 

Alignment of images is performed by setting the required value to a property `OverlayImage.Alignment` (type - `System.Drawing.ContentAlignment`), then it is recommended to update the picture by the above method. 

To change the transparency of the picture you need to change the field value `OverlayImage.Transparency`. It takes values from 0 (full transparency) to 255 (completely opaque), the default value is 128. 

### Example 

```csharp
listView1.OverlayImage.Image = Image.FromFile("D:\\images\\limeleaf.png");
listView1.OverlayImage.Alignment = ContentAlignment.BottomLeft;
listView1.OverlayImage.Transparency = 50;
``` 

![](/images/pages/products/flexberry-winforms/desktop/desktop-picture.png) 

Margins horizontally and vertically, respectively, are specified using attributes `OverlayImage.InsetX` and `OverlayImage.InsetY` (type - int) 

## Context menu 
Consider the context menu invoked by right-clicking the mouse in free space. 

![](/images/pages/products/flexberry-winforms/desktop/context-menu.png) 

* __Update__ – updates all the list items by current position - `listView1.BuildList(true)`. 
* __Tree display__ – hide/show the treeView (the property `treeView1.Visible`). 
* __Show folders__ – display/displaying a folder in a listView. 
* __Grouping the elements__ – to group/not group the items of the listView (property `ShowGroups`). 
* __Columns__ – choose columns to display in the listView. PstrfНазвание` column is always displayed.
* __Always combine in__ – choose a column that will always be group items (property `AlwaysGroupByColumn`). 

## Grouping desktop items 
Grouping is specified by the property `ShowGroups` (default `true`). 
The column to group by default is determined by the column that the ordering of the elements. A group name takes the value of this column from the corresponding element. 

For example, if all elements have different names, then sorting by name will display as many groups as all items in `listView`, each group will have one element. The group name will take the name of the contained element. 

You can specify the column by which grouping will occur always, even if sorted by another column. For this is the attribute `AlwaysGroupByColumn` (default `null`), it is assigned a corresponding column. 

### Example 

```csharp
listView1.ShowGroups = true;
listView1.AlwaysGroupByColumn = this.ItemType;
``` 

![](/images/pages/products/flexberry-winforms/desktop/desktop-group.png) 

## change icons of the list items 
If you are not satisfied with the standard icons for folders, custom forms and edit forms, replace them as follows: 
* Replacement of the entire group of icons. <br / >class instance `Runner` box for pictures `imagecache` should be `null` (is the default). Then the picture is taken from `imageListRunners` and `imageListRunnersLarge` (to display small (16x16) large (32x32) icons, respectively). As the key uses the following values: ""IconFolder"" folder ""IconForm"" – custom shape, ""IconEdit"" edit, ""IconFolderUp"" – to level up. In a custom application icon changes using the same name field of the component `DesktopCtrl`, with the exception of ""IconFolderUp"" – here it can not be replaced. 
* Replacement icons for the specific instance of the class `Runner` (custom form or edit form). the <br>Replacement is in the code of the user application. 

### Example (ЛюдиDesktopCustomizer.cs) 

```csharp
public override ICSSoft.STORMNET.UI.Runner[] GetRunners()
…
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Люди.Л_ЧеловекL), "People", "Man", "A description of the man"));
arr.Add(new ICSSoft.STORMNET.UI.ContRunner(typeof(IIS.Люди.Л_МашинаL), "People", "The machine", "Machine description"));
arr.Add(new ICSSoft.STORMNET.Windows.Forms.FormRunner(typeof(IIS.Люди.WinformForm), "People", "Form", "Form description"));
…
// *** Start programmer edit section *** (People GetRunners() End) 
((ICSSoft.STORMNET.UI.ContRunner)arr[0]).SetImage("D:\\images\\goldstar3.png"); // "Person" 
((ICSSoft.STORMNET.Windows.Forms.FormRunner)arr[2]).SetImage("D:\\images\\caseberrytmcut.jpg"); //"Form" 
// *** End programmer edit section *** (People GetRunners() End) 
``` 

![](/images/pages/products/flexberry-winforms/desktop/list-icons.png) 

## Update objects `ListView` 
There may be situations where adding, modifying or deleting any objects in `ListView` some elements are not displayed correctly. Then `ListView` need to update force. For this purpose the following methods: 
* `BuildList` – rebuild all items in the listView. True as argument means that you need to maintain focus and selection. 
* `RefreshOverlay` to update the overlay, in particular the picture (passed argument). 
* `RefreshObject` to update the row with the passed in argument object. 
* `RebuildColumns` to update existing columns. Is called, for example, after modifying the properties `IsVisible` column. 

## Work with the Column and Item 
The component `ObjectListView` there are some features you configure and add columns and records. First of all you need to say a few words on the support class `ItemInListView`. 

Class `ItemInListView` in fact links Items with Columns. `ObjectListView` itself creates a new item and assigns each field to the desired value. To make-to-one correspondence `поле in `ItemInListView` – column in `ObjectListView`` field names in `ItemInListView` match the value fields `AspectName` (type `string`) in their respective columns. 
The addition of a new item occurs according to the scheme: 
* Created an instance of the class `ItemInListView` and filled its fields. 
* Added it to `ObjectListView` method `AddObjects` for multiple item or `AddObject` for one item. 

With this approach there is no need to work with the subitem's and attached to each item can be referred to as `ItemInListView`: 

```csharp
((ItemInListView)listView1.SelectedObjects[0]).Description
``` 

When creating columns, you need to specify `AspectName` to communicate with and `ImageAspectName` to communicate with the picture. To add columns you need in two arrays. 

### Example 

```csharp
listView1.AllColumns.Add(Caption);
listView1.AllColumns.Add(Description);
listView1.AllColumns.Add(ItemType);
listView1.AllColumns.Add(ParentFolder);
listView1.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
    Caption,
    Description,
    ItemType,
    ParentFolder});
``` 

## Event TreeViewItemSelected 
Event `TreeViewItemSelected` happens with the re-election item in the treeView. Allows, under certain conditions, replace the listView with some of their controls. For example, if selected in the treeView the node is a leaf, i.e. it has no forms and tables, but only the folder (see the property `IsListNodeSelected`) instead of listView show text with background information. 

In your application, you need to subscribe to an event `TreeViewItemSelected` instance `DesktopCtrl`. In an inherited class `TreeViewShowControlsEventArgs` need to set this value to true field `Handled` if you want to display your controls and false if you want to display a listView. Further, controls are created and placed in `PanelOnListView`. 

### Example 

```csharp
private void desktopCtrl2_TreeViewItemSelected(object sender, ICSSoft.STORMNET.Windows.Forms.DesktopCtrl.TreeViewShowControlsEventArgs e)
{
    if (this.desktopCtrl2.IsListNodeSelected)
    {
        e.Handled = true;
        Label label = new Label();
        label.Text = "Some text instead of the ListView";
        label.AutoSize = true;
        label.ForeColor = Color.DarkBlue;
        label.Location = new Point(100, 50);
        e.PanelOnListView.Controls.Add(label);
        e.PanelOnListView.BackColor = Color.LightYellow;
    }
    else
    {
        e.Handled = false;
    }
}
``` 

![](/images/pages/products/flexberry-winforms/desktop/treeview-item-selected.png) 

## DesktopSettings 
Seriousely class `DesktopSettings` is used to store user settings such as window size, column width, columns displayed, etc. in order to setup has been loaded, you need to set the property `UseSettings` class `SettingManager` is true. In this case, settings are saved before closing the user application, and loaded when you start. 

Stores the following settings: 
* string[] `History` - the story line in `AddressBar`. 
* string `curpath` - current path in folders. 
* int `Viewmode` mode of presentation. 0 – large icons, 1 – list, 2 – detail, 3 – small icons. 
* `FoldersSplitterPos` int - the position of the splitter between the treeView and the listView. 
* bool `TreeViewVisible` – display the treeView. 
* bool `ListViewShowFolders` – show folders in the listView. 
* bool `ListViewShowGroups` – group items in the listView. 
* `ListViewAlwaysGroupByColumnname` string – name of column on which you want to always group elements ("“Caption”" title "“Description”" description, ""ItemType"", ""ParentFolder"" - folder). 
* `ListViewColumnSort` string – the name of the column that sorts the listView items. 
* PstrfListViewSortOrder` SortOrder – the sort order of the listView items order (descending, ascending). 
* Display columns: `ColumnDescriptionVisible`, `ColumnItemTypeVisible` – type `ColumnParentFolderVisible` – folder. PstrfНазвание` column is always displayed. 
The width of the columns: `ColumnCaptionWidth` - name, `ColumnDescriptionWidth`, `ColumnItemTypeWidth` - type `ColumnParentFolderWidth` – folder. 





 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/