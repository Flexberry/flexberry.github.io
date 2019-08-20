---
title: Межформенное взаимодействие — передача объекта между страницами
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_object-transfer-web.html
lang: ru
---

Цель — передать объект с одной страницы на другую. Объект помещается в сессию, для этого генерируется `уникальный идентификатор` (GUID), по которому объект извлекается из сессии на другой странице.

`GUID (Globally Unique Identifier)` — статистически уникальный 128-битный идентификатор. Его главная особенность — уникальность, которая позволяет создавать расширяемые сервисы и приложения без опасения конфликтов, вызванных совпадением идентификаторов.

```csharp
var guid = Guid.NewGuid().ToString();
```

### Передача Limit Function

В `Page_Load` создать Limit Function:

```csharp
Function f = FunctionBuilder.BuildEquals("Name", "Имя");
```
### Передача объекта

Описывать класс, отметить его как `Serializable`, создать экземпляр класса:

```csharp
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

Поместить объект f в сессию по ключу GUID:

```csharp
HttpContext.Current.Session[guid] = f;
```

### Передача идентификатора в строке запроса

Создать ссылку на странице

```xml
<asp:HyperLink runat="server" ID="MyLink" Text="Передать объект"></asp:HyperLink>
```

Для ссылки указать URL, в параметре `guid` указать идентификатор:

```xml
MyLink.NavigateUrl = "~/SessionTest.aspx?guid=" + guid;
```

### Получение объекта из сессии

На принимающей странице считать GUID из строки запроса, по нему считать объект из сессии:

```csharp
string guid = Request.QueryString.Get("guid");
var myObject = Context.Session[guid];
```

### ISerializable

### Отказоустойчивость при хранении значений состояния сеанса

Состояние сеанса может завершиться (по умолчанию через 20 минут бездействия), и данные могут быть потеряны. Временем жизни состояния сеанса можно управлять при помощи атрибута `timeout` раздела конфигурации [sessionState](http://msdn.microsoft.com/ru-ru/library/h6bb9cz9(v=vs.90).aspx).

В зависимости от требований приложения может понадобиться альтернативный состоянию сеанса способ хранения данных каждого пользователя. ASP.NET предоставляет дополнительные параметры для сохранения данных в приложении. Их сравнение содержится в разделе [Рекомендации по управлению состоянием ASP.NET](http://msdn.microsoft.com/ru-ru/library/z1hkazw7(v=vs.90).aspx).
