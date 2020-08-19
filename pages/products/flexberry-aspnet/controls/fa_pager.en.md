---
title: Universal pager control Pager
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Controls)
toc: true
permalink: en/fa_pager.html
lang: en
autotranslated: true
hash: 9b37cc0d6d023a3819d6d461181761c61df81345d9a2b121f39bfe8b74fd71b1
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
| `SetAvailableItemsOnPageCounts(ReadOnlyCollection<int> counts)` | Static method that allows you to set options users choose the number of lines on each page. The default collection { 5, 10, 15, 20, 30, 50, 100, 200 }.|

### Properties

| Name | Type | Description|
|---------------------|-------|---------------------------------------------------------------|
| `CurrentPage` | `int` | Property to get the number of the current selected page|
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
| `ItemsOnPageSettingsKey` | `string` | Property to get or set the key that uniquely identifies the pager to store user settings between pages. Default is defined using the page URL and the unique ID of the control|
| `ItemsCount` | `int` | Property for access to the full number of items displayed|
| `InlineGoToPage` | `bool` | Add the current page to the input field to quickly jump to the page or not|
| `PagesCount` | `int` | total number of pages displayed|
| `LastPages` | `int` | Minimum number of pages displayed at the end of the page list|
| `LastPagesWithoutSkipping` | `int` | Number of pages to be displayed without gaps, if the current page at the end of the list|
| `NavigationBackText` | `string` | Text navigation buttons to move to the previous page|
| `NavigationForwardText` | `string` | Text navigation buttons to move to the next page|
| `NavigationSeparatorText` | `string` | delimiter Text between navigation buttons|
| `PagesSkipText` | `string` | Text block skip long lists of pages|
| `PageSeparatorText` | `string` | delimiter Text between pages|
| `PagesWithoutSkipping` | `int` | Maximum number of pages which are not displayed units pass|
| `Sequence` | `IEnumerable < PagerContainerType >` | Sequence containers pager|

## Customize the appearance of control

| CSS | |
|----------------------|-----------------------------------------|
| `ContainerCssClass` | CSS class for the pager container|
| `CurrentPageCssClass` | CSS class for the current page|
| `GoToPageCssClass` | CSS class of the container unit to move to the entered page number|
| `GoToPageInputCssClass` | CSS class of input field switch to the selected page number|
| `GoToPageLabelCssClass` | CSS class signatures to navigate to the entered page number|
| `InformationContainerCssClass` | CSS class of the container unit information on the number of pages and the display elements|
| `InlineGoToPageCssClass` | CSS class for the current page with an input field for quick navigation to the entered page|
| `InlineGoToPageInputCssClass` | CSS class input field to quickly move to the entered page|
| `ItemsOnPageCaptionCssClass` | CSS class for signature to the select list number of items on the page|
| `ItemsOnPageContainerCssClass` | CSS class of container for the block selection of the number of elements on the page|
| `ItemsOnPageListCssClass` | CSS class for the select list number of items on the page|
| `NavigationButtonBackCssClass` | CSS class of the navigation buttons to move to the previous page|
| `NavigationButtonCssClass` | Shared CSS class of the navigation buttons|
| `NavigationButtonDisabledCssclass` | CSS class disabled navigation buttons (when the transition is impossible)|
| `NavigationButtonEnabledCssClass` | CSS class enabled navigation buttons (when the transition is possible)|
| `NavigationButtonForwardCssClass` | CSS class of the navigation buttons to go to the next page|
| `NavigationContainerCssClass` | CSS class of the base container unit navigation|
| `NavigationSeparatorCssClass` | CSS class of the separator between the navigation buttons|
| `PageCssClass` | Shared CSS class for all pages|
| `PagesContainerCssClass` | CSS class for the container list of pages|
| `PagesSkipCssClass` | CSS class for the block skip long lists of pages|

## Customizing

You should pay attention to the fact that when you specify a nonexistent page number and try to click it, you will jump to the first page of the list.



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}