--- 
title: FileControl for Winforms 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Controls, FileControl 
summary: the Connection, properties, methods, interaction with lists, change files 
toc: true 
permalink: en/fw_file-control.html 
lang: en 
autotranslated: true 
hash: bb80d8e61cbb61bc9a6c52600513208ea317c110dfd75cc6d990fa5dbb1e1f0b 
--- 

`FileControl` is control for file that gives the following functionality: 

* select file from the directory (this creates a copy of the contents of the file and the application is running). 
* save the file to the directory. 
* delete the file. 
* start of file (file opens in the associated application). 

![](/images/pages/products/flexberry-winforms/controls/file-control/file-control.png) 

## Connection to the FileControl application 

To work `FileControl` necessary `ICSSoft.STORMNET.FileType.dll` (standard delivery [Flexberry Winforms](fw_landing_page.html)). 

To connect `FileControl` to the project, do the following: 

* Define the class diagram the class `File` with the stereotype [typedef](fd_typedef.html). 

* Configure card types (ORM -> C# -> model Properties -> Map types), adding the line: 

```
File | ICSSoft.STORMNET.FileType.File | ICSSoft.STORMNET.UserDataTypes.dll
``` 

* To configure the map display types (ORM -> SQL -> Microsoft SQL Server -> databases -> Map types), adding the line: 

```
File | TEXT
``` 

## properties and methods of the FileControl 

### How to show/hide buttons on FileControl 

The control has properties that allow you to show/hide buttons, change them to `false` or `true`. 

```csharp
ctrlФайл.HideOpenButtons = false; //show button, select the file from the directory 
ctrlФайл.HideSaveButtons= false; //show the save button of the file in the directory 
ctrlФайл.HideDeleteButtons = false; //show the delete button of the file 
ctrlФайл.HideStartButtons = false; //show a button to run the file (open in the associated application) 
``` 

By default, all buttons on the control on the edit form hidden. 

### Other properties and methods 

| Property | Type | Description | 
| ------------- | ------------- | ------------- | 
| `ButtonChooseFileFromFolder` | `Button` | Button to select the file from the directory | 
| `ButtonSaveFileToFolder` | `Button` | Button save the file to the directory 
| `ButtonDelete` | `Button` | delete Button of the file 
| `ButtonOpenFile` | `Button` | start Button of the file (open in the associated application) 
| `GetDisplayValue` | `string` | Get the displayed value for the field [GroupEdit](fw_group-edit.html), which is associated контрол; the possibility of empty values 
| `InnerFile` | `MemoryStream` | Field where the file is stored without the. zip archive 
| `ToolTipControl` | `ToolTip` | Control, responsible for tips 
| `Value` | `object` | Field where the file is stored with the zip-archiving 

| Method | return Type |Description | 
| ------------- | ------------- | ------------- | 
| `GetDisplayValue` | `string` | Get the displayed value for the field [GroupEdit](fw_group-edit.html), which is associated контрол; the possibility of empty values| 

## FileControl and list form 

{% include important.html content="Not recommended field, processed using FileControl, in [views](fd_key-concepts.html) treated in [form list](fd_key-concepts.html), since in this case, when viewing the list in memory loaded all the files (besides, it is not guaranteed that the displayed file name is meaningful)." %} 

## Change open using FileControl files 

If `FileControl` through open files to make changes in an external program (not the version that is in the directory where the file is taken, and one that is `FileControl`), the file in `FileControl` will be updated automatically. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}