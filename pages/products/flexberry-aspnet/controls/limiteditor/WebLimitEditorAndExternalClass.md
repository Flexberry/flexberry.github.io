---
title: External-классы в расширенном редакторе ограничений
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/web-limit-editor-and-external-class.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Платформа''': [FlexberryASP.NET](flexberry-a-s-p-n-e-t.html).
* '''Компонент''': [Расширенный редактор ограничений](advanced-limit-editor.html).
* '''Предназначение''': 
</td>
</tr></tbody></table></a>
</div>



# Расширенный редактор ограничений
Эта статья описывает часть информации о [Расширенном редакторе ограничений](advanced-limit-editor.html).

# External-классы в расширенном редакторе ограничений
При десериализации [ограничения](advanced-limit-editor.html) система должна найти использованные в [ограничении](advanced-limit-editor.html) типы. Для поиска типов используется набор системных сборок, а также текущая исполняемая сборка. Если в [ограничении](advanced-limit-editor.html) есть класс, например, наследуемый от [external-класса](external-classes.html), то при десериализации может произойти ошибка, поскольку система не сможет найти тип, указанный в [ограничении](advanced-limit-editor.html). Для решения данной проблемы в версии шаблона после 06.11.2014 можно указывать в config-файлы сборки, в которых могут присутствовать типы, используемые в [ограничении](advanced-limit-editor.html).

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
Пусть есть класс `MyClass`, расположенный в сборке `StageWithBaseClassProduct(Objects)`, который является [external-классом](external-classes.html) для класса `SonClass`, расположенного в сборке `StageWithExternalClassProduct(Objects)`. При задании ограничения в приложении `StageWithExternalClassProduct` на класс `SonClass` без указания дополнительных настроек произойдёт ошибка, поскольку системе не удастся найти базовый класс `MyClass` в известных ей сборках.

Для того, чтобы ошибки не было, необходимо в файле web-config указать следующее: зарегистрировать секцию
```xml
<configSections>
    <section name="WebLimitSerializer" type="IIS.ExpressionSerialization.Configuration.WebLimitSerializer, IIS.ExpressionSerialization" />
</configSections>
```

и указать, в каких сборках могут находиться [external-классы](external-classes.html)
```xml
<WebLimitSerializer>
	<searchedAssemblies>
		<add assembly="StageWithBaseClassProduct(Objects), Version=1.0.0.1, Culture=neutral, PublicKeyToken=null" />
	</searchedAssemblies>
</WebLimitSerializer>
```
