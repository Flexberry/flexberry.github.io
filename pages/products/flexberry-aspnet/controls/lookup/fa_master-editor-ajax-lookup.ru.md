---
title: MasterEditorAjaxLookUp
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_master-editor-ajax-lookup.html
lang: ru
---

Когда поднимается форма на лукап, а потом в ней выбирается объект, то происходит запрос к сервису. Сервис возвращает список контролов, значения которых нужно изменить, и сами значения.

{% include note.html content="При работе с сервисом используется [ServiceSecurityProvider](fa_service-security-provider.html)." %}

У контрола имеется свойство:

```csharp
/// <summary>
/// Добавлять ли используемый тип в ServiceSecurityProvider при использовании контрола
/// </summary>
public bool AddTypeToSecurityProvider = true;
```

По умолчанию, контрол сам добавит все необходимые типы и название методов в [ServiceSecurityProvider](fa_service-security-provider.html), но это будет происходить в рантайме и на конфигурационном XML файле никак не отразится. Поэтому, если необходимо, чтобы все настройки [ServiceSecurityProvider](fa_service-security-provider.html) брались из файла и не менялись во время исполнения, то нужно добавить в `ServiceSecurityProvider.xml` настройки для метода `LoadObject`. Если у сервиса не будет полномочий для вычитки объектов из базы, то он всегда будет возвращать пустые значения.

## Возможные проблемы

При разворачивании приложения, использующего данный контрол, нужно обязательно удостовериться, что в `iis веб-сад` установлен только 1 процесс. Иначе лукапы будут работать "через раз".

## UpdateOnDocumentReady

Использовать данное свойство нужно аккуратно. Свойство отвечает за обновление значения лукапа при загрузке страницы. По-умолчанию, значение свойства `false`.  
Значение в лукап проставляет `WebBinder`, но если по каким-то причинам нужно обновлять лукап во время загрузки страницы, то следует обратить внимание на фокусо на странице и возможные побочные эффекты.

## Автодополнение

Имеется возможность по вынесенному мастеровому свойству (например, лукап типа `Standard`) добавить автодополнение и по введенному мастеровому свойству будет проставляться значение в лукап. Имеется возможность реализовать схожую функциональность: [Связывание AjaxAutocomplete и AjaxLookup](fa_link-ajax-autocomplete-ajax-lookup.html).  
Для того чтобы включить автодополнение, нужно установить лукапу свойство `Autocomplete = true` и убедиться, что лукапу проставляется значение свойства `PropertyToShow` (в стандартном случае это делает `WebBinder`).

Например,

```xml
<ac:MasterEditorAjaxLookUp Autocomplete="true" ID="ctrlВладелецАвтокомплит" CssClass="descTxt" runat="server" />
```

И у объекта проставлен атрибут

```csharp
[MasterViewDefineAttribute("КошкаE", "ВладелецАвтокомплит", ICSSoft.STORMNET.LookupTypeEnum.Standard, "", "Имя")]
```

Для автодополнения можно задать следующие настройки:

```csharp
    /// <summary>
    /// Применять ли значение при выборе объекта в autocomplete.
    /// </summary>
    public bool ApplyOnAutocompleteSelect = true;

    /// <summary>
    /// Применять ли значение при autocomplete, если сменился фокус.
    /// </summary>
    public bool ApplyOnAutocompleteLostFocus;

    /// <summary>
    /// Имя представления для предиктивного ввода.
    /// </summary>
    public string AutocompleteViewName;

    /// <summary>
    /// Включить поиск по подстроке для автокомплита.
    /// </summary>
    public bool IsSubstring;

    /// <summary>
    /// Свойство для предиктивного ввода - по умолчанию PropertyToShow.
    /// </summary>
    public string PropertyToAutocomplete;

    /// <summary>
    /// Свойство для предиктивного ввода - Multiline.
    /// </summary>
    public TextBoxMode PropertyToAutocompleteMultiline;

    /// <summary>
    /// Количество символов, с которых начинается предиктивный ввод (по умолчанию 2).
    /// </summary>
    public int AutocompleteMinChars;

    /// <summary>
    /// Количество возможных вариантов для выбора при предиктивном вводе (по умолчанию 10).
    /// </summary>
    public int AutocompleteLimitCount;
```

