---
title: Выбор темы Web-приложения
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_choose-theme.html

---

* **Платформа:** [FlexberryASP.NET.](fa_flexberry-a-s-p-n-e-t.html)
* **Компонент:** [Темы оформления и разметка Flexberry ASP.NET](fa_flexberry-asp-net-themes.html)
* **Предназначение:** изменение внешнего вида web-приложения.

## Темы
Темы приложения отвечают за его внешний вид. При изменении темы изменяется:

* Цветовая гамма
* Шрифты
* Иконки
* Отступы
* и прочее.

Внешний вид тем можно оценить по следующим снимкам:

### Default

[WOLV](web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/default.png)

[Web-форма редактирования](fa_web-edit-form.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-default.png)

### Light
[WOLV](web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/light.png)

[Web-форма редактирования](fa_web-edit-form.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-light.png)

### Smart
[WOLV](web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/smart.png)

[Web-форма редактирования](fa_web-edit-form.html):

![](/images/pages/products/flexberry-aspnet/themes/edit-page-smart.png)

### Spring
[WOLV](web-object-list-view.html):

![](/images/pages/products/flexberry-aspnet/themes/spring.png)

[Web-форма редактирования](fa_web-edit-form.html):

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

## Откуда ссылаются на эту страницу

* [Создание приложения при помощи Flexberry. Содержание.](fd_flexberry.html)
* [Темы оформления и разметка Flexberry ASP.NET](fa_flexberry-asp-net-themes.html)
* [Создание приложения при помощи Flexberry. Этап создания прототипа.](fd_prototype-creation.html)
* [Компоненты технологии CASEBERRY]()
* [Структура тем web-шаблона](fa_theme-structure.html)
* [Web-форма редактирования](fa_web-edit-form.html)
* [WebObjectListView (WOLV)]()

## Куда ссылается эта страница

* [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* [Темы оформления и разметка Flexberry ASP.NET](fa_flexberry-asp-net-themes.html)
* [Web-форма редактирования](fa_web-edit-form.html)
* [WebObjectListView (WOLV)]()