---
title: Класс ICSSoft.STORMNET.KeyGen.KeyGuid
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, KeyGen, KeyGuid
toc: true
permalink: ru/fo_key-guid.html
summary: Описание класса KeyGuid.
lang: ru
---

## Класс `ICSSoft.STORMNET.KeyGen.KeyGuid`

### Описание

`KeyGuid` отличается от `System.Guid` наличием метода `Parse` и отсутствием некоторых конструкторов.
Класс расположен в сборке `ICSSoft.STORMNET.DataObject`.

### Преобразование `KeyGuid` в строку

Для того чтобы получить значение `KeyGuid` в строке без скобок:

```cs
string stringKeyGuid = keyGuid.Guid.ToString("D");
//5ed31914-6827-485d-b34d-df1f65a08f47
```

Для того чтобы получить значение `KeyGuid` в строке с фигурными скобками:

```cs
string stringKeyGuid3 = keyGuid.Guid.ToString("B");
//{5ed31914-6827-485d-b34d-df1f65a08f47}
```

[Дополнительная инофрмация](https://msdn.microsoft.com/ru-ru/library/97af8hh4)
