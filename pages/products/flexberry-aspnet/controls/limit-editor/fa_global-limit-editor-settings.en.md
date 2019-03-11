--- 
title: Global settings editor limitations 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_global-limit-editor-settings.html 
lang: en 
autotranslated: true 
hash: 725480e49d06d53100acb0f92bcee861cf2ffc45fe3868bcc63a68bb086ea2bc 
--- 

Global settings [editor limitations](fa_advanced-limit-editor.html) allow you to specify in one place the settings that will be used for all runs in this app editors restrictions. 

The configuration is done via a static class (for example, in `Global.asax.cs`): 

```csharp
public class Global : HttpApplication
{
	// ... 
	
	private void ServiceInit()
	{
		// ... 
		NewPlatform.Flexberry.Web.LimitEditor.Settings.ShowParameterPanel = true;
	}
}
``` 

## hide the panel display options in the constraint editor 

Turn off the display panel with the parameters in the [constraint editor](fa_advanced-limit-editor.html) is done by setting "false" (default "true") parameter 

```csharp
NewPlatform.Flexberry.Web.LimitEditor.Settings.ShowParameterPanel
``` 

## displays the first parent properties 

To enable the display of the first parent property in the constructor constraint (to distinguish which property refers to) is being set to "false" (default "true") parameter 

```csharp
NewPlatform.Flexberry.Web.LimitEditor.Settings.UseShortNames
``` 

The display properties if `UseShortNames = true`: 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/use-short-names.png) 

The display properties if `UseShortNames = false`: 

![](/images/pages/products/flexberry-aspnet/controls/limit-editor/not-use-short-names.png) 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}