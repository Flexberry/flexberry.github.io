---
title: Стилизация WebObjectListView
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET
toc: true
permalink: ru/fa_wolv-stylization.html
lang: ru
---

У WOLV имеется свойство

```csharp
public IList<TextStylization> Stylizations;
```

Оно дает возможность применять к строкам [WOLV](fa_web-object-list-view.html) определенные css-классы в зависимости от значений в столбцах. Чаще всего используется для раскраски строк.

## Подсветка по строковым полям

Есть возможность раскрашивать строки, используя лямбды.

Пример:

```csharp
var stylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "PKОценившихЭкспертов";

var currUserPK = Utils.GetCurrentUser().__PrimaryKey.ToString().Replace("{", string.Empty).Replace("}", string.Empty);

stylization.LambdaStyle =
    val =>
    string.IsNullOrEmpty(val) || !val.ToUpper().Contains(currUserPK.ToUpper())
        ? "wolv-current-user"
        : null;

WebObjectListView1.Stylizations.Add(stylization);
```

Пример: cписок, у которого есть столбец `Семья.КоличЧленовСемьи`. Нужно раскрасить зеленым, если членов семьи 2, коричневым - 3, черным - 4.

```csharp
var two = new TextStyle("2", "WOLV-color-Green");
var three = new TextStyle("3", "WOLV-color-Brown");
var four = new TextStyle("4", "WOLV-color-Black");

WebObjectListView1.Stylizations.Add(new TextStylization(
    "Семья.КоличЧленовСемьи",
    new[] { two, three, four });
```

CSS:

```css
tr.WOLV-color-Green td{
    color: green !important;
}

tr.WOLV-color-Brown td{
    color: brown !important;
}

tr.WOLV-color-Black td{
    color: black !important;
}
```

![](/images/pages/products/flexberry-aspnet/controls/wolv/stylization.png)


## Несколько стилей

Следует обратить внимание на то, что если к строке применено несколько стилей, задающих одно и то же свойство (например, цвет), то будет применен тот стиль, который наиболее поздно описан в .css файле.

Если изменить приведенный выше пример, добавив еще один стиль:

```csharp
var countStylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "PKОценившихЭкспертов";

var currUserPK = Utils.GetCurrentUser().__PrimaryKey.ToString().Replace("{", string.Empty).Replace("}", string.Empty);

countStylization.LambdaStyle =
    val =>
    string.IsNullOrEmpty(val) || !val.ToUpper().Contains(currUserPK.ToUpper())
        ? "wolv-current-user"
        : null;

WebObjectListView1.Stylizations.Add(countStylization);

var regionStylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "ОрганУчета";

regionStylization.LambdaStyle =
    val =>
    !string.IsNullOrEmpty(val) && val.Equals(“Краснокамский район”)
        ? "wolv-color-Red"
        : null;

WebObjectListView1.Stylizations.Add(regionStylization);
```

и если в .css-файле стиль `wolv-color-Red` описан после остальных, то в результате:

![](/images/pages/products/flexberry-aspnet/controls/wolv/stylization1.png)

## Подсветка по полям типа `bool`

Для раскрашивания строк по полю типа `bool` используйте лямбда-выражение:

```csharp
var stylization = new TextStylization(Information.ExtractPropertyPath<ТипСПолемBool>(x => x.ПолеТипаBool),
                          new TextStyle[0])
{
    LambdaStyle =
        val =>
        !string.IsNullOrEmpty(val) && val.ToUpper().Contains("TRUE")
            ? "WOLV-color-Green"
            : "WOLV-color-Black"
};
WebObjectListView1.Stylizations.Add(stylization);
```

Если нужно раскрасить конкретную ячейку, то необходимо встраивать web-контрол. Web-контрол должен реализовывать интерфейс `ICSSoft.STORMNET.Web.Tools.WOLVFeatures.IWebObjectListViewCompatible`. В свойстве `TableCellCssClass` возвращается класс, который применится к ячейке с заданным web-контролом.

## Настройка внешнего вида таблиц в теме BlueSky

В тему `BlueSky` были добавлены 2 варианта раскраски таблиц:

* Вид таблицы по умолчанию:

![](/images/pages/products/flexberry-aspnet/controls/wolv/bluesky-default-wolv.png)

* Классический вид таблицы:

![](/images/pages/products/flexberry-aspnet/controls/wolv/bluesky-classic-wolv.png)

По умолчанию используется новый стиль с вертикальной зеброй, для того чтобы изменить раскраску на классическую нужно в `_VariablesBasic.less` изменить значение переменной `@BlueSkyTableStyle` на false.

{% include important.html content="При этом, также изменится и стиль таблиц AGE" %}
