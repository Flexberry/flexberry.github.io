---
title: MasterEditorAjaxDropDown
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, JavaScript API
toc: true
permalink: ru/fa_master-editor-ajax-dropdown.html
lang: ru
---

`MasterEditorAjaxDropDown` - это [web-контрол](fa_web-controls.html), который позволяет просматривать список доступных мастеровых объектов в виде выпадающего списка.

Ниже показан пример отображения данного контрола на веб-форме.

![](/images/pages/products/flexberry-aspnet/on-form.png)

## Подключение

Для того чтобы использовать `MasterEditorAjaxDropDown` на форме редактирования, необходимо на [форме редактирования представления во Flexberry Designer выбрать тип лукапа `combo`](fd_view-edit-form.html), а в качестве свойства мастера указать предпочитаемое для отображения свойство объекта мастера.

Чтобы [встроить данный контрол в AGE](fa_age-applied-controls.html), требуется произвести настройки? аналогичные [этим](fa_age-applied-controls.html). При этом в [WebControlProvider.xml](fa_web-control-provider.html) появится запись вида:

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

## Использование

Использование `MasterEditorAjaxDropDown` уместно, когда заранее известно, что количество мастеровых объектов небольшое и нет потребности для их выбора поднимать отдельную форму выбора.

### Пример кода

После [генерации кода объектов и форм](fa_asp-net-generator.html) появятся структуры вида:

в объектах:

```csharp
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailView", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```

на веб-форме (если контрол не [встроен в AGE](fa_age-applied-controls.html)):

```csharp
<ac:MasterEditorAjaxDropDown ID="ctrlTestSpecialControlLink" CssClass="descTxt" runat="server" EnablePostBack="false"/>
```

### Настройки контрола

#### Связывание контролов редактирования мастеров

О том, как связывать web-контролы редактирования мастеров (как, например, `MasterEditorAjaxDropDown`), описано в [статье Связывание контролов редактирования мастеров](fa_linked-master-editors.html).

#### Единообразное изменение настроек

Для единообразного изменения настроек реализовано два статических делегата:

1. `InitDropDownSettings` - Делегат для инициализации настроек. Установленные настройки можно переопределять на aspx формах.
2. `ChangeDropDownSettings` - Делегат для смены настроек, при помощи которого можно привести все MasterEditorAjaxDropDown к единообразному виду.

Пример: нужно подписаться в `Global.asax`:

```csharp
MasterEditorAjaxDropDown.ChangeDropDownSettings = ChangeMasterEditorAjaxDropDownSettings;
```

```csharp
/// <summary>
/// Делегат для изменения настроек <see cref="MasterEditorAjaxDropDown"/>.
/// </summary>
/// <param name="dropdown">
/// Экземпляр <see cref="MasterEditorAjaxDropDown"/>, настройки которого требуется изменить.
/// </param>
public static void ChangeMasterEditorAjaxDropDownSettings(MasterEditorAjaxDropDown dropdown)
{
    var type = Type.GetType(dropdown.MasterTypeName);
    
    // Фильтровать все виды отделки только по актуальным значениям
    if (type != null && typeof(ВидОтделки).IsAssignableFrom(type))
    {
        Function actualTypesLimitFunction = FunctionBuilder.BuildEquals<ВидОтделки>(x => x.Актуально, true);
        dropdown.LimitFunction = dropdown.LimitFunction != null 
            ? FunctionBuilder.BuildAnd(                
                dropdown.LimitFunction,
                actualTypesLimitFunction)
            : actualTypesLimitFunction;
    }
}
```

## JS API

Для манипуляций с `MasterEditorAjaxDropDown` на стороне клиента следует использовать [JS API](fa_javascript-api.html), который представляет собой jQuery плагин (`icsMasterEditorAjaxDropDown`).

### Установка значения `MasterEditorAjaxDropDown`

Для установки значения `MasterEditorAjaxDropDown` можно воспользоваться методом `val`.

Например, если по нажатию кнопки `changeMasterDropDownValue` требуется менять значение `MasterEditorAjaxDropDown` `ctrlTestSpecialControlLink` на указанное в контроле `masterDropDownValues`, то мы воспользуемся следующим кодом:

```javascript
$(document).ready(function () {
	$('#<%= changeMasterDropDownValue.ClientID %>').click(function () {
		$('#<%=ctrlTestSpecialControlLink.ClientID%>').icsMasterEditorAjaxDropDown('val', $('#<%=masterDropDownValues.ClientID%>').val());
		return false;
	});
});
```

## Возможные ошибки

Если `MasterEditorAjaxDropDown` отображает не только значение мастерового свойства, а весь мастеровой объект целиком, то вероятнее всего не была закончена [настройка лукапа в представлении во Flexberry Designer](fd_view-edit-form.html).

![](/images/pages/products/flexberry-aspnet/on-form-bad.png)

Проверить это можно, удостоверившись, что у соответствующего объекта данных есть запись вида:

```csharp
[MasterViewDefineAttribute("TestMasterEditorAjaxDropDownDetailView", "TestSpecialControlLink", ICSSoft.STORMNET.LookupTypeEnum.Combo, "", "PoleString")]
```
