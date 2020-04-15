---
title: Динамические свойства объекта
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, пример, свойство
summary: Особенности, алгоритмы создания и использования динамических свойств
toc: true
permalink: ru/fo_dynamic-properties.html
lang: ru
---

`DynamicProperties` - экземпляр класса NameObjectCollection (расширение класса [NameObjectCollectionBase](http://msdn.microsoft.com/ru-ru/library/system.collections.specialized.nameobjectcollectionbase.aspx)), который является, по сути, словарем, хранящим пары значений строка-объект.

Динамические свойства помогают хранить дополнительную информацию об объекте и помогают в ситуациях, когда логика использования объекта разнится от обстоятельств (вызов с разных форм, разными пользователями и т.д.).

Динамические свойства __не сохраняются__ в базу.

{% include important.html content="Собственный экземпляр `DynamicProperties` содержится в __каждом__ объекте класса, наследуемого от [DataObject](fo_data-object.html)." %}

## Использование динамических свойств

Обратиться к динамическим свойствам объекта можно следующим образом:

```csharp
dataObject.DynamicProperties
```

`Добавление свойства`

```csharp
var someObject = new object();
dataObject.DynamicProperties.Add("propertyName", someObject);
```

`Проверка на наличие свойства у объекта`

```csharp
if (dataObject.DynamicProperties.ContainsKey("propertyName"))
{
    ...
}
```

`Удаление свойства объекта`

```csharp
dataObject.DynamicProperties.Remove("propertyName");
```

## Основной сценарий использования динамических свойств объекта

Основной сценарий использования на прикладных проектах следующий:

* В динамические свойства объекта добавляется некий флаг.
* В [бизнес-сервере](fo_business-server.html) обновления объекта проверяется наличие этого флага и выполняется или не выполняется набор некоторых действий.

### Пример

![](/images/pages/products/flexberry-orm/additional-features/templates.png)

Объект `СтрокаПланаПогашения`. Например, необходимо ввести шаблон оплаты, содержащий в себе информацию о `КредитнойКарте` и `Кредите`, но с незаполненными полями `ДатыОплаты` и `Суммы`. Однако, в бизнес-сервере обновления объекта уже находится проверка, отвечающая за заполненность этих полей. Следовательно, объект не сможет обновиться и сохраниться в базу.

На форме создания шаблона оплаты перед отправкой объекта на сохранение необходимо добавить динамическое свойство 

```csharp
dataObject.DynamicProperties.Add("Template", true); @@
```

А в бизнес-сервере изменить проверку следующим образом:

```csharp
if (!UpdatedObject.DynamicProperties.ContainsKey("Template"))
{
    // Проверки на обязательность заполненности полей Сумма и ДатаОплаты
}
```
