---
title: Кастомизация подписей в Web-контролах
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_web-controls-custom-captions.html
lang: ru
---

Подписи к контролам храняться в строковых ресурсах в *.resx-файлах соответствующих тому или иному контролу.
Например подписи к элементам WebObjectListView храняться в проекте `ICSSoft.STORMNET.Web.AjaxControls` в файле `Resources/WebObjectListView.resx`,
а подписи к контролу DatePicker в том же проекте ICSSoft.STORMNET.Web.AjaxControls в файле Resources/DatePicker.resx, и т.д.

Если на прикладном уровне требуется переопределить подпись к тому или иному элементу контрола, достаточно в прикладном проекте создать файл со строковыми ресурсами, с именем соответствующим контролу, и переопределить в нем желаемый строковый ресурс.  
Например, чтобы в контроле WebObjectListView изменить подпись к кнопке обновления с "Обновить" на "Обновить список", достаточно в прикладном проеекте создать файл `Resources/WebObjectListView.resx`
и определить в нем строковый ресурс "ToolbarButtonRefreshCaption" со значением "Обновить список".
Актуальный перечень доступных `*.resx-файлов` и их содержимое лучше смотреть в проекте `ICSSoft.STORMNET.Web.AjaxControls` в каталоге `Resources`.

## Разработка контролов с кастомизируемыми подписями

Для того, чтобы подписи разрабатываемых контролов были кастомизируемы (при помощи ранее описанного подхода), в designer-e генерируемых `*.resx-файлов` необходимо заменять использование стандартного `ResourceManager-a ` на `ICSSoft.STORMNET.Web.Tools.Resources.ResourceManger`.

Например в `WebObjectListView.Designer.cs`, соответствующем файлу `WebObjectListView.resx`, следующий сгенерированный код:

```csharp
private static global::System.Resources.ResourceManager resourceMan;
        
private static global::System.Globalization.CultureInfo resourceCulture;

[global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
internal WebObjectListView() {
}

/// <summary>
///   Returns the cached ResourceManager instance used by this class.
/// </summary>
[global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
internal static global::System.Resources.ResourceManager ResourceManager {
	get {
		if (object.ReferenceEquals(resourceMan, null)) {
		    // Обратите внимание, в автоматически сгенерированном коде первым параметром отдается полный путь к *.resx-файлу, включая пространство имен.
			// В этом случае для компонента не будет недоступна кастомизация подписей.
			global::System.Resources.ResourceManager temp = new global::System.Resources.ResourceManager("ICSSoft.STORMNET.Web.AjaxControls.Resources.WebObjectListView", typeof(WebObjectListView).Assembly);
			resourceMan = temp;
		}
		return resourceMan;
	}
}

```

был заменен на:

```csharp
private static ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager resourceManager;

private static global::System.Globalization.CultureInfo resourceCulture;

[global::System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1811:AvoidUncalledPrivateCode")]
internal WebObjectListView() {
}

/// <summary>
///   Returns the cached ResourceManager instance used by this class.
/// </summary>
[global::System.ComponentModel.EditorBrowsableAttribute(global::System.ComponentModel.EditorBrowsableState.Advanced)]
internal static ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager ResourceManager {
	get {
		if (object.ReferenceEquals(resourceManager, null)) {
		    // Обратите внимание, что здесь первым параметром отдается не полный путь к *.resx-файлу, а путь относительно каталога Resources.
			// Ожидается, что строковые ресурсы как в сборке ICSSoft.STORMNET.Web.AjaxControls, так и в прикладном приложении нажодятся в каталоге Resources в файле WebObjectListView.resx.
			ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager temp = new ICSSoft.STORMNET.Web.Tools.Resources.ResourceManager("WebObjectListView", typeof(WebObjectListView).Assembly);
			resourceManager = temp;
		}
		return resourceManager;
	}
}
```

**Важно: в designer-e прикладного *.resx-файла ничего изменять не нужно.**
