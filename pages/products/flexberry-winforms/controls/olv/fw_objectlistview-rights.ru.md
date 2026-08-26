---
title: Управление операциями списков в зависимости от прав пользователя
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, контролы, список
summary: Настройка видимости и удаление настроек пользователя
toc: true
permalink: ru/fw_objectlistview-rights.html
lang: ru
---

LV автоматически устанавливает доступность операций редактирования в зависимости от прав. Применяются следующие правила:

* При отсутствии прав на INSERT объекта на списке недоступны операции "Создания" и "Создания по шаблону".
* При отсутствии прав на DELETE объекта недоступна операция "Удалить".
* При (отсутствии прав на UPDATE объекта и наличии прав на READ) операция называться "Просмотр", а объект открывается только на просмотр.

При наличии в списке нескольких типов объектов доступные операции отображаются в зависимости от выбранного объекта.
Текущую настройку можно узнать с помощью свойства `ObjectListView.RightSet`, а смену доступности отследить с помощью события `ObjectListView.RightSetChanged`.

## Настройка видимости колонок ObjectListView

Пусть необходимо, например, в зависимости от полномочий пользователя устанавливать видимость некоторой колонки в [ObjectListView](fw_objectlistview.html).

Изменять `objectListView1.Columns` во время работы приложения нельзя.

Одним из решений поставленной задачи может быть использование `ObjectListView.View.Properties`.

```csharp
public class WinformC__ПользовательПриложенияL : ICSSoft.STORMNET.UI.BaseWinListStandard, IIS.TryAccessSystem.DPDIC__ПользовательПриложенияL
{
	public WinformC__ПользовательПриложенияL() //конструктор формы
	{
		this.InitializeComponent();
		this.prv_TuneAdditionalObjectListViews();
		// *** Start programmer edit section *** (Form Constructor)
		//...
		if (!ICSSoft.STORMNET.RightManager.AccessObjectCheck(curObject, "Update", false)) //проверяем полномочия пользователя
		{
			var columnInfoList = (from ICSSoft.STORMNET.PropertyInView mi in objectListView1.View.Properties
								  where mi.Name == "ЛогинПользователя"
								  select mi).ToList(); //ищем нужное свойство
			if (columnInfoList.Count == 1) //проверяем, что нужное свойство найдено
			{
				ICSSoft.STORMNET.PropertyInView columnInfo = columnInfoList[0];
				int cINumber = objectListView1.View.Properties.ToList().IndexOf(columnInfo);
				objectListView1.View.Properties[cINumber].Visible = false; //устанавливаем Visible в false
			}
		}
		//...
		// *** End programmer edit section *** (Form Constructor)
	}
	//...
}
```

{% include note.html content="Специфика работы с массивом `ObjectListView.View.Properties` объясняется тем, что `PropertyInView` - это [не класс, а структура](http://generally.wordpress.com/2007/06/21/c-list-of-struct/)." %}

{% include important.html content="Настройка видимости колонки происходит в конструкторе формы." %}

## Удаление настроек пользователя в ObjectListView

Удалить настройки пользователя можно через окно настройки списка. Необходимо нажать на кнопку `Сбросить настройки…`, после подтверждения выполнения операции настройки будут удалены из базы. Однако изменения не отобразятся немедленно, а только после повторного открытия списка. Данная особенность связана с тем, что значения по умолчанию (настройка колонок) присваиваются только при инициализации списка (начальной загрузке контрола).