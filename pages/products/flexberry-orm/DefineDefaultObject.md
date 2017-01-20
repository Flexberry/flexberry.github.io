---
title: Установка текущего объекта при запуске приложения
sidebar: product--sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/define-default-object.html
folder: product--folder
lang: ru
---
Была поставлена следующая задача: "Добавить выбор магазина в начало работы с программой, чтобы не вводить его каждый раз при составлении заказа". Возможно следующее решение данной задачи:
# Для выбора текущего объекта в начале работы с программой можно использовать [генерируемую Flexberry форму для нехранимого класса](using-of-not-stored-classes.html).
# Для хранения текущего объекта можно использовать [статическое поле](http://msdn.microsoft.com/library/98f28cdx.aspx) общедоступного класса.
# Для установки текущего объекта можно использовать решение, предложенное [здесь](define-field-of-created-object.html). 

Для демонстрации варианта решения данной проблемы ниже представлено решение следующей задачи: "Есть список магазинов. При запуске приложения появляется форма, где необходимо выбрать текущий магазин. При создании записи о покупке текущий магазин автоматически подставляется.".

# Работа в Flexberry Tool
В Flexberry была создана диаграмма классов. Для определения текущего магазина [был создан нехранимый класс](using-of-not-stored-classes.html)  `ВыборМагазинаПоУмолчанию` и сгенерирован программный код.


![](/images/pages/img/page/DefineDefaultObject/ClassDiagram_Shops.jpg)


# Работа с программным кодом
В общедоступном для разрабатываемых приложений классе определяем [статическое поле](http://msdn.microsoft.com/library/98f28cdx.aspx).
```cs
public class Магазин : ICSSoft.STORMNET.DataObject
{
	public static Магазин CurrentShop;
	//...
}
```
Проводим дизайн сгенерированной формы редактирования `<nowiki>WinformC__ТекущийМагазинE</nowiki>` [нехранимого класса](using-of-not-stored-classes.html), для подавления сообщения о сохранении данных на форме [переопределяем метод `OnClosing`, где запрещаем вызов базового метода](using-of-not-stored-classes.html).


Для задания значения поля при открытии формы `<nowiki>WinformC__ПокупкаE</nowiki>` [переопределяем событие `Edit`](define-field-of-created-object.html).


Определяем работу формы `<nowiki>WinformC__ТекущийМагазинE</nowiki>` при нажатии на кнопки.
```cs
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
[Запускаем форму модально](using-of-not-stored-classes.html) до запуска рабочего стола приложения. Пример из класса `МенеджерМагазиновDesktop`:
```cs
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
----