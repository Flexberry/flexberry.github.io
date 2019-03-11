--- 
title: Opening lookup-shape with the support of the hierarchy 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_lookup-form-hierarchy.html 
lang: en 
autotranslated: true 
hash: 7b1e84de2fe8898af59a2e26a23fa6f05e049753829455df541fe89cbd4c6357 
--- 

Two ways by which you can display the data on the LookUp form a hierarchical view: 

* Use properties `LookUpFormURL`; 
* Use `WOLVSettApplyer`. 

## the use of the LookUpFormURL 

Control `MasterEditorAjaxLookUp` allows you to specify a URL of the form that will be displayed to select the element by specifying the properties `LookUpFormURL`. 

Before you set the value of this property, you must ensure that the list form with the support of the hierarchy has been developed, and also to avoid possible errors, specify the address for the list form that displays data in hierarchical form, it is necessary to define a property `FormPath` that contains the path to the list forms a hierarchy. 

For example, there is a list form `TestHierarchy.aspx`, which has the following property set `FormPath`: 

```csharp
/// <summary> 
/// The path to the forms. 
/// </summary> 
public static string FormPath
{
    get
    {
        return "~/forms/Controls/WOLV/HierarchyTests/TestHierarchy.aspx";
    }
}
``` 

Then on that page where you used lookup, you will need to control-lucapa to set properties `LookUpFormURL` the following (in addition to other essential settings): 

```csharp
lookupTest.LookUpFormURL = TestHierarchy.FormPath;
``` 

## Use WOLVSettApplyer 

You can also use a special tuner for WOLV, which allows you to specify additional settings for WOLV in the application layer, - `WOLVSettApplyer`, which in turn is `корне` Assembly ASP.NET app. 

However, you should verify that in the configuration file of the web application you specify the correct handler for lookup-shape, namely that which was generated. That is, if you have an Assembly `SomeAssambly(ASP.NET Application)`, the correct indication handler lookup forms will be as follows: 

__For IIS 7:__ 

```xml
<handlers>
...
<add name="LookUpForm" path="LookUpForm.aspx" verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, SomeAssambly(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" resourceType="Unspecified" preCondition="integratedMode" />
...
</handlers>
``` 

__For IIS 6:__ 

```csharp
<httpHandlers>
...
<add verb="*" path="LookUpForm.aspx" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[ICSSoft.STORMNET.Web.Controls.LookUpForm, SomeAssambly(ASP.NET Application)]], ICSSoft.STORMNET.Web.AjaxControls" validate="false" />
...
</httpHandlers>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}