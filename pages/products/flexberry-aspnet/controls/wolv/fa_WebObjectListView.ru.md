---
title: WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_web-object-list-view.html
folder: products/flexberry-aspnet/
lang: ru
---

{% capture my-include %}{% include fa_control_info.md %}{% endcapture %}
{{ my-include | markdownify }}

## Введение

**WebObjectListView** (далее, `WOLV`) - веб-контрол, который используется для отображения списка объектов.

## Внешний вид WOLV

### Внешний вид WOLV при использовании различных тем

Внешний вид WOLV при использовании различных тем можно посмотреть в [статье про Темы](choose-theme.html).

### Stylization

Основная статья про [cтилизацию](fa_w-o-l-v-stylization.html).

### CSS-классы

Основная статья о [CSS-классах WOLV](fa_w-o-l-v-c-s-s.html).

### Пейджинг

Основная статья про [пейджинг WOLV](fa_w-o-l-v-paging.html).

### Фиксация шапки списка

Чтобы зафиксировать шапку списка, чтобы она не "убегала" при прокрутке до самого низа страницы, достаточно добавить на страницу клиентский скрипт:

```csharp
$('.wolv-caption-wrapper').sticky( { topSpacing : <x> } );
```

где `x` - высота от верхнего края в пикселях.

### Прокрутка списка `ScrollToSelectedObject`

После совершения операции с элементом список прокручивается к выделенному объекту данных.

Настройку удобно применять к большим спискам, когда выделенный объект по умолчанию не всегда попадает в зону видимости пользователем.

