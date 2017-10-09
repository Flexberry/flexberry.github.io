---
title: Создание пользовательского типа данных
sidebar: flexberry-orm_sidebar
keywords: Flexberry ORM, типы данных, пример
summary: Пример создания стереотипа typedef
toc: true
permalink: ru/fo_creating-stereotypes-example.html
lang: ru
---

Существует возможность определить собственный тип на диаграмме Flexberry. Для этого следует разместить новый класс на диаграмме и поменять его [стереотип на "typedef"](fd_typedef.html).
Как результат, данный тип можно будет использовать как атрибут для свойств классов во всей стадии Flexberry.

Однако, этот тип является лишь псевдонимом, и используется только на уровне абстракции при моделировании.

Необходимо разрешить тип данного "typedef", указав соответствующие типы в C# и SQL на специальной [карте типов Flexberry](fd_types-map.html).

К примеру, если посмотреть на класс String4000 (Flexberry sample repository, Entities diagram), то видно, что он маппится на классы System.String и VARCHAR(4000).

Как пример сгенерированного свойства, следует обратить внимание на Description класса CD:

```csharp
[StrLen(4000)]
public virtual string Description
{
        get {...}
        set {...}
}
```

Полный список примеров кода Flexberry ORM находится в статье [Примеры кода](fo_code-samples.html).
