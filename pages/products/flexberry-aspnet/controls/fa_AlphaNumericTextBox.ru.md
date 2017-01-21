---
title: AlphaNumericTextBox
sidebar: flexberry-aspnet_sidebar
keywords: Flexberry ASP-NET, Web UI (Контролы)
toc: true
permalink: ru/fa_alpha-numeric-text-box.html
folder: products/flexberry-aspnet/
lang: ru
---

<div style="margin:5px; padding-left:28px; float:right; width:60%; outline:1px solid white;">
<br>
<table border="0" width="100%" bgcolor="#6495ED">
<tbody><tr><td bgcolor="#FFFFFF">
&nbsp;&nbsp;&nbsp;'''AlphaNumericTextBox'''

* '''Платформа''': Web.
* '''Предназначение''': контрол, используемый для отображения и редактирования числовых и строковых значений. Поддерживаются словари допустимых для ввода символов.
* '''JavaScript API''': Нет.
* '''[Автодокументация](http://storm:20013/class_i_c_s_soft_1_1_s_t_o_r_m_n_e_t_1_1_web_1_1_ajax_controls_1_1_alpha_numeric_text_box.html)'''.
* '''[AlphaNumericTextBox на тестовом стенде](http://ru:6158/forms/Controls/AlphaNumericTextBox/)'''.

</td>
</tr></tbody></table></a>
</div>

# AlphaNumericTextBox
Контрол `ICSSoft.STORMNET.Web.AjaxControls.AlphaNumericTextBox` предназначен для полей, где допустимы только буквы и/или числа. Наследует `System.Web.UI.WebControls.TextBox`, реализует `ICSSoft.STORMNET.Web.AjaxControls.IAjaxGroupEditCompatible`.
<BR>Поддерживает вставку значений из буфера. 

## Настройка
Настройка свойств осуществляется при загрузке страницы в методе `PreApplyToControls()`.

### Тип
Для задания типа ввода используется свойство `Type` типа `AlphaNumericType`:
```cs
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
Для инициализации свойств контрола можно использовать статическое свойство-делегат `InitSettings` типа `[InitControlSettingsDelegate<>](init-control-settings-delegate.html)`, который будет вызван в конструкторе контрола.

## Примеры
Следующий код устанавливает тип контрола в Numeric (что позволит вводить в него только цифры), а также устанавливает словарь допустимых символов (что позволит вводить в него только цифры 0, 1 и 2):
```cs
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.NumericChars = "012";
```

Следующий код устанавливает минимальное и максимальное значение, допустимое для ввода:
```cs
ctrlAlpha.Type = AlphaNumericType.Numeric;
ctrlAlpha.Min = 1200;
ctrlAlpha.Max = 1300;
```

Следующий код устанавливает тип в AlphaNumeric (что позволит вводить и буквы и цифры), а также устанавливает словари допустмых символов. Таким образом, в контрол можно будет вводить только цифры 1 и 2, а также буквы 'a' и 'b':
```cs
ctrlAlpha.Type = AlphaNumericType.AlphaNumeric;
ctrlAlpha.NumericChars = "12";
ctrlAlpha.AlphabeticChars = "ab";
```

# Смотри также
* [DecimalTextBox - контрол для ввода дробных чисел](decimal-text-box.html).

