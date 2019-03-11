--- 
title: Pagers WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-paging.html 
lang: en 
autotranslated: true 
hash: 20d9f2383254422b69be79977fcb836276d09bda355fa362b2d7d351ee3b25cb 
--- 

To display paging uses two [paging control](fa_pager.html) verhnii and Nijni. For different `Pager'ов` use different CSS classes and settings. 

## Settings Pager's 

| The parameter pager upper | lower Setting of the pager | | 
| --------------- | -------------- | ------------------ | 
| `TopPagerVisible` | `BottomPagerPagesVisible` | Show the pager or not. | 
| `TopPagerNavigationVisible` | `BottomPagerNavigationVisible` | or not to Show the navigation bar (forward or backward) in the pager. | 
| `TopPagerGoToPageVisible` | `BottomPagerGoToPageVisible` | or not to Show the bar move to an arbitrary page in the pager. | 
| `TopPagerPagesVisible` | `BottomPagerPagesVisible` | or not to Show a list of pages in the pager. | 
| `TopPagerInformationVisible` | `BottomPagerInformationVisible` | Show or not information about the amount of downloaded data in the pager. | 
| `TopPagerInformationFormatter` | `BottomPagerInformationFormatter` | Delegate for the formation of the line with information about the downloaded data in the pager. | 
| `TopPagerItemsInPageVisible` | `BottomPagerItemsInPageVisible` | or not to Show the toolbar with the select list number of items to display per page in the pager. | 
| `TopPagerInlineGoToPage` | `BottomPagerInlineGoToPage` | Show input field to jump to an arbitrary page in the block with the current page in the pager. | 
| `TopPagerNavigationBackText` | `BottomPagerNavigationBackText` | Text navigation buttons to move to the previous page in the pager. | 
| `TopPagerNavigationForwardText` | `BottomPagerNavigationForwardtext` | Text navigation buttons to move to the next page in the pager. | 
| `TopPagerNavigationSeparatortext` | `BottomPagerNavigationSeparatortext` | Dividing the text between navigation buttons in the pager. | 
| `TopPagerGoToPageLabelText` | `BottomPagerGoToPageLabelText` | the label Text to the input field to jump to an arbitrary page in the pager. | 
| `TopPagerPagesSkipText` | `BottomPagerPagesSkipText` | Text skip long lists of pages in the pager. | 
| `TopPagerPagesWithoutSkippingcount` | `BottomPagerPagesWithoutSkippingcount` | Maximum number of pages to be displayed without gaps in the pager. | 
| `TopPagerFirstPagesCount` | `BottomPagerFirstPagesCount` | Number of the first pages displayed in the pager. | 
| `TopPagerFirstPagesWithoutSkippingcount` | `BottomPagerFirstPagesWithoutskippingcount` | Number of the first pages that are displayed without gaps in the pager. | 
| `TopPagerLastPagesCount` | `BottomPagerLastPagesCount` | the Number of pages displayed in pager. | 
| `TopPagerLastPagesWithoutSkippingcount` | `BottomPagerLastPagesWithoutskippingcount` | the Number of pages displayed without gaps in the pager. | 
| `TopPagerItemsOnPageCaptionText` | `BottomPagerItemsOnCaptionText` | Text captions select list number of items to display in the pager. | 

## CSS classes Pager's 

