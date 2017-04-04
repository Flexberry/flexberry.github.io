---
title: Иерархический WOLV
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-hierarhy.html
folder: products/flexberry-aspnet/
lang: ru
---

{% include note.html content='Данная статья актуальная для версий шаблона после 2013-10-08' %}

Иерархический WOLV не является отдельным контролом, это обычный [WebObjectListView](fa_web-object-list-view.html), настроенный особым образом.

Иерархический WOLV позволяет отображать иерархические данные.

## Пример

Рассмотрим на примере:
![](/images/pages/img/CaseberryWeb/wolv/WOLVHierarhyDiagramm.png)

Настроим WebObjectListView для отображения `Территорий` в иерархическом виде.

1. Настроим [L-представление](l-view.html) Территории.
2. Настроим WOLV.

### Настройка L-представления

Чтобы WOLV заработал, необходимо, чтобы свойство `Иерархия` попадало в L-представление. По умолчанию в него входит только `Иерархия.Наименование`, необходимо
добавить ссылку на `Иерархию` и снять с неё видимость, чтобы не показывать пользователю на списковой форме ключи (`PrimaryKey`) Территорий.

### Настройка WOLV

Настройка WOLV заключается в указании иерархического свойства.

В код загрузки страницы ТерриторияL в метод `PreLoad()` добавляется следующий код (включение иерархического режима):

```cs
protected override void Preload()
{
    WebObjectListView1.HierarchyProperty = "Иерархия";
}
```


### Результат

В результате, WOLV преобретает следующий вид:

![](/images/pages/img/CaseberryWeb/wolv/WOLVHierarhicalView.png)

В этом виде у WOLV'а (пока) отсутствуют Pager'ы, а также добавляются кнопки для сворачивания и разворачивания иерархии.

{% capture note %}
Обратите внимание на добавившуюся на панель кнопку переключения вида WOLV:

![](/images/pages/img/CaseberryWeb/wolv/WOLVHierarhicalPanel.png)

При нажатии на неё WOLV приобретает обычный вид:

![](/images/pages/img/CaseberryWeb/wolv/WOLVSimpleView.png)
{% endcapture %}
{% include note.html content=note %}

## Отображение родителей, не которых наложено ограничение

Начиная с версии Flexberry ASP.NET 1.4.0 иерархический WOLV поддерживает возможность отображения иерархии в случае наложенного ограничения на родительские
элементы по аналогии с [OLV](object-list-view.html) в Windows-приложениях.

Для включения данного режима требуется свойству `UseLimitFunctionExtension` соответствующего экземпляра WOLV присвоить значение `true`.

Ограничение на родительские элементы может накладываться как в самом WOLV-е, так и в лукапе, если WOLV в иерархическом режиме используется для выбора элемента
на форме, поднимаемой по лукапу (с использованием [WOLVSettApplyer](wolv-sett-applyer.html)).


