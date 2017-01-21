---
title: Наложение ограничения на LookUp в Веб
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/fa_look-up-limit-web.html
folder: products/flexberry-aspnet/
lang: ru
---

# LookUp'ы
По умолчанию при открытии формы на [LookUp Overview|LookUp] отображается полный список объектов, из которых необходимо выбрать значение. Однако, зачастую возникает ситуация, когда отображать полный список нельзя.

Следовательно, возникает вопрос: каким образом ограничить выводимые данные при поднятии на LookUp?

# Пример 1
Пусть дана следующая диаграмма.
![](/images/pages/img/Filters/FilterExDiagram.PNG)


При создании нового объекта типа `Кредит` необходимо выбрать `Клиента`, которому выдается данный кредит. Предположим, что введено ограничение: выдавать кредиты только лицам, проживающим в городе Перми (т.е. если `Прописка` Клиента содержит в себе "Пермь".

Для начала, построим ограничение, используя [LINQProvider](l-i-n-q-provider.html):

```
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Contains("Пермь"));```
Затем, получим ограничивающую функцию, используя класс `LinqToLcs`:
```
Function onlyPermKlients = LinqToLcs.GetLcs(limit.Expression, Клиент.Views.КлиентL).LimitFunction;
```

Наконец, ограничим LookUp, установив поле `LimitFunction`:

```
ctrlКлиент.LimitFunction = onlyPermKlients;
```

В результате, при открытии лукап-формы Клиентов с этой страницы будут отображаться только Клиенты, у которых встречается слово "Пермь" в поле "Прописка".

# Пример 2
Немного усложним задачу: пускай прописка представляет собой отдельный класс, таким образом, нам необходимо наложить ограничение на мастер мастера:
![](/images/pages/img/Filters/KreditDiagramm.png)

Дополнительно, нам необходимо отображать только работающих `КредитныхИнспекторов`.

1) Строим ограничения, испльзуя LINQ-провайдер: 

```
var ds = (SQLDataService) DataServiceProvider.DataService;
IQueryable<Клиент> limit1 = ds.Query<Клиент>(Клиент.Views.КлиентL).Where(klient => klient.Прописка.Город == "Пермь");
IQueryable<КредитныйИнспектор> limit2 = ds.Query<КредитныйИнспектор>(КредитныйИнспектор.Views.КредитныйИнспекторL).Where(insp => insp.Работает);```

2) Получаем ограничивающие функции:
```
Function onlyPermKlients = LinqToLcs.GetLcs(limit1.Expression, Клиент.Views.КлиентL).LimitFunction;
Function onlyWorkingInspektors = LinqToLcs.GetLcs(limit2.Expression, КредитныйИнспектор.Views.КредитныйИнспекторL).LimitFunction;```

3) Устанавливаем ограничения на LookUpы для Клиентов и Инспекторов:
```
ctrlКлиент.LimitFunction = onlyPermKlients;
ctrlКредитныйИнспектор.LimitFunction = onlyWorkingInspektors;```
