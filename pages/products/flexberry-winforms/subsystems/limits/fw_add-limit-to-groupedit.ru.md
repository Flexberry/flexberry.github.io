---
title: Наложение ограничений на GroupEdit
sidebar: flexberry-winforms_sidebar
keywords: 
summary: 
toc: true
permalink: ru/fw_add-limit-to-groupedit.html
lang: ru
---

## Делегат скрытия строк в GroupEdit

Для наложения простых ограничений на записи, отображаемые в [GroupEdit](fw_group-edit.html), можно воспользоваться делегатом скрытия строк соответствующего контрола. Данный делегат определяет видимость каждой записи в [GroupEdit](fw_group-edit.html).

```csharp
public IsObjectVisibleDelegate IsObjectVisible;
public delegate bool IsObjectVisibleDelegate(DataObject dataObject);
```

## Пример использования делегата скрытия строк

Пусть на форме имеется [GroupEdit](fw_group-edit.html) `geВизитКлиентаВБанк`, содержащий записи типа `ВизитКлиентаВБанк`, и в `geВизитКлиентаВБанк` необходимо отобразить только те записи, что содержат пустое значение в поле `ЦельВизита`.

Для решения данной задачи необходимо следующее:

1) Определить требуемый делегат (он возвращает `true`, если поле `ЦельВизита` у поданной на вход записи типа `ВизитКлиентаВБанк` не заполнено):

```csharp
private bool IsObjectVisibleMyImplement(DataObject dataObject)
{
	return string.IsNullOrEmpty(((ВизитКлиентаВБанк)dataObject).ЦельВизита);
}
```

2) Назначить делегат `geВизитКлиентаВБанк`:

```csharp
public class WinformБанкE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.LookUpEditManager2.DPDIБанкE
{
	//...
	public WinformБанкE()
	{
		//...
		this.geВизитКлиентаВБанк.IsObjectVisible = IsObjectVisibleMyImplement;
		//...
	}
}
```