--- 
title: Connection of scripts and styles based on PlaceholderPageContentConnector 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_placeholder-page-content-connector.html 
lang: en 
autotranslated: true 
hash: 99061a34e5b2d5731450243b15cc671f72a1d7cc07fed89fe0dc2068021720d7 
--- 

Class `PlaceholderPageContentConnector` is used to connect the technological scripts and styles necessary to operate the controls and runtime using the controls-placeholders. This method allows you to configure your application more flexible by adding application code before/after the technological and user. 

Is an alternative method of connection resources [FilterPageContentConnector](fa_filter-page-content-connector.html). 

## working Principle 

Connect resources is by handling events of the beginning of rednering page (`PreRenderComplete`): 

1. in the collection of controls on the page (including Master page) are the controls with the following IDs: 
* `FlexberryScripts` is a placeholder for the connection скриптов; 
* `FlexberryStyles` is a placeholder for the connection стилей; 
* `FlexberryRawHtml` is a placeholder for the connection of special technological элементов; 
2. the collection of placeholders controls are added controls that connect resources. 

If controls are found, an exception will be raised. 

{% include warning.html content="due To the fact that the resources are connected in `PreRenderComplete` the elements connected after will not be added to the markup." %} 

## Setting 

1.To connect resources based on the placeholders you want to configure `unity` (`web.config`): 

```xml
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http"
          mapTo="NewPlatform.Flexberry.Web.Http.PlaceholderPageContentConnector, NewPlatform.Flexberry.Web.Http" />
``` 

2.In the page layout / Master page you need to add controls-placeholders: 

```xml
<asp:Placeholder ID="FlexberryStyles" runat="server" />
<asp:Placeholder ID="FlexberryScripts" runat="server" />
<asp:Placeholder ID="FlexberryRawHtml" runat="server" />
``` 

{% include note.html content="a Placeholder `FlexberryStyles` usually added in the <head> of the page." %} 

{% include warning.html content="the placeholder `FlexberryScripts` affect the correct operation of custom scripts added to the page in `ContentPlaceHolder0`. `FlexberryScripts` should be placed higher in the markup." %} 

{% include note.html content="when manually added FlexberryScripts, the data can still be connected to the end of the <body> tag (it should have an attribute `runat=server`)!" %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}