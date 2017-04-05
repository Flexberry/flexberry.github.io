---
title: Отключение пэйджинга
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-disable-paging.html
folder: products/flexberry-aspnet/controls/wolv
lang: ru
---

По умолчанию данные для WebObjectListView загружаются порциями (постранично),
однако предусмотрен и режим отображения данных с отключенным пэйджингом, в котором все доступные данные
будут загруджаться не постранично, а все сразу на единственной странице, при этом пейджеры вверху и внизу списка будут отключены.

![](/images/pages/products/flexberry-aspnet/controls/wolv/disable-paging1.png)

## Отключение пэйджинга из разметки страницы
Пейджинг можно отключить в разметке страницы, проставив у контрола настройку AllowPaging="False".

```xml
<ac:WebObjectListView ID="WebObjectListView1" runat="server" Visible="true" AllowPaging="False" />
```

## Отключение пейджинга из behind-кода страницы
Пейджинг можно отключить и behind-кода страницы, так же проставив у контрола настройку AllowPaging=false.

```csharp
/// <summary>
/// Вызывается самым первым в Page_Load.
/// </summary>
protected override void Preload()
{
    WebObjectListView1.AllowPaging = false;
}
```
