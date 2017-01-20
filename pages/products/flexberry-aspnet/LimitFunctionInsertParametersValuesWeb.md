---
title: Подстановка значений параметров в ограничивающую функцию (в Web)
sidebar: product--sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/limit-function-insert-parameters-values-web.html
folder: product--folder
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:40%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
* '''Продукт''': [Flexberry ASP.NET](flexberry-a-s-p-n-e-t.html)
* '''Компонент''': [Функции ограничения (Limit Function)](limit-function.html)
* '''Программная библиотека''': ICSSoft.STORMNET.Web.Tools.dll
* '''Предназначение''': Описание подстановки параметров в ограничивающую функцию.
</td>
</tr></tbody></table></a>
</div>

# Описание
Для подстановки параметров в функцию в классе `ICSSoft.STORMNET.Web.Tools.AdvLimitUtils` есть метод:
```cs /// <summary>
/// Подставить конкретные значения вместо параметров функции.
/// </summary>
/// <param name="function">Сама функция, в которой будут заменять параметры.</param>
/// <param name="paramValues">Справочник значений параметров (название параметра - значение).</param>  
/// <returns>Функция, в которой все параметры заменены на соответствующие значения.</returns>
public static Function SubstituteParameters(Function function, IDictionary<string, object> paramValues)
``` 

# Пример
```cs ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
 
 // Ограничивающая функция.
 ICSSoft.STORMNET.FunctionalLanguage.Function limitFunction = externalLangDef.GetFunction(
   externalLangDef.funcEQ,
   new ParameterDef("Дата", externalLangDef.DateTimeType, false, "")); 

 // Функция с подставленным значением параметра.
 limitFunction = AdvLimitUtils.SubstituteParameters(
    limitFunction,
    new Dictionary<string, object>() { {"Дата", DateTime.Now} });
```

# Дополнительно
[Подстановка значений параметров в ограничивающую функцию (в Win)](limit-function-insert-parameters-values.html)
