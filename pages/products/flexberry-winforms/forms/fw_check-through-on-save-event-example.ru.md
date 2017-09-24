---
title: Пример проверки данных на форме через OnSave/OnSaveEvent
sidebar: flexberry-winforms_sidebar
keywords: DataObject (объекты данных)
toc: true
permalink: ru/fw_check-through-on-save-event-example.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))

Cуть проверки состоит в том, что событие `[OnSave](form-interaction.html)`/`[OnSaveEvent](form-interaction.html)` переопределяется и, если данные не удовлетворяют некоторым условиям, базовый метод вызван не будет.


`[OnSaveEvent](form-interaction.html)` зависимой формы:
```cs
protected override void OnSaveEvent()
{
	ОбъектыДанных.ЗаявНаВыплату vЗаяв = (ОбъектыДанных.ЗаявНаВыплату) EditManager.DataObject;
	bool bContinueSave = true;
	if (vЗаяв.ДатаНачалаНачисл != null && vЗаяв.ЛгКатЛичн != null && vЗаяв.ЛгКатЛичн.ДатаНазначения != null &&
		   vЗаяв.ДатаНачалаНачисл.Value < vЗаяв.ЛгКатЛичн.ДатаНазначения.Value)
	{
		   if (System.Windows.Forms.MessageBox.Show("Выплата может быть назначена с " + vЗаяв.ЛгКатЛичн.ДатаНазначения.Value.ToString("dd.MM.yyyy") + ". Сохранить изменения? ","Внимание",
				  System.Windows.Forms.MessageBoxButtons.YesNo,System.Windows.Forms.MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.No)
				  bContinueSave = false;
	}						
	if (bContinueSave)
		   base.OnSaveEvent (); //вызов базового метода
	if (!m_bFailedSave) //значение переменной могло измениться в базовом методе
	{
		   olПереплата.FillData();
		   olУдержания.FillData();
	}
}
```
`[OnSave](form-interaction.html)` независимой формы:
```cs
protected override void OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)
{
	BS.BFСправочникиBS BS = new ICSSoft.Соцзащита.BS.BFСправочникиBS();
	ОбъектыДанных.Специалист vСпециалист;
	vСпециалист = BS.ИдентифицироватьСпециалиста();
	if (vСпециалист != null)
	{
		   ОбъектыДанных.Личность vПолучатель = заявка.Получатель;
		   if (!vСпециалист.ПроверитьСпеца(vПолучатель))
		   {
				  FailedSave(new Exception ("Сохранение изменений не возможно!")); //генерация исключения о том, что сохранение невозможно
				  return;
		   }
	}
	base.OnSave(e); //вызов базового метода
}
```


(((
<msg type=important> Следует различать `OnSave()` и `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` независимой формы. Если говорить [упрощённо](form-interaction.html), то если закрытие формы осуществлялось по крестику и пользователь согласился сохранить объект, то будет вызван `OnSave()`, после чего `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)`, а если сохранение формы осуществлялось через тулбар, то сначала будет вызван `OnSaveEvent()` зависимой формы, после чего `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` независимой формы. 
</msg>
)))

----