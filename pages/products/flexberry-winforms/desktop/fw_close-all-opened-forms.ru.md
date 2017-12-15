---
title: Закрытие всех открытых форм Flexberry
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
summary: На примере показано как закрыть все открытые формы приложения
toc: true
permalink: ru/fw_close-all-opened-forms.html
folder: products/flexberry-winforms/
lang: ru
---

Чтобы закрыть все открытые формы приложения, как происходит при закрытии главного окна приложения Flexberry, можно использовать следующий код.

```csharp
var coll = desktopCtrl2.PathRunners;

foreach (ArrayList runList in coll.GetAllValues())
{
	foreach (Runner run in runList)
	{
		if (run.Alive)
		{
			run.Stop();
		}
	}
}
```
