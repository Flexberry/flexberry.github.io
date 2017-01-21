---
title: Выбор темы Web-приложения
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_choose-theme.html
folder: products/flexberry-aspnet/
lang: ru
---
## Темы
Темы приложения отвечают за его внешний вид. При изменении темы изменяется:

* Цветовая гамма
* Шрифты
* Иконки
* Отступы

и прочее.

Внешний вид тем можно оценить по следующим снимкам:

### Default
[WOLV](web-object-list-view.html):

![](/images/pages/pages/img/WOLV/Themes/default.png)

[Web-форма редактирования](web-edit-form.html):

![](/images/pages/pages/img/WOLV/Themes/EditPageDefault.png)

### Light
[WOLV](web-object-list-view.html):

![](/images/pages/pages/img/WOLV/Themes/Light.png)

[Web-форма редактирования](web-edit-form.html):

![](/images/pages/pages/img/WOLV/Themes/EditPageLight.png)

### Smart
[WOLV](web-object-list-view.html):

![](/images/pages/pages/img/WOLV/Themes/Smart.png)

[Web-форма редактирования](web-edit-form.html):

![](/images/pages/pages/img/WOLV/Themes/EditPageSmart.png)

### Spring
[WOLV](web-object-list-view.html):

![](/images/pages/pages/img/WOLV/Themes/spring.png)

[Web-форма редактирования](web-edit-form.html):

![](/images/pages/pages/img/WOLV/Themes/EditPageSpring.png)

## Выбор темы в Web-приложении
Чтобы установить тему по умолчанию в Web-приложении достаточно в файле конфигурации `web.config` вписать название темы

```xml
<pages validateRequest="false" enableSessionState="true" theme="Spring" maintainScrollPositionOnPostBack="true">
```

в свойство `theme`.

По умолчанию выбирается тема `Spring`.

Список тем, поставляемых при генерации приложения:

* `Default` (несмотря на название она не выбирается по умолчанию)
* `Light`
* `Smart`
* `Spring` (выбирается по умолчанию)

Есть возможность переключать темы в пользовательском режиме. На любой странице в левом нижнем углу есть строка в которой можно указать, какую тему нужно
использовать. Выбранная тема устанавливается для данного конкретного пользователя. Тема, установленная по умолчанию, может меняться только в файле конфигурации. 
