---
title: Linking LookUp in AjaxGroupEdit with external LookUp
sidebar: flexberry-aspnet_sidebar
keywords: FAQ, Flexberry ASP NET, How to (page)
toc: true
permalink: en/fa_change-lcs-lookup-age.html
lang: en
autotranslated: true
hash: 1bd01bfe757d5eee97b2f2697e0dcb0ed94651e5a76ae558cbe901f3b63a9656

---

**The purpose of the binding LookUp in AGE from external LookUp:** [Function limitations](fo_limit-function.html) [LookUp](fa_master-editor-ajax-lookup.html) inside [AjaxGroupEdit](fa_ajax-group-edit.html) should depend on the values in the external LookUp.

Model:

![Model](/images/pages/products/flexberry-aspnet/controls/groupedit/model.png)

Code:

```csharp
/// <summary> 
/// Method modifies the LCS in lucapa, located in AGE. 
/// </summary> 
/// <param name="territoryKey">the Key values from the "main" lucapa.</param> 
/// <param name="lfKey">session Key.</param> 
/// <returns>the session Key.</returns> 
[WebMethod]
public static string CreateLf(string territoryKey, string lfKey)
{
    if (string.IsNullOrEmpty(lfKey))
    {
        lfKey = Guid.NewGuid().ToString("B");
    }

    var lf = FunctionBuilder.BuildEquals<Company>(x => x.Territory, territoryKey);

    LimitFunctionsHolder.PersistLimitFunction(lfKey, lf);

    return lfKey;
}
```

```javascript
<script type="text/javascript">
        $(document).ready(function() {
            var lfName = '';
            var territoryKey = $('#<%=ctrlTerritory.ClientID%>').val();
            var changeLf = function() {
                $.ajax({
                    type: POST,
                    url: window.location.pathname + "/CreateLf",
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify({ territoryKey: territoryKey, lfKey: lfName }),
                    async: false, cache: false,
                    success: function(data) {
                        lfName = data.d;
                    }
                });
            }
            /** 
* Iterate in Agay ctrlCompanyEmployee all lucapa ctrlCompany and stamped the changed limit function. 
*/
            if (territoryKey) {
                changeLf();
                $('[id$=ctrlCompany]', '#<%=ctrlCompanyEmployee.ClientID%>').each(
                    function() {
                        $(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
                    });
            }
            /** 
* If in the "main" lukapa ctrlTerritory changed the value, then override all lucapa ctrlCompany in Agay ctrlCompanyEmployee. 
*/
            $('#<%=ctrlTerritory.ClientID%>').on('change',
                function() {
                    territoryKey = $('#<%=ctrlTerritory.ClientID%>').val();
                    changeLf();
                    $('[id$=ctrlCompany]', '#<%=ctrlCompanyEmployee.ClientID%>').each(
                        function() {
                            $(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
                        });
                });
            /** 
* If new row added in Agay, just assign the limit function. 
* @param {int} row Number of the added row. 
*/
            $('#<%=ctrlCompanyEmployee.ClientID%>').on('rowadded.ajaxgroupedit', function(e, d) {
                $('[id$=ctrlCompany]', d).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
            });
        });
    </script>
```



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}