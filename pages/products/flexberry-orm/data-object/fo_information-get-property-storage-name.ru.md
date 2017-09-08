---
title: Information.GetPropertyStorageName
sidebar: flexberry-orm_sidebar
keywords: Объекты данных, Flexberry ORM, базы данных
summary: Особенности использования метода GetPropertyStorageName класса Information
toc: true
permalink: ru/fo_information-get-property-storage-name.html
lang: ru
---

Статический метод `GetPropertyStorageName` класса [Information](fo_methods-class-information.html) позволяет получить [имя хранения .Net-свойства, установленное атрибутом `PropertyStorageAttribute`](fo_storing-data-objects.html).

### Метод без использования индекса

Метод, не требующий указания индекса при вызове, имеет следующий вид:

``` csharp
/// <param name="type">.Net-тип класса объекта данных</param>
/// <param name="property">имя свойства</param>
/// <returns>имя хранения</returns>
static public string GetPropertyStorageName(System.Type type, string property)
```

Данный метод необходимо применять, если атрибут свойства `PropertyStorageAttribute` имеет вид:

``` csharp
[PropertyStorage("TimeQu"))
```

### Метод с использованием индекса

Метод, требующий указания индекса при вызове, имеет следующий вид:

``` csharp
/// <param name="type">.Net-тип класса объекта данных</param>
/// <param name="property">имя свойства</param>
/// <param name="index">индекс в множестве</param>
/// <returns>имя хранения</returns>
static public string GetPropertyStorageName(System.Type type, string property, int index)
```

Данный метод необходимо применять, если при объявлении атрибута свойства `PropertyStorageAttribute` используется массив имён:

``` csharp
PropertyStorage(new string[]) {"ПростоКласс"}))
```

В этом случае параметр `index` определит номер элемента из массива имён, определённого при объявлении атрибута свойства `PropertyStorageAttribute`, который будет возвращён методом.

### Ошибочное использование Information.GetPropertyStorageName

* Если атрибут `PropertyStorageAttribute` был объявлен без использования массива, но используется вызов `Information.GetPropertyStorageName` с индексом, то будет вызвано исключение.
* Если атрибут `PropertyStorageAttribute` был объявлен с использования массива, но используется вызов `Information.GetPropertyStorageName` без индекса, то будет возвращена пустая строка.

## Особенности генерации атрибута свойств PropertyStorage

* Атрибут `Storage`, заданный для [свойств классов](fo_attributes-class-data.html), после генерации будет представлен в виде объявления атрибута `PropertyStorage` без массива.
* Атрибуты `Aggregator Storage` [детейловой связи](fo_detail-associations-properties.html) и `Storage` [мастеровой связи](fd_master-association.html) после генерации будут представлены в виде объявления атрибута `PropertyStorage` без массива, если выполнены следующие условия:
  * Storage-атрибут содержит только одно непустое значение.
  * Атрибут [TypeUsage](fo_type-usage-problem.html) содержит более одного значения.
