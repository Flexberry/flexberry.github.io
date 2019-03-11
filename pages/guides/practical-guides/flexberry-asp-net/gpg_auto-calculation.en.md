--- 
title: Automatic calculation 
sidebar: guide-practical-guides_sidebar 
keywords: guide 
toc: true 
permalink: en/gpg_auto-calculation.html 
lang: en 
autotranslated: true 
hash: d40666c016b5998685aa788b74d1590223fdb59f81574afe8742f8ccf8e5c303 
--- 

Goal: the Price of goods should be calculated automatically. 

1.To do in `СтрокеЗаказа` computable attributes `ЦенаСНалогами` and `Сумма`, as their values uniquely determined by the value `Цены` `Товара` and `Количества` in `СтрокеЗаказа`: 

![](/images/pages/guides/flexberry-aspnet/not-stored.png) 

2.In `СтрокаЗаказа.cs` in the code `get` accessors for properties `ЦенаСНалогами` and `Сумма` to prescribe the computation of the attributes: 

```csharp
[ICSSoft.STORMNET.NotStored()]
public virtual double ЦенаСНалогами
{
	get
	{
		// *** Start programmer edit section *** (Starokazache.Zeynaloglu Get) 
		Double ценасналогами = 0;
		if (Товар != null && Товар.Цена > 0)
		{
			ценасналогами = Товар.Цена * 1.35;
		}
		return ценасналогами;
		// *** End programmer edit section *** (Starokazache.Zeynaloglu Get) 
	}
	set
	{
		// *** Start programmer edit section *** (Starokazache.Zeynaloglu Set) 
		// *** End programmer edit section *** (Starokazache.Zeynaloglu Set) 
	}
}

[ICSSoft.STORMNET.NotStored()]
public virtual double Сумма
{
	get
	{
		// *** Start programmer edit section *** (Starokazache.Amount Get) 
		Double сумма = 0;
		if (Товар != null && Товар.Цена > 0)
		{
			сумма = ЦенаСНалогами * Количество;
		}
		return сумма;
		// *** End programmer edit section *** (Starokazache.Amount Get) 
	}
	set
	{
		// *** Start programmer edit section *** (Starokazache.The Amount Is Set) 
		// *** End programmer edit section *** (Starokazache.The Amount Is Set) 
	}
}
``` 

3.Similarly to specify the calculation of the attribute `Цена` in `Заказ.cs`: 

```csharp
[ICSSoft.STORMNET.NotStored()]
public virtual double Цена
{
	get
	{
		// *** Start programmer edit section *** (Order.Price Get) 
		double цена = 0;
		for (int i = 0; i < this.СтрокаЗаказа.Count; i++)
			цена += (double)this.СтрокаЗаказа[i].Сумма;
		return цена;
		// *** End programmer edit section *** (Order.Price Get) 
	}
	set
	{
		// *** Start programmer edit section *** (Order.Price Set) 

		// *** End programmer edit section *** (Order.Price Set) 
	}
}
``` 

{% include note.html content="it is Important to remember that to add your code only necessary parentheses programmer (between words `Start programmer edit section` and `End programmer edit section`), otherwise at the next generation of the source code, your changes will be lost." %} 

4.Since the computation uses `Товар.Цена`, it is important to ensure `Товар.Цена` of the artisan class for the order line was read from the database, what we need in the view to add this field: 

![](/images/pages/guides/flexberry-aspnet/view-stroka-order.png) 

5.PstrfЦена` remove a field from view for the order list, as `Цена` is no longer needed in the list of orders. 

![](/images/pages/guides/flexberry-aspnet/view-zakaz.png) 

6.To regenerate is to recompile the objects. 

7.So the user was not able to fix the value of the field `Цена`, in the method `Preload()` it is necessary to prescribe the following: 

```csharp
protected override void Preload()
{
	ctrlЦена.ReadOnly = true;
}
``` 

PstrfReadOnly` property determines whether the object is read-only. By default, this property is set to `false`. 
`PreLoad()` - this method is called very first in the method `Page_Load()`. 

To scripts on the page, otrabotali after connecting all major libraries need to do the following: 

1.Open konfiguratsiooni file `Web.config`, there are the main configuration settings. 
2.Find in configurational file the line: 

```
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http" mapTo="NewPlatform.Flexberry.Web.Http.FilterPageContentConnector, NewPlatform.Flexberry.Web.Http" />
``` 
3.To change the line found the following: 

```
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http" mapTo="NewPlatform.Flexberry.Web.Http.PlaceholderPageContentConnector, NewPlatform.Flexberry.Web.Http" />
``` 

The scripts on the page you need to register to the placeholder: 

```
<asp:Content ContentPlaceHolderID="ContentPlaceHolder0"  runat="server" >
</asp:Content>
``` 

4.Next, you need to organize automatic calculation on the client, which in `ZakazE.aspx` should register: 

```js
<asp:Content ContentPlaceHolderID="ContentPlaceHolder0"  runat="server" >
	<script type="text/javascript">

		$(document).ready(function() {
			var autoCalc = function (_this, row) {
				var col = parseInt($('[id$=ctrlКоличество]', row).val());
				var price = parseFloat($('[id$=ctrlТовар_Цена]', row).text());
				price = price ? price : 0; 
				var pricetax = 1.35 * price;
				$('[id$=ctrlЦенаСНалогами]', row).text(pricetax.toFixed(2));
				$('[id$=ctrlСумма]', row).text((col * pricetax).toFixed(2));
				var sum = 0;
				$('[id$=ctrlСумма]', '#<%=ctrlСтрокаЗаказа.ClientID%>').each(
						function() {
								sum += parseFloat($(this).text());
						});

				$('#<%=ctrlЦена.ClientID%>').val(sum.toFixed(2));
			}

			/** 
* Calculation of the rates and amounts when you change the product. 
*/
			$( '#<%=ctrlСтрокаЗаказа.ClientID%>' ).on('change', '[id$=ctrlТовар]', function() {
				autoCalc(this, this.parentNode.parentNode.parentNode.parentNode);
			});
			/** 
* Calculation of the rates and amounts when you change the number. 
*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('change', '[id$=ctrlКоличество]', function (e) {
				autoCalc(this, this.parentNode.parentNode.parentNode);
			});
		});

	</script>
</asp:Content>
``` 

__Note:__ When adding certain floating point numbers, issued arithmetically incorrect result. Such results are obtained because of the peculiarities of working with floating point numbers, so you need to round the number. 

5.To the next generation of source code changes is not lost, you should change the parameter value `flexberryautogenerated` on `false`. 

![](/images/pages/guides/flexberry-aspnet/autogen-false.png) 

6.Start the app and to see how changes (if changes to the product or its quantity, price is automatically calculated tax amount and the price of the order). 

## Go 

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [an Indication of the current date.](gpg_date-time-now.html) 
* [The imposition of restrictions on the LookUp in the AGE, associate lyapov.](gpg_limit-function-for-lookup-in-age.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}