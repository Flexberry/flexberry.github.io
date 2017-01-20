---
title: Закрытие всех открытых форм Flexberry
sidebar: product--sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/close-all-opened-forms.html
folder: product--folder
lang: ru
---



# Закрытие всех открытых форм Flexberry
Чтобы закрыть все открытые формы приложения, как происходит при закрытии главного окна приложения Flexberry, можно использовать следующий код.

```
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
}```
