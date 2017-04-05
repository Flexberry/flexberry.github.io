---
title: External-классы в расширенном редакторе ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/fa_web-limit-editor-external-class.html
lang: ru
---

При десериализации [ограничения](fa_advanced-limit-editor.html) система должна найти использованные в [ограничении](fa_advanced-limit-editor.html) типы. Для поиска типов используется набор системных сборок, а также текущая исполняемая сборка. Если в [ограничении](fa_advanced-limit-editor.html) есть класс, например, наследуемый от [external-класса](fd_external-classes.html), то при десериализации может произойти ошибка, поскольку система не сможет найти тип, указанный в [ограничении](fa_advanced-limit-editor.html). Для решения данной проблемы можно указывать в config-файлы сборки, в которых могут присутствовать типы, используемые в [ограничении](fa_advanced-limit-editor.html).

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
      <add assembly="ПолноеИмяСборки" />
    </searchedAssemblies>
  </WebLimitSerializer>
  <!-- ... -->
</configuration>
```

# Пример

Пусть есть класс `MyClass`, расположенный в сборке `StageWithBaseClassProduct(Objects)`, который является [external-классом](fd_external-classes.html) для класса `SonClass`, расположенного в сборке `StageWithExternalClassProduct(Objects)`. При задании ограничения в приложении `StageWithExternalClassProduct` на класс `SonClass` без указания дополнительных настроек произойдёт ошибка, поскольку системе не удастся найти базовый класс `MyClass` в известных ей сборках.

Для того, чтобы ошибки не было, необходимо в файле web-config указать следующее: зарегистрировать секцию

```xml
<configSections>
    <section name="WebLimitSerializer" type="IIS.ExpressionSerialization.Configuration.WebLimitSerializer, IIS.ExpressionSerialization" />
</configSections>
```

и указать, в каких сборках могут находиться [external-классы](fd_external-classes.html)

```xml
<WebLimitSerializer>
	<searchedAssemblies>
		<add assembly="StageWithBaseClassProduct(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null" />
	</searchedAssemblies>
</WebLimitSerializer>
```
