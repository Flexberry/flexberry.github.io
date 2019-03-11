--- 
title: Technological forms Flexberry ASP.NET 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_tech-forms-web.html 
lang: en 
autotranslated: true 
hash: 7a1da6332baf79cdb5474fac7f46f8505e39b6f33d9e783ce23d24692f895d12 
--- 

Technology Flexberry ASP.Net generates a standard (universal) page to display and edit data. 

## a List of process forms 

### Page setup using DynamicPageRoute 

Part of the process pages are available via [DynamicPageRoute](fa_routing.html): 
* [Web-form audit](fa_audit-web-forms.html) 
* User locks (`routes.AddDynamicPageRoute("flexberry/LocksList", DynamicPageIdentifier.LocksList)`) 
* Entry form log (`routes.AddDynamicPageRoute("flexberry/LogList", DynamicPageIdentifier.LogList)`) 
* Form versions of assemblies (`routes.AddDynamicPageRoute("flexberry/Version", DynamicPageIdentifier.Version)`) 
* LookUp-form 
* Other 

### Limiting access to forms 

Because access forms needs to be restricted, then in the web.config to give access to a technological form that only users that have the role `admin`, you can add: 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>

	...

  <!--Define access to technological forms.-->
  <location path="flexberry">
    <system.web>
      <authorization>
        <!--The following code is given access only to admins. To prevent access by unauthorized users, you can use the 'deny users="?"'.-->
        <allow roles="admin"/>
        <deny users="*"/>
      </authorization>
    </system.web>
  </location>
</configuration>
``` 

## Other technological forms 

* [Lookup](fa_lookup-overview.html)-page `ICSSoft.STORMNET.Web.AjaxControls.Forms.LookUpForm` 

![](/images/pages/products/flexberry-aspnet/lookup-form.png) 

* Page column settings `ICSSoft.STORMNET.Web.AjaxControls.Forms.ChooseColumns` 

![](/images/pages/products/flexberry-aspnet/column-setup-page.png) 

{% include note.html content="columns settings Page identical to the page used for the [export to Excel](fa_wolv-export-excel.html)." %} 

* Page printing (`ICSSoft.STORMNET.Web.AjaxControls.Forms.ListPrintForm`) 
* A page with information about the versions of the assemblies 

## adjustment of process forms 

Main article [setup tehnologicheskih pages Flexberry ASP.NET](fa_technological-forms-customization-example.html) 

## Create your own page (for example, Lookup page) 

1. To create your own class and posledovat it from `ICSSoft.STORMNET.Web.Controls.LookUpForm`. 
2. (optional) to Create a factory instance on the page (a class that will create a page). Instead of creating your implementation, you may use technological class `ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories<T>`. 
3. Set in `web.config` HTTP handler for the page 

for the HTTP handler: 

```xml
<add verb="*" path="LookUpForm.aspx" type="MyProjectNamespace.LookUpFormHandlerFactoryType" validate="false"/>
``` 

for processing HTTP handler: 

```xml
<add path="LookUpForm.aspx" 
       verb="*" 
       type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.PageHandlersFactory`1[[MyProjectNamespace.LookUpFormType, MyProjectAssembly]], ICSSoft.STORMNET.Web.AjaxControls" 
       validate="false" />
``` 

{% include note.html content="the Generator automatically creates and registers lucap application form for new projects." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}