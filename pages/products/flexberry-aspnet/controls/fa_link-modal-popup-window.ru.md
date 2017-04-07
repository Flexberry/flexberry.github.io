---
title: LinkModalPopupWindow
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_link-modal-popup-window.html
lang: ru
---

С помощью данного контрола можно поднять модальное окно и открыть в нём страницу.

## Подключение

Контрол можно подключить двумя способами:

* расположить на странице
* добавить динамически.

## Использование

Расположить контрол на странице и настроить:

```xml
<ac:LinkModalPopupWindow ID="myLink" EnableViewState="false" runat="server" URL="homework.aspx" Enabled="true" Text="Ссылка" WindowTitle="Домашнее задание" />
```

Можно этот контрол добавить динамически или даже отрендерить вручную.
Или получить, используя следующую функцию:

```csharp
LinkModalPopupWindow.GetHtml(this.ClientID + "_lmpw" + j.ToString(), "Подробнее", "TemaPlanLessonE.aspx?LookUp=true&amp;pk={0}", "Тематический план урока", 640, 480, true)
```

### Настройки контрола

| Параметры | Описание|
|---------------|--------------------|
| ClientId | ID-контрола|
| Text | Текст ссылки|
| Url | Адрес, который нужно открывать|
| Enabled | Доступна ли ссылка|
| WindowTitle | Заголовок окна|
| ATitle | Tooltip для ссылки|

### Настройки оформления

| Параметры | Описание|
|-----------------------|----------------------|
| WindowWidth | Ширина окна|
| WindowHeight | Высота окна|-
| CssStyle | Стиль ссылки|-
| CssClass | CSS-Класс ссылки| 
 