--- 
title: Structure of the Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_theme-structure.html 
lang: en 
autotranslated: true 
hash: b4fddce26ff876c4f1115a8419c2ac775565e93ac3db2c7aa91a9eb99e01eadc 
--- 

The appearance of topics as well as additional information set out in [article theme Selection Web application](fa_choose-theme.html). 

## Themes 

Initially themes Flexberry ASP.NET represent an unordered set .css-files from which it was hard to understand what is responsible for what. This led to the fact that the process of finalizing the third-party projects is complicated. It was necessary to structure the topic and to simplify the process of finalizing their plans. 
In order to achieve this goal, it was decided to use a dynamic language styles LESS. It allows you to group styles, as well as move some style rules in variables, and then import them in the right .less-files. 
This approach allows us to create a separate file group, which is styles used in all subjects. More on that described in [article basic theme Flexberry ASP.NET and its connection with other themes](fa_base-theme-structure.html). And also "collect" General styles for all themes and transfer them to separate .less file, create variables and import them as needed. 
This reduces the amount of unnecessary code, but themes take a common structure. In addition, it improves the possibility of revision topics on applied projects. This is discussed in [article Revision themes on applied projects](fa_change-theme.html). 

## structure of a topic 

By default, the structure looks like the following: 

* `Controls` 
* `__DateTimePicker__` 
* Images 
* _Settings.less 
* _Variables.less 
* DateTimePicker.less 
* `__DefaultInputs__` 
* Images 
* _Settings.less 
* _Variables.less 
* DefaultInputs.less 
* `__Lookup__` 
* Images 
* _Settings.less 
* _Variables.less 
* Lookup.less 
* _Variables.less 
* `__ShowHideDiv__` 
* Images 
* _Settings.less 
* ShowHideDiv.less 
* `__TableControls__` 
* `AjaxGroupEdit` 
* _Settings.less 
* _Variables.less 
* AjaxGroupEdit.less 
* `WebObjectListView` 
* Images 
* WebObjectListView-Settings 
* Images 
* _Variables.less 
* WebObjectListView-HierarchySettings.less 
* WebObjectListView-LimitEditSettings.less 
* WebObjectListView-ViewColumnProvider.less 
* _Variables.less 
* _Settings.less 
* WebObjectListView-captionToolbar.less 
* WebObjectListView-contextMenu.less 
* WebObjectListView-pager.less 
* WebObjectListView-tableToolbar.less 
* WebObjectListView-toolbar.less 
* WebObjectListView.less 
* `Forms` 
* `__Messages__` 
* `AlertMessage` 
* _Settings.less 
* _Variables.less 
* AlertMessage.less 
* `__ModalWindows__` 
* `ModalWindowContent` 
* Images 
* _Settings.less 
* _Variables.less 
* ModalWindowContent.less 
* `ModalWindowWrapper` 
* Images 
* _Settings.less 
* _Variables.less 
* ModalWindowContent.less 
* `__Pages__` 
* `EditPage` 
* Images 
* _Settings.less 
* _Variables.less 
* EditPage.less 
* `ExceptionPage` 
* _Settings.less 
* _Variables.less 
* ExceptionPage.less 
* `LoginPage` 
* _Settings.less 
* _Variables.less 
* LoginPage.less 
* `MainPage` 
* Images 
* _Settings.less 
* _Variables.less 
* MainPage.less 
* `Images` 
* `Libraries` 
* `__jQueryUI__` 
* Images 
* jQueryUI.Base.less 
* jQueryUI.DateTimePicker.less 
* jQueryUI.less 
* jQueryUI.Settings.less 
* BaseStyles.css 
* BaseStyles.less 
* Theme.skin 
* Web.config 

### Folder and file Controls 

#### Standard structure 

In the `Controls` folder contains files and folders that are associated with controls. By default, this is: standard controls: `DateTimePicker`, `ShowHideDiv`, `Lookup` and `WebObjectListView` (WOLV) and `AjaxGroupEdit` (AGE). Each control is contained in a separate folder. By default, each folder has three files and a folder with pictures Images (WOLV structure is a little different). An example is provided below. 

* DateTimePicker 
* `Images ` is the folder that contains the pictures, is dedicated to this control. 
* `_Settings.less` file that is used to add style rules applied by developers. How it exactly works is described below. 
* `_Varaibles.less` file that contains any variables for this control. 
* `DateTimePicker.less` - the main file that contains the styles for this control. 

