--- 
title: the Subsystem settings 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms 
summary: the operation of the storage subsystem of user settings using the class SettingManager; specified as to include storage settings, what are the methods to be used for writing and reading settings 
toc: true 
permalink: en/fw_setting-manager.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 5945c9c00c5614b7d57aa12564c3e505271d8dbae3c8d447e05252d03e7ec869 
--- 

## Enable subsystem settings 
The subsystem is to implement saving the user preferences in the application. 


Access to the system settings provided by the wizard, a set of static class methods `ICSSoft.STORMNET.Settings.SettingManager`. 


In order to enable the subsystem settings, you need to install a static property `ICSSoft.STORMNET.Settings.SettingManager.UseSettings` in `true`. Also, you can do it in `config` file for an application, which requires to add the line: 

```xml
<add key="UseSettings" value="True"/>	
``` 

## Save your own settings 
Saving settings is performed by the function `SetSettings`, read settings function `GetSetting`. 


The settings indicated: 
* The name of the module that saved the settings. It's just a logical name that allows you to set group settings. Hence, when any setting, actually read and cached all the settings for the module. 
* Name settings. 
* The setting value. 
Setting identificireba unique module name, setup name and user name. Username settings Manager recovers automatically. 


Example (saving position and size of the form): 

```csharp
SettingManager.SetSetting("SettingsTest", "Form Position&Size", string.Join(",", new string[]{
					this.Left.ToString(),
					this.Top.ToString(),
					this.Width.ToString(),
					this.Height.ToString()
		}));
``` 

An example of (read the position and size of the form): 

```csharp
	string setting = SettingManager.GetSetting("SettingsTest", "Form Position&Size");
	if (setting!=null)
	{
		string[] splittedsetting = setting.Split(',');
		this.Left=int.Parse(splittedsetting[0]);
		this.Top=int.Parse(splittedsetting[1]);
		this.Width=int.Parse(splittedsetting[2]);
		this.Height=int.Parse(splittedsetting[3]);
	}
``` 

{% include important.html content="There is a possibility to represent any object in the form `XML` string using the standard `SOAP` serialization `.NET`. This is done by the utility `ICSSoft.STORMNET.Windows.Forms.Utils.ObjectToString` (reverse — `ObjectFromString`). Therefore, it is possible not to form the string manually setting value, and use this utility." %} 

Serialization of data objects is performed in a different way: [How the «spin» data object to an XML string and restore back](fo_aggregating-function.html). 

{% include note.html content="new Designed [UserSettingsService](fa_user-settings-service.html), designed to replace `SettingManager`." %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}