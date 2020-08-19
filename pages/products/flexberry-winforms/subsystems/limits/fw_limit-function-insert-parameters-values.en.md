--- 
title: Substitution of parameter values in the bounding function 
sidebar: flexberry-winforms_sidebar 
keywords: Flexberry Winforms, Restrictions 
summary: the example shown in limitFunction with a parameter of type date to insert the value of this parameter is the current date 
toc: true 
permalink: en/fw_limit-function-insert-parameters-values.html 
folder: products/flexberry-winforms/ 
lang: en 
autotranslated: true 
hash: 8118150672e82127c73cc5b998af48c869eee61bdc6e028af3f0c15e64db9337 
--- 

## Question 
There limitFunction](fo_limit-function.html) (class `ICSSoft.STORMNET.FunctionalLanguage.Function`) with parameter @date. How is this parameter to substitute the current date? 

## Response 
Let `limitFunction` set function with a parameter @date: 
```csharp 
ExternalLangDef externalLangDef = ExternalLangDef.LanguageDef;
 
 ICSSoft.STORMNET.FunctionalLanguage.Function limitFunction = externalLangDef.GetFunction(
   externalLangDef.funcEQ,
   new ParameterDef(Date, externalLangDef.DateTimeType, false, ""),
   new DateTime(2000, 01, 01));
``` 

Read more about [ExternalLangDef](fo_external-lang-def.html). 

### For Web 
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

### Example 

```csharp 
    limitFunction = AdvLimitUtils.SubstituteParameters(
    limitFunction,
    new Dictionary<string, object>() { {Date, DateTime.Now} });
``` 

### For Win 
For parameter substitution in the bounding function in the class `ICSSoft.STORMNET.Windows.Forms.AdvansedLimitExtension` there is a method: 
```csharp 
/// <summary> 
 /// Get restriction with the substituted parameter values 
 /// </summary> 
 /// <param name="advansedLimit">the constraint.</param> 
 /// <param name="parValues">a Dictionary with parameter values. Key - the parameter name</param> 
 /// <returns>the Function-restriction with the substituted values of the parameters</returns> 
 public static Function GetCustomizedFunctionByParameters<R, T>(this R advansedLimit, T parValues)
     where T : IDictionary, IDictionary<string, object>
     where R : AdvansedLimit
``` 

But for this method you need an instance of the class `ICSSoft.STORMNET.Windows.Forms.AdvansedLimit` (object model limitations includes both the bounding function and parameter setting). 

### Example 
```csharp 
 AdvansedLimit advlimit = new AdvansedLimit {Function = limitFunction};
 advlimit.Parameters = new ParameterDef[1] { (ParameterDef)advlimit.Function.Parameters[0] };
 limitFunction = AdvansedLimitExtension.GetCustomizedFunctionByParameters(
    advlimit,
    new Dictionary<string, object>() { { Date, DateTime.Now } });
``` 



{% include callout.html content="Переведено сервисом «Яндекс.Переводчик» <http://translate.yandex.ru>" type="info" %}