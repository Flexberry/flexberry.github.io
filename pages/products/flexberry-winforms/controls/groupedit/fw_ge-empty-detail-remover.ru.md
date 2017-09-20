---
title: GEEmptyDetailRemover
sidebar: product--sidebar
keywords: Windows UI (Контролы), Windows UI (формы)
toc: true
permalink: ru/fw_ge-empty-detail-remover.html
folder: products/flexberry-winforms/
lang: ru
---

(((Данная статья ещё редактируется)))

# GEEmptyDetailRemover
`GEEmptyDetailRemover` - расширение компонента `[GroupEdit](group-edit.html)`, позволяющее организовать следующее поведение: удаление пустых строк из `[GroupEdit](group-edit.html)` при сохранении (при наличии пустых строк в `[GroupEdit](group-edit.html)` и сохранении может возникнуть сообщение о незаполненных полях (в этой пустой строке).).


(((
<msg type=important>
`GEEmptyDetailRemover` не входит в стандартную поставку Flexberry.</msg>
)))


# Как подключить GEEmptyDetailRemover
1. В зависимой форме класса определяем объект класса GEEmptyDetailRemover:
```cs
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
	public IIS.AMS02.GEEmptyDetailRemover gedr = new IIS.AMS02.GEEmptyDetailRemover();
	//...
}
```
2. Указываем в конструкторе формы `[GroupEdit](group-edit.html)`'ы, для которых надо удалять пустые строки:
```cs
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
	public WinformC__ПокупательE()
	{
		this.InitializeComponent();
		m_sCaption = "Покупатель";
		this.prv_TuneLookupInformations();
		// *** Start programmer edit section *** (Form Constructor)
		//...
		#region возможность удаления пустых детейлов из GE
		gedr.AddGroupEdit(Покупки);
		gedr.AddGroupEdit(Продажи);
		#endregion возможность удаления пустых детейлов из GE
		//...
		// *** End programmer edit section *** (Form Constructor)
	}
	//...
}
```
3. В независимой форме в методе `[OnSave](form-interaction.html)` вызываем функцию удаления пустых детейлов:
```cs
public class C__ПокупательE : ICSSoft.STORMNET.UI.BaseIndpdEdit
{ 
	// *** Start programmer edit section *** (C__ПокупательE CustomMembers)
	protected override void OnSave()
	{
		//...
		#region удаляем пустые детейлы
		(Editor as WinformC__ПокупательE).gedr.RemoveEmptyDetails();
		#endregion удаляем пустые детейлы
		//...
	}
	//...
}
```
----