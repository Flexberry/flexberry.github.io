---
title: Иерархический WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-hierarhy.html
lang: ru
---

Иерархический WOLV не является отдельным контролом, это обычный [WebObjectListView](fa_web-object-list-view.html), настроенный особым образом.

Иерархический WOLV позволяет отображать иерархические данные.

## Пример

Пример диаграммы:

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhy-diagramm.png)

### Настройка иерархического WOLV

Отображение списка `Территории` в иерархическом виде.

1. Настроить [L-представление](fd_l-view.html) Территории.
2. Настроть WOLV.

#### Настройка L-представления

Чтобы WOLV заработал, необходимо, чтобы свойство `Иерархия` попадало в L-представление. По умолчанию в него входит только `Иерархия.Наименование`, необходимо добавить ссылку на `Иерархию` и снять с неё видимость, чтобы не показывать пользователю на списковой форме ключи (`PrimaryKey`) Территорий.

#### Настройка WOLV

Настройка WOLV заключается в указании иерархического свойства. В код загрузки страницы `ТерриторияL` в метод `PreLoad()` добавляется следующий код (включение иерархического режима):

```csharp
protected override void Preload()
{
    WebObjectListView1.HierarchyProperty = "Иерархия";
}
```

#### Результат

На панель добавлена кнопка переключения вида WOLV: ![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhical-panel.png)

Если кнопку нажать, то WOLV преобретает следующий вид:

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-hierarhical-view.png)

В этом виде у WOLV'а (пока) отсутствуют Pager'ы, а также добавляются кнопки для сворачивания и разворачивания иерархии.

При повторном нажатии на кнопки WOLV приобретает обычный вид:

![](/images/pages/products/flexberry-aspnet/controls/wolv/wolv-simple-view.png)

## Отображение родителей, не которых наложено ограничение

Иерархический WOLV поддерживает возможность отображения иерархии в случае наложенного ограничения на родительские элементы по аналогии с [OLV](fw_objectlistview.html) в Windows-приложениях.

Для включения данного режима требуется свойству `UseLimitFunctionExtension` соответствующего экземпляра WOLV присвоить значение `true`.

Ограничение на родительские элементы может накладываться как в самом WOLV-е, так и в лукапе, если WOLV в иерархическом режиме используется для выбора элемента на форме, поднимаемой по лукапу (с использованием [WOLVSettApplyer](fa_wolv-sett-applyer.html)).


