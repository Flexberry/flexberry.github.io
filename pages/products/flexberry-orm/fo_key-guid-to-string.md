---
title: Flexberry ORM ODataService
sidebar: product--sidebar
keywords: Flexberry ORM ODataService, OData
toc: true
permalink: ru/key-guid-to-string.html
folder: product--folder
lang: ru
---

## ICSSoft.STORMNET.DataObject KeyGen

### Класс KeyGuid
Guid, отличающийся от KeyGuid наличием метода Parse и отсутствием половины ненужных конструкторов.

### Преобразование KeyGuid в строку

Для того чтобы получить значение keyGuid в строке без скобок:

```cs
  string stringKeyGuid = keyGuid.Guid.ToString("D");
```

Для того чтобы получить значение keyGuid в строке с фигурными скобками:

```cs
  string stringKeyGuid3 = keyGuid.Guid.ToString("B");
```
