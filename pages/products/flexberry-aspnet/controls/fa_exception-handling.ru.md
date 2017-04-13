---
title: WebErrorBoxRiser и ErrorForm
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_exception-handling.html
lang: ru
---

`ErrorForm` - aspx-страница, которая показывает необработанное исключение, она вызывается в `Global.asax`.

`WebErrorBoxRiser` - контрол, который используется для показа исключения.

## Отображение исключения

Для того чтобы показать красивое окно с обработанным исключением нужно вызывать метод `WebErrorBox.Rise(exception, showDetails, errorCaption)`.

Пример:

```csharp
try
{
    // Делаем что-нибудь небезопасное.
}
catch (Exception ex)
{
    WebErrorBoxRiser.Rise(ex, false, "Произошла ошибка при обработке формы");
}
```

## Отображение ErrorForm

`ErrorForm` отображается, когда происходит необработанное исключение. На ней показывается случившееся исключение.

{% include note.html content="Возможен случай, когда в `Page_Load` страницы обработано исключение при помощи `WebErrorBoxRiser`, но после этого будут вызываться `Page_Load` контролы, которые, в свою очередь, создадут исключительную ситуацию. Таким образом, окно с ошибкой не появится, а появится `ErrorForm`, которая отобразит и исключение из контрола и то исключение, которое обработано в `WebErrorBoxRiser`." %}

## Отображение WebErrorBoxRiser

При вызове метода 

```csharp
WebErrorBoxRiser.Rise(exception, showDetails, errorCaption)
```
исключение добавляется в

```csharp
HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions]
```

Затем, при помощи [ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule](fa_response-filter-module.html), вызывается `Render` у `WebErrorBoxRiser`, который генерирует Html и отображает на страницу.
Для того чтобы получить все исключения, которые были добавлены при помощи `WebErrorBoxRiser` следует воспользоваться

```csharp
(List<WebErrorBoxRiser>) HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions];
```

## Показ дополонительной информации об исключении

Для того чтобы увидеть `StackTrace` на странице следует добавить в `web.config` в `<appSettings>`

```xml
<add key="ShowErrorDetails" value="true" />
```
