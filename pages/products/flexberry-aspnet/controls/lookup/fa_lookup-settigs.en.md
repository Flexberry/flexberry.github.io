--- 
title: Properties LookUp's for Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: JavaScript, Windows UI (Controls) 
toc: true 
permalink: en/fa_lookup-settigs.html 
lang: en 
autotranslated: true 
hash: 4995113d3b6a9df946353e0de4417d62de57b27c4a58e287291d9d50c0301677 
--- 

* `ColumnsSort` - sorting by columns in [WOLV](fa_web-object-list-view.html) to open the form. 
* `ConnStrName` - the connection string to the database. 
* `EditedProperty` - editable property of the object. 
* `EnablePostBack` - turn the page refresh after selecting. 
* `LimitFunction` - limiting function, limiting the list to raise on lookup form. PstrfLookUpFormLFName` will automatically register during installation. 
* `LookUpFormCaption` - header form, raised on lookup. Default `Выбор значения`. 
* `LookUpFormCountOnPage` - the Number of rows [WOLV](fa_web-object-list-view.html) in the form of selecting values of lucapa. 
The default number of rows [WOLV](fa_web-object-list-view.html) on the underlying lookup form will be determined based on stored user preferences or default values in the [Pager](fa_web-object-list-view.html). This behavior can be overridden in the application lookup-form. 
If there is a specific value, the number of rows in the list when opening the form will always be equal. When setting the value, note that [a Universal paging control. Pager.](fa_pager.html) uses a white list of possible values for the number of displayed objects. 
* `LookUpFormHeight` - height form raised on lookup. Default `600`. 
* `LookUpFormLeft` the left border of the unit. Default `100`. 
* `LookUpFormLocation` - whether to display in the browser address bar when you open on lookup. Is not displayed by default. 
* `LookUpFormMenubar` - whether to display the menu in the browser when opening lookup. Is not displayed by default. 
* `LookUpFormResizable` - to give the ability to change the size of the opening on lookup form. By default, the opportunity is there. 
* `LookUpFormScrollbars` show whether scrollbar to open the form. By default. 
* `LookUpFormStatus` - whether to display the status bar to open the form. Are not displayed by default. 
* `LookUpFormToolbar` - whether to display the toolbar (the button "Forward", "Back", etc.). Are not displayed by default. 
* `LookUpFormTop` is the upper bound of the unit. Default `100`. 
* `LookUpFormURL` - URL list form that will be used to select a value. 
* `LookUpFormWidth` - the width of the unit. Default `750`. 
* `MasterTypeName` - type [artisan](fd_master-association.html) of the object. Installed with `typeof(Type).AssemblyQualifiedName`.
* `MasterViewName` representation of the workman of the object. 
* `ObjectTypeName` - type of the edited object. Installed with `typeof(Type).AssemblyQualifiedName`. 
* `ObjectViewName` - [view](fd_view-definition.html) of the edited object. 
* `PropertyToShow` is the property of the master, which will be displayed in lucapa for the selected object. 
* `SelectedMasterPK` - [PrimaryKey] in(fo_primary-keys-objects.html) of the selected object. 
* `ShowInThickBox` - show lucapa in thickbox or not. 
* `ShowObjectUrl` - URL, which will rise form view for the selected object. 
ApplicationUrl ListFormURL. If not specified, the standard form is used. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}