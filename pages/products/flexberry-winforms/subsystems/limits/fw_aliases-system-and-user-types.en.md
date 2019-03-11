--- 
title: Aliases system / user-defined types for parameters in the form of restrictions 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Restrictions 
summary: Examined how to create aliases system/user-defined types for parameters in the form of restrictions using a static delegate 
toc: true 
permalink: en/fw_aliases-system-and-user-types.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: f0b9c3e7a00c7ec67b2a1a3c84c4d79f21fb123f340dc9b869dd5be1968cfbcb 
--- 

## Description 

You can create aliases (aliases, synonyms, types of the Russian language :) system / user-defined types for parameters in the form of constraints. 

To do this in __main()__ method of application we define __static__ delegate __`EditAdvansedFilter1.TypeToString` = ...__, 
with the following signature: 

"`public delegate string TypeToStringDelegate(System.Type Type)`" 

This delegate specifies the method that accepts the input .Net type (system / user) and returns its string representation for display. 
__Method must return "null" if the type match is not found.__ 


## Example usage: 

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
    return "string";

  if (type == typeof(Планета))
    return "Planetamazon";
             
  return null;
}
```


{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}