---
title: FuncToChar
sidebar: flexberry-orm_sidebar
keywords: DateTime, Flexberry ORM, Ограничения
summary: Параметры и пример использования функции FuncToChar
toc: true
permalink: ru/fo_func-to-char.html
lang: ru
---

`FuncToChar` - функция из [ExternalLangDef](fo_external-lang-def.html), служащая для того, чтобы задать преобразование выражения в строку. Это бывает необходимо для корректного сравнения значений выражений со строковыми константами. На данный момент реализована только для [MSSQLDataService](fo_mssql-data-service.html) и [OracleDataService](fo_oracle-data-service.html).

## Использование

Функция может использоваться в двух вариантах:

* С двумя аргументами, где первый - какое-либо выражение, возвращающее значение, а второй - число, означающее длину результирующей строки.
* С тремя аргументами, где первый - выражение, имеющее тип `DATETIME`, второй - длина строки, а третий - номер формата для отображения даты. Для задания последнего параметра можно использовать перечисление `ExternalLangDef.DateFormats`. В нем определены следующие значения:

    * `German` (DD.MM.YY)
    * `GermanWithCentury` (DD.MM.YYYY)
    * `Month` (DD Mon YY - сокращенное название месяца)
    * `MonthWithCentury` (DD Mon YYYY)
    * `Time` (hh:mi:ss - время)

Кроме этого, можно использовать, другие форматы, задавая их числом. Полный список находится [здесь](http://msdn.microsoft.com/ru-ru/library/ms187928.aspx) (это возможно только для [MSSQLDataService](fo_mssql-data-service.html).

### Пример использования

Получение выражения, приводящего дату к строке и сравнение с помощью функции LIKE

```csharp
var stringDate = _langdef.GetFunction(
					_langdef.funcToChar, 
					new VariableDef(_langdef.DateTimeType, propertyName),
					10, // Длина строки, содержащей дату в формате 'DD.MM.YYYY'
					ExternalLangDef.DateFormats.GermanWithCentury);

return _langdef.GetFunction(
			_langdef.funcLike,
			stringDate,
			string.Format("%{0}%", searchValue.Trim().ToLower()));
```
