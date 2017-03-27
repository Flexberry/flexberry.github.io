---
title: Глобальные настройки редактора ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_global-web-limit-editor-settings.html
folder: products/flexberry-aspnet/
lang: ru
---

Глобальные настройки [редактора ограничений](fa_advanced-limit-editor.html) позволяют задать в одном месте настройки, которые будут использоваться для всех запускаемых в этом приложении редакторов ограничений.

Настройка производится через статический класс (например, в `Global.asax.cs`):

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

## Скрытие отображения панели параметров в редакторе ограничений
Отключения отображения панели с параметрами в [редакторе ограничений](fa_advanced-limit-editor.html) производится через установку "false" (по умолчанию "true") у параметра
```csharp
  NewPlatform.Flexberry.Web.LimitEditor.Settings.ShowParameterPanel
```

## Отображение первого родительского свойства
Для включения режима отображения первого родительского свойства в конструкторе ограничений (чтобы отличать, какое свойство к чему относится) производится установка "false" (по умолчанию "true") в параметр
```csharp
  NewPlatform.Flexberry.Web.LimitEditor.Settings.UseShortNames
```

Отображение свойств, если `UseShortNames = true`:

![](/images/pages/img/page/GlobalWebLimitEditorSettings/UseShortNames.png)

Отображение свойств, если `UseShortNames = false`:

![](/images/pages/img/page/GlobalWebLimitEditorSettings/NotUseShortNames.png)

----------
## Глобальные настройки редактора ограничений

Глобальные настройки [Advanced-limit-editor|редактора ограничений) позволяют задать в одном месте настройки, которые будут использоваться для всех запускаемых в этом приложении редакторов ограничений.

Настройка производится через статический класс (например, в `Global.asax.cs`):
``` csharp
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

### Скрытие отображения панели параметров в редакторе ограничений

Отключения отображения панели с параметрами в [Advanced-limit-editor|редакторе ограничений) производится через установку "false" (по умолчанию "true") у параметра
``` csharp
  NewPlatform.Flexberry.Web.LimitEditor.Settings.ShowParameterPanel
```

### Отображение первого родительского свойства

Для включения режима отображения первого родительского свойства в конструкторе ограничений (чтобы отличать, какое свойство к чему относится) производится установка "false" (по умолчанию "true") в параметр
``` csharp
  NewPlatform.Flexberry.Web.LimitEditor.Settings.UseShortNames
```

Отображение свойств, если `UseShortNames = true`:

[image||{UP(GlobalWebLimitEditorSettings)}UseShortNames.png)

Отображение свойств, если `UseShortNames = false`:

[image||{UP(GlobalWebLimitEditorSettings)}NotUseShortNames.png)