Стоит отметить, что для [автодополнения](fa_ajax-autocomplete.html) вызывается еще один метод сервиса(`GetPropertyValues`), поэтому, если изменено свойство `AddTypeToSecurityProvider` на `false`, то нужно убедиться, что данный тип объекта прописан в `ServiceSecurityProvider.xml`.

{% include important.html content="По умолчанию свойство `IsSubstring` имеет значение `False` =&gt; без изменения значения в `True` поиск по подстроке не будет работать." %}

{% include important.html content="Если в качестве PropertyToShow используется свойство объекта данных не строкового типа, а например типа дата, то следует внимательно ознакомиться со [статьей Автодополнение в веб-системах (AjaxAutocomplete)](fa_ajax-autocomplete.html), и завести в объекте специальное нехранимое вычислимое свойство.  
И в качестве `PropertyToShow` для `MasterEditorAjaxLookup` указывать именно это вычислимое свойство." %}

## Создание зависимых лукапов

Про создание зависимых лукапов рассказывается в [статье Связывание контролов редактирования мастеров](fa_linked-master-editors.html).

## Кнопка для просмотра

Если указать лукапу свойство `ShowObjectUr`l, то рядом с кнопками лукапа появится еще одна кнопка по нажатию на которую можно будет просмотреть мастеровой объект

```xml
<uc1:MasterEditorLookUp ID="ctrlВладелец" ShowObjectUrl="~/forms/Vladelec/VladelecE.aspx" CssClass="descTxt" runat="server" />
```

Также, если у WOLV на поднимающейся форме с лукапа не указан путь к форме редактирования, то `ShowObjectUrl` проставится WOLV в качестве `EditPage`. Размер всплывающего окна берется из размеров формы, которая поднимается на лукап.

## Показ HTML свойств, например, картинки

Если в `PropertyToShow` необходимо показать HTML, то необходимо, чтобы у свойства в объекте был атрибут `IsHTMLAttribute` (т.е. `ViewColumnProvider.GetPropertyIsHTML` от свойства возвращал бы `true`).

![Пример](/images/pages/products/flexberry-aspnet/controls/lookup/ajax-look-html.png)

## Задание типов объектов, доступных для выбора в лукапе

Для задания типов отображаемых в `MasterEditorAjaxLookUp` объектов используется свойство `MasterTypes`:

```csharp
public Type[] MasterTypes { get; set; }
```

По умолчанию это свойство хранит типы, заданные для редактируемого свойства объекта в атрибуте [TypeUsage](fo_type-usage-problem.html). Если же атрибут у соответствующего свойства отсутствует, используется тип из свойства `MasterTypeName` и его потомки, при этом выбираются только хранимые классы. Инициализировать свойство `MasterTypes` нужно в `OnInit` или `OnPreInit`.

## [JS API](fa_javascript-api.html)

Для манипуляций с LookUp на стороне клиента следует использовать `LookUp JS API`, который представляет собой jQuery плагин (`icsMasterEditorAjaxLookup`).

### Операции

| Наименование | Параметры | Описание
|------------------|--------------------|------------------------------|
| clear |  | Метод очистки лукапа|
| block |  | Метод блокировки лукапа. Блокирует поле ввода и установливает флаг блокировки обработчиков кликов по кнопкам|
| show |  | Метод запуска окна лукапа|
| unblock |  | Метод разблокировки лукапа. Разблокирует поле ввода и сбрасывает установленный флаг блокировки обработчиков кликов по кнопкам|
| val | value | Метод установки значения лукапа. Лукапу ставится значение `value`|
| updateOptions | См. таблицу ниже. | Метод обновления опций плагина|

### Параметры `updateOptions`

| Параметр | Описание|
|---------------|---------------------------------|
| `Свойства лукапа`||
| LFName | Наименование LimitFunction, хранящейся в сессии|
| viewname | Наименование представления, аналогично свойству лукапа `MasterViewName`|
| typename | Наименование используемого типа, аналогично свойству лукапа `MasterTypeName`|
| connStrName | Наименование строки подключения, аналогично свойству лукапа `ConnStrName`|
| nameValueControl | `ClientID` лукапа, в который будет проставлено значение с лукап-формы, `OBSOLETE`|
| FormCaption | Заголовок открываемой на лукап формы, аналогично свойству лукапа `LookUpFormCaption`|
| CountOnPage | Количество элементов на странице WOLV'а страницы-лукапа, аналогично свойству лукапа `LookUpFormCountOnPage`|
| EditPage | Страница редактирования элементов WOLV'a страницы-лукапа|
| csdName | Наименование настройки хранения столбцов, хранящейся в сессии|-
| `Свойства открываемой формы`||
| width | Ширина формы, по умолчанию `800`|
| height | Высота формы, по умолчанию `600`|
| left | Отступ слева|
| top | Отступ сверху|
| status | Текст в статусбаре у стандартного браузерного окна|
| resizable | Можно ли изменять размер окна|
| menubar | Показывать ли меню браузера|
| toolbar | Показывать ли toolBar браузера|
| caption | Заголовок окна|
| `Прочие параметры`||
| useDefaultWindow | Использовать браузерное окно для открытия лукап-формы, по умолчанию `false`|
| formParams | Url-параметры окна лукапа|
| url | Ссылка для окна лукапа|
| additionalControls | Связанные лукапы|

### Практическая реализация

Кнопки, которые будут:

* очищать лукап
* блокировать лукап
* разблокировать лукап
* открывать форму лукапа
* подменять адрес формы лукапа

Добавить клиентские кнопки и повесить обработчики `onclick`:

```javascript
<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('unblock'); return false;">РАЗБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('block'); return false;">ЗАБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('clear'); return false;">ОЧИСТИТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('show'); return false;">ОТКРЫТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', {lookup:{LFName:'LFName1'}}); return false;">Настройки</button>
```

{% include important.html content="Без добавления директивы `return false;` будет происходить PostBack и ничего работать не будет."%}

Для вызова `JS API MasterEditorAjaxLookup` при загрузке страницы нужно вызывать эти функции в самом конце события `ready`. Для этого существует несколько способов:

1.Обработка события `load`.

```javascript
$(window).load(function () {
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
});
```

2.Использование трюка добавления обработчика события `ready` при обработке этого же события. Данный способ работает, так как обработчики выполняются в том порядке, в котором они были добавлены.

```javascript
$(function () {
    $(function() {
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
    });
});
```

#### Установка значения `MasterEditorAjaxLookup`

Например, если по нажатию кнопки `changeMasterLookUpValue` должно изменяться значение `MasterEditorAjaxLookup` `ctrlLimitEditorMaster1` на указанное в контроле `masterLookUpValues`, то:

```javascript
$(document).ready(function () {
$('#<%= changeMasterLookUpValue.ClientID %>').click(function () {
    $('#<%=ctrlLimitEditorMaster1.ClientID%>').icsMasterEditorAjaxLookup('val', $('#<%=masterLookUpValues.ClientID%>').val());
    return false;
});
});
```

### События

| Событие | Описание|
|------------|---------------------------------------|
| change | Срабатывает после выбора значения мастера и "размазывания" значений лукапа по контролам|

#### Практическая реализация контрола

См. [LookUp](fw_lookup.html).

### Связывание лукапов, находящихся внутри других элементов

См. [статью Связывание LookUp'ов в AGE с внешним LookUp'ом](fa_change-lcs-lookup-age.html).
