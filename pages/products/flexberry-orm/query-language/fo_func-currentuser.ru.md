---
title: FuncCurrentUser
sidebar: flexberry-orm_sidebar
keywords: CurrentUser, Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncCurrentUser
toc: true
permalink: ru/fo_func-currentuser.html
lang: ru
---

`FuncCurrentUser` - функция из [ExternalLangDef](fo_external-lang-def.html), служащая для того, чтобы в ограничении использовать имя текущего пользователя (`FriendlyName`).

## Использование

Для применения данной функции в используемом сервисе данных [`SQLDataService`](fo_sql-data-service.html) необходимо означить свойство `ICurrentUser CurrentUser`, тогда при формировании ограничения будет использовано `CurrentUser.FriendlyName`.

```csharp
if (value.FunctionDef.StringedView == "CurrentUser")
{
    if (CurrentUser != null)
    {
        return string.Format("'{0}'", CurrentUser.FriendlyName);
    }
    else
    {
        throw new InvalidOperationException("Property CurrentUser is not defined for this data service. Add initialization for this property.");
    }
}
```

### Пример использования

Ниже представлен пример применения функции `funcCurrentUser`. Здесь среди записей типа `Лес` ищется та, где `Лес.Название` равно `FriendlyName` текущего пользователя.

```csharp
SQLDataService ds = dataService as SQLDataService;
ExternalLangDef languageDef = new ExternalLangDef(ds);
LoadingCustomizationStruct lcs = LoadingCustomizationStruct.GetSimpleStruct(typeof(Лес), Лес.Views.ЛесE);

lcs.LimitFunction = languageDef.GetFunction(
	languageDef.funcEQ,
	new VariableDef(languageDef.StringType, nameof(Лес.Название)),
	languageDef.GetFunction(languageDef.funcCurrentUser)); // Задание ограничения.

ICurrentUser currentUser = new TestCurrentUser();
ds.CurrentUser = currentUser;
DataObject[] foundObjects = ds.LoadObjects(lcs);
```

В данном примере использован следующий класс.
```csharp
private class TestCurrentUser : ICurrentUser
{
    public string Login { get => throw new NotImplementedException(); set => throw new NotImplementedException(); }

    public string Domain { get => throw new NotImplementedException(); set => throw new NotImplementedException(); }

    public string FriendlyName { get => "Great One"; set => throw new NotImplementedException(); }
}
```

В реальном примере требуется задание логики определения текущего пользователя.
