---
title: Размазывание по Control-ам информации с LookUp-а
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
summary: На примере показано как можно переопределить метод __Edited__, чтобы использовать полученные  от LookUp данные
toc: true
permalink: en/fw_lookup-fill-information.html
folder: products/flexberry-winforms/
lang: en
---

Переопределяем метод __Edited__:

```csharp
public override void Edited(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname)
		{
			base.Edited (dataobject, contpath, propertyname);

			if (propertyname == "Сотрудник")
			{
				Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
				Объекты.Сотрудник р = (Объекты.Сотрудник)Э.Сотрудник;

				Э.ФИОСотрудника = Э.Сотрудник.ОпрПолноеФИО();
				if (р.Должность != null)
				{
					DataServiceProvider.DataService.LoadObject(р.Должность);
					Э.Должность = р.Должность.Наименование;
				}
				else Э.Должность = "";

				EditManager.Change("ФИОСотрудника");
				EditManager.Change("Должность");
			}	

			if (propertyname == "Сотрудник")
			{
				Объекты.ДвижениеГруппыОтказнМатер Э = (Объекты.ДвижениеГруппыОтказнМатер)EditManager.DataObject;
				Объекты.ОтвЗаРешение р = (Объекты.ОтвЗаРешение)Э.УКогоНаходится;

				if (р != null)
				{
					extTextControl1.Text = р.Наименование;
				}
				else extTextControl1.Text = "";
			}	
		}
```