Пример использования можно посмотреть на [стенде](http://ru:6158/Forms/Controls/WOLV/AppearanceTests/TestFixHead.aspx).

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

Тестирование на [стенде](http://ru:6158/Forms/Controls/WOLV/AppearanceTests/TestSaveScroll.aspx).

## Пользовательские настройки WOLV

### Фильтры

Основная статья про [фильтры WOLV](fa_w-o-l-v-filters.html).

### Поиск

Основная статья про [поиск в WOLV](fa_w-o-l-v-search.html).

### Редактор ограничений

[Основная статья про расширенный редактор ограничений WOLV](fa_advanced-limit-editor.html).

* В версии после 11.10.2013 `WOLV` проводит индикацию наложенного ограничения в углу. Если имя ограничения известно, то оно будет выводиться. Если нет - то
  будет отображаться сообщение, что список ограничен.
  Обратите внимание, что если при работе в [редакторе ограничений](fa_advanced-limit-editor.html) сохранить ограничение, а потом его применить, то это не
  гарантирует, что было применено именно то ограничение, что было сохранено, поэтому имя ограничения на списке выведено не будет.

* Для ускорения работы контрола можно добавить [кэширование сохраненных ограничений](fa_wolv-adv-limit-caching.html).

### Настройка отображения столбцов

Основная статья про [настройку отображения столбцов WOLV](fa_w-o-l-v-columns.html).

### Настройка сортировки

Основная статья про [настройку сортировки WOLV](list-sort.html).

### Возможность изменения ширины столбцов

Пользователь может самостоятельно изменить ширину каждого столбца списка, если установлено свойство `Operations.AllowColumnResizing = true`. Настроенные размеры
столбцов сохраняются для каждого пользователя на сервере и восстанавливаются при следующей загрузке страницы. Установленная пользователем ширина столбца более
приоритетна, чем значение, указанное в [ViewColumnProvider.xml](view-column-provider.html).

При изменении ширины столбцов таблицы исползуется jQuery-плагин [jquery.colresize](fa_jquery-colresize.html).

Поведение содержимого ячейки при переполнении по ширине столбца может быть настроено с помощью опции `OperationsWOLV.OverflowWordEllipsis`. Если опция
установлена в `false` (по умолчанию), то происходит перенос содержимого ячейки на новую строку. Если опция установлена в `true`, то содержимое ячейки обрезается
и в конец добавляется многоточие.

## Настройки WOLV

### Операции

Основная статья об [операциях WOLV](fa_w-o-l-v-operations.html).

### События

Основная статья про [события WOLV](fa_w-o-l-v-events.html).

### Печать

Существуют настройки WOLV, позволяющие напечатать список или часть списка. Описание настройки печати представлено в статье
[печать списка](fa_print-wolv.html).

### Отображение иерархических данных

Основная статья про [иерархический WOLV](fa_w-o-l-v-hierarhy.html).

### Отображение на одной странице списковой формы и формы редактирования

Основная статья про [EditorInFrame](fa_editor-in-frame.html).

### Добавление кнопки в тулбар и строки

Основная статья про [добавление кнопок в тулбар и строки WOLV](fa_w-o-l-v-add-button.html).

### Использование собственных контролов для отображения данных

Для использования собственных контролов для отображения данных используйте [WebControlProvider](fa_web-control-provider.html).

### Формат данных

Для того, чтобы отобразить данные в определенном формате, можно написать отдельный контрол. Но, также, есть возможность задать свойству атрибут
`System.ComponentModel.DataAnnotations.DisplayFormat`.

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

* Если в `WebControlProvider.xml` задан пользовательский компонент для отображения дат (например, `FormattedDateTimeControl`), то формат должен быть настроен
  непосредственно в компоненте (с помощью `WebControlProvider.TuneControlDelegateMethod`).
  Либо можно создать новый компонент, унаследованный от `FormattedDateTimeControl`, переопределив свойство `Format`, и указать его в `WebControlProvider.xml`.

* Если в `WebControlProvider.xml` задан пользовательский компонент для отображения дат, но при этом хочется, чтобы для конкретного свойства работал атрибут
  `DisplayFormatAttribute`, то в `WebControlProvider.xml` необходимо сбросить пользовательский компонент для этого свойства:

```xml
<customproperty class="DatePickerTest" property="OnlyDate">
    <control typename="" property="" codefile="" />
</customproperty>
```

### Загрузка данных и EmptyControl

Основная статья о [загрузке данных и EmptyControl в WOLV](fa_w-o-l-v-load-data-and-empty-control.html).

### Локализация заголовков атрибутов

Операцию WOLV `UseLocalizedCaptions` нужно использовать в том случае, если требуется поддержка нескольких языков. Когда она включена, WOLV использует метод
`View.GetLocalizedPropertyCaption(propName)`. Чтобы задать локализованные заголовки, нужно:
* в сборке с объектами создать файл ресурсов `Captions.resx`;
* задать ему Acess modifier `"Public"`;
* поместить класс, соответствующий файлу ресурсов, в пространство имен `"<namespace сборки>.ObjectsResources"` (можно сразу создавать файл в папке
  `ObjectsResources`);
* для каждого локализуемого заголовка добавить строчки с ключами вида: `"<namespace сборки>_<класс>_<имя представления>_<имя атрибута>"`, причем в пространстве
  имен сборки нужно заменить точки на "_";
* в этом же пространстве имен для каждой культуры создать свой файл ресурсов.

Если нужно задать заголовок независимо от представления, то вместо имени преставления напишите "DefCaption". Например: `"IIS_КошкиСЛапами_Кошка_DefCaption_Кличка"`.

### Варианты открытия формы редактирования при создании\редактировании объекта

Основная статья про [открытие формы редактирования](fa_w-o-l-v-edit-form.html).

### Настройки ThickBox

Для управления настройками `ThickBox` в `WOLV` нужно обращаться к свойству `ThickBoxSettings`.

Например, для `WOLV` удобно указывать высоту и ширину в `WOLVSettAplyer`:

```csharp
wolv.ThickBoxSettings.Height = 480;
wolv.ThickBoxSettings.Width = 640;
```

## Советы

1. ID WOLV лучше заполнять **латинскими символами** - это связано с тем, что ID WOLV часто отображается в адресной строке браузера и киррилические символы
   декодирются, занимая очень много места в URL страницы. `WOLV` никак не связан с WebBinder, поэтому ID можно задавать как вам удобно.
2. Всегда оборачивайте настройку `WOLV` через [WolvSettApplyer](fa_wolv-sett-applyer.html):

```csharp
var wsa = new WOLVSettApplyer();
wsa.SettingsApply(WebObjectListView1);
```

## Сервис данных WOLV

Основная статья про [сервис данных WOLV](fa_w-o-l-v-data-service.html).

## Экспорт в Excel и XML

Основная статья про [экспорт из WOLV в Excel](fa_w-o-l-v-export2-excel.html).

## Поведение флажков выбора элементов

Основная статья про [поведение флажков выбора элементов WOLV](fa_w-o-l-v-check-boxes.html).

## JS API

Основная статья про [JavaScript API для WOLV](fa_js-api-wolv.html).
