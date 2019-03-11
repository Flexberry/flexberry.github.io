--- 
title: Examples of how technological forms 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_technological-forms-customization-example.html 
lang: en 
autotranslated: true 
hash: 8c53065c6a8efb443f739a26017abb36b949d7c6c9cfc1a40ea909131082873f 
--- 

Settings technological forms [web applications](fa_flexberry-asp-net.html) are carried out using the service `DynamicPageTuner`. The class was in section `unity`. 

```xml
<unity xmlns="http://schemas.microsoft.com/practices/2010/unity">
      <...>
    <container>
      <...>         
      <!-- Configuration service for customization of technological forms in the project. -->
      <register type="NewPlatform.Flexberry.Web.Page.IDynamicPageTuner, ICSSoft.STORMNET.Web.AjaxControls" mapTo="WebFormsTestStand.DynamicPageTuner, TestStand(ASP.NET Application)">
        <lifetime type="singleton" />
        <constructor />
      </register> 
      <...>         
    </container>
  </unity>
``` 

It can help you: 

* change the appearance of technological forms, 
* configure the form title, 
* to defer loading of the list 
* customize the order and display of columns and more. 

## Example lazy loading of data 

```csharp
public class DynamicPageTuner : IDynamicPageTuner, IDynamicPageWolvTuner
{
   /// <summary> 
   /// Sample implementation of the configuration method of <see cref="WebObjectListView" /> on the technology pages. 
   /// </summary> 
   /// <param name="pageId">page ID.</param> 
   /// <param name="wolv">Instance of <see cref="WebObjectListView" /> to configure.</param> 
   public void Tune(DynamicPageIdentifier pageId, WebObjectListView wolv)
   {
       // For the list of classes included lazy loading of data 
       // for other pages, nothing changes. 
       if (pageId == DynamicPageIdentifier.SecurityClassesList)
       {
           if (!wolv.Page.IsPostBack)
               wolv.SkipDataLoad = true;
       }
    }
}
``` 

## an Example of configuring display columns 

```csharp
public class DynamicPageTuner : IDynamicPageTuner, IDynamicPageWolvTuner
{
   // Configure the order and number of columns on the user list. 
   if (pageId == DynamicPageIdentifier.SecurityUsersList)
   {
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Login), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Name), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Creator), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.CreateTime), true));
      wolv.ColumnsConfig.Add(new ColumnsConfig
           (Information.ExtractPropertyPath<Agent>(x => x.Enabled), true));
   }
}
``` 

As follows: 

![](/images/pages/products/flexberry-aspnet/example-teh-settings.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}