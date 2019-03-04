--- 
title: Universal pager control Pager 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_pager.html 
lang: en 
autotranslated: true 
hash: f9a706f92fadd7e8773117697584ed390fd48b54afa0f798c7bd932221768c66 
--- 

`ICSSoft.STORMNET.Web.AjaxControls.Pager` class control to display paging for an arbitrary set of data. To work correctly you need only task the total number of pages to display. 

The control is inherited from the base web control [BaseWebControl](fa_base-web-control.html). 

## Connection 

Control automatically attaches all necessary scripts. In particular, `jquery.icsPager.js` and scripts basic web control. 

## Interface 

### Methods 

| Signature | Description| 
|------------------------|----------------------------------------------| 
| `AddPageToContainer()` | Adds the container to the desired control to display the index page| 
| `GenerateCurrentPage()` | Method to generate the internal structure of the current page. Generation and establishment of control are different methods due to the fact that the establishment of control occurs before adding it to the controls collection of the parent. In this case, at the time of generation will be missing the client ID of the input field the page number.| 
| `GenerateGoToPage()` | Method for generating a block of navigation (go to the entered page number)| 
| `GenerateInforamtion()` | Method of generating information on the number of pages and display items. To build a string uses a specified method for formatting, if it is installed. Otherwise, use a template from resources,| 
| `GenerateItemsOnPage()` | generation Method block with a dropdown selection list to set the number of items displayed on the page| 
| `GenerateNavigation()` | Method for generating navigation unit (forwards / backwards)| 
| `GeneratePages()` | Method to generate the list of pages| 
| `GetCurrentPageControl()` | Method to create a control that displays the current page| 
| `GetNavigateUrl()` | Method to generate the link to jump to the specified page| 
| `GetOtherPageControl()` | Method to create a control that displays other (not current) page. A hint to the button is generated using either a pattern resource or through the application installed in the app delegate for formation| 
| `GetPageSeparatorControl()` | Method to create the control separator between pages| 
| `GetSkipPagesControl()` | Method to create a control that displays absences in the long lists of pages. Example: 1 2 ... 10| 
| `OnInit()` | Override event handler for initialize the control. Initialisere auxiliary controls| 
| `OnPreRender()` | Overridden event handler method before rendering the control. Generate a structure of control. The structure of the control is generated before the rendering of due to the fact that the parent control must be set to the total number of page displays and optionally, the current page number. In the method you cannot assign server-side event handlers, because for correct processing of events, controls must be added to the collection page is used to| 
| `Render()` | the event Handler for rendering the control. Registers controls-sources PostBack| 

### Properties 

| Name | Type | Description| 
|---------------------|-------|---------------------------------------------------------------| 
| `CurrentPage` | ` int ` | Property to get the number of the current selected page| 
| `DisplayInformation` | `bool` | Show unit information about the number of pages and elements or no| 
| `DisplayNavigation` | `bool` | Show the navigation bar or not| 
| `DisplayPages` | `bool` | Show a list of pages or not| 
| `EnableGoToPage` | `bool` | Display unit to switch to an entered page number or not| 
| `EnableItemsOnPage` | `bool` | Show block selection of the number of elements on the page or not| 
| `FirstPages` | `int` | Minimum number of pages displayed in the list of pages| 
| `FirstPagesWithoutSkipping` | `int` | Number of pages to be displayed without gaps, if the current page at the beginning of the list| 
| `GoToPageLabelText` | `string` | Text captions to navigate to the entered page number| 
| `HideOnNoData` | `bool` | Hide all the containers in the absence of data| 
| `HideOnOnePage` | `bool` | Hide all the containers if only one page data| 
| `ItemsOnPage` | `int` | Property to get the number of items to display on the page| 
| `ItemsOnPageCaptionText` | `string` | Text captions select list number of items on the page| 
| `ItemsOnPageSettingsKey` | `string` | Property to get or set the key that uniquely identifies the pager to store user settings between pages.


 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/