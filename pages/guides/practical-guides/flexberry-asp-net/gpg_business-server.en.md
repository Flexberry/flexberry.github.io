---
title: Work with business server
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_business-server.html
lang: en
autotranslated: true
hash: b7c17dd34bceed4efe2b9f9f04f9a8544cc21084bdcf8ab16287afb75321b551
---

Goal: to translate the order into a state `Оплаченный` it is necessary to check whether the goods to be discharged, and if so subtract the required amount of product.
Note: for simplicity, we assume that the goods may be located in different warehouses, and the requested amount is searched from all warehouses, summing up.

A business server is a specialized class that allows to intercept the current service data operations on a data source (such as creating a record in a database table, delete, update), depending on the state of the data object. To implement such a class has the stereotype `businessserver`.

Such as, in order to check the availability of goods in stock at the time the order is saved with status `Оплаченный`, you must:

1.To add to the class diagram the class with the stereotype `businessserver`.

![](/images/pages/guides/flexberry-aspnet/add-bsclass.png)

2.To save the graph, in the properties of the class `Заказ` to specify the name of the business server, and save the editing form class. Then, from the drop-down list, choose positive `OnAllEvents` (i.e. during any service operations, data):

![](/images/pages/guides/flexberry-aspnet/set-bsclass-in-zakaz.png)

3.To save the graph. Generate business servers and data objects.

![](/images/pages/guides/flexberry-aspnet/gen-bs-and-objects.png)

4.The project with the business servers to add to `Solution`. Add a project reference to the business server to the application projects.
5.Then register in parentheses programmer the following lines (to select the options use a combination of `Ctrl` Space):

```csharp
// *** Start programmer edit section *** (Using statements) 
using System.Collections;

using ICSSoft.STORMNET;
using ICSSoft.STORMNET.FunctionalLanguage;
using ICSSoft.STORMNET.FunctionalLanguage.SQLWhere;

// *** End programmer edit section *** (Using statements) 
```

6.Later in the code business servers to handle all of the following:

```csharp
public virtual ICSSoft.STORMNET.DataObject[] OnUpdateЗаказ(АСУ_Склад.Заказ UpdatedObject)
{
	// *** Start programmer edit section *** (OnUpdateЗаказ) 

	// Define the array that will return for updates. 
	DataObject[] ret = new DataObject[0];

	// Check that the received object is exactly what we need (created or modified and the status is set to Paid). 
	if ((UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Created || UpdatedObject.GetStatus() == ICSSoft.STORMNET.ObjectStatus.Altered) && Array.IndexOf(UpdatedObject.GetAlteredPropertyNames(), Status) >= 0 && UpdatedObject.Статус == СостояниеЗаказа.Оплаченный)			
	{   
		// Build the restriction and subtract all the objects in Tavarnelle that suit us. 
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

		ICSSoft.STORMNET.Business.LoadingCustomizationStruct lcs = ICSSoft.STORMNET.Business.LoadingCustomizationStruct.GetSimpleStruct(typeof(ТоварНаСкладе),"Tavernacle");
		lcs.LimitFunction = lf;
		ICSSoft.STORMNET.DataObject[] objs = ICSSoft.STORMNET.Business.DataServiceProvider.DataService.LoadObjects(lcs);

		// Place the read objects in a sorted list for easy access later on. 
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

		// Define the string for the error message. 
		string errStr = string.Empty;
		ArrayList retObjs = new ArrayList();

		// Check the availability of goods in warehouses, if not enough, then give error message if missing, then subtract the number. 
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
					errStr += "Missing item \"" + заказ.СтрокаЗаказа[i].Товар.Название + "\" available: " + количествоНаСкладах + "requires " + заказ.СтрокаЗаказа[i].Количество + Environment.NewLine;
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
				errStr += "Product \"" +
				заказ.СтрокаЗаказа[i].Товар.Название + "\" available is missing." + Environment.NewLine;
			}
		}

		// In case something is not enough, we inform the user. 
		if (errStr != string.Empty)
		{
			throw new Exception(errStr);
		}

		// If everything is fine, then return an array of objects that need to be updated. 
		ret = new DataObject[retObjs.Count]; retObjs.CopyTo(ret, 0);
	}
	return ret;

	// *** End programmer edit section *** (OnUpdateЗаказ) 
}
```

## Go

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Automatic retrieving data from LookUp](gpg_auto-get-data-from-lookup.html)
* [Lock item in the edit form](gpg_set-ctrl-read-only.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}