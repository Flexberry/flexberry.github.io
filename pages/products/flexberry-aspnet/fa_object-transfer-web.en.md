---
title: Performance interaction — the transfer of an object between pages
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: en/fa_object-transfer-web.html
lang: en
autotranslated: true
hash: c5d7edbf5b99de83271a8360903a18c20e7e597f4f87046868ca3521affdf710
---

The goal is to pass an object from one page to another. The object is placed in session, this is generated `уникальный идентификатор` (GUID) on which the object is retrieved from session on other page.

`GUID (Globally Unique Identifier)` — statistically unique 128-bit identifier. Its main feature is the uniqueness that allows you to create extensible services and apps without fear of conflicts caused by duplicate IDs.

```csharp
var guid = Guid.NewGuid().ToString();
```

### Transfer Limit Function

In `Page_Load` to create a Limit Function:

```csharp
Function f = FunctionBuilder.BuildEquals("Name", "Name");
```
### Transfer object

To describe the class, to mark it as `Serializable`, create an instance of the class:

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

### Saving object in session

Place the object f to the session key GUID:

```csharp
HttpContext.Current.Session[guid] = f;
```

### The transmission ID in the query string

To create a link on the page

```xml
<asp:HyperLink runat="server" ID="MyLink" Text="Pass an object"></asp:HyperLink>
```

For the link URL, in the parameter `guid` to specify the ID:

```xml
MyLink.NavigateUrl = "~/SessionTest.aspx?guid=" + guid;
```

### Retrieve object from session

On the receiving page to count the GUID from the query string, for him to consider the object from the session:

```csharp
string guid = Request.QueryString.Get("guid");
var myObject = Context.Session[guid];
```

### ISerializable

### Fault tolerance when storing the values in session state

Session state can be completed (by default after 20 minutes of inactivity) and data may be lost. The lifetime of the session state can be managed using the attribute `timeout` configuration section [sessionState](http://msdn.microsoft.com/ru-ru/library/h6bb9cz9(v=vs.90).aspx).

Depending on your application requirements, you may need alternative session state, the data storing method of each user. ASP.NET provides additional options for saving data in the app. Their comparison, see [recommendations for the management of the condition ASP.NET](http://msdn.microsoft.com/ru-ru/library/z1hkazw7(v=vs.90).aspx).



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}