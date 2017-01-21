---
title: Межформенное взаимодействие — передача объекта между страницами
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_object-transfer-web.html
folder: products/flexberry-aspnet/
lang: ru
---

Цель —&nbsp;передать объект с одной страницы на другую. Объект помещается в сессию, для этого генерируется уникальный идентификатор (GUID), по которому объект извлекается из сессии на другой странице.

### Генерация уникального идентификатора
GUID (Globally Unique Identifier) — статистически уникальный 128-битный идентификатор. Его главная особенность — уникальность, которая позволяет создавать расширяемые сервисы и приложения без опасения конфликтов, вызванных совпадением идентификаторов.

```cs
var guid = Guid.NewGuid().ToString();
```

### 1. Передача Limit Function

В Page_Load создаем Limit Function:

```cs
var ld = ExternalLangDef.LanguageDef;
Function f = ld.GetFunction(ld.funcEQ,new VariableDef(ld.StringType, "Name"),"Имя");
```
### 2. Передача объекта

Описываем класс, отмечаем его как Serialisable, создаем экземпляр класса:
```cs
[Serializable]
public class Person
{
  public string Name;

  public Person()
  {
     this.Name = "unknown";
  }
}

var f = new Person();
```



### Сохранение объекта в сессии

Помещаем объект f в сессию по ключу GUID:

```cs
HttpContext.Current.Session[guid] = f;
```

### Передача идентификатора в строке запроса

Создаем ссылку на странице
```

<asp:HyperLink runat="server" ID="MyLink" Text="Передать объект"></asp:HyperLink>
```

Для ссылки указываем URL, в параметре ’guid’ указываем идентификатор:

```

MyLink.NavigateUrl = "~/SessionTest.aspx?guid=" + guid;
```
### Получение объекта из сессии

На принимающей странице считываем GUID из строки запроса, по нему считываем объект из сессии:

```cs
string guid = Request.QueryString.Get("guid");
var myObject = Context.Session[guid];
```

### [ISerializable](http://storm:2011/%D0%A1%D0%B5%D1%80%D0%B8%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F-LimitFunction.ashx?HL=%D1%81%D0%B5%D1%80%D0%B8%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F#ISerializable_2)
...

### Отказоустойчивость при хранении значений состояния сеанса
Состояние сеанса может завершиться (по умолчанию через 20 минут бездействия), и данные могут быть потеряны. Временем жизни состояния сеанса можно управлять при помощи атрибута timeout раздела конфигурации [sessionState](http://msdn.microsoft.com/ru-ru/library/h6bb9cz9(v=vs.90).aspx).

В зависимости от требований приложения может понадобиться альтернативный состоянию сеанса способ хранения данных каждого пользователя. ASP.NET предоставляет дополнительные параметры для сохранения данных в приложении. Их сравнение содержится в разделе [Рекомендации по управлению состоянием ASP.NET](http://msdn.microsoft.com/ru-ru/library/z1hkazw7(v=vs.90).aspx).