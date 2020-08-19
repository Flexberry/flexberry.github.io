--- 
title: Adding a button in the toolbar or in line WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-add-button.html 
lang: en 
autotranslated: true 
hash: 9c977306d7971331e36f9b69a38782032c221de1353c21613165db971a289baf 
--- 

## Add buttons to the toolbar WOLV 

To add a button in Toolbar WOLV, you must use the method `AddImageButton(...)` list. 
However, before you can add the button in the Toolbar, is to determine which handler will have this button: 

* Server 
* Client 
* The server and client. 

### Server processor 

If the handler needs to be a server, you must use the following method overload `AddImageButton`: 

```csharp
/// <summary> 
/// Method to add custom buttons to the toolbar with the possibility of 
/// a server handler. 
/// </summary> 
/// <param name="id">Server ID of the button.</param> 
/// <param name="cssClass">CSS class that is applied to the button.</param> 
/// <param name="alternateText">the tooltip Text to the button.</param> 
/// <param name="clickHandler">Server-side handler for the button click.</param> 
public void AddImageButton(string id, string cssClass, string alternateText, ToolBarBtnEventHandler clickHandler)
``` 

As you can see, the method is passed the ID of the button, CSS class, tooltip text, and a backend handler for the clicking of the following types: 

```csharp
public delegate void ToolBarBtnEventHandler(LinkButton sender, ToolBarBtnEventArgs eventArgs);
``` 

#### ToolBarBtnEventArgs 

`ToolBarBtnEventArgs` - arguments to be passed to the server handler for the button click in the Toolbar e WOLV. 

The information can be obtained from `ToolBarBtnEventArgs`: 

| Name | Description | 
| -------- | -------- | 
| `List<string> PrimaryKeys` | List of keys of selected objects in the list. __Important: if the option "Select all on all pages", it will return an empty list__. | 
| `bool IsAllSelected` | if the option "Select all on all pages". | 
| `WebObjectListView WOLV` | Instance list button on a Toolbar which button was pressed. | 
| `Function LimitFunction` | Limiting function is superimposed on the list. | 

#### Example usage 

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestToolbarServerButton", // The ID of the button 
        "wolv-test-toolbar-server-button", // CSS Class 
        "Test server button of the toolbar", // Tooltip text 
        CustomToolbarButtonClickHandler); // Handler 
    ...
}
...

private void CustomToolbarButtonClickHandler(object sender, ToolBarBtnEventArgs eventArgs)
{
    TestToolbarButtonLabel.Visible = true;

    // Check properties Wolv event arguments. 
    if (eventArgs.Wolv != WebObjectListView1)
    {
        throw new Exception("WOLV instance in the handler of button click of the toolbar is incorrect.");
    }

    // Check the properties of PrimaryKeys. 
    if (eventArgs.PrimaryKeys.Count > 0)
    {
        var objects = eventArgs.PrimaryKeys.Select(x => new Город { __PrimaryKey = Guid.Parse(x) })
                            .Cast<DataObject>().ToArray();
        DataServiceProvider.DataService.LoadObjects(objects, Город.Views.ГородL, true);

        SelectedCitiesLabel.Visible = SelectedCitiesList.Visible = true;
        foreach (var obj in objects)
        {
            SelectedCitiesList.Controls.Add(new HtmlGenericControl("li") { InnerText = ((Город)obj).Наименование });
        }
    }

    // Check properties IsAllSelected. 
    AllIsSelectedLabel.Visible = eventArgs.IsAllSelected;
}
``` 

### Client handler 

If the handler needs to be a client, you should use the following overload of the method `AddImageButton`: 

```csharp
/// <summary> 
/// Method to add custom buttons to the toolbar with the possibility of 
/// client handler. 
/// </summary> 
/// <param name="id">Server ID of the button.</param> 
/// <param name="cssClass">CSS class that is applied to the button.</param> 
/// <param name="alternateText">the tooltip Text to the button.</param> 
/// <param name="clientClickHandler">the name of the JS function in a global object client-side handler for the button click.</param> 
/// <param name="clientClickAddParams">Additional options for the JS handler.</param> 
public void AddImageButton(string id, string cssClass, string alternateText, string clientClickHandler, string clientClickAddParams)
``` 

In addition to ID, CSS class and the tooltip text in the method is also passed the name of the JS function and the parameters that should pass to this function. 

{% include note.html content="Is the name of the JS function, not its code." %} 

#### Example usage 

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestToolbarClientButton", // The ID of the button 
        "wolv-test-toolbar-client-button", // CSS class 
        "Test client button of the toolbar",  // Tooltip text 
        "ToolbarBtnClickAlert", // Name of the JS function. 
        // In principle, the option alert(\"Pressed custom button toolbar client handler\"); 
        // also rolls, but for more complex handlers may not work correctly. 
        // Recommended to pass the function name, and describe it separately! 
        string.Empty); // The parameters passed to the function ToolbarBtnClickAlert. 
    ...
}
``` 

### Server and client handler 

To add the button at the same time and a server and a client-side handler, you must use the following method overload `AddImageButton(...)`: 

