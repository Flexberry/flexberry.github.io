---
title: Imposing restrictions on the LookUp in the AGE, associate lyapov.
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: en/gpg_limit-function-for-lookup-in-age.html
lang: en
autotranslated: true
hash: cb2c9124c04ed39c2ab07c966d73b1c39c060dfd9e34948c8f5a02d196cdf0cd
---

The imposition of restrictions on a list of selectable items in the order.

Objective: in the order it was impossible to specify multiple single product, it is necessary to impose restrictions so that the already selected items in the list for a new selection no longer appeared.
You have to add the constraint for commodities, which will vary depending on the state of the list of items on the page.

1.In block pluggable namespaces, add the following lines:

```
using System;
using System.Collections.Generic;
using System.Web.Services;

using ICSSoft.STORMNET.Web.Tools;
```

2.In `ZakazE.aspx.cs` add the following method:

```csharp
/// <summary> 
/// Method modifies the LCS in lucapa, located in AGE. 
/// </summary> 
/// <param name="ordKeys">the Key.</param> 
/// <param name="lfKey">session Key.</param> 
/// <returns>the session Key.</returns> 
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

Attaching the attribute to a method `WebMethod` `Public` means that this method should be published as part of the web service `XML`. The properties of this attribute can also be used to fine-tune the behavior of the method, the web service `XML`.

3.In `ZakazE.aspx` to fix the script:

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
					type: POST,
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
* Iterate in Agay ctrlСтрокаЗаказа all lucapa ctrlТовар and stamped the changed limit function. 
*/
			if (ordKeys) {
				changeLf();
				$('[id$=ctrlТовар]', '#<%=ctrlСтрокаЗаказа.ClientID%>').each(
					function() {
						$(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
					});
			}
			/** 
* If one of lyapov ctrlТовар changed the value, then override all lucapa ctrlТовар in Agay ctrlСтрокаЗаказа. 
*/
			$( '#<%=ctrlСтрокаЗаказа.ClientID%>' ).on('change', '[id$=ctrlТовар]', function() {
				changeAll();
				autoCalc(this, this.parentNode.parentNode.parentNode.parentNode);
			});
			/** 
* If new row added in Agay, just assign the limit function. 
* @param {int} row Number of the added row. 
*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('rowadded.ajaxgroupedit', function(e, d) {
				$('[id$=ctrlТовар]', d).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
			});
			/** 
* If the deleted row in AGAY, just change limit function. 
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
* Calculating rates and amounts. 
*/
			$('#<%=ctrlСтрокаЗаказа.ClientID%>').on('change', '[id$=ctrlКоличество]', function (e) {
				autoCalc(this, this.parentNode.parentNode.parentNode);
			});
		});

	</script>
</asp:Content>
```

## Go

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Automatic calculation](gpg_auto-calculation.html)
* [Exception handling](gpg_set-exception.html) <i class="fa fa-arrow-right" aria-hidden="true"></i>



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}