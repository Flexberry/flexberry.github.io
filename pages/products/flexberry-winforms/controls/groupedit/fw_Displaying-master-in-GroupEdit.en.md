---
title: Отображение мастера в GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: en/fw_displaying-master-in-groupedit.html
folder: products/flexberry-winforms/
lang: en
---

При необходимости отображения в ячейке ['GroupEdit'](fw_group-edit.html) «презентационного» атрибута мастера (т.е. некоторого выражения из атрибутов мастера) можно воспользоваться одним из описанных ниже решений.
 
1. Переопределить метод `ToString()` у объекта данных. Особенность данного решения в том, что переопределение повлияет всюду, где используется метод `ToString()`.

2. Реализовать у контрола, который связан со столбцом GroupEdit, интерфейс `IValueDisplayResponsible`. Единственный метод  `GetDisplayValue` данного интерфейса должен вернуть отображаемое в ячейке значение. У [GroupEdit](fw_group-edit.html) установить свойство `EnableValueDisplayResponsibility` в `true`.

```csharp
#region IValueDisplayResponsible Members

public string GetDisplayValue(ICSSoft.STORMNET.DataObject dataObject)
{
	if (curObject == null)
		return string.Empty;

	return string.Format(Формат,
		Tools.IsNull(ICSSoft.STORMNET.Information.GetPropValueByName(curObject, Наименование), "").ToString(),
		Tools.IsNull(ICSSoft.STORMNET.Information.GetPropValueByName(curObject, Код), "").ToString()).TrimStart();
}
#region IValueDisplayResponsible Members
```