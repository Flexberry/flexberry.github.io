---
title: MasterEditorAjaxLookUp
sidebar: product--sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/master-editor-ajax-look-up.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''MasterEditorAjaxLookUp'''

* '''Платформа''': Web.
* '''Предназначение:''' 
* '''JavaScript API:''' 
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_master_editor_ajax_look_up.html)'''.
* '''[MasterEditorAjaxLookUp на тестовом стенде](http://ru:6158/forms/Controls/MasterEditorAjaxLookUp/)'''.

</td>
</tr></tbody></table></a>
</div>


# Введение
Контрол MasterEditorAjaxLookUp позволяет выбирать объект в лукапе без перезагрузки страницы, используя технологию AJAX. Контрол находится в сборке "ICSSoft.STORMNET.Web.AjaxControls". Он использует веб сервис "ICSSoft.STORMNET.Web.AjaxControls.AjaxDataService.asmx".

# Как он работает
Когда вы поднимаете форму на лукап, а потом в ней выбираете объект, то происходит запрос к сервису. Сервис возвращает список контролов, значения которых нужно изменить, и сами значения. 

(((<msg type=important>При работе с сервисом используется [ServiceSecurityProvider](service-security-provider.html).</msg>)))
<br />
У контрола имеется свойство:
```
/// <summary>
/// Добавлять ли используемый тип в ServiceSecurityProvider при использовании контрола
/// </summary>
public bool AddTypeToSecurityProvider = true;
```
По умолчанию, контрол сам добавит все необходимые типы и название методов в [ServiceSecurityProvider](service-security-provider.html), но это будет происходить в рантайме и на конфигурационном XML файле никак не отразится. Поэтому, если вы хотите, чтобы все настройки [ServiceSecurityProvider](service-security-provider.html) брались из файла и не менялись во время исполнения, то вам нужно добавить в ServiceSecurityProvider.xml настройки для метода "LoadObject".
Если у сервиса не будет полномочий для вычитки объектов из базы, то он всегда будет возвращать пустые значения. Ответы от сервиса всегда легко посмотреть в Firebug, вкладка XHR.

# Возможные проблемы
При разворачивании приложения, использующего данный контрол, нужно обязательно удостовериться, что в "iis веб-сад" установлен только 1 процесс. Иначе лукапы будут работать "через раз".

# UpdateOnDocumentReady
Использовать данное свойство нужно аккуратно. Свойство отвечает за обновление значения лукапа при загрузке страницы.
<br />
По-умолчанию, значение свойства false.
<br />
Значение в лукап проставляет WebBinder, но если вам по каким-то причинам нужно обновлять лукап во время загрузки страницы, то будьте внимательны с фокусом на странице и возможными побочными эффектами.
# Автодополнение
Имеется возможность по вынесенному мастеровому свойству (например, лукап типа Standard) добавить автодополнение и по введенному мастеровому свойству будет проставляться значение в лукап. Имееться возможность реализовать схожую функциональность: [Связывание AjaxAutocomplete и AjaxLookup](link--ajax-autocomplete-and--ajax-lookup.html).<br />
Для того, чтобы включить автодополнение нужно установить лукапу свойство Autocomplete = true и убедиться, что лукапу проставляется значение свойства PropertyToShow(в стандартном случае это делает WebBinder).<br />
Например,
```

<ac:MasterEditorAjaxLookUp Autocomplete="true" ID="ctrlВладелецАвтокомплит" CssClass="descTxt" runat="server" />
```
И у объекта проставлен атрибут
```

[MasterViewDefineAttribute("КошкаE", "ВладелецАвтокомплит", ICSSoft.STORMNET.LookupTypeEnum.Standard, "", "Имя")]
```
Для автодополнения можно задать следующие настройки:
```

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

Стоит отметить, что для [автодополнения](ajax-autocomplete.html) вызывается еще один метод сервиса(GetPropertyValues), поэтому, если вы изменили свойство AddTypeToSecurityProvider на false, то нужно убедиться, что данный тип объекта прописан в ServiceSecurityProvider.xml.

(((<msg type=Important>По умолчанию свойство `IsSubstring` имеет значение `False` =&gt; без изменения значения в `True` поиск по подстроке не будет работать.</msg>)))

(((<msg type=Important>
Если в качестве PropertyToShow используется свойство объекта данных не строкового типа, а например типа дата, то следует внимательно ознакомиться с [AjaxAutocomplete.ashx#Особенности_работы_с_нестроковыми_полями_объектов_6|этой статьей], и завести в объекте специальное нехранимое вычислимое свойство.

И в качестве PropertyToShow для MasterEditorAjaxLookup указывать именно это вычислимое свойство.

Пример есть на [тестовом стенде](http://ru:6158/Forms/Controls/AjaxAuto%D0%A1omplete/AutocompleteByDateInLookUp.aspx).
</msg>)))

# Создание зависимых лукапов
Про создание зависимых лукапов рассказывается в [этой статье](linked-master-editors.html).

# Кнопка для просмотра
Если вы укажете лукапу свойство ShowObjectUrl, то рядом с кнопками лукапа появится еще одна кнопка по нажатию на которую можно будет просмотреть мастеровой объект
```

