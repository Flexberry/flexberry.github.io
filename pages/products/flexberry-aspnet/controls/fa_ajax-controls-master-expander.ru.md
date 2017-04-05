---
title: MasterExpander
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_ajax-controls-master-expander.html
lang: ru
---

Веб-контрол для отображения объектов разворачиваемыми группами, сформированными по значению мастера. Объекты без мастера помещаются в категорию `Остальное`. Для отображения каждой группы используется контрол [ObjecTileView](fa_ajax-controls-object-tile-view.html).

## Интерфейс

### Методы

| Сигнатура | Описание|
|---------------------------------------|----------------------------------------|
| `protected override void OnLoad(EventArgs e)` | Перегрузка метода класса `WebControl`, вызывается при загрузке контрола|
| `protected override void RenderContents(HtmlTextWriter writer)` | Перегрузка метода класса `WebControl`, используется для рендеринга контрола при размещении на веб-форме|
| `protected override IEnumerable<SсriptDesсriptor> GetSсriptDesсriptors()` | Перегрузка метода класса `SсriptControl`. Метод для получения дескрипторов скриптов, используемых контролом|
| `protected override IEnumerable<SсriptReference> GetSсriptReferences()` | Перегрузка метода класса `SсriptControl`. Метод для получения ссылок на скриптовые ресурсы, используемые контролом.|
| `public static string GetMarkup(string guid, int pageNum)` | Статический метод, возвращающий строку с разметкой нужной страницы. Вызывается веб-сервисом `MasterExpanderService` при обработке ajax-запросов. Настройки берутся из сессии по `guid`'у|
| `public static string GetTileViewMarkup(string guid, int index)` | Статический метод, возвращающий строку с разметкой конкретного `ObjectTileView`. Вызывается веб-сервисом `MasterExpanderService` при обработке ajax-запросов разворачивания какой-либо категории. Настройки берутся из сессии по `guid`'у. Внутри метода происходит конструирование экземпляра `ObjectTileView` и получение его разметки|

### Свойства

Свойства, которые по именам совпадают со свойствами `ObjecTileView`, просто передаются конкретному экземпляру этого контрола при его создании, про них можно почитать [в статье ObjectTileView](fa_ajax-controls-object-tile-view.html). Значение `TilesPerPage` будет записано в `ItemsPerPage` каждого `ObjectTileView`. Остальные свойства перечислены ниже:

| Название | Тип | Описание|
|--------------|------------------------|------------------------|
| `MastersPerPage` | `uint` | Количество категорий на одной странице `MasterExpander`|
| `MasterName` | `string` | Имя атрибута объектов, **хранящего мастера**, используемого для группировки|
| `MasterAttributeName` | `string` | Имя атрибута **мастера**, по которому производится разделение. Если, например, группировать объекты по названию темы, то `MasterName` будет равен `Тема`, а `MasterAttributeName` - `Название`|
| `SectionHeaderUserContentGenerator` | `ICSSoft.STORMNET.Web.AjaxControls.<br>MasterExpanderUserContentDelegate` | Используется для задания функции, которая будет генерировать пользовательское содержимое в заголовках каждой категории. Подробнее о типе делегата MasterExpanderUserContentDelegate написано ниже.|

### MasterExpanderUserContentDelegate

```csharp
    public delegate string MasterExpanderUserContentDelegate<in TData>(
        MasterExpander.MasterExpanderSettings settings,
        TData data);
```

Делегат, который предполагается использовать для передачи пользовательских функции генерации содержимого в контрол MasterExpander. В качестве параметров функция должна принимать настройки MasterExpander (типа MasterExpander.MasterExpanderSettings) и какие-нибудь данные, зависящие от контекста использования. Ниже приведен пример реализации функции для добавления разметки в заголовок категории MasterExpander, где в качестве "данных" выступает индекс категории, соответственно, `TData` конкретизируется в `int`.

```csharp
protected static string GenerateSectionHeaderContent(
    MasterExpander.MasterExpanderSettings settings,
    int index)
{
    return string.Format("<p>Категория №{0} из {1}</p>", index, 
    settings.MastersValues.Count);
}
```

### Web.config

Для включения веб-сервиса `MasterExpanderService.asmx` необходимо добавить запись в `Web.config`:

#### Для IIS6

```xml
<configuration>
...
<system.web>
    ...
    <httpHandlers>
        ...
        <add verb="*" path="MasterExpanderService.asmx" 
        validate="false" type="AjaxControls.MasterExpanderHandlerFactory" />
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
        <add name="MasterExpanderHandler" path="MasterExpanderService.asmx"
        verb="*" type="AjaxControls.MasterExpanderHandlerFactory" 
        resourceType="Unspecified" preCondition="integratedMode" />
        ...  
    </handlers>
    ...
  </system.webServer>
  ...
</configuration>
```

## Вид контрола

![](/images/pages/products/flexberry-aspnet/controls/master-expander.png)

## CSS

Для изменения вида контрола можно переопределить CSS-атрибуты для классов:

* Внешний див контрола: `div.me-masterName-expander`
* Заголовок категории: `div.me-section-header`
* Разворачивающийся див (содержащий `ObjectTileView`): `div.me-expandable`
* Область выбора страницы: `div.me-page-select-area`
* Кнопка выбора страницы: `a.me-page-button`
* Кнопка выбранной страницы: `a.me-selected-page-button`
* Свойства для `ObjectTileView` также можно посмотреть в соответствующей [статье ObjectTileView](fa_ajax-controls-object-tile-view.html). 
 