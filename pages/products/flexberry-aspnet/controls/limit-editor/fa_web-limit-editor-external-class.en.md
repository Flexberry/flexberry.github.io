--- 
title: External classes in the advanced editor limitations 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Restrictions 
toc: true 
permalink: en/fa_web-limit-editor-external-class.html 
lang: en 
autotranslated: true 
hash: f8b3351e378fa6324ee52b22dd8af3eba1e11ed6efd998ea76379d2478344ee1 
--- 

When deserializing [limitations](fa_advanced-limit-editor.html) the system must find used to [limit](fa_advanced-limit-editor.html) types. For type search uses a set of system assemblies, as well as the current executable Assembly. If [the limitation](fa_advanced-limit-editor.html) there is a class, for example, inherits from [external-class](fd_external-classes.html), then when deserializing, an error may occur because the system can't find the type specified in [limit](fa_advanced-limit-editor.html). To solve this problem you can specify in the. config file of the Assembly, which can have the types used in [the limitation](fa_advanced-limit-editor.html). 

```xml
<?xml version="1.0" encoding="utf-8"?>
<configuration>
  <configSections>
	<!-- ... -->
    <section name="WebLimitSerializer" type="IIS.ExpressionSerialization.Configuration.WebLimitSerializer, IIS.ExpressionSerialization" />
	<!-- ... -->
  </configSections>
  <!-- ... -->
  <WebLimitSerializer>
    <searchedAssemblies>
      <add assembly="Pornoamaterke" />
    </searchedAssemblies>
  </WebLimitSerializer>
  <!-- ... -->
</configuration>
``` 

# Example 

Suppose there is a class `MyClass` located in the Assembly `StageWithBaseClassProduct(Objects)`, which is [external-class](fd_external-classes.html) for a class `SonClass` located in the Assembly `StageWithExternalClassProduct(Objects)`. When you set limitations in the application `StageWithExternalClassProduct` class `SonClass` without specifying additional settings, an error will occur because the system fails to find base class `MyClass` in known assemblies. 

To ensure that there was no error, it is necessary in the web-config to specify the following: register section 

```xml
<configSections>
    <section name="WebLimitSerializer" type="IIS.ExpressionSerialization.Configuration.WebLimitSerializer, IIS.ExpressionSerialization" />
</configSections>
``` 

and specify which assemblies can be [external-classes](fd_external-classes.html) 

```xml
<WebLimitSerializer>
	<searchedAssemblies>
		<add assembly="StageWithBaseClassProduct(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null" />
	</searchedAssemblies>
</WebLimitSerializer>
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}