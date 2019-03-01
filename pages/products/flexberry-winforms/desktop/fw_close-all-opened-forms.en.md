--- 
title: Closing all open forms Flexberry 
sidebar: flexberry-winforms_sidebar 
keywords: Windows UI (forms) 
summary: the example shows how to close all open forms of an application 
toc: true 
permalink: en/fw_close-all-opened-forms.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 9acdf231124796499ee512244a19b487bf605ad92712bf3093b71489bcf1a33a 
--- 

To close all open forms application, as happens when closing the main application window Flexberry, you can use the following code. 

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



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/