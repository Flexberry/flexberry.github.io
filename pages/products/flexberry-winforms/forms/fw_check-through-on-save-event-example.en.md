--- 
title: Example of data validation on the form using OnSave/OnSaveEvent 
sidebar: flexberry-winforms_sidebar 
keywords: DataObject (object data) 
summary: Shows an example of overriding the OnSave/OnSaveEvent to validate data on the form 
toc: true 
permalink: en/fw_check-through-on-save-event-example.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 782721f0a858403b6044d06c3f9aedea71ccb9848344ab4c85d8c7b4e8230248 
--- 

<!-- This article is still being edited --> 

The essence of the test is that the event [`OnSave`](fw_form-interaction.html)/[`OnSaveEvent`](fw_form-interaction.html) is overridden and if the data does not satisfy certain conditions, the base method does not get called. 


[`OnSaveEvent`](fw_form-interaction.html) dependent forms: 

```csharp
protected override void OnSaveEvent()
{
	ОбъектыДанных.ЗаявНаВыплату vЗаяв = (ОбъектыДанных.ЗаявНаВыплату) EditManager.DataObject;
	bool bContinueSave = true;
	if (vЗаяв.ДатаНачалаНачисл != null && vЗаяв.ЛгКатЛичн != null && vЗаяв.ЛгКатЛичн.ДатаНазначения != null &&
		   vЗаяв.ДатаНачалаНачисл.Value < vЗаяв.ЛгКатЛичн.ДатаНазначения.Value)
	{
		   if (System.Windows.Forms.MessageBox.Show("The payment may be designated with a " + vЗаяв.ЛгКатЛичн.ДатаНазначения.Value.ToString("dd.MM.yyyy") + ". Save changes? ","Attention",
				  System.Windows.Forms.MessageBoxButtons.YesNo,System.Windows.Forms.MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.No)
				  bContinueSave = false;
	}						
	if (bContinueSave)
		   base.OnSaveEvent (); //call the base method 
	if (!m_bFailedSave) //the value of the variable could change in the base method 
	{
		   olПереплата.FillData();
		   olУдержания.FillData();
	}
}
``` 

[`OnSave`](fw_form-interaction.html) independent of the form: 

```csharp
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
				  FailedSave(new Exception ("Saving changes is not possible!")); //generate the exception that you cannot save 
				  return;
		   }
	}
	base.OnSave(e); //call the base method 
}
``` 

{% include important.html content=" we Must distinguish between `OnSave()` and `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` independent. Speaking [simplified](fw_form-interaction.html), if the closing of the forms was carried out on the cross and the user has agreed to store the object that will be invoked `OnSave()`, then `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)`, but if the shape was carried out through the toolbar, the first will be called `OnSaveEvent()` dependent form, and then `OnSave(ICSSoft.STORMNET.UI.SaveEventArgs e)` independent. 
"%}


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}