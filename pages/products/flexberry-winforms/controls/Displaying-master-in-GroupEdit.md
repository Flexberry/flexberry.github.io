---
title: Отображение мастера в GroupEdit
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/displaying-master-in--group-edit.html
folder: product--folder
lang: ru
---

При необходимости отображения в ячейке `[GroupEdit](group-edit.html)` «презентационного» атрибута мастера (т.е. некоторого выражения из атрибутов мастера) можно воспользоваться одним из описанных ниже решений.
 
# Переопределить метод `ToString()` у объекта данных. Особенность данного решения в том, что переопределение повлияет всюду, где используется метод `ToString()`.

# Реализовать у контрола, который связан со столбцом GroupEdit, интерфейс `IValueDisplayResponsible`. Единственный метод  `GetDisplayValue` данного интерфейса должен вернуть отображаемое в ячейке значение. У [GroupEdit](group-edit.html) установить свойство `EnableValueDisplayResponsibility` в `true`.


```cs
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