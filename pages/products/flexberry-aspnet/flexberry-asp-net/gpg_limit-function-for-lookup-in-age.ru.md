---
title: Наложение ограничения на LookUp в AGE, связывание лукапов.
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_limit-function-for-lookup-in-age.html
lang: ru
---

Наложение ограничения на список выбираемых товаров в заказе.

Цель: Чтобы в заказе нельзя было указать несколько раз один товар, необходимо наложить ограничение таким образом, чтобы уже выбранные товары в списке для нового выбора более не появлялись.
Для нужно добавить ограничение для товаров, которое будет изменятся в зависимости от состояния списка товаров на странице.

1.В блок подключаемых пространств имен добавить следующие строчки:    

```
using System;
using System.Collections.Generic;
using System.Web.Services;

using ICSSoft.STORMNET.Web.Tools;
```

2.В `ZakazE.aspx.cs` добавить следующий метод:

```csharp
/// <summary>
/// Метод изменяющий LCS в лукапах, находящихся в AGE.
/// </summary>
/// <param name="ordKeys">Ключи.</param>
/// <param name="lfKey">Ключ сессии.</param>
/// <returns>Ключ сессии.</returns>
[WebMethod]
public static string CreateLf(string[] ordKeys, string lfKey)
{
	if (string.IsNullOrEmpty(lfKey))
	{
		lfKey = Guid.NewGuid().ToString("B");
	}
	
	Function lf = FunctionBuilder.BuildNotIn(ordKeys);
	LimitFunctionsHolder.PersistLimitFunction(lfKey, lf);

	return lfKey;
}
```

Присоединение атрибута `WebMethod` к методу `Public` означает, что этот метод должен публиковаться в качестве составной части веб-службы `XML`. Свойства этого атрибута также можно использовать для более детальной настройки поведения метода веб-службы `XML`.

3.В `ZakazE.aspx` исправить скрипт:

```js
<asp:Content ContentPlaceHolderID="ContentPlaceHolder0"  runat="server" >
	<script type="text/javascript">
		
		$(document).ready(function() {
			var lfName = '';
			var ordKeys = [];
			$('[id$=ctrlТовар]', '#<%=ctrlСтрокаЗаказа.ClientID%>').each(
				function() {
					ordKeys.push($(this).val());
			});
			
			var changeLf = function() {
				$.ajax({
					type: "POST",
					url: window.location.pathname + "/CreateLf",
					dataType: "json",
					contentType: "application/json; charset=utf-8",
					data: JSON.stringify({ ordKeys: ordKeys, lfKey: lfName }),
					async: false, cache: false,
					success: function(data) {
						lfName = data.d;
					}
				});
				return false;
			}

			var changeAll = function() {
				ordKeys = [];
				var data = $('#<%=ctrlСтрокаЗаказа.ClientID%>').ajaxgroupedit('getDataRows');
				$('[id$=ctrlТовар]', data).each(
					function() {
						ordKeys.push($(this).val());
					});
				changeLf();
				$('[id$=ctrlТовар]', data).each(
					function() {
						$(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
					});
			};
			/**
				* Перебираем в АГЕ ctrlСтрокаЗаказа все лукапы ctrlТовар и проставляем им измененный limit function.
				*/
			if (ordKeys) {
				changeLf();
				$('[id$=ctrlТовар]', '#<%=ctrlСтрокаЗаказа.ClientID%>').each(
					function() {
						$(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
					});
			}
			/**
				* Если в одном из лукапов ctrlТовар поменяли значение - то переопределяем все лукапы ctrlТовар в АГЕ ctrlСтрокаЗаказа.
				*/
			$( '#<%=ctrlСтрокаЗаказа.ClientID%>' ).on('change', '[id$=ctrlТовар]', function() {
				changeAll();
				autoCalc(this, this.parentNode.parentNode.parentNode.parentNode);
			});
			/**
				* Если добавлена новая строка в АГЕ, сразу назначим limit function.
				* @param {int} row Номер добавленной строки.
				*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('rowadded.ajaxgroupedit', function(e, d) {
				$('[id$=ctrlТовар]', d).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
			});
			/**
				* Если удалена строка в АГЕ, сразу изменяем limit function.
				*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('rowdeleted.ajaxgroupedit', function () {
				changeAll();
			});

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
				* Вычисление цены и суммы.
				*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('change', '[id$=ctrlКоличество]', function (e) {
				autoCalc(this, this.parentNode.parentNode.parentNode);
			});
		});

	</script>
</asp:Content>
```

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Автоматическое вычисление](gpg_auto-calculation.html)
* [Обработка исключений](gpg_set-exception.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