| CSS-class top-pager | CSS-class bottom-pager | | 
| --------------------------- | -------------------------- | -------- | 
| `ics-wolv-pager ics-wolv-pager-top` | `ics-wolv-pager ics-wolv-pager-bottom` | CSS class of the container of the pager | 
| `ics-wolv-pager-navigation ics-wolv-pager-navigation-top` | `ics-wolv-pager-navigation ics-wolv-pager-navigation-bottom` | CSS class of the container of the navigation unit of the pager. | 
| `ics-wolv-pager-navigation-button` | `ics-wolv-pager-navigation-button` | Shared CSS class to the navigation buttons of the pager. | 
| `ics-wolv-pager-navigation-button-back` | `ics-wolv-pager-navigation-button-back` | CSS class of the navigation buttons to move to the previous page of the pager. | 
| `ics-wolv-pager-navigation-button-forward` | `ics-wolv-pager-navigation-button-forward` | CSS class of the navigation buttons to move to the next page of the pager. | 
| `ics-wolv-pager-navigation-disabled` | `ics-wolv-pager-navigation-disabled` | CSS class disabled navigation buttons of the pager. | 
| `ics-wolv-pager-navigation-enabled` | `ics-wolv-pager-navigation-enabled` | CSS class enabled the navigation buttons of the pager. | 
| `ics-wolv-pager-navigation-separator` | `ics-wolv-pager-navigation-separator` | CSS class of the separator between the navigation buttons of the pager. | 
| `ics-wolv-pager-go-to-page ics-wolv-pager-go-to-page-top` | `ics-wolv-pager-go-to-page ics-wolv-pager-go-to-page-bottom` | CSS class of the container of the transport unit to an arbitrary page of a pager. | 
| `ics-wolv-pager-go-to-page-label` | `ics-wolv-pager-go-to-page-label` | CSS class of the caption input field the page number to go to the pager. | 
| `ics-wolv-pager-go-to-page-input` | `ics-wolv-pager-go-to-page-input` | CSS class input field, the page number for the transition of the pager. | 
| `ics-wolv-pager-inline-go-to-page` | `ics-wolv-pager-inline-go-to-page` | CSS class of the current page with an input field the page number to go to the pager. | 
| `ics-wolv-pager-inline-go-to-page-input` | `ics-wolv-pager-inline-go-to-page-input` | CSS class input field the page number to jump to block the current page of the pager. | 
| `ics-wolv-pager-pages ics-wolv-pager-pages-top` | `ics-wolv-pager-pages ics-wolv-pager-pages-bottom` | CSS class of the container unit with the list of pages on the pager. | 
| `ics-wolv-pager-page ics-wolv-pager-page-top` | `ics-wolv-pager-page ics-wolv-pager-page-bottom` | Shared CSS class blocks of pages of the pager. | 
| `ics-wolv-pager-current-page ics-wolv-pager-current-page-top` | `ics-wolv-pager-current-page ics-wolv-pager-current-page-bottom` | CSS class to block the current page of the pager. | 
| `ics-wolv-pager-skip ics-wolv-pager-skip-top` | `ics-wolv-pager-skip ics-wolv-pager-skip-bottom` | CSS class of the block of missing pages of the pager. | 
| `ics-wolv-pager-items-on-page ics-wolv-pager-items-on-page-top` | `ics-wolv-pager-items-on-page ics-wolv-pager-items-on-page-bottom` | CSS class of the container unit the choice of the number of items to display pager. | 
| `ics-wolv-pager-items-on-page-caption ics-wolv-pager-items-on-page-caption-top` | `ics-wolv-pager-items-on-page-caption ics-wolv-pager-items-on-page-caption-bottom` | CSS class names to the list of choice of the number of items to display pager. | 
| `ics-wolv-pager-items-on-page-list ics-wolv-pager-items-on-page-list-top` | `ics-wolv-pager-items-on-page-list ics-wolv-pager-items-on-page-list-bottom` | CSS class of the select list the number of items to display pager. 

## Save custom settings 

Saving user-defined values `Pager`'s produced by key, composed of the path to form `UniqueID` `WOLV`'and and view name. Thus, at one and the same form when you change the view setting will also change. 

## Disable paging 

Have WebObjectListView you can disable paging by configuring `AllowPaging`. 
By default it has the value `true`, if you put `false`, then paging will be disabled, all available records will be displayed on a single page, on the site of the pagers only display information that shows 1 - N of N records. 

![](/images/pages/products/flexberry-aspnet/controls/wolv/disable-paging1.png) 

Paging can be disabled in the markup of the page by checking the control setting AllowPaging="False". 

```xml
<ac:WebObjectListView ID="WebObjectListView1" runat="server" Visible="true" AllowPaging="False" />
``` 

Paging can be disabled and behind code page, as well indicating the control setting AllowPaging=false. 

```csharp
/// <summary> 
/// Called the first in the Page_Load. 
/// </summary> 
protected override void Preload()
{
    WebObjectListView1.AllowPaging = false;
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}