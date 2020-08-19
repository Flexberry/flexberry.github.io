--- 
title: FAQ Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP NET Tutorials 
toc: true 
permalink: en/fa_faq-flexberry-web.html 
lang: en 
autotranslated: true 
hash: 69d2c8a432b3fe8b57f16ee688923da781ff8cffc83f405fcbe834b29df7e1cd 
--- 

## How to see the queries going to the source data? 

Need in the web.***config*** in the section <appSettings> add the following line: 

```csharp
<add key="BusinessTaskMonitorType" value="EventTaskMonitor.EventTaskMon, EventTaskMonitor, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null" />
``` 

The Assembly ***EventTaskMonitor.dll*** must be in the bin folder of the web application. 
All SQL queries are displayed in the standard "windows event viewer". To run it you need a command prompt and write the command "eventvwr": 

```

Microsoft Windows [Version 6.1.7601]
(c) Корпорация Майкрософт (Microsoft Corp.), 2009. Все права защищены.

C:\Users\istomin>eventvwr

``` 

## How to watch the timings of execution and debug information on the page? 

To enable tracing on the page, the definition 

```csharp
<%@ Page Language="C#" MasterPageFile="~/Site1.Master"  AutoEventWireup="true" CodeBehind="AC_WebSearchE.aspx.cs" 
Inherits="ICSSoft.STORMNET.AdmConsole.AC_WebSearchE" %>
``` 

Specify Trace="true" as shown below: 

```csharp
<%@ Page Language="C#" MasterPageFile="~/Site1.Master"  AutoEventWireup="true" CodeBehind="AC_WebSearchE.aspx.cs" 
Inherits="ICSSoft.STORMNET.AdmConsole.AC_WebSearchE" Trace="true" %>
``` 

Some of the controls from Flexberry ASP.NET Framework output service information on operation of those or other methods. 

## How to watch the timings of performance and debugging information for the entire web application? 

In the web.config specifies the following key: 

```csharp
<system.web>
<!-- To zakommentirovat it before release to users could not see the extra -->
<trace enabled="true" localOnly="false"/>
<!-- To zakommentirovat it before release to users could not see the extra -->
</system.web>
``` 

localOnly="false" - this in order to be able to view the trace for the server remotely. 

For debug output you need to use code like this: 

```csharp
if (Trace.IsEnabled)
     Trace.Warn("WOLV " + (fView!=null?fView.Name:""), "Begin Page_Load");
``` 

## How to set visibility of buttons for lists? 

WebObjectListView1.Operations is used to set the availability of buttons. For example: 

```csharp
WebObjectListView1.Operations.NewByExampleInRow = true;
WebObjectListView1.Operations.LimitEdit = false;
WebObjectListView1.Operations.DeleteInRow = true;
``` 

## How to set styles on a value in list forms? 

Styles by value for list forms, you can configure the following: 

```csharp
WebObjectListView1.Stylization.ColumnName = "Name";
ICSSoft.STORMNET.Web.Controls.TextStyle ts = new ICSSoft.STORMNET.Web.Controls.TextStyle("Vacuum cleaners", "redtext");
WebObjectListView1.Stylization.Styles = new ICSSoft.STORMNET.Web.Controls.TextStyle&#91;&#93; { ts };
``` 

In this example, for objects that have the property» «name is specified» «vacuum Cleaners will apply the redtext style «qmc. Don't forget to define styles in css before such descriptions. 

## How to disable editing of the object's properties immediately to the entire form? 

Edit the properties of an object directly in all the former you can prevent the following: 

```csharp
webBinder.SetReadOnlyForm(this.Controls, fView, false);
``` 

Only locked server controls that have a ReadOnly property or the Enabled. Applies only to controls that are bound to the transmitted representation. To block individual control, use: 

```csharp
webBinder.SetReadonlyToControl(Ctrl, ReadOnlyFlag);
``` 

### How to display the error that occurred in the pop-up window? 

```csharp
 ICSSoft.STORMNET.Web.Controls.WebErrorBox.RiseErrorBox(ex, Page.Form, false, null); 
``` 

