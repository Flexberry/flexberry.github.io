--- 
title: MsDropDown 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_ms-drop-down.html 
lang: en 
autotranslated: true 
hash: ac104d76651ee7615d7a00236cf3d7a82bb9d2aad45f03ab881ca1cb4cf1ee55 
--- 

`MsDropDown` is [web control](fa_web-controls.html), which allows you to view the list in a drop-down list (similar [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) with the icons for list items. 

Below shows an example of displaying this control on web form. 

![](/images/pages/products/flexberry-aspnet/controls/ms-drop-down.png) 

### Public properties MsDropDown 

|Property|Description| 
|-------------|---------------------------------| 
|`Items`|List items| 
|`SelectedItem`|Selected element| 
|`ReadOnly`|the Flag responsible for allow/disallow manipulation of control| 

### MsDropDownItem and its properties 

Each `Item` (list item) is an instance of the class `MsDropDownItem`. 

|Property|Description| 
|-------------|---------------------------------| 
|`Text`|Main text by which to identify the object in display list| 
|`ImagePath`|Path to the picture you want to display next to the text| 
|`Description`|Description, alternate text, which will be located near the main| 

## Connection 

The connection is possible using a page layout content list is available only manually in the code. To do this in the markup of the page .aspx we need to add the appropriate control: 

```xml
<ac:MsDropDown ID="<ID of control>" ReadOnly="<False/True>" runat="server"/>
``` 

## Use 

Use `MsDropDown` (as [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) appropriate when it is known beforehand that the number of craftsmen small and there is no need to select them to raise a separate form of choice. 

### Sample code 

To get the list shown in the picture above, you should do the following: 

Code .aspx page: 

```xml
<div class="clearfix">
    <asp:Label CssClass="descLbl" ID="ctrlTestMsDropDownLabel" runat="server" Text=List EnableViewState="False">
    </asp:Label>
    <ac:MsDropDown ID="ctrlTestMsDropDown" ReadOnly="False" runat="server"/>
</div>
``` 

Creating a list of elements: 

```csharp
/// <summary> 
/// Creating a list of elements of <see cref="MsDropDown"/> when loading the control. 
/// </summary> 
/// <param name="e">event Parameters.</param> 
protected void Page_Load(object sender, EventArgs e)
{
    // Create list items programmatically. 
    // Text - the primary text by which to identify the object in the display list. 
    // ImagePath is the path to the picture you want to display next to the text. 
    // Description - additional text that will be located next to the main. 
    var item1 = new MsDropDownItem();
    item1.Text = "First";
    item1.ImagePath = "/Images/cat.png";
    item1.Description = "List element";

    var item2 = new MsDropDownItem();
    item2.Text = "Second";
    item2.ImagePath = "/Images/dog.png";
    item2.Description = "List element";

    var item3 = new MsDropDownItem();
    item3.Text = "Third";
    item3.ImagePath = "/Images/pig.png";
    item3.Description = "List element";

    // Add created elements to the control. 
    ctrlTestMsDropDown.Items.Add(item1);
    ctrlTestMsDropDown.Items.Add(item2);
    ctrlTestMsDropDown.Items.Add(item3);
}
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}