---
title: ObjectTileView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ajax-controls-object-tile-view.html
lang: ru
---

Для отображения каждого конкретного объекта служит контрол, реализующий интерфейс `IObjectTile`. Тип тайлов задается в свойстве TileType. По умолчанию в качестве тайлов используется [AjaxControls.ObjectTile](fa_ajax-controls-object-tile.html).

## Интерфейс

### Методы

| Сигнатура | Описание|
|-----------------------|------------------------------|-------------------------------|
| `protected override void OnLoad(EventArgs e)` | Перегрузка метода класса `WebControl`, вызывается при загрузке контрола|
| `protected override void RenderContents(HtmlTextWriter writer)` | Перегрузка метода класса `WebControl`, используется для рендеринга контрола при размещении его непосредственно на веб-форме|
| `protected override IEnumerable<SсriptDesсriptor> GetSсriptDesсriptors()` | Перегрузка метода класса `SсriptControl`. Метод для получения дескрипторов скриптов, используемых контролом|
| `protected override IEnumerable<SсriptReference> GetSсriptReferences()` | Перегрузка метода класса `SсriptControl`. Метод для получения ссылок на скриптовые ресурсы, используемые контролом.|
| `public string GetInitiaMarkup()` | Метод, возвращающий строку с начальной html-разметкой контрола (содержащей скрипт для ajax-получения первой страницы)|
| `public static string GetMarkup(int pageNum, string guid)` | Статический метод, возвращающий строку с разметкой нужной страницы. Вызывается веб-сервисом `ObjectTileViewService` при обработке ajax-запросов. Настройки берутся из сессии по `guid`'у|

### Свойства

| Название | Тип | Описание|
|-------------------------|---------------------------|----------------------------------|
| `View` | `ICSSoft.STORMNET.View` | Используется для задания или получения представления, согласно которому отображаются объекты|
| `Type` | `System.Type` | Используется для задания или получения типа объектов, которые нужно выгрузить и отобразить|
| `ItemsPerPage` | `uint` | Количество тайлов на странице (если `0`, то пейджинг отключен)|
| `LimitFunction` | `ICSSoft.STORMNET.FunctionalLanguage.Function` | Ограничение на загружаемые объекты|
| `ColumnsSorting` | `List&#60ColumnsSortDef&#62` | Список записей о колонках, по которым нужно производить сортировку выводимых объектов, в порядке их приоритета|
| `Styles` | `IEnumerable&#60string&#62` | Реализация интерфейса `IHavingResources`. Используется для получения коллекции связанных с контролом ресурсов (CSS). Но при вызове метода `OnLoad` ресурсы автоматически добавляются на страницу|
| `TileType` | `System.Type` | Класс контрола, который будет использоваться в качестве тайла (отображать один объект). Должен реализовывать `AjaxControls.IObjectTile`. Чтобы в head страницы подключить стилевые файлы, используемые контролом, можно реализовать интерфейс `AjaxConrols.IHavingResources`. По умолчанию установлен класс [AjaxControls.ObjectTile](fa_ajax-controls-object-tile.html)|
| `TileProperties` | `Dictionary<string, object>` | Свойство, позволяющее задавать значения произвольных свойств отображаемых тайлов. Подробнее описывается ниже|

## TileProperties

`TileProperties` - словарь с ключами типа `string` (имя свойства) и значениями типа `object` (значение свойства). Во-первых, рекомендуется задавать имена свойств с помощью определения строковых констант по причине того, что при задании несуществующего имени (или при допущении ошибки в нем) контрол будет нормально работать за исключением того, что значение свойства не будет присвоено. Во-вторых, следует внимательно относиться к типу передаваемого значения, который должен быть наследником типа соответствующего свойства или являться им.

### Пример использования свойства `TileProperties`

Для класса тайлов `ObjectTile` задается свойство `AttributeRenderers`.

```csharp
//expander - контрол класса MasterExpander
Dictionary<string, AttributeRenderer> renderers = 
                new Dictionary<string, AttributeRenderer>();
renderers.Add("Кличка", delegate (object val)
                        {
                            return string.Format("<b>{0}</b>", val);
                        });
expander.TileProperties.Add(ObjectTile.AttributeRenderersPropertyName, renderers);
```

## Web.config

Для включения веб-сервиса `ObjectTileViewService.asmx` необходимо добавить запись в `Web.config`:

#### Для IIS6

```xml
<configuration>
...
<system.web>
    ...
    <httpHandlers>
        ...
        <add verb="*" path="ObjectTileViewService.asmx" validate="false" 
          type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.ObjectTileViewHandlerFactory" />
        ...  
    </httpHandlers>
    ...
  </system.web>
  ...
</configuration>
```

#### Для IIS7

```xml
<configuration>
...
<system.webServer>
    ...
    <handlers>
        ...
        <add name="ObjectTileViewHandler" path="ObjectTileViewService.asmx" 
          verb="*" type="ICSSoft.STORMNET.Web.AjaxControls.HandlerFactories.ObjectTileViewHandlerFactory" 
          resourceType="Unspecified" preCondition="integratedMode" />
        ...  
    </handlers>
    ...
  </system.webServer>
  ...
</configuration>
```

## Вид контрола

![](/images/pages/products/flexberry-aspnet/controls/object-tile-view.png)

## CSS

Для изменения вида контрола можно переопределить CSS-атрибуты для классов:

* Внешний див контрола: `div.otv-tile-view`
* Область отображения тайлов: `div.otv-tiles-area`
* Область выбора страницы: `div.otv-page-select-area`
* Кнопка выбора страницы: `a.otv-page-button`
* Кнопка выбранной страницы: `a.otv-selected-page-button`

## Пример обособленного использования ObjectTileView

Для статического добавления контрола в aspx-разметку прежде всего нужно зарегистрировать префикс пространства имен (или сразу тег) контрола, например:

```xml
...
<%@ Register TagPrefix="ac" Namespace="ICSSoft.STORMNET.Web.AjaxControls" Assembly="ICSSoft.STORMNET.Web.AjaxControls" %>
...
```

Для работы контролу ObjectTileView необходимо присутствие на странице ScriptManager'a:

```xml
...
<asp:ScriptManager runat="server"/>
...
```

В нужное место добавляем сам контрол:

```xml
...
<ac:ObjectTileView runat="server" ID="myTileView"/>
...
```

Для инициализации контрола необходимо при загрузке страницы (в методе `Page_Load`) выполнить примерно следующее:

```csharp
// Задаем представление, по которому из БД будут загружаться и отображаться объекты
myTileView.View = Information.GetView("DataTypeL", typeof(DataType));
// Задаем класс объектов, которые будут отображаться в контроле
myTileView.Type = typeof(DataType);
```

Для работы контрола необходима подключенная библиотека `jQuery`. Остальные свойства задаются опционально и описаны выше. Если нужно кастомизировать отображение объектов в контроле, см. свойство TileType.
 