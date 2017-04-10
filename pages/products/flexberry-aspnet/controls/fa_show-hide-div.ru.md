---
title: ShowHideDiv
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_show-hide-div.html
lang: ru
---

`ICSSoft.STORMNET.Web.AjaxControls.ShowHideDiv` - контрол для показа/скрытия контента. 

### Свойства:

| Свойство | Тип | Описание|
|-----------------|--------------------|----------------------------|
| `Content` | `System.Web.UI.ControlCollection` | Контент, который нужно скрывать/показывать|
| `CookieName` | `string` | Возвращает имя для сохранения состояния контрола в cookies (формируется из имени формы и ID контрола)|
| `Title` | `string` | Заголовок|

### Методы:

| Метод | Описание|
|--------|---------|
| `HideDiv()` | Скрыть контент|
| `ShowDiv()` | Показать контент|

## Пример использования:

```xml
...
<%@ Register TagPrefix="ac" Namespace="ICSSoft.STORMNET.Web.AjaxControls" Assembly="ICSSoft.STORMNET.Web.AjaxControls" %>
...
        <ac:ShowHideDiv ID="shd" runat="server">
            <img src="http://flexberry.ru/App_Themes/Blue/images/flex/Logo_h113px.png" />         
        </ac:ShowHideDiv>
...
```

## Вид

### В закрытом состоянии:

![](/images/pages/products/flexberry-aspnet/controls/show-hide-div-collapsed.png)

### В открытом состоянии:

![](/images/pages/products/flexberry-aspnet/controls/show-hide-div-expanded.png)

## Клиентские методы

Скрытие и раскрытие: `setExpand`

```csharp
$('#ИД').showHide('setExpand', false)
```
 
 