<uc1:MasterEditorLookUp ID="ctrlВладелец" ShowObjectUrl="~/forms/Vladelec/VladelecE.aspx" CssClass="descTxt" runat="server" />
```
Также, если у WOLV на поднимающейся форме с лукапа не указан путь к форме редактирования, то ShowObjectUrl проставится WOLV в качестве EditPage.
<br />
Размер всплывающего окна берется из размеров формы, которая поднимается на лукап.

# Показ HTML свойств, например, картинки
Это касается не только Ajax, но и обычного лукапа. Если вы хотите в PropertyToShow показать HTML, то необходимо, чтобы у свойства в объекте был атрибут IsHTMLAttribute (т.е. ViewColumnProvider.GetPropertyIsHTML от свойства возвращал бы true).

![](/images/pages/img/CaseberryWeb/AjaxLookHtml.png)

# Открытие формы с лукапом не в новом всплывающем окне, а в thickbox
Для того, чтобы форма для выбора появлялась в thickbox нужно лукапу задать свойство ShowInThickBox = true. Также, если вам необходимо поднимать лукап в thickbox не по кнопке, а по ссылке, то вы можете скрыть кнопку (LookUpBtnVisible = false), и задать текст ссылки в свойство ThickBoxLinkText = "ваш текст"

![](/images/pages/img/CaseberryWeb/AjaxLookUpThickboxLink.png)

## Пример
```

	<ac:MasterEditorAjaxLookUp Autocomplete="true" ApplyOnAutocompleteLostFocus="true" ID="ctrlВладелецАвтокомплит" CssClass="descTxt" runat="server" ShowInThickBox="true" ThickBoxLinkText="Выберите владельца" LookUpBtnVisible="false" />
