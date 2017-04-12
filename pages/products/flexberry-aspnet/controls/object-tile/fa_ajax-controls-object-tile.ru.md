---
title: ObjectTile
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_ajax-controls-object-tile.html
lang: ru
---

Контрол служит для отображения объектов, являющихся `DataObject`, согласно заданному представлению, в виде тайлов на веб-странице. По умолчанию происходит приведение значений атрибутов к строке, для изменения этого нужно использовать свойство `AttributeRenderers`. Реализует интерфейсы `IObjectTile`, `IHavingResources`.

## Интерфейс

### Методы

| Сигнатура | Описание|
|----------------|--------------------|
| `protected override void OnLoad(EventArgs e)` | Перегрузка метода класса `WebControl`, вызывается при загрузке контрола|
| `protected override void RenderContents(HtmlTextWriter writer)` | Перегрузка метода класса `WebControl`, используется для рендеринга контрола при размещении его непосредственно на веб-форме|
| `public string GetMarkup()` | Реализация интерфейса `IObjectTile`. Метод для получения html-разметки контрола в виде строки, используется при необходимости работы непосредственно с разметкой|

### Свойства

| Название | Тип | Описание|
|---------------|-------------------|----------------------------|
| `View` | `ICSSoft.STORMNET.View` | Реализация интерфейса `IObjectTile`. Используется для задания или получения представления, согласно которому отображаются объекты|
| `DataObject` | `ICSSoft.STORMNET.DataObject` | Реализация интерфейса `IObjectTile`. Используется для задания или получения объекта данных, который отображается данным тайлом|
| `AttributeRenderers` | `Dictionary&#60;string, AjaxControls.AttributeRenderer&#62;` | Используется для настройки отображения конкретных атрибутов|
| `Styles` | `IEnumerable&#60string&#62` | Реализация интерфейса `IHavingResources`. Используется для получения коллекции связанных с контролом ресурсов (CSS). Но при вызове метода `OnLoad` ресурсы автоматически добавляются на страницу|

## AttributeRenderers

`AttributeRenderers` - свойство, позволяющее изменять отображение отдельных атрибутов объекта с помощью использования пользовательских функций. Оно представляет собой словарь с ключами типа `string` (имя атрибута) и значениями типа `AttributeRenderer` (пользовательская функция, принимающая `object` и возвращающая `string`). Возвращаемая строка должна содержать html-разметку, которая будет отображена вместо простого приведения значения атрибута к строке. `AttributeRenderer` - делегат, объявленный в `AjaxControls`:

```csharp
public delegate string AttributeRenderer(object attribute);
```

## Пример инициализации

```csharp
//tempCat - экземпляр класса Кошка
ObjectTile tile = new ObjectTile();
tile.View = Information.GetView("КошкаL", typeof(Кошка));
tile.DataObject = tempCat;

tile.AttributeRenderers = new Dictionary<string, AttributeRenderer>();
tile.AttributeRenderers.Add("Кличка", delegate (object val)
                                      {
                                          return string.Format("<b>{0}</b>", val);
                                      });
```

## Вид контрола

![](/images/pages/products/flexberry-aspnet/controls/object-tile.png)