{% include note.html content="file _Variables.less and _Settings.less will be encountered repeatedly. Their purpose is the same everywhere, so for each element of the structure will not be considered." %} 

### Structure [WOLV](fa_web-object-list-view.html) 

The structure of the control WOLV is slightly different. This control, like AGE, is contained in the folder `TableControls` responsible for table controls. The number of files WOLV much more than the other controls. 
The structure is logically divided into two levels: 

* the file responsible for part of the WOLV 
* the file responsible for settings WOLV. 

Following the example of other controls, WOLV has its own folder `Images`, files `_Variables.less` and `_Settings.less`. As styles for WOLV very much, they were broken into several files.
In the folder `WebObjectListView-Settings` also contain `Images` folder and file `_Variables.less`. The contents of this folder is dynamic because the number of settings grows WOLV. 
At the moment, separately presented three of them: 
* `WebObjectListView-HierarchySettings.less` 
* `WebObjectListView-LimitEditSettings.less` 
* `WebObjectListView-ViewColumnProvider.less`. 

The details of the structure is presented below. 

* TableControls 
* WebObjectListView 
* Images 
* WebObjectListView-Settings 
* Images 
* _Variables.less 
* `WebObjectListView-HierarchySettings.less` file that contains styles for hierarchical display of data 
* `WebObjectListView-LimitEditSettings.less` file that contains the box style restrictions. 
* `WebObjectListView-ViewColumnProvider.less` file that contains the style settings to display the columns in the table. 
* _Variables.less 
* _Settings.less 
* `WebObjectListView-captionToolbar.less` file that contains the styles toolbar in the header of the table WOLV. 
* `WebObjectListView-contextMenu.less` file that contains the context menu styles. 
* `WebObjectListView-pager.less` file containing the styles of the lower and upper pagers. 
* `WebObjectListView-tableToolbar.less` file that contains the styles toolbar in the table WOLV. 
* `WebObjectListView-toolbar.less` file that contains the styles main toolbar. 
* `WebObjectListView.less` - based file that contains the styles WOLV. 

### Folders and files in Forms 

PstrfForms` in a folder to contain the files and folders that are associated with different forms of application. 
The contents of this folder is subdivided into three groups: 

* messages (Messages) 
* modal Windows (ModalWindows) 
* page (Pages). 

The structure and description of each folder that is responsible for what is presented below. 

* Forms 
* Messages 
* `AlertMessage` - contains the styles for the modal message boxes. 
* _Settings.less 
* _Variables.less 
* AlertMessage.less 
* ModalWindows 
* `ModalWindowContent` - contains the styles for the content of modal Windows. 
* Images 
* _Settings.less 
* _Variables.less 
* ModalWindowContent.less 
* `ModalWindowWrapper` - contains styles for the shell modal dialogs. 
* Images 
* _Settings.less 
* _Variables.less 
* ModalWindowContent.less 
* Pages 
* `EditPage` - contains the styles for the edit form. 
* Images 
* _Settings.less 
* _Variables.less 
* EditPage.less 
* `ExceptionPage` - contains styles for the default error page. 
* _Settings.less 
* _Variables.less 
* ExceptionPage.less 
* `LoginPage` - contains the styles for the login page. 
* _Settings.less 
* _Variables.less 
* LoginPage.less 
* `MainPage` - contains styles for the main page. 
* Images 
* _Settings.less 
* _Variables.less 
* MainPage.less 

### Files and folders in Libraries 

PstrfLibraries` folder contains files and folders in different libraries. At the moment only one `jQueryUI`. 

* Libraries 
* jQueryUI 
* `jQueryUI.Base.less` file that contains styles for jQueryUI library. 
* `jQueryUI.DateTimePicker.less` file that contains styles for DateTimePicker. 
* `jQueryUI.less` - the main file containing imported files. 
* `jQueryUI.Settings.less` file that contains settings for the jQueryUI styles. 

### Other theme elements 

To the rest of the theme elements include: 

* `Images ` images used in the Theme.skin. 
* `BaseStyles.css` - style file obtained through the compilation BaseStyles.less. 
* `BaseStyles.less` file containing the imported .less files of the theme. 
* `Theme.skin` - "skin" of the website. 
* `Web.config` --//-. 

### External theme files 

The external files are files relating to all topics. They are in the folder `App_Themes`. 

* `CommonSettings.less` file contains settings for all themes. 
* `WolvClasses.less` - in this file contain variables that represent the classes and ID to the WOLV. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}