---
title: Наложение ограничения на LookUp в Web
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/fa_lookup-limit-web.html
lang: ru
---

По умолчанию при открытии формы на [LookUp](fa_lookup-overview.html) отображается полный список объектов, из которых необходимо выбрать значение. Однако, зачастую возникает ситуация, когда отображать полный список нельзя.

Следовательно, возникает вопрос: каким образом ограничить выводимые данные при поднятии на LookUp?

## Пример 1

Пусть дана следующая диаграмма.

![](/images/pages/products/flexberry-aspnet/controls/lookup/filter-ex-diagram.png)

При создании нового объекта типа `Кредит` необходимо выбрать `Клиента`, которому выдается данный кредит. Например, что введено ограничение: выдавать кредиты только лицам, проживающим в городе Перми (т.е. если `Прописка` Клиента содержит в себе "Пермь").

Построить ограничение, используя [LINQProvider](fo_linq-provider.html):

```csharp
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Contains("Пермь"));
```

Затем получить ограничивающую функцию, используя класс `LinqToLcs`:

```csharp
Function onlyPermKlients = LinqToLcs.GetLcs(limit.Expression, Клиент.Views.КлиентL).LimitFunction;
```

Ограничить LookUp, установив поле `LimitFunction`:

```csharp
ctrlКлиент.LimitFunction = onlyPermKlients;
```

В результате, при открытии лукап-формы Клиентов с этой страницы будут отображаться только Клиенты, у которых встречается слово "Пермь" в поле "Прописка".

## Пример 2

Более сложная задача: пускай прописка представляет собой отдельный класс, таким образом, необходимо наложить ограничение на мастер мастера:

![](/images/pages/products/flexberry-aspnet/controls/lookup/kredit-diagramm.png)

Дополнительно, необходимо отображать только работающих `КредитныхИнспекторов`.

1.Построить ограничения, испльзуя LINQ-провайдер: 

```csharp
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit1 = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Город == "Пермь");
IQueryable<КредитныйИнспектор> limit2 = ds.Query<КредитныйИнспектор>(КредитныйИнспектор.Views.КредитныйИнспекторL).Where(insp => insp.Работает);
```

2.Получить ограничивающие функции:

```csharp
Function onlyPermKlients = LinqToLcs.GetLcs(limit1.Expression, Клиент.Views.КлиентL).LimitFunction;
Function onlyWorkingInspektors = LinqToLcs.GetLcs(limit2.Expression, КредитныйИнспектор.Views.КредитныйИнспекторL).LimitFunction;
```

3.Устанавливаем ограничения на LookUpы для Клиентов и Инспекторов:

```csharp
ctrlКлиент.LimitFunction = onlyPermKlients;
ctrlКредитныйИнспектор.LimitFunction = onlyWorkingInspektors;
```
