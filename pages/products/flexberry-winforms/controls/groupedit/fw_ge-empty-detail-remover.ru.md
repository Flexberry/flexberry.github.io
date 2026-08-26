---
title: Удаление пустых строк при сохранении
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, GroupEdit, GEEmptyDetailRemover
summary: Правила подключения компонента для удаления пустых строк детейлов при сохранении
toc: true
permalink: ru/fw_ge-empty-detail-remover.html
lang: ru
---

`GEEmptyDetailRemover` - расширение компонента [GroupEdit](fw_group-edit.html), позволяющее реализовать удаление пустых строк из `GroupEdit` при сохранении. При сохранении `GroupEdit` с пустыми строками может возникнуть сообщение о незаполненных полях (в этой пустой строке).

{% include important.html content="`GEEmptyDetailRemover` не входит в стандартную поставку Flexberry Winforms." %}

## Подключение GEEmptyDetailRemover

1) В зависимой форме класса определяется объект класса `GEEmptyDetailRemover`:

```csharp
public class WinformC__ПокупательE : ICSSoft.STORMNET.UI.BaseWinEdit, IIS.MasterField.DPDIC__ПокупательE
{
	public IIS.AMS02.GEEmptyDetailRemover gedr = new IIS.AMS02.GEEmptyDetailRemover();
	//...
}
```

2) Указываются в конструкторе формы `GroupEdit`, для которых надо удалять пустые строки:

```csharp
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

3) В независимой форме в методе [OnSave](fw_form-interaction.html) вызывается функция удаления пустых детейлов:

```csharp
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
