---
title: Установка текущего объекта при запуске приложения
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, пример
summary: Выбор объекта до запуска основного контента приложения
toc: true
permalink: ru/fo_define-default-object.html
lang: ru
---

Например, при оформлении заказа требуется, чтобы магазин был уже установлен. Для этого выбор магазина должен быть предоставлен в начале работы с программой.

Возможно следующее решение данной задачи:

* Для выбора текущего объекта в начале работы с программой можно использовать [генерируемую Flexberry форму для нехранимого класса](fo_using-not-stored-classes.html).
* Для хранения текущего объекта можно использовать [статическое поле](http://msdn.microsoft.com/library/98f28cdx.aspx) общедоступного класса.
* Для установки текущего объекта можно использовать решение, предложенное в статье [Задание значения поля создаваемого объекта](fo_define-field-created.html). 

На уровне пользователя решение будет выглядеть так: есть список магазинов, при запуске приложения появляется форма, где необходимо выбрать текущий магазин. При создании записи о покупке текущий магазин автоматически подставляется.

## Работа в Flexberry Tool

В Flexberry была создана диаграмма классов. Для определения текущего магазина [был создан нехранимый класс](fo_using-not-stored-classes.html)  `ВыборМагазинаПоУмолчанию` и сгенерирован программный код.

![](/images/pages/products/flexberry-orm/data-object/class-diagram_shops.jpg)

## Работа с программным кодом

В общедоступном для разрабатываемых приложений классе определить [статическое поле](http://msdn.microsoft.com/library/98f28cdx.aspx).

```csharp
public class Магазин : ICSSoft.STORMNET.DataObject
{
	public static Магазин CurrentShop;
	//...
}
```

На форме нехранимого класса, для подавления сообщения о сохранении данных на форме, переопределить метод `OnClosing`, где запретить вызов базового метода.

Для задания значения поля при открытии формы [переопределить событие `Edit`](fo_define-field-created.html).

Определить работу формы при нажатии на кнопки.

```csharp
private void buttonAuthorize_Click(object sender, EventArgs e) //нажатие на кнопку "Авторизовать"
{
	//проверить, что магазин выбран
	if ((DataObject as ВыборМагазинаПоУмолчанию).ТекущийМагазин != null)
	{		
		Магазин.CurrentShop = ((ВыборМагазинаПоУмолчанию)DataObject).ТекущийМагазин; //сохранить текущий магазин
		this.DialogResult = DialogResult.OK; //закончить авторизацию
		this.Close();
	}
	else
	{
		MessageBox.Show("Вы не выбрали магазин для авторизации.");
	}
}
private void buttonExit_Click(object sender, EventArgs e) //нажатие на кнопку "Выйти"
{
	this.Close();
}
```

Запустить форму модально до запуска рабочего стола приложения. Пример из класса `МенеджерМагазиновDesktop`:

```csharp
public static bool Run_SetCurrentShopForm()
{
	bool result = false;
	try
	{
		ICSSoft.STORMNET.RightManager.DisableAllRightChecks();
		CheckForIllegalCrossThreadCalls = false;
			IIS.WinUI.Runners.EditFormRunner fr =
				new IIS.WinUI.Runners.EditFormRunner(typeof(C__ВыборМагазинаПоУмолчаниюE),
											"", "Выбор текущего магазина", "",
											new ВыборМагазинаПоУмолчанию(),
											typeof(ВыборМагазинаПоУмолчанию), false);
			System.Windows.Forms.Form frm = null;
			frm = fr.RunAndGetForm();
			if (!frm.IsDisposed && !frm.Disposing)
			{
				frm.BringToFront();
				frm.Visible = false;
				CheckForIllegalCrossThreadCalls = false;
				if (frm.ShowDialog() == DialogResult.OK)
				{
					result = true;
				}
			}
		return result;
	}
	catch (Exception)
	{
		throw new Exception("Не удалось установить значение по умолчанию.");
	}
}

static void Main()
{
	try
	{
		// *** Start programmer edit section *** (МенеджерМагазинов Before authorization)
		//ниже код по запуску формы
		if (!Run_SetCurrentShopForm())
		{
			return; //то есть пользователь не захотел выбрать значение по умолчанию, прекращаем работу
		}
		// *** End programmer edit section *** (МенеджерМагазинов Before authorization)
		//...
	}
	//...
}
```
