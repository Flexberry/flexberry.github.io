---
title: Поддержка нескольких хранилищ данных в одном приложении
sidebar: product--sidebar
keywords: Flexberry ORM, Public
toc: true
permalink: ru/multibase.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ORM](flexberry-o-r-m.html)
* '''Компонент''': [Сервис данных](data-service.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Business.dll, ICSSoft.STORMNET.DataObject.dll
* '''Предназначение''': Описаны способы обращения к нескольким хранилищам.
</td>
</tr></tbody></table></a>
</div>

# Подмена строки сервиса данных
`[SQLDataService](s-q-l-data-service.html)` имеет специальный делегат 
```cs
    /// <summary>
    /// Делегат для изменения строки соединения (организация работы с несколькими базами)
    /// </summary>
    /// <param name="types">Массив типов, который получается из объектов пришедших в сервис данных</param>
    /// <returns>Новая строка соединения, если вернётся пустое значение или null, строка не изменится</returns>
    public delegate string ChangeCustomizationStringDelegate(System.Type[] types);

    /// <summary>
    /// Делегат для смены строки соединения
    /// </summary>
    public static ChangeCustomizationStringDelegate ChangeCustomizationString = null;
```
который позволяет подменить строку соединения для передаваемого типа. Инстанция сервиса данных не меняется, меняется только строка соединения. 
Дополнительно есть специальное свойство `[SQLDataService](s-q-l-data-service.html)`, позволяющее отменить действие данного делегата (это позволяет иметь несколько сервисов данных, которые работают исключительно со своими БД без перенастройки):

```cs
/// <summary>
/// Не менять строку соединения общим делегатом ChangeCustomizationString
/// </summary>
public bool DoNotChangeCustomizationString
{
  get { return _doNotChangeCustomizationString; }
  set { _doNotChangeCustomizationString = value; }
}
```

# Подмена StorageName
`[Information](information-class-as-metadata-supervisor.html)` содержит делегат 

```cs
/// <summary>
/// Делегат для смены ClassStorageName (можно подставить имя_базы.dbo.имя_таблицы, например)
/// </summary>
/// <param name="classType">Тип класса</param>
/// <param name="originalStorageName">Оригинальный StorageName</param>
/// <returns>новый StorageName (если пустое или null, то возьмём оригинальное)</returns>
public delegate string ChangeClassStorageNameDelegate(Type classType, string originalStorageName);

/// <summary>
/// Делегат для смены ClassStorageName (можно подставить имя_базы.dbo.имя_таблицы, например)
/// </summary>
public static ChangeClassStorageNameDelegate ChangeClassStorageName = null;
```

который позволяет подменить в динамике [имя таблицы и схемы, в которой хранится этот класс](data-objects-and-database-structures.html).
Для того, чтобы реализовать поддержку нескольких схем требуется выдавать строку такого вида: '''`dbo"."ТипЛапы`'''. 
Этот делегат вызывается единожды для каждого типа, поскольку возвращённое значение кэшируется. Если кроме имени схемы указать имя таблицы, то нужно убедиться, что в рамках одной транзакции не будет обращений к разным БД т.к. это приведёт к исключению при выполнении всей операции. 

'''Внимание!''' Надо добавлять кавычки внутри возвращаемого значения, поскольку идентификатор по сути ломает стандартную логику обращения к таблице. Кавычки добавляются всегда при упаковке идентификаторов (для MSSQL).
```cs
имя_базы".[dbo]."название_класса
```

Экранировать кавычки в web.config при необходимости можно стандартными средствами XML.

<msg type=information head=Кстати>Следует иметь в виду, что [бизнес-сервер](business--servers--wrapper--business--facade.html) пытается выполнить обновление всех пришедших объектов в одной транзакции. Поэтому необходимо избегать ситуации, когда [реальные хранилища обновляемых объектов](data-objects-and-database-structures.html) располагаются в разных БД. В противном случае [бизнес-сервер](business--servers--wrapper--business--facade.html) попытается выполнить закрытие транзакции на источнике, отличном от того, на котором транзакция открывалась, что приведёт к возникновению ошибки.</msg>