```
![](/images/pages/img/CaseberryWeb/AjaxLookUpThickBox.png)

# Задание типов объектов, доступных для выбора в лукапе
Для задания типов отображаемых в `MasterEditorAjaxLookUp` объектов используется свойство `MasterTypes`:
```cs
public Type[] MasterTypes { get; set; }
```
По умолчанию это свойство хранит типы, заданные для редактируемого свойства объекта в атрибуте [Проблема-используемых-типов.ashx|TypeUsage]. Если же атрибут у соответствующего свойства отсутствует, используется тип из свойства `MasterTypeName` и его потомки, при этом выбираются только хранимые классы. Инициализировать свойство `MasterTypes` нужно в '''OnInit''' или '''OnPreInit'''.

# [JS API](java-script-a-p-i.html)
Для манипуляций с LookUp на стороне клиента следует использовать LookUp JS API, который представляет собой jQuery плагин (icsMasterEditorAjaxLookup).

## Операции
{| 
! Наименование !! Параметры !! Описание
|-
| clear || Нет. || Метод очистки лукапа.
|-
| block || Нет || Метод блокировки лукапа. Блокирует поле ввода и установливает флаг блокировки обработчиков кликов по кнопкам.
|-
| show || Нет. || Метод запуска окна лукапа.
|-
| unblock || Нет || Метод разблокировки лукапа. Разблокирует поле ввода и сбрасывает установленный флаг блокировки обработчиков кликов по кнопкам.
|-
| val || value || Метод установки значения лукапа. Лукапу ставится значение `value`.
|-
| updateOptions || См. таблицу ниже. || Метод обновления опций плагина.
|}

### Параметры `updateOptions`
{| 
! Параметр !! Описание
|-
| bgcolor="LightGray" | '''Свойства лукапа'''
|-
| LFName || Наименование LimitFunction, хранящейся в сессии.
|-
|  viewname || Наименование представления, аналогично свойству лукапа `MasterViewName`.
|-
| typename || Наименование используемого типа, аналогично свойству лукапа `MasterTypeName`.
|-
| connStrName || Наименование строки подключения, аналогично свойству лукапа `ConnStrName`.
|-
| nameValueControl || `ClientID` лукапа, в который будет проставлено значение с лукап-формы, `OBSOLETE`.
|-
| FormCaption || Заголовок открываемой на лукап формы, аналогично свойству лукапа `LookUpFormCaption`.
|-
| CountOnPage || Количество элементов на странице WOLV'а страницы-лукапа, аналогично свойству лукапа `LookUpFormCountOnPage`.
|-
| EditPage || Страница редактирования элементов WOLV'a страницы-лукапа.
|-
| csdName || Наименование настройки хранения столбцов, хранящейся в сессии.
|-
| bgcolor="LightGray" | '''Свойства открываемой формы'''
|-
| width || Ширина формы, по умолчанию `800`.
|-
| height || Высота формы, по умолчанию `600`.
|-
| left || Отступ слева.
|-
| top || Отступ сверху.
|-
| status || Текст в статусбаре у стандартного браузерного окна.
|-
| resizable || Можно ли изменять размер окна.
|-
| menubar || Показывать ли меню браузера.
|-
| toolbar || Показывать ли toolBar браузера.
|-
| caption || Заголовок окна.
|-
| bgcolor="LightGray" | '''Прочие параметры'''
|-
| useDefaultWindow || Использовать браузерное окно для открытия лукап-формы, по умолчанию `false`.
|-
| formParams || Url-параметры окна лукапа.
|-
| url || Ссылка для окна лукапа.
|-
| additionalControls || Связанные лукапы.
|}


### Практическая реализация
Реализуем кнопки, которые будут
* очищать лукап
* блокировать лукап
* разблокировать лукап
* открывать форму лукапа
* подменять адрес формы лукапа

Добавим клиентские кнопки и повесим обработчики `onclick`:
```
<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('unblock'); return false;">РАЗБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('block'); return false;">ЗАБЛОКИРОВАТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('clear'); return false;">ОЧИСТИТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('show'); return false;">ОТКРЫТЬ</button>

<button onclick="$('#<%=ctrlИнспектор.ClientID%>').icsMasterEditorAjaxLookup('updateOptions', {lookup:{LFName:'LFName1'}}); return false;">Настройки</button>```

(((<msg type=important>Без добавления директивы `return false;` будет происходить PostBack и ничего работать не будет.</msg>)))

Для вызова JS API MasterEditorAjaxLookup при загрузке страницы нужно вызывать эти функции в самом конце события ready. Для этого существует несколько способов:
1) Обработка события load.

```
$(window).load(function () {
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
    $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
});```

2) Использование трюка добавления обработчика события ready при обработке этого же события. Данный способ работает, так как обработчики выполняются в том порядке, в котором они были добавлены.

```
$(function () {
    $(function() {
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('block');
        $('#<%=ctrlСтруктурноеПодразделение.ClientID %>').icsMasterEditorAjaxLookup('clear');
    });
});```

''Установка значения `MasterEditorAjaxLookup`''

Например, если мы хотим по нажатию кнопки `changeMasterLookUpValue` менять значение `MasterEditorAjaxLookup` `ctrlLimitEditorMaster1` на указанное в контроле `masterLookUpValues`, то мы воспользуемся следующим кодом:
```

$(document).ready(function () {
	$('#<%= changeMasterLookUpValue.ClientID %>').click(function () {
		$('#<%=ctrlLimitEditorMaster1.ClientID%>').icsMasterEditorAjaxLookup('val', $('#<%=masterLookUpValues.ClientID%>').val());
		return false;
	});
});
```


## События
{|
! Событие !! Описание
|-
| change || Срабатывает после выбора значения мастера и "размазывания" значений лукапа по контролам.
|}

### Практическая реализация
См. [эту статью](look-up-fill-information.html).

## Связывание лукапов, находящихся внутри других элементов
См. [эту статью](http://wiki.ics.perm.ru/ChangeLCSForLookUpInAGE.ashx).
