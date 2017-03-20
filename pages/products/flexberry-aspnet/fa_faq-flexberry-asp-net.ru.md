---
title: FAQ Flexberry ASP.NET
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Учебники
toc: true
permalink: ru/fa_f-a-q--Flexberry--web.html
folder: products/flexberry-aspnet/
lang: ru

---

* **Платформа:** [FlexberryASP.NET.](fa_flexberry-a-s-p-n-e-t.html)
* **Предназначение:** Ответы на часто возникающие вопросы.

{% include note.html content="Страница находится в разработке" %}

## Частые вопросы и ответы

Статья содержит частые вопросы и ответы. Задавайте новые вопросы в комментариях.

### Как посмотреть запросы, идущие к источнику данных?

Необходимо в web.***config*** в секцию <appSettings> добавить следующую строку:

```cs
<add key="BusinessTaskMonitorType" value="EventTaskMonitor.EventTaskMon, EventTaskMonitor, Version=1.0.0.1, Culture=neutral, PublicKeyToken=null" />
```

При этом сборка ***EventTaskMonitor.dll*** должна находиться в папке bin веб-приложения.
Все SQL запросы будут отображаться в стандартном "просмотрщик событий windows". Для его запуска необходимо в командной строке написать команду "eventvwr":

```

Microsoft Windows [Version 6.1.7601]
(c) Корпорация Майкрософт (Microsoft Corp.), 2009. Все права защищены.

C:\Users\istomin>eventvwr

```

### Как посмотреть тайминги исполнения и отладочную информацию на странице?
Для включения отслеживания на странице, в определении

```cs
<%@ Page Language="C#" MasterPageFile="~/Site1.Master"  AutoEventWireup="true" CodeBehind="AC_WebSearchE.aspx.cs" 
Inherits="ICSSoft.STORMNET.AdmConsole.AC_WebSearchE" %>
```

Указать Trace="true", как показано ниже:

```cs
<%@ Page Language="C#" MasterPageFile="~/Site1.Master"  AutoEventWireup="true" CodeBehind="AC_WebSearchE.aspx.cs" 
Inherits="ICSSoft.STORMNET.AdmConsole.AC_WebSearchE" Trace="true" %>
```

Некоторые элементы управления из Flexberry Web Framework выводят служебную информацию о срабатывании тех или иных методов.

### Как посмотреть тайминги исполнения и отладочную информацию для всего веб-приложения?
В web.config указывается следующий ключ:

```cs
<system.web>
<!-- Закоментировать это перед релизом, чтобы юзеры не могли увидеть лишнего -->
<trace enabled="true" localOnly="false"/>
<!-- Закоментировать это перед релизом, чтобы юзеры не могли увидеть лишнего -->
</system.web>
```

localOnly="false" - это для того, чтобы можно было просматривать трассировку для сервера удалённо.

Для вывода отладочной информации нужно использовать примерно такой код:

```cs
if (Trace.IsEnabled)
     Trace.Warn("WOLV " + (fView!=null?fView.Name:""), "Begin Page_Load");
```

### Как настроить видимость кнопок для списков?
WebObjectListView1.Operations используется для настройки доступности кнопок. Например, так:

```cs
WebObjectListView1.Operations.NewByExampleInRow = true;
WebObjectListView1.Operations.LimitEdit = false;
WebObjectListView1.Operations.DeleteInRow = true;
```


### Как настроить стили по значению на списковых формах?
Смотрите следующий пример:

```cs
WebObjectListView1.Stylization.ColumnName = "ФИО";
ICSSoft.STORMNET.Web.Controls.TextStyle ts = new ICSSoft.STORMNET.Web.Controls.TextStyle("Пылесосов", "redtext");
WebObjectListView1.Stylization.Styles = new ICSSoft.STORMNET.Web.Controls.TextStyle&#91;&#93; { ts };
```

В данном примере для объектов, у которых в свойстве «ФИО» указано значение «Пылесосов», будет применен стиль «redtext». Не забывайте определять стили в css перед подобными описаниями.

### Как запретить редактирование свойств объекта сразу на всей форме?
Смотри следующий пример:

```cs
webBinder.SetReadOnlyForm(this.Controls, fView, false);
```

Блокируются только серверные контролы, которые имеют свойства ReadOnly или Enabled. Распространяется только на контролы, которые привязаны к передаваемому представлению. Для блокирования отдельного контрола используйте: 

```cs
webBinder.SetReadonlyToControl(Ctrl, ReadOnlyFlag);
```

### Как отобразить возникшую ошибку во всплывающем окне?

```cs
 ICSSoft.STORMNET.Web.Controls.WebErrorBox.RiseErrorBox(ex, Page.Form, false, null); 
```

Главное, чтобы был подключен стиль exceptionpanelstyle.css, thickbox.css. И скрипт exceptionpanel.js и thickbox.js. По умолчанию это всё подключается через ContextHelper и не требует дополнительного вмешательства.

### Как подключить *.js или *.css файл или добавить сгенерированный скрипт из контентной страницы?
Используйте класс ContextHelper. 

```cs
 public static void ПодключитьВнешнийФайл(string относительныйПутьКФайлу) 
```

Для добавления строки с самим скриптом:

```cs
 public static void ДобавитьСкрипт(string скрипт) 
```


