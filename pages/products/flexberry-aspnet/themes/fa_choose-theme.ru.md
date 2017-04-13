---
title: Выбор темы Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_choose-theme.html
lang: ru
---

Темы приложения отвечают за его внешний вид. При изменении темы изменяется:

* Цветовая гамма
* Шрифты
* Иконки
* Отступы
* и прочее.

Внешний вид тем можно оценить по следующим снимкам:

### Default

[WOLV](fa_web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/default.png)

[Web-форма редактирования](fa_editform.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-default.png)

### Light

[WOLV](fa_web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/light.png)

[Web-форма редактирования](fa_editform.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-light.png)

### Smart

[WOLV](fa_web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/smart.png)

[Web-форма редактирования](fa_editform.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-smart.png)

### Spring

[WOLV](fa_web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/spring.png)

[Web-форма редактирования](fa_editform.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-spring.png)

## Выбор темы в Web-приложении

Чтобы установить тему по умолчанию в Web-приложении достаточно в файле конфигурации `web.config` вписать название темы

```xml
<pages validateRequest="false" enableSessionState="true" theme="Spring" maintainScrollPositionOnPostBack="true">
```

в свойство theme=.

По умолчанию выбирается тема Spring.

Список тем, поставляемых при генерации приложения:

* Default (несмотря на название она не выбирается по умолчанию)
* Light
* Smart
* Spring (выбирается по умолчанию)

Есть возможность переключать темы в пользовательском режиме. На любой странице в левом нижнем углу есть строка в которой можно указать, какую тему нужно
использовать. Выбранная тема устанавливается для данного конкретного пользователя. Тема, установленная по умолчанию, может меняться только в файле конфигурации. 
