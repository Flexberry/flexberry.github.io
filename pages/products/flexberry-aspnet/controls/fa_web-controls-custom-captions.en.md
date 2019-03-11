--- 
title: Customizing signatures in Web controls 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET, Web UI (Controls) 
toc: true 
permalink: en/fa_web-controls-custom-captions.html 
lang: en 
autotranslated: true 
hash: 0492cab388d6688e76546d67f1132524ba54912dfedf912bc0ddb08e90941274 
--- 

Captions controls are stored in the string resources in *.. resx files corresponding to one or another control. 
For example, the signature elements WebObjectListView are stored in the project `ICSSoft.STORMNET.Web.AjaxControls` in the file `Resources/WebObjectListView.resx`, 
a signature to the DatePicker control in the same project ICSSoft.STORMNET.Web.AjaxControls in the file Resources/DatePicker.resx, etc. 

If at the application level you want to override the caption for a particular control element, is sufficient in the application project create a file with string resource with the name of the relevant control, and override the desired string resource. 
For example, to the control WebObjectListView to change the caption on the update button from "Update" to "Refresh list", just in the application proiecte to create a file `Resources/WebObjectListView.resx` 
and define it in the string resource "ToolbarButtonRefreshCaption" with the value "Update". 
The current list of available `*.resx файлов` and their contents a better look at the project `ICSSoft.STORMNET.Web.AjaxControls` in the directory `Resources`. 

## Development of controls with customizable captions 

In order to develop signatures of the controls were customizable (using the previously described approach), in designer-generated e `*.resx файлов` need to replace the use of standard `ResourceManager-a ` on `ICSSoft.STORMNET.Web.Tools.Resources.ResourceManger`. 

For example, in `WebObjectListView.Designer.cs` corresponding to the file `WebObjectListView.resx` following generated code: 

```csharp
private static global::System.Resources.ResourceManager resourceMan;
        
private static global::System.Globalization.CultureInfo resourceCulture;

[global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
internal WebObjectListView() {
}

/// <summary> 
/// Returns the cached ResourceManager instance used by this class. 
/// </summary> 
[global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
internal static global::System.Resources.ResourceManager ResourceManager {
	get {
		if (object.ReferenceEquals(resourceMan, null)) {
		    // Please note, in the automatically-generated code, the first parameter is given the full path to the *.the. resx file, including the namespace. 
			// In this case, the component may not be available customization of the signatures. 
			global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("ICSSoft.STORMNET.Web.AjaxControls.Resources.WebObjectListView", typeof(WebObjectListView).Assembly);
			resourceMan = temp;
		}
		return resourceMan;
	}
}

``` 

was replaced by: 

```csharp
private static ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager resourceManager;

private static global::System.Globalization.CultureInfo resourceCulture;

[global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
internal WebObjectListView() {
}

/// <summary> 
/// Returns the cached ResourceManager instance used by this class. 
/// </summary> 
[global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
internal static ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager ResourceManager {
	get {
		if (object.ReferenceEquals(resourceManager, null)) {
		    // Note that the first parameter is given not the full path *.the. resx file and a path relative to the Resources directory. 
			// Expected string resources in the Assembly ICSSoft.STORMNET.Web.AjaxControls, and applied nahodatsa application in the Resources directory in the file WebObjectListView.resx. 
			ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager temp = new ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager("WebObjectListView", typeof(WebObjectListView).Assembly);
			resourceManager = temp;
		}
		return resourceManager;
	}
}
``` 

**Important: designer-e application *.a. resx file to change anything not necessary.** 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}