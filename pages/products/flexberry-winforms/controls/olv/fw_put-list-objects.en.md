---
title: Placing a list of objects in an ObjectListView
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Controls, OLV, DataObject
summary: Алгоритм создания списка, установка типа и представление, включение данных
toc: true
permalink: en/fw_put-list-objects.html
lang: en
---

Так как [ObjectListView](fw_objectlistview.html) не используется для отображения данных не из БД, стандартного способа отобразить список объектов, созданных прямо в коде, нет.

Тем не менее существует алгоритм решения этой задачи.

## Алгоритм добавления данных

* Создать список данных, которые будут отображены.
* Установить у `ObjectListView` используемый тип и представление.
* Установить `LimitFunction` так, чтобы из базы ничего не подгрузилось.
* Вызвать метод `SetObjects`.

### Создание списка данных

В первую очередь создается собственно список данных, которые необходимо будет загрузить в `ObjectListView`. Не обязательно, чтобы эти данные были в базе.

Для примера используется переменная `myObjects`, в которую будет сохранен созданный список объектов.

### Установка используемого типа и представления

Например, необходимо отображать объекты типа `Адрес`, по представлению `АдресL`.

В коде кстанавливаются настройки `ObjectListView`:

```csharp
objectListView1.DataObjectTypes = new[] { typeof(Адрес) };
objectListView1.ViewName = "АдресL";
```

{% include important.html content="Несмотря на то, что данные не обязательно должны находиться в БД, представление у класса, отвечающее за эти данные, __должно быть__." %}

### Блокировка подгрузки данных из базы

Чтобы данные из базы не подгружались во время обновления (нажатия на кнопку `Refresh`), необходимо установить `LimitFunction` у `ObjectListView` так, чтобы условие никогда не выполнялось. Например, следующим образом:

```csharp
SQLWhereLanguageDef langdef = SQLWhereLanguageDef.LanguageDef;
objectListView1.LimitFunction = langdef.GetFunction(langdef.funcSQL, "1 = 2");
```

### Добавление объектов в список

Чтобы добавить объекты в список, достаточно вызвать метод `SetObjects` у `ObjectListView`:

```csharp
objectListView1.SetObjects(myObjects.ToArray());
```

Однако, если вызвать этот метод в момент создания формы, то данные потеряются при нажатии на кнопку `Refresh`.

Чтобы данные сохранялись, необходимо вызывать этот метод __после загрузки данных из базы__. Для этого необходимо подписаться на событие `AfterFillData` и добавить в обработчик вызов метода `SetObjects`, к примеру так:

```csharp
objectListView1.AfterFillData += (o, s) =>
{
	objectListView1.SetObjects(myObjects.ToArray());
};
```
