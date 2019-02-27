--- 
title: Substitution of parameter values in the bounding function 
sidebar: flexberry-aspnet_sidebar 
keywords: Flexberry ASP-NET Restrictions 
toc: true 
permalink: en/fa_limit-function-parameters.html 
lang: en 
autotranslated: true 
hash: da24df1d8bd20f6f9ef5e8fc24c173f70f5c4571292a1e9bc7e7800a809aa0d3 
--- 

To pass parameters to a function in the class `ICSSoft.STORMNET.Web.Tools.AdvLimitUtils` there is a method: 

```csharp 
/// <summary> 
/// Substitute a specific value instead of function parameters. 
/// </summary> 
/// <param name="function">the function that will replace the parameters.</param> 
/// <param name="paramValues">the Reference values of parameters (parameter name - value).</param> 
/// <returns>the Function in which all parameters replaced by the appropriate values.</returns> 
public static Function SubstituteParameters(Function function, IDictionary<string, object> paramValues)
``` 

## Example 

```csharp
ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
 
 // The bounding function. 
 ICSSoft.STORMNET.FunctionalLanguage.Function limitFunction = externalLangDef.GetFunction(
   externalLangDef.funcEQ,
   new ParameterDef(Date, externalLangDef.DateTimeType, false, "")); 

 // The function with the substituted value. 
 limitFunction = AdvLimitUtils.SubstituteParameters(
    limitFunction,
    new Dictionary<string, object>() { {Date, DateTime.Now} });
``` 

Additionally ## 

[Substitution of parameter values in the bounding function (Win)](fw_limit-function-insert-parameters-values.html) 



 # Переведено сервисом «Яндекс.Переводчик» http://translate.yandex.ru/