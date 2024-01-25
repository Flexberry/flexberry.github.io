---
title: Подстановка значений параметров в ограничивающую функцию
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Ограничения
toc: true
permalink: ru/fa_limit-function-parameters.html
lang: ru
---

Для подстановки параметров в функцию в классе `ICSSoft.STORMNET.Web.Tools.AdvLimitUtils` есть метод:

```csharp
/// <summary>
/// Подставить конкретные значения вместо параметров функции.
/// </summary>
/// <param name="function">Сама функция, в которой будут заменять параметры.</param>
/// <param name="paramValues">Справочник значений параметров (название параметра - значение).</param>  
/// <returns>Функция, в которой все параметры заменены на соответствующие значения.</returns>
public static Function SubstituteParameters(Function function, IDictionary<string, object> paramValues)
```

## Пример

```csharp
IUnityContainer mainUnityContainer = ...; // Получение основного контейнера для работы с Unity.
IDataService ds = mainUnityContainer.Resolve<IDataService>();
ExternalLangDef languageDef = new ExternalLangDef(ds)
 
 // Ограничивающая функция.
 ICSSoft.STORMNET.FunctionalLanguage.Function limitFunction = languageDef.GetFunction(
   languageDef.funcEQ,
   new ParameterDef("Дата", languageDef.DateTimeType, false, "")); 

 // Функция с подставленным значением параметра.
 limitFunction = AdvLimitUtils.SubstituteParameters(
    limitFunction,
    new Dictionary<string, object>() { {"Дата", DateTime.Now} });
```

## Дополнительно

[Подстановка значений параметров в ограничивающую функцию (в Win)](fw_limit-function-insert-parameters-values.html)
