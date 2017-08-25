---
title: WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_web-object-list-view.html
lang: ru
---

**WebObjectListView** (далее, `WOLV`) - веб-контрол, который используется для отображения списка объектов.

## Внешний вид WOLV

### Внешний вид WOLV при использовании различных тем

Внешний вид WOLV при использовании различных тем можно посмотреть в [статье Выбор темы Web-приложения](fa_choose-theme.html).

### Stylization

Основная статья про [cтилизацию WebObjectListView](fa_wolv-stylization.html).

### CSS-классы

Основная статья о [CSS-классах WebObjectListView](fa_wolv-css.html).

### Пейджинг

Основная статья про [пейджинг WebObjectListView](fa_wolv-paging.html).

### Фиксация шапки списка

Зафиксировать шапку списка можно, добавив на страницу клиентский скрипт:

```javascript
$('.wolv-caption-wrapper').sticky( { topSpacing : <x> } );
```

где `x` - высота от верхнего края в пикселях.

### Прокрутка списка `ScrollToSelectedObject`

После совершения операции с элементом список прокручивается к выделенному объекту данных.  
Настройку удобно применять к большим спискам, когда выделенный объект по умолчанию не всегда попадает в зону видимости пользователем.

Включить настройку:

```csharp
WebObjectListView1.Operations.ScrollToSelectedObject = true;
```

### Сохранение состояния прокрутки списка

Включается с помощью настроек:

```csharp
WebObjectListView1.Operations.SaveHorizontalScroll = true;
WebObjectListView1.Operations.SaveVerticalScroll = true;
```

Позиции скроллов списка будут восстанавливаться всегда, в т.ч. при переходах по страницам и сортировке в `WOLV`.

### Отображение иерархических данных

Основная статья про [иерархический WebObjectListView](fa_wolv-hierarhy.html).

### Группировка данных

Основная статья про [группировку данных в WebObjectListView](fa_wolv-group-mode.html).

### Загрузка данных и EmptyControl

Основная статья о [загрузке данных и EmptyControl в WebObjectListView](fa_wolv-empty-control.html).

## Операции

Основная статья об [операциях WebObjectListView](fa_wolv-operations.html).

## События

Основная статья про [события WebObjectListView](fa_wolv-events.html).

## Взаимодействие WebObjectListView и формы редактирования

### Отображение на одной странице списковой формы и формы редактирования

Отображение на одной странице списковой формы и формы редактирования описано в соответствующей [статье](fa_editor-in-frame.html).

### Создание нового объекта на основе (прототипизация)

Особенности использования прототипизицаии в `WebObjectListView` описано в [статье Прототипизация для Flexberry ASP.NET](fa_web-data-object-prototyping.html).

### Варианты открытия формы редактирования при создании\редактировании объекта

Основная статья про [открытие формы редактирования](fa_wolv-edit-form.html).

## Фильтры

Основная статья про [фильтры WebObjectListView](fa_wolv-filters.html).

## Поиск

Основная статья про [поиск в WebObjectListView](fa_wolv-search.html).

## Печать

Существуют настройки WOLV, позволяющие напечатать список или часть списка. Описание настройки печати представлено в статье [Печать списка](fa_print-wolv.html).

## Пользовательские настройки WebObjectListView

Инструмент настройки для WebObjectListView описан в [статье WolvSettApplyer](fa_wolv-sett-applyer.html).

### Настройка отображения столбцов

Основная статья про [настройку отображения столбцов WebObjectListView](fa_wolv-columns.html).

### Настройка сортировки

Настройка сортировки списка описана в [статье Сортировка для WebObjectListView](fa_list-sort.html).

### Возможность изменения ширины столбцов

Пользователь может самостоятельно изменить ширину каждого столбца списка, если установлено свойство `Operations.AllowColumnResizing = true`. Настроенные размеры столбцов сохраняются для каждого пользователя на сервере и восстанавливаются при следующей загрузке страницы.  
Установленная пользователем ширина столбца более приоритетна, чем значение, указанное в [ViewColumnProvider.xml](fa_view-column-provider.html).  
При изменении ширины столбцов таблицы исползуется jQuery-плагин [jquery.colresize](fa_jquery-colresize.html).  
Поведение содержимого ячейки при переполнении по ширине столбца может быть настроено с помощью опции `OperationsWOLV.OverflowWordEllipsis`. Если опция
установлена в `false` (по умолчанию), то происходит перенос содержимого ячейки на новую строку. Если опция установлена в `true`, то содержимое ячейки обрезается
и в конец добавляется многоточие.

### Добавление кнопки в тулбар и строки

Основная статья про [добавление кнопок в тулбар и строки WebObjectListView](fa_wolv-add-button.html).

### Использование собственных контролов для отображения данных

Для использования собственных контролов для отображения данных предназначен [WebControlProvider](fa_web-control-provider.html).

### Локализация заголовков атрибутов

Операцию WOLV `UseLocalizedCaptions` нужно использовать в том случае, если требуется поддержка нескольких языков. Когда она включена, WOLV использует метод `View.GetLocalizedPropertyCaption(propName)`. Чтобы задать локализованные заголовки, нужно:
* в сборке с объектами создать файл ресурсов `Captions.resx`;
* задать ему Acess modifier `"Public"`;
* поместить класс, соответствующий файлу ресурсов, в пространство имен `"<namespace сборки>.ObjectsResources"` (можно сразу создавать файл в папке `ObjectsResources`);
* для каждого локализуемого заголовка добавить строчки с ключами вида: `"<namespace сборки>_<класс>_<имя представления>_<имя атрибута>"`, причем в пространстве имен сборки нужно заменить точки на "_";
* в этом же пространстве имен для каждой культуры создать свой файл ресурсов.

