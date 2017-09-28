---
title: Алиасы системных / пользовательских типов для параметров на форме ограничений
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Ограничения
toc: true
permalink: ru/fw_aliases-system-and-user-types.html
folder: products/flexberry-winforms/
lang: ru
---

## Описание

    Сейчас стало доступным создавать алиасы (псевдонимы, синонимы, типы русским языком :) системных / пользовательских типов для параметров на форме ограничений.

    Для этого в '''main()''' методе приложения мы определяем '''статический''' делегат '''`EditAdvansedFilter1.TypeToString` = ...''', 
имеющий следующую сигнатуру: 
''`public delegate string TypeToStringDelegate(System.Type type)`''

    В этом делегате задается метод, который принимает на вход .Net тип (системный / пользовательский), и возвращает его строковое представление для отображения.
'''Метод должен возвратить ''null'', если для данного типа соответствие не установлено.
''' 
----
### Пример использования:

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