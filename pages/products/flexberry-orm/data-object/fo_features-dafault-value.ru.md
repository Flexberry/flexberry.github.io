---
title: Особенности задания значения по умолчанию
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, типы данных
summary: Особенности методов задания значения по умолчанию
toc: true
permalink: ru/fo_features-dafault-value.html
lang: ru
---

Задание значений по умолчанию доступно как при редактировании [диаграммы классов](fd_class-diagram.html), так и в программном коде. Рассмотрим основные особенности этих методов.

## Задание значения по умолчанию на диаграмме классов

### Скалярные типы

Чтобы указать на [диаграмме классов](fd_class-diagram.html) значение по умолчанию для полей скалярных типов достаточно определить его в [DefaultValue](fo_attributes-class-data.html) у соответствующего поля (обратите внимание на особенности генерации для [DefaultValue](fo_attributes-class-data.html)).

{% include note.html content="Если тип значения по умолчанию не будет соответствовать типу поля, то проект будет сгенерирован, но не скомпилируется." %}

### Cкалярные nullable-типы

Задание значения по умолчанию на [диаграмме классов](fd_class-diagram.html) для [скалярных nullable-типов](fd_nullable-types.html) происходит аналогично скалярным типам.

Generic Nullable-типы могут быть инициализированы значениями, которые достаются из внутренних типов. Например, если присвоить значение `Now` в поле `Default` для атрибута на диаграмме классов, можно получить такой код:

```cs
private System.Nullable<System.DateTime> fRelease = System.DateTime.Now;
```
{% include note.html content="Обратите внимание, что задать по умолчанию `null` не получится." %}

### Комплексные типы

Задать значение по умолчанию на диаграмме классов для поля комплексного типа (например, заданного с помощью класса со [стереотипом](fd_key-concepts.html) [Типы-данных-классы-со-стереотипом-type-и-их-своиства](fd_data-types-properties.html)) в общем случае невозможно.

### Синонимы типов

Для [синонимов типов](fd_typedef.html) задание значения по умолчанию на диаграмме классов происходит аналогично тому, как это происходит у типов, синонимами которых они являются. 

## Задание значения по умолчанию в программном коде

| Приём | Преимущества | Недостатки |
|---|---|---|
| Задание значения по умолчанию в коде формы | + Позволяет для каждой формы задавать свои значения по умолчанию | - Требует проводить инициализацию значений по умолчанию в каждой форме |
| Задание значения по умолчанию в [объекте данных](fo_data-object.html) | + Позволяет задавать значение по умолчанию в одном месте | - Усложнение кода, если каждой форме требуются свои значения по умолчанию |
| Задание значения по умолчанию в [бизнес-сервере](fo_business-server.html) | + Позволяет задавать значение по умолчанию в одном месте и отделить логику от интерфейса | - Усложнение кода, если каждой форме требуются свои значения по умолчанию |

### Задание значения по умолчанию в коде формы

```csharp
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

### Задание значения по умолчанию в объекте данных

```csharp
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

### Задание значения по умолчанию в бизнес-сервере
Код формы:

```csharp
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

```csharp
public class TestBS : ICSSoft.STORMNET.Business.BusinessServer
{
	public void InitializeRecord(IIS.BSTest.ЗаписьНаПриём RecordToInitialize)
	{
		RecordToInitialize.Дата = new DateTime(2012,12,12); //установка значения по умолчанию
	}
	//...
}
```
