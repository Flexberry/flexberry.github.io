---
title: Enumerated data types (classes with stereotype enumeration)
sidebar: flexberry-designer_sidebar
keywords: Flexberry Designer, Flexberry ORM, an enumeration, a drop-down list, emnumeration, enum, stereotype, generation, example, headings, dropdown
summary: peculiarities of generation of an enumerated type, generating headers for an enumerated type, an example of generating the listing
toc: true
permalink: en/fd_enumerations.html
lang: en
autotranslated: true
hash: 18ea6c410209db77b53552378431f77d48f293659f9226702d86fd8552c40098
---

`Enumeration` - [stereotype](fd_key-concepts.html) the UML class representing the enum type.

The attributes of a UML class are values of an enumerated type.

![Example](/images/pages/products/flexberry-designer/class-diagram/enumeration.jpg)

## Additional editable properties

The window edit properties of enum type looks like the following (two tabs):

Bookmark `Класс`

![Example](/images/pages/products/flexberry-designer/class-diagram/enumpropp1.jpg)

* `Name` - the name of the enum type, what is displayed directly on `UML` class.
* `Description` - if necessary, a detailed description, to enhance the information content of the model in [Flexberry Designer](fd_flexberry-designer.html) is generated in the code on the `.Net`-language `DocComment`;
* `OnlyShowSelectedValue` - not used (reserved for future versions);
* `Packet, NamespacePostfix` - allow to set the Assembly and namespace in which to generate the type, see [assemblies Location after code generation](fo_location-assembly.html).

Bookmark `Возможные значения`

![Example](/images/pages/products/flexberry-designer/class-diagram/enumpropp2.jpg)

Use this page to configure the properties values of an enumerated type. For each property:

* `Name` - value name of an enumerated type, it is displayed directly on a UML class.
* `Description` - ri necessary, a detailed description of the values, to enhance the information content of the model in [Flexberry Designer](fd_flexberry-designer.html) is generated in the code on the `.Net`-language `DocComment`;
* `Caption` - allows you to specify a value that is informative for the user (used in the UI);
* `DefaultValue` - [integer value to an enumerated type](http://msdn.microsoft.com/en-us/library/sbbt4032(v=vs.71).aspx).

## That is generated with the description of the enum type

Generated | Generate SQL DDL Generation .Net language
:--------------|:----------------------------|:----------------------------------------------
The name of the UML class | | Name .Net-type (enumeration)
The name of the attribute UML class | Select the longest name of all in the enum type is considered to be the number of characters field in the table is declared of VARCHAR type(number of characters).Further, data services store the values of an enumerated type as a string constant matching the name | the name of the constant in the enum type
The Caption attribute of a UML class | `Значения enum type stored in the database with the values заголовков` | Generated .Net-attribute [EnumCaption](fo_function-list.html) (`Namespace: ICSSoft.STORMNET Assembly: ICSSoft.STORMNET.DataObject (in ICSSoft.STORMNET.DataObject.dll)`)
Property `DefaultValue` attribute of a UML class | | [an Integer for the value of an enumerated type](http://msdn.microsoft.com/en-us/library/sbbt4032(v=vs.71).aspx) (if not specified otherwise, when editing the fields of an enum type, the default item is displayed with minimal `DefaultValue`, and in the drop down list the elements of an enumerated type will be displayed in ascending order of their `DefaultValue`)

An example generated in C# code enum type:

```csharp
public enum СостояниеОшибки
{
    Черновик,
    Зарегистрирована,
    Оценка,
    Подтверждена,
    Отладка,
    Задержка,
    Повторена,
    AlphaTesting,
    BetaTesting,
    Исправлена,
}
```

## Display values of an enumerated type

The programmer can customize the display of values of an enumerated type in a single select (like `ComboBox`) or mark few (like `RadioButton`). This is done by specifying an attribute `OnlyShowSelectedValueAttribute` specified for an enumerated type.

## The headers are values of an enumerated type

It is often convenient to the values of enumerated types had some title that is understandable to the user. This title is attributed directly `Caption` attribute values of an enumerated type.

Example:

```code

public enum AccessModifier
  {
    [Caption("")]
    Public,
    [Caption("-")]
    Private,
    [Caption("#")]
    Protected
  }
```

`Значения enum type stored in the database with the header values.`

### Empty values

To add an empty value in the enumeration (empty string), you must create a new element and in his `Caption` to put a "tilde" (~):

![Example](/images/pages/products/flexberry-designer/class-diagram/enum-empty.png)

As a result, in the code appears the following entry:

```csharp
[Caption(""))
Пусто,
```

### With headers

The programmer can convert values from `Caption` an enumerated type and back, by calling methods of static class `ICSSoft.STORMNET.EnumCaption`:

* `EnumCaption.GetCaptionFor(object value)`
* `EnumCaption.GetValueFor(string caption, Type enumType)`
* `EnumCaption.TryGetValueFor<TEnum>(string caption, out TEnum enumValue)`



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}