### Как настроить отображение колонок в WebObjectListView?
Используйте методы класса ViewColumnProvider и xml-описание для объектов данных. 
Пример:

```cs

<type name="IIS.СЭДиЖ.УчебнаяГруппаКласса">
    <property name="Описание" width="30" filter="false" cut="true"/>
    <property name="Класс.Школа.Наименование" width="100" filter="true" cut="true" align="ПоЦентру"/>
    <property name="Наименование" width="100" filter="false" cut="true" align="ПоПравомуКраю" sort="false"/>
</type>
```

align свойство указывается по значениям перечисления "Выравнивание".

```cs

    /// <summary>
    /// Выравнивание в столбцах
    /// </summary>
    public enum Выравнивание
    {
        ПоЛевомуКраю,
        ПоЦентру,
        ПоПравомуКраю,
        НеУказано        
    }
```

cut – обрезать длинные строки или нет. filter – доступно ли фильтрование, поиск. sort - сортировка для этого поля.

### Как задать операции, доступные в WebObjectListView по-умолчанию?
Используйте ключ в конфигурационном файле:

```cs

    <add key="WOLVDefaultOperations" value="Refresh,Filter"/>
```

Значение – это названия операций (названия полей структуры OperationsWOLV), указанные через запятую.

### Как добавить кнопку WebObjectListView с клиентским/серверным обработчиком 
Как добавить кнопку:
* на тулбар WebObjectListView с клиентским обработчиком?
* на тулбар WebObjectListView с серверным обработчиком?
* в строки WebObjectListView с клиентским обработчиком?
* в строки WebObjectListView с серверным обработчиком?
Ответ на все эти вопросы вы найдете в соответствующей [статье](w-o-l-v-add-button.html).

### Как использовать кэширование?
Используйте класс CacheHelper.
Пример использования:

1. Вариант простого кэширования:

```cs

string key = "ViewColumnProviderXmlDoc";
xmlDoc = (XmlDocument)CacheHelper.GetFromCache(key);
if (xmlDoc == null)
{
    xmlDoc = new XmlDocument();
    xmlDoc.Load(HttpContext.Current.Server.MapPath("~/xml/ViewColumnProvider.xml"));
    CacheHelper.SetToCache(key, xmlDoc);
}
```

2. Кеш списка объектов из БД:

```cs

List<ПравилоДоступа> list = CacheHelper.GetFromCache<ПравилоДоступа>("ПД_" + роль.__PrimaryKey.ToString(), loadingCustomizationStruct);
```

В этом случае объекты будут загружены по loadingCustomizationStruct если они не обнаружены в кэше. 

3. Загрузка объекта из кэша:

```cs

CacheHelper.LoadFromCache(userRole.Роль);
```

В этом случае ключом в кэше будет PrimaryKey объекта. Если объекта в кэше не обнаружится, объект будет загружен ds.LoadObject(userRole.Роль). (Сервис данных будет получен по умолчанию).

4. Очистка кэша:

```cs

CacheHelper.ClearCacheByKeyStartsWith(startswith);
```

Удалятся только те объекты, ключи которых начинаются с указанных символов. String.Emty очистит весь кэш.
Кэш хранится для всего домена, то есть он один на всех страницах для всех пользователей. Поэтому используйте кэширование, только если данные будут часто использоваться повторно. Не забывайте, что кэш должен вручную синхронизироваться с источниками данных. За устаревшие данные в кэше несёт ответственность разработчик.

### Как поднять модальное окно и открыть в нём страницу?
Располагаем контрол на странице и настраиваем его:

```cs

<ac:LinkModalPopupWindow ID="myLink" EnableViewState="false" runat="server" URL="homework.aspx" Enabled="true" Text="Ссылка" WindowTitle="Домашнее задание" />
```

Можно этот контрол добавить динамически или даже отрендерить вручную.
Или получить, используя следующую функцию:

```cs

LinkModalPopupWindow.GetHtml(this.ClientID + "_lmpw" + j.ToString(), "Подробнее", "TemaPlanLessonE.aspx?LookUp=true&amp;pk={0}", "Тематический план урока", 640, 480, true)
```

## Смотрите также
* [WebObjectListView](fa_web-object-list-view.html)
* [Операции WebObjectListView](fa_w-o-l-v-operations.html)
* [Базовая системно-технологическая архитектура Flexberry Platform](fw_flexberry-winforms-architecture.html)
* [Экспорт в Excel и XML](fa_export-to--excel-and--x-m-l.html)

## Откуда ссылаются на эту страницу

* [Учебники и руководства пользователя]()

## Куда ссылается эта страницах

* [Экспорт в Excel и XML](fa_export-to--excel-and--x-m-l.html)
* [Flexberry ASP.NET](fa_flexberry-a-s-p-n-e-t.html)
* [Базовая системно-технологическая архитектура Flexberry Winforms](fw_flexberry-winforms-architecture.html)
* [Подсказки к полям данных](fa_hints-for-attributes.html)
* [WebObjectListView (WOLV)](fa_web-object-list-view.html)
* [Добавление кнопки в Toolbar или в строки WOLV](fa_w-o-l-v-add-button.html)
* [Операции WOLV](fa_w-o-l-v-operations.html)