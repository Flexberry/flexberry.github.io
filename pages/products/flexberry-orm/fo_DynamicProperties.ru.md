---
title: Dynamic Properties
sidebar: flexberry-orm_sidebar
keywords: DataObject (объекты данных), Flexberry ORM, Public
toc: true
permalink: ru/fo_dynamic-properties.html
---
* **Продукт:** [Flexberry ORM](fo_flexberry-o-r-m.html)
* **Компонент:** [Объект данных](fo_dataobject.html)
* **Программная библиотека:** ICSSoft.STORMNET.DataObject.dll
* **Предназначение:** `DynamicProperties` (динамические свойства объекта) является, по сути, словарем, хранящим пары значений строка-объект. Динамические свойства помогают хранить дополнительную информацию об объекте.

## DynamicProperties (динамические свойства объекта)

`DynamicProperties` - экземпляр класса NameObjectCollection (расширение класса [NameObjectCollectionBase](http://msdn.microsoft.com/ru-ru/library/system.collections.specialized.nameobjectcollectionbase.aspx)), который является, по сути, словарем, хранящим пары значений строка-объект.

Динамические свойства помогают хранить дополнительную информацию об объекте и помогают в ситуациях, когда логика использования объекта разнится от обстоятельств (вызов с разных форм, разными пользователями и т.д.).

Динамические свойства __не сохраняются__ в базу.


% include important.html content="Собственный экземпляр `DynamicProperties` содержится в __каждом__ объекте класса, наследуемого от [DataObject](fo_dataobject.html)." %}

## Использование динамических свойств

Обратиться к динамическим свойствам объекта можно следующим образом:

```cs
dataObject.DynamicProperties @@
```

`Добавление свойства`

```cs
var someObject = new object();
dataObject.DynamicProperties.Add("propertyName", someObject);
```

`Проверка на наличие свойства у объекта`

```cs
if (dataObject.DynamicProperties.ContainsKey("propertyName"))
{
    ...
}
```

`Удаление свойства объекта`

```cs
dataObject.DynamicProperties.Remove("propertyName"); @@
```

## Основной сценарий использования динамических свойств объекта

Основной сценарий использования на прикладных проектах следующий:

* В динамические свойства объекта добавляется некий флаг.
* В [бизнес-сервере](fo_business--servers--wrapper--business--facade.html) обновления объекта проверяется наличие этого флага и выполняется или не выполняется набор некоторых действий.

### Пример

![image](/images/pages/products/flexberry-orm/dynamic-properties/Templates.PNG)

Рассмотрим объект `СтрокаПланаПогашения`. Предположим, что мы хотим ввести шаблон оплаты, содержащий в себе информацию о `КредитнойКарте` и `Кредите`, но с незаполненными полями `ДатыОплаты` и `Суммы`. Однако, в [бизнес-сервере](fo_business--servers--wrapper--business--facade.html) обновления объекта уже находится проверка, отвечающая за заполненность этих полей => объект не сможет обновиться и сохраниться в базу.

На форме создания шаблона оплаты перед отправкой объекта на сохранение необходимо добавить динамическое свойство 

```cs
dataObject.DynamicProperties.Add("Template", true); @@
```

А в бизнес-сервере изменить проверку следующим образом:

```cs
if (!UpdatedObject.DynamicProperties.ContainsKey("Template"))
{
    // Проверки на обязательность заполненности полей Сумма и ДатаОплаты
}
```


 






