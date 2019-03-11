--- 
title: Load data and EmptyControl in WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-empty-control.html 
lang: en 
autotranslated: true 
hash: ed74164261562e992fd4793a3acf7cbca4e9a7df02292c616be854d0a41e5c50 
--- 

## EmptyControl 

If WOLV is no data, you can display your control that tells the user about the lack of data. The default seems `ICSSoft.STORMNET.Web.AjaxControls.EmptyWOLVControl`. 

To change control is necessary in the property `WOLV.EmptyControlType` to specify the type it type the following: 

1.To create a control with an indication that should be displayed instead of the default content. 

```csharp
    /// <summary> 
    /// ... 
    /// </summary> 
    public partial class TestEmptyControl : BaseListWebControl
    {
        /// <summary> 
        /// ... 
        /// </summary> 
        protected override void OnLoad(EventArgs e)
        {
            var control = new LiteralControl("This list is not provided"data);
            Controls.Add(control);
            base.OnLoad(e);
        }
    }
    ``` 

2.Create a list page where you will specify the type of control. 

```csharp
    /// <summary> 
    /// ... 
    /// </summary> 
    public partial class BaseListFrormEmptyControl : BaseListForm<...>
    {
        ...

        /// <summary> 
        /// Called the first in the Page_Load. 
        /// </summary> 
        protected void Page_Load(object sender, EventArgs e)
        {
            WebObjectListView1.EmptyControlType = typeof(TestEmptyControl);
        }

        ...
     }
    ``` 

In the end, the list page will look like this: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/empty-wolv-control.png) 

## Download information 

To disable loading data, you must set the property `SkipDataLoad`. 

For example, if the data in long and WOLV are loaded when you first load the page, it is preferable to hide them, but immediately to allow the user to enter a filter value, it is possible to `Page_Load` to register: 

```csharp
wolv.SkipDataLoad = !IsPostBack;
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}