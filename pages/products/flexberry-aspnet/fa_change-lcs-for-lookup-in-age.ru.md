---
title: Связывание LookUp'ов в AGE с внешним LookUp'ом
sidebar: flexberry-aspnet_sidebar
keywords: FAQ, Flexberry ASP-NET, How to (page)
toc: true
permalink: ru/fa_change-l-c-s-for-look-up-in-a-g-e.html

---

* **Платформа**: [FlexberryASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент**: [Настройки для LookUp в AGE](settings-for-look-up-in-a-g-e.html)
* **Предназначение**: Настройка ограничения для LookUp в [AjaxGroupEdit](ajax-group-edit.html) 

## Связывание LookUp'ов в AGE с внешним LookUp'ом

Цель: 
<<<<<<< HEAD:pages/products/flexberry-aspnet/fa_ChangeLCSForLookUpInAGE.ru.md
[Функция ограничения](fo_limit-function.html) [LookUp'ов](master-editor-ajax-look-up.html) внутри [AjaxGroupEdit](ajax-group-edit.html) должна зависеть от значения во внешнем LookUp'е.
=======
[Функция ограничения](fo_limit-function.html) [LookUp'ов](fa_master-editor-ajax-look-up.html) внутри [AjaxGroupEdit](fa_ajax-group-edit.html) должна зависеть от значения во внешнем LookUp'е.
>>>>>>> d39794c485bf490f825f86803b545b9c10b0808f:pages/products/flexberry-aspnet/fa_change-lcs-for-lookup-in-age.ru.md


Модель:

![](/images/pages/products/flexberry-aspnet/aspnet/model.png)

Код:

```cs
/// <summary>
/// Метод изменяющий LCS в лукапах, находящихся в AGE.
/// </summary>
/// <param name="territoryKey">Ключ значения с "главного" лукапа.</param>
/// <param name="lfKey">Ключ сессии.</param>
/// <returns>Ключ сессии.</returns>
[WebMethod]
public static string CreateLf(string territoryKey, string lfKey)
{
    if (string.IsNullOrEmpty(lfKey))
    {
        lfKey = Guid.NewGuid().ToString("B");
    }

    var langdef = ExternalLangDef.LanguageDef;

    var lf = langdef.GetFunction(langdef.funcEQ,
        new VariableDef(langdef.GuidType, Information.ExtractPropertyName<Company>(x => x.Territory)),
        territoryKey);

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
                    type: "POST",
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
             * Перебираем в АГЕ ctrlCompanyEmployee все лукапы ctrlCompany и проставляем им измененный limit function.
             */
            if (territoryKey) {
                changeLf();
                $('[id$=ctrlCompany]', '#<%=ctrlCompanyEmployee.ClientID%>').each(
                    function() {
                        $(this).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
                    });
            }
            /**
             * Если в "главном" лукапе ctrlTerritory поменяли значение - то переопределяем все лукапы ctrlCompany в АГЕ ctrlCompanyEmployee.
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
             * Если добавлена новая строка в АГЕ, сразу назначим limit function.
             * @param {int} row Номер добавленной строки.
             */
            $('#<%=ctrlCompanyEmployee.ClientID%>').on('rowadded.ajaxgroupedit', function(e, d) {
                $('[id$=ctrlCompany]', d).icsMasterEditorAjaxLookup('updateOptions', { lookup: { LFName: lfName } });
            });
        });
    </script>
```

## Откуда ссылаются на эту страницу

* [AjaxGroupEdit (AGE)](ajax-group-edit.html)
* [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html)

## Куда ссылается эта страница

* [AjaxGroupEdit (AGE)](ajax-group-edit.html)
* [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* [Функции ограничения (Limit Function)](fo_limit-function.html)
* [MasterEditorAjaxLookUp](fa_master-editor-ajax-look-up.html)
* [Настройки для LookUp в AGE](fa_settings-for-look-up-in-a-g-e.html)
