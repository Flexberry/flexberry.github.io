---
title: Проверка данных на форме во время сохранения
sidebar: flexberry-winforms_sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/fw_check-form-field-during-save.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))

Проверка данных на форме во время сохранения осуществляется [ через события `OnSave`/`OnSaveEvent`](fw_check-through-on-save-event-example.html) и может включать следующие элементы:
* Определение обязательных для заполнения полей на диаграмме классов через атрибут [`NotNull`](fo_attributes-class-data.html).
* Проверка через [`DataObjectErrorProvider`](fw_data-object-error-provider.html) (как при этом осуществить подсветку ошибочных полей описано [здесь](прикладные-системы_Подсветка-ошибочных-полеи.html)).
* Проверка через [ЛУСы](прикладные-системы_features-of-work-with-log-conditions.html) (как при этом осуществить подсветку ошибочных полей описано [здесь](прикладные-системы_Подсветка-ошибочных-полеи.html)).

| Приём | Преимущества | Недостатки |
|--|--|--|
| Определение обязательных для заполнения полей на диаграмме классов через атрибут [`NotNull`](fo_attributes-class-data.html) | + позволяет в модели задать обязательные для заполнения поля | - не позволяет определять поля, обязательные только в некоторых ситуациях
| Проверка через [`DataObjectErrorProvider`](fw_data-object-error-provider.html) | + позволяет быстро прописать в коде перечень обязательных полей и пользователи приложения не смогут его менять | - не позволяет пользователям менять условия проверки данных на форме
| Проверка через [ЛУСы](прикладные-системы_features-of-work-with-log-conditions.html) | + даёт возможность пользователям самим настраивать условия проверки данных на форме <br> + см. [здесь](прикладные-системы_Реализация-пользовательского-интерфеиса-экранных-форм-редактирования.html) | - в приложение вообще и на конкретную форму в частности должны быть встроены [ЛУСы](прикладные-системы_features-of-work-with-log-conditions.html)
|||

Пример использования всех методов:
```cs
protected override void OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) //метод OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e) независимой формы
{
	System.Collections.ArrayList arl = new System.Collections.ArrayList();
	arl.AddRange(e.dataobject.CheckNotNullProperties(m_objView, true)); //проверяем заданные в модели NotNull-поля
	arl.AddRange((Editor as WinformВещьE).dataObjectErrorProvider1.GetNullProperties()); //проверяем заданные с помощью DataObjectErrorProvider обязательные поля
	if (arl.Count > 0)
	{
		System.Windows.Forms.MessageBox.Show("Остались незаполненными обязательные поля: " + Environment.NewLine + string.Join(", ", (string[])arl.ToArray(typeof(string))), "Внимание");
		(Editor as WinformВещьE).dataObjectErrorProvider1.FocusProperty(arl[0].ToString());
		m_bFailedSave = true;
		Editor.FailedSave(null);
		((ICSSoft.STORMNET.UI.IDpdEditForm)Editor).SetStatusMessage(ICSSoft.STORMNET.UI.EditFormStatusMessage.ErrorInSave);
	}
	else
	{
		#region Лусы //проверка через лусы и подсветка через DataObjectErrorProvider 
		SpecificControls.DataObjectErrorProvider errorProvider = null;
		if (Editor != null) errorProvider = (Editor as WinformВещьE).ValidationErrorProvider;
		if (!SQLValidationManagerIntegrator.CheckAllRulesDigitReport(e.dataobject, this, null, errorProvider))
		{
			ОтменитьСохранение();
			return;
		}
		#endregion
		base.OnSave(e); //сохранить, если все проверки пройдены
	}
}
```

Другие методы проверки данных на форме описаны [здесь](fw_edit-form-validation.html). 


----* [Лусы](прикладные-системы_Лусы.html)
* [Реализация экранных форм редактирования (Windows Forms)](прикладные-системы_Реализация-пользовательского-интерфеиса-экранных-форм-редактирования.html)
* [Подсветка ошибочных полей](прикладные-системы_Подсветка-ошибочных-полеи.html)
* [Особенности работы с ЛУСами](прикладные-системы_features-of-work-with-log-conditions.html)
