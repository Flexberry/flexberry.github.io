---
title: Подстановка значений параметров в ограничивающую функцию
sidebar: flexberry-winforms_sidebar
keywords: Flexberry Winforms, Ограничения
summary: На примере показано как в limitFunction с параметром типа Дата вставить в качестве значения этого параметра текущую дату
toc: true
permalink: ru/fw_limit-function-insert-parameters-values.html
folder: products/flexberry-winforms/
lang: ru
---

## Вопрос
Имеется [limitFunction](fo_limit-function.html) (класс `ICSSoft.STORMNET.FunctionalLanguage.Function`) с параметром @Дата. Как вместо этого параметра подставить текущую дату?

## Ответ
Пусть задана функция `limitFunction` с параметром @Дата:
```csharp 
ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
 
 ICSSoft.STORMNET.FunctionalLanguage.Function limitFunction = externalLangDef.GetFunction(
   externalLangDef.funcEQ,
   new ParameterDef("Дата", externalLangDef.DateTimeType, false, ""),
   new DateTime(2000, 01, 01));
```

Подробнее о [ExternalLangDef](fo_external-lang-def.html).

### Для Web
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

### Пример

```csharp 
    limitFunction = AdvLimitUtils.SubstituteParameters(
    limitFunction,
    new Dictionary<string, object>() { {"Дата", DateTime.Now} });
```

### Для Win
Для подстановки параметров в ограничивающую функцию в классе `ICSSoft.STORMNET.Windows.Forms.AdvansedLimitExtension` есть метод:
```csharp 
/// <summary>
 /// Получить ограничение с подставленными значениями параметров
 /// </summary>
 /// <param name="advansedLimit">Само ограничение.</param>
 /// <param name="parValues">Словарь со значениями параметров. Ключ - имя параметра</param>
 /// <returns>Функция-ограничение с подставленными значениями параметров</returns>
 public static Function GetCustomizedFunctionByParameters<R, T>(this R advansedLimit, T parValues)
     where T : IDictionary, IDictionary<string, object>
     where R : AdvansedLimit
```

Но для этого метода нужен экземпляр класса `ICSSoft.STORMNET.Windows.Forms.AdvansedLimit` (объектная модель ограничения, включает в себя как ограничивающую функцию, так и определения параметров). 

### Пример
```csharp 
 AdvansedLimit advlimit = new AdvansedLimit {Function = limitFunction};
 advlimit.Parameters = new ParameterDef[1] { (ParameterDef)advlimit.Function.Parameters[0] };
 limitFunction = AdvansedLimitExtension.GetCustomizedFunctionByParameters(
    advlimit,
    new Dictionary<string, object>() { { "Дата", DateTime.Now } });
```
