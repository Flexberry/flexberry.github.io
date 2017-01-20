---
title: WebErrorBox и ErrorForm
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/web-error-box-and--error-form.html
folder: product--folder
lang: ru
---

# Введение
ErrorForm - aspx страница, которая показывает необработанное исключение, она вызывается в Global.asax;
<br />
WebErrorBox - контрол, который использовался для показа исключения.''' Внимание! '''он больше не поддерживается и использовать его не следует. Вместо него нужно использовать WebTools.WebErrorBoxRiser;

# Как нужно показывать исключение
Для того, чтобы показать красивое окно с обработанным исключением нужно вызывать метод WebErrorBox.Rise(exception, showDetails, errorCaption).
```cs
try
{
    // Делаем что-нибудь небезопасное.
}
catch (Exception ex)
{
    WebErrorBoxRiser.Rise(ex, false, "Произошла ошибка при обработке формы");
}
```

# Когда показывается ErrorForm
ErrorForm показывает, когда происходит необработанное исключение. На ней показывается случившееся исключение.
<br />
'''Внимание:''' Возможен случай, когда в Page_Load страницы вы обработали исключение при помощи WebErrorBoxRiser, но после этого будут вызываться Page_Load контролов, которые, в свою очередь, создадут исключительную ситуацию. Таким образом, окно с ошибкой не появится, а появится ErrorForm, который отобразит и исключение из контрола и то исключение, которое вы обработали в WebErrorBoxRiser.


# Как работает WebErrorBoxRiser
При вызове метода ```
WebErrorBoxRiser.Rise(exception, showDetails, errorCaption)
``` исключение добавляется в 
```
HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions]
```
Потом, при помощи [ ICSSoft.STORMNET.Web.HttpModules.ResponseFilterModule](response-filter-module.html), вызывается Render у WebErrorBox, который генерирует Html и отображает на страницу.
Для того, чтобы получить все исключения, которые были добавлены при помощи WebErrorBox нужно воспользоваться 
```
(List<WebErrorBoxRiser>) HttpContext.Current.Items[WebParamController.PageFilter_WebErrorBoxExceptions];
```
[ResponseFilterModule.ashx]

# Показ дополонительной информации об исключении
Для того, чтобы увидеть StackTrace на странице нужно добавить в web.config в "appSettings"
```

<add key="ShowErrorDetails" value="true" />
```

