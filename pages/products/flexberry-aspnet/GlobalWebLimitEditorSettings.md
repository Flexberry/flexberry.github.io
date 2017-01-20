---
title: Глобальные настройки редактора ограничений
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_global-web-limit-editor-settings.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Web-контролы и web-компоненты](web-controls.html)
* '''Предназначение''': Описаны доступные глобальные настройки для [редактора ограничений](advanced-limit-editor.html).
</td>
</tr></tbody></table></a>
</div>

# Глобальные настройки редактора ограничений
Глобальные настройки [редактора ограничений](advanced-limit-editor.html) позволяют задать в одном месте настройки, которые будут использоваться для всех запускаемых в этом приложении редакторов ограничений.

Настройка производится через статический класс (например, в `Global.asax.cs`):
```cs
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
Отключения отображения панели с параметрами в [редакторе ограничений](advanced-limit-editor.html) производится через установку "false" (по умолчанию "true") у параметра
```cs
  NewPlatform.Flexberry.Web.LimitEditor.Settings.ShowParameterPanel
```

## Отображение первого родительского свойства
Для включения режима отображения первого родительского свойства в конструкторе ограничений (чтобы отличать, какое свойство к чему относится) производится установка "false" (по умолчанию "true") в параметр
```cs
  NewPlatform.Flexberry.Web.LimitEditor.Settings.UseShortNames
```

Отображение свойств, если `UseShortNames = true`:

![](/images/pages/img/page/GlobalWebLimitEditorSettings/UseShortNames.png)

Отображение свойств, если `UseShortNames = false`:

![](/images/pages/img/page/GlobalWebLimitEditorSettings/NotUseShortNames.png)
