---
title: Автоматическое вычисление
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_auto-calculation.html
lang: ru
---

Цель: Цена товара в заказе должна вычисляться автоматически.

1.Сделать в `СтрокеЗаказа` вычислимыми атрибуты `ЦенаСНалогами` и `Сумма`, так как их значения однозначно определяются значением `Цены` `Товара` и `Количества` в `СтрокеЗаказа`:

![](/images/pages/guides/flexberry-aspnet/not-stored.png)

2.В `СтрокаЗаказа.cs` в коде аксессоров `get` для свойств `ЦенаСНалогами` и `Сумма` прописать вычисления атрибутов:

```csharp
[ICSSoft.STORMNET.NotStored()]
public virtual double ЦенаСНалогами
{
	get
	{
		// *** Start programmer edit section *** (СтрокаЗаказа.ЦенаСНалогами Get)
		Double ценасналогами = 0;
		if (Товар != null && Товар.Цена > 0)
		{
			ценасналогами = Товар.Цена * 1.35;
		}
		return ценасналогами;
		// *** End programmer edit section *** (СтрокаЗаказа.ЦенаСНалогами Get)
	}
	set
	{
		// *** Start programmer edit section *** (СтрокаЗаказа.ЦенаСНалогами Set)
		// *** End programmer edit section *** (СтрокаЗаказа.ЦенаСНалогами Set)
	}
}

[ICSSoft.STORMNET.NotStored()]
public virtual double Сумма
{
	get
	{
		// *** Start programmer edit section *** (СтрокаЗаказа.Сумма Get)
		Double сумма = 0;
		if (Товар != null && Товар.Цена > 0)
		{
			сумма = ЦенаСНалогами * Количество;
		}
		return сумма;
		// *** End programmer edit section *** (СтрокаЗаказа.Сумма Get)
	}
	set
	{
		// *** Start programmer edit section *** (СтрокаЗаказа.Сумма Set)
		// *** End programmer edit section *** (СтрокаЗаказа.Сумма Set)
	}
}
```

3.Аналогично прописать вычисление атрибута `Цена` в `Заказ.cs`:

```csharp
[ICSSoft.STORMNET.NotStored()]
public virtual double Цена
{
	get
	{
		// *** Start programmer edit section *** (Заказ.Цена Get)
		double цена = 0;
		for (int i = 0; i < this.СтрокаЗаказа.Count; i++)
			цена += (double)this.СтрокаЗаказа[i].Сумма;
		return цена;
		// *** End programmer edit section *** (Заказ.Цена Get)
	}
	set
	{
		// *** Start programmer edit section *** (Заказ.Цена Set)

		// *** End programmer edit section *** (Заказ.Цена Set)
	}
}
```

{% include note.html content="Важно помнить, что добавлять свой код необходимо только в скобках программиста (между словами `Start  programmer  edit  section`  и  `End  programmer  edit  section`), иначе при очередной перегенерации исходного кода, внесённые изменения будут потеряны." %}

4.Поскольку в вычислении используется `Товар.Цена`, необходимо, чтобы значение `Товар.Цена` из мастерового класса для строки заказа зачитывалось из базы, для чего надо в представление добавить это поле:

![](/images/pages/guides/flexberry-aspnet/view-stroka-zakaza.png)

5.Убрать поле `Цена` из представления для списка заказов, так как `Цена` больше не нужна в списке заказов.

![](/images/pages/guides/flexberry-aspnet/view-zakaz.png)

6.Перегенерировать-перекомпилировать объекты.

7.Чтобы пользователь не имел возможности исправить значение поля `Цена`, в методе `Preload()` нужно прописать следующее:

```csharp
protected override void Preload()
{
	ctrlЦена.ReadOnly = true;
}
```

Свойство `ReadOnly` определяет, доступен ли объект только для чтения. По умолчанию данное свойство имеет значение `false`.
`PreLoad()` - данный метод вызывается самым первым в методе `Page_Load()`.

Чтобы скрипты на странице отрабатвыали после подключения всех основных библиотек нужно выполнить следующие действия:

1.Открыть конфигурациооный файл `Web.config`, в нем располагаются основные настройки конфигурации. 
2.Найти в конфмгурационном файле строку: 

```
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http" mapTo="NewPlatform.Flexberry.Web.Http.FilterPageContentConnector, NewPlatform.Flexberry.Web.Http" />
```
3.Сменить найденную строку на следующую:

```
<register type="NewPlatform.Flexberry.Web.Http.IPageContentConnector, NewPlatform.Flexberry.Web.Http" mapTo="NewPlatform.Flexberry.Web.Http.PlaceholderPageContentConnector, NewPlatform.Flexberry.Web.Http" />
```

Скрипты на странице нужно прописывать в плейсхолдер:

```
<asp:Content ContentPlaceHolderID="ContentPlaceHolder0"  runat="server" >
</asp:Content>
```

4.Далее необходимо организовать автоматическое вычисление на клиенте, для чего в `ZakazE.aspx` следует прописать:

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
				* Вычисление цены и суммы при изменении товара.
				*/
			$( '#<%=ctrlСтрокаЗаказа.ClientID%>' ).on('change', '[id$=ctrlТовар]', function() {
				autoCalc(this, this.parentNode.parentNode.parentNode.parentNode);
			});
			/**
				* Вычисление цены и суммы при изменении количества.
				*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('change', '[id$=ctrlКоличество]', function (e) {
				autoCalc(this, this.parentNode.parentNode.parentNode);
			});
		});

	</script>
</asp:Content>
```

__Примечание:__ При сложении некоторых дробных чисел, выдаётся арифметически неверный результат. Такие результаты получаются из-за особенностей работы c числами с плавающей точкой, поэтому необходимо округлять числа.

5.Чтобы при очередной перегенерации исходного кода внесённые изменения не были потеряны, следует изменить значение параметра `flexberryautogenerated` на `false`.

![](/images/pages/guides/flexberry-aspnet/autogen-false.png)

6.Запустить приложение и проверить, как работают внесённые изменения (при изменении товара или его количества, автоматически вычисляется цена с налогами, сумма и цена заказа).

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Указание текущей даты.](gpg_date-time-now.html)
* [Наложение ограничения на LookUp в AGE, связывание лукапов.](gpg_limit-function-for-lookup-in-age.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
