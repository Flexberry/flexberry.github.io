---
title: Operations WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_wolv-operations.html
lang: en
autotranslated: true
hash: 7e264dc36a64f40d050c1ab6ece92addf405073f2d8c81765a8a2c239050dbea
---

By using Operations, you can configure the behavior WebObjectListView.

To access the Transactions through the property `Operations` control.

To enable print button, list, execute the following code when the page loads:

```csharp
webObjectListView1.Operations.Print = true;
```

## The default operation

To set the default operation for all applications through the configuration file `web.config`

For this you need to add the key `WOLVDefaultOperations`, as the value to specify the list of operations separated by commas.
The key is added to the section `configuration` - `appSettings`:

```xml
<configuration>
  <appSettings>
    <!--Other keys-->
    <add key="WOLVDefaultOperations" value="Refresh,Filter,Search,New,Delete,ShowMarks,EditInRow,ConfigureColumns,NewByExampleInRow,ExportToExcel,AllowColumnResizing,LimitEdit,EditOnClickInRow,FixTableHeader,HighlightObject" />
    <!--Other keys-->
  </appSettings>
</configuration>
```

## The list of operations

| Transaction | Description |
| -------- | -------- |
| AdjustListHeight | Show on WOLV the entire height of the page |
| AllowColumnResizing | Give the user the ability to change the width of list columns
| CheckChanges | whether to Enable verification of changes on the edit form when you move from it, triggered by this list (default true). See below for more information. |
| ConfigureColumns | Button on the toolbar to edit the column display |
Delete The "Delete" Button |
| DeleteInRow | Remove Button in the line |
| Edit | Edit Button |
| EditInRow | Edit Button in the line |
| EditOnClickInRow | Up to edit by clicking the line (if the form is not on lukapa) |
| ExportToExcel | Button "Export to Excel" |
| ExportToXML | Button "Export to XML" |
| Filter | "Filter" Button (simple) |
| FullViewFilter | Button "Filter on all fields" |
| FilterStartPercent | Filter already filled with % at the beginning of the string (default true) |
| FilterEndPercent | Filter already filled with % in the end of the row (default true) |
| FilterSubString | Filter by a substring: % already substituted at the beginning and end of the string, all spaces are also replaced with % (default false). |
| FixTableHeader | freeze table header. When you scroll the header will stay in place. |
| FullViewSearch | Change from search to search all fields ([more](fa_wolv-search.html)). |
| FullViewSearchSplitBySpace | When using the search all view properties to replace spaces with %. |
| HierarchyDisableAutoSelectChildren | Disable automatic selection of child items when you click the parent in the presence of hierarchy in the list. |
| HighlightObject | Backlight object pk which was specified in the URL ("PK" or WOLVSO) (default true). Highlighting is done by selecting the class for td |
| LimitEdit | Advanced editor |
| MultiSelect | Multiple selection |
| New | "New" Button |
| NewByExampleInRow | Button "Create" in the string (see [here](fa_web-data-object-prototyping.html)) |
| OpenEditorInNewWindow | Open the edit form (when you create\edit\view the object) in another browser window |
| OpenEditorInModalWindow | Show the edit form (when you create\edit\view the object) in a modal form. Only works if `OpenEditorInNewWindow == true`; Custom title of modal window can be set via the optional property WOLV-a `EditorInModalWindowCaption` |
| Order | Buttons to move the rows when the view Oreded attributes |
| Print | Print list |
| ReadCommittedUsage | Cancel the use of dirty read |
| ReturnUrlParams | Options that will be added in `ReturnUrl`. Must be in the format `param1=value1&amp;param2=value2`. If the list form is already a parameter that is passed through `ReturnUrlParams`, the priority will have `ReturnUrlParams` (i.e. overwrite option were on a list form). |
| Refresh | "Refresh" Button |
| Search | Search |
| ShowInRow | Display button to set the view object. If it is not set, it uses edit form in Read-Only mode. |
| ShowMarks | Show jackdaws |
| SwitchNextPage | go to the next page when you edit the last item on the current page |
| UseLocalizedCaptions | use of the mechanism of localization of the column headers |
| OverflowWordEllipsis | Sets the behavior of the cell contents overflow the width of the column. If the option is set to False (the default), then transfer the contents of a cell on a new line. If this option is set to True, then the contents of the cell will be clipped and added to the end of the ellipsis. |
| UserColumnsSort | Enables or disables setting the user sort the list and restore the user settings sort. |

## CheckChanges

This property works only in cases where the WOLV is on the edit form.

When you try to leave the page (for example, clicking on the "Close" button) is enabled verification of changes made to the object. If the object has been modified, the user will wonder whether he wants to keep it.

Similar functionality was added to the list located on the edit form: for example, if the user tries to add a new item (and thus to make the transition form one object to the edit form of another), then the system will ask him if he wants to save the changes.

Operation `CheckChanges` enables or disables this check.

{% include note.html content="If this setting is disabled, the processing of saving the changes imposed on the application programmer.
You can use the [events](fa_wolv-events.html list), or [JS API](fa_js-api-wolv.html)." %}

By default this option is enabled.

## Appearance WOLV-enabled operations

![](/images/pages/products/flexberry-aspnet/controls/wolv/all-operations-wolv.png)

## Application settings for WOLV'and lucapa

For applied projects implemented functional configuration WOLV inside LookUp'. Settings are applied in the classroom
`WOLWSettApplyer.cs`, which is located in the root directory.It is necessary to override `ApplyLookUpSettings`.
First, call the base method `base.ApplyLookUpSettings(wolv)`; after him that you want to override.
For example, in order to lucapa the filter operated without the use * need:

```csharp
public override void ApplyLookUpSettings(WebObjectListView wolv)
        {
            base.ApplyLookUpSettings(wolv);

            wolv.Operations.FilterSubString = true;
        }
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}