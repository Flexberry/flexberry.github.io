--- 
title: Connection of scripts and styles based on FilterPageContentConnector 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_filter-page-content-connector.html 
lang: en 
autotranslated: true 
hash: 956b8bdb751722d8a1950e59b5472e036182315da0c9c5e52455539e7db8e4dc 
--- 

Class `FilterPageContentConnector` used for technological connection of scripts and styles that are necessary for the operation of the controls and the runtime environment by using post-processing of the page layout. The scripts are included at the end of the tag `<body>`, styles - at the end of the tag `<head>`. 
Is an alternative method of connection resources [PlaceholderPageContentConnector](fa_placeholder-page-content-connector.html). 

## working Principle 

Before sending the generated HTML markup the user is post-processing page 

1. determines the position for inserting data (end tag `<head>` for styles and the end tag `<body>` for scripts and other specials. компонентов; 
2. the position of the inserted extra HTML markup to connect ресурсов; 
3. the corrected data is sent to the user. 

{% include note.html content="Scripts, stylesheets and other files are always appended to the end of the page, and an application developer there is no way to insert some code after them." %} 

{% include warning.html content="With this method of connection data, there are potential errors associated with the algorithm of finding the position to add a markup. For example, when on the page for some reason, there are two closing tag `<head>` (e.g. inside a comment), the position can be determined correctly. 

These problems relate to the fact that a full analysis of HTML is not possible (due to complexity and resource consumption), it looks for the first possible position. 

In most cases (especially for new projects) is to use [PlaceholderPageContentConnector](fa_placeholder-page-content-connector.html)." %} 

## Setting 

To connect resources, based on the filters you want to configure unity (web.config): 

```xml
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http"
          mapTo="NewPlatform.Flexberry.Web.Http.FilterPageContentConnector, NewPlatform.Flexberry.Web.Http" />
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}