Если нужно задать заголовок независимо от представления, то вместо имени преставления следует написать "DefCaption". __Например:__ `"IIS_КошкиСЛапами_Кошка_DefCaption_Кличка"`.

### Формат данных

Для того чтобы отобразить данные в определенном формате, можно написать отдельный контрол.  
Также, есть возможность задать свойству атрибут `System.ComponentModel.DataAnnotations.DisplayFormat`.

Пример:

```csharp
/// <summary>
/// Курс реабилитации
/// </summary>
// *** Start programmer edit section *** (КурсРеабилитации.ВыраженностьОграниченийЗначение CustomAttributes)
[System.ComponentModel.DataAnnotations.DisplayFormat(DataFormatString = "F2")]
// *** End programmer edit section *** (КурсРеабилитации.ВыраженностьОграниченийЗначение CustomAttributes)
public virtual System.Nullable<System.Double> ВыраженностьОграниченийЗначение
{
    get
    {
        //...
    }
    set
    {
        //...
    }
}
```

#### Формат даты и времени

* Формат даты, отображаемой в ячейке может быть настроен с помощью атрибута DisplayFormatAttribute:

```csharp
[DisplayFormat(DataFormatString = "yyyy.MM")]
public virtual System.DateTime OnlyDate
```

Чтобы это работало, дата должна отображаться в ячейке WOLV с помощью стандартного компонента (т.е. если компонент не переопределен в WebControlProvider.xml).

* Если в `WebControlProvider.xml` задан пользовательский компонент для отображения дат (например, `FormattedDateTimeControl`), то формат должен быть настроен непосредственно в компоненте (с помощью `WebControlProvider.TuneControlDelegateMethod`).  
Либо можно создать новый компонент, унаследованный от `FormattedDateTimeControl`, переопределив  свойство `Format`, и указать его в `WebControlProvider.xml`.
* Если в `WebControlProvider.xml` задан пользовательский компонент для отображения дат, но при этом хочется, чтобы для конкретного свойства работал атрибут
  `DisplayFormatAttribute`, то в `WebControlProvider.xml` необходимо сбросить пользовательский компонент для этого свойства:

```xml
<customproperty class="DatePickerTest" property="OnlyDate">
    <control typename="" property="" codefile="" />
</customproperty>
```

#### Формат даты и времени с использованием дополнительного класса

* Способ задания формата для даты и времени без использования атрибута DataFormatString.  
Необходимо создать новый класс:  

```csharp 
/// <summary>
/// Вспомогательный класс для вывода даты в формате "HH:mm".
/// </summary>
public sealed class DateTimeFormattedHHmm : FormattedDateTimeControl
{
    /// <summary>
    /// Метод установки формата даты.
    /// </summary>
    public DateTimeFormattedHHmm()
    {
        Format = "HH:mm";
    }
} 
``` 

Так же необходимо внести изменения в `WebControlProvider.xml`:  

```xml 
<customproperty class="TestDataTimeClassObject" property="poleDateTime">
    <control typename="WebFormsTestStand.Forms.Controls.WOLV.AppearanceTests.DateTimeFormattedHHmm, TestStand(ASP.NET Application)" property="Text" codefile="" />
</customproperty> 
```

## Редактор ограничений

Подробно о редакторе ограничений изложено в [статье Расширенный редактор ограничений для Flexberry ASP.NET](fa_advanced-limit-editor.html).

* `WOLV` проводит индикацию наложенного ограничения в углу. Если имя ограничения известно, то оно будет выводиться. Если нет - то будет отображаться сообщение, что список ограничен.  
__Внимание:__ если при работе в редакторе ограничений сохранить ограничение, а потом его применить, то это не гарантирует, что было применено именно то ограничение, что было сохранено, поэтому имя ограничения на списке выведено не будет.

* Для ускорения работы контрола можно добавить [кэширование сохраненных ограничений](fa_wolv-adv-limit-caching.html).

## Сервис данных WOLV

Основная статья про [сервис данных WebObjectListView](fa_wolv-data-service.html).

## Экспорт в Excel и XML

Экспорт из WebObjectListView в Excel описан в соответствующей [статье](fa_wolv-export-excel.html).  
Экспорт в Excel и XML описан в соответствующей [статье](fa_export-excel-xml.html).

## Поведение флажков выбора элементов

Основная статья про [поведение флажков выбора элементов WebObjectListView](fa_wolv-check-boxes.html).

## JS API

Основная статья про [JavaScript API для WebObjectListView](fa_js-api-wolv.html).

## Советы

1. ID WOLV лучше заполнять **латинскими символами** - это связано с тем, что ID WOLV часто отображается в адресной строке браузера и киррилические символы декодирются, занимая очень много места в URL страницы. `WOLV` никак не связан с WebBinder, поэтому ID можно задавать как удобно.
2. Всегда оборачивайть настройку `WOLV` через [WolvSettApplyer](fa_wolv-sett-applyer.html):

```csharp
var wsa = new WOLVSettApplyer();
wsa.SettingsApply(WebObjectListView1);
```
