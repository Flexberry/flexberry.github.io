--- 
title: PageContentManager 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_page-content-manager.html 
lang: en 
autotranslated: true 
hash: 2d582e77db739766b60f53983672cf0047a3faa583811029505dc3b3e2dea5f4 
--- 

Class `PageContentManager` is designed to connect skitov (js) and styles (css) in C# code. 

{% include note.html content="`PageContentManager` is a replacement for the deprecated [ContextHelper](fa_context-helper.html). All new projects should use it." %} 

## working Principle 

The main purpose of `PageContentManager` - formation of the list of resources that need to be connected to the current page. The connection is made through `IPageContentConnector` (which must be configured in unity). 

There are two ways of connecting resources: 

* [based on filtering HTML](fa_filter-page-content-connector.html) (default for reasons of backward compatibility); 
* [on the basis of the controls-placeholders](fa_placeholder-page-content-connector.html) (recommended); 

{% include note.html content="If you do not register a type in the unity configuration file, it will work the old way of adding scripts to the page using [FilterPageContentConnector](fa_filter-page-content-connector.html)." %} 

{% include note.html content="Resources will be connected in the same order in which they were added (FIFO)." %} 

{% include note.html content="Scripts and styles with the same path are connected only once." %} 

### setup activation script 

By analogy with Ohm `ContextHelper`'and his methods `ДобавитьСкрипт` and `ДобавитьСкриптВDocumentReady`, `PageContentManager`'and have the ability to customize the activation of the script. To this end, the method call to add the script has a flag `onPageLoad` the default `false`. 

Thus, the analog method is `ДобавитьСкриптВDocumentReady` call `AttachJavaScriptCode(script, true);` 

## Example usage 

```csharp
PageContentManager.AttachJavaScriptCode("alert('Hello');", true); // Displays a message box when the page loads. 
``` 

## Own method of connection of scripts and styles 

To use your own method of connection resources, you must create a new class and inherit interface `IPageContentConnector`. 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}