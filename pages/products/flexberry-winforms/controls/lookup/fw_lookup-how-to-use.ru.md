---
title: Как вызвать на LookUp
sidebar: flexberry-winforms_sidebar
keywords: Windows UI (Контролы)
toc: true
permalink: ru/fw_lookup-how-to-use.html
folder: products/flexberry-winforms/
lang: ru
---

# Lookup
# вызывается списочная форма объектов для выбора
# процедуре вызова этой формы передаётся класс и название свойства этого класса, значением которого является объект класса, 	который выбирается на форме

Если такого класса нет, то его надо '''создать'''. 

## Рассмотрим __пример__.

Есть класс '''Реагирование''' и не связанный с ним (отношениями ассоциации или композиции) класс '''ДежурнаяГруппа'''.

С формы редактирования Реагирования надо вызвать список дежурных групп для размазывания свойств конкретной выбранной дежурной группы по свойствам Реагирования(точнее, добавления соответствующих детейлов). 

Нет в модели класса, который содержал бы ссылку на дежурную группу.

Для этого:
Создали '''класс''' (внутренний или нет - не важно, в примере - внутренний) внутри формы Реагирования(там, где вызываем список)
Объект сделали '''нехранимым''', чтобы не рисовать в модели, не генерить и не множить таблицы в базе, ведь класс нам нужен только для LookUp'а

```
	public class WinformРеагированиеE : ICSSoft.STORMNET.UI.BaseWinEdit, ICSSoft.AMS02.Происшествия.DPDIРеагированиеE
	{
		// .....

		[AutoAltered()]
		[NotStored()]
		class Временный_ДежурнаяГруппа : ICSSoft.STORMNET.DataObject
		{
			private Объекты.ДежурнаяГруппа fДЖ = null;
			public virtual Объекты.ДежурнаяГруппа ДЖ
			{
				get
				{
					return fДЖ;
				}
				set
				{
					fДЖ = value;
				}
			}
		}
	
		//.....
	}
```

(((<msg type=Important>ДЖ должно быть свойством (а не публичным полем), т.к. вызывается метод '''SetPropByName''', который работает только со __свойствами__ (при несоблюдении вылетает ошибка, что не могу записать то-то в такое-то свойство, т.к. Null Reference где-то там + не работает двойное нажатие мышки (ничего не происходит) - результат возвращается (точнее вызывается ошибка) только по Enter или кнопке "Up").</msg>)))

 3. На кнопку поднятия списка вешаем такой код:

```

			string propertyName = "ДЖ";
			string contPath = "";
			base.OnEdit(propertyName, new Временный_ДежурнаяГруппа(), contPath, null);
```

__propertyName__ - имя свойства класса, который  передаётся 2-м параметром (в данном случае Временный_ДежурнаяГруппа), в который вернётся выбранный элемент. 

__Однако__, это работает, только если класс ''ХРАНИМЫЙ'' (т.к. возврат параметров происходит через SaveEvent (вроде как...)).

Поэтому делаем так:

```

		{
			object form = null;
			System.Type FormType = null;
			ICSSoft.STORMNET.DataObject dobj = null;
			string propertyName = "ДЖ";
			
			FormType = System.Type.GetType("ICSSoft.AMS02.Происшествия.ДежурнаяГруппаL,Происшествия(Forms)");
			
			Временный_ДежурнаяГруппа FL = new Временный_ДежурнаяГруппа();
			//FL.ДЖ = ...;
			dobj = FL;

			form = ICSSoft.STORMNET.UI.ContRunner.RunForm(FormType);
			if (form is ICSSoft.STORMNET.UI.BaseIndpdList)
			{
				(form as ICSSoft.STORMNET.UI.BaseIndpdList).SaveEvent += new ICSSoft.STORMNET.UI.SaveEventArgsHandler(OnReturnFromList);
				(form as ICSSoft.STORMNET.UI.BaseIndpdList).Edit(dobj, "", propertyName, null);
			}
		}

		Объекты.ДежурнаяГруппа ДежурГр;

		private void OnReturnFromList(object sender, ICSSoft.STORMNET.UI.SaveEventArgs e)
		{
			this.ДежурГр = ((Временный_ДежурнаяГруппа)e.dataobject).ДЖ;
		}
```

 4. Подписываемся на свой обработчик сохранения и делаем там, всё что надо. Если FL.ДЖ присвоить конкретное значение, то в списке будет подсвечен (выбран) этот элемент
 

