---
title: "Размазывание" по Control'ам информации с LookUp'а
sidebar: product--sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_look-up-fill-information.html
folder: products/flexberry-winforms/
lang: ru
---

# "Размазывание" по Control'ам информации с LookUp'а

Переопределяем метод '''Edited''':

```
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
		}```