The main thing that was connected style exceptionpanelstyle.css thickbox.css. And the script exceptionpanel.js and thickbox.js. The default is all connected through the ContextHelper, and does not require further intervention. 

## How to connect *.js or *.css file or add the generated script from a content page? 

Use class `ContextHelper`. 

```csharp
 public static void ПодключитьВнешнийФайл(string относительныйПутьКФайлу) 
``` 

To add a new row by the script: 

```csharp
 public static void ДобавитьСкрипт(string скрипт) 
``` 

## How to customize the display of columns in WebObjectListView? 

You should use class methods ViewColumnProvider and xml description of data objects.
Example: 

```csharp

<type name="IIS.Sadie.Webmapplus">
    <property name="Description" width="30" filter="false" cut="true"/>
    <property name="Class.School.The name" width="100" filter="true" cut="true" align="Pacentro"/>
    <property name="Name" width="100" filter="false" cut="true" align="Poprawka" sort="false"/>
</type>
``` 

align property specifies the enumeration values Alignment. 

```csharp

    /// <summary> 
    /// Alignment in columns 
    /// </summary> 
    public enum Выравнивание
    {
        ПоЛевомуКраю,
        ПоЦентру,
        ПоПравомуКраю,
        НеУказано        
    }
``` 

cut – trim long strings or not. filter is available, filtering, searching. sort - sorting for this field. 

## How to specify the operations available in WebObjectListView default? 

Use the key in the config file: 

```csharp

    <add key="WOLVDefaultOperations" value="Refresh Filter"/>
``` 

The value is the names of the operations (names of structure fields OperationsWOLV) specified, separated by commas. 

## How to add a button WebObjectListView with client/server handler 

How to add a button: 

* on the toolbar WebObjectListView from a client handler? 
* on the toolbar WebObjectListView with a server handler? 
* in line WebObjectListView from a client handler? 
* in line WebObjectListView with a server handler? 
The answer to all these questions you will find in the appropriate [article](fa_wolv-add-button.html). 

## How to use cache? 

Use class `CacheHelper`. 
Example usage: 

1.Plain caching: 

```csharp

string key = "ViewColumnProviderXmlDoc";
xmlDoc = (XmlDocument)CacheHelper.GetFromCache(key);
if (xmlDoc == null)
{
    xmlDoc = new XmlDocument();
    xmlDoc.Load(HttpContext.Current.Server.MapPath("~/xml/ViewColumnProvider.xml"));
    CacheHelper.SetToCache(key, xmlDoc);
}
``` 

2.Cache list of objects from DB: 

```csharp

List<ПравилоДоступа> list = CacheHelper.GetFromCache<ПравилоДоступа>("PD" + роль.__PrimaryKey.ToString(), loadingCustomizationStruct);
``` 

In this case, the objects will be loaded at loadingCustomizationStruct if they were not found in the cache. 

3.Download object from the cache: 

```csharp

CacheHelper.LoadFromCache(userRole.Роль);
``` 

In this case, the key cache will be the PrimaryKey of the object. If the object in the cache is not found, the object is downloaded ds.LoadObject(userRole.Role). (A data service by default). 

4.Clearing the cache: 

```csharp

CacheHelper.ClearCacheByKeyStartsWith(startswith);
``` 

Will deletes only those objects whose keys begin with the specified characters. String.Emty will clear the entire cache. 
The cache is stored for the entire domain, that is on all pages for all users. Therefore, you should use caching only if data will often be reused. Need palmnet that the cache must manually be synchronized with the data sources. For stale data in the cache is the responsibility of the developer. 

## How to raise a modal window and open the page? 

Positioning the control on the page and configure it: 

```csharp

<ac:LinkModalPopupWindow ID="myLink" EnableViewState="false" runat="server" URL="homework.aspx" Enabled="true" Text=Link WindowTitle=Homework />
``` 

Can this control to add dynamically or even render manually. 
Or receive using the following function: 

```csharp

LinkModalPopupWindow.GetHtml(this.ClientID + "_lmpw" + j.ToString(), "Read more", "TemaPlanLessonE.aspx?LookUp=true&amp;pk={0}", "Thematic lesson plan", 640, 480, true)
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}