```csharp
/// <summary> 
/// Adding buttons to the toolbar. 
/// </summary> 
/// <param name="lb">Click to add.</param> 
public void AddImageButton(LinkButton lb) 
``` 

Using this method, it is important to understand the following: 

1. To use this method is necessary only when other methods are not suitable. 
2. All button settings (including appearance and styles) falls on the application developer. 

#### Example usage 

```csharp
var twoHandlersButton = new LinkButton
{
    OnClientClick = "alert('Load server-side button handler, 2 handler.');",
    ID = "TwoHandlersButton",
    CssClass = "ics-wolv-toolbar-button wolv-test-toolbar-client-server-button",
};
twoHandlersButton.Click += CustomToolbarButtonClickHandler1;
twoHandlersButton.ToolTip = "Button with two handlers.";
WebObjectListView1.AddImageButton(twoHandlersButton);
``` 

## Add buttons in the rows WOLV 

To add a button in Toolbar WOLV, you must use the method `AddImageButtonToRow(...)` list.

However, before you can add the button in the Toolbar, is to determine which handler will have this button: 

* Server 
* Client. 

### Server processor 

If the handler needs to be a server, you must use the following method overload `AddImageButtonToRow`: 

```csharp
/// <summary> 
/// Add a button to each row 
/// </summary> 
/// <param name="id">the ID of the button (will be added to the index of the row)</param> 
/// <param name="cssClass">the Class of the control</param> 
/// <param name="alternateText">Signature</param> 
/// <param name="clickHandler">Server-side event that will come clicking 
/// The PrimaryKey of the object in the row can be obtained as follows: 
/// primaryKey = (sender as ImageButton).Attributes["pk"]; 
/// </param> 
public void AddImageButtonToRow(string id, string cssClass, string alternateText, ToolBarBtnEventHandler clickHandler)
``` 

As in the case of the add server button on the Toolbar is passed into the method ID of a button, CSS class, tooltip text, and a backend handler for the clicking of the following types: 

```csharp
public delegate void ToolBarBtnEventHandler(LinkButton sender, ToolBarBtnEventArgs eventArgs);
``` 

#### ToolBarBtnEventArgs 

Similarly EventArgs'am Toolbar a. 

#### Example usage 

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButtonToRow(
        "TestRowServerButton", // The ID of the button 
        "ics-wolv-toolbar-button ics-wolv-toolbar-button-icon wolv-test-row-server-button", // CSS class 
        "Test server button in the row", // Tooltip text 
        CustomToolbarButtonClickHandler); // A server handler for the clicking 
    ...
}

...

private void CustomToolbarButtonClickHandler(object sender, ToolBarBtnEventArgs eventArgs)
{
    TestToolbarButtonLabel.Visible = true;

    // Check properties Wolv event arguments. 
    if (eventArgs.Wolv != WebObjectListView1)
    {
        throw new Exception("WOLV instance in the handler of button click of the toolbar is incorrect.");
    }

    // Check the properties of PrimaryKeys. 
    if (eventArgs.PrimaryKeys.Count > 0)
    {
        var objects = eventArgs.PrimaryKeys.Select(x => new Город { __PrimaryKey = Guid.Parse(x) })
                            .Cast<DataObject>().ToArray();
        DataServiceProvider.DataService.LoadObjects(objects, Город.Views.ГородL, true);

        SelectedCitiesLabel.Visible = SelectedCitiesList.Visible = true;
        foreach (var obj in objects)
        {
            SelectedCitiesList.Controls.Add(new HtmlGenericControl("li") { InnerText = ((Город)obj).Наименование });
        }
    }

    // Check properties IsAllSelected. 
    AllIsSelectedLabel.Visible = eventArgs.IsAllSelected;
}
``` 

### Client handler 

If the handler needs to be a client, you should use the following overload of the method `AddImageButton`: 

```csharp
/// <summary> 
/// Add a button to each row 
/// </summary> 
/// <param name="id">the ID of the button (will be added to the index of the row)</param> 
/// <param name="cssClass">the Class of the control</param> 
/// <param name="alternateText">Signature</param> 
/// <param name="clientClickHandler">the name of the client function that will be invoked when clicked</param> 
/// <param name="clientClickAddParams">Additional parameters that will be passed to the client function</param> 
public void AddImageButtonToRow(string id, string cssClass, string alternateText, string clientClickHandler, string clientClickAddParams)
``` 

In addition to ID, CSS class and the tooltip text in the method is also passed the name of the JS function and the parameters that should pass to this function. 

{% include note.html content="Is the name of the JS function, not its code." %} 

#### Example usage 

```csharp
protected override void Preload()
{
    ...
    WebObjectListView1.AddImageButton(
        "TestRowClientButton", // The ID of the button 
        "ics-wolv-toolbar-button ics-wolv-toolbar-button-icon wolv-test-row-client-button", // CSS class 
        "Test client button in the line",  // Tooltip text 
        "RowBtnClickAlert", // Name of the JS function. 
        // In principle, the option alert(\"you Pressed the custom button in the row with the client handler\"); 
        // also rolls, but for more complex handlers may not work correctly. 
        // Recommended to pass the function name, and describe it separately! 
        string.Empty); // The parameters passed to the function RowBtnClickAlert. 
    ...
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}