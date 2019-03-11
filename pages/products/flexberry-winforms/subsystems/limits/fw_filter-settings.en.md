--- 
title: filter Settings 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms), Limitations 
summary: Description of setting filters with a special application AdmConsole and in the code 
toc: true 
permalink: en/fw_filter-settings.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 29d0ac1a29112eac782cea9a338f8a0726a0431f93c81eccd2bae7e21ac1a33e 
--- 

## filter Settings 

These settings can be set using a special application called AdmConsole. 

Data filter settings should indicate which views are used to display detailov in the tree in [form constraint](fw_limit-editor-simple-view.html s) and list the form should open to select master. 


In General, the configuration looks like the following: 
* To specify the settings in AdmConsole 
* "Manual" 
* When using generator settings 
* To put the name of FilterSetting list form 
* With the help of designer 
* In the code 

## Run AdmConsole 
* In the file "AdmConsole.exe.config" I need to write the ConnectionString to the database приложения; 
* Run AdmConsole and in the main menu, select "Assembly Cache" -> "cache Folder assemblies". You must select a folder with the Assembly of application objects 

## Creation settings "automatically" in AdmConsole 
Settings detailov more you can put down the [manual](#создание_настройки_вручную_в_admconsole), but lyapov bring a lot of inconvenience. In order to simplify the setting for lyapov you can use the generator of the filter settings. 

Form generator settings can be opened as follows 
* In the folder with the Assembly cache needs to be build "IIS.WinUI.AdvancedFSCtrl.dll" (DLL located in the folder AdmConsole); 
* In the folder with the Assembly cache needs to be build forms for easy affixing settings lyapov (Build forms is taken from the project, and is <Projectname>(Forms).dll); 
* AdmConsole to open the list form of "Special form options" -> "filter preferences". Form as follows: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings_generated1.jpg) 
![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings_generated2.jpg) 

## create the setup "manually" in AdmConsole 
* Open list form of "General" -> "filter Settings". 
* Click on add object 
* A form will appear<br> 
![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings_before.jpg) 
* Fields: 
* `Name` - the name of the setting, 
* `DataObjectView` - representation of the object, which is superimposed on the limit 
* `Lookups` - list of masters used in the presentation, to be able to build the primary key constraint of the master. Unlike the artisans of the attributes is that the value rises list form object, which gives a wider range of possibilities (searching, sorting, imposition of restrictions). 
* `DataObjectType` – type object wizard 
* `Container` – path to the list object form (e.g.: IIS.MOB.UgStat.Reference.КтоУстановилL, IIS.MOB.UgStat.Reference(Forms)) 
* `FieldsToView` – list of properties (comma separated, no space) that will be displayed when you select the wizard for the constraint. For simple reference, which do not have masters, normally, use the property “Name”. For other objects, a list of these attributes should be the analyst, or they must be specified in the formulation. 
* `Details` - list of datalow for the filter. 

This may be implicit detaily. Implicit detalam nespravny is any object referencing the object, a filter which we custom. 

Implicit detaily must be specified after the obvious. The order within these groups should identify the analyst. 
* `Caption` – header detail 
* `DataObjectView` – [D-view](fd_d-view.html) detail 
* [`ConnectMasterProp`](fw_master-details-filters.html) – the property name referencing the master class. 
* [`OwnerConnectProp`](fw_master-details-filters.html) – the property name referencing the class detail. 
Master detailov should also be added to the list of Lookups 

After filling all the fields the form may look like the following: 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings_after.jpg) 


## Job settings form in the VS designer 
The name of the settings are stored in the application systems necessary to specify the list form in the component `AdvLimit`. 
On the list form you want to allocate AdvLimitComponent and set its property "FilterSettingName". 

![](/images/pages/products/flexberry-winforms/subsystems/limits/filtersettings_vs.jpg) 

## Job settings form in the code 
If the list form is the universal form (no generated code), the name of the filter settings can be specified in an independent form in the method (__applicable if only one list__): 

```csharp
protected override ICSSoft.STORMNET.UI.IEditInitiator GetDpdForm()
{
// *** Start programmer edit section *** (ПроектL.GetDpdForm() start) 

// *** End programmer edit section *** (ПроектL.GetDpdForm() start) 
ICSSoft.STORMNET.Windows.Forms.Operations objOperations1 =
	ICSSoft.STORMNET.Windows.Forms.Operations.GetAllTrue();
ICSSoft.STORMNET.UI.UniWinListStandard form =
	new ICSSoft.STORMNET.UI.UniWinListStandard(new ICSSoft.STORMNET.UI.ObjectListViewInformation[]{
new ICSSoft.STORMNET.UI.ObjectListViewInformation(ICSSoft.STORMNET.Information.GetView("ПроектL",
typeof (IIS.Звонки.Проект)),objOperations1, new System.Type[]{typeof (IIS.Звонки.Проект)}, null)}, "Projects");
// *** Start programmer edit section *** (ПроектL.GetDpdForm() end) 
form.FilterSettingName = "Project";
// *** End programmer edit section *** (ПроектL.GetDpdForm() end)) 
return form;
}
``` 

String `form.FilterSettingName = "Project";` should be written in [brackets programmer](fo_programmer-brackets.html) (so as not to lose changes when regeneration). 

## Example 

An example of using AdvLimit and settings FilterSettings you can see in [this article](fw_filter-example.html)


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}