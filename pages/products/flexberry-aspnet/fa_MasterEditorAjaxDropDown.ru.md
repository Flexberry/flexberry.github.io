---
title: MasterEditorAjaxDropDown
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/fa_master-editor-ajax-dropdown.html
folder: products/flexberry-aspnet/
lang: ru
---

# Описание
`MasterEditorAjaxDropDown` - это [web-контрол](web-controls.html), который позволяет просматривать список доступных мастеровых объектов в виде выпадающего списка.

Ниже показан пример отображения данного контрола на веб-форме.

![](/images/pages/img/page/MasterEditorAjaxDropDown/MasterEditorAjaxDropDownOnForm.png)

# Подключение
Для того, чтобы использовать `MasterEditorAjaxDropDown` на форме редактирования, необходимо на [форме редактирования представления во Flexberry Designer выбрать тип лукапа `combo`](view-edit-form.html), а в качестве свойства мастера указать предпочитаемое для отображения свойство объекта мастера.

Чтобы [встроить данный контрол в AGE](a-g-e-applied-controls.html), требуется произвести настройки, аналогичные [этим](a-g-e-applied-controls.html). При этом в [WebControlProvider.xml](fa_web-control-provider.html) появится запись вида:
```xml
<?xml version="1.0" encoding="utf-8" ?>
<root xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="WebControlProvider.xsd">

	...

  <!--Свойство TestSpecialControlLink у класса LimitEditorDetail2 редактируем с помощью MasterEditorAjaxDropDown.-->
  <customproperty class="LimitEditorDetail2" property="TestSpecialControlLink">
    <control typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxDropDown, ICSSoft.STORMNET.Web.AjaxControls" property="SelectedMasterPK" codefile=""/>
    <editcontrol typename="ICSSoft.STORMNET.Web.AjaxControls.MasterEditorAjaxDropDown, ICSSoft.STORMNET.Web.AjaxControls" codefile="" property="SelectedMasterPK"/>
  </customproperty>
</root>
```
# Использование
Использование `MasterEditorAjaxDropDown` уместно, когда заранее известно, что количество мастеровых объектов небольшое и нет потребности для их выбора поднимать отдельную форму выбора.

## Пример кода
После [генерации кода объектов и форм](flexberry-asp-net-case-plugin.html) появятся структуры вида:

в объектах:
```cs
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailView", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```

на веб-форме (если контрол не [встроен в AGE](a-g-e-applied-controls.html)):
```cs
<ac:MasterEditorAjaxDropDown ID="ctrlTestSpecialControlLink" CssClass="descTxt" runat="server" EnablePostBack="false"/>
```

## Настройки контрола
### Единообразное изменение настроек
Для единообразного изменения настроек реализовано два статических делегата:
# InitDropDownSettings- Делегат для инициализации настроек. Установленные настройки можно переопределять на aspx формах.
# ChangeDropDownSettings- Делегат для смены настроек, при помощи которого можно привести все MasterEditorAjaxDropDown к единообразному виду.

Пример,
Нужно подписаться в Global.asax:
```

            MasterEditorAjaxDropDown.ChangeDropDownSettings = ChangeMasterEditorAjaxDropDownSettings;
```
```

        /// <summary>
        /// Делегат для изменения настроек <see cref="MasterEditorAjaxDropDown"/>.
        /// </summary>
        /// <param name="dropdown">
        /// Экземпляр <see cref="MasterEditorAjaxDropDown"/>, настройки которого требуется изменить.
        /// </param>
        public static void ChangeMasterEditorAjaxDropDownSettings(MasterEditorAjaxDropDown dropdown)
        {
            var langDef = SQLWhereLanguageDef.LanguageDef;
            var type = Type.GetType(dropdown.MasterTypeName);
            
            // Фильтровать все виды отделки только по актуальным значениям
            if (type != null && typeof(ВидОтделки).IsAssignableFrom(type))
            {
                Function actualTypesLimitFunction = langDef.GetFunction(
                    langDef.funcEQ,
                    new VariableDef(langDef.BoolType, Information.ExtractPropertyName<ВидОтделки>(x => x.Актуально)),
                    true);
                dropdown.LimitFunction = dropdown.LimitFunction != null 
                    ? langDef.GetFunction(
                        langDef.funcAND,
                        dropdown.LimitFunction,
                        actualTypesLimitFunction)
                    : actualTypesLimitFunction;
            }
        }
```


## Настройки оформления

# JS API
Для манипуляций с `MasterEditorAjaxDropDown` на стороне клиента следует использовать [JS API](fa_javascript-api.html), который представляет собой jQuery плагин (icsMasterEditorAjaxDropDown).

''Установка значения `MasterEditorAjaxDropDown`''

Для установки значения `MasterEditorAjaxDropDown` можно воспользоваться методом `val`.

Например, если мы хотим по нажатию кнопки `changeMasterDropDownValue` менять значение `MasterEditorAjaxDropDown` `ctrlTestSpecialControlLink` на указанное в контроле `masterDropDownValues`, то мы воспользуемся следующим кодом:
```

$(document).ready(function () {
	$('#<%= changeMasterDropDownValue.ClientID %>').click(function () {
		$('#<%=ctrlTestSpecialControlLink.ClientID%>').icsMasterEditorAjaxDropDown('val', $('#<%=masterDropDownValues.ClientID%>').val());
		return false;
	});
});
```

# Возможные ошибки
Если `MasterEditorAjaxDropDown` отображает не только значение мастерового свойства, а весь мастеровой объект целиком, то вероятнее всего не была закончена [настройка лукапа в представлении во Flexberry Designer](view-edit-form.html).

![](/images/pages/img/page/MasterEditorAjaxDropDown/MasterEditorAjaxDropDownOnFormBad.png)

Проверить это можно, удостоверившись, что у соответствующего объекта данных есть запись вида:
```cs
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailView", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```
