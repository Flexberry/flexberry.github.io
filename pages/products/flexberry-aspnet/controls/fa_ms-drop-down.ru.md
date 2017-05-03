---
title: MsDropDown
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ms-drop-down.html
lang: ru
---

`MsDropDown` - это [web-контрол](fa_web-controls.html), который позволяет просматривать список доступных мастеровых объектов в виде выпадающего списка (аналогично [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) с иконками для элементов списка.

Ниже показан пример отображения данного контрола на веб-форме.

![](/images/pages/products/flexberry-aspnet/controls/ms-drop-down.png)

### Публичные свойства MsDropDown

|Свойство|Описание|
|-------------|---------------------------------|
|`Items`|Список элементов|
|`SelectedItem`|Выбранный элемент|
|`ReadOnly`|Флаг, отвечающий за разрешение/запрет манипуляций с контролом|

### MsDropDownItem и его свойства

Каждый `Item` (элемент списка) является экземпляром класса `MsDropDownItem`.

|Свойство|Описание|
|-------------|---------------------------------|
|`Text`|Основной текст, по которому можно идентифицировать объект в отображаемом списке|
|`ImagePath`|Путь до картинки, которую следует отображать рядом с текстом|
|`Description`|Описание, дополнительный текст, который будет расположен рядом с основным|

## Подключение

Подключение возможно только вручную в коде. Для этого в разметке страницы .aspx надо добавить соответствующий контрол:

```xml
<ac:MsDropDown ID="<ID контрола>" ReadOnly="<False/True>" runat="server"/>
```

В коде обработчика страницы (в методе `Page_Load`) сформировать список элементов:

```csharp
var <item> = new MsDropDownItem();
<item>.Text = "<Текст объекта>";
<item>.ImagePath = "<Путь до иконки>";
<item>.Description = "<Описание объекта>";

<контрол>.Items.Add(<item>);
```

## Использование

Использование `MsDropDown` (как и [MasterEditorAjaxDropDown](fa_master-editor-ajax-dropdown.html)) уместно, когда заранее известно, что количество мастеровых объектов небольшое и нет потребности для их выбора поднимать отдельную форму выбора.

### Пример кода

Для того чтобы получить список, продемонстрированный на картинке выше, следует сделать следующее:

Код .aspx-страницы с возможностью менять состояние `ReadOnly` контрола `MsDropDown`:

```xml
<asp:Content ID="Content2" ContentPlaceHolderID="TestContentPlaceHolder" runat="server">
    <%--CheckBox для возможности менять состояние контрола--%>
    <div class="clearfix">
        <asp:Label CssClass="descLbl" ID="ctrlReadOnlyEnabledLabel" runat="server" Text="Только для чтения" EnableViewState="False">
        </asp:Label>
        <asp:CheckBox ID="ctrlReadOnlyEnabled" AutoPostBack="True" runat="server" OnCheckedChanged="ctrlReadOnlyEnabled_OnCheckedChanged"/>
    </div>
    <%--Собственно добавление MsDropDown в разметку страницы--%>
    <div class="clearfix">
        <asp:Label CssClass="descLbl" ID="ctrlTestMsDropDownLabel" runat="server" Text="Список" EnableViewState="False">
        </asp:Label>
        <ac:MsDropDown ID="ctrlTestMsDropDown" ReadOnly="False" runat="server"/>
    </div>
</asp:Content>
```

Формирование списка элементов и обработчик состояния `MsDropDown` в зависимости от состояния `CheckBox` (Checked/Unchecked):

```csharp
/// <summary>
/// Формирование списка элементов для <see cref="MsDropDown"/> при загрузке контрола.
/// </summary>
/// <param name="e">Параметры события.</param> 
protected void Page_Load(object sender, EventArgs e)
{
    // Создание списка элементов програмно.
    // Text - сновной текст, по которому можно идентифицировать объект в отображаемом списке.
    // ImagePath - путь до картинки, которую следует отображать рядом с текстом.
    // Description - дополнительный текст, который будет расположен рядом с основным.
    var item1 = new MsDropDownItem();
    item1.Text = "Первый";
    item1.ImagePath = "/Images/cat.png";
    item1.Description = "Элемент списка";

    var item2 = new MsDropDownItem();
    item2.Text = "Второй";
    item2.ImagePath = "/Images/dog.png";
    item2.Description = "Элемент списка";

    var item3 = new MsDropDownItem();
    item3.Text = "Третий";
    item3.ImagePath = "/Images/pig.png";
    item3.Description = "Элемент списка";

    // Добавление созданных элементов в контрол.
    ctrlTestMsDropDown.Items.Add(item1);
    ctrlTestMsDropDown.Items.Add(item2);
    ctrlTestMsDropDown.Items.Add(item3);
}

/// <summary>
/// Определение состояния контрола: доступен для чтения/ не доступен для чтения.
/// Переводится в состояние ReadOnly при активном checkBox "Только для чтения".
/// </summary>
/// <param name="sender">Параметры события.</param>
/// <param name="e">Параметры события.</param>
protected void ctrlReadOnlyEnabled_OnCheckedChanged(object sender, EventArgs e)
{
    ctrlTestMsDropDown.ReadOnly = ctrlReadOnlyEnabled.Checked;
}
```
 