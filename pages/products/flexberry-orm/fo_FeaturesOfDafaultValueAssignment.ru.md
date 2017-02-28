---
title: Особенности задания значения по умолчанию
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_features-of-dafault-value-assignment.html
folder: products/flexberry-orm/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;"> <br> <table border="0" width="100%" bgcolor="#6495ED"> <tbody><tr><td bgcolor="#FFFFFF"> 

* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Объект данных](dataobject.html)
* '''Программная библиотека''': ICSSoft.STORMNET.DataObject.dll
* '''Предназначение''': Приведены основные способы задания значения по умолчанию.
</td>
</tr></tbody></table></a>
</div>

Задание значений по умолчанию доступно как при редактировании [диаграммы классов](fd_class-diagram.html), так и в программном коде. Рассмотрим основные особенности этих методов.

# Задание значения по умолчанию на диаграмме классов
## Скалярные типы
Чтобы указать на [диаграмме классов](fd_class-diagram.html) значение по умолчанию для полей скалярных типов достаточно определить его в `[DefaultValue](attributes-class-data.html)` у соответствующего поля (обратите внимание на особенности генерации для `[DefaultValue](attributes-class-data.html)`).

(((
<msg type=note>Если тип значения по умолчанию не будет соответствовать типу поля, то проект будет сгенерирован, но не скомпилируется.</msg>
)))

## Cкалярные nullable-типы
Задание значения по умолчанию на [диаграмме классов](fd_class-diagram.html) для [скалярных nullable-типов](nullable-types.html) происходит аналогично скалярным типам.

Generic Nullable-типы могут быть инициализированы значениями, которые достаются из внутренних типов. Например, если присвоить значение `Now` в поле `Default` для атрибута на диаграмме классов, можно получить такой код:
```cs
private System.Nullable<System.DateTime> fRelease = System.DateTime.Now;
```
(((
<msg type=important>Обратите внимание, что задать по умолчанию `null` не получится.</msg>
)))

## Комплексные типы
Задать значение по умолчанию на диаграмме классов для поля комплексного типа (например, заданного с помощью класса со [стереотипом](fd_key-concepts.html) `[Типы-данных-классы-со-стереотипом-type-и-их-своиства|type]`) в общем случае невозможно.

## Синонимы типов
Для [синонимов типов](classes-with-stereotype--typedef.html) задание значения по умолчанию на диаграмме классов происходит аналогично тому, как это происходит у типов, синонимами которых они являются. 

# Задание значения по умолчанию в программном коде
{| cellspacing="0" cellpadding="2" border="1"
! Приём !! Преимущества !! Недостатки
|-
| Задание значения по умолчанию в коде формы || + Позволяет для каждой формы задавать свои значения по умолчанию || - Требует проводить инициализацию значений по умолчанию в каждой форме
|-
| Задание значения по умолчанию в [объекте данных](dataobject.html) || + Позволяет задавать значение по умолчанию в одном месте || - Усложнение кода, если каждой форме требуются свои значения по умолчанию
|-
| Задание значения по умолчанию в [бизнес-сервере](business--servers--wrapper--business--facade.html) || + Позволяет задавать значение по умолчанию в одном месте и отделить логику от интерфейса || - Усложнение кода, если каждой форме требуются свои значения по умолчанию
|}

## Задание значения по умолчанию в коде формы
```cs
public class WinformC__ПациентE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.BSTest.DPDIC__ПациентE
{
	private void WinformC__ПациентE_DataObjectOnFormLoaded(object sender, EventArgs e)
	{
		((Пациент) this.DataObject).ФИО = "ФИОПациента"; //задаём значения по умолчанию
		...
	}
	//...
}
```

## Задание значения по умолчанию в объекте данных
```cs
public class Пациент : ICSSoft.STORMNET.DataObject
{
	private fФИО = "ФИОПациента"; //установка значения по умолчанию
	public string ФИО 
	{
		get
		{
			return fФИО;
		}
		
		set
		{
			fФИО = value;
		}
	}
	//...
}
```

## Задание значения по умолчанию в бизнес-сервере
Код формы:
```cs
public class WinformC__ЗаписьНаПриёмE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.BSTest.DPDIC__ЗаписьНаПриёмE
{
	private void WinformC__ЗаписьНаПриёмE_DataObjectOnFormLoaded(object sender, EventArgs e)
	{
		//определяем, какие бизнес-сервера определены для объекта типа "ЗаписьНаПриём"
		BusinessServer[] businessServers = BusinessServerProvider.GetBusinessServer(typeof(ЗаписьНаПриём), DataServiceObjectEvents.OnAllEvents, DataServiceProvider.DataService);
		if (businessServers.Length>0) 
		{
			TestBS curBS = (TestBS) businessServers[0]; //берём первый и единственный (в данном случае единственный)
			curBS.InitializeRecord(this.DataObject as ЗаписьНаПриём); //задаём значения по умолчанию
			EditManager.Change(); //применяем внесённые изменения
		}
	}
	//...
}
```
Код бизнес-сервера:
```cs
public class TestBS : ICSSoft.STORMNET.Business.BusinessServer
{
	public void InitializeRecord(IIS.BSTest.ЗаписьНаПриём RecordToInitialize)
	{
		RecordToInitialize.Дата = new DateTime(2012,12,12); //установка значения по умолчанию
	}
	//...
}
```
