--- 
title: Styling WebObjectListView 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET 
toc: true 
permalink: en/fa_wolv-stylization.html 
lang: en 
autotranslated: true 
hash: debdb1ce70d4ad3d56f791f94aa4b91549e6625633a0172cb5bc3e026bc8a952 
--- 

WOLV do you have a property 

```csharp
public IList<TextStylization> Stylizations;
``` 

It gives the opportunity to apply to rows [WOLV](fa_web-object-list-view.html) some css classes depending on the values in the columns. Most often used for coloring the lines. 

## lights on string fields 

There is an opportunity to paint a line using lambda. 

Example: 

```csharp
var stylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "Scottishfalsetto";

var currUserPK = Utils.GetCurrentUser().__PrimaryKey.ToString().Replace("{", string.Empty).Replace("}", string.Empty);

stylization.LambdaStyle =
    val =>
    string.IsNullOrEmpty(val) || !val.ToUpper().Contains(currUserPK.ToUpper())
        ? "wolv-current-user"
        : null;

WebObjectListView1.Stylizations.Add(stylization);
``` 

Example: a list that has a column `Семья.КоличЧленовСемьи`. Color it green if family members 2, brown - 3, black - 4. 

```csharp
var two = new TextStyle("2", "WOLV-color-Green");
var three = new TextStyle("3", "WOLV-color-Brown");
var four = new TextStyle("4", "WOLV-color-Black");

WebObjectListView1.Stylizations.Add(new TextStylization(
    "Family.Clicklesley",
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


## Several styles 

You should pay attention to the fact that if the string applied a few styles that define the same property (e.g., color), will be applied to the style described in the most late .css file. 

If you change the above example by adding another style: 

```csharp
var countStylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "Scottishfalsetto";

var currUserPK = Utils.GetCurrentUser().__PrimaryKey.ToString().Replace("{", string.Empty).Replace("}", string.Empty);

countStylization.LambdaStyle =
    val =>
    string.IsNullOrEmpty(val) || !val.ToUpper().Contains(currUserPK.ToUpper())
        ? "wolv-current-user"
        : null;

WebObjectListView1.Stylizations.Add(countStylization);

var regionStylization = new TextStylization(string.Empty, new TextStyle[0]);
stylization.ColumnName = "Organoclay";

regionStylization.LambdaStyle =
    val =>
    !string.IsNullOrEmpty(val) && val.Equals(“Краснокамский район”)
        ? "wolv-color-Red"
        : null;

WebObjectListView1.Stylizations.Add(regionStylization);
``` 

and if .css file style `wolv-color-Red` described after the other, the result is: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/stylization1.png) 

## Backlight type fields `bool` 

For painting lines on the field type `bool` use a lambda expression: 

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

If you want to color a specific cell, you must embed a web control. The Web control must implement the interface `ICSSoft.STORMNET.Web.Tools.WOLVFeatures.IWebObjectListViewCompatible`. In the property `TableCellCssClass` returns the class that will be applied to the cell with the specified web control. 

## customize the appearance of tables in the theme BlueSky 

The topic `BlueSky` was added 2 version of the coloring table: 

* The default table: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/bluesky-default-wolv.png) 

* Classic form of a table: 

![](/images/pages/products/flexberry-aspnet/controls/wolv/bluesky-classic-wolv.png) 

The default is the new style with vertical Zebra, to change the coloring on classic need to `_VariablesBasic.less` to change the value of the variable `@BlueSkyTableStyle` to false. 

{% include important.html content="At the same time, also will change the table style AGE" %} 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}