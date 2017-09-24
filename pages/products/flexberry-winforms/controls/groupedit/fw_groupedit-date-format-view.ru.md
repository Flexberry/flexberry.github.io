---
title: Формат даты GroupEdit в режиме отображения
sidebar: flexberry-winforms_sidebar
keywords: DateTime (работа с датами), Windows UI (Контролы)
toc: true
permalink: ru/fw_Формат-даты--group-edit-в-режиме-отображения.html
folder: products/flexberry-winforms/
lang: ru
---

Формат даты GroupEdit в режиме отображения для отдельного GroupEdit'а можно задать так (в конструкторе формы):
```
			C1.Win.C1FlexGrid.C1FlexGrid flex = Tools.GetFlexGrid(this.ДвижениеОтказа);
			string attributeName = "Дата";			
			try
			{
				flex.Cols[attributeName].Style.Format = "dd.MM.yyyy"; 
			}
			catch
			{
				Tools.ShowWarning("Не удалось установить формат даты для атрибута " + attributeName + 
							      " - для него будет использоваться формат даты по умолчанию");
			}
this.ДвижениеОтказа имеет тип GroupEdit
!!! Порядковые номера столбцов НЕ РАВНЫ порядку отображения. Использовать имена столбцов (не названия). Имена можно узнать в Properties для GroupEdit, Атрибут Columns.```