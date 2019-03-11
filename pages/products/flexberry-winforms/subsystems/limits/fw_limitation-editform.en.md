--- 
title: Form of assignment restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Constraints 
summary: Describes the internal structure of the form AdvLimitComponent 
toc: true 
permalink: en/fw_limitation-editform.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: a30a8fb3f54fe8dc5d0c83abcb030d9aaec08c38e39f5b0da47b6c4ad12e3161 
--- 

## General internal form device 
### AdvLimitComponent 

The programmer sees AdvLimitComponent, which is located on the form with a list control [ObjectListView](fw_objectlistview.html). This component adds to the ObjectListView toolbar button that allows you to control the imposed restrictions. For hierarchical lists AdvLimitComponent not automatically generated, but you can add it manually. The main parameter AdvLimitComponent is a list control that will be assigned to this component. 

### EditAdvLimitDialog 

Component that allows you to call the form of the task constraints from arbitrary locations (not only from list). 

The dialog parameters are specified using the properties EditAdvLimitDialog.DialogParameters type EditAdvLimitDialog.DialogParams. 

Be set using this property the following: 

| Property | Description| 
|--|--| 
| `DoNotShowStandardTab` | don't show the tab standard limits 
| `DoNotShowSimpleTab` | don't show the tab simple constraints 
| `DoNotShowParametersArea` | Not display job settings 
| `DoNotShowLimitArea` | don't show the scope of the job limitations 
| `DoNotSaveEmptyLimit` | When you try to save empty limit to give a message about failing the operation 
| `DisableLimitName` | Do not enter the constraint name 
| `ApplyBtnText` | Text on the button use restrictions 
| `ApplyLimitButtonImage` | the picture on the button apply constraints 
||| 


### EditAdvansedFilter1 

Form advanced editor restrictions. Includes the processing of the editing restrictions and embedded controls for editing the constraints. 

### AdvansedLimit 

Object model constraints edited in the form of task constraints. Includes as a limiting function and the parameter definitions. 

### STORMAdvLimit 

An object representation stored in database constraints. Does not contain logic for deserialization. The class itself knows only the serialized Value. Is deserialized into its Nechranice property AdvLimit with AdvLimitComponent. Used only to read / write to the database (the successor of [DataObject](fo_data-object.html)). 

### DataObjectTypeCreator 

Utility class that allows the dynamics to create the type. These types are used on a custom form, specify the settings for restrictions. 

### ExpressionBox 

Control for editing an expression in the constraint. 

## Useful links 

[Restriction on](fw_self-limit.html) <BR> 
[A simplified view of the editor limitations](fw_limit-editor-simple-view.html) <BR> 
[The standard editor view of limitations](fw_standart-view-limits-editor.html) <BR> 
[The imposition of constraints on columns in the lists](fw_nalozhenie-ogranichenij-po-stolbcam-v-spiskah.html) <BR> 
[Copy / paste branch restrictions (saving and loading from file))](fw_copy-paste-limitation-branch.html) <BR> 
[The master in the selection list the type parameters in the editor AdvLimit](fw_masters-in-list-selection-type-parameters-in-advlimit.html)<BR> 
[Function implications when setting constraints](fo_function-implication.html)<BR> 
[Functions for working with date when setting constraints](fw_date-time-funtions-in-limits.html) (see example [here](fw_date-limits-standart-view.html))<BR> 
[Restrictions on the form of task constraints](fo_limit-function-serialization.html) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}