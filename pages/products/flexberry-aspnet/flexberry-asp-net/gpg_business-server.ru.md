---
title: Работа с бизнес-сервером
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_business-server.html
lang: ru
---

Цель: При переводе заказа в состояние `Оплаченный` необходимо проверять, может ли быть товар выписан, и если да, то вычитать необходимое количество товара.
Примечание: для простоты будем считать, что товар может находиться на разных складах, и затребованное количество ищется по всем складам, суммируя.

Бизнес-сервер – это специализированный класс, который позволяет перехватить реально выполняемые сервисом данных операции над источником данных (такие как создание записи в таблице базы данных, удаление, обновление) в зависимости от состояния объекта данных. Для реализации такого класса есть стереотип `businessserver`.

Нпример, для реализации проверки наличия товара на складе в момент сохранения заказа со статусом `Оплаченный`, необходимо:

1.Добавить на диаграмму классов класс со  стереотипом `businessserver`.

![](/images/pages/guides/flexberry-aspnet/add-bsclass.png)

2.Сохранить диаграмму, в свойствах класса `Заказ` указать соответствующее имя бизнес-сервера и сохранить форму редактирования класса. Затем, из появившегося выпадающего списка, выбрать срабатывание на `OnAllEvents` (т.е. при любых операциях сервиса данных):

![](/images/pages/guides/flexberry-aspnet/set-bsclass-in-zakaz.png)

3.Сохранить диаграмму. Сгенерировать бизнес-серверы и объекты данных.

![](/images/pages/guides/flexberry-aspnet/gen-bs-and-objects.png)

4.Проект с бизнес-серверами добавить в `Solution`. Добавить ссылку на проект с бизнес-сервером в проекты приложений.
5.После этого прописать в скобках программиста следующие строчки (для выбора вариантов используется сочетание `Ctrl`+Пробел):

```csharp
// *** Start programmer edit section *** (Using statements)
using System.Collections;

using ICSSoft.STORMNET;
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;

// *** End programmer edit section *** (Using statements)
``` 

6.Далее в коде бизнес-сервера обработать всё следующим образом:

```csharp
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateЗаказ(АСУ_Склад.Заказ UpdatedObject)
{
	// *** Start programmer edit section *** (OnUpdateЗаказ)

	// Определим массив, который будем возвращать для обновления.
	DataObject[] ret = new DataObject[0];

	// Проверим  на  то,  что  пришедший  объект  -  это	именно то, что нам нужно (создан или изменён и статус установлен в Оплачено).		
	if ((UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created || UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Altered) && Array.IndexOf(UpdatedObject.GetAlteredPropertyNames(), "Статус") >= 0 && UpdatedObject.Статус == СостояниеЗаказа.Оплаченный)			
	{   
		// Построим ограничение и вычитаем все объекты ТоварНаСкладе, которые нам подходят.
		Заказ заказ = UpdatedObject;		
		ICSSoft.STORMNET.FunctionalLanguage.Function lf = null; 

		for (int i = 0; i < заказ.СтрокаЗаказа.Count; i++)
		{
			if (lf != null)
			{
				if (заказ.СтрокаЗаказа[i].Товар != null)
					lf = FunctionBuilder.BuildOr(
							lf,
							FunctionBuilder.BuildEquals<ТоварНаСкладе>(x => x.Goods, заказ.СтрокаЗаказа[i].Товар));
			}

			else
			{
				if (заказ.СтрокаЗаказа[i].Товар != null)
					lf = FunctionBuilder.BuildEquals<ТоварНаСкладе>(x => x.Goods, заказ.СтрокаЗаказа[i].Товар);
			}
		}

		ICSSoft.STORMNET.Business.LoadingCustomizationStruct lcs = ICSSoft.STORMNET.Business.LoadingCustomizationStruct.GetSimpleStruct(typeof(ТоварНаСкладе),"ТоварНаСкладеE");
		lcs.LimitFunction = lf;
		ICSSoft.STORMNET.DataObject[] objs = ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObjects(lcs);

		// Разместим вычитанные объекты в отсортированном списке для удобного доступа в дальнейшем.
		System.Collections.SortedList sl = new System.Collections.SortedList();

		for (int i = 0; i < objs.Length; i++)
		{
			if (sl.ContainsKey(((ТоварНаСкладе)objs[i]).Товар.__PrimaryKey))
			{
				((System.Collections.ArrayList)sl[objs[i].__PrimaryKey]).Add(objs[i]);
			}
			else
			{
				System.Collections.ArrayList списокТоваров = new System.Collections.ArrayList();
				списокТоваров.Add(objs[i]);
				sl.Add(((ТоварНаСкладе)objs[i]).Товар.__PrimaryKey, списокТоваров);
			}
		}

		// Определим строчку для сообщения об ошибке. 
		string errStr = string.Empty;
		ArrayList retObjs = new ArrayList();

		// Проверим наличие товара на складах, если не хватает, то выдадим сообщение об ошибке, если хватает, то вычитаем количество.
		for (int i = 0; i < заказ.СтрокаЗаказа.Count; i++)
		{
			if (sl.ContainsKey(заказ.СтрокаЗаказа[i].Товар.__PrimaryKey))
			{
				ArrayList arl = ((System.Collections.ArrayList)sl[заказ.СтрокаЗаказа[i].Товар.__PrimaryKey]);

				int количествоНаСкладах = 0; for (int j = 0; j < arl.Count; j++)
				{
					количествоНаСкладах +=
					((ТоварНаСкладе)arl[j]).Количество;
				}

				if (количествоНаСкладах <
				заказ.СтрокаЗаказа[i].Количество)
				{
					errStr += " Не хватает товара \"" + заказ.СтрокаЗаказа[i].Товар.Название + "\" в наличии: " + количествоНаСкладах + ", требуется " + заказ.СтрокаЗаказа[i].Количество + Environment.NewLine;
				}
				else
				{
					int колич = заказ.СтрокаЗаказа[i].Количество;
					for (int j = 0; j < arl.Count; j++)
					{
						if (колич > 0 &&
						((ТоварНаСкладе)arl[j]).Количество > колич)
						{
							((ТоварНаСкладе)arl[j]).Количество -= колич;
							колич = 0; retObjs.Add(arl[j]);
						}
						else
						{
							if (колич > 0)
							{
								колич -= ((ТоварНаСкладе)arl[j]).Количество;
								((ТоварНаСкладе)arl[j]).Количество = 0;
								retObjs.Add(arl[j]);
							}
						}
					}
				}
			}
			else
			{
				errStr += "Товар	\"" +
				заказ.СтрокаЗаказа[i].Товар.Название + "\" в наличии отсутствует." + Environment.NewLine;
			}
		}

		// В случае, если чего-то не хватило, сообщаем об этом пользователю.
		if (errStr != string.Empty)
		{
			throw new Exception(errStr);
		}

		// Если всё нормально, то возвращаем массив объектов, которые надо обновить.
		ret = new DataObject[retObjs.Count]; retObjs.CopyTo(ret, 0);
	}
	return ret;

	// *** End programmer edit section *** (OnUpdateЗаказ)
}
```

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Автоматическое получение данных из LookUp](gpg_auto-get-data-from-lookup.html)
* [Блокировка элемента на форме редактирования](gpg_set-ctrl-read-only.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
