---
title: Алиасы системных / пользовательских типов для параметров на форме ограничений
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Ограничения
summary: Рассмотрено как создавать алиасы системных/пользовательских типов для параметров на форме ограничений с помощью статического делегата
toc: true
permalink: ru/fw_aliases-system-and-user-types.html
folder: products/flexberry-winforms/
lang: ru
---

## Описание

Существует возможность создавать алиасы (псевдонимы, синонимы, типы русским языком :) системных / пользовательских типов для параметров на форме ограничений.

Для этого в __main()__ методе приложения мы определяем __статический__ делегат __`EditAdvansedFilter1.TypeToString` = ...__, 
имеющий следующую сигнатуру: 

''`public delegate string TypeToStringDelegate(System.Type type)`''

В этом делегате задается метод, который принимает на вход .Net тип (системный / пользовательский), и возвращает его строковое представление для отображения.
__Метод должен возвратить ''null'', если для данного типа соответствие не установлено.__


## Пример использования:

```csharp
static void Main()
{
  ...
  EditAdvansedFilter1.TypeToString = MyStringViewForSpecificType;
  ...
}

private static string MyStringViewForSpecificType(Type type)
{
  if (type == typeof(string))
    return "строка";

  if (type == typeof(Планета))
    return "ПланетаОбезьян";
             
  return null;
}
```