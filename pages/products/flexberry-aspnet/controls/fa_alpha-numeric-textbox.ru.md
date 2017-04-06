---
title: AlphaNumericTextBox
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_alpha-numeric-textbox.html
lang: ru
---

Контрол `ICSSoft.STORMNET.Web.AjaxControls.AlphaNumericTextBox` предназначен для полей, где допустимы только буквы и/или числа. Наследует `System.Web.UI.WebControls.TextBox`, реализует `ICSSoft.STORMNET.Web.AjaxControls.IAjaxGroupEditCompatible`.  
Поддерживает вставку значений из буфера. 

## Настройка

Настройка свойств осуществляется при загрузке страницы в методе `PreApplyToControls()`.

### Тип

Для задания типа ввода используется свойство `Type` типа `AlphaNumericType`:

```csharp
/// <summary>
/// Перечисление с типами ввода для контрола AlphaNumericTextBox
/// </summary>
public enum AlphaNumericType
{
	/// <summary>
	/// Буквенный
	/// </summary>
	Alpha, 

	/// <summary>
	/// Числовой
	/// </summary>
	Numeric, 
	
	/// <summary>
	/// Буквенно-числовой
	/// </summary>
	AlphaNumeric
}
```

### Интервал

Можно задать допустимый интервал вводимых чисел: `Min` и `Max`. По умолчанию, можно вводить числа из диапазона `int32`, то есть можно использовать как положительные, так и отрицательные значения.

### Алфавиты

В поля `NumericChars` и `AlphabeticChars` можно вводить символы допустимого алфавита цифр и букв соответственно. Символы, не попадающие в эти алфавиты, не будут вводиться в данный контрол.

### Инициализация

Для инициализации свойств контрола можно использовать статическое свойство-делегат `InitSettings` типа [InitControlSettingsDelegate<>](fa_init-control-settings-delegate.html), который будет вызван в конструкторе контрола.

### Особенности

При отключенном `ViewState` контрол не сохраняет значения между PostBack-ами, если он находится в состоянии `Disabled` (из-за особенностей инфраструктуры ASP.NET).

## Примеры

Следующий код устанавливает тип контрола в `Numeric` (что позволит вводить в него только цифры), а также устанавливает словарь допустимых символов (что позволит вводить в него только цифры 0, 1 и 2):

```csharp
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.NumericChars = "012";
```

Следующий код устанавливает минимальное и максимальное значение, допустимое для ввода:

```csharp
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.Min = 1200;
ctrlAlpha.Max = 1300;
```

Следующий код устанавливает тип в `AlphaNumeric` (что позволит вводить и буквы и цифры), а также устанавливает словари допустмых символов. Таким образом, в контрол можно будет вводить только цифры 1 и 2, а также буквы 'a' и 'b':

```csharp
ctrlAlpha.Type = AlphaNumericType.AlphaNumeric;
ctrlAlpha.NumericChars = "12";
ctrlAlpha.AlphabeticChars = "ab";
```
