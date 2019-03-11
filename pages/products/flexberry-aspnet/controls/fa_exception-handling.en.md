--- 
title: WebErrorBoxRiser and ErrorForm 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_exception-handling.html 
lang: en 
autotranslated: true 
hash: cd35cbc03c559bfa0e58ebf0437a0556244896180d52d0e8c0e5e3a3d5b781ad 
--- 

`ErrorForm` - aspx-page that shows an unhandled exception, it is called in `Global.asax`. 

`WebErrorBoxRiser` - control, which is used to display exceptions. 

## Display exception 

In order to show the beautiful window with the processed exception call the `WebErrorBox.Rise(exception, showDetails, errorCaption)`. 

Example: 

```csharp
try
{
    // Do something unsafe. 
}
catch (Exception ex)
{
    WebErrorBoxRiser.Rise(ex, false, "An error occurred while processing the form");
}
``` 

## Display ErrorForm 

`ErrorForm` is displayed when an unhandled exception occurs. It shows the incident the exception. 

{% include note.html content="it is Possible when in `Page_Load` page an exception is handled using `WebErrorBoxRiser`, but after that will be called `Page_Load` controls, which, in turn, will create an exception. Thus, an error window will not appear and will appear `ErrorForm`, which displays to the exclusion of the control and the exception that is processed in `WebErrorBoxRiser`." %} 

## Display WebErrorBoxRiser 

When you call the method 

```csharp
WebErrorBoxRiser.Rise(exception, showDetails, errorCaption)
``` 
an exception is added to 

```csharp
HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions]
``` 

Then, by [ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule](fa_response-filter-module.html), is called `Render` have `WebErrorBoxRiser` that generates the Html and displays the page. 
To get all the exceptions that were added using the `WebErrorBoxRiser` you should use 

```csharp
(List<WebErrorBoxRiser>) HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions];
``` 

## Show dopolnitelnoi information about the exception 

To see `StackTrace` on the page you want to add to `web.config` in `<appSettings>` 

```xml
<add key="ShowErrorDetails" value="true" />
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}