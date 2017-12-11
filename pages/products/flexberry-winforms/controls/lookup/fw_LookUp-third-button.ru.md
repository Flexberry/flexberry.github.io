---
title: Создание третьей кнопки рядом с LookUp'ом
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: На примере показано как можно создать третью кнопку рядом с LookUp'ом
toc: true
permalink: ru/fw_lookup-third-button.html
folder: products/flexberry-winforms/
lang: ru
---

* Создать плоский __button__ 
* Навесить картинку из __ImageList__.
* На нажатие это кнопки повесить __вызов__ следующей функции:

```csharp
public void ПоказатьСписок()
		{
			SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
			Function lf = null;

			// формируем lf
			LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Лицо), "ЛицоL");
			lcs.LimitFunction = lf;

			ICSSoft.STORMNET.DataObject[] objs = DataServiceProvider.DataService.LoadObjects(lcs);	
	
			if (objs.Length == 0)
			{
				Tools.ShowInformation("Не найдено ни одного лица с заданными параметрами");
				return;
			}
			else // хоть кого-то нашли
			{
				string contpath = "STORMCASE.STORMNET.Generator.SerializeNewEditForm/EditPanel(Panel)/TabControl(Tab" +
					"Control)/panel(TabPage)/panel(GroupBox)/ctrlЛицо(LookUp)";
				base.OnEdit("Лицо", EditManager.DataObject, contpath, lf);
			}
		}
```
