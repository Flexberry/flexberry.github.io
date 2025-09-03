---
title: Указание текущей даты
sidebar: guide-practical-guides_sidebar
keywords: guide
toc: true
permalink: ru/gpg_date-time-now.html
lang: ru
---

Цель: в атрибут типа `DateTime` по умолчанию должна проставляться текущая дата.

Для того, чтобы текущая дата проставлялась автоматически необходимо:

1.Написать на диаграмме инициализирующее значение следующим образом

![](/images/pages/guides/flexberry-aspnet/date-now.png)

2.Перегенерировать объекты.

3.Проверить, что настраиваемый атрибут типа `DateTime` в исходном коде класса определен как `System.DateTime.Now`.
Например, атрибут `ДатаЗаполнения` класса `Документ`:

```csharp
private System.DateTime fДатаЗаполнения = System.DateTime.Now;
```

__Примечание:__ Согласно `C#` инициализаторы равнозначны конструктору класса по-умолчанию, поэтому при каждом конструировании нового `Документа` и любого его наследника будет проставляться текущее время.

## Перейти

* <i class="fa fa-arrow-left" aria-hidden="true"></i> [Наложение ограничения на LookUp](gpg_limitfunction-for-lookup.html)
* [Автоматическое вычисление](gpg_auto-calculation.html) <i class="fa fa-arrow-right" aria-hidden="true"></i> 
