--- 
title: Development ASP.NET controls 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_developing-controls.html 
lang: en 
autotranslated: true 
hash: 37b2af6b8140d03abcc87305ddba9a7923b48c001355d718b99482e2ee09769c 
--- 

The article proposes a common set of rules for proper/supported/enhanced the architecture of the web controls using the platform [FlexberryASP.NET](fa_flexberry-asp-net.html). 

## Connection of scripts and styles 

1. Control should be as Autonomous from the point of view of dependency from the scripts/styles. It should be possible to connect the control to the empty ASP.NET page ([System.Web.UI.Page](https://msdn.microsoft.com/en-us/library/system.web.ui.page(v=vs.110).aspx)) without additional manipulations. 
* **all** used JS libraries and scripts should connect themselves контролом; 
* if the control uses a common JS libraries (e.g. jQuery), then all dependent scripts have to connect after stage *Init* (*Load* and on) since the base libraries are connected to *MasterPage.Init*; connection on stage *Init* will lead to the fact that the required library will connect later in the markup with all its последствиями; 
2. All scripts and styles must be stored in the web Assembly. 
3. Connect all skipti and styles follows by [PageContentManager](fa_page-content-manager.html) in `OnLoad`. 
* this prevents multiple connections to the same scripts/styles if the file podklyuchaetsya in several places (the controls); 

## Initialization of the internal structure of the control 

### Download information 

1. All data should be fully uploaded by the end of the stage *Load* 

### Work with IDs 

Peculiarities of generation of client IDs of controls in ASP.NET: 

1. Starting with the .NET Framework 4, to generate the *ClientId* uses several algorithms - so-called *ClientIDMode* ([MSDN](https://msdn.microsoft.com/en-us/library/system.web.ui.control.clientidmode.aspx)). 
2. If control is not specified *ID* (*ID == null*) then it will be automatically generated when you **first call** to *ClientID* using the current *ClientIDMode*. 
3. If the control id specified in the attributes, and set *ID*, then the rendering will be used *CleintID*. If *ID* is not specified, then it will use the value from the attribute. 

In this regard, may appear strange behavior when rendering IDs in atleticke, for example, when using `Watch`. 

Example: 

``` csharp
_inputField = new HtmlGenericControl("div"); // ID is not specified => _inputField.ID == null 
_inputField.Attributes["id") = "SomeID";     // The client ID is specified via the HTML attribute. 
                                             // If somewhere before the rendering / the Watch will be called _inputField.ClinetID, 
                                             // ID != null => the generated CleintID will be used. 
``` 

The expected result of rendering: 

```html
<div id="SomeID"></div>
``` 

The result of the rendering (when referring to `ClientID`): 

```html
<div id="ctl90"></div>
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}