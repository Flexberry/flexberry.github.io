---
title: Получение метаданных объектов
sidebar: flexberry-orm_sidebar
keywords: DataObject, Flexberry ORM, methods
summary: Методы класса Information
toc: true
permalink: ru/fo_methods-class-information.html
lang: ru
---

Любые метаданные могут быть получены через класс `Information` с набором статических методов.

Ниже приведены наиболее часто используемые методы класса `Information`, сгруппированные по назначению.

## Работа со свойствами объекта (определение значения, загруженности и пр., задание значения)

### GetPropValueByName

__Назначение__: Получить значение свойства объекта данных по имени этого свойства

__Параметры__:

* `obj` - Объект данных, значение свойства которого требуется получить
* `propName` - Имя свойства объекта данных, значение которого требуется получить

__Сигнатура__:

```csharp
static public object GetPropValueByName(DataObject obj, string propName) 
```

### SetPropValueByName

__Назначение__: Установить значение свойства объекта данных по имени этого свойства, значение передаётся строкой. При установке свойства выполняется попытка преобразовать строковое значение в значение соответствующего типа путём вызова статического метода Parse(string) у этого типа.

__Параметры__:

* `obj` - Объект данных, значение свойства которого устанавливается данным методом
* `propName` - Имя свойства объекта данных, значение которого устанавливается данным методом
* `PropValue `- Значение свойства объекта данных, которое будет установлено данным методом

__Сигнатура__:

```csharp
static public void SetPropValueByName(DataObject obj, string propName, string PropValue) 
```

### SetPropValueByName

__Назначение__: Установить значение свойства объекта данных по имени этого свойства, значение передаётся типизированно. Если попытка преобразования типа неудачна, возвращается сообщение об ошибке.

__Сигнатура__:

```csharp
static public void SetPropValueByName(DataObject obj, string propName, object PropValue) 
```

### TrimmedStringStorage

__Назначение__: Обрезать ли строки для данного свойства.

__Параметры__:

* `tp` - тип объекта данных
* `propName` - Имя свойства объекта данных

__Сигнатура__:

```csharp
static public bool TrimmedStringStorage(System.Type tp, string propname) 
```

### IsStoredProperty

__Назначение__: Хранимое ли свойство

__Параметры__:

* `type` - .Net-тип класса объекта данных 
* `propName` - имя свойства 

__Сигнатура__:

```csharp
static public bool IsStoredProperty(Type type, string propName) 
```

### GetPropertyNotNull

__Назначение__: Проверить, установлен ли для указанного .Net-свойства атрибут `NotNullAttribute` 

__Параметры__:

* `type` - .Net-тип класса объекта данных 
* `property` - имя свойства 

__Возвращаемый результат__: true, если установлен, иначе false

__Сигнатура__:

```csharp
static public bool GetPropertyNotNull(System.Type type, string property) 
```

### GetPropertyStrLen

__Назначение__: Получить для указанного .Net-свойства атрибут `StrLenAttribute`. 

__Параметры__:

* `type` - .Net-тип класса объекта данных 
* `property` - имя свойства 

__Возвращаемый результат__: Значение установленного атрибута (-1 если не установлено)

__Сигнатура__:

```csharp
static public int GetPropertyStrLen(System.Type type, string property) 
```

### GetPropertyType

__Назначение__: Получить .Net-тип свойства класса объекта данных по имени этого свойства 

__Параметры__:

* `declarationType` - .Net-тип класса объекта данных
* `propname` - имя свойства 

__Сигнатура__:

```csharp
static public Type GetPropertyType(System.Type declarationType, string propname) 
```

### CheckPropertyExist

__Назначение__: Проверить есть ли такое свойство в указанном типе

__Параметры__:

* `type` - .Net-тип класса объекта данных
* `propName` - Имя свойства

__Возвращаемый результат__:

* `true` - свойство есть,
* `false` - нет

__Сигнатура__:

```csharp
static public bool CheckPropertyExist(System.Type type, string propName)
```

### ExtractPropertyName

__Назначение__: Извлечение свойства внутри текущего класса 

__Параметры__:
    
* `propertyExpression` - Лямбда - выражение для доступа к свойству
* `TSource` - Тип класса - источника 

__Возвращаемый результат__: Имя свойства (одиночное!)

__Сигнатура__:

``` csharp
public static string ExtractPropertyName<TSource>(Expression<Func<TSource>> propertyExpression)
```

См. [Доступ к собственным атрибутам объекта и атрибутам связанных объектов](fo_own-object-attributes.html).

### ExtractPropertyName

__Назначение__: Explicit извлечение свойства по типу

__Параметры__:
    
* `propertyExpression` - Лямбда - выражение для доступа к свойству
* `TSource` - Тип класса - источника 

__Возвращаемый результат__: Имя свойства (одиночное!)

__Сигнатура__:

``` csharp
public static string ExtractPropertyName<TSource>(Expression<Func<TSource, object>> propertyExpression)
```

### ExtractPropertyPath

__Назначение__: Рекурсивный метод получения пути для свойства, заданного через вложенную лямбду.Лямбда-выражение может содержать вложенные обращения к мастерам. 

__Параметры__:
    
* `TProperty` - Тип свойства
* `propertyExpression` - Лямбда - выражение для доступа к свойству

__Возвращаемый результат__: Полный путь к свойству (разделение через точку) 

__Сигнатура__:

``` csharp
public static string ExtractPropertyPath<TProperty>(Expression<Func<TProperty>> propertyExpression)
```

### ExtractPropertyPath

__Назначение__: Рекурсивный метод получения пути для свойства, заданного через вложенную лямбду.Лямбда-выражение может содержать вложенные обращения к мастерам.

__Параметры__:
    
* `propertyExpression` - Лямбда - выражение для доступа к свойству
* `TSource` - Тип класса - источника 

__Возвращаемый результат__: Полный путь к свойству (разделение через точку)  

__Сигнатура__:

``` csharp
public static string ExtractPropertyPath<TSource>(Expression<Func<TSource, object>> propertyExpression)
```

### ExtractPropertyInfo

__Назначение__:  Explicit извлечение свойства по типу

__Параметры__:
    
* `propertyExpression` - Лямбда - выражение для доступа к свойству
* `TSource` - Тип класса - источника 

__Возвращаемый результат__: `PropertyInfo` свойства (самого последнего)

__Сигнатура__:

``` csharp
public static PropertyInfo ExtractPropertyInfo<TSource>(Expression<Func<TSource, object>> propertyExpression) 
```

## Работа с представлениями

### GetView

__Назначение__: Получить представление по его имени и классу объекта данных

__Сигнатура__:

```csharp
static public View GetView(string ViewName, System.Type type) 
```

### GetCompatibleView

__Назначение__: Получить представление, "совместимое" с переданными классами. Ищет общего предка, затем пытается взять у него указанное представление. Если представление не найдено, возвращается null.

__Параметры__:

* `ViewName` - имя представления
* `types` - одномерный массив типов классов данных

__Сигнатура__:

```csharp
static public View GetCompatibleView(string ViewName, System.Type[] types) 
```

### AllViews

__Назначение__: Получить список имён представлений для указанного класса объекта данных

__Параметры__:

`type` - класс объекта данных
 
__Возвращаемое значение__: Массив строк, содержащих имена представлений для указанного типа
 
__Сигнатура__:

```csharp
static public string[] AllViews(System.Type type) 
```

### AllViews

__Назначение__: Получить список имён общих представлений для указанных классов. Речь идёт о ситуации, когда образующие иерархию наследования классы имеют представления, что означает, что имеется множество представлений, общее для некоторого множества классов. Указывая в этот метод это множество классов, Вы и получите имена их общих представлений.

__Сигнатура__:

```csharp
static public string[] AllViews(params System.Type[] types) 
```

### CheckViewForClasses

__Назначение__: Проверить, доступно ли указанное по имени представление во всех перечисленных классах. Речь идёт о ситуации, когда образующие иерархию наследования классы имеют представления, что означает, что имеется множество представлений, общее для некоторого множества классов.

__Сигнатура__:

```csharp
static public bool CheckViewForClasses(string ViewName, params System.Type[] types) 
```

### GetAllTypesFromView

__Назначение__: Вернуть список всех встречающихся в представлении типов, включая детейлы.

__Параметры__:

`view` - Представление
 
__Возвращаемое значение__: Список типов без дублей
    
__Сигнатура__:

```csharp
public static List<Type> GetAllTypesFromView(View view) 
```

### GetAllTypesFromView

__Назначение__: Вернуть список всех встречающихся в представлении типов, включая детейлы и псевдодетейлы. 

__Параметры__:

`view` - Расширенное представление (с псевдодетейлами).

__Возвращаемое значение__: Список типов без дублей
 
__Сигнатура__:

```csharp
public static List<Type> GetAllTypesFromView(ExtendedView view) 
```

## Работа с объектами данных

### GetAlteredPropertyNames

__Назначение__: Сравнить два объекта данных и вернуть список различающихся .Net-свойств. (Объект или свойство с атрибутом `NotStored`проверяться не будет) 

__Параметры__:

* `obj1` - 1-й объект данных
* `obj2` - 2-й объект данных 
* `WithDetailsComparing` - со сравниванием детейловах объектов 
  
__Возвращаемое значение__: одномерный строковый массив имён свойств 

__Сигнатура__:

```csharp
static public string[] GetAlteredPropertyNames(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
```

### GetAlteredProperyNames

__Назначение__: Используйте метод GetAlteredPropertyNames. Оставлен для совместимости(ошибка в имени метода).

### GetAlteredPropertyNamesWithNotStored

__Назначение__: Сравнить два объекта данных и вернуть список различающихся .Net-свойств. (NotStored-атрибуты не игнорируются и тоже проверяются вместе с остальными) 

__Параметры__:

* `obj1` - 1-й объект данных 
* `obj2` - 2-й объект данных 
* `WithDetailsComparing` - со сравниванием детейловах объектов 
   
__Возвращаемое значение__: одномерный строковый массив имён свойств 

__Сигнатура__:

```csharp
static public string[] GetAlteredPropertyNamesWithNotStored(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
```

### ContainsAlteredProps

__Назначение__: Сравнить два объекта данных и вернуть true - если объекты различаются

__Сигнатура__:

```csharp
 static public bool ContainsAlteredProps(DataObject obj1, DataObject obj2, bool WithDetailsComparing) 
```

### CheckNotNullAttributes

__Назначение__: Проверить, нет ли непустых значений в NotNull .Net-свойствах.

__Параметры__:

`dataObject` - объект данных 
 
__Возвращаемое значение__: возвращает null, если непустых значений нет, иначе одномерный строковый массив с именами свойств, где значения есть 

__Сигнатура__:

```csharp
static public string[] CheckNotNullAttributes(DataObject dataObject) 
```

### GetAllPropertyNames

__Назначение__: Вернуть все имена .Net-свойств для .Net-типа класса объекта данных

__Параметры__:

`type` - .Net-тип класса объекта данных
 
__Возвращаемое значение__: одномерный строковый массив имён свойств

__Сигнатура__:

```csharp
static public string[] GetAllPropertyNames(System.Type type) 
```

## Информация о генераторе первичных ключей

### GetKeyGeneratorType

__Назначение__: Получить тип генератора ключей

__Параметры__:

`typeofdataobject` - для какого типа
 
__Сигнатура__:

```csharp
static public System.Type GetKeyGeneratorType(System.Type typeofdataobject) 
```

## Имена хранения объектов

### GetTypeStorageName

__Назначение__: Имя хранилища для типа

__Сигнатура__:

``` csharp
 static public string GetTypeStorageName(System.Type type) 
```

### GetClassStorageName

__Назначение__: Получить имя хранения для .Net-типа класса объекта данных, заданное атрибутом `ClassStorageAttribute`.

__Параметры__:

`type` - .Net-тип класса объекта данных
 
__Возвращаемое значение__: имя хранения в строке

__Сигнатура__:

```csharp
static public string GetClassStorageName(System.Type type) 
```

### GetPrimaryKeyStorageName

__Назначение__: Получить имя хранения первичного ключа, установленное атрибутом `PrimaryKeyStorageAttribute`

__Параметры__:
`type` - .Net-тип класса объекта данных
    
__Сигнатура__:

```csharp
static public string GetPrimaryKeyStorageName(System.Type type) 
```

### GetPropertyStorageName

__Назначение__: Получить имя хранения .Net-свойства, установленное атрибутом `PropertyStorageAttribute`.

__Сигнатура__:

```csharp
static public string GetPropertyStorageName(System.Type type, string property, int index) 
```

Более подробное описание данного метода можно найти в статье [Information.GetPropertyStorageName](fo_information-get-property-storage-name.html).

## Проверка прав на атрибуты объекта

### CheckAccessToAttribute

__Назначение__: Проверка прав на атрибуты объекта. Метод является оберткой для метода CheckAccessToAttribute класса `ICSSoft.STORMNET.RightManager` и используется для проверки прав в Get'ерах вычислимых свойств DataObject. Обработка мастеров не проиводится.

__Параметры__:

* `type` - .Net-тип класса объекта данных
* `propertyName` - Имя свойства объекта данных, на которое проверяются права.
* `deniedAccessValue` - Значение атрибута при отсутствии прав.
   
__Сигнатура__:

```csharp
public static bool CheckAccessToAttribute(Type type, string propertyName, out object deniedAccessValue) 
```

## Работа с изображениями

### GetImageForInstance

__Назначение__: Вернуть картинку для объекта

__Сигнатура__:

```csharp
public static System.Drawing.Image GetImageForInstance(DataObject dobject) 
```

### GetClassImage

__Назначение__: Вернуть картинку для класса, установленную атрибутом `ClassImageFileAttribute`.

__Параметры__:

`dataObjectType` - Объект данных этого класса
 
__Возвращаемое значение__: Картинка

__Сигнатура__:

```csharp
public static System.Drawing.Image GetClassImage(System.Type dataObjectType) 
```

### GetClassImageProperty

__Назначение__: Вернуть свойство-картинку, установленное атрибутом `ClassImagePropertyAttribute` 
  
__Сигнатура__:

```csharp
public static string GetClassImageProperty(System.Type dataobjectType) 
```