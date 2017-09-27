---
title: Определение доступных для редактирования полей для одной формы, но разных приложений
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (формы)
toc: true
permalink: ru/fw_different-applications-and-fields.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))
Была поставлена следующая задача: "Сделать для разных приложений доступными разные поля для одной и той же формы".
Возможно следующее решение данной задачи:
# В зависимости от того, какое приложение запущено, можно блокировать поля на редактирование с помощью [EditManager.SetReadonlyFlagProperties](fw_editmanager-set-readonly-flag-properties.html).
# Определять запущенное приложение можно при его запуске путём записи определённого значения в [статическое поле](http://msdn.microsoft.com/library/98f28cdx.aspx) общедоступного класса.

Для демонстрации варианта решения данной проблемы ниже представлено решение следующей задачи: "Есть список сотрудников. Сотрудники отдела кадров могут редактировать поля "ФИО", "Дата рождения" и "Адрес прописки", а руководители предприятия могут оценивать "Работоспособность" сотрудников".

# Работа в Flexberry Tool
В Flexberry была создана диаграмма классов.


![](/images/pages/products/flexberry-winforms/desktop/class-diagram_-workers.jpg)


Затем определены приложения пользователей и сгенерирован программный код.


# Работа с программным кодом
В общедоступном для разрабатываемых приложений классе определяем статическое поле.
```cs
    public enum tWorkerShowType //перечислимый тип для указания запущенного приложения
    {
        Unknown, //ничего не было установлено
        ToHead, //начальник
        ToPersonnelOffice //отдел кадров
    }
    public class Сотрудник : ICSSoft.STORMNET.DataObject
    {
        public static WorkerShowType CurShowType = tWorkerShowType.Unknown; //статическое поле для указания запущенного приложения
        //...
    }
```
В коде приложения определяем значение этого статического поля.
```cs
static void Main()
{
	try
	{
		Сотрудник.CurShowType = tWorkerShowType.ToPersonnelOffice; //определяем значение статического поля
		//...
	}
	//...
}
```
Переопределяем метод `[Edit](fw_form-interaction.html)` на форме редактирования.
```cs
public override void Edit(ICSSoft.STORMNET.DataObject dataobject, string contpath, string propertyname, object tag)
{
	base.Edit(dataobject, contpath, propertyname, tag); //вызов базового метода
	if (dataobject != null)
	{
		switch (Сотрудник.CurShowType)
		{
			case tWorkerShowType.ToHead: //если запущено приложение руководителя
				EditManager.SetReadonlyFlagProperties(
					true, new string[] { "ФИО", "ДатаРождения", "АдресПрописки" });
				break;
			case tWorkerShowType.ToPersonnelOffice: //если запущено приложение сотрудника отдела кадров
				EditManager.SetReadonlyFlagProperties(true, new string[] { "Работоспособность" });
				break;
			case tWorkerShowType.Unknown: //если не определён тип приложения
				MessageBox.Show("Не был установлен параметр, от имени кого была запущена форма.");
				break;
		}
	}
}
```

{% include note.html content="Вызов `base.Edit(...)` должен предшествовать определению полей, доступных на